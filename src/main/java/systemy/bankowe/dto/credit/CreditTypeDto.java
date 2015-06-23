package systemy.bankowe.dto.credit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "rodzaj_kredytu")
public class CreditTypeDto implements Serializable {

	private static final long serialVersionUID = 6256516386454396987L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_TYPE_SEQ")
    @SequenceGenerator(name = "CREDIT_TYPE_SEQ", sequenceName = "RODZAJ_KREDYTU_SEQ", allocationSize = 1)
    @Column(name = "id_rodzaju", nullable = false)
    private int id;

	@Column(name = "nazwa", nullable = false)
    private String nazwa;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
    
}
