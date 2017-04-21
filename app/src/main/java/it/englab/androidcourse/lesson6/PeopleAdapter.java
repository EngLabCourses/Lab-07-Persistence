package it.englab.androidcourse.lesson6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.englab.androidcourse.lesson6.db.Person;

/**
 * Created by Peppe on 18/04/2017.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private List<Person> items;

    public PeopleAdapter() {
        items = new ArrayList<>();
    }

    public void setList(List<Person> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person,parent,false));
    }

    @Override
    public void onBindViewHolder(PeopleAdapter.ViewHolder holder, int position) {
        Person p = items.get(position);
        holder.name.setText(p.getName());
        holder.surname.setText(p.getSurname());
        holder.email.setText(p.getEmail());
        holder.telephone.setText(String.valueOf(p.getTelephone()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, surname, email, telephone;

        ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            surname = (TextView) itemView.findViewById(R.id.surname);
            email = (TextView) itemView.findViewById(R.id.email);
            telephone = (TextView) itemView.findViewById(R.id.telephone);
        }

    }
}
