package kr.taggle.alarmbot.role;

import org.springframework.stereotype.Service;

import kr.taggle.alarmbot.role.domain.MemberRoles;
import kr.taggle.alarmbot.role.domain.MemberRolesRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberRolesService {
    private final MemberRolesRepository memberRolesRepository;

    public MemberRoles findCurrentDayRoles() {
        return memberRolesRepository.findRoleByCurrentDay();
    }
}
