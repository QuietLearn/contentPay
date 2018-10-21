package com.stylefeng.guns.core.mutidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author fengshuonan
 * AbstractRoutingDataSource 路由datasource
 * 当执行操作数据库时，需要告诉它使用哪个数据库，你用哪个要告知
 * 如何决定当前环境使用哪个datasource
 * @date 2017年3月5日 上午9:11:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	//决定当前的key在MultiDataSourceConfig里有写DynamicDataSource
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}

}
