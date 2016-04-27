package com.ttu_se1_project_team_3.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.SessionDataField;
import com.ttu_se1_project_team_3.model.StudyTemplate;

/**
 * Created by ryanberg on 4/19/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DataFragment extends Fragment {
    private TextView data_item_name;
    private EditText data_text_input;
    private RadioGroup data_radio_group;
    private CheckBox data_box;
    private Firebase db;
    private int tempnum = 0;
    private ArrayList<StudyTemplate> templates = new ArrayList<StudyTemplate>();
    private StudyTemplate current;
    private ArrayList<SessionDataField> datafields;
    private String dataitemname;
    private String selectedStudy = ConductStudy.templateName;



    public void loadTemplatesFromDB(ChildEventListener listener) {

        db = DBconn.getInstance().getFbConnection().child("Templates");
        //final ArrayList<StudyTemplate> template = new ArrayList<StudyTemplate>();

        Query queryRef = db.orderByChild("name");
        queryRef.addChildEventListener(listener);
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.data_fragment, container, false);

        /// initializatons for variables
        data_item_name = (TextView) view.findViewById(R.id.data_item_names);
        data_text_input = (EditText) view.findViewById(R.id.data_text_entry);
        data_radio_group = (RadioGroup) view.findViewById(R.id.data_button_group);
        data_box = (CheckBox) view.findViewById(R.id.data_checkbox_group);

        data_text_input.setVisibility(view.GONE);
        data_radio_group.setVisibility(view.GONE);
        data_box.setVisibility(view.GONE);



        /**
         * loades Templates from the Database and stores them in StudyTemplate ArrayList
         */
        loadTemplatesFromDB(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                StudyTemplate studyTemplate = snapshot.getValue(StudyTemplate.class);
                templates.add(studyTemplate);
                if (templates.size() > 0) {
                    data_item_name.setText(selectedStudy);
                    for (int i = 0; i < templates.size(); i++) {
                        if (templates.get(i).getName().equals(selectedStudy)) {
                            current = templates.get(i);
                            data_item_name.setText(current.getName());
                            datafields = templates.get(i).getSessionDataFields();
                            data_item_name.setText(datafields.get(0).getItemName());
                            //ItemInput = logfields.get(tempnum).getItemInput();
                        }
                    }
                }
                System.out.print(snapshot.getKey() + " is template with: ");
                System.out.println("Name: " + studyTemplate.getName());
                System.out.println("SessionLogFields: " + studyTemplate.getSessionLogFields());
                System.out.println("SessionDataFields: " + studyTemplate.getSessionDataFields());
                System.out.println(templates.size() + "potatos");

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final GestureDetector gestures = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                //Toast.makeText(getActivity(), "Down", Toast.LENGTH_LONG).show();
                return true;
            }

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                final int SWIPE_MIN_DISTANCE = 120;
                final int SWIPE_MAX_OFF_PATH = 250;
                final int SWIPE_THRESHOLD_VELOCITY = 200;
                try {
                    if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        if(tempnum < datafields.size()){
                            tempnum++;
                            System.out.println(tempnum + "woot");
                            dataitemname = datafields.get(tempnum).getItemName();
                            data_item_name.setText(dataitemname);
                            if(tempnum == 0) {
                                System.out.println("Tempnum 0");
                                data_text_input.setVisibility(view.VISIBLE);
                                data_radio_group.setVisibility(view.GONE);
                                data_box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 1) {
                                System.out.println("Tempnum 1");
                                data_text_input.setVisibility(view.GONE);
                                data_radio_group.setVisibility(view.VISIBLE);
                                data_box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 2) {
                                System.out.println("Tempnum 2");
                                data_text_input.setVisibility(view.GONE);
                                data_radio_group.setVisibility(view.GONE);
                                data_box.setVisibility(view.VISIBLE);
                            }
                        }
                        //Toast.makeText(getActivity(), "Right to Left", Toast.LENGTH_LONG).show();
                    }
                    else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        if(tempnum > 0) {
                            tempnum--;
                            if(tempnum == 0) {
                                System.out.println("Tempnum 0");
                                data_text_input.setVisibility(view.VISIBLE);
                                data_radio_group.setVisibility(view.GONE);
                                data_box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 1) {
                                System.out.println("Tempnum 1");
                                data_text_input.setVisibility(view.GONE);
                                data_radio_group.setVisibility(view.VISIBLE);
                                data_box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 2) {
                                System.out.println("Tempnum 2");
                                data_text_input.setVisibility(view.GONE);
                                data_radio_group.setVisibility(view.GONE);
                                data_box.setVisibility(view.VISIBLE);
                            }
                            dataitemname = datafields.get(tempnum).getItemName();
                            data_item_name.setText(dataitemname);
                        }
                        //Toast.makeText(getActivity(), "Left to Right", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // nothing
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestures.onTouchEvent(event);
            }
        });



        return view;


    }

}
