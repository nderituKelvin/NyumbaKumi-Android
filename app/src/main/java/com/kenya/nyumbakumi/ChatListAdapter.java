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
 * Created by Nderitu on 15/11/2017.
 */

public class ChatListAdapter extends ArrayAdapter<ChatListy> {
    private final Context context;
    private final ArrayList<ChatListy> chats;

    public ChatListAdapter(Context context, ArrayList<ChatListy> chats) {
        super(context, R.layout.custom_chat_list_view, chats);
        this.context = context;
        this.chats = chats;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View chatList = inflater.inflate(R.layout.custom_chat_list_view, parent, false);
        TextView names = chatList.findViewById(R.id.chatNameOfMember);
        TextView theTime = chatList.findViewById(R.id.theTime);
        TextView theMessage = chatList.findViewById(R.id.theTextMessage);

        names.setText(chats.get(position).getNames());
        theTime.setText(chats.get(position).getTime());
        theMessage.setText(chats.get(position).getMessage());

        return chatList;
    }
}
