package com.example.nekr0s.get_my_driver_card.views.create.documents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface DocumentsContracts {
    interface Presenter {
        Intent capturePhoto(boolean isFront);

        void subscribe(View view);

        boolean deviceHasCamera();

        void unsubscribe();

        File createImageFile() throws IOException;

        Bitmap getBitmap(BitmapFactory.Options bmOptions);

        Uri getCurrentUri();
    }

    interface View {
        void setPresenter(Presenter presenter);

        Bitmap rotateImage(Bitmap img, int degree);

        void showError(Throwable throwable);

        void fillIcon(String whichButton) throws FileNotFoundException;
    }
}
