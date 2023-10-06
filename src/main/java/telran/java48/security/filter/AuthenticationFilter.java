package telran.java48.security.filter;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java48.accounting.dao.UserAccountRepository;
import telran.java48.accounting.dto.exceptions.UserNotFoundExeption;
import telran.java48.accounting.model.UserAccount;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

	final UserAccountRepository userAccountRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// System.out.println(request.getServletPath()+ " -> " + request.getMethod());
		// System.out.println(request.getHeader("Authorization"));

		String[] credentials = getCredentials(request.getHeader("Authorization"));
		UserAccount userAccount = userAccountRepository.findById(credentials[0]).orElseThrow(UserNotFoundExeption::new);
		if(!BCrypt.checkpw(credentials[1], userAccount.getPassword())) {
			throw new UserNotFoundExeption();
		}
		chain.doFilter(request, response);
	}

	private String[] getCredentials(String header) {
		String token = header.substring(6);
		String decode = new String(Base64.getDecoder().decode(token));
		return decode.split(":");
	}

}
