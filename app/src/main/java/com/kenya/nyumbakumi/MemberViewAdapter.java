package com.kenya.nyumbakumi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nderitu on 12/11/2017.
 */

public class MemberViewAdapter extends ArrayAdapter<MemberListy> {
    private final Context context;
    private final ArrayList<MemberListy> values;

    public MemberViewAdapter(@NonNull Context context, ArrayList<MemberListy> list) {
        super(context, R.layout.view_members_customlist, list);
        this.context = context;
        this.values = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custList = layoutInflater.inflate(R.layout.view_members_customlist, parent, false);
        TextView theNameofMember = custList.findViewById(R.id.theNameofMember);
        TextView thePhoneofMember = custList.findViewById(R.id.thePhoneOfMember);
        ImageView proffPic = custList.findViewById(R.id.proffPic);

        //set the data
        theNameofMember.setText(values.get(position).getName());
        thePhoneofMember.setText(values.get(position).getPhone());
        //set the proffpic here too

        return custList;
    }


}
