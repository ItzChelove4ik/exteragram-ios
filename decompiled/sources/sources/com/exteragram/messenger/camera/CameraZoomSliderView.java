package com.exteragram.messenger.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Locale;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes4.dex */
public abstract class CameraZoomSliderView extends View {
    private static final FloatPropertyCompat<CameraZoomSliderView> CONTROL_WIDTH = null;
    private static final PorterDuffXfermode DST_IN_XFERMODE = null;
    private static final PorterDuffXfermode DST_OVER_XFERMODE = null;
    private static final double LOG_2 = 0.0d;
    private static final TimeInterpolator MORPH_INTERPOLATOR = null;
    private static final FloatPropertyCompat<CameraZoomSliderView> SELECTOR_OFFSET = null;
    private static final PorterDuffXfermode XOR_XFERMODE = null;
    private static final TimeInterpolator ZOOM_INTERPOLATOR = null;
    private float animatedControlWidth;
    private float animatedSelectorOffset;
    private final Runnable autoCollapseRunnable;
    private final Paint backgroundPaint;
    private final RectF bubbleBounds;
    private final Paint bubblePaint;
    private final Paint bubbleTextPaint;
    private final RectF compactBounds;
    private boolean compactGestureDown;
    private final RectF compactTouchBounds;
    private final RectF controlBounds;
    private float displayNormalizationFactor;
    private float downX;
    private float downY;
    private int dragPrimarySegment;
    private boolean dragStartedFromCompact;
    private float dragTick;
    private boolean dragging;
    private final Paint edgeFadePaint;
    private boolean expanded;
    private ValueAnimator expandedAnimator;
    private float expandedProgress;
    private boolean externalZoomGesture;
    private int intervalCount;
    private int lastDescribedZoom;
    private float lastTouchX;
    private final Runnable longPressRunnable;
    private final Paint markerPaint;
    private float maxZoom;
    private float minZoom;
    private int minorTickColor;
    private boolean movedPastSlop;
    private int onSecondaryFixedColor;
    private OnZoomChangeListener onZoomChangeListener;
    private int oneXTick;
    private float pendingConfigurationSelectorX;
    private int pressedToggleIndex;
    private int primaryColor;
    private final SparseArray<String> primaryLabels;
    private int protectionBackgroundColor;
    private int[] rebuiltPrimaryTickIndices;
    private final RectF rulerBounds;
    private final Paint rulerLabelPaint;
    private float[] rulerStops;
    private final RectF rulerTouchBounds;
    private int secondaryFixedColor;
    private boolean selectedShowsStopValue;
    private int selectedToggleIndex;
    private final Paint selectedToggleTextPaint;
    private final RectF selectorBounds;
    private final Paint selectorPaint;
    private final SpringAnimation selectorSpring;
    private float stickyDistance;
    private float stickyFactor;
    private int stickyTick;
    private final Paint tickPaint;
    private float tickSpacing;
    private float[] toggleStops;
    private final Paint toggleTextPaint;
    private final int touchSlop;
    private int unselectedToggleColor;
    private VelocityTracker velocityTracker;
    private final SpringAnimation widthSpring;
    private float zoom;
    private ValueAnimator zoomAnimator;

    /* renamed from: com.exteragram.messenger.camera.CameraZoomSliderView$1, reason: invalid class name */
    public class AnonymousClass1 extends FloatPropertyCompat<CameraZoomSliderView> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(CameraZoomSliderView r1) {
            return CameraZoomSliderView.m1144$$Nest$fgetanimatedControlWidth(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(CameraZoomSliderView r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(CameraZoomSliderView r1, float r2) {
            CameraZoomSliderView.m1155$$Nest$fputanimatedControlWidth(r1, Math.max(0.0f, r2));
            r1.invalidate();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(CameraZoomSliderView r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: com.exteragram.messenger.camera.CameraZoomSliderView$2, reason: invalid class name */
    public class AnonymousClass2 extends FloatPropertyCompat<CameraZoomSliderView> {
        public AnonymousClass2(String r1) {
            super(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(CameraZoomSliderView r1) {
            return CameraZoomSliderView.m1145$$Nest$fgetanimatedSelectorOffset(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(CameraZoomSliderView r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(CameraZoomSliderView r1, float r2) {
            CameraZoomSliderView.m1156$$Nest$fputanimatedSelectorOffset(r1, r2);
            r1.invalidate();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(CameraZoomSliderView r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: com.exteragram.messenger.camera.CameraZoomSliderView$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ CameraZoomSliderView this$0;

        public AnonymousClass3(CameraZoomSliderView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraZoomSliderView.m1146$$Nest$fgetcompactGestureDown(this.this$0) == true) goto L5;
            return;
        L5:
            if (CameraZoomSliderView.m1152$$Nest$fgetmovedPastSlop(this.this$0) == false) goto L7;
            return;
        L7:
            if (CameraZoomSliderView.m1149$$Nest$fgetexpanded(this.this$0) == true) goto L13;
            CameraZoomSliderView.m1157$$Nest$fputdragStartedFromCompact(this.this$0, true);
            CameraZoomSliderView r0 = this.this$0;
            CameraZoomSliderView.m1160$$Nest$mbeginDrag(r0, CameraZoomSliderView.m1147$$Nest$fgetdownX(r0));
            this.this$0.setExpanded(true, true);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.camera.CameraZoomSliderView$4, reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ CameraZoomSliderView this$0;

        public AnonymousClass4(CameraZoomSliderView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraZoomSliderView.m1149$$Nest$fgetexpanded(this.this$0) == true) goto L5;
            return;
        L5:
            if (CameraZoomSliderView.m1148$$Nest$fgetdragging(this.this$0) == false) goto L7;
            return;
        L7:
            if (CameraZoomSliderView.m1151$$Nest$fgetexternalZoomGesture(this.this$0) == true) goto L12;
            this.this$0.setExpanded(false, true);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.camera.CameraZoomSliderView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ CameraZoomSliderView this$0;

        public AnonymousClass5(CameraZoomSliderView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (CameraZoomSliderView.m1150$$Nest$fgetexpandedAnimator(this.this$0) != r2) goto L6;
            CameraZoomSliderView.m1158$$Nest$fputexpandedAnimator(this.this$0, null);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.camera.CameraZoomSliderView$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ CameraZoomSliderView this$0;
        final /* synthetic */ boolean val$claimsToggle;
        final /* synthetic */ float val$finalTarget;
        final /* synthetic */ boolean val$fromUser;

        public AnonymousClass6(CameraZoomSliderView r1, float r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.val$finalTarget = r2;
            this.val$fromUser = r3;
            this.val$claimsToggle = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            if (this.cancelled == true) goto L8;
            if (Math.abs(CameraZoomSliderView.m1153$$Nest$fgetzoom(this.this$0) - this.val$finalTarget) <= 1.0E-4f) goto L8;
            CameraZoomSliderView.m1161$$Nest$msetZoomInternal(this.this$0, this.val$finalTarget, this.val$fromUser, !this.val$claimsToggle);
        L8:
            if (CameraZoomSliderView.m1154$$Nest$fgetzoomAnimator(this.this$0) != r6) goto L11;
            CameraZoomSliderView.m1159$$Nest$fputzoomAnimator(this.this$0, null);
            CameraZoomSliderView.m1162$$Nest$msyncSelectedToggle(this.this$0, true);
            return;
        }
    }

    public interface OnZoomChangeListener {
        void onZoomChanged(float r1);
    }

    public static final class ZoomLookupInterpolator implements TimeInterpolator {
        private static final float[] VALUES = null;

        static {
            VALUES = new float[]{0.0f, 8.0E-4f, 0.0016f, 0.0024f, 0.0032f, 0.0057f, 0.0083f, 0.0109f, 0.0134f, 0.0171f, 0.0218f, 0.0266f, 0.0313f, 0.036f, 0.0431f, 0.0506f, 0.0581f, 0.0656f, 0.0733f, 0.0835f, 0.0937f, 0.1055f, 0.1179f, 0.1316f, 0.1466f, 0.1627f, 0.181f, 0.2003f, 0.2226f, 0.2468f, 0.2743f, 0.306f, 0.3408f, 0.3852f, 0.4317f, 0.4787f, 0.5177f, 0.5541f, 0.5834f, 0.6123f, 0.6333f, 0.6542f, 0.6739f, 0.6887f, 0.7035f, 0.7183f, 0.7308f, 0.7412f, 0.7517f, 0.7621f, 0.7725f, 0.7805f, 0.7879f, 0.7953f, 0.8027f, 0.8101f, 0.8175f, 0.823f, 0.8283f, 0.8336f, 0.8388f, 0.8441f, 0.8494f, 0.8546f, 0.8592f, 0.863f, 0.8667f, 0.8705f, 0.8743f, 0.878f, 0.8818f, 0.8856f, 0.8893f, 0.8927f, 0.8953f, 0.898f, 0.9007f, 0.9034f, 0.9061f, 0.9087f, 0.9114f, 0.9141f, 0.9168f, 0.9194f, 0.9218f, 0.9236f, 0.9255f, 0.9274f, 0.9293f, 0.9312f, 0.9331f, 0.935f, 0.9368f, 0.9387f, 0.9406f, 0.9425f, 0.9444f, 0.946f, 0.9473f, 0.9486f, 0.9499f, 0.9512f, 0.9525f, 0.9538f, 0.9551f, 0.9564f, 0.9577f, 0.959f, 0.9603f, 0.9616f, 0.9629f, 0.9642f, 0.9654f, 0.9663f, 0.9672f, 0.968f, 0.9689f, 0.9697f, 0.9706f, 0.9715f, 0.9723f, 0.9732f, 0.9741f, 0.9749f, 0.9758f, 0.9766f, 0.9775f, 0.9784f, 0.9792f, 0.9801f, 0.9808f, 0.9813f, 0.9819f, 0.9824f, 0.9829f, 0.9835f, 0.984f, 0.9845f, 0.985f, 0.9856f, 0.9861f, 0.9866f, 0.9872f, 0.9877f, 0.9882f, 0.9887f, 0.9893f, 0.9898f, 0.9903f, 0.9909f, 0.9914f, 0.9917f, 0.992f, 0.9922f, 0.9925f, 0.9928f, 0.9931f, 0.9933f, 0.9936f, 0.9939f, 0.9942f, 0.9944f, 0.9947f, 0.995f, 0.9953f, 0.9955f, 0.9958f, 0.9961f, 0.9964f, 0.9966f, 0.9969f, 0.9972f, 0.9975f, 0.9977f, 0.9979f, 0.9981f, 0.9982f, 0.9983f, 0.9984f, 0.9986f, 0.9987f, 0.9988f, 0.9989f, 0.9991f, 0.9992f, 0.9993f, 0.9994f, 0.9995f, 0.9995f, 0.9996f, 0.9996f, 0.9997f, 0.9997f, 0.9997f, 0.9998f, 0.9998f, 0.9998f, 0.9999f, 0.9999f, 1.0f, 1.0f};
        }

        private ZoomLookupInterpolator() {
        }

        public /* synthetic */ ZoomLookupInterpolator(CameraZoomSliderViewIA r1) {
            this();
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r3) {
            if (r3 > 0.0f) goto L6;
            return 0.0f;
        L6:
            if (r3 < 1.0f) goto L8;
            return 1.0f;
        L8:
            int r2 = Math.min((int) (200.0f * r3), 199);
            float r32 = (r3 - (r2 * 0.005f)) / 0.005f;
            float[] r0 = VALUES;
            float r1 = r0[r2];
            return r1 + (r32 * (r0[r2 + 1] - r1));
        }
    }

    /* renamed from: $r8$lambda$-w3gVNOEoFX2D-WrdQPJ-qNF0hc, reason: not valid java name */
    public static /* synthetic */ void m1143$r8$lambda$w3gVNOEoFX2DWrdQPJqNF0hc(CameraZoomSliderView r0, boolean r1, boolean r2, ValueAnimator r3) {
        r0.lambda$animateZoomTo$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$poGd2TzEM5gKH7nPTrqqmHH_Htg(CameraZoomSliderView r0, ValueAnimator r1) {
        r0.lambda$animateExpandedProgress$0(r1);
    }

    /* renamed from: -$$Nest$fgetanimatedControlWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1144$$Nest$fgetanimatedControlWidth(CameraZoomSliderView r0) {
        return r0.animatedControlWidth;
    }

    /* renamed from: -$$Nest$fgetanimatedSelectorOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1145$$Nest$fgetanimatedSelectorOffset(CameraZoomSliderView r0) {
        return r0.animatedSelectorOffset;
    }

    /* renamed from: -$$Nest$fgetcompactGestureDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1146$$Nest$fgetcompactGestureDown(CameraZoomSliderView r0) {
        return r0.compactGestureDown;
    }

    /* renamed from: -$$Nest$fgetdownX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1147$$Nest$fgetdownX(CameraZoomSliderView r0) {
        return r0.downX;
    }

    /* renamed from: -$$Nest$fgetdragging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1148$$Nest$fgetdragging(CameraZoomSliderView r0) {
        return r0.dragging;
    }

    /* renamed from: -$$Nest$fgetexpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1149$$Nest$fgetexpanded(CameraZoomSliderView r0) {
        return r0.expanded;
    }

    /* renamed from: -$$Nest$fgetexpandedAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m1150$$Nest$fgetexpandedAnimator(CameraZoomSliderView r0) {
        return r0.expandedAnimator;
    }

    /* renamed from: -$$Nest$fgetexternalZoomGesture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1151$$Nest$fgetexternalZoomGesture(CameraZoomSliderView r0) {
        return r0.externalZoomGesture;
    }

    /* renamed from: -$$Nest$fgetmovedPastSlop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1152$$Nest$fgetmovedPastSlop(CameraZoomSliderView r0) {
        return r0.movedPastSlop;
    }

    /* renamed from: -$$Nest$fgetzoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1153$$Nest$fgetzoom(CameraZoomSliderView r0) {
        return r0.zoom;
    }

    /* renamed from: -$$Nest$fgetzoomAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m1154$$Nest$fgetzoomAnimator(CameraZoomSliderView r0) {
        return r0.zoomAnimator;
    }

    /* renamed from: -$$Nest$fputanimatedControlWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1155$$Nest$fputanimatedControlWidth(CameraZoomSliderView r0, float r1) {
        r0.animatedControlWidth = r1;
    }

    /* renamed from: -$$Nest$fputanimatedSelectorOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1156$$Nest$fputanimatedSelectorOffset(CameraZoomSliderView r0, float r1) {
        r0.animatedSelectorOffset = r1;
    }

    /* renamed from: -$$Nest$fputdragStartedFromCompact, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1157$$Nest$fputdragStartedFromCompact(CameraZoomSliderView r0, boolean r1) {
        r0.dragStartedFromCompact = r1;
    }

    /* renamed from: -$$Nest$fputexpandedAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1158$$Nest$fputexpandedAnimator(CameraZoomSliderView r0, ValueAnimator r1) {
        r0.expandedAnimator = r1;
    }

    /* renamed from: -$$Nest$fputzoomAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1159$$Nest$fputzoomAnimator(CameraZoomSliderView r0, ValueAnimator r1) {
        r0.zoomAnimator = r1;
    }

    /* renamed from: -$$Nest$mbeginDrag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1160$$Nest$mbeginDrag(CameraZoomSliderView r0, float r1) {
        r0.beginDrag(r1);
    }

    /* renamed from: -$$Nest$msetZoomInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1161$$Nest$msetZoomInternal(CameraZoomSliderView r0, float r1, boolean r2, boolean r3) {
        r0.setZoomInternal(r1, r2, r3);
    }

    /* renamed from: -$$Nest$msyncSelectedToggle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1162$$Nest$msyncSelectedToggle(CameraZoomSliderView r0, boolean r1) {
        r0.syncSelectedToggle(r1);
    }

    static {
        LOG_2 = Math.log(2.0d);
        ZOOM_INTERPOLATOR = new ZoomLookupInterpolator(null);
        MORPH_INTERPOLATOR = new CubicBezierInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
        XOR_XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        DST_OVER_XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
        DST_IN_XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        CONTROL_WIDTH = new AnonymousClass1("controlWidth");
        SELECTOR_OFFSET = new AnonymousClass2("selectorOffset");
    }

    public CameraZoomSliderView(Context r2) {
        this(r2, null);
    }

    public CameraZoomSliderView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public CameraZoomSliderView(Context r3, AttributeSet r4, int r5) {
        super(r3, r4, r5);
        this.backgroundPaint = new Paint(1);
        this.selectorPaint = new Paint(1);
        this.toggleTextPaint = new Paint(129);
        this.selectedToggleTextPaint = new Paint(129);
        this.tickPaint = new Paint(1);
        this.markerPaint = new Paint(1);
        this.rulerLabelPaint = new Paint(129);
        this.edgeFadePaint = new Paint(1);
        this.bubblePaint = new Paint(1);
        this.bubbleTextPaint = new Paint(129);
        this.controlBounds = new RectF();
        this.compactBounds = new RectF();
        this.rulerBounds = new RectF();
        this.compactTouchBounds = new RectF();
        this.rulerTouchBounds = new RectF();
        this.selectorBounds = new RectF();
        this.bubbleBounds = new RectF();
        this.primaryLabels = new SparseArray();
        this.minZoom = 0.5f;
        this.maxZoom = 30.0f;
        this.zoom = 1.0f;
        this.toggleStops = new float[]{0.5f, 1.0f, 2.0f, 5.0f};
        this.rulerStops = new float[]{0.5f, 1.0f, 2.0f, 5.0f, 10.0f, 30.0f};
        this.rebuiltPrimaryTickIndices = new int[0];
        this.oneXTick = -1;
        this.protectionBackgroundColor = -1728053248;
        this.primaryColor = -5715974;
        this.minorTickColor = -1842205;
        this.secondaryFixedColor = -2628615;
        this.onSecondaryFixedColor = -14142651;
        this.unselectedToggleColor = -1;
        this.displayNormalizationFactor = 1.0f;
        this.pendingConfigurationSelectorX = Float.NaN;
        this.pressedToggleIndex = -1;
        this.lastDescribedZoom = Integer.MIN_VALUE;
        this.stickyTick = -1;
        this.dragPrimarySegment = Integer.MIN_VALUE;
        this.longPressRunnable = new AnonymousClass3(this);
        this.autoCollapseRunnable = new AnonymousClass4(this);
        this.touchSlop = ViewConfiguration.get(r3).getScaledTouchSlop();
        this.tickSpacing = Math.max(1.0f, Math.round(AndroidUtilities.dp(8.0f)));
        this.widthSpring = createSpring(CONTROL_WIDTH, 1.0f, 3800.0f, AndroidUtilities.dp(0.1f));
        this.selectorSpring = createSpring(SELECTOR_OFFSET, 0.8f, 800.0f, 1.0f);
        configurePaints();
        rebuildScale();
        int r32 = findToggleSegment(this.zoom);
        this.selectedToggleIndex = r32;
        this.animatedSelectorOffset = getSelectorOffset(r32);
        this.animatedControlWidth = getCompactWidth();
        setClickable(true);
        setFocusable(true);
        updateAccessibilityDescription();
    }

    private void animateExpandedProgress(float r3, boolean r4) {
        ValueAnimator r0 = this.expandedAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.expandedAnimator = null;
    L5:
        if (r4 == true) goto L7;
    L11:
        this.expandedProgress = r3;
        return;
    L7:
        if (isLaidOut() == false) goto L11;
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{this.expandedProgress, r3});
        this.expandedAnimator = r32;
        r32.setDuration(217);
        r32.setInterpolator(MORPH_INTERPOLATOR);
        r32.addUpdateListener(new CameraZoomSliderView$$ExternalSyntheticLambda0(this));
        r32.addListener(new AnonymousClass5(this));
        r32.start();
    }

    private void animateSelectorTo(int r1, boolean r2) {
        float r12 = getSelectorOffset(r1);
        if (r2 == true) goto L5;
    L9:
        this.selectorSpring.cancel();
        this.animatedSelectorOffset = r12;
        invalidate();
        return;
    L5:
        if (isLaidOut() == false) goto L9;
        this.selectorSpring.animateToFinalPosition(r12);
    }

    private void animateZoomTo(float r2, boolean r3) {
        animateZoomTo(r2, r3, -1);
    }

    private void animateZoomTo(float r8, boolean r9, int r10) {
        if (r10 >= 0) goto L5;
    L7:
        boolean r2 = false;
    L8:
        if (r2 == false) goto L10;
        stopZoomAnimator();
    L11:
        float r82 = Utilities.clamp(r8, this.maxZoom, this.minZoom);
        if (r2 == false) goto L15;
        this.selectedToggleIndex = r10;
        this.selectedShowsStopValue = true;
        animateSelectorTo(r10, true);
    L15:
        if (Math.abs(r82 - this.zoom) >= 1.0E-4f) goto L18;
        setZoomInternal(r82, r9, !r2);
        return;
    L18:
        float r102 = this.zoom;
        long r3 = Math.min(500, (long) Math.rint(((Math.max(r102, r82) / Math.min(r102, r82)) * 500.0f) / 3.0f));
        ValueAnimator r103 = ValueAnimator.ofFloat(new float[]{r102, r82});
        this.zoomAnimator = r103;
        r103.setDuration(r3);
        r103.setInterpolator(ZOOM_INTERPOLATOR);
        r103.addUpdateListener(new CameraZoomSliderView$$ExternalSyntheticLambda1(this, r9, r2));
        r103.addListener(new AnonymousClass6(this, r82, r9, r2));
        r103.start();
        return;
    L10:
        cancelZoomAnimator(true);
        goto L11
    L5:
        if (r10 >= this.toggleStops.length) goto L7;
        r2 = true;
        goto L8
    }

    private void beginDrag(float r3) {
        if (stopZoomAnimator() == false) goto L5;
        syncSelectedToggle(true);
    L5:
        this.dragging = true;
        this.lastTouchX = r3;
        this.dragTick = zoomToTick(this.zoom);
        this.dragPrimarySegment = Integer.MIN_VALUE;
        this.stickyTick = -1;
        this.stickyDistance = 0.0f;
        this.stickyFactor = 0.0f;
        removeCallbacks(this.autoCollapseRunnable);
    }

    private float calculateStickiness(float r3) {
        float r2 = Math.abs(r3);
        if (r2 > 100.0f) goto L6;
        return 0.7f;
    L6:
        if (r2 < 1000.0f) goto L10;
        return 0.0f;
    L10:
        return ((1000.0f - r2) / 900.0f) * 0.7f;
    }

    private void cancelTransientSprings() {
        ValueAnimator r0 = this.expandedAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.expandedAnimator = null;
    L5:
        this.widthSpring.cancel();
        this.selectorSpring.cancel();
    }

    private void cancelZoomAnimator() {
        cancelZoomAnimator(false);
    }

    private void cancelZoomAnimator(boolean r2) {
        if (stopZoomAnimator() == false) goto L6;
        syncSelectedToggle(r2);
        return;
    }

    private float centeredChildLeft(float r1, float r2) {
        return getPaddingLeft() + ((int) ((r1 - r2) / 2.0f));
    }

    private void clearTouchState() {
        this.dragging = false;
        this.compactGestureDown = false;
        this.dragStartedFromCompact = false;
        this.movedPastSlop = false;
        this.pressedToggleIndex = -1;
        this.stickyTick = -1;
        this.stickyDistance = 0.0f;
        this.stickyFactor = 0.0f;
        this.dragPrimarySegment = Integer.MIN_VALUE;
        setPressed(false);
        requestParentIntercept(true);
    }

    private void configurePaintColors() {
        this.tickPaint.setColor(this.minorTickColor);
        this.markerPaint.setColor(this.primaryColor);
        this.rulerLabelPaint.setColor(this.primaryColor);
        this.selectorPaint.setColor(this.secondaryFixedColor);
        this.bubblePaint.setColor(this.secondaryFixedColor);
        this.bubbleTextPaint.setColor(this.onSecondaryFixedColor);
    }

    private void configurePaints() {
        Paint r0 = this.backgroundPaint;
        Paint.Style r1 = Paint.Style.FILL;
        r0.setStyle(r1);
        this.selectorPaint.setStyle(r1);
        this.bubblePaint.setStyle(r1);
        Paint r02 = this.tickPaint;
        Paint.Cap r12 = Paint.Cap.ROUND;
        r02.setStrokeCap(r12);
        this.markerPaint.setStrokeCap(r12);
        Paint r03 = this.toggleTextPaint;
        Paint.Align r13 = Paint.Align.CENTER;
        r03.setTextAlign(r13);
        this.toggleTextPaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.toggleTextPaint.setTypeface(AndroidUtilities.bold());
        this.selectedToggleTextPaint.setTextAlign(r13);
        this.selectedToggleTextPaint.setTextSize(AndroidUtilities.dp(16.0f));
        this.selectedToggleTextPaint.setTypeface(AndroidUtilities.bold());
        this.rulerLabelPaint.setTextAlign(r13);
        this.rulerLabelPaint.setTextSize(AndroidUtilities.dp(11.0f));
        this.rulerLabelPaint.setTypeface(AndroidUtilities.bold());
        this.bubbleTextPaint.setTextAlign(r13);
        this.bubbleTextPaint.setTextSize(AndroidUtilities.dp(16.0f));
        this.bubbleTextPaint.setTypeface(AndroidUtilities.bold());
        configurePaintColors();
    }

    private SpringAnimation createSpring(FloatPropertyCompat<CameraZoomSliderView> r2, float r3, float r4, float r5) {
        SpringAnimation r0 = new SpringAnimation(this, r2);
        r0.setSpring(new SpringForce().setDampingRatio(r3).setStiffness(r4));
        r0.setMinimumVisibleChange(r5);
        return r0;
    }

    private void drawBubble(Canvas r11, float r12) {
        String r0 = formatBubble(this.zoom);
        if (r0.isEmpty() == true) goto L10;
        Paint.FontMetricsInt r1 = this.bubbleTextPaint.getFontMetricsInt();
        float r3 = Math.max(AndroidUtilities.dp(40.0f), (float) Math.ceil(this.bubbleTextPaint.measureText(r0))) + (AndroidUtilities.dp(4.0f) * 2.0f);
        float r2 = getBubbleHeight();
        float r5 = AndroidUtilities.dp(8.0f);
        float r6 = this.controlBounds.centerX();
        float r22 = r2 / 2.0f;
        float r7 = (this.controlBounds.top - r5) - r22;
        float r32 = r3 / 2.0f;
        this.bubbleBounds.set(r6 - r32, r7 - r22, r6 + r32, r7 + r22);
        float r122 = Utilities.clamp01(r12);
        if (r122 > 0.001f) goto L8;
        return;
    L8:
        this.bubblePaint.setColor(this.secondaryFixedColor);
        this.bubbleTextPaint.setColor(this.onSecondaryFixedColor);
        float r33 = (this.bubbleBounds.top + AndroidUtilities.dp(6.0f)) - r1.ascent;
        int r112 = r11.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), Math.round(r122 * 255.0f));
        r11.drawRoundRect(this.bubbleBounds, r22, r22, this.bubblePaint);
        r11.drawText(r0, this.bubbleBounds.centerX(), r33, this.bubbleTextPaint);
        r11.restoreToCount(r112);
        return;
    }

    private void drawProtectionBackground(Canvas r5) {
        if (this.controlBounds.isEmpty() == false) goto L5;
        return;
    L5:
        this.backgroundPaint.setColor(this.protectionBackgroundColor);
        this.backgroundPaint.setXfermode(DST_OVER_XFERMODE);
        r5.drawRoundRect(this.controlBounds, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), this.backgroundPaint);
        this.backgroundPaint.setXfermode(null);
    }

    private void drawRuler(Canvas r20, float r21) {
        if (this.rulerBounds.isEmpty() == false) goto L5;
        return;
    L5:
        RectF r1 = this.rulerBounds;
        float r2 = r1.left;
        RectF r3 = this.controlBounds;
        int r8 = r20.saveLayer(r2, r3.top, r1.right, r3.bottom, null);
        RectF r22 = this.rulerBounds;
        float r32 = r22.left;
        RectF r4 = this.controlBounds;
        r20.clipRect(r32, r4.top, r22.right, r4.bottom);
        float r9 = this.rulerBounds.centerX();
        float r10 = zoomToTick(this.zoom);
        float r5 = this.controlBounds.bottom - AndroidUtilities.dp(22.0f);
        float r11 = this.controlBounds.bottom - AndroidUtilities.dp(23.0f);
        boolean r13 = false;
        int r23 = Math.max(0, ((int) Math.floor(r10 - ((this.rulerBounds.width() / 2.0f) / this.tickSpacing))) - 1);
        int r14 = Math.min(this.intervalCount, ((int) Math.ceil(((this.rulerBounds.width() / 2.0f) / this.tickSpacing) + r10)) + 1);
        Paint.FontMetricsInt r33 = this.rulerLabelPaint.getFontMetricsInt();
        float r12 = ((this.controlBounds.bottom - AndroidUtilities.dp(4.0f)) - (r33.descent - r33.ascent)) - r33.ascent;
    L6:
        if (r23 > r14) goto L24;
        float r42 = ((r23 - r10) * this.tickSpacing) + r9;
        String r6 = this.primaryLabels.get(r23);
        if (r6 == null) goto L11;
        boolean r17 = true;
    L12:
        Paint r34 = this.tickPaint;
        if (r17 == false) goto L15;
        int r132 = this.primaryColor;
    L16:
        r34.setColor(Theme.multAlpha(r132, r21));
        this.tickPaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        if (r17 == false) goto L19;
        float r35 = 12.0f;
    L20:
        int r18 = r23;
        r20.drawLine(r42, r5 - AndroidUtilities.dp(r35), r42, r5, this.tickPaint);
        if (r17 == false) goto L23;
        this.rulerLabelPaint.setColor(Theme.multAlpha(this.primaryColor, r21));
        r20.drawText(r6, r42, r12, this.rulerLabelPaint);
    L23:
        r23 = r18 + 1;
        r13 = false;
        goto L6
    L19:
        r35 = 6.0f;
        goto L20
    L15:
        r132 = this.minorTickColor;
        goto L16
    L11:
        r17 = r13;
        goto L12
    L24:
        this.markerPaint.setColor(Theme.multAlpha(this.primaryColor, r21));
        this.markerPaint.setStrokeWidth(AndroidUtilities.dp(4.0f));
        r20.drawLine(r9, r11 - AndroidUtilities.dp(12.0f), r9, r11, this.markerPaint);
        Paint r15 = this.edgeFadePaint;
        RectF r24 = this.rulerBounds;
        r15.setShader(new LinearGradient(r24.left, 0.0f, r24.right, 0.0f, new int[]{855638016, -16777216, -16777216, 855638016}, new float[]{0.0f, 0.33333334f, 0.6666667f, 1.0f}, Shader.TileMode.CLAMP));
        this.edgeFadePaint.setXfermode(DST_IN_XFERMODE);
        RectF r16 = this.rulerBounds;
        float r25 = r16.left;
        RectF r36 = this.controlBounds;
        r20.drawRect(r25, r36.top, r16.right, r36.bottom, this.edgeFadePaint);
        this.edgeFadePaint.setShader(null);
        this.edgeFadePaint.setXfermode(null);
        r20.restoreToCount(r8);
    }

    private void drawToggleLabels(Canvas r8, float r9, int r10) {
        int r0 = 0;
    L4:
        if (r0 >= this.toggleStops.length) goto L10;
        if (r0 != this.selectedToggleIndex) goto L8;
        Paint r1 = this.selectedToggleTextPaint;
    L9:
        r1.setColor(Theme.multAlpha(r10, r9));
        float r2 = this.compactBounds.left + ((r0 + 0.5f) * AndroidUtilities.dp(48.0f));
        Paint.FontMetricsInt r3 = r1.getFontMetricsInt();
        int r4 = r3.descent - r3.ascent;
        r8.drawText(getToggleLabel(r0), r2, (this.compactBounds.top + ((int) ((r5.height() - r4) / 2.0f))) - r3.ascent, r1);
        r0 = r0 + 1;
        goto L4
    L8:
        r1 = this.toggleTextPaint;
        goto L9
    }

    private void drawToggleRow(Canvas r8, float r9) {
        if (this.toggleStops.length != 0) goto L5;
        return;
    L5:
        if (this.compactBounds.isEmpty() == true) goto L13;
        float r0 = AndroidUtilities.dp(2.0f);
        float r1 = AndroidUtilities.dp(44.0f);
        float r2 = (this.compactBounds.left + Math.round(this.animatedSelectorOffset)) + r0;
        RectF r3 = this.selectorBounds;
        float r4 = this.compactBounds.top;
        r3.set(r2, r4 + r0, r2 + r1, (r4 + r0) + r1);
        drawToggleLabels(r8, r9, this.unselectedToggleColor);
        if (this.expanded == true) goto L14;
        this.selectorPaint.setColor(Theme.multAlpha(this.secondaryFixedColor, r9));
        this.selectorPaint.setXfermode(XOR_XFERMODE);
        r8.drawOval(this.selectorBounds, this.selectorPaint);
        this.selectorPaint.setColor(Theme.multAlpha(this.onSecondaryFixedColor, r9));
        this.selectorPaint.setXfermode(DST_OVER_XFERMODE);
        r8.drawOval(this.selectorBounds, this.selectorPaint);
        this.selectorPaint.setXfermode(null);
        return;
    L14:
        return;
    }

    private int findPrimarySegment(float r7, float r8) {
        int[] r0 = this.rebuiltPrimaryTickIndices;
        if (r0.length != 0) goto L6;
        return Integer.MIN_VALUE;
    L6:
        int r1 = 0;
        float r02 = r0[0];
        if (r7 >= r02) goto L9;
        return -1;
    L9:
        if (r7 != r02) goto L13;
        if (r8 < 0.0f) goto L13;
        return -1;
    L13:
        int[] r03 = this.rebuiltPrimaryTickIndices;
        if (r1 >= (r03.length - 1)) goto L24;
        int r3 = r1 + 1;
        float r4 = r03[r3];
        if (r8 >= 0.0f) goto L19;
        if (r7 >= r4) goto L22;
    L21:
        return r03[r1];
    L22:
        r1 = r3;
        goto L13
    L19:
        if (r7 > r4) goto L22;
    L24:
        return r03[r03.length - 1];
    }

    private int findToggleIndexAt(float r4) {
        RectF r0 = this.compactBounds;
        float r1 = r0.left;
        if (r4 >= r1) goto L5;
        return -1;
    L5:
        if (r4 <= r0.right) goto L7;
        return -1;
    L7:
        if (this.toggleStops.length != 0) goto L10;
        return -1;
    L10:
        return Math.max(0, Math.min(this.toggleStops.length - 1, (int) ((r4 - r1) / AndroidUtilities.dp(48.0f))));
    }

    private int findToggleSegment(float r6) {
        if (this.toggleStops.length != 0) goto L6;
        return -1;
    L6:
        int r0 = 1;
        int r1 = 0;
    L7:
        float[] r2 = this.toggleStops;
        if (r0 >= r2.length) goto L13;
        if (r6 < r2[r0]) goto L13;
        r1 = r0;
        r0 = r0 + 1;
    L13:
        return r1;
    }

    private void finishDrag(boolean r4) {
        this.dragging = false;
        this.compactGestureDown = false;
        this.dragStartedFromCompact = false;
        this.stickyTick = -1;
        this.stickyDistance = 0.0f;
        this.stickyFactor = 0.0f;
        this.dragPrimarySegment = Integer.MIN_VALUE;
        this.pressedToggleIndex = -1;
        setPressed(false);
        requestParentIntercept(true);
        resetAutoCollapseTimeout();
        if (r4 == false) goto L6;
        performClick();
        return;
    }

    private String formatBubble(float r2) {
        return formatZoomNumber(r2) + "×";
    }

    private String formatRuler(float r1) {
        return formatZoomNumber(r1);
    }

    private String formatToggle(float r1) {
        return formatZoomNumber(r1);
    }

    private String formatZoomNumber(float r3) {
        float r2 = normalizeDisplayZoom(r3);
        Locale r32 = Locale.getDefault();
        if ((r2 % 1.0f) == 0.0f) goto L5;
        String r22 = String.format(r32, "%.1f", new Object[]{Float.valueOf(r2)});
        if (r22.startsWith(MVEL.VERSION_SUB) == true) goto L9;
        return r22;
    L9:
        return r22.substring(1);
    L5:
        return String.format(r32, "%.0f", new Object[]{Float.valueOf(r2)});
    }

    private float getBubbleHeight() {
        Paint.FontMetricsInt r2 = this.bubbleTextPaint.getFontMetricsInt();
        return (r2.descent - r2.ascent) + (AndroidUtilities.dp(6.0f) * 2.0f);
    }

    private float getCompactWidth() {
        return AndroidUtilities.dp(48.0f) * this.toggleStops.length;
    }

    private float getCurrentXVelocity() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 != null) goto L6;
        return 0.0f;
    L6:
        r0.computeCurrentVelocity(1000);
        return this.velocityTracker.getXVelocity();
    }

    private float getExpandedBackgroundWidth() {
        return AndroidUtilities.dp(288.0f);
    }

    private float getExpandedRulerWidth() {
        return AndroidUtilities.dp(258.0f);
    }

    private float getSelectorOffset(int r2) {
        return AndroidUtilities.dp(48.0f) * Math.max(0, r2);
    }

    private String getToggleLabel(int r2) {
        if (r2 != this.selectedToggleIndex) goto L11;
        if (this.selectedShowsStopValue == false) goto L7;
        float r22 = this.toggleStops[r2];
    L9:
        return formatBubble(r22);
    L7:
        r22 = this.zoom;
        goto L9
    L11:
        return formatToggle(this.toggleStops[r2]);
    }

    private /* synthetic */ void lambda$animateExpandedProgress$0(ValueAnimator r1) {
        this.expandedProgress = Utilities.clamp01(((Float) r1.getAnimatedValue()).floatValue());
        invalidate();
    }

    private /* synthetic */ void lambda$animateZoomTo$1(boolean r1, boolean r2, ValueAnimator r3) {
        setZoomInternal(((Float) r3.getAnimatedValue()).floatValue(), r1, !r2);
    }

    private void moveDrag(float r9) {
        float r0 = r9 - this.lastTouchX;
        this.lastTouchX = r9;
        if (Math.abs(r0) >= 1.0E-4f) goto L5;
        return;
    L5:
        float r92 = this.dragTick;
        float r1 = getCurrentXVelocity();
        if (this.stickyTick < 0) goto L13;
        float r93 = Math.max(0.0f, (this.tickSpacing * this.stickyFactor) - this.stickyDistance);
        float r2 = Math.abs(r0);
        if (r2 > r93) goto L11;
        this.stickyDistance += r2;
        setDragTick(this.stickyTick);
        return;
    L11:
        r0 = Math.copySign(r2 - r93, r0);
        r92 = this.stickyTick;
        this.stickyTick = -1;
        this.stickyDistance = 0.0f;
        this.stickyFactor = 0.0f;
    L13:
        if (this.rebuiltPrimaryTickIndices.length <= 0) goto L27;
        int r22 = findPrimarySegment(r92, r0);
        int r4 = this.dragPrimarySegment;
        if (r4 != Integer.MIN_VALUE) goto L17;
        this.dragPrimarySegment = r22;
        goto L27
    L17:
        if (r22 == r4) goto L27;
        this.dragPrimarySegment = r22;
        float r12 = calculateStickiness(r1);
        if (r12 <= 0.0f) goto L27;
        int r23 = Math.max(r4, r22);
        float r42 = this.tickSpacing * r12;
        float r5 = r23;
        float r6 = Math.abs(r92 - r5) * this.tickSpacing;
        if (r6 >= r42) goto L27;
        float r43 = r42 - r6;
        float r94 = Math.abs(r0);
        if (r94 > r43) goto L26;
        this.stickyTick = r23;
        this.stickyDistance = r6 + r94;
        this.stickyFactor = r12;
        setDragTick(r23);
        return;
    L26:
        r0 = Math.copySign(r94 - r43, r0);
        r92 = r5;
    L27:
        setDragTick(Utilities.clamp(r92 - (r0 / this.tickSpacing), this.intervalCount, 0.0f));
    }

    private float normalizeDisplayZoom(float r9) {
        float r92 = r9 / this.displayNormalizationFactor;
        float r0 = r92 * 10.0f;
        if (r92 < 1.0f) goto L5;
        double r2 = r0;
        float r4 = (float) Math.floor(r2);
        if ((r4 % 5.0f) != 0.0f) goto L9;
        r0 = r4;
    L12:
        float r02 = ((float) Math.rint(r0)) / 10.0f;
        if ((r02 % 1.0f) == 0.0f) goto L19;
        if (r02 >= 8.0f) goto L19;
        return r02;
    L19:
        return (float) Math.rint(r92);
    L9:
        float r22 = (float) Math.ceil(r2);
        if ((r22 % 5.0f) != 0.0f) goto L12;
        r0 = r22;
        goto L12
    L5:
        return ((float) Math.floor(r0)) / 10.0f;
    }

    private void obtainVelocityTracker(MotionEvent r2) {
        recycleVelocityTracker();
        VelocityTracker r0 = VelocityTracker.obtain();
        this.velocityTracker = r0;
        r0.addMovement(r2);
    }

    private void rebuildScale() {
        double r0 = Math.log(this.maxZoom / this.minZoom);
        double r2 = LOG_2;
        float r02 = (float) (r0 / r2);
        float r1 = this.displayNormalizationFactor;
        if (this.minZoom < r1) goto L5;
    L11:
        this.oneXTick = -1;
        this.intervalCount = Math.max(1, Math.round(r02 * 5.0f));
    L12:
        this.primaryLabels.clear();
        float[] r03 = this.rulerStops;
        int[] r12 = new int[r03.length];
        int r22 = r03.length;
        int r3 = 0;
        int r4 = 0;
    L13:
        if (r3 >= r22) goto L27;
        float r5 = r03[r3];
        if (r5 < this.minZoom) goto L26;
        if (r5 > this.maxZoom) goto L26;
        int r6 = Math.round(zoomToTick(r5));
        this.primaryLabels.put(r6, formatRuler(r5));
        int r52 = 0;
    L20:
        if (r52 >= r4) goto L25;
        if (r12[r52] == r6) goto L26;
        r52 = r52 + 1;
        goto L20
    L25:
        r12[r4] = r6;
        r4 = r4 + 1;
    L26:
        r3 = r3 + 1;
        goto L13
    L27:
        int[] r04 = Arrays.copyOf(r12, r4);
        this.rebuiltPrimaryTickIndices = r04;
        Arrays.sort(r04);
        return;
    L5:
        if (this.maxZoom < r1) goto L11;
        this.oneXTick = Math.max(3, Math.round(((float) (Math.log(r1 / r4) / r2)) * 5.0f));
        if (this.maxZoom <= r1) goto L9;
        int r05 = Math.max(1, Math.round(((float) (Math.log(r0 / r1) / r2)) * 5.0f));
    L10:
        this.intervalCount = this.oneXTick + r05;
        goto L12
    L9:
        r05 = 0;
        goto L10
    }

    private void recycleVelocityTracker() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 == null) goto L6;
        r0.recycle();
        this.velocityTracker = null;
        return;
    }

    private void requestParentIntercept(boolean r1) {
        ViewParent r0 = getParent();
        if (r0 == null) goto L6;
        r0.requestDisallowInterceptTouchEvent(!r1);
        return;
    }

    private void resetAutoCollapseTimeout() {
        removeCallbacks(this.autoCollapseRunnable);
        AccessibilityManager r0 = (AccessibilityManager) getContext().getSystemService("accessibility");
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L9:
        if (this.expanded == true) goto L11;
        return;
    L11:
        if (this.dragging == false) goto L13;
        return;
    L13:
        if (this.externalZoomGesture == true) goto L19;
        if (r02 == true) goto L20;
        postDelayed(this.autoCollapseRunnable, 1500);
        return;
    L20:
        return;
    L19:
        return;
    L5:
        if (r0.isTouchExplorationEnabled() == false) goto L7;
        r02 = true;
        goto L9
    }

    private static float[] sanitizeStops(float[] r6, float r7, float r8) {
        int r0 = 0;
        if (r6 == null) goto L30;
        if (r6.length == 0) goto L30;
        float[] r1 = new float[r6.length];
        int r2 = r6.length;
        int r3 = 0;
    L8:
        if (r0 >= r2) goto L17;
        float r4 = r6[r0];
        if (Float.isFinite(r4) == false) goto L16;
        if (r4 < r7) goto L16;
        if (r4 > r8) goto L16;
        r1[r3] = r4;
        r3 = r3 + 1;
    L16:
        r0 = r0 + 1;
        goto L8
    L17:
        float[] r62 = Arrays.copyOf(r1, r3);
        Arrays.sort(r62);
        if (r62.length >= 2) goto L20;
        return r62;
    L20:
        int r72 = 1;
        int r82 = 1;
    L22:
        if (r72 >= r62.length) goto L28;
        if (Float.compare(r62[r72], r62[r82 - 1]) == 0) goto L26;
        r62[r82] = r62[r72];
        r82 = r82 + 1;
    L26:
        r72 = r72 + 1;
        goto L22
    L28:
        return Arrays.copyOf(r62, r82);
    L30:
        return new float[0];
    }

    private void selectToggle(int r3) {
        if (r3 < 0) goto L13;
        float[] r0 = this.toggleStops;
        if (r3 < r0.length) goto L7;
        return;
    L7:
        if (r3 == this.selectedToggleIndex) goto L9;
    L11:
        animateZoomTo(r0[r3], true, r3);
        return;
    L9:
        if (this.selectedShowsStopValue == false) goto L11;
        return;
    }

    private void setDragTick(float r3) {
        float r32 = Utilities.clamp(r3, this.intervalCount, 0.0f);
        this.dragTick = r32;
        setTickInternal(r32, true);
    }

    private void setTickInternal(float r2, boolean r3) {
        setZoomInternal(tickToZoom(r2), r3, true);
    }

    private void setZoomFromRulerTap(float r3) {
        animateZoomTo(tickToZoom(zoomToTick(this.zoom) + ((r3 - this.rulerBounds.centerX()) / this.tickSpacing)), true);
    }

    private void setZoomInternal(float r3, boolean r4, boolean r5) {
        this.zoom = Utilities.clamp(r3, this.maxZoom, this.minZoom);
        if (r5 == false) goto L5;
        syncSelectedToggle(!this.expanded);
    L5:
        updateAccessibilityDescription();
        invalidate();
        if (r4 == false) goto L11;
        OnZoomChangeListener r32 = this.onZoomChangeListener;
        if (r32 == null) goto L12;
        r32.onZoomChanged(this.zoom);
        return;
    L12:
        return;
    }

    private void settleTransientAnimationValues() {
        boolean r0 = this.expanded;
        if (r0 == false) goto L5;
        float r1 = 1.0f;
    L6:
        this.expandedProgress = r1;
        if (r0 == false) goto L9;
        float r02 = getExpandedBackgroundWidth();
    L10:
        this.animatedControlWidth = r02;
        this.animatedSelectorOffset = getSelectorOffset(this.selectedToggleIndex);
        return;
    L9:
        r02 = getCompactWidth();
        goto L10
    L5:
        r1 = 0.0f;
        goto L6
    }

    private boolean stopZoomAnimator() {
        ValueAnimator r0 = this.zoomAnimator;
        if (r0 != null) goto L6;
        return false;
    L6:
        this.zoomAnimator = null;
        r0.cancel();
        return true;
    }

    private void syncSelectedToggle(boolean r3) {
        this.selectedShowsStopValue = false;
        int r0 = findToggleSegment(this.zoom);
        if (r0 >= 0) goto L7;
        this.selectedToggleIndex = -1;
        this.animatedSelectorOffset = 0.0f;
        return;
    L7:
        if (this.selectedToggleIndex == r0) goto L10;
        this.selectedToggleIndex = r0;
        animateSelectorTo(r0, r3);
        return;
    L10:
        if (r3 == true) goto L13;
        this.selectorSpring.cancel();
        this.animatedSelectorOffset = getSelectorOffset(r0);
        return;
    }

    private float tickToZoom(float r6) {
        float r62 = Utilities.clamp(r6, this.intervalCount, 0.0f);
        if (r62 <= 0.0f) goto L5;
        int r0 = this.intervalCount;
        if (r62 >= r0) goto L9;
        int r1 = this.oneXTick;
        if (r1 >= 0) goto L13;
        float r63 = r62 / r0;
        double r12 = this.minZoom;
        double r5 = Math.exp(Math.log(this.maxZoom / r0) * r63);
    L20:
        return (float) (r12 * r5);
    L13:
        if (r62 != r1) goto L17;
        return this.displayNormalizationFactor;
    L17:
        if (r62 > r1) goto L21;
        float r64 = r62 / r1;
        r12 = this.minZoom;
        r5 = Math.exp(Math.log(this.displayNormalizationFactor / r0) * r64);
        goto L20
    L21:
        float r65 = (r62 - r1) / (r0 - r1);
        r12 = this.displayNormalizationFactor;
        r5 = Math.exp(Math.log(this.maxZoom / r0) * r65);
        goto L20
    L9:
        return this.maxZoom;
    L5:
        return this.minZoom;
    }

    private void updateAccessibilityDescription() {
        int r0 = Math.round(normalizeDisplayZoom(this.zoom) * 10.0f);
        if (r0 != this.lastDescribedZoom) goto L5;
        return;
    L5:
        this.lastDescribedZoom = r0;
        setContentDescription(formatBubble(this.zoom));
    }

    private void updateLayoutBounds() {
        float r0 = Math.max(0.0f, (getWidth() - getPaddingLeft()) - getPaddingRight());
        float r2 = Math.max(0.0f, r0 - (AndroidUtilities.dp(8.0f) * 2.0f));
        float r3 = getHeight() - getPaddingBottom();
        float r4 = r3 - AndroidUtilities.dp(64.0f);
        float r5 = AndroidUtilities.dp(48.0f) + r4;
        float r6 = Math.min(r2, Math.round(getExpandedBackgroundWidth()));
        float r7 = Math.min(r2, Math.round(getCompactWidth()));
        if (this.animatedControlWidth <= 0.0f) goto L5;
    L9:
        float r1 = Math.min(r2, Math.round(this.animatedControlWidth));
        float r62 = centeredChildLeft(r0, r1);
        this.controlBounds.set(r62, r4, r1 + r62, r5);
        float r12 = centeredChildLeft(r0, r7);
        this.compactBounds.set(r12, r4, r7 + r12, r5);
        float r13 = Math.min(r2, getExpandedRulerWidth());
        float r02 = centeredChildLeft(r0, r13);
        this.rulerBounds.set(r02, r4, r13 + r02, r3);
        this.compactTouchBounds.set(this.compactBounds);
        this.compactTouchBounds.bottom = r3;
        this.rulerTouchBounds.set(this.rulerBounds);
        return;
    L5:
        if (this.expanded == true) goto L8;
        r6 = r7;
    L8:
        this.animatedControlWidth = r6;
        goto L9
    }

    private void updateTargetControlWidth(boolean r2) {
        if (this.expanded == false) goto L5;
        float r0 = getExpandedBackgroundWidth();
    L6:
        if (r2 == true) goto L8;
    L12:
        this.widthSpring.cancel();
        this.animatedControlWidth = r0;
        invalidate();
        return;
    L8:
        if (isLaidOut() == false) goto L12;
        this.widthSpring.animateToFinalPosition(r0);
        return;
    L5:
        r0 = getCompactWidth();
        goto L6
    }

    private float zoomToTick(float r5) {
        float r52 = Utilities.clamp(r5, this.maxZoom, this.minZoom);
        if (r52 > this.minZoom) goto L7;
        return 0.0f;
    L7:
        if (r52 >= this.maxZoom) goto L9;
        int r1 = this.oneXTick;
        if (r1 < 0) goto L23;
        float r2 = this.displayNormalizationFactor;
        if (r52 != r2) goto L17;
        return r1;
    L17:
        if (r52 > r2) goto L21;
        float r53 = (float) (Math.log(r52 / r0) / Math.log(this.displayNormalizationFactor / this.minZoom));
        int r4 = this.oneXTick;
    L20:
        return r53 * r4;
    L21:
        float r54 = (float) (Math.log(r52 / r2) / Math.log(this.maxZoom / this.displayNormalizationFactor));
        return this.oneXTick + (r54 * (this.intervalCount - r0));
    L23:
        r53 = (float) (Math.log(r52 / r0) / Math.log(this.maxZoom / this.minZoom));
        r4 = this.intervalCount;
        goto L20
    L9:
        return this.intervalCount;
    }

    public void cancelZoomConfigurationTransition() {
        this.pendingConfigurationSelectorX = Float.NaN;
    }

    public abstract boolean drawPillBackground(Canvas r1, RectF r2, float r3);

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        if (this.expanded == false) goto L7;
        Class r0 = SeekBar.class;
    L6:
        return r0.getName();
    L7:
        r0 = View.class;
        goto L6
    }

    public float getMaximumZoom() {
        return this.maxZoom;
    }

    public float getMinimumZoom() {
        return this.minZoom;
    }

    public float[] getToggleStops() {
        float[] r1 = this.toggleStops;
        return Arrays.copyOf(r1, r1.length);
    }

    public float getZoom() {
        return this.zoom;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        cancelTransientSprings();
        settleTransientAnimationValues();
        if (this.expanded == true) goto L5;
        return;
    L5:
        if (this.dragging == true) goto L9;
        resetAutoCollapseTimeout();
        return;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        cancelTransientSprings();
        this.tickSpacing = Math.max(1.0f, Math.round(AndroidUtilities.dp(8.0f)));
        configurePaints();
        rebuildScale();
        settleTransientAnimationValues();
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelZoomConfigurationTransition();
        removeCallbacks(this.longPressRunnable);
        removeCallbacks(this.autoCollapseRunnable);
        animate().cancel();
        cancelZoomAnimator();
        cancelTransientSprings();
        settleTransientAnimationValues();
        clearTouchState();
        recycleVelocityTracker();
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        super.onDraw(r13);
        updateLayoutBounds();
        if (this.controlBounds.isEmpty() == false) goto L5;
    L7:
        boolean r0 = false;
    L8:
        float r1 = Utilities.clamp01(1.0f - this.expandedProgress);
        float r3 = Utilities.clamp01(this.expandedProgress);
        int r132 = r13.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
        if (r1 <= 0.001f) goto L12;
        int r12 = r13.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), Math.round(r1 * 255.0f));
        drawToggleRow(r13, 1.0f);
        r13.restoreToCount(r12);
    L12:
        if (r3 <= 0.001f) goto L14;
        int r14 = r13.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), Math.round(r3 * 255.0f));
        drawBubble(r13, 1.0f);
        drawRuler(r13, 1.0f);
        r13.restoreToCount(r14);
    L14:
        if (r0 == true) goto L16;
        drawProtectionBackground(r13);
    L16:
        r13.restoreToCount(r132);
        return;
    L5:
        if (drawPillBackground(r13, this.controlBounds, AndroidUtilities.dp(24.0f)) == false) goto L7;
        r0 = true;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        r4.setClassName(getAccessibilityClassName());
        r4.setContentDescription(formatBubble(this.zoom));
        if (this.expanded == false) goto L6;
        r4.setScrollable(true);
        r4.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(1, this.minZoom, this.maxZoom, this.zoom));
        r4.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
        r4.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
        r4.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
        return;
    L6:
        r4.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
    }

    @Override // android.view.View
    public void onMeasure(int r6, int r7) {
        float r0 = getBubbleHeight();
        float r2 = AndroidUtilities.dp(8.0f);
        setMeasuredDimension(View.resolveSize((Math.round(Math.max(getExpandedBackgroundWidth(), getCompactWidth()) + (AndroidUtilities.dp(8.0f) * 2.0f)) + getPaddingLeft()) + getPaddingRight(), r6), View.resolveSize((Math.round((r0 + (r2 * 2.0f)) + AndroidUtilities.dp(64.0f)) + getPaddingTop()) + getPaddingBottom(), r7));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (isEnabled() == true) goto L5;
    L83:
        return false;
    L5:
        if (this.toggleStops.length == 0) goto L83;
        int r0 = r8.getActionMasked();
        if (r0 != 0) goto L28;
        updateLayoutBounds();
        boolean r02 = this.expanded;
        if (r02 == false) goto L12;
        RectF r3 = this.rulerTouchBounds;
    L14:
        if (r3.contains(r8.getX(), r8.getY()) == true) goto L16;
        return false;
    L16:
        this.downX = r8.getX();
        float r32 = r8.getY();
        this.downY = r32;
        float r4 = this.downX;
        this.lastTouchX = r4;
        this.movedPastSlop = false;
        this.compactGestureDown = !r02;
        this.dragStartedFromCompact = false;
        if (r02 == false) goto L19;
    L22:
        int r33 = -1;
    L23:
        this.pressedToggleIndex = r33;
        setPressed(true);
        requestParentIntercept(false);
        obtainVelocityTracker(r8);
        if (r02 == false) goto L26;
        beginDrag(this.downX);
    L27:
        return true;
    L26:
        postDelayed(this.longPressRunnable, ViewConfiguration.getLongPressTimeout());
        goto L27
    L19:
        if (this.compactBounds.contains(r4, r32) == false) goto L22;
        r33 = findToggleIndexAt(this.downX);
        goto L23
    L12:
        r3 = this.compactTouchBounds;
        goto L14
    L28:
        VelocityTracker r34 = this.velocityTracker;
        if (r34 == null) goto L32;
        r34.addMovement(r8);
    L32:
        if (r0 == 2) goto L34;
        if (r0 != 1) goto L74;
        removeCallbacks(this.longPressRunnable);
        boolean r03 = this.dragging;
        boolean r1 = this.movedPastSlop;
        if (r03 == false) goto L61;
        if (r1 == false) goto L58;
    L60:
        finishDrag(true);
    L71:
        recycleVelocityTracker();
        return true;
    L58:
        if (this.dragStartedFromCompact == true) goto L60;
        setZoomFromRulerTap(r8.getX());
        goto L60
    L61:
        if (r1 == false) goto L63;
    L70:
        clearTouchState();
        goto L71
    L63:
        if (this.pressedToggleIndex < 0) goto L70;
        int r04 = findToggleIndexAt(r8.getX());
        if (this.compactBounds.contains(r8.getX(), r8.getY()) == true) goto L67;
    L69:
        clearTouchState();
        performClick();
        goto L71
    L67:
        if (r04 != this.pressedToggleIndex) goto L69;
        selectToggle(r04);
        goto L69
    L74:
        if (r0 != 3) goto L82;
        removeCallbacks(this.longPressRunnable);
        if (this.dragging == false) goto L78;
        finishDrag(false);
    L79:
        recycleVelocityTracker();
        return true;
    L78:
        clearTouchState();
        goto L79
    L82:
        return super.onTouchEvent(r8);
    L34:
        if (this.dragging == false) goto L43;
        float r05 = (float) Math.hypot(r8.getX() - this.downX, r8.getY() - this.downY);
        if (this.movedPastSlop == false) goto L38;
    L40:
        moveDrag(r8.getX());
        return true;
    L38:
        if (r05 <= this.touchSlop) goto L40;
        this.movedPastSlop = true;
        goto L40
    L43:
        if (this.compactGestureDown == true) goto L45;
        return true;
    L45:
        float r06 = r8.getX() - this.downX;
        float r12 = r8.getY() - this.downY;
        if (this.movedPastSlop == false) goto L48;
    L52:
        return true;
    L48:
        if (Math.hypot(r06, r12) <= this.touchSlop) goto L52;
        this.movedPastSlop = true;
        removeCallbacks(this.longPressRunnable);
        if (Math.abs(r06) < Math.abs(r12)) goto L52;
        this.dragStartedFromCompact = true;
        beginDrag(this.downX);
        setExpanded(true, true);
        moveDrag(r8.getX());
        goto L52
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r4, Bundle r5) {
        if (r4 != 16) goto L7;
        setExpanded(!this.expanded, true);
        return true;
    L7:
        if (r4 != 4096) goto L11;
        cancelZoomAnimator();
        setTickInternal(zoomToTick(this.zoom) + 1.0f, true);
        resetAutoCollapseTimeout();
        return true;
    L11:
        if (r4 != 8192) goto L15;
        cancelZoomAnimator();
        setTickInternal(zoomToTick(this.zoom) - 1.0f, true);
        resetAutoCollapseTimeout();
        return true;
    L15:
        if (r4 != AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS.getId()) goto L22;
        if (r5 == null) goto L22;
        if (r5.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE") == false) goto L22;
        cancelZoomAnimator();
        setZoomInternal(r5.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"), true, true);
        resetAutoCollapseTimeout();
        return true;
    L22:
        return super.performAccessibilityAction(r4, r5);
    }

    @Override // android.view.View
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public void prepareZoomConfigurationTransition() {
        this.pendingConfigurationSelectorX = Float.NaN;
        if (isLaidOut() == true) goto L5;
        return;
    L5:
        if (this.toggleStops.length == 0) goto L13;
        updateLayoutBounds();
        if (this.compactBounds.isEmpty() == true) goto L12;
        this.pendingConfigurationSelectorX = this.compactBounds.left + this.animatedSelectorOffset;
        return;
    L12:
        return;
    }

    public void setColors(int r1, int r2, int r3, int r4, int r5) {
        this.protectionBackgroundColor = r1;
        this.minorTickColor = r2;
        this.primaryColor = r3;
        this.secondaryFixedColor = r4;
        this.onSecondaryFixedColor = r5;
        configurePaintColors();
        invalidate();
    }

    public void setDisplayNormalizationFactor(float r3) {
        if (Float.isFinite(r3) == true) goto L5;
    L7:
        r3 = 1.0f;
    L9:
        if (Math.abs(this.displayNormalizationFactor - r3) >= 1.0E-4f) goto L11;
        return;
    L11:
        this.displayNormalizationFactor = r3;
        rebuildScale();
        syncSelectedToggle(false);
        updateAccessibilityDescription();
        invalidate();
        return;
    L5:
        if (r3 <= 0.0f) goto L7;
        goto L7
    }

    public void setExpanded(boolean r5, boolean r6) {
        if (r5 == false) goto L4;
        float r0 = 1.0f;
    L5:
        if (r5 == false) goto L7;
        float r1 = getExpandedBackgroundWidth();
    L9:
        if (this.expanded == r5) goto L11;
    L17:
        this.expanded = r5;
        removeCallbacks(this.longPressRunnable);
        removeCallbacks(this.autoCollapseRunnable);
        animateExpandedProgress(r0, r6);
        if (r6 == true) goto L20;
    L23:
        this.widthSpring.cancel();
        this.animatedControlWidth = r1;
        invalidate();
    L24:
        if (r5 == true) goto L26;
    L28:
        updateAccessibilityDescription();
        sendAccessibilityEvent(2048);
        return;
    L26:
        if (this.dragging == true) goto L28;
        resetAutoCollapseTimeout();
        goto L28
    L20:
        if (isLaidOut() == false) goto L23;
        this.widthSpring.animateToFinalPosition(r1);
        goto L24
    L11:
        if (Math.abs(this.expandedProgress - r0) >= 1.0E-4f) goto L17;
        if (Math.abs(this.animatedControlWidth - r1) >= 0.1f) goto L17;
        if (r5 == false) goto L30;
        resetAutoCollapseTimeout();
        return;
    L30:
        return;
    L7:
        r1 = getCompactWidth();
        goto L9
    L4:
        r0 = 0.0f;
        goto L5
    }

    public void setExternalZoomGestureActive(boolean r2) {
        if (this.externalZoomGesture != r2) goto L5;
        return;
    L5:
        this.externalZoomGesture = r2;
        if (r2 == false) goto L9;
        setExpanded(true, true);
        return;
    L9:
        resetAutoCollapseTimeout();
    }

    public void setOnZoomChangeListener(OnZoomChangeListener r1) {
        this.onZoomChangeListener = r1;
    }

    public void setToggleTextColor(int r1) {
        this.unselectedToggleColor = r1;
        invalidate();
    }

    public void setZoom(float r2) {
        setZoom(r2, false);
    }

    public void setZoom(float r3, boolean r4) {
        float r32 = Utilities.clamp(r3, this.maxZoom, this.minZoom);
        cancelZoomAnimator();
        if (r4 == true) goto L5;
    L8:
        setZoomInternal(r32, false, true);
        return;
    L5:
        if (isLaidOut() == false) goto L8;
        animateZoomTo(r32, false);
    }

    public void setZoomConfiguration(float r5, float r6, float[] r7, float[] r8, float r9, boolean r10) {
        if (Float.isFinite(r5) == true) goto L5;
    L24:
        f$$ExternalSyntheticBUOutline1.m("Zoom range must satisfy 0 < minZoom < maxZoom");
        return;
    L5:
        if (Float.isFinite(r6) == false) goto L24;
        if (r5 <= 0.0f) goto L24;
        if (r6 <= r5) goto L24;
        float r1 = this.pendingConfigurationSelectorX;
        this.pendingConfigurationSelectorX = Float.NaN;
        if (r10 == true) goto L13;
    L17:
        boolean r102 = false;
    L18:
        this.minZoom = r5;
        this.maxZoom = r6;
        this.toggleStops = sanitizeStops(r7, r5, r6);
        this.rulerStops = sanitizeStops(r8, r5, r6);
        this.zoom = Utilities.clamp(r9, r6, r5);
        rebuildScale();
        if (r102 == false) goto L21;
        this.selectedToggleIndex = findToggleSegment(this.zoom);
        float r52 = Math.max(0.0f, (getWidth() - getPaddingLeft()) - getPaddingRight());
        float r53 = centeredChildLeft(r52, Math.min(Math.max(0.0f, r52 - (AndroidUtilities.dp(8.0f) * 2.0f)), Math.round(getCompactWidth())));
        this.selectorSpring.cancel();
        this.animatedSelectorOffset = r1 - r53;
        animateSelectorTo(this.selectedToggleIndex, true);
        updateTargetControlWidth(true);
    L22:
        updateAccessibilityDescription();
        requestLayout();
        invalidate();
        return;
    L21:
        syncSelectedToggle(false);
        updateTargetControlWidth(false);
        goto L22
    L13:
        if (isLaidOut() == false) goto L17;
        if (Float.isFinite(r1) == false) goto L17;
        r102 = true;
        goto L18
    }
}
