package com.kenya.nyumbakumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewMembers extends AppCompatActivity {

    ListView listViewMembers;
    ArrayList<MemberListy> membersData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_members);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewMembers = findViewById(R.id.members_list_view);
        membersData = new ArrayList<MemberListy>();

        //create data and load it to list here
        MemberListy member1 = new MemberListy("Nderitu Kelvin", "0705314090", "imageBBB");
        MemberListy member2 = new MemberListy("Edwin Macha", "0705314090", "imageBBB");
        MemberListy member3 = new MemberListy("Collins Bwayo", "0705314090", "imageBBB");
        MemberListy member4 = new MemberListy("Gladys Wangari", "0705314090", "imageBBB");
        MemberListy member5 = new MemberListy("Joy Wambui", "0705314090", "imageBBB");

        //add to a list
        membersData.add(member1);
        membersData.add(member2);
        membersData.add(member3);
        membersData.add(member4);
        membersData.add(member5);

        MemberViewAdapter memberViewAdapter = new MemberViewAdapter(this, membersData);

        listViewMembers.setAdapter(memberViewAdapter);
    }
}
