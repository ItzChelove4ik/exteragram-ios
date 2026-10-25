package org.telegram.ui.Components.Paint.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Paint.PersistColorPalette;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public abstract class PaintColorsListView extends RecyclerListView {
    private static Paint checkerboardPaint;
    private static Paint checkerboardPaintWhite;
    private static Paint colorCirclePaint;
    private static Path colorCirclePath;
    private Consumer<Integer> colorListener;
    private PersistColorPalette colorPalette;
    private Paint outlinePaint;
    private Paint paint;
    private int selectedColorIndex;

    /* renamed from: org.telegram.ui.Components.Paint.Views.PaintColorsListView$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.Adapter {
        final /* synthetic */ PaintColorsListView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass1(PaintColorsListView r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return 14;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            ColorView r42 = (ColorView) r4.itemView;
            r42.getLayoutParams().height = ((this.this$0.getHeight() - this.this$0.getPaddingTop()) - this.this$0.getPaddingBottom()) / 2;
            if (PaintColorsListView.m11549$$Nest$fgetcolorPalette(this.this$0) == null) goto L10;
            r42.setColor(PaintColorsListView.m11549$$Nest$fgetcolorPalette(this.this$0).getColor(r5));
            if (PaintColorsListView.m11552$$Nest$fgetselectedColorIndex(this.this$0) != r5) goto L7;
            boolean r3 = true;
        L8:
            r42.setSelected(r3, false);
            return;
        L7:
            r3 = false;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            return new RecyclerListView.Holder(new ColorView(this.this$0, this.val$context));
        }
    }

    public final class ColorView extends View {
        private int mColor;
        private float selectProgress;
        final /* synthetic */ PaintColorsListView this$0;

        public ColorView(PaintColorsListView r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
            setLayoutParams(new RecyclerView.LayoutParams(-2, 0));
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            PaintColorsListView.m11551$$Nest$fgetpaint(this.this$0).setColor(this.mColor);
            float r0 = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom()) / 2.0f;
            if (this.selectProgress == 0.0f) goto L5;
            r0 = r0 - ((AndroidUtilities.dp(3.0f) + PaintColorsListView.m11550$$Nest$fgetoutlinePaint(this.this$0).getStrokeWidth()) * this.selectProgress);
        L5:
            float r2 = ((getWidth() / 2.0f) + getPaddingLeft()) - getPaddingRight();
            float r4 = ((getHeight() / 2.0f) + getPaddingTop()) - getPaddingBottom();
            PaintColorsListView.drawColorCircle(r7, r2, r4, r0, this.mColor);
            if (this.selectProgress == 0.0f) goto L9;
            PaintColorsListView.m11550$$Nest$fgetoutlinePaint(this.this$0).setColor(this.mColor);
            PaintColorsListView.m11550$$Nest$fgetoutlinePaint(this.this$0).setAlpha(255);
            r7.drawCircle(r2, r4, (Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom()) / 2.0f) - AndroidUtilities.dp(2.0f), PaintColorsListView.m11550$$Nest$fgetoutlinePaint(this.this$0));
            return;
        }

        public void setColor(int r1) {
            this.mColor = r1;
            invalidate();
        }

        public void setSelected(boolean r1, boolean r2) {
            if (r2 == false) goto L4;
            return;
        L4:
            if (r1 == false) goto L6;
            float r12 = 1.0f;
        L7:
            this.selectProgress = r12;
            invalidate();
            return;
        L6:
            r12 = 0.0f;
            goto L7
        }
    }

    /* renamed from: $r8$lambda$zCusoMOv0JQhY_t9dYTzyShdy-I, reason: not valid java name */
    public static /* synthetic */ void m11548$r8$lambda$zCusoMOv0JQhY_t9dYTzyShdyI(PaintColorsListView r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcolorPalette, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PersistColorPalette m11549$$Nest$fgetcolorPalette(PaintColorsListView r0) {
        return r0.colorPalette;
    }

    /* renamed from: -$$Nest$fgetoutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11550$$Nest$fgetoutlinePaint(PaintColorsListView r0) {
        return r0.outlinePaint;
    }

    /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11551$$Nest$fgetpaint(PaintColorsListView r0) {
        return r0.paint;
    }

    /* renamed from: -$$Nest$fgetselectedColorIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11552$$Nest$fgetselectedColorIndex(PaintColorsListView r0) {
        return r0.selectedColorIndex;
    }

    static {
        checkerboardPaint = new Paint(1);
        checkerboardPaintWhite = new Paint(1);
        checkerboardPaint.setColor(-2013265920);
        checkerboardPaintWhite.setColor(-1996488705);
        colorCirclePath = new Path();
        colorCirclePaint = new Paint(1);
    }

    public PaintColorsListView(Context r5) {
        super(r5);
        this.paint = new Paint(1);
        Paint r0 = new Paint(1);
        this.outlinePaint = r0;
        this.selectedColorIndex = -1;
        r0.setStyle(Paint.Style.STROKE);
        this.outlinePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        setLayoutManager(new GridLayoutManager(r5, 7));
        setAdapter(new AnonymousClass1(this, r5));
        setOverScrollMode(2);
        setOnItemClickListener(new PaintColorsListView$$ExternalSyntheticLambda0(this));
    }

    public static void drawCheckerboard(Canvas r10, RectF r11, int r12) {
        float r2 = r11.left;
    L4:
        if (r2 > r11.right) goto L10;
        float r3 = r11.top;
    L7:
        if (r3 > r11.bottom) goto L9;
        float r0 = r12;
        float r4 = r2 + r0;
        float r5 = r3 + r0;
        Canvas r1 = r10;
        r1.drawRect(r2, r3, r4, r5, checkerboardPaint);
        float r102 = r12 * 2;
        float r6 = r2 + r102;
        float r52 = r3;
        r1.drawRect(r4, r52, r6, r5, checkerboardPaintWhite);
        float r8 = r52 + r102;
        r1.drawRect(r4, r5, r6, r8, checkerboardPaint);
        r1.drawRect(r2, r5, r4, r8, checkerboardPaintWhite);
        r10 = r1;
        r3 = r8;
        goto L7
    L9:
        r2 = r2 + (r12 * 2);
        r10 = r10;
        goto L4
    }

    public static void drawColorCircle(Canvas r10, float r11, float r12, float r13, int r14) {
        colorCirclePaint.setColor(r14);
        if (colorCirclePaint.getAlpha() == 255) goto L6;
        RectF r3 = AndroidUtilities.rectTmp;
        r3.set(r11 - r13, r12 - r13, r11 + r13, r12 + r13);
        colorCirclePaint.setAlpha(255);
        r10.drawArc(r3, -45.0f, -180.0f, true, colorCirclePaint);
        colorCirclePath.rewind();
        colorCirclePath.moveTo(r3.centerX(), r3.centerY());
        colorCirclePath.lineTo((float) (r3.centerX() + ((r3.width() / 2.0f) * Math.cos(-1.5707963267948966d))), (float) (r3.centerY() + ((r3.height() / 2.0f) * Math.sin(-1.5707963267948966d))));
        colorCirclePath.moveTo(r3.centerX(), r3.centerY());
        colorCirclePath.lineTo((float) (r3.centerX() + ((r3.width() / 2.0f) * Math.cos(4.71238898038469d))), (float) (r3.centerY() + ((r3.height() / 2.0f) * Math.sin(4.71238898038469d))));
        colorCirclePath.addArc(r3, -45.0f, 180.0f);
        r10.save();
        r10.clipPath(colorCirclePath);
        drawCheckerboard(r10, r3, AndroidUtilities.dp(4.0f));
        r10.restore();
        colorCirclePaint.setColor(r14);
        r10.drawArc(r3, -45.0f, 180.0f, true, colorCirclePaint);
        return;
    L6:
        r10.drawCircle(r11, r12, r13, colorCirclePaint);
    }

    private /* synthetic */ void lambda$new$0(View r2, int r3) {
        this.colorListener.accept(Integer.valueOf(this.colorPalette.getColor(r3)));
        this.colorPalette.setCurrentBrushColorByColorIndex(r3);
    }

    public int getSelectedColorIndex() {
        return this.selectedColorIndex;
    }

    public void setColorListener(Consumer<Integer> r1) {
        this.colorListener = r1;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void setColorPalette(PersistColorPalette r1) {
        this.colorPalette = r1;
        getAdapter().notifyDataSetChanged();
    }

    public void setProgress(float r6, boolean r7) {
        if (r7 == false) goto L4;
        float r62 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r6);
    L5:
        float r72 = 1.0f / (getChildCount() - 1);
        int r1 = 0;
    L7:
        if (r1 >= getChildCount()) goto L17;
        View r2 = getChildAt(r1);
        if (r1 == 0) goto L11;
        float r3 = r1 * r72;
        float r4 = Math.min(r62, r3) / r3;
        r2.setScaleX(r4);
        r2.setScaleY(r4);
    L16:
        r1 = r1 + 1;
        goto L7
    L11:
        if (r62 != 1.0f) goto L13;
        float r32 = 1.0f;
    L14:
        r2.setAlpha(r32);
        goto L16
    L13:
        r32 = 0.0f;
        goto L14
    L17:
        invalidate();
        return;
    L4:
        r62 = CubicBezierInterpolator.EASE_IN.getInterpolation(r6);
        goto L5
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void setSelectedColorIndex(int r1) {
        this.selectedColorIndex = r1;
        getAdapter().notifyDataSetChanged();
    }
}
