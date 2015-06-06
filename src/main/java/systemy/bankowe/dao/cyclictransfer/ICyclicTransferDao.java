package systemy.bankowe.dao.cyclictransfer;

import java.util.List;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.CyclicTransferDto;
import systemy.bankowe.dto.UserDto;

public interface ICyclicTransferDao {
    /**
     * Zwraca listę wszystkich przelewów cyklicznych danego użytkownika.
     * 
     * @param userDto dane użytkownika.
     * @return lista przelewów cyklicznych.
     */
    List<CyclicTransferDto> getAllCyclicTransfers(final UserDto userDto);
    /**
     * Usuwa wszystkie przelewy cykliczne, które wychodzą z przesłanego konta.
     * 
     * @param debitAccount konto z którego ma wyjść dany przlew cykliczny.
     */
    void removeCyclicTransfers(final AccountDto debitAccount);
    /**
     * Zwraca listę przelewów z żadanego dnia miesiąca.
     * 
     * @param dayOfMonth dzień miesiąca.
     * @return lista przelewów cyklicznych, które mają zostać wykonane zadanego dnia miesąca.
     */
    List<CyclicTransferDto> getAllCyclicTransfersFromDay(int dayOfMonth);
}
