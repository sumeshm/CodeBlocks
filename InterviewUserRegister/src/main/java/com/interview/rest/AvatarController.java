package com.interview.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.common.InputValidationException;
import com.interview.model.AvatarRequest;
import com.interview.service.IAvatarService;
import com.interview.service.IExclusionService;

@RestController
@RequestMapping("/avatars")
public class AvatarController {

	// http://localhost:8080/avatars/

	private static final Logger LOGGER = LoggerFactory.getLogger(AvatarController.class);

	@Autowired
	private IAvatarService avatarService;

	@Autowired
	private IExclusionService exclusionService;

	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> register(@RequestBody AvatarRequest avatarRequest) {
		LOGGER.info("input avatar request=" + avatarRequest.toString());

		String retUrl;
		try {
			retUrl = avatarService.createAvatar(avatarRequest);
			return new ResponseEntity<String>(retUrl, HttpStatus.CREATED);
		} catch (InputValidationException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed to create AVATAR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/validate", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> validate(@RequestParam("ssn") String ssn, @RequestParam("dob") String dob) {
		LOGGER.info("input params: ssn=" + ssn + ", dob=" + dob);

		try {
			Boolean retVal = exclusionService.validate(dob, ssn);
			return new ResponseEntity<String>("BLACKLISTED=" + retVal.toString(), HttpStatus.OK);
		} catch (InputValidationException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed to validate AVATAR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/blacklist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> blacklist(@RequestBody Map<String, String> blacklistMap) {
		LOGGER.info("input params: blacklistMap=" + blacklistMap);

		try {
			Map<String, String> retVal = exclusionService.addBlacklist(blacklistMap);
			return new ResponseEntity<Map<?, ?>>(retVal, HttpStatus.OK);
		} catch (InputValidationException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed to blacklist AVATARs", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/blacklist")
	public ResponseEntity<?> blacklist() {
		try {
			exclusionService.clearBlacklist();
			return new ResponseEntity<String>("DELETED BLACKLIST", HttpStatus.OK);
		} catch (InputValidationException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed to blacklist AVATARs", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
