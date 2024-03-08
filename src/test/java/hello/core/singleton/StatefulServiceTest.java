package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulSerivceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1  = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2  = ac.getBean("statefulService", StatefulService.class);

        //ThreadA
        statefulService1.order("UserA", 10000);
        //ThreadB
        statefulService2.order("UserB", 20000);

        //ThreadA: 사용자 A가 주문 금액 조회
        int priceA = statefulService1.getPrice();
        //ThreadA: 사용자 A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("Price = "+priceA);

        Assertions.assertEquals(statefulService1.getPrice(), 20000);
    }


    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}