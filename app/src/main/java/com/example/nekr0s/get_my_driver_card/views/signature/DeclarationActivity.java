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
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeclarationActivity extends Activity {
    @BindView(R.id.header_msg_declaration)
    TextView mHeader;

    @BindView(R.id.sign_here)
    ImageView mSignImage;

    @BindView(R.id.checkbox)
    CheckBox mCheckBox;

    @BindView(R.id.declaration_text)
    TextView mDeclarationText;

    @BindView(R.id.finish_button)
    Button mFinishButton;

    public static final String ALMOST_READY_REQUEST = "ALMOST_READY_REQUEST";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        ButterKnife.bind(this);

        // Get intent
        Intent intent = getIntent();
        intent.getSerializableExtra(ALMOST_READY_REQUEST);


        mSignImage.setOnClickListener(onButtonClick);

        //disable button if checkbox is not checked else enable button
        mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mFinishButton.setEnabled(true);
            } else {
                mFinishButton.setEnabled(false);
            }
        });

        //to get imagepath from SignatureActivity and set it on ImageView
        String image_path = getIntent().getStringExtra("imagePath");
        Bitmap bitmap = BitmapFactory.decodeFile(image_path);
        mSignImage.setImageBitmap(bitmap);
    }

    Button.OnClickListener onButtonClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCheckBox.isChecked()) {
                Intent i = new Intent(DeclarationActivity.this, SignatureActivity.class);
                startActivity(i);
            } else {
                mFinishButton.setEnabled(false);
            }
        }
    };
}
