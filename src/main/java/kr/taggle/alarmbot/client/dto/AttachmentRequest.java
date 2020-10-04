package kr.taggle.alarmbot.client.dto;

import static java.util.Collections.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AttachmentRequest {
    private final String color;
    private final String pretext;
    private final String title;
    private final String title_link;
    private final String text;

    public WebHookRequest toWebHookRequest() {
        return new WebHookRequest(singletonList(this));
    }
}
