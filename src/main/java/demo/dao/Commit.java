package demo.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Commit implements Serializable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonView(CommitViews.List.class)
    private String sha;
    @JsonView(CommitViews.List.class)
    private String author;
    @JsonView(CommitViews.List.class)
    private String message;

    @JsonView(CommitViews.List.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JsonView(CommitViews.List.class)
    private Long removed;
    @JsonView(CommitViews.List.class)
    private Long added;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "COMMIT_ID")
    private Collection<FileDiff> fileDiffs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<FileDiff> getFileDiffs() {
        return fileDiffs;
    }

    public void setFileDiffs(Collection<FileDiff> fileDiffs) {
        this.fileDiffs = fileDiffs;
    }

    public Long getRemoved() {
        return removed;
    }

    public void setRemoved(Long removed) {
        this.removed = removed;
    }

    public Long getAdded() {
        return added;
    }

    public void setAdded(Long added) {
        this.added = added;
    }

    public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public Commit() {}// jpa
}
