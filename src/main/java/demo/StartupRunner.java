package demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import demo.dao.Commit;
import demo.dao.CommitRepository;
import demo.dao.FileDiff;
import demo.dao.FileDiffRepository;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    CommitRepository commitRepo;

    @Autowired
    FileDiffRepository fileDiffRepo;

    @Value("${git.repo.path}")
    String gitRepoPath;

	@Override
	public void run(String... arg0) throws Exception {
		File repoDir = new File("C:/projects/gitstat/.git");

		// now open the resulting repository with a FileRepositoryBuilder
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repository = builder.setGitDir(repoDir).readEnvironment().findGitDir().build();

		List<Commit> commits = new ArrayList<>();
		Iterable<RevCommit> logs = new Git(repository).log().call();
		for(RevCommit revCommit : logs){
			Commit commit = new Commit();
            commit.setAuthor(revCommit.getAuthorIdent().getEmailAddress());
            commit.setDate(Date.from(Instant.ofEpochSecond(revCommit.getCommitTime())));
            commit.setMessage(revCommit.getFullMessage());
            commit.setSha(revCommit.getName());

            // prepare the two iterators to compute the diff between
            if(revCommit.getParentCount() == 1){//no merge commits
	    		Collection<FileDiff> fileDiffs = getFileDiffs(repository, revCommit);
	            Long added = fileDiffs.stream().collect(Collectors.summingLong(FileDiff::getAdded));
	            Long removed = fileDiffs.stream().collect(Collectors.summingLong(FileDiff::getRemoved));
	            commit.setAdded(added);
	            commit.setRemoved(removed);
	            fileDiffRepo.save(fileDiffs);
	            commit.setFileDiffs(fileDiffs);
            }
            commits.add(commit);
		}
		commitRepo.save(commits);
		repository.close();
	}

	private Collection<FileDiff> getFileDiffs(Repository repository,
			RevCommit revCommit) throws IncorrectObjectTypeException,
			IOException, GitAPIException {
		ObjectReader reader = repository.newObjectReader();
		CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
		oldTreeIter.reset(reader, revCommit.getParent(0).getTree());
		CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
		newTreeIter.reset(reader, revCommit.getTree());

		Collection<FileDiff> fileDiffs = new ArrayList<>();
		// finally get the list of changed files
		List<DiffEntry> diffs= new Git(repository).diff()
		                    .setNewTree(newTreeIter)
		                    .setOldTree(oldTreeIter)
		                    .call();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DiffFormatter formatter = new DiffFormatter(baos);
		formatter.setContext(0);
		formatter.setRepository(repository);

		for (DiffEntry entry : diffs) {
			FileDiff fileDiff = new FileDiff();
			fileDiff.setName(entry.getNewPath());
			
			formatter.format(entry);
			List<String> lines = Arrays.asList(baos.toString().split("\n"));
			Long added = lines.stream().skip(5).filter(s -> s.matches("^\\+.*")).collect(Collectors.counting());
			Long removed = lines.stream().skip(5).filter(s -> s.matches("^\\-.*")).collect(Collectors.counting());
			
			baos.reset();
			fileDiff.setAdded(added);
			fileDiff.setRemoved(removed);
		    fileDiffs.add(fileDiff);
		}
		return fileDiffs;
	}
}
