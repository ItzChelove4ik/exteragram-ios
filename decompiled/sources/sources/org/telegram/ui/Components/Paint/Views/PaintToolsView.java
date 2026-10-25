package org.telegram.ui.Components.Paint.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Brush;
import org.telegram.ui.Components.Paint.PersistColorPalette;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes7.dex */
public class PaintToolsView extends LinearLayout {
    private final int brushesCount;
    private RLottieImageView[] buttons;
    private Delegate delegate;
    private boolean isShapeSelected;
    private ValueAnimator nextSelectedAnimator;
    private int nextSelectedIndex;
    private float nextSelectedIndexProgress;
    private int selectedIndex;
    private Paint selectorPaint;

    /* renamed from: org.telegram.ui.Components.Paint.Views.PaintToolsView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ PaintToolsView this$0;

        public AnonymousClass1(PaintToolsView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != PaintToolsView.m11561$$Nest$fgetnextSelectedAnimator(this.this$0)) goto L6;
            PaintToolsView r22 = this.this$0;
            PaintToolsView.m11565$$Nest$fputselectedIndex(r22, PaintToolsView.m11562$$Nest$fgetnextSelectedIndex(r22));
            PaintToolsView.m11564$$Nest$fputnextSelectedIndex(this.this$0, -1);
            PaintToolsView.m11563$$Nest$fputnextSelectedAnimator(this.this$0, null);
            return;
        }
    }

    public interface Delegate {
        void onAddButtonPressed(View r1);

        void onBrushSelected(Brush r1);

        void onColorPickerSelected();

        PersistColorPalette onGetPalette();
    }

    /* renamed from: $r8$lambda$Vg2AMNqBbHNw3qNl2GmhO-UUuXQ, reason: not valid java name */
    public static /* synthetic */ void m11559$r8$lambda$Vg2AMNqBbHNw3qNl2GmhOUUuXQ(PaintToolsView r0, int r1, Brush r2, View r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$g6jZoAJEGMrkPaMFVQ9tJL28vE8(PaintToolsView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$iLGAF8E5agLZgYHdxyJtmXgPLuQ(View r0) {
        r0.performClick();
    }

    /* renamed from: $r8$lambda$ot3ifK9JLklAj9RQJs4B-PmqZo4, reason: not valid java name */
    public static /* synthetic */ void m11560$r8$lambda$ot3ifK9JLklAj9RQJs4BPmqZo4(PaintToolsView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$xjO9kUKzJqdkaKCagw92nqgKmiQ(PaintToolsView r0, ValueAnimator r1) {
        r0.lambda$animateNextIndex$3(r1);
    }

    /* renamed from: -$$Nest$fgetnextSelectedAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11561$$Nest$fgetnextSelectedAnimator(PaintToolsView r0) {
        return r0.nextSelectedAnimator;
    }

    /* renamed from: -$$Nest$fgetnextSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11562$$Nest$fgetnextSelectedIndex(PaintToolsView r0) {
        return r0.nextSelectedIndex;
    }

    /* renamed from: -$$Nest$fputnextSelectedAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11563$$Nest$fputnextSelectedAnimator(PaintToolsView r0, ValueAnimator r1) {
        r0.nextSelectedAnimator = r1;
    }

    /* renamed from: -$$Nest$fputnextSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11564$$Nest$fputnextSelectedIndex(PaintToolsView r0, int r1) {
        r0.nextSelectedIndex = r1;
    }

    /* renamed from: -$$Nest$fputselectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11565$$Nest$fputselectedIndex(PaintToolsView r0, int r1) {
        r0.selectedIndex = r1;
    }

    public PaintToolsView(Context r8, boolean r9) {
        super(r8);
        this.buttons = new RLottieImageView[Brush.BRUSHES_LIST.size() + 2];
        this.selectorPaint = new Paint(1);
        this.selectedIndex = 1;
        this.nextSelectedIndex = -1;
        this.nextSelectedIndexProgress = 0.0f;
        setOrientation(0);
        setGravity(16);
        setWillNotDraw(false);
        setClipToPadding(false);
        this.selectorPaint.setColor(822083583);
        this.brushesCount = Brush.BRUSHES_LIST.size() - (!r9 ? 1 : 0);
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= (Brush.BRUSHES_LIST.size() + 2)) goto L30;
        RLottieImageView[] r3 = this.buttons;
        if (r1 != 0) goto L8;
        boolean r4 = true;
    L10:
        if (r1 != (Brush.BRUSHES_LIST.size() + 1)) goto L12;
        boolean r5 = true;
    L13:
        r3[r2] = createView(r4, r5);
        if (r1 != 0) goto L16;
        this.buttons[r2].setOnClickListener(new PaintToolsView$$ExternalSyntheticLambda1(this));
    L28:
        addView(this.buttons[r2]);
        r2 = r2 + 1;
    L29:
        r1 = r1 + 1;
        goto L4
    L16:
        if (r1 <= 0) goto L26;
        if (r1 > Brush.BRUSHES_LIST.size()) goto L26;
        Brush r32 = Brush.BRUSHES_LIST.get(r1 - 1);
        if (r9 == false) goto L22;
    L24:
        this.buttons[r2].setAnimation(r32.getIconRes(), 28, 28);
        this.buttons[r2].setOnClickListener(new PaintToolsView$$ExternalSyntheticLambda2(this, r2, r32));
        goto L28
    L22:
        if ((r32 instanceof Brush.Blurer) == false) goto L24;
    L26:
        if (r1 != (Brush.BRUSHES_LIST.size() + 1)) goto L28;
        this.buttons[r2].setImageResource(R.drawable.msg_add);
        this.buttons[r2].setOnClickListener(new PaintToolsView$$ExternalSyntheticLambda3(this));
        goto L28
    L12:
        r5 = false;
        goto L13
    L8:
        r4 = false;
        goto L10
    }

    private void animateNextIndex(int r4) {
        if (r4 < 0) goto L27;
        RLottieImageView[] r0 = this.buttons;
        if (r4 < r0.length) goto L7;
        return;
    L7:
        if (this.nextSelectedAnimator != null) goto L9;
    L11:
        RLottieImageView r02 = r0[r4];
        if (r02 == null) goto L16;
        Drawable r03 = r02.getDrawable();
        if ((r03 instanceof RLottieDrawable) == false) goto L16;
        RLottieDrawable r04 = (RLottieDrawable) r03;
        r04.setCurrentFrame(0);
        r04.start();
    L16:
        ValueAnimator r05 = this.nextSelectedAnimator;
        if (r05 == null) goto L20;
        r05.cancel();
    L20:
        if (this.selectedIndex != r4) goto L23;
        return;
    L23:
        if (this.isShapeSelected == false) goto L25;
        this.isShapeSelected = false;
        AndroidUtilities.updateImageViewImageAnimated(this.buttons[this.brushesCount + 1], R.drawable.msg_add);
    L25:
        this.nextSelectedIndex = r4;
        this.nextSelectedIndexProgress = 0.0f;
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(250);
        this.nextSelectedAnimator = r42;
        r42.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.nextSelectedAnimator.addUpdateListener(new PaintToolsView$$ExternalSyntheticLambda4(this));
        this.nextSelectedAnimator.addListener(new AnonymousClass1(this));
        this.nextSelectedAnimator.start();
        return;
    L9:
        if (this.nextSelectedIndex != r4) goto L11;
        return;
    }

    private RLottieImageView createView(boolean r4, boolean r5) {
        RLottieImageView r0 = new RLottieImageView(getContext());
        float r3 = 0.0f;
        if (r4 == false) goto L5;
        float r42 = 0.0f;
    L6:
        int r43 = AndroidUtilities.dp(r42);
        int r2 = AndroidUtilities.dp(8.0f);
        if (r5 == true) goto L10;
        r3 = 8.0f;
    L10:
        r0.setPadding(r43, r2, AndroidUtilities.dp(r3), AndroidUtilities.dp(8.0f));
        r0.setLayoutParams(LayoutHelper.createLinear(0, 40, 1.0f));
        r0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        return r0;
    L5:
        r42 = 8.0f;
        goto L6
    }

    private float getOffsetForIndex(int r1) {
        if (r1 == (this.brushesCount + 1)) goto L5;
        return 0.0f;
    L5:
        return AndroidUtilities.dp(4.0f);
    }

    private /* synthetic */ void lambda$animateNextIndex$3(ValueAnimator r1) {
        this.nextSelectedIndexProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        this.delegate.onColorPickerSelected();
    }

    private /* synthetic */ void lambda$new$1(int r1, Brush r2, View r3) {
        animateNextIndex(r1);
        this.delegate.onGetPalette().setCurrentBrush(r1 - 1);
        this.delegate.onBrushSelected(r2);
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        this.delegate.onAddButtonPressed(r1);
    }

    public void animatePlusToIcon(int r4) {
        animateNextIndex(this.brushesCount + 1);
        AndroidUtilities.updateImageViewImageAnimated(this.buttons[this.brushesCount + 1], r4);
        this.isShapeSelected = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r6) {
        if (r6.getAction() != 0) goto L5;
    L8:
        float r0 = r6.getX();
        r6.getY();
        int r2 = 1;
    L10:
        if (r2 >= (getChildCount() - 1)) goto L26;
        View r3 = getChildAt(r2);
        if (r0 < r3.getLeft()) goto L24;
        if (r0 > r3.getRight()) goto L24;
        if (this.nextSelectedAnimator == null) goto L21;
        if (this.nextSelectedIndex == r2) goto L24;
    L22:
        animateNextIndex(r2);
        post(new PaintToolsView$$ExternalSyntheticLambda0(r3));
        return true;
    L21:
        if (this.selectedIndex != r2) goto L22;
    L24:
        r2 = r2 + 1;
    L26:
        return super.dispatchTouchEvent(r6);
    L5:
        if (r6.getAction() == 2) goto L8;
        if (r6.getAction() != 1) goto L26;
        goto L8
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r11) {
        super.onDraw(r11);
        RLottieImageView[] r0 = this.buttons;
        RLottieImageView r1 = r0[this.selectedIndex];
        int r2 = this.nextSelectedIndex;
        if (r2 == (-1)) goto L5;
        RLottieImageView r02 = r0[r2];
    L6:
        float r22 = 0.0f;
        if (r02 == null) goto L9;
        float r4 = this.nextSelectedIndexProgress;
    L10:
        float r7 = 1.0f;
        if (r4 > 0.25f) goto L14;
    L22:
        float r5 = ((Math.min((r1.getWidth() - r1.getPaddingLeft()) - r1.getPaddingRight(), (r1.getHeight() - r1.getPaddingTop()) - r1.getPaddingBottom()) / 2.0f) + AndroidUtilities.dp(3.0f)) + (AndroidUtilities.dp(3.0f) * r7);
        float r72 = (r1.getX() + (r1.getWidth() / 2.0f)) + getOffsetForIndex(this.selectedIndex);
        if (r02 == null) goto L25;
        float r8 = r02.getX() + (r02.getWidth() / 2.0f);
    L26:
        int r03 = this.nextSelectedIndex;
        if (r03 == (-1)) goto L29;
        r22 = getOffsetForIndex(r03);
    L29:
        r11.drawCircle(AndroidUtilities.lerp(r72, r8 + r22, r4), r1.getY() + (r1.getHeight() / 2.0f), r5, this.selectorPaint);
        return;
    L25:
        r8 = 0.0f;
        goto L26
    L14:
        if (r4 >= 0.75f) goto L22;
        if (r4 > 0.25f) goto L19;
    L21:
        r7 = 1.0f - ((0.75f - r4) / 0.25f);
        goto L22
    L19:
        if (r4 >= 0.5f) goto L21;
        r7 = (0.5f - r4) / 0.25f;
        goto L22
    L9:
        r4 = 0.0f;
        goto L10
    L5:
        r02 = null;
        goto L6
    }

    public void select(int r1) {
        animateNextIndex(r1);
        this.delegate.onGetPalette().setCurrentBrush(r1 - 1);
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setSelectedIndex(int r2) {
        this.selectedIndex = r2;
        if (this.isShapeSelected == false) goto L5;
        this.isShapeSelected = false;
        AndroidUtilities.updateImageViewImageAnimated(this.buttons[this.brushesCount + 1], R.drawable.msg_add);
    L5:
        invalidate();
    }
}
