package kr.taggle.alarmbot.client.scheduler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "slack.alarm.message.title-link")
public class TitleLinkProperties {
    private String role;
    private String timeSchedule;
}
