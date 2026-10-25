package org.telegram.ui.Components.chat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.inset.InAppKeyboardInsetView;
import org.telegram.ui.Components.inset.WindowInsetsProvider;

/* loaded from: classes3.dex */
public class ChatInputViewsContainer extends FrameLayout {
    private BlurredBackgroundWithFadeDrawable backgroundWithFadeDrawable;
    public BlurredBackgroundDrawable blurredBackgroundDrawable;
    private float blurredBottomHeight;
    private float bubbleInputTranlationY;
    private boolean captured;
    private int currentBlurredHeight;
    public boolean drawInputBackground;
    private final View fadeView;
    private float imeBottomInset;
    private final FrameLayout inAppKeyboardBubbleContainer;
    private float inputBubbleHeight;
    private int inputBubbleHeightRound;
    private float inputBubbleOffsetLeft;
    private float inputBubbleOffsetRight;
    private final FrameLayout inputIslandBubbleContainer;
    private float maxBottomInset;
    private boolean needDrawInAppKeyboard;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private BlurredBackgroundDrawable underKeyboardBackgroundDrawable;
    private final Path underKeyboardPath;
    private WindowInsetsProvider windowInsetsProvider;

    /* renamed from: org.telegram.ui.Components.chat.ChatInputViewsContainer$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ChatInputViewsContainer this$0;

        public AnonymousClass1(ChatInputViewsContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public void addView(View r1, int r2, int r3) {
            super.addView(r1, r2, r3);
            ChatInputViewsContainer.m13692$$Nest$mcheckViewsPositions(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.chat.ChatInputViewsContainer$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends View {
        final /* synthetic */ ChatInputViewsContainer this$0;

        public AnonymousClass2(ChatInputViewsContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r2) {
            if (ChatInputViewsContainer.m13691$$Nest$fgetbackgroundWithFadeDrawable(this.this$0) == null) goto L5;
            ChatInputViewsContainer.m13691$$Nest$fgetbackgroundWithFadeDrawable(this.this$0).draw(r2);
        L5:
            super.dispatchDraw(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$oGCgBzltdcMfxudZrCeiJqcrmqY(ChatInputViewsContainer r0) {
        r0.lambda$checkInsets$0();
    }

    /* renamed from: -$$Nest$fgetbackgroundWithFadeDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundWithFadeDrawable m13691$$Nest$fgetbackgroundWithFadeDrawable(ChatInputViewsContainer r0) {
        return r0.backgroundWithFadeDrawable;
    }

    /* renamed from: -$$Nest$mcheckViewsPositions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13692$$Nest$mcheckViewsPositions(ChatInputViewsContainer r0) {
        r0.checkViewsPositions();
    }

    public ChatInputViewsContainer(Context r6) {
        super(r6);
        this.drawInputBackground = true;
        this.underKeyboardPath = new Path();
        this.tmpRect = new Rect();
        this.tmpRectF = new RectF();
        FrameLayout r0 = new FrameLayout(r6);
        this.inputIslandBubbleContainer = r0;
        addView(r0, LayoutHelper.createFrame(-1, -2, 80));
        AnonymousClass1 r02 = new AnonymousClass1(this, r6);
        this.inAppKeyboardBubbleContainer = r02;
        addView(r02, LayoutHelper.createFrame(-1, -2, 80));
        this.fadeView = new AnonymousClass2(this, r6);
    }

    private void checkBlurredHeight(boolean r6) {
        checkViewsPositions();
        int r0 = (this.inputBubbleHeightRound + AndroidUtilities.dp(9.0f)) + Math.round(this.maxBottomInset);
        if (this.currentBlurredHeight != r0) goto L7;
        if (r6 == true) goto L7;
        return;
    L7:
        this.currentBlurredHeight = r0;
        int r62 = AndroidUtilities.dp(20.0f);
        this.tmpRectF.set(0.0f, getMeasuredHeight() - this.imeBottomInset, getMeasuredWidth(), getMeasuredHeight());
        this.underKeyboardPath.rewind();
        float r63 = r62;
        this.underKeyboardPath.addRoundRect(this.tmpRectF, new float[]{r63, r63, r63, r63, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CW);
        this.underKeyboardPath.close();
        invalidate();
    }

    private void checkDrawableBounds() {
        BlurredBackgroundWithFadeDrawable r0 = this.backgroundWithFadeDrawable;
        if (r0 == null) goto L10;
        int r02 = r0.getBounds().top;
        int r1 = getMeasuredHeight() - Math.round(this.blurredBottomHeight);
        if (r02 == r1) goto L9;
        this.backgroundWithFadeDrawable.setBounds(0, r1, getMeasuredWidth(), getMeasuredHeight());
        this.fadeView.invalidate(0, Math.max(0, Math.min(r02, r1)), getMeasuredWidth(), getMeasuredHeight());
        invalidate(0, Math.max(0, Math.min(r02, r1)), getMeasuredWidth(), getMeasuredHeight());
        return;
    L9:
        return;
    }

    private void checkInAppKeyboardChild() {
        int r0 = this.windowInsetsProvider.getCurrentNavigationBarInset();
        float r1 = this.windowInsetsProvider.getAnimatedImeBottomInset();
        int r2 = this.inAppKeyboardBubbleContainer.getChildCount();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L8;
        KeyEvent.Callback r4 = this.inAppKeyboardBubbleContainer.getChildAt(r3);
        if ((r4 instanceof InAppKeyboardInsetView) == false) goto L7;
        InAppKeyboardInsetView r42 = (InAppKeyboardInsetView) r4;
        r42.applyNavigationBarHeight(r0);
        r42.applyInAppKeyboardAnimatedHeight(r1);
    L7:
        r3 = r3 + 1;
        goto L3
    }

    private void checkInAppKeyboardViewHeight() {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.inAppKeyboardBubbleContainer.getLayoutParams();
        int r1 = r0.height;
        int r2 = this.windowInsetsProvider.getInAppKeyboardRecommendedViewHeight();
        if (r1 == r2) goto L6;
        r0.height = r2;
        requestLayout();
        return;
    }

    private void checkViewsPositions() {
        this.inputIslandBubbleContainer.setTranslationY((-this.maxBottomInset) - AndroidUtilities.dp(9.0f));
        this.inAppKeyboardBubbleContainer.setTranslationY(r0.getMeasuredHeight() - this.imeBottomInset);
    }

    private /* synthetic */ void lambda$checkInsets$0() {
        if (isAttachedToWindow() == false) goto L6;
        requestLayout();
        return;
    }

    public void checkInsets() {
        this.maxBottomInset = this.windowInsetsProvider.getAnimatedMaxBottomInset();
        this.imeBottomInset = this.windowInsetsProvider.getAnimatedImeBottomInset();
        this.needDrawInAppKeyboard = this.windowInsetsProvider.inAppViewIsVisible();
        int r1 = 0;
        if (this.inAppKeyboardBubbleContainer.getVisibility() != 0) goto L5;
        boolean r0 = true;
    L6:
        boolean r2 = this.needDrawInAppKeyboard;
        if (r0 == r2) goto L17;
        FrameLayout r02 = this.inAppKeyboardBubbleContainer;
        if (r2 == false) goto L11;
        int r22 = 0;
    L12:
        r02.setVisibility(r22);
        if (this.needDrawInAppKeyboard == false) goto L17;
        if (AndroidUtilities.isInMultiwindow == false) goto L17;
        post(new ChatInputViewsContainer$$ExternalSyntheticLambda0(this));
        goto L17
    L11:
        r22 = 8;
    L17:
        checkInAppKeyboardViewHeight();
        checkBlurredHeight(false);
        checkInAppKeyboardChild();
        if (this.underKeyboardBackgroundDrawable != null) goto L20;
        return;
    L20:
        if (Build.VERSION.SDK_INT < 31) goto L30;
        WindowInsets r03 = getRootWindowInsets();
        if (r03 == null) goto L30;
        RoundedCorner r23 = r03.getRoundedCorner(3);
        RoundedCorner r04 = r03.getRoundedCorner(2);
        if (r23 != null) goto L26;
        int r24 = 0;
    L27:
        if (r04 == null) goto L31;
        r1 = r04.getRadius();
    L31:
        this.underKeyboardBackgroundDrawable.setRadius(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), r1, r24, true);
        return;
    L26:
        r24 = r23.getRadius();
    L30:
        r24 = 0;
        goto L31
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r7) {
        this.underKeyboardBackgroundDrawable.setBounds(0, getMeasuredHeight() - ((int) this.imeBottomInset), getMeasuredWidth(), Math.max(getMeasuredHeight(), (getMeasuredHeight() - ((int) this.imeBottomInset)) + AndroidUtilities.dp(40.0f)));
        int r0 = getMeasuredHeight() - this.currentBlurredHeight;
        this.tmpRect.set(Math.round(this.inputBubbleOffsetLeft), 0, getMeasuredWidth() - Math.round(this.inputBubbleOffsetRight), this.inputBubbleHeightRound);
        this.tmpRect.inset(0, -AndroidUtilities.dp(7.0f));
        this.tmpRect.offset(0, r0 + ((int) this.bubbleInputTranlationY));
        this.blurredBackgroundDrawable.setBounds(this.tmpRect);
        if (this.drawInputBackground == false) goto L6;
        this.blurredBackgroundDrawable.draw(r7);
    L6:
        if (this.needDrawInAppKeyboard == false) goto L8;
        this.underKeyboardBackgroundDrawable.draw(r7);
    L8:
        super.dispatchDraw(r7);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r3, View r4, long r5) {
        if (r4 != this.inAppKeyboardBubbleContainer) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        r3.save();
        r3.clipPath(this.underKeyboardBackgroundDrawable.getPath());
    L8:
        boolean r2 = super.drawChild(r3, r4, r5);
        if (r0 == false) goto L11;
        r3.restore();
    L11:
        return r2;
    L5:
        r0 = false;
        goto L6
    }

    public View getFadeView() {
        return this.fadeView;
    }

    public FrameLayout getInAppKeyboardBubbleContainer() {
        return this.inAppKeyboardBubbleContainer;
    }

    public float getInputBubbleBottom() {
        return (getMeasuredHeight() - this.maxBottomInset) - AndroidUtilities.dp(9.0f);
    }

    public float getInputBubbleHeight() {
        return this.inputBubbleHeight;
    }

    public float getInputBubbleTop() {
        return getInputBubbleBottom() - getInputBubbleHeight();
    }

    public FrameLayout getInputIslandBubbleContainer() {
        return this.inputIslandBubbleContainer;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkViewsPositions();
        checkInAppKeyboardChild();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        checkBlurredHeight(true);
        checkDrawableBounds();
        checkViewsPositions();
        checkInAppKeyboardChild();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        int r0 = r7.getAction();
        if (r0 != 0) goto L17;
        int r3 = (int) r7.getX();
        int r72 = (int) r7.getY();
        BlurredBackgroundDrawable r4 = this.blurredBackgroundDrawable;
        if (r4 != null) goto L7;
    L10:
        BlurredBackgroundDrawable r42 = this.underKeyboardBackgroundDrawable;
        if (r42 != null) goto L13;
    L15:
        boolean r73 = false;
    L16:
        this.captured = r73;
        goto L17
    L13:
        if (r42.getBounds().contains(r3, r72) == false) goto L15;
    L14:
        r73 = true;
        goto L16
    L7:
        if (r4.getAlpha() != 255) goto L10;
        if (this.blurredBackgroundDrawable.getBounds().contains(r3, r72) == true) goto L14;
    L17:
        if (r0 != 1) goto L19;
    L20:
        this.captured = false;
    L22:
        return this.captured;
    L19:
        if (r0 != 3) goto L22;
        goto L20
    }

    public void setBackgroundWithFadeDrawable(BlurredBackgroundWithFadeDrawable r1) {
        this.backgroundWithFadeDrawable = r1;
    }

    public void setBlurredBottomHeight(float r2) {
        if (this.blurredBottomHeight == r2) goto L6;
        this.blurredBottomHeight = r2;
        checkDrawableBounds();
        return;
    }

    public void setInputBubbleAlpha(int r1) {
        BlurredBackgroundDrawable r0 = this.blurredBackgroundDrawable;
        if (r0 == null) goto L6;
        r0.setAlpha(r1);
        return;
    }

    public void setInputBubbleHeight(float r1) {
        this.inputBubbleHeight = r1;
        this.inputBubbleHeightRound = Math.round(r1);
        checkBlurredHeight(false);
    }

    public void setInputBubbleOffsets(float r1, float r2) {
        this.inputBubbleOffsetLeft = r1;
        this.inputBubbleOffsetRight = r2;
        invalidate();
    }

    public void setInputBubbleTranslationY(float r1) {
        this.bubbleInputTranlationY = r1;
        invalidate();
    }

    public void setInputIslandBubbleDrawable(BlurredBackgroundDrawable r2) {
        this.blurredBackgroundDrawable = r2;
        r2.setPadding(AndroidUtilities.dp(7.0f));
        this.blurredBackgroundDrawable.setRadius(AndroidUtilities.dp(24.0f));
    }

    public void setUnderKeyboardBackgroundDrawable(BlurredBackgroundDrawable r4) {
        this.underKeyboardBackgroundDrawable = r4;
        r4.enableInAppKeyboardOptimization();
        this.underKeyboardBackgroundDrawable.setRadius(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), 0.0f, 0.0f);
        this.underKeyboardBackgroundDrawable.setThickness(AndroidUtilities.dp(32.0f));
        this.underKeyboardBackgroundDrawable.setIntensity(0.4f);
    }

    public void setWindowInsetsProvider(WindowInsetsProvider r1) {
        this.windowInsetsProvider = r1;
    }

    public void updateColors() {
        this.blurredBackgroundDrawable.updateColors();
        this.underKeyboardBackgroundDrawable.updateColors();
        invalidate();
    }
}
