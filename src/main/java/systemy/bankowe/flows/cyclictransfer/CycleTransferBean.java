package systemy.bankowe.flows.cyclictransfer;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class CycleTransferBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3040872231703707041L;

    /**
     * Zwraca listę zleceń stałych dla użytkownika.
     * 
     * @return lista zleceń stałych dla użytkownika.
     */
    public List<CycleTransferStub> getCycleTransfers() {
        return Collections.emptyList();
    }
}
