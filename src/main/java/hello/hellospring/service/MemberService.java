package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//@Service -> SpringConfig.class 에서 스프링 bean을 직접 생성해줬기 때문에. 생략 가능.
public class MemberService
{

    private final MemberRepository memberRepository;

//    @Autowired  -> SpringConfig.class 에서 스프링 bean을 직접 생성해줬기 때문에. 생략 가능.
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member)
    {
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers()
    {
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID)
    {
        return memberRepository.findById(memberID);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }
}
