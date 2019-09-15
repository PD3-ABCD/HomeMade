package com.example.MaaKaKhana.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.MaaKaKhana.MainActivity;
import com.example.MaaKaKhana.R;

import static android.content.Context.MODE_PRIVATE;

public class LogoutFragment extends Fragment {

    private LogoutViewModel logoutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logout();
        return null;
    }

    private void logout() {
        FragmentActivity activity = getActivity();
        if (activity!=null) {
            activity.getSharedPreferences("session",MODE_PRIVATE).edit().clear().apply();
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
