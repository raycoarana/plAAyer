package com.google.android.apps.auto.sdk.service.a;

import android.support.car.CarInfoManager;
import android.support.car.CarNotConnectedException;
import com.google.android.gms.car.CarInfoManager.CarInfo;

public final class e extends CarInfoManager {
    private CarInfo a;

    public e(com.google.android.gms.car.CarInfoManager carInfoManager) throws CarNotConnectedException {
        if (carInfoManager == null) {
            throw new IllegalArgumentException("carInfoManager must be non-null.");
        }
        try {
            this.a = carInfoManager.loadCarInfo();
            carInfoManager.loadCarUiInfo();
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final int getDriverPosition() throws CarNotConnectedException {
        if (this.a == null) {
            throw new CarNotConnectedException();
        }
        switch (this.a.getDriverPosition()) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            default:
                return 0;
        }
    }

    public final String getHeadunitManufacturer() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getManufacturer();
        }
        throw new CarNotConnectedException();
    }

    public final String getHeadunitModel() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getHeadUnitModel();
        }
        throw new CarNotConnectedException();
    }

    public final String getHeadunitSoftwareBuild() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getHeadUnitSoftwareBuild();
        }
        throw new CarNotConnectedException();
    }

    public final String getHeadunitSoftwareVersion() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getHeadUnitSoftwareVersion();
        }
        throw new CarNotConnectedException();
    }

    public final String getManufacturer() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getManufacturer();
        }
        throw new CarNotConnectedException();
    }

    public final String getModel() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getModel();
        }
        throw new CarNotConnectedException();
    }

    public final String getModelYear() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getModelYear();
        }
        throw new CarNotConnectedException();
    }

    public final String getVehicleId() throws CarNotConnectedException {
        if (this.a != null) {
            return this.a.getVehicleId();
        }
        throw new CarNotConnectedException();
    }

    public final void onCarDisconnected() {
        this.a = null;
    }
}
