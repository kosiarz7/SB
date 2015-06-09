package systemy.bankowe.services.card;

import java.io.Serializable;

public class Result implements Serializable {

	
	public final static Result OK = new Result();
	/**
	 * UID
	 */
	private static final long serialVersionUID = 9191071961572323743L;
	private boolean fail = false;
	private String cause;

	public Result() {
		super();
	}

	public Result(String cause) {
		super();
		this.fail = true;
		this.cause = cause;
	}

	public boolean isFail() {
		return fail;
	}

	public void setFail(boolean fail) {
		this.fail = fail;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
