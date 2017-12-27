package com.errui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author Kevin
 * @date 2017-06-13
 * @param args
 * @comment 範例說明
 * 1.建立資料庫
    CREATE DATABASE springbootdb;
 * 2.建立資料表
   DROP TABLE IF EXISTS  `city`;
   CREATE TABLE `city` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
   `province_id` int(10) unsigned  NOT NULL COMMENT '省份编号',
   `city_name` varchar(25) DEFAULT NULL COMMENT '城市名称',
   `description` varchar(25) DEFAULT NULL COMMENT '描述',
   PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 * 3.插入數據
    INSERT city VALUES (1 ,1,'新北市','BY ERRUI 的家在新北市');
   4.根據 ID,獲取城市信息
   GET http://127.0.0.1:8080/api/city/1
   5.更新程式信息
   PUT http://127.0.0.1:8080/api/city
   6.刪除程式信息
   ELETE http://127.0.0.1:8080/api/city/2
   
 */
// Spring Boot 應用的標識
@SpringBootApplication
// mapper 接口類掃描包配置
@MapperScan("com.errui.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

