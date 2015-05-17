package systemy.bankowe.flows.addaccount;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import systemy.bankowe.dto.CitizenshipDto;

public class CitizenshipConverter implements Converter, Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 7575759851254996112L;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CitizenshipDto dto = new CitizenshipDto();
        
        if (!"@EMPTY@".equals(value)) {
            String[] tmp = value.split("#");
            dto.setId(Integer.valueOf(tmp[0]));
            dto.setCitizenship(tmp[1]);
        }
        
        return dto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String val = "@EMPTY@";
        
        if (value instanceof CitizenshipDto) {
            CitizenshipDto dto = (CitizenshipDto) value;
            val = dto.getId() + "#" + dto.getCitizenship();
        }
        
        return val;
    }

}
