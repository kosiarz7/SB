package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;

public class MoneyTransferResult implements Serializable {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public MoneyTransferResult() {

    }

    public MoneyTransferResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return errorMessage != null;
    }

    public static String convertErrorCode(int code) {
        switch (code) {

        case 1:
            return "Rachunek dłużnika nie został odnaleziony.";
        case 2:
            return "Rachunek wierzyciela nie został odnaleziony.";
        case 3:
            return "Rachunek wierzyciela nie należy do zdefiniowanego wierzyciela.";
        case 4:
            return "Rachunek wierzyciela nie należy do wierzyciela o podanym adresie.";
        case 5:
            return "Nie ma wystarczających środków na koncie dłużnika.";
        case 6:
            return "Data realizacji przelewu już minęła.";
        case 7:
            return "Kwota poniżej zera.";
        default:
            return null;
        }
    }
}
