package com.example.nekr0s.get_my_driver_card.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PhotoEncodeHelper {

    public static String getByteString(Uri uri, Context context) throws FileNotFoundException {
        final InputStream imageStream = context.getContentResolver().openInputStream(uri);
        final Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        return encodedImage(bitmap);
    }

    private static String encodedImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}
