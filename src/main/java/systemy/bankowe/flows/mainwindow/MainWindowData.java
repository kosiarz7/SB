package systemy.bankowe.flows.mainwindow;

import java.io.Serializable;

public class MainWindowData implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = 5322829118344514916L;
    /**
     * Wybrane konto.
     */
    private AccountStub account;;

    public AccountStub getAccount() {
        return account;
    }

    public void setAccount(AccountStub account) {
        this.account = account;
    }
}
