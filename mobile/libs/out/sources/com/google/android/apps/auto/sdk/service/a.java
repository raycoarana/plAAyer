package com.google.android.apps.auto.sdk.service;

import com.google.android.apps.auto.sdk.service.a.c;
import com.google.android.gms.car.CarApi;
import com.google.android.gms.car.CarFirstPartyManager;
import com.google.android.gms.car.CarNotConnectedException;
import com.google.android.gms.car.CarNotSupportedException;

public class a {
    public Object a(CarApi carApi, String str) throws CarNotSupportedException, CarNotConnectedException {
        if (str.equals(CarFirstPartyManager.SERVICE_NAME)) {
            return new c((CarFirstPartyManager) carApi.getCarManager(CarFirstPartyManager.SERVICE_NAME));
        }
        return null;
    }
}
