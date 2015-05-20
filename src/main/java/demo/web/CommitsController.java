package demo.web;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import demo.dao.Commit;
import demo.dao.CommitRepository;
import demo.dao.CommitViews;
import demo.dao.FileDiff;

@RestController
public class CommitsController {

    @Autowired
    CommitRepository commitRepo;

    @RequestMapping("/commits")
    @JsonView(CommitViews.List.class)
    public Collection<Commit> getAllCommits(){
        Collection<Commit> allCommits = commitRepo.findAll();
        allCommits.forEach(commit -> {
            Long added = commit.getFileDiffs().stream().collect(Collectors.summingLong(FileDiff::getAdded));
            Long removed = commit.getFileDiffs().stream().collect(Collectors.summingLong(FileDiff::getRemoved));
            commit.setAdded(added);
            commit.setRemoved(removed);
        });
        return allCommits;
    }

}
