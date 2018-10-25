package com.example.nekr0s.get_my_driver_card.views.CameraView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.nekr0s.get_my_driver_card.R;
import com.otaliastudios.cameraview.CameraUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicturePreviewActivity extends Activity {
    private static boolean mIsFrontFacing;
    private static WeakReference<byte[]> mImage;

    @BindView(R.id.picpreview_image_view)
    ImageView mImageView;

    public static void setImage(@Nullable byte[] imageBytes, boolean isFrontFacing) {
        mIsFrontFacing = isFrontFacing;

        mImage = (imageBytes != null)
                ? new WeakReference<>(imageBytes)
                : null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_preview_layout);

        ButterKnife.bind(this);

        byte[] bitmap = (mImage == null)
                ? null
                : mImage.get();

        if (bitmap == null) {
            finish();
            return;
        }

        CameraUtils.decodeBitmap(bitmap, 1000, 1000, bitmapCallback ->
        {
            mImageView.setImageBitmap(bitmapCallback);

            if (mIsFrontFacing)
                mImageView.setRotationY(180);
        });
    }
}