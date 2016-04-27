package com.ttu_se1_project_team_3.activities;

/**
 * Created by ryanberg on 4/19/16.
 */

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.SessionLogField;
import com.ttu_se1_project_team_3.model.StudyTemplate;

import java.util.ArrayList;

public class LogFragment extends Fragment {
    private String LogItemName;
    private TextView textname;
    private TextView LogName;
    private String ItemInput;
    private ArrayList<SessionLogField> logfields;
    private int tempnum = 0;
    private ArrayList<StudyTemplate> templates = new ArrayList<StudyTemplate>();
    private StudyTemplate current;
    private Firebase db;
    private EditText Log_Input;
    private RadioGroup Log_Radio;
    private CheckBox Log_Box;
    int[] tester = {0, 1, 2};
    private String selectedStudy = ConductStudy.templateName;



    public void loadTemplatesFromDB(ChildEventListener listener) {

        db = DBconn.getInstance().getFbConnection().child("Templates");
        //final ArrayList<StudyTemplate> template = new ArrayList<StudyTemplate>();

        Query queryRef = db.orderByChild("name");
        queryRef.addChildEventListener(listener);
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final  View view = inflater.inflate(R.layout.log_fragment, container, false);

       //Initializations for all xml objects
        textname = (TextView) view.findViewById(R.id.ItemName);
        LogName = (TextView) view.findViewById(R.id.LogItemName);
        Log_Input = (EditText) view.findViewById(R.id.log_text_entry);
        Log_Radio = (RadioGroup) view.findViewById(R.id.log_button_group);
        Log_Box = (CheckBox) view.findViewById(R.id.log_checkbox_group);

        //setting all to invisible until they're needed
        Log_Input.setVisibility(view.GONE);
        Log_Radio.setVisibility(view.GONE);
        Log_Box.setVisibility(view.GONE);


        /**
         * loads all the templates from the database and adds the to the studyTemplat arraylit
         */
        loadTemplatesFromDB(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                StudyTemplate studyTemplate = snapshot.getValue(StudyTemplate.class);
                templates.add(studyTemplate);
                if (templates.size() > 1) {
                    textname.setText(selectedStudy);
                    for (int i = 0; i < templates.size(); i++) {
                        if (templates.get(i).getName().equals(selectedStudy)) {
                            current = templates.get(i);
                            textname.setText(current.getName());
                            logfields = current.getSessionLogFields();
                            LogName.setText(logfields.get(0).getItemName());
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

        if(tempnum == 0) {
            //Log_Input = new EditText(this.getContext());
            //Log_Input.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            //System.out.println("Tempnum 0");
            Log_Input.setVisibility(view.VISIBLE);
            Log_Radio.setVisibility(view.GONE);
            Log_Box.setVisibility(view.GONE);
        }
        else if(tempnum == 1) {
            System.out.println("Tempnum 1");
            Log_Input.setVisibility(view.GONE);
            Log_Radio.setVisibility(view.VISIBLE);
            Log_Box.setVisibility(view.GONE);
        }
        else if(tempnum == 2) {
            System.out.println("Tempnum 2");
            Log_Input.setVisibility(view.GONE);
            Log_Radio.setVisibility(view.GONE);
            Log_Box.setVisibility(view.VISIBLE);
        }


        /**
         * Detectes swipe getures in fragment and responds depending on the direction of the gesture.
         * Swipes move through the data in objects.
         */
        final GestureDetector gestures = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                //Toast.makeText(getActivity(), "Down", Toast.LENGTH_LONG).show();
                return true;
            }

            /**
             *
             * @param e1
             * @param e2
             * @param velocityX
             * @param velocityY
             * @return detects left and right gestures
             */
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
                        if(tempnum < logfields.size()){
                            tempnum++;
                            System.out.println(tempnum + "woot");
                            LogItemName = logfields.get(tempnum).getItemName();
                            LogName.setText(LogItemName);
                            if(tempnum == 0) {
                                System.out.println("Tempnum 0");
                                Log_Input.setVisibility(view.VISIBLE);
                                Log_Radio.setVisibility(view.GONE);
                                Log_Box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 1) {
                                System.out.println("Tempnum 1");
                                Log_Input.setVisibility(view.GONE);
                                Log_Radio.setVisibility(view.VISIBLE);
                                Log_Box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 2) {
                                System.out.println("Tempnum 2");
                                Log_Input.setVisibility(view.GONE);
                                Log_Radio.setVisibility(view.GONE);
                                Log_Box.setVisibility(view.VISIBLE);
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
                                Log_Input.setVisibility(view.VISIBLE);
                                Log_Radio.setVisibility(view.GONE);
                                Log_Box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 1) {
                                System.out.println("Tempnum 1");
                                Log_Input.setVisibility(view.GONE);
                                Log_Radio.setVisibility(view.VISIBLE);
                                Log_Box.setVisibility(view.GONE);
                            }
                            else if(tempnum == 2) {
                                System.out.println("Tempnum 2");
                                Log_Input.setVisibility(view.GONE);
                                Log_Radio.setVisibility(view.GONE);
                                Log_Box.setVisibility(view.VISIBLE);
                            }
                            LogItemName = logfields.get(tempnum).getItemName();
                            LogName.setText(LogItemName);
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
