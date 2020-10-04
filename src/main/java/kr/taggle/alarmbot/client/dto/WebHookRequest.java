package kr.taggle.alarmbot.client.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WebHookRequest {
    private final List<AttachmentRequest> attachments;
}
