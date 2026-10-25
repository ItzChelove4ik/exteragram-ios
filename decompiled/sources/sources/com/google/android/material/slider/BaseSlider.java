package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.EncodedValueReader$$ExternalSyntheticBUOutline0;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.WearAuthSheet$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends BaseOnChangeListener<S>, T extends BaseOnSliderTouchListener<S>> extends View {
    private static final int DEFAULT_LABEL_ANIMATION_ENTER_DURATION = 83;
    private static final int DEFAULT_LABEL_ANIMATION_EXIT_DURATION = 117;
    static final int DEF_STYLE_RES = 0;
    private static final String EXCEPTION_ILLEGAL_CONTINUOUS_MODE_TICK_COUNT = "The continuousModeTickCount(%s) must be greater than or equal to 0";
    private static final String EXCEPTION_ILLEGAL_DISCRETE_VALUE = "Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION = "minSeparation(%s) must be greater or equal to 0";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE = "minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT = "minSeparation(%s) cannot be set as a dimension when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_STEP_SIZE = "The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range";
    private static final String EXCEPTION_ILLEGAL_VALUE = "Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_FROM = "valueFrom(%s) must be smaller than valueTo(%s)";
    private static final int HALO_ALPHA = 63;
    private static final int LABEL_ANIMATION_ENTER_DURATION_ATTR = 0;
    private static final int LABEL_ANIMATION_ENTER_EASING_ATTR = 0;
    private static final int LABEL_ANIMATION_EXIT_DURATION_ATTR = 0;
    private static final int LABEL_ANIMATION_EXIT_EASING_ATTR = 0;
    private static final float LEFT_LABEL_PIVOT_X = 1.2f;
    private static final float LEFT_LABEL_PIVOT_Y = 0.5f;
    private static final int MAX_TIMEOUT_TOOLTIP_WITH_ACCESSIBILITY = 120000;
    private static final int MIN_TIMEOUT_TOOLTIP_WITH_ACCESSIBILITY = 10000;
    private static final float RIGHT_LABEL_PIVOT_X = -0.2f;
    private static final float RIGHT_LABEL_PIVOT_Y = 0.5f;
    private static final String TAG = "BaseSlider";
    private static final double THRESHOLD = 1.0E-4d;
    private static final float THUMB_WIDTH_PRESSED_RATIO = 0.5f;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    private static final float TOP_LABEL_PIVOT_X = 0.5f;
    private static final float TOP_LABEL_PIVOT_Y = 1.2f;
    private static final float TOUCH_SLOP_RATIO = 0.8f;
    private static final int TRACK_CORNER_SIZE_UNSET = -1;
    static final int UNIT_PX = 0;
    static final int UNIT_VALUE = 1;
    private static final String WARNING_FLOATING_POINT_ERROR = "Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.";
    private static final String WARNING_PARSE_ERROR = "Error parsing value(%s), valueFrom(%s), and valueTo(%s) into a float.";
    private BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender;
    private final AccessibilityHelper accessibilityHelper;
    private final AccessibilityManager accessibilityManager;
    private int accessibilityMinTouchTargetSize;
    private int activeThumbIdx;
    private final Paint activeTicksPaint;
    private final Paint activeTrackPaint;
    private final RectF activeTrackRect;
    private boolean centered;
    private final List<L> changeListeners;
    private int continuousModeTickCount;
    private final RectF cornerRect;
    private Drawable customThumbDrawable;
    private List<Drawable> customThumbDrawablesForValues;
    private final List<MaterialShapeDrawable> defaultThumbDrawables;
    private int defaultThumbHeight;
    private int defaultThumbRadius;
    private int defaultThumbTrackGapSize;
    private int defaultThumbWidth;
    private int defaultTickActiveRadius;
    private int defaultTickInactiveRadius;
    private int defaultTrackThickness;
    private boolean dirtyConfig;
    List<Rect> exclusionRects;
    private int focusedThumbIdx;
    private boolean forceDrawCompatHalo;
    private LabelFormatter formatter;
    private ColorStateList haloColor;
    private final Paint haloPaint;
    private int haloRadius;
    private final Rect iconRect;
    private final RectF iconRectF;
    private final Paint inactiveTicksPaint;
    private final RectF inactiveTrackLeftRect;
    private final Paint inactiveTrackPaint;
    private final RectF inactiveTrackRightRect;
    private boolean isLongPress;
    private int labelBehavior;
    private int labelPadding;
    private final Rect labelRect;
    private int labelStyle;
    private final List<TooltipDrawable> labels;
    private boolean labelsAreAnimatedIn;
    private ValueAnimator labelsInAnimator;
    private ValueAnimator labelsOutAnimator;
    private MotionEvent lastEvent;
    private int minTickSpacing;
    private int minTouchTargetSize;
    private int minTrackSidePadding;
    private int minWidgetThickness;
    private final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    private List<Float> previousDownTouchEventValues;
    private final Runnable resetActiveThumbIndex;
    private final Matrix rotationMatrix;
    private final int scaledTouchSlop;
    private int separationUnit;
    private float stepSize;
    private final Paint stopIndicatorPaint;
    private boolean thisAndAncestorsVisible;
    private float thumbElevation;
    private int thumbHeight;
    private final int thumbHeightDecreaseFocusRing;
    private boolean thumbIsPressed;
    private final Paint thumbPaint;
    private ColorStateList thumbStrokeColor;
    private float thumbStrokeWidth;
    private ColorStateList thumbTintList;
    private int thumbTrackGapSize;
    private int thumbWidth;
    private int tickActiveRadius;
    private ColorStateList tickColorActive;
    private ColorStateList tickColorInactive;
    private int tickInactiveRadius;
    private int tickVisibilityMode;
    private float[] ticksCoordinates;
    private final int tooltipTimeoutMillis;
    private float touchDownAxis1;
    private float touchDownAxis2;
    private final List<T> touchListeners;
    private float touchPosition;
    private ColorStateList trackColorActive;
    private ColorStateList trackColorInactive;
    private int trackCornerSize;
    private ColorStateList trackIconActiveColor;
    private Drawable trackIconActiveEnd;
    private boolean trackIconActiveEndMutated;
    private Drawable trackIconActiveStart;
    private boolean trackIconActiveStartMutated;
    private ColorStateList trackIconInactiveColor;
    private Drawable trackIconInactiveEnd;
    private boolean trackIconInactiveEndMutated;
    private Drawable trackIconInactiveStart;
    private boolean trackIconInactiveStartMutated;
    private int trackIconPadding;
    private int trackIconSize;
    private int trackInsideCornerSize;
    private final Path trackPath;
    private int trackSidePadding;
    private int trackStopIndicatorSize;
    private int trackThickness;
    private int trackWidth;
    private float valueFrom;
    private float valueTo;
    private ArrayList<Float> values;
    private final Rect viewRect;
    private int widgetOrientation;
    private int widgetThickness;

    /* renamed from: com.google.android.material.slider.BaseSlider$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseSlider this$0;

        public AnonymousClass1(BaseSlider r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            ViewOverlay r22 = BaseSlider.access$000(this.this$0);
            if (r22 == null) goto L9;
            Iterator r1 = BaseSlider.access$100(this.this$0).iterator();
        L7:
            if (r1.hasNext() == false) goto L11;
            r22.remove((TooltipDrawable) r1.next());
            goto L7
        L11:
            return;
        }
    }

    public class AccessibilityEventSender implements Runnable {
        final /* synthetic */ BaseSlider this$0;
        int virtualViewId;

        private AccessibilityEventSender(BaseSlider r1) {
            this.this$0 = r1;
            this.virtualViewId = -1;
        }

        public /* synthetic */ AccessibilityEventSender(BaseSlider r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.access$300(this.this$0).sendEventForVirtualView(this.virtualViewId, 4);
        }

        public void setVirtualViewId(int r1) {
            this.virtualViewId = r1;
        }
    }

    public static class AccessibilityHelper extends ExploreByTouchHelper {
        private final BaseSlider<?, ?, ?> slider;
        final Rect virtualViewBounds;

        public AccessibilityHelper(BaseSlider<?, ?, ?> r2) {
            super(r2);
            this.virtualViewBounds = new Rect();
            this.slider = r2;
        }

        private String startOrEndDescription(int r2) {
            if (r2 == (this.slider.getValues().size() - 1)) goto L5;
            if (r2 == 0) goto L8;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            return this.slider.getContext().getString(R.string.material_slider_range_start);
        L5:
            return this.slider.getContext().getString(R.string.material_slider_range_end);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float r5, float r6) {
            int r0 = 0;
        L4:
            if (r0 >= this.slider.getValues().size()) goto L9;
            this.slider.updateBoundsForVirtualViewId(r0, this.virtualViewBounds);
            if (this.virtualViewBounds.contains((int) r5, (int) r6) == true) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r0;
        L9:
            return -1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.slider.getValues().size()) goto L6;
            r3.add(Integer.valueOf(r0));
            r0 = r0 + 1;
            goto L4
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int r5, int r6, Bundle r7) {
            if (this.slider.isEnabled() == true) goto L6;
            return false;
        L6:
            if (r6 == 4096) goto L20;
            if (r6 == 8192) goto L20;
            if (r6 == 16908349) goto L11;
            return false;
        L11:
            if (r7 != null) goto L13;
        L19:
            return false;
        L13:
            if (r7.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE") == false) goto L19;
            float r62 = r7.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
            if (BaseSlider.access$700(this.slider, r5, r62) == false) goto L19;
            BaseSlider.access$800(this.slider);
            this.slider.postInvalidate();
            return true;
        L20:
            float r72 = BaseSlider.access$900(this.slider, 20);
            if (r6 != 8192) goto L24;
            r72 = -r72;
        L24:
            if (this.slider.isRtl() == false) goto L26;
            r72 = -r72;
        L26:
            float r63 = MathUtils.clamp(this.slider.getValues().get(r5).floatValue() + r72, this.slider.getValueFrom(), this.slider.getValueTo());
            if (BaseSlider.access$700(this.slider, r5, r63) == false) goto L30;
            this.slider.setActiveThumbIndex(r5);
            this.slider.scheduleTooltipTimeout();
            BaseSlider.access$800(this.slider);
            this.slider.postInvalidate();
            return true;
        L30:
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int r9, AccessibilityNodeInfoCompat r10) {
            r10.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
            List<Float> r0 = this.slider.getValues();
            Float r1 = r0.get(r9);
            float r2 = r1.floatValue();
            float r3 = this.slider.getValueFrom();
            float r4 = this.slider.getValueTo();
            if (this.slider.isEnabled() == true) goto L5;
        L10:
            NumberFormat r5 = NumberFormat.getNumberInstance();
            r5.setMaximumFractionDigits(2);
            r3 = r5.parse(r5.format(r3)).floatValue();     // Catch: ParseException -> L13
            r4 = r5.parse(r5.format(r4)).floatValue();     // Catch: ParseException -> L13
            r2 = r5.parse(r5.format(r2)).floatValue();     // Catch: ParseException -> L13
        L14:
            r10.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, r3, r4, r2));
            r10.setClassName(SeekBar.class.getName());
            StringBuilder r32 = new StringBuilder();
            if (this.slider.getContentDescription() == null) goto L17;
            r32.append(this.slider.getContentDescription());
            r32.append(",");
        L17:
            String r22 = BaseSlider.access$600(this.slider, r2);
            String r42 = this.slider.getContext().getString(R.string.material_slider_value);
            if (r0.size() <= 1) goto L20;
            r42 = startOrEndDescription(r9);
        L20:
            CharSequence r02 = ViewCompat.getStateDescription(this.slider);
            if (TextUtils.isEmpty(r02) == true) goto L23;
            r10.setStateDescription(r02);
        L24:
            r10.setContentDescription(r32.toString());
            this.slider.updateBoundsForVirtualViewId(r9, this.virtualViewBounds);
            r10.setBoundsInParent(this.virtualViewBounds);
            return;
        L23:
            r32.append(String.format(Locale.getDefault(), "%s, %s", new Object[]{r42, r22}));
        L13:
            Log.w(BaseSlider.access$500(), String.format(BaseSlider.WARNING_PARSE_ERROR, new Object[]{r1, Float.valueOf(r3), Float.valueOf(r4)}));
            goto L14
        L5:
            if (r2 <= r3) goto L8;
            r10.addAction(8192);
        L8:
            if (r2 >= r4) goto L10;
            r10.addAction(4096);
            goto L10
        }
    }

    public enum FullCornerDirection extends Enum<FullCornerDirection> {
        private static final /* synthetic */ FullCornerDirection[] $VALUES = null;
        public static final FullCornerDirection BOTH = null;
        public static final FullCornerDirection LEFT = null;
        public static final FullCornerDirection NONE = null;
        public static final FullCornerDirection RIGHT = null;

        private static /* synthetic */ FullCornerDirection[] $values() {
            return new FullCornerDirection[]{BOTH, LEFT, RIGHT, NONE};
        }

        static {
            BOTH = new FullCornerDirection("BOTH", 0);
            LEFT = new FullCornerDirection("LEFT", 1);
            RIGHT = new FullCornerDirection("RIGHT", 2);
            NONE = new FullCornerDirection("NONE", 3);
            $VALUES = $values();
        }

        FullCornerDirection(String r1, int r2) {
        }

        public static FullCornerDirection valueOf(String r1) {
            return (FullCornerDirection) Enum.valueOf(FullCornerDirection.class, r1);
        }

        public static FullCornerDirection[] values() {
            return (FullCornerDirection[]) $VALUES.clone();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = null;
        boolean hasFocus;
        float stepSize;
        float valueFrom;
        float valueTo;
        ArrayList<Float> values;

        /* renamed from: com.google.android.material.slider.BaseSlider$SliderState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SliderState> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SliderState createFromParcel(Parcel r2) {
                return new SliderState(r2, null);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SliderState createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SliderState[] newArray(int r1) {
                return new SliderState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SliderState[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        private SliderState(Parcel r3) {
            super(r3);
            this.valueFrom = r3.readFloat();
            this.valueTo = r3.readFloat();
            ArrayList<Float> r0 = new ArrayList();
            this.values = r0;
            r3.readList(r0, Float.class.getClassLoader());
            this.stepSize = r3.readFloat();
            this.hasFocus = r3.createBooleanArray()[0];
        }

        public /* synthetic */ SliderState(Parcel r1, AnonymousClass1 r2) {
            this(r1);
        }

        public SliderState(Parcelable r1) {
            super(r1);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r2, int r3) {
            super.writeToParcel(r2, r3);
            r2.writeFloat(this.valueFrom);
            r2.writeFloat(this.valueTo);
            r2.writeList(this.values);
            r2.writeFloat(this.stepSize);
            r2.writeBooleanArray(new boolean[]{this.hasFocus});
        }
    }

    /* renamed from: $r8$lambda$4h7OGYoK8jQLbX-Z2EYTAM52aVc, reason: not valid java name */
    public static /* synthetic */ void m1901$r8$lambda$4h7OGYoK8jQLbXZ2EYTAM52aVc(BaseSlider r1) {
        r1.setActiveThumbIndex(-1);
        r1.invalidate();
    }

    /* renamed from: $r8$lambda$La4jj88vosfjEVZcojIkrE-8EeQ, reason: not valid java name */
    public static /* synthetic */ void m1902$r8$lambda$La4jj88vosfjEVZcojIkrE8EeQ(BaseSlider r2, ValueAnimator r3) {
        r2.getClass();
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        Iterator<TooltipDrawable> r0 = r2.labels.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().setRevealFraction(r32);
        goto L4
    L6:
        r2.postInvalidateOnAnimation();
    }

    public static /* synthetic */ void $r8$lambda$WXiNVeXFM7RTh57Z9Tr5jBbN9l4(BaseSlider r0) {
        r0.updateLabels();
    }

    /* renamed from: $r8$lambda$wmOcrqvwfn0H-XDxTA4SeazLUmI, reason: not valid java name */
    public static /* synthetic */ void m1903$r8$lambda$wmOcrqvwfn0HXDxTA4SeazLUmI(BaseSlider r0) {
        r0.updateLabels();
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_Slider;
        LABEL_ANIMATION_ENTER_DURATION_ATTR = R.attr.motionDurationMedium4;
        LABEL_ANIMATION_EXIT_DURATION_ATTR = R.attr.motionDurationShort3;
        LABEL_ANIMATION_ENTER_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
        LABEL_ANIMATION_EXIT_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;
    }

    public BaseSlider(Context r2) {
        this(r2, null);
    }

    public BaseSlider(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.sliderStyle);
    }

    public BaseSlider(Context r6, AttributeSet r7, int r8) {
        super(MaterialThemeOverlay.wrap(r6, r7, r8, DEF_STYLE_RES), r7, r8);
        this.labels = new ArrayList();
        this.changeListeners = new ArrayList();
        this.touchListeners = new ArrayList();
        this.labelsAreAnimatedIn = false;
        this.defaultThumbWidth = -1;
        this.defaultThumbTrackGapSize = -1;
        this.defaultThumbHeight = -1;
        this.centered = false;
        this.trackIconActiveStartMutated = false;
        this.trackIconActiveEndMutated = false;
        this.trackIconInactiveStartMutated = false;
        this.trackIconInactiveEndMutated = false;
        this.viewRect = new Rect();
        this.exclusionRects = new ArrayList();
        this.previousDownTouchEventValues = new ArrayList();
        this.thumbIsPressed = false;
        this.values = new ArrayList();
        this.activeThumbIdx = -1;
        this.focusedThumbIdx = -1;
        this.stepSize = 0.0f;
        this.continuousModeTickCount = 0;
        this.isLongPress = false;
        this.trackPath = new Path();
        this.activeTrackRect = new RectF();
        this.inactiveTrackLeftRect = new RectF();
        this.inactiveTrackRightRect = new RectF();
        this.cornerRect = new RectF();
        this.labelRect = new Rect();
        this.iconRectF = new RectF();
        this.iconRect = new Rect();
        this.rotationMatrix = new Matrix();
        this.defaultThumbDrawables = new ArrayList();
        this.customThumbDrawablesForValues = Collections.EMPTY_LIST;
        this.separationUnit = 0;
        this.onScrollChangedListener = new BaseSlider$$ExternalSyntheticLambda1(this);
        this.onGlobalLayoutListener = new BaseSlider$$ExternalSyntheticLambda2(this);
        this.resetActiveThumbIndex = new BaseSlider$$ExternalSyntheticLambda3(this);
        Context r62 = getContext();
        this.thisAndAncestorsVisible = isShown();
        this.inactiveTrackPaint = new Paint();
        this.activeTrackPaint = new Paint();
        Paint r0 = new Paint(1);
        this.thumbPaint = r0;
        Paint.Style r2 = Paint.Style.FILL;
        r0.setStyle(r2);
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r02 = new Paint(1);
        this.haloPaint = r02;
        r02.setStyle(r2);
        Paint r03 = new Paint();
        this.inactiveTicksPaint = r03;
        Paint.Style r3 = Paint.Style.STROKE;
        r03.setStyle(r3);
        Paint.Cap r4 = Paint.Cap.ROUND;
        r03.setStrokeCap(r4);
        Paint r04 = new Paint();
        this.activeTicksPaint = r04;
        r04.setStyle(r3);
        r04.setStrokeCap(r4);
        Paint r05 = new Paint();
        this.stopIndicatorPaint = r05;
        r05.setStyle(r2);
        r05.setStrokeCap(r4);
        this.thumbHeightDecreaseFocusRing = r62.getResources().getDimensionPixelSize(R.dimen.m3_slider_focus_ring_thumb_height_decrease);
        loadResources(r62.getResources());
        processAttributes(r62, r7, r8);
        setFocusable(true);
        setClickable(true);
        this.scaledTouchSlop = ViewConfiguration.get(r62).getScaledTouchSlop();
        AccessibilityHelper r63 = new AccessibilityHelper(this);
        this.accessibilityHelper = r63;
        ViewCompat.setAccessibilityDelegate(this, r63);
        AccessibilityManager r64 = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.accessibilityManager = r64;
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.tooltipTimeoutMillis = r64.getRecommendedTimeoutMillis(10000, 6);
        return;
    L6:
        this.tooltipTimeoutMillis = MAX_TIMEOUT_TOOLTIP_WITH_ACCESSIBILITY;
    }

    public static /* synthetic */ ViewOverlay access$000(BaseSlider r0) {
        return r0.getContentViewOverlay();
    }

    public static /* synthetic */ List access$100(BaseSlider r0) {
        return r0.labels;
    }

    public static /* synthetic */ AccessibilityHelper access$300(BaseSlider r0) {
        return r0.accessibilityHelper;
    }

    public static /* synthetic */ String access$500() {
        return TAG;
    }

    public static /* synthetic */ String access$600(BaseSlider r0, float r1) {
        return r0.formatValue(r1);
    }

    public static /* synthetic */ boolean access$700(BaseSlider r0, int r1, float r2) {
        return r0.snapThumbToValue(r1, r2);
    }

    public static /* synthetic */ void access$800(BaseSlider r0) {
        r0.updateHaloHotspot();
    }

    public static /* synthetic */ float access$900(BaseSlider r0, int r1) {
        return r0.calculateStepIncrement(r1);
    }

    private void adjustCustomThumbDrawableBounds(int r5, Drawable r6) {
        int r0 = r6.getIntrinsicWidth();
        int r1 = r6.getIntrinsicHeight();
        if (r0 != (-1)) goto L7;
        if (r1 != (-1)) goto L7;
        r6.setBounds(0, 0, r5, this.thumbHeight);
        return;
    L7:
        float r4 = Math.max(r5, this.thumbHeight) / Math.max(r0, r1);
        r6.setBounds(0, 0, (int) (r0 * r4), (int) (r1 * r4));
    }

    private void adjustCustomThumbDrawableBounds(Drawable r2) {
        adjustCustomThumbDrawableBounds(this.thumbWidth, r2);
    }

    private void attachLabelToContentView(TooltipDrawable r1) {
        r1.setRelativeToView(ViewUtils.getContentView(this));
    }

    private void calculateBoundsAndDrawTrackIcon(Canvas r7, RectF r8, Drawable r9, boolean r10) {
        if (r9 == null) goto L7;
        calculateTrackIconBounds(r8, this.iconRectF, this.trackIconSize, this.trackIconPadding, r10);
        if (this.iconRectF.isEmpty() == true) goto L8;
        drawTrackIcon(r7, this.iconRectF, r9);
        return;
    L8:
        return;
    }

    private float calculateEndTrackCornerSize(float r4) {
        if (this.values.isEmpty() == false) goto L5;
    L18:
        return r4;
    L5:
        if (hasGapBetweenThumbAndTrack() == false) goto L18;
        if (isRtl() == false) goto L10;
    L13:
        int r0 = 0;
    L14:
        float r02 = valueToX(this.values.get(r0).floatValue()) - this.trackSidePadding;
        int r1 = this.trackWidth;
        if (r02 <= (r1 - r4)) goto L18;
        return Math.max(r1 - r02, this.trackInsideCornerSize);
    L10:
        if (isVertical() == true) goto L13;
        r0 = this.values.size() - 1;
        goto L14
    }

    private Float calculateIncrementForKey(int r3) {
        if (this.isLongPress == false) goto L5;
        float r0 = calculateStepIncrement(20);
    L7:
        if (r3 == 21) goto L28;
        if (r3 == 22) goto L23;
        if (r3 == 69) goto L21;
        if (r3 == 70) goto L19;
        if (r3 == 81) goto L19;
        return null;
    L19:
        return Float.valueOf(r0);
    L21:
        return Float.valueOf(-r0);
    L23:
        if (isRtl() == false) goto L26;
        r0 = -r0;
    L26:
        return Float.valueOf(r0);
    L28:
        if (isRtl() == true) goto L32;
        r0 = -r0;
    L32:
        return Float.valueOf(r0);
    L5:
        r0 = calculateStepIncrement();
        goto L7
    }

    private void calculateLabelBounds(TooltipDrawable r6, float r7) {
        boolean r0 = isVertical();
        int r1 = this.trackSidePadding;
        if (r0 == false) goto L9;
        int r12 = (r1 + ((int) (normalizeValue(r7) * this.trackWidth))) - (r6.getIntrinsicHeight() / 2);
        int r72 = r6.getIntrinsicHeight() + r12;
        if (isRtl() == false) goto L8;
        int r02 = calculateTrackCenter() - (this.labelPadding + (this.thumbHeight / 2));
        int r62 = r6.getIntrinsicWidth();
    L7:
        int r63 = r02 - r62;
    L10:
        this.labelRect.set(r12, r63, r72, r02);
        return;
    L8:
        int r03 = calculateTrackCenter() + (this.labelPadding + (this.thumbHeight / 2));
        r02 = r6.getIntrinsicWidth() + r03;
        r63 = r03;
        goto L10
    L9:
        r12 = (r1 + ((int) (normalizeValue(r7) * this.trackWidth))) - (r6.getIntrinsicWidth() / 2);
        r72 = r6.getIntrinsicWidth() + r12;
        r02 = calculateTrackCenter() - (this.labelPadding + (this.thumbHeight / 2));
        r62 = r6.getIntrinsicHeight();
        goto L7
    }

    private float calculateStartTrackCornerSize(float r3) {
        if (this.values.isEmpty() == false) goto L5;
    L18:
        return r3;
    L5:
        if (hasGapBetweenThumbAndTrack() == false) goto L18;
        if (isRtl() == false) goto L10;
    L13:
        int r0 = this.values.size() - 1;
    L14:
        float r02 = valueToX(this.values.get(r0).floatValue()) - this.trackSidePadding;
        if (r02 >= r3) goto L18;
        return Math.max(r02, this.trackInsideCornerSize);
    L10:
        if (isVertical() == true) goto L13;
        r0 = 0;
        goto L14
    }

    private float calculateStepIncrement() {
        float r1 = this.stepSize;
        if (r1 != 0.0f) goto L6;
        return 1.0f;
    L6:
        return r1;
    }

    private float calculateStepIncrement(int r3) {
        float r0 = calculateStepIncrement();
        if (((this.valueTo - this.valueFrom) / r0) > r3) goto L6;
        return r0;
    L6:
        return Math.round(r1 / r2) * r0;
    }

    private int calculateThumbTrackGapSize(int r2) {
        if (this.thumbIsPressed == false) goto L13;
        if (r2 != this.activeThumbIdx) goto L13;
        if (this.customThumbDrawable != null) goto L13;
        if (this.customThumbDrawablesForValues.isEmpty() == false) goto L13;
        return this.thumbTrackGapSize - ((this.thumbWidth - Math.round(this.thumbWidth * 0.5f)) / 2);
    L13:
        return this.thumbTrackGapSize;
    }

    private int calculateTrackCenter() {
        int r0 = this.widgetThickness / 2;
        int r3 = 0;
        if (this.labelBehavior == 1) goto L7;
        if (shouldAlwaysShowLabel() == true) goto L7;
    L10:
        return r0 + r3;
    L7:
        if (this.labels.isEmpty() == true) goto L10;
        r3 = this.labels.get(0).getIntrinsicHeight();
        goto L10
    }

    private void calculateTrackIconBounds(RectF r3, RectF r4, int r5, int r6, boolean r7) {
        if ((r3.right - r3.left) >= ((r6 * 2) + r5)) goto L5;
        r4.setEmpty();
        return;
    L5:
        if (isRtl() == false) goto L7;
    L10:
        boolean r0 = true;
    L12:
        if ((r7 ^ r0) == false) goto L14;
        float r32 = r3.left + r6;
    L15:
        float r52 = r5;
        float r2 = calculateTrackCenter() - (r52 / 2.0f);
        r4.set(r32, r2, r32 + r52, r52 + r2);
        return;
    L14:
        r32 = (r3.right - r6) - r5;
        goto L15
    L7:
        if (isVertical() == true) goto L10;
        r0 = false;
        goto L12
    }

    private int convertToTickVisibilityMode(boolean r1) {
        if (r1 == false) goto L5;
        return 0;
    L5:
        return 2;
    }

    private ValueAnimator createLabelAnimator(boolean r5) {
        float r0 = 1.0f;
        if (r5 == false) goto L5;
        float r2 = 0.0f;
    L6:
        if (r5 == false) goto L8;
        ValueAnimator r3 = this.labelsOutAnimator;
    L9:
        float r22 = getAnimatorCurrentValueOrDefault(r3, r2);
        if (r5 == true) goto L13;
        r0 = 0.0f;
    L13:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r22, r0});
        if (r5 == false) goto L16;
        int r52 = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_ENTER_DURATION_ATTR, 83);
        TimeInterpolator r1 = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_ENTER_EASING_ATTR, AnimationUtils.DECELERATE_INTERPOLATOR);
    L17:
        r02.setDuration(r52);
        r02.setInterpolator(r1);
        r02.addUpdateListener(new BaseSlider$$ExternalSyntheticLambda0(this));
        return r02;
    L16:
        r52 = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_EXIT_DURATION_ATTR, 117);
        r1 = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_EXIT_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        goto L17
    L8:
        r3 = this.labelsInAnimator;
        goto L9
    L5:
        r2 = 1.0f;
        goto L6
    }

    private void createLabelPool() {
        if (this.labels.size() <= this.values.size()) goto L11;
        List<TooltipDrawable> r0 = this.labels.subList(this.values.size(), this.labels.size());
        Iterator<TooltipDrawable> r1 = r0.iterator();
    L6:
        if (r1.hasNext() == false) goto L10;
        TooltipDrawable r2 = r1.next();
        if (isAttachedToWindow() == false) goto L6;
        detachLabelFromContentView(r2);
        goto L6
    L10:
        r0.clear();
    L11:
        int r22 = 0;
        if (this.labels.size() >= this.values.size()) goto L17;
        TooltipDrawable r02 = TooltipDrawable.createFromAttributes(getContext(), null, 0, this.labelStyle);
        this.labels.add(r02);
        if (isAttachedToWindow() == false) goto L11;
        attachLabelToContentView(r02);
        goto L11
    L17:
        if (this.labels.size() == 1) goto L20;
        r22 = 1;
    L20:
        Iterator<TooltipDrawable> r4 = this.labels.iterator();
    L22:
        if (r4.hasNext() == false) goto L24;
        r4.next().setStrokeWidth(r22);
        goto L22
    }

    private MaterialShapeDrawable createNewDefaultThumb() {
        MaterialShapeDrawable r0 = new MaterialShapeDrawable();
        r0.setShadowCompatibilityMode(2);
        r0.setFillColor(getThumbTintList());
        r0.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, this.thumbWidth / 2.0f).build());
        r0.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
        r0.setElevation(getThumbElevation());
        r0.setStrokeWidth(getThumbStrokeWidth());
        r0.setStrokeTint(getThumbStrokeColor());
        r0.setState(getDrawableState());
        return r0;
    }

    private void detachLabelFromContentView(TooltipDrawable r2) {
        ViewGroup r1 = ViewUtils.getContentView(this);
        if (r1 != null) goto L5;
        return;
    L5:
        r1.getOverlay().remove(r2);
        r2.detachView(r1);
    }

    private float dimenToValue(float r3) {
        if (r3 != 0.0f) goto L5;
        return 0.0f;
    L5:
        float r32 = (r3 - this.trackSidePadding) / this.trackWidth;
        float r0 = this.valueFrom;
        return (r32 * (r0 - this.valueTo)) + r0;
    }

    private void dispatchOnChangedFromUser(int r5) {
        Iterator<L> r0 = this.changeListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onValueChange(this, this.values.get(r5).floatValue(), true);
        goto L4
    L6:
        AccessibilityManager r02 = this.accessibilityManager;
        if (r02 != null) goto L9;
        return;
    L9:
        if (r02.isEnabled() == false) goto L14;
        scheduleAccessibilityEventSender(r5);
        this.accessibilityHelper.invalidateVirtualView(r5);
        return;
    }

    private void dispatchOnChangedProgrammatically() {
        Iterator<L> r0 = this.changeListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        L r1 = r0.next();
        ArrayList<Float> r2 = this.values;
        int r3 = r2.size();
        int r5 = 0;
    L6:
        if (r5 >= r3) goto L4;
        Float r6 = r2.get(r5);
        r5 = r5 + 1;
        r1.onValueChange(this, r6.floatValue(), false);
        goto L6
    }

    private void drawActiveTracks(Canvas r17, int r18, int r19) {
        BaseSlider<S, L, T> r0 = this;
        float[] r6 = r0.getActiveRange();
        int r1 = r0.trackSidePadding;
        float r4 = r18;
        float r2 = r1 + (r6[1] * r4);
        float r12 = r1 + (r6[0] * r4);
        if (r12 < r2) goto L6;
        r0.activeTrackRect.setEmpty();
        return;
    L6:
        FullCornerDirection r3 = FullCornerDirection.NONE;
        if (r0.values.size() == 1) goto L9;
    L17:
        FullCornerDirection r5 = r3;
        int r9 = 0;
    L19:
        if (r9 >= r0.values.size()) goto L53;
        if (r0.values.size() <= 1) goto L29;
        if (r9 <= 0) goto L24;
        r12 = r0.valueToX(r0.values.get(r9 - 1).floatValue());
    L24:
        r2 = r0.valueToX(r0.values.get(r9).floatValue());
        if (r0.isRtl() == false) goto L27;
    L28:
        r2 = r12;
        r12 = r2;
        goto L29
    L27:
        if (r0.isVertical() == true) goto L28;
    L29:
        int r32 = r0.getTrackCornerSize();
        int r42 = r5.ordinal();
        if (r42 != 1) goto L32;
        r12 = r12 - r32;
        int r43 = r0.calculateThumbTrackGapSize(r9);
    L38:
        r2 = r2 - r43;
    L39:
        float r10 = r12;
        float r11 = r2;
        RectF r22 = r0.activeTrackRect;
        if (r10 < r11) goto L51;
        r22.setEmpty();
    L52:
        r9 = r9 + 1;
        r0 = this;
        r12 = r10;
        r2 = r11;
        goto L19
    L51:
        float r13 = r19;
        int r44 = r0.trackThickness;
        r22.set(r10, r13 - (r44 / 2.0f), r11, r13 + (r44 / 2.0f));
        r0.updateTrack(r17, r0.activeTrackPaint, r0.activeTrackRect, r32, r5);
        goto L52
    L32:
        if (r42 != 2) goto L34;
        r12 = r12 + r0.calculateThumbTrackGapSize(r9);
        r2 = r2 + r32;
        goto L39
    L34:
        if (r42 != 3) goto L39;
        if (r9 <= 0) goto L41;
        r12 = r12 + r0.calculateThumbTrackGapSize(r9 - 1);
        r43 = r0.calculateThumbTrackGapSize(r9);
        goto L38
    L41:
        if (r6[1] != 0.5f) goto L44;
        r12 = r12 + r0.calculateThumbTrackGapSize(r9);
        goto L39
    L44:
        if (r6[0] != 0.5f) goto L39;
        r43 = r0.calculateThumbTrackGapSize(r9);
        goto L38
    L53:
        return;
    L9:
        if (r0.isCentered() == true) goto L17;
        if (r0.isRtl() == false) goto L13;
    L16:
        r3 = FullCornerDirection.RIGHT;
        goto L17
    L13:
        if (r0.isVertical() == true) goto L16;
        r3 = FullCornerDirection.LEFT;
        goto L17
    }

    private void drawInactiveTrackSection(float r3, float r4, float r5, float r6, Canvas r7, RectF r8, FullCornerDirection r9, int r10) {
        if ((r4 - r3) <= (getTrackCornerSize() - r10)) goto L5;
        r8.set(r3, r5, r4, r6);
    L6:
        updateTrack(r7, this.inactiveTrackPaint, r8, getTrackCornerSize(), r9);
        return;
    L5:
        r8.setEmpty();
        goto L6
    }

    private void drawInactiveTracks(Canvas r16, int r17, int r18) {
        float[] r10 = getActiveRange();
        float r1 = r18;
        int r2 = this.trackThickness;
        float r3 = r1 - (r2 / 2.0f);
        float r4 = r1 + (r2 / 2.0f);
        int r13 = 0;
        if (isCentered() == false) goto L9;
        if (r10[0] != 0.5f) goto L9;
        int r12 = this.thumbTrackGapSize;
    L7:
        int r8 = r12;
        float r14 = r17;
        drawInactiveTrackSection(this.trackSidePadding - getTrackCornerSize(), (this.trackSidePadding + (r10[0] * r14)) - r8, r3, r4, r16, this.inactiveTrackLeftRect, FullCornerDirection.LEFT, r8);
        if (isCentered() == false) goto L23;
        if (r10[1] != 0.5f) goto L23;
        int r15 = this.thumbTrackGapSize;
    L21:
        int r82 = r15;
        drawInactiveTrackSection((this.trackSidePadding + (r10[1] * r14)) + r82, (r1 + r17) + getTrackCornerSize(), r3, r4, r16, this.inactiveTrackRightRect, FullCornerDirection.RIGHT, r82);
        return;
    L23:
        if (isRtl() == false) goto L25;
    L28:
        r15 = calculateThumbTrackGapSize(r13);
        goto L21
    L25:
        if (isVertical() == true) goto L28;
        r13 = this.values.size() - 1;
    L9:
        if (isRtl() == false) goto L11;
    L14:
        int r19 = this.values.size() - 1;
    L15:
        r12 = calculateThumbTrackGapSize(r19);
        goto L7
    L11:
        if (isVertical() == true) goto L14;
        r19 = 0;
        goto L15
    }

    private void drawStopIndicator(Canvas r6, float r7, float r8) {
        int r0 = 0;
    L4:
        if (r0 >= this.values.size()) goto L11;
        float r1 = valueToX(this.values.get(r0).floatValue());
        float r2 = calculateThumbTrackGapSize(r0) + (this.thumbWidth / 2.0f);
        if (r7 < (r1 - r2)) goto L10;
        if (r7 > (r1 + r2)) goto L10;
        return;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        boolean r02 = isVertical();
        Paint r5 = this.stopIndicatorPaint;
        if (r02 == false) goto L15;
        r6.drawPoint(r8, r7, r5);
        return;
    L15:
        r6.drawPoint(r7, r8, r5);
    }

    private void drawThumbDrawable(Canvas r2, int r3, int r4, float r5, Drawable r6) {
        r2.save();
        if (isVertical() == false) goto L5;
        r2.concat(this.rotationMatrix);
    L5:
        r2.translate((this.trackSidePadding + ((int) (normalizeValue(r5) * r3))) - (r6.getBounds().width() / 2.0f), r4 - (r6.getBounds().height() / 2.0f));
        r6.draw(r2);
        r2.restore();
    }

    private void drawThumbs(Canvas r9, int r10, int r11) {
        int r0 = 0;
    L4:
        if (r0 >= this.values.size()) goto L16;
        float r6 = this.values.get(r0).floatValue();
        Drawable r7 = this.customThumbDrawable;
        if (r7 == null) goto L8;
        BaseSlider<S, L, T> r2 = this;
        Canvas r3 = r9;
        int r4 = r10;
        int r5 = r11;
        r2.drawThumbDrawable(r3, r4, r5, r6, r7);
    L15:
        r0 = r0 + 1;
        BaseSlider<S, L, T> this = r2;
        r9 = r3;
        r10 = r4;
        r11 = r5;
        goto L4
    L8:
        r2 = this;
        r3 = r9;
        r4 = r10;
        r5 = r11;
        if (r0 >= r2.customThumbDrawablesForValues.size()) goto L12;
        r2.drawThumbDrawable(r3, r4, r5, r6, r2.customThumbDrawablesForValues.get(r0));
        goto L15
    L12:
        if (r2.isEnabled() == true) goto L14;
        r3.drawCircle(r2.trackSidePadding + (r2.normalizeValue(r6) * r4), r5, r2.getThumbRadius(), r2.thumbPaint);
    L14:
        r2.drawThumbDrawable(r3, r4, r5, r6, r2.defaultThumbDrawables.get(r0));
        goto L15
    }

    private void drawTicks(int r4, int r5, Canvas r6, Paint r7) {
    L2:
        if (r4 >= r5) goto L16;
        boolean r0 = isVertical();
        float[] r1 = this.ticksCoordinates;
        if (r0 == false) goto L6;
        float r02 = r1[r4 + 1];
    L8:
        if (isOverlappingThumb(r02) == true) goto L15;
        if (isCentered() == true) goto L12;
    L14:
        float[] r03 = this.ticksCoordinates;
        r6.drawPoint(r03[r4], r03[r4 + 1], r7);
        goto L15
    L12:
        if (isOverlappingCenterGap(r02) == false) goto L14;
    L15:
        r4 = r4 + 2;
        goto L2
    L6:
        r02 = r1[r4];
        goto L8
    }

    private void drawTrackIcon(Canvas r2, RectF r3, Drawable r4) {
        if (isVertical() == false) goto L5;
        this.rotationMatrix.mapRect(r3);
    L5:
        r3.round(this.iconRect);
        r4.setBounds(this.iconRect);
        r4.draw(r2);
    }

    private void drawTrackIcons(Canvas r4, RectF r5, RectF r6) {
        if (hasTrackIcons() == true) goto L6;
        return;
    L6:
        if (this.values.size() <= 1) goto L8;
        Log.w(TAG, "Track icons can only be used when only 1 thumb is present.");
    L8:
        calculateBoundsAndDrawTrackIcon(r4, r5, this.trackIconActiveStart, true);
        calculateBoundsAndDrawTrackIcon(r4, r6, this.trackIconInactiveStart, true);
        calculateBoundsAndDrawTrackIcon(r4, r5, this.trackIconActiveEnd, false);
        calculateBoundsAndDrawTrackIcon(r4, r6, this.trackIconInactiveEnd, false);
    }

    private void ensureLabelsAdded(boolean r4) {
        if (this.labelsAreAnimatedIn == true) goto L5;
        this.labelsAreAnimatedIn = true;
        ValueAnimator r0 = createLabelAnimator(true);
        this.labelsInAnimator = r0;
        this.labelsOutAnimator = null;
        r0.start();
    L5:
        Iterator<TooltipDrawable> r02 = this.labels.iterator();
        if (r4 == false) goto L18;
        int r42 = 0;
    L9:
        if (r42 >= this.values.size()) goto L18;
        if (r02.hasNext() == false) goto L18;
        if (r42 == this.focusedThumbIdx) goto L16;
        setValueForLabel(r02.next(), this.values.get(r42).floatValue());
    L16:
        r42 = r42 + 1;
    L18:
        if (r02.hasNext() == false) goto L21;
        setValueForLabel(r02.next(), this.values.get(this.focusedThumbIdx).floatValue());
        return;
    L21:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("Not enough labels(%d) to display all the values(%d)", new Object[]{Integer.valueOf(this.labels.size()), Integer.valueOf(this.values.size())});
    }

    private void ensureLabelsRemoved() {
        if (this.labelsAreAnimatedIn == false) goto L6;
        this.labelsAreAnimatedIn = false;
        ValueAnimator r0 = createLabelAnimator(false);
        this.labelsOutAnimator = r0;
        this.labelsInAnimator = null;
        r0.addListener(new AnonymousClass1(this));
        this.labelsOutAnimator.start();
        return;
    }

    private void focusThumbOnFocusGained(int r4) {
        if (r4 != 1) goto L5;
        moveFocus(Integer.MAX_VALUE);
        return;
    L5:
        if (r4 != 2) goto L7;
        moveFocus(Integer.MIN_VALUE);
        return;
    L7:
        if (r4 != 17) goto L9;
        moveFocusInAbsoluteDirection(Integer.MAX_VALUE);
        return;
    L9:
        if (r4 == 66) goto L11;
        return;
    L11:
        moveFocusInAbsoluteDirection(Integer.MIN_VALUE);
    }

    private String formatValue(float r2) {
        if (hasLabelFormatter() == false) goto L7;
        return this.formatter.getFormattedValue(r2);
    L7:
        if (((int) r2) != r2) goto L9;
        String r1 = "%.0f";
    L11:
        return String.format(r1, new Object[]{Float.valueOf(r2)});
    L9:
        r1 = "%.2f";
        goto L11
    }

    private float[] getActiveRange() {
        float r0 = this.values.get(0).floatValue();
        ArrayList<Float> r2 = this.values;
        float r22 = r2.get(r2.size() - 1).floatValue();
        if (this.values.size() != 1) goto L5;
        r0 = this.valueFrom;
    L5:
        float r02 = normalizeValue(r0);
        float r23 = normalizeValue(r22);
        if (isCentered() == false) goto L9;
        float r3 = Math.min(0.5f, r23);
        r23 = Math.max(0.5f, r23);
        r02 = r3;
    L9:
        if (isCentered() == true) goto L17;
        if (isRtl() == true) goto L15;
        if (isVertical() == false) goto L17;
    L15:
        return new float[]{r23, r02};
    L17:
        return new float[]{r02, r23};
    }

    private static float getAnimatorCurrentValueOrDefault(ValueAnimator r1, float r2) {
        if (r1 != null) goto L4;
        return r2;
    L4:
        if (r1.isRunning() == false) goto L8;
        float r22 = ((Float) r1.getAnimatedValue()).floatValue();
        r1.cancel();
        return r22;
    L8:
        return r2;
    }

    private RippleDrawable getBackgroundRipple() {
        Drawable r1 = getBackground();
        if ((r1 instanceof DrawableWrapper) == false) goto L6;
        r1 = ((DrawableWrapper) r1).getDrawable();
    L6:
        if ((r1 instanceof RippleDrawable) == true) goto L8;
        return null;
    L8:
        return (RippleDrawable) r1;
    }

    private float getClampedValue(int r4, float r5) {
        float r0 = getMinSeparation();
        if (this.separationUnit != 0) goto L6;
        r0 = dimenToValue(r0);
    L6:
        if (isRtl() == false) goto L8;
    L9:
        r0 = -r0;
    L10:
        int r1 = r4 + 1;
        if (r1 < this.values.size()) goto L13;
        float r12 = this.valueTo;
    L14:
        int r42 = r4 - 1;
        if (r42 >= 0) goto L17;
        float r3 = this.valueFrom;
    L19:
        return MathUtils.clamp(r5, r3, r12);
    L17:
        r3 = this.values.get(r42).floatValue() + r0;
        goto L19
    L13:
        r12 = this.values.get(r1).floatValue() - r0;
        goto L14
    L8:
        if (isVertical() == false) goto L10;
        goto L9
    }

    private int getColorForState(ColorStateList r2) {
        return r2.getColorForState(getDrawableState(), r2.getDefaultColor());
    }

    private ViewOverlay getContentViewOverlay() {
        ViewGroup r0 = ViewUtils.getContentView(this);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getOverlay();
    }

    private float[] getCornerRadii(float r10, float r11) {
        if (isVertical() == false) goto L7;
        return new float[]{r10, r10, r10, r10, r11, r11, r11, r11};
    L7:
        return new float[]{r10, r10, r11, r11, r11, r11, r10, r10};
    }

    private int getDesiredTickCount() {
        return (int) (((this.valueTo - this.valueFrom) / this.stepSize) + 1.0f);
    }

    private FocusRingDrawable getFocusRing() {
        return FocusRingDrawable.find(getBackground());
    }

    private int getMaxTickCount() {
        return (this.trackWidth / this.minTickSpacing) + 1;
    }

    private float getValueOfTouchPosition() {
        double r0 = snapPosition(this.touchPosition);
        if (isRtl() == false) goto L5;
    L6:
        r0 = 1.0d - r0;
    L7:
        float r2 = this.valueTo;
        return (float) ((r0 * (r2 - r4)) + this.valueFrom);
    L5:
        if (isVertical() == false) goto L7;
        goto L6
    }

    private float getValueOfTouchPositionAbsolute() {
        float r0 = this.touchPosition;
        if (isRtl() == false) goto L5;
    L6:
        r0 = 1.0f - r0;
    L7:
        float r1 = this.valueTo;
        float r2 = this.valueFrom;
        return (r0 * (r1 - r2)) + r2;
    L5:
        if (isVertical() == false) goto L7;
        goto L6
    }

    private boolean hasGapBetweenThumbAndTrack() {
        if (this.thumbTrackGapSize <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean hasTrackIcons() {
        if (this.trackIconActiveStart == null) goto L5;
        return true;
    L5:
        if (this.trackIconActiveEnd == null) goto L7;
        return true;
    L7:
        if (this.trackIconInactiveStart == null) goto L9;
        return true;
    L9:
        if (this.trackIconInactiveEnd != null) goto L17;
        return false;
    L17:
        return true;
    }

    private Drawable initializeCustomThumbDrawable(Drawable r1) {
        Drawable r12 = r1.mutate().getConstantState().newDrawable();
        adjustCustomThumbDrawableBounds(r12);
        return r12;
    }

    private void invalidateTrack() {
        this.inactiveTrackPaint.setStrokeWidth(this.trackThickness);
        this.activeTrackPaint.setStrokeWidth(this.trackThickness);
    }

    private boolean isFocusRingEnabled() {
        FocusRingDrawable r0 = getFocusRing();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isFocusRingEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isInHorizontalScrollingContainer() {
        ViewParent r3 = getParent();
    L4:
        if ((r3 instanceof ViewGroup) == false) goto L13;
        ViewGroup r0 = (ViewGroup) r3;
        if (r0.canScrollHorizontally(1) == true) goto L10;
        if (r0.canScrollHorizontally(-1) == true) goto L10;
    L12:
        r3 = r3.getParent();
    L10:
        if (r0.shouldDelayChildPressedState() == false) goto L12;
        return true;
    L13:
        return false;
    }

    private boolean isInVerticalScrollingContainer() {
        ViewParent r3 = getParent();
    L4:
        if ((r3 instanceof ViewGroup) == false) goto L13;
        ViewGroup r0 = (ViewGroup) r3;
        if (r0.canScrollVertically(1) == true) goto L10;
        if (r0.canScrollVertically(-1) == true) goto L10;
    L12:
        r3 = r3.getParent();
    L10:
        if (r0.shouldDelayChildPressedState() == false) goto L12;
        return true;
    L13:
        return false;
    }

    private static boolean isMouseEvent(MotionEvent r2) {
        if (r2.getToolType(0) != 3) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isMultipleOfStepSize(double r3) {
        if (Math.abs(Math.round(r2) - new BigDecimal(Double.toString(r3)).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue()) >= THRESHOLD) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isOverlappingCenterGap(float r3) {
        float r0 = (this.trackWidth + (this.trackSidePadding * 2)) / 2.0f;
        int r2 = this.thumbTrackGapSize;
        if (r3 >= (r0 - r2)) goto L5;
        return false;
    L5:
        if (r3 > (r0 + r2)) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isOverlappingThumb(float r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.values.size()) goto L12;
        float r2 = valueToX(this.values.get(r1).floatValue());
        float r3 = calculateThumbTrackGapSize(r1) + (this.thumbWidth / 2.0f);
        if (r7 < (r2 - r3)) goto L11;
        if (r7 > (r2 + r3)) goto L11;
        return true;
    L11:
        r1 = r1 + 1;
        goto L4
    L12:
        return false;
    }

    private boolean isPotentialHorizontalScroll(MotionEvent r1) {
        if (isMouseEvent(r1) == false) goto L5;
        return false;
    L5:
        if (isInHorizontalScrollingContainer() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isPotentialVerticalScroll(MotionEvent r1) {
        if (isMouseEvent(r1) == false) goto L5;
        return false;
    L5:
        if (isInVerticalScrollingContainer() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isSliderVisibleOnScreen() {
        Rect r0 = new Rect();
        ViewUtils.getContentView(this).getHitRect(r0);
        if (getLocalVisibleRect(r0) == true) goto L5;
        return false;
    L5:
        if (isThisAndAncestorsVisible() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isThisAndAncestorsVisible() {
        return this.thisAndAncestorsVisible;
    }

    private void loadResources(Resources r2) {
        this.minWidgetThickness = r2.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int r0 = r2.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.minTrackSidePadding = r0;
        this.trackSidePadding = r0;
        this.defaultThumbRadius = r2.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.defaultTrackThickness = r2.getDimensionPixelSize(R.dimen.mtrl_slider_track_height);
        this.defaultTickActiveRadius = r2.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.defaultTickInactiveRadius = r2.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.minTickSpacing = r2.getDimensionPixelSize(R.dimen.mtrl_slider_tick_min_spacing);
        this.labelPadding = r2.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
        this.trackIconPadding = r2.getDimensionPixelOffset(R.dimen.m3_slider_track_icon_padding);
        this.accessibilityMinTouchTargetSize = r2.getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
    }

    private void maybeDrawCompatHalo(Canvas r11, int r12, int r13) {
        if (shouldDrawCompatHalo() == false) goto L13;
        float[] r132 = {this.trackSidePadding + (normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * r12), r13};
        if (isVertical() == false) goto L8;
        this.rotationMatrix.mapPoints(r132);
    L8:
        if (Build.VERSION.SDK_INT >= 28) goto L10;
        float r122 = r132[0];
        int r2 = this.haloRadius;
        float r3 = r132[1];
        Canvas r4 = r11;
        r4.clipRect(r122 - r2, r3 - r2, r122 + r2, r3 + r2, Region.Op.UNION);
    L11:
        r4.drawCircle(r132[0], r132[1], this.haloRadius, this.haloPaint);
        return;
    L10:
        r4 = r11;
        goto L11
    }

    private void maybeDrawStopIndicator(Canvas r4, int r5) {
        if (this.trackStopIndicatorSize > 0) goto L5;
        return;
    L5:
        if (this.values.isEmpty() == true) goto L21;
        ArrayList<Float> r0 = this.values;
        float r02 = r0.get(r0.size() - 1).floatValue();
        float r1 = this.valueTo;
        if (r02 >= r1) goto L11;
        drawStopIndicator(r4, valueToX(r1), r5);
    L11:
        if (isCentered() == false) goto L13;
    L16:
        drawStopIndicator(r4, valueToX(this.valueFrom), r5);
        return;
    L13:
        if (this.values.size() > 1) goto L15;
        return;
    L15:
        if (this.values.get(0).floatValue() > this.valueFrom) goto L16;
        return;
    }

    private void maybeDrawTicks(Canvas r8) {
        float[] r0 = this.ticksCoordinates;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.length == 0) goto L18;
        float[] r02 = getActiveRange();
        int r2 = (int) Math.ceil(r02[0] * ((this.ticksCoordinates.length / 2.0f) - 1.0f));
        int r03 = (int) Math.floor(r02[1] * ((this.ticksCoordinates.length / 2.0f) - 1.0f));
        if (r2 <= 0) goto L10;
        drawTicks(0, r2 * 2, r8, this.inactiveTicksPaint);
    L10:
        if (r2 > r03) goto L12;
        drawTicks(r2 * 2, (r03 + 1) * 2, r8, this.activeTicksPaint);
    L12:
        int r04 = (r03 + 1) * 2;
        float[] r1 = this.ticksCoordinates;
        if (r04 >= r1.length) goto L17;
        drawTicks(r04, r1.length, r8, this.inactiveTicksPaint);
        return;
    L17:
        return;
    }

    private boolean maybeIncreaseTrackSidePadding() {
        int r0 = Math.max((this.thumbWidth / 2) - this.defaultThumbRadius, 0);
        int r2 = Math.max((this.trackThickness - this.defaultTrackThickness) / 2, 0);
        int r3 = Math.max(this.tickActiveRadius - this.defaultTickActiveRadius, 0);
        int r4 = Math.max(this.tickInactiveRadius - this.defaultTickInactiveRadius, 0);
        int r5 = this.minTrackSidePadding + Math.max(Math.max(r0, r2), Math.max(r3, r4));
        if (this.trackSidePadding != r5) goto L5;
        return false;
    L5:
        this.trackSidePadding = r5;
        if (isLaidOut() == true) goto L8;
        return true;
    L8:
        if (isVertical() == false) goto L10;
        int r02 = getHeight();
    L11:
        updateTrackWidth(r02);
        return true;
    L10:
        r02 = getWidth();
        goto L11
    }

    private boolean maybeIncreaseWidgetThickness() {
        if (isVertical() == false) goto L6;
        int r0 = getPaddingLeft();
        int r1 = getPaddingRight();
    L5:
        int r02 = r0 + r1;
        int r12 = this.trackThickness + r02;
        int r2 = this.thumbHeight + r02;
        int r03 = Math.max(this.minWidgetThickness, Math.max(r12, r2));
        if (r03 != this.widgetThickness) goto L11;
        return false;
    L11:
        this.widgetThickness = r03;
        return true;
    L6:
        r0 = getPaddingTop();
        r1 = getPaddingBottom();
        goto L5
    }

    private boolean moveFocus(int r12) {
        int r0 = this.focusedThumbIdx;
        int r122 = (int) MathUtils.clamp(r0 + r12, 0, this.values.size() - 1);
        this.focusedThumbIdx = r122;
        if (r122 != r0) goto L6;
        return false;
    L6:
        this.activeThumbIdx = r122;
        updateThumbWidthWhenPressed();
        updateHaloHotspot();
        postInvalidate();
        return true;
    }

    private boolean moveFocusInAbsoluteDirection(int r2) {
        if (isRtl() == true) goto L7;
        if (isVertical() == true) goto L7;
    L11:
        return moveFocus(r2);
    L7:
        if (r2 != Integer.MIN_VALUE) goto L9;
        r2 = Integer.MAX_VALUE;
        goto L11
    L9:
        r2 = -r2;
        goto L11
    }

    private float normalizeValue(float r3) {
        float r0 = this.valueFrom;
        float r32 = (r3 - r0) / (this.valueTo - r0);
        if (isRtl() == true) goto L9;
        if (isVertical() == true) goto L9;
        return r32;
    L9:
        return 1.0f - r32;
    }

    private void onStartTrackingTouch() {
        Iterator<T> r0 = this.touchListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onStartTrackingTouch(this);
        goto L4
    }

    private void onStopTrackingTouch() {
        Iterator<T> r0 = this.touchListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onStopTrackingTouch(this);
        goto L4
    }

    private void positionLabel(TooltipDrawable r2, float r3) {
        calculateLabelBounds(r2, r3);
        if (isVertical() == false) goto L5;
        RectF r32 = new RectF(this.labelRect);
        this.rotationMatrix.mapRect(r32);
        r32.round(this.labelRect);
    L5:
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, this.labelRect);
        r2.setBounds(this.labelRect);
    }

    private void processAttributes(Context r8, AttributeSet r9, int r10) {
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r8, r9, R.styleable.Slider, r10, DEF_STYLE_RES, new int[0]);
        setOrientation(r82.getInt(R.styleable.Slider_android_orientation, 0));
        this.labelStyle = r82.getResourceId(R.styleable.Slider_labelStyle, R.style.Widget_MaterialComponents_Tooltip);
        this.valueFrom = r82.getFloat(R.styleable.Slider_android_valueFrom, 0.0f);
        this.valueTo = r82.getFloat(R.styleable.Slider_android_valueTo, 1.0f);
        setCentered(r82.getBoolean(R.styleable.Slider_centered, false));
        this.stepSize = r82.getFloat(R.styleable.Slider_android_stepSize, 0.0f);
        this.continuousModeTickCount = r82.getInt(R.styleable.Slider_continuousModeTickCount, 0);
        float r92 = MaterialAttributes.resolveMinimumAccessibleTouchTarget(r8);
        this.minTouchTargetSize = (int) Math.ceil(r82.getDimension(R.styleable.Slider_minTouchTargetSize, r92));
        boolean r93 = r82.hasValue(R.styleable.Slider_trackColor);
        if (r93 == false) goto L5;
        int r1 = R.styleable.Slider_trackColor;
    L6:
        if (r93 == false) goto L8;
        int r94 = R.styleable.Slider_trackColor;
    L9:
        ColorStateList r12 = MaterialResources.getColorStateList(r8, r82, r1);
        if (r12 != null) goto L13;
        r12 = AppCompatResources.getColorStateList(r8, R.color.material_slider_inactive_track_color);
    L13:
        setTrackInactiveTintList(r12);
        ColorStateList r95 = MaterialResources.getColorStateList(r8, r82, r94);
        if (r95 != null) goto L17;
        r95 = AppCompatResources.getColorStateList(r8, R.color.material_slider_active_track_color);
    L17:
        setTrackActiveTintList(r95);
        ColorStateList r96 = MaterialResources.getColorStateList(r8, r82, R.styleable.Slider_thumbColor);
        if (r96 != null) goto L21;
        r96 = AppCompatResources.getColorStateList(r8, R.color.material_slider_thumb_color);
    L21:
        setThumbTintList(r96);
        if (r82.hasValue(R.styleable.Slider_thumbStrokeColor) == false) goto L24;
        setThumbStrokeColor(MaterialResources.getColorStateList(r8, r82, R.styleable.Slider_thumbStrokeColor));
    L24:
        setThumbStrokeWidth(r82.getDimension(R.styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList r97 = MaterialResources.getColorStateList(r8, r82, R.styleable.Slider_haloColor);
        if (r97 != null) goto L28;
        r97 = AppCompatResources.getColorStateList(r8, R.color.material_slider_halo_color);
    L28:
        setHaloTintList(r97);
        if (r82.hasValue(R.styleable.Slider_tickVisibilityMode) == false) goto L31;
        int r98 = r82.getInt(R.styleable.Slider_tickVisibilityMode, -1);
    L32:
        this.tickVisibilityMode = r98;
        boolean r99 = r82.hasValue(R.styleable.Slider_tickColor);
        if (r99 == false) goto L35;
        int r3 = R.styleable.Slider_tickColor;
    L36:
        if (r99 == false) goto L38;
        int r910 = R.styleable.Slider_tickColor;
    L39:
        ColorStateList r32 = MaterialResources.getColorStateList(r8, r82, r3);
        if (r32 != null) goto L43;
        r32 = AppCompatResources.getColorStateList(r8, R.color.material_slider_inactive_tick_marks_color);
    L43:
        setTickInactiveTintList(r32);
        ColorStateList r911 = MaterialResources.getColorStateList(r8, r82, r910);
        if (r911 != null) goto L47;
        r911 = AppCompatResources.getColorStateList(r8, R.color.material_slider_active_tick_marks_color);
    L47:
        setTickActiveTintList(r911);
        setThumbTrackGapSize(r82.getDimensionPixelSize(R.styleable.Slider_thumbTrackGapSize, 0));
        setTrackStopIndicatorSize(r82.getDimensionPixelSize(R.styleable.Slider_trackStopIndicatorSize, 0));
        setTrackCornerSize(r82.getDimensionPixelSize(R.styleable.Slider_trackCornerSize, -1));
        setTrackInsideCornerSize(r82.getDimensionPixelSize(R.styleable.Slider_trackInsideCornerSize, 0));
        setTrackIconActiveStart(MaterialResources.getDrawable(r8, r82, R.styleable.Slider_trackIconActiveStart));
        setTrackIconActiveEnd(MaterialResources.getDrawable(r8, r82, R.styleable.Slider_trackIconActiveEnd));
        setTrackIconActiveColor(MaterialResources.getColorStateList(r8, r82, R.styleable.Slider_trackIconActiveColor));
        setTrackIconInactiveStart(MaterialResources.getDrawable(r8, r82, R.styleable.Slider_trackIconInactiveStart));
        setTrackIconInactiveEnd(MaterialResources.getDrawable(r8, r82, R.styleable.Slider_trackIconInactiveEnd));
        setTrackIconInactiveColor(MaterialResources.getColorStateList(r8, r82, R.styleable.Slider_trackIconInactiveColor));
        setTrackIconSize(r82.getDimensionPixelSize(R.styleable.Slider_trackIconSize, 0));
        int r912 = r82.getDimensionPixelSize(R.styleable.Slider_thumbRadius, 0) * 2;
        int r0 = r82.getDimensionPixelSize(R.styleable.Slider_thumbWidth, r912);
        int r913 = r82.getDimensionPixelSize(R.styleable.Slider_thumbHeight, r912);
        setThumbWidth(r0);
        setThumbHeight(r913);
        setHaloRadius(r82.getDimensionPixelSize(R.styleable.Slider_haloRadius, 0));
        setThumbElevation(r82.getDimension(R.styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(r82.getDimensionPixelSize(R.styleable.Slider_trackHeight, 0));
        setTickActiveRadius(r82.getDimensionPixelSize(R.styleable.Slider_tickRadiusActive, this.trackStopIndicatorSize / 2));
        setTickInactiveRadius(r82.getDimensionPixelSize(R.styleable.Slider_tickRadiusInactive, this.trackStopIndicatorSize / 2));
        setLabelBehavior(r82.getInt(R.styleable.Slider_labelBehavior, 0));
        if (r82.getBoolean(R.styleable.Slider_android_enabled, true) == true) goto L50;
        setEnabled(false);
    L50:
        setValues(new Float[]{Float.valueOf(this.valueFrom)});
        r82.recycle();
        return;
    L38:
        r910 = R.styleable.Slider_tickColorActive;
        goto L39
    L35:
        r3 = R.styleable.Slider_tickColorInactive;
        goto L36
    L31:
        r98 = convertToTickVisibilityMode(r82.getBoolean(R.styleable.Slider_tickVisible, true));
        goto L32
    L8:
        r94 = R.styleable.Slider_trackColorActive;
        goto L9
    L5:
        r1 = R.styleable.Slider_trackColorInactive;
        goto L6
    }

    private void resetThumbWidth() {
        if (hasGapBetweenThumbAndTrack() == false) goto L10;
        int r0 = this.defaultThumbWidth;
        if (r0 != (-1)) goto L7;
        return;
    L7:
        if (this.defaultThumbTrackGapSize == (-1)) goto L12;
        setThumbSize(r0, this.defaultThumbHeight, Integer.valueOf(this.activeThumbIdx));
        return;
    L12:
        return;
    }

    private void scheduleAccessibilityEventSender(int r3) {
        BaseSlider<S, L, T>.AccessibilityEventSender r0 = this.accessibilityEventSender;
        if (r0 != null) goto L5;
        this.accessibilityEventSender = new AccessibilityEventSender(this, null);
    L6:
        this.accessibilityEventSender.setVirtualViewId(r3);
        postDelayed(this.accessibilityEventSender, 200);
        return;
    L5:
        removeCallbacks(r0);
        goto L6
    }

    private void setThumbSize(int r7, int r8, Integer r9) {
        int r1 = 0;
    L4:
        if (r1 >= this.defaultThumbDrawables.size()) goto L14;
        if (r9 != null) goto L7;
    L8:
        this.defaultThumbDrawables.get(r1).setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, r7 / 2.0f).build());
        MaterialShapeDrawable r2 = this.defaultThumbDrawables.get(r1);
        if (r8 < 0) goto L11;
        int r3 = r8;
    L12:
        r2.setBounds(0, 0, r7, r3);
    L13:
        r1 = r1 + 1;
        goto L4
    L11:
        r3 = this.thumbHeight;
        goto L12
    L7:
        if (r1 != r9.intValue()) goto L13;
    L14:
        updateWidgetLayout(false);
    }

    private void setValueForLabel(TooltipDrawable r2, float r3) {
        r2.setText(formatValue(r3));
        positionLabel(r2, r3);
        ViewOverlay r1 = getContentViewOverlay();
        if (r1 != null) goto L5;
        return;
    L5:
        r1.add(r2);
    }

    private void setValuesInternal(ArrayList<Float> r3) {
        if (r3.isEmpty() == true) goto L11;
        Collections.sort(r3);
        if (this.values.size() == r3.size()) goto L7;
    L9:
        this.values = r3;
        this.dirtyConfig = true;
        updateDefaultThumbDrawables();
        this.focusedThumbIdx = 0;
        updateHaloHotspot();
        createLabelPool();
        dispatchOnChangedProgrammatically();
        postInvalidate();
        return;
    L7:
        if (this.values.equals(r3) == false) goto L9;
        return;
    L11:
        f$$ExternalSyntheticBUOutline1.m("At least one value must be set");
    }

    private boolean shouldAlwaysShowLabel() {
        if (this.labelBehavior != 3) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean shouldDrawCompatHalo() {
        if (this.forceDrawCompatHalo == false) goto L5;
        return true;
    L5:
        if (getBackgroundRipple() == null) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean snapActiveThumbToValue(float r2) {
        return snapThumbToValue(this.activeThumbIdx, r2);
    }

    private double snapPosition(float r3) {
        float r0 = this.stepSize;
        if (r0 <= 0.0f) goto L7;
        return Math.round(r3 * r2) / ((int) ((this.valueTo - this.valueFrom) / r0));
    L7:
        return r3;
    }

    private void snapThumbToPreviousDownTouchEventValue() {
        if (this.activeThumbIdx != (-1)) goto L5;
        return;
    L5:
        if (this.previousDownTouchEventValues.isEmpty() == true) goto L17;
        int r0 = 0;
    L8:
        if (r0 >= this.values.size()) goto L18;
        if (r0 == this.activeThumbIdx) goto L11;
        r0 = r0 + 1;
        goto L8
    L11:
        snapThumbToValue(r0, this.previousDownTouchEventValues.get(r0).floatValue());
        return;
    L18:
        return;
    }

    private boolean snapThumbToValue(int r5, float r6) {
        this.focusedThumbIdx = r5;
        if (Math.abs(r6 - this.values.get(r5).floatValue()) >= THRESHOLD) goto L6;
        return false;
    L6:
        this.values.set(r5, Float.valueOf(getClampedValue(r5, r6)));
        dispatchOnChangedFromUser(r5);
        return true;
    }

    private boolean snapTouchPosition() {
        return snapActiveThumbToValue(getValueOfTouchPosition());
    }

    private void updateDefaultThumbDrawables() {
        if (this.defaultThumbDrawables.size() == this.values.size()) goto L8;
        this.defaultThumbDrawables.clear();
        int r0 = 0;
    L6:
        if (r0 >= this.values.size()) goto L10;
        this.defaultThumbDrawables.add(createNewDefaultThumb());
        r0 = r0 + 1;
        goto L6
    L10:
        return;
    }

    private void updateFocusRingBounds(float r7, float r8) {
        FocusRingDrawable r0 = getFocusRing();
        if (r0 == null) goto L10;
        float r1 = getResources().getDimensionPixelOffset(R.dimen.m3_slider_focus_ring_padding);
        float r2 = (this.thumbWidth / 2.0f) + (r1 * 2.0f);
        float r4 = (this.thumbHeight / 2.0f) + r1;
        if (isVertical() == true) goto L7;
        float r6 = r7 - r2;
        float r72 = r7 + r2;
        float r12 = r8 - r4;
        float r82 = r8 + r4;
    L8:
        r0.mutate();
        r0.setFocusRingBounds((int) r6, (int) r12, (int) r72, (int) r82);
        return;
    L7:
        r6 = r8 - r4;
        float r83 = r8 + r4;
        r12 = r7 - r2;
        r82 = r7 + r2;
        r72 = r83;
        goto L8
    }

    private void updateHaloHotspot() {
        float r0 = (normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * this.trackWidth) + this.trackSidePadding;
        int r1 = calculateTrackCenter();
        if (shouldDrawCompatHalo() == false) goto L5;
    L12:
        updateFocusRingBounds(r0, r1);
        return;
    L5:
        if (getMeasuredWidth() <= 0) goto L12;
        RippleDrawable r2 = getBackgroundRipple();
        if (r2 == null) goto L12;
        int r3 = this.haloRadius;
        float[] r7 = {r0 - r3, r1 - r3, r3 + r0, r3 + r1};
        if (isVertical() == false) goto L11;
        this.rotationMatrix.mapPoints(r7);
    L11:
        r2.setHotspotBounds((int) r7[0], (int) r7[1], (int) r7[2], (int) r7[3]);
        goto L12
    }

    private void updateLabelPivots() {
        boolean r0 = isVertical();
        boolean r1 = isRtl();
        float r2 = 0.5f;
        if (r0 == false) goto L6;
        if (r1 == false) goto L6;
        float r12 = 0.5f;
        r2 = -0.2f;
    L9:
        Iterator<TooltipDrawable> r4 = this.labels.iterator();
    L11:
        if (r4.hasNext() == false) goto L13;
        r4.next().setPivots(r2, r12);
        goto L11
    L13:
        return;
    L6:
        r12 = 1.2f;
        if (r0 == false) goto L9;
        r2 = 1.2f;
        r12 = 0.5f;
        goto L9
    }

    private void updateLabels() {
        updateLabelPivots();
        int r0 = this.labelBehavior;
        if (r0 == 0) goto L23;
        if (r0 == 1) goto L23;
        if (r0 != 2) goto L9;
        ensureLabelsRemoved();
        return;
    L9:
        if (r0 == 3) goto L11;
        IntegerType$$ExternalSyntheticBUOutline0.m("Unexpected labelBehavior: ", this.labelBehavior);
        return;
    L11:
        if (isEnabled() == true) goto L13;
    L16:
        ensureLabelsRemoved();
        return;
    L13:
        if (isSliderVisibleOnScreen() == false) goto L16;
        ensureLabelsAdded(true);
        return;
    L23:
        if (this.activeThumbIdx != (-1)) goto L25;
    L28:
        ensureLabelsRemoved();
        return;
    L25:
        if (isEnabled() == false) goto L28;
        ensureLabelsAdded(false);
    }

    private void updateRotationMatrix() {
        float r0 = calculateTrackCenter();
        this.rotationMatrix.reset();
        this.rotationMatrix.setRotate(90.0f, r0, r0);
    }

    private void updateThumbWidthWhenPressed() {
        if (hasGapBetweenThumbAndTrack() == true) goto L5;
        return;
    L5:
        if (this.customThumbDrawable == null) goto L7;
        return;
    L7:
        if (this.customThumbDrawablesForValues.isEmpty() == false) goto L16;
        int r0 = this.thumbWidth;
        this.defaultThumbWidth = r0;
        this.defaultThumbHeight = this.thumbHeight;
        this.defaultThumbTrackGapSize = this.thumbTrackGapSize;
        int r02 = Math.round(r0 * 0.5f);
        if (isFocusRingEnabled() == false) goto L11;
        int r1 = this.thumbHeight - this.thumbHeightDecreaseFocusRing;
    L12:
        setThumbSize(r02, r1, Integer.valueOf(this.activeThumbIdx));
        return;
    L11:
        r1 = -1;
        goto L12
    }

    private void updateTicksCoordinates() {
        validateConfigurationIfDirty();
        if (this.stepSize > 0.0f) goto L6;
        updateTicksCoordinates(this.continuousModeTickCount);
        return;
    L6:
        int r0 = this.tickVisibilityMode;
        if (r0 == 0) goto L18;
        int r2 = 0;
        if (r0 != 1) goto L11;
        int r02 = getDesiredTickCount();
        if (r02 > getMaxTickCount()) goto L19;
        r2 = r02;
    L19:
        updateTicksCoordinates(r2);
        return;
    L11:
        if (r0 == 2) goto L19;
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("Unexpected tickVisibilityMode: ", this.tickVisibilityMode);
        return;
    L18:
        r2 = Math.min(getDesiredTickCount(), getMaxTickCount());
        goto L19
    }

    private void updateTicksCoordinates(int r8) {
        if (r8 != 0) goto L5;
        this.ticksCoordinates = null;
        return;
    L5:
        float[] r0 = this.ticksCoordinates;
        if (r0 != null) goto L8;
    L9:
        this.ticksCoordinates = new float[r8 * 2];
    L10:
        float r02 = this.trackWidth / (r8 - 1);
        float r1 = calculateTrackCenter();
        int r2 = 0;
    L12:
        if (r2 >= (r8 * 2)) goto L15;
        float[] r3 = this.ticksCoordinates;
        r3[r2] = this.trackSidePadding + ((r2 / 2.0f) * r02);
        r3[r2 + 1] = r1;
        r2 = r2 + 2;
        goto L12
    L15:
        if (isVertical() == false) goto L19;
        this.rotationMatrix.mapPoints(this.ticksCoordinates);
        return;
    L19:
        return;
    L8:
        if (r0.length == (r8 * 2)) goto L10;
        goto L9
    }

    private void updateTrack(Canvas r7, Paint r8, RectF r9, float r10, FullCornerDirection r11) {
        if (r9.isEmpty() == false) goto L5;
        return;
    L5:
        float r0 = calculateStartTrackCornerSize(r10);
        float r102 = calculateEndTrackCornerSize(r10);
        int r1 = r11.ordinal();
        if (r1 == 1) goto L14;
        if (r1 != 2) goto L9;
        r0 = this.trackInsideCornerSize;
    L15:
        r8.setStyle(Paint.Style.FILL);
        r8.setStrokeCap(Paint.Cap.BUTT);
        if (hasGapBetweenThumbAndTrack() == false) goto L18;
        r8.setAntiAlias(true);
    L18:
        RectF r12 = new RectF(r9);
        if (isVertical() == false) goto L21;
        this.rotationMatrix.mapRect(r12);
    L21:
        this.trackPath.reset();
        if (r9.width() < (r0 + r102)) goto L25;
        this.trackPath.addRoundRect(r12, getCornerRadii(r0, r102), Path.Direction.CW);
        r7.drawPath(this.trackPath, r8);
        return;
    L25:
        float r4 = Math.min(r0, r102);
        float r103 = Math.max(r0, r102);
        r7.save();
        this.trackPath.addRoundRect(r12, r4, r4, Path.Direction.CW);
        r7.clipPath(this.trackPath);
        int r112 = r11.ordinal();
        if (r112 == 1) goto L31;
        RectF r13 = this.cornerRect;
        if (r112 == 2) goto L30;
        r13.set(r9.centerX() - r103, r9.top, r9.centerX() + r103, r9.bottom);
    L33:
        if (isVertical() == false) goto L35;
        this.rotationMatrix.mapRect(this.cornerRect);
    L35:
        r7.drawRoundRect(this.cornerRect, r103, r103, r8);
        r7.restore();
        return;
    L30:
        float r113 = r9.right;
        r13.set(r113 - (2.0f * r103), r9.top, r113, r9.bottom);
        goto L33
    L31:
        RectF r114 = this.cornerRect;
        float r14 = r9.left;
        r114.set(r14, r9.top, (2.0f * r103) + r14, r9.bottom);
        goto L33
    L9:
        if (r1 != 3) goto L15;
        int r104 = this.trackInsideCornerSize;
        r0 = r104;
    L12:
        r102 = r104;
        goto L15
    L14:
        r104 = this.trackInsideCornerSize;
        goto L12
    }

    private void updateTrackIconActiveEnd() {
        Drawable r0 = this.trackIconActiveEnd;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.trackIconActiveEndMutated == true) goto L10;
        if (this.trackIconActiveColor == null) goto L10;
        this.trackIconActiveEnd = DrawableCompat.wrap(r0).mutate();
        this.trackIconActiveEndMutated = true;
    L10:
        if (this.trackIconActiveEndMutated == false) goto L14;
        this.trackIconActiveEnd.setTintList(this.trackIconActiveColor);
        return;
    }

    private void updateTrackIconActiveStart() {
        Drawable r0 = this.trackIconActiveStart;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.trackIconActiveStartMutated == true) goto L10;
        if (this.trackIconActiveColor == null) goto L10;
        this.trackIconActiveStart = DrawableCompat.wrap(r0).mutate();
        this.trackIconActiveStartMutated = true;
    L10:
        if (this.trackIconActiveStartMutated == false) goto L14;
        this.trackIconActiveStart.setTintList(this.trackIconActiveColor);
        return;
    }

    private void updateTrackIconInactiveEnd() {
        Drawable r0 = this.trackIconInactiveEnd;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.trackIconInactiveEndMutated == true) goto L10;
        if (this.trackIconInactiveColor == null) goto L10;
        this.trackIconInactiveEnd = DrawableCompat.wrap(r0).mutate();
        this.trackIconInactiveEndMutated = true;
    L10:
        if (this.trackIconInactiveEndMutated == false) goto L14;
        this.trackIconInactiveEnd.setTintList(this.trackIconInactiveColor);
        return;
    }

    private void updateTrackIconInactiveStart() {
        Drawable r0 = this.trackIconInactiveStart;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.trackIconInactiveStartMutated == true) goto L10;
        if (this.trackIconInactiveColor == null) goto L10;
        this.trackIconInactiveStart = DrawableCompat.wrap(r0).mutate();
        this.trackIconInactiveStartMutated = true;
    L10:
        if (this.trackIconInactiveStartMutated == false) goto L14;
        this.trackIconInactiveStart.setTintList(this.trackIconInactiveColor);
        return;
    }

    private void updateTrackWidth(int r2) {
        this.trackWidth = Math.max(r2 - (this.trackSidePadding * 2), 0);
        updateTicksCoordinates();
    }

    private void updateWidgetLayout(boolean r4) {
        boolean r0 = maybeIncreaseWidgetThickness();
        boolean r1 = maybeIncreaseTrackSidePadding();
        if (isVertical() == false) goto L5;
        updateRotationMatrix();
    L5:
        if (r0 == true) goto L11;
        if (r4 == true) goto L11;
        if (r1 == false) goto L13;
        postInvalidate();
        return;
    L13:
        return;
    L11:
        requestLayout();
    }

    private void validateConfigurationIfDirty() {
        if (this.dirtyConfig == false) goto L6;
        validateValues();
        validateStepSize();
        validateMinSeparation();
        warnAboutFloatingPointError();
        this.dirtyConfig = false;
        return;
    }

    private void validateMinSeparation() {
        float r0 = getMinSeparation();
        if (r0 < 0.0f) goto L19;
        float r2 = this.stepSize;
        if (r2 > 0.0f) goto L7;
        return;
    L7:
        if (r0 > 0.0f) goto L9;
        return;
    L9:
        if (this.separationUnit == 1) goto L11;
        EncodedValueReader$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT, new Object[]{Float.valueOf(r0), Float.valueOf(this.stepSize)});
        return;
    L11:
        if (r0 >= r2) goto L13;
    L15:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE, new Object[]{Float.valueOf(r0), Float.valueOf(this.stepSize), Float.valueOf(this.stepSize)});
        return;
    L13:
        if (isMultipleOfStepSize(r0) == false) goto L15;
        return;
    L19:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_MIN_SEPARATION, new Object[]{Float.valueOf(r0)});
    }

    private void validateStepSize() {
        if (this.stepSize > 0.0f) goto L5;
        return;
    L5:
        if (valueLandsOnTick(this.valueTo) == true) goto L10;
        EncodedValueReader$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_STEP_SIZE, new Object[]{Float.valueOf(this.stepSize), Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)});
        return;
    }

    private void validateValues() {
        float r0 = this.valueFrom;
        if (r0 >= this.valueTo) goto L19;
        ArrayList<Float> r02 = this.values;
        int r1 = r02.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L29;
        Float r3 = r02.get(r2);
        r2 = r2 + 1;
        Float r32 = r3;
        if (r32.floatValue() < this.valueFrom) goto L17;
        if (r32.floatValue() > this.valueTo) goto L17;
        if (this.stepSize <= 0.0f) goto L5;
        if (valueLandsOnTick(r32.floatValue()) == true) goto L5;
        EncodedValueReader$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_DISCRETE_VALUE, new Object[]{r32, Float.valueOf(this.valueFrom), Float.valueOf(this.stepSize), Float.valueOf(this.stepSize)});
        return;
    L17:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_VALUE, new Object[]{r32, Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)});
        return;
    L29:
        return;
    L19:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_VALUE_FROM, new Object[]{Float.valueOf(r0), Float.valueOf(this.valueTo)});
    }

    private boolean valueLandsOnTick(float r3) {
        return isMultipleOfStepSize(new BigDecimal(Float.toString(r3)).subtract(new BigDecimal(Float.toString(this.valueFrom)), MathContext.DECIMAL64).doubleValue());
    }

    private float valueToX(float r2) {
        return (normalizeValue(r2) * this.trackWidth) + this.trackSidePadding;
    }

    private void warnAboutFloatingPointError() {
        float r0 = this.stepSize;
        if (r0 != 0.0f) goto L6;
        return;
    L6:
        if (((int) r0) == r0) goto L8;
        Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, new Object[]{"stepSize", Float.valueOf(r0)}));
    L8:
        float r02 = this.valueFrom;
        if (((int) r02) == r02) goto L11;
        Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, new Object[]{"valueFrom", Float.valueOf(r02)}));
    L11:
        float r4 = this.valueTo;
        if (((int) r4) == r4) goto L15;
        Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, new Object[]{"valueTo", Float.valueOf(r4)}));
        return;
    }

    public void addOnChangeListener(L r1) {
        this.changeListeners.add(r1);
    }

    public void addOnSliderTouchListener(T r1) {
        this.touchListeners.add(r1);
    }

    public void clearOnChangeListeners() {
        this.changeListeners.clear();
    }

    public void clearOnSliderTouchListeners() {
        this.touchListeners.clear();
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent r2) {
        if (this.accessibilityHelper.dispatchHoverEvent(r2) == false) goto L5;
        return true;
    L5:
        if (super.dispatchHoverEvent(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent r1) {
        return super.dispatchKeyEvent(r1);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.inactiveTrackPaint.setColor(getColorForState(this.trackColorInactive));
        this.activeTrackPaint.setColor(getColorForState(this.trackColorActive));
        this.inactiveTicksPaint.setColor(getColorForState(this.tickColorInactive));
        this.activeTicksPaint.setColor(getColorForState(this.tickColorActive));
        this.stopIndicatorPaint.setColor(getColorForState(this.tickColorInactive));
        Iterator<TooltipDrawable> r0 = this.labels.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        TooltipDrawable r1 = r0.next();
        if (r1.isStateful() == false) goto L4;
        r1.setState(getDrawableState());
        goto L4
    L8:
        int r02 = 0;
    L10:
        if (r02 >= this.defaultThumbDrawables.size()) goto L15;
        if (this.defaultThumbDrawables.get(r02).isStateful() == false) goto L14;
        this.defaultThumbDrawables.get(r02).setState(getDrawableState());
    L14:
        r02 = r02 + 1;
        goto L10
    L15:
        this.haloPaint.setColor(getColorForState(this.haloColor));
        this.haloPaint.setAlpha(63);
    }

    public void forceDrawCompatHalo(boolean r1) {
        this.forceDrawCompatHalo = r1;
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.accessibilityHelper.getAccessibilityFocusedVirtualViewId();
    }

    public int getActiveThumbIndex() {
        return this.activeThumbIdx;
    }

    public int getContinuousModeTickCount() {
        return this.continuousModeTickCount;
    }

    public int getFocusedThumbIndex() {
        return this.focusedThumbIdx;
    }

    public int getHaloRadius() {
        return this.haloRadius;
    }

    public ColorStateList getHaloTintList() {
        return this.haloColor;
    }

    public int getLabelBehavior() {
        return this.labelBehavior;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public float getThumbElevation() {
        return this.thumbElevation;
    }

    public int getThumbHeight() {
        return this.thumbHeight;
    }

    public int getThumbRadius() {
        return this.thumbWidth / 2;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.thumbStrokeColor;
    }

    public float getThumbStrokeWidth() {
        return this.thumbStrokeWidth;
    }

    public ColorStateList getThumbTintList() {
        return this.thumbTintList;
    }

    public int getThumbTrackGapSize() {
        return this.thumbTrackGapSize;
    }

    public int getThumbWidth() {
        return this.thumbWidth;
    }

    public int getTickActiveRadius() {
        return this.tickActiveRadius;
    }

    public ColorStateList getTickActiveTintList() {
        return this.tickColorActive;
    }

    public int getTickInactiveRadius() {
        return this.tickInactiveRadius;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.tickColorInactive;
    }

    public ColorStateList getTickTintList() {
        if (this.tickColorInactive.equals(this.tickColorActive) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
        return null;
    L5:
        return this.tickColorActive;
    }

    public int getTickVisibilityMode() {
        return this.tickVisibilityMode;
    }

    public ColorStateList getTrackActiveTintList() {
        return this.trackColorActive;
    }

    public int getTrackCornerSize() {
        int r0 = this.trackCornerSize;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        return this.trackThickness / 2;
    }

    public int getTrackHeight() {
        return this.trackThickness;
    }

    public ColorStateList getTrackIconActiveColor() {
        return this.trackIconActiveColor;
    }

    public Drawable getTrackIconActiveEnd() {
        return this.trackIconActiveEnd;
    }

    public Drawable getTrackIconActiveStart() {
        return this.trackIconActiveStart;
    }

    public ColorStateList getTrackIconInactiveColor() {
        return this.trackIconInactiveColor;
    }

    public Drawable getTrackIconInactiveEnd() {
        return this.trackIconInactiveEnd;
    }

    public Drawable getTrackIconInactiveStart() {
        return this.trackIconInactiveStart;
    }

    public int getTrackIconSize() {
        return this.trackIconSize;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.trackColorInactive;
    }

    public int getTrackInsideCornerSize() {
        return this.trackInsideCornerSize;
    }

    public int getTrackSidePadding() {
        return this.trackSidePadding;
    }

    public int getTrackStopIndicatorSize() {
        return this.trackStopIndicatorSize;
    }

    public ColorStateList getTrackTintList() {
        if (this.trackColorInactive.equals(this.trackColorActive) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
        return null;
    L5:
        return this.trackColorActive;
    }

    public int getTrackWidth() {
        return this.trackWidth;
    }

    public float getValueFrom() {
        return this.valueFrom;
    }

    public float getValueTo() {
        return this.valueTo;
    }

    public List<Float> getValues() {
        return new ArrayList(this.values);
    }

    public boolean hasLabelFormatter() {
        if (this.formatter == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isCentered() {
        return this.centered;
    }

    public final boolean isRtl() {
        if (getLayoutDirection() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isTickVisible() {
        int r0 = this.tickVisibilityMode;
        if (r0 != 0) goto L5;
        return true;
    L5:
        if (r0 == 1) goto L12;
        if (r0 != 2) goto L9;
        return false;
    L9:
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("Unexpected tickVisibilityMode: ", this.tickVisibilityMode);
        return false;
    L12:
        if (getDesiredTickCount() > getMaxTickCount()) goto L14;
        return true;
    L14:
        return false;
    }

    public boolean isVertical() {
        if (this.widgetOrientation != 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.thisAndAncestorsVisible = isShown();
        getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
        getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
        Iterator<TooltipDrawable> r0 = this.labels.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        attachLabelToContentView(r0.next());
        goto L4
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.AccessibilityEventSender r0 = this.accessibilityEventSender;
        if (r0 == null) goto L5;
        removeCallbacks(r0);
    L5:
        this.labelsAreAnimatedIn = false;
        Iterator<TooltipDrawable> r02 = this.labels.iterator();
    L7:
        if (r02.hasNext() == false) goto L9;
        detachLabelFromContentView(r02.next());
        goto L7
    L9:
        getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
        getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r4) {
        if (this.dirtyConfig == false) goto L5;
        validateConfigurationIfDirty();
        updateTicksCoordinates();
    L5:
        super.onDraw(r4);
        int r0 = calculateTrackCenter();
        drawInactiveTracks(r4, this.trackWidth, r0);
        drawActiveTracks(r4, this.trackWidth, r0);
        if (isRtl() == false) goto L8;
    L11:
        drawTrackIcons(r4, this.activeTrackRect, this.inactiveTrackLeftRect);
    L12:
        maybeDrawTicks(r4);
        maybeDrawStopIndicator(r4, r0);
        if (this.thumbIsPressed == true) goto L17;
        if (isFocused() == true) goto L17;
    L19:
        updateLabels();
        drawThumbs(r4, this.trackWidth, r0);
        return;
    L17:
        if (isEnabled() == false) goto L19;
        maybeDrawCompatHalo(r4, this.trackWidth, r0);
        goto L19
    L8:
        if (isVertical() == true) goto L11;
        drawTrackIcons(r4, this.activeTrackRect, this.inactiveTrackRightRect);
        goto L12
    }

    @Override // android.view.View
    public void onFocusChanged(boolean r1, int r2, Rect r3) {
        super.onFocusChanged(r1, r2, r3);
        if (r1 == true) goto L7;
        resetThumbWidth();
        this.activeThumbIdx = -1;
        this.accessibilityHelper.clearKeyboardFocusForVirtualView(this.focusedThumbIdx);
        return;
    L7:
        if (this.activeThumbIdx != (-1)) goto L9;
        focusThumbOnFocusGained(r2);
        this.activeThumbIdx = this.focusedThumbIdx;
    L9:
        resetThumbWidth();
        updateThumbWidthWhenPressed();
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(this.focusedThumbIdx);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        r1.setVisibleToUser(false);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r3, KeyEvent r4) {
        if (isEnabled() == false) goto L5;
        this.activeThumbIdx = this.focusedThumbIdx;
        this.isLongPress |= r4.isLongPress();
        Float r0 = calculateIncrementForKey(r3);
        if (r0 == null) goto L13;
        if (snapActiveThumbToValue(this.values.get(this.activeThumbIdx).floatValue() + r0.floatValue()) == false) goto L11;
        updateHaloHotspot();
        postInvalidate();
    L11:
        return true;
    L13:
        if (r3 != 61) goto L25;
        resetThumbWidth();
        if (r4.hasNoModifiers() == false) goto L19;
        return moveFocus(1);
    L19:
        if (r4.isShiftPressed() == true) goto L21;
        return false;
    L21:
        return moveFocus(-1);
    L25:
        return super.onKeyDown(r3, r4);
    L5:
        return super.onKeyDown(r3, r4);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int r2, KeyEvent r3) {
        this.isLongPress = false;
        return super.onKeyUp(r2, r3);
    }

    @Override // android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        Rect r22 = this.viewRect;
        r22.left = 0;
        r22.top = 0;
        r22.right = r5 - r3;
        r22.bottom = r6 - r4;
        if (this.exclusionRects.contains(r22) == true) goto L5;
        this.exclusionRects.add(this.viewRect);
    L5:
        ViewCompat.setSystemGestureExclusionRects(this, this.exclusionRects);
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        int r2 = 0;
        if (this.labelBehavior != 1) goto L5;
    L6:
        r2 = this.labels.get(0).getIntrinsicHeight();
    L7:
        int r0 = View.MeasureSpec.makeMeasureSpec(this.widgetThickness + r2, TLObject.FLAG_30);
        if (isVertical() == false) goto L11;
        super.onMeasure(r0, r5);
        return;
    L11:
        super.onMeasure(r4, r0);
        return;
    L5:
        if (shouldAlwaysShowLabel() == false) goto L7;
        goto L6
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        SliderState r22 = (SliderState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        this.valueFrom = r22.valueFrom;
        this.valueTo = r22.valueTo;
        setValuesInternal(r22.values);
        this.stepSize = r22.stepSize;
        if (r22.hasFocus == false) goto L6;
        requestFocus();
        return;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState r1 = new SliderState(super.onSaveInstanceState());
        r1.valueFrom = this.valueFrom;
        r1.valueTo = this.valueTo;
        r1.values = new ArrayList(this.values);
        r1.stepSize = this.stepSize;
        r1.hasFocus = hasFocus();
        return r1;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        if (isVertical() == false) goto L5;
        r1 = r2;
    L5:
        updateTrackWidth(r1);
        updateHaloHotspot();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (isEnabled() == true) goto L6;
        return false;
    L6:
        if (isVertical() == false) goto L8;
        float r0 = r8.getY();
    L10:
        if (isVertical() == false) goto L12;
        float r2 = r8.getX();
    L13:
        float r3 = (r0 - this.trackSidePadding) / this.trackWidth;
        this.touchPosition = r3;
        float r32 = Math.max(0.0f, r3);
        this.touchPosition = r32;
        this.touchPosition = Math.min(1.0f, r32);
        int r33 = r8.getActionMasked();
        if (r33 != 0) goto L16;
        this.touchDownAxis1 = r0;
        this.touchDownAxis2 = r2;
        this.previousDownTouchEventValues.clear();
        this.previousDownTouchEventValues = getValues();
        if (isVertical() == true) goto L65;
        if (isPotentialVerticalScroll(r8) == false) goto L65;
    L73:
        setPressed(this.thumbIsPressed);
        this.lastEvent = MotionEvent.obtain(r8);
        return true;
    L65:
        if (isVertical() == true) goto L67;
    L69:
        getParent().requestDisallowInterceptTouchEvent(true);
        if (pickActiveThumb() == false) goto L73;
        requestFocus();
        this.thumbIsPressed = true;
        updateThumbWidthWhenPressed();
        onStartTrackingTouch();
        snapTouchPosition();
        updateHaloHotspot();
        invalidate();
        goto L73
    L67:
        if (isPotentialHorizontalScroll(r8) == false) goto L69;
    L16:
        if (r33 != 1) goto L18;
        this.thumbIsPressed = false;
        MotionEvent r02 = this.lastEvent;
        if (r02 == null) goto L56;
        if (r02.getActionMasked() != 0) goto L56;
        if (Math.abs(this.lastEvent.getX() - r8.getX()) > this.scaledTouchSlop) goto L56;
        if (Math.abs(this.lastEvent.getY() - r8.getY()) > this.scaledTouchSlop) goto L56;
        if (pickActiveThumb() == false) goto L56;
        onStartTrackingTouch();
    L56:
        if (this.activeThumbIdx == (-1)) goto L58;
        snapTouchPosition();
        updateHaloHotspot();
        resetThumbWidth();
        this.activeThumbIdx = -1;
        onStopTrackingTouch();
    L58:
        invalidate();
        goto L73
    L18:
        if (r33 == 2) goto L24;
        if (r33 != 3) goto L73;
        this.thumbIsPressed = false;
        snapThumbToPreviousDownTouchEventValue();
        updateHaloHotspot();
        resetThumbWidth();
        this.activeThumbIdx = -1;
        onStopTrackingTouch();
        invalidate();
        goto L73
    L24:
        if (this.thumbIsPressed == false) goto L26;
    L43:
        snapTouchPosition();
        updateHaloHotspot();
        invalidate();
        goto L73
    L26:
        if (isVertical() == true) goto L33;
        if (isPotentialVerticalScroll(r8) == false) goto L33;
        if (Math.abs(r0 - this.touchDownAxis1) >= this.scaledTouchSlop) goto L33;
        return false;
    L33:
        if (isVertical() == true) goto L35;
    L39:
        getParent().requestDisallowInterceptTouchEvent(true);
        if (pickActiveThumb() == false) goto L73;
        this.thumbIsPressed = true;
        updateThumbWidthWhenPressed();
        onStartTrackingTouch();
        goto L43
    L35:
        if (isPotentialHorizontalScroll(r8) == false) goto L39;
        if (Math.abs(r2 - this.touchDownAxis2) >= (this.scaledTouchSlop * TOUCH_SLOP_RATIO)) goto L39;
        return false;
    L12:
        r2 = r8.getY();
        goto L13
    L8:
        r0 = r8.getX();
        goto L10
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean r1) {
        super.onVisibilityAggregated(r1);
        this.thisAndAncestorsVisible = r1;
    }

    @Override // android.view.View
    public void onVisibilityChanged(View r1, int r2) {
        super.onVisibilityChanged(r1, r2);
        if (r2 == 0) goto L11;
        ViewOverlay r12 = getContentViewOverlay();
        if (r12 == null) goto L13;
        Iterator<TooltipDrawable> r0 = this.labels.iterator();
    L9:
        if (r0.hasNext() == false) goto L14;
        r12.remove(r0.next());
        goto L9
    L14:
        return;
    L13:
        return;
    }

    public boolean pickActiveThumb() {
        if (this.activeThumbIdx == (-1)) goto L5;
        return true;
    L5:
        float r0 = getValueOfTouchPositionAbsolute();
        float r3 = valueToX(r0);
        this.activeThumbIdx = 0;
        float r5 = Math.abs(this.values.get(0).floatValue() - r0);
        int r6 = 1;
    L7:
        if (r6 >= this.values.size()) goto L37;
        float r7 = Math.abs(this.values.get(r6).floatValue() - r0);
        float r8 = valueToX(this.values.get(r6).floatValue());
        if (Float.compare(r7, r5) > 0) goto L37;
        if (isRtl() == true) goto L21;
        if (isVertical() == true) goto L21;
        if ((r8 - r3) >= 0.0f) goto L19;
    L18:
        boolean r9 = true;
    L24:
        if (Float.compare(r7, r5) >= 0) goto L27;
        this.activeThumbIdx = r6;
    L34:
        r5 = r7;
    L35:
        r6 = r6 + 1;
        goto L7
    L27:
        if (Float.compare(r7, r5) != 0) goto L35;
        if (Math.abs(r8 - r3) < this.scaledTouchSlop) goto L30;
        if (r9 == false) goto L35;
        this.activeThumbIdx = r6;
        goto L34
    L30:
        this.activeThumbIdx = -1;
        return false;
    L19:
        r9 = false;
    L21:
        if ((r8 - r3) <= 0.0f) goto L19;
    L37:
        if (this.activeThumbIdx == (-1)) goto L39;
        return true;
    L39:
        return false;
    }

    public void removeOnChangeListener(L r1) {
        this.changeListeners.remove(r1);
    }

    public void removeOnSliderTouchListener(T r1) {
        this.touchListeners.remove(r1);
    }

    public void scheduleTooltipTimeout() {
        removeCallbacks(this.resetActiveThumbIndex);
        postDelayed(this.resetActiveThumbIndex, this.tooltipTimeoutMillis);
    }

    public void setActiveThumbIndex(int r1) {
        this.activeThumbIdx = r1;
    }

    public void setCentered(boolean r2) {
        if (this.centered != r2) goto L5;
        return;
    L5:
        this.centered = r2;
        float r0 = this.valueFrom;
        if (r2 == false) goto L8;
        setValues(new Float[]{Float.valueOf((r0 + this.valueTo) / 2.0f)});
    L9:
        updateWidgetLayout(true);
        return;
    L8:
        setValues(new Float[]{Float.valueOf(r0)});
        goto L9
    }

    public void setContinuousModeTickCount(int r2) {
        if (r2 >= 0) goto L4;
        FieldSet$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_CONTINUOUS_MODE_TICK_COUNT, new Object[]{Integer.valueOf(r2)});
        return;
    L4:
        if (this.continuousModeTickCount == r2) goto L9;
        this.continuousModeTickCount = r2;
        this.dirtyConfig = true;
        postInvalidate();
        return;
    }

    public void setCustomThumbDrawable(int r2) {
        setCustomThumbDrawable(getResources().getDrawable(r2));
    }

    public void setCustomThumbDrawable(Drawable r1) {
        this.customThumbDrawable = initializeCustomThumbDrawable(r1);
        this.customThumbDrawablesForValues.clear();
        postInvalidate();
    }

    public void setCustomThumbDrawablesForValues(int... r5) {
        Drawable[] r0 = new Drawable[r5.length];
        int r1 = 0;
    L4:
        if (r1 >= r5.length) goto L6;
        r0[r1] = getResources().getDrawable(r5[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        setCustomThumbDrawablesForValues(r0);
    }

    public void setCustomThumbDrawablesForValues(Drawable... r5) {
        this.customThumbDrawable = null;
        this.customThumbDrawablesForValues = new ArrayList();
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Drawable r2 = r5[r1];
        this.customThumbDrawablesForValues.add(initializeCustomThumbDrawable(r2));
        r1 = r1 + 1;
        goto L3
    L5:
        postInvalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        super.setEnabled(r2);
        if (r2 == false) goto L5;
        int r22 = 0;
    L6:
        setLayerType(r22, null);
        return;
    L5:
        r22 = 2;
        goto L6
    }

    public void setFocusedThumbIndex(int r2) {
        if (r2 >= 0) goto L4;
    L7:
        f$$ExternalSyntheticBUOutline1.m("index out of range");
        return;
    L4:
        if (r2 >= this.values.size()) goto L7;
        this.focusedThumbIdx = r2;
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(r2);
        postInvalidate();
    }

    public void setHaloRadius(int r2) {
        if (r2 != this.haloRadius) goto L5;
        return;
    L5:
        this.haloRadius = r2;
        RippleDrawable r22 = getBackgroundRipple();
        if (shouldDrawCompatHalo() == true) goto L10;
        if (r22 == null) goto L10;
        DrawableUtils.setRippleDrawableRadius(r22, this.haloRadius);
        return;
    L10:
        postInvalidate();
    }

    public void setHaloRadiusResource(int r2) {
        setHaloRadius(getResources().getDimensionPixelSize(r2));
    }

    public void setHaloTintList(ColorStateList r3) {
        if (r3.equals(this.haloColor) == false) goto L5;
        return;
    L5:
        this.haloColor = r3;
        RippleDrawable r0 = getBackgroundRipple();
        if (shouldDrawCompatHalo() == true) goto L10;
        if (r0 == null) goto L10;
        r0.setColor(r3);
        return;
    L10:
        this.haloPaint.setColor(getColorForState(r3));
        this.haloPaint.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int r2) {
        if (this.labelBehavior == r2) goto L6;
        this.labelBehavior = r2;
        updateWidgetLayout(true);
        return;
    }

    public void setLabelFormatter(LabelFormatter r1) {
        this.formatter = r1;
    }

    public void setOrientation(int r2) {
        if (this.widgetOrientation != r2) goto L5;
        return;
    L5:
        this.widgetOrientation = r2;
        updateWidgetLayout(true);
    }

    public void setSeparationUnit(int r1) {
        this.separationUnit = r1;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setStepSize(float r2) {
        if (r2 >= 0.0f) goto L5;
        FieldSet$$ExternalSyntheticBUOutline0.m(EXCEPTION_ILLEGAL_STEP_SIZE, new Object[]{Float.valueOf(r2), Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)});
        return;
    L5:
        if (this.stepSize == r2) goto L10;
        this.stepSize = r2;
        this.dirtyConfig = true;
        postInvalidate();
        return;
    }

    public void setThumbElevation(float r3) {
        if (r3 == this.thumbElevation) goto L9;
        this.thumbElevation = r3;
        int r32 = 0;
    L7:
        if (r32 >= this.defaultThumbDrawables.size()) goto L11;
        this.defaultThumbDrawables.get(r32).setElevation(this.thumbElevation);
        r32 = r32 + 1;
        goto L7
    L11:
        return;
    }

    public void setThumbElevationResource(int r2) {
        setThumbElevation(getResources().getDimension(r2));
    }

    public void setThumbHeight(int r5) {
        if (r5 != this.thumbHeight) goto L5;
        return;
    L5:
        this.thumbHeight = r5;
        int r0 = 0;
    L7:
        if (r0 >= this.defaultThumbDrawables.size()) goto L9;
        this.defaultThumbDrawables.get(r0).setBounds(0, 0, this.thumbWidth, this.thumbHeight);
        r0 = r0 + 1;
        goto L7
    L9:
        Drawable r02 = this.customThumbDrawable;
        if (r02 == null) goto L12;
        adjustCustomThumbDrawableBounds(r02);
    L12:
        Iterator<Drawable> r03 = this.customThumbDrawablesForValues.iterator();
    L14:
        if (r03.hasNext() == false) goto L16;
        adjustCustomThumbDrawableBounds(r03.next());
        goto L14
    L16:
        updateWidgetLayout(false);
    }

    public void setThumbHeightResource(int r2) {
        setThumbHeight(getResources().getDimensionPixelSize(r2));
    }

    public void setThumbRadius(int r1) {
        int r12 = r1 * 2;
        setThumbWidth(r12);
        setThumbHeight(r12);
    }

    public void setThumbRadiusResource(int r2) {
        setThumbRadius(getResources().getDimensionPixelSize(r2));
    }

    public void setThumbStrokeColor(ColorStateList r3) {
        if (r3 != this.thumbStrokeColor) goto L5;
        return;
    L5:
        this.thumbStrokeColor = r3;
        int r0 = 0;
    L7:
        if (r0 >= this.defaultThumbDrawables.size()) goto L9;
        this.defaultThumbDrawables.get(r0).setStrokeColor(r3);
        r0 = r0 + 1;
        goto L7
    L9:
        postInvalidate();
    }

    public void setThumbStrokeColorResource(int r2) {
        if (r2 == 0) goto L5;
        setThumbStrokeColor(AppCompatResources.getColorStateList(getContext(), r2));
        return;
    }

    public void setThumbStrokeWidth(float r3) {
        if (r3 != this.thumbStrokeWidth) goto L5;
        return;
    L5:
        this.thumbStrokeWidth = r3;
        int r0 = 0;
    L7:
        if (r0 >= this.defaultThumbDrawables.size()) goto L9;
        this.defaultThumbDrawables.get(r0).setStrokeWidth(r3);
        r0 = r0 + 1;
        goto L7
    L9:
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(int r2) {
        if (r2 == 0) goto L5;
        setThumbStrokeWidth(getResources().getDimension(r2));
        return;
    }

    public void setThumbTintList(ColorStateList r3) {
        if (r3.equals(this.thumbTintList) == false) goto L5;
        return;
    L5:
        this.thumbTintList = r3;
        int r32 = 0;
    L7:
        if (r32 >= this.defaultThumbDrawables.size()) goto L9;
        this.defaultThumbDrawables.get(r32).setFillColor(this.thumbTintList);
        r32 = r32 + 1;
        goto L7
    L9:
        invalidate();
    }

    public void setThumbTrackGapSize(int r2) {
        if (this.thumbTrackGapSize != r2) goto L5;
        return;
    L5:
        this.thumbTrackGapSize = r2;
        invalidate();
    }

    public void setThumbWidth(int r3) {
        if (r3 != this.thumbWidth) goto L5;
        return;
    L5:
        this.thumbWidth = r3;
        Drawable r0 = this.customThumbDrawable;
        if (r0 == null) goto L8;
        adjustCustomThumbDrawableBounds(r3, r0);
    L8:
        int r02 = 0;
    L10:
        if (r02 >= this.customThumbDrawablesForValues.size()) goto L12;
        adjustCustomThumbDrawableBounds(r3, this.customThumbDrawablesForValues.get(r02));
        r02 = r02 + 1;
        goto L10
    L12:
        setThumbSize(r3, -1, null);
    }

    public void setThumbWidthResource(int r2) {
        setThumbWidth(getResources().getDimensionPixelSize(r2));
    }

    public void setTickActiveRadius(int r2) {
        if (this.tickActiveRadius == r2) goto L6;
        this.tickActiveRadius = r2;
        this.activeTicksPaint.setStrokeWidth(r2 * 2);
        updateWidgetLayout(false);
        return;
    }

    public void setTickActiveTintList(ColorStateList r2) {
        if (r2.equals(this.tickColorActive) == false) goto L5;
        return;
    L5:
        this.tickColorActive = r2;
        this.activeTicksPaint.setColor(getColorForState(r2));
        invalidate();
    }

    public void setTickInactiveRadius(int r2) {
        if (this.tickInactiveRadius == r2) goto L6;
        this.tickInactiveRadius = r2;
        this.inactiveTicksPaint.setStrokeWidth(r2 * 2);
        updateWidgetLayout(false);
        return;
    }

    public void setTickInactiveTintList(ColorStateList r2) {
        if (r2.equals(this.tickColorInactive) == false) goto L5;
        return;
    L5:
        this.tickColorInactive = r2;
        this.inactiveTicksPaint.setColor(getColorForState(r2));
        invalidate();
    }

    public void setTickTintList(ColorStateList r1) {
        setTickInactiveTintList(r1);
        setTickActiveTintList(r1);
    }

    public void setTickVisibilityMode(int r2) {
        if (this.tickVisibilityMode == r2) goto L6;
        this.tickVisibilityMode = r2;
        postInvalidate();
        return;
    }

    @Deprecated
    public void setTickVisible(boolean r1) {
        setTickVisibilityMode(convertToTickVisibilityMode(r1));
    }

    public void setTrackActiveTintList(ColorStateList r2) {
        if (r2.equals(this.trackColorActive) == false) goto L5;
        return;
    L5:
        this.trackColorActive = r2;
        this.activeTrackPaint.setColor(getColorForState(r2));
        invalidate();
    }

    public void setTrackCornerSize(int r2) {
        if (this.trackCornerSize != r2) goto L5;
        return;
    L5:
        this.trackCornerSize = r2;
        invalidate();
    }

    public void setTrackHeight(int r2) {
        if (this.trackThickness == r2) goto L6;
        this.trackThickness = r2;
        invalidateTrack();
        updateWidgetLayout(false);
        return;
    }

    public void setTrackIconActiveColor(ColorStateList r2) {
        if (r2 != this.trackIconActiveColor) goto L5;
        return;
    L5:
        this.trackIconActiveColor = r2;
        updateTrackIconActiveStart();
        updateTrackIconActiveEnd();
        invalidate();
    }

    public void setTrackIconActiveEnd(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setTrackIconActiveEnd(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setTrackIconActiveEnd(Drawable r2) {
        if (r2 != this.trackIconActiveEnd) goto L5;
        return;
    L5:
        this.trackIconActiveEnd = r2;
        this.trackIconActiveEndMutated = false;
        updateTrackIconActiveEnd();
        invalidate();
    }

    public void setTrackIconActiveStart(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setTrackIconActiveStart(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setTrackIconActiveStart(Drawable r2) {
        if (r2 != this.trackIconActiveStart) goto L5;
        return;
    L5:
        this.trackIconActiveStart = r2;
        this.trackIconActiveStartMutated = false;
        updateTrackIconActiveStart();
        invalidate();
    }

    public void setTrackIconInactiveColor(ColorStateList r2) {
        if (r2 != this.trackIconInactiveColor) goto L5;
        return;
    L5:
        this.trackIconInactiveColor = r2;
        updateTrackIconInactiveStart();
        updateTrackIconInactiveEnd();
        invalidate();
    }

    public void setTrackIconInactiveEnd(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setTrackIconInactiveEnd(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setTrackIconInactiveEnd(Drawable r2) {
        if (r2 != this.trackIconInactiveEnd) goto L5;
        return;
    L5:
        this.trackIconInactiveEnd = r2;
        this.trackIconInactiveEndMutated = false;
        updateTrackIconInactiveEnd();
        invalidate();
    }

    public void setTrackIconInactiveStart(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setTrackIconInactiveStart(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setTrackIconInactiveStart(Drawable r2) {
        if (r2 != this.trackIconInactiveStart) goto L5;
        return;
    L5:
        this.trackIconInactiveStart = r2;
        this.trackIconInactiveStartMutated = false;
        updateTrackIconInactiveStart();
        invalidate();
    }

    public void setTrackIconSize(int r2) {
        if (this.trackIconSize != r2) goto L5;
        return;
    L5:
        this.trackIconSize = r2;
        invalidate();
    }

    public void setTrackInactiveTintList(ColorStateList r2) {
        if (r2.equals(this.trackColorInactive) == false) goto L5;
        return;
    L5:
        this.trackColorInactive = r2;
        this.inactiveTrackPaint.setColor(getColorForState(r2));
        invalidate();
    }

    public void setTrackInsideCornerSize(int r2) {
        if (this.trackInsideCornerSize != r2) goto L5;
        return;
    L5:
        this.trackInsideCornerSize = r2;
        invalidate();
    }

    public void setTrackStopIndicatorSize(int r2) {
        if (this.trackStopIndicatorSize != r2) goto L5;
        return;
    L5:
        this.trackStopIndicatorSize = r2;
        this.stopIndicatorPaint.setStrokeWidth(r2);
        invalidate();
    }

    public void setTrackTintList(ColorStateList r1) {
        setTrackInactiveTintList(r1);
        setTrackActiveTintList(r1);
    }

    public void setValueFrom(float r1) {
        this.valueFrom = r1;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setValueTo(float r1) {
        this.valueTo = r1;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setValues(List<Float> r2) {
        setValuesInternal(new ArrayList(r2));
    }

    public void setValues(Float... r2) {
        ArrayList<Float> r0 = new ArrayList();
        Collections.addAll(r0, r2);
        setValuesInternal(r0);
    }

    public void updateBoundsForVirtualViewId(int r7, Rect r8) {
        int r0 = this.trackSidePadding + ((int) (normalizeValue(getValues().get(r7).floatValue()) * this.trackWidth));
        int r72 = calculateTrackCenter();
        int r1 = Math.max(this.minTouchTargetSize, this.accessibilityMinTouchTargetSize) / 2;
        int r2 = Math.max(this.thumbWidth / 2, r1);
        int r12 = Math.max(this.thumbHeight / 2, r1);
        RectF r3 = new RectF(r0 - r2, r72 - r12, r0 + r2, r72 + r12);
        if (isVertical() == false) goto L5;
        this.rotationMatrix.mapRect(r3);
    L5:
        r8.set((int) r3.left, (int) r3.top, (int) r3.right, (int) r3.bottom);
    }
}
