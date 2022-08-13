package earstone.springdb.service;

import earstone.springdb.domain.Member;
import earstone.springdb.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {

    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        // 1. 트랜잭션 시작
        // 2. DataSource 설정해서 갖고 있다.
        // 3. 커넥션 생성
        // 4. con.setAutoCommit(false);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // 비지니스 로직
            bizLogic(fromId, toId, money);
            // 트랜잭션 매니저는 트랜잭션 동기화 매니저에서 트랜잭션을 획득하고
            // 커밋 또는 롤백 후 동기화 매니저에 있는 리소스 포함 해당 리소스를 정리한다.
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        // 트랜잭션 동기화 매니저에서 커넥션 조회 후 SQL 실행
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validateMember(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private void validateMember(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생!!");
        }
    }
}
