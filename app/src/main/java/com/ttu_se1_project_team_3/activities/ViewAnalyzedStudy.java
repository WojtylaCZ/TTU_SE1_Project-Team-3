/**
 * This is test code for displaying data from a csv file that already has all of the statistical
 * data prepared.
 * Created by Colton Mikes
 */

package com.ttu_se1_project_team_3.activities;

import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import com.ttu_se1_project_team_3.R;


public class ViewAnalyzedStudy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream inputStream = getResources().openRawResource(R.raw.pseudo_study);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> pseudo_study = csvFile.read();
        AnalyzedStudyListAdapter adapter=new AnalyzedStudyListAdapter(this, R.layout.analyzed_study_list_row,R.id.txtid, pseudo_study);
        ListView listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    private class CSVFile {
        InputStream inputStream;

        public CSVFile(InputStream inputStream){
            this.inputStream = inputStream;
        }

        public List<String[]> read(){

            List<String[]> resultList = new ArrayList<String[]>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(",");
                    resultList.add(row);
                }
            }
            catch (IOException e) {
                Log.e("Main",e.getMessage());
            }
            finally {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    Log.e("Main",e.getMessage());
                }
            }
            return resultList;
        }
    }


}
