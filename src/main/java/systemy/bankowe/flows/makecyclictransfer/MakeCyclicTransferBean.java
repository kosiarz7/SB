package systemy.bankowe.flows.makecyclictransfer;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

import systemy.bankowe.scheduler.MakeCyclicTransfers;
import systemy.bankowe.util.FacesContextCommon;

public class MakeCyclicTransferBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 4944174560061182430L;

    private MakeCyclicTransfers deamon;

    public void makeTransfers(MakeCyclicTransferDataBean data) {
        deamon.makeCyclicTransfers(data.getDayOfMonth());
        FacesContextCommon.addMessage(FacesMessage.SEVERITY_INFO, "Przelewy cykliczne z dnia: " + data.getDayOfMonth()
                + " zostały wykonane.");
    }

    public void setDeamon(MakeCyclicTransfers deamon) {
        this.deamon = deamon;
    }
}
