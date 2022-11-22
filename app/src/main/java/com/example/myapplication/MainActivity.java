package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView monthYearText;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthYearText=findViewById(R.id.monthYearText);
        Button prevBtn=findViewById(R.id.pre_btn);
        Button nextBtn=findViewById(R.id.next_btn);
        recyclerView=findViewById(R.id.recyclerView);

        CalendarUtil.selectedDate=Calendar.getInstance();

        setMonthView();

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtil.selectedDate.add(Calendar.MONTH,-1);
                setMonthView();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtil.selectedDate.add(Calendar.MONTH,1);
                setMonthView();
            }
        });
    }

    private String monthYearFromDate(Calendar calendar){

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;

        String monthYear=month+"월"+year;

        return monthYear;
    }

    private void setMonthView(){

        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));

        ArrayList<Date> dayList=daysInMonthArray();

        CalendarAdapter adapter=new CalendarAdapter(dayList);

        RecyclerView.LayoutManager manager=new GridLayoutManager(getApplicationContext(), 7);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    private ArrayList<Date> daysInMonthArray(){
        ArrayList<Date> dayList=new ArrayList<>();

        Calendar monthCalendar=(Calendar) CalendarUtil.selectedDate.clone();

        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayOfMonth=monthCalendar.get(Calendar.DAY_OF_MONTH)-1;

        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth);

        while (dayList.size()<42){
            dayList.add(monthCalendar.getTime());

            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }


        return dayList;
    }}
