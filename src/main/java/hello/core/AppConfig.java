package hello.core;

import hello.core.dicount.DiscountPolicy;
import hello.core.dicount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberServiceImpl memberService(){
        return new MemberServiceImpl( memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl( memberRepository(), discountPolicy());
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
