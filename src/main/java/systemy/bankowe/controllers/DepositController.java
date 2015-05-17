package systemy.bankowe.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

import systemy.bankowe.dto.deposit.Deposit;
import systemy.bankowe.dto.deposit.Lokaty;
import systemy.bankowe.services.deposit.DepositService;
import systemy.bankowe.services.deposit.IDepositService;

public class DepositController implements Serializable{
	/**
	 * 
	 */
	@Autowired private DepositService depositService;
	
	private static final long serialVersionUID = 1L;

	public void submit(String name){
		System.out.println(name);
	}
	
	public boolean createDeposit3Month(){

		//Deposit deposit,String name){
		Lokaty deposit = new Lokaty();
		deposit.setIdLokata(BigDecimal.valueOf(12));
		deposit.setWartoscObecna(BigDecimal.valueOf(3000));
		depositService.createDeposit(deposit);
		return true;
		
	}
}
