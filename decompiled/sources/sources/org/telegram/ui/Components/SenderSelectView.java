package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SenderSelectView extends View {
    private static final FloatPropertyCompat<SenderSelectView> MENU_PROGRESS = null;
    private AvatarDrawable avatarDrawable;
    private ImageReceiver avatarImage;
    private Paint backgroundPaint;
    private ValueAnimator menuAnimator;
    private Paint menuPaint;
    private float menuProgress;
    private SpringAnimation menuSpring;
    private boolean scaleIn;
    private boolean scaleOut;
    private Drawable selectorDrawable;

    /* renamed from: org.telegram.ui.Components.SenderSelectView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SenderSelectView this$0;

        public AnonymousClass1(SenderSelectView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != SenderSelectView.m12638$$Nest$fgetmenuAnimator(this.this$0)) goto L6;
            SenderSelectView.m12639$$Nest$fputmenuAnimator(this.this$0, null);
            return;
        }
    }

    /* renamed from: $r8$lambda$K-0kIe8gi8ce3Pl7QoAyAgwuavA, reason: not valid java name */
    public static /* synthetic */ void m12635$r8$lambda$K0kIe8gi8ce3Pl7QoAyAgwuavA(SenderSelectView r0, float r1) {
        r0.menuProgress = r1;
        r0.invalidate();
    }

    /* renamed from: $r8$lambda$PWdSLJbNnGXVrUJByDRFSoShm-M, reason: not valid java name */
    public static /* synthetic */ float m12636$r8$lambda$PWdSLJbNnGXVrUJByDRFSoShmM(SenderSelectView r0) {
        return r0.menuProgress;
    }

    /* renamed from: $r8$lambda$TAa0Dv9xwsh-B4spXs7S47ZhaEs, reason: not valid java name */
    public static /* synthetic */ void m12637$r8$lambda$TAa0Dv9xwshB4spXs7S47ZhaEs(SenderSelectView r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$setProgress$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$uSX8Keztwrz4qkYHmIqcffUdGHY(SenderSelectView r0, boolean r1, float r2, float r3, DynamicAnimation r4, float r5, float r6) {
        r0.lambda$setProgress$2(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$ywn_DLV5zslnPs5iBwiIhVgq8y0(SenderSelectView r0, ValueAnimator r1) {
        r0.lambda$setProgress$4(r1);
    }

    /* renamed from: -$$Nest$fgetmenuAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m12638$$Nest$fgetmenuAnimator(SenderSelectView r0) {
        return r0.menuAnimator;
    }

    /* renamed from: -$$Nest$fputmenuAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12639$$Nest$fputmenuAnimator(SenderSelectView r0, ValueAnimator r1) {
        r0.menuAnimator = r1;
    }

    static {
        MENU_PROGRESS = new SimpleFloatPropertyCompat("menuProgress", new SenderSelectView$$ExternalSyntheticLambda0(), new SenderSelectView$$ExternalSyntheticLambda1()).setMultiplier(100.0f);
    }

    public SenderSelectView(Context r3) {
        super(r3);
        this.avatarImage = new ImageReceiver(this);
        this.avatarDrawable = new AvatarDrawable();
        this.backgroundPaint = new Paint(1);
        this.menuPaint = new Paint(1);
        this.avatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(36.0f));
        this.menuPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.menuPaint.setStrokeCap(Paint.Cap.ROUND);
        this.menuPaint.setStyle(Paint.Style.STROKE);
        updateColors();
        setContentDescription(LocaleController.formatString("AccDescrSendAsPeer", R.string.AccDescrSendAsPeer, new Object[]{_UrlKt.FRAGMENT_ENCODE_SET}));
    }

    private /* synthetic */ void lambda$setProgress$2(boolean r1, float r2, float r3, DynamicAnimation r4, float r5, float r6) {
        if (r1 == false) goto L10;
        if (r5 <= (r2 / 2.0f)) goto L7;
        return;
    L7:
        if (this.scaleIn == false) goto L16;
    L13:
        this.scaleIn = !r1;
        this.scaleOut = r1;
        return;
    L16:
        return;
    L10:
        if (r5 >= (r3 / 2.0f)) goto L12;
        return;
    L12:
        if (this.scaleOut == true) goto L13;
    }

    private /* synthetic */ void lambda$setProgress$3(DynamicAnimation r1, boolean r2, float r3, float r4) {
        this.scaleIn = false;
        this.scaleOut = false;
        if (r2 == true) goto L6;
        r1.cancel();
    L6:
        if (r1 != this.menuSpring) goto L9;
        this.menuSpring = null;
        return;
    }

    private /* synthetic */ void lambda$setProgress$4(ValueAnimator r1) {
        this.menuProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void updateColors() {
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_chat_messagePanelVoiceBackground));
        this.menuPaint.setColor(Theme.getColor(Theme.key_chat_messagePanelVoicePressed));
        Drawable r0 = Theme.createSimpleSelectorRoundRectDrawable(ExteraConfig.getAvatarCorners(36.0f), 0, Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite), 0.2f));
        this.selectorDrawable = r0;
        r0.setCallback(this);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.selectorDrawable.setState(getDrawableState());
    }

    public float getProgress() {
        return this.menuProgress;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.selectorDrawable.jumpToCurrentState();
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
    public void onDraw(Canvas r11) {
        r11.save();
        float r3 = 1.0f;
        if (this.scaleOut == false) goto L6;
        r3 = 1.0f - this.menuProgress;
    L8:
        r11.scale(r3, r3, getWidth() / 2.0f, getHeight() / 2.0f);
        super.onDraw(r11);
        this.avatarImage.draw(r11);
        int r9 = (int) (this.menuProgress * 255.0f);
        this.backgroundPaint.setAlpha(r9);
        r11.drawRoundRect(0.0f, 0.0f, getWidth(), getHeight(), ExteraConfig.getAvatarCorners(getWidth(), true), ExteraConfig.getAvatarCorners(getWidth(), true), this.backgroundPaint);
        r11.save();
        this.menuPaint.setAlpha(r9);
        float r2 = this.menuPaint.getStrokeWidth() + AndroidUtilities.dp(10.0f);
        r11.drawLine(r2, r2, getWidth() - r2, getHeight() - r2, this.menuPaint);
        r11.drawLine(r2, getHeight() - r2, getWidth() - r2, r2, this.menuPaint);
        r11.restore();
        this.selectorDrawable.setBounds(0, 0, getWidth(), getHeight());
        this.selectorDrawable.draw(r11);
        r11.restore();
        return;
    L6:
        if (this.scaleIn == false) goto L8;
        r3 = this.menuProgress;
        goto L8
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(getLayoutParams().width, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getLayoutParams().height, TLObject.FLAG_30));
        this.avatarImage.setImageCoords(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
    }

    public void setAvatar(TLObject r4) {
        if ((r4 instanceof TLRPC.User) == false) goto L6;
        String r0 = UserObject.getFirstName((TLRPC.User) r4);
    L12:
        setContentDescription(LocaleController.formatString("AccDescrSendAsPeer", R.string.AccDescrSendAsPeer, new Object[]{r0}));
        this.avatarDrawable.setInfo(r4);
        this.avatarImage.setForUserOrChat(r4, this.avatarDrawable);
        return;
    L6:
        if ((r4 instanceof TLRPC.Chat) == false) goto L9;
        r0 = ((TLRPC.Chat) r4).title;
        goto L12
    L9:
        if ((r4 instanceof TLRPC.ChatInvite) == false) goto L11;
        r0 = ((TLRPC.ChatInvite) r4).title;
        goto L12
    L11:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    }

    public void setProgress(float r2) {
        setProgress(r2, true);
    }

    public void setProgress(float r2, boolean r3) {
        if (r2 == 0.0f) goto L5;
        boolean r0 = true;
    L6:
        setProgress(r2, r3, r0);
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setProgress(float r5, boolean r6, boolean r7) {
        if (r6 == false) goto L18;
        SpringAnimation r62 = this.menuSpring;
        if (r62 == null) goto L6;
        r62.cancel();
    L6:
        ValueAnimator r63 = this.menuAnimator;
        if (r63 == null) goto L9;
        r63.cancel();
    L9:
        boolean r64 = false;
        this.scaleIn = false;
        this.scaleOut = false;
        float r0 = this.menuProgress;
        if (r7 == false) goto L16;
        float r02 = r0 * 100.0f;
        SpringAnimation r2 = new SpringAnimation(this, MENU_PROGRESS).setStartValue(r02);
        this.menuSpring = r2;
        if (r5 >= this.menuProgress) goto L14;
        r64 = true;
    L14:
        float r52 = r5 * 100.0f;
        this.scaleIn = r64;
        this.scaleOut = !r64;
        r2.setSpring(new SpringForce(r52).setFinalPosition(r52).setStiffness(450.0f).setDampingRatio(1.0f));
        this.menuSpring.addUpdateListener(new SenderSelectView$$ExternalSyntheticLambda2(this, r64, r02, r52));
        this.menuSpring.addEndListener(new SenderSelectView$$ExternalSyntheticLambda3(this));
        this.menuSpring.start();
        return;
    L16:
        ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r0, r5}).setDuration(200);
        this.menuAnimator = r53;
        r53.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.menuAnimator.addUpdateListener(new SenderSelectView$$ExternalSyntheticLambda4(this));
        this.menuAnimator.addListener(new AnonymousClass1(this));
        this.menuAnimator.start();
        return;
    L18:
        this.menuProgress = r5;
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (this.selectorDrawable == r2) goto L11;
        return false;
    L11:
        return true;
    }
}
