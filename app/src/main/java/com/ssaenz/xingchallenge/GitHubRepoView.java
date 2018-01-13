package com.ssaenz.xingchallenge;

/**
 * This interface has to be implemented by the view layer.
 * Provides the method to pass the data to the view.
 */

public interface GitHubRepoView {

    public void setRepoName(String repoName);

    public void setOwnerName(String ownerName);

    public void setRepoDescription(String repoDescription);
}
