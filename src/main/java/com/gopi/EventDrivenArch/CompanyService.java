package com.gopi.EventDrivenArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyEventProducer companyEventProducer;

	public Company submitCompany(CompanySubmissionRequest request) {

		// Validation
		if (companyRepository.existsByCompanyName(request.getCompanyName())) {
			throw new RuntimeException("Company with name '" + request.getCompanyName() + "' already exists");
		}

		if (companyRepository.existsByContactEmail(request.getContactEmail())) {
			throw new RuntimeException("Company with contact email '" + request.getContactEmail() + "' already exists");
		}

		// Create company entity
		Company company = new Company();

		// Company Information
		company.setCompanyName(request.getCompanyName());
		company.setDba(request.getDba());
		company.setCompanyUrl(request.getCompanyUrl());

		// Address Information
		company.setStreet1(request.getStreet1());
		company.setStreet2(request.getStreet2());
		company.setCity(request.getCity());
		company.setState(request.getState());
		company.setZipCode(request.getZipCode());
		company.setCountry(request.getCountry());

		// Contact Information
		company.setContactName(request.getContactName());
		company.setContactTitle(request.getContactTitle());
		company.setContactEmail(request.getContactEmail());
		company.setContactPhone(request.getContactPhone());

		// Metadata
		company.setSubmissionTime(LocalDateTime.now());

		// Save to database
		Company savedCompany = companyRepository.save(company);
		System.out.println("Company saved to database: " + savedCompany.getCompanyName());

		// Send company submission event to Kafka
		companyEventProducer.sendCompanySubmissionEvent(savedCompany);

		return savedCompany;
	}

	// Additional service methods
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
	}

	public boolean isCompanyNameAvailable(String companyName) {
		return !companyRepository.existsByCompanyName(companyName);
	}

	public boolean isContactEmailAvailable(String contactEmail) {
		return !companyRepository.existsByContactEmail(contactEmail);
	}
}