package com.example.MaaKaKhana.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.MaaKaKhana.R;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
     //   cartViewModel =
       //         ViewModelProviders.of(this).get(CartViewModel.class);
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        //cartViewModel.getText().observe(this, new Observer<String>() {
       //     @Override
         //   public void onChanged(@Nullable String s) {
           //     textView.setText(s);
            //}
        //});
        return view;
    }
}