package com.example.nekr0s.get_my_driver_card.views.create.attatchments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;

public class DocumentsActivity extends AppCompatActivity {

    public static final String REQUEST_SO_FAR = "REQUEST_SO_FAR";
    private Request mRequestSoFar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);

        // Gets request so far
        Intent intent = getIntent();
        mRequestSoFar = (Request) intent.getSerializableExtra(REQUEST_SO_FAR);
    }
}
