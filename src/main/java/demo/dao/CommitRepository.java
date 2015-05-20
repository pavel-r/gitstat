package demo.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit, Long> {
    Collection<Commit> findCommitByAuthor(String author);
}