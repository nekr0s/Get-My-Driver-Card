package com.example.nekr0s.get_my_driver_card.views.create.attatchments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DocumentsPresenter implements DocumentsContracts.Presenter {

    private DocumentsContracts.View mView;
    private Context mContext;

    private String mCurrentPhotoPath;

    DocumentsPresenter(Context context) {
        mContext = context;
    }

    @Override
    public Intent capturePhoto(boolean isFront) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (isFront) takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(mContext.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(mContext,
                        "com.example.nekr0s.get_my_driver_card.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            }
        }
        return takePictureIntent;
    }

    @Override
    public void subscribe(DocumentsContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void savePicToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = FileProvider.getUriForFile(mContext,
                "com.example.nekr0s.get_my_driver_card.fileprovider",
                f);
        mediaScanIntent.setData(contentUri);
        mContext.sendBroadcast(mediaScanIntent);
//        MediaScannerConnection.scanFile(mContext,
//                new String[]{f.toString()}, null,
//                (path, uri) -> {
//                    Log.i("ExternalStorage", "Scanned " + path + ":");
//                    Log.i("ExternalStorage", "-> uri=" + uri);
//                });
    }

    @Override
    public File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public Bitmap getBitmap(BitmapFactory.Options bmOptions) {
        return BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
    }

    @Override
    public String getCurrentPath() {
        return mCurrentPhotoPath;
    }

    @Override
    public boolean deviceHasCamera() {
        return mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void setCurrentPhotoPath(String mCurrentPhotoPath) {
        this.mCurrentPhotoPath = mCurrentPhotoPath;
    }
}
