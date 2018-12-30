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
import com.interview.service.IExclusionService;
import com.interview.service.IRegistrationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/avatars")
public class AvatarController {

	// http://localhost:8080/avatars/

	private static final Logger LOGGER = LoggerFactory.getLogger(AvatarController.class);

	@Autowired
	private IRegistrationService registrationService;

	@Autowired
	private IExclusionService exclusionService;

	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiOperation(value = "Register Avatar", notes = "Register an Avatar if not blacklisted and not pre-registered")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful registration"),
			@ApiResponse(code = 400, message = "Avatar details are invalid OR Already registered OR Avatar is blacklisted") })
	public ResponseEntity<?> register(@RequestBody AvatarRequest avatarRequest) {
		logAPI("register", avatarRequest.toString());

		String retUrl;
		try {
			retUrl = registrationService.createAvatar(avatarRequest);
			return new ResponseEntity<String>(retUrl, HttpStatus.CREATED);
		} catch (InputValidationException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed to create AVATAR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/validate", produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiOperation(value = "Validate if Blacklisted", notes = "Validate given pair(ssn, dob) is blacklisted")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "TRUE if blacklisted else FALSE"),
			@ApiResponse(code = 400, message = "Avatar details (DOB and/or SSN) are invalid") })
	public ResponseEntity<?> validate(@RequestParam("ssn") String ssn, @RequestParam("dob") String dob) {
		logAPI("validate", "SSN=" + ssn + ", DOB=" + dob);

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
	@ApiOperation(value = "Add to Blacklist", notes = "Add given map(ssn, dob) to blacklist")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Full set of blacklisted avatars (SSN-DOB pairs)"),
			@ApiResponse(code = 400, message = "Avatar details (DOB and/or SSN) are invalid") })
	public ResponseEntity<?> addToBlacklist(@RequestBody Map<String, String> blacklistMap) {
		logAPI("blacklist-add", blacklistMap.toString());

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
	@ApiOperation(value = "Clear Blacklist", notes = "Clear the complete blacklist")
	public ResponseEntity<?> clearBlacklist() {
		logAPI("blacklist-clear", "NIL");

		try {
			exclusionService.clearBlacklist();
			return new ResponseEntity<String>("DELETED BLACKLIST", HttpStatus.OK);
		} catch (InputValidationException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed to blacklist AVATARs", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void logAPI(String apiName, String inputRequest) {
		LOGGER.info("");
		LOGGER.info("-------------------------------------------------------");
		LOGGER.info("--> API   : " + apiName);
		LOGGER.info("--> INPUT : " + inputRequest);
		LOGGER.info("-------------------------------------------------------");
		LOGGER.info("");
	}
}
