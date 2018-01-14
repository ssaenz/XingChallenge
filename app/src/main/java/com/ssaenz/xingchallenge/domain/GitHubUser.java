package com.ssaenz.xingchallenge.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Stores information about a GitHub user
 *
 */

public class GitHubUser {

    private String login;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
