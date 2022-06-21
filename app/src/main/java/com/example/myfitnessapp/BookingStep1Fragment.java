package com.example.myfitnessapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Manager.City;
import com.example.myfitnessapp.Manager.Nutritionist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class BookingStep1Fragment extends Fragment implements AllCitiesLoadListener, BranchLoadListener {

    static BookingStep1Fragment instance;

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    private City city;

    private List<String> list;

    private Nutritionist nutritionist;
    private List<Nutritionist> nutritionistList = new ArrayList<Nutritionist>();
    CardView cardView;


    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    MyNutritionistAdapter adapter1;

    AllCitiesLoadListener allCitiesLoadListener;
    BranchLoadListener branchLoadListener;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    @BindView(R.id.recycler_city)
    RecyclerView recycler_city;

    Unbinder unbinder;
    AlertDialog dialog;

    public static BookingStep1Fragment getInstance() {
        if(instance == null)
            instance = new BookingStep1Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("City");

        allCitiesLoadListener = this;
        branchLoadListener = this;

        dialog = new SpotsDialog.Builder().setContext(getActivity()).build();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step1, container, false);
        unbinder = ButterKnife.bind(this, itemView);

        initView();

        spinner = itemView.findViewById(R.id.spinner);
        recycler_city = itemView.findViewById(R.id.recycler_city);
        cardView = itemView.findViewById(R.id.card_nutritionist);

        adapter1 = new MyNutritionistAdapter(getActivity(), nutritionistList);
        recycler_city.setAdapter(adapter1);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);

        loadAllCities();


        return itemView;
    }

    private void initView() {
        recycler_city.setHasFixedSize(true);
        recycler_city.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycler_city.addItemDecoration(new SpacesItemDecoration(4));
    }

    private void loadAllCities() {

        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()) {
                    list.add(d.getValue(City.class).name);
                }
                allCitiesLoadListener.onAllCitiesLoadSuccess(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onAllCitiesLoadSuccess(List<String> areaNameList) {

        spinner.setItems(areaNameList);

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if(position >= 0) {
                    loadBranchOfCity(item.toString());
                }
            }
        });
    }

    private void loadBranchOfCity(String cityName) {

        //dialog.show();

        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("City");

        Query findByNameQuery = databaseReference.orderByChild("name").equalTo(cityName);
        findByNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key = "";
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    key = child.getKey();
                    break;
                }
                System.out.println(key);
                databaseReference = database.getReference().child("City").child(key).child("Nutritionists");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        nutritionistList.clear();
                        for (DataSnapshot d : snapshot.getChildren()) {
                            nutritionist = d.getValue(Nutritionist.class);
                            System.out.println(nutritionist);
                            nutritionist.id = d.getKey();
                            nutritionistList.add(nutritionist);
                        }
                        adapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onAllCitiesLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBranchLoadSuccess(List<Nutritionist> nutritionistList) {

         dialog.dismiss();
    }

    @Override
    public void onBranchLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

}
