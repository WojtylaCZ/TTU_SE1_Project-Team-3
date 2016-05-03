package com.ttu_se1_project_team_3.activities;

/**
 * Created by ryanberg on 4/19/16.
 * This Class should likely be refactored and broken into a few. (Ryan Berg)
 * Isaac gonna be mad
 */


//soooo many
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.SessionLogField;
import com.ttu_se1_project_team_3.model.StudyTemplate;
import java.util.ArrayList;
import java.util.HashMap;


public class LogFragment extends Fragment {
    private TextView textname;
    private TextView LogName;
    private String ItemInput;
    private ArrayList<SessionLogField> logfields;
    private int currentField = 0;
    private ArrayList<StudyTemplate> templates = new ArrayList<StudyTemplate>();
    private StudyTemplate current;
    private Firebase db;
    private EditText Log_Input;
    private RadioGroup group;
    private CheckBox Log_Box;
    private String selectedStudy = ConductStudy.templateName;
    private RelativeLayout rlayout;
    private HashMap<String, String> input_items_list;
    private ArrayList<String> RC_items;
    private LinearLayout llayout;

    /**
     * Creates the new EditText based on the Input Type of current LogItem
     */
    public void createEditText() {
        Log_Input = new EditText(this.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.LogItemName);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 30, 0, 0);
        Log_Input.setWidth(250);
        Log_Input.setLayoutParams(params);
        rlayout.addView(Log_Input);
        //Todo: create Listener to add to database when input.
    }

    /**
     * Creates the new RadioGroup based on the Input Type of current LogItem
     * @param options
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
        //Todo: create Listener to add to database when input.

    }

    /**
     * Creates the new CheckBox Group based on the Input Type of current LogItem
     */
    public void createCheckboxGroup(ArrayList<String> options) {
        llayout.setVisibility(getView().VISIBLE);
        for(int i = 0; i < options.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            Log_Box = new CheckBox(this.getContext());
            Log_Box.setId(i);
            Log_Box.setText(options.get(i));
            llayout.addView(Log_Box, params);
        }
        //Todo: create Listener to add to database when input.
    }



    public void loadTemplatesFromDB(ChildEventListener listener) {

        db = DBconn.getInstance().getFbConnection().child("Templates");
        Query queryRef = db.orderByChild("name");
        queryRef.addChildEventListener(listener);
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final  View view = inflater.inflate(R.layout.log_fragment, container, false);


        //Initializations for all xml objects

        llayout = (LinearLayout) view.findViewById(R.id.log_lin_layout);
        llayout.setVisibility(view.GONE);
        rlayout = (RelativeLayout) view.findViewById(R.id.fragment_layout);
        textname = (TextView) view.findViewById(R.id.ItemName);
        LogName = (TextView) view.findViewById(R.id.LogItemName);
        group = new RadioGroup(this.getContext());
        Log_Box = new CheckBox(this.getContext());
        Log_Input = new EditText(this.getContext());


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
                            LogName.setText(logfields.get(currentField).getItemName());
                            ItemInput = String.valueOf(logfields.get(currentField).getItemInput());
                            input_items_list = logfields.get(currentField).getItemValues();
                            if(input_items_list != null) {
                                RC_items = new ArrayList<String>(input_items_list.keySet());
                            }
                            //System.out.println(RC_items.toString() + "owls");// for testing
                            //System.out.println(ItemInput + "owls");// for testing


                            }
                        }

                        /**
                         * Inital input of
                         * Again this should be made a function.. it is usued multipel times (Ryan Berg)
                         */
                    if (ItemInput != null) {
                        if (ItemInput.equals("TEXT")) {
                            rlayout.removeView(Log_Input);
                            createEditText();
                            group.removeAllViews();
                            llayout.removeAllViews();
                            llayout.setVisibility(getView().GONE);
                        } else if (ItemInput.equals("RADIOBUTTONS")) {
                            llayout.removeAllViews();
                            if(RC_items != null) {
                                createRadioGroup(RC_items);
                            }
                            Log_Input.setVisibility(view.GONE);
                        } else if (ItemInput.equals("CHECKBOXES")) {
                            llayout.removeAllViews();
                            group.removeAllViews();
                            if(RC_items != null) {
                                createCheckboxGroup(RC_items);
                            }
                            Log_Input.setVisibility(view.GONE);

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
             *
             *
             * Found this swipe motion online.. credit to stackOverflow (Ryan Berg)
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
                                rlayout.removeView(Log_Input);
                                group.removeAllViews();
                                createEditText();
                                llayout.removeAllViews();
                                llayout.setVisibility(getView().GONE);


                            }
                            else if(ItemInput.equals("RADIOBUTTONS")) {
                                if(RC_items != null) {
                                    createRadioGroup(RC_items);
                                }
                                llayout.removeAllViews();
                                llayout.setVisibility(getView().GONE);
                                Log_Input.setVisibility(view.GONE);
                            }
                            else if(ItemInput.equals("CHECKBOXES")) {
                                llayout.removeAllViews();
                                group.removeAllViews();
                                if(RC_items != null) {
                                    createCheckboxGroup(RC_items);
                                }
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
                            System.out.println(ItemInput + ": water" + currentField);
                            if(ItemInput.equals("TEXT")) {
                                rlayout.removeView(Log_Input);
                                createEditText();
                                group.removeAllViews();
                                llayout.removeAllViews();
                                llayout.setVisibility(getView().GONE);
                            }
                            else if(ItemInput.equals("RADIOBUTTONS")) {
                                llayout.removeAllViews();
                                if(RC_items != null) {
                                    createRadioGroup(RC_items);
                                }
                                Log_Input.setVisibility(view.GONE);
                            }
                            else if(ItemInput.equals("CHECKBOXES")) {
                                llayout.removeAllViews();
                                group.removeAllViews();
                                if(RC_items != null) {
                                    createCheckboxGroup(RC_items);
                                }
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
