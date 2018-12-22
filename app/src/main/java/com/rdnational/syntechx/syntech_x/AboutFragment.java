package com.rdnational.syntechx.syntech_x;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AboutFragment extends Fragment {

    ImageView website,instagram,youtube,facebook,twitter;
    Intent pass;
    String url;
    TextView what_is_it;
    TextView objective;
    TextView events;
    TextView special_features;
    TextView goal;
    String Key_What="what_is_it";
    String Key_Obj="objective";
    String Key_events="events";
    String Key_Special="special_features";
    String Key_Goal="goal";
    String Developer = "developer_name";
    String DeveloperImag = "developer_img";
    String devName;
    String devImg;
    CardView developer;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private DocumentReference aboutref = database.document("AboutUs/About_Us");
    private DocumentReference devref = database.document("AppDeveloper/AppDeveloper");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about,container,false);
        website = rootView.findViewById(R.id.website);
        instagram = rootView.findViewById(R.id.instagram_link);
        youtube = rootView.findViewById(R.id.youtube_link);
        facebook = rootView.findViewById(R.id.facebook_link);
        twitter = rootView.findViewById(R.id.twitter_link);
        developer = rootView.findViewById(R.id.developer_info);

        what_is_it = rootView.findViewById(R.id.about_background_details);
        objective = rootView.findViewById(R.id.about_objective_details);
        events = rootView.findViewById(R.id.events_details);
        special_features = rootView.findViewById(R.id.speial_features_detalis);
        goal = rootView.findViewById(R.id.goal_detalis);

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                developerInfo();
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url="http://syntech-x.rdnational.ac.in/";
                openPage(url);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url="https://www.instagram.com/syntechxrdnc/";
                openPage(url);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url="https://www.youtube.com/channel/UCcK48NxSAUnxdPkr_WHlCgw";
                openPage(url);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url="https://www.facebook.com/SynTechXRDNC/";
                openPage(url);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url="https://twitter.com/syntechxrdnc";
                openPage(url);
            }
        });
        return rootView;
    }
    public void openPage(String url){
        pass = new Intent(getContext(),SocialMediaLinks.class);
        pass.putExtra("URL",url);
        startActivity(pass);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadAdbout();
    }

    public void loadAdbout(){
        aboutref.addSnapshotListener(getActivity(),new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(documentSnapshot.exists()){
                    String whatisit = documentSnapshot.getString(Key_What);
                    String obj = documentSnapshot.getString(Key_Obj);
                    String events_details = documentSnapshot.getString(Key_events);
                    String special = documentSnapshot.getString(Key_Special);
                    String goal_details = documentSnapshot.getString(Key_Goal);

                    what_is_it.setText(whatisit);
                    objective.setText(obj);
                    events.setText(events_details);
                    special_features.setText(special);
                    goal.setText(goal_details);
                }
            }
        });
    }

    public void developerInfo(){
        final AlertDialog.Builder alertadd = new AlertDialog.Builder(getContext());
        final LayoutInflater factory = LayoutInflater.from(getContext());
        final View view = factory.inflate(R.layout.developer_info, null);
        final ImageView image= (ImageView) view.findViewById(R.id.developer_img);
        final TextView developerName = view.findViewById(R.id.developer_name);
//        ImageButton CallDev = view.findViewById(R.id.call_developer);
//        CallDev.setImageResource(R.drawable.ic_call);
//        CallDev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makeACall("tel:9004863252");
//            }
//        });
        alertadd.setTitle("App Developed By:");

        devref.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(documentSnapshot.exists()){
                    devName = documentSnapshot.getString(Developer);
                    devImg = documentSnapshot.getString(DeveloperImag);

                    developerName.setText(devName);
                    Glide.with(getContext()).load(devImg).into(image);
                }
            }
        });
        image.setImageResource(R.drawable.ic_profile);
        alertadd.setView(view);
        alertadd.show();
    }

    public void makeACall(String phone_no) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(phone_no));
        startActivity(callIntent);
    }
}
