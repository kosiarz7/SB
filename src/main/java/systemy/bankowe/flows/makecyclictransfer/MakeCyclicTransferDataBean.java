package systemy.bankowe.flows.makecyclictransfer;

import java.io.Serializable;

public class MakeCyclicTransferDataBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -3240673621463977902L;

    private int dayOfMonth = 1;

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
