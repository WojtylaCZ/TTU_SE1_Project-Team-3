/**
 * An Adapter for the Analyzed Study Activity. Will most likely ignore
 * Created by Colton Mikes
 */


package com.ttu_se1_project_team_3.activities;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.ttu_se1_project_team_3.R;


public class AnalyzedStudyListAdapter extends ArrayAdapter<String[]> {
    int groupid;
    List<String[]> items;
    Context context;
    String path;

    public AnalyzedStudyListAdapter(Context context, int vg, int id, List<String[]> items) {
        super(context, vg, id, items);
        this.context = context;
        groupid = vg;
        this.items = items;

    }

    static class ViewHolder {
        public TextView textid;
        public TextView textname;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textid = (TextView) rowView.findViewById(R.id.txtid);
            viewHolder.textname = (TextView) rowView.findViewById(R.id.txtname);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        String[] row = items.get(position);
        holder.textid.setText(row[0]);
        holder.textname.setText(row[1]);

        return rowView;
    }
}