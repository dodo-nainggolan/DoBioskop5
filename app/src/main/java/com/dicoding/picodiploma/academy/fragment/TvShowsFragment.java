package com.dicoding.picodiploma.academy.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.picodiploma.academy.R;
import com.dicoding.picodiploma.academy.adapter.TvShowsAdapter;
import com.dicoding.picodiploma.academy.entity.TvShows;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TvShowsFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<TvShows>> {

    TvShowsAdapter adapter;

    private ArrayList<TvShows> list = new ArrayList<> ();

    private RecyclerView rv;
    private ProgressBar progressBar;

    public TvShowsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.fragment_tv_shows, container, false );
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        adapter = new TvShowsAdapter ();
        adapter.notifyDataSetChanged ();

        rv = view.findViewById ( R.id.grid_view_list_item );
        progressBar = view.findViewById ( R.id.progressBar );

        rv.setLayoutManager ( new GridLayoutManager ( getActivity (), 2 ) );
        rv.setAdapter ( adapter );

        Bundle bundle = new Bundle ();

        getLoaderManager ().initLoader ( 0, bundle, this );
    }

    @Override
    public Loader<ArrayList<TvShows>> onCreateLoader(int id, Bundle args) {

        return new TvShowsAsyncTaskLoader ( getActivity (), "" );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<TvShows>> loader, ArrayList<TvShows> data) {
        adapter.setData ( data );

    }


    @Override
    public void onLoaderReset(Loader<ArrayList<TvShows>> loader) {
//TODO optimize dirty trick
//         adapter.setData ( null );
    }
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility ( View.VISIBLE );
        } else {
            progressBar.setVisibility ( View.GONE );
        }
    }
}


