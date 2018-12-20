package com.ceh.spring.websocket.config;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by enHui.Chen on 2018/6/25.
 */
@Slf4j
@SuppressWarnings("all")
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = MybatisConfiguration.BASEPACKAGE)
public class MybatisConfiguration implements InitializingBean {

    public static final String BASEPACKAGE = "com.ceh.spring.websocket";

    @Autowired
    private CuxMybatisConfiguration cuxMybatisConfiguration;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置springboot对mybatis的bean扫描的类
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 不为Null,不为空字符串，且不为纯空格
        log.info("getMapperLocations:{}",cuxMybatisConfiguration.getMapperLocations()[0]);
        if (StringUtils.hasText(cuxMybatisConfiguration.getMapperLocations()[0])) {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(cuxMybatisConfiguration.getMapperLocations()[0]));
        }

        // 不为Null,不为空字符串，且不为纯空格,若yml文件指定全局配置文件，其余配置失效
        if (StringUtils.hasText(cuxMybatisConfiguration.getConfigLocation())) {
            sqlSessionFactoryBean.setConfigLocation(resolver.getResource(cuxMybatisConfiguration.getConfigLocation()));
            return sqlSessionFactoryBean.getObject();
        }

        List<CuxMybatisConfiguration.MyInterceptor> myInterceptors = cuxMybatisConfiguration.getMyInterceptor();
        if (!CollectionUtils.isEmpty(myInterceptors)) {
            Interceptor[] interceptors = new Interceptor[myInterceptors.size()];
            for (int i = 0; i < myInterceptors.size(); i++) {
                interceptors[i] = (Interceptor) Class.forName(myInterceptors.get(i).getInterceptor()).newInstance();
                interceptors[i].setProperties(myInterceptors.get(i).getProperties());
            }
            sqlSessionFactoryBean.setPlugins(interceptors);
        }

        sqlSessionFactoryBean.setTypeAliasesPackage(cuxMybatisConfiguration.getTypeAliasesPackage());
        sqlSessionFactoryBean.setTypeHandlersPackage(cuxMybatisConfiguration.getTypeHandlersPackage());
        sqlSessionFactoryBean.setConfiguration(cuxMybatisConfiguration.getConfiguration());

        return sqlSessionFactoryBean.getObject();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("===================mybatis初始化完毕===================");
    }
}
