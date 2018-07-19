package learn.redis;

import java.io.Serializable;
import java.util.Date;


public class SomeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4558280121421149561L;

	private Integer acdId;
	private Integer isPrimary;
	private String icwsURL;
	private String icwsUserId;
	private String icwsPassword;
	private String createdBy;
	private Date createdOn;
	private Date lastUpdatedOn;


	public Integer getAcdId() {
		return acdId;
	}

	public void setAcdId(Integer acdId) {
		this.acdId = acdId;
	}

	public Integer getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getIcwsURL() {
		return icwsURL;
	}

	public void setIcwsURL(String icwsURL) {
		this.icwsURL = icwsURL;
	}

	public String getIcwsUserId() {
		return icwsUserId;
	}

	public void setIcwsUserId(String icwsUserId) {
		this.icwsUserId = icwsUserId;
	}

	public String getIcwsPassword() {
		return icwsPassword;
	}

	public void setIcwsPassword(String icwsPassword) {
		this.icwsPassword = icwsPassword;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Override
	public String toString() {
		return "[ StructParamACD {acdId=" + acdId  + 
				", isPrimary=" + isPrimary +
				", icwsURL=" + icwsURL +
				", icwsUserId=" + icwsUserId +
				", icwsPassword=" + icwsPassword +
				", createdBy=" + createdBy +
				", createdOn=" + createdOn +
				"} ]";
	}
}
