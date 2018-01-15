package com.ssaenz.xingchallenge;

import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoPresenter;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.ssaenz.xingchallenge.GitHubReposMother.MOCK_OWNER_LOGIN;
import static com.ssaenz.xingchallenge.GitHubReposMother.createMockData;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Test for GitHubRepoPresenter.
 */
public class PresenterTest {

    private static final int MOCK_REPOS_SIZE = 10;

    @Mock
    private GitHubRepoView repoView;

    private GitHubRepoPresenter presenter;

    @Before
    public void setUp () {
        MockitoAnnotations.initMocks(this);
        presenter = new GitHubRepoPresenter();
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
        verify(repoView).setFork(true);
    }


}