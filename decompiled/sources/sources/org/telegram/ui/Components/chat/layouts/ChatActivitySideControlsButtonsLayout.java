package org.telegram.ui.Components.chat.layouts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import me.vkryl.android.AnimatorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.chat.buttons.ChatActivityBlurredRoundPageDownButton;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatActivitySideControlsButtonsLayout extends FrameLayout implements FactorAnimator.Target {
    private static final int[] buttonIcons = null;
    private final BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableViewFactory;
    private final String[] buttonDescriptions;
    private final ButtonHolder[] buttonHolders;
    private final BlurredBackgroundColorProvider colorProvider;
    private int gravity;
    private ButtonOnClickListener onClickListener;
    private ButtonOnLongClickListener onLongClickListener;
    private final ButtonPendingState[] pendingStates;
    private final Theme.ResourcesProvider resourcesProvider;

    public static class ButtonHolder {
        public final ChatActivityBlurredRoundPageDownButton button;
        public final BoolAnimator counterVisibilityAnimator;
        public final BoolAnimator visibilityAnimator;

        private ButtonHolder(ChatActivityBlurredRoundPageDownButton r1, BoolAnimator r2, BoolAnimator r3) {
            this.button = r1;
            this.visibilityAnimator = r2;
            this.counterVisibilityAnimator = r3;
        }

        public /* synthetic */ ButtonHolder(ChatActivityBlurredRoundPageDownButton r1, BoolAnimator r2, BoolAnimator r3, ChatActivitySideControlsButtonsLayoutIA r4) {
            this(r1, r2, r3);
        }
    }

    public static class ButtonPendingState {
        public int count;
        public boolean enabled;
        public boolean loading;

        private ButtonPendingState() {
            this.count = 0;
            this.loading = false;
            this.enabled = true;
        }

        public /* synthetic */ ButtonPendingState(ChatActivitySideControlsButtonsLayoutIA r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$dRI-X88dSSMMXqg7mgPtJLGyshM, reason: not valid java name */
    public static /* synthetic */ void m13698$r8$lambda$dRIX88dSSMMXqg7mgPtJLGyshM(ChatActivitySideControlsButtonsLayout r0, int r1, View r2) {
        r0.lambda$getOrCreateButtonHolder$0(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$kt7hwCJfq0thdB9wq9NGgwv4lJk(ChatActivitySideControlsButtonsLayout r0, int r1, View r2) {
        return r0.lambda$getOrCreateButtonHolder$1(r1, r2);
    }

    static {
        int r0 = R.drawable.msg_input_attach2;
        int r1 = R.drawable.pagedown;
        buttonIcons = new int[]{r0, r1, R.drawable.mentionbutton, R.drawable.reactionbutton, R.drawable.menu_poll_notify, r1, r1};
    }

    public ChatActivitySideControlsButtonsLayout(Context r8, Theme.ResourcesProvider r9, BlurredBackgroundColorProvider r10, BlurredBackgroundDrawableViewFactory r11) {
        super(r8);
        this.buttonDescriptions = new String[]{LocaleController.getString(R.string.AttachMenu), LocaleController.getString(R.string.AccDescrPageDown), LocaleController.getString(R.string.AccDescrMentionDown), LocaleController.getString(R.string.AccDescrReactionMentionDown), LocaleController.getString(R.string.AccDescrPollVotesMentionDown), LocaleController.getString(R.string.AccDescrSearchPrev), LocaleController.getString(R.string.AccDescrSearchNext)};
        this.buttonHolders = new ButtonHolder[7];
        this.pendingStates = new ButtonPendingState[7];
        this.gravity = 83;
        this.blurredBackgroundDrawableViewFactory = r11;
        this.colorProvider = r10;
        this.resourcesProvider = r9;
    }

    private void checkButtonsPositionsAndVisibility() {
        float r3 = 0.0f;
        int r2 = 0;
    L3:
        ButtonHolder[] r4 = this.buttonHolders;
        if (r2 >= r4.length) goto L17;
        ButtonHolder r42 = r4[r2];
        if (r42 == null) goto L16;
        float r5 = r42.visibilityAnimator.getFloatValue();
        float r6 = r42.counterVisibilityAnimator.getFloatValue();
        ChatActivityBlurredRoundPageDownButton r7 = r42.button;
        if (r5 <= 0.0f) goto L11;
        int r8 = 0;
    L12:
        r7.setVisibility(r8);
        r42.button.setAlpha(r5);
        r42.button.setScaleX(AndroidUtilities.lerp(0.7f, 1.0f, r5));
        r42.button.setScaleY(AndroidUtilities.lerp(0.7f, 1.0f, r5));
        if (r2 == 0) goto L15;
        r42.button.setTranslationY((AndroidUtilities.dp(80.0f) * (1.0f - r5)) - r3);
    L15:
        r3 = r3 + ((AndroidUtilities.dp(48.0f) + AndroidUtilities.dp((r6 * 10.0f) + 10.0f)) * r5);
        goto L16
    L11:
        r8 = 8;
    L16:
        r2 = r2 + 1;
        goto L3
    }

    private ButtonHolder getButtonHolder(int r2) {
        if (r2 < 0) goto L8;
        ButtonHolder[] r1 = this.buttonHolders;
        if (r2 < r1.length) goto L7;
        return null;
    L7:
        return r1[r2];
    L8:
        return null;
    }

    private ButtonHolder getOrCreateButtonHolder(int r21) {
        ChatActivitySideControlsButtonsLayout r2 = this;
        if (r2.buttonHolders[r21] != null) goto L41;
        int r7 = r21 << 16;
        int r1 = r7 | 1;
        if (r21 != 0) goto L7;
        Interpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
    L9:
        if (r21 != 0) goto L11;
        long r4 = 300;
    L12:
        BoolAnimator r0 = new BoolAnimator(r1, r2, r3, r4);
        int r12 = r7 | 2;
        if (r21 != 0) goto L16;
        Interpolator r22 = CubicBezierInterpolator.EASE_OUT_QUINT;
    L15:
        Interpolator r32 = r22;
        if (r21 != 0) goto L20;
        long r42 = 300;
    L19:
        r2 = this;
        BoolAnimator r02 = new BoolAnimator(r12, r2, r32, r42);
        if (r21 != 0) goto L25;
        int r13 = 50;
        int r33 = 32;
    L24:
        int r14 = r13;
        ChatActivityBlurredRoundPageDownButton r15 = ChatActivityBlurredRoundPageDownButton.create(r2.getContext(), r14, r33, r2.resourcesProvider, r2.blurredBackgroundDrawableViewFactory, r2.colorProvider, buttonIcons[r21]);
        float r34 = r14 / 2.0f;
        r15.setPivotX(AndroidUtilities.dp(r34));
        r15.setPivotY(AndroidUtilities.dp(r34 + 8.0f));
        r15.setVisibility(8);
        r15.setContentDescription(r2.buttonDescriptions[r21]);
        r15.setOnClickListener(new ChatActivitySideControlsButtonsLayout$$ExternalSyntheticLambda0(r2, r21));
        r15.setOnLongClickListener(new ChatActivitySideControlsButtonsLayout$$ExternalSyntheticLambda1(r2, r21));
        if (r21 != 6) goto L29;
        r15.reverseIconByY();
    L29:
        boolean r35 = true;
        if (r21 != 1) goto L32;
        r15.reverseCounter();
    L32:
        r2.addView(r15, LayoutHelper.createFrame(r14, r14 + 8, r2.gravity));
        r2.buttonHolders[r21] = new ButtonHolder(r15, r0, r02, null);
        ButtonPendingState r43 = r2.pendingStates[r21];
        if (r43 == null) goto L39;
        r15.setCount(r43.count, false);
        r0.setValue(false, false);
        if (r43.count > 0) goto L38;
        r35 = false;
    L38:
        r02.setValue(r35, false);
        r15.showLoading(r43.loading, false);
        r15.setEnabled(r43.enabled, false);
    L39:
        r2.checkButtonsPositionsAndVisibility();
        goto L41
    L25:
        r13 = 56;
        r33 = 48;
        goto L24
    L20:
        r42 = 280;
        goto L19
    L16:
        r22 = AnimatorUtils.DECELERATE_INTERPOLATOR;
        goto L15
    L11:
        r4 = 280;
        goto L12
    L7:
        r3 = AnimatorUtils.DECELERATE_INTERPOLATOR;
    L41:
        return r2.buttonHolders[r21];
    }

    private ButtonPendingState getOrCreatePendingState(int r4) {
        ButtonPendingState[] r0 = this.pendingStates;
        if (r0[r4] != null) goto L6;
        r0[r4] = new ButtonPendingState(null);
    L6:
        return this.pendingStates[r4];
    }

    private /* synthetic */ void lambda$getOrCreateButtonHolder$0(int r1, View r2) {
        ButtonOnClickListener r0 = this.onClickListener;
        if (r0 == null) goto L6;
        r0.onClick(r1, r2);
        return;
    }

    private /* synthetic */ boolean lambda$getOrCreateButtonHolder$1(int r1, View r2) {
        ButtonOnLongClickListener r0 = this.onLongClickListener;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.onLongClick(r1, r2);
    }

    public boolean getButtonLocationInWindow(int r1, int[] r2) {
        ButtonHolder r0 = this.buttonHolders[r1];
        if (r0 == null) goto L6;
        r0.button.getLocationInWindow(r2);
        return true;
    L6:
        return false;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isButtonVisible(int r1) {
        ButtonHolder r0 = getButtonHolder(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.visibilityAnimator.getValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        int r22 = r1 >> 16;
        int r12 = r1 & 65535;
        if (r22 < 0) goto L15;
        ButtonHolder[] r32 = this.buttonHolders;
        if (r22 < r32.length) goto L7;
        return;
    L7:
        if (r32[r22] != null) goto L10;
        return;
    L10:
        if (r12 != 1) goto L12;
    L13:
        checkButtonsPositionsAndVisibility();
        return;
    L12:
        if (r12 == 2) goto L13;
        return;
    }

    public void setButtonCount(int r2, int r3, boolean r4) {
        getOrCreatePendingState(r2).count = r3;
        ButtonHolder r1 = this.buttonHolders[r2];
        if (r1 == null) goto L10;
        r1.button.setCount(r3, r4);
        BoolAnimator r12 = r1.counterVisibilityAnimator;
        if (r3 <= 0) goto L7;
        boolean r22 = true;
    L8:
        r12.setValue(r22, r4);
        return;
    L7:
        r22 = false;
        goto L8
    }

    public void setButtonEnabled(int r2, boolean r3, boolean r4) {
        getOrCreatePendingState(r2).enabled = r3;
        ButtonHolder r1 = this.buttonHolders[r2];
        if (r1 == null) goto L6;
        r1.button.setEnabled(r3, r4);
        return;
    }

    public void setButtonLoading(int r2, boolean r3, boolean r4) {
        getOrCreatePendingState(r2).loading = r3;
        ButtonHolder r1 = this.buttonHolders[r2];
        if (r1 == null) goto L6;
        r1.button.showLoading(r3, r4);
        return;
    }

    public void setGravity(int r1) {
        this.gravity = r1;
    }

    public void setOnClickListener(ButtonOnClickListener r1) {
        this.onClickListener = r1;
    }

    public void setOnLongClickListener(ButtonOnLongClickListener r1) {
        this.onLongClickListener = r1;
    }

    public void showButton(int r2, boolean r3, boolean r4) {
        if (this.buttonHolders[r2] != null) goto L6;
        if (r3 == true) goto L6;
        return;
    L6:
        getOrCreateButtonHolder(r2).visibilityAnimator.setValue(r3, r4);
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
}
