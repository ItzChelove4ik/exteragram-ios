package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.TextureView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraView;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class QRScanner {
    private Bitmap cacheBitmap;
    private CameraView cameraView;
    private final AtomicReference<BarcodeDetector> detector;
    private Detected lastDetected;
    private final Utilities.Callback<Detected> listener;
    private final AtomicBoolean paused;
    private final String prefix;
    private final Runnable process;

    public static final class Detected {
        public final float cx;
        public final float cy;
        public final String link;
        public final PointF[] points;

        private Detected(String r5, PointF[] r6) {
            this.link = r5;
            this.points = r6;
            float r52 = 0.0f;
            if (r6 == null) goto L9;
            int r1 = 0;
            float r0 = 0.0f;
        L6:
            if (r1 >= r6.length) goto L8;
            PointF r2 = r6[r1];
            r52 = r52 + r2.x;
            r0 = r0 + r2.y;
            r1 = r1 + 1;
            goto L6
        L8:
            r52 = r52 / r6.length;
            float r62 = r0 / r6.length;
        L10:
            this.cx = r52;
            this.cy = r62;
            return;
        L9:
            r62 = 0.0f;
            goto L10
        }

        public /* synthetic */ Detected(String r1, PointF[] r2, QRScannerIA r3) {
            this(r1, r2);
        }

        public boolean equals(Detected r7) {
            if (r7 != null) goto L6;
            return false;
        L6:
            if (TextUtils.equals(this.link, r7.link) == true) goto L8;
            return false;
        L8:
            PointF[] r1 = this.points;
            PointF[] r2 = r7.points;
            if (r1 != r2) goto L11;
            return true;
        L11:
            if (r1 == null) goto L13;
            boolean r4 = true;
        L14:
            if (r2 == null) goto L16;
            boolean r5 = true;
        L17:
            if (r4 == r5) goto L19;
            return false;
        L19:
            if (r1 == null) goto L36;
            if (r2 == null) goto L36;
            if (r1.length == r2.length) goto L25;
            return false;
        L25:
            int r12 = 0;
        L26:
            PointF[] r22 = this.points;
            if (r12 >= r22.length) goto L35;
            if (Math.abs(r22[r12].x - r7.points[r12].x) > 0.001f) goto L34;
            if (Math.abs(this.points[r12].y - r7.points[r12].y) > 0.001f) goto L34;
            r12 = r12 + 1;
        L34:
            return false;
        L35:
            return true;
        L36:
            return false;
        L16:
            r5 = false;
            goto L17
        L13:
            r4 = false;
            goto L14
        }
    }

    public static class QrRegionDrawer {
        private final AnimatedFloat[] animatedQPX;
        private final AnimatedFloat[] animatedQPY;
        private final AnimatedFloat animatedQr;
        private final AnimatedFloat animatedQrCX;
        private final AnimatedFloat animatedQrCY;
        private boolean hasQrResult;
        private final Runnable invalidate;
        private final Paint qrPaint;
        private final Path qrPath;
        private Detected qrResult;

        public QrRegionDrawer(Runnable r11) {
            Paint r0 = new Paint(1);
            this.qrPaint = r0;
            r0.setStyle(Paint.Style.STROKE);
            r0.setColor(-8697);
            r0.setStrokeWidth(AndroidUtilities.dp(6.0f));
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeCap(Paint.Cap.ROUND);
            r0.setShadowLayer(1.0804527E9f, 0.0f, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(6.0f));
            this.qrPath = new Path();
            this.invalidate = r11;
            CubicBezierInterpolator r9 = CubicBezierInterpolator.EASE_OUT;
            this.animatedQr = new AnimatedFloat(0.0f, r11, 0, 320, r9);
            this.animatedQrCX = new AnimatedFloat(0.0f, r11, 0, 160, r9);
            this.animatedQrCY = new AnimatedFloat(0.0f, r11, 0, 160, r9);
            this.animatedQPX = new AnimatedFloat[]{new AnimatedFloat(0.0f, r11, 0, 160, r9), new AnimatedFloat(0.0f, r11, 0, 160, r9), new AnimatedFloat(0.0f, r11, 0, 160, r9), new AnimatedFloat(0.0f, r11, 0, 160, r9)};
            this.animatedQPY = new AnimatedFloat[]{new AnimatedFloat(0.0f, r11, 0, 160, r9), new AnimatedFloat(0.0f, r11, 0, 160, r9), new AnimatedFloat(0.0f, r11, 0, 160, r9), new AnimatedFloat(0.0f, r11, 0, 160, r9)};
        }

        public void draw(Canvas r19, RectF r20) {
            Detected r3 = this.qrResult;
            if (r3 != null) goto L5;
            return;
        L5:
            if (r3.points.length <= 0) goto L26;
            float r32 = this.animatedQr.set(this.hasQrResult);
            float r4 = this.animatedQrCX.set(this.qrResult.cx);
            float r5 = r20.left + (r20.width() * r4);
            float r6 = this.animatedQrCY.set(this.qrResult.cy);
            float r7 = r20.top + (r20.height() * r6);
            float r8 = AndroidUtilities.lerp(0.5f, 1.1f, r32);
            r19.save();
            r19.scale(r8, r8, r5, r7);
            if (r32 <= 0.0f) goto L20;
            this.qrPath.rewind();
            int r52 = Math.min(4, this.qrResult.points.length);
            int r82 = 0;
        L10:
            if (r82 >= r52) goto L19;
            int r9 = r82 - 1;
            if (r9 >= 0) goto L14;
            r9 = r52 - 1;
        L14:
            int r10 = r82 + 1;
            if (r10 < r52) goto L17;
            int r11 = 0;
        L18:
            Detected r12 = this.qrResult;
            PointF[] r13 = r12.points;
            PointF r14 = r13[r9];
            PointF r15 = r13[r82];
            PointF r132 = r13[r11];
            float r16 = r32;
            float r17 = r4;
            float r72 = r20.left + ((this.animatedQPX[r9].set(r14.x - r12.cx) + r17) * r20.width());
            float r33 = r20.top + ((this.animatedQPY[r9].set(r14.y - this.qrResult.cy) + r6) * r20.height());
            float r42 = r20.left + ((this.animatedQPX[r82].set(r15.x - this.qrResult.cx) + r17) * r20.width());
            float r92 = r20.top + ((this.animatedQPY[r82].set(r15.y - this.qrResult.cy) + r6) * r20.height());
            float r83 = r20.left + ((this.animatedQPX[r11].set(r132.x - this.qrResult.cx) + r17) * r20.width());
            float r122 = (r20.top + ((this.animatedQPY[r11].set(r132.y - this.qrResult.cy) + r6) * r20.height())) - r92;
            this.qrPath.moveTo(((r72 - r42) * 0.18f) + r42, ((r33 - r92) * 0.18f) + r92);
            this.qrPath.lineTo(r42, r92);
            this.qrPath.lineTo(r42 + ((r83 - r42) * 0.18f), r92 + (r122 * 0.18f));
            r82 = r10;
            r32 = r16;
            r4 = r17;
            goto L10
        L17:
            r11 = r10;
            goto L18
        L19:
            this.qrPaint.setAlpha((int) (255.0f * r32));
            r19.drawPath(this.qrPath, this.qrPaint);
        L20:
            r19.restore();
            return;
        }

        public boolean hasNoDraw() {
            if (this.hasQrResult == false) goto L5;
            return false;
        L5:
            if (this.animatedQr.get() > 0.0f) goto L10;
            return true;
        L10:
            return false;
        }

        public void setQrDetected(Detected r7) {
            if (r7 == null) goto L4;
            this.qrResult = r7;
        L4:
            boolean r0 = false;
            if (r7 != null) goto L7;
        L12:
            if (r7 == null) goto L14;
            r0 = true;
        L14:
            this.hasQrResult = r0;
            this.invalidate.run();
            return;
        L7:
            if (this.hasQrResult == true) goto L12;
            this.animatedQrCX.set(r7.cx, true);
            this.animatedQrCY.set(r7.cy, true);
            int r2 = 0;
        L10:
            if (r2 >= Math.min(4, r7.points.length)) goto L12;
            this.animatedQPX[r2].set(r7.points[r2].x - r7.cx, true);
            this.animatedQPY[r2].set(r7.points[r2].y - r7.cy, true);
            r2 = r2 + 1;
            goto L10
        }
    }

    /* renamed from: $r8$lambda$6nLRI9LNqddkBid0H-j8Z4o88qs, reason: not valid java name */
    public static /* synthetic */ void m20174$r8$lambda$6nLRI9LNqddkBid0Hj8Z4o88qs(QRScanner r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$JBva-qj4nehq_4FGfX1GcuJq74I, reason: not valid java name */
    public static /* synthetic */ void m20175$r8$lambda$JBvaqj4nehq_4FGfX1GcuJq74I(QRScanner r0) {
        r0.lambda$setPaused$1();
    }

    public static /* synthetic */ void $r8$lambda$_IyJiayA5MaVmcHkE_VMyBtF0D0(QRScanner r0, Detected r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$t8KlCvS150d2ML0dD328fL8druw(QRScanner r0) {
        r0.lambda$new$3();
    }

    public QRScanner(Context r2, Utilities.Callback<Detected> r3) {
        this.detector = new AtomicReference();
        this.paused = new AtomicBoolean(false);
        this.process = new QRScanner$$ExternalSyntheticLambda1(this);
        this.listener = r3;
        this.prefix = MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix;
        Utilities.globalQueue.postRunnable(new QRScanner$$ExternalSyntheticLambda2(this));
    }

    private Detected detect(Bitmap r10) {
        if (r10 != null) goto L5;
        return null;
    L5:
        BarcodeDetector r1 = this.detector.get();
        if (r1 != null) goto L8;
    L29:
        return null;
    L8:
        if (r1.isOperational() == false) goto L29;
        int r2 = r10.getWidth();
        int r3 = r10.getHeight();
        SparseArray<Barcode> r102 = r1.detect(new Frame.Builder().setBitmap(r10).build());
        int r12 = 0;
        int r4 = 0;
    L12:
        if (r4 >= r102.size()) goto L29;
        Barcode r5 = r102.valueAt(r4);
        String r6 = r5.rawValue;
        if (r6 == null) goto L22;
        String r62 = r6.trim();
        if (r62.startsWith(this.prefix) == true) goto L23;
        if (r62.startsWith("https://" + this.prefix) == true) goto L23;
        if (r62.startsWith("http://" + this.prefix) == false) goto L22;
    L23:
        PointF[] r9 = new PointF[r5.cornerPoints.length];
    L25:
        if (r12 >= r5.cornerPoints.length) goto L28;
        Point r42 = r5.cornerPoints[r12];
        r9[r12] = new PointF(r42.x / r2, r42.y / r3);
        r12 = r12 + 1;
        goto L25
    L28:
        return new Detected(r62, r9, null);
    L22:
        r4 = r4 + 1;
        goto L12
    }

    private /* synthetic */ void lambda$new$0() {
        this.detector.set(new BarcodeDetector.Builder(ApplicationLoader.applicationContext).setBarcodeFormats(256).build());
        attach(this.cameraView);
    }

    private /* synthetic */ void lambda$new$2(Detected r1) {
        this.listener.run(r1);
    }

    private /* synthetic */ void lambda$new$3() {
        if (this.detector.get() != null) goto L5;
        return;
    L5:
        if (this.cameraView != null) goto L7;
        return;
    L7:
        if (this.paused.get() == true) goto L42;
        TextureView r0 = this.cameraView.getTextureView();
        if (r0 == null) goto L36;
        int r1 = r0.getWidth();
        int r2 = r0.getHeight();
        if (r1 > 720) goto L14;
        if (r2 > 720) goto L14;
    L15:
        boolean r3 = true;
        int r12 = Math.max(1, r1);
        int r22 = Math.max(1, r2);
        Bitmap r4 = this.cacheBitmap;
        if (r4 != null) goto L18;
    L21:
        this.cacheBitmap = Bitmap.createBitmap(r12, r22, Bitmap.Config.ARGB_8888);
    L22:
        r0.getBitmap(this.cacheBitmap);
        Detected r02 = detect(this.cacheBitmap);
        Detected r13 = this.lastDetected;
        if (r13 == null) goto L25;
        boolean r42 = true;
    L26:
        if (r02 != null) goto L29;
        r3 = false;
    L29:
        if (r42 != r3) goto L34;
        if (r02 == null) goto L36;
        if (r13 == null) goto L36;
        if (r02.equals(r13) == true) goto L36;
    L34:
        this.lastDetected = r02;
        AndroidUtilities.runOnUIThread(new QRScanner$$ExternalSyntheticLambda3(this, r02));
        goto L36
    L25:
        r42 = false;
        goto L26
    L18:
        if (r12 != r4.getWidth()) goto L21;
        if (r22 == this.cacheBitmap.getHeight()) goto L22;
    L14:
        float r14 = r1;
        float r23 = r2;
        float r32 = Math.min(720.0f / r14, 720.0f / r23);
        r1 = (int) (r14 * r32);
        r2 = (int) (r23 * r32);
    L36:
        if (this.paused.get() == true) goto L41;
        Utilities.globalQueue.cancelRunnable(this.process);
        Utilities.globalQueue.postRunnable(this.process, getTimeout());
        return;
    L41:
        return;
    }

    private /* synthetic */ void lambda$setPaused$1() {
        this.listener.run(null);
    }

    public void attach(CameraView r4) {
        this.cameraView = r4;
        if (this.detector.get() != null) goto L6;
        return;
    L6:
        if (this.paused.get() == true) goto L9;
        Utilities.globalQueue.cancelRunnable(this.process);
        Utilities.globalQueue.postRunnable(this.process, getTimeout());
        return;
    }

    public void destroy() {
        this.cameraView = null;
        Utilities.globalQueue.cancelRunnable(this.process);
        Utilities.globalQueue.postRunnable(new QRScanner$$ExternalSyntheticLambda0(this));
    }

    public void detach() {
        BarcodeDetector r1 = this.detector.getAndSet(null);
        if (r1 == null) goto L6;
        r1.release();
        return;
    }

    public Detected getDetected() {
        return this.lastDetected;
    }

    public long getTimeout() {
        if (this.lastDetected != null) goto L6;
        return 750;
    L6:
        int r2 = SharedConfig.getDevicePerformanceClass();
        if (r2 != 1) goto L9;
        return 400;
    L9:
        if (r2 == 2) goto L12;
        return 800;
    L12:
        return 80;
    }

    public void setPaused(boolean r4) {
        if (this.paused.getAndSet(r4) == r4) goto L13;
        if (r4 == false) goto L10;
        Utilities.globalQueue.cancelRunnable(this.process);
        if (this.lastDetected == null) goto L12;
        this.lastDetected = null;
        AndroidUtilities.runOnUIThread(new QRScanner$$ExternalSyntheticLambda4(this));
        return;
    L12:
        return;
    L10:
        Utilities.globalQueue.cancelRunnable(this.process);
        Utilities.globalQueue.postRunnable(this.process, getTimeout());
        return;
    }
}
