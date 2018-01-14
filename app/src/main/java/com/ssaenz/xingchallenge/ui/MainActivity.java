package com.ssaenz.xingchallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ssaenz.xingchallenge.R;
import com.ssaenz.xingchallenge.data.EndpointFactory;
import com.ssaenz.xingchallenge.data.GitHubService;
import com.ssaenz.xingchallenge.ui.adapter.GitHubRepoAdapter;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private static final String GITHUB_URL = "https://api.github.com";
    private static final String XING_LOGIN = "xing";
    private static final String GITHUB_TOKEN = "790d0fa04804386d0ff6ca9a6097aa28b52ad033";
    private static final int FIRST_PAGE = 1;
    private static final int PAGE_SIZE = 10;

    private GitHubRepoAdapter mGitHubRepoAdapter;
    private GitHubService mGitHubService;
    private CompositeDisposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGitHubService = new EndpointFactory<GitHubService>()
                .createEndpoint(GitHubService.class, GITHUB_URL);
        mDisposable = new CompositeDisposable();
        loadAndConfigureUI();
        loadGitHubRepos(FIRST_PAGE, PAGE_SIZE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposable!=null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private void loadAndConfigureUI() {
        RecyclerView recyclerView = findViewById(R.id.rv_list);

        GitHubRepoPresenter gitHubRepoPresenter = new GitHubRepoPresenter();
        mGitHubRepoAdapter = new GitHubRepoAdapter(gitHubRepoPresenter);
        mGitHubRepoAdapter.setOnLongClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(mGitHubRepoAdapter);
        recyclerView.addOnScrollListener(new EndScrollListener() {

            @Override
            public void onScrollEnd(int totalItemCount) {
                loadGitHubRepos(getNextPage(totalItemCount), PAGE_SIZE);
            }
        });
    }

    private int getNextPage(int totalItemCount) {
        return totalItemCount / PAGE_SIZE + 1;
    }

    private void loadGitHubRepos(final int page, final int size) {
        Disposable subscribe = mGitHubService.listRepos(XING_LOGIN, page, size, GITHUB_TOKEN)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(repos -> mGitHubRepoAdapter.addRepositories(repos));
        mDisposable.add(subscribe);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
