package com.example.myfitnessapp.Scanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.SearchFoodBarcode;
import com.google.zxing.Result;

public class ScannerDiaryActivity extends AppCompatActivity {

    private CodeScanner codeScanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_scanner);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this, scannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ScannerDiaryActivity.this, SearchFoodBarcode.class);
                        intent.putExtra("Camera", result.getText().toString().trim());
                        startActivity(intent);
                    }
                });
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        codeScanner.startPreview();
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        codeScanner.releaseResources();
    }
}
