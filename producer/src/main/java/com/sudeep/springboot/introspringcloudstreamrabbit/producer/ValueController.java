package com.sudeep.springboot.introspringcloudstreamrabbit.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ValueController {

    private StreamBridge streamBridge;

    public ValueController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("values/{value}")
    public ResponseEntity<String> values(@PathVariable String value) {
        log.info("Sending value {} to topic", value);
        streamBridge.send("values-topic", value);
        return ResponseEntity.ok("ok");
    }
}
