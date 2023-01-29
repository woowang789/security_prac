package com.mycompany.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

	@ResponseBody
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@ResponseBody
	@GetMapping("/admin")
	public String admin(@AuthenticationPrincipal Member member) {
		log.info(member+"");
		return "admin";
	}
	
	@GetMapping("/user")
	public String user(@AuthenticationPrincipal Member member) {
		log.info(member+"");
		return "apiUser";
	}
	
	@ResponseBody
	@GetMapping("/api/user")
	public String getApiUser(@RequestParam String test, @AuthenticationPrincipal Member member) {
		log.info("/api/user test");
		log.info(member.toString());
		log.info(test+"");
		return "apiUser";
	}
	
	@ResponseBody
	@PostMapping("/api/user")
	public String getApiPost(@RequestBody Map<String, Object> map, @AuthenticationPrincipal Member member) {
		log.info("/api/user test post");
		log.info(member.toString());
		log.info(map+"");
		return "apiUser Get";
	}
}
