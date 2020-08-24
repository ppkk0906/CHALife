package board;

import java.sql.Date;
import java.sql.Timestamp;

public class UserDataBean {
private int user_index;
private String user_id,password,real_name,email;
private Timestamp time_reg;
private boolean is_available;
private Date date_punishment;

public int getUser_index() {
	return user_index;
}
public void setUser_index(int user_index) {
	this.user_index = user_index;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getReal_name() {
	return real_name;
}
public void setReal_name(String real_name) {
	this.real_name = real_name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Timestamp getTime_reg() {
	return time_reg;
}
public void setTime_reg(Timestamp time_reg) {
	this.time_reg = time_reg;
}
public boolean isIs_available() {
	return is_available;
}
public void setIs_available(boolean is_available) {
	this.is_available = is_available;
}
public Date getDate_punishment() {
	return date_punishment;
}
public void setDate_punishment(Date date_punishment) {
	this.date_punishment = date_punishment;
}

}
