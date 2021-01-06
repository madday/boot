package com.example.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.domain.TestDomain;
import com.example.boot.service.TestService;
import com.example.boot.util.StringUtil;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private TestService testService;
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@GetMapping("/test")
	public int test() {
		logger.info("test function");
		TestDomain domain = new TestDomain();
		String pk = StringUtil.getUUID();
		domain.setPk(pk);
		domain.setName("zhang");
		int result = testService.insert(domain);
		return result;
	}
}
