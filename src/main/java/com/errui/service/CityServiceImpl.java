package com.errui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


import com.errui.dao.CityDao;
import com.errui.domain.City;
import com.errui.service.CityService;

/**
 * @author Kevin
 * @date
 * @comment
 * @comment 首先這裡注入了 RedisTemplate 對象。
 * 聯想到 Spring 的 JdbcTemplate ，RedisTemplate 封裝了 RedisConnection，具有連接管理，序列化和 Redis 操作等功能。
 * 還有針對 String 的支持對象 StringRedisTemplate。
 * Redis 操作視圖接口類用的是 ValueOperations，對應的是 Redis String/Value 操作。
 * 還有其他的操作視圖，ListOperations、SetOperations、ZSetOperations 和 HashOperations 。ValueOperations 
 * 插入緩存是可以設置失效時間，這裡設置的失效時間是 10 s。
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate<String, City> redisTemplate;

    /**
     * 獲取城市邏輯：
     * 如果緩存存在，從緩存中獲取城市信息
     * 如果緩存不存在，從 DB 中獲取城市信息，然後插入緩存
     */
    public City findCityById(Long id) {
        // 從緩存中獲取城市信息
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 緩存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 從緩存中獲取了城市 >> " + city.toString());
            return city;
        }

        // 從 DB 中獲取城市信息
        City city = cityDao.findById(id);

        // 插入緩存
        operations.set(key, city, 10, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入緩存 >> " + city.toString());

        return city;
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    /**
     * 更新城市邏輯：
     * 如果緩存存在，刪除
     * 如果緩存不存在，不操作
     */
    @Override
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);

        // 緩存存在，刪除緩存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 從緩存中刪除城市 >> " + city.toString());
        }

        return ret;
    }

    @Override
    public Long deleteCity(Long id) {

        Long ret = cityDao.deleteCity(id);

        // 緩存存在，刪除緩存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.deleteCity() : 從緩存中刪除城市 ID >> " + id);
        }
        return ret;
    }

}
