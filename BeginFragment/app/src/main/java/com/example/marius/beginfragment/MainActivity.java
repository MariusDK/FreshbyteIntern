package com.example.marius.beginfragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (findViewById(R.id.fragment_container)!=null)
        {
            if (savedInstanceState != null)
            {
                return;
            }
            HeadlinesFragment firstFragment = new HeadlinesFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment).commit();
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            //args.putInt(ARG_POSITION,position);
            newFragment.setArguments(args);
        }

    }

    @Override
    public void onArticleSelected(int position) {

        ArticleFragment articleFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFragment != null){
        }
    }
}
