package demo.dao;

import java.io.Serializable;

public class Stats implements Serializable{

	private String author;
	private Long added = 0L;
	private Long removed = 0L;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getAdded() {
		return added;
	}
	public void setAdded(Long added) {
		this.added = added;
	}
	public Long getRemoved() {
		return removed;
	}
	public void setRemoved(Long removed) {
		this.removed = removed;
	}
	
	public Stats(){
	}

	
	public static Stats ZERO_STAT = new Stats();

	public static Stats addStats(Stats a, Stats b){
		String author = b.getAuthor() != null ? b.getAuthor() : a.getAuthor();
		Stats stats = new Stats();
		stats.setAuthor(author);
		stats.setAdded(a.added + b.added);
		stats.setRemoved(a.removed + b.removed);
		return stats;
	}
}
