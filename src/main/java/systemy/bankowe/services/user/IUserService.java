package systemy.bankowe.services.user;


import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.deposit.DepositDto;

import java.util.List;


/**
 * Usługi związane z użytkownikiem.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface IUserService {
    /**
     * Wyszukuję uzytkownika na podstawie jego nazwy.
     * 
     * @param username nazwa użytkownika.
     * @throws NoSuchUserException występuje gdy brak użytkownika o zadanej nazwie.
     */
    UserData loadUserByLogin(final String username) throws NoSuchUserException;
    /**
     * Zeruje liczbę nieudanych logowań.
     * 
     * @param userData dane użytkownika.
     */
    void resetFailAttempts(final UserData userData);
    /**
     * Zwiększa lizbę nieudanych logowań.
     * 
     * @param userData dane użytkownika.
     */
    void incrementFailAttempts(final UserData userData);
    /**
     * Dodaje kolejne konto dla zalogowanego użytkownika.
     * 
     * @param accountName nazwa konta.
     * @return true - konto zostało dodane; false - wystąpiły błedy podczas próby dodania konta.
     */
    boolean addNextAccount(String accountName);
    /**
     * Zamyka żądany rachunek.
     * 
     * @param accountNumber numer rachunku do zamknięcia.
     */
    void closeAccount(final String accountNumber);
    /**
     * Dodaje kolejn� lokat� dla zalogowanego u�ytkownika.
     * 
     * @param deposit lokata.
     * @return true - lokata zosta�a dodana; false - wyst�pi� b��d podczas zaka�adania lokaty.
     */
    boolean addNextDeposit(DepositDto deposit,AccountDto sourceAccount);
    /**
     * Zwraca aktualnie zalogowanego u�ytkownika
     */
    UserDto getLoggedAccount();
    /**
     *Zamyka podan� lokat�
     */
    void closeDeposit(DepositDto deposit);
    /**
     * Zwraca listę kont należących do użytkownika.
     * 
     * @param userData dane użytkownika.
     * @return lista kont należących do użytkownika.
     */
    List<AccountDto> getUserAccounts(final UserData userData);
    /**
     * Zwraca bieżące saldo na żadanym koncie.
     * 
     * @param userData dane zalogowanego użytkownika.
     * @param accoutNumber numer konta.
     * @return saldo.
     */
    double getSaldo(final UserData userData, final String accoutNumber);
}
