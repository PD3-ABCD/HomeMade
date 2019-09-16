package com.example.MaaKaKhana.ui.seller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SellerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SellerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Favourite fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}