package com.example.firebase_trial;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtname, txtage;
    Button btnsave;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtname = (EditText)findViewById(R.id.txtname);
        txtage = (EditText)findViewById(R.id.txtage);
        btnsave = (Button)findViewById(R.id.btnsave);
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");

        Toast.makeText(this, "Firebase connected successfully", Toast.LENGTH_SHORT).show();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                int agea = Integer.parseInt(txtage.getText().toString().trim());
                member.setName(txtname.getText().toString().trim());
                member.setAge(agea);

                reff.push().setValue(member);
                Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
