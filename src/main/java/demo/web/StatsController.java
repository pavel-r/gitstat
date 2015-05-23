package demo.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.dao.Commit;
import demo.dao.CommitRepository;
import demo.dao.Stats;

@RestController
@RequestMapping("/api")
public class StatsController {
	@Autowired
	CommitRepository commitRepo;
	
    @RequestMapping("/stats")
    public Collection<Stats> getStatsByAuthor() {
    	Map<String, Stats> statsMap = commitRepo.findAll().stream()
    	.collect(Collectors.groupingBy(Commit::getAuthor, Collectors.reducing(Stats.ZERO_STAT, (Commit c) -> {
    		Stats stats = new Stats();
    		stats.setAuthor(c.getAuthor());
    		stats.setAdded(c.getAdded());
    		stats.setRemoved(c.getRemoved());
    		return stats;
    	}, Stats::addStats)));
        return statsMap.values();
    }
}
