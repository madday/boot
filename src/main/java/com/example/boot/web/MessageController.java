package com.example.boot.web;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.boot.base.dto.ResultDto;
import com.example.boot.domain.TestDomain;
import com.example.boot.mq.ConfirmCallbackService;
import com.example.boot.util.StringUtil;

@RestController
@RequestMapping("/msg")
public class MessageController {
	@Autowired
	private ConfirmCallbackService  rabbitTemplate;
	@GetMapping("/send")
	public ResultDto<String> test() {
		TestDomain testDomain = new TestDomain();
		testDomain.setName("name");
		String pk = StringUtil.getUUID();
		testDomain.setPk(pk);
		CorrelationData data = new CorrelationData();
		data.setId(pk);
		rabbitTemplate.getRabbitTemplate().convertAndSend("amq.direct","test-queue", JSON.toJSONString(testDomain),data);
		ResultDto<String> retDto = new ResultDto<>();
		retDto.setData(pk);
		return retDto;
	}
}
