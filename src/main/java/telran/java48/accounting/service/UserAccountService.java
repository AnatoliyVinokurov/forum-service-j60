package telran.java48.accounting.service;

import telran.java48.accounting.dto.RolesDto;
import telran.java48.accounting.dto.UserDto;
import telran.java48.accounting.dto.UserEditDto;
import telran.java48.accounting.dto.UserRegisterDto;
import telran.java48.security.model.Role;



public interface UserAccountService {

	UserDto register(UserRegisterDto userRegisterDto);

	UserDto getUser(String login);

	UserDto removeUser(String login);

	UserDto updateUser(String login, UserEditDto userEditDto);

	RolesDto changeRolesList(String login, Role role, boolean isAddRole);

	void changePassword(String login, String newPassword);

}
