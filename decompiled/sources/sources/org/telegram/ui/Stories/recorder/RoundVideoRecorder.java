package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.io.File;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.camera.CameraView;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Paint.Views.RoundView;

/* loaded from: classes7.dex */
public abstract class RoundVideoRecorder extends FrameLayout {
    public final long MAX_DURATION;
    private float alpha;
    public final CameraView cameraView;
    private ValueAnimator cameraViewAnimator;
    private boolean cancelled;
    private ValueAnimator destroyAnimator;
    private float destroyT;
    public final File file;
    private Runnable onDestroyCallback;
    private Utilities.Callback3<File, String, Long> onDoneCallback;
    private final Paint progressPaint;
    private long recordingStarted;
    private long recordingStopped;
    private RoundView roundView;
    private final Paint shadowPaint;
    private final Runnable stopRunnable;

    /* renamed from: org.telegram.ui.Stories.recorder.RoundVideoRecorder$1, reason: invalid class name */
    public class AnonymousClass1 extends CameraView {
        private final Path circlePath;
        final /* synthetic */ RoundVideoRecorder this$0;

        public AnonymousClass1(RoundVideoRecorder r1, Context r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.circlePath = new Path();
        }

        @Override // org.telegram.messenger.camera.CameraView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            r7.save();
            this.circlePath.rewind();
            this.circlePath.addCircle(getWidth() / 2.0f, getHeight() / 2.0f, Math.min(getWidth() / 2.0f, getHeight() / 2.0f), Path.Direction.CW);
            r7.clipPath(this.circlePath);
            super.dispatchDraw(r7);
            r7.restore();
        }

        @Override // org.telegram.messenger.camera.CameraView
        public void receivedAmplitude(double r1) {
            this.this$0.receivedAmplitude(r1);
        }

        @Override // org.telegram.messenger.camera.CameraView
        public boolean square() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.RoundVideoRecorder$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ RoundVideoRecorder this$0;
        final /* synthetic */ RoundView val$roundView;

        public AnonymousClass2(RoundVideoRecorder r1, RoundView r2) {
            this.this$0 = r1;
            this.val$roundView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            RoundView r22 = this.val$roundView;
            if (r22 == null) goto L6;
            r22.setDraw(true);
        L6:
            if ((this.this$0.getParent() instanceof ViewGroup) == false) goto L9;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.RoundVideoRecorder$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ RoundVideoRecorder this$0;

        public AnonymousClass3(RoundVideoRecorder r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if ((this.this$0.getParent() instanceof ViewGroup) == false) goto L6;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            return;
        }
    }

    /* renamed from: $r8$lambda$0mbBDAVwhFO6IFm-M3Bkrw8SLLM, reason: not valid java name */
    public static /* synthetic */ void m20197$r8$lambda$0mbBDAVwhFO6IFmM3Bkrw8SLLM(RoundVideoRecorder r0, ValueAnimator r1) {
        r0.lambda$destroy$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$FaS3RzXPANLqvBxQZuuPOfZVUtI(RoundVideoRecorder r0) {
        r0.lambda$hideTo$3();
    }

    /* renamed from: $r8$lambda$UupXVzWlk_3pJEKWUPAT-Mx4lFw, reason: not valid java name */
    public static /* synthetic */ void m20198$r8$lambda$UupXVzWlk_3pJEKWUPATMx4lFw(RoundVideoRecorder r0, float r1, float r2, float r3, float r4, ValueAnimator r5) {
        r0.lambda$hideTo$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$bcXe6VzfMeFhR0QVvcdYyS2tyDU(RoundVideoRecorder r0, String r1, long r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$bs5gXD-YdkKjIL5CvQBwTsCD_6Q, reason: not valid java name */
    public static /* synthetic */ void m20199$r8$lambda$bs5gXDYdkKjIL5CvQBwTsCD_6Q(RoundVideoRecorder r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$i22P1rHLO8bwmFun1Qic_TB-O1A, reason: not valid java name */
    public static /* synthetic */ void m20200$r8$lambda$i22P1rHLO8bwmFun1Qic_TBO1A(RoundVideoRecorder r0, RoundView r1) {
        r0.lambda$hideTo$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$o8S9AoC2PSVu1MWp0Il1KhbLSRs(RoundVideoRecorder r0) {
        r0.lambda$new$2();
    }

    public RoundVideoRecorder(Context r5) {
        super(r5);
        this.recordingStarted = -1;
        this.recordingStopped = -1;
        this.MAX_DURATION = 59500;
        this.shadowPaint = new Paint(1);
        Paint r0 = new Paint(1);
        this.progressPaint = r0;
        this.stopRunnable = new RoundVideoRecorder$$ExternalSyntheticLambda0(this);
        this.alpha = 1.0f;
        this.cancelled = false;
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeCap(Paint.Cap.ROUND);
        r0.setStrokeJoin(Paint.Join.ROUND);
        this.file = StoryEntry.makeCacheFile(UserConfig.selectedAccount, true);
        AnonymousClass1 r02 = new AnonymousClass1(this, r5, true, false);
        this.cameraView = r02;
        r02.setScaleX(0.0f);
        r02.setScaleY(0.0f);
        addView(r02);
        r02.setDelegate(new RoundVideoRecorder$$ExternalSyntheticLambda1(this));
        r02.initTexture();
        setWillNotDraw(false);
    }

    private /* synthetic */ void lambda$destroy$6(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.destroyT = r32;
        this.cameraView.setScaleX(1.0f - r32);
        this.cameraView.setScaleY(1.0f - this.destroyT);
        invalidate();
    }

    private /* synthetic */ void lambda$hideTo$3() {
        if ((getParent() instanceof ViewGroup) == false) goto L6;
        ((ViewGroup) getParent()).removeView(this);
        return;
    }

    private /* synthetic */ void lambda$hideTo$4(float r3, float r4, float r5, float r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        this.cameraView.setScaleX(AndroidUtilities.lerp(r3, r4, r72));
        this.cameraView.setScaleY(AndroidUtilities.lerp(r3, r4, r72));
        this.cameraView.setTranslationX(r5 * r72);
        this.cameraView.setTranslationY(r6 * r72);
        float r42 = 1.0f - r72;
        this.cameraView.setAlpha(r42);
        this.alpha = r42;
        invalidate();
    }

    private /* synthetic */ void lambda$hideTo$5(RoundView r9) {
        if (r9.getWidth() > 0) goto L6;
        this.cameraView.animate().scaleX(0.0f).scaleY(1.0f).withEndAction(new RoundVideoRecorder$$ExternalSyntheticLambda6(this)).start();
        return;
    L6:
        float r5 = r9.getWidth() / this.cameraView.getWidth();
        ValueAnimator r0 = this.cameraViewAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        this.cameraViewAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.cameraViewAnimator.addUpdateListener(new RoundVideoRecorder$$ExternalSyntheticLambda7(this, this.cameraView.getScaleX(), r5, (r9.getX() + (r9.getWidth() / 2.0f)) - (this.cameraView.getX() + (this.cameraView.getWidth() / 2.0f)), (r9.getY() + (r9.getHeight() / 2.0f)) - (this.cameraView.getY() + (this.cameraView.getHeight() / 2.0f))));
        this.cameraViewAnimator.addListener(new AnonymousClass2(this, r9));
        this.cameraViewAnimator.setDuration(320);
        this.cameraViewAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.roundView = r9;
        this.cameraViewAnimator.start();
    }

    private /* synthetic */ void lambda$new$0(String r4, long r5) {
        this.recordingStopped = System.currentTimeMillis();
        AndroidUtilities.cancelRunOnUIThread(this.stopRunnable);
        if (this.cancelled == false) goto L6;
        return;
    L6:
        if (r5 <= 1000) goto L11;
        this.cameraView.destroy(true, null);
        Utilities.Callback3<File, String, Long> r0 = this.onDoneCallback;
        if (r0 == null) goto L13;
        r0.run(this.file, r4, Long.valueOf(r5));
        return;
    L13:
        return;
    L11:
        destroy(false);
    }

    private /* synthetic */ void lambda$new$1() {
        this.cameraView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(280).start();
        this.recordingStarted = System.currentTimeMillis();
        invalidate();
        performHapticFeedback(3);     // Catch: Exception -> L6
    L4:
        AndroidUtilities.runOnUIThread(this.stopRunnable, 59500);
    }

    private /* synthetic */ void lambda$new$2() {
        if (this.recordingStarted <= 0) goto L5;
        return;
    L5:
        CameraController.getInstance().recordVideo(this.cameraView.getCameraSessionObject(), this.file, false, new RoundVideoRecorder$$ExternalSyntheticLambda3(this), new RoundVideoRecorder$$ExternalSyntheticLambda4(this), this.cameraView, true);
    }

    public void cancel() {
        this.cancelled = true;
        AndroidUtilities.cancelRunOnUIThread(this.stopRunnable);
        CameraController.getInstance().stopVideoRecording(this.cameraView.getCameraSessionRecording(), false, false);
        destroy(false);
    }

    public void destroy(boolean r4) {
        Runnable r0 = this.onDestroyCallback;
        if (r0 == null) goto L5;
        r0.run();
        this.onDestroyCallback = null;
    L5:
        AndroidUtilities.cancelRunOnUIThread(this.stopRunnable);
        this.cameraView.destroy(true, null);
        this.file.delete();     // Catch: Exception -> L16
    L7:
        if (r4 == true) goto L9;
        ValueAnimator r42 = this.destroyAnimator;
        if (r42 == null) goto L14;
        r42.cancel();
    L14:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{this.destroyT, 1.0f});
        this.destroyAnimator = r43;
        r43.addUpdateListener(new RoundVideoRecorder$$ExternalSyntheticLambda2(this));
        this.destroyAnimator.addListener(new AnonymousClass3(this));
        this.destroyAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.destroyAnimator.setDuration(280);
        this.destroyAnimator.start();
        return;
    L9:
        if ((getParent() instanceof ViewGroup) == false) goto L19;
        ((ViewGroup) getParent()).removeView(this);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r12) {
        RectF r1 = AndroidUtilities.rectTmp;
        r1.set(this.cameraView.getX() + ((this.cameraView.getWidth() / 2.0f) * (1.0f - this.cameraView.getScaleX())), this.cameraView.getY() + ((this.cameraView.getHeight() / 2.0f) * (1.0f - this.cameraView.getScaleY())), (this.cameraView.getX() + this.cameraView.getWidth()) - ((this.cameraView.getWidth() / 2.0f) * (1.0f - this.cameraView.getScaleX())), (this.cameraView.getY() + this.cameraView.getHeight()) - ((this.cameraView.getHeight() / 2.0f) * (1.0f - this.cameraView.getScaleY())));
        this.shadowPaint.setShadowLayer(AndroidUtilities.dp(2.0f), 0.0f, AndroidUtilities.dp(0.66f), Theme.multAlpha(536870912, this.alpha));
        this.shadowPaint.setAlpha((int) (this.alpha * 255.0f));
        r12.drawCircle(r1.centerX(), r1.centerY(), Math.min(r1.width() / 2.0f, r1.height() / 2.0f) - 1.0f, this.shadowPaint);
        super.dispatchDraw(r12);
        RoundView r0 = this.roundView;
        if (r0 == null) goto L10;
        if (r0.getWidth() <= 0) goto L10;
        if (this.roundView.getHeight() <= 0) goto L10;
        r12.save();
        r12.translate(r1.left, r1.top);
        r12.scale(r1.width() / this.roundView.getWidth(), r1.height() / this.roundView.getHeight());
        float r02 = this.roundView.getAlpha();
        this.roundView.setDraw(true);
        this.roundView.setAlpha(1.0f - this.alpha);
        this.roundView.draw(r12);
        this.roundView.setAlpha(r02);
        this.roundView.setDraw(false);
        r12.restore();
    L10:
        if (this.recordingStarted <= 0) goto L15;
        float r03 = Utilities.clamp(sinceRecording() / 59500.0f, 1.0f, 0.0f);
        this.progressPaint.setStrokeWidth(AndroidUtilities.dp(3.33f));
        this.progressPaint.setColor(Theme.multAlpha(-1090519041, this.alpha));
        this.progressPaint.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(0.33f), Theme.multAlpha(536870912, this.alpha));
        r1.inset(-AndroidUtilities.dp(7.665f), -AndroidUtilities.dp(7.665f));
        r12.drawArc(r1, -90.0f, r03 * 360.0f, false, this.progressPaint);
        if (this.recordingStopped > 0) goto L16;
        invalidate();
        return;
    L16:
        return;
    }

    public void hideTo(RoundView r5) {
        if (r5 != null) goto L6;
        destroy(false);
        return;
    L6:
        AndroidUtilities.cancelRunOnUIThread(this.stopRunnable);
        this.cameraView.destroy(true, null);
        r5.setDraw(false);
        post(new RoundVideoRecorder$$ExternalSyntheticLambda5(this, r5));
    }

    public RoundVideoRecorder onDestroy(Runnable r1) {
        this.onDestroyCallback = r1;
        return this;
    }

    public RoundVideoRecorder onDone(Utilities.Callback3<File, String, Long> r1) {
        this.onDoneCallback = r1;
        return this;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        int r42 = ((r4 - r2) - this.cameraView.getMeasuredWidth()) - AndroidUtilities.dp(16.0f);
        int r12 = AndroidUtilities.dp(72.0f);
        CameraView r22 = this.cameraView;
        r22.layout(r42, r12, r22.getMeasuredWidth() + r42, this.cameraView.getMeasuredHeight() + r12);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r52 = View.MeasureSpec.getSize(r5);
        int r62 = View.MeasureSpec.getSize(r6);
        int r0 = (int) (Math.min(r52, r62) * 0.43f);
        this.cameraView.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30));
        setMeasuredDimension(r52, r62);
    }

    public abstract void receivedAmplitude(double r1);

    public long sinceRecording() {
        if (this.recordingStarted >= 0) goto L5;
        return 0;
    L5:
        long r0 = this.recordingStopped;
        if (r0 >= 0) goto L9;
        r0 = System.currentTimeMillis();
    L9:
        return Math.min(59500, r0 - this.recordingStarted);
    }

    public String sinceRecordingText() {
        long r0 = sinceRecording();
        int r4 = (int) (r0 / 1000);
        int r02 = (int) ((r0 - (r4 * 1000)) / 100);
        int r1 = r4 / 60;
        int r42 = r4 % 60;
        StringBuilder r2 = new StringBuilder();
        r2.append(r1);
        r2.append(":");
        if (r42 >= 10) goto L5;
        String r12 = MVEL.VERSION_SUB;
    L6:
        r2.append(r12);
        r2.append(r42);
        r2.append(".");
        r2.append(r02);
        return r2.toString();
    L5:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public void stop() {
        AndroidUtilities.cancelRunOnUIThread(this.stopRunnable);
        if (this.recordingStarted > 0) goto L6;
        destroy(true);
        return;
    L6:
        CameraController.getInstance().stopVideoRecording(this.cameraView.getCameraSessionRecording(), false, false);
    }
}
