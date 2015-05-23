package demo.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommitRepository extends JpaRepository<Commit, Long> {
    Collection<Commit> findCommitByAuthor(String author);
    Commit findCommitBySha(String sha);
}