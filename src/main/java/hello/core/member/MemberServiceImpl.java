package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository repository) {
        this.memberRepository = repository;
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
