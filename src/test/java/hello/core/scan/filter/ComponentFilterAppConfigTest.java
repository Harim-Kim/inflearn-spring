package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Test
    void FilterScan(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = applicationContext.getBean("beanA", BeanA.class);
        Assertions.assertNotNull(beanA);

        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)},
            excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
                              @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class) // class 지정
            }
    )
    static class ComponentFilterAppConfig{

    }
}
