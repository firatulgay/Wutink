package com.fulgay.wutink.security.aspect;

import com.fulgay.wutink.security.annotation.IdGuard;
import com.fulgay.wutink.security.jwt.manager.EncryptionManager;
import com.fulgay.wutink.security.jwt.manager.JwtTokenManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class IdGuardAspect {
	
	@Autowired
	private JwtTokenManager tokenManager;
	
	@Autowired
	private EncryptionManager encryptionManager;
	

	@Before("@annotation(idGuard)")
	public void execute(JoinPoint joinPoint, IdGuard idGuard) {
	
		int argIndex = idGuard.parameterIndex();
		if(argIndex < 0){
			argIndex = 0;
		}
		
		Object[] args = joinPoint.getArgs();
		if(args == null || args.length == 0) {
			throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
		}
		
		Object idParameterObj = args[argIndex];
		if(idParameterObj == null) {
			throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
		}
		
		String expectedTicket = encryptionManager.encrypt(idParameterObj.toString());
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String token = tokenManager.extractJwtFromRequest(request);
		if(!StringUtils.hasText(token)) {
			throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
		}
		
		String realTicket = tokenManager.extractTicket(token);
		if(!(StringUtils.hasText(expectedTicket) && expectedTicket.contains(realTicket)) ) {
			throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
		}
	}
}
