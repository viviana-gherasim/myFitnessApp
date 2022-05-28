package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NutritionMain extends AppCompatActivity{

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView t1;
    protected User user;
    private CardView cardView1;
    private Button chatBot, takeQuiz;

//    @BindView(R.id.card_view_booking)
//    void booking() {
//        startActivity(new Intent(getActivity(), BookingActivity.class));
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_home);

        t1 = (TextView) findViewById(R.id.email_user);  //email
        cardView1 = (CardView) findViewById(R.id.card_view_booking);

        chatBot = (Button) findViewById(R.id.buttonChatBot);
        takeQuiz = (Button) findViewById(R.id.buttonQuiz);

        FirebaseUser userConnected = FirebaseAuth.getInstance().getCurrentUser();
        if(userConnected != null) {
            String userEmail = userConnected.getEmail();
            database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
            databaseReference = database.getReference().child("Users");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot d : snapshot.getChildren()) {
                        user = d.getValue(User.class);
                        if(user.getEmail().equals(userEmail)) {
                            t1.setText(user.getEmail());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://kajabi-storefronts-production.kajabi-cdn.com/kajabi-storefronts-production/blogs/33996/images/Fe2A4oIS0aQnFu98xveA_Blog_Posts.png", "The foods"));
        slideModels.add(new SlideModel("https://www.nhmrc.gov.au/sites/default/files/images/featured-image/Nutrition.jpg", "We choose"));
        slideModels.add(new SlideModel("https://assets.sweat.com/html_body_blocks/images/000/009/927/original/NutritionDefinition_enb9d1cc7ac2e4031aec72392988db7d86.jpg?1538607034", "Make a difference "));
        imageSlider.setImageList(slideModels, true);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionMain.this, BookingActivity.class));
            }
        });

        chatBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionMain.this, MessageMain.class));
            }
        });

        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionMain.this, QuizMain.class));
            }
        });
    }


}
