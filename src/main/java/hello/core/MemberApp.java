package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args){
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // name은 method 이름으로! method로 등록되므로.  반환 타입.

        Member member = new Member(1L, "harim", Grade.VIP);
        memberService.join(member);

        Member findMemeber = memberService.findMember(1L);
        System.out.println("new member: "+ member.getName());
        System.out.println("find member: " + findMemeber.getName());
    }
}
