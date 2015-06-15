package systemy.bankowe.flows.credit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import systemy.bankowe.dto.credit.CreditInstallmentDto;

public class CreditView implements Serializable {

	private static final long serialVersionUID = 2421705924623627688L;
	private List<InstallmentView> raty;
	private Double calyKapital;
	private Double caleOdsetki;
	
	
	public CreditView(List<CreditInstallmentDto> raty) {
		this.raty = new ArrayList<InstallmentView>();
		Double calyKapital = 0.;
		Double caleOdsetki = 0.;
		for (CreditInstallmentDto instalment : raty) {
			this.raty.add(new InstallmentView(instalment.getNumerRaty(), instalment.getKapital(), instalment.getOdsetki()));
			calyKapital += new Double(instalment.getKapital() / 100.0);
			caleOdsetki += new Double(instalment.getOdsetki() / 100.0);
		}
		this.caleOdsetki = caleOdsetki;
		this.calyKapital = calyKapital;
	}

	public List<InstallmentView> getRaty() {
		return raty;
	}

	public void setRaty(List<InstallmentView> raty) {
		this.raty = raty;
	}

	public Double getCalyKapital() {
		return calyKapital;
	}

	public void setCalyKapital(Double calyKapital) {
		this.calyKapital = calyKapital;
	}

	public Double getCaleOdsetki() {
		return caleOdsetki;
	}

	public void setCaleOdsetki(Double caleOdsetki) {
		this.caleOdsetki = caleOdsetki;
	}
	
}