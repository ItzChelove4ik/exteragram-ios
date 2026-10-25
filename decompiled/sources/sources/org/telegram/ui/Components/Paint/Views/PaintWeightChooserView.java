package org.telegram.ui.Components.Paint.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.math.MathUtils;
import androidx.core.view.GestureDetectorCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Paint.RenderView;
import org.telegram.ui.Components.Paint.Swatch;

/* loaded from: classes7.dex */
public class PaintWeightChooserView extends View {
    private AnimatedFloat animatedMax;
    private AnimatedFloat animatedMin;
    private AnimatedFloat animatedWeight;
    private Paint backgroundPaint;
    private Paint colorPaint;
    private Swatch colorSwatch;
    private boolean drawCenter;
    private GestureDetectorCompat gestureDetector;
    private float hideProgress;
    private boolean isPanTransitionInProgress;
    private boolean isTouchInProgress;
    private boolean isViewHidden;
    private long lastUpdate;
    private float max;
    private float min;
    private Runnable onUpdate;
    private Path path;
    private RenderView renderView;
    private boolean showPreview;
    private float showProgress;
    private RectF touchRect;
    private ValueOverride valueOverride;

    /* renamed from: org.telegram.ui.Components.Paint.Views.PaintWeightChooserView$1, reason: invalid class name */
    public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        float startDeltaY;
        float startWeight;
        boolean startedY;
        final /* synthetic */ PaintWeightChooserView this$0;

        public AnonymousClass1(PaintWeightChooserView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r3) {
            boolean r32 = PaintWeightChooserView.m11572$$Nest$fgettouchRect(this.this$0).contains(r3.getX(), r3.getY());
            if (PaintWeightChooserView.m11568$$Nest$fgetisTouchInProgress(this.this$0) == r32) goto L12;
            PaintWeightChooserView.m11574$$Nest$fputisTouchInProgress(this.this$0, r32);
            this.this$0.invalidate();
            if (r32 == false) goto L12;
            ValueOverride r33 = PaintWeightChooserView.m11573$$Nest$fgetvalueOverride(this.this$0);
            PaintWeightChooserView r0 = this.this$0;
            if (r33 == null) goto L9;
            float r34 = PaintWeightChooserView.m11573$$Nest$fgetvalueOverride(r0).get();
        L10:
            this.startWeight = r34;
            this.startedY = false;
            goto L12
        L9:
            r34 = PaintWeightChooserView.m11567$$Nest$fgetcolorSwatch(r0).brushWeight;
        L12:
            return PaintWeightChooserView.m11568$$Nest$fgetisTouchInProgress(this.this$0);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r2, MotionEvent r3, float r4, float r5) {
            if (PaintWeightChooserView.m11568$$Nest$fgetisTouchInProgress(this.this$0) == false) goto L16;
            if (this.startedY == true) goto L7;
            this.startDeltaY = r2.getY() - r3.getY();
            this.startedY = true;
        L7:
            float r22 = MathUtils.clamp(this.startWeight + ((((r2.getY() - r3.getY()) - this.startDeltaY) / PaintWeightChooserView.m11572$$Nest$fgettouchRect(this.this$0).height()) * (PaintWeightChooserView.m11569$$Nest$fgetmax(this.this$0) - PaintWeightChooserView.m11570$$Nest$fgetmin(this.this$0))), PaintWeightChooserView.m11570$$Nest$fgetmin(this.this$0), PaintWeightChooserView.m11569$$Nest$fgetmax(this.this$0));
            ValueOverride r32 = PaintWeightChooserView.m11573$$Nest$fgetvalueOverride(this.this$0);
            PaintWeightChooserView r42 = this.this$0;
            if (r32 == null) goto L10;
            PaintWeightChooserView.m11573$$Nest$fgetvalueOverride(r42).set(r22);
        L11:
            PaintWeightChooserView.m11566$$Nest$fgetanimatedWeight(this.this$0).set(r22, true);
            if (PaintWeightChooserView.m11571$$Nest$fgetonUpdate(this.this$0) == null) goto L14;
            PaintWeightChooserView.m11571$$Nest$fgetonUpdate(this.this$0).run();
        L14:
            this.this$0.invalidate();
            goto L16
        L10:
            PaintWeightChooserView.m11567$$Nest$fgetcolorSwatch(r42).brushWeight = r22;
        L16:
            return PaintWeightChooserView.m11568$$Nest$fgetisTouchInProgress(this.this$0);
        }
    }

    public interface ValueOverride {
        float get();

        void set(float r1);
    }

    /* renamed from: -$$Nest$fgetanimatedWeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m11566$$Nest$fgetanimatedWeight(PaintWeightChooserView r0) {
        return r0.animatedWeight;
    }

    /* renamed from: -$$Nest$fgetcolorSwatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Swatch m11567$$Nest$fgetcolorSwatch(PaintWeightChooserView r0) {
        return r0.colorSwatch;
    }

    /* renamed from: -$$Nest$fgetisTouchInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11568$$Nest$fgetisTouchInProgress(PaintWeightChooserView r0) {
        return r0.isTouchInProgress;
    }

    /* renamed from: -$$Nest$fgetmax, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11569$$Nest$fgetmax(PaintWeightChooserView r0) {
        return r0.max;
    }

    /* renamed from: -$$Nest$fgetmin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11570$$Nest$fgetmin(PaintWeightChooserView r0) {
        return r0.min;
    }

    /* renamed from: -$$Nest$fgetonUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11571$$Nest$fgetonUpdate(PaintWeightChooserView r0) {
        return r0.onUpdate;
    }

    /* renamed from: -$$Nest$fgettouchRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m11572$$Nest$fgettouchRect(PaintWeightChooserView r0) {
        return r0.touchRect;
    }

    /* renamed from: -$$Nest$fgetvalueOverride, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueOverride m11573$$Nest$fgetvalueOverride(PaintWeightChooserView r0) {
        return r0.valueOverride;
    }

    /* renamed from: -$$Nest$fputisTouchInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11574$$Nest$fputisTouchInProgress(PaintWeightChooserView r0, boolean r1) {
        r0.isTouchInProgress = r1;
    }

    public PaintWeightChooserView(Context r6) {
        super(r6);
        this.backgroundPaint = new Paint(1);
        this.colorPaint = new Paint(1);
        this.path = new Path();
        this.touchRect = new RectF();
        this.showPreview = true;
        this.animatedWeight = new AnimatedFloat(this);
        this.animatedMin = new AnimatedFloat(this);
        this.animatedMax = new AnimatedFloat(this);
        this.colorSwatch = new Swatch(-1, 1.0f, 0.016773745f);
        this.drawCenter = true;
        this.gestureDetector = new GestureDetectorCompat(r6, new AnonymousClass1(this));
        this.colorPaint.setColor(-1);
        this.colorPaint.setShadowLayer(AndroidUtilities.dp(4.0f), 0.0f, AndroidUtilities.dp(2.0f), 1342177280);
        this.backgroundPaint.setColor(1090519039);
        this.backgroundPaint.setShadowLayer(AndroidUtilities.dp(3.0f), 0.0f, AndroidUtilities.dp(1.0f), 637534208);
    }

    private void drawCircleWithShadow(Canvas r7, float r8, float r9, float r10, boolean r11) {
        if (r11 == false) goto L4;
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set((r8 - r10) - AndroidUtilities.dp(6.0f), (r9 - r10) - AndroidUtilities.dp(6.0f), (r8 + r10) + AndroidUtilities.dp(6.0f), (r9 + r10) + AndroidUtilities.dp(6.0f));
        r7.saveLayerAlpha(r0, (int) (this.showProgress * 255.0f), 31);
    L4:
        r7.drawCircle(r8, r9, r10, this.colorPaint);
        if (r11 == false) goto L8;
        r7.restore();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        super.onDraw(r18);
        long r2 = Math.min(16, System.currentTimeMillis() - this.lastUpdate);
        this.lastUpdate = System.currentTimeMillis();
        AnimatedFloat r4 = this.animatedWeight;
        ValueOverride r5 = this.valueOverride;
        if (r5 == null) goto L5;
        float r52 = r5.get();
    L6:
        float r6 = r4.set(r52);
        float r42 = this.animatedMin.set(this.min);
        float r53 = this.animatedMax.set(this.max);
        boolean r7 = this.isViewHidden;
        if (r7 == false) goto L11;
        float r11 = this.hideProgress;
        if (r11 == 1.0f) goto L11;
        this.hideProgress = Math.min(1.0f, r11 + (r2 / 200.0f));
        invalidate();
    L15:
        boolean r72 = this.isTouchInProgress;
        if (r72 == false) goto L20;
        float r112 = this.showProgress;
        if (r112 == 1.0f) goto L20;
        this.showProgress = Math.min(1.0f, r112 + (r2 / 200.0f));
        invalidate();
    L24:
        float r22 = this.touchRect.height();
        int r3 = AndroidUtilities.dp(16.0f);
        int r8 = AndroidUtilities.dp(3.0f);
        int r73 = AndroidUtilities.dp(3.0f);
        this.path.rewind();
        this.path.moveTo(0.0f, 0.0f);
        RectF r113 = AndroidUtilities.rectTmp;
        int r12 = -r73;
        int r13 = -r3;
        r113.set(AndroidUtilities.lerp(r12, r13, this.showProgress), 0.0f, AndroidUtilities.lerp(r73, r3, this.showProgress), AndroidUtilities.lerp(r73, r3, this.showProgress) * 2);
        this.path.arcTo(r113, -90.0f, 90.0f);
        this.path.lineTo(AndroidUtilities.lerp(r73, r8, this.showProgress), r22);
        r113.set(AndroidUtilities.lerp(r12, -r8, this.showProgress), r22 - (r8 * 2), AndroidUtilities.lerp(r73, r8, this.showProgress), r22);
        this.path.arcTo(r113, 0.0f, 180.0f);
        float r14 = r3;
        this.path.lineTo(AndroidUtilities.lerp(r12, r13, this.showProgress), r14);
        r113.set(AndroidUtilities.lerp(r12, r13, this.showProgress), 0.0f, AndroidUtilities.lerp(r73, r3, this.showProgress), r3 * 2);
        this.path.arcTo(r113, -180.0f, 90.0f);
        this.path.close();
        if (this.hideProgress == 0.0f) goto L27;
        r113.set(0.0f, 0.0f, getWidth(), getHeight());
        r18.saveLayerAlpha(r113, (int) ((1.0f - this.hideProgress) * 255.0f), 31);
    L27:
        r18.save();
        float r32 = AndroidUtilities.dp(32.0f);
        CubicBezierInterpolator r74 = CubicBezierInterpolator.DEFAULT;
        r18.translate(r32 * r74.getInterpolation(this.showProgress), this.touchRect.top);
        r18.drawPath(this.path, this.backgroundPaint);
        r18.restore();
        float r23 = AndroidUtilities.dp(32.0f) * r74.getInterpolation(this.showProgress);
        RectF r33 = this.touchRect;
        float r9 = (r6 - r42) / (r53 - r42);
        float r75 = r33.top + (r33.height() * (1.0f - r9));
        RectF r34 = this.touchRect;
        float r54 = r8 * 1.5f;
        drawCircleWithShadow(r18, r23, MathUtils.clamp(r75, r34.top + r14, r34.bottom - Math.min(r54, r14)), AndroidUtilities.lerp(AndroidUtilities.dp(12.0f), AndroidUtilities.lerp(Math.min(r54, r14), r14, r9), this.showProgress), false);
        if (this.drawCenter == false) goto L37;
        if (this.showProgress == 0.0f) goto L37;
        if (this.showPreview == false) goto L37;
        if (this.renderView == null) goto L37;
        drawCircleWithShadow(r18, getWidth() / 2.0f, getHeight() / 2.0f, this.renderView.getCurrentBrush().getPreviewScale() * (this.renderView.brushWeightForSize(r6) * this.renderView.getCurrentBrush().getScale()), true);
    L37:
        if (this.hideProgress == 0.0f) goto L40;
        r18.restore();
        return;
    L40:
        return;
    L20:
        if (r72 == true) goto L24;
        float r76 = this.showProgress;
        if (r76 == 0.0f) goto L24;
        this.showProgress = Math.max(0.0f, r76 - (r2 / 200.0f));
        invalidate();
    L11:
        if (r7 == true) goto L15;
        float r77 = this.hideProgress;
        if (r77 == 0.0f) goto L15;
        this.hideProgress = Math.max(0.0f, r77 - (r2 / 200.0f));
        invalidate();
        goto L15
    L5:
        r52 = this.colorSwatch.brushWeight;
        goto L6
    }

    @Override // android.view.View
    public void onSizeChanged(int r2, int r3, int r4, int r5) {
        super.onSizeChanged(r2, r3, r4, r5);
        if (this.isPanTransitionInProgress == true) goto L6;
        int r22 = (int) (getHeight() * 0.3f);
        this.touchRect.set(0.0f, (getHeight() - r22) / 2.0f, AndroidUtilities.dp(32.0f), (getHeight() + r22) / 2.0f);
        return;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r4) {
        boolean r0 = this.gestureDetector.onTouchEvent(r4);
        if (r4.getActionMasked() != 1) goto L5;
    L8:
        this.isTouchInProgress = false;
        invalidate();
        return r0;
    L5:
        if (r4.getActionMasked() == 3) goto L8;
        return r0;
    }

    public void setBrushWeight(float r2) {
        this.colorSwatch.brushWeight = r2;
        invalidate();
    }

    public void setColorSwatch(Swatch r1) {
        this.colorSwatch = r1;
        invalidate();
    }

    public void setDrawCenter(boolean r1) {
        this.drawCenter = r1;
        invalidate();
    }

    public void setMinMax(float r1, float r2) {
        this.min = r1;
        this.max = r2;
        invalidate();
    }

    public void setOnUpdate(Runnable r1) {
        this.onUpdate = r1;
    }

    public void setRenderView(RenderView r1) {
        this.renderView = r1;
    }

    public void setShowPreview(boolean r1) {
        this.showPreview = r1;
        invalidate();
    }

    public void setValueOverride(ValueOverride r1) {
        this.valueOverride = r1;
        invalidate();
    }

    public void setViewHidden(boolean r1) {
        this.isViewHidden = r1;
        invalidate();
    }

    public void stopPanTransition() {
        this.isPanTransitionInProgress = false;
        invalidate();
    }
}
