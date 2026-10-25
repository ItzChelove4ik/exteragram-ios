package com.google.android.material.shape;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.util.ObjectsCompat;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.math.MathUtils;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.BitSet;

/* loaded from: classes5.dex */
public class MaterialShapeDrawable extends Drawable implements Shapeable {
    private static final SpringAnimatedCornerSizeProperty[] CORNER_SIZES_IN_PX = null;
    static final ShapeAppearanceModel DEFAULT_INTERPOLATION_START_SHAPE_APPEARANCE_MODEL = null;
    public static final int SHADOW_COMPAT_MODE_ALWAYS = 2;
    public static final int SHADOW_COMPAT_MODE_DEFAULT = 0;
    public static final int SHADOW_COMPAT_MODE_NEVER = 1;
    private static final float SHADOW_OFFSET_MULTIPLIER = 0.25f;
    private static final float SHADOW_RADIUS_MULTIPLIER = 0.75f;
    private static final String TAG = "MaterialShapeDrawable";
    private static final Paint clearPaint = null;
    private final BitSet containsIncompatibleShadowOp;
    private final ShapePath.ShadowCompatOperation[] cornerShadowOperation;
    SpringAnimation[] cornerSpringAnimations;
    private SpringForce cornerSpringForce;
    private MaterialShapeDrawableState drawableState;
    private final ShapePath.ShadowCompatOperation[] edgeShadowOperation;
    private final Paint fillPaint;
    private final RectF insetRectF;
    private boolean isRoundRectCornerMorph;
    private final Matrix matrix;
    private OnCornerSizeChangeListener onCornerSizeChangeListener;
    private final Path path;
    private final RectF pathBounds;
    private boolean pathDirty;
    private final Path pathInsetByStroke;
    private final ShapeAppearancePathProvider pathProvider;
    private final ShapeAppearancePathProvider.PathListener pathShadowListener;
    private final RectF rectF;
    private int resolvedTintColor;
    private final Region scratchRegion;
    private boolean shadowBitmapDrawingEnable;
    private final ShadowRenderer shadowRenderer;
    private float[] springAnimatedCornerSizes;
    private float[] springAnimatedStrokeCornerSizes;
    private final ShapeAppearanceModel.CornerSizeUnaryOperator strokeInsetCornerSizeUnaryOperator;
    private final Paint strokePaint;
    private boolean strokePathDirty;
    private ShapeAppearanceModel strokeShapeAppearanceModel;
    private PorterDuffColorFilter strokeTintFilter;
    private PorterDuffColorFilter tintFilter;
    private final Region transparentRegion;

    /* renamed from: com.google.android.material.shape.MaterialShapeDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements ShapeAppearanceModel.CornerSizeUnaryOperator {
        final /* synthetic */ MaterialShapeDrawable this$0;

        public AnonymousClass1(MaterialShapeDrawable r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
        public CornerSize apply(CornerSize r2) {
            if ((r2 instanceof RelativeCornerSize) == false) goto L6;
            return r2;
        L6:
            return new AdjustedCornerSize(-MaterialShapeDrawable.access$000(this.this$0), r2);
        }
    }

    /* renamed from: com.google.android.material.shape.MaterialShapeDrawable$2, reason: invalid class name */
    public class AnonymousClass2 implements ShapeAppearancePathProvider.PathListener {
        final /* synthetic */ MaterialShapeDrawable this$0;

        public AnonymousClass2(MaterialShapeDrawable r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
        public void onCornerPathCreated(ShapePath r3, Matrix r4, int r5) {
            MaterialShapeDrawable.access$100(this.this$0).set(r5, r3.containsIncompatibleShadowOp());
            MaterialShapeDrawable.access$200(this.this$0)[r5] = r3.createShadowCompatOperation(r4);
        }

        @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
        public void onEdgePathCreated(ShapePath r4, Matrix r5, int r6) {
            MaterialShapeDrawable.access$100(this.this$0).set(r6 + 4, r4.containsIncompatibleShadowOp());
            MaterialShapeDrawable.access$300(this.this$0)[r6] = r4.createShadowCompatOperation(r5);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CompatibilityShadowMode {
    }

    public static class MaterialShapeDrawableState extends Drawable.ConstantState {
        int alpha;
        ColorFilter colorFilter;
        float elevation;
        ElevationOverlayProvider elevationOverlayProvider;
        ColorStateList fillColor;
        float interpolation;
        Rect padding;
        Paint.Style paintStyle;
        float parentAbsoluteElevation;
        float scale;
        int shadowCompatMode;
        int shadowCompatOffset;
        int shadowCompatRadius;
        int shadowCompatRotation;
        ShapeAppearance shapeAppearance;
        ColorStateList strokeColor;
        ColorStateList strokeTintList;
        float strokeWidth;
        ColorStateList tintList;
        PorterDuff.Mode tintMode;
        float translationZ;
        boolean useTintColorForShadow;

        public MaterialShapeDrawableState(MaterialShapeDrawableState r3) {
            this.fillColor = null;
            this.strokeColor = null;
            this.strokeTintList = null;
            this.tintList = null;
            this.tintMode = PorterDuff.Mode.SRC_IN;
            this.padding = null;
            this.scale = 1.0f;
            this.interpolation = 1.0f;
            this.alpha = 255;
            this.parentAbsoluteElevation = 0.0f;
            this.elevation = 0.0f;
            this.translationZ = 0.0f;
            this.shadowCompatMode = 0;
            this.shadowCompatRadius = 0;
            this.shadowCompatOffset = 0;
            this.shadowCompatRotation = 0;
            this.useTintColorForShadow = false;
            this.paintStyle = Paint.Style.FILL_AND_STROKE;
            this.shapeAppearance = r3.shapeAppearance;
            this.elevationOverlayProvider = r3.elevationOverlayProvider;
            this.strokeWidth = r3.strokeWidth;
            this.colorFilter = r3.colorFilter;
            this.fillColor = r3.fillColor;
            this.strokeColor = r3.strokeColor;
            this.tintMode = r3.tintMode;
            this.tintList = r3.tintList;
            this.alpha = r3.alpha;
            this.scale = r3.scale;
            this.shadowCompatOffset = r3.shadowCompatOffset;
            this.shadowCompatMode = r3.shadowCompatMode;
            this.useTintColorForShadow = r3.useTintColorForShadow;
            this.interpolation = r3.interpolation;
            this.parentAbsoluteElevation = r3.parentAbsoluteElevation;
            this.elevation = r3.elevation;
            this.translationZ = r3.translationZ;
            this.shadowCompatRadius = r3.shadowCompatRadius;
            this.shadowCompatRotation = r3.shadowCompatRotation;
            this.strokeTintList = r3.strokeTintList;
            this.paintStyle = r3.paintStyle;
            if (r3.padding == null) goto L6;
            this.padding = new Rect(r3.padding);
            return;
        }

        public MaterialShapeDrawableState(ShapeAppearance r3, ElevationOverlayProvider r4) {
            this.fillColor = null;
            this.strokeColor = null;
            this.strokeTintList = null;
            this.tintList = null;
            this.tintMode = PorterDuff.Mode.SRC_IN;
            this.padding = null;
            this.scale = 1.0f;
            this.interpolation = 1.0f;
            this.alpha = 255;
            this.parentAbsoluteElevation = 0.0f;
            this.elevation = 0.0f;
            this.translationZ = 0.0f;
            this.shadowCompatMode = 0;
            this.shadowCompatRadius = 0;
            this.shadowCompatOffset = 0;
            this.shadowCompatRotation = 0;
            this.useTintColorForShadow = false;
            this.paintStyle = Paint.Style.FILL_AND_STROKE;
            this.shapeAppearance = r3;
            this.elevationOverlayProvider = r4;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            MaterialShapeDrawable r0 = new MaterialShapeDrawable(this);
            MaterialShapeDrawable.access$602(r0, true);
            MaterialShapeDrawable.access$702(r0, true);
            return r0;
        }
    }

    public interface OnCornerSizeChangeListener {
        void onCornerSizeChange(float r1);
    }

    public static class SpringAnimatedCornerSizeProperty extends FloatPropertyCompat<MaterialShapeDrawable> {
        private final int index;

        public SpringAnimatedCornerSizeProperty(int r3) {
            super("cornerSizeAtIndex" + r3);
            this.index = r3;
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(MaterialShapeDrawable r2) {
            if (MaterialShapeDrawable.access$400(r2) != null) goto L5;
            return 0.0f;
        L5:
            return MaterialShapeDrawable.access$400(r2)[this.index];
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(MaterialShapeDrawable r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(MaterialShapeDrawable r3, float r4) {
            if (MaterialShapeDrawable.access$400(r3) != null) goto L5;
            return;
        L5:
            if (MaterialShapeDrawable.access$400(r3)[this.index] == r4) goto L12;
            MaterialShapeDrawable.access$400(r3)[this.index] = r4;
            if (MaterialShapeDrawable.access$500(r3) == null) goto L9;
            MaterialShapeDrawable.access$500(r3).onCornerSizeChange(r3.getCornerSizeDiffX());
        L9:
            r3.invalidateSelf();
            return;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(MaterialShapeDrawable r1, float r2) {
            setValue2(r1, r2);
        }
    }

    static {
        int r2 = 0;
        DEFAULT_INTERPOLATION_START_SHAPE_APPEARANCE_MODEL = ShapeAppearanceModel.builder().setAllCorners(0, 0.0f).build();
        Paint r0 = new Paint(1);
        clearPaint = r0;
        r0.setColor(-1);
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        CORNER_SIZES_IN_PX = new SpringAnimatedCornerSizeProperty[4];
    L3:
        SpringAnimatedCornerSizeProperty[] r02 = CORNER_SIZES_IN_PX;
        if (r2 >= r02.length) goto L6;
        r02[r2] = new SpringAnimatedCornerSizeProperty(r2);
        r2 = r2 + 1;
        goto L3
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    public MaterialShapeDrawable(Context r1, AttributeSet r2, int r3, int r4) {
        this(ShapeAppearanceModel.builder(r1, r2, r3, r4).build());
    }

    public MaterialShapeDrawable(MaterialShapeDrawableState r6) {
        this.strokeInsetCornerSizeUnaryOperator = new AnonymousClass1(this);
        this.cornerShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.containsIncompatibleShadowOp = new BitSet(8);
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        Paint r1 = new Paint(1);
        this.fillPaint = r1;
        Paint r3 = new Paint(1);
        this.strokePaint = r3;
        this.shadowRenderer = new ShadowRenderer();
        this.pathProvider = ShapeAppearancePathProvider.getInstanceOrCreate();
        this.pathBounds = new RectF();
        this.shadowBitmapDrawingEnable = true;
        this.isRoundRectCornerMorph = true;
        this.cornerSpringAnimations = new SpringAnimation[4];
        this.drawableState = r6;
        r3.setStyle(Paint.Style.STROKE);
        r1.setStyle(Paint.Style.FILL);
        updateTintFilter();
        updateColorsForState(getState());
        this.pathShadowListener = new AnonymousClass2(this);
    }

    public MaterialShapeDrawable(ShapeAppearance r3) {
        this(new MaterialShapeDrawableState(r3, null));
    }

    public MaterialShapeDrawable(ShapeAppearanceModel r3) {
        this(new MaterialShapeDrawableState(r3, null));
    }

    @Deprecated
    public MaterialShapeDrawable(ShapePathModel r1) {
        this(r1);
    }

    public static /* synthetic */ float access$000(MaterialShapeDrawable r0) {
        return r0.getStrokeInsetLength();
    }

    public static /* synthetic */ BitSet access$100(MaterialShapeDrawable r0) {
        return r0.containsIncompatibleShadowOp;
    }

    public static /* synthetic */ ShapePath.ShadowCompatOperation[] access$200(MaterialShapeDrawable r0) {
        return r0.cornerShadowOperation;
    }

    public static /* synthetic */ ShapePath.ShadowCompatOperation[] access$300(MaterialShapeDrawable r0) {
        return r0.edgeShadowOperation;
    }

    public static /* synthetic */ float[] access$400(MaterialShapeDrawable r0) {
        return r0.springAnimatedCornerSizes;
    }

    public static /* synthetic */ OnCornerSizeChangeListener access$500(MaterialShapeDrawable r0) {
        return r0.onCornerSizeChangeListener;
    }

    public static /* synthetic */ boolean access$602(MaterialShapeDrawable r0, boolean r1) {
        r0.pathDirty = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$702(MaterialShapeDrawable r0, boolean r1) {
        r0.strokePathDirty = r1;
        return r1;
    }

    private PorterDuffColorFilter calculatePaintColorTintFilter(Paint r1, boolean r2) {
        if (r2 == false) goto L7;
        int r12 = r1.getColor();
        int r22 = compositeElevationOverlayIfNeeded(r12);
        this.resolvedTintColor = r22;
        if (r22 != r12) goto L6;
        return null;
    L6:
        return new PorterDuffColorFilter(r22, PorterDuff.Mode.SRC_IN);
    L7:
        return null;
    }

    private void calculatePath(RectF r5, Path r6) {
        calculatePathForSize(r5, r6);
        if (this.drawableState.scale == 1.0f) goto L5;
        this.matrix.reset();
        Matrix r0 = this.matrix;
        float r1 = this.drawableState.scale;
        r0.setScale(r1, r1, r5.width() / 2.0f, r5.height() / 2.0f);
        r6.transform(this.matrix);
    L5:
        r6.computeBounds(this.pathBounds, true);
    }

    private float calculateRoundRectCornerSize(RectF r1, ShapeAppearanceModel r2, float[] r3) {
        if (r3 != null) goto L8;
        if (r2.isRoundRect(r1) == true) goto L6;
        return -1.0f;
    L6:
        return r2.getTopLeftCornerSize().getCornerSize(r1);
    L8:
        if (this.isRoundRectCornerMorph == true) goto L10;
        return -1.0f;
    L10:
        return r3[0];
    }

    private void calculateStrokePath() {
        this.pathProvider.calculatePath(this.strokeShapeAppearanceModel, this.springAnimatedStrokeCornerSizes, this.drawableState.interpolation, getBoundsInsetByStroke(), null, this.pathInsetByStroke);
    }

    private PorterDuffColorFilter calculateTintColorTintFilter(ColorStateList r3, PorterDuff.Mode r4, boolean r5) {
        int r32 = r3.getColorForState(getState(), 0);
        if (r5 == false) goto L5;
        r32 = compositeElevationOverlayIfNeeded(r32);
    L5:
        this.resolvedTintColor = r32;
        return new PorterDuffColorFilter(r32, r4);
    }

    private PorterDuffColorFilter calculateTintFilter(ColorStateList r1, PorterDuff.Mode r2, Paint r3, boolean r4) {
        if (r1 == null) goto L8;
        if (r2 == null) goto L8;
        return calculateTintColorTintFilter(r1, r2, r4);
    L8:
        return calculatePaintColorTintFilter(r3, r4);
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context r1) {
        return createWithElevationOverlay(r1, 0.0f);
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context r1, float r2) {
        return createWithElevationOverlay(r1, r2, null);
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context r1, float r2, ColorStateList r3) {
        if (r3 != null) goto L4;
        r3 = ColorStateList.valueOf(MaterialColors.getColor(r1, R.attr.colorSurface, MaterialShapeDrawable.class.getSimpleName()));
    L4:
        MaterialShapeDrawable r0 = new MaterialShapeDrawable();
        r0.initializeElevationOverlay(r1);
        r0.setFillColor(r3);
        r0.setElevation(r2);
        return r0;
    }

    private void drawCompatShadow(Canvas r5) {
        if (this.containsIncompatibleShadowOp.cardinality() <= 0) goto L6;
        Log.w(TAG, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
    L6:
        if (this.drawableState.shadowCompatOffset == 0) goto L8;
        r5.drawPath(this.path, this.shadowRenderer.getShadowPaint());
    L8:
        int r0 = 0;
    L10:
        if (r0 >= 4) goto L13;
        this.cornerShadowOperation[r0].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, r5);
        this.edgeShadowOperation[r0].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, r5);
        r0 = r0 + 1;
        goto L10
    L13:
        if (this.shadowBitmapDrawingEnable == false) goto L17;
        int r02 = getShadowOffsetX();
        int r1 = getShadowOffsetY();
        r5.translate(-r02, -r1);
        r5.drawPath(this.path, clearPaint);
        r5.translate(r02, r1);
        return;
    }

    private void drawFillShape(Canvas r8) {
        drawShape(r8, this.fillPaint, this.path, this.drawableState.shapeAppearance.getDefaultShape(), this.springAnimatedCornerSizes, getBoundsAsRectF());
    }

    private void drawShape(Canvas r1, Paint r2, Path r3, ShapeAppearanceModel r4, float[] r5, RectF r6) {
        float r42 = calculateRoundRectCornerSize(r6, r4, r5);
        if (r42 < 0.0f) goto L6;
        float r43 = r42 * this.drawableState.interpolation;
        r1.drawRoundRect(r6, r43, r43, r2);
        return;
    L6:
        r1.drawPath(r3, r2);
    }

    private RectF getBoundsInsetByStroke() {
        this.insetRectF.set(getBoundsAsRectF());
        float r0 = getStrokeInsetLength();
        this.insetRectF.inset(r0, r0);
        return this.insetRectF;
    }

    private float getStrokeInsetLength() {
        if (hasStroke() == true) goto L5;
        return 0.0f;
    L5:
        return this.strokePaint.getStrokeWidth() / 2.0f;
    }

    private boolean hasCompatShadow() {
        MaterialShapeDrawableState r0 = this.drawableState;
        int r1 = r0.shadowCompatMode;
        if (r1 != 1) goto L5;
        return false;
    L5:
        if (r0.shadowCompatRadius > 0) goto L7;
        return false;
    L7:
        if (r1 != 2) goto L9;
    L10:
        return true;
    L9:
        if (requiresCompatShadow() == true) goto L10;
        return false;
    }

    private boolean hasFill() {
        Paint.Style r1 = this.drawableState.paintStyle;
        if (r1 != Paint.Style.FILL_AND_STROKE) goto L5;
        return true;
    L5:
        if (r1 == Paint.Style.FILL) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean hasStroke() {
        Paint.Style r0 = this.drawableState.paintStyle;
        if (r0 == Paint.Style.FILL_AND_STROKE) goto L7;
        if (r0 == Paint.Style.STROKE) goto L7;
        return false;
    L7:
        if (this.strokePaint.getStrokeWidth() <= 0.0f) goto L12;
        return true;
    L12:
        return false;
    }

    private void invalidateSelfIgnoreShape() {
        super.invalidateSelf();
    }

    private boolean isCornerSpringAnimationRunning() {
        SpringAnimation[] r4 = this.cornerSpringAnimations;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        SpringAnimation r3 = r4[r2];
        if (r3 == null) goto L10;
        if (r3.isRunning() == false) goto L10;
        return true;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        return false;
    }

    private void maybeDrawCompatShadow(Canvas r8) {
        if (hasCompatShadow() == true) goto L5;
        return;
    L5:
        r8.save();
        prepareCanvasForShadow(r8);
        if (this.shadowBitmapDrawingEnable == true) goto L9;
        drawCompatShadow(r8);
        r8.restore();
        return;
    L9:
        Rect r0 = getBounds();
        int r1 = (int) (this.pathBounds.width() - r0.width());
        int r2 = (int) (this.pathBounds.height() - r0.height());
        if (r1 < 0) goto L15;
        if (r2 < 0) goto L15;
        Bitmap r3 = Bitmap.createBitmap((((int) this.pathBounds.width()) + (this.drawableState.shadowCompatRadius * 2)) + r1, (((int) this.pathBounds.height()) + (this.drawableState.shadowCompatRadius * 2)) + r2, Bitmap.Config.ARGB_8888);
        Canvas r4 = new Canvas(r3);
        int r5 = r0.left;
        int r6 = this.drawableState.shadowCompatRadius;
        float r12 = (r5 - r6) - r1;
        float r02 = (r0.top - r6) - r2;
        r4.translate(-r12, -r02);
        drawCompatShadow(r4);
        r8.drawBitmap(r3, r12, r02, null);
        r3.recycle();
        r8.restore();
        return;
    L15:
        throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path. extra width: " + r1 + " extra height: " + r2 + " path bounds: " + this.pathBounds);
    }

    private static int modulateAlpha(int r1, int r2) {
        return (r1 * (r2 + (r2 >>> 7))) >>> 8;
    }

    private void prepareCanvasForShadow(Canvas r2) {
        r2.translate(getShadowOffsetX(), getShadowOffsetY());
    }

    private void setStateListShapeAppearanceModel(StateListShapeAppearanceModel r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.shapeAppearance == r3) goto L6;
        r0.shapeAppearance = r3;
        updateShape(getState(), true);
        invalidateSelf();
        return;
    }

    private boolean shouldCalculatePath() {
        if (hasCompatShadow() == false) goto L5;
        return true;
    L5:
        if (isRoundRect() == false) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean updateColorsForState(int[] r5) {
        if (this.drawableState.fillColor == null) goto L7;
        int r0 = this.fillPaint.getColor();
        int r2 = this.drawableState.fillColor.getColorForState(r5, r0);
        if (r0 == r2) goto L7;
        this.fillPaint.setColor(r2);
        boolean r02 = true;
    L9:
        if (this.drawableState.strokeColor == null) goto L14;
        int r22 = this.strokePaint.getColor();
        int r52 = this.drawableState.strokeColor.getColorForState(r5, r22);
        if (r22 == r52) goto L14;
        this.strokePaint.setColor(r52);
        return true;
    L14:
        return r02;
    L7:
        r02 = false;
        goto L9
    }

    private void updateShape(int[] r2) {
        updateShape(r2, false);
    }

    private void updateShape(int[] r6, boolean r7) {
        RectF r0 = getBoundsAsRectF();
        if (this.drawableState.shapeAppearance.isStateful() == true) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L43;
        int r2 = 0;
        if (this.cornerSpringForce != null) goto L10;
        boolean r1 = true;
    L11:
        boolean r72 = r7 | r1;
        if (this.springAnimatedCornerSizes != null) goto L14;
        this.springAnimatedCornerSizes = new float[4];
    L14:
        ShapeAppearanceModel r62 = this.drawableState.shapeAppearance.getShapeForState(r6);
        if (MathUtils.areAllElementsEqual(this.springAnimatedCornerSizes) == true) goto L17;
    L19:
        boolean r12 = false;
    L20:
        this.isRoundRectCornerMorph = r12;
        if (r12 == true) goto L23;
        this.pathDirty = true;
        this.strokePathDirty = true;
    L23:
        if (r2 >= 4) goto L33;
        float r13 = this.pathProvider.getCornerSizeForIndex(r2, r62).getCornerSize(r0);
        if (r72 == false) goto L27;
        this.springAnimatedCornerSizes[r2] = r13;
    L27:
        SpringAnimation r3 = this.cornerSpringAnimations[r2];
        if (r3 == null) goto L32;
        r3.animateToFinalPosition(r13);
        if (r72 == false) goto L32;
        this.cornerSpringAnimations[r2].skipToEnd();
    L32:
        r2 = r2 + 1;
        goto L23
    L33:
        if (r72 == false) goto L42;
        invalidateSelf();
        return;
    L42:
        return;
    L17:
        if (r62.isRoundRect(getBoundsAsRectF()) == false) goto L19;
        r12 = true;
        goto L20
    L10:
        r1 = false;
        goto L11
    }

    private void updateStrokeShapeAppearanceModels() {
        this.strokeShapeAppearanceModel = getShapeAppearanceModel().withTransformedCornerSizes(this.strokeInsetCornerSizeUnaryOperator);
        float[] r0 = this.springAnimatedCornerSizes;
        if (r0 != null) goto L7;
        this.springAnimatedStrokeCornerSizes = null;
        return;
    L7:
        if (this.springAnimatedStrokeCornerSizes != null) goto L9;
        this.springAnimatedStrokeCornerSizes = new float[r0.length];
    L9:
        float r02 = getStrokeInsetLength();
        int r1 = 0;
    L10:
        float[] r2 = this.springAnimatedCornerSizes;
        if (r1 >= r2.length) goto L13;
        this.springAnimatedStrokeCornerSizes[r1] = Math.max(0.0f, r2[r1] - r02);
        r1 = r1 + 1;
        goto L10
    }

    private boolean updateTintFilter() {
        PorterDuffColorFilter r0 = this.tintFilter;
        PorterDuffColorFilter r1 = this.strokeTintFilter;
        MaterialShapeDrawableState r2 = this.drawableState;
        this.tintFilter = calculateTintFilter(r2.tintList, r2.tintMode, this.fillPaint, true);
        MaterialShapeDrawableState r22 = this.drawableState;
        this.strokeTintFilter = calculateTintFilter(r22.strokeTintList, r22.tintMode, this.strokePaint, false);
        MaterialShapeDrawableState r23 = this.drawableState;
        if (r23.useTintColorForShadow == false) goto L6;
        this.shadowRenderer.setShadowColor(r23.tintList.getColorForState(getState(), 0));
    L6:
        if (ObjectsCompat.equals(r0, this.tintFilter) == true) goto L8;
    L11:
        return true;
    L8:
        if (ObjectsCompat.equals(r1, this.strokeTintFilter) == false) goto L11;
        return false;
    }

    private void updateZ() {
        float r0 = getZ();
        this.drawableState.shadowCompatRadius = (int) Math.ceil(SHADOW_RADIUS_MULTIPLIER * r0);
        this.drawableState.shadowCompatOffset = (int) Math.ceil(r0 * SHADOW_OFFSET_MULTIPLIER);
        updateTintFilter();
        if (shouldCalculatePath() == false) goto L6;
        invalidateSelf();
        return;
    L6:
        invalidateSelfIgnoreShape();
    }

    public final void calculatePathForSize(RectF r8, Path r9) {
        this.pathProvider.calculatePath(this.drawableState.shapeAppearance.getDefaultShape(), this.springAnimatedCornerSizes, this.drawableState.interpolation, r8, this.pathShadowListener, r9);
    }

    public float calculateRoundRectCornerSize() {
        float r0 = calculateRoundRectCornerSize(getBoundsAsRectF(), this.drawableState.shapeAppearance.getDefaultShape(), this.springAnimatedCornerSizes);
        if (r0 >= 0.0f) goto L5;
        return r0;
    L5:
        return r0 * this.drawableState.interpolation;
    }

    public int compositeElevationOverlayIfNeeded(int r3) {
        float r0 = getZ() + getParentAbsoluteElevation();
        ElevationOverlayProvider r2 = this.drawableState.elevationOverlayProvider;
        if (r2 != null) goto L5;
        return r3;
    L5:
        return r2.compositeOverlayIfNeeded(r3, r0);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r7) {
        this.fillPaint.setColorFilter(this.tintFilter);
        int r0 = this.fillPaint.getAlpha();
        this.fillPaint.setAlpha(modulateAlpha(r0, this.drawableState.alpha));
        this.strokePaint.setColorFilter(this.strokeTintFilter);
        this.strokePaint.setStrokeWidth(this.drawableState.strokeWidth);
        int r1 = this.strokePaint.getAlpha();
        this.strokePaint.setAlpha(modulateAlpha(r1, this.drawableState.alpha));
        boolean r2 = shouldCalculatePath();
        if (hasFill() == false) goto L11;
        if (this.pathDirty == false) goto L9;
        if (r2 == false) goto L8;
        calculatePath(getBoundsAsRectF(), this.path);
    L8:
        this.pathDirty = false;
    L9:
        maybeDrawCompatShadow(r7);
        drawFillShape(r7);
    L11:
        if (hasStroke() == true) goto L13;
    L19:
        this.fillPaint.setAlpha(r0);
        this.strokePaint.setAlpha(r1);
        return;
    L13:
        if (this.strokePathDirty == false) goto L18;
        updateStrokeShapeAppearanceModels();
        if (r2 == false) goto L17;
        calculateStrokePath();
    L17:
        this.strokePathDirty = false;
    L18:
        drawStrokeShape(r7);
        goto L19
    }

    public void drawShape(Canvas r9, Paint r10, Path r11, RectF r12) {
        drawShape(r9, r10, r11, this.drawableState.shapeAppearance.getDefaultShape(), this.springAnimatedCornerSizes, r12);
    }

    public void drawStrokeShape(Canvas r8) {
        drawShape(r8, this.strokePaint, this.pathInsetByStroke, this.strokeShapeAppearanceModel, this.springAnimatedStrokeCornerSizes, getBoundsInsetByStroke());
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.drawableState.alpha;
    }

    public float getBottomLeftCornerResolvedSize() {
        float[] r0 = this.springAnimatedCornerSizes;
        if (r0 == null) goto L7;
        return r0[2];
    L7:
        return this.drawableState.shapeAppearance.getDefaultShape().getBottomLeftCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getBottomRightCornerResolvedSize() {
        float[] r0 = this.springAnimatedCornerSizes;
        if (r0 == null) goto L7;
        return r0[1];
    L7:
        return this.drawableState.shapeAppearance.getDefaultShape().getBottomRightCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public RectF getBoundsAsRectF() {
        this.rectF.set(getBounds());
        return this.rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    public float getCornerSizeDiffX() {
        float[] r0 = this.springAnimatedCornerSizes;
        if (r0 != null) goto L5;
        RectF r02 = getBoundsAsRectF();
        return (((this.pathProvider.getCornerSizeForIndex(3, getShapeAppearanceModel()).getCornerSize(r02) + this.pathProvider.getCornerSizeForIndex(2, getShapeAppearanceModel()).getCornerSize(r02)) - this.pathProvider.getCornerSizeForIndex(1, getShapeAppearanceModel()).getCornerSize(r02)) - this.pathProvider.getCornerSizeForIndex(0, getShapeAppearanceModel()).getCornerSize(r02)) / 2.0f;
    L5:
        return (((r0[3] + r0[2]) - r0[1]) - r0[0]) / 2.0f;
    }

    public SpringForce getCornerSpringForce() {
        return this.cornerSpringForce;
    }

    public float getElevation() {
        return this.drawableState.elevation;
    }

    public ColorStateList getFillColor() {
        return this.drawableState.fillColor;
    }

    public float getInterpolation() {
        return this.drawableState.interpolation;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline r4) {
        if (this.drawableState.shadowCompatMode == 2) goto L17;
        RectF r0 = getBoundsAsRectF();
        if (r0.isEmpty() == false) goto L8;
        return;
    L8:
        float r1 = calculateRoundRectCornerSize(r0, this.drawableState.shapeAppearance.getDefaultShape(), this.springAnimatedCornerSizes);
        if (r1 < 0.0f) goto L13;
        r4.setRoundRect(getBounds(), r1 * this.drawableState.interpolation);
        return;
    L13:
        if (this.pathDirty == false) goto L15;
        calculatePath(r0, this.path);
        this.pathDirty = false;
    L15:
        DrawableUtils.setOutlineToPath(r4, this.path);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect r2) {
        Rect r0 = this.drawableState.padding;
        if (r0 == null) goto L7;
        r2.set(r0);
        return true;
    L7:
        return super.getPadding(r2);
    }

    public Paint.Style getPaintStyle() {
        return this.drawableState.paintStyle;
    }

    public float getParentAbsoluteElevation() {
        return this.drawableState.parentAbsoluteElevation;
    }

    public Path getPath() {
        return this.path;
    }

    @Deprecated
    public void getPathForSize(int r3, int r4, Path r5) {
        calculatePathForSize(new RectF(0.0f, 0.0f, r3, r4), r5);
    }

    public int getResolvedTintColor() {
        return this.resolvedTintColor;
    }

    public float getScale() {
        return this.drawableState.scale;
    }

    public int getShadowCompatRotation() {
        return this.drawableState.shadowCompatRotation;
    }

    public int getShadowCompatibilityMode() {
        return this.drawableState.shadowCompatMode;
    }

    @Deprecated
    public int getShadowElevation() {
        return (int) getElevation();
    }

    public int getShadowOffsetX() {
        MaterialShapeDrawableState r4 = this.drawableState;
        return (int) (r4.shadowCompatOffset * Math.sin(Math.toRadians(r4.shadowCompatRotation)));
    }

    public int getShadowOffsetY() {
        MaterialShapeDrawableState r4 = this.drawableState;
        return (int) (r4.shadowCompatOffset * Math.cos(Math.toRadians(r4.shadowCompatRotation)));
    }

    public int getShadowRadius() {
        return this.drawableState.shadowCompatRadius;
    }

    public int getShadowVerticalOffset() {
        return this.drawableState.shadowCompatOffset;
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.shapeAppearance.getDefaultShape();
    }

    @Deprecated
    public ShapePathModel getShapedViewModel() {
        ShapeAppearanceModel r1 = getShapeAppearanceModel();
        if ((r1 instanceof ShapePathModel) == true) goto L5;
        return null;
    L5:
        return (ShapePathModel) r1;
    }

    public StateListShapeAppearanceModel getStateListShapeAppearanceModel() {
        ShapeAppearance r1 = this.drawableState.shapeAppearance;
        if ((r1 instanceof StateListShapeAppearanceModel) == true) goto L5;
        return null;
    L5:
        return (StateListShapeAppearanceModel) r1;
    }

    public ColorStateList getStrokeColor() {
        return this.drawableState.strokeColor;
    }

    public ColorStateList getStrokeTintList() {
        return this.drawableState.strokeTintList;
    }

    public float getStrokeWidth() {
        return this.drawableState.strokeWidth;
    }

    public ColorStateList getTintList() {
        return this.drawableState.tintList;
    }

    public float getTopLeftCornerResolvedSize() {
        float[] r0 = this.springAnimatedCornerSizes;
        if (r0 == null) goto L7;
        return r0[3];
    L7:
        return this.drawableState.shapeAppearance.getDefaultShape().getTopLeftCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getTopRightCornerResolvedSize() {
        float[] r0 = this.springAnimatedCornerSizes;
        if (r0 == null) goto L7;
        return r0[0];
    L7:
        return this.drawableState.shapeAppearance.getDefaultShape().getTopRightCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getTranslationZ() {
        return this.drawableState.translationZ;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        Rect r0 = getBounds();
        this.transparentRegion.set(r0);
        calculatePath(getBoundsAsRectF(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public float getZ() {
        return getElevation() + getTranslationZ();
    }

    public void initializeElevationOverlay(Context r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        r0.elevationOverlayProvider = new ElevationOverlayProvider(r3);
        updateZ();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.pathDirty = true;
        this.strokePathDirty = true;
        super.invalidateSelf();
    }

    public boolean isElevationOverlayEnabled() {
        ElevationOverlayProvider r0 = this.drawableState.elevationOverlayProvider;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isThemeElevationOverlayEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isElevationOverlayInitialized() {
        if (this.drawableState.elevationOverlayProvider == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPointInTransparentRegion(int r1, int r2) {
        return getTransparentRegion().contains(r1, r2);
    }

    public boolean isRoundRect() {
        if (this.drawableState.shapeAppearance.getShapeForState(getState()).isRoundRect(getBoundsAsRectF()) == true) goto L5;
        return false;
    L5:
        if (this.springAnimatedCornerSizes != null) goto L7;
        return true;
    L7:
        if (this.isRoundRectCornerMorph == false) goto L13;
        return true;
    L13:
        return false;
    }

    @Deprecated
    public boolean isShadowEnabled() {
        int r1 = this.drawableState.shadowCompatMode;
        if (r1 != 0) goto L5;
        return true;
    L5:
        if (r1 == 2) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (super.isStateful() == true) goto L25;
        ColorStateList r0 = this.drawableState.tintList;
        if (r0 != null) goto L7;
    L8:
        ColorStateList r02 = this.drawableState.strokeTintList;
        if (r02 != null) goto L11;
    L12:
        ColorStateList r03 = this.drawableState.strokeColor;
        if (r03 != null) goto L15;
    L16:
        ColorStateList r04 = this.drawableState.fillColor;
        if (r04 == null) goto L21;
        if (r04.isStateful() == false) goto L21;
        return true;
    L21:
        if (this.drawableState.shapeAppearance.isStateful() == true) goto L31;
        return false;
    L31:
        return true;
    L15:
        if (r03.isStateful() == false) goto L16;
        return true;
    L11:
        if (r02.isStateful() == false) goto L12;
        return true;
    L7:
        if (r0.isStateful() == false) goto L8;
        return true;
    L25:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.drawableState = new MaterialShapeDrawableState(this.drawableState);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r3) {
        this.pathDirty = true;
        this.strokePathDirty = true;
        super.onBoundsChange(r3);
        if (this.drawableState.shapeAppearance.isStateful() == true) goto L5;
        return;
    L5:
        if (r3.isEmpty() == true) goto L9;
        updateShape(getState(), true ^ isCornerSpringAnimationRunning());
        return;
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] r2) {
        if (this.drawableState.shapeAppearance.isStateful() == false) goto L5;
        updateShape(r2);
    L5:
        boolean r22 = updateColorsForState(r2);
        boolean r0 = updateTintFilter();
        if (r22 == true) goto L10;
        if (r0 == true) goto L10;
        boolean r23 = false;
    L11:
        if (r23 == false) goto L13;
        invalidateSelf();
    L13:
        return r23;
    L10:
        r23 = true;
        goto L11
    }

    public boolean requiresCompatShadow() {
        if (isRoundRect() == false) goto L5;
        return false;
    L5:
        if (this.path.isConvex() == false) goto L7;
        return false;
    L7:
        if (Build.VERSION.SDK_INT >= 29) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.alpha == r3) goto L6;
        r0.alpha = r3;
        invalidateSelfIgnoreShape();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.drawableState.colorFilter = r2;
        invalidateSelfIgnoreShape();
    }

    public void setCornerSize(float r2) {
        setShapeAppearanceModel(this.drawableState.shapeAppearance.withCornerSize(r2));
    }

    public void setCornerSize(CornerSize r2) {
        setShapeAppearanceModel(this.drawableState.shapeAppearance.withCornerSize(r2));
    }

    public void setCornerSpringForce(SpringForce r5) {
        if (this.cornerSpringForce == r5) goto L16;
        this.cornerSpringForce = r5;
        int r0 = 0;
    L5:
        SpringAnimation[] r1 = this.cornerSpringAnimations;
        if (r0 >= r1.length) goto L11;
        if (r1[r0] != null) goto L10;
        r1[r0] = new SpringAnimation(this, CORNER_SIZES_IN_PX[r0]);
    L10:
        this.cornerSpringAnimations[r0].setSpring(new SpringForce().setDampingRatio(r5.getDampingRatio()).setStiffness(r5.getStiffness()));
        r0 = r0 + 1;
        goto L5
    L11:
        updateShape(getState(), true);
        invalidateSelf();
        return;
    }

    public void setEdgeIntersectionCheckEnable(boolean r1) {
        this.pathProvider.setEdgeIntersectionCheckEnable(r1);
    }

    public void setElevation(float r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.elevation == r3) goto L6;
        r0.elevation = r3;
        updateZ();
        return;
    }

    public void setFillColor(ColorStateList r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.fillColor == r3) goto L6;
        r0.fillColor = r3;
        onStateChange(getState());
        return;
    }

    public void setInterpolation(float r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.interpolation == r3) goto L6;
        r0.interpolation = r3;
        this.pathDirty = true;
        this.strokePathDirty = true;
        invalidateSelf();
        return;
    }

    public void setOnCornerSizeChangeListener(OnCornerSizeChangeListener r1) {
        this.onCornerSizeChangeListener = r1;
    }

    public void setPadding(int r3, int r4, int r5, int r6) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.padding != null) goto L5;
        r0.padding = new Rect();
    L5:
        this.drawableState.padding.set(r3, r4, r5, r6);
        invalidateSelf();
    }

    public void setPaintStyle(Paint.Style r2) {
        this.drawableState.paintStyle = r2;
        invalidateSelfIgnoreShape();
    }

    public void setParentAbsoluteElevation(float r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.parentAbsoluteElevation == r3) goto L6;
        r0.parentAbsoluteElevation = r3;
        updateZ();
        return;
    }

    public void setScale(float r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.scale == r3) goto L6;
        r0.scale = r3;
        invalidateSelf();
        return;
    }

    public void setShadowBitmapDrawingEnable(boolean r1) {
        this.shadowBitmapDrawingEnable = r1;
    }

    public void setShadowColor(int r2) {
        this.shadowRenderer.setShadowColor(r2);
        this.drawableState.useTintColorForShadow = false;
        invalidateSelfIgnoreShape();
    }

    public void setShadowCompatRotation(int r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.shadowCompatRotation == r3) goto L6;
        r0.shadowCompatRotation = r3;
        invalidateSelfIgnoreShape();
        return;
    }

    public void setShadowCompatibilityMode(int r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.shadowCompatMode == r3) goto L6;
        r0.shadowCompatMode = r3;
        invalidateSelfIgnoreShape();
        return;
    }

    @Deprecated
    public void setShadowElevation(int r1) {
        setElevation(r1);
    }

    @Deprecated
    public void setShadowEnabled(boolean r1) {
        setShadowCompatibilityMode(!r1 ? 1 : 0);
    }

    @Deprecated
    public void setShadowRadius(int r1) {
        this.drawableState.shadowCompatRadius = r1;
    }

    public void setShadowVerticalOffset(int r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.shadowCompatOffset == r3) goto L6;
        r0.shadowCompatOffset = r3;
        invalidateSelfIgnoreShape();
        return;
    }

    public void setShapeAppearance(ShapeAppearance r2) {
        if ((r2 instanceof ShapeAppearanceModel) == false) goto L6;
        setShapeAppearanceModel((ShapeAppearanceModel) r2);
        return;
    L6:
        setStateListShapeAppearanceModel((StateListShapeAppearanceModel) r2);
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r2) {
        this.drawableState.shapeAppearance = r2;
        this.springAnimatedCornerSizes = null;
        this.springAnimatedStrokeCornerSizes = null;
        invalidateSelf();
    }

    @Deprecated
    public void setShapedViewModel(ShapePathModel r1) {
        setShapeAppearanceModel(r1);
    }

    public void setStroke(float r1, int r2) {
        setStrokeWidth(r1);
        setStrokeColor(ColorStateList.valueOf(r2));
    }

    public void setStroke(float r1, ColorStateList r2) {
        setStrokeWidth(r1);
        setStrokeColor(r2);
    }

    public void setStrokeColor(ColorStateList r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.strokeColor == r3) goto L6;
        r0.strokeColor = r3;
        onStateChange(getState());
        return;
    }

    public void setStrokeTint(int r1) {
        setStrokeTint(ColorStateList.valueOf(r1));
    }

    public void setStrokeTint(ColorStateList r2) {
        this.drawableState.strokeTintList = r2;
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    public void setStrokeWidth(float r2) {
        this.drawableState.strokeWidth = r2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int r1) {
        setTintList(ColorStateList.valueOf(r1));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList r2) {
        this.drawableState.tintList = r2;
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.tintMode == r3) goto L6;
        r0.tintMode = r3;
        updateTintFilter();
        invalidateSelfIgnoreShape();
        return;
    }

    public void setTranslationZ(float r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.translationZ == r3) goto L6;
        r0.translationZ = r3;
        updateZ();
        return;
    }

    public void setUseTintColorForShadow(boolean r3) {
        MaterialShapeDrawableState r0 = this.drawableState;
        if (r0.useTintColorForShadow == r3) goto L6;
        r0.useTintColorForShadow = r3;
        invalidateSelf();
        return;
    }

    public void setZ(float r2) {
        setTranslationZ(r2 - getElevation());
    }
}
