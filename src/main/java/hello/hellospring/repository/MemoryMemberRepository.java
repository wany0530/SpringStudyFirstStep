package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository -> SpringConfig.class 에서 스프링 bean을 직접 생성해줬기 때문에. 생략 가능.
public class MemoryMemberRepository implements MemberRepository
{
    public static Map<Long, Member> store = new HashMap<>();
    public static long sequence = 0L;

    @Override
    public Member save(Member member)
    {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findbyId(Long id)
    {
        return Optional.ofNullable(store.get(id)); //Optional.ofNullable()을 쓰면 안에 값이 null이여도 괜찮다.
    }

    @Override
    public Optional<Member> findByName(String name)
    {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll()
    {
        return new ArrayList<>(store.values());
    }

    public void clearStore()
    {
        store.clear();
    }

}
