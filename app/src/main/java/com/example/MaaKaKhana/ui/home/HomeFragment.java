package com.example.MaaKaKhana.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.MaaKaKhana.R;


public class HomeFragment extends Fragment {


    ViewFlipper v_fliper;

    public HomeFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_home,container,false);
        v_fliper = view.findViewById(R.id.v_fliper);

        int images[]= {R.drawable.imag1,R.drawable.imag2,R.drawable.imag3};

        v_fliper = view.findViewById(R.id.v_fliper);

        for(int image:images){
            fliperImages(image);
        }

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(getActivity(),ImageSliderBar.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }

    public void fliperImages(int image){
        ImageView imageView=new ImageView(getContext());
        imageView.setImageResource(image);

        v_fliper.addView(imageView);
        v_fliper.setFlipInterval(3000);
        v_fliper.setAutoStart(true);


        //ANIMATION
        v_fliper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        v_fliper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }



}
