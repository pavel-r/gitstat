package demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import demo.dao.Commit;
import demo.dao.CommitRepository;
import demo.dao.FileDiff;
import demo.dao.FileDiffRepository;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}

@Component
class StartupRunner implements CommandLineRunner {

    @Autowired
    CommitRepository commitRepo;

    @Autowired
    FileDiffRepository fileDiffRepo;

    @Override
    public void run(String... arg0) throws Exception {

        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(new String[]{"bash", "C:/projects/gitstat/src/main/resources/gitstat.sh"});
        List<Commit> commits = new ArrayList<>();
        try (Scanner scanner = new Scanner(/*App.class.getResourceAsStream("/gitstat.log")*/process.getInputStream())) {
            while (scanner.hasNext()) {
                Commit commit = new Commit();
                commit.setAuthor(scanner.nextLine());
                commit.setDate(scanner.nextLine());
                commit.setMessage(scanner.nextLine());
                Collection<FileDiff> fileDiffs = new ArrayList<>();
                while(scanner.hasNext()){
                    if(scanner.hasNextLong()){
                        FileDiff fileDiff = new FileDiff();
                        fileDiff.setAdded(scanner.nextLong());
                        fileDiff.setRemoved(scanner.nextLong());
                        fileDiff.setName(scanner.nextLine());
                        fileDiffs.add(fileDiff);
                    } else {
                        String line = scanner.nextLine();
                        if(line.length() == 0) break;
                    }
                }
                Long added = fileDiffs.stream().collect(Collectors.summingLong(FileDiff::getAdded));
                Long removed = fileDiffs.stream().collect(Collectors.summingLong(FileDiff::getRemoved));
                commit.setAdded(added);
                commit.setRemoved(removed);
                fileDiffRepo.save(fileDiffs);
                commit.setFileDiffs(fileDiffs);
                commits.add(commit);
            }
        }
        commitRepo.save(commits);
    }

}
