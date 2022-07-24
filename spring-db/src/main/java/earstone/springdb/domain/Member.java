package earstone.springdb.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {

    private String memberId;
    private int money;

    public void setInfo(String member_id, int money) {
        this.memberId = member_id;
        this.money = money;
    }
}
