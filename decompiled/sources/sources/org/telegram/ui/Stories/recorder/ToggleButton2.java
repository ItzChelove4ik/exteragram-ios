package org.telegram.ui.Stories.recorder;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.recorder.FlashViews;

/* loaded from: classes7.dex */
public class ToggleButton2 extends View implements FlashViews.Invertable {
    private Bitmap activeBitmap;
    private final Paint activeBitmapPaint;
    private final Paint activePaint;
    private final AnimatedFloat animatedSelected;
    private ValueAnimator animator;
    private final Path clipPath;
    private int currentIcon;
    private Drawable drawable;
    private float scale;
    private boolean selected;

    public static /* synthetic */ void $r8$lambda$33XN8h3U98J_07iM51E0bhplbpw(ToggleButton2 r0, AtomicBoolean r1, int r2, ValueAnimator r3) {
        r0.lambda$setIcon$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Yxyi8iUnhrJg17ntbuqGH-nAVN8, reason: not valid java name */
    public static /* synthetic */ void m20592$r8$lambda$Yxyi8iUnhrJg17ntbuqGHnAVN8(ToggleButton2 r0, AtomicBoolean r1, Drawable r2, ValueAnimator r3) {
        r0.lambda$setIcon$1(r1, r2, r3);
    }

    public ToggleButton2(Context r10) {
        super(r10);
        this.clipPath = new Path();
        Paint r102 = new Paint(1);
        this.activePaint = r102;
        Paint r0 = new Paint(3);
        this.activeBitmapPaint = r0;
        this.animatedSelected = new AnimatedFloat(this, 0, 380, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.scale = 1.0f;
        r102.setColor(-1);
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    private /* synthetic */ void lambda$setIcon$0(AtomicBoolean r3, int r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.scale = Math.abs(r52 - 0.5f) + 0.5f;
        if (r52 >= 0.5f) goto L5;
    L7:
        invalidate();
        return;
    L5:
        if (r3.get() == true) goto L7;
        r3.set(true);
        setDrawable(r4);
        goto L7
    }

    private /* synthetic */ void lambda$setIcon$1(AtomicBoolean r3, Drawable r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.scale = Math.abs(r52 - 0.5f) + 0.5f;
        if (r52 >= 0.5f) goto L5;
        return;
    L5:
        if (r3.get() == true) goto L9;
        r3.set(true);
        setDrawable(r4);
        return;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r14) {
        if (this.drawable == null) goto L19;
        float r0 = this.animatedSelected.set(this.selected);
        int r1 = this.drawable.getIntrinsicWidth();
        int r2 = this.drawable.getIntrinsicHeight();
        Rect r3 = AndroidUtilities.rectTmp2;
        r3.set((getWidth() - r1) / 2, (getHeight() - r2) / 2, (getWidth() + r1) / 2, (getHeight() + r2) / 2);
        if (r0 > 0.0f) goto L9;
        this.drawable.setBounds(r3);
        this.drawable.draw(r14);
    L12:
        if (r0 <= 0.0f) goto L18;
        r14.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        r14.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, AndroidUtilities.dp(16.0f) * r0, this.activePaint);
        r14.save();
        Bitmap r142 = this.activeBitmap;
        if (r142 == null) goto L16;
        r14.drawBitmap(r142, null, r3, this.activeBitmapPaint);
    L16:
        r14.restore();
        r14.restore();
        return;
    L18:
        return;
    L9:
        if (r0 >= 1.0f) goto L12;
        r14.save();
        this.clipPath.rewind();
        this.clipPath.addCircle(getWidth() / 2.0f, getHeight() / 2.0f, AndroidUtilities.dp(16.0f) * r0, Path.Direction.CW);
        r14.clipPath(this.clipPath, Region.Op.DIFFERENCE);
        this.drawable.setBounds(r3);
        this.drawable.draw(r14);
        r14.restore();
        goto L12
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.activeBitmap == null) goto L5;
        return;
    L5:
        if (this.currentIcon == 0) goto L9;
        this.activeBitmap = BitmapFactory.decodeResource(getResources(), this.currentIcon);
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap r0 = this.activeBitmap;
        if (r0 == null) goto L6;
        r0.recycle();
        this.activeBitmap = null;
        return;
    }

    public void setDrawable(int r2) {
        this.drawable = getContext().getResources().getDrawable(r2).mutate();
        Bitmap r0 = this.activeBitmap;
        if (r0 == null) goto L6;
        r0.recycle();
        this.activeBitmap = null;
    L6:
        if (this.activeBitmap != null) goto L9;
        if (r2 == 0) goto L9;
        this.activeBitmap = BitmapFactory.decodeResource(getResources(), r2);
    L9:
        invalidate();
    }

    public void setDrawable(Drawable r4) {
        this.drawable = r4;
        Bitmap r0 = this.activeBitmap;
        if (r0 == null) goto L6;
        r0.recycle();
        this.activeBitmap = null;
    L6:
        if (this.activeBitmap != null) goto L13;
        if (r4 == null) goto L13;
        if (r4.getIntrinsicWidth() <= 0) goto L13;
        if (r4.getIntrinsicHeight() <= 0) goto L13;
        Bitmap r02 = Bitmap.createBitmap(r4.getIntrinsicWidth(), r4.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        this.activeBitmap = r02;
        r4.setBounds(0, 0, r02.getWidth(), this.activeBitmap.getHeight());
        r4.draw(new Canvas(this.activeBitmap));
    L13:
        invalidate();
    }

    public void setIcon(int r3, boolean r4) {
        if (this.currentIcon != r3) goto L5;
        return;
    L5:
        this.currentIcon = r3;
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.animator = null;
    L8:
        if (r4 == false) goto L11;
        this.animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        AtomicBoolean r42 = new AtomicBoolean();
        this.animator.addUpdateListener(new ToggleButton2$$ExternalSyntheticLambda1(this, r42, r3));
        this.animator.start();
        return;
    L11:
        this.scale = 1.0f;
        setDrawable(r3);
    }

    public void setIcon(Drawable r3, boolean r4) {
        if (this.drawable != r3) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.animator = null;
    L8:
        if (r4 == false) goto L11;
        this.animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        AtomicBoolean r42 = new AtomicBoolean();
        this.animator.addUpdateListener(new ToggleButton2$$ExternalSyntheticLambda0(this, r42, r3));
        this.animator.start();
        return;
    L11:
        this.scale = 1.0f;
        setDrawable(r3);
    }

    @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
    public void setInvert(float r7) {
        Drawable r0 = this.drawable;
        if (r0 == null) goto L5;
        r0.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-1, -16777216, r7), PorterDuff.Mode.MULTIPLY));
    L5:
        this.activePaint.setColor(ColorUtils.blendARGB(-1, -16777216, r7));
        invalidate();
    }

    @Override // android.view.View
    public void setSelected(boolean r1) {
        this.selected = r1;
        invalidate();
    }

    public void setSelected(boolean r2, boolean r3) {
        this.selected = r2;
        if (r3 == true) goto L9;
        AnimatedFloat r32 = this.animatedSelected;
        if (r2 == false) goto L7;
        float r22 = 1.0f;
    L8:
        r32.set(r22, true);
        goto L9
    L7:
        r22 = 0.0f;
    L9:
        invalidate();
    }
}
