package org.telegram.ui.Components.chat.layouts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import me.vkryl.android.AnimatorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.chat.buttons.ChatActivityBlurredRoundButton;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatActivityActionsButtonsLayout extends LinearLayout {
    private final ButtonHolder forwardButton;
    private final ButtonHolder replyButton;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ButtonHolder selectButton;
    private float totalVisibilityFactor;

    public class ButtonHolder implements FactorAnimator.Target {
        public ChatActivityBlurredRoundButton button;
        public BoolAnimator enabledAnimator;
        public TextView textView;
        final /* synthetic */ ChatActivityActionsButtonsLayout this$0;
        public BoolAnimator visibilityAnimator;

        private ButtonHolder(ChatActivityActionsButtonsLayout r9) {
            this.this$0 = r9;
            CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.visibilityAnimator = new BoolAnimator(0, this, r3, 320, true);
            this.enabledAnimator = new BoolAnimator(1, this, r3, 320, true);
        }

        public /* synthetic */ ButtonHolder(ChatActivityActionsButtonsLayout r1, ChatActivityActionsButtonsLayoutIA r2) {
            this(r1);
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
            this.textView.setAlpha(AndroidUtilities.lerp(0.5f, 1.0f, this.enabledAnimator.getFloatValue()));
            ChatActivityActionsButtonsLayout.m13697$$Nest$mcheckHolderPositionsAndVisibility(this.this$0, this);
        }
    }

    /* renamed from: $r8$lambda$93jBtX9Pu1-HgVxuSin9Qptiq48, reason: not valid java name */
    public static /* synthetic */ void m13695$r8$lambda$93jBtX9Pu1HgVxuSin9Qptiq48(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$OWO67Z7oaPucPZ7e7VDOmXkKf18(View r0) {
    }

    /* renamed from: $r8$lambda$hGVe-BG6LPQS6sq_s-AAeOPhOqg, reason: not valid java name */
    public static /* synthetic */ void m13696$r8$lambda$hGVeBG6LPQS6sq_sAAeOPhOqg(View r0) {
    }

    /* renamed from: -$$Nest$mcheckHolderPositionsAndVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13697$$Nest$mcheckHolderPositionsAndVisibility(ChatActivityActionsButtonsLayout r0, ButtonHolder r1) {
        r0.checkHolderPositionsAndVisibility(r1);
    }

    public ChatActivityActionsButtonsLayout(Context r11, Theme.ResourcesProvider r12, BlurredBackgroundColorProvider r13, BlurredBackgroundDrawableViewFactory r14) {
        super(r11);
        ButtonHolder r0 = new ButtonHolder(this, null);
        this.replyButton = r0;
        ButtonHolder r2 = new ButtonHolder(this, null);
        this.forwardButton = r2;
        ButtonHolder r3 = new ButtonHolder(this, null);
        this.selectButton = r3;
        this.resourcesProvider = r12;
        ChatActivityBlurredRoundButton r1 = ChatActivityBlurredRoundButton.create(r11, r14, r13, r12);
        r0.button = r1;
        r1.setOnClickListener(new ChatActivityActionsButtonsLayout$$ExternalSyntheticLambda0());
        ScaleStateListAnimator.apply(r0.button, 0.065f, 2.0f);
        ChatActivityBlurredRoundButton r15 = ChatActivityBlurredRoundButton.create(r11, r14, r13, r12);
        r2.button = r15;
        r15.setOnClickListener(new ChatActivityActionsButtonsLayout$$ExternalSyntheticLambda1());
        ScaleStateListAnimator.apply(r2.button, 0.065f, 2.0f);
        ChatActivityBlurredRoundButton r122 = ChatActivityBlurredRoundButton.create(r11, r14, r13, r12);
        r3.button = r122;
        r122.setOnClickListener(new ChatActivityActionsButtonsLayout$$ExternalSyntheticLambda2());
        ScaleStateListAnimator.apply(r3.button, 0.065f, 2.0f);
        r3.button.setVisibility(4);
        r3.button.setAlpha(0.0f);
        addTextView(r0, LocaleController.getString(R.string.Reply), R.drawable.input_reply, false);
        addTextView(r2, LocaleController.getString(R.string.Forward), R.drawable.input_forward, true);
        addTextView(r3, LocaleController.getString(R.string.Select), R.drawable.select_between, false);
        setOrientation(0);
        setClipChildren(false);
        FrameLayout r123 = new FrameLayout(r11);
        r123.setClipChildren(false);
        r123.addView(r0.button, LayoutHelper.createFrame(-1, -1.0f));
        r123.addView(r3.button, LayoutHelper.createFrame(-1, -1.0f));
        addView(r123, LayoutHelper.createLinear(0, 56, 1.0f, 1, 0, -1, 0));
        addView(r2.button, LayoutHelper.createLinear(0, 56, 1.0f, -1, 0, 1, 0));
    }

    private void addTextView(ButtonHolder r4, String r5, int r6, boolean r7) {
        TextView r0 = new TextView(getContext());
        r0.setText(r5);
        r0.setGravity(16);
        r0.setTextSize(1, 15.0f);
        r0.setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(21.0f), 0);
        r0.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        r0.setTextColor(Theme.getColor(Theme.key_glass_defaultText, this.resourcesProvider));
        r0.setTypeface(AndroidUtilities.bold());
        Drawable r52 = getContext().getResources().getDrawable(r6).mutate();
        r52.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
        if (r7 == false) goto L5;
        Drawable r62 = r52;
    L6:
        if (r7 == false) goto L8;
        r52 = null;
    L8:
        r0.setCompoundDrawablesWithIntrinsicBounds(r62, null, r52, null);
        r4.textView = r0;
        r4.button.addView(r0, LayoutHelper.createFrame(-2, -2, 17));
        return;
    L5:
        r62 = null;
        goto L6
    }

    private void checkButtonsPositionsAndVisibility() {
        checkHolderPositionsAndVisibility(this.forwardButton);
        checkHolderPositionsAndVisibility(this.replyButton);
        checkHolderPositionsAndVisibility(this.selectButton);
    }

    private void checkHolderPositionsAndVisibility(ButtonHolder r6) {
        float r0 = this.totalVisibilityFactor * r6.visibilityAnimator.getFloatValue();
        float r1 = (-AndroidUtilities.dp(54.0f)) * (1.0f - r0);
        float r3 = (getMeasuredWidth() / 2.0f) * (1.0f - AnimatorUtils.DECELERATE_INTERPOLATOR.getInterpolation(r0));
        if (r6 != this.replyButton) goto L5;
    L6:
        r3 = r3 * (-1.0f);
    L7:
        r6.button.setTranslationX(r3);
        r6.button.setTranslationY(r1);
        r6.button.setAlpha(r0);
        ChatActivityBlurredRoundButton r62 = r6.button;
        if (r0 > 0.05f) goto L11;
        r62.setVisibility(4);
        return;
    L11:
        r62.setVisibility(0);
        return;
    L5:
        if (r6 != this.selectButton) goto L7;
        goto L6
    }

    public View getForwardButton() {
        return this.forwardButton.button;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        checkButtonsPositionsAndVisibility();
    }

    public void setForwardButtonEnabled(boolean r2, boolean r3) {
        this.forwardButton.enabledAnimator.setValue(r2, r3);
        this.forwardButton.button.setEnabled(r2);
    }

    public void setForwardButtonOnClickListener(View.OnClickListener r1) {
        this.forwardButton.button.setOnClickListener(r1);
    }

    public void setForwardButtonOnLongClickListener(View.OnLongClickListener r1) {
        this.forwardButton.button.setOnLongClickListener(r1);
    }

    public void setReplyButtonOnClickListener(View.OnClickListener r1) {
        this.replyButton.button.setOnClickListener(r1);
    }

    public void setSelectButtonOnClickListener(View.OnClickListener r1) {
        this.selectButton.button.setOnClickListener(r1);
    }

    public void setTotalVisibilityFactor(float r2) {
        if (this.totalVisibilityFactor == r2) goto L6;
        this.totalVisibilityFactor = r2;
        checkButtonsPositionsAndVisibility();
        return;
    }

    public void showReplyButton(boolean r1, boolean r2) {
        this.replyButton.visibilityAnimator.setValue(r1, r2);
    }

    public void showSelectButton(boolean r1, boolean r2) {
        this.selectButton.visibilityAnimator.setValue(r1, r2);
    }
}
