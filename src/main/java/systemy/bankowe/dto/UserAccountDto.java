package systemy.bankowe.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Dodalem klase, bo nie wiem jak zdefiniowane sekwencje do gerowania ID przy  ManyToMany.
 * @author iblis
 *
 */
@Entity
@Table(name = "klienci_rachunki")
public class UserAccountDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5984870508185503713L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KLIENCI_RACHUNKI_SEQ")
    @SequenceGenerator(name = "KLIENCI_RACHUNKI_SEQ", sequenceName = "KLIENCI_RACHUNKI_SEQ", allocationSize = 1)
    @Column(name = "id_klient_rachunek", nullable = false)
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_klient")
    private UserDto user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rachunek")
    private AccountDto account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }
    
    
    
}
