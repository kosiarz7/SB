package systemy.bankowe.services.accountnumber;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Optional;

import systemy.bankowe.dao.account.IAccountDao;
import systemy.bankowe.util.StringUtil;

/**
 * Serwis obsługujący operację związane z numerem konta.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class AccountNumberService implements IAccountNumberService, Serializable {
    /**
     * Numer pierwszego konta bankowego.
     */
    public static final String FIRST_ACCOUNT_NUMBER = "101010230000000000000001";
    /**
     * UID.
     */
    private static final long serialVersionUID = 7434398696356692172L;
    /**
     * Dzielnik.
     */
    private static final BigInteger DIVISOR = new BigInteger("97");
    /**
     * DAO dla kont bankowych.
     */
    private IAccountDao accountDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String accountNumber) {
        if (StringUtil.isEmpty(accountNumber)) {
            return false;
        }
        if (!accountNumber.matches("\\d{26}")) {
            return false;
        }
        String numberToValidation = accountNumber.substring(2).concat("2521").concat(accountNumber.substring(0, 2));
        return new BigInteger(numberToValidation).mod(DIVISOR).equals(BigInteger.ONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String calculateControlNumber(final String accountNumber) {
        BigInteger number = new BigInteger(accountNumber.concat("252100"));
        int controlNumber = 98 - number.mod(DIVISOR).intValue();
        return String.format("%02d", controlNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createNewAccountNumber() {
        Optional<String> lastAccountNumber = accountDao.getLastCreatedAccountNumber();
        String newAccountNumber;

        if (lastAccountNumber.isPresent()) {
            newAccountNumber = new BigInteger(lastAccountNumber.get().substring(2)).add(BigInteger.ONE).toString();
            newAccountNumber = addLeadingZeros(newAccountNumber, 24);
        }
        else {
            newAccountNumber = FIRST_ACCOUNT_NUMBER;
        }

        return calculateControlNumber(newAccountNumber).concat(newAccountNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addLeadingZeros(String accountNumber, int requiredLength) {
        while (accountNumber.length() < requiredLength) {
            accountNumber = "0" + accountNumber;
        }
        return accountNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatAccountNumber(String accountNumber) {
        String formattedNumber = "";

        if (!StringUtil.isEmpty(accountNumber)) {
            for (int i = accountNumber.length() - 4; i >= 0; i -= 4) {
                formattedNumber = accountNumber.substring(i, i + 4) + " " + formattedNumber;
            }
            int rest = accountNumber.length() % 4;
            if (rest != 0) {
                formattedNumber = accountNumber.substring(0, rest) + " " + formattedNumber;
            }
            formattedNumber = formattedNumber.trim();
        }

        return formattedNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String removeWhitespaces(String accountNumber) {
        return StringUtil.removeWhitespaces(accountNumber);
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
}