package systemy.bankowe.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Encja uprawnienia użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
@Entity
@Table(name = "ROLES")
public class RoleDto extends AbstractDto implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = -2828027452403509314L;
    /**
     * Id uprawnienia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int id;
    /**
     * Nazwa uprawnienia.
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    
    /**
     * Konstrutkor.
     */
    public RoleDto() {
        id = UNLOADED_ID;
    }

    // SETTRY I GETTERY
    @Override
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
}