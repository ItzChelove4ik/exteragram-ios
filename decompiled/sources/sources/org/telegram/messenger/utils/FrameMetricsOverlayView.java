package org.telegram.messenger.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Choreographer;
import android.view.FrameMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes6.dex */
public final class FrameMetricsOverlayView extends View {
    private final AtomicBoolean attachedToWindowManager;
    private final Paint bgPaint;
    private Choreographer.FrameCallback choreographerCallback;
    private Window hostWindow;
    private Window.OnFrameMetricsAvailableListener listener;
    private WindowManager.LayoutParams lp;
    private Handler metricsHandler;
    private HandlerThread metricsThread;
    private View observedView;
    private final AtomicInteger onDrawCountAccum;
    private ViewTreeObserver.OnDrawListener onDrawListener;
    private int onDrawPerSecond;
    private final Runnable redraw;
    private final AtomicBoolean running;
    private final Paint textPaint;
    private final Handler uiHandler;
    private int vsyncCountAccum;
    private int vsyncPerSecond;
    private long vsyncWindowStartNs;
    private WindowManager wm;

    /* renamed from: org.telegram.messenger.utils.FrameMetricsOverlayView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FrameMetricsOverlayView this$0;

        public AnonymousClass1(FrameMetricsOverlayView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FrameMetricsOverlayView.m5192$$Nest$fgetrunning(this.this$0).get() == true) goto L5;
            return;
        L5:
            this.this$0.invalidate();
            FrameMetricsOverlayView.m5193$$Nest$fgetuiHandler(this.this$0).postDelayed(this, 300);
        }
    }

    /* renamed from: org.telegram.messenger.utils.FrameMetricsOverlayView$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric = null;

        static {
            int[] r0 = new int[Metric.values().length];
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric = r0;
            r0[Metric.INPUT_HANDLING_DURATION.ordinal()] = 1;     // Catch: NoSuchFieldError -> L13
        L24:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.ANIMATION_DURATION.ordinal()] = 2;     // Catch: NoSuchFieldError -> L14
        L28:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.LAYOUT_MEASURE_DURATION.ordinal()] = 3;     // Catch: NoSuchFieldError -> L15
        L38:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.DRAW_DURATION.ordinal()] = 4;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.SYNC_DURATION.ordinal()] = 5;     // Catch: NoSuchFieldError -> L17
        L30:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.COMMAND_ISSUE_DURATION.ordinal()] = 6;     // Catch: NoSuchFieldError -> L18
        L32:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.GPU_DURATION.ordinal()] = 7;     // Catch: NoSuchFieldError -> L19
        L34:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.UNKNOWN_DELAY_DURATION.ordinal()] = 8;     // Catch: NoSuchFieldError -> L20
        L22:
            $SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[Metric.SWAP_BUFFERS_DURATION.ordinal()] = 9;     // Catch: NoSuchFieldError -> L21
            return;
        }
    }

    public enum Metric extends Enum<Metric> {
        private static final /* synthetic */ Metric[] $VALUES = null;
        public static final Metric ANIMATION_DURATION = null;
        public static final Metric COMMAND_ISSUE_DURATION = null;
        public static final Metric DRAW_DURATION = null;
        public static final Metric GPU_DURATION = null;
        public static final Metric INPUT_HANDLING_DURATION = null;
        public static final Metric LAYOUT_MEASURE_DURATION = null;
        public static final Metric SWAP_BUFFERS_DURATION = null;
        public static final Metric SYNC_DURATION = null;
        public static final Metric TOTAL_DURATION = null;
        public static final Metric UNKNOWN_DELAY_DURATION = null;
        double avgMs;
        final boolean isDuration;
        final int key;
        final String label;
        long last;
        final int minApi;

        private static /* synthetic */ Metric[] $values() {
            return new Metric[]{UNKNOWN_DELAY_DURATION, INPUT_HANDLING_DURATION, ANIMATION_DURATION, LAYOUT_MEASURE_DURATION, DRAW_DURATION, SYNC_DURATION, COMMAND_ISSUE_DURATION, SWAP_BUFFERS_DURATION, GPU_DURATION, TOTAL_DURATION};
        }

        static {
            UNKNOWN_DELAY_DURATION = new Metric("UNKNOWN_DELAY_DURATION", 0, 0, "unknown delay", true);
            INPUT_HANDLING_DURATION = new Metric("INPUT_HANDLING_DURATION", 1, 1, PluginsConstants.Settings.TYPE_INPUT, true);
            ANIMATION_DURATION = new Metric("ANIMATION_DURATION", 2, 2, "animation", true);
            LAYOUT_MEASURE_DURATION = new Metric("LAYOUT_MEASURE_DURATION", 3, 3, "layout", true);
            DRAW_DURATION = new Metric("DRAW_DURATION", 4, 4, "draw", true);
            SYNC_DURATION = new Metric("SYNC_DURATION", 5, 5, "sync", true);
            COMMAND_ISSUE_DURATION = new Metric("COMMAND_ISSUE_DURATION", 6, 6, "cmd issue", true);
            SWAP_BUFFERS_DURATION = new Metric("SWAP_BUFFERS_DURATION", 7, 7, "swap buffers", true);
            GPU_DURATION = new Metric("GPU_DURATION", 8, 12, "gpu", true, 31);
            TOTAL_DURATION = new Metric("TOTAL_DURATION", 9, 8, "total", true);
            $VALUES = $values();
        }

        Metric(String r8, int r9, int r10, String r11, boolean r12) {
            this(r8, r9, r10, r11, r12, 24);
        }

        Metric(String r1, int r2, int r3, String r4, boolean r5, int r6) {
            this.last = Long.MIN_VALUE;
            this.avgMs = 0.0d;
            this.key = r3;
            this.label = r4;
            this.isDuration = r5;
            this.minApi = r6;
        }

        public static Metric valueOf(String r1) {
            return (Metric) Enum.valueOf(Metric.class, r1);
        }

        public static Metric[] values() {
            return (Metric[]) $VALUES.clone();
        }

        public boolean isAvailable() {
            if (Build.VERSION.SDK_INT < this.minApi) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: $r8$lambda$m42WIPdz4HTot3OVFKZ-a0YbcXM, reason: not valid java name */
    public static /* synthetic */ void m5189$r8$lambda$m42WIPdz4HTot3OVFKZa0YbcXM(Window r8, FrameMetrics r9, int r10) {
        Metric[] r82 = Metric.values();
        int r102 = r82.length;
        int r0 = 0;
    L3:
        if (r0 >= r102) goto L17;
        Metric r1 = r82[r0];
        if (r1.isAvailable() == true) goto L7;
        r1.last = Long.MIN_VALUE;
    L16:
        r0 = r0 + 1;
        goto L3
    L7:
        long r2 = r9.getMetric(r1.key);
        r1.last = r2;
        if (r1.isDuration == false) goto L16;
        if (r2 < 0) goto L16;
        double r22 = r2 / 1000000.0d;
        double r4 = r1.avgMs;
        if (r4 == 0.0d) goto L15;
        r22 = ((r22 - r4) * 0.05d) + r4;
    L15:
        r1.avgMs = r22;
        goto L16
    }

    /* renamed from: $r8$lambda$tZjZH254NAiFWunKAjYF90qd6-E, reason: not valid java name */
    public static /* synthetic */ void m5190$r8$lambda$tZjZH254NAiFWunKAjYF90qd6E(FrameMetricsOverlayView r0) {
        r0.lambda$attachOnDrawListener$2();
    }

    /* renamed from: $r8$lambda$vsVCXcogVOilQ0ZN5Nm2a0aq-Vk, reason: not valid java name */
    public static /* synthetic */ void m5191$r8$lambda$vsVCXcogVOilQ0ZN5Nm2a0aqVk(FrameMetricsOverlayView r0, long r1) {
        r0.lambda$start$1(r1);
    }

    /* renamed from: -$$Nest$fgetrunning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AtomicBoolean m5192$$Nest$fgetrunning(FrameMetricsOverlayView r0) {
        return r0.running;
    }

    /* renamed from: -$$Nest$fgetuiHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Handler m5193$$Nest$fgetuiHandler(FrameMetricsOverlayView r0) {
        return r0.uiHandler;
    }

    public FrameMetricsOverlayView(Context r6) {
        super(r6.getApplicationContext());
        this.vsyncCountAccum = 0;
        this.vsyncWindowStartNs = 0;
        this.vsyncPerSecond = 0;
        this.onDrawCountAccum = new AtomicInteger(0);
        this.onDrawPerSecond = 0;
        Paint r0 = new Paint(1);
        this.bgPaint = r0;
        Paint r2 = new Paint(1);
        this.textPaint = r2;
        this.running = new AtomicBoolean(false);
        this.attachedToWindowManager = new AtomicBoolean(false);
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.redraw = new AnonymousClass1(this);
        r0.setColor(-1342177280);
        r2.setColor(-1);
        r2.setTextSize(AndroidUtilities.dp(9.0f));
        r2.setFakeBoldText(true);
        r2.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
        setWillNotDraw(false);
    }

    @SuppressLint({"RtlHardcoded"})
    private void attachInternal(Activity r7, int r8, int r9) {
        this.wm = (WindowManager) r7.getSystemService("window");
        this.hostWindow = r7.getWindow();
        WindowManager.LayoutParams r0 = new WindowManager.LayoutParams(-2, -2, 2, 792, -3);
        this.lp = r0;
        r0.gravity = r8;
        int r72 = AndroidUtilities.dp(r9);
        WindowManager.LayoutParams r82 = this.lp;
        r82.x = r72;
        r82.y = r72;
        r82.width = AndroidUtilities.dp(260.0f);
        this.wm.addView(this, this.lp);
        this.attachedToWindowManager.set(true);
        start();
    }

    private void attachOnDrawListener() {
        if (this.observedView == null) goto L10;
        this.onDrawListener = new FrameMetricsOverlayView$$ExternalSyntheticLambda2(this);
        ViewTreeObserver r0 = this.observedView.getViewTreeObserver();
        if (r0.isAlive() == false) goto L9;
        r0.addOnDrawListener(this.onDrawListener);
        return;
    L9:
        return;
    }

    public static FrameMetricsOverlayView attachToActivityCorner(Activity r1, int r2, int r3, View r4) {
        FrameMetricsOverlayView r0 = new FrameMetricsOverlayView(r1);
        r0.setObservedView(r4);
        r0.attachInternal(r1, r2, r3);
        return r0;
    }

    private void detachOnDrawListener() {
        View r0 = this.observedView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.onDrawListener == null) goto L13;
        ViewTreeObserver r02 = r0.getViewTreeObserver();
        if (r02.isAlive() == false) goto L10;
        r02.removeOnDrawListener(this.onDrawListener);
    L10:
        this.onDrawListener = null;
        return;
    }

    private /* synthetic */ void lambda$attachOnDrawListener$2() {
        this.onDrawCountAccum.incrementAndGet();
    }

    private /* synthetic */ void lambda$start$1(long r5) {
        if (this.running.get() == true) goto L5;
        return;
    L5:
        long r0 = this.vsyncWindowStartNs;
        if (r0 != 0) goto L8;
        this.vsyncWindowStartNs = r5;
    L12:
        Choreographer.getInstance().postFrameCallback(this.choreographerCallback);
        return;
    L8:
        long r02 = r5 - r0;
        int r1 = this.vsyncCountAccum;
        if (r02 < 1000000000) goto L11;
        this.vsyncPerSecond = r1;
        this.onDrawPerSecond = this.onDrawCountAccum.getAndSet(0);
        this.vsyncCountAccum = 0;
        this.vsyncWindowStartNs = r5;
        goto L12
    L11:
        this.vsyncCountAccum = r1 + 1;
        goto L12
    }

    private void start() {
        if (this.running.getAndSet(true) == false) goto L5;
        return;
    L5:
        HandlerThread r0 = new HandlerThread("FrameMetrics");
        this.metricsThread = r0;
        r0.start();
        this.metricsHandler = new Handler(this.metricsThread.getLooper());
        FrameMetricsOverlayView$$ExternalSyntheticLambda0 r02 = new FrameMetricsOverlayView$$ExternalSyntheticLambda0();
        this.listener = r02;
        this.hostWindow.addOnFrameMetricsAvailableListener(r02, this.metricsHandler);
        this.choreographerCallback = new FrameMetricsOverlayView$$ExternalSyntheticLambda1(this);
        Choreographer.getInstance().postFrameCallback(this.choreographerCallback);
        attachOnDrawListener();
        this.uiHandler.post(this.redraw);
    }

    private void stop() {
        this.running.set(false);
        this.uiHandler.removeCallbacks(this.redraw);
        Window r0 = this.hostWindow;
        if (r0 == null) goto L8;
        Window.OnFrameMetricsAvailableListener r1 = this.listener;
        if (r1 == null) goto L8;
        r0.removeOnFrameMetricsAvailableListener(r1);
    L8:
        if (this.choreographerCallback == null) goto L10;
        Choreographer.getInstance().removeFrameCallback(this.choreographerCallback);
        this.choreographerCallback = null;
    L10:
        detachOnDrawListener();
        HandlerThread r2 = this.metricsThread;
        if (r2 == null) goto L14;
        r2.quitSafely();
        return;
    }

    public void detach() {
        stop();
        if (this.wm != null) goto L5;
    L7:
        this.wm = null;
        this.lp = null;
        this.hostWindow = null;
        return;
    L5:
        if (this.attachedToWindowManager.getAndSet(false) == false) goto L7;
        this.wm.removeViewImmediate(this);     // Catch: Throwable -> L9
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r37) {
        float r9 = AndroidUtilities.dp(8.0f);
        float r10 = AndroidUtilities.dp(11.0f);
        int r1 = Metric.values().length + 9;
        if (getWidth() <= 0) goto L6;
        int r2 = getWidth();
    L7:
        r37.drawRoundRect(0.0f, 0.0f, r2, (2.0f * r9) + (r1 * r10), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.bgPaint);
        float r22 = r9 + r10;
        Metric[] r3 = Metric.values();
        int r4 = r3.length;
        double r19 = 0.0d;
        double r21 = 0.0d;
        double r23 = 0.0d;
        double r25 = 0.0d;
        float r8 = r10;
        int r7 = 0;
        long r5 = 0;
        long r102 = 0;
        long r12 = 0;
        long r14 = 0;
    L8:
        Metric[] r16 = r3;
        if (r7 >= r4) goto L32;
        int r29 = r4;
        Metric r42 = r16[r7];
        if (r42.isAvailable() == false) goto L29;
        int r30 = r7;
        float r31 = r8;
        long r72 = r42.last;
        if (r72 >= 0) goto L15;
    L14:
        long r34 = r5;
        long r32 = r14;
        String r33 = String.format(Locale.US, "%-16s : n/a", new Object[]{r42.label});
    L27:
        r14 = r32;
    L28:
        r5 = r34;
    L31:
        r37.drawText(r33, r9, r22, this.textPaint);
        r22 = r22 + r31;
        r7 = r30 + 1;
        r3 = r16;
        r4 = r29;
        r8 = r31;
        goto L8
    L15:
        r32 = r14;
        if (r42.isDuration == false) goto L26;
        r34 = r5;
        r33 = String.format(Locale.US, "%-16s : %5.2f / %5.2f ms", new Object[]{r42.label, Double.valueOf(r72 / 1000000.0d), Double.valueOf(r42.avgMs)});
        switch(AnonymousClass2.$SwitchMap$org$telegram$messenger$utils$FrameMetricsOverlayView$Metric[r42.ordinal()]) {
            case 1: goto L25;
            case 2: goto L25;
            case 3: goto L25;
            case 4: goto L25;
            case 5: goto L23;
            case 6: goto L22;
            case 7: goto L21;
            case 8: goto L20;
            case 9: goto L20;
            default: goto L27;
        };
    L20:
        r14 = r32 + r42.last;
        r25 = r25 + r42.avgMs;
        goto L28
    L21:
        r12 = r12 + r42.last;
        r23 = r23 + r42.avgMs;
        goto L27
    L22:
        r102 = r102 + r42.last;
        r21 = r21 + r42.avgMs;
        goto L27
    L23:
        long r52 = r42.last;
        double r142 = r42.avgMs;
        r19 = r19 + r142;
        r102 = r102 + r52;
        r21 = r21 + r142;
        r5 = r34 + r52;
    L24:
        r14 = r32;
        goto L31
    L25:
        r5 = r34 + r42.last;
        r19 = r19 + r42.avgMs;
        goto L24
    L26:
        r34 = r5;
        r33 = String.format(Locale.US, "%-16s : %d", new Object[]{r42.label, Long.valueOf(r72)});
        goto L27
    L29:
        r30 = r7;
        r31 = r8;
        goto L14
    L32:
        long r342 = r5;
        float r312 = r8;
        long r322 = r14;
        long r43 = Math.max(r342, Math.max(r102, r12));
        double r143 = r21;
        long r212 = r12;
        double r44 = r19;
        double r122 = Math.max(r44, Math.max(r143, r23));
        float r24 = r22 + r312;
        Locale r6 = Locale.US;
        r37.drawText(String.format(r6, "%-16s : %5.2f / %5.2f ms", new Object[]{"ui", Double.valueOf(r342 / 1000000.0d), Double.valueOf(r44)}), r9, r24, this.textPaint);
        float r26 = r24 + r312;
        r37.drawText(String.format(r6, "%-16s : %5.2f / %5.2f ms", new Object[]{"rt", Double.valueOf(r102 / 1000000.0d), Double.valueOf(r143)}), r9, r26, this.textPaint);
        float r27 = r26 + r312;
        r37.drawText(String.format(r6, "%-16s : %5.2f / %5.2f ms", new Object[]{"gpu", Double.valueOf(r212 / 1000000.0d), Double.valueOf(r23)}), r9, r27, this.textPaint);
        float r28 = r27 + r312;
        r37.drawText(String.format(r6, "%-16s : %5.2f / %5.2f ms", new Object[]{"other", Double.valueOf(r322 / 1000000.0d), Double.valueOf(r25)}), r9, r28, this.textPaint);
        float r210 = r28 + r312;
        r37.drawText(String.format(r6, "%-16s : %5.2f / %5.2f ms", new Object[]{"frame", Double.valueOf(r43 / 1000000.0d), Double.valueOf(r122)}), r9, r210, this.textPaint);
        float r211 = (r210 + r312) + r312;
        r37.drawText(String.format(r6, "%-16s : %d /s", new Object[]{"vsync", Integer.valueOf(this.vsyncPerSecond)}), r9, r211, this.textPaint);
        float r213 = r211 + r312;
        if (this.observedView == null) goto L35;
        String r35 = "onDraw";
    L36:
        r37.drawText(String.format(r6, "%-16s : %d /s", new Object[]{r35, Integer.valueOf(this.onDrawPerSecond)}), r9, r213, this.textPaint);
        return;
    L35:
        r35 = "onDraw (none)";
        goto L36
    L6:
        r2 = AndroidUtilities.dp(260.0f);
        goto L7
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        setMeasuredDimension(AndroidUtilities.dp(210.0f), (AndroidUtilities.dp(8.0f) * 2) + (AndroidUtilities.dp(11.0f) * (Metric.values().length + 9)));
    }

    public void setObservedView(View r1) {
        detachOnDrawListener();
        this.observedView = r1;
        if (this.running.get() == false) goto L6;
        attachOnDrawListener();
        return;
    }
}
