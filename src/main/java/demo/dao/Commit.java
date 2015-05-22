package demo.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;

@Entity
public class Commit implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JsonView(CommitViews.List.class)
    private String author;
    @JsonView(CommitViews.List.class)
    private String message;
    @JsonView(CommitViews.List.class)
    private String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Commit() {}// jpa
}
