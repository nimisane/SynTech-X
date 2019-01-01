package com.rdnational.syntechx.syntech_x;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class Sponsors extends AppCompatActivity {

    RecyclerView sRecyclerView;
    private ArrayList<SponsorItems> sponsorItems;
    private SponsorAdapter sponsorAdapter;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference sponRef = database.collection("Sponsors");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        getSupportActionBar().hide();
        Slidr.attach(this);
        sponsorItems = new ArrayList<>();
        sRecyclerView = findViewById(R.id.sponsors_recyclerview);
        sRecyclerView.setHasFixedSize(true);
        sRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        loadSponsors();
    }

    public void loadSponsors(){
        sponRef.orderBy("s_id").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                sponsorItems.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    SponsorItems sponsorItem = documentSnapshot.toObject(SponsorItems.class);
                    String s_logo = sponsorItem.getsImg();
                    sponsorItems.add(new SponsorItems(s_logo));
                }
                sponsorAdapter = new SponsorAdapter(getApplicationContext(),sponsorItems);
                sRecyclerView.setAdapter(sponsorAdapter);
            }
        });
    }
}
