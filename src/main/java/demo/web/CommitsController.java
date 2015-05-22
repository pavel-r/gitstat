package demo.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import demo.dao.Commit;
import demo.dao.CommitRepository;
import demo.dao.CommitViews;

@RestController
public class CommitsController {

    @Autowired
    CommitRepository commitRepo;

    @RequestMapping("/commits")
    @JsonView(CommitViews.List.class)
    public Collection<Commit> getAllCommits() {
        Collection<Commit> allCommits = commitRepo.findAll();
        return allCommits;
    }

    @RequestMapping("/commits/{commitId}")
    public Commit getCommitById(@PathVariable Long commitId) {
        Commit commit = commitRepo.findOne(commitId);
        return commit;
    }
}
