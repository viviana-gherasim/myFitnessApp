package com.example.myfitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class QuizMain extends AppCompatActivity {

    private TextView questionTV, questionNumberTV;
    private Button option1Btn, option2Btn, option3Btn, option4Btn;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    int currentScore = 0, questionAttempted = 1, currentPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_main);

        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);
        quizModalArrayList = new ArrayList<>();
        random = new Random();

        getQuizQuestion(quizModalArrayList);
        currentPos = random.nextInt(quizModalArrayList.size());
        setDataToViews(currentPos);

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

    }

    private void showBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuizMain.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet, (LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        scoreTV.setText("Your score is \n"+currentScore + "/15");
        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
                questionAttempted = 1;
                currentScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void setDataToViews(int currentPos) {
        questionNumberTV.setText("Questions Attempted: " + questionAttempted + "/15");
        if(questionAttempted == 15) {
            showBottomSheet();
        }
        else {
            questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModalArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModalArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModalArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModalArrayList.get(currentPos).getOption4());
        }

    }

    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("Which of these is a sugar found only in Diary products?", "Glucose", "Fructose", "Lactose", "Maltose", "Lactose"));
        quizModalArrayList.add(new QuizModal("What is the average requirement of proteins for a healthy adult per day?", "40-50g/day", "85-95g/day", "100-120g/day", "55-65g/day", "55-65g/day"));
        quizModalArrayList.add(new QuizModal("Which nutrients prevents constipation?", "Fibre", "Protein", "Vitamins", "Minerals", "Fibre"));
        quizModalArrayList.add(new QuizModal("What is measured in terms of calories?", "Fat", "Energy", "Vitamins", "Protein", "Energy"));
        quizModalArrayList.add(new QuizModal("What is the recommended amount of salt intake for a healthy adult per day?", "3-4g/day", "10-12g/day", "9.5-11.5g/day", "8-10g/day", "8-10g/day"));
        quizModalArrayList.add(new QuizModal("Which of these is a healthy fat?", "Trans fat", "Saturated fat", "Unsaturated fat", "Visceral fat", "Unsaturated fat"));
        quizModalArrayList.add(new QuizModal("Which of these trace minerals helps maintaining kidneys?", "Selenium", " Molybdenum", "Manganesium", "Chloride", "Molybdenum"));
        quizModalArrayList.add(new QuizModal("What is the main source of energy in Indian food?", "Carbohydrate", "Protein", "Fat", "Vitamins", "Carbohydrate"));
        quizModalArrayList.add(new QuizModal("What of these foods is said to have complete protein?", "Panner", "Tofu", "Egg", "Chicken", "Egg"));
        quizModalArrayList.add(new QuizModal("How many times of vitamin B do we consume?", "12", "10", "8", "6", "8"));
        quizModalArrayList.add(new QuizModal("Which mineral kick starts bain function in the morning?", "Iron", "Calcium", "Zinc", "Sodium", "Sodium"));
        quizModalArrayList.add(new QuizModal("Which of the following sugars is obtained from fruits?", "Fructose", "Maltose", "Galactose", "Glucose", "Fructose"));
        quizModalArrayList.add(new QuizModal("Which of the following vitamins has the least dietary source?", "Vitamin A", "vitamin B", "vitamin C", "vitamin D", "vitamin D"));
        quizModalArrayList.add(new QuizModal("Which mineral is required in higher proportion compared to sodium?", "Zinc", "Potassium", "Selenium", "Nickel", "Potassium"));
        quizModalArrayList.add(new QuizModal("Which vitamin is known for vitamin H?", "Biotin", "Thiamine", "Calciferol", "Cobalmine", "Biotin"));
    }
}
