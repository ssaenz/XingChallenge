package com.ssaenz.xingchallenge;

import com.ssaenz.xingchallenge.domain.GitHubRepository;
import com.ssaenz.xingchallenge.domain.GitHubUser;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoPresenter;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Test for GitHubRepoPresenter.
 */
public class PresenterTest {

    private static final String MOCK_OWNER_LOGIN = "xing";
    private static final String MOCK_OWNER_URL = "https://github.com/xing";
    private static final int MOCK_REPOS_SIZE = 10;

    @Mock
    private GitHubRepoView repoView;

    private GitHubRepoPresenter presenter;

    @Before
    public void setUp () {
        MockitoAnnotations.initMocks(this);
        presenter.addRepositories(createMockData(MOCK_REPOS_SIZE));
        presenter.showRepositoryAtPosition(repoView, 0);
    }

    @Test
    public void count_test() throws Exception {
        assertEquals(10,presenter.getRepositoriesCount());
    }

    @Test
    public void show_repo_test () {
        verify(repoView).setRepoName("repo 0");
        verify(repoView).setRepoDescription("Description repo 0");
        verify(repoView).setOwnerName(MOCK_OWNER_LOGIN);
    }

    private List<GitHubRepository> createMockData (int size) {
        List<GitHubRepository> repositories = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            repositories.add(createRepo("repo " + i, "Description repo " + i));
        }
        return repositories;
    }

    private GitHubRepository createRepo (String name, String description) {
        GitHubRepository repo = new GitHubRepository();
        repo.setName(name);
        repo.setDescription(description);
        repo.setOwner(createOwner());
        return repo;
    }

    private GitHubUser createOwner() {
        GitHubUser user = new GitHubUser();
        user.setLogin(MOCK_OWNER_LOGIN);
        user.setHtmlUrl(MOCK_OWNER_URL);
        return user;
    }
}