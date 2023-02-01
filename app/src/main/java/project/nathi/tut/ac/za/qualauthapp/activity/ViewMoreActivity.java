package project.nathi.tut.ac.za.qualauthapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import project.nathi.tut.ac.za.qualauthapp.R;

public class ViewMoreActivity extends AppCompatActivity {


    private TextView txtFirstName, txtSecondNme, txtLastName, txtIdNumber, txtGenger, txtQualification,txtStatus, txtError;
    private ImageView image;
    private Button btn_viewMore;
    private ProgressBar progressBar;
    private TicketView ticketView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtFirstName = findViewById(R.id.firstName);
        txtSecondNme = findViewById(R.id.secondName);
        txtLastName = findViewById(R.id.lastName);
        txtIdNumber = findViewById(R.id.lastName);
        txtGenger = findViewById(R.id.lastName);
        txtQualification = findViewById(R.id.txtQualificationNames);
        txtStatus = findViewById(R.id.txtStatus);
        image = findViewById(R.id.image);
        txtError = findViewById(R.id.txt_error);
        ticketView = findViewById(R.id.layout_ticket);
        progressBar = findViewById(R.id.progressBar);

        btn_viewMore = (Button) findViewById(R.id.btn_viewMore);

        btn_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewMoreActivity.class);
                startActivity(intent);

            }
        });
        ticketView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
    private void showNoTicket() {
        txtError.setVisibility(View.VISIBLE);
        ticketView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
}
