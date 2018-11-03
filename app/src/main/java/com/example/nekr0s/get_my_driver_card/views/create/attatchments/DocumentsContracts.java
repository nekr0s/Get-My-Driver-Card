package com.example.nekr0s.get_my_driver_card.views.create.attatchments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;

public interface DocumentsContracts {
    interface Presenter {
        Intent capturePhoto(boolean isFront);

        void subscribe(View view);

        boolean deviceHasCamera();

        void unsubscribe();

        void savePicToGallery();

        File createImageFile() throws IOException;

        Bitmap getBitmap(BitmapFactory.Options bmOptions);
    }

    interface View {
        void setPresenter(Presenter presenter);

        Bitmap rotateImage(Bitmap img, int degree);

        void showError(Throwable throwable);

        void fillIcon(String whichButton);
    }
}
