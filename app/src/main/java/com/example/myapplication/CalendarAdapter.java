package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{
    ArrayList<Date> dayList;

    public CalendarAdapter(ArrayList<Date> dayList){
        this.dayList=dayList;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.calendar_cell, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position){
        Date monthDate= dayList.get(position);

        Calendar dateCalendar=Calendar.getInstance();

        dateCalendar.setTime(monthDate);

        int currentDay=CalendarUtil.selectedDate.get(Calendar.DAY_OF_MONTH);
        int currentMonth=CalendarUtil.selectedDate.get(Calendar.MONTH)+1;
        int currentYear=CalendarUtil.selectedDate.get(Calendar.YEAR);

        int displayDay=dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth=dateCalendar.get(Calendar.MONTH)+1;
        int displayYear=dateCalendar.get(Calendar.YEAR);

        if(displayMonth==currentMonth && displayYear==currentYear){
            holder.parentView.setBackgroundColor(Color.parseColor("#D5D5D5"));

            holder.itemView.setBackgroundColor(Color.parseColor("#CEFBC9"));

        }else {
            holder.parentView.setBackgroundColor(Color.parseColor("#F6F6F6"));
        }

        int dayNo=dateCalendar.get(Calendar.DAY_OF_MONTH);
        holder.dayText.setText(String.valueOf(dayNo));


        if ((position+1)%7==0){
            holder.dayText.setTextColor(Color.BLUE);
        }else if(position==0 || position%7==0){
            holder.dayText.setTextColor(Color.RED);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount(){
        return dayList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder{
        TextView dayText;
        View parentView;

        public CalendarViewHolder(@NonNull View itemView){
            super(itemView);

            dayText=itemView.findViewById(R.id.dayText);

            parentView=itemView.findViewById(R.id.parentView);
        }
    }

}
