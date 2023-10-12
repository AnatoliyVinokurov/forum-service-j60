package telran.java48.security.model;

import java.security.Principal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User implements Principal {
	
	String userName;
	@Getter
	//Set<String> roles;
	Set<Role> roles;
	
	@Override
	public String getName() {
		return userName;
	}

}
