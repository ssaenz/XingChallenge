package com.ssaenz.xingchallenge.domain;

/**
 * Stores information about a GitHub repository
 *
 * Created by ssaenz on 13/01/18.
 */

public class GitHubRepository {

    private String name;
    private String description;
    private GitHubUser owner;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GitHubUser getOwner() {
        return owner;
    }

    public void setOwner(GitHubUser owner) {
        this.owner = owner;
    }
}
