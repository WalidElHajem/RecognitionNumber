package com.example.walid.recognitionnumber.cameratool;

import android.graphics.Bitmap;
import android.os.Environment;

public interface CameraInterface {

    public static final String OCR_TEXT = "OcrText";
    public static final String OCR_ACCURACY = "OcrAccuracy";
    public static final String DATA_PATH = Environment
            .getExternalStorageDirectory().toString() + "/RecognitionNumber/";
    public static final String LANG = "eng";
    public static final int OCR_QUEUE_SIZE = 3;


    interface OCRListener {
         void onTextRecognized(String text, float accuracy);
    }

    interface CropListener {
         void onCropUpdate(Bitmap image);
    }
}