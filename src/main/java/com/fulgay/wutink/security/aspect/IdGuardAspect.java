package com.fulgay.wutink.security.aspect;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.security.annotation.IdGuard;
import com.fulgay.wutink.security.jwt.manager.EncryptionManager;
import com.fulgay.wutink.security.jwt.manager.JwtTokenManager;
import com.fulgay.wutink.service.SessionService;
import com.fulgay.wutink.service.UserService;
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

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;
	

	@Before("@annotation(idGuard)") // idGuard annotation gördüğün metodda metoda girmeden burası çalışacak.
	public void execute(JoinPoint joinPoint, IdGuard idGuard) {

		int argIndex = idGuard.parameterIndex();
		if(argIndex < 0){
			argIndex = 0;
		}

		Object[] args = joinPoint.getArgs();
		if (args == null || args.length == 0) {
			String userName = sessionService.getSessionUserName();
			User user = userService.findUserByUserName(userName);
			args = new Object[]{user.getId()};
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

		/**
		 * Burası herhangi bir idor zaafiyetine önlem olarak kullanılıyor.
		 * For ex : Cookiedeki jwt çalınsa veya kullanıcı findUserById endpointine
		 * farklı bir id gönderse bile kendi id sinin dışında bir id gönderdiğini anlıyor ve isteği geri çeviriyor.
		 */
		String realTicket = tokenManager.extractTicket(token);
		if(!(StringUtils.hasText(expectedTicket) && expectedTicket.contains(realTicket)) ) {
			throw new RuntimeException("ACCESS ERROR FOR INVALID RESOURCE");
		}
	}
}
