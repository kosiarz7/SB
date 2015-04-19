package systemy.bankowe.dao.user;

import systemy.bankowe.dto.UserDto;

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
}
