package read;

public class RzsxxxBean {
	private String username;
	private String ip;
	private String time;
	private String action;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "RzsxxxBean [username=" + username + ", ip=" + ip + ", time=" + time + ", action=" + action + "]";
	}

	public RzsxxxBean() {
		super();
	}
	
	public RzsxxxBean(String username, String ip, String time, String action) {
		super();
		this.username = username;
		this.ip = ip;
		this.time = time;
		this.action = action;
	}

}
