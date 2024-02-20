package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

    @Test
    void contextLoads() {
        OrderServiceTest orderTest = new OrderServiceTest();
        MemberServiceTest memberTest = new MemberServiceTest();

        memberTest.join();
        orderTest.createOrder();
    }

}
