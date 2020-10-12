package kr.taggle.alarmbot.client.scheduler;

import static java.time.format.DateTimeFormatter.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.taggle.alarmbot.client.SlackClientAPI;
import kr.taggle.alarmbot.client.dto.AttachmentRequest;
import kr.taggle.alarmbot.client.scheduler.config.PreTextProperties;
import kr.taggle.alarmbot.client.scheduler.config.TextProperties;
import kr.taggle.alarmbot.client.scheduler.config.TitleLinkProperties;
import kr.taggle.alarmbot.client.scheduler.config.TitleProperties;
import kr.taggle.alarmbot.role.MemberRolesService;
import kr.taggle.alarmbot.utils.MarkdownParser;
import kr.taggle.alarmbot.utils.OutputRoleParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Getter
public class AlarmScheduler {
    private static final String SERVER_ZONE = "Asia/Seoul";
    private static final String GREEN_COLOR = "#36a64f";
    private static final DateTimeFormatter TODAY_FORMAT = ofPattern("MM월 dd일");
    private static final DateTimeFormatter CURRENT_HOUR_FORMAT = ofPattern("hh시");

    private final SlackClientAPI slackClientAPI;
    private final MemberRolesService memberRolesService;
    private final TitleProperties titleProperties;
    private final TitleLinkProperties titleLinkProperties;
    private final TextProperties textProperties;
    private final PreTextProperties preTextProperties;

    @Scheduled(cron = "${slack.alarm.cron.monday-daily}", zone = SERVER_ZONE)
    public void alarmToMakeDailyLinkAtMonday() {
        final AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color(GREEN_COLOR)
                .pretext(MarkdownParser.toChannel(String.format(preTextProperties.getRole(), parseTodayFormat())))
                .title(titleProperties.getRole())
                .title_link(titleLinkProperties.getRole())
                .text(parseDailyMessage())
                .build();
        slackClientAPI.send(attachmentRequest.toWebHookRequest());
    }

    @Scheduled(cron = "${slack.alarm.cron.other-daily}", zone = SERVER_ZONE)
    public void alarmToMakeDailyLink() {
        final AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color(GREEN_COLOR)
                .pretext(MarkdownParser.toChannel(String.format(preTextProperties.getRole(), parseTodayFormat())))
                .title(titleProperties.getRole())
                .title_link(titleLinkProperties.getRole())
                .text(parseDailyMessage())
                .build();
        slackClientAPI.send(attachmentRequest.toWebHookRequest());
    }

    private String parseDailyMessage() {
        return OutputRoleParser.parseMemberRoles(memberRolesService.findCurrentDayRoles());
    }

    @Scheduled(cron = "${slack.alarm.cron.monday-time-schedule}", zone = SERVER_ZONE)
    public void alarmToAddTimeScheduleAtMonday() {
        final AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color(GREEN_COLOR)
                .pretext(MarkdownParser.toChannel(String.format(preTextProperties.getTimeSchedule(), parseCurrentHourFormat())))
                .title(titleProperties.getTimeSchedule())
                .title_link(titleLinkProperties.getTimeSchedule())
                .text(textProperties.getTimeSchedule())
                .build();
        slackClientAPI.send(attachmentRequest.toWebHookRequest());
    }

    @Scheduled(cron = "${slack.alarm.cron.other-time-schedule}", zone = SERVER_ZONE)
    public void alarmToAddTimeSchedule() {
        final AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color(GREEN_COLOR)
                .pretext(MarkdownParser.toChannel(String.format(preTextProperties.getTimeSchedule(), parseCurrentHourFormat())))
                .title(titleProperties.getTimeSchedule())
                .title_link(titleLinkProperties.getTimeSchedule())
                .text(textProperties.getTimeSchedule())
                .build();
        slackClientAPI.send(attachmentRequest.toWebHookRequest());
    }

    @Scheduled(cron = "${slack.alarm.cron.off-work}", zone = SERVER_ZONE)
    public void alarmToNextDayWorker() {
        final AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color(GREEN_COLOR)
                .pretext(MarkdownParser.toChannel(preTextProperties.getOffWork()))
                .title(titleProperties.getOffWork())
                .title_link(titleLinkProperties.getTimeSchedule())
                .text(textProperties.getOffWork())
                .build();
        slackClientAPI.send(attachmentRequest.toWebHookRequest());
    }

    private String parseTodayFormat() {
        return LocalDateTime.now().format(TODAY_FORMAT);
    }

    private String parseCurrentHourFormat() {
        return LocalDateTime.now().format(CURRENT_HOUR_FORMAT);
    }
}