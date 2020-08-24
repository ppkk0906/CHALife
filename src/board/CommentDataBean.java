package board;

import java.sql.Timestamp;

public class CommentDataBean {
private long comment_index;
private int article_index, user_index;
private boolean comment_depth, is_deleted;
private StringBuffer content;
private String ip;
private Timestamp time_updated, time_written, time_deleted;
public long getComment_index() {
	return comment_index;
}
public void setComment_index(long comment_index) {
	this.comment_index = comment_index;
}
public int getArticle_index() {
	return article_index;
}
public void setArticle_index(int article_index) {
	this.article_index = article_index;
}
public int getUser_index() {
	return user_index;
}
public void setUser_index(int user_index) {
	this.user_index = user_index;
}
public boolean isComment_depth() {
	return comment_depth;
}
public void setComment_depth(boolean comment_depth) {
	this.comment_depth = comment_depth;
}
public boolean isIs_deleted() {
	return is_deleted;
}
public void setIs_deleted(boolean is_deleted) {
	this.is_deleted = is_deleted;
}
public StringBuffer getContent() {
	return content;
}
public void setContent(StringBuffer content) {
	this.content = content;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public Timestamp getTime_updated() {
	return time_updated;
}
public void setTime_updated(Timestamp time_updated) {
	this.time_updated = time_updated;
}
public Timestamp getTime_written() {
	return time_written;
}
public void setTime_written(Timestamp time_written) {
	this.time_written = time_written;
}
public Timestamp getTime_deleted() {
	return time_deleted;
}
public void setTime_deleted(Timestamp time_deleted) {
	this.time_deleted = time_deleted;
}

}
