package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    TextView no_internet_connection;
    Button refresh;
    ProgressBar homeProgress;
    public RecyclerView gallery_recyclerView1;

    private GalleryFragmentAdapter galleryFragmentAdapter;
    private ArrayList<GalleryItems> galleryItemsList1;

    List<String> rules;

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference insref = database.collection("Inauguration");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        no_internet_connection = rootView.findViewById(R.id.committee_no_internet);
        refresh = rootView.findViewById(R.id.home_refresh_button);
        homeProgress = rootView.findViewById(R.id.home_progressBar);
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        galleryItemsList1 = new ArrayList<>();

        gallery_recyclerView1 = rootView.findViewById(R.id.gallery_recyclerview1);

        gallery_recyclerView1.setHasFixedSize(true);

        gallery_recyclerView1.setLayoutManager(layoutManager);

        checkConnection();
        loadInauguration();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInauguration();
            }
        });

        return rootView;
    }

    public void loadInauguration(){
        insref.orderBy("p_id").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                   for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                       GalleryItems galleryItems = documentSnapshot.toObject(GalleryItems.class);

                       String photos = galleryItems.getPhoto();
                       int p_id = galleryItems.getP_id();
                       galleryItemsList1.add(new GalleryItems(photos, p_id));
                   }
                   galleryFragmentAdapter = new GalleryFragmentAdapter(getContext(),galleryItemsList1);
                    gallery_recyclerView1.setAdapter(galleryFragmentAdapter);
                    gallery_recyclerView1.addOnScrollListener(new CenterScrollListener());
                    if(galleryItemsList1.isEmpty())
                    {
                        visible();
                        homeProgress.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        visibilityGone();
                        homeProgress.setVisibility(View.GONE);
                    }

            }
        });
    }

    protected boolean online()
    {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info != null && info.isConnectedOrConnecting())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void checkConnection()
    {
        if(online())
        {
            visibilityGone();
        }
        else
        {
            visible();
        }
    }

    public void visibilityGone()
    {
        no_internet_connection.setVisibility(View.GONE);
        refresh.setVisibility(View.GONE);
    }

    public void visible()
    {
        refresh.setVisibility(View.VISIBLE);
        no_internet_connection.setVisibility(View.VISIBLE);
        homeProgress.setVisibility(View.GONE);
    }
}
