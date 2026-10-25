package com.google.android.material.focus;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.animation.OvershootInterpolator;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.StateListShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class FocusRingDrawable extends DrawableWrapper {
    private static final int ANIMATION_DURATION = 300;
    private static final boolean DEBUG_COLORS = false;
    private static final Drawable EMPTY_DRAWABLE = null;
    private static final int[] FOCUSED_STATE_SET = null;
    private static final TimeInterpolator INTERPOLATOR = null;
    private static final FloatProperty<FocusRingDrawable> PROPERTY_INTERPOLATION = null;
    private ObjectAnimator animator;
    private boolean focused;
    private float interpolation;
    private WeakReference<MaterialShapeDrawable> materialShapeDrawable;
    private final Matrix matrix;
    private boolean mutated;
    private final Paint paint;
    private final ShapeAppearancePathProvider pathProvider;
    private boolean previousStateSetEmpty;
    private float shapeAppearanceCornerSize;
    private final Path shapeAppearancePath;
    private FocusRingState state;
    private final Path tmpPath;
    private final Rect tmpRect;
    private final RectF tmpRectF;

    /* renamed from: com.google.android.material.focus.FocusRingDrawable$1, reason: invalid class name */
    public class AnonymousClass1 extends FloatProperty<FocusRingDrawable> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Float get(FocusRingDrawable r1) {
            return Float.valueOf(FocusRingDrawable.access$000(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((FocusRingDrawable) r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(FocusRingDrawable r1, float r2) {
            FocusRingDrawable.access$002(r1, r2);
            r1.invalidateSelf();
        }

        @Override // android.util.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(FocusRingDrawable r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: com.google.android.material.focus.FocusRingDrawable$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ FocusRingDrawable this$0;

        public AnonymousClass2(FocusRingDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            super.onAnimationCancel(r2);
            FocusRingDrawable.access$002(this.this$0, 1.0f);
            this.this$0.invalidateSelf();
        }
    }

    public static final class FocusRingState extends Drawable.ConstantState {
        int mChangingConfigurations;
        private Rect ringCustomBounds;
        private boolean ringEnabled;
        private int ringEnabledAttr;
        private boolean ringEnabledInflated;
        private int ringInnerColor;
        private int ringInnerColorAttr;
        private float ringInnerInset;
        private int ringInnerInsetAttr;
        private float ringInnerStrokeWidth;
        private int ringInnerStrokeWidthAttr;
        private float ringInset;
        private int ringInsetAttr;
        private int ringOuterColor;
        private int ringOuterColorAttr;
        private float ringOuterStrokeWidth;
        private int ringOuterStrokeWidthAttr;
        private float ringRadius;
        private int ringRadiusAttr;
        private ShapeAppearance ringShapeAppearance;
        private int ringShapeAppearanceAttr;
        private int ringShapeAppearanceResId;
        private int[] ringStateSet;
        Drawable.ConstantState wrappedState;

        public FocusRingState(FocusRingState r3) {
            this.mChangingConfigurations = 0;
            this.ringEnabled = false;
            this.ringEnabledAttr = Integer.MIN_VALUE;
            this.ringEnabledInflated = false;
            this.ringOuterColor = Integer.MIN_VALUE;
            this.ringOuterColorAttr = Integer.MIN_VALUE;
            this.ringInnerColor = Integer.MIN_VALUE;
            this.ringInnerColorAttr = Integer.MIN_VALUE;
            this.ringOuterStrokeWidth = Float.NaN;
            this.ringOuterStrokeWidthAttr = Integer.MIN_VALUE;
            this.ringInnerStrokeWidth = Float.NaN;
            this.ringInnerStrokeWidthAttr = Integer.MIN_VALUE;
            this.ringRadius = Float.NaN;
            this.ringRadiusAttr = Integer.MIN_VALUE;
            this.ringInset = Float.NaN;
            this.ringInsetAttr = Integer.MIN_VALUE;
            this.ringInnerInset = Float.NaN;
            this.ringInnerInsetAttr = Integer.MIN_VALUE;
            this.ringShapeAppearance = null;
            this.ringShapeAppearanceResId = Integer.MIN_VALUE;
            this.ringShapeAppearanceAttr = Integer.MIN_VALUE;
            this.ringCustomBounds = null;
            this.ringStateSet = FocusRingDrawable.access$2300();
            if (r3 == null) goto L16;
            this.wrappedState = r3.wrappedState;
            this.mChangingConfigurations = r3.mChangingConfigurations;
            this.ringEnabled = r3.ringEnabled;
            this.ringEnabledAttr = r3.ringEnabledAttr;
            this.ringEnabledInflated = r3.ringEnabledInflated;
            this.ringOuterColor = r3.ringOuterColor;
            this.ringOuterColorAttr = r3.ringOuterColorAttr;
            this.ringInnerColor = r3.ringInnerColor;
            this.ringInnerColorAttr = r3.ringInnerColorAttr;
            this.ringOuterStrokeWidth = r3.ringOuterStrokeWidth;
            this.ringOuterStrokeWidthAttr = r3.ringOuterStrokeWidthAttr;
            this.ringInnerStrokeWidth = r3.ringInnerStrokeWidth;
            this.ringInnerStrokeWidthAttr = r3.ringInnerStrokeWidthAttr;
            this.ringRadius = r3.ringRadius;
            this.ringRadiusAttr = r3.ringRadiusAttr;
            this.ringInset = r3.ringInset;
            this.ringInsetAttr = r3.ringInsetAttr;
            this.ringInnerInset = r3.ringInnerInset;
            this.ringInnerInsetAttr = r3.ringInnerInsetAttr;
            this.ringShapeAppearanceResId = r3.ringShapeAppearanceResId;
            this.ringShapeAppearanceAttr = r3.ringShapeAppearanceAttr;
            ShapeAppearance r0 = r3.ringShapeAppearance;
            if ((r0 instanceof ShapeAppearanceModel) == false) goto L8;
            this.ringShapeAppearance = ((ShapeAppearanceModel) r0).toBuilder().build();
        L12:
            if (r3.ringCustomBounds == null) goto L14;
            this.ringCustomBounds = new Rect(r3.ringCustomBounds);
        L14:
            int[] r32 = r3.ringStateSet;
            this.ringStateSet = Arrays.copyOf(r32, r32.length);
            return;
        L8:
            if ((r0 instanceof StateListShapeAppearanceModel) == false) goto L10;
            this.ringShapeAppearance = ((StateListShapeAppearanceModel) r0).toBuilder().build();
            goto L12
        L10:
            this.ringShapeAppearance = r0;
            goto L12
        }

        public static /* synthetic */ int access$100(FocusRingState r0) {
            return r0.ringEnabledAttr;
        }

        public static /* synthetic */ int access$1000(FocusRingState r0) {
            return r0.ringInnerStrokeWidthAttr;
        }

        public static /* synthetic */ int access$1002(FocusRingState r0, int r1) {
            r0.ringInnerStrokeWidthAttr = r1;
            return r1;
        }

        public static /* synthetic */ int access$102(FocusRingState r0, int r1) {
            r0.ringEnabledAttr = r1;
            return r1;
        }

        public static /* synthetic */ float access$1100(FocusRingState r0) {
            return r0.ringInnerStrokeWidth;
        }

        public static /* synthetic */ float access$1102(FocusRingState r0, float r1) {
            r0.ringInnerStrokeWidth = r1;
            return r1;
        }

        public static /* synthetic */ int access$1200(FocusRingState r0) {
            return r0.ringRadiusAttr;
        }

        public static /* synthetic */ int access$1202(FocusRingState r0, int r1) {
            r0.ringRadiusAttr = r1;
            return r1;
        }

        public static /* synthetic */ float access$1300(FocusRingState r0) {
            return r0.ringRadius;
        }

        public static /* synthetic */ float access$1302(FocusRingState r0, float r1) {
            r0.ringRadius = r1;
            return r1;
        }

        public static /* synthetic */ int access$1400(FocusRingState r0) {
            return r0.ringInsetAttr;
        }

        public static /* synthetic */ int access$1402(FocusRingState r0, int r1) {
            r0.ringInsetAttr = r1;
            return r1;
        }

        public static /* synthetic */ float access$1500(FocusRingState r0) {
            return r0.ringInset;
        }

        public static /* synthetic */ float access$1502(FocusRingState r0, float r1) {
            r0.ringInset = r1;
            return r1;
        }

        public static /* synthetic */ int access$1600(FocusRingState r0) {
            return r0.ringInnerInsetAttr;
        }

        public static /* synthetic */ int access$1602(FocusRingState r0, int r1) {
            r0.ringInnerInsetAttr = r1;
            return r1;
        }

        public static /* synthetic */ float access$1700(FocusRingState r0) {
            return r0.ringInnerInset;
        }

        public static /* synthetic */ float access$1702(FocusRingState r0, float r1) {
            r0.ringInnerInset = r1;
            return r1;
        }

        public static /* synthetic */ int access$1800(FocusRingState r0) {
            return r0.ringShapeAppearanceAttr;
        }

        public static /* synthetic */ int access$1802(FocusRingState r0, int r1) {
            r0.ringShapeAppearanceAttr = r1;
            return r1;
        }

        public static /* synthetic */ int access$1900(FocusRingState r0) {
            return r0.ringShapeAppearanceResId;
        }

        public static /* synthetic */ int access$1902(FocusRingState r0, int r1) {
            r0.ringShapeAppearanceResId = r1;
            return r1;
        }

        public static /* synthetic */ boolean access$200(FocusRingState r0) {
            return r0.ringEnabled;
        }

        public static /* synthetic */ ShapeAppearance access$2000(FocusRingState r0) {
            return r0.ringShapeAppearance;
        }

        public static /* synthetic */ ShapeAppearance access$2002(FocusRingState r0, ShapeAppearance r1) {
            r0.ringShapeAppearance = r1;
            return r1;
        }

        public static /* synthetic */ boolean access$202(FocusRingState r0, boolean r1) {
            r0.ringEnabled = r1;
            return r1;
        }

        public static /* synthetic */ int[] access$2100(FocusRingState r0) {
            return r0.ringStateSet;
        }

        public static /* synthetic */ int[] access$2102(FocusRingState r0, int[] r1) {
            r0.ringStateSet = r1;
            return r1;
        }

        public static /* synthetic */ Rect access$2200(FocusRingState r0) {
            return r0.ringCustomBounds;
        }

        public static /* synthetic */ Rect access$2202(FocusRingState r0, Rect r1) {
            r0.ringCustomBounds = r1;
            return r1;
        }

        public static /* synthetic */ boolean access$300(FocusRingState r0) {
            return r0.ringEnabledInflated;
        }

        public static /* synthetic */ boolean access$302(FocusRingState r0, boolean r1) {
            r0.ringEnabledInflated = r1;
            return r1;
        }

        public static /* synthetic */ int access$400(FocusRingState r0) {
            return r0.ringOuterColorAttr;
        }

        public static /* synthetic */ int access$402(FocusRingState r0, int r1) {
            r0.ringOuterColorAttr = r1;
            return r1;
        }

        public static /* synthetic */ int access$500(FocusRingState r0) {
            return r0.ringOuterColor;
        }

        public static /* synthetic */ int access$502(FocusRingState r0, int r1) {
            r0.ringOuterColor = r1;
            return r1;
        }

        public static /* synthetic */ int access$600(FocusRingState r0) {
            return r0.ringInnerColorAttr;
        }

        public static /* synthetic */ int access$602(FocusRingState r0, int r1) {
            r0.ringInnerColorAttr = r1;
            return r1;
        }

        public static /* synthetic */ int access$700(FocusRingState r0) {
            return r0.ringInnerColor;
        }

        public static /* synthetic */ int access$702(FocusRingState r0, int r1) {
            r0.ringInnerColor = r1;
            return r1;
        }

        public static /* synthetic */ int access$800(FocusRingState r0) {
            return r0.ringOuterStrokeWidthAttr;
        }

        public static /* synthetic */ int access$802(FocusRingState r0, int r1) {
            r0.ringOuterStrokeWidthAttr = r1;
            return r1;
        }

        public static /* synthetic */ float access$900(FocusRingState r0) {
            return r0.ringOuterStrokeWidth;
        }

        public static /* synthetic */ float access$902(FocusRingState r0, float r1) {
            r0.ringOuterStrokeWidth = r1;
            return r1;
        }

        public boolean canConstantState() {
            if (this.wrappedState == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            Drawable.ConstantState r0 = this.wrappedState;
            if (r0 == null) goto L5;
            int r02 = r0.getChangingConfigurations();
        L7:
            return this.mChangingConfigurations | r02;
        L5:
            r02 = 0;
            goto L7
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new FocusRingDrawable(this, null, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources r3) {
            return new FocusRingDrawable(this, r3, null);
        }
    }

    static {
        EMPTY_DRAWABLE = new ColorDrawable(0);
        FOCUSED_STATE_SET = new int[]{R.attr.state_focused, R.attr.state_window_focused};
        INTERPOLATOR = new OvershootInterpolator(4.0f);
        PROPERTY_INTERPOLATION = new AnonymousClass1("interpolation");
    }

    public FocusRingDrawable() {
        super(null);
        this.paint = new Paint(1);
        this.tmpRectF = new RectF();
        this.tmpRect = new Rect();
        this.tmpPath = new Path();
        this.shapeAppearancePath = new Path();
        this.matrix = new Matrix();
        this.pathProvider = ShapeAppearancePathProvider.getInstanceOrCreate();
        this.shapeAppearanceCornerSize = -1.0f;
        this.interpolation = 1.0f;
        this.focused = false;
        this.mutated = false;
        this.state = new FocusRingState(null);
    }

    public FocusRingDrawable(Context r3, Drawable r4) {
        super(r4);
        this.paint = new Paint(1);
        this.tmpRectF = new RectF();
        this.tmpRect = new Rect();
        this.tmpPath = new Path();
        this.shapeAppearancePath = new Path();
        this.matrix = new Matrix();
        this.pathProvider = ShapeAppearancePathProvider.getInstanceOrCreate();
        this.shapeAppearanceCornerSize = -1.0f;
        this.interpolation = 1.0f;
        this.focused = false;
        this.mutated = false;
        FocusRingState r0 = new FocusRingState(null);
        this.state = r0;
        if (r4 == null) goto L5;
        r0.wrappedState = r4.getConstantState();
    L5:
        init(r3.getTheme());
    }

    private FocusRingDrawable(FocusRingState r3, Resources r4) {
        super(null);
        this.paint = new Paint(1);
        this.tmpRectF = new RectF();
        this.tmpRect = new Rect();
        this.tmpPath = new Path();
        this.shapeAppearancePath = new Path();
        this.matrix = new Matrix();
        this.pathProvider = ShapeAppearancePathProvider.getInstanceOrCreate();
        this.shapeAppearanceCornerSize = -1.0f;
        this.interpolation = 1.0f;
        this.focused = false;
        this.mutated = false;
        FocusRingState r0 = new FocusRingState(r3);
        this.state = r0;
        Drawable.ConstantState r32 = r0.wrappedState;
        if (r32 == null) goto L8;
        if (r4 == null) goto L6;
        Drawable r33 = r32.newDrawable(r4);
    L7:
        setDrawable(r33);
        goto L8
    L6:
        r33 = r32.newDrawable();
    L8:
        updateLocalState();
    }

    public /* synthetic */ FocusRingDrawable(FocusRingState r1, Resources r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static /* synthetic */ float access$000(FocusRingDrawable r0) {
        return r0.interpolation;
    }

    public static /* synthetic */ float access$002(FocusRingDrawable r0, float r1) {
        r0.interpolation = r1;
        return r1;
    }

    public static /* synthetic */ int[] access$2300() {
        return FOCUSED_STATE_SET;
    }

    private void calculateBounds(RectF r5) {
        if (FocusRingState.access$2200(this.state) == null) goto L6;
        r5.set(FocusRingState.access$2200(this.state));
        return;
    L6:
        WeakReference<MaterialShapeDrawable> r0 = this.materialShapeDrawable;
        if (r0 == null) goto L13;
        if (r0.get() == null) goto L13;
        r5.set(this.materialShapeDrawable.get().getBounds());
        return;
    L13:
        if ((getDrawable() instanceof RippleDrawable) == false) goto L19;
        RippleDrawable r02 = (RippleDrawable) getDrawable();
        r02.getHotspotBounds(this.tmpRect);
        int r03 = r02.getRadius();
        if (r03 <= 0) goto L17;
        int r1 = Math.max(0, (this.tmpRect.width() / 2) - r03);
        int r04 = Math.max(0, (this.tmpRect.height() / 2) - r03);
        this.tmpRect.inset(r1, r04);
    L17:
        r5.set(this.tmpRect);
        return;
    L19:
        r5.set(getBounds());
    }

    private float calculateInnerInset() {
        return (FocusRingState.access$1500(this.state) + FocusRingState.access$1700(this.state)) + ((FocusRingState.access$1100(this.state) / 2.0f) * this.interpolation);
    }

    private float calculateInnerRadius(float r2) {
        return Math.max(0.0f, r2 - (FocusRingState.access$900(this.state) / 2.0f));
    }

    private float calculateOuterInset() {
        return FocusRingState.access$1500(this.state) + ((FocusRingState.access$900(this.state) / 2.0f) * this.interpolation);
    }

    private float calculateOuterRadius() {
        if (Float.isNaN(FocusRingState.access$1300(this.state)) == false) goto L5;
        float r0 = this.shapeAppearanceCornerSize;
        if (r0 < 0.0f) goto L9;
        return r0;
    L9:
        WeakReference<MaterialShapeDrawable> r02 = this.materialShapeDrawable;
        if (r02 != null) goto L12;
    L17:
        Drawable r3 = getDrawable();
        if ((r3 instanceof RippleDrawable) == false) goto L23;
        int r32 = ((RippleDrawable) r3).getRadius();
        if (r32 < 0) goto L23;
        return r32;
    L23:
        return 0.0f;
    L12:
        if (r02.get() == null) goto L17;
        float r03 = this.materialShapeDrawable.get().calculateRoundRectCornerSize();
        if (r03 < 0.0f) goto L17;
        return Math.max(0.0f, r03 - (FocusRingState.access$900(this.state) / 2.0f));
    L5:
        return FocusRingState.access$1300(this.state);
    }

    private void calculateShapeAppearanceRoundRectOrPath() {
        if (FocusRingState.access$2000(this.state) == null) goto L7;
        updateShapeAppearanceCornerSizeOrPath(FocusRingState.access$2000(this.state));
        return;
    L7:
        if (updateFocusRingShapeAppearanceFromWrappedDrawable() == false) goto L9;
        return;
    L9:
        this.shapeAppearanceCornerSize = -1.0f;
        this.shapeAppearancePath.reset();
    }

    private ObjectAnimator createAnimator() {
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this, PROPERTY_INTERPOLATION, new float[]{0.0f, 1.0f});
        r0.setDuration(300);
        r0.setInterpolator(INTERPOLATOR);
        r0.addListener(new AnonymousClass2(this));
        return r0;
    }

    private void drawPath(Canvas r5, Path r6, float r7, float r8, int r9) {
        calculateBounds(this.tmpRectF);
        float r72 = r7 * 2.0f;
        float r0 = 1.0f - (r72 / this.tmpRectF.width());
        float r1 = 1.0f - (r72 / this.tmpRectF.height());
        this.matrix.reset();
        this.matrix.postScale(r0, r1, this.tmpRectF.centerX(), this.tmpRectF.centerY());
        r6.transform(this.matrix, this.tmpPath);
        this.paint.setStrokeWidth(r8 * this.interpolation);
        this.paint.setColor(r9);
        r5.drawPath(this.tmpPath, this.paint);
    }

    private void drawRoundRect(Canvas r2, float r3, float r4, float r5, int r6) {
        calculateBounds(this.tmpRectF);
        this.tmpRectF.inset(r4, r4);
        this.paint.setStrokeWidth(r5 * this.interpolation);
        this.paint.setColor(r6);
        r2.drawRoundRect(this.tmpRectF, r3, r3, this.paint);
    }

    public static FocusRingDrawable find(Drawable r3) {
        if ((r3 instanceof FocusRingDrawable) == false) goto L7;
        return (FocusRingDrawable) r3;
    L7:
        if ((r3 instanceof DrawableWrapper) == false) goto L13;
        Drawable r0 = ((DrawableWrapper) r3).getDrawable();
        if ((r0 instanceof FocusRingDrawable) == false) goto L13;
        return (FocusRingDrawable) r0;
    L13:
        if ((r3 instanceof LayerDrawable) == false) goto L22;
        LayerDrawable r32 = (LayerDrawable) r3;
        int r02 = 0;
    L16:
        if (r02 >= r32.getNumberOfLayers()) goto L26;
        Drawable r1 = r32.getDrawable(r02);
        if ((r1 instanceof FocusRingDrawable) == true) goto L20;
        r02 = r02 + 1;
        goto L16
    L20:
        return (FocusRingDrawable) r1;
    L26:
        return null;
    L22:
        return null;
    }

    public static FocusRingDrawable findAndMutate(Drawable r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (find(r1) == null) goto L10;
        r1.mutate();
        return find(r1);
    L10:
        return null;
    }

    private float[] getCornerRadiiOrNull(GradientDrawable r1) {
        return r1.getCornerRadii();
    L4:
        return null;
    }

    private float getCornerRadius(GradientDrawable r1) {
        return r1.getCornerRadius();
    L4:
        return -1.0f;
    }

    private Path getNonEmptyPath() {
        if (this.shapeAppearancePath.isEmpty() == false) goto L5;
        WeakReference<MaterialShapeDrawable> r0 = this.materialShapeDrawable;
        if (r0 != null) goto L9;
        return null;
    L9:
        if (r0.get() == null) goto L15;
        Path r1 = this.materialShapeDrawable.get().getPath();
        if (r1.isEmpty() == true) goto L16;
        return r1;
    L16:
        return null;
    L15:
        return null;
    L5:
        return this.shapeAppearancePath;
    }

    private int getResIdIfReference(TypedArray r3, int r4) {
        if (r3.getType(r4) == 1) goto L5;
        return Integer.MIN_VALUE;
    L5:
        return r3.getResourceId(r4, Integer.MIN_VALUE);
    }

    private int getValueDataIfAttr(TypedArray r2, int r3) {
        if (r2.getType(r3) != 2) goto L8;
        TypedValue r1 = new TypedValue();
        if (r2.getValue(r3, r1) == true) goto L7;
        return Integer.MIN_VALUE;
    L7:
        return r1.data;
    L8:
        return Integer.MIN_VALUE;
    }

    private void inflateChildDrawable(Resources r5, XmlPullParser r6, AttributeSet r7, Resources.Theme r8) {
        int r0 = r6.getDepth();
        Drawable r1 = null;
    L3:
        int r2 = r6.next();
        if (r2 == 1) goto L12;
        if (r2 != 3) goto L10;
        if (r6.getDepth() <= r0) goto L12;
    L10:
        if (r2 != 2) goto L3;
        r1 = Drawable.createFromXmlInner(r5, r6, r7, r8);
    L12:
        if (r1 == null) goto L15;
        setDrawable(r1);
        FocusRingState r4 = this.state;
        r4.wrappedState = r1.getConstantState();
        return;
    L15:
        Drawable r52 = EMPTY_DRAWABLE;
        setDrawable(r52);
        FocusRingState r42 = this.state;
        r42.wrappedState = r52.getConstantState();
    }

    private void init(Resources.Theme r2) {
        TypedArray r0 = r2.obtainStyledAttributes(com.google.android.material.R.styleable.FocusRingDrawable);
        updateStateFromTypedArrayWithThemeAttrsAndDefaults(r0, r2);
        r0.recycle();
        updateLocalState();
    }

    public static FocusRingDrawable layer(Context r1, LayerDrawable r2) {
        return layer(r1, r2, null);
    }

    public static FocusRingDrawable layer(Context r2, LayerDrawable r3, MaterialShapeDrawable r4) {
        if (shouldUseFocusRing(r2) == true) goto L6;
        return null;
    L6:
        FocusRingDrawable r0 = new FocusRingDrawable(r2, EMPTY_DRAWABLE);
        if (r4 == null) goto L9;
        r0.setFocusRingMaterialShapeDrawable(r4);
    L9:
        r3.addLayer(r0);
        r0.setCallback(r3);
        return r0;
    }

    private void maybeAnimate(boolean r2) {
        ObjectAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.animator = null;
    L5:
        if (r2 == false) goto L8;
        ObjectAnimator r22 = createAnimator();
        this.animator = r22;
        r22.start();
        return;
    L8:
        this.interpolation = 1.0f;
    }

    private int maybeResolveColor(int r1, Resources.Theme r2, int r3, TypedArray r4, int r5, int r6) {
        if (r1 == Integer.MIN_VALUE) goto L5;
        return r1;
    L5:
        if (r3 == Integer.MIN_VALUE) goto L11;
        TypedValue r0 = new TypedValue();
        if (r2.resolveAttribute(r3, r0, true) == false) goto L11;
        return r0.data;
    L11:
        return r4.getColor(r5, r6);
    }

    private float maybeResolveDimension(float r2, Resources.Theme r3, int r4, TypedArray r5, int r6, int r7) {
        if (Float.isNaN(r2) == true) goto L5;
        return r2;
    L5:
        Resources r1 = r3.getResources();
        if (r4 == Float.MIN_VALUE) goto L11;
        TypedValue r22 = new TypedValue();
        if (r3.resolveAttribute(r4, r22, true) == false) goto L11;
        return r22.getDimension(r1.getDisplayMetrics());
    L11:
        float r32 = r5.getDimension(r6, Float.NaN);
        if (Float.isNaN(r32) == true) goto L14;
        return r32;
    L14:
        if (r7 != 0) goto L17;
        return Float.NaN;
    L17:
        return r1.getDimension(r7);
    }

    private static boolean shouldUseFocusRing(Context r2) {
        return MaterialAttributes.resolveBoolean(r2.getTheme(), com.google.android.material.R.attr.focusRingsEnabled, false);
    }

    private ShapeAppearance toShapeAppearance(Drawable r2) {
        if ((r2 instanceof ShapeDrawable) == false) goto L7;
        return toShapeAppearance((ShapeDrawable) r2);
    L7:
        if ((r2 instanceof GradientDrawable) == true) goto L9;
        return null;
    L9:
        return toShapeAppearance((GradientDrawable) r2);
    }

    private ShapeAppearance toShapeAppearance(GradientDrawable r3) {
        float[] r0 = getCornerRadiiOrNull(r3);
        if (r0 != null) goto L5;
        float r2 = getCornerRadius(r3);
        if (r2 > 0.0f) goto L9;
        return null;
    L9:
        return ShapeAppearanceModel.builder().setAllCornerSizes(r2).build();
    L5:
        return ShapeAppearanceModel.builder().setTopLeftCornerSize(Math.min(r0[0], r0[1])).setTopRightCornerSize(Math.min(r0[2], r0[3])).setBottomRightCornerSize(Math.min(r0[4], r0[5])).setBottomLeftCornerSize(Math.min(r0[6], r0[7])).build();
    }

    private ShapeAppearance toShapeAppearance(ShapeDrawable r2) {
        Outline r1 = new Outline();
        r2.getOutline(r1);
        if (r1.getRadius() > 0.0f) goto L5;
        return null;
    L5:
        return ShapeAppearanceModel.builder().setAllCornerSizes(r1.getRadius()).build();
    }

    private void updateLocalState() {
        this.paint.setStyle(Paint.Style.STROKE);
        if (Float.isNaN(FocusRingState.access$900(this.state)) == true) goto L6;
        this.paint.setStrokeWidth(FocusRingState.access$900(this.state));
        return;
    }

    private void updateShapeAppearanceCornerSizeOrPath(ShapeAppearance r9) {
        calculateBounds(this.tmpRectF);
        ShapeAppearanceModel r2 = r9.getShapeForState(FOCUSED_STATE_SET);
        if (r2.isRoundRect(this.tmpRectF) == false) goto L6;
        float r92 = calculateOuterInset();
        this.tmpRectF.inset(r92, r92);
        this.shapeAppearanceCornerSize = r2.getTopLeftCornerSize().getCornerSize(this.tmpRectF);
        this.shapeAppearancePath.reset();
        return;
    L6:
        this.pathProvider.calculatePath(r2, null, 1.0f, this.tmpRectF, null, this.shapeAppearancePath);
        this.shapeAppearanceCornerSize = -1.0f;
    }

    private void updateStateFromTypedArrayWithThemeAttrsAndDefaults(TypedArray r10, Resources.Theme r11) {
        if (FocusRingState.access$100(this.state) == Integer.MIN_VALUE) goto L12;
        TypedValue r0 = MaterialAttributes.resolve(r11, FocusRingState.access$100(this.state));
        if (r0 == null) goto L12;
        FocusRingState r2 = this.state;
        if (r0.data == 0) goto L9;
        boolean r02 = true;
    L10:
        FocusRingState.access$202(r2, r02);
        FocusRingState.access$302(this.state, true);
        goto L12
    L9:
        r02 = false;
    L12:
        if (FocusRingState.access$300(this.state) == true) goto L15;
        FocusRingState r03 = this.state;
        FocusRingState.access$202(r03, MaterialAttributes.resolveBoolean(r11, com.google.android.material.R.attr.focusRingsEnabled, FocusRingState.access$200(r03)));
    L15:
        if (FocusRingState.access$200(this.state) == false) goto L33;
        FocusRingState r04 = this.state;
        FocusRingState.access$502(r04, maybeResolveColor(FocusRingState.access$500(r04), r11, FocusRingState.access$400(this.state), r10, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeColor, -16777216));
        FocusRingState r9 = this.state;
        FocusRingState.access$702(r9, maybeResolveColor(FocusRingState.access$700(r9), r11, FocusRingState.access$600(this.state), r10, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeColor, -1));
        FocusRingState r92 = this.state;
        FocusRingState.access$902(r92, maybeResolveDimension(FocusRingState.access$900(r92), r11, FocusRingState.access$800(this.state), r10, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeWidth, com.google.android.material.R.dimen.mtrl_focus_ring_outer_stroke_width));
        FocusRingState r93 = this.state;
        FocusRingState.access$1102(r93, maybeResolveDimension(FocusRingState.access$1100(r93), r11, FocusRingState.access$1000(this.state), r10, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth, com.google.android.material.R.dimen.mtrl_focus_ring_inner_stroke_width));
        FocusRingState r94 = this.state;
        FocusRingState.access$1302(r94, maybeResolveDimension(FocusRingState.access$1300(r94), r11, FocusRingState.access$1200(this.state), r10, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsRadius, 0));
        FocusRingState r95 = this.state;
        FocusRingState.access$1502(r95, maybeResolveDimension(FocusRingState.access$1500(r95), r11, FocusRingState.access$1400(this.state), r10, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInset, 0));
        if (Float.isNaN(FocusRingState.access$1500(this.state)) == false) goto L20;
        FocusRingState.access$1502(this.state, 0.0f);
    L20:
        FocusRingState r96 = this.state;
        FocusRingState.access$1702(r96, maybeResolveDimension(FocusRingState.access$1700(r96), r11, FocusRingState.access$1600(this.state), r10, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeInset, com.google.android.material.R.dimen.mtrl_focus_ring_inner_stroke_inset));
        int r97 = FocusRingState.access$1900(this.state);
        FocusRingState r102 = this.state;
        if (r97 == Integer.MIN_VALUE) goto L25;
        FocusRingState.access$2002(r102, ShapeAppearanceModel.builder(r11, FocusRingState.access$1900(r102)).build());
        return;
    L25:
        if (FocusRingState.access$1800(r102) == Integer.MIN_VALUE) goto L27;
        int r98 = FocusRingState.access$1800(this.state);
    L28:
        TypedValue r99 = MaterialAttributes.resolve(r11, r98);
        if (r99 == null) goto L32;
        FocusRingState.access$2002(this.state, ShapeAppearanceModel.builder(r11, r99.resourceId).build());
        return;
    L32:
        return;
    L27:
        r98 = com.google.android.material.R.attr.focusRingsShapeAppearance;
        goto L28
    }

    private void updateStateFromTypedArrayWithoutThemeAttrsOrDefaults(TypedArray r5) {
        FocusRingState.access$102(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsEnabled));
        if (FocusRingState.access$100(this.state) == Integer.MIN_VALUE) goto L5;
    L7:
        FocusRingState.access$402(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeColor));
        if (FocusRingState.access$400(this.state) != Integer.MIN_VALUE) goto L10;
        FocusRingState.access$502(this.state, r5.getColor(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeColor, Integer.MIN_VALUE));
    L10:
        FocusRingState.access$602(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeColor));
        if (FocusRingState.access$600(this.state) != Integer.MIN_VALUE) goto L13;
        FocusRingState.access$702(this.state, r5.getColor(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeColor, Integer.MIN_VALUE));
    L13:
        FocusRingState.access$802(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeWidth));
        if (FocusRingState.access$800(this.state) != Integer.MIN_VALUE) goto L16;
        FocusRingState.access$902(this.state, r5.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeWidth, Float.NaN));
    L16:
        FocusRingState.access$1002(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth));
        if (FocusRingState.access$1000(this.state) != Integer.MIN_VALUE) goto L19;
        FocusRingState.access$1102(this.state, r5.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth, Float.NaN));
    L19:
        FocusRingState.access$1002(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth));
        if (FocusRingState.access$1000(this.state) != Integer.MIN_VALUE) goto L22;
        FocusRingState.access$1102(this.state, r5.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth, Float.NaN));
    L22:
        FocusRingState.access$1202(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsRadius));
        if (FocusRingState.access$1200(this.state) != Integer.MIN_VALUE) goto L25;
        FocusRingState.access$1302(this.state, r5.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsRadius, Float.NaN));
    L25:
        FocusRingState.access$1402(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInset));
        if (FocusRingState.access$1400(this.state) != Integer.MIN_VALUE) goto L28;
        FocusRingState.access$1502(this.state, r5.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInset, Float.NaN));
    L28:
        FocusRingState.access$1602(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeInset));
        if (FocusRingState.access$1600(this.state) != Integer.MIN_VALUE) goto L31;
        FocusRingState.access$1702(this.state, r5.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeInset, Float.NaN));
    L31:
        FocusRingState.access$1802(this.state, getValueDataIfAttr(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsShapeAppearance));
        FocusRingState.access$1902(this.state, getResIdIfReference(r5, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsShapeAppearance));
        return;
    L5:
        if (r5.hasValue(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsEnabled) == false) goto L7;
        FocusRingState r0 = this.state;
        FocusRingState.access$202(r0, r5.getBoolean(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsEnabled, FocusRingState.access$200(r0)));
        FocusRingState.access$302(this.state, true);
        goto L7
    }

    public static Drawable wrap(Context r1, Drawable r2) {
        if (shouldUseFocusRing(r1) == true) goto L6;
        return r2;
    L6:
        return new FocusRingDrawable(r1, r2);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme r1) {
        super.applyTheme(r1);
        init(r1);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return true;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas r12) {
        super.draw(r12);
        if (FocusRingState.access$200(this.state) == true) goto L5;
        return;
    L5:
        if (this.focused == false) goto L14;
        float r4 = calculateOuterInset();
        float r8 = calculateInnerInset();
        Path r3 = getNonEmptyPath();
        if (r3 == null) goto L11;
        drawPath(r12, r3, r8, FocusRingState.access$1100(this.state), FocusRingState.access$700(this.state));
        drawPath(r12, r3, r4, FocusRingState.access$900(this.state), FocusRingState.access$500(this.state));
        return;
    L11:
        float r32 = calculateOuterRadius();
        drawRoundRect(r12, calculateInnerRadius(r32), r8, FocusRingState.access$1100(this.state), FocusRingState.access$700(this.state));
        drawRoundRect(r12, r32, r4, FocusRingState.access$900(this.state), FocusRingState.access$500(this.state));
        return;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.state.canConstantState() == false) goto L6;
        FocusRingState r0 = this.state;
        r0.mChangingConfigurations = getChangingConfigurations();
        return this.state;
    L6:
        return null;
    }

    public Rect getFocusRingBounds() {
        return FocusRingState.access$2200(this.state);
    }

    public float getFocusRingInnerInset() {
        return FocusRingState.access$1700(this.state);
    }

    public int getFocusRingInnerStrokeColor() {
        return FocusRingState.access$700(this.state);
    }

    public float getFocusRingInnerStrokeWidth() {
        return FocusRingState.access$1100(this.state);
    }

    public float getFocusRingInset() {
        return FocusRingState.access$1500(this.state);
    }

    public MaterialShapeDrawable getFocusRingMaterialShapeDrawable() {
        WeakReference<MaterialShapeDrawable> r0 = this.materialShapeDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.get();
    }

    public int getFocusRingOuterStrokeColor() {
        return FocusRingState.access$500(this.state);
    }

    public float getFocusRingOuterStrokeWidth() {
        return FocusRingState.access$900(this.state);
    }

    public float getFocusRingRadius() {
        return FocusRingState.access$1300(this.state);
    }

    public ShapeAppearance getFocusRingShapeAppearance() {
        return FocusRingState.access$2000(this.state);
    }

    public int[] getFocusRingStateSet() {
        return FocusRingState.access$2100(this.state);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean hasFocusStateSpecified() {
        if (super.hasFocusStateSpecified() == true) goto L9;
        if (FocusRingState.access$200(this.state) == true) goto L15;
        return false;
    L15:
        return true;
    L9:
        return true;
    L12:
        return FocusRingState.access$200(this.state);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources r2, XmlPullParser r3, AttributeSet r4) {
        inflate(r2, r3, r4, null);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void inflate(Resources r3, XmlPullParser r4, AttributeSet r5, Resources.Theme r6) {
        super.inflate(r3, r4, r5, r6);
        if (r6 == null) goto L5;
        TypedArray r0 = r6.obtainStyledAttributes(r5, com.google.android.material.R.styleable.FocusRingDrawable, 0, 0);
    L6:
        updateStateFromTypedArrayWithoutThemeAttrsOrDefaults(r0);
        r0.recycle();
        inflateChildDrawable(r3, r4, r5, r6);
        return;
    L5:
        r0 = r3.obtainAttributes(r5, com.google.android.material.R.styleable.FocusRingDrawable);
        goto L6
    }

    public boolean isFocusRingEnabled() {
        return FocusRingState.access$200(this.state);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Drawable r0 = getDrawable();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isProjected() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (super.isStateful() == false) goto L5;
        return true;
    L5:
        if (FocusRingState.access$200(this.state) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        ObjectAnimator r0 = this.animator;
        if (r0 == null) goto L6;
        r0.end();
        this.animator = null;
        return;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (this.mutated == false) goto L5;
    L10:
        return this;
    L5:
        if (super.mutate() != this) goto L10;
        this.state = new FocusRingState(this.state);
        Drawable r0 = getDrawable();
        if (r0 == null) goto L9;
        FocusRingState r1 = this.state;
        r1.wrappedState = r0.getConstantState();
    L9:
        this.mutated = true;
        goto L10
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        if (FocusRingState.access$200(this.state) == true) goto L5;
        return;
    L5:
        calculateShapeAppearanceRoundRectOrPath();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] r6) {
        if (FocusRingState.access$200(this.state) == true) goto L6;
        this.focused = false;
        return super.onStateChange(r6);
    L6:
        boolean r0 = StateSet.stateSetMatches(FocusRingState.access$2100(this.state), r6);
        if (this.focused == r0) goto L9;
        boolean r2 = true;
    L10:
        this.focused = r0;
        if (r2 == false) goto L18;
        if (r6.length <= 0) goto L18;
        if (this.previousStateSetEmpty == true) goto L18;
        maybeAnimate(r0);
    L18:
        if (r6.length != 0) goto L20;
        boolean r02 = true;
    L21:
        this.previousStateSetEmpty = r02;
        if (super.onStateChange(r6) == true) goto L26;
        if (r2 == true) goto L26;
        return false;
    L26:
        return true;
    L20:
        r02 = false;
        goto L21
    L9:
        r2 = false;
        goto L10
    }

    public void setFocusRingBounds(int r3, int r4, int r5, int r6) {
        if (FocusRingState.access$2200(this.state) != null) goto L5;
        FocusRingState.access$2202(this.state, new Rect());
    L5:
        FocusRingState.access$2200(this.state).set(r3, r4, r5, r6);
    }

    public void setFocusRingBounds(Rect r1) {
        FocusRingState.access$2202(this.state, r1);
    }

    public void setFocusRingEnabled(boolean r1) {
        FocusRingState.access$202(this.state, r1);
    }

    public void setFocusRingInnerInset(float r1) {
        FocusRingState.access$1702(this.state, r1);
    }

    public void setFocusRingInnerStrokeColor(int r1) {
        FocusRingState.access$702(this.state, r1);
    }

    public void setFocusRingInnerStrokeWidth(float r1) {
        FocusRingState.access$1102(this.state, r1);
    }

    public void setFocusRingInset(float r1) {
        FocusRingState.access$1502(this.state, r1);
    }

    public void setFocusRingMaterialShapeDrawable(MaterialShapeDrawable r2) {
        this.materialShapeDrawable = new WeakReference(r2);
    }

    public void setFocusRingOuterStrokeColor(int r1) {
        FocusRingState.access$502(this.state, r1);
    }

    public void setFocusRingOuterStrokeWidth(float r1) {
        FocusRingState.access$902(this.state, r1);
    }

    public void setFocusRingRadius(float r1) {
        FocusRingState.access$1302(this.state, r1);
    }

    public void setFocusRingShapeAppearance(ShapeAppearance r1) {
        FocusRingState.access$2002(this.state, r1);
    }

    public void setFocusRingStateSet(int[] r1) {
        FocusRingState.access$2102(this.state, r1);
    }

    public boolean updateFocusRingShapeAppearanceFromWrappedDrawable() {
        ShapeAppearance r0 = toShapeAppearance(getDrawable());
        if (r0 == null) goto L6;
        updateShapeAppearanceCornerSizeOrPath(r0);
        return true;
    L6:
        return false;
    }
}
