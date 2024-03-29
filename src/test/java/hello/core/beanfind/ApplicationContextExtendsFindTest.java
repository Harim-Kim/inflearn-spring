package hello.core.beanfind;

import hello.core.dicount.DiscountPolicy;
import hello.core.dicount.FixDiscountPolicy;
import hello.core.dicount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertInstanceOf( RateDiscountPolicy.class, rateDiscountPolicy);
    }


    @Test
    @DisplayName("특정 하위 타입으로 조회하면 됨 - 안 좋은 방법임")
    void findBeanBySubType(){
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertInstanceOf( RateDiscountPolicy.class, rateDiscountPolicy);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertEquals(beansOfType.size(),2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = "+key+" value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBean(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
//        Assertions.assertEquals(beansOfType.size(),2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = "+key+" value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        // DiscountPolicy로 조회시 아래 두개가 다 나온다.
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
