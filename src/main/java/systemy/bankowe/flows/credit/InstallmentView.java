package systemy.bankowe.flows.credit;

import java.io.Serializable;

public class InstallmentView implements Serializable{
	private static final long serialVersionUID = 660420557233940278L;
	private Integer numerRaty;
	private Double odsetki;
	private Double kapital;
	
	public InstallmentView(Integer numerRaty, Integer kapital, Integer odsetki) {
		this.numerRaty = numerRaty;
		this.kapital = new Double(kapital / 100.0);
		this.odsetki = new Double(odsetki / 100.0);
	}
	
	public Integer getNumerRaty() {
		return numerRaty;
	}

	public void setNumerRaty(Integer numerRaty) {
		this.numerRaty = numerRaty;
	}

	public Double getOdsetki() {
		return odsetki;
	}
	
	public void setOdsetki(Double odsetki) {
		this.odsetki = odsetki;
	}
	
	public Double getKapital() {
		return kapital;
	}
	
	public void setKapital(Double kapital) {
		this.kapital = kapital;
	}

}
