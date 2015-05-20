package systemy.bankowe.flows.insurancemain;

import java.io.Serializable;

public class InsuranceTypeStub implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String type;

	private String profit_desc;
	
	private String other_desc;

	public InsuranceTypeStub(int id, String name, String type,
			String profit_desc, String other_desc) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.profit_desc = profit_desc;
		this.other_desc = other_desc;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProfit_desc() {
		return profit_desc;
	}

	public void setProfit_desc(String profit_desc) {
		this.profit_desc = profit_desc;
	}

	public String getOther_desc() {
		return other_desc;
	}

	public void setOther_desc(String other_desc) {
		this.other_desc = other_desc;
	}
}
