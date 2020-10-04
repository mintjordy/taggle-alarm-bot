package kr.taggle.alarmbot.client.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.taggle.alarmbot.client.SlackClientAPI;
import kr.taggle.alarmbot.client.dto.WebHookRequest;
import kr.taggle.alarmbot.client.scheduler.config.MessageProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Component
@Setter
@Getter
public class AlarmScheduler {
    private final SlackClientAPI slackClientAPI;
    private final MessageProperties messageProperties;

    @Scheduled(cron = "${slack.alarm.cron.monday-daily}", zone= "Asia/Seoul")
    public void alarmToMakeDailyLinkAtMonday() {
        final WebHookRequest webHookRequest = WebHookRequest.builder()
                .text(messageProperties.getDaily())
                .build();
        slackClientAPI.send(webHookRequest);
    }

    @Scheduled(cron = "${slack.alarm.cron.other-daily}", zone= "Asia/Seoul")
    public void alarmToMakeDailyLink() {
        final WebHookRequest webHookRequest = WebHookRequest.builder()
                .text(messageProperties.getDaily())
                .build();
        slackClientAPI.send(webHookRequest);
    }

    @Scheduled(cron = "${slack.alarm.cron.monday-time-schedule}", zone= "Asia/Seoul")
    public void alarmToAddTimeScheduleAtMonday() {
        final WebHookRequest webHookRequest = WebHookRequest.builder()
                .text(messageProperties.getTimeSchedule())
                .build();
        slackClientAPI.send(webHookRequest);
    }

    @Scheduled(cron = "${slack.alarm.cron.other-time-schedule}", zone= "Asia/Seoul")
    public void alarmToAddTimeSchedule() {
        final WebHookRequest webHookRequest = WebHookRequest.builder()
                .text(messageProperties.getTimeSchedule())
                .build();
        slackClientAPI.send(webHookRequest);
    }

    @Scheduled(cron = "${slack.alarm.cron.off-work}", zone= "Asia/Seoul")
    public void alarmToNextDayWorker() {
        final WebHookRequest webHookRequest = WebHookRequest.builder()
                .text(messageProperties.getOffWork())
                .build();
        slackClientAPI.send(webHookRequest);
    }

    @Scheduled(cron = "${slack.alarm.cron.what}", zone= "Asia/Seoul")
    public void yes() {
        final WebHookRequest webHookRequest = WebHookRequest.builder()
                .text(messageProperties.getOffWork())
                .build();
        slackClientAPI.send(webHookRequest);
    }
}
