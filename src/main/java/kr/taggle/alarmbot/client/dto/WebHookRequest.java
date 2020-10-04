package kr.taggle.alarmbot.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WebHookRequest {
    private String text;

    @Builder
    public WebHookRequest(String text) {
        this.text = text;
    }
}
