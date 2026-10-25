package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SuggestEmojiView;
import org.telegram.ui.Components.poll.PollAttachButton;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class PollEditTextCell extends FrameLayout implements SuggestEmojiView.AnchorViewDelegate, FactorAnimator.Target {
    private boolean alwaysShowText2;
    private final BoolAnimator animatorCheckboxMultiselect;
    private final BoolAnimator animatorEmojiButtonVisible;
    public PollAttachButton attachView;
    private CheckBox2 checkBox;
    private AnimatorSet checkBoxAnimation;
    public ImageView deleteImageView;
    private ChatActivityEnterViewAnimatedIconView emojiButton;
    public ImageView moveImageView;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private Integer right;
    private boolean showNextButton;
    public EditTextBoldCursor textView;
    private SimpleTextView textView2;

    /* renamed from: org.telegram.ui.Cells.PollEditTextCell$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextCaption {
        final /* synthetic */ PollEditTextCell this$0;

        public AnonymousClass1(PollEditTextCell r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.EditTextEffects
        public int emojiCacheType() {
            return 3;
        }

        @Override // org.telegram.ui.Components.EditTextCaption, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo r3) {
            InputConnection r0 = super.onCreateInputConnection(r3);
            if (PollEditTextCell.m7001$$Nest$fgetshowNextButton(this.this$0) == false) goto L5;
            r3.imeOptions &= -1073741825;
        L5:
            return r0;
        }

        @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
        public void onDraw(Canvas r2) {
            super.onDraw(r2);
            this.this$0.onEditTextDraw(this, r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean r1, int r2, Rect r3) {
            super.onFocusChanged(r1, r2, r3);
            this.this$0.onEditTextFocusChanged(r1);
        }

        @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
        public boolean onTextContextMenuItem(int r9) {
            if (r9 != 16908322) goto L25;
            ClipData r0 = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip();
            if (r0 == null) goto L25;
            if (r0.getItemCount() != 1) goto L25;
            if (AndroidUtilities.charSequenceIndexOf(r0.getItemAt(0).getText(), "\n") <= 0) goto L25;
            CharSequence r02 = r0.getItemAt(0).getText();
            ArrayList<CharSequence> r3 = new ArrayList();
            StringBuilder r4 = new StringBuilder();
            int r5 = 0;
        L12:
            if (r5 >= r02.length()) goto L19;
            char r6 = r02.charAt(r5);
            if (r6 != '\n') goto L16;
            r3.add(r4.toString());
            r4.setLength(0);
        L17:
            r5 = r5 + 1;
            goto L12
        L16:
            r4.append(r6);
            goto L17
        L19:
            if (TextUtils.isEmpty(r4) == true) goto L22;
            r3.add(r4);
        L22:
            if (this.this$0.onPastedMultipleLines(r3) == false) goto L25;
            return true;
        L25:
            return super.onTextContextMenuItem(r9);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (isEnabled() == true) goto L7;
            return false;
        L7:
            if (r3.getAction() != 1) goto L10;
            this.this$0.onFieldTouchUp(this);
        L10:
            return super.onTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, android.view.View
        public ActionMode startActionMode(ActionMode.Callback r2) {
            ActionMode r22 = super.startActionMode(r2);
            this.this$0.onActionModeStart(this, r22);
            return r22;
        }

        @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, android.view.View
        public ActionMode startActionMode(ActionMode.Callback r1, int r2) {
            ActionMode r12 = super.startActionMode(r1, r2);
            this.this$0.onActionModeStart(this, r12);
            return r12;
        }
    }

    /* renamed from: $r8$lambda$BR-nB7GoqS3KmY3I6yfDV7ytna8, reason: not valid java name */
    public static /* synthetic */ void m7000$r8$lambda$BRnB7GoqS3KmY3I6yfDV7ytna8(PollEditTextCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$l6z6lGUq3GgN1lEWzelz5aBxHsU(PollEditTextCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetshowNextButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7001$$Nest$fgetshowNextButton(PollEditTextCell r0) {
        return r0.showNextButton;
    }

    public PollEditTextCell(Context r2, View.OnClickListener r3) {
        this(r2, false, 0, r3);
    }

    public PollEditTextCell(Context r7, boolean r8, int r9, View.OnClickListener r10) {
        this(r7, r8, r9, r10, null);
    }

    public PollEditTextCell(Context r23, boolean r24, int r25, View.OnClickListener r26, Theme.ResourcesProvider r27) {
        super(r23);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorCheckboxMultiselect = new BoolAnimator(0, this, r3, 380);
        this.animatorEmojiButtonVisible = new BoolAnimator(1, this, r3, 380);
        this.resourcesProvider = r27;
        AnonymousClass1 r0 = new AnonymousClass1(this, r23, r27);
        this.textView = r0;
        r0.setAllowTextEntitiesIntersection(true);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r27));
        this.textView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r27));
        this.textView.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r27));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setMaxLines(Integer.MAX_VALUE);
        this.textView.setBackground(null);
        EditTextBoldCursor r02 = this.textView;
        r02.setImeOptions(r02.getImeOptions() | 268435456);
        EditTextBoldCursor r03 = this.textView;
        r03.setInputType(r03.getInputType() | 16384);
        this.textView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(11.0f));
        float r4 = 0.0f;
        if (r26 == null) goto L52;
        if (r25 != 1) goto L6;
        int r11 = 92;
    L7:
        EditTextBoldCursor r12 = this.textView;
        boolean r13 = LocaleController.isRTL;
        if (r13 == false) goto L10;
        int r14 = 5;
    L11:
        int r17 = r14 | 16;
        float r142 = 54.0f;
        if (r13 == false) goto L14;
        float r18 = r11;
    L15:
        if (r13 == true) goto L17;
        r142 = r11;
    L17:
        addView(r12, LayoutHelper.createFrame(-1, -2.0f, r17, r18, 0.0f, r142, 0.0f));
        ImageView r112 = new ImageView(r23);
        this.moveImageView = r112;
        r112.setFocusable(false);
        ImageView r113 = this.moveImageView;
        ImageView.ScaleType r122 = ImageView.ScaleType.CENTER;
        r113.setScaleType(r122);
        this.moveImageView.setImageResource(R.drawable.menu_poll_order_24);
        ImageView r114 = this.moveImageView;
        int r143 = Theme.key_windowBackgroundWhiteGrayIcon;
        int r15 = Theme.getColor(r143, r27);
        PorterDuff.Mode r5 = PorterDuff.Mode.MULTIPLY;
        r114.setColorFilter(new PorterDuffColorFilter(r15, r5));
        ImageView r115 = this.moveImageView;
        if (LocaleController.isRTL == false) goto L20;
        int r132 = 5;
    L21:
        addView(r115, LayoutHelper.createFrame(48, 48.0f, r132 | 48, 6.0f, 2.0f, 6.0f, 0.0f));
        ImageView r116 = new ImageView(r23);
        this.deleteImageView = r116;
        r116.setFocusable(false);
        this.deleteImageView.setScaleType(r122);
        this.deleteImageView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector, r27)));
        this.deleteImageView.setImageResource(R.drawable.poll_remove);
        this.deleteImageView.setOnClickListener(r26);
        this.deleteImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r143, r27), r5));
        this.deleteImageView.setContentDescription(LocaleController.getString(R.string.Delete));
        ImageView r52 = this.deleteImageView;
        boolean r8 = LocaleController.isRTL;
        if (r8 == false) goto L24;
        int r117 = 3;
    L25:
        int r172 = r117 | 48;
        if (r8 == false) goto L28;
        float r182 = 3.0f;
    L29:
        if (r8 == false) goto L31;
        float r20 = 0.0f;
    L32:
        addView(r52, LayoutHelper.createFrame(48, 50.0f, r172, r182, 0.0f, r20, 0.0f));
        SimpleTextView r53 = new SimpleTextView(r23);
        this.textView2 = r53;
        r53.setTextSize(13);
        SimpleTextView r54 = this.textView2;
        if (LocaleController.isRTL == false) goto L35;
        int r82 = 3;
    L36:
        r54.setGravity(r82 | 48);
        SimpleTextView r55 = this.textView2;
        boolean r83 = LocaleController.isRTL;
        if (r83 == false) goto L39;
        int r118 = 3;
    L40:
        int r173 = r118 | 48;
        if (r83 == false) goto L43;
        float r183 = 20.0f;
    L44:
        if (r83 == false) goto L46;
        float r202 = 0.0f;
    L47:
        addView(r55, LayoutHelper.createFrame(48, 24.0f, r173, r183, 43.0f, r202, 0.0f));
        CheckBox2 r56 = new CheckBox2(r23, 21, r27);
        this.checkBox = r56;
        r56.setColor(-1, r143, Theme.key_checkboxCheck);
        this.checkBox.setContentDescription(LocaleController.getString(R.string.AccDescrQuizCorrectAnswer));
        this.checkBox.setDrawUnchecked(true);
        this.checkBox.setChecked(true, false);
        this.checkBox.setAlpha(0.0f);
        this.checkBox.setDrawBackgroundAsArc(8);
        CheckBox2 r57 = this.checkBox;
        if (LocaleController.isRTL == false) goto L50;
        int r84 = 5;
    L51:
        addView(r57, LayoutHelper.createFrame(48, 48.0f, r84 | 48, 6.0f, 2.0f, 6.0f, 0.0f));
        this.checkBox.setOnClickListener(new PollEditTextCell$$ExternalSyntheticLambda0(this));
    L67:
        if (r25 != 1) goto L84;
        ChatActivityEnterViewAnimatedIconView r1 = new ChatActivityEnterViewAnimatedIconView(r23);
        this.emojiButton = r1;
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, r27), PorterDuff.Mode.SRC_IN));
        this.emojiButton.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, false);
        int r16 = AndroidUtilities.dp(9.5f);
        this.emojiButton.setPadding(r16, r16, r16, r16);
        this.emojiButton.setVisibility(8);
        if (this.deleteImageView != null) goto L71;
        int r04 = 3;
    L72:
        ChatActivityEnterViewAnimatedIconView r19 = this.emojiButton;
        boolean r32 = LocaleController.isRTL;
        if (r32 == false) goto L75;
        int r133 = 3;
    L76:
        if (r32 == false) goto L78;
        float r144 = r04;
    L79:
        if (r32 == true) goto L82;
        r4 = r04;
    L82:
        addView(r19, LayoutHelper.createFrame(48, 48.0f, r133, r144, 0.0f, r4, 0.0f));
        this.emojiButton.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector, r27)));
        this.emojiButton.setOnClickListener(new PollEditTextCell$$ExternalSyntheticLambda1(this));
        this.emojiButton.setContentDescription(LocaleController.getString(R.string.Emoji));
        return;
    L78:
        r144 = 0.0f;
        goto L79
    L75:
        r133 = 5;
        goto L76
    L71:
        r04 = 38;
        goto L72
    L84:
        return;
    L50:
        r84 = 3;
        goto L51
    L46:
        r202 = 20.0f;
        goto L47
    L43:
        r183 = 0.0f;
        goto L44
    L39:
        r118 = 5;
        goto L40
    L35:
        r82 = 5;
        goto L36
    L31:
        r20 = 3.0f;
        goto L32
    L28:
        r182 = 0.0f;
        goto L29
    L24:
        r117 = 5;
        goto L25
    L20:
        r132 = 3;
        goto L21
    L14:
        r18 = 54.0f;
        goto L15
    L10:
        r14 = 3;
        goto L11
    L6:
        r11 = 58;
        goto L7
    L52:
        if (r25 != 1) goto L54;
        int r58 = 70;
    L55:
        EditTextBoldCursor r85 = this.textView;
        boolean r119 = LocaleController.isRTL;
        if (r119 == false) goto L58;
        int r123 = 5;
    L59:
        int r152 = r123 | 16;
        float r124 = 19.0f;
        if (r119 == false) goto L62;
        float r162 = r58;
    L63:
        if (r119 == true) goto L66;
        r124 = r58;
    L66:
        addView(r85, LayoutHelper.createFrame(-1, -2.0f, r152, r162, 0.0f, r124, 0.0f));
        goto L67
    L62:
        r162 = 19.0f;
        goto L63
    L58:
        r123 = 3;
        goto L59
    L54:
        r58 = 19;
        goto L55
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        if (this.checkBox.getTag() != null) goto L5;
        return;
    L5:
        onCheckBoxClick(this, !this.checkBox.isChecked());
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        onEmojiButtonClicked(this);
    }

    public View addAttachView() {
        ImageView r0 = this.deleteImageView;
        if (r0 == null) goto L5;
        r0.setVisibility(8);
    L5:
        PollAttachButton r02 = new PollAttachButton(getContext(), this.resourcesProvider);
        this.attachView = r02;
        r02.setFocusable(false);
        this.attachView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector, this.resourcesProvider)));
        ScaleStateListAnimator.apply(this.attachView);
        PollAttachButton r03 = this.attachView;
        boolean r1 = LocaleController.isRTL;
        int r2 = 5;
        if (r1 == false) goto L8;
        int r4 = 3;
    L9:
        int r7 = r4 | 48;
        if (r1 == false) goto L12;
        float r8 = 4.0f;
    L13:
        if (r1 == false) goto L15;
        float r10 = 0.0f;
    L16:
        addView(r03, LayoutHelper.createFrame(48, 50.0f, r7, r8, 0.0f, r10, 0.0f));
        ChatActivityEnterViewAnimatedIconView r04 = this.emojiButton;
        if (r04 == null) goto L30;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L21;
        int r42 = 3;
    L22:
        int r72 = r42 | 48;
        if (r12 == false) goto L25;
        float r82 = 44.0f;
    L26:
        if (r12 == false) goto L28;
        float r102 = 0.0f;
    L29:
        r04.setLayoutParams(LayoutHelper.createFrame(48, 48.0f, r72, r82, 1.0f, r102, 0.0f));
        goto L30
    L28:
        r102 = 44.0f;
        goto L29
    L25:
        r82 = 0.0f;
        goto L26
    L21:
        r42 = 5;
    L30:
        EditTextBoldCursor r05 = this.textView;
        if (r05 == null) goto L52;
        if (LocaleController.isRTL == false) goto L35;
        int r06 = ((ViewGroup.MarginLayoutParams) r05.getLayoutParams()).rightMargin;
    L36:
        float r07 = r06 / AndroidUtilities.density;
        if (this.emojiButton == null) goto L39;
        int r13 = 70;
    L40:
        int r14 = r13 + 24;
        EditTextBoldCursor r43 = this.textView;
        boolean r5 = LocaleController.isRTL;
        if (r5 == true) goto L44;
        r2 = 3;
    L44:
        int r83 = r2 | 16;
        if (r5 == false) goto L47;
        float r9 = r14;
    L48:
        if (r5 == true) goto L50;
        r07 = r14;
    L50:
        r43.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r83, r9, 0.0f, r07, 0.0f));
        goto L52
    L47:
        r9 = r07;
        goto L48
    L39:
        r13 = 19;
        goto L40
    L35:
        r06 = ((ViewGroup.MarginLayoutParams) r05.getLayoutParams()).leftMargin;
    L52:
        return this.attachView;
    L15:
        r10 = 4.0f;
        goto L16
    L12:
        r8 = 0.0f;
        goto L13
    L8:
        r4 = 5;
        goto L9
    }

    @Override // org.telegram.ui.Components.SuggestEmojiView.AnchorViewDelegate
    public void addTextChangedListener(TextWatcher r1) {
        this.textView.addTextChangedListener(r1);
    }

    public void addTextWatcher(TextWatcher r1) {
        this.textView.addTextChangedListener(r1);
    }

    public void callOnDelete() {
        ImageView r0 = this.deleteImageView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.callOnClick();
    }

    public void createErrorTextView() {
        this.alwaysShowText2 = true;
        SimpleTextView r0 = new SimpleTextView(getContext());
        this.textView2 = r0;
        r0.setTextSize(13);
        SimpleTextView r02 = this.textView2;
        int r2 = 5;
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 3;
    L6:
        r02.setGravity(r1 | 48);
        SimpleTextView r03 = this.textView2;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L9;
        r2 = 3;
    L9:
        int r5 = r2 | 48;
        if (r12 == false) goto L12;
        float r6 = 20.0f;
    L13:
        if (r12 == false) goto L15;
        float r8 = 0.0f;
    L16:
        addView(r03, LayoutHelper.createFrame(48, 24.0f, r5, r6, 17.0f, r8, 0.0f));
        return;
    L15:
        r8 = 20.0f;
        goto L16
    L12:
        r6 = 0.0f;
        goto L13
    L5:
        r1 = 5;
        goto L6
    }

    public boolean drawDivider() {
        return true;
    }

    public CheckBox2 getCheckBox() {
        return this.checkBox;
    }

    @Override // org.telegram.ui.Components.SuggestEmojiView.AnchorViewDelegate
    public EditTextBoldCursor getEditField() {
        return this.textView;
    }

    @Override // org.telegram.ui.Components.SuggestEmojiView.AnchorViewDelegate
    public Editable getEditText() {
        return this.textView.getText();
    }

    public ChatActivityEnterViewAnimatedIconView getEmojiButton() {
        return this.emojiButton;
    }

    @Override // org.telegram.ui.Components.SuggestEmojiView.AnchorViewDelegate
    public CharSequence getFieldText() {
        if (this.textView.length() > 0) goto L5;
        return null;
    L5:
        return this.textView.getText();
    }

    @Override // org.telegram.ui.Components.SuggestEmojiView.AnchorViewDelegate
    public BaseFragment getParentFragment() {
        return null;
    }

    public String getText() {
        return this.textView.getText().toString();
    }

    public EditTextBoldCursor getTextView() {
        return this.textView;
    }

    public SimpleTextView getTextView2() {
        return this.textView2;
    }

    public boolean isChecked(PollEditTextCell r1) {
        return false;
    }

    public void onActionModeStart(EditTextBoldCursor r1, ActionMode r2) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.checkBox == null) goto L6;
        setShowCheckBox(shouldShowCheckBox(), false);
        this.checkBox.setChecked(isChecked(this), false);
        return;
    }

    public void onCheckBoxClick(PollEditTextCell r1, boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (drawDivider() == false) goto L25;
        float r1 = 20.0f;
        if (LocaleController.isRTL == false) goto L11;
        float r0 = 0.0f;
    L9:
        float r4 = r0;
        float r5 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == true) goto L18;
        int r12 = 0;
    L22:
        r10.drawLine(r4, r5, r02 - r12, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L18:
        if (this.moveImageView == null) goto L20;
        r1 = 58.0f;
    L20:
        r12 = AndroidUtilities.dp(r1);
        goto L22
    L11:
        if (this.moveImageView == null) goto L13;
        float r03 = 58.0f;
    L14:
        r0 = AndroidUtilities.dp(r03);
        goto L9
    L13:
        r03 = 20.0f;
        goto L14
    }

    public void onEditTextDraw(EditTextBoldCursor r1, Canvas r2) {
    }

    public void onEditTextFocusChanged(boolean r1) {
    }

    public void onEmojiButtonClicked(PollEditTextCell r1) {
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L8;
        CheckBox2 r12 = this.checkBox;
        if (r12 == null) goto L27;
        r12.getCheckBoxBase().setCustomRadiusFactor(this.animatorCheckboxMultiselect.getFloatValue());
        this.checkBox.invalidate();
        return;
    L27:
        return;
    L8:
        if (r1 == 1) goto L10;
        return;
    L10:
        if (this.emojiButton == null) goto L29;
        float r13 = this.animatorEmojiButtonVisible.getFloatValue();
        float r32 = 0.85f * r13;
        this.emojiButton.setScaleX(r32);
        this.emojiButton.setScaleY(r32);
        this.emojiButton.setAlpha(r13);
        ChatActivityEnterViewAnimatedIconView r22 = this.emojiButton;
        if (r13 <= 0.0f) goto L14;
        int r33 = 0;
    L15:
        r22.setVisibility(r33);
        SimpleTextView r23 = this.textView2;
        if (r23 != null) goto L18;
        return;
    L18:
        if (this.deleteImageView == null) goto L20;
        return;
    L20:
        if (r23.getVisibility() != 0) goto L32;
        PollAttachButton r24 = this.attachView;
        SimpleTextView r0 = this.textView2;
        if (r24 == null) goto L25;
        r0.setTranslationY(AndroidUtilities.dp(36.0f));
        return;
    L25:
        r0.setTranslationY(AndroidUtilities.dp(26.0f) * r13);
        return;
    L32:
        return;
    L14:
        r33 = 8;
        goto L15
    }

    public void onFieldTouchUp(EditTextBoldCursor r1) {
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        int r0 = View.MeasureSpec.getSize(r8);
        int r2 = 0;
    L4:
        if (r2 >= getChildCount()) goto L28;
        View r3 = getChildAt(r2);
        if (r3 == this.textView) goto L27;
        ImageView r5 = this.deleteImageView;
        if (r3 != r5) goto L11;
        r5.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        goto L27
    L11:
        ChatActivityEnterViewAnimatedIconView r52 = this.emojiButton;
        if (r3 != r52) goto L14;
        r52.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        goto L27
    L14:
        ImageView r53 = this.moveImageView;
        if (r3 != r53) goto L17;
        r53.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        goto L27
    L17:
        SimpleTextView r54 = this.textView2;
        if (r3 != r54) goto L20;
        r54.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
        goto L27
    L20:
        CheckBox2 r55 = this.checkBox;
        if (r3 != r55) goto L23;
        r55.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        goto L27
    L23:
        ViewGroup.LayoutParams r56 = r3.getLayoutParams();
        if (r56 == null) goto L26;
        r3.measure(View.MeasureSpec.makeMeasureSpec(r56.width, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r56.height, TLObject.FLAG_30));
        goto L27
    L26:
        r3.measure(r8, r9);
    L27:
        r2 = r2 + 1;
        goto L4
    L28:
        Integer r82 = this.right;
        if (r82 == null) goto L32;
        int r83 = r82.intValue();
    L41:
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(((r0 - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(r83), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        int r84 = this.textView.getMeasuredHeight();
        setMeasuredDimension(r0, Math.max(AndroidUtilities.dp(50.0f), this.textView.getMeasuredHeight()) + (this.needDivider ? 1 : 0));
        SimpleTextView r92 = this.textView2;
        if (r92 != null) goto L44;
        return;
    L44:
        if (this.alwaysShowText2 == false) goto L46;
        return;
    L46:
        if (r84 < AndroidUtilities.dp(52.0f)) goto L48;
        float r7 = 1.0f;
    L49:
        r92.setAlpha(r7);
        return;
    L48:
        r7 = 0.0f;
        goto L49
    L32:
        if (this.textView2 != null) goto L35;
        r83 = 42;
        goto L41
    L35:
        if (this.deleteImageView != null) goto L38;
        r83 = 70;
        goto L41
    L38:
        if (this.emojiButton == null) goto L40;
        r83 = 144;
        goto L41
    L40:
        r83 = 122;
        goto L41
    }

    public boolean onPastedMultipleLines(ArrayList<CharSequence> r1) {
        return false;
    }

    public void setCheckboxMultiselect(boolean r1, boolean r2) {
        this.animatorCheckboxMultiselect.setValue(r1, r2);
    }

    public void setChecked(boolean r1, boolean r2) {
        this.checkBox.setChecked(r1, r2);
    }

    public void setEmojiButtonVisibility(boolean r2) {
        this.animatorEmojiButtonVisible.setValue(r2, true);
    }

    @Override // org.telegram.ui.Components.SuggestEmojiView.AnchorViewDelegate
    public void setFieldText(CharSequence r1) {
        this.textView.setText(r1);
    }

    public void setIconsColor(int r5) {
        ImageView r0 = this.moveImageView;
        if (r0 == null) goto L5;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
    L5:
        ImageView r02 = this.deleteImageView;
        if (r02 == null) goto L8;
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
    L8:
        ChatActivityEnterViewAnimatedIconView r03 = this.emojiButton;
        if (r03 == null) goto L12;
        r03.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        return;
    }

    public void setShowCheckBox(boolean r8, boolean r9) {
        if (this.checkBox.getTag() == null) goto L5;
        boolean r0 = true;
    L6:
        if (r8 != r0) goto L8;
        return;
    L8:
        AnimatorSet r02 = this.checkBoxAnimation;
        Integer r3 = null;
        if (r02 == null) goto L11;
        r02.cancel();
        this.checkBoxAnimation = null;
    L11:
        CheckBox2 r03 = this.checkBox;
        if (r8 == false) goto L14;
        r3 = 1;
    L14:
        r03.setTag(r3);
        float r04 = 0.0f;
        if (r9 == false) goto L26;
        AnimatorSet r92 = new AnimatorSet();
        this.checkBoxAnimation = r92;
        CheckBox2 r4 = this.checkBox;
        if (r8 == false) goto L19;
        float r5 = 1.0f;
    L20:
        float[] r6 = {r5};
        Property r52 = View.ALPHA;
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r4, r52, r6);
        ImageView r62 = this.moveImageView;
        if (r8 == true) goto L24;
        r04 = 1.0f;
    L24:
        r92.playTogether(new Animator[]{r42, ObjectAnimator.ofFloat(r62, r52, new float[]{r04})});
        this.checkBoxAnimation.setDuration(180);
        this.checkBoxAnimation.start();
        return;
    L19:
        r5 = 0.0f;
        goto L20
    L26:
        CheckBox2 r93 = this.checkBox;
        if (r8 == false) goto L29;
        float r1 = 1.0f;
    L30:
        r93.setAlpha(r1);
        ImageView r7 = this.moveImageView;
        if (r8 == true) goto L34;
        r04 = 1.0f;
    L34:
        r7.setAlpha(r04);
        return;
    L29:
        r1 = 0.0f;
        goto L30
    L5:
        r0 = false;
        goto L6
    }

    public void setShowNextButton(boolean r1) {
        this.showNextButton = r1;
    }

    public void setText2(String r1) {
        SimpleTextView r0 = this.textView2;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setText(r1);
    }

    public void setTextAndHint(CharSequence r3, String r4, boolean r5) {
        ImageView r0 = this.deleteImageView;
        if (r0 == null) goto L5;
        r0.setTag(null);
    L5:
        this.textView.setText(r3);
        if (TextUtils.isEmpty(r3) == true) goto L8;
        EditTextBoldCursor r32 = this.textView;
        r32.setSelection(r32.length());
    L8:
        this.textView.setHint(r4);
        this.needDivider = r5;
        setWillNotDraw(!r5);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }

    public void setTextRight(int r1) {
        this.right = Integer.valueOf(r1);
    }

    public boolean shouldShowCheckBox() {
        return false;
    }

    public void supportMultiselect() {
        CheckBox2 r0 = this.checkBox;
        if (r0 == null) goto L6;
        r0.getCheckBoxBase().setCustomRadius(AndroidUtilities.dp(6.0f));
        this.checkBox.getCheckBoxBase().setCustomRadiusFactor(this.animatorCheckboxMultiselect.getFloatValue());
        return;
    }
}
