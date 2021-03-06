package com.ssaenz.xingchallenge.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Stores information about a GitHub repository
 *
 */

public class GitHubRepository {

    private String name;

    private String description;

    private GitHubUser owner;

    @SerializedName("html_url")
    private String htmlUrl;

    private boolean fork;

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

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
