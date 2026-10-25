package org.telegram.ui.bots;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes7.dex */
public class BotSensors {
    private long absoluteOrientationDesiredRefreshRate;
    private final SensorEventListener absoluteOrientationListener;
    private Runnable absoluteOrientationListenerPostponed;
    private Sensor accelerometer;
    private long accelerometerDesiredRefreshRate;
    private final SensorEventListener accelerometerListener;
    private Runnable accelerometerListenerPostponed;
    private Sensor gyroscope;
    private long gyroscopeDesiredRefreshRate;
    private final SensorEventListener gyroscopeListener;
    private Runnable gyroscopeListenerPostponed;
    private Sensor orientationAccelerometer;
    private Sensor orientationMagnetometer;
    private boolean paused;
    private long relativeOrientationDesiredRefreshRate;
    private final SensorEventListener relativeOrientationListener;
    private Runnable relativeOrientationListenerPostponed;
    private Sensor rotation;
    private final SensorManager sensorManager;
    private BotWebViewContainer.MyWebView webView;

    /* renamed from: org.telegram.ui.bots.BotSensors$1, reason: invalid class name */
    public class AnonymousClass1 implements SensorEventListener {
        private long lastTime;
        final /* synthetic */ BotSensors this$0;
        private float[] xyz;

        public AnonymousClass1(BotSensors r1) {
            this.this$0 = r1;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor r1, int r2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent r5) {
            if (BotSensors.m21328$$Nest$fgetaccelerometerListenerPostponed(this.this$0) == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(BotSensors.m21328$$Nest$fgetaccelerometerListenerPostponed(this.this$0));
            BotSensors.m21336$$Nest$fputaccelerometerListenerPostponed(this.this$0, null);
        L6:
            if (BotSensors.m21331$$Nest$fgetpaused(this.this$0) == false) goto L8;
            return;
        L8:
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) == null) goto L17;
            long r0 = System.currentTimeMillis() - this.lastTime;
            this.xyz = r5.values;
            if (r0 >= BotSensors.m21327$$Nest$fgetaccelerometerDesiredRefreshRate(this.this$0)) goto L14;
            BotSensors r52 = this.this$0;
            BotSensors$1$$ExternalSyntheticLambda0 r2 = new BotSensors$1$$ExternalSyntheticLambda0(this);
            BotSensors.m21336$$Nest$fputaccelerometerListenerPostponed(r52, r2);
            AndroidUtilities.runOnUIThread(r2, BotSensors.m21327$$Nest$fgetaccelerometerDesiredRefreshRate(this.this$0) - r0);
            return;
        L14:
            post();
            return;
        }

        public void post() {
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) != null) goto L6;
            return;
        L6:
            if (this.xyz == null) goto L15;
            this.lastTime = System.currentTimeMillis();
            JSONObject r1 = new JSONObject();     // Catch: Exception -> L11
            r1.put("x", -this.xyz[0]);     // Catch: Exception -> L11
            r1.put("y", -this.xyz[1]);     // Catch: Exception -> L11
            r1.put("z", -this.xyz[2]);     // Catch: Exception -> L11
            BotSensors.m21334$$Nest$fgetwebView(this.this$0).evaluateJS("window.Telegram.WebView.receiveEvent('accelerometer_changed', " + r1 + ");");     // Catch: Exception -> L11
            return;
        L16:
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotSensors$2, reason: invalid class name */
    public class AnonymousClass2 implements SensorEventListener {
        private float[] captured;
        private long lastTime;
        final /* synthetic */ BotSensors this$0;

        public AnonymousClass2(BotSensors r1) {
            this.this$0 = r1;
            this.captured = new float[3];
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor r1, int r2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent r5) {
            if (BotSensors.m21330$$Nest$fgetgyroscopeListenerPostponed(this.this$0) == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(BotSensors.m21330$$Nest$fgetgyroscopeListenerPostponed(this.this$0));
            BotSensors.m21337$$Nest$fputgyroscopeListenerPostponed(this.this$0, null);
        L6:
            if (BotSensors.m21331$$Nest$fgetpaused(this.this$0) == false) goto L8;
            return;
        L8:
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) == null) goto L17;
            float[] r0 = this.captured;
            float r2 = r0[0];
            float[] r52 = r5.values;
            r0[0] = r2 + r52[0];
            r0[1] = r0[1] + r52[1];
            r0[2] = r0[2] + r52[2];
            long r02 = System.currentTimeMillis() - this.lastTime;
            if (r02 >= BotSensors.m21329$$Nest$fgetgyroscopeDesiredRefreshRate(this.this$0)) goto L14;
            BotSensors r53 = this.this$0;
            BotSensors$2$$ExternalSyntheticLambda0 r22 = new BotSensors$2$$ExternalSyntheticLambda0(this);
            BotSensors.m21337$$Nest$fputgyroscopeListenerPostponed(r53, r22);
            AndroidUtilities.runOnUIThread(r22, BotSensors.m21329$$Nest$fgetgyroscopeDesiredRefreshRate(this.this$0) - r02);
            return;
        L14:
            post();
            return;
        }

        public void post() {
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) != null) goto L5;
            return;
        L5:
            this.lastTime = System.currentTimeMillis();
            float[] r1 = this.captured;
            JSONObject r5 = new JSONObject();     // Catch: Exception -> L9
            r5.put("x", r1[0]);     // Catch: Exception -> L9
            r5.put("y", r1[1]);     // Catch: Exception -> L9
            r5.put("z", r1[2]);     // Catch: Exception -> L9
            BotSensors.m21334$$Nest$fgetwebView(this.this$0).evaluateJS("window.Telegram.WebView.receiveEvent('gyroscope_changed', " + r5 + ");");     // Catch: Exception -> L9
        L7:
            float[] r9 = this.captured;
            r9[0] = 0.0f;
            r9[1] = 0.0f;
            r9[2] = 0.0f;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotSensors$3, reason: invalid class name */
    public class AnonymousClass3 implements SensorEventListener {
        private float[] geomagnetic;
        private float[] gravity;
        private long lastTime;
        final /* synthetic */ BotSensors this$0;

        public AnonymousClass3(BotSensors r1) {
            this.this$0 = r1;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor r1, int r2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent r5) {
            if (BotSensors.m21326$$Nest$fgetabsoluteOrientationListenerPostponed(this.this$0) == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(BotSensors.m21326$$Nest$fgetabsoluteOrientationListenerPostponed(this.this$0));
            BotSensors.m21335$$Nest$fputabsoluteOrientationListenerPostponed(this.this$0, null);
        L6:
            if (BotSensors.m21331$$Nest$fgetpaused(this.this$0) == false) goto L8;
            return;
        L8:
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) == null) goto L23;
            long r0 = System.currentTimeMillis() - this.lastTime;
            if (r5.sensor.getType() != 1) goto L14;
            this.gravity = r5.values;
        L14:
            if (r5.sensor.getType() != 2) goto L17;
            this.geomagnetic = r5.values;
        L17:
            if (r0 >= BotSensors.m21325$$Nest$fgetabsoluteOrientationDesiredRefreshRate(this.this$0)) goto L20;
            BotSensors r52 = this.this$0;
            BotSensors$3$$ExternalSyntheticLambda0 r2 = new BotSensors$3$$ExternalSyntheticLambda0(this);
            BotSensors.m21335$$Nest$fputabsoluteOrientationListenerPostponed(r52, r2);
            AndroidUtilities.runOnUIThread(r2, BotSensors.m21325$$Nest$fgetabsoluteOrientationDesiredRefreshRate(this.this$0) - r0);
            return;
        L20:
            post();
            return;
        }

        public void post() {
            if (this.gravity != null) goto L5;
            return;
        L5:
            if (this.geomagnetic != null) goto L8;
            return;
        L8:
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) == null) goto L21;
            this.lastTime = System.currentTimeMillis();
            float[] r2 = new float[9];
            if (SensorManager.getRotationMatrix(r2, new float[9], this.gravity, this.geomagnetic) == false) goto L19;
            SensorManager.getOrientation(r2, new float[3]);
            JSONObject r22 = new JSONObject();     // Catch: Exception -> L15
            r22.put("absolute", true);     // Catch: Exception -> L15
            r22.put("alpha", -r1[0]);     // Catch: Exception -> L15
            r22.put("beta", -r1[1]);     // Catch: Exception -> L15
            r22.put("gamma", r1[2]);     // Catch: Exception -> L15
            BotSensors.m21334$$Nest$fgetwebView(this.this$0).evaluateJS("window.Telegram.WebView.receiveEvent('device_orientation_changed', " + r22 + ");");     // Catch: Exception -> L15
            return;
        L22:
            return;
        L19:
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotSensors$4, reason: invalid class name */
    public class AnonymousClass4 implements SensorEventListener {
        private long lastTime;
        private float[] mDeviceRotationMatrix;
        private float[] mTruncatedRotationVector;
        final /* synthetic */ BotSensors this$0;
        private float[] values;

        public AnonymousClass4(BotSensors r1) {
            this.this$0 = r1;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor r1, int r2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent r5) {
            if (BotSensors.m21333$$Nest$fgetrelativeOrientationListenerPostponed(this.this$0) == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(BotSensors.m21333$$Nest$fgetrelativeOrientationListenerPostponed(this.this$0));
            BotSensors.m21338$$Nest$fputrelativeOrientationListenerPostponed(this.this$0, null);
        L6:
            if (BotSensors.m21331$$Nest$fgetpaused(this.this$0) == false) goto L8;
            return;
        L8:
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) == null) goto L20;
            long r0 = System.currentTimeMillis() - this.lastTime;
            if (r0 >= BotSensors.m21332$$Nest$fgetrelativeOrientationDesiredRefreshRate(this.this$0)) goto L15;
            BotSensors r52 = this.this$0;
            BotSensors$4$$ExternalSyntheticLambda0 r2 = new BotSensors$4$$ExternalSyntheticLambda0(this);
            BotSensors.m21338$$Nest$fputrelativeOrientationListenerPostponed(r52, r2);
            AndroidUtilities.runOnUIThread(r2, BotSensors.m21332$$Nest$fgetrelativeOrientationDesiredRefreshRate(this.this$0) - r0);
            return;
        L15:
            if (r5.sensor.getType() != 15) goto L17;
            this.values = r5.values;
        L17:
            post();
            return;
        }

        public void post() {
            if (this.values != null) goto L6;
            return;
        L6:
            if (BotSensors.m21334$$Nest$fgetwebView(this.this$0) == null) goto L25;
            this.lastTime = System.currentTimeMillis();
            if (this.mDeviceRotationMatrix != null) goto L12;
            this.mDeviceRotationMatrix = new float[9];
        L12:
            if (this.mTruncatedRotationVector != null) goto L14;
            this.mTruncatedRotationVector = new float[4];
        L14:
            float[] r1 = this.values;
            if (r1.length <= 4) goto L17;
            System.arraycopy(r1, 0, this.mTruncatedRotationVector, 0, 4);
            SensorManager.getRotationMatrixFromVector(this.mDeviceRotationMatrix, this.mTruncatedRotationVector);
        L18:
            SensorManager.getOrientation(this.mDeviceRotationMatrix, new float[3]);
            JSONObject r2 = new JSONObject();     // Catch: Exception -> L21
            r2.put("absolute", false);     // Catch: Exception -> L21
            r2.put("alpha", -r1[0]);     // Catch: Exception -> L21
            r2.put("beta", -r1[1]);     // Catch: Exception -> L21
            r2.put("gamma", r1[2]);     // Catch: Exception -> L21
            BotSensors.m21334$$Nest$fgetwebView(this.this$0).evaluateJS("window.Telegram.WebView.receiveEvent('device_orientation_changed', " + r2 + ");");     // Catch: Exception -> L21
            return;
        L26:
            return;
        L17:
            SensorManager.getRotationMatrixFromVector(this.mDeviceRotationMatrix, r1);
            goto L18
        }
    }

    /* renamed from: -$$Nest$fgetabsoluteOrientationDesiredRefreshRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21325$$Nest$fgetabsoluteOrientationDesiredRefreshRate(BotSensors r2) {
        return r2.absoluteOrientationDesiredRefreshRate;
    }

    /* renamed from: -$$Nest$fgetabsoluteOrientationListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21326$$Nest$fgetabsoluteOrientationListenerPostponed(BotSensors r0) {
        return r0.absoluteOrientationListenerPostponed;
    }

    /* renamed from: -$$Nest$fgetaccelerometerDesiredRefreshRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21327$$Nest$fgetaccelerometerDesiredRefreshRate(BotSensors r2) {
        return r2.accelerometerDesiredRefreshRate;
    }

    /* renamed from: -$$Nest$fgetaccelerometerListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21328$$Nest$fgetaccelerometerListenerPostponed(BotSensors r0) {
        return r0.accelerometerListenerPostponed;
    }

    /* renamed from: -$$Nest$fgetgyroscopeDesiredRefreshRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21329$$Nest$fgetgyroscopeDesiredRefreshRate(BotSensors r2) {
        return r2.gyroscopeDesiredRefreshRate;
    }

    /* renamed from: -$$Nest$fgetgyroscopeListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21330$$Nest$fgetgyroscopeListenerPostponed(BotSensors r0) {
        return r0.gyroscopeListenerPostponed;
    }

    /* renamed from: -$$Nest$fgetpaused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21331$$Nest$fgetpaused(BotSensors r0) {
        return r0.paused;
    }

    /* renamed from: -$$Nest$fgetrelativeOrientationDesiredRefreshRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21332$$Nest$fgetrelativeOrientationDesiredRefreshRate(BotSensors r2) {
        return r2.relativeOrientationDesiredRefreshRate;
    }

    /* renamed from: -$$Nest$fgetrelativeOrientationListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21333$$Nest$fgetrelativeOrientationListenerPostponed(BotSensors r0) {
        return r0.relativeOrientationListenerPostponed;
    }

    /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BotWebViewContainer.MyWebView m21334$$Nest$fgetwebView(BotSensors r0) {
        return r0.webView;
    }

    /* renamed from: -$$Nest$fputabsoluteOrientationListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21335$$Nest$fputabsoluteOrientationListenerPostponed(BotSensors r0, Runnable r1) {
        r0.absoluteOrientationListenerPostponed = r1;
    }

    /* renamed from: -$$Nest$fputaccelerometerListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21336$$Nest$fputaccelerometerListenerPostponed(BotSensors r0, Runnable r1) {
        r0.accelerometerListenerPostponed = r1;
    }

    /* renamed from: -$$Nest$fputgyroscopeListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21337$$Nest$fputgyroscopeListenerPostponed(BotSensors r0, Runnable r1) {
        r0.gyroscopeListenerPostponed = r1;
    }

    /* renamed from: -$$Nest$fputrelativeOrientationListenerPostponed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21338$$Nest$fputrelativeOrientationListenerPostponed(BotSensors r0, Runnable r1) {
        r0.relativeOrientationListenerPostponed = r1;
    }

    public BotSensors(Context r1, long r2) {
        this.accelerometerListener = new AnonymousClass1(this);
        this.gyroscopeListener = new AnonymousClass2(this);
        this.absoluteOrientationListener = new AnonymousClass3(this);
        this.relativeOrientationListener = new AnonymousClass4(this);
        this.sensorManager = (SensorManager) r1.getSystemService("sensor");
    }

    private static int getSensorDelay(long r2) {
        if (r2 < 160) goto L7;
        return 3;
    L7:
        if (r2 < 60) goto L10;
        return 2;
    L10:
        return 1;
    }

    public void attachWebView(BotWebViewContainer.MyWebView r1) {
        this.webView = r1;
    }

    public void detachWebView(BotWebViewContainer.MyWebView r2) {
        if (this.webView != r2) goto L6;
        this.webView = null;
        pause();
        return;
    }

    public void pause() {
        if (this.paused == true) goto L37;
        this.paused = true;
        SensorManager r0 = this.sensorManager;
        if (r0 == null) goto L35;
        Sensor r1 = this.accelerometer;
        if (r1 == null) goto L10;
        r0.unregisterListener(this.accelerometerListener, r1);
    L10:
        Runnable r02 = this.accelerometerListenerPostponed;
        if (r02 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.accelerometerListenerPostponed = null;
    L13:
        Sensor r03 = this.gyroscope;
        if (r03 == null) goto L16;
        this.sensorManager.unregisterListener(this.gyroscopeListener, r03);
    L16:
        Runnable r04 = this.gyroscopeListenerPostponed;
        if (r04 == null) goto L19;
        AndroidUtilities.cancelRunOnUIThread(r04);
        this.gyroscopeListenerPostponed = null;
    L19:
        Sensor r05 = this.orientationAccelerometer;
        if (r05 == null) goto L22;
        this.sensorManager.unregisterListener(this.absoluteOrientationListener, r05);
    L22:
        Sensor r06 = this.orientationMagnetometer;
        if (r06 == null) goto L25;
        this.sensorManager.unregisterListener(this.absoluteOrientationListener, r06);
    L25:
        Runnable r07 = this.absoluteOrientationListenerPostponed;
        if (r07 == null) goto L28;
        AndroidUtilities.cancelRunOnUIThread(r07);
        this.absoluteOrientationListenerPostponed = null;
    L28:
        Sensor r08 = this.rotation;
        if (r08 == null) goto L31;
        this.sensorManager.unregisterListener(this.relativeOrientationListener, r08);
    L31:
        Runnable r09 = this.relativeOrientationListenerPostponed;
        if (r09 == null) goto L36;
        AndroidUtilities.cancelRunOnUIThread(r09);
        this.relativeOrientationListenerPostponed = null;
        return;
    L36:
        return;
    L35:
        return;
    }

    public void resume() {
        if (this.paused == false) goto L25;
        this.paused = false;
        SensorManager r0 = this.sensorManager;
        if (r0 == null) goto L23;
        Sensor r1 = this.accelerometer;
        if (r1 == null) goto L10;
        r0.registerListener(this.accelerometerListener, r1, getSensorDelay(this.accelerometerDesiredRefreshRate));
    L10:
        Sensor r02 = this.gyroscope;
        if (r02 == null) goto L13;
        this.sensorManager.registerListener(this.gyroscopeListener, r02, getSensorDelay(this.gyroscopeDesiredRefreshRate));
    L13:
        Sensor r03 = this.orientationAccelerometer;
        if (r03 == null) goto L16;
        this.sensorManager.registerListener(this.absoluteOrientationListener, r03, getSensorDelay(this.absoluteOrientationDesiredRefreshRate));
    L16:
        Sensor r04 = this.orientationMagnetometer;
        if (r04 == null) goto L19;
        this.sensorManager.registerListener(this.absoluteOrientationListener, r04, getSensorDelay(this.absoluteOrientationDesiredRefreshRate));
    L19:
        Sensor r05 = this.rotation;
        if (r05 == null) goto L24;
        this.sensorManager.registerListener(this.relativeOrientationListener, r05, getSensorDelay(this.relativeOrientationDesiredRefreshRate));
        return;
    L24:
        return;
    L23:
        return;
    }

    public boolean startAccelerometer(long r5) {
        SensorManager r0 = this.sensorManager;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (this.accelerometer == null) goto L8;
        return true;
    L8:
        Sensor r02 = r0.getDefaultSensor(1);
        this.accelerometer = r02;
        if (r02 != null) goto L11;
        return false;
    L11:
        this.accelerometerDesiredRefreshRate = r5;
        if (this.paused == true) goto L14;
        this.sensorManager.registerListener(this.accelerometerListener, r02, getSensorDelay(r5));
    L14:
        return true;
    }

    public boolean startGyroscope(long r5) {
        SensorManager r0 = this.sensorManager;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (this.gyroscope == null) goto L8;
        return true;
    L8:
        Sensor r02 = r0.getDefaultSensor(4);
        this.gyroscope = r02;
        if (r02 != null) goto L11;
        return false;
    L11:
        this.gyroscopeDesiredRefreshRate = r5;
        if (this.paused == true) goto L14;
        this.sensorManager.registerListener(this.gyroscopeListener, r02, getSensorDelay(r5));
    L14:
        return true;
    }

    public boolean startOrientation(boolean r6, long r7) {
        if (this.sensorManager != null) goto L6;
        return false;
    L6:
        if (r6 == false) goto L32;
        if (this.rotation == null) goto L19;
        Runnable r62 = this.relativeOrientationListenerPostponed;
        if (r62 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(r62);
        this.relativeOrientationListenerPostponed = null;
    L13:
        if (this.paused == true) goto L17;
        Sensor r63 = this.rotation;
        if (r63 == null) goto L17;
        this.sensorManager.unregisterListener(this.relativeOrientationListener, r63);
    L17:
        this.rotation = null;
    L19:
        if (this.orientationMagnetometer != null) goto L21;
    L23:
        this.orientationAccelerometer = this.sensorManager.getDefaultSensor(1);
        Sensor r64 = this.sensorManager.getDefaultSensor(2);
        this.orientationMagnetometer = r64;
        Sensor r2 = this.orientationAccelerometer;
        if (r2 == null) goto L30;
        if (r64 == null) goto L30;
        this.absoluteOrientationDesiredRefreshRate = r7;
        if (this.paused == true) goto L56;
        this.sensorManager.registerListener(this.absoluteOrientationListener, r2, getSensorDelay(r7));
        this.sensorManager.registerListener(this.absoluteOrientationListener, this.orientationMagnetometer, getSensorDelay(r7));
    L56:
        return true;
    L30:
        return false;
    L21:
        if (this.orientationAccelerometer == null) goto L23;
        return true;
    L32:
        if (this.orientationMagnetometer == null) goto L34;
    L35:
        Runnable r65 = this.absoluteOrientationListenerPostponed;
        if (r65 == null) goto L39;
        AndroidUtilities.cancelRunOnUIThread(r65);
        this.absoluteOrientationListenerPostponed = null;
    L39:
        if (this.paused == true) goto L46;
        Sensor r66 = this.orientationAccelerometer;
        if (r66 == null) goto L43;
        this.sensorManager.unregisterListener(this.absoluteOrientationListener, r66);
    L43:
        Sensor r67 = this.orientationMagnetometer;
        if (r67 == null) goto L46;
        this.sensorManager.unregisterListener(this.absoluteOrientationListener, r67);
    L46:
        this.orientationAccelerometer = null;
        this.orientationMagnetometer = null;
    L48:
        if (this.rotation == null) goto L50;
        return true;
    L50:
        Sensor r68 = this.sensorManager.getDefaultSensor(15);
        this.rotation = r68;
        if (r68 != null) goto L53;
        return false;
    L53:
        this.relativeOrientationDesiredRefreshRate = r7;
        if (this.paused == true) goto L56;
        this.sensorManager.registerListener(this.relativeOrientationListener, r68, getSensorDelay(r7));
        goto L56
    L34:
        if (this.orientationAccelerometer == null) goto L48;
        goto L35
    }

    public boolean stopAccelerometer() {
        SensorManager r0 = this.sensorManager;
        if (r0 != null) goto L6;
        return false;
    L6:
        Sensor r1 = this.accelerometer;
        if (r1 != null) goto L10;
        return true;
    L10:
        if (this.paused == true) goto L12;
        r0.unregisterListener(this.accelerometerListener, r1);
    L12:
        Runnable r02 = this.accelerometerListenerPostponed;
        if (r02 == null) goto L15;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.accelerometerListenerPostponed = null;
    L15:
        this.accelerometer = null;
        return true;
    }

    public void stopAll() {
        stopOrientation();
        stopGyroscope();
        stopAccelerometer();
    }

    public boolean stopGyroscope() {
        SensorManager r0 = this.sensorManager;
        if (r0 != null) goto L6;
        return false;
    L6:
        Sensor r1 = this.gyroscope;
        if (r1 != null) goto L10;
        return true;
    L10:
        if (this.paused == true) goto L12;
        r0.unregisterListener(this.gyroscopeListener, r1);
    L12:
        Runnable r02 = this.gyroscopeListenerPostponed;
        if (r02 == null) goto L15;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.gyroscopeListenerPostponed = null;
    L15:
        this.gyroscope = null;
        return true;
    }

    public boolean stopOrientation() {
        SensorManager r0 = this.sensorManager;
        if (r0 != null) goto L6;
        return false;
    L6:
        Sensor r1 = this.orientationAccelerometer;
        if (r1 != null) goto L14;
        if (this.orientationMagnetometer != null) goto L14;
        if (this.rotation != null) goto L14;
        return true;
    L14:
        if (this.paused == true) goto L23;
        if (r1 == null) goto L17;
        r0.unregisterListener(this.absoluteOrientationListener, r1);
    L17:
        Sensor r02 = this.orientationMagnetometer;
        if (r02 == null) goto L20;
        this.sensorManager.unregisterListener(this.absoluteOrientationListener, r02);
    L20:
        Sensor r03 = this.rotation;
        if (r03 == null) goto L23;
        this.sensorManager.unregisterListener(this.relativeOrientationListener, r03);
    L23:
        Runnable r04 = this.absoluteOrientationListenerPostponed;
        if (r04 == null) goto L26;
        AndroidUtilities.cancelRunOnUIThread(r04);
        this.absoluteOrientationListenerPostponed = null;
    L26:
        Runnable r05 = this.relativeOrientationListenerPostponed;
        if (r05 == null) goto L29;
        AndroidUtilities.cancelRunOnUIThread(r05);
        this.relativeOrientationListenerPostponed = null;
    L29:
        this.orientationAccelerometer = null;
        this.orientationMagnetometer = null;
        this.rotation = null;
        return true;
    }
}
