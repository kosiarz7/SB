package systemy.bankowe.dao.user;

import java.util.Optional;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.deposit.DepositDto;

/**
 * Operacje na bazie związane z użytkownikiem.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface IUserDao {
    /**
     * Wczytuje dane użytkownika na podstawie jego loginu.
     * 
     * @param login login użytkownika.
     * @return dane użytkownika.
     */
    UserDto loadUserByUserName(final String login);
    /**
     * Uaktualnia dane użytkownika.
     * 
     * @param userDto dane użytkownika.
     */
    void updateUser(final UserDto userDto);
    /**
     * Zwraca największy login.
     * 
     * @return największy login.
     */
    Optional<String> getExtremeLogin();
    /**
     * Dodaje konto użytkownikowi.
     * 
     * @param userDto użytkownik.
     * @param accountDto konto.
     */
    void addAccount(final UserDto userDto, final AccountDto accountDto);
    /**
     * Dodaje lokate u�ytkownikowi.
     * 
     * @param userDto u�ytkownik.
     * @param deposit lokata.
     */
    void addDeposit(final UserDto userDto, final DepositDto deposit);
}
