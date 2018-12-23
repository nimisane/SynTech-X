package com.rdnational.syntechx.syntech_x;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import java.util.ArrayList;

import static com.rdnational.syntechx.syntech_x.CommitteesFragment.COMMIMAGE;
import static com.rdnational.syntechx.syntech_x.CommitteesFragment.COMMNAME;
import static com.rdnational.syntechx.syntech_x.CommitteesFragment.COMMPOS;
import static com.rdnational.syntechx.syntech_x.CommitteesFragment.DATAREF;

public class CommitteeHeadRecyclerView extends AppCompatActivity {

    private RecyclerView commHeadRecyclerview;
    private CommitteeHeadAdapter committeeHeadAdapter;
    private ArrayList<CommitteeHeadItems> committeeHeadItems;
    ImageView comm_logo;
    Toolbar comm_name;
    TextView comm_position;
    ProgressBar commHeadProgress;
    SwipeBackLayout swipeBackLayout;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    private CollectionReference coomHeadRef;

    String DatabaseRef = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_head_recycler_view);
        Intent i = getIntent();
        DatabaseRef = i.getStringExtra(DATAREF);
        coomHeadRef = database.collection(DatabaseRef);
        comm_logo = findViewById(R.id.committeeHead_image);
       // comm_name = findViewById(R.id.committeeHead_toolbar);
        comm_position = findViewById(R.id.committee_position);
        commHeadProgress = findViewById(R.id.commHead_progress);
        swipeBackLayout = findViewById(R.id.swipeBack_committee);
        swipeBackLayout.setEnablePullToBack(true);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.TOP);

        String commName = i.getStringExtra(COMMNAME);
        String commImg = i.getStringExtra(COMMIMAGE);
        String commPos = i.getStringExtra(COMMPOS);

        commHeadRecyclerview = findViewById(R.id.committee_head_recyclerview);
        commHeadRecyclerview.setHasFixedSize(true);
        commHeadRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        committeeHeadItems = new ArrayList<>();

        //comm_name.setTitle("\t"+commName);
        Glide.with(this).load(commImg).into(comm_logo);
        comm_position.setText(commPos);
        loadCommitteeHeads();
    }

    public void loadCommitteeHeads(){
        coomHeadRef.orderBy("head_id").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                committeeHeadItems.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    CommitteeHeadItems committeeHeads = documentSnapshot.toObject(CommitteeHeadItems.class);
                    String commHeadName = committeeHeads.getCommittee_head_name();
                    String commHeadImg = committeeHeads.getCommittee_head_image();

                    committeeHeadItems.add(new CommitteeHeadItems(commHeadName,commHeadImg));
                }
                committeeHeadAdapter = new CommitteeHeadAdapter(CommitteeHeadRecyclerView.this,committeeHeadItems);
                commHeadRecyclerview.setAdapter(committeeHeadAdapter);
                if(committeeHeadItems.isEmpty()){
                    commHeadProgress.setVisibility(View.VISIBLE);
                }
                else {
                    commHeadProgress.setVisibility(View.GONE);
                }

            }
        });
    }
}
