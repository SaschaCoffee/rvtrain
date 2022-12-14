package com.example.sqldemo3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>
implements Filterable {
    private Context context;
    private ArrayList<CustomerModel> listContacts;
    private ArrayList<CustomerModel> mArrayList;
    private DataBaseHelper mData;

    ContactAdapter(Context context, ArrayList<CustomerModel> listContacts){
        this.context = context;
        this.listContacts = listContacts;
        this.mArrayList = listContacts;
        mData = new DataBaseHelper(context);
    }



    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listContacts = mArrayList;
                }
                else {
                    ArrayList<CustomerModel> filteredList = new ArrayList<>();
                    for (CustomerModel contacts : mArrayList) {
                        if (contacts.getName().toLowerCase().contains(charString)) {
                            filteredList.add(contacts);
                        }
                    }
                    listContacts = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listContacts;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listContacts = (ArrayList<CustomerModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactistlayout, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
    final CustomerModel contacts = listContacts.get(position);
    holder.tvName.setText(contacts.getName());
    holder.tvPhoneNum.setText(contacts.getPhno());

    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }
}
