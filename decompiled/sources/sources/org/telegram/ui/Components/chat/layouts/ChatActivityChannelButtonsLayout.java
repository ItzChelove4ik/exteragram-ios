package org.telegram.ui.Components.chat.layouts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.HashSet;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.chat.buttons.ChatActivityBlurredRoundButton;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatActivityChannelButtonsLayout extends FrameLayout implements FactorAnimator.Target {
    private static final int[] buttonIcons = null;
    private static final int[] buttonsOrderLeft = null;
    private static final int[] buttonsOrderRight = null;
    private static final RectF tmpRect = null;
    private int accentColor;
    private final BoolAnimator animatorCenterAccentBackground;
    private final BoolAnimator animatorWrappingButton;
    private final Paint backgroundAccentPaint;
    private final BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableViewFactory;
    private final ButtonHolder[] buttonHolders;
    private final BlurredBackgroundColorProvider colorProvider;
    private final FrameLayout container;
    private BlurredBackgroundDrawable containerDrawable;
    private final OnButtonFullyVisibleListener[] onButtonFullyVisible;
    private OnButtonsTotalWidthChanged onButtonsTotalWidthChanged;
    private final View.OnClickListener[] onClickListeners;
    private final Theme.ResourcesProvider resourcesProvider;
    private float totalVisibilityFactor;
    private float totalWidthLeft;
    private float totalWidthRight;
    private final HashSet<View> wrapContentButtons;

    public static class ButtonHolder {
        public final ChatActivityBlurredRoundButton button;
        public final BoolAnimator visibilityAnimator;
        public boolean wasShown;

        private ButtonHolder(ChatActivityBlurredRoundButton r1, BoolAnimator r2) {
            this.button = r1;
            this.visibilityAnimator = r2;
        }

        public /* synthetic */ ButtonHolder(ChatActivityBlurredRoundButton r1, BoolAnimator r2, ChatActivityChannelButtonsLayoutIA r3) {
            this(r1, r2);
        }
    }

    public interface OnButtonFullyVisibleListener {
        void onButtonFullyVisible(View r1, int r2, boolean r3);
    }

    public interface OnButtonsTotalWidthChanged {
        void onButtonsTotalWidthChanged(float r1, float r2);
    }

    public static /* synthetic */ void $r8$lambda$R7l9GSFMR0N3pbVNbgxasrX3pGI(ChatActivityChannelButtonsLayout r0, int r1, View r2) {
        r0.lambda$showButton$0(r1, r2);
    }

    static {
        int r0 = R.drawable.msg_search;
        int r1 = R.drawable.input_gift_s;
        int r2 = R.drawable.input_message;
        int r3 = R.drawable.msg_help;
        buttonIcons = new int[]{r0, r1, r2, r3, r3};
        buttonsOrderLeft = new int[]{0};
        buttonsOrderRight = new int[]{1, 2, 3, 4};
        tmpRect = new RectF();
    }

    public ChatActivityChannelButtonsLayout(Context r9, Theme.ResourcesProvider r10, BlurredBackgroundColorProvider r11, BlurredBackgroundDrawableViewFactory r12) {
        super(r9);
        this.buttonHolders = new ButtonHolder[5];
        this.onClickListeners = new View.OnClickListener[5];
        this.onButtonFullyVisible = new OnButtonFullyVisibleListener[5];
        this.wrapContentButtons = new HashSet();
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorCenterAccentBackground = new BoolAnimator(99, this, r4, 320);
        this.animatorWrappingButton = new BoolAnimator(100, this, r4, 320);
        this.backgroundAccentPaint = new Paint(1);
        this.accentColor = 0;
        this.blurredBackgroundDrawableViewFactory = r12;
        this.colorProvider = r11;
        this.resourcesProvider = r10;
        FrameLayout r102 = new FrameLayout(r9);
        this.container = r102;
        r102.setClipToOutline(true);
        r102.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(24.0f)));
        addView(r102, LayoutHelper.createFrame(-1, 48, 16));
    }

    private void checkButtonsPositionsAndVisibility() {
        float r0 = 0.0f;
        this.totalWidthLeft = 0.0f;
        this.totalWidthRight = 0.0f;
        ButtonHolder[] r1 = this.buttonHolders;
        int r2 = r1.length;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r4 >= r2) goto L14;
        ButtonHolder r6 = r1[r4];
        if (r6 == null) goto L13;
        float r7 = r6.visibilityAnimator.getFloatValue() * this.totalVisibilityFactor;
        ChatActivityBlurredRoundButton r8 = r6.button;
        if (r7 <= 0.0f) goto L11;
        int r9 = 0;
    L12:
        r8.setVisibility(r9);
        r6.button.setAlpha(r7);
        r6.button.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r7));
        r6.button.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r7));
        goto L13
    L11:
        r9 = 8;
    L13:
        r4 = r4 + 1;
        goto L4
    L14:
        int r12 = AndroidUtilities.dp(3.0f);
        int[] r22 = buttonsOrderLeft;
        int r42 = r22.length;
        int r62 = 0;
    L16:
        if (r62 >= r42) goto L22;
        ButtonHolder r82 = this.buttonHolders[r22[r62]];
        if (r82 == null) goto L21;
        float r92 = r82.visibilityAnimator.getFloatValue() * AndroidUtilities.dp(58.0f);
        r82.button.setTranslationX(r12 + this.totalWidthLeft);
        this.totalWidthLeft += r92;
    L21:
        r62 = r62 + 1;
        goto L16
    L22:
        int[] r23 = buttonsOrderRight;
        int r43 = r23.length;
        int r63 = 0;
    L23:
        if (r63 >= r43) goto L30;
        ButtonHolder r83 = this.buttonHolders[r23[r63]];
        if (r83 == null) goto L28;
        float r93 = r83.visibilityAnimator.getFloatValue() * AndroidUtilities.dp(58.0f);
        r83.button.setTranslationX(((getMeasuredWidth() - r83.button.getMeasuredWidth()) - r12) - this.totalWidthRight);
        this.totalWidthRight += r93;
    L28:
        r63 = r63 + 1;
        goto L23
    L30:
        if (this.totalVisibilityFactor >= 1.0f) goto L46;
        int[] r13 = buttonsOrderLeft;
        int r24 = r13.length;
        int r44 = 0;
    L32:
        if (r44 >= r24) goto L38;
        ButtonHolder r64 = this.buttonHolders[r13[r44]];
        if (r64 == null) goto L37;
        ChatActivityBlurredRoundButton r65 = r64.button;
        r65.setTranslationX(r65.getTranslationX() - (this.totalWidthLeft * (1.0f - this.totalVisibilityFactor)));
    L37:
        r44 = r44 + 1;
        goto L32
    L38:
        int[] r14 = buttonsOrderRight;
        int r25 = r14.length;
        int r45 = 0;
    L39:
        if (r45 >= r25) goto L45;
        ButtonHolder r66 = this.buttonHolders[r14[r45]];
        if (r66 == null) goto L44;
        ChatActivityBlurredRoundButton r67 = r66.button;
        r67.setTranslationX(r67.getTranslationX() + (this.totalWidthRight * (1.0f - this.totalVisibilityFactor)));
    L44:
        r45 = r45 + 1;
        goto L39
    L45:
        float r15 = this.totalWidthLeft;
        float r26 = this.totalVisibilityFactor;
        this.totalWidthLeft = r15 * r26;
        this.totalWidthRight *= r26;
    L46:
        float r16 = this.animatorWrappingButton.getFloatValue();
        if (r16 > 0.0f) goto L49;
    L61:
        OnButtonsTotalWidthChanged r02 = this.onButtonsTotalWidthChanged;
        if (r02 == null) goto L83;
        r02.onButtonsTotalWidthChanged(this.totalWidthLeft, this.totalWidthRight);
        return;
    L83:
        return;
    L49:
        if (getMeasuredWidth() <= 0) goto L61;
        float r27 = getMeasuredWidth();
    L52:
        if (r3 >= getContainer().getChildCount()) goto L58;
        if (this.wrapContentButtons.contains(getContainer().getChildAt(r3)) == false) goto L56;
        r27 = Math.min(r27, r4.getLeft());
        r0 = Math.max(r0, r4.getRight());
    L56:
        r3 = r3 + 1;
        goto L52
    L58:
        if (r27 <= r0) goto L60;
        r0 = (r27 + r0) / 2.0f;
        r27 = r0;
    L60:
        this.totalWidthLeft = AndroidUtilities.lerp(this.totalWidthLeft, r27 - AndroidUtilities.dp(3.33f), r16);
        this.totalWidthRight = AndroidUtilities.lerp(this.totalWidthRight, (getMeasuredWidth() - r0) - AndroidUtilities.dp(17.66f), r16);
        goto L61
    }

    private void checkContainerPaddings(boolean r10) {
        int r1 = AndroidUtilities.dp(7.0f);
        int r0 = AndroidUtilities.dp(7.0f);
        int[] r2 = buttonsOrderLeft;
        int r3 = r2.length;
        int r5 = 0;
    L4:
        if (r5 >= r3) goto L14;
        ButtonHolder r7 = this.buttonHolders[r2[r5]];
        if (r7 == null) goto L13;
        if (r7.visibilityAnimator.getValue() == false) goto L11;
        int r6 = AndroidUtilities.dp(58.0f);
    L12:
        r1 = r1 + r6;
        goto L13
    L11:
        r6 = 0;
    L13:
        r5 = r5 + 1;
        goto L4
    L14:
        int[] r22 = buttonsOrderRight;
        int r32 = r22.length;
        int r52 = 0;
    L15:
        if (r52 >= r32) goto L25;
        ButtonHolder r72 = this.buttonHolders[r22[r52]];
        if (r72 == null) goto L24;
        if (r72.visibilityAnimator.getValue() == false) goto L22;
        int r73 = AndroidUtilities.dp(58.0f);
    L23:
        r0 = r0 + r73;
        goto L24
    L22:
        r73 = 0;
    L24:
        r52 = r52 + 1;
        goto L15
    L25:
        ViewGroup.MarginLayoutParams r23 = (ViewGroup.MarginLayoutParams) this.container.getLayoutParams();
        if (r23.leftMargin == r1) goto L28;
    L29:
        r23.leftMargin = r1;
        r23.rightMargin = r0;
        if (r10 == false) goto L40;
        this.container.requestLayout();
        return;
    L40:
        return;
    L28:
        if (r23.rightMargin != r0) goto L29;
    }

    private /* synthetic */ void lambda$showButton$0(int r1, View r2) {
        View.OnClickListener r0 = this.onClickListeners[r1];
        if (r0 == null) goto L6;
        r0.onClick(r2);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        int r0 = (int) ((this.totalVisibilityFactor * 255.0f) * this.animatorCenterAccentBackground.getFloatValue());
        if (r0 <= 0) goto L5;
        RectF r1 = tmpRect;
        r1.set(this.totalWidthLeft + AndroidUtilities.dp(10.0f), AndroidUtilities.dp(7.0f), (getMeasuredWidth() - AndroidUtilities.dp(10.0f)) - this.totalWidthRight, getMeasuredHeight() - AndroidUtilities.dp(7.0f));
        this.backgroundAccentPaint.setColor(this.accentColor);
        this.backgroundAccentPaint.setAlpha(r0);
        r8.drawRoundRect(r1, AndroidUtilities.dp(21.0f), AndroidUtilities.dp(21.0f), this.backgroundAccentPaint);
    L5:
        super.dispatchDraw(r8);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r6, View r7, long r8) {
        if (r7 != this.container) goto L8;
        if (this.containerDrawable == null) goto L8;
        RectF r0 = tmpRect;
        r0.set(this.totalWidthLeft + AndroidUtilities.dp(1.0f), 0.0f, (getMeasuredWidth() - AndroidUtilities.dp(1.0f)) - this.totalWidthRight, getMeasuredHeight());
        Rect r1 = AndroidUtilities.rectTmp2;
        r0.round(r1);
        this.containerDrawable.setBounds(r1);
        this.containerDrawable.draw(r6);
    L8:
        return super.drawChild(r6, r7, r8);
    }

    public FrameLayout getContainer() {
        return this.container;
    }

    public boolean isButtonVisible(int r2) {
        if (r2 < 0) goto L10;
        ButtonHolder[] r1 = this.buttonHolders;
        if (r2 >= r1.length) goto L12;
        ButtonHolder r12 = r1[r2];
        if (r12 != null) goto L9;
        return false;
    L9:
        return r12.visibilityAnimator.getValue();
    L12:
        return false;
    L10:
        return false;
    }

    public void makeViewWrapContent(View r1) {
        this.wrapContentButtons.add(r1);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChangeFinished(int r3, float r4, FactorAnimator r5) {
        if (r3 != 99) goto L5;
    L6:
        invalidate();
    L7:
        int r42 = r3 >> 16;
        int r32 = r3 & 65535;
        if (r42 < 0) goto L23;
        ButtonHolder[] r52 = this.buttonHolders;
        if (r42 >= r52.length) goto L24;
        ButtonHolder r53 = r52[r42];
        if (r53 != null) goto L15;
        return;
    L15:
        if (r32 == 1) goto L17;
        return;
    L17:
        if (r53.visibilityAnimator.getValue() == false) goto L26;
        OnButtonFullyVisibleListener r2 = this.onButtonFullyVisible[r42];
        if (r2 == null) goto L21;
        r2.onButtonFullyVisible(r53.button, r42, !r53.wasShown);
    L21:
        r53.wasShown = true;
        return;
    L26:
        return;
    L24:
        return;
    L23:
        return;
    L5:
        if (r3 != 100) goto L7;
        goto L6
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 99) goto L7;
        invalidate();
        return;
    L7:
        if (r1 != 100) goto L9;
        checkButtonsPositionsAndVisibility();
        invalidate();
    L9:
        int r22 = r1 >> 16;
        int r12 = r1 & 65535;
        if (r22 < 0) goto L20;
        ButtonHolder[] r32 = this.buttonHolders;
        if (r22 < r32.length) goto L14;
        return;
    L14:
        if (r32[r22] != null) goto L17;
        return;
    L17:
        if (r12 != 1) goto L22;
        checkContainerPaddings(true);
        checkButtonsPositionsAndVisibility();
        invalidate();
        return;
    L22:
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkButtonsPositionsAndVisibility();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        checkContainerPaddings(false);
        super.onMeasure(r2, r3);
        checkButtonsPositionsAndVisibility();
    }

    public void setAccentColor(int r1) {
        this.accentColor = r1;
    }

    public void setButtonOnClickListener(int r1, View.OnClickListener r2) {
        this.onClickListeners[r1] = r2;
    }

    public void setButtonOnFullyVisibleListener(int r1, OnButtonFullyVisibleListener r2) {
        this.onButtonFullyVisible[r1] = r2;
    }

    public void setCenterAccentBackground(boolean r1, boolean r2) {
        this.animatorCenterAccentBackground.setValue(r1, r2);
    }

    public void setOnButtonsTotalWidthChanged(OnButtonsTotalWidthChanged r1) {
        this.onButtonsTotalWidthChanged = r1;
    }

    public void setTotalVisibilityFactor(float r2) {
        if (this.totalVisibilityFactor == r2) goto L6;
        this.totalVisibilityFactor = r2;
        checkButtonsPositionsAndVisibility();
        invalidate();
        return;
    }

    @Override // android.view.View
    public void setVisibility(int r1) {
        super.setVisibility(r1);
        updateWrappingVisible(false);
    }

    public void setupDrawableForContainer() {
        this.containerDrawable = this.blurredBackgroundDrawableViewFactory.create(this).setColorProvider(this.colorProvider).setRadius(AndroidUtilities.dp(22.0f)).setPadding(AndroidUtilities.dp(6.0f));
    }

    public void showButton(int r12, boolean r13, boolean r14) {
        if (r12 < 0) goto L26;
        ButtonHolder[] r0 = this.buttonHolders;
        if (r12 >= r0.length) goto L27;
        ButtonHolder r02 = r0[r12];
        if (r02 != null) goto L10;
        if (r13 == true) goto L10;
        return;
    L10:
        if (r02 != null) goto L23;
        ChatActivityChannelButtonsLayout r4 = this;
        BoolAnimator r2 = new BoolAnimator((r12 << 16) | 1, r4, CubicBezierInterpolator.EASE_OUT_QUINT, 300);
        ChatActivityBlurredRoundButton r11 = ChatActivityBlurredRoundButton.create(r4.getContext(), r4.blurredBackgroundDrawableViewFactory, r4.colorProvider, r4.resourcesProvider, buttonIcons[r12], 48);
        if (r12 != 1) goto L15;
        r11.setContentDescription(LocaleController.getString(R.string.ProfileActionsGift));
    L22:
        ScaleStateListAnimator.apply(r11, 0.13f, 2.0f);
        r11.setVisibility(8);
        r11.setOnClickListener(new ChatActivityChannelButtonsLayout$$ExternalSyntheticLambda0(r4, r12));
        r4.addView(r11, LayoutHelper.createFrame(56, 56.0f));
        r4.buttonHolders[r12] = new ButtonHolder(r11, r2, null);
        r4.checkButtonsPositionsAndVisibility();
    L24:
        r4.buttonHolders[r12].visibilityAnimator.setValue(r13, r14);
        return;
    L15:
        if (r12 != 2) goto L17;
        r11.setContentDescription(LocaleController.getString(R.string.ChannelOpenDirect));
        goto L22
    L17:
        if (r12 != 0) goto L20;
        r11.setContentDescription(LocaleController.getString(R.string.Search));
        goto L22
    L20:
        if (r12 != 3) goto L22;
        r11.setContentDescription(LocaleController.getString(R.string.BroadcastGroupInfo));
        goto L22
    L23:
        r4 = this;
        goto L24
    L27:
        return;
    }

    public void updateColors() {
        ButtonHolder[] r3 = this.buttonHolders;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        ButtonHolder r2 = r3[r1];
        if (r2 == null) goto L7;
        r2.button.updateColors();
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void updateWrappingVisible(boolean r5) {
        boolean r1 = false;
        int r12 = 0;
        r1 = false;
        if (getVisibility() == 0) goto L5;
    L16:
        this.animatorWrappingButton.setValue(r1, r5);
        return;
    L5:
        if (getContainer().getVisibility() != 0) goto L16;
        boolean r0 = false;
    L8:
        if (r12 >= getContainer().getChildCount()) goto L15;
        View r2 = getContainer().getChildAt(r12);
        if (this.wrapContentButtons.contains(r2) == false) goto L14;
        if (r2.getVisibility() != 0) goto L14;
        r0 = true;
    L14:
        r12 = r12 + 1;
        goto L8
    L15:
        r1 = r0;
        goto L16
    }
}
