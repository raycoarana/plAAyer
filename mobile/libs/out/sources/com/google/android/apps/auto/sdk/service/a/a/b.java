package com.google.android.apps.auto.sdk.service.a.a;

import android.support.car.CarNotConnectedException;
import android.support.car.media.CarAudioRecord;

public final class b extends CarAudioRecord {
    private com.google.android.gms.car.CarAudioRecord a;

    b(com.google.android.gms.car.CarAudioRecord carAudioRecord) {
        this.a = carAudioRecord;
    }

    public final int getAudioSessionId() {
        return 0;
    }

    public final int getBufferSize() {
        return this.a.getBufferSize();
    }

    public final int getRecordingState() {
        return this.a.getRecordingState();
    }

    public final int getState() {
        return this.a.getState();
    }

    public final int read(byte[] bArr, int i, int i2) throws IllegalStateException, CarNotConnectedException {
        try {
            return this.a.read(bArr, i, i2);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void release() {
        this.a.release();
    }

    public final void startRecording() throws CarNotConnectedException {
        try {
            this.a.startRecording();
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void stop() {
        this.a.stop();
    }
}
