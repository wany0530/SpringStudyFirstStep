package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

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
