package com.ssaenz.xingchallenge.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ssaenz.xingchallenge.R;
import com.ssaenz.xingchallenge.data.network.EndpointFactory;
import com.ssaenz.xingchallenge.data.network.GitHubService;
import com.ssaenz.xingchallenge.domain.GitHubRepository;
import com.ssaenz.xingchallenge.ui.adapter.GitHubRepoAdapter;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoPresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
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

    @Override
    public boolean onLongClick(View v) {
        GitHubRepository repo = (GitHubRepository) v.getTag();
        openDialog(repo);
        return false;
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
                .subscribeWith(new DisposableObserver<List<GitHubRepository>>() {
                    @Override
                    public void onNext(List<GitHubRepository> repositories) {
                        mGitHubRepoAdapter.addRepositories(repositories);
                    }

                    @Override
                    public void onError(Throwable e) {
                        reconnectDialog(page, size);
                    }

                    @Override
                    public void onComplete() {
                        //Do nothing
                    }
                });
        mDisposable.add(subscribe);
    }

    private void reconnectDialog (final int page, final int size) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_no_connection_error)
                .setMessage(R.string.dialog_message_no_connectio_error)
                .setPositiveButton(R.string.button_retry, (dialog, which) -> {
                    loadGitHubRepos(page, size);
                    dialog.dismiss();
                })
                .setNegativeButton(R.string.button_exit_app, (dialog, which) -> {
                    dialog.dismiss();
                    MainActivity.this.finish();
                })
                .show();
    }

    private void openBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        this.startActivity(intent);
    }

    private void openDialog (GitHubRepository repo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(repo.getName())
                .setMessage(R.string.dialog_open_repo_message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.button_open_repo, (dialog, which) -> {
                    dialog.dismiss();
                    openBrowser(repo.getHtmlUrl());
                })
                .setNeutralButton(R.string.button_open_owner, (dialog, which) -> {
                    dialog.dismiss();
                    openBrowser(repo.getOwner().getHtmlUrl());
                })
                .setNegativeButton(R.string.button_cancel, (dialog, which) -> {
                    dialog.dismiss();
                }).show();
    }
}
