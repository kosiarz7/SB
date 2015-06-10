package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;

public class OrderToPayResult implements Serializable {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public OrderToPayResult() {

    }

    public OrderToPayResult(String errorMessage) {
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
            return "Rachunek wierzyciela nie został odnaleziony.";
        case 2:
            return "Rachunek dłużnika nie został odnaleziony.";
        case 3:
            return "Data rozpoczęcia upoważnienia zapłaty już minęła.";
        case 4:
            return "Data zakończenia upoważnienia zapłaty jest wcześniej niż data rozpoczęcia.";
        case 5:
            return "Maksymalna kwota upoważnienia jest ujemna.";
        default:
            return null;
        }
    }
}
