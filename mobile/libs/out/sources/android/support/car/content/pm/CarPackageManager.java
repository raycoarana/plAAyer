package android.support.car.content.pm;

import android.support.car.CarManagerBase;
import android.support.car.CarNotConnectedException;

public abstract class CarPackageManager implements CarManagerBase {
    public abstract boolean isActivityAllowedWhileDriving(String str, String str2) throws CarNotConnectedException;

    public abstract boolean isServiceAllowedWhileDriving(String str, String str2) throws CarNotConnectedException;
}
