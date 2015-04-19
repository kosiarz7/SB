package systemy.bankowe.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Encja użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
@Entity
@Table(name = "USERS")
public class UserDto extends AbstractDto implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = -9122458827485225637L;
    /**
     * Id użytkownika.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;
    /**
     * Imię użytkownika.
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    /**
     * Nazwisko użytkownika.
     */
    @Column(name = "SURNAME", nullable = false, length = 100)
    private String surname;
    /**
     * Login użytkownika.
     */
    @Column(name = "LOGIN", nullable = false, length = 100)
    private String login;
    /**
     * Skrót hasła użytkownika.
     */
    @Column(name = "PASSWORD", nullable = false, length = 128)
    private String password;
    /**
     * Liczba nieudanych prób logowania.
     */
    @Column(name = "FAIL_ATTEMPTS", nullable = false)
    private int failAttempts;
    /**
     * Uprawnienia użytkownika.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    private List<RoleDto> roles;

    /**
     * Konstrutkor.
     */
    public UserDto() {
        id = UNLOADED_ID;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    // SETTERY I GETTERY
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public int getFailAttempts() {
        return failAttempts;
    }

    public void setFailAttempts(int failAttempts) {
        this.failAttempts = failAttempts;
    }
}