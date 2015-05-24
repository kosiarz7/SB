package systemy.bankowe.services.deposit;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import systemy.bankowe.dao.user.UserDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.deposit.DepositDto;
import systemy.bankowe.dto.deposit.DepositTypeDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.user.UserData;
import systemy.bankowe.services.user.UserService;

@Service
@Transactional(readOnly = true)
public class DepositService implements IDepositService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired private UserService userService;

	@Override
	public Set<DepositDto> getListOfDepositsForLoggedUser() {
		UserDto user = userService.getLoggedAccount();
		return user.getDeposits();
	}

}