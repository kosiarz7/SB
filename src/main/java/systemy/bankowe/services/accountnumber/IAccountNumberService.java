package systemy.bankowe.services.accountnumber;


/**
 * Serwis obsługujący operację związane z numerem konta.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface IAccountNumberService {
    /**
     * Sprawdcza czy przesłany numer konta jest poprawny.
     * 
     * @param accountNumber numer konta bankowego.
     * @return true - numer jest poprawny; false - numer jest niepoprawny.
     */
    boolean isValid(final String accountNumber);
    /**
     * Tworzy numer nowego konta bankowego.
     * 
     * @return numer nowego konta bankowego.
     */
    String createNewAccountNumber();
    /**
     * Oblicza liczbę kontrolną przesłanego numera konta bankowego.
     * 
     * @param accountNumber numer konta bankowego (bez liczby kontrolnej - 24 znaki).
     * @return dwucyfrowa liczba kontrolna.
     */
    String calculateControlNumber(final String accountNumber);
    /**
     * Dodaje wiądące zera do numeru konta bankowego (jeśli jest to konieczne).
     * 
     * @param accountNumber numer konta bankowego.
     * @param requiredLength żądana długość numeru konta bankowego.
     * @return numer konta bankowego uzupełniony o wiądące zera (jeśli jest to konieczne).
     */
    String addLeadingZeros(String accountNumber, int requiredLength);
    /**
     * Formatuje numer konta bankowego (grupuje cyfry w grupy po cztery znaki i oddziela ja spacjami).
     * 
     * @param accountNumber numer konta bankowego.
     * @return sformatowany numer konta bankowego.
     */
    String formatAccountNumber(String accountNumber);
    /**
     * Usuwa białe znaki z numeru konta bankowego.
     * 
     * @param accountNumber numer konta bankowego.
     * @return numer konta bankowego bez białych znaków.
     */
    String removeWhitespaces(String accountNumber);
}
