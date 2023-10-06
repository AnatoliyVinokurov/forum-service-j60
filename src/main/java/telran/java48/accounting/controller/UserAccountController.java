package telran.java48.accounting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java48.accounting.dto.RolesDto;
import telran.java48.accounting.dto.UserDto;
import telran.java48.accounting.dto.UserEditDto;
import telran.java48.accounting.dto.UserRegisterDto;
import telran.java48.accounting.service.UserAccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {

	final UserAccountService userAccountService;

	@PostMapping("/register")
	public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return userAccountService.register(userRegisterDto);
	}

	@PostMapping("/login")
	public UserDto login() {
		//TODO
		return null;
	}

	@GetMapping("/user/{login}")
	public UserDto getUser(@PathVariable String login) {
		return userAccountService.getUser(login);
	}

	@DeleteMapping("/user/{login}")
	public UserDto removeUser(@PathVariable String login) {
		return userAccountService.removeUser(login);
	}

	@PutMapping("/user/{login}")
	public UserDto updateUser(@PathVariable String login, @RequestBody UserEditDto userEditDto) {
		return userAccountService.updateUser(login, userEditDto);
	}

	@PutMapping("/user/{login}/role/{role}")
	public RolesDto addRole(@PathVariable String login, @PathVariable String role) {
		return userAccountService.changeRolesList(login, role, true);
	}

	@DeleteMapping("/user/{login}/role/{role}")
	public RolesDto deleteRole(@PathVariable String login, @PathVariable String role) {
		return userAccountService.changeRolesList(login, role, false);
	}

	@PutMapping("/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword() {
		//TODO

	}

}
