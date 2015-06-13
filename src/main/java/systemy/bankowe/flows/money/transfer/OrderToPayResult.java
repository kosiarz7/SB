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
            return "Bład: Rachunek wierzyciela nie został odnaleziony.";
        case 2:
            return "Bład: Rachunek dłużnika nie został odnaleziony.";
        case 3:
            return "Bład: Data rozpoczęcia upoważnienia zapłaty już minęła.";
        case 4:
            return "Bład: Data zakończenia upoważnienia zapłaty jest wcześniej niż data rozpoczęcia.";
        case 5:
            return "Bład: Maksymalna kwota upoważnienia jest ujemna.";
        default:
            return null;
        }
    }

	@Override
	public String toString() {
		return "OrderToPayResult [errorMessage=" + errorMessage + "]";
	}
    
    
}
