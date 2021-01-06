package com.example.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.domain.TestDomain;
import com.example.boot.mapper.TestMapper;

@Service
public class TestService {
	@Autowired
	private TestMapper testMapper;
	
	public Integer insert(TestDomain domain) {
		return this.testMapper.insert(domain);
	}
}
