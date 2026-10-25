package org.telegram.ui.Charts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.VibrationEffect;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.ChartPickerDelegate;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.view_data.ChartBottomSignatureData;
import org.telegram.ui.Charts.view_data.ChartHeaderView;
import org.telegram.ui.Charts.view_data.ChartHorizontalLinesData;
import org.telegram.ui.Charts.view_data.LegendSignatureView;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes6.dex */
public abstract class BaseChartView<T extends ChartData, L extends LineViewData> extends View implements ChartPickerDelegate.Listener {
    protected static final boolean ANIMATE_PICKER_SIZES = false;
    private static final int BOTTOM_SIGNATURE_OFFSET = 0;
    public static final int BOTTOM_SIGNATURE_START_ALPHA = 0;
    private static final int BOTTOM_SIGNATURE_TEXT_HEIGHT = 0;
    private static final int DP_1 = 0;
    private static final int DP_12 = 0;
    private static final int DP_2 = 0;
    private static final int DP_5 = 0;
    private static final int DP_6 = 0;
    private static final int DP_8 = 0;
    public static final float HORIZONTAL_PADDING = 0.0f;
    public static FastOutSlowInInterpolator INTERPOLATOR;
    private static final int LANDSCAPE_END_PADDING = 0;
    private static final int PICKER_CAPTURE_WIDTH = 0;
    protected static final int PICKER_PADDING = 0;
    private static final float SELECTED_LINE_WIDTH = 0.0f;
    public static final int SIGNATURE_TEXT_HEIGHT = 0;
    public static final float SIGNATURE_TEXT_SIZE = 0.0f;
    public static final boolean USE_LINES = false;
    private final int ANIM_DURATION;
    ValueAnimator alphaAnimator;
    ValueAnimator alphaBottomAnimator;
    public boolean animateLegentTo;
    float animateToMaxHeight;
    float animateToMinHeight;
    protected float animatedToPickerMaxHeight;
    protected float animatedToPickerMinHeight;
    private Bitmap bottomChartBitmap;
    private Canvas bottomChartCanvas;
    ArrayList<ChartBottomSignatureData> bottomSignatureDate;
    protected int bottomSignatureOffset;
    Paint bottomSignaturePaint;
    float bottomSignaturePaintAlpha;
    protected boolean canCaptureChartSelection;
    long capturedTime;
    int capturedX;
    int capturedY;
    int chartActiveLineAlpha;
    public RectF chartArea;
    int chartBottom;
    protected boolean chartCaptured;
    T chartData;
    public float chartEnd;
    public float chartFullWidth;
    ChartHeaderView chartHeaderView;
    public float chartStart;
    public float chartWidth;
    ChartBottomSignatureData currentBottomSignatures;
    public float currentMaxHeight;
    public float currentMinHeight;
    protected DateSelectionListener dateSelectionListener;
    protected boolean drawPointOnSelection;
    Paint emptyPaint;
    public boolean enabled;
    int endXIndex;
    private Rect exclusionRect;
    private List<Rect> exclusionRects;
    private ValueAnimator.AnimatorUpdateListener heightUpdateListener;
    int hintLinePaintAlpha;
    ArrayList<ChartHorizontalLinesData> horizontalLines;
    boolean invalidatePickerChart;
    boolean landscape;
    int lastH;
    long lastTime;
    int lastW;
    int lastX;
    int lastY;
    public boolean legendShowing;
    public LegendSignatureView legendSignatureView;
    Paint linePaint;
    public ArrayList<L> lines;
    Animator maxValueAnimator;
    private ValueAnimator.AnimatorUpdateListener minHeightUpdateListener;
    private float minMaxUpdateStep;
    Path pathTmp;
    Animator pickerAnimator;
    public ChartPickerDelegate pickerDelegate;
    private ValueAnimator.AnimatorUpdateListener pickerHeightUpdateListener;
    protected float pickerMaxHeight;
    protected float pickerMinHeight;
    private ValueAnimator.AnimatorUpdateListener pickerMinHeightUpdateListener;
    Rect pickerRect;
    Paint pickerSelectorPaint;
    public float pickerWidth;
    public int pikerHeight;
    boolean postTransition;
    protected Theme.ResourcesProvider resourcesProvider;
    Paint ripplePaint;
    protected float selectedCoordinate;
    protected int selectedIndex;
    Paint selectedLinePaint;
    public float selectionA;
    ValueAnimator selectionAnimator;
    private ValueAnimator.AnimatorUpdateListener selectionAnimatorListener;
    Paint selectionBackgroundPaint;
    private Animator.AnimatorListener selectorAnimatorEndListener;
    public SharedUiComponents sharedUiComponents;
    TextPaint signaturePaint;
    TextPaint signaturePaint2;
    float signaturePaintAlpha;
    private float startFromMax;
    private float startFromMaxH;
    private float startFromMin;
    private float startFromMinH;
    int startXIndex;
    boolean superDraw;
    float thresholdMaxHeight;
    protected int tmpI;
    protected int tmpN;
    private final int touchSlop;
    public int transitionMode;
    public TransitionParams transitionParams;
    Paint unactiveBottomChartPaint;
    boolean useAlphaSignature;
    protected boolean useMinHeight;
    VibrationEffect vibrationEffect;
    Paint whiteLinePaint;

    /* renamed from: org.telegram.ui.Charts.BaseChartView$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BaseChartView this$0;

        public AnonymousClass1(BaseChartView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            BaseChartView r0 = this.this$0;
            r0.pickerMaxHeight = ((Float) r2.getAnimatedValue()).floatValue();
            BaseChartView r1 = this.this$0;
            r1.invalidatePickerChart = true;
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Charts.BaseChartView$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BaseChartView this$0;

        public AnonymousClass2(BaseChartView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            BaseChartView r0 = this.this$0;
            r0.pickerMinHeight = ((Float) r2.getAnimatedValue()).floatValue();
            BaseChartView r1 = this.this$0;
            r1.invalidatePickerChart = true;
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Charts.BaseChartView$3, reason: invalid class name */
    public class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BaseChartView this$0;

        public AnonymousClass3(BaseChartView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            BaseChartView r0 = this.this$0;
            r0.selectionA = ((Float) r2.getAnimatedValue()).floatValue();
            BaseChartView r22 = this.this$0;
            r22.legendSignatureView.setAlpha(r22.selectionA);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Charts.BaseChartView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseChartView this$0;

        public AnonymousClass4(BaseChartView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            BaseChartView r32 = this.this$0;
            if (r32.animateLegentTo == true) goto L5;
            r32.legendShowing = false;
            r32.legendSignatureView.setVisibility(8);
            this.this$0.invalidate();
        L5:
            this.this$0.postTransition = false;
        }
    }

    /* renamed from: org.telegram.ui.Charts.BaseChartView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseChartView this$0;
        final /* synthetic */ ChartHorizontalLinesData val$newData;

        public AnonymousClass5(BaseChartView r1, ChartHorizontalLinesData r2) {
            this.this$0 = r1;
            this.val$newData = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.horizontalLines.clear();
            this.this$0.horizontalLines.add(this.val$newData);
        }
    }

    /* renamed from: org.telegram.ui.Charts.BaseChartView$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseChartView this$0;
        final /* synthetic */ ChartBottomSignatureData val$data;

        public AnonymousClass6(BaseChartView r1, ChartBottomSignatureData r2) {
            this.this$0 = r1;
            this.val$data = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.bottomSignatureDate.clear();
            this.this$0.bottomSignatureDate.add(this.val$data);
        }
    }

    public interface DateSelectionListener {
        void onDateSelected(long r1);
    }

    public static class SharedUiComponents {
        private Canvas canvas;
        private boolean invalidate;
        int k;
        private Bitmap pickerRoundBitmap;
        private RectF rectF;
        private Theme.ResourcesProvider resourcesProvider;
        private Paint xRefP;

        public SharedUiComponents() {
            this(null);
        }

        public SharedUiComponents(Theme.ResourcesProvider r4) {
            this.rectF = new RectF();
            Paint r0 = new Paint(1);
            this.xRefP = r0;
            this.k = 0;
            this.invalidate = true;
            r0.setColor(0);
            this.xRefP.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            this.resourcesProvider = r4;
        }

        public Bitmap getPickerMaskBitmap(int r4, int r5) {
            int r0 = (r4 + r5) << 10;
            if (r0 == this.k) goto L5;
        L6:
            this.invalidate = false;
            this.k = r0;
            this.pickerRoundBitmap = Bitmap.createBitmap(r5, r4, Bitmap.Config.ARGB_8888);
            this.canvas = new Canvas(this.pickerRoundBitmap);
            this.rectF.set(0.0f, 0.0f, r5, r4);
            this.canvas.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
            this.canvas.drawRoundRect(this.rectF, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.xRefP);
        L8:
            return this.pickerRoundBitmap;
        L5:
            if (this.invalidate == false) goto L8;
            goto L6
        }

        public void invalidate() {
            this.invalidate = true;
        }
    }

    public static /* synthetic */ void $r8$lambda$1pIq4xvzwxuMMHLar5czZw28VP4(BaseChartView r0, LineViewData r1, ValueAnimator r2) {
        r0.lambda$onCheckChanged$5(r1, r2);
    }

    /* renamed from: $r8$lambda$A-XptsAnIXPu2xR4hWPRhkGvjRQ, reason: not valid java name */
    public static /* synthetic */ void m7370$r8$lambda$AXptsAnIXPu2xR4hWPRhkGvjRQ(BaseChartView r0, ChartBottomSignatureData r1, ValueAnimator r2) {
        r0.lambda$updateDates$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$A2JflOeeZkfiHMl9EHO5_5WdebU(BaseChartView r0, LineViewData r1, ValueAnimator r2) {
        r0.lambda$onCheckChanged$4(r1, r2);
    }

    /* renamed from: $r8$lambda$T6S62-lFD1fjNkua2VUMadAgjHg, reason: not valid java name */
    public static /* synthetic */ void m7371$r8$lambda$T6S62lFD1fjNkua2VUMadAgjHg(BaseChartView r0, ValueAnimator r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$TDUFRa8WhBI7mm8d8Me5RDEfOkY(BaseChartView r0, ChartHorizontalLinesData r1, ValueAnimator r2) {
        r0.lambda$setMaxMinValue$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sVMCrrcPMEnizSzwasZhUg2JcQY(BaseChartView r0, ValueAnimator r1) {
        r0.lambda$new$1(r1);
    }

    static {
        HORIZONTAL_PADDING = AndroidUtilities.dpf2(16.0f);
        SELECTED_LINE_WIDTH = AndroidUtilities.dpf2(1.5f);
        SIGNATURE_TEXT_SIZE = AndroidUtilities.dpf2(12.0f);
        SIGNATURE_TEXT_HEIGHT = AndroidUtilities.dp(18.0f);
        BOTTOM_SIGNATURE_TEXT_HEIGHT = AndroidUtilities.dp(14.0f);
        BOTTOM_SIGNATURE_START_ALPHA = AndroidUtilities.dp(10.0f);
        PICKER_PADDING = AndroidUtilities.dp(16.0f);
        PICKER_CAPTURE_WIDTH = AndroidUtilities.dp(24.0f);
        LANDSCAPE_END_PADDING = AndroidUtilities.dp(16.0f);
        BOTTOM_SIGNATURE_OFFSET = AndroidUtilities.dp(10.0f);
        DP_12 = AndroidUtilities.dp(12.0f);
        DP_8 = AndroidUtilities.dp(8.0f);
        DP_6 = AndroidUtilities.dp(6.0f);
        DP_5 = AndroidUtilities.dp(5.0f);
        DP_2 = AndroidUtilities.dp(2.0f);
        DP_1 = AndroidUtilities.dp(1.0f);
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        boolean r0 = true;
    L6:
        USE_LINES = r0;
        ANIMATE_PICKER_SIZES = true;
        INTERPOLATOR = new FastOutSlowInInterpolator();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public BaseChartView(Context r2) {
        this(r2, null);
    }

    public BaseChartView(Context r5, Theme.ResourcesProvider r6) {
        super(r5);
        this.horizontalLines = new ArrayList(10);
        this.bottomSignatureDate = new ArrayList(25);
        this.lines = new ArrayList();
        this.ANIM_DURATION = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        this.drawPointOnSelection = true;
        this.currentMaxHeight = 250.0f;
        this.currentMinHeight = 0.0f;
        this.animateToMaxHeight = 0.0f;
        this.animateToMinHeight = 0.0f;
        this.thresholdMaxHeight = 0.0f;
        this.invalidatePickerChart = true;
        this.landscape = false;
        this.enabled = true;
        this.emptyPaint = new Paint();
        this.linePaint = new Paint();
        this.selectedLinePaint = new Paint();
        this.signaturePaint = new TextPaint(1);
        this.signaturePaint2 = new TextPaint(1);
        this.bottomSignaturePaint = new TextPaint(1);
        this.pickerSelectorPaint = new Paint(1);
        this.unactiveBottomChartPaint = new Paint();
        this.selectionBackgroundPaint = new Paint(1);
        this.ripplePaint = new Paint(1);
        this.whiteLinePaint = new Paint(1);
        this.pickerRect = new Rect();
        this.pathTmp = new Path();
        this.postTransition = false;
        this.pickerDelegate = new ChartPickerDelegate(this);
        this.chartCaptured = false;
        this.selectedIndex = -1;
        this.selectedCoordinate = -1.0f;
        this.legendShowing = false;
        this.selectionA = 0.0f;
        this.superDraw = false;
        this.useAlphaSignature = false;
        this.transitionMode = 0;
        this.pikerHeight = AndroidUtilities.dp(46.0f);
        this.chartArea = new RectF();
        this.pickerHeightUpdateListener = new AnonymousClass1(this);
        this.pickerMinHeightUpdateListener = new AnonymousClass2(this);
        this.heightUpdateListener = new BaseChartView$$ExternalSyntheticLambda0(this);
        this.minHeightUpdateListener = new BaseChartView$$ExternalSyntheticLambda1(this);
        this.selectionAnimatorListener = new AnonymousClass3(this);
        this.selectorAnimatorEndListener = new AnonymousClass4(this);
        this.useMinHeight = false;
        this.lastW = 0;
        this.lastH = 0;
        this.exclusionRect = new Rect();
        ArrayList r0 = new ArrayList();
        this.exclusionRects = r0;
        r0.add(this.exclusionRect);
        this.lastTime = 0;
        this.animateLegentTo = false;
        this.resourcesProvider = r6;
        init();
        this.touchSlop = ViewConfiguration.get(r5).getScaledTouchSlop();
    }

    public static Path RoundedRect(Path r4, float r5, float r6, float r7, float r8, float r9, float r10, boolean r11, boolean r12, boolean r13, boolean r14) {
        r4.reset();
        if (r9 >= 0.0f) goto L6;
        r9 = 0.0f;
    L6:
        if (r10 >= 0.0f) goto L8;
        r10 = 0.0f;
    L8:
        float r52 = r7 - r5;
        float r82 = r8 - r6;
        float r2 = r52 / 2.0f;
        if (r9 <= r2) goto L11;
        r9 = r2;
    L11:
        float r22 = r82 / 2.0f;
        if (r10 <= r22) goto L14;
        r10 = r22;
    L14:
        float r53 = r52 - (r9 * 2.0f);
        float r83 = r82 - (2.0f * r10);
        r4.moveTo(r7, r6 + r10);
        if (r12 == false) goto L17;
        float r62 = -r10;
        r4.rQuadTo(0.0f, r62, -r9, r62);
    L18:
        r4.rLineTo(-r53, 0.0f);
        if (r11 == false) goto L21;
        float r63 = -r9;
        r4.rQuadTo(r63, 0.0f, r63, r10);
    L22:
        r4.rLineTo(0.0f, r83);
        if (r14 == false) goto L25;
        r4.rQuadTo(0.0f, r10, r9, r10);
    L26:
        r4.rLineTo(r53, 0.0f);
        if (r13 == false) goto L29;
        r4.rQuadTo(r9, 0.0f, r9, -r10);
    L30:
        r4.rLineTo(0.0f, -r83);
        r4.close();
        return r4;
    L29:
        r4.rLineTo(r9, 0.0f);
        r4.rLineTo(0.0f, -r10);
        goto L30
    L25:
        r4.rLineTo(0.0f, r10);
        r4.rLineTo(r9, 0.0f);
        goto L26
    L21:
        r4.rLineTo(-r9, 0.0f);
        r4.rLineTo(0.0f, r10);
        goto L22
    L17:
        r4.rLineTo(0.0f, -r10);
        r4.rLineTo(-r9, 0.0f);
        goto L18
    }

    private /* synthetic */ void lambda$new$0(ValueAnimator r1) {
        this.currentMaxHeight = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$new$1(ValueAnimator r1) {
        this.currentMinHeight = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$onCheckChanged$4(LineViewData r1, ValueAnimator r2) {
        r1.alpha = ((Float) r2.getAnimatedValue()).floatValue();
        this.invalidatePickerChart = true;
        invalidate();
    }

    private /* synthetic */ void lambda$onCheckChanged$5(LineViewData r1, ValueAnimator r2) {
        r1.alpha = ((Float) r2.getAnimatedValue()).floatValue();
        this.invalidatePickerChart = true;
        invalidate();
    }

    private /* synthetic */ void lambda$setMaxMinValue$2(ChartHorizontalLinesData r6, ValueAnimator r7) {
        r6.alpha = (int) ((Float) r7.getAnimatedValue()).floatValue();
        ArrayList<ChartHorizontalLinesData> r72 = this.horizontalLines;
        int r0 = r72.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        ChartHorizontalLinesData r2 = r72.get(r1);
        r1 = r1 + 1;
        ChartHorizontalLinesData r22 = r2;
        if (r22 == r6) goto L3;
        r22.alpha = (int) ((r22.fixedAlpha / 255.0f) * (255 - r6.alpha));
        goto L3
    L7:
        invalidate();
    }

    private /* synthetic */ void lambda$updateDates$3(ChartBottomSignatureData r7, ValueAnimator r8) {
        float r82 = ((Float) r8.getAnimatedValue()).floatValue();
        ArrayList<ChartBottomSignatureData> r0 = this.bottomSignatureDate;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        ChartBottomSignatureData r3 = r0.get(r2);
        r2 = r2 + 1;
        ChartBottomSignatureData r32 = r3;
        if (r32 == r7) goto L6;
        r32.alpha = (int) ((1.0f - r82) * r32.fixedAlpha);
        goto L3
    L6:
        r7.alpha = (int) (255.0f * r82);
        goto L3
    L8:
        invalidate();
    }

    private void measureHeightThreshold() {
        int r0 = getMeasuredHeight() - this.chartBottom;
        float r1 = this.animateToMaxHeight;
        if (r1 == 0.0f) goto L8;
        if (r0 == 0) goto L9;
        this.thresholdMaxHeight = (r1 / r0) * SIGNATURE_TEXT_SIZE;
        return;
    L9:
        return;
    }

    private void measureSizes() {
        if (getMeasuredHeight() > 0) goto L5;
        return;
    L5:
        if (getMeasuredWidth() <= 0) goto L17;
        float r0 = getMeasuredWidth();
        float r1 = HORIZONTAL_PADDING;
        this.pickerWidth = r0 - (2.0f * r1);
        this.chartStart = r1;
        float r02 = getMeasuredWidth();
        if (this.landscape == false) goto L10;
        float r2 = LANDSCAPE_END_PADDING;
    L11:
        float r03 = r02 - r2;
        this.chartEnd = r03;
        float r04 = r03 - this.chartStart;
        this.chartWidth = r04;
        ChartPickerDelegate r22 = this.pickerDelegate;
        this.chartFullWidth = r04 / (r22.pickerEnd - r22.pickerStart);
        updateLineSignature();
        this.chartBottom = AndroidUtilities.dp(100.0f);
        this.chartArea.set(this.chartStart - r1, 0.0f, this.chartEnd + r1, getMeasuredHeight() - this.chartBottom);
        if (this.chartData == null) goto L14;
        this.bottomSignatureOffset = (int) (AndroidUtilities.dp(20.0f) / (this.pickerWidth / this.chartData.x.length));
    L14:
        measureHeightThreshold();
        return;
    L10:
        r2 = r1;
        goto L11
    }

    private void setMaxMinValue(long r9, long r11, boolean r13) {
        setMaxMinValue(r9, r11, r13, false, false);
    }

    private void updateDates(int r7) {
        ChartBottomSignatureData r0 = this.currentBottomSignatures;
        if (r0 != null) goto L5;
    L8:
        int r72 = Integer.highestOneBit(r7) << 1;
        ChartBottomSignatureData r02 = this.currentBottomSignatures;
        if (r02 != null) goto L11;
    L13:
        ValueAnimator r03 = this.alphaBottomAnimator;
        if (r03 == null) goto L16;
        r03.removeAllListeners();
        this.alphaBottomAnimator.cancel();
    L16:
        double r04 = r72;
        double r2 = 0.2d * r04;
        ChartBottomSignatureData r1 = new ChartBottomSignatureData(r72, (int) (r04 + r2), (int) (r04 - r2));
        r1.alpha = 255;
        if (this.currentBottomSignatures != null) goto L20;
        this.currentBottomSignatures = r1;
        r1.alpha = 255;
        this.bottomSignatureDate.add(r1);
        return;
    L20:
        this.currentBottomSignatures = r1;
        this.tmpN = this.bottomSignatureDate.size();
        int r05 = 0;
    L21:
        int r22 = this.tmpN;
        ArrayList<ChartBottomSignatureData> r3 = this.bottomSignatureDate;
        if (r05 >= r22) goto L24;
        ChartBottomSignatureData r23 = r3.get(r05);
        r23.fixedAlpha = r23.alpha;
        r05 = r05 + 1;
        goto L21
    L24:
        r3.add(r1);
        if (this.bottomSignatureDate.size() <= 2) goto L27;
        this.bottomSignatureDate.remove(0);
    L27:
        ValueAnimator r73 = createAnimator(0.0f, 1.0f, new BaseChartView$$ExternalSyntheticLambda3(this, r1)).setDuration(200);
        this.alphaBottomAnimator = r73;
        r73.addListener(new AnonymousClass6(this, r1));
        this.alphaBottomAnimator.start();
        return;
    L11:
        if (r02.step != r72) goto L13;
        return;
    L5:
        if (r7 >= r0.stepMax) goto L8;
        if (r7 <= r0.stepMin) goto L8;
    }

    private void updateLineSignature() {
        T r0 = this.chartData;
        if (r0 == null) goto L9;
        float r1 = this.chartWidth;
        if (r1 == 0.0f) goto L10;
        updateDates((int) ((r1 / (this.chartFullWidth * r0.oneDayPercentage)) / 6.0f));
        return;
    L10:
        return;
    }

    public void animateLegend(boolean r3) {
        moveLegend();
        if (this.animateLegentTo != r3) goto L5;
        return;
    L5:
        this.animateLegentTo = r3;
        ValueAnimator r0 = this.selectionAnimator;
        if (r0 == null) goto L8;
        r0.removeAllListeners();
        this.selectionAnimator.cancel();
    L8:
        float r02 = this.selectionA;
        if (r3 == false) goto L11;
        float r32 = 1.0f;
    L12:
        ValueAnimator r33 = createAnimator(r02, r32, this.selectionAnimatorListener).setDuration(200);
        this.selectionAnimator = r33;
        r33.addListener(this.selectorAnimatorEndListener);
        this.selectionAnimator.start();
        return;
    L11:
        r32 = 0.0f;
        goto L12
    }

    public void clearSelection() {
        this.selectedIndex = -1;
        this.legendShowing = false;
        this.animateLegentTo = false;
        this.legendSignatureView.setVisibility(8);
        this.selectionA = 0.0f;
    }

    public ValueAnimator createAnimator(float r2, float r3, ValueAnimator.AnimatorUpdateListener r4) {
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{r2, r3});
        r1.setDuration(400);
        r1.setInterpolator(INTERPOLATOR);
        r1.addUpdateListener(r4);
        return r1;
    }

    public ChartHorizontalLinesData createHorizontalLinesData(long r11, long r13, int r15) {
        return new ChartHorizontalLinesData(r11, r13, this.useMinHeight, this.chartData.yRate, r15, this.signaturePaint, this.signaturePaint2);
    }

    public LegendSignatureView createLegendView() {
        return new LegendSignatureView(getContext(), this.resourcesProvider);
    }

    public abstract L createLineViewData(ChartData.Line r1);

    public void drawBottomLine(Canvas r9) {
        if (this.chartData == null) goto L18;
        int r0 = this.transitionMode;
        float r3 = 1.0f;
        if (r0 != 2) goto L8;
        r3 = 1.0f - this.transitionParams.progress;
    L13:
        this.linePaint.setAlpha((int) (this.hintLinePaintAlpha * r3));
        this.signaturePaint.setAlpha((int) ((this.signaturePaintAlpha * 255.0f) * r3));
        this.signaturePaint2.setAlpha((int) ((this.signaturePaintAlpha * 255.0f) * r3));
        int r02 = (int) (SIGNATURE_TEXT_HEIGHT - this.signaturePaint.getTextSize());
        float r4 = (getMeasuredHeight() - this.chartBottom) - 1;
        r9.drawLine(this.chartStart, r4, this.chartEnd, r4, this.linePaint);
        if (this.useMinHeight == false) goto L16;
        return;
    L16:
        r9.drawText(MVEL.VERSION_SUB, HORIZONTAL_PADDING, r1 - r02, this.signaturePaint);
        return;
    L8:
        if (r0 != 1) goto L11;
        r3 = this.transitionParams.progress;
        goto L13
    L11:
        if (r0 != 3) goto L13;
        r3 = this.transitionParams.progress;
        goto L13
    }

    public void drawBottomSignature(Canvas r18) {
        if (this.chartData == null) goto L51;
        this.tmpN = this.bottomSignatureDate.size();
        int r1 = this.transitionMode;
        if (r1 != 2) goto L8;
        float r12 = 1.0f - this.transitionParams.progress;
    L14:
        this.tmpI = 0;
    L15:
        int r5 = this.tmpI;
        if (r5 >= this.tmpN) goto L58;
        int r52 = this.bottomSignatureDate.get(r5).alpha;
        int r6 = this.bottomSignatureDate.get(this.tmpI).step;
        if (r6 != 0) goto L20;
        r6 = 1;
    L20:
        int r7 = this.startXIndex - this.bottomSignatureOffset;
    L22:
        if ((r7 % r6) == 0) goto L24;
        r7 = r7 - 1;
        goto L22
    L24:
        int r8 = this.endXIndex - this.bottomSignatureOffset;
    L26:
        if ((r8 % r6) != 0) goto L50;
        if (r8 < (this.chartData.x.length - 1)) goto L50;
        int r9 = this.bottomSignatureOffset;
        int r72 = r7 + r9;
        int r82 = r8 + r9;
        float r92 = (this.chartFullWidth * this.pickerDelegate.pickerStart) - HORIZONTAL_PADDING;
    L31:
        if (r72 >= r82) goto L49;
        if (r72 < 0) goto L48;
        long[] r10 = this.chartData.x;
        if (r72 >= (r10.length - 1)) goto L48;
        long r11 = r10[r72];
        long r13 = r10[0];
        float r112 = (((r11 - r13) / (r10[r10.length - 1] - r13)) * this.chartFullWidth) - r92;
        float r102 = r112 - BOTTOM_SIGNATURE_OFFSET;
        if (r102 <= 0.0f) goto L48;
        float r122 = this.chartWidth;
        float r132 = HORIZONTAL_PADDING;
        if (r102 > (r122 + r132)) goto L48;
        int r14 = BOTTOM_SIGNATURE_START_ALPHA;
        if (r102 >= r14) goto L43;
        this.bottomSignaturePaint.setAlpha((int) (((r52 * (1.0f - ((r14 - r102) / r14))) * this.bottomSignaturePaintAlpha) * r12));
    L47:
        r18.drawText(this.chartData.getDayString(r72), r112, ((getMeasuredHeight() - this.chartBottom) + BOTTOM_SIGNATURE_TEXT_HEIGHT) + AndroidUtilities.dp(3.0f), this.bottomSignaturePaint);
        goto L48
    L43:
        Paint r15 = this.bottomSignaturePaint;
        if (r102 <= r122) goto L46;
        r15.setAlpha((int) (((r52 * (1.0f - ((r102 - r122) / r132))) * this.bottomSignaturePaintAlpha) * r12));
        goto L47
    L46:
        r15.setAlpha((int) ((r52 * this.bottomSignaturePaintAlpha) * r12));
    L48:
        r72 = r72 + r6;
        goto L31
    L49:
        this.tmpI++;
    L50:
        r8 = r8 + 1;
        goto L26
    L58:
        return;
    L8:
        if (r1 != 1) goto L11;
        r12 = this.transitionParams.progress;
        goto L14
    L11:
        if (r1 != 3) goto L13;
        r12 = this.transitionParams.progress;
        goto L14
    L13:
        r12 = 1.0f;
        goto L14
    }

    public abstract void drawChart(Canvas r1);

    public void drawHorizontalLines(Canvas r12, ChartHorizontalLinesData r13) {
        int r1 = r13.values.length;
        float r3 = 1.0f;
        if (r1 <= 2) goto L7;
        float r0 = (r0[1] - r0[0]) / (this.currentMaxHeight - this.currentMinHeight);
        if (r0 >= 0.1d) goto L7;
        float r02 = r0 / 0.1f;
    L8:
        int r5 = this.transitionMode;
        if (r5 != 2) goto L11;
        r3 = 1.0f - this.transitionParams.progress;
    L16:
        this.linePaint.setAlpha((int) (((r13.alpha * (this.hintLinePaintAlpha / 255.0f)) * r3) * r02));
        this.signaturePaint.setAlpha((int) (((r13.alpha * this.signaturePaintAlpha) * r3) * r02));
        this.signaturePaint2.setAlpha((int) (((r13.alpha * this.signaturePaintAlpha) * r3) * r02));
        int r03 = (getMeasuredHeight() - this.chartBottom) - SIGNATURE_TEXT_HEIGHT;
        int r32 = !this.useMinHeight ? 1 : 0;
    L17:
        if (r32 >= r1) goto L19;
        float r4 = getMeasuredHeight() - this.chartBottom;
        float r6 = r13.values[r32];
        float r7 = this.currentMinHeight;
        r12.drawRect(this.chartStart, (int) (r4 - (r03 * ((r6 - r7) / (this.currentMaxHeight - r7)))), this.chartEnd, r4 + 1, this.linePaint);
        r32 = r32 + 1;
        goto L17
    L19:
        return;
    L11:
        if (r5 != 1) goto L14;
        r3 = this.transitionParams.progress;
        goto L16
    L14:
        if (r5 != 3) goto L16;
        r3 = this.transitionParams.progress;
    L7:
        r02 = 1.0f;
        goto L8
    }

    public void drawPicker(Canvas r30) {
        if (this.chartData == null) goto L71;
        this.pickerDelegate.pickerWidth = this.pickerWidth;
        int r2 = getMeasuredHeight();
        int r3 = PICKER_PADDING;
        int r7 = r2 - r3;
        int r8 = (getMeasuredHeight() - this.pikerHeight) - r3;
        float r22 = HORIZONTAL_PADDING;
        float r32 = this.pickerWidth;
        ChartPickerDelegate r4 = this.pickerDelegate;
        int r5 = (int) ((r4.pickerStart * r32) + r22);
        int r42 = (int) ((r4.pickerEnd * r32) + r22);
        int r6 = this.transitionMode;
        if (r6 != 1) goto L10;
        TransitionParams r11 = this.transitionParams;
        int r12 = (int) ((r11.pickerStartOut * r32) + r22);
        int r33 = (int) ((r32 * r11.pickerEndOut) + r22);
        float r112 = r11.progress;
        r5 = (int) (r5 + ((r12 - r5) * (1.0f - r112)));
        r42 = (int) (r42 + ((r33 - r42) * (1.0f - r112)));
    L8:
        int r113 = r42;
        int r122 = r5;
        float r34 = 1.0f;
    L13:
        if (this.chartData != null) goto L15;
        Canvas r1 = r30;
        r1.drawRect(r22, r8, getMeasuredWidth() - r22, r7, this.unactiveBottomChartPaint);
    L53:
        SharedUiComponents r23 = this.sharedUiComponents;
        int r35 = this.pikerHeight;
        float r43 = getMeasuredWidth();
        float r52 = HORIZONTAL_PADDING;
        r1.drawBitmap(r23.getPickerMaskBitmap(r35, (int) (r43 - (2.0f * r52))), r52, (getMeasuredHeight() - PICKER_PADDING) - this.pikerHeight, this.emptyPaint);
        if (this.chartData == null) goto L70;
        this.pickerRect.set(r122, r8, r113, r7);
        this.pickerDelegate.middlePickerArea.set(this.pickerRect);
        Path r24 = this.pathTmp;
        Rect r36 = this.pickerRect;
        int r44 = r36.left;
        int r62 = r36.top;
        int r9 = DP_1;
        int r10 = DP_12;
        float r37 = r36.bottom + r9;
        int r13 = DP_8;
        r1.drawPath(RoundedRect(r24, r44, r62 - r9, r44 + r10, r37, r13, r13, true, false, false, true), this.pickerSelectorPaint);
        r1.drawPath(RoundedRect(this.pathTmp, r4 - r10, r3.top - r9, this.pickerRect.right, r3.bottom + r9, r13, r13, false, true, true, false), this.pickerSelectorPaint);
        Rect r25 = this.pickerRect;
        r1.drawRect(r25.left + r10, r25.bottom, r25.right - r10, r4 + r9, this.pickerSelectorPaint);
        Rect r14 = this.pickerRect;
        r30.drawRect(r14.left + r10, r3 - r9, r14.right - r10, r14.top, this.pickerSelectorPaint);
        Rect r15 = this.pickerRect;
        int r26 = r15.left;
        int r92 = DP_6;
        float r38 = r15.centerY() - r92;
        Rect r16 = this.pickerRect;
        r30.drawLine(r26 + r92, r38, r16.left + r92, r16.centerY() + r92, this.whiteLinePaint);
        Rect r17 = this.pickerRect;
        float r27 = r17.right - r92;
        float r39 = r17.centerY() - r92;
        Rect r18 = this.pickerRect;
        r30.drawLine(r27, r39, r18.right - r92, r18.centerY() + r92, this.whiteLinePaint);
        ChartPickerDelegate.CapturesData r28 = this.pickerDelegate.getMiddleCaptured();
        Rect r310 = this.pickerRect;
        int r45 = r310.bottom;
        int r311 = r310.top;
        int r46 = (r45 - r311) >> 1;
        int r312 = r311 + r46;
        if (r28 != null) goto L63;
        ChartPickerDelegate.CapturesData r29 = this.pickerDelegate.getLeftCaptured();
        ChartPickerDelegate.CapturesData r53 = this.pickerDelegate.getRightCaptured();
        if (r29 == null) goto L61;
        r30.drawCircle(this.pickerRect.left + DP_5, r312, (r46 * r29.aValue) - DP_2, this.ripplePaint);
    L61:
        if (r53 == null) goto L63;
        r30.drawCircle(this.pickerRect.right - DP_5, r312, (r46 * r53.aValue) - DP_2, this.ripplePaint);
    L63:
        Rect r19 = this.pickerDelegate.leftPickerArea;
        int r210 = PICKER_CAPTURE_WIDTH;
        r19.set(r122 - r210, r8, r122 + (r210 >> 1), r7);
        this.pickerDelegate.rightPickerArea.set(r113 - (r210 >> 1), r8, r113 + r210, r7);
        return;
    L70:
        return;
    L15:
        if (r6 != 0) goto L29;
        int r47 = 0;
    L18:
        if (r47 >= this.lines.size()) goto L29;
        L r54 = this.lines.get(r47);
        ValueAnimator r63 = r54.animatorIn;
        if (r63 != null) goto L22;
    L23:
        ValueAnimator r55 = r54.animatorOut;
        if (r55 == null) goto L28;
        if (r55.isRunning() == false) goto L28;
    L27:
        boolean r48 = true;
    L30:
        if (r48 == false) goto L33;
        r30.save();
        float r211 = HORIZONTAL_PADDING;
        int r56 = getMeasuredHeight();
        int r64 = PICKER_PADDING;
        r30.clipRect(r211, (r56 - r64) - this.pikerHeight, getMeasuredWidth() - r211, getMeasuredHeight() - r64);
        r30.translate(r211, (getMeasuredHeight() - r64) - this.pikerHeight);
        drawPickerChart(r30);
        r30.restore();
    L36:
        if (r48 == true) goto L49;
        int r49 = this.transitionMode;
        if (r49 != 2) goto L41;
        float r410 = HORIZONTAL_PADDING;
        float r65 = this.pickerWidth;
        TransitionParams r142 = this.transitionParams;
        float r66 = (r65 * r142.xPercentage) + r410;
        this.emptyPaint.setAlpha((int) ((1.0f - r142.progress) * 255.0f));
        r30.save();
        r30.clipRect(r410, r8, getMeasuredWidth() - r410, r7);
        r30.scale((this.transitionParams.progress * 2.0f) + 1.0f, 1.0f, r66, ((r7 - r8) + r8) >> 1);
        r30.drawBitmap(this.bottomChartBitmap, r410, (getMeasuredHeight() - PICKER_PADDING) - this.pikerHeight, this.emptyPaint);
        r30.restore();
        goto L49
    L41:
        if (r49 != 1) goto L47;
        float r313 = ((r7 - r8) + r8) >> 1;
        float r411 = HORIZONTAL_PADDING;
        float r67 = this.pickerWidth;
        TransitionParams r143 = this.transitionParams;
        float r152 = r143.xPercentage;
        float r57 = r411 + (r67 * r152);
        if (r152 > 0.5f) goto L46;
        r152 = 1.0f - r152;
    L46:
        float r68 = (r67 * r152) * r143.progress;
        r30.save();
        r30.clipRect(r57 - r68, r8, r68 + r57, r7);
        this.emptyPaint.setAlpha((int) (this.transitionParams.progress * 255.0f));
        r30.scale(this.transitionParams.progress, 1.0f, r57, r313);
        r30.drawBitmap(this.bottomChartBitmap, r411, (getMeasuredHeight() - PICKER_PADDING) - this.pikerHeight, this.emptyPaint);
        r30.restore();
        goto L49
    L47:
        this.emptyPaint.setAlpha((int) (r34 * 255.0f));
        r30.drawBitmap(this.bottomChartBitmap, HORIZONTAL_PADDING, (getMeasuredHeight() - PICKER_PADDING) - this.pikerHeight, this.emptyPaint);
    L49:
        if (this.transitionMode == 2) goto L72;
        float r212 = HORIZONTAL_PADDING;
        float r314 = r8;
        int r93 = DP_12;
        float r58 = r7;
        r30.drawRect(r212, r314, r122 + r93, r58, this.unactiveBottomChartPaint);
        r1 = r30;
        r1.drawRect(r113 - r93, r314, getMeasuredWidth() - r212, r58, this.unactiveBottomChartPaint);
        goto L53
    L72:
        return;
    L33:
        if (this.invalidatePickerChart == false) goto L36;
        this.bottomChartBitmap.eraseColor(0);
        drawPickerChart(this.bottomChartCanvas);
        this.invalidatePickerChart = false;
    L28:
        r47 = r47 + 1;
        goto L18
    L22:
        if (r63.isRunning() == true) goto L27;
    L29:
        r48 = false;
        goto L30
    L10:
        if (r6 != 3) goto L8;
        r34 = this.transitionParams.progress;
        r113 = r42;
        r122 = r5;
        goto L13
    }

    public abstract void drawPickerChart(Canvas r1);

    public void drawSelection(Canvas r12) {
        int r0 = this.selectedIndex;
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (this.legendShowing == false) goto L27;
        T r1 = this.chartData;
        if (r1 == null) goto L30;
        int r2 = (int) (this.chartActiveLineAlpha * this.selectionA);
        float r3 = this.chartWidth;
        ChartPickerDelegate r4 = this.pickerDelegate;
        float r5 = r4.pickerEnd;
        float r42 = r4.pickerStart;
        float r32 = r3 / (r5 - r42);
        float r43 = (r42 * r32) - HORIZONTAL_PADDING;
        float[] r13 = r1.xPercentage;
        if (r0 >= r13.length) goto L28;
        float r6 = (r13[r0] * r32) - r43;
        this.selectedLinePaint.setAlpha(r2);
        r12.drawLine(r6, 0.0f, r6, this.chartArea.bottom, this.selectedLinePaint);
        if (this.drawPointOnSelection == false) goto L29;
        this.tmpN = this.lines.size();
        int r122 = 0;
    L14:
        this.tmpI = r122;
        int r123 = this.tmpI;
        if (r123 >= this.tmpN) goto L31;
        L r124 = this.lines.get(r123);
        if (r124.enabled == false) goto L19;
    L21:
        float r02 = r124.line.y[this.selectedIndex];
        float r14 = this.currentMinHeight;
        float r15 = (getMeasuredHeight() - this.chartBottom) - (((r02 - r14) / (this.currentMaxHeight - r14)) * ((getMeasuredHeight() - this.chartBottom) - SIGNATURE_TEXT_HEIGHT));
        r124.selectionPaint.setAlpha((int) ((r124.alpha * 255.0f) * this.selectionA));
        this.selectionBackgroundPaint.setAlpha((int) ((r124.alpha * 255.0f) * this.selectionA));
        r12.drawPoint(r6, r15, r124.selectionPaint);
        r12.drawPoint(r6, r15, this.selectionBackgroundPaint);
    L22:
        r122 = this.tmpI + 1;
        goto L14
    L19:
        if (r124.alpha != 0.0f) goto L21;
    L31:
        return;
    L29:
        return;
    L28:
        return;
    L30:
        return;
    }

    public void drawSignaturesToHorizontalLines(Canvas r12, ChartHorizontalLinesData r13) {
        int r1 = r13.values.length;
        float r3 = 1.0f;
        if (r1 <= 2) goto L7;
        float r0 = (r0[1] - r0[0]) / (this.currentMaxHeight - this.currentMinHeight);
        if (r0 >= 0.1d) goto L7;
        float r02 = r0 / 0.1f;
    L8:
        int r5 = this.transitionMode;
        if (r5 != 2) goto L11;
        r3 = 1.0f - this.transitionParams.progress;
    L16:
        this.linePaint.setAlpha((int) (((r13.alpha * (this.hintLinePaintAlpha / 255.0f)) * r3) * r02));
        this.signaturePaint.setAlpha((int) (((r13.alpha * this.signaturePaintAlpha) * r3) * r02));
        this.signaturePaint2.setAlpha((int) (((r13.alpha * this.signaturePaintAlpha) * r3) * r02));
        int r03 = getMeasuredHeight() - this.chartBottom;
        int r32 = SIGNATURE_TEXT_HEIGHT;
        int r04 = r03 - r32;
        int r33 = (int) (r32 - this.signaturePaint.getTextSize());
        int r7 = 1 ^ (this.useMinHeight ? 1 : 0);
    L17:
        if (r7 >= r1) goto L22;
        float r2 = getMeasuredHeight() - this.chartBottom;
        float r52 = r13.values[r7];
        float r6 = this.currentMinHeight;
        int r22 = (int) (r2 - (r04 * ((r52 - r6) / (this.currentMaxHeight - r6))));
        float r8 = HORIZONTAL_PADDING;
        float r9 = r22 - r33;
        Canvas r53 = r12;
        ChartHorizontalLinesData r4 = r13;
        r4.drawText(r53, 0, r7, r8, r9, this.signaturePaint);
        if (r4.valuesStr2 == null) goto L21;
        r4.drawText(r53, 1, r7, getMeasuredWidth() - r8, r9, this.signaturePaint2);
    L21:
        r7 = r7 + 1;
        r13 = r4;
        r12 = r53;
        goto L17
    L22:
        return;
    L11:
        if (r5 != 1) goto L14;
        r3 = this.transitionParams.progress;
        goto L16
    L14:
        if (r5 != 3) goto L16;
        r3 = this.transitionParams.progress;
    L7:
        r02 = 1.0f;
        goto L8
    }

    public void fillTransitionParams(TransitionParams r1) {
    }

    public long findMaxValue(int r8, int r9) {
        int r0 = this.lines.size();
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L11;
        if (this.lines.get(r3).enabled == false) goto L10;
        long r4 = this.lines.get(r3).line.segmentTree.rMaxQ(r8, r9);
        if (r4 <= r1) goto L10;
        r1 = r4;
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        return r1;
    }

    public long findMinValue(int r8, int r9) {
        int r0 = this.lines.size();
        long r1 = LongCompanionObject.MAX_VALUE;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L11;
        if (this.lines.get(r3).enabled == false) goto L10;
        long r4 = this.lines.get(r3).line.segmentTree.rMinQ(r8, r9);
        if (r4 >= r1) goto L10;
        r1 = r4;
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        return r1;
    }

    public long getEndDate() {
        return this.chartData.x[this.endXIndex];
    }

    public float getMinDistance() {
        T r2 = this.chartData;
        if (r2 != null) goto L5;
        return 0.1f;
    L5:
        int r22 = r2.x.length;
        if (r22 >= 5) goto L9;
        return 1.0f;
    L9:
        float r1 = 5.0f / r22;
        if (r1 >= 0.1f) goto L12;
        return 0.1f;
    L12:
        return r1;
    }

    public long getSelectedDate() {
        int r0 = this.selectedIndex;
        if (r0 >= 0) goto L7;
        return -1;
    L7:
        return this.chartData.x[r0];
    }

    public long getStartDate() {
        return this.chartData.x[this.startXIndex];
    }

    public void init() {
        this.linePaint.setStrokeWidth(1.0f);
        this.selectedLinePaint.setStrokeWidth(SELECTED_LINE_WIDTH);
        TextPaint r0 = this.signaturePaint;
        float r1 = SIGNATURE_TEXT_SIZE;
        r0.setTextSize(r1);
        this.signaturePaint2.setTextSize(r1);
        this.signaturePaint2.setTextAlign(Paint.Align.RIGHT);
        this.bottomSignaturePaint.setTextSize(r1);
        this.bottomSignaturePaint.setTextAlign(Paint.Align.CENTER);
        this.selectionBackgroundPaint.setStrokeWidth(AndroidUtilities.dpf2(6.0f));
        Paint r02 = this.selectionBackgroundPaint;
        Paint.Cap r12 = Paint.Cap.ROUND;
        r02.setStrokeCap(r12);
        setLayerType(2, null);
        setWillNotDraw(false);
        LegendSignatureView r03 = createLegendView();
        this.legendSignatureView = r03;
        r03.setVisibility(8);
        this.whiteLinePaint.setColor(-1);
        this.whiteLinePaint.setStrokeWidth(AndroidUtilities.dpf2(3.0f));
        this.whiteLinePaint.setStrokeCap(r12);
        updateColors();
    }

    public void initPickerMaxHeight() {
        ArrayList<L> r0 = this.lines;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L16;
        L r3 = r0.get(r2);
        r2 = r2 + 1;
        L r32 = r3;
        boolean r4 = r32.enabled;
        if (r4 == false) goto L9;
        long r5 = r32.line.maxValue;
        if (r5 <= this.pickerMaxHeight) goto L9;
        this.pickerMaxHeight = r5;
    L9:
        if (r4 == false) goto L13;
        long r33 = r32.line.minValue;
        if (r33 >= this.pickerMinHeight) goto L13;
        this.pickerMinHeight = r33;
    L13:
        float r34 = this.pickerMaxHeight;
        float r42 = this.pickerMinHeight;
        if (r34 != r42) goto L3;
        this.pickerMaxHeight = r34 + 1.0f;
        this.pickerMinHeight = r42 - 1.0f;
        goto L3
    }

    public void moveLegend() {
        moveLegend((this.chartFullWidth * this.pickerDelegate.pickerStart) - HORIZONTAL_PADDING);
    }

    public void moveLegend(float r10) {
        T r0 = this.chartData;
        if (r0 == null) goto L23;
        int r2 = this.selectedIndex;
        if (r2 < 0) goto L24;
        long[] r1 = r0.x;
        if (r2 < r1.length) goto L9;
        return;
    L9:
        if (this.legendShowing == false) goto L26;
        this.legendSignatureView.setData(r2, r1[r2], this.lines, false, r0.yTooltipFormatter, r0.yRate);
        this.legendSignatureView.setVisibility(0);
        this.legendSignatureView.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE));
        float r02 = (this.chartData.xPercentage[this.selectedIndex] * this.chartFullWidth) - r10;
        if (r02 <= ((this.chartStart + this.chartWidth) / 2.0f)) goto L14;
        float r03 = r02 - (this.legendSignatureView.getWidth() + DP_5);
    L16:
        if (r03 >= 0.0f) goto L19;
        r03 = 0.0f;
    L21:
        this.legendSignatureView.setTranslationX(r03);
        return;
    L19:
        if ((this.legendSignatureView.getMeasuredWidth() + r03) <= getMeasuredWidth()) goto L21;
        r03 = getMeasuredWidth() - this.legendSignatureView.getMeasuredWidth();
        goto L21
    L14:
        r03 = r02 + DP_5;
        goto L16
    L26:
        return;
    L24:
        return;
    }

    public void onActionUp() {
    }

    public void onCheckChanged() {
        onPickerDataChanged(true, true, true);
        this.tmpN = this.lines.size();
        int r1 = 0;
    L3:
        this.tmpI = r1;
        int r12 = this.tmpI;
        if (r12 >= this.tmpN) goto L36;
        L r13 = this.lines.get(r12);
        if (r13.enabled == false) goto L11;
        ValueAnimator r2 = r13.animatorOut;
        if (r2 == null) goto L11;
        r2.cancel();
    L11:
        if (r13.enabled == true) goto L16;
        ValueAnimator r22 = r13.animatorIn;
        if (r22 == null) goto L16;
        r22.cancel();
    L16:
        if (r13.enabled == false) goto L26;
        if (r13.alpha == 1.0f) goto L26;
        ValueAnimator r23 = r13.animatorIn;
        if (r23 != null) goto L22;
    L24:
        ValueAnimator r24 = createAnimator(r13.alpha, 1.0f, new BaseChartView$$ExternalSyntheticLambda4(this, r13));
        r13.animatorIn = r24;
        r24.start();
        goto L26
    L22:
        if (r23.isRunning() == false) goto L24;
    L35:
        r1 = this.tmpI + 1;
    L26:
        if (r13.enabled == true) goto L35;
        if (r13.alpha == 0.0f) goto L35;
        ValueAnimator r25 = r13.animatorOut;
        if (r25 != null) goto L32;
    L34:
        ValueAnimator r26 = createAnimator(r13.alpha, 0.0f, new BaseChartView$$ExternalSyntheticLambda5(this, r13));
        r13.animatorOut = r26;
        r26.start();
        goto L35
    L32:
        if (r25.isRunning() == false) goto L34;
    L36:
        updatePickerMinMaxHeight();
        if (this.legendShowing == false) goto L46;
        LegendSignatureView r14 = this.legendSignatureView;
        int r27 = this.selectedIndex;
        T r0 = this.chartData;
        r14.setData(r27, r0.x[r27], this.lines, true, r0.yTooltipFormatter, r0.yRate);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        if (this.superDraw == false) goto L6;
        super.onDraw(r6);
        return;
    L6:
        tick();
        int r0 = r6.save();
        r6.clipRect(0.0f, this.chartArea.top, getMeasuredWidth(), this.chartArea.bottom);
        drawBottomLine(r6);
        this.tmpN = this.horizontalLines.size();
        int r1 = 0;
        this.tmpI = 0;
    L7:
        int r2 = this.tmpI;
        if (r2 >= this.tmpN) goto L10;
        drawHorizontalLines(r6, this.horizontalLines.get(r2));
        this.tmpI++;
        goto L7
    L10:
        drawChart(r6);
    L11:
        this.tmpI = r1;
        int r12 = this.tmpI;
        if (r12 >= this.tmpN) goto L14;
        drawSignaturesToHorizontalLines(r6, this.horizontalLines.get(r12));
        r1 = this.tmpI + 1;
        goto L11
    L14:
        r6.restoreToCount(r0);
        drawBottomSignature(r6);
        drawPicker(r6);
        drawSelection(r6);
        super.onDraw(r6);
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        super.onMeasure(r5, r6);
        if (this.landscape == true) goto L5;
        setMeasuredDimension(View.MeasureSpec.getSize(r5), View.MeasureSpec.getSize(r5));
    L7:
        if (getMeasuredWidth() == this.lastW) goto L9;
    L10:
        this.lastW = getMeasuredWidth();
        this.lastH = getMeasuredHeight();
        float r52 = getMeasuredWidth();
        float r62 = HORIZONTAL_PADDING;
        this.bottomChartBitmap = Bitmap.createBitmap((int) (r52 - (r62 * 2.0f)), this.pikerHeight, Bitmap.Config.ARGB_4444);
        this.bottomChartCanvas = new Canvas(this.bottomChartBitmap);
        this.sharedUiComponents.getPickerMaskBitmap(this.pikerHeight, (int) (getMeasuredWidth() - (2.0f * r62)));
        measureSizes();
        if (this.legendShowing == false) goto L13;
        moveLegend((this.chartFullWidth * this.pickerDelegate.pickerStart) - r62);
    L13:
        onPickerDataChanged(false, true, false);
    L15:
        if (Build.VERSION.SDK_INT < 29) goto L18;
        Rect r53 = this.exclusionRect;
        int r63 = getMeasuredHeight();
        int r1 = PICKER_PADDING;
        r53.set(0, r63 - ((this.pikerHeight + r1) + r1), getMeasuredWidth(), getMeasuredHeight());
        setSystemGestureExclusionRects(this.exclusionRects);
        return;
    L18:
        return;
    L9:
        if (getMeasuredHeight() == this.lastH) goto L15;
    L5:
        setMeasuredDimension(View.MeasureSpec.getSize(r5), AndroidUtilities.displaySize.y - AndroidUtilities.dp(56.0f));
        goto L7
    }

    @Override // org.telegram.ui.Charts.ChartPickerDelegate.Listener
    public void onPickerDataChanged() {
        onPickerDataChanged(true, false, false);
    }

    public void onPickerDataChanged(boolean r11, boolean r12, boolean r13) {
        if (this.chartData != null) goto L5;
        return;
    L5:
        float r0 = this.chartWidth;
        ChartPickerDelegate r1 = this.pickerDelegate;
        this.chartFullWidth = r0 / (r1.pickerEnd - r1.pickerStart);
        updateIndexes();
        if (this.useMinHeight == false) goto L9;
        long r02 = findMinValue(this.startXIndex, this.endXIndex);
    L8:
        long r5 = r02;
        setMaxMinValue(findMaxValue(this.startXIndex, this.endXIndex), r5, r11, r12, r13);
        if (this.legendShowing == false) goto L14;
        if (r12 == true) goto L14;
        animateLegend(false);
        moveLegend((this.chartFullWidth * this.pickerDelegate.pickerStart) - HORIZONTAL_PADDING);
    L14:
        invalidate();
        return;
    L9:
        r02 = 0;
        goto L8
    }

    @Override // org.telegram.ui.Charts.ChartPickerDelegate.Listener
    public void onPickerJumpTo(float r9, float r10, boolean r11) {
        T r0 = this.chartData;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r11 == false) goto L8;
        int r92 = r0.findStartIndex(Math.max(r9, 0.0f));
        int r102 = this.chartData.findEndIndex(r92, Math.min(r10, 1.0f));
        setMaxMinValue(findMaxValue(r92, r102), findMinValue(r92, r102), true, true, false);
        animateLegend(false);
        return;
    L8:
        updateIndexes();
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r14) {
        boolean r1 = false;
        if (this.chartData != null) goto L6;
        return false;
    L6:
        if (this.enabled == true) goto L9;
        this.pickerDelegate.uncapture(r14, r14.getActionIndex());
        getParent().requestDisallowInterceptTouchEvent(false);
        this.chartCaptured = false;
        return false;
    L9:
        int r0 = (int) r14.getX(r14.getActionIndex());
        int r2 = (int) r14.getY(r14.getActionIndex());
        int r3 = r14.getActionMasked();
        if (r3 == 0) goto L68;
        if (r3 == 1) goto L54;
        if (r3 != 2) goto L15;
        int r32 = r0 - this.lastX;
        int r5 = r2 - this.lastY;
        if (this.pickerDelegate.captured() == false) goto L33;
        boolean r02 = this.pickerDelegate.move(r0, r2, r14.getActionIndex());
        if (r14.getPointerCount() <= 1) goto L30;
        this.pickerDelegate.move((int) r14.getX(1), (int) r14.getY(1), 1);
    L30:
        getParent().requestDisallowInterceptTouchEvent(r02);
        return true;
    L33:
        if (this.chartCaptured == false) goto L46;
        if (this.canCaptureChartSelection == false) goto L40;
        if ((System.currentTimeMillis() - this.capturedTime) <= 200) goto L40;
    L38:
        r1 = true;
    L44:
        this.lastX = r0;
        this.lastY = r2;
        getParent().requestDisallowInterceptTouchEvent(r1);
        selectXOnChart(r0, r2);
    L52:
        return true;
    L40:
        if (Math.abs(r32) > Math.abs(r5)) goto L38;
        if (Math.abs(r5) >= this.touchSlop) goto L44;
    L46:
        if (this.chartArea.contains(this.capturedX, this.capturedY) == false) goto L52;
        int r142 = this.capturedX - r0;
        int r12 = this.capturedY - r2;
        if (Math.sqrt((r142 * r142) + (r12 * r12)) <= this.touchSlop) goto L50;
    L51:
        this.chartCaptured = true;
        selectXOnChart(r0, r2);
        goto L52
    L50:
        if ((System.currentTimeMillis() - this.capturedTime) <= 200) goto L52;
    L15:
        if (r3 == 3) goto L54;
        if (r3 == 5) goto L24;
        if (r3 == 6) goto L21;
        return false;
    L21:
        this.pickerDelegate.uncapture(r14, r14.getActionIndex());
        return true;
    L24:
        return this.pickerDelegate.capture(r0, r2, r14.getActionIndex());
    L54:
        if (this.pickerDelegate.uncapture(r14, r14.getActionIndex()) == false) goto L57;
        return true;
    L57:
        if (this.chartArea.contains(this.capturedX, this.capturedY) == true) goto L59;
    L61:
        this.pickerDelegate.uncapture();
        updateLineSignature();
        getParent().requestDisallowInterceptTouchEvent(false);
        this.chartCaptured = false;
        onActionUp();
        invalidate();
        if (this.useMinHeight == false) goto L65;
        long r03 = findMinValue(this.startXIndex, this.endXIndex);
    L66:
        setMaxMinValue(findMaxValue(this.startXIndex, this.endXIndex), r03, true, true, false);
        return true;
    L65:
        r03 = 0;
        goto L66
    L59:
        if (this.chartCaptured == true) goto L61;
        animateLegend(false);
        goto L61
    L68:
        this.capturedTime = System.currentTimeMillis();
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.pickerDelegate.capture(r0, r2, r14.getActionIndex()) == false) goto L71;
        return true;
    L71:
        this.lastX = r0;
        this.capturedX = r0;
        this.lastY = r2;
        this.capturedY = r2;
        if (this.chartArea.contains(r0, r2) == true) goto L74;
        return false;
    L74:
        if (this.selectedIndex >= 0) goto L76;
    L77:
        this.chartCaptured = true;
        selectXOnChart(r0, r2);
    L78:
        return true;
    L76:
        if (this.animateLegentTo == true) goto L78;
        goto L77
    }

    @Override // android.view.View
    public void requestLayout() {
        super.requestLayout();
    }

    public void runSmoothHaptic() {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        if (this.vibrationEffect != null) goto L7;
        this.vibrationEffect = VibrationEffect.createWaveform(new long[]{0, 2}, -1);
    L7:
        VibratorUtils.vibrateEffect(this.vibrationEffect);
    }

    public void selectDate(long r2) {
        this.selectedIndex = Arrays.binarySearch(this.chartData.x, r2);
        this.legendShowing = true;
        this.legendSignatureView.setVisibility(0);
        this.selectionA = 1.0f;
        moveLegend((this.chartFullWidth * this.pickerDelegate.pickerStart) - HORIZONTAL_PADDING);
        performHapticFeedback(3, 2);     // Catch: Exception -> L5
        return;
    }

    public void selectXOnChart(int r7, int r8) {
        int r82 = this.selectedIndex;
        T r0 = this.chartData;
        if (r0 == null) goto L30;
        float r1 = this.chartFullWidth;
        float r2 = (this.pickerDelegate.pickerStart * r1) - HORIZONTAL_PADDING;
        float r72 = (r7 + r2) / r1;
        this.selectedCoordinate = r72;
        if (r72 >= 0.0f) goto L9;
        this.selectedIndex = 0;
        this.selectedCoordinate = 0.0f;
    L16:
        int r73 = this.selectedIndex;
        int r02 = this.endXIndex;
        if (r73 <= r02) goto L19;
        this.selectedIndex = r02;
    L19:
        int r74 = this.selectedIndex;
        int r03 = this.startXIndex;
        if (r74 >= r03) goto L23;
        this.selectedIndex = r03;
    L23:
        if (r82 == this.selectedIndex) goto L29;
        this.legendShowing = true;
        animateLegend(true);
        moveLegend(r2);
        DateSelectionListener r75 = this.dateSelectionListener;
        if (r75 == null) goto L27;
        r75.onDateSelected(getSelectedDate());
    L27:
        runSmoothHaptic();
        invalidate();
        return;
    L29:
        return;
    L9:
        if (r72 <= 1.0f) goto L11;
        this.selectedIndex = r0.x.length - 1;
        this.selectedCoordinate = 1.0f;
        goto L16
    L11:
        int r04 = r0.findIndex(this.startXIndex, this.endXIndex, r72);
        this.selectedIndex = r04;
        int r12 = r04 + 1;
        float[] r3 = this.chartData.xPercentage;
        if (r12 >= r3.length) goto L16;
        if (Math.abs(this.chartData.xPercentage[this.selectedIndex + 1] - r72) >= Math.abs(r3[r04] - r72)) goto L16;
        this.selectedIndex++;
        goto L16
    }

    public boolean setData(T r14) {
        long r1 = 0;
        boolean r6 = false;
        if (this.chartData == r14) goto L23;
        invalidate();
        this.lines.clear();
        if (r14 != null) goto L7;
    L12:
        clearSelection();
        this.chartData = r14;
        if (r14 == null) goto L22;
        long r62 = r14.x[0];
        ChartPickerDelegate r63 = this.pickerDelegate;
        if (r62 != 0) goto L17;
        r63.pickerStart = 0.0f;
        r63.pickerEnd = 1.0f;
        goto L22
    L17:
        r63.minDistance = getMinDistance();
        ChartPickerDelegate r0 = this.pickerDelegate;
        float r64 = r0.pickerEnd;
        float r7 = r64 - r0.pickerStart;
        float r8 = r0.minDistance;
        if (r7 >= r8) goto L22;
        float r65 = r64 - r8;
        r0.pickerStart = r65;
        if (r65 >= 0.0f) goto L22;
        r0.pickerStart = 0.0f;
        r0.pickerEnd = 1.0f;
    L22:
        r6 = true;
        goto L23
    L7:
        if (r14.lines == null) goto L12;
        int r02 = 0;
    L10:
        if (r02 >= r14.lines.size()) goto L12;
        this.lines.add(createLineViewData(r14.lines.get(r02)));
        r02 = r02 + 1;
    L23:
        measureSizes();
        if (r14 == null) goto L36;
        updateIndexes();
        if (this.useMinHeight == false) goto L28;
        r1 = findMinValue(this.startXIndex, this.endXIndex);
    L28:
        setMaxMinValue(findMaxValue(this.startXIndex, this.endXIndex), r1, false);
        this.pickerMaxHeight = 0.0f;
        this.pickerMinHeight = 2.1474836E9f;
        initPickerMaxHeight();
        int r13 = r14.yTooltipFormatter;
        if (r13 == 1) goto L33;
        if (r13 == 2) goto L33;
        this.legendSignatureView.setSize(this.lines.size());
    L34:
        this.invalidatePickerChart = true;
        updateLineSignature();
        return r6;
    L33:
        this.legendSignatureView.setSize(this.lines.size() * 2);
        goto L34
    L36:
        ChartPickerDelegate r132 = this.pickerDelegate;
        r132.pickerStart = 0.7f;
        r132.pickerEnd = 1.0f;
        this.pickerMinHeight = 0.0f;
        this.pickerMaxHeight = 0.0f;
        this.horizontalLines.clear();
        Animator r133 = this.maxValueAnimator;
        if (r133 == null) goto L39;
        r133.cancel();
    L39:
        ValueAnimator r134 = this.alphaAnimator;
        if (r134 == null) goto L42;
        r134.removeAllListeners();
        this.alphaAnimator.cancel();
    L42:
        return r6;
    }

    public void setDateSelectionListener(DateSelectionListener r1) {
        this.dateSelectionListener = r1;
    }

    public void setHeader(ChartHeaderView r1) {
        this.chartHeaderView = r1;
    }

    public void setLandscape(boolean r1) {
        this.landscape = r1;
    }

    public void setMaxMinValue(long r15, long r17, boolean r19, boolean r20, boolean r21) {
        if (Math.abs(ChartHorizontalLinesData.lookupHeight(r15) - this.animateToMaxHeight) < this.thresholdMaxHeight) goto L7;
        if (r15 == 0) goto L7;
    L9:
        ChartHorizontalLinesData r1 = createHorizontalLinesData(r15, r17, this.chartData.yTickFormatter);
        long[] r2 = r1.values;
        long r5 = r2[r2.length - 1];
        int r3 = 0;
        long r7 = r2[0];
        if (r21 == true) goto L35;
        float r9 = this.currentMaxHeight;
        float r10 = this.currentMinHeight;
        float r12 = r5 - r7;
        float r11 = (r9 - r10) / r12;
        if (r11 <= 1.0f) goto L14;
        r11 = r12 / (r9 - r10);
    L14:
        double r92 = r11;
        if (r92 <= 0.7d) goto L18;
        float r93 = 0.1f;
    L22:
        if (r5 == this.animateToMaxHeight) goto L24;
        boolean r102 = true;
    L26:
        if (this.useMinHeight == true) goto L28;
    L30:
        if (r102 == false) goto L35;
        Animator r103 = this.maxValueAnimator;
        if (r103 == null) goto L34;
        r103.removeAllListeners();
        this.maxValueAnimator.cancel();
    L34:
        this.startFromMaxH = this.currentMaxHeight;
        this.startFromMinH = this.currentMinHeight;
        this.startFromMax = 0.0f;
        this.startFromMin = 0.0f;
        this.minMaxUpdateStep = r93;
        goto L35
    L28:
        if (r7 == this.animateToMinHeight) goto L30;
        r102 = true;
        goto L30
    L24:
        r102 = false;
        goto L26
    L18:
        if (r92 >= 0.1d) goto L20;
        r93 = 0.03f;
        goto L22
    L20:
        r93 = 0.045f;
    L35:
        float r52 = r5;
        this.animateToMaxHeight = r52;
        float r6 = r7;
        this.animateToMinHeight = r6;
        measureHeightThreshold();
        long r72 = System.currentTimeMillis();
        if ((r72 - this.lastTime) >= 320) goto L39;
        if (r20 == true) goto L39;
        return;
    L39:
        this.lastTime = r72;
        ValueAnimator r73 = this.alphaAnimator;
        if (r73 == null) goto L42;
        r73.removeAllListeners();
        this.alphaAnimator.cancel();
    L42:
        if (r19 == true) goto L45;
        this.currentMaxHeight = r52;
        this.currentMinHeight = r6;
        this.horizontalLines.clear();
        this.horizontalLines.add(r1);
        r1.alpha = 255;
        return;
    L45:
        this.horizontalLines.add(r1);
        if (r21 == false) goto L54;
        Animator r74 = this.maxValueAnimator;
        if (r74 == null) goto L50;
        r74.removeAllListeners();
        this.maxValueAnimator.cancel();
    L50:
        this.minMaxUpdateStep = 0.0f;
        AnimatorSet r75 = new AnimatorSet();
        r75.playTogether(new Animator[]{createAnimator(this.currentMaxHeight, r52, this.heightUpdateListener)});
        if (this.useMinHeight == false) goto L53;
        r75.playTogether(new Animator[]{createAnimator(this.currentMinHeight, r6, this.minHeightUpdateListener)});
    L53:
        this.maxValueAnimator = r75;
        r75.start();
    L54:
        int r4 = this.horizontalLines.size();
    L55:
        if (r3 >= r4) goto L60;
        ChartHorizontalLinesData r53 = this.horizontalLines.get(r3);
        if (r53 == r1) goto L59;
        r53.fixedAlpha = r53.alpha;
    L59:
        r3 = r3 + 1;
        goto L55
    L60:
        ValueAnimator r22 = createAnimator(0.0f, 255.0f, new BaseChartView$$ExternalSyntheticLambda2(this, r1));
        this.alphaAnimator = r22;
        r22.addListener(new AnonymousClass5(this, r1));
        this.alphaAnimator.start();
        return;
    L7:
        if (r15 != this.animateToMinHeight) goto L9;
    }

    public void tick() {
        float r0 = this.minMaxUpdateStep;
        if (r0 == 0.0f) goto L24;
        float r1 = this.currentMaxHeight;
        float r2 = this.animateToMaxHeight;
        if (r1 == r2) goto L13;
        float r12 = this.startFromMax + r0;
        this.startFromMax = r12;
        if (r12 <= 1.0f) goto L10;
        this.startFromMax = 1.0f;
        this.currentMaxHeight = r2;
    L11:
        invalidate();
        goto L13
    L10:
        float r02 = this.startFromMaxH;
        this.currentMaxHeight = r02 + ((r2 - r02) * CubicBezierInterpolator.EASE_OUT.getInterpolation(r12));
    L13:
        if (this.useMinHeight == false) goto L22;
        float r03 = this.currentMinHeight;
        float r13 = this.animateToMinHeight;
        if (r03 == r13) goto L23;
        float r04 = this.startFromMin + this.minMaxUpdateStep;
        this.startFromMin = r04;
        if (r04 <= 1.0f) goto L19;
        this.startFromMin = 1.0f;
        this.currentMinHeight = r13;
    L20:
        invalidate();
        return;
    L19:
        float r22 = this.startFromMinH;
        this.currentMinHeight = r22 + ((r13 - r22) * CubicBezierInterpolator.EASE_OUT.getInterpolation(r04));
        goto L20
    L23:
        return;
    L22:
        return;
    }

    public void updateColors() {
        TextPaint r0 = this.signaturePaint;
        if (this.useAlphaSignature == false) goto L5;
        int r1 = Theme.key_statisticChartSignatureAlpha;
    L6:
        r0.setColor(Theme.getColor(r1, this.resourcesProvider));
        TextPaint r02 = this.signaturePaint2;
        if (this.useAlphaSignature == false) goto L9;
        int r12 = Theme.key_statisticChartSignatureAlpha;
    L10:
        r02.setColor(Theme.getColor(r12, this.resourcesProvider));
        this.bottomSignaturePaint.setColor(Theme.getColor(Theme.key_statisticChartSignature, this.resourcesProvider));
        this.linePaint.setColor(Theme.getColor(Theme.key_statisticChartHintLine, this.resourcesProvider));
        this.selectedLinePaint.setColor(Theme.getColor(Theme.key_statisticChartActiveLine, this.resourcesProvider));
        this.pickerSelectorPaint.setColor(Theme.getColor(Theme.key_statisticChartActivePickerChart, this.resourcesProvider));
        this.unactiveBottomChartPaint.setColor(Theme.getColor(Theme.key_statisticChartInactivePickerChart, this.resourcesProvider));
        this.selectionBackgroundPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        this.ripplePaint.setColor(Theme.getColor(Theme.key_statisticChartRipple, this.resourcesProvider));
        this.legendSignatureView.recolor();
        this.hintLinePaintAlpha = this.linePaint.getAlpha();
        this.chartActiveLineAlpha = this.selectedLinePaint.getAlpha();
        this.signaturePaintAlpha = this.signaturePaint.getAlpha() / 255.0f;
        this.bottomSignaturePaintAlpha = this.bottomSignaturePaint.getAlpha() / 255.0f;
        ArrayList<L> r03 = this.lines;
        int r13 = r03.size();
        int r2 = 0;
    L11:
        if (r2 >= r13) goto L14;
        L r3 = r03.get(r2);
        r2 = r2 + 1;
        r3.updateColors();
        goto L11
    L14:
        if (this.legendShowing == false) goto L18;
        int r22 = this.selectedIndex;
        T r04 = this.chartData;
        long[] r14 = r04.x;
        if (r22 >= r14.length) goto L18;
        this.legendSignatureView.setData(r22, r14[r22], this.lines, false, r04.yTooltipFormatter, r04.yRate);
    L18:
        this.invalidatePickerChart = true;
        return;
    L9:
        r12 = Theme.key_statisticChartSignature;
        goto L10
    L5:
        r1 = Theme.key_statisticChartSignature;
        goto L6
    }

    public void updateIndexes() {
        T r0 = this.chartData;
        if (r0 != null) goto L5;
        return;
    L5:
        int r02 = r0.findStartIndex(Math.max(this.pickerDelegate.pickerStart, 0.0f));
        this.startXIndex = r02;
        int r03 = this.chartData.findEndIndex(r02, Math.min(this.pickerDelegate.pickerEnd, 1.0f));
        this.endXIndex = r03;
        int r1 = this.startXIndex;
        if (r03 >= r1) goto L8;
        this.endXIndex = r1;
    L8:
        ChartHeaderView r04 = this.chartHeaderView;
        if (r04 == null) goto L11;
        long[] r2 = this.chartData.x;
        r04.setDates(r2[r1], r2[this.endXIndex]);
    L11:
        updateLineSignature();
    }

    public void updatePicker(ChartData r10, long r11) {
        int r0 = r10.x.length;
        long r112 = r11 - (r11 % DurationKt.MILLIS_IN_DAY);
        long r1 = 86399999 + r112;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r3 >= r0) goto L11;
        long r7 = r10.x[r3];
        if (r112 <= r7) goto L8;
        r4 = r3;
    L8:
        if (r1 <= r7) goto L10;
        r5 = r3;
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        ChartPickerDelegate r9 = this.pickerDelegate;
        float[] r102 = r10.xPercentage;
        r9.pickerStart = r102[r4];
        r9.pickerEnd = r102[r5];
    }

    public void updatePickerMinMaxHeight() {
        if (ANIMATE_PICKER_SIZES == false) goto L38;
        ArrayList<L> r0 = this.lines;
        int r1 = r0.size();
        long r5 = LongCompanionObject.MAX_VALUE;
        int r9 = 0;
        long r7 = 0;
    L6:
        if (r9 >= r1) goto L17;
        L r10 = r0.get(r9);
        r9 = r9 + 1;
        L r102 = r10;
        boolean r11 = r102.enabled;
        if (r11 == false) goto L12;
        long r12 = r102.line.maxValue;
        if (r12 <= r7) goto L12;
        r7 = r12;
    L12:
        if (r11 == false) goto L6;
        long r103 = r102.line.minValue;
        if (r103 >= r5) goto L6;
        r5 = r103;
        goto L6
    L17:
        if (r5 == 2147483647L) goto L21;
        if (r5 == this.animatedToPickerMinHeight) goto L21;
    L24:
        this.animatedToPickerMaxHeight = r7;
        Animator r02 = this.pickerAnimator;
        if (r02 == null) goto L27;
        r02.cancel();
    L27:
        AnimatorSet r03 = new AnimatorSet();
        r03.playTogether(new Animator[]{createAnimator(this.pickerMaxHeight, this.animatedToPickerMaxHeight, this.pickerHeightUpdateListener), createAnimator(this.pickerMinHeight, this.animatedToPickerMinHeight, this.pickerMinHeightUpdateListener)});
        this.pickerAnimator = r03;
        r03.start();
        return;
    L21:
        if (r7 > 0) goto L23;
        return;
    L23:
        if (r7 != this.animatedToPickerMaxHeight) goto L24;
        return;
    }
}
