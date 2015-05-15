package systemy.bankowe.flows.cyclictransfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dane przelewu cyklicznego.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class CyclicTransferData implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -2248901799604065870L;
    
    private static final List<String> DAY_OF_WEEK;
    private static final List<String> DAY_OF_MONTH;
    
    static {
        DAY_OF_MONTH = new ArrayList<>();
        for (int i = 1; i < 29; ++i) {
            DAY_OF_MONTH.add(Integer.toString(i));
        }
        
        DAY_OF_WEEK = new ArrayList<>();
        DAY_OF_WEEK.add("poniedziałek");
        DAY_OF_WEEK.add("wtorek");
        DAY_OF_WEEK.add("środa");
        DAY_OF_WEEK.add("czwartek");
        DAY_OF_WEEK.add("piątek");
        DAY_OF_WEEK.add("sobota");
        DAY_OF_WEEK.add("niedziela");
    }
    /**
     * Nazwa.
     */
    private String name;
    /**
     * Konto wierzyciela.
     */
    private String account;
    /**
     * Nazwa wierzyciela.
     */
    private String creditName;
    /**
     * Tytuł przelewu.
     */
    private String title;
    /**
     * Adres wierzyciela.
     */
    private String address;
    /**
     * Kwota.
     */
    private double amount;
    /**
     * Okres.
     * 
     * true - miesiąc; false - tydzień.
     */
    private boolean period;
    /**
     * Etykieta dnia przelewu.
     */
    private String dayLabel;
    /**
     * Dzień przelewu.
     */
    private String day;
    /**
     * Dostępne dni do przelewu.
     */
    private List<String> availabelDays;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPeriod() {
        return period;
    }

    public void setPeriod(boolean period) {
        this.period = period;
    }

    public String getDayLabel() {
        return dayLabel;
    }

    public void setDayLabel(String dayLabel) {
        this.dayLabel = dayLabel;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<String> getAvailabelDays() {
        return availabelDays;
    }

    public void setAvailabelDays(List<String> availabelDays) {
        this.availabelDays = availabelDays;
    }
}
