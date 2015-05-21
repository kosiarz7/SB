package systemy.bankowe.dto.transfer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "typ_przelewu")
public class TransferType extends AbstractDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_typ_przelewu")
    private int id;

    @Column(name = "nazwa_typu")
    private String typeName;

    @Transient
    private TransferTypeEnum transferTypeEnum;

    public TransferType() {

    }

    public TransferType(TransferTypeEnum transferTypeEnum)
    {
        this.transferTypeEnum = transferTypeEnum;
    }
    public TransferType(int id, String typeName) {
        super();
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public TransferTypeEnum getTransferTypeEnum() {
        return transferTypeEnum;
    }

    public void setTransferTypeEnum(TransferTypeEnum transferTypeEnum) {
        this.transferTypeEnum = transferTypeEnum;
    }

    @Override
    public String toString() {
        return "TransferType [id=" + id + ", typeName=" + typeName + ", transferTypeEnum=" + transferTypeEnum + "]";
    }

    /**
     * INSERT INTO typ_przelewu VALUES(1, 'Przelew jednorazowy');
INSERT INTO typ_przelewu VALUES(2, 'Polecenie zapłaty');

     * @author iblis
     *
     */
    public static enum TransferTypeEnum {
        ONE_TIME_TRANSFER("Przelew jednorazowy"), ORDER_TO_PAY("Polecenie zapłaty");
        private String name; // must the same as in the database
        
        private TransferTypeEnum(String name)
        {
            this.name = name;
        }
        
        public String sqlValue()
        {
            return name;
        }
    }
}
