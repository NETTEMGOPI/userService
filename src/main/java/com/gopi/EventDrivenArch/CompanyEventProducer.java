package com.gopi.EventDrivenArch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyEventProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendCompanySubmissionEvent(Company company) {
        try {
            Map<String, Object> companyEvent = new HashMap<>();
            companyEvent.put("companyId", company.getId());
            companyEvent.put("companyName", company.getCompanyName());
            companyEvent.put("dba", company.getDba());
            companyEvent.put("companyUrl", company.getCompanyUrl());

            // Address Information
            companyEvent.put("street1", company.getStreet1());
            companyEvent.put("street2", company.getStreet2());
            companyEvent.put("city", company.getCity());
            companyEvent.put("state", company.getState());
            companyEvent.put("zipCode", company.getZipCode());
            companyEvent.put("country", company.getCountry());

            // Contact Information
            companyEvent.put("contactName", company.getContactName());
            companyEvent.put("contactTitle", company.getContactTitle());
            companyEvent.put("contactEmail", company.getContactEmail());
            companyEvent.put("contactPhone", company.getContactPhone());

            // Event Metadata
            companyEvent.put("eventType", "COMPANY_SUBMITTED");
            companyEvent.put("submissionTime", company.getSubmissionTime().toString());

            String message = objectMapper.writeValueAsString(companyEvent);

            kafkaTemplate.send("companySubmission", message);
            System.out.println("Company submission event sent to Kafka: " + message);

        } catch (Exception e) {
            System.err.println("Error sending company submission event: " + e.getMessage());
        }
    }
}