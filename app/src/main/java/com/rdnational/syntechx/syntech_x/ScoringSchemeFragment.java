package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

public class ScoringSchemeFragment extends Fragment {

    TextView scheme;
    ImageView score_img;
    String rules = "";
    CardView score_card;

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private DocumentReference scoreref = database.document("Rules/Scoring_Scheme");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scoring_scheme, container, false);
        score_img = rootView.findViewById(R.id.generalRules_image);
        scheme = rootView.findViewById(R.id.rules);
        score_card = rootView.findViewById(R.id.generalRulesCard);
        checkConnection();
        loadScoringScheme();

        return rootView;
    }

    public void loadScoringScheme(){
        scoreref.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(documentSnapshot.exists()){
                    RulesItems rulesItems = documentSnapshot.toObject(RulesItems.class);
                    List<String> s_scheme;
                    String img = rulesItems.getTabImage();
                    s_scheme = rulesItems.getTabInfo();
                    for (String score: s_scheme){
                        rules += score+"\n\n";
                    }
                    scheme.setText("");
                    scheme.setText(rules);
                    if (getActivity() == null) {
                        return;
                    } else {
                    Glide.with(getContext()).load(img).into(score_img);}
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
        if(!online())
        {
            Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }

}
