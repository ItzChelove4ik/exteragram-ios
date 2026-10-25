package org.telegram.ui.Components;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.PowerManager;
import com.exteragram.messenger.utils.system.SystemUtils;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.ui.PhotoViewer;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public class EarListener implements SensorEventListener {
    private Sensor accelerometerSensor;
    private boolean accelerometerVertical;
    private boolean attached;
    private final AudioManager audioManager;
    private final Context context;
    private int countLess;
    private VideoPlayer currentPlayer;
    private float[] gravity;
    private float[] gravityFast;
    private Sensor gravitySensor;
    private long lastAccelerometerDetected;
    private float lastProximityValue;
    private long lastTimestamp;
    private float[] linearAcceleration;
    private Sensor linearSensor;
    private final PowerManager powerManager;
    private float previousAccValue;
    private boolean proximityHasDifferentValues;
    private Sensor proximitySensor;
    private boolean proximityTouched;
    private PowerManager.WakeLock proximityWakeLock;
    private boolean raised;
    private int raisedToBack;
    private int raisedToTop;
    private int raisedToTopSign;
    private final SensorManager sensorManager;
    private long timeSinceRaise;

    public EarListener(Context r3) {
        this.lastTimestamp = 0;
        this.lastProximityValue = -100.0f;
        this.gravity = new float[3];
        this.gravityFast = new float[3];
        this.linearAcceleration = new float[3];
        this.context = r3;
        SensorManager r32 = (SensorManager) ApplicationLoader.applicationContext.getSystemService("sensor");
        this.sensorManager = r32;
        this.proximitySensor = r32.getDefaultSensor(8);
        this.linearSensor = r32.getDefaultSensor(10);
        Sensor r0 = r32.getDefaultSensor(9);
        this.gravitySensor = r0;
        if (this.linearSensor == null) goto L6;
        if (r0 == null) goto L6;
    L9:
        PowerManager r33 = (PowerManager) ApplicationLoader.applicationContext.getSystemService("power");
        this.powerManager = r33;
        this.proximityWakeLock = r33.newWakeLock(32, "telegram:proximity_lock2");
        this.audioManager = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("gravity or linear sensor not found");
    L8:
        this.accelerometerSensor = r32.getDefaultSensor(1);
        this.linearSensor = null;
        this.gravitySensor = null;
        goto L9
    }

    private boolean disableWakeLockWhenNotUsed() {
        return !Build.MANUFACTURER.equalsIgnoreCase("samsung");
    }

    private boolean isNearToSensor(float r2) {
        if (r2 < 5.0f) goto L5;
        return false;
    L5:
        if (r2 == this.proximitySensor.getMaximumRange()) goto L10;
        return true;
    L10:
        return false;
    }

    public void attach() {
        if (this.attached == true) goto L20;
        Sensor r0 = this.gravitySensor;
        if (r0 == null) goto L7;
        this.sensorManager.registerListener(this, r0, 30000);
    L7:
        Sensor r02 = this.linearSensor;
        if (r02 == null) goto L10;
        this.sensorManager.registerListener(this, r02, 30000);
    L10:
        Sensor r03 = this.accelerometerSensor;
        if (r03 == null) goto L13;
        this.sensorManager.registerListener(this, r03, 30000);
    L13:
        this.sensorManager.registerListener(this, this.proximitySensor, 3);
        if (this.proximityWakeLock != null) goto L16;
    L18:
        this.attached = true;
        return;
    L16:
        if (disableWakeLockWhenNotUsed() == true) goto L18;
        this.proximityWakeLock.acquire();
        goto L18
    }

    public void attachPlayer(VideoPlayer r1) {
        this.currentPlayer = r1;
        updateRaised();
    }

    public void detach() {
        if (this.attached == false) goto L20;
        Sensor r0 = this.gravitySensor;
        if (r0 == null) goto L7;
        this.sensorManager.unregisterListener(this, r0);
    L7:
        Sensor r02 = this.linearSensor;
        if (r02 == null) goto L10;
        this.sensorManager.unregisterListener(this, r02);
    L10:
        Sensor r03 = this.accelerometerSensor;
        if (r03 == null) goto L13;
        this.sensorManager.unregisterListener(this, r03);
    L13:
        this.sensorManager.unregisterListener(this, this.proximitySensor);
        PowerManager.WakeLock r04 = this.proximityWakeLock;
        if (r04 != null) goto L16;
    L18:
        this.attached = false;
        return;
    L16:
        if (r04.isHeld() == false) goto L18;
        this.proximityWakeLock.release();
        goto L18
    }

    public boolean forbidRaiseToListen() {
        return SystemUtils.hasExternalAudioOutput(this.audioManager);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor r1, int r2) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v49, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v50 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v40 */
    /* JADX WARN: Type inference failed for: r4v41 */
    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent r20) {
        if (this.attached == true) goto L5;
        return;
    L5:
        if (VoIPService.getSharedInstance() == null) goto L8;
        return;
    L8:
        if (r20.sensor.getType() == 8) goto L10;
        Sensor r2 = r20.sensor;
        if (r2 == this.accelerometerSensor) goto L22;
        long r16 = 0;
        int r18 = 1;
        int r15 = 2;
        if (r2 != this.linearSensor) goto L29;
        float[] r22 = this.linearAcceleration;
        float[] r3 = r20.values;
        r22[0] = r3[0];
        r22[1] = r3[1];
        r22[2] = r3[2];
        int r182 = r18;
    L32:
        Sensor r1 = r20.sensor;
        if (r1 != this.linearSensor) goto L35;
    L38:
        float[] r12 = this.gravity;
        float r23 = r12[0];
        float[] r4 = this.linearAcceleration;
        float r24 = ((r23 * r4[0]) + (r12[r182] * r4[r182])) + (r12[r15] * r4[r15]);
        int r13 = this.raisedToBack;
        if (r13 != 6) goto L41;
    L100:
        this.previousAccValue = r24;
        float[] r14 = this.gravityFast;
        if (r14[r182] > 2.5f) goto L103;
    L107:
        ?? r17 = false;
    L108:
        this.accelerometerVertical = r17;
    L110:
        if (this.raisedToBack != 6) goto L112;
    L113:
        this.lastAccelerometerDetected = System.currentTimeMillis();
    L115:
        if (this.raisedToBack == 6) goto L121;
        if (this.accelerometerVertical == true) goto L121;
        if ((System.currentTimeMillis() - this.lastAccelerometerDetected) < 60) goto L121;
    L127:
        ?? r19 = false;
    L129:
        if (this.proximityWakeLock != null) goto L131;
    L145:
        boolean r25 = this.proximityTouched;
        if (r25 == false) goto L152;
        if (r19 == false) goto L152;
        if (this.raised == true) goto L151;
        this.raised = r182;
        updateRaised();
    L151:
        this.raisedToBack = 0;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
    L170:
        if (this.timeSinceRaise != r16) goto L172;
        return;
    L172:
        if (this.raisedToBack == 6) goto L174;
        return;
    L174:
        if (Math.abs(System.currentTimeMillis() - this.timeSinceRaise) <= 1000) goto L180;
        this.raisedToBack = 0;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
        this.timeSinceRaise = r16;
        return;
    L180:
        return;
    L152:
        if (r25 == false) goto L165;
        if (this.accelerometerSensor == null) goto L158;
        if (this.linearSensor != null) goto L165;
    L158:
        if (this.gravitySensor != null) goto L165;
        if (VoIPService.isAnyKindOfCallActive() == true) goto L165;
        if (this.raised == true) goto L170;
        this.raised = true;
        updateRaised();
    L165:
        if (this.proximityTouched == true) goto L170;
        if (this.raised == false) goto L170;
        this.raised = false;
        updateRaised();
        goto L170
    L131:
        if (disableWakeLockWhenNotUsed() == false) goto L145;
        boolean r26 = this.proximityWakeLock.isHeld();
        if (r26 == false) goto L139;
        if (r19 == true) goto L139;
        if (BuildVars.LOGS_ENABLED == false) goto L138;
        FileLog.d("wake lock releasing");
    L138:
        this.proximityWakeLock.release();
    L139:
        if (r26 == true) goto L145;
        if (r19 == false) goto L145;
        if (BuildVars.LOGS_ENABLED == false) goto L144;
        FileLog.d("wake lock acquiring");
    L144:
        this.proximityWakeLock.acquire();
    L121:
        if (forbidRaiseToListen() == true) goto L127;
        if (VoIPService.isAnyKindOfCallActive() == true) goto L127;
        if (PhotoViewer.getInstance().isVisible() == true) goto L127;
        r19 = r182;
        goto L129
    L112:
        if (this.accelerometerVertical == false) goto L115;
    L103:
        if (Math.abs(r14[r15]) >= 4.0f) goto L107;
        if (Math.abs(this.gravityFast[0]) <= 1.5f) goto L107;
        r17 = r182;
        goto L108
    L41:
        if (r24 <= 0.0f) goto L45;
        if (this.previousAccValue <= 0.0f) goto L45;
    L48:
        if (r24 <= 0.0f) goto L55;
        if (r24 <= 15.0f) goto L52;
        ?? r42 = r182;
    L53:
        int r5 = r182;
    L59:
        int r6 = this.raisedToTopSign;
        if (r6 == 0) goto L80;
        if (r6 == r5) goto L80;
        int r52 = this.raisedToTop;
        if (r52 != 6) goto L73;
        if (r42 == false) goto L73;
        if (r13 >= 6) goto L100;
        int r110 = r13 + 1;
        this.raisedToBack = r110;
        if (r110 != 6) goto L100;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
        this.timeSinceRaise = System.currentTimeMillis();
        if (BuildVars.LOGS_ENABLED == false) goto L100;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L100;
        FileLog.d("motion detected");
    L73:
        if (r42 == true) goto L76;
        this.countLess++;
    L76:
        if (this.countLess == 10) goto L79;
        if (r52 != 6) goto L79;
        if (r13 == 0) goto L100;
    L79:
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.raisedToBack = 0;
        this.countLess = 0;
    L80:
        if (r42 == false) goto L91;
        if (r13 != 0) goto L91;
        if (r6 == 0) goto L84;
        if (r6 != r5) goto L91;
    L84:
        int r111 = this.raisedToTop;
        if (r111 >= 6) goto L100;
        if (this.proximityTouched == true) goto L100;
        this.raisedToTopSign = r5;
        int r112 = r111 + 1;
        this.raisedToTop = r112;
        if (r112 != 6) goto L100;
        this.countLess = 0;
    L91:
        if (r42 == true) goto L93;
        this.countLess++;
    L93:
        if (r6 == r5) goto L95;
    L99:
        this.raisedToBack = 0;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
        goto L100
    L95:
        if (this.countLess == 10) goto L99;
        if (this.raisedToTop != 6) goto L99;
        if (r13 == 0) goto L100;
    L52:
        r42 = false;
        goto L53
    L55:
        if (r24 >= (-15.0f)) goto L57;
        r42 = r182;
    L58:
        r5 = r15;
        goto L59
    L57:
        r42 = false;
    L45:
        if (r24 >= 0.0f) goto L100;
        if (this.previousAccValue >= 0.0f) goto L100;
    L35:
        if (r1 == this.gravitySensor) goto L38;
        if (r1 != this.accelerometerSensor) goto L110;
    L29:
        r182 = r18;
        if (r2 != this.gravitySensor) goto L32;
        float[] r27 = this.gravityFast;
        float[] r32 = this.gravity;
        float[] r43 = r20.values;
        float r53 = r43[0];
        r32[0] = r53;
        r27[0] = r53;
        float r54 = r43[1];
        r32[1] = r54;
        r27[1] = r54;
        float r44 = r43[2];
        r32[2] = r44;
        r27[2] = r44;
        r182 = r18;
        goto L32
    L22:
        if (this.lastTimestamp != 0) goto L24;
        double r28 = 0.9800000190734863d;
    L25:
        this.lastTimestamp = r20.timestamp;
        float[] r9 = this.gravity;
        double r10 = 1.0d - r28;
        float[] r142 = r20.values;
        r16 = 0;
        float r45 = (float) ((r9[0] * r28) + (r142[0] * r10));
        r9[0] = r45;
        r182 = 1;
        r15 = 2;
        float r55 = (float) ((r9[1] * r28) + (r142[1] * r10));
        r9[1] = r55;
        float r29 = (float) ((r28 * r9[2]) + (r10 * r142[2]));
        r9[2] = r29;
        float[] r33 = this.gravityFast;
        r33[0] = (r45 * 0.8f) + (r142[0] * 0.19999999f);
        r33[1] = (r55 * 0.8f) + (r142[1] * 0.19999999f);
        r33[2] = (r29 * 0.8f) + (r142[2] * 0.19999999f);
        float[] r210 = this.linearAcceleration;
        r210[0] = r142[0] - r9[0];
        r210[1] = r142[1] - r9[1];
        r210[2] = r142[2] - r9[2];
        goto L32
    L24:
        r28 = 1.0d / (((r20.timestamp - r2) / 1.0E9d) + 1.0d);
        goto L25
    L10:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("proximity changed to " + r20.values[0] + " max value = " + r20.sensor.getMaximumRange());
    L12:
        float r211 = this.lastProximityValue;
        float r34 = r20.values[0];
        if (r211 == r34) goto L15;
        this.proximityHasDifferentValues = true;
    L15:
        this.lastProximityValue = r34;
        if (this.proximityHasDifferentValues == false) goto L18;
        this.proximityTouched = isNearToSensor(r34);
    L18:
        r16 = 0;
        r182 = 1;
        r15 = 2;
        goto L32
    }

    public void updateRaised() {
        VideoPlayer r0 = this.currentPlayer;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.raised == false) goto L8;
        int r1 = 0;
    L9:
        r0.setStreamType(r1);
        return;
    L8:
        r1 = 3;
        goto L9
    }
}
