package board;

import java.sql.Timestamp;

public class BoardDataBean {
private long index;
private int user_index, read_count;
private byte article_depth;
private String subject, ip;
private StringBuffer content;
private Timestamp time_updated, time_deleted, time_written;
private boolean is_deleted;
public long getIndex() {
	return index;
}
public void setIndex(long index) {
	this.index = index;
}
public int getUser_index() {
	return user_index;
}
public void setUser_index(int user_index) {
	this.user_index = user_index;
}
public int getRead_count() {
	return read_count;
}
public void setRead_count(int read_count) {
	this.read_count = read_count;
}
public byte getArticle_depth() {
	return article_depth;
}
public void setArticle_depth(byte article_depth) {
	this.article_depth = article_depth;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public StringBuffer getContent() {
	return content;
}
public void setContent(StringBuffer content) {
	this.content = content;
}
public Timestamp getTime_updated() {
	return time_updated;
}
public void setTime_updated(Timestamp time_updated) {
	this.time_updated = time_updated;
}
public Timestamp getTime_deleted() {
	return time_deleted;
}
public void setTime_deleted(Timestamp time_deleted) {
	this.time_deleted = time_deleted;
}
public Timestamp getTime_written() {
	return time_written;
}
public void setTime_written(Timestamp time_written) {
	this.time_written = time_written;
}
public boolean isIs_deleted() {
	return is_deleted;
}
public void setIs_deleted(boolean is_deleted) {
	this.is_deleted = is_deleted;
}

}
