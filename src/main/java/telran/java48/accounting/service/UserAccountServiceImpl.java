package telran.java48.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java48.accounting.dao.UserAccountRepository;
import telran.java48.accounting.dto.RolesDto;
import telran.java48.accounting.dto.UserDto;
import telran.java48.accounting.dto.UserEditDto;
import telran.java48.accounting.dto.UserRegisterDto;
import telran.java48.accounting.dto.exceptions.UserExistsExeption;
import telran.java48.accounting.dto.exceptions.UserNotFoundExeption;
import telran.java48.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
	final UserAccountRepository userAccountRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		if (userAccountRepository.existsById(userRegisterDto.getLogin())) {
			throw new UserExistsExeption();
		}
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
		userAccount.addRole("USER");
		userAccountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundExeption::new);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto removeUser(String login) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundExeption::new);
		userAccountRepository.delete(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundExeption::new);
		if(userEditDto.getFirstName() != null) {
			userAccount.setFirstName(userEditDto.getFirstName());
		}
		if(userEditDto.getLastName() != null) {
			userAccount.setLastName(userEditDto.getLastName());
		}
		userAccountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundExeption::new);
		boolean res;
		if(isAddRole) {
			res = userAccount.addRole(role);
		}else {
			res = userAccount.removeRole(role);
		}
		if(res) {
			userAccountRepository.save(userAccount);
		}
		return modelMapper.map(userAccount, RolesDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundExeption::new);
		userAccount.setPassword(newPassword);
		userAccountRepository.save(userAccount);
	}
	
}
