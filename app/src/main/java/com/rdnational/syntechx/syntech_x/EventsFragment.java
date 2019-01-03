package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class EventsFragment extends Fragment implements EventsFragmentAdapter.OnItemClickListner{
    private RecyclerView events_recyclerView;
    private EventsFragmentAdapter events_adapter;
    private ArrayList<EventsFragmentItems> eventsFragmentlist;
    Intent i;
    public static final String EVENT_HEAD = "head";
    public static final String EVENT_NAME = "eventname";
    public static final String EVENT_DESC = "desc";
    public static final String EVENT_RULES = "rules";
    public static final String EVENT_FEES = "fees";
    public static final String EVENT_LOGO = "event_logo";
    public static final String EVENT_PARTICIPANTS = "participants";
    public static final String EVENT_HEAD_PHONE = "phone";
    public static final String EVENT_HEAD_IMG = "event_head_img";
    public static final String EVENT_VENUE = "venue";
    TextView no_internet_connection;
    List<String> rules;
    List<String> fees;
    ProgressBar events_progress;
    EventsFragmentItems complete_item_details;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference eventsref = database.collection("Events");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        eventsFragmentlist = new ArrayList<>();
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        events_recyclerView = rootView.findViewById(R.id.events_recyclerview);
        events_progress = rootView.findViewById(R.id.events_Progress);
        events_recyclerView.setHasFixedSize(true);
        events_recyclerView.setLayoutManager(layoutManager);
        no_internet_connection = rootView.findViewById(R.id.scoring_no_internet);
        checkConnection();
        load_events();
        return rootView;
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
    }

    public void visible()
    {
        no_internet_connection.setVisibility(View.VISIBLE);
        events_progress.setVisibility(View.GONE);
    }


    public void load_events(){
            eventsref.orderBy("event_id").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    eventsFragmentlist.clear();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        EventsFragmentItems eventsFragmentItems = documentSnapshot.toObject(EventsFragmentItems.class);

                        int eid = eventsFragmentItems.getEvent_id();
                        String event_name = eventsFragmentItems.getEvent_name();
                        String event_head = eventsFragmentItems.getEvent_head();
                        String event_logo = eventsFragmentItems.getEvent_logo();
                        String desc = eventsFragmentItems.getDescription();
                        String no_of_participants = eventsFragmentItems.getParticipants();
                        String phone = eventsFragmentItems.getEvent_head_phone();
                        String event_head_img = eventsFragmentItems.getEvent_head_img();
                        String color = eventsFragmentItems.getEvent_color();
                        String event_venue = eventsFragmentItems.getVenue();
                        rules = eventsFragmentItems.getEvent_rules();
                        fees = eventsFragmentItems.getEntry_fees();
                        eventsFragmentlist.add(new EventsFragmentItems(eid, event_logo, event_name, desc, no_of_participants, event_head, phone, event_head_img, rules,fees, color, event_venue));
                    }
                    events_adapter = new EventsFragmentAdapter(getContext(), eventsFragmentlist);
                    events_recyclerView.setAdapter(events_adapter);
                    events_recyclerView.addOnScrollListener(new CenterScrollListener());
                    events_adapter.setOnItemClickListener(EventsFragment.this);
                    if (eventsFragmentlist.isEmpty()) {
                        visible();
                        events_progress.setVisibility(View.VISIBLE);
                    } else {
                        visibilityGone();
                        events_progress.setVisibility(View.GONE);
                    }

                }
            });

    }

    @Override
    public void onItemClick(int position) {
        String eventRules="";
        String entryFees="";
        i = new Intent(getContext(),EventDetails.class);
        complete_item_details = eventsFragmentlist.get(position);
        i.putExtra(EVENT_LOGO,complete_item_details.getEvent_logo());
        i.putExtra(EVENT_NAME,complete_item_details.getEvent_name());
        i.putExtra(EVENT_DESC,complete_item_details.getDescription());
        i.putExtra(EVENT_PARTICIPANTS,complete_item_details.getParticipants());
        i.putExtra(EVENT_HEAD_PHONE,complete_item_details.getEvent_head_phone());
        i.putExtra(EVENT_HEAD_IMG,complete_item_details.getEvent_head_img());
        i.putExtra(EVENT_HEAD,complete_item_details.getEvent_head());
        i.putExtra(EVENT_VENUE,complete_item_details.getVenue());

        for (String ev_rules: complete_item_details.getEvent_rules()){
            eventRules += ev_rules+"\n\n";
        }

        for(String ev_fees: complete_item_details.getEntry_fees()){
            entryFees += ev_fees+"\n";
        }
        i.putExtra(EVENT_RULES, eventRules);
        i.putExtra(EVENT_FEES,entryFees);
        startActivity(i);
    }
}
