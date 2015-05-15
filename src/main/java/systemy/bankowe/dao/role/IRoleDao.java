package systemy.bankowe.dao.role;

import java.util.Optional;

import systemy.bankowe.dto.RoleDto;

/**
 * DAO dla uprawnień.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface IRoleDao {
    /**
     * Wyszukuje uprawnienie po jego nazwie.
     * 
     * @param name nazwa uprawnienia.
     * @return obiekt uprawnienia.
     */
    Optional<RoleDto> getRoleByName(final String name);
}
