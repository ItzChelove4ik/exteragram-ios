package org.telegram.ui.Components;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class WallpaperParallaxEffect implements SensorEventListener {
    private Sensor accelerometer;
    private int bufferOffset;
    private Callback callback;
    private boolean enabled;
    private float[] pitchBuffer;
    private float[] rollBuffer;
    private SensorManager sensorManager;
    private WindowManager wm;

    public interface Callback {
        void onOffsetsChanged(int r1, int r2, float r3);
    }

    public WallpaperParallaxEffect(Context r3) {
        this.rollBuffer = new float[3];
        this.pitchBuffer = new float[3];
        this.wm = (WindowManager) r3.getSystemService("window");
        SensorManager r32 = (SensorManager) r3.getSystemService("sensor");
        this.sensorManager = r32;
        this.accelerometer = r32.getDefaultSensor(1);
    }

    public float getScale(int r2, int r3) {
        float r22 = r2;
        float r1 = AndroidUtilities.dp(16.0f) * 2;
        float r0 = (r22 + r1) / r22;
        float r23 = r3;
        return Math.max(r0, (r1 + r23) / r23);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor r1, int r2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent r17) {
        int r1 = this.wm.getDefaultDisplay().getRotation();
        float[] r2 = r17.values;
        float r4 = r2[0] / 9.80665f;
        float r7 = r2[1] / 9.80665f;
        float r22 = r2[2] / 9.80665f;
        float r23 = r22 * r22;
        float r5 = (float) ((Math.atan2(r4, Math.sqrt((r7 * r7) + r23)) / 3.141592653589793d) * 2.0d);
        float r24 = (float) ((Math.atan2(r7, Math.sqrt((r4 * r4) + r23)) / 3.141592653589793d) * 2.0d);
        if (r1 == 1) goto L10;
        if (r1 != 2) goto L6;
        float r12 = -r24;
        r24 = -r5;
        r5 = r12;
        goto L10
    L6:
        if (r1 == 3) goto L8;
        r5 = r24;
        r24 = r5;
        goto L10
    L8:
        r5 = -r5;
    L10:
        float[] r13 = this.rollBuffer;
        int r3 = this.bufferOffset;
        r13[r3] = r5;
        this.pitchBuffer[r3] = r24;
        this.bufferOffset = (r3 + 1) % r13.length;
        float r25 = 0.0f;
        float r42 = 0.0f;
        int r32 = 0;
    L11:
        float[] r52 = this.rollBuffer;
        if (r32 >= r52.length) goto L14;
        r25 = r25 + r52[r32];
        r42 = r42 + this.pitchBuffer[r32];
        r32 = r32 + 1;
        goto L11
    L14:
        float r26 = r25 / r52.length;
        float r43 = r42 / r52.length;
        if (r26 <= 1.0f) goto L19;
        float r53 = 2.0f;
    L17:
        r26 = r53 - r26;
    L21:
        int r72 = Math.round(AndroidUtilities.dpf2(16.0f) * r43);
        int r54 = Math.round(AndroidUtilities.dpf2(16.0f) * r26);
        float r44 = Math.max(-1.0f, Math.min(1.0f, (-r43) / 0.45f));
        float r27 = Math.max(-1.0f, Math.min(1.0f, (-r26) / 0.45f));
        float r33 = (float) Math.sqrt((r44 * r44) + (r27 * r27));
        float r45 = r44 / r33;
        float r28 = r27 / r33;
        float r29 = (float) (Math.atan2((r45 * (-1.0f)) - (r28 * 0.0f), (r45 * 0.0f) + (r28 * (-1.0f))) / 0.017453292519943295d);
        if (r29 >= 0.0f) goto L24;
        r29 = r29 + 360.0f;
    L24:
        Callback r0 = this.callback;
        if (r0 == null) goto L29;
        r0.onOffsetsChanged(r72, r54, r29);
        return;
    L29:
        return;
    L19:
        if (r26 >= (-1.0f)) goto L21;
        r53 = -2.0f;
        goto L17
    }

    public void setCallback(Callback r1) {
        this.callback = r1;
    }

    public void setEnabled(boolean r3) {
        if (this.enabled == r3) goto L13;
        this.enabled = r3;
        Sensor r0 = this.accelerometer;
        if (r0 == null) goto L14;
        SensorManager r1 = this.sensorManager;
        if (r3 == false) goto L11;
        r1.registerListener(this, r0, 1);
        return;
    L11:
        r1.unregisterListener(this);
        return;
    L14:
        return;
    }
}
