package systemy.bankowe.services.user;

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
}