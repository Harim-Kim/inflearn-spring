package hello.core.dicount;

import hello.core.member.Member;

public interface DiscountPolicy {
    int discount (Member member, int price);
}
