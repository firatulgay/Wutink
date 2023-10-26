package com.fulgay.wutink.security.jwt.manager;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EncryptionManager {

	@Value("${jwt.token.secret}")
	private String salt;

	public String encrypt(String payload) {
		
		if(!StringUtils.hasText(payload)) {
			return "";
		}
		return DigestUtils.sha256Hex(payload + salt); // Burada ticket dediğimiz yani kullanıcıyı tanıyacağımız encrypted bir değer döndürüyorum.
	}
}
