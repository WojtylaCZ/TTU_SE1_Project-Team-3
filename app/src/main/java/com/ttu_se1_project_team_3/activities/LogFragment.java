package com.ttu_se1_project_team_3.activities;

/**
 * Created by ryanberg on 4/19/16.
 * This Class should likely be refactored and broken into a few.
 * Isaac gonna be mad
 */

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.ClipData;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import com.ttu_se1_project_team_3.model.SessionDataField;
import com.ttu_se1_project_team_3.model.SessionLogField;
import com.ttu_se1_project_team_3.model.StudyTemplate;
import java.util.ArrayList;
import java.util.HashMap;


public class LogFragment extends Fragment {
    private String LogItemName;
    private TextView textname;
    private TextView LogName;
    private String ItemInput;
    private ArrayList<SessionDataField> logfields;
    private int tempnum = 0;
    private int currentField = 0;
    private ArrayList<StudyTemplate> templates = new ArrayList<StudyTemplate>();
    private StudyTemplate current;
    private Firebase db;
    private EditText Log_Input;
    private RadioGroup group;
    private CheckBox[] Log_Box;
    int[] tester = {0, 1, 2};
    private String selectedStudy = ConductStudy.templateName;
    private RelativeLayout rlayout;
    private HashMap<String, String> input_items_list;
    private ArrayList<String> RC_items;

    /**
     * Creates the new EditText based on the Input Type of current LogItem
     */
    public void createEditText() {
        Log_Input = new EditText(this.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.LogItemName);
        Log_Input.setLayoutParams(params);
        Log_Input.setGravity(Gravity.CENTER_HORIZONTAL);
        Log_Input.setWidth(150);
        Log_Input.setHeight(40);
        rlayout.addView(Log_Input);
    }

    /**
     * Creates the new RadioGroup based on the Input Type of current LogItem
     */
    public void createRadioGroup(ArrayList<String> options) {
        RelativeLayout.LayoutParams  params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.LogItemName);
        group = new RadioGroup(this.getContext());
        group.setLayoutParams(params);
        for(int i = 0; i < options.size(); i++) {
            RadioButton btn = new RadioButton(this.getContext());
            btn.setId(i);
            btn.setText(options.get(i));
            group.addView(btn);
        }
        group.setGravity(Gravity.CENTER_HORIZONTAL);
        rlayout.addView(group);

    }

    /**
     * Creates the new CheckBox Group based on the Input Type of current LogItem
     */
    public void createCheckboxGroup(ArrayList<String> options) {
        Log_Box = new CheckBox[options.size()];
        for(int i = 0; i < options.size(); i++) {
            RelativeLayout.LayoutParams  params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            Log_Box[i] = new CheckBox(this.getContext());
            Log_Box[i].setLayoutParams(params);
            Log_Box[i].setId(i);
            Log_Box[i].setText(options.get(i));
            Log_Box[i].setGravity(Gravity.CENTER_HORIZONTAL);
        }
        for(int i = 0; i < options.size(); i++) {
            rlayout.addView(Log_Box[i]);
        }
    }

    public void loadTemplatesFromDB(ChildEventListener listener) {

        db = DBconn.getInstance().getFbConnection().child("Templates");
        //final ArrayList<StudyTemplate> template = new ArrayList<StudyTemplate>();

        Query queryRef = db.orderByChild("name");
        queryRef.addChildEventListener(listener);
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final  View view = inflater.inflate(R.layout.log_fragment, container, false);


        //Initializations for all xml objects
        rlayout = (RelativeLayout) view.findViewById(R.id.fragment_layout);
        textname = (TextView) view.findViewById(R.id.ItemName);
        LogName = (TextView) view.findViewById(R.id.LogItemName);
        //Log_Input = (EditText) view.findViewById(R.id.log_text_entry);
        //Log_Radio = (RadioGroup) view.findViewById(R.id.log_button_group);
        //Log_Box = (CheckBox) view.findViewById(R.id.log_checkbox_group);


        //setting all to invisible until they're needed
        //Log_Input.setVisibility(view.GONE);
        //Log_Radio.setVisibility(view.GONE);
        //Log_Box.setVisibility(view.GONE);


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
                            logfields = current.getSessionDataFields();
                            LogName.setText(logfields.get(currentField).getItemName());
                            ItemInput = String.valueOf(logfields.get(currentField).getItemInput());
                            input_items_list = logfields.get(currentField).getItemValues();
                            RC_items = new ArrayList<String>(input_items_list.keySet());
                            System.out.println(RC_items.toString() + "owls");
                            System.out.println(ItemInput + "owls");
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

    if(ItemInput != null) {
        if (ItemInput.equals("TEXT")) {
            System.out.println("Tempnum 0");
            createEditText();
            //Log_Input.setVisibility(view.VISIBLE);
            group.removeAllViews();
        } else if (ItemInput.equals("RADIOBUTTONS")) {
            System.out.println("Tempnum 1");
            createRadioGroup(RC_items);
            Log_Input.setVisibility(view.GONE);
        } else if (ItemInput.equals("CHECKBOXES")) {
            System.out.println("Tempnum 2");
            createCheckboxGroup(RC_items);
            group.removeAllViews();
        }
    }

        /**
         * Detectes swipe getures in fragment and responds depending on the direction of the gesture.
         * Swipes move through the data in objects.
         */
        final GestureDetector gestures = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

            public boolean onDown(MotionEvent e) {

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
                        if(currentField < logfields.size()){
                            currentField++;
                            LogName.setText(logfields.get(currentField).getItemName());
                            ItemInput = String.valueOf(logfields.get(currentField).getItemInput());
                            input_items_list = logfields.get(currentField).getItemValues();
                            RC_items = new ArrayList<String>(input_items_list.keySet());
                            System.out.println(ItemInput + ": water");
                            if(ItemInput.equals("TEXT")) {
                                group.removeAllViews();
                                createEditText();
                                System.out.println("Tempnum 0");

                            }
                            else if(ItemInput.equals("RADIOBUTTONS")) {
                                System.out.println("Tempnum 1");
                                createRadioGroup(RC_items);
                                Log_Input.setVisibility(view.GONE);
                            }
                            else if(ItemInput.equals("CHECKBOXES")) {
                                System.out.println("Tempnum 2");
                                group.removeAllViews();
                                createCheckboxGroup(RC_items);
                                Log_Input.setVisibility(view.GONE);
                            }
                        }
                        //Toast.makeText(getActivity(), "Right to Left", Toast.LENGTH_LONG).show();
                    }
                    else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        if(currentField > 0) {
                            currentField--;
                            LogName.setText(logfields.get(currentField).getItemName());
                            ItemInput = String.valueOf(logfields.get(currentField).getItemInput());
                            input_items_list = logfields.get(currentField).getItemValues();
                            RC_items = new ArrayList<String>(input_items_list.keySet());
                            System.out.println(ItemInput + ": water");
                            if(ItemInput.equals("TEXT")) {
                                System.out.println("Tempnum 0");
                                group.removeAllViews();
                                createEditText();
                            }
                            else if(ItemInput.equals("RADIOBUTTONS")) {
                                System.out.println("Tempnum 1");
                                createRadioGroup(RC_items);
                                Log_Input.setVisibility(view.GONE);
                            }
                            else if(ItemInput.equals("CHECKBOXES")) {
                                System.out.println("Tempnum 2");
                                group.removeAllViews();
                                createCheckboxGroup(RC_items);
                                Log_Input.setVisibility(view.GONE);

                            }
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
