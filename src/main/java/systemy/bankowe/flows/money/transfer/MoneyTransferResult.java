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

    @Override
	public String toString() {
		return "MoneyTransferResult [errorMessage=" + errorMessage + "]";
	}

	public static String convertErrorCode(int code) {
        switch (code) {

        case 1:
            return "Błąd: Rachunek dłużnika nie został odnaleziony.";
        case 2:
            return "Błąd: Rachunek wierzyciela nie został odnaleziony.";
        case 3:
            return "Błąd: Rachunek nie należy do wierzyciela.";
        case 4:
            return "Błąd: Rachunek nie należy do wierzyciela o podanym adresie.";
        case 5:
            return "Błąd: Nie ma wystarczających środków na koncie dłużnika.";
        case 6:
            return "Błąd: Data realizacji przelewu już minęła.";
        case 7:
            return "Błąd: Kwota poniżej zera.";
        case 8:
        	return "Błąd: Za duża kwota.";
        default:
        	
            return null;
        }
    }
}
