package pl.recompiled.hibernatenplusonedemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("integration-test")
@RequiredArgsConstructor
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

    private final CountingQueryExecutionListener queryExecutionListener;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource dataSource) {
            return ProxyDataSourceBuilder.create(dataSource)
                    .listener(queryExecutionListener)
                    .build();
        } else {
            return bean;
        }
    }
}
