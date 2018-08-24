package com.coolgua.signup.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 配置数据源
 * 
 */
@Configuration
@MapperScan(basePackages = "com.coolgua.signup.mapper")
public class DataSourceConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	/**
	 * 创建druid数据源
	 * 
	 * @param dataSourceName
	 * @return
	 */
	public DataSource createDataSource(final String dataSourceName) {
		// 使用Druid连接池连接数据库
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		druidDataSource.setUrl(String.format(env.getProperty("jdbc.url"), dataSourceName));
		druidDataSource.setUsername(env.getProperty("jdbc.username"));
		druidDataSource.setPassword(env.getProperty("jdbc.password"));
		return druidDataSource;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		sqlSessionFactoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));// 指定xml文件位置
		return sqlSessionFactoryBean.getObject();
	}



}
