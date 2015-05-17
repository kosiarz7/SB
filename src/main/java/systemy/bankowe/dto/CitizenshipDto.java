package systemy.bankowe.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Encja obywatelstwa.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
@Entity
@Table(name = "obywatelstwa")
public class CitizenshipDto implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 4057391334745995512L;
    /**
     * ID obywatelstwa.
     */
    @Id
    @Column(name = "id_obywatelstwa", nullable = false)
    private int id;
    /**
     * Obywatelstwo.
     */
    @Column(name = "obywatelstwo", nullable = false, length = 64)
    private String citizenship;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    // GETTERY I SETTERY
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
