package com.example.boot.util;

import java.util.UUID;

public class StringUtil {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
