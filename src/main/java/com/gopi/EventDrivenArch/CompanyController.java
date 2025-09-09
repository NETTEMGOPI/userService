package com.gopi.EventDrivenArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
//@CrossOrigin(origins = "*") // Allow CORS for frontend form
@CrossOrigin(origins = {"http://52.14.2.86"/*, "https://yourdomain.com"*/})
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/submit")
	public ResponseEntity<Map<String, Object>> submitCompany(@RequestBody CompanySubmissionRequest request) {
		Map<String, Object> response = new HashMap<>();

		try {
			// Validation
			if (request.getCompanyName() == null || request.getCompanyName().trim().isEmpty()) {
				response.put("success", false);
				response.put("message", "Company name is required");
				return ResponseEntity.badRequest().body(response);
			}

			if (request.getContactEmail() == null || request.getContactEmail().trim().isEmpty()) {
				response.put("success", false);
				response.put("message", "Contact email is required");
				return ResponseEntity.badRequest().body(response);
			}

			// Check for duplicates
			if (!companyService.isCompanyNameAvailable(request.getCompanyName())) {
				response.put("success", false);
				response.put("message", "Company with this name already exists");
				return ResponseEntity.badRequest().body(response);
			}

			if (!companyService.isContactEmailAvailable(request.getContactEmail())) {
				response.put("success", false);
				response.put("message", "Company with this contact email already exists");
				return ResponseEntity.badRequest().body(response);
			}

			// Process company submission
			Company savedCompany = companyService.submitCompany(request);
			System.out.println("Company submission processed: " + savedCompany.getCompanyName());

			response.put("success", true);
			response.put("message", "Company information submitted successfully");
			response.put("companyId", savedCompany.getId());
			response.put("companyName", savedCompany.getCompanyName());
			response.put("submissionTime", savedCompany.getSubmissionTime().toString());

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			System.err.println("Error processing company submission: " + e.getMessage());
			response.put("success", false);
			response.put("message", "Submission failed: " + e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/check-name/{companyName}")
	public ResponseEntity<Map<String, Object>> checkCompanyNameAvailability(@PathVariable String companyName) {
		Map<String, Object> response = new HashMap<>();

		boolean available = companyService.isCompanyNameAvailable(companyName);
		response.put("available", available);
		response.put("companyName", companyName);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/check-email/{contactEmail}")
	public ResponseEntity<Map<String, Object>> checkContactEmailAvailability(@PathVariable String contactEmail) {
		Map<String, Object> response = new HashMap<>();

		boolean available = companyService.isContactEmailAvailable(contactEmail);
		response.put("available", available);
		response.put("contactEmail", contactEmail);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getCompany(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			Company company = companyService.getCompanyById(id);
			response.put("success", true);
			response.put("company", company);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/health")
	public ResponseEntity<Map<String, String>> health() {
		Map<String, String> response = new HashMap<>();
		response.put("status", "UP");
		response.put("service", "Company Microservice");
		response.put("description", "Handles company information submissions");
		return ResponseEntity.ok(response);
	}
}