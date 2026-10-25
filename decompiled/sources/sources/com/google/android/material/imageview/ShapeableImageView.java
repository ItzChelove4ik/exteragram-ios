package com.google.android.material.imageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class ShapeableImageView extends AppCompatImageView implements Shapeable {
    private static final int DEF_STYLE_RES = 0;
    private static final int UNDEFINED_PADDING = Integer.MIN_VALUE;
    private final Paint borderPaint;
    private int bottomContentPadding;
    private final Paint clearPaint;
    private final RectF destination;
    private int endContentPadding;
    private boolean hasAdjustedPaddingAfterLayoutDirectionResolved;
    private int leftContentPadding;
    private Path maskPath;
    private final RectF maskRect;
    private final Path path;
    private final ShapeAppearancePathProvider pathProvider;
    private int rightContentPadding;
    private MaterialShapeDrawable shadowDrawable;
    private ShapeAppearanceModel shapeAppearanceModel;
    private int startContentPadding;
    private ColorStateList strokeColor;
    private float strokeWidth;
    private int topContentPadding;

    public class OutlineProvider extends ViewOutlineProvider {
        private final Rect rect;
        final /* synthetic */ ShapeableImageView this$0;

        public OutlineProvider(ShapeableImageView r1) {
            this.this$0 = r1;
            this.rect = new Rect();
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r3, Outline r4) {
            if (ShapeableImageView.access$000(this.this$0) != null) goto L6;
            return;
        L6:
            if (ShapeableImageView.access$100(this.this$0) != null) goto L8;
            ShapeableImageView.access$102(this.this$0, new MaterialShapeDrawable(ShapeableImageView.access$000(this.this$0)));
        L8:
            ShapeableImageView.access$200(this.this$0).round(this.rect);
            ShapeableImageView.access$100(this.this$0).setBounds(this.rect);
            ShapeableImageView.access$100(this.this$0).getOutline(r4);
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_ShapeableImageView;
    }

    public ShapeableImageView(Context r3) {
        this(r3, null, 0);
    }

    public ShapeableImageView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ShapeableImageView(Context r7, AttributeSet r8, int r9) {
        int r0 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r0), r8, r9);
        this.pathProvider = ShapeAppearancePathProvider.getInstance();
        this.path = new Path();
        this.hasAdjustedPaddingAfterLayoutDirectionResolved = false;
        Context r1 = getContext();
        Paint r2 = new Paint();
        this.clearPaint = r2;
        r2.setAntiAlias(true);
        r2.setColor(-1);
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.destination = new RectF();
        this.maskRect = new RectF();
        this.maskPath = new Path();
        TypedArray r22 = r1.obtainStyledAttributes(r8, R.styleable.ShapeableImageView, r9, r0);
        setLayerType(2, null);
        this.strokeColor = MaterialResources.getColorStateList(r1, r22, R.styleable.ShapeableImageView_strokeColor);
        this.strokeWidth = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_strokeWidth, 0);
        int r72 = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_contentPadding, 0);
        this.leftContentPadding = r72;
        this.topContentPadding = r72;
        this.rightContentPadding = r72;
        this.bottomContentPadding = r72;
        this.leftContentPadding = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_contentPaddingLeft, r72);
        this.topContentPadding = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_contentPaddingTop, r72);
        this.rightContentPadding = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_contentPaddingRight, r72);
        this.bottomContentPadding = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_contentPaddingBottom, r72);
        this.startContentPadding = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_contentPaddingStart, Integer.MIN_VALUE);
        this.endContentPadding = r22.getDimensionPixelSize(R.styleable.ShapeableImageView_contentPaddingEnd, Integer.MIN_VALUE);
        r22.recycle();
        Paint r73 = new Paint();
        this.borderPaint = r73;
        r73.setStyle(Paint.Style.STROKE);
        r73.setAntiAlias(true);
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(r1, r8, r9, r0).build();
        setOutlineProvider(new OutlineProvider(this));
    }

    public static /* synthetic */ ShapeAppearanceModel access$000(ShapeableImageView r0) {
        return r0.shapeAppearanceModel;
    }

    public static /* synthetic */ MaterialShapeDrawable access$100(ShapeableImageView r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ MaterialShapeDrawable access$102(ShapeableImageView r0, MaterialShapeDrawable r1) {
        r0.shadowDrawable = r1;
        return r1;
    }

    public static /* synthetic */ RectF access$200(ShapeableImageView r0) {
        return r0.destination;
    }

    private void drawStroke(Canvas r4) {
        if (this.strokeColor == null) goto L12;
        this.borderPaint.setStrokeWidth(this.strokeWidth);
        int r0 = this.strokeColor.getColorForState(getDrawableState(), this.strokeColor.getDefaultColor());
        if (this.strokeWidth <= 0.0f) goto L10;
        if (r0 == 0) goto L11;
        this.borderPaint.setColor(r0);
        r4.drawPath(this.path, this.borderPaint);
        return;
    L11:
        return;
    L10:
        return;
    }

    private boolean isContentPaddingRelative() {
        if (this.startContentPadding == Integer.MIN_VALUE) goto L5;
        return true;
    L5:
        if (this.endContentPadding != Integer.MIN_VALUE) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isRtl() {
        if (getLayoutDirection() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private void updateShapeMask(int r6, int r7) {
        this.destination.set(getPaddingLeft(), getPaddingTop(), r6 - getPaddingRight(), r7 - getPaddingBottom());
        this.pathProvider.calculatePath(this.shapeAppearanceModel, 1.0f, this.destination, this.path);
        this.maskPath.rewind();
        this.maskPath.addPath(this.path);
        this.maskRect.set(0.0f, 0.0f, r6, r7);
        this.maskPath.addRect(this.maskRect, Path.Direction.CCW);
    }

    public int getContentPaddingBottom() {
        return this.bottomContentPadding;
    }

    public final int getContentPaddingEnd() {
        int r0 = this.endContentPadding;
        if (r0 == Integer.MIN_VALUE) goto L6;
        return r0;
    L6:
        if (isRtl() == false) goto L10;
        return this.leftContentPadding;
    L10:
        return this.rightContentPadding;
    }

    public int getContentPaddingLeft() {
        if (isContentPaddingRelative() == false) goto L15;
        if (isRtl() == false) goto L10;
        int r0 = this.endContentPadding;
        if (r0 == Integer.MIN_VALUE) goto L10;
        return r0;
    L10:
        if (isRtl() == true) goto L15;
        int r02 = this.startContentPadding;
        if (r02 == Integer.MIN_VALUE) goto L15;
        return r02;
    L15:
        return this.leftContentPadding;
    }

    public int getContentPaddingRight() {
        if (isContentPaddingRelative() == false) goto L15;
        if (isRtl() == false) goto L10;
        int r0 = this.startContentPadding;
        if (r0 == Integer.MIN_VALUE) goto L10;
        return r0;
    L10:
        if (isRtl() == true) goto L15;
        int r02 = this.endContentPadding;
        if (r02 == Integer.MIN_VALUE) goto L15;
        return r02;
    L15:
        return this.rightContentPadding;
    }

    public final int getContentPaddingStart() {
        int r0 = this.startContentPadding;
        if (r0 == Integer.MIN_VALUE) goto L6;
        return r0;
    L6:
        if (isRtl() == false) goto L10;
        return this.rightContentPadding;
    L10:
        return this.leftContentPadding;
    }

    public int getContentPaddingTop() {
        return this.topContentPadding;
    }

    @Override // android.view.View
    public int getPaddingBottom() {
        return super.getPaddingBottom() - getContentPaddingBottom();
    }

    @Override // android.view.View
    public int getPaddingEnd() {
        return super.getPaddingEnd() - getContentPaddingEnd();
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        return super.getPaddingLeft() - getContentPaddingLeft();
    }

    @Override // android.view.View
    public int getPaddingRight() {
        return super.getPaddingRight() - getContentPaddingRight();
    }

    @Override // android.view.View
    public int getPaddingStart() {
        return super.getPaddingStart() - getContentPaddingStart();
    }

    @Override // android.view.View
    public int getPaddingTop() {
        return super.getPaddingTop() - getContentPaddingTop();
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    public ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas r3) {
        super.onDraw(r3);
        r3.drawPath(this.maskPath, this.clearPaint);
        drawStroke(r3);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, r4);
        if (this.hasAdjustedPaddingAfterLayoutDirectionResolved == false) goto L6;
        return;
    L6:
        if (isLayoutDirectionResolved() == true) goto L8;
        return;
    L8:
        this.hasAdjustedPaddingAfterLayoutDirectionResolved = true;
        if (isPaddingRelative() == false) goto L11;
    L15:
        setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
        return;
    L11:
        if (isContentPaddingRelative() == true) goto L15;
        setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        updateShapeMask(r1, r2);
    }

    public void setContentPadding(int r6, int r7, int r8, int r9) {
        this.startContentPadding = Integer.MIN_VALUE;
        this.endContentPadding = Integer.MIN_VALUE;
        super.setPadding((super.getPaddingLeft() - this.leftContentPadding) + r6, (super.getPaddingTop() - this.topContentPadding) + r7, (super.getPaddingRight() - this.rightContentPadding) + r8, (super.getPaddingBottom() - this.bottomContentPadding) + r9);
        this.leftContentPadding = r6;
        this.topContentPadding = r7;
        this.rightContentPadding = r8;
        this.bottomContentPadding = r9;
    }

    public void setContentPaddingRelative(int r6, int r7, int r8, int r9) {
        super.setPaddingRelative((super.getPaddingStart() - getContentPaddingStart()) + r6, (super.getPaddingTop() - this.topContentPadding) + r7, (super.getPaddingEnd() - getContentPaddingEnd()) + r8, (super.getPaddingBottom() - this.bottomContentPadding) + r9);
        if (isRtl() == false) goto L5;
        int r0 = r8;
    L6:
        this.leftContentPadding = r0;
        this.topContentPadding = r7;
        if (isRtl() == true) goto L10;
        r6 = r8;
    L10:
        this.rightContentPadding = r6;
        this.bottomContentPadding = r9;
        return;
    L5:
        r0 = r6;
        goto L6
    }

    @Override // android.view.View
    public void setPadding(int r2, int r3, int r4, int r5) {
        super.setPadding(r2 + getContentPaddingLeft(), r3 + getContentPaddingTop(), r4 + getContentPaddingRight(), r5 + getContentPaddingBottom());
    }

    @Override // android.view.View
    public void setPaddingRelative(int r2, int r3, int r4, int r5) {
        super.setPaddingRelative(r2 + getContentPaddingStart(), r3 + getContentPaddingTop(), r4 + getContentPaddingEnd(), r5 + getContentPaddingBottom());
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r2) {
        this.shapeAppearanceModel = r2;
        MaterialShapeDrawable r0 = this.shadowDrawable;
        if (r0 == null) goto L5;
        r0.setShapeAppearanceModel(r2);
    L5:
        updateShapeMask(getWidth(), getHeight());
        invalidate();
        invalidateOutline();
    }

    public void setStrokeColor(ColorStateList r1) {
        this.strokeColor = r1;
        invalidate();
    }

    public void setStrokeColorResource(int r2) {
        setStrokeColor(AppCompatResources.getColorStateList(getContext(), r2));
    }

    public void setStrokeWidth(float r2) {
        if (this.strokeWidth == r2) goto L6;
        this.strokeWidth = r2;
        invalidate();
        return;
    }

    public void setStrokeWidthResource(int r2) {
        setStrokeWidth(getResources().getDimensionPixelSize(r2));
    }
}
