package systemy.bankowe.services.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.account.IAccountDao;
import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dao.user.UserDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.deposit.DepositDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.accountnumber.IAccountNumberService;

/**
 * Usługi związane z użytkownikiem.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class UserService implements IUserService, Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -968298866296529495L;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    /**
     * DAO dla użytkownika.
     */
    private IUserDao userDao;
    /**
     * Serwis obsługujący numery kont.
     */
    private IAccountNumberService accountNumberService;
    /**
     * Metody wspomagającę pracę z Spring Security.
     */
    private SpringSecurityContextUtil springSecurityUtil;
    /**
     * Podstawowe DAO dla kont bankowych.
     */
    private CommonDao<AccountDto> accountCommonDao;
    /**
     * Podstawowe DAO dla lokat bankowych.
     */
    private CommonDao<DepositDto> depositCommonDao;
    
    
    /**
     * DAO dla kont.
     */
    private IAccountDao accountDao;


    /**
     * {@inheritDoc}
     */
    @Override
    public UserData loadUserByLogin(final String username) throws NoSuchUserException {
        UserDto userDto = userDao.loadUserByUserName(username);
        if (!userDto.isLoaded()) {
            throw new NoSuchUserException("Brak użytkownika o nazwie: " + username);
        }
        return new UserData(userDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetFailAttempts(final UserData userData) {
        UserDto userDto = userData.getUserDto();
        userDto.setFailAttempts(0);
        userDao.updateUser(userDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementFailAttempts(final UserData userData) {
        UserDto userDto = userData.getUserDto();
        userDto.setFailAttempts(userDto.getFailAttempts() + 1);
        userDao.updateUser(userDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addNextAccount(String accountName) {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();

        if (user.isPresent()) {
            AccountDto accountDto = new AccountDto();
            accountDto.setNumber(accountNumberService.createNewAccountNumber());
            accountDto.setName(accountName);
            accountDto.setSaldo(new Random().nextDouble() * 5000.0 + 200.45);
            accountDto.setSetupDate(new Date());
            accountDto.setEnabled(true);
            Set<UserDto> owners = new HashSet<>();
            owners.add(user.get().getUserDto());
            accountDto.setOwners(owners);
            userDao.addAccount(user.get().getUserDto(), accountDto);
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeAccount(String accountNumber) {
        String accountToClose = accountNumberService.removeWhitespaces(accountNumber);
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();

        if (user.isPresent()) {
            AccountDto account = null;
            UserDto userDto = user.get().getUserDto();
            for (AccountDto a : userDto.getAccounts()) {
                if (accountToClose.equals(a.getNumber())) {
                    account = a;
                }
            }

            if (null == account) {
                LOGGER.warn("closeAccount|Użytkownik o loginie: {} nie posiada konta bankowego o numerze: {}",
                        userDto.getLogin(), accountNumberService.formatAccountNumber(accountToClose));
                throw new IllegalArgumentException("Uzytkownik nie posiada konta o numerze: " + accountToClose);
            }

            account.setEnabled(false);
            accountCommonDao.update(account);
            
            LOGGER.debug("closeAccount|Konto {} zostało zamknięte.",
                    accountNumberService.formatAccountNumber(accountToClose));
        }
        else {
            throw new IllegalStateException("Użytkownik jest niezalogowany!");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountDto> getUserAccounts(final UserData userData) {
        return accountDao.getUserAccounts(userData.getUserDto());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double getSaldo(UserData userData, String accoutNumber) {
        List<AccountDto> accounts = getUserAccounts(userData).stream().filter(a -> accoutNumber.equals(a.getNumber()))
                .collect(Collectors.toList());
        if (null != accounts && !accounts.isEmpty()) {
            return accounts.get(0).getSaldo();
        }
        else {
            LOGGER.error("getSaldo|Użytkownik: {} nie posiada konta o numerze: {}", userData, accoutNumber);
            throw new IllegalArgumentException("Zalogowany użytkownik nie posiada knota o numerze: " + accoutNumber);
        }
    }

    /**
     * Ustawia DAO użytkownika.
     * 
     * @param userDao dao użytkownika.
     */
    public void setUserDao(final IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

    public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
        this.springSecurityUtil = springSecurityUtil;
    }

    public void setAccountCommonDao(CommonDao<AccountDto> accountCommonDao) {
        this.accountCommonDao = accountCommonDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean addNextDeposit(DepositDto deposit,AccountDto sourceAccount) {
		UserDto user = getLoggedAccount();
		Set<UserDto> owners = new HashSet<>();
		owners.add(user);
		deposit.setOwners(owners);
		
		List<AccountDto> accountsList = user.getAccounts();
		for(AccountDto account:accountsList){
			if(account.getId() == sourceAccount.getId()){
				account.setSaldo(sourceAccount.getSaldo() - deposit.getWartoscStartowa());
				break;
			}
		}
		
		userDao.addDeposit(user, deposit);
		return true;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public UserDto getLoggedAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     	String name = auth.getName();
     	UserDto user = userDao.loadUserByUserName(name);
		return user;
	}

	@Override
	public void closeDeposit(DepositDto deposit) {
		UserDto user = getLoggedAccount();
		List<AccountDto> accountsList = user.getAccounts();
		for(AccountDto account:accountsList){
			if(account.getId() == deposit.getAccountDto().getId()){
				account.setSaldo(account.getSaldo() + deposit.getWartoscObecna());
				break;
			}
		}
		userDao.updateUser(user);
		//depositCommonDao.update(deposit);
	}
    
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

}
