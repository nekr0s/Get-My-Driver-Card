package com.example.nekr0s.get_my_driver_card.views.signature;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.nekr0s.get_my_driver_card.R;

public class DeclarationActivity extends Activity {
    Button signatureButton;
    ImageView signImage;
    CheckBox checkBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);
        signatureButton = findViewById(R.id.getSign);
        signatureButton.setOnClickListener(onButtonClick);
        checkBox = findViewById(R.id.checkbox);
        //disable button if checkbox is not checked else enable button
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                signatureButton.setEnabled(true);
            } else {
                signatureButton.setEnabled(false);
            }
        });
        //to get imagepath from SignatureActivity and set it on ImageView
        String image_path = getIntent().getStringExtra("imagePath");
        Bitmap bitmap = BitmapFactory.decodeFile(image_path);
        signImage.setImageBitmap(bitmap);
    }

    Button.OnClickListener onButtonClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkBox.isChecked()) {
                Intent i = new Intent(DeclarationActivity.this, SignatureActivity.class);
                startActivity(i);
            } else {
                signatureButton.setEnabled(false);
            }
        }
    };
}
