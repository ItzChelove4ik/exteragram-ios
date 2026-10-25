package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimationProperties;

/* loaded from: classes7.dex */
public class ZoomControlView extends View {
    public final Property<ZoomControlView, Float> ZOOM_PROPERTY;
    private float animatingToZoom;
    private AnimatorSet animatorSet;
    private ZoomControlViewDelegate delegate;
    public boolean enabledTouch;
    private Drawable filledProgressDrawable;
    private Drawable knobDrawable;
    private boolean knobPressed;
    private float knobStartX;
    private float knobStartY;
    private int minusCx;
    private int minusCy;
    private Drawable minusDrawable;
    private int plusCx;
    private int plusCy;
    private Drawable plusDrawable;
    private boolean pressed;
    private Drawable pressedKnobDrawable;
    private Drawable progressDrawable;
    private int progressEndX;
    private int progressEndY;
    private int progressStartX;
    private int progressStartY;
    private float zoom;

    /* renamed from: org.telegram.ui.Components.ZoomControlView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<ZoomControlView> {
        final /* synthetic */ ZoomControlView this$0;

        public AnonymousClass1(ZoomControlView r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(ZoomControlView r1) {
            return Float.valueOf(ZoomControlView.m13652$$Nest$fgetzoom(this.this$0));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((ZoomControlView) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(ZoomControlView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ZoomControlView r1, float r2) {
            ZoomControlView.m13654$$Nest$fputzoom(this.this$0, r2);
            if (ZoomControlView.m13651$$Nest$fgetdelegate(this.this$0) == null) goto L5;
            ZoomControlView.m13651$$Nest$fgetdelegate(this.this$0).didSetZoom(ZoomControlView.m13652$$Nest$fgetzoom(this.this$0));
        L5:
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.ZoomControlView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ZoomControlView this$0;

        public AnonymousClass2(ZoomControlView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ZoomControlView.m13653$$Nest$fputanimatorSet(this.this$0, null);
        }
    }

    public interface ZoomControlViewDelegate {
        void didSetZoom(float r1);
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ZoomControlViewDelegate m13651$$Nest$fgetdelegate(ZoomControlView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetzoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13652$$Nest$fgetzoom(ZoomControlView r0) {
        return r0.zoom;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13653$$Nest$fputanimatorSet(ZoomControlView r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputzoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13654$$Nest$fputzoom(ZoomControlView r0, float r1) {
        r0.zoom = r1;
    }

    public ZoomControlView(Context r3) {
        super(r3);
        this.enabledTouch = true;
        this.ZOOM_PROPERTY = new AnonymousClass1(this, "clipProgress");
        this.minusDrawable = r3.getResources().getDrawable(R.drawable.zoom_minus);
        this.plusDrawable = r3.getResources().getDrawable(R.drawable.zoom_plus);
        this.progressDrawable = r3.getResources().getDrawable(R.drawable.zoom_slide);
        this.filledProgressDrawable = r3.getResources().getDrawable(R.drawable.zoom_slide_a);
        this.knobDrawable = r3.getResources().getDrawable(R.drawable.zoom_round);
        this.pressedKnobDrawable = r3.getResources().getDrawable(R.drawable.zoom_round_b);
    }

    private boolean animateToZoom(float r6) {
        if (r6 >= 0.0f) goto L5;
    L12:
        return false;
    L5:
        if (r6 > 1.0f) goto L12;
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L10;
        r0.cancel();
    L10:
        this.animatingToZoom = r6;
        AnimatorSet r02 = new AnimatorSet();
        this.animatorSet = r02;
        r02.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, this.ZOOM_PROPERTY, new float[]{r6})});
        this.animatorSet.setDuration(180);
        this.animatorSet.addListener(new AnonymousClass2(this));
        this.animatorSet.start();
        return true;
    }

    public float getZoom() {
        if (this.animatorSet == null) goto L7;
        return this.animatingToZoom;
    L7:
        return this.zoom;
    }

    public boolean isTouch() {
        if (this.pressed == false) goto L5;
        return true;
    L5:
        if (this.knobPressed == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        int r0 = getMeasuredWidth() / 2;
        int r1 = getMeasuredHeight() / 2;
        if (getMeasuredWidth() <= getMeasuredHeight()) goto L5;
        boolean r2 = true;
    L7:
        if (r2 == false) goto L9;
        this.minusCx = AndroidUtilities.dp(41.0f);
        this.minusCy = r1;
        this.plusCx = getMeasuredWidth() - AndroidUtilities.dp(41.0f);
        this.plusCy = r1;
        this.progressStartX = this.minusCx + AndroidUtilities.dp(18.0f);
        this.progressStartY = r1;
        this.progressEndX = this.plusCx - AndroidUtilities.dp(18.0f);
        this.progressEndY = r1;
    L10:
        this.minusDrawable.setBounds(this.minusCx - AndroidUtilities.dp(7.0f), this.minusCy - AndroidUtilities.dp(7.0f), this.minusCx + AndroidUtilities.dp(7.0f), this.minusCy + AndroidUtilities.dp(7.0f));
        this.minusDrawable.draw(r11);
        this.plusDrawable.setBounds(this.plusCx - AndroidUtilities.dp(7.0f), this.plusCy - AndroidUtilities.dp(7.0f), this.plusCx + AndroidUtilities.dp(7.0f), this.plusCy + AndroidUtilities.dp(7.0f));
        this.plusDrawable.draw(r11);
        int r02 = this.progressEndX;
        int r12 = this.progressStartX;
        int r3 = this.progressEndY;
        int r5 = this.progressStartY;
        float r8 = this.zoom;
        int r03 = (int) (r12 + ((r02 - r12) * r8));
        int r6 = (int) (r5 + ((r3 - r5) * r8));
        Drawable r7 = this.progressDrawable;
        if (r2 == false) goto L13;
        r7.setBounds(r12, r5 - AndroidUtilities.dp(3.0f), this.progressEndX, this.progressStartY + AndroidUtilities.dp(3.0f));
        this.filledProgressDrawable.setBounds(this.progressStartX, this.progressStartY - AndroidUtilities.dp(3.0f), r03, this.progressStartY + AndroidUtilities.dp(3.0f));
    L14:
        this.progressDrawable.draw(r11);
        this.filledProgressDrawable.draw(r11);
        if (r2 == true) goto L18;
        r11.restore();
    L18:
        if (this.knobPressed == false) goto L20;
        Drawable r10 = this.pressedKnobDrawable;
    L21:
        int r13 = r10.getIntrinsicWidth() / 2;
        r10.setBounds(r03 - r13, r6 - r13, r03 + r13, r6 + r13);
        r10.draw(r11);
        return;
    L20:
        r10 = this.knobDrawable;
        goto L21
    L13:
        r7.setBounds(r5, 0, r3, AndroidUtilities.dp(6.0f));
        this.filledProgressDrawable.setBounds(this.progressStartY, 0, r6, AndroidUtilities.dp(6.0f));
        r11.save();
        r11.rotate(90.0f);
        r11.translate(0.0f, (-this.progressStartX) - AndroidUtilities.dp(3.0f));
        goto L14
    L9:
        this.minusCx = r0;
        this.minusCy = AndroidUtilities.dp(41.0f);
        this.plusCx = r0;
        this.plusCy = getMeasuredHeight() - AndroidUtilities.dp(41.0f);
        this.progressStartX = r0;
        this.progressStartY = this.minusCy + AndroidUtilities.dp(18.0f);
        this.progressEndX = r0;
        this.progressEndY = this.plusCy - AndroidUtilities.dp(18.0f);
        goto L10
    L5:
        r2 = false;
        goto L7
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r15) {
        if (this.enabledTouch == true) goto L5;
        return false;
    L5:
        float r0 = r15.getX();
        float r2 = r15.getY();
        int r3 = r15.getAction();
        if (getMeasuredWidth() <= getMeasuredHeight()) goto L8;
        boolean r4 = true;
    L9:
        int r5 = this.progressStartX;
        int r8 = this.progressEndX;
        float r10 = this.zoom;
        int r7 = (int) (r5 + ((r8 - r5) * r10));
        int r9 = this.progressStartY;
        int r12 = this.progressEndY;
        int r102 = (int) (r9 + ((r12 - r9) * r10));
        if (r3 == 1) goto L31;
        if (r3 == 0) goto L31;
        if (r3 == 2) goto L16;
    L95:
        boolean r02 = false;
    L96:
        if (r3 != 1) goto L98;
        this.pressed = false;
        this.knobPressed = false;
        invalidate();
    L98:
        if (r02 == true) goto L106;
        if (this.pressed == true) goto L106;
        if (this.knobPressed == true) goto L106;
        if (super.onTouchEvent(r15) == true) goto L106;
        return false;
    L106:
        return true;
    L16:
        if (this.knobPressed == false) goto L95;
        if (r4 == false) goto L19;
        this.zoom = ((r0 + this.knobStartX) - r5) / (r8 - r5);
    L20:
        float r03 = this.zoom;
        if (r03 >= 0.0f) goto L24;
        this.zoom = 0.0f;
    L26:
        ZoomControlViewDelegate r04 = this.delegate;
        if (r04 == null) goto L29;
        r04.didSetZoom(this.zoom);
    L29:
        invalidate();
        goto L95
    L24:
        if (r03 <= 1.0f) goto L26;
        this.zoom = 1.0f;
        goto L26
    L19:
        this.zoom = ((r2 + this.knobStartY) - r9) / (r12 - r9);
    L31:
        if (r0 < (r7 - AndroidUtilities.dp(20.0f))) goto L109;
        if (r0 > (AndroidUtilities.dp(20.0f) + r7)) goto L109;
        if (r2 < (r102 - AndroidUtilities.dp(25.0f))) goto L109;
        if (r2 > (AndroidUtilities.dp(25.0f) + r102)) goto L109;
        if (r3 != 0) goto L40;
        this.knobPressed = true;
        this.knobStartX = r0 - r7;
        this.knobStartY = r2 - r102;
        invalidate();
    L40:
        r02 = true;
    L109:
        if (r0 < (this.minusCx - AndroidUtilities.dp(16.0f))) goto L56;
        if (r0 > (this.minusCx + AndroidUtilities.dp(16.0f))) goto L56;
        if (r2 < (this.minusCy - AndroidUtilities.dp(16.0f))) goto L56;
        if (r2 > (this.minusCy + AndroidUtilities.dp(16.0f))) goto L56;
        if (r3 == 1) goto L51;
    L54:
        this.pressed = true;
        goto L40
    L51:
        if (animateToZoom((((float) Math.floor(getZoom() / 0.25f)) * 0.25f) - 0.25f) == false) goto L54;
        performHapticFeedback(3);     // Catch: Exception -> L107
    L108:
    L56:
        if (r0 < (this.plusCx - AndroidUtilities.dp(16.0f))) goto L70;
        if (r0 > (this.plusCx + AndroidUtilities.dp(16.0f))) goto L70;
        if (r2 < (this.plusCy - AndroidUtilities.dp(16.0f))) goto L70;
        if (r2 > (this.plusCy + AndroidUtilities.dp(16.0f))) goto L70;
        if (r3 == 1) goto L65;
    L68:
        this.pressed = true;
        goto L40
    L65:
        if (animateToZoom((((float) Math.floor(getZoom() / 0.25f)) * 0.25f) + 0.25f) == false) goto L68;
        performHapticFeedback(3);     // Catch: Exception -> L107
    L70:
        if (r4 == false) goto L84;
        if (r0 < this.progressStartX) goto L95;
        if (r0 > this.progressEndX) goto L95;
        if (r3 != 0) goto L78;
        this.knobStartX = r0;
        this.pressed = true;
        goto L40
    L78:
        if (Math.abs(this.knobStartX - r0) > AndroidUtilities.dp(10.0f)) goto L40;
        float r05 = (r0 - this.progressStartX) / (this.progressEndX - r2);
        this.zoom = r05;
        ZoomControlViewDelegate r22 = this.delegate;
        if (r22 == null) goto L82;
        r22.didSetZoom(r05);
    L82:
        invalidate();
        goto L40
    L84:
        if (r2 < this.progressStartY) goto L95;
        if (r2 > this.progressEndY) goto L95;
        if (r3 != 1) goto L90;
        this.knobStartY = r2;
        this.pressed = true;
        goto L40
    L90:
        if (Math.abs(this.knobStartY - r2) > AndroidUtilities.dp(10.0f)) goto L40;
        float r23 = (r2 - this.progressStartY) / (this.progressEndY - r0);
        this.zoom = r23;
        ZoomControlViewDelegate r06 = this.delegate;
        if (r06 == null) goto L94;
        r06.didSetZoom(r23);
    L94:
        invalidate();
        goto L40
    L8:
        r4 = false;
        goto L9
    }

    public void setDelegate(ZoomControlViewDelegate r1) {
        this.delegate = r1;
    }

    public void setZoom(float r3, boolean r4) {
        if (r3 != this.zoom) goto L5;
        return;
    L5:
        float r0 = 0.0f;
        if (r3 >= 0.0f) goto L8;
    L7:
        r3 = r0;
    L11:
        this.zoom = r3;
        if (r4 == false) goto L16;
        ZoomControlViewDelegate r42 = this.delegate;
        if (r42 == null) goto L16;
        r42.didSetZoom(r3);
    L16:
        invalidate();
        return;
    L8:
        r0 = 1.0f;
        if (r3 <= 1.0f) goto L11;
        goto L11
    }
}
