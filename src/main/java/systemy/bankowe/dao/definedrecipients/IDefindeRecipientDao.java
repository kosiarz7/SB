package systemy.bankowe.dao.definedrecipients;

import java.util.List;

import systemy.bankowe.dto.DefinedRecipientDto;
import systemy.bankowe.dto.UserDto;

/**
 * DAO dla zdefiniowanego odbiorcy.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public interface IDefindeRecipientDao {
    /**
     * Pobiera listę zdefiniowanych odbiorców dla wybranego użytkownika.
     * 
     * @param userDto użytkownik.
     * @return lista zdefiniowanych odbiorców.
     */
    List<DefinedRecipientDto> getDefindeRecipients(final UserDto userDto);
}
