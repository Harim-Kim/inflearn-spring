package hello.core;

import hello.core.dicount.DiscountPolicy;
import hello.core.dicount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberServiceImpl memberService(){
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl( memberRepository());
    }
    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderSerivce");
        return new OrderServiceImpl( memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
