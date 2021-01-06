package com.google.android.apps.auto.sdk.service.a.a;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioFormat.Builder;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.support.car.CarNotConnectedException;
import android.support.car.media.CarAudioManager;
import android.support.car.media.CarAudioRecord;
import android.util.Log;
import com.google.android.gms.car.CarNotSupportedException;
import java.lang.reflect.InvocationTargetException;

public final class a extends CarAudioManager {
    private static final AudioFormat c = new Builder().setEncoding(2).setChannelMask(16).setSampleRate(16000).build();
    private final AudioManager a;
    private final com.google.android.gms.car.CarAudioManager b;

    public a(AudioManager audioManager, com.google.android.gms.car.CarAudioManager carAudioManager) {
        this.a = audioManager;
        this.b = carAudioManager;
    }

    private static AudioAttributes a(int i, int i2) {
        return new AudioAttributes.Builder().setContentType(i).setUsage(i2).build();
    }

    public final void abandonAudioFocus(OnAudioFocusChangeListener onAudioFocusChangeListener, AudioAttributes audioAttributes) {
        this.a.abandonAudioFocus(onAudioFocusChangeListener);
    }

    public final CarAudioRecord createCarAudioRecord(int i) throws SecurityException, CarNotConnectedException {
        try {
            return new b(this.b.createCarAudioRecord(0, 0, i));
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        } catch (CarNotSupportedException e2) {
            throw new UnsupportedOperationException(e2);
        }
    }

    public final AudioAttributes getAudioAttributesForCarUsage(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                return a(2, 1);
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return a(1, 12);
            case 8:
            case 9:
                return a(4, 13);
            default:
                Log.i("CarAudioManagerGms", "An unknown audio usage was passed in.  Audio usage = " + i);
                return a(0, 0);
        }
    }

    public final AudioFormat getAudioRecordAudioFormat() {
        return c;
    }

    public final int getAudioRecordMaxBufferSize() {
        return 524288;
    }

    public final int getAudioRecordMinBufferSize() throws CarNotConnectedException {
        try {
            return this.b.getAudioRecordMinBufferSize(0, 0);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        } catch (CarNotSupportedException e2) {
            throw new UnsupportedOperationException(e2);
        }
    }

    public final boolean isAudioRecordSupported() throws CarNotConnectedException {
        try {
            return this.b.isAudioRecordStreamSupported(0);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final boolean isMediaMuted() throws CarNotConnectedException {
        return false;
    }

    public final void onCarDisconnected() {
    }

    public final int requestAudioFocus(OnAudioFocusChangeListener onAudioFocusChangeListener, AudioAttributes audioAttributes, int i) throws IllegalArgumentException {
        return requestAudioFocus(onAudioFocusChangeListener, audioAttributes, i, 0);
    }

    public final int requestAudioFocus(OnAudioFocusChangeListener onAudioFocusChangeListener, AudioAttributes audioAttributes, int i, int i2) throws IllegalArgumentException {
        try {
            return ((Integer) this.a.getClass().getMethod("requestAudioFocus", new Class[]{OnAudioFocusChangeListener.class, AudioAttributes.class, Integer.TYPE, Integer.TYPE}).invoke(this.a, new Object[]{onAudioFocusChangeListener, audioAttributes, Integer.valueOf(i), Integer.valueOf(i2)})).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.w("CarAudioManagerGms", "Audio focus request failed", e);
            return 0;
        }
    }

    public final boolean setMediaMute(boolean z) throws CarNotConnectedException {
        return false;
    }
}
