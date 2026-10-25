package org.telegram.ui.Components.poll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.ChatAttachAlertPollLayout;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class PollAddOptionFieldLayout extends FrameLayout implements ViewTreeObserver.OnPreDrawListener {
    private final BoolAnimator animatorTextErrorVisibility;
    private final BoolAnimator animatorTextWarnVisibility;
    private final PollAttachButton attachButton;
    private PollAttachedMedia attachedMedia;
    public ChatMessageCell cellToWatch;
    private final int[] cords;
    private final EmojiButton emojiButton;
    private final BaseFragment fragment;
    private int lastColor;
    private final SimpleTextView limitTextView;
    private final FrameLayout.LayoutParams lp;
    private final int maxLength;
    private int messageIdToWatch;
    private ViewTreeObserver observer;
    private Runnable onCancel;
    private final Rect rect;
    public final EditTextBoldCursor textView;
    private FrameLayout viewsContainer;
    private ViewWrapper viewsContainerWrapper;

    /* renamed from: org.telegram.ui.Components.poll.PollAddOptionFieldLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextCaption {
        final /* synthetic */ PollAddOptionFieldLayout this$0;

        /* renamed from: $r8$lambda$GfpACol8rJZ8dMwb_-TCKsjZxwY, reason: not valid java name */
        public static /* synthetic */ void m13728$r8$lambda$GfpACol8rJZ8dMwb_TCKsjZxwY(AnonymousClass1 r0) {
            r0.lambda$onSizeChanged$0();
        }

        public AnonymousClass1(PollAddOptionFieldLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        private /* synthetic */ void lambda$onSizeChanged$0() {
            this.this$0.updateCell();
        }

        @Override // org.telegram.ui.Components.EditTextEffects
        public int emojiCacheType() {
            return 3;
        }

        @Override // org.telegram.ui.Components.EditTextCaption, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo r3) {
            InputConnection r2 = super.onCreateInputConnection(r3);
            r3.imeOptions &= -1073741825;
            return r2;
        }

        @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            postOnAnimation(new PollAddOptionFieldLayout$1$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.PollAddOptionFieldLayout$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcherImpl {
        final /* synthetic */ PollAddOptionFieldLayout this$0;

        public AnonymousClass2(PollAddOptionFieldLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            PollAddOptionFieldLayout.m13727$$Nest$mcheckTextLengthLimit(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.poll.PollAddOptionFieldLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ PollAddOptionFieldLayout this$0;

        public AnonymousClass3(PollAddOptionFieldLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }
    }

    public static final class EmojiButton extends View {
        private final BoolAnimator animatorIsEmojiVisible;
        private final Drawable emojiDrawable;
        private final Drawable keyboardDrawable;

        /* renamed from: -$$Nest$fgetanimatorIsEmojiVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BoolAnimator m13729$$Nest$fgetanimatorIsEmojiVisible(EmojiButton r0) {
            return r0.animatorIsEmojiVisible;
        }

        /* renamed from: -$$Nest$fgetemojiDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m13730$$Nest$fgetemojiDrawable(EmojiButton r0) {
            return r0.emojiDrawable;
        }

        /* renamed from: -$$Nest$fgetkeyboardDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m13731$$Nest$fgetkeyboardDrawable(EmojiButton r0) {
            return r0.keyboardDrawable;
        }

        public EmojiButton(Context r5) {
            super(r5);
            this.animatorIsEmojiVisible = new BoolAnimator(this, CubicBezierInterpolator.EASE_OUT_QUINT, 320);
            this.emojiDrawable = r5.getResources().getDrawable(R.drawable.outline_poll_emoji_24).mutate();
            this.keyboardDrawable = r5.getResources().getDrawable(R.drawable.input_keyboard).mutate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            super.onDraw(r4);
            float r0 = this.animatorIsEmojiVisible.getFloatValue();
            DrawableUtils.drawWithScale(r4, this.emojiDrawable, 1.0f - r0);
            DrawableUtils.drawWithScale(r4, this.keyboardDrawable, r0);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            float r12 = r1 / 2.0f;
            float r22 = r2 / 2.0f;
            DrawableUtils.setBounds(this.emojiDrawable, r12, r22, 17);
            DrawableUtils.setBounds(this.keyboardDrawable, r12, r22, 17);
        }
    }

    public static class ViewWrapper extends FrameLayout {
        public ViewWrapper(Context r1) {
            super(r1);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return super.drawChild(r1, r2, r3);
        }

        public void drawInCell(Canvas r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$33Vpsz0jCJKF2D2jeEzFr8N6j5E(PollAddOptionFieldLayout r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.checkLimitText(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$EUzUKA-smW2oUTbYat30Gid7ZAQ, reason: not valid java name */
    public static /* synthetic */ void m13725$r8$lambda$EUzUKAsmW2oUTbYat30Gid7ZAQ(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$a1qlypusK4e4Hq0gEH2rXxAnHp0(PollAddOptionFieldLayout r0, BaseFragment r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cSpreHSkEgUUXRkQaFsMusp2QSE(PollAddOptionFieldLayout r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$y-y4giRtjBBS4fP1ocx-hB3Wthg, reason: not valid java name */
    public static /* synthetic */ void m13726$r8$lambda$yy4giRtjBBS4fP1ocxhB3Wthg(PollAddOptionFieldLayout r0, PollAttachedMedia r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$mcheckTextLengthLimit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13727$$Nest$mcheckTextLengthLimit(PollAddOptionFieldLayout r0) {
        r0.checkTextLengthLimit();
    }

    public PollAddOptionFieldLayout(BaseFragment r13, Context r14, Theme.ResourcesProvider r15) {
        super(r14);
        FrameLayout.LayoutParams r0 = new FrameLayout.LayoutParams(0, -2);
        this.lp = r0;
        this.cords = new int[2];
        this.rect = new Rect();
        PollAddOptionFieldLayout$$ExternalSyntheticLambda1 r4 = new PollAddOptionFieldLayout$$ExternalSyntheticLambda1(this);
        CubicBezierInterpolator r5 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorTextWarnVisibility = new BoolAnimator(0, r4, r5, 380);
        this.animatorTextErrorVisibility = new BoolAnimator(0, new PollAddOptionFieldLayout$$ExternalSyntheticLambda1(this), r5, 380);
        this.fragment = r13;
        this.maxLength = r13.getMessagesController().config.pollAnswerLengthMax.get();
        AnonymousClass1 r1 = new AnonymousClass1(this, r14, r15);
        this.textView = r1;
        r1.setAllowTextEntitiesIntersection(true);
        r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r15));
        r1.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r15));
        r1.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r15));
        r1.setHint(LocaleController.getString(R.string.PollAddAnOptionHint));
        r1.setTextSize(1, 15.0f);
        r1.setMaxLines(Integer.MAX_VALUE);
        r1.setBackground(null);
        r1.setImeOptions(268435462);
        r1.setInputType(r1.getInputType() | 16384);
        r1.addTextChangedListener(new AnonymousClass2(this));
        EmojiButton r2 = new EmojiButton(r14);
        this.emojiButton = r2;
        int r3 = Theme.key_stickers_menuSelector;
        r2.setBackground(Theme.createSelectorDrawable(Theme.getColor(r3, r15)));
        ScaleStateListAnimator.apply(r2);
        PollAttachButton r42 = new PollAttachButton(getContext(), r15, 36);
        this.attachButton = r42;
        r42.setBackground(Theme.createSelectorDrawable(Theme.getColor(r3, r15)));
        r42.setOnClickListener(new PollAddOptionFieldLayout$$ExternalSyntheticLambda2(this, r13));
        ScaleStateListAnimator.apply(r42);
        SimpleTextView r132 = new SimpleTextView(getContext());
        this.limitTextView = r132;
        r132.setTextSize(13);
        r132.setGravity(17);
        r132.setTranslationY(AndroidUtilities.dp(44.0f));
        r132.setVisibility(8);
        ViewWrapper r152 = new ViewWrapper(r14);
        this.viewsContainerWrapper = r152;
        addView(r152, r0);
        AnonymousClass3 r153 = new AnonymousClass3(this, r14);
        this.viewsContainer = r153;
        this.viewsContainerWrapper.addView(r153, LayoutHelper.createFrame(-1, -2.0f));
        this.viewsContainer.addView(r132, LayoutHelper.createFrame(54, 24, 53));
        this.viewsContainer.addView(r2, LayoutHelper.createFrame(44, 44, 51));
        this.viewsContainer.addView(r42, LayoutHelper.createFrame(44, 44.0f, 53, 0.0f, 0.0f, 5.0f, 0.0f));
        this.viewsContainer.addView(r1, LayoutHelper.createFrame(-1, -2.0f, 119, 39.0f, 0.0f, 47.0f, 0.0f));
        r1.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(11.0f));
    }

    private void cancel() {
        Runnable r0 = this.onCancel;
        if (r0 == null) goto L6;
        r0.run();
        this.onCancel = null;
        return;
    }

    private void checkLimitText(int r1, float r2, float r3, FactorAnimator r4) {
        FragmentFloatingButton.setAnimatedVisibility(this.limitTextView, this.animatorTextWarnVisibility.getFloatValue());
        int r12 = ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.fragment.getResourceProvider()), Theme.getColor(Theme.key_text_RedRegular, this.fragment.getResourceProvider()), this.animatorTextErrorVisibility.getFloatValue());
        this.limitTextView.setTextColor(r12);
    }

    private void checkTextLengthLimit() {
        int r0 = this.textView.getText().length();
        BoolAnimator r1 = this.animatorTextWarnVisibility;
        boolean r3 = false;
        if (r0 <= ((this.maxLength * 7) / 10)) goto L5;
        boolean r2 = true;
    L6:
        r1.setValue(r2, true);
        BoolAnimator r12 = this.animatorTextErrorVisibility;
        if (r0 <= this.maxLength) goto L9;
        r3 = true;
    L9:
        r12.setValue(r3, true);
        this.limitTextView.setText(Integer.toString(this.maxLength - r0));
        return;
    L5:
        r2 = false;
        goto L6
    }

    private /* synthetic */ void lambda$new$0() {
        AndroidUtilities.showKeyboard(this.textView);
    }

    private /* synthetic */ void lambda$new$1(PollAttachedMedia r3) {
        this.attachedMedia = r3;
        this.attachButton.setAttachedMedia(r3, true);
        AndroidUtilities.runOnUIThread(new PollAddOptionFieldLayout$$ExternalSyntheticLambda4(this), 200);
    }

    private /* synthetic */ void lambda$new$2(BaseFragment r3, View r4) {
        ChatAttachAlertPollLayout.openPollAttachMenu(r3, ChatAttachAlertPollLayout.getStartLayoutForMedia(this.attachedMedia), ChatAttachAlertPollLayout.getAllowedLayoutsForIndex(0), new PollAddOptionFieldLayout$$ExternalSyntheticLambda3(this), null);
    }

    public void doOnCancel(Runnable r1) {
        this.onCancel = r1;
    }

    public void doOnEmojiClick(Runnable r2) {
        this.emojiButton.setOnClickListener(new PollAddOptionFieldLayout$$ExternalSyntheticLambda0(r2));
    }

    public void drawInCell(Canvas r1) {
        this.viewsContainerWrapper.drawInCell(r1);
    }

    public PollAttachedMedia getAttachedMedia() {
        return this.attachedMedia;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewTreeObserver r0 = getViewTreeObserver();
        this.observer = r0;
        r0.addOnPreDrawListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewTreeObserver r0 = this.observer;
        if (r0 != null) goto L5;
    L7:
        this.observer = null;
        super.onDetachedFromWindow();
        return;
    L5:
        if (r0.isAlive() == false) goto L7;
        this.observer.removeOnPreDrawListener(this);
        goto L7
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        ChatMessageCell r0 = this.cellToWatch;
        if (r0 != null) goto L5;
        return true;
    L5:
        int r02 = r0.getMessageObject().getId();
        if (this.cellToWatch.isAttachedToWindow() == true) goto L8;
    L17:
        cancel();
        return true;
    L8:
        if (this.messageIdToWatch != r02) goto L17;
        if (this.cellToWatch.getPollAddButtonBounds(this.rect) == false) goto L17;
        this.cellToWatch.getLocationInWindow(this.cords);
        int[] r03 = this.cords;
        int r3 = r03[0];
        int r4 = r03[1];
        getLocationInWindow(r03);
        int[] r04 = this.cords;
        this.rect.offset(r3 - r04[0], r4 - r04[1]);
        int r05 = this.rect.width();
        FrameLayout.LayoutParams r2 = this.lp;
        if (r2.width == r05) goto L15;
        r2.width = r05;
        this.viewsContainerWrapper.setLayoutParams(r2);
    L15:
        this.viewsContainerWrapper.setTranslationX(this.rect.left);
        this.viewsContainerWrapper.setTranslationY(this.rect.top + AndroidUtilities.dp(0.66f));
        return true;
    }

    public void setAnimatedVisibility(float r1) {
        this.viewsContainer.setAlpha(r1);
    }

    public void setCellToWatch(ChatMessageCell r1) {
        this.cellToWatch = r1;
        this.messageIdToWatch = r1.getMessageObject().getId();
    }

    public void setColor(int r3) {
        if (this.lastColor == r3) goto L6;
        this.lastColor = r3;
        PorterDuffColorFilter r0 = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
        EmojiButton.m13730$$Nest$fgetemojiDrawable(this.emojiButton).setColorFilter(r0);
        EmojiButton.m13731$$Nest$fgetkeyboardDrawable(this.emojiButton).setColorFilter(r0);
        this.attachButton.attachDrawable.setColorFilter(r0);
        this.textView.setCursorColor(r3);
        this.textView.setHandlesColor(r3);
        this.textView.setHintTextColor(r3);
        return;
    }

    public void setEmojiKeyboardVisible(boolean r1, boolean r2) {
        EmojiButton.m13729$$Nest$fgetanimatorIsEmojiVisible(this.emojiButton).setValue(r1, r2);
    }

    public void updateCell() {
        ChatMessageCell r0 = this.cellToWatch;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getDelegate() == null) goto L9;
        this.cellToWatch.getDelegate().forceUpdate(this.cellToWatch, false);
        return;
    }
}
