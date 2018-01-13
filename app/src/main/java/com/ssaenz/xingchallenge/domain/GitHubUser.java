package com.ssaenz.xingchallenge.domain;

/**
 * Stores information about a GitHub user
 *
 * Created by ssaenz on 13/01/18.
 */

class GitHubUser {

    private String login;
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
