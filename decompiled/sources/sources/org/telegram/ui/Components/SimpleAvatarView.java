package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SimpleAvatarView extends View {
    private ValueAnimator animator;
    private AvatarDrawable avatarDrawable;
    private ImageReceiver avatarImage;
    private boolean isAvatarHidden;
    private Paint selectPaint;
    private float selectProgress;

    /* renamed from: org.telegram.ui.Components.SimpleAvatarView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SimpleAvatarView this$0;

        public AnonymousClass1(SimpleAvatarView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (SimpleAvatarView.m13008$$Nest$fgetanimator(this.this$0) != r2) goto L6;
            SimpleAvatarView.m13009$$Nest$fputanimator(this.this$0, null);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$wQGocz0U1a6EOemMHG81Tq_njRY(SimpleAvatarView r0, ValueAnimator r1) {
        r0.lambda$setSelected$0(r1);
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m13008$$Nest$fgetanimator(SimpleAvatarView r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13009$$Nest$fputanimator(SimpleAvatarView r0, ValueAnimator r1) {
        r0.animator = r1;
    }

    public SimpleAvatarView(Context r2) {
        super(r2);
        this.avatarImage = new ImageReceiver(this);
        this.avatarDrawable = new AvatarDrawable();
        this.selectPaint = new Paint(1);
        this.avatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f));
        this.selectPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.selectPaint.setStyle(Paint.Style.STROKE);
    }

    private /* synthetic */ void lambda$setSelected$0(ValueAnimator r1) {
        this.selectProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // android.view.View
    public boolean isSelected() {
        if (this.selectProgress != 1.0f) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.avatarImage.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.avatarImage.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        super.onDraw(r10);
        r10.save();
        float r0 = (this.selectProgress * 0.1f) + 0.9f;
        r10.scale(r0, r0);
        this.selectPaint.setColor(Theme.getColor(Theme.key_dialogTextBlue));
        this.selectPaint.setAlpha((int) (Color.alpha(r0.getColor()) * this.selectProgress));
        float r02 = this.selectPaint.getStrokeWidth();
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(r02, r02, getWidth() - r02, getHeight() - r02);
        if (ExteraConfig.getAvatarCorners() >= 28.0f) goto L5;
        float r03 = r02 * 2.0f;
        r10.drawRoundRect(r2, ExteraConfig.getAvatarCorners(getWidth() - r03, true), ExteraConfig.getAvatarCorners(getWidth() - r03, true), this.selectPaint);
        Canvas r1 = r10;
    L6:
        r1.restore();
        if (this.isAvatarHidden == true) goto L10;
        float r102 = (this.selectPaint.getStrokeWidth() * 2.5f) * this.selectProgress;
        float r7 = 2.0f * r102;
        this.avatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(getWidth() - r7, true));
        this.avatarImage.setImageCoords(r102, r102, getWidth() - r7, getHeight() - r7);
        this.avatarImage.draw(r1);
        return;
    L10:
        return;
    L5:
        r1 = r10;
        r1.drawArc(r2, -90.0f, this.selectProgress * 360.0f, false, this.selectPaint);
        goto L6
    }

    public void setAvatar(TLObject r2) {
        this.avatarDrawable.setInfo(r2);
        this.avatarImage.setForUserOrChat(r2, this.avatarDrawable);
    }

    public void setAvatarCorners(int r1) {
        this.avatarImage.setRoundRadius(r1);
    }

    public void setHideAvatar(boolean r1) {
        this.isAvatarHidden = r1;
        invalidate();
    }

    public void setSelected(boolean r3, boolean r4) {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r02 = 0.0f;
        if (r4 == false) goto L11;
        if (r3 == false) goto L9;
        r02 = 1.0f;
    L9:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{this.selectProgress, r02}).setDuration(250);
        r32.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r32.addUpdateListener(new SimpleAvatarView$$ExternalSyntheticLambda0(this));
        r32.addListener(new AnonymousClass1(this));
        r32.start();
        this.animator = r32;
        return;
    L11:
        if (r3 == false) goto L13;
        r02 = 1.0f;
    L13:
        this.selectProgress = r02;
        invalidate();
    }
}
