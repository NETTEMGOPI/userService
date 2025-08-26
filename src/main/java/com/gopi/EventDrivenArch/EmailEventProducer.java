package com.gopi.EventDrivenArch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopi.EventDrivenArch.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Users users;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendEmailEvent(Users users) {
        try {
            Map<String, Object> emailEvent = new HashMap<>();
            emailEvent.put("userId", users.getId());
            emailEvent.put("email", users.getEmailID());
            emailEvent.put("firstName", users.getFirstName());
            emailEvent.put("eventType", "USER_REGISTERED");

            String message = objectMapper.writeValueAsString(emailEvent);

            kafkaTemplate.send("sendEmail", message);
            System.out.println("📧 Email event sent to Kafka: " + message);

        } catch (Exception e) {
            System.err.println("❌ Error sending email event: " + e.getMessage());
        }
    }
}
