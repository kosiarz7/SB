package systemy.bankowe.dto.credit;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import systemy.bankowe.dto.RoleDto;
import systemy.bankowe.dto.UserDto;

@Entity
@Table(name = "rachunek_kredytowy")
public class CreditAccountDto implements Serializable {
	private static final long serialVersionUID = -2195352173247309446L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "RACHUNEK_KREDYTOWY_SEQ", allocationSize = 1)
    @Column(name = "id_rachunek", nullable = false)
    private int id;
	
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="klient_id")
//    private UserDto klient;
    
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CreditDto.class)
//    @JoinTable(name = "kredyty", joinColumns = { @JoinColumn(name = "id_kredytu", referencedColumnName = "id_kredytu") }, inverseJoinColumns = { @JoinColumn(name = "id_kredytu", referencedColumnName = "id_kredytu") })
//    private List<CreditDto> kredyty;
    
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

//	public UserDto getKlient() {
//		return klient;
//	}
//
//	public void setKlient(UserDto klient) {
//		this.klient = klient;
//	}

//	public List<CreditDto> getKredyty() {
//		return kredyty;
//	}
//
//	public void setKredyty(List<CreditDto> kredyty) {
//		this.kredyty = kredyty;
//	}

}
