package demo.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.dao.Commit;
import demo.dao.CommitRepository;

@RestController
public class CommitsController {

    @Autowired
    CommitRepository commitRepo;
    
    @RequestMapping("/commits")
    public Collection<Commit> getAllCommits(){
        return commitRepo.findAll();
    }
}
