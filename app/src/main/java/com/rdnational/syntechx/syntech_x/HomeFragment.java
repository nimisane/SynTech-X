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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment {
    TextView no_internet_connection;
    Button refresh;
    ProgressBar homeProgress;
    private ArrayList<String> sectionTitle;
    private RecyclerView home_recyclerView;
    //private HomeFragmentAdapter events_adapter;
    RecyclerViewDataAdapter adapter;
    ArrayList<HomeSingleItemsModel> homeFragmentlist;
    private ArrayList<SectionDataModel> allSampleData;
    SectionDataModel dataModel;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference eventsref = database.collection("Events");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        no_internet_connection = rootView.findViewById(R.id.home_no_internet);
        refresh = rootView.findViewById(R.id.home_refresh_button);
        homeProgress = rootView.findViewById(R.id.home_progressBar);

        ///final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        //layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        homeFragmentlist = new ArrayList<>();

        allSampleData = new ArrayList<>();
        sectionTitle = new ArrayList<>();
        sectionTitle.add("Glimpes of SynTech-X 2017-18");
        sectionTitle.add("Events");
        sectionTitle.add("Winners of SynTech-X 2017-18");
        checkConnection();
        loadhometab();
        home_recyclerView = rootView.findViewById(R.id.home_recyclerview);
        home_recyclerView.setHasFixedSize(true);
        home_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadhometab();
            }
        });
        return rootView;
    }

    public void loadhometab(){

       // for(String secName : sectionTitle) {
        for (int i = 1; i <= 5; i++){
            dataModel = new SectionDataModel();
            dataModel.setHeaderTitle("secName"+i);

            Task task1 = eventsref.whereLessThan("event_id", 18).orderBy("event_id").get();
            Task task2 = eventsref.whereGreaterThan("event_id", 18).orderBy("event_id").get();
            Task<List<QuerySnapshot>> alltasks = Tasks.whenAllSuccess(task1, task2);
            alltasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
                @Override
                public void onSuccess(List<QuerySnapshot> querySnapshots) {
                    //homeFragmentlist.clear();
                    for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            HomeSingleItemsModel homeSingleItemsModel = documentSnapshot.toObject(HomeSingleItemsModel.class);
                            String eventName = homeSingleItemsModel.getEvent_name();
                            String eventlogo = homeSingleItemsModel.getEvent_logo();
                            String eventColor = homeSingleItemsModel.getEvent_color();
                            homeFragmentlist.add(new HomeSingleItemsModel(eventlogo, eventName, eventColor));
                        }
                    }
                    dataModel.setAllItemInSection(homeFragmentlist);
                    allSampleData.add(dataModel);
                    adapter = new RecyclerViewDataAdapter(allSampleData, getContext());

                    home_recyclerView.setAdapter(adapter);
                  //  home_recyclerView.addOnScrollListener(new CenterScrollListener());
                    if (homeFragmentlist.isEmpty()) {
                        homeProgress.setVisibility(View.VISIBLE);
                        visible();
                    } else {
                        visibilityGone();
                        homeProgress.setVisibility(View.GONE);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("error", e.getMessage());
                }
            });

        }
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
    }
}
