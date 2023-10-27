package telran.java48.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserProfile extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3890063154971793848L;
	private boolean passwordNotExpired;

	public UserProfile(String username, String password, Collection<? extends GrantedAuthority> authorities,
			boolean passwordNotExpired) {
		super(username, password, authorities);
		this.passwordNotExpired = passwordNotExpired;
	}

	public boolean isPasswordNotExpired() {
		return passwordNotExpired;
	}
}