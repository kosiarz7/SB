package systemy.bankowe.services.login;

/**
 * Serwis obsługujący operacje związane z loginem użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface ILoginService {
    /**
     * Tworzy nowy login użytkownika.
     * 
     * @return nowy login użytkownika.
     */
    String getNewLogin();
}
