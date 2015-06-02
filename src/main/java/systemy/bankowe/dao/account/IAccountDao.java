package systemy.bankowe.dao.account;

import java.util.List;
import java.util.Optional;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;

/**
 * DAO dal konta bankowego.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface IAccountDao {
    /**
     * Zwraca numer ostatnio stworzonego numeru konta bankowego.
     * 
     * @return numer ostatnio stworzonego konta bankowego.
     */
    Optional<String> getLastCreatedAccountNumber();
    /**
     * Pobiera obiekt konta na podstawie jego numeru.
     * 
     * @param accountNumber numer konta.
     * @return obiekt konta.
     */
    AccountDto getAccountByNumber(String accountNumber);
    /**
     * Pobiera listę kont użytkownika.
     * 
     * @param userDto DTO użytkownika.
     * @return lista kont użytkownika.
     */
    List<AccountDto> getUserAccounts(final UserDto userDto);
}
