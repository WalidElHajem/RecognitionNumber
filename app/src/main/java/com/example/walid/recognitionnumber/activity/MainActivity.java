package com.example.walid.recognitionnumber.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.walid.recognitionnumber.R;
import com.example.walid.recognitionnumber.cameratool.CameraInterface;
import com.example.walid.recognitionnumber.cameratool.CameraTool;

public class MainActivity extends Activity implements CameraInterface.OCRListener, CameraInterface.CropListener {

    private static final String TAG = "MainActivity";
    private CameraTool mCameraTool;
    private RelativeLayout mCameraRL;
    private TextView mOcrTV;
    private ImageView mCropIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraRL = (RelativeLayout) findViewById(R.id.cameraRL);
        mOcrTV = (TextView) findViewById(R.id.ocrTV);
        mCropIV = (ImageView) findViewById(R.id.cropIV);

        mCameraTool = new CameraTool(this);
        mCameraTool.container(mCameraRL)		// ViewGroup that will contain the Preview and Picker views
                .OCRListener(this)			// Listener for OCR results
                .cropListener(this)			// Listener for Picker changes
                .save(10)					// Stores last 10 previews on SD-Card
                .source(CameraTool.CameraSource.BACK)	// Defines source camera
                .start();					// Starts preview and OCR
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTextRecognized(String text, float accuracy) {
        mOcrTV.setText(text);
    }

    @Override
    public void onCropUpdate(final Bitmap image) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCropIV.setImageBitmap(image);
            }
        });


    }

}
