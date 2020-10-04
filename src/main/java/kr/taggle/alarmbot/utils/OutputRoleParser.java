package kr.taggle.alarmbot.utils;

import kr.taggle.alarmbot.role.domain.MemberRoles;

public class OutputRoleParser {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String MEMBER_ROLE_FORMAT = "팀장: %s%s데일리: %s%s서기: %s%s장소대여: %s%s타임테이블 관리자: %s";

    private OutputRoleParser() {
    }

    public static String parseMemberRoles(MemberRoles roles) {
        return String.format(MEMBER_ROLE_FORMAT,
                MarkdownParser.toItalic(roles.getTeamLeader()),
                LINE_SEPARATOR,
                MarkdownParser.toItalic(roles.getScrumMaster()),
                LINE_SEPARATOR,
                MarkdownParser.toItalic(roles.getWriter()),
                LINE_SEPARATOR,
                MarkdownParser.toItalic(roles.getLocationBorrower()),
                LINE_SEPARATOR,
                MarkdownParser.toItalic(roles.getCalenderManager())
        );
    }
}
