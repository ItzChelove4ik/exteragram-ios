package org.telegram.ui.bots;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.Iterator;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.android.animator.ReplaceAnimator;
import me.vkryl.core.lambda.Destroyable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.inset.InAppKeyboardInsetView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public abstract class BotKeyboardView extends LinearLayout implements InAppKeyboardInsetView, ReplaceAnimator.Callback {
    private final ReplaceAnimator<ButtonsLayout> animator;
    private TLRPC.TL_replyKeyboardMarkup botButtons;
    private int buttonHeight;
    private final ArrayList<Button> buttonViews;
    private BotKeyboardViewDelegate delegate;
    private final GradientDrawable fadeDrawable;
    private final FrameLayout frameLayout;
    private boolean isFullSize;
    private int lastFadeColor;
    private int navigationBarHeight;
    private int panelHeight;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ScrollView scrollView;

    public interface BotKeyboardViewDelegate {
        void didPressedButton(TL_keyboard.KeyboardButton r1);
    }

    public class Button extends FrameLayout {
        private final TL_keyboard.KeyboardButton button;
        private final ImageView icon;
        private boolean isBottom;
        private boolean isLeft;
        private boolean isRight;
        private boolean isTop;
        private final SpoilersTextView textView;
        final /* synthetic */ BotKeyboardView this$0;

        public Button(BotKeyboardView r11, Context r12, TL_keyboard.KeyboardButton r13) {
            this.this$0 = r11;
            super(r12);
            this.button = r13;
            SpoilersTextView r0 = new SpoilersTextView(r12);
            this.textView = r0;
            r0.allowClickSpoilers = false;
            r0.setTextSize(1, 14.0f);
            r0.setTypeface(AndroidUtilities.bold());
            NotificationCenter.listenEmojiLoading(r0);
            addView(r0, LayoutHelper.createFrame(-2, -2, 17));
            NotificationCenter.listenEmojiLoading(r0);
            setTag(r13);
            SpannableStringBuilder r1 = new SpannableStringBuilder();
            TL_keyboard.KeyboardButtonStyle r3 = r13.style;
            if (r3 != null) goto L5;
        L7:
            r1.append(Emoji.replaceEmoji(r13.text, r0.getPaint().getFontMetricsInt(), false));
            ImageView r2 = new ImageView(getContext());
            this.icon = r2;
            r2.setColorFilter(BotKeyboardView.m21321$$Nest$mgetThemedColor(r11, Theme.key_chat_botKeyboardButtonText));
            if (TLKeyboardHelper.isButtonWebView(r13) == false) goto L10;
            r2.setImageResource(R.drawable.bot_webview);
            r2.setVisibility(0);
        L11:
            addView(r2, LayoutHelper.createFrame(12, 12.0f, 53, 0.0f, 8.0f, 8.0f, 0.0f));
            r0.setText(r1);
            return;
        L10:
            r2.setVisibility(8);
            goto L11
        L5:
            if (r3.icon == 0) goto L7;
            r1.append("* ");
            r1.setSpan(new AnimatedEmojiSpan(r13.style.icon, r0.getPaint().getFontMetricsInt()), 0, 1, 33);
            goto L7
        }

        public void setPositionFlags(boolean r1, boolean r2, boolean r3, boolean r4) {
            this.isLeft = r1;
            this.isTop = r2;
            this.isRight = r3;
            this.isBottom = r4;
            updateColors();
        }

        public void updateColors() {
            int r0 = AndroidUtilities.dp(12.0f);
            int r1 = AndroidUtilities.dp(11.0f);
            int r2 = BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_chat_botKeyboardButtonBackground);
            int r3 = BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_chat_botKeyboardButtonBackgroundPressed);
            int r4 = BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_chat_botKeyboardButtonText);
            TL_keyboard.KeyboardButtonStyle r5 = this.button.style;
            if (r5 != null) goto L5;
        L14:
            int r12 = r2;
            int r13 = r3;
        L15:
            this.icon.setColorFilter(r4);
            this.textView.setTextColor(r4);
            boolean r22 = this.isLeft;
            if (r22 == true) goto L18;
        L20:
            int r8 = r1;
        L21:
            boolean r32 = this.isRight;
            if (r32 == true) goto L24;
        L26:
            int r9 = r1;
        L27:
            if (r32 == true) goto L29;
        L31:
            int r10 = r1;
        L32:
            if (r22 == true) goto L34;
        L36:
            int r11 = r1;
        L37:
            setBackground(Theme.createSimpleSelectorRoundRectDrawable(r8, r9, r10, r11, r12, r13, r13));
            return;
        L34:
            if (this.isBottom == false) goto L36;
            r11 = r0;
            goto L37
        L29:
            if (this.isBottom == false) goto L31;
            r10 = r0;
            goto L32
        L24:
            if (this.isTop == false) goto L26;
            r9 = r0;
            goto L27
        L18:
            if (this.isTop == false) goto L20;
            r8 = r0;
            goto L21
        L5:
            if (r5.bg_primary == false) goto L9;
            int r23 = Theme.multAlpha(BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_botKeyboard_button_primary), 0.8f);
            int r33 = ColorUtils.compositeColors(BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_listSelector), r23);
        L7:
            r12 = r23;
            r13 = r33;
            r4 = -1;
            goto L15
        L9:
            if (r5.bg_danger == false) goto L12;
            r23 = Theme.multAlpha(BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_botKeyboard_button_danger), 0.8f);
            r33 = ColorUtils.compositeColors(BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_listSelector), r23);
            goto L7
        L12:
            if (r5.bg_success == false) goto L14;
            r23 = Theme.multAlpha(BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_botKeyboard_button_success), 0.8f);
            r33 = ColorUtils.compositeColors(BotKeyboardView.m21321$$Nest$mgetThemedColor(this.this$0, Theme.key_listSelector), r23);
            goto L7
        }
    }

    public static class ButtonsLayout extends LinearLayout implements Destroyable {
        public ButtonsLayout(Context r1) {
            super(r1);
        }

        @Override // me.vkryl.core.lambda.Destroyable
        public void performDestroy() {
            ((ViewGroup) getParent()).removeView(this);
        }
    }

    /* renamed from: $r8$lambda$-jPeXVbCJl5ykuSHahpPefbQMuM, reason: not valid java name */
    public static /* synthetic */ void m21320$r8$lambda$jPeXVbCJl5ykuSHahpPefbQMuM(BotKeyboardView r0, View r1) {
        r0.lambda$setButtons$0(r1);
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21321$$Nest$mgetThemedColor(BotKeyboardView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public BotKeyboardView(Context r5, Theme.ResourcesProvider r6) {
        super(r5);
        this.buttonViews = new ArrayList();
        this.fadeDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, null);
        this.animator = new ReplaceAnimator(this, CubicBezierInterpolator.EASE_OUT_QUINT, 320);
        this.resourcesProvider = r6;
        setOrientation(1);
        ScrollView r62 = new ScrollView(r5);
        this.scrollView = r62;
        r62.setClipToPadding(false);
        addView(r62);
        FrameLayout r0 = new FrameLayout(r5);
        this.frameLayout = r0;
        r62.addView(r0);
        updateColors();
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$setButtons$0(View r1) {
        this.delegate.didPressedButton((TL_keyboard.KeyboardButton) r1.getTag());
    }

    @Override // org.telegram.ui.Components.inset.InAppKeyboardInsetView
    public void applyInAppKeyboardAnimatedHeight(float r1) {
    }

    @Override // org.telegram.ui.Components.inset.InAppKeyboardInsetView
    public void applyNavigationBarHeight(int r3) {
        if (this.navigationBarHeight != r3) goto L5;
        return;
    L5:
        this.navigationBarHeight = r3;
        if (this.scrollView.getPaddingBottom() == r3) goto L8;
        this.scrollView.setPadding(0, 0, 0, r3);
    L8:
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r6) {
        super.dispatchDraw(r6);
        float r0 = AndroidUtilities.getNavigationBarThirdButtonsFactor(this.navigationBarHeight);
        if (r0 <= 0.0f) goto L9;
        int r02 = Theme.multAlpha(getThemedColor(Theme.key_chat_emojiPanelBackground), r0);
        if (this.lastFadeColor == r02) goto L7;
        this.fadeDrawable.setColors(new int[]{r02, Theme.multAlpha(r02, 0.66f), ColorUtils.setAlphaComponent(r02, 0)});
        this.lastFadeColor = r02;
    L7:
        this.fadeDrawable.setBounds(0, getMeasuredHeight() - this.navigationBarHeight, getMeasuredWidth(), getMeasuredHeight());
        this.fadeDrawable.draw(r6);
        return;
    }

    public int getKeyboardHeight() {
        TLRPC.TL_replyKeyboardMarkup r0 = this.botButtons;
        if (r0 != null) goto L7;
        return 0;
    L7:
        if (this.isFullSize == false) goto L11;
        return this.panelHeight;
    L11:
        return ((r0.rows.size() * AndroidUtilities.dp(this.buttonHeight)) + AndroidUtilities.dp(16.0f)) + ((this.botButtons.rows.size() - 1) * AndroidUtilities.dp(4.0f));
    }

    public void invalidateViews() {
        int r0 = 0;
    L4:
        if (r0 >= this.buttonViews.size()) goto L6;
        this.buttonViews.get(r0).invalidate();
        r0 = r0 + 1;
        goto L4
    }

    public boolean isFullSize() {
        return this.isFullSize;
    }

    @Override // me.vkryl.android.animator.ReplaceAnimator.Callback
    public void onItemChanged(ReplaceAnimator<?> r4) {
        Iterator<ListAnimator.Entry<ButtonsLayout>> r3 = this.animator.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        ListAnimator.Entry<ButtonsLayout> r42 = r3.next();
        float r0 = r42.getVisibility();
        float r1 = AndroidUtilities.lerp(0.7f, 1.0f, r0);
        r42.item.setAlpha(r0);
        r42.item.setScaleX(r1);
        r42.item.setScaleY(r1);
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setButtons(TLRPC.TL_replyKeyboardMarkup r20) {
        if (TlUtils.tlEquals(r20, this.botButtons) == false) goto L5;
        return;
    L5:
        this.botButtons = r20;
        this.buttonViews.clear();
        float r2 = this.scrollView.getScrollY();
        Iterator<ListAnimator.Entry<ButtonsLayout>> r3 = this.animator.iterator();
    L7:
        if (r3.hasNext() == false) goto L9;
        ButtonsLayout r4 = r3.next().item;
        r4.setTranslationY(r4.getTranslationY() - r2);
        goto L7
    L9:
        int r32 = 0;
        this.scrollView.scrollTo(0, 0);
        if (r20 != null) goto L12;
    L54:
        this.animator.clear(true);
        return;
    L12:
        if (this.botButtons.rows.isEmpty() == true) goto L54;
        ButtonsLayout r42 = new ButtonsLayout(getContext());
        r42.setOrientation(1);
        r42.setAlpha(0.0f);
        this.frameLayout.addView(r42);
        boolean r6 = r20.resize;
        this.isFullSize = !r6;
        if (r6 == false) goto L16;
        int r62 = 44;
    L17:
        this.buttonHeight = r62;
        int r63 = 0;
    L19:
        if (r63 >= r20.rows.size()) goto L52;
        TL_keyboard.KeyboardButtonRow r8 = r20.rows.get(r63);
        LinearLayout r9 = new LinearLayout(getContext());
        r9.setOrientation(r32);
        int r12 = this.buttonHeight;
        if (r63 != 0) goto L23;
        float r14 = 8.0f;
    L25:
        if (r63 != (r20.rows.size() - 1)) goto L27;
        float r16 = 8.0f;
    L28:
        r42.addView(r9, LayoutHelper.createLinear(-1, r12, 8.0f, r14, 8.0f, r16));
        float r142 = 1.0f / r8.buttons.size();
        int r10 = r32;
    L30:
        if (r10 >= r8.buttons.size()) goto L51;
        Button r122 = new Button(this, getContext(), r8.buttons.get(r10));
        if (r10 != 0) goto L34;
        boolean r11 = 1;
    L35:
        if (r63 != 0) goto L37;
        boolean r13 = 1;
    L39:
        if (r10 != (r8.buttons.size() - 1)) goto L41;
        boolean r15 = 1;
    L43:
        if (r63 != (r20.rows.size() - 1)) goto L45;
        boolean r33 = true;
    L46:
        r122.setPositionFlags(r11, r13, r15, r33);
        FrameLayout r34 = new FrameLayout(getContext());
        r34.addView(r122, LayoutHelper.createFrame(-1, -1.0f));
        if (r10 == (r8.buttons.size() - 1)) goto L49;
        int r17 = 4;
    L50:
        r9.addView(r34, LayoutHelper.createLinear(0, -1, r142, 0, 0, r17, 0));
        r122.setOnClickListener(new BotKeyboardView$$ExternalSyntheticLambda0(this));
        ScaleStateListAnimator.apply(r122, 0.02f, 1.5f);
        this.buttonViews.add(r122);
        r122.updateColors();
        r10 = r10 + 1;
        r32 = 0;
        goto L30
    L49:
        r17 = 0;
        goto L50
    L45:
        r33 = false;
        goto L46
    L41:
        r15 = r32;
        goto L43
    L37:
        r13 = r32;
        goto L39
    L34:
        r11 = r32;
        goto L35
    L51:
        r63 = r63 + 1;
        r32 = 0;
        goto L19
    L27:
        r16 = 0.0f;
        goto L28
    L23:
        r14 = 4.0f;
        goto L25
    L52:
        this.animator.replace(r42, true);
        return;
    L16:
        r62 = (int) Math.max(44.0f, (((this.panelHeight - AndroidUtilities.dp(16.0f)) - ((this.botButtons.rows.size() - 1) * AndroidUtilities.dp(4.0f))) / this.botButtons.rows.size()) / AndroidUtilities.density);
        goto L17
    }

    public void setDelegate(BotKeyboardViewDelegate r1) {
        this.delegate = r1;
    }

    public void setPanelHeight(int r7) {
        this.panelHeight = r7;
        if (this.isFullSize == false) goto L21;
        TLRPC.TL_replyKeyboardMarkup r72 = this.botButtons;
        if (r72 != null) goto L7;
        return;
    L7:
        if (r72.rows.isEmpty() == false) goto L9;
        return;
    L9:
        if (this.isFullSize == true) goto L11;
        int r73 = 44;
    L12:
        this.buttonHeight = r73;
        int r74 = AndroidUtilities.dp(r73);
        Iterator<ListAnimator.Entry<ButtonsLayout>> r6 = this.animator.iterator();
    L14:
        if (r6.hasNext() == false) goto L28;
        ListAnimator.Entry<ButtonsLayout> r0 = r6.next();
        int r1 = r0.item.getChildCount();
        int r2 = 0;
    L16:
        if (r2 >= r1) goto L14;
        View r3 = r0.item.getChildAt(r2);
        LinearLayout.LayoutParams r4 = (LinearLayout.LayoutParams) r3.getLayoutParams();
        if (r4.height == r74) goto L20;
        r4.height = r74;
        r3.setLayoutParams(r4);
    L20:
        r2 = r2 + 1;
        goto L16
    L28:
        return;
    L11:
        r73 = (int) Math.max(44.0f, (((this.panelHeight - AndroidUtilities.dp(16.0f)) - ((this.botButtons.rows.size() - 1) * AndroidUtilities.dp(4.0f))) / this.botButtons.rows.size()) / AndroidUtilities.density);
        goto L12
    }

    public void updateColors() {
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, getThemedColor(Theme.key_chat_emojiPanelBackground));
        int r0 = 0;
    L4:
        if (r0 >= this.buttonViews.size()) goto L6;
        this.buttonViews.get(r0).updateColors();
        r0 = r0 + 1;
        goto L4
    L6:
        invalidate();
    }
}
