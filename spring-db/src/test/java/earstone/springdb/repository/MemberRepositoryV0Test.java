package earstone.springdb.repository;

import earstone.springdb.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @DisplayName("멤버 CRUD 기능을 테스트한다.")
    @Test
    void crud() throws SQLException {
        // given
        String memberId = "memberV0";

        // save
        Member member = new Member(memberId, 10_000);
        Member savedMember = repository.save(member);

        // findById
        Member findMember = repository.findById(savedMember.getMemberId());
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(member);

        // update
        repository.update(memberId, 20_000);
        Member updatedMember = repository.findById(savedMember.getMemberId());
        log.info("updatedMember={}", updatedMember);
        assertThat(updatedMember.getMoney()).isEqualTo(20_000);

        // delete
        repository.delete(memberId);
        assertThatThrownBy(() -> repository.findById(memberId))
                .isExactlyInstanceOf(NoSuchElementException.class);
    }

}