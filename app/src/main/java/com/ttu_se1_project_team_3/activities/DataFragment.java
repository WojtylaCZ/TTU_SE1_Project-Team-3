package com.ttu_se1_project_team_3.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.Gravity;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ryanberg on 4/19/16.
 * DataFragment pulls and displays the data inputs from firebase and dynamically creates the output
 * the user to display
 *
 * This Class needs to be reworked it is far too long. (Ryan Berg)
 */


public class DataFragment extends Fragment {

    private TextView data_item_name;
    private EditText data_text_input;
    private RadioGroup data_radio_group;
    private CheckBox data_box;
    private Firebase db;
    private String ItemInput;
    private int currentField = 0;
    private ArrayList<StudyTemplate> templates = new ArrayList<StudyTemplate>();
    private StudyTemplate current;
    private ArrayList<SessionDataField> datafields;
    private String dataitemname;
    private String selectedStudy = ConductStudy.templateName;
    private HashMap<String, String> data_item_list;
    private ArrayList<String> DC_items;
    private LinearLayout dllayout;
    private RelativeLayout drlayout;
    private Button saveButton;


    /**
     * Create Edit text dynamical creates the edit texts for the Data Fragment Class (Ryan Berg)
     *
     */
    private void createEditText() {
        data_text_input = new EditText(this.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.data_item_names);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 30, 0, 0);
        data_text_input.setWidth(250);
        data_text_input.setLayoutParams(params);
        drlayout.addView(data_text_input);
        //Todo: create Listener to add to database when input.
    }

    /**
     * createRadioGroup takes in an arraylist of strings and uses thos as the tags for a radioButtonGroup
     * @param options
     */
    public void createRadioGroup(ArrayList<String> options) {
        RelativeLayout.LayoutParams  params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.data_item_names);
        data_radio_group = new RadioGroup(this.getContext());
        data_radio_group.setLayoutParams(params);
        for(int i = 0; i < options.size(); i++) {
            RadioButton btn = new RadioButton(this.getContext());
            btn.setId(i);
            btn.setText(options.get(i));
            data_radio_group.addView(btn);
        }

        data_radio_group.setGravity(Gravity.CENTER_HORIZONTAL);
        drlayout.addView(data_radio_group);
        //Todo: create Listener to add to database when input.
    }

    /**
     * createCheckBoxGroup takes in an arraylist of strings and uses thos as the tags for a radioButtonGroup
     * @param options
     */
    public void createCheckboxGroup(ArrayList<String> options) {
        dllayout.setVisibility(getView().VISIBLE);
        for(int i = 0; i < options.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT);
            data_box = new CheckBox(this.getContext());
            data_box.setId(i);
            data_box.setText(options.get(i));
            dllayout.addView(data_box, params);
        }
        //Todo: create Listener to add to database when input.
    }


    /**
     * loads the templates from the databases and finds the one that is selected during selectstudy
     * @param listener
     */
    public void loadTemplatesFromDB(ChildEventListener listener) {

        db = DBconn.getInstance().getFbConnection().child("Templates");
        Query queryRef = db.orderByChild("name");
        queryRef.addChildEventListener(listener);
    }

    @Override
    /**
     * Ocreate starts the method
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.data_fragment, container, false);

        /// initializatons for variables
        data_item_name = (TextView) view.findViewById(R.id.data_item_names);
        dllayout = (LinearLayout) view.findViewById(R.id.data_fragment_linear);
        drlayout = (RelativeLayout) view.findViewById(R.id.data_frag_relative);
        saveButton = (Button) view.findViewById(R.id.saveButton);
        data_radio_group = new RadioGroup(this.getContext());
        data_text_input = new EditText(this.getContext());
        data_box = new CheckBox(this.getContext());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conductStud = new Intent(getContext(), Homepage.class);
                Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                startActivity(conductStud);
            }
        });


        /**
         * loades Templates from the Database and stores them in StudyTemplate ArrayList
         *

         */
        loadTemplatesFromDB(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                StudyTemplate studyTemplate = snapshot.getValue(StudyTemplate.class);
                templates.add(studyTemplate);
                if (templates.size() > 1) {
                    data_item_name.setText(selectedStudy);
                    for (int i = 0; i < templates.size(); i++) {
                        if (templates.get(i).getName().equals(selectedStudy)) {
                            current = templates.get(i);
                            data_item_name.setText(current.getName());
                            datafields = current.getSessionDataFields();
                            ItemInput = String.valueOf(datafields.get(currentField).getItemInput());
                            data_item_name.setText(datafields.get(currentField).getItemName());
                            data_item_list = datafields.get(currentField).getItemValues();
                            if (data_item_list != null) {
                                DC_items = new ArrayList<String>(data_item_list.keySet());
                            }

                        }
                    }

                    /**
                     * This should be refactored into a method if time permits.. it is used multiple times
                     */
                    if (ItemInput != (null)) {
                        if (ItemInput.equals("TEXT")) {
                            drlayout.removeView(data_text_input);
                            data_radio_group.removeAllViews();
                            createEditText();
                            dllayout.removeAllViews();
                            dllayout.setVisibility(getView().GONE);

                        } else if (ItemInput.equals("RADIOBUTTONS")) {
                            data_radio_group.removeAllViews();
                            if (DC_items != null) {
                                createRadioGroup(DC_items);
                            }
                            dllayout.removeAllViews();
                            dllayout.setVisibility(getView().GONE);
                            data_text_input.setVisibility(view.GONE);
                        } else if (ItemInput.equals("CHECKBOXES")) {
                            dllayout.removeAllViews();
                            data_radio_group.removeAllViews();
                            if (DC_items != null) {
                                createCheckboxGroup(DC_items);
                            }
                            data_text_input.setVisibility(view.GONE);
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

            /**
             *
             * @param e1
             * @param e2
             * @param velocityX
             * @param velocityY
             * @return
             *
             * Found this swipe motion online.. credit to stackOverflow (Ryan Berg)
             * there are some nice templates for this online as well.
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
                        if(currentField < datafields.size()){
                            currentField++;
                            System.out.println("woot" + currentField);
                            ItemInput = String.valueOf(datafields.get(currentField).getItemInput());
                            data_item_list = datafields.get(currentField).getItemValues();
                            dataitemname = datafields.get(currentField).getItemName();
                            DC_items = new ArrayList<String>(data_item_list.keySet());
                            data_item_name.setText(dataitemname);
                            if(ItemInput.equals("TEXT")) {
                                drlayout.removeView(data_text_input);
                                data_radio_group.removeAllViews();
                                createEditText();
                                dllayout.removeAllViews();
                                dllayout.setVisibility(getView().GONE);

                            }
                            else if(ItemInput.equals("RADIOBUTTONS")) {
                                if(DC_items != null) {
                                    createRadioGroup(DC_items);
                                }
                                dllayout.removeAllViews();
                                dllayout.setVisibility(getView().GONE);
                                data_text_input.setVisibility(view.GONE);
                            }
                            else if(ItemInput.equals("CHECKBOXES")) {
                                dllayout.removeAllViews();
                                data_radio_group.removeAllViews();
                                if(DC_items != null) {
                                    createCheckboxGroup(DC_items);
                                }
                                data_text_input.setVisibility(view.GONE);
                            }
                        }
                        //Toast.makeText(getActivity(), "Right to Left", Toast.LENGTH_LONG).show();
                    }
                    else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        if(currentField > 0) {
                            currentField--;
                            ItemInput = String.valueOf(datafields.get(currentField).getItemInput());
                            data_item_list = datafields.get(currentField).getItemValues();
                            dataitemname = datafields.get(currentField).getItemName();
                            DC_items = new ArrayList<String>(data_item_list.keySet());
                            data_item_name.setText(dataitemname);
                            System.out.println("woot " + currentField); //Testing
                            if(ItemInput.equals("TEXT")) {
                                drlayout.removeView(data_text_input);
                                data_radio_group.removeAllViews();
                                createEditText();
                                dllayout.removeAllViews();
                                dllayout.setVisibility(getView().GONE);

                            }
                            else if(ItemInput.equals("RADIOBUTTONS")) {
                                dllayout.removeAllViews();
                                if(DC_items != null) {
                                    createRadioGroup(DC_items);
                                }
                                dllayout.setVisibility(getView().GONE);
                                data_text_input.setVisibility(view.GONE);
                            }
                            else if(ItemInput.equals("CHECKBOXES")) {
                                dllayout.removeAllViews();
                                data_radio_group.removeAllViews();
                                if(DC_items != null) {
                                    createCheckboxGroup(DC_items);
                                }
                                data_text_input.setVisibility(view.GONE);
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
