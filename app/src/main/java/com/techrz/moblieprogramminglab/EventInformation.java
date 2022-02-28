package com.techrz.moblieprogramminglab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class EventInformation extends AppCompatActivity {

    private EditText etName;
    private EditText etPlace;
    private EditText etdateTime;
    private EditText etCapacity;
    private EditText etBudget;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etDescription;
    private RadioButton rdIndoor;
    private RadioButton rdOutDoor;
    private RadioButton rdOnline;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);

        findViewById(R.id.btnCancel).setOnClickListener(v->finish());
        findViewById(R.id.btnSave).setOnClickListener(v -> save());
        etName = findViewById(R.id.etName);
        etPlace = findViewById(R.id.etPlace);
        etdateTime = findViewById(R.id.etdateTime);
        etCapacity = findViewById(R.id.etCapacity);
        etBudget = findViewById(R.id.etBudget);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etDescription = findViewById(R.id.etDescription);
        rdIndoor = findViewById(R.id.rdIndoor);
        rdOutDoor = findViewById(R.id.rdOutDoor);
        rdOnline = findViewById(R.id.rdOnline);
        errorMessage = findViewById(R.id.errorMessage);

    }

    void save(){
        String error = "";
        String name = etName.getText().toString().trim();
        if(name.length()>15){
            error+="Name length is too high";
        }
        else{
            System.out.println("Event name "+ name);
        }

        String Place = etPlace.getText().toString().trim();
        if(Place.length()>50){
            error+="Place length is too high";
        }
        else{
            System.out.println("Place name "+ Place);
        }

        boolean indoorIsChecked = rdIndoor.isChecked();
        if(indoorIsChecked== true){
            System.out.println("Indoor checked ");
        }

        boolean outDoorIsChecked = rdOutDoor.isChecked();
        if(outDoorIsChecked== true){
            System.out.println("Outdoor checked ");
        }

        boolean onlineIsChecked = rdOnline.isChecked();
        if(onlineIsChecked== true){
            System.out.println("Online checked ");
        }

        String dateTime = etdateTime.getText().toString().trim();
        System.out.println("Date Time "+ dateTime);

        String Capacity = etCapacity.getText().toString().trim();
        int capacityInt=Integer.parseInt(Capacity);
        if(capacityInt <=0 ){
            error+= "Capacity Must be greater than 0 ";
            error+= "\n";
        }
        else{
            System.out.println("Capacity "+ capacityInt);
        }

        String Budget = etBudget.getText().toString().trim();
        int BudgetInt=Integer.parseInt(Budget);
        if(BudgetInt <=0 ){
            error+= "Budget Must be greater than 0 ";
            error+= "\n";
        }
        else{
            System.out.println("Budget "+ BudgetInt);
        }

        String Email = etEmail.getText().toString().trim();
        if(isValidEmail(Email) ==  false){
            error+= "Wrong Email";
            error+= "\n";
        }
        else{
            System.out.println("Email "+ Email);
        }
        String Phone = etPhone.getText().toString().trim();
        int phoneInt=Integer.parseInt(Phone);
        if(Phone.length()!= 11 || phoneInt <=0 ){
            error+= "Phone number got some error ";
            error+= "\n";
        }
        else{
            System.out.println("Phone "+ phoneInt);
        }

        String Description = etDescription.getText().toString().trim();
        System.out.println("Description "+ Description);

        System.out.println(error);
        errorMessage.setText(error);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("@EventInformation onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("@EventInformation onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("@EventInformation onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("@EventInformation onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("@EventInformation onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("@EventInformation onDestroy");
    }

}