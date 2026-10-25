package com.exteragram.messenger.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.hardware.Camera;
import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.camera.core.Camera;
import androidx.camera.core.ZoomState;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.exteragram.messenger.ExteraConfig;
import java.util.Arrays;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.Camera2Session;
import org.telegram.messenger.camera.CameraInfo;
import org.telegram.messenger.camera.CameraSession;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class InstantCameraZoomSlider extends CameraZoomSliderView {
    public static final AnimationProperties.FloatProperty<InstantCameraZoomSlider> OPEN_ALPHA = null;
    private boolean animateNextConfiguration;
    private ValueAnimator appearAnimator;
    private float appearProgress;
    private Backend backend;
    private float baseTranslationY;
    private int bindRetries;
    private final Runnable bindRunnable;
    private BlurredBackgroundDrawable blurBackground;
    private float blurCornerRadius;
    private float camera1LinearZoom;
    private CameraSession camera1Session;
    private int camera1ZoomIndex;
    private float[] camera1ZoomRatios;
    private Camera2Session camera2Session;
    private CameraXSession cameraXSession;
    private final Observer<ZoomState> cameraXZoomObserver;
    private LiveData<ZoomState> cameraXZoomState;
    private OnCameraZoomChangeListener cameraZoomChangeListener;
    private float defaultZoom;
    private float displayOneZoom;
    private float lastAppliedZoom;
    private long lastZoomAppliedAt;
    private float openAlpha;
    private float[] opticalZoomRatios;
    private float pendingZoom;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean switchingCamera;
    private int textureViewSize;
    private float wideZoom;
    private final Runnable zoomFlushRunnable;
    private boolean zoomFlushScheduled;

    /* renamed from: com.exteragram.messenger.camera.InstantCameraZoomSlider$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<InstantCameraZoomSlider> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Float get(InstantCameraZoomSlider r1) {
            return Float.valueOf(r1.getOpenAlpha());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((InstantCameraZoomSlider) r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(InstantCameraZoomSlider r1, float r2) {
            r1.setOpenAlpha(r2);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(InstantCameraZoomSlider r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: com.exteragram.messenger.camera.InstantCameraZoomSlider$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ InstantCameraZoomSlider this$0;

        public AnonymousClass2(InstantCameraZoomSlider r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (InstantCameraZoomSlider.m1165$$Nest$fgetappearAnimator(this.this$0) != r2) goto L6;
            InstantCameraZoomSlider.m1166$$Nest$fputappearAnimator(this.this$0, null);
            InstantCameraZoomSlider.m1167$$Nest$msetAppearProgress(this.this$0, 1.0f);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.camera.InstantCameraZoomSlider$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend = null;

        static {
            int[] r0 = new int[Backend.values().length];
            $SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend = r0;
            r0[Backend.CAMERA_X.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend[Backend.CAMERA_2.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend[Backend.CAMERA_1.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend[Backend.NONE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public enum Backend extends Enum<Backend> {
        private static final /* synthetic */ Backend[] $VALUES = null;
        public static final Backend CAMERA_1 = null;
        public static final Backend CAMERA_2 = null;
        public static final Backend CAMERA_X = null;
        public static final Backend NONE = null;

        private static /* synthetic */ Backend[] $values() {
            return new Backend[]{NONE, CAMERA_1, CAMERA_2, CAMERA_X};
        }

        static {
            NONE = new Backend("NONE", 0);
            CAMERA_1 = new Backend("CAMERA_1", 1);
            CAMERA_2 = new Backend("CAMERA_2", 2);
            CAMERA_X = new Backend("CAMERA_X", 3);
            $VALUES = $values();
        }

        Backend(String r1, int r2) {
        }

        public static Backend valueOf(String r1) {
            return (Backend) Enum.valueOf(Backend.class, r1);
        }

        public static Backend[] values() {
            return (Backend[]) $VALUES.clone();
        }
    }

    public interface OnCameraZoomChangeListener {
        void onCameraZoomChanged(float r1, boolean r2);
    }

    /* renamed from: $r8$lambda$fRYy7CUAwiHLPk8NP_XpzR1oS-o, reason: not valid java name */
    public static /* synthetic */ void m1163$r8$lambda$fRYy7CUAwiHLPk8NP_XpzR1oSo(InstantCameraZoomSlider r0) {
        r0.flushPendingZoom();
    }

    public static /* synthetic */ void $r8$lambda$gO3UQutF66xYIJcAIdCV1LtLzHs(InstantCameraZoomSlider r0, ZoomState r1) {
        r0.onCameraXZoomStateChanged(r1);
    }

    public static /* synthetic */ void $r8$lambda$rYGu6gRtjtI_vxnk1tPQF8O3O9M(InstantCameraZoomSlider r0, float r1) {
        r0.applyZoom(r1);
    }

    /* renamed from: $r8$lambda$tnIW3zKDQPaJ-_cHoV9IF18-79E, reason: not valid java name */
    public static /* synthetic */ void m1164$r8$lambda$tnIW3zKDQPaJ_cHoV9IF1879E(InstantCameraZoomSlider r0, ValueAnimator r1) {
        r0.lambda$showAnimated$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$wkhswiV0MLdAtVnh_zQoPXRxrWQ(InstantCameraZoomSlider r0) {
        r0.tryBind();
    }

    /* renamed from: -$$Nest$fgetappearAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m1165$$Nest$fgetappearAnimator(InstantCameraZoomSlider r0) {
        return r0.appearAnimator;
    }

    /* renamed from: -$$Nest$fputappearAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1166$$Nest$fputappearAnimator(InstantCameraZoomSlider r0, ValueAnimator r1) {
        r0.appearAnimator = r1;
    }

    /* renamed from: -$$Nest$msetAppearProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1167$$Nest$msetAppearProgress(InstantCameraZoomSlider r0, float r1) {
        r0.setAppearProgress(r1);
    }

    static {
        OPEN_ALPHA = new AnonymousClass1("openAlpha");
    }

    public InstantCameraZoomSlider(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.backend = Backend.NONE;
        this.camera1ZoomRatios = new float[0];
        this.opticalZoomRatios = new float[0];
        this.camera1ZoomIndex = -1;
        this.defaultZoom = 1.0f;
        this.wideZoom = 1.0f;
        this.displayOneZoom = 1.0f;
        this.pendingZoom = Float.NaN;
        this.lastAppliedZoom = Float.NaN;
        this.bindRunnable = new InstantCameraZoomSlider$$ExternalSyntheticLambda0(this);
        this.zoomFlushRunnable = new InstantCameraZoomSlider$$ExternalSyntheticLambda1(this);
        this.cameraXZoomObserver = new InstantCameraZoomSlider$$ExternalSyntheticLambda2(this);
        this.blurCornerRadius = -1.0f;
        this.resourcesProvider = r3;
        setVisibility(8);
        applyAppearProgress();
        setOnZoomChangeListener(new InstantCameraZoomSlider$$ExternalSyntheticLambda3(this));
        applyTelegramColors();
    }

    private void applyAppearProgress() {
        setAlpha(this.openAlpha * this.appearProgress);
        float r1 = (this.appearProgress * 0.100000024f) + 0.9f;
        setScaleX(r1);
        setScaleY(r1);
    }

    private void applyPosition() {
        setTranslationY(((this.baseTranslationY + (this.textureViewSize / 2.0f)) + AndroidUtilities.dp(80.0f)) - (getMeasuredHeight() / 2.0f));
    }

    private void applyTelegramColors() {
        int r3 = Theme.getColor(Theme.key_chat_messagePanelBackground, this.resourcesProvider);
        int r5 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        int r7 = Theme.getColor(Theme.key_chats_actionIcon, this.resourcesProvider);
        int r4 = Theme.getColor(Theme.key_chat_messagePanelText, this.resourcesProvider);
        setColors(r3, r4, r5, r5, r7);
        setToggleTextColor(r4);
    }

    private void applyZoom(float r1) {
        this.pendingZoom = r1;
        scheduleZoomFlush();
    }

    private void attachCameraXZoomObserver() {
        CameraXSession r0 = this.cameraXSession;
        if (r0 == null) goto L12;
        Camera r02 = r0.camera;
        if (r02 == null) goto L13;
        LiveData<ZoomState> r03 = r02.getCameraInfo().getZoomState();
        if (this.cameraXZoomState == r03) goto L14;
        detachCameraXZoomObserver();
        this.cameraXZoomState = r03;
        r03.observeForever(this.cameraXZoomObserver);
        return;
    L14:
        return;
    L13:
        return;
    }

    private float camera1LinearZoomForIndex(int r2) {
        int r1 = this.camera1ZoomRatios.length - 1;
        if (r2 > 0) goto L6;
        return 0.0f;
    L6:
        if (r2 < r1) goto L10;
        return 1.0f;
    L10:
        return (r2 + 0.001f) / r1;
    }

    private float camera1RatioForLinearZoom(float r3) {
        int r0 = this.camera1ZoomRatios.length - 1;
        return this.camera1ZoomRatios[Math.min((int) (Utilities.clamp01(r3) * r0), r0)];
    }

    private int camera1ZoomIndexForRatio(float r6) {
        int r0 = 0;
        float r2 = Float.MAX_VALUE;
        int r1 = 0;
    L3:
        float[] r3 = this.camera1ZoomRatios;
        if (r0 >= r3.length) goto L9;
        float r32 = Math.abs(r3[r0] - r6);
        if (r32 >= r2) goto L8;
        r1 = r0;
        r2 = r32;
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        return r1;
    }

    private void cancelAppearAnimation() {
        ValueAnimator r0 = this.appearAnimator;
        if (r0 == null) goto L6;
        this.appearAnimator = null;
        r0.cancel();
        return;
    }

    private void detachCameraXZoomObserver() {
        LiveData<ZoomState> r0 = this.cameraXZoomState;
        if (r0 == null) goto L6;
        r0.removeObserver(this.cameraXZoomObserver);
        this.cameraXZoomState = null;
        return;
    }

    private void discardPendingZoom() {
        removeCallbacks(this.zoomFlushRunnable);
        this.zoomFlushScheduled = false;
        this.pendingZoom = Float.NaN;
    }

    private void flushPendingZoom() {
        this.zoomFlushScheduled = false;
        float r0 = this.pendingZoom;
        if (Float.isNaN(r0) == false) goto L5;
        return;
    L5:
        if (this.backend == Backend.NONE) goto L19;
        long r1 = SystemClock.uptimeMillis();
        if (Float.isNaN(this.lastAppliedZoom) == false) goto L10;
    L16:
        this.lastAppliedZoom = r0;
        this.lastZoomAppliedAt = r1;
        sendZoomToCamera(r0);
        return;
    L10:
        if (r0 != this.lastAppliedZoom) goto L13;
        return;
    L13:
        if ((r1 - this.lastZoomAppliedAt) >= getZoomUpdateIntervalMs()) goto L16;
        scheduleZoomFlush();
        return;
    }

    private long getZoomUpdateIntervalMs() {
        Backend r0 = this.backend;
        if (r0 != Backend.CAMERA_X) goto L8;
        CameraXSession r1 = this.cameraXSession;
        if (r1 == null) goto L8;
        int r4 = r1.getRecordingFrameRate();
    L13:
        if (r4 > 0) goto L16;
        r4 = 30;
    L16:
        return Math.max(1, 1000 / r4);
    L8:
        if (r0 != Backend.CAMERA_2) goto L12;
        Camera2Session r42 = this.camera2Session;
        if (r42 == null) goto L12;
        r4 = r42.getRecordingFrameRate();
    L12:
        r4 = 0;
        goto L13
    }

    private void hideImmediately() {
        cancelAppearAnimation();
        setAppearProgress(0.0f);
        setVisibility(8);
    }

    private /* synthetic */ void lambda$showAnimated$0(ValueAnimator r1) {
        setAppearProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    private void onCameraXZoomStateChanged(ZoomState r3) {
        if (this.backend != Backend.CAMERA_X) goto L10;
        if (r3 == null) goto L12;
        OnCameraZoomChangeListener r2 = this.cameraZoomChangeListener;
        if (r2 == null) goto L11;
        r2.onCameraZoomChanged(r3.getLinearZoom(), false);
        return;
    L11:
        return;
    L12:
        return;
    }

    private static float[] readCamera1ZoomRatios(CameraSession r7) {
        CameraInfo r72 = r7.cameraInfo;     // Catch: Exception -> L31
        if (r72 == null) goto L6;
        android.hardware.Camera r73 = r72.getCamera();     // Catch: Exception -> L31
    L7:
        if (r73 != null) goto L9;
        return null;
    L9:
        Camera.Parameters r74 = r73.getParameters();     // Catch: Exception -> L31
        int r1 = 0;
        if (r74 == null) goto L30;
        if (r74.isZoomSupported() == false) goto L30;
        List<Integer> r75 = r74.getZoomRatios();     // Catch: Exception -> L31
        if (r75 == null) goto L28;
        if (r75.size() < 2) goto L28;
        int r2 = r75.size();     // Catch: Exception -> L31
        float[] r3 = new float[r2];     // Catch: Exception -> L31
    L20:
        if (r1 >= r2) goto L26;
        float r5 = 1.0f;
        if (r75.get(r1) == null) goto L25;
        r5 = Math.max(1.0f, r4.intValue() / 100.0f);     // Catch: Exception -> L31
    L25:
        r3[r1] = r5;     // Catch: Exception -> L31
        r1 = r1 + 1;     // Catch: Exception -> L31
        goto L20
    L26:
        return r3;
    L28:
        return new float[0];
    L30:
        return new float[0];
    L6:
        r73 = null;
    L31:
        return null;
    }

    private void resetBinding(boolean r3) {
        setExternalZoomGestureActive(false);
        resetZoomThrottle();
        this.backend = Backend.NONE;
        detachCameraXZoomObserver();
        setZoom(getZoom());
        this.cameraXSession = null;
        this.camera2Session = null;
        this.camera1Session = null;
        this.camera1ZoomRatios = new float[0];
        this.opticalZoomRatios = new float[0];
        this.camera1ZoomIndex = -1;
        this.defaultZoom = 1.0f;
        this.wideZoom = 1.0f;
        this.displayOneZoom = 1.0f;
        this.bindRetries = 0;
        removeCallbacks(this.bindRunnable);
        this.animateNextConfiguration = false;
        if (r3 == false) goto L6;
        cancelZoomConfigurationTransition();
        setEnabled(true);
        setExpanded(false, false);
        hideImmediately();
        return;
    }

    private void resetZoomThrottle() {
        discardPendingZoom();
        this.lastAppliedZoom = Float.NaN;
        this.lastZoomAppliedAt = 0;
    }

    private void retryBinding() {
        if (this.backend == Backend.NONE) goto L8;
        int r0 = this.bindRetries;
        this.bindRetries = r0 + 1;
        if (r0 >= 25) goto L8;
        postDelayed(this.bindRunnable, 100);
        return;
    L8:
        this.animateNextConfiguration = false;
        cancelZoomConfigurationTransition();
        setEnabled(true);
        hideImmediately();
    }

    private void scheduleZoomFlush() {
        if (this.zoomFlushScheduled == true) goto L6;
        this.zoomFlushScheduled = true;
        postOnAnimation(this.zoomFlushRunnable);
        return;
    }

    private void sendZoomToCamera(float r5) {
        int r0 = AnonymousClass3.$SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend[this.backend.ordinal()];
        if (r0 != 1) goto L5;
        CameraXSession r02 = this.cameraXSession;
        if (r02 == null) goto L35;
        r02.setZoomRatio(r5);
        r5 = this.cameraXSession.getLinearZoom();
    L26:
        OnCameraZoomChangeListener r4 = this.cameraZoomChangeListener;
        if (r4 == null) goto L31;
        r4.onCameraZoomChanged(r5, true);
        return;
    L31:
        return;
    L35:
        return;
    L5:
        if (r0 != 2) goto L7;
        Camera2Session r03 = this.camera2Session;
        if (r03 == null) goto L34;
        r03.setZoom(r5);
        goto L26
    L34:
        return;
    L7:
        if (r0 == 3) goto L10;
        return;
    L10:
        if (this.camera1Session != null) goto L12;
        return;
    L12:
        if (this.camera1ZoomRatios.length < 2) goto L33;
        int r52 = camera1ZoomIndexForRatio(r5);
        float r04 = camera1LinearZoomForIndex(r52);
        this.camera1LinearZoom = r04;
        if (r52 == this.camera1ZoomIndex) goto L17;
        this.camera1ZoomIndex = r52;
        this.camera1Session.setZoom(r04);
    L17:
        r5 = this.camera1LinearZoom;
        goto L26
    }

    private void setAppearProgress(float r2) {
        if (this.appearProgress == r2) goto L6;
        this.appearProgress = r2;
        applyAppearProgress();
        return;
    }

    private void showAnimated() {
        setEnabled(true);
        if (ExteraConfig.getZoomSlider() == true) goto L7;
        hideImmediately();
        return;
    L7:
        if (getVisibility() == 0) goto L10;
        setVisibility(0);
    L10:
        if (this.appearAnimator != null) goto L16;
        float r1 = this.appearProgress;
        if (r1 >= 1.0f) goto L17;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{r1, 1.0f});
        this.appearAnimator = r0;
        r0.addUpdateListener(new InstantCameraZoomSlider$$ExternalSyntheticLambda4(this));
        r0.addListener(new AnonymousClass2(this));
        r0.setDuration(180);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r0.start();
        return;
    L17:
        return;
    }

    private void tryBind() {
        int r0 = AnonymousClass3.$SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend[this.backend.ordinal()];
        if (r0 != 1) goto L5;
        CameraXSession r02 = this.cameraXSession;
        if (r02 == null) goto L78;
        if (r02.isReady() == false) goto L78;
        ZoomState r03 = this.cameraXSession.camera.getCameraInfo().getZoomState().getValue();
        if (r03 != null) goto L47;
        retryBinding();
        return;
    L47:
        float r4 = r03.getMinZoomRatio();
        float r04 = r03.getMaxZoomRatio();
        boolean r5 = this.cameraXSession.isActiveCameraFrontface();
        float r8 = r04;
    L27:
        float r7 = r4;
        if (Float.isFinite(r7) == false) goto L76;
        if (Float.isFinite(r8) == false) goto L76;
        if (r7 <= 0.0f) goto L76;
        if (r8 <= r7) goto L76;
        this.defaultZoom = Utilities.clamp(1.0f, r8, r7);
        this.wideZoom = r7;
        this.displayOneZoom = 1.0f;
        setDisplayNormalizationFactor(1.0f);
        Backend r05 = this.backend;
        Backend r1 = Backend.CAMERA_X;
        if (r05 != r1) goto L60;
        float[] r06 = CameraLensStops.opticalZoomRatios(getContext(), this.cameraXSession.getActiveCameraId(), r7);
    L61:
        this.opticalZoomRatios = r06;
        float[] r9 = CameraLensStops.buildToggleStops(r5, r7, r8, r06);
        float[] r10 = CameraLensStops.buildRulerStops(r7, r8, r9);
        Backend r07 = this.backend;
        if (r07 != r1) goto L65;
        float r08 = getCameraXResetZoom();
    L68:
        float r11 = Utilities.clamp(r08, r8, r7);
        boolean r12 = this.animateNextConfiguration;
        this.animateNextConfiguration = false;
        setZoomConfiguration(r7, r8, r9, r10, r11, r12);
        if (r12 == true) goto L72;
        setExpanded(false, false);
    L72:
        if (this.backend != r1) goto L74;
        attachCameraXZoomObserver();
        this.cameraXSession.setZoomRatio(r11);
    L74:
        showAnimated();
        return;
    L65:
        if (r07 != Backend.CAMERA_2) goto L67;
        r08 = this.camera2Session.getZoom();
        goto L68
    L67:
        r08 = camera1RatioForLinearZoom(this.camera1LinearZoom);
        goto L68
    L60:
        r06 = new float[0];
    L76:
        this.animateNextConfiguration = false;
        cancelZoomConfigurationTransition();
        setEnabled(true);
        hideImmediately();
        return;
    L78:
        retryBinding();
        return;
    L5:
        if (r0 != 2) goto L7;
        Camera2Session r09 = this.camera2Session;
        if (r09 != null) goto L33;
    L36:
        retryBinding();
        return;
    L33:
        if (r09.isInitiated() == false) goto L36;
        r4 = this.camera2Session.getMinZoom();
        float r010 = this.camera2Session.getMaxZoom();
    L26:
        r8 = r010;
        r5 = false;
        goto L27
    L7:
        if (r0 == 3) goto L9;
        return;
    L9:
        CameraSession r011 = this.camera1Session;
        if (r011 != null) goto L12;
    L28:
        retryBinding();
        return;
    L12:
        if (r011.isInitied() == false) goto L28;
        float[] r012 = readCamera1ZoomRatios(this.camera1Session);
        this.camera1ZoomRatios = r012;
        if (r012 != null) goto L19;
        this.camera1ZoomRatios = new float[0];
        retryBinding();
        return;
    L19:
        if (r012.length != 0) goto L21;
        r4 = 1.0f;
    L23:
        if (r012.length != 0) goto L25;
        r010 = 1.0f;
        goto L26
    L25:
        r010 = r012[r012.length - 1];
        goto L26
    L21:
        r4 = r012[0];
        goto L23
    }

    public void beginCameraSwitch() {
        if (this.backend != Backend.NONE) goto L5;
        return;
    L5:
        if (getVisibility() == 0) goto L7;
        return;
    L7:
        if (isLaidOut() == false) goto L13;
        this.switchingCamera = true;
        prepareZoomConfigurationTransition();
        setEnabled(false);
        setExpanded(false, true);
        return;
    }

    public void beginExternalZoomGesture() {
        if (this.backend == Backend.CAMERA_X) goto L5;
        return;
    L5:
        discardPendingZoom();
        setZoom(getZoom());
    }

    public void beginPinchZoomGesture() {
        beginExternalZoomGesture();
        setExternalZoomGestureActive(true);
    }

    public void beginSteppedZoomGesture() {
        beginExternalZoomGesture();
        setExpanded(true, true);
    }

    public void bindSession(CameraXSession r5) {
        if (r5 != null) goto L5;
    L15:
        boolean r2 = false;
    L16:
        this.switchingCamera = false;
        if (r2 == false) goto L19;
        prepareZoomConfigurationTransition();
    L19:
        resetBinding(!r2);
        if (r5 != null) goto L22;
        return;
    L22:
        this.backend = Backend.CAMERA_X;
        this.cameraXSession = r5;
        this.animateNextConfiguration = r2;
        if (r2 == false) goto L25;
        setEnabled(false);
        setExpanded(false, true);
    L25:
        tryBind();
        return;
    L5:
        if (getVisibility() != 0) goto L15;
        if (isLaidOut() == false) goto L15;
        if (this.switchingCamera == false) goto L11;
    L14:
        r2 = true;
        goto L16
    L11:
        if (this.backend != Backend.CAMERA_X) goto L15;
        if (this.cameraXSession != r5) goto L15;
        goto L14
    }

    public void bindSession(Camera2Session r3) {
        if (r3 != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        this.switchingCamera = false;
        resetBinding(!r1);
        if (r3 != null) goto L11;
        return;
    L11:
        this.backend = Backend.CAMERA_2;
        this.camera2Session = r3;
        this.animateNextConfiguration = r1;
        tryBind();
        return;
    L5:
        if (this.switchingCamera == false) goto L7;
        r1 = true;
        goto L8
    }

    public void bindSession(CameraSession r3, float r4) {
        if (r3 != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        this.switchingCamera = false;
        resetBinding(!r1);
        if (r3 != null) goto L11;
        return;
    L11:
        this.backend = Backend.CAMERA_1;
        this.camera1Session = r3;
        this.camera1LinearZoom = Utilities.clamp01(r4);
        this.animateNextConfiguration = r1;
        tryBind();
        return;
    L5:
        if (this.switchingCamera == false) goto L7;
        r1 = true;
        goto L8
    }

    @Override // com.exteragram.messenger.camera.CameraZoomSliderView
    public boolean drawPillBackground(Canvas r4, RectF r5, float r6) {
        BlurredBackgroundDrawable r0 = this.blurBackground;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (this.blurCornerRadius == r6) goto L9;
        this.blurCornerRadius = r6;
        r0.setRadius(r6);
    L9:
        this.blurBackground.setBounds(Math.round(r5.left), Math.round(r5.top), Math.round(r5.right), Math.round(r5.bottom));
        this.blurBackground.draw(r4);
        return true;
    }

    public void endPinchZoomGesture() {
        setExternalZoomGestureActive(false);
    }

    public float getCameraXResetZoom() {
        CameraXSession r0 = this.cameraXSession;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L9:
        if (ExteraConfig.getStartWithWideAngleCamera() == false) goto L14;
        if (r02 == true) goto L14;
        return this.wideZoom;
    L14:
        return this.defaultZoom;
    L5:
        if (r0.isActiveCameraFrontface() == false) goto L7;
        r02 = true;
        goto L9
    }

    public float getDisplayOneZoom() {
        return this.displayOneZoom;
    }

    public float getOpenAlpha() {
        return this.openAlpha;
    }

    public float[] getOpticalZoomRatios() {
        float[] r1 = this.opticalZoomRatios;
        return Arrays.copyOf(r1, r1.length);
    }

    @Override // com.exteragram.messenger.camera.CameraZoomSliderView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.backend == Backend.NONE) goto L13;
        if (getVisibility() == 0) goto L7;
    L10:
        this.bindRetries = 0;
        tryBind();
        return;
    L7:
        if (isEnabled() == false) goto L10;
        if (this.animateNextConfiguration == true) goto L10;
    L13:
        if (this.backend != Backend.CAMERA_X) goto L16;
        attachCameraXZoomObserver();
        return;
    }

    @Override // com.exteragram.messenger.camera.CameraZoomSliderView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.bindRunnable);
        discardPendingZoom();
        detachCameraXZoomObserver();
        if (this.appearAnimator == null) goto L10;
        cancelAppearAnimation();
        if (getVisibility() != 0) goto L7;
        float r0 = 1.0f;
    L8:
        setAppearProgress(r0);
        return;
    L7:
        r0 = 0.0f;
        goto L8
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        applyPosition();
    }

    @Override // com.exteragram.messenger.camera.CameraZoomSliderView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r3) {
        if (isEnabled() == true) goto L5;
        return false;
    L5:
        if (getAlpha() >= 0.5f) goto L8;
        return false;
    L8:
        return super.onTouchEvent(r3);
    }

    public void scaleCameraXZoom(float r3) {
        if (this.backend == Backend.CAMERA_X) goto L5;
        return;
    L5:
        if (this.cameraXSession != null) goto L7;
        return;
    L7:
        if (Float.isFinite(r3) == false) goto L12;
        setCameraXZoomRatio(getZoom() * r3);
        return;
    }

    public void setBaseTranslationY(float r2) {
        if (this.baseTranslationY == r2) goto L6;
        this.baseTranslationY = r2;
        applyPosition();
        return;
    }

    public void setBlurBackground(BlurredBackgroundDrawable r2) {
        if (this.blurBackground == r2) goto L6;
        this.blurBackground = r2;
        this.blurCornerRadius = -1.0f;
        invalidate();
        return;
    }

    public void setCameraXZoomRatio(float r3) {
        if (this.backend == Backend.CAMERA_X) goto L5;
        return;
    L5:
        if (this.cameraXSession == null) goto L9;
        float r32 = Utilities.clamp(r3, getMaximumZoom(), getMinimumZoom());
        setZoom(r32);
        this.cameraXSession.setZoomRatio(r32);
        return;
    }

    public void setOnCameraZoomChangeListener(OnCameraZoomChangeListener r1) {
        this.cameraZoomChangeListener = r1;
    }

    public void setOpenAlpha(float r2) {
        if (this.openAlpha == r2) goto L6;
        this.openAlpha = r2;
        setAlpha(r2 * this.appearProgress);
        return;
    }

    public void setTextureViewSize(int r2) {
        if (this.textureViewSize == r2) goto L6;
        this.textureViewSize = r2;
        applyPosition();
        return;
    }

    public void syncZoom() {
        int r0 = AnonymousClass3.$SwitchMap$com$exteragram$messenger$camera$InstantCameraZoomSlider$Backend[this.backend.ordinal()];
        if (r0 != 1) goto L5;
        CameraXSession r02 = this.cameraXSession;
        if (r02 != null) goto L22;
        return;
    L22:
        if (r02.isReady() == false) goto L29;
        setZoom(this.cameraXSession.getZoomRatio());
        return;
    L29:
        return;
    L5:
        if (r0 != 2) goto L7;
        Camera2Session r03 = this.camera2Session;
        if (r03 != null) goto L16;
        return;
    L16:
        if (r03.isInitiated() == false) goto L27;
        setZoom(this.camera2Session.getZoom());
        return;
    L27:
        return;
    L7:
        if (r0 == 3) goto L10;
        return;
    L10:
        if (this.camera1ZoomRatios.length <= 1) goto L25;
        setZoom(camera1RatioForLinearZoom(this.camera1LinearZoom));
        return;
    }

    public void syncZoom(float r3) {
        Backend r0 = this.backend;
        if (r0 != Backend.CAMERA_1) goto L9;
        float r32 = Utilities.clamp01(r3);
        this.camera1LinearZoom = r32;
        this.camera1ZoomIndex = -1;
        if (this.camera1ZoomRatios.length <= 1) goto L14;
        setZoom(camera1RatioForLinearZoom(r32));
        return;
    L14:
        return;
    L9:
        if (r0 != Backend.CAMERA_2) goto L12;
        setZoom(r3);
        return;
    L12:
        syncZoom();
    }

    public void unbindSession() {
        this.switchingCamera = false;
        resetBinding(true);
    }
}
