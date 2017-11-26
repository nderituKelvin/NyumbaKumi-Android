package com.kenya.nyumbakumi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nderitu on 13/11/2017.
 */

public class ProvidersAdapter extends ArrayAdapter<ProvidersListy> {
    private final Context context;
    private final ArrayList<ProvidersListy> values;

    public ProvidersAdapter(@NonNull Context context, ArrayList<ProvidersListy> values) {
        super(context, R.layout.manage_providers_custom_list, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custList = layoutInflater.inflate(R.layout.manage_providers_custom_list, parent, false);
        TextView theNameofCompany = custList.findViewById(R.id.theNameofCompany);
        TextView thePhoneOfCompany = custList.findViewById(R.id.thePhoneOfCompany);

        theNameofCompany.setText(values.get(position).getName());
        thePhoneOfCompany.setText(values.get(position).getPhone());

        return custList;
    }
}
