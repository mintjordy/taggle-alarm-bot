package kr.taggle.alarmbot.role.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberRoles {
    private final String teamLeader;
    private final String scrumMaster;
    private final String writer;
    private final String locationBorrower;
    private final String calenderManager;
}
