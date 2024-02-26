package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 핵심
 * 1. 전체 메소드 테스트할 때, 메소드 순서는 보장되지 않는다.
 * 2. 때문에, findAll이 먼저 실행되고 findByName에서 spring1으로 이름을 찾을때 객체가 2개가 나와버리니 이놈이 분간을 못하기 때문이다.
 * 3. @AfterEach 는 각 메소드가 끝낼때 마다 실행시켜주는 메소드이다.
 */
public class MemoryMemberRepositoryTest
{
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach()
    {
        repository.clearStore(); // findAll 다음에 findByName 실행할 때 오류가 발생하니까. 각 메소드마다 store Map을 초기화시킴.
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
