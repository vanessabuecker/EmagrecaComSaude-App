package com.vbuecker.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rv_main);

        String color_string ="#FEF09A";
        int myColor = Color.parseColor(color_string);

        String color_string2 ="#ABC6F4";
        int myColor2 = Color.parseColor(color_string2);


        List<MainItem> mainItems = new ArrayList<>();
        mainItems.add(new MainItem(1, R.drawable.icon, R.string.label_imc, myColor));
        mainItems.add(new MainItem(2, R.drawable.ic_baseline_remove_red_eye_24, R.string.label_tmb, myColor2));
        mainItems.add(new MainItem(3, R.drawable.did_you_know, R.string.empty, Color.TRANSPARENT));


        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvMain.setLayoutManager(layoutManager);
        MainAdapter adapter = new MainAdapter(mainItems);

        adapter.setListener(id -> {
            switch (id){
                case 1:
                   startActivity (new Intent (MainActivity.this, ActivityImc.class));
                    break;
                case 2:
                    startActivity (new Intent( MainActivity.this, TmbActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this, DidUknowActivity.class));
                    break;
            }

        });
        rvMain.setAdapter(adapter);
        rvMain.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
    //construtor do adapter

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
        private List<MainItem> mainItems;
        private OnItemClickListener listener;

        public MainAdapter(List<MainItem> mainItems) {
            this.mainItems = mainItems;
        }

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.activity_main_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            MainItem mainItemCurrent = mainItems.get(position);
            holder.bind(mainItemCurrent);
        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }


        private class MainViewHolder extends RecyclerView.ViewHolder {

            public MainViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void bind(MainItem item) {
                TextView txtName = itemView.findViewById(R.id.item_txt_name);
                ImageView imgIcon = itemView.findViewById(R.id.item_img_icon);
                LinearLayout btnImc = (LinearLayout) itemView.findViewById(R.id.btn_imc);


                btnImc.setOnClickListener(v -> {
                    listener.onClick(item.getId());
                });
                txtName.setText(item.getTextStringId());
                imgIcon.setImageResource(item.getDrawableId());
                btnImc.setBackgroundColor(item.getColor());


            }
        }
    }
}

