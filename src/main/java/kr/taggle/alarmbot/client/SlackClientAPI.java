package kr.taggle.alarmbot.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import kr.taggle.alarmbot.client.dto.WebHookRequest;

@Component
public class SlackClientAPI {
    private final RestTemplate restTemplate;

    @Value("${web_hook_uri}")
    private String webHookURI;

    public SlackClientAPI(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void send(WebHookRequest webHookRequest) {
        final ResponseEntity<String> response = restTemplate.postForEntity(URI.create(webHookURI),
                webHookRequest, String.class);
    }
}
