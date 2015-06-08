package systemy.bankowe.flows.addcoowner;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import systemy.bankowe.dto.UserDto;

public class AddCoownerDataBean implements Serializable {

    private static final long serialVersionUID = -2509482497012514534L;

    private String pesel;

    private UserDto userData;

    private boolean userExist;

    private String msg;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getCoownerName() {
        String coownerName = "";

        if (userExist) {
            coownerName = userData.getName() + " " + userData.getSurname().substring(0, 1);
        }

        return coownerName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public UserDto getUserData() {
        return userData;
    }

    public void setUserData(UserDto userData) {
        this.userData = userData;
    }

    public boolean isUserExist() {
        return userExist;
    }

    public void setUserExist(boolean userExist) {
        this.userExist = userExist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
