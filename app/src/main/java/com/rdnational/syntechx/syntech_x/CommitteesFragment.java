package com.rdnational.syntechx.syntech_x;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CommitteesFragment extends Fragment implements CommitteeNameAdapter.OnItemClickListener {
    private RecyclerView commNameRecyclerview;
    private CommitteeNameAdapter committeeNameAdapter;
    private ArrayList<CommitteeNameItems> committeeNameItems;
    private ProgressBar commProgress;
    CollapsingToolbarLayout c1;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference coomRef = database.collection("Committees");
    public static final String DATAREF = "headref";
    public static final String COMMIMAGE = "commImg";
    public static final String COMMNAME = "commName";
    public static final String COMMPOS = "commPosition";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_committees,container,false);

        commNameRecyclerview = rootView.findViewById(R.id.committee_recyclerview);
        commProgress = rootView.findViewById(R.id.committee_progressbar);
        commNameRecyclerview.setHasFixedSize(true);
        commNameRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        committeeNameItems = new ArrayList<>();
        c1 = rootView.findViewById(R.id.home_collapsingToolbarLayout);
        c1.setCollapsedTitleTextColor(android.graphics.Color.BLACK);
        c1.setExpandedTitleColor(android.graphics.Color.BLACK);
        loadCommittees();
        return rootView;
    }


    public void loadCommittees(){
        coomRef.orderBy("comm_id").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                committeeNameItems.clear();
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    CommitteeNameItems committeeName = documentSnapshot.toObject(CommitteeNameItems.class);

                    String committee_name = committeeName.getCommitteeName();
                    String committee_img = committeeName.getCommitteeImage();
                    String committee_path = committeeName.getCommitteePath();
                    String committee_position = committeeName.getCommitteePosition();
                    committeeNameItems.add(new CommitteeNameItems(committee_name,committee_path,committee_img,committee_position));
                }
                committeeNameAdapter = new CommitteeNameAdapter(getContext(),committeeNameItems);
                commNameRecyclerview.setAdapter(committeeNameAdapter);
                committeeNameAdapter.setOnItemClickListener(CommitteesFragment.this);
                if(committeeNameItems.isEmpty()){
                    commProgress.setVisibility(View.VISIBLE);
                }
                else {
                    commProgress.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getContext(),CommitteeHeadRecyclerView.class);
        CommitteeNameItems clickedItem = committeeNameItems.get(position);
        i.putExtra(DATAREF,clickedItem.getCommitteePath());
        i.putExtra(COMMIMAGE,clickedItem.getCommitteeImage());
        i.putExtra(COMMNAME,clickedItem.getCommitteeName());
        i.putExtra(COMMPOS,clickedItem.getCommitteePosition());
        startActivity(i);
    }
}
