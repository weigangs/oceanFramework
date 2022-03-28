package com.lkyl.oceanframework.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@ConditionalOnProperty(name = "ocean.datasource.type", havingValue = "druid")
public class OceanDruidConfiguration {

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClassName);//如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setValidationQuery(dataSourceProperties.getValidateQuery());//用来检测连接是否有效
        dataSource.setTestOnBorrow(dataSourceProperties.getTestOnBorrow());//借用连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(dataSourceProperties.getTestOnReturn());//归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        //连接空闲时检测，如果连接空闲时间大于timeBetweenEvictionRunsMillis指定的毫秒，执行validationQuery指定的SQL来检测连接是否有效
        dataSource.setTestWhileIdle(dataSourceProperties.getTestWhileIdle());//如果检测失败，则连接将被从池中去除
        dataSource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());//1分钟
        dataSource.setMaxActive(dataSourceProperties.getMaxActive());
        dataSource.setInitialSize(dataSourceProperties.getInitialSize());
        return dataSource;
    }

}
