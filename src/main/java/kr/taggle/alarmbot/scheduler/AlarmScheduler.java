package kr.taggle.alarmbot.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlarmScheduler {
    @Scheduled(cron = "0 45 12 ? * MON *", zone= "Asia/Seoul")
    public void alarmToMakeDailyLinkAtMonday() {
        System.out.println("굿모닝! 오늘 데일리 담당자는 데일리를 위한 행아웃 링크를 만들어주세요!");
    }

    @Scheduled(cron = "0 45 9 ? * TUE-FRI *", zone= "Asia/Seoul")
    public void alarmToMakeDailyLink() {
        System.out.println("굿모닝! 오늘 데일리 담당자 ooo는 데일리를 위해 링크를 만들어주세요!");
    }

    @Scheduled(cron = "0 0 13-17 * * MON", zone= "Asia/Seoul")
    public void alarmToAddTimeScheduleAtMonday() {
        System.out.println("정각 떙! 전원 한시간 동안 할 일을 Notion 타임 테이블에 등록해주세요!");
    }

    @Scheduled(cron = "0 0 10-17 * * TUE-FRI", zone= "Asia/Seoul")
    public void alarmToAddTimeSchedule() {
        System.out.println("정각 떙! 전원 한시간 동안 할 일을 Notion 타임 테이블에 등록해주세요!");
    }

    @Scheduled(cron = "0 0 18 * * MON-FRI", zone= "Asia/Seoul")
    public void alarmToNextDayWorker() {
        System.out.println("모두 고생 많았습니다. 퇴근시간! ETC 담당자는 다음날 Notion 타임 테이블을 추가해주세요!");
    }
}
