package org.eclipse.fx.code.compensator.project.vcs.git;

import java.io.File;
import java.net.URI;

import org.eclipse.fx.code.compensator.project.vcs.VersionControlService;
import org.eclipse.fx.core.ProgressReporter;
import org.eclipse.fx.core.ReturnValue;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class GitVersionControlService implements VersionControlService {

	@Override
	public String getId() {
		return "git";
	}

	@Override
	public String getLabel() {
		return "Git";
	}

	@Override
	public String getIconURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnValue<URI> cloneRepository(File localDirectory, String url, String username,
			String password, ProgressReporter reporter) {
		Git result = null;
		try {
			result = Git.cloneRepository().setURI(url).setDirectory(localDirectory).call();
			return ReturnValue.ok(localDirectory.toURI());
		} catch (GitAPIException e) {
			return ReturnValue.error("Unable to clone repository", e);
		} finally {
			if( result != null ) {
				result.close();
			}
		}
	}
}
