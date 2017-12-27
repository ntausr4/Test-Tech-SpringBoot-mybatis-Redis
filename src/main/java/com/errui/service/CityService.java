package com.errui.service;


import com.errui.domain.City;


/**
 * @author Kevin
 * @date
 * @comment
 */
public interface CityService {
    /**
     * @author Kevin
     * @param id
     * @return
     * @comment 根据城市 ID,查询城市信息
     */
    City findCityById(Long id);

    /**
     * @author Kevin
     * @param city
     * @return
     * @comment 新增城市信息
     */
    Long saveCity(City city);

    /**
     * @author Kevin
     * @param city
     * @return
     * @comment 更新城市信息
     */
    Long updateCity(City city);

    /**
     * @author Kevin
     * @param id
     * @return
     * @comment 根据城市 ID,删除城市信息
     */
    Long deleteCity(Long id);
}
