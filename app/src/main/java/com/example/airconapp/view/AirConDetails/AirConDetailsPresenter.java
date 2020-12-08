package com.example.airconapp.view.AirConDetails;

import com.example.airconapp.domain.AirCon;

public class AirConDetailsPresenter
{
    AirConDetailsView view;
    AirCon airCon;

    public AirConDetailsPresenter(AirConDetailsView view) {
        this.view = view;
    }
}
