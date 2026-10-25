package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.TypefaceSpan;

/* loaded from: classes6.dex */
public abstract class EditEmojiTextCell extends FrameLayout {
    private boolean allowEntities;
    public boolean autofocused;
    public final EditTextEmoji editTextEmoji;
    private boolean focused;
    private final ImageView[] iconImageView;
    private boolean ignoreEditText;
    final AnimatedTextView.AnimatedTextDrawable limit;
    final AnimatedColor limitColor;
    private int limitCount;
    private int maxLength;
    private boolean needDivider;
    private boolean showLimitWhenEmpty;
    private boolean showLimitWhenFocused;
    private int showLimitWhenNear;

    /* renamed from: org.telegram.ui.Cells.EditEmojiTextCell$1, reason: invalid class name */
    public class AnonymousClass1 implements TextView.OnEditorActionListener {
        final /* synthetic */ EditEmojiTextCell this$0;
        final /* synthetic */ Runnable val$whenEnter;

        public AnonymousClass1(EditEmojiTextCell r1, Runnable r2) {
            this.this$0 = r1;
            this.val$whenEnter = r2;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 6) goto L6;
            this.val$whenEnter.run();
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Cells.EditEmojiTextCell$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextEmoji {
        final /* synthetic */ EditEmojiTextCell this$0;
        final /* synthetic */ boolean val$multiline;
        final /* synthetic */ Theme.ResourcesProvider val$resourceProvider;

        public AnonymousClass2(EditEmojiTextCell r1, Context r2, SizeNotifierFrameLayout r3, BaseFragment r4, int r5, boolean r6, Theme.ResourcesProvider r7, boolean r8) {
            this.this$0 = r1;
            this.val$resourceProvider = r7;
            this.val$multiline = r8;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public boolean allowEntities() {
            if (EditEmojiTextCell.m6922$$Nest$fgetallowEntities(this.this$0) == true) goto L5;
            return false;
        L5:
            if (super.allowEntities() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public int emojiCacheType() {
            return this.this$0.emojiCacheType();
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void extendActionMode(ActionMode r5, Menu r6) {
            if (r6.findItem(R.id.menu_bold) == null) goto L5;
            return;
        L5:
            r6.removeItem(android.R.id.shareText);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_spoiler, 6, LocaleController.getString(R.string.Spoiler));
            SpannableStringBuilder r4 = new SpannableStringBuilder(LocaleController.getString(R.string.Bold));
            r4.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r4.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_bold, 7, r4);
            SpannableStringBuilder r42 = new SpannableStringBuilder(LocaleController.getString(R.string.Italic));
            r42.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC)), 0, r42.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_italic, 8, r42);
            SpannableStringBuilder r43 = new SpannableStringBuilder(LocaleController.getString(R.string.Strike));
            TextStyleSpan.TextStyleRun r52 = new TextStyleSpan.TextStyleRun();
            r52.flags |= 8;
            r43.setSpan(new TextStyleSpan(r52), 0, r43.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_strike, 9, r43);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_regular, 10, LocaleController.getString(R.string.Regular));
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            r8.save();
            int r3 = 0;
            r8.clipRect(getScrollX() + getPaddingLeft(), 0, (getScrollX() + getWidth()) - getPaddingRight(), getHeight());
            super.onDraw(r8);
            r8.restore();
            EditEmojiTextCell r0 = this.this$0;
            AnimatedColor r1 = r0.limitColor;
            if (r1 == null) goto L9;
            AnimatedTextView.AnimatedTextDrawable r2 = r0.limit;
            if (EditEmojiTextCell.m6924$$Nest$fgetlimitCount(r0) > 0) goto L7;
            int r02 = Theme.key_text_RedRegular;
        L8:
            r2.setTextColor(r1.set(Theme.getColor(r02, this.val$resourceProvider)));
            goto L9
        L7:
            r02 = Theme.key_dialogSearchHint;
        L9:
            int r03 = Math.min(AndroidUtilities.dp(48.0f), getHeight());
            if (this.val$multiline == false) goto L12;
            float r12 = 0.0f;
        L13:
            AnimatedTextView.AnimatedTextDrawable r22 = this.this$0.limit;
            float r4 = getScrollX();
            float r5 = (getHeight() + r12) - r03;
            int r04 = getScrollX() + getWidth();
            if (this.val$multiline == true) goto L16;
            r3 = 44;
        L16:
            r22.setBounds(r4, r5, r04 - AndroidUtilities.dp(r3 + 12), r12 + getHeight());
            this.this$0.limit.draw(r8);
            return;
        L12:
            r12 = -AndroidUtilities.dp(1.0f);
            goto L13
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.this$0.limit) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Cells.EditEmojiTextCell$3, reason: invalid class name */
    public class AnonymousClass3 implements EditTextCaption.EditTextCaptionDelegate {
        final /* synthetic */ EditEmojiTextCell this$0;
        final /* synthetic */ EditTextCaption val$editText;

        public AnonymousClass3(EditEmojiTextCell r1, EditTextCaption r2) {
            this.this$0 = r1;
            this.val$editText = r2;
        }

        @Override // org.telegram.ui.Components.EditTextCaption.EditTextCaptionDelegate
        public void onSpansChanged() {
            this.this$0.onTextChanged(this.val$editText.getText());
        }
    }

    /* renamed from: org.telegram.ui.Cells.EditEmojiTextCell$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ EditEmojiTextCell this$0;
        final /* synthetic */ EditTextCaption val$editText;
        final /* synthetic */ int val$maxLength;
        final /* synthetic */ boolean val$multiline;

        public AnonymousClass4(EditEmojiTextCell r1, int r2, EditTextCaption r3, boolean r4) {
            this.this$0 = r1;
            this.val$maxLength = r2;
            this.val$editText = r3;
            this.val$multiline = r4;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            if (EditEmojiTextCell.m6923$$Nest$fgetignoreEditText(this.this$0) == true) goto L12;
            if (this.val$maxLength <= 0) goto L10;
            if (r4 == null) goto L10;
            if (r4.length() <= this.val$maxLength) goto L10;
            EditEmojiTextCell.m6927$$Nest$fputignoreEditText(this.this$0, true);
            this.val$editText.setText(r4.subSequence(0, this.val$maxLength));
            EditTextCaption r0 = this.val$editText;
            r0.setSelection(r0.length());
            EditEmojiTextCell.m6927$$Nest$fputignoreEditText(this.this$0, false);
        L10:
            this.this$0.onTextChanged(r4);
        L12:
            if (this.val$multiline == false) goto L16;
        L13:
            int r02 = r4.toString().indexOf("\n");
            if (r02 < 0) goto L16;
            r4.delete(r02, r02 + 1);
        L16:
            AnimatedTextView.AnimatedTextDrawable r42 = this.this$0.limit;
            if (r42 != null) goto L19;
            return;
        L19:
            if (this.val$maxLength <= 0) goto L24;
            r42.cancelAnimation();
            EditEmojiTextCell.m6928$$Nest$mupdateLimitText(this.this$0);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (EditEmojiTextCell.m6923$$Nest$fgetignoreEditText(this.this$0) == true) goto L6;
            this.this$0.autofocused = false;
            return;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Cells.EditEmojiTextCell$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnFocusChangeListener {
        final /* synthetic */ EditEmojiTextCell this$0;

        public AnonymousClass5(EditEmojiTextCell r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View r1, boolean r2) {
            EditEmojiTextCell.m6926$$Nest$fputfocused(this.this$0, r2);
            if (EditEmojiTextCell.m6925$$Nest$fgetshowLimitWhenFocused(this.this$0) == false) goto L5;
            EditEmojiTextCell.m6928$$Nest$mupdateLimitText(this.this$0);
        L5:
            this.this$0.onFocusChanged(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$w25SxXRyMeEuAwIZKaER23bXoiQ(EditEmojiTextCell r0) {
        r0.lambda$hideKeyboardOnEnter$0();
    }

    /* renamed from: -$$Nest$fgetallowEntities, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6922$$Nest$fgetallowEntities(EditEmojiTextCell r0) {
        return r0.allowEntities;
    }

    /* renamed from: -$$Nest$fgetignoreEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6923$$Nest$fgetignoreEditText(EditEmojiTextCell r0) {
        return r0.ignoreEditText;
    }

    /* renamed from: -$$Nest$fgetlimitCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6924$$Nest$fgetlimitCount(EditEmojiTextCell r0) {
        return r0.limitCount;
    }

    /* renamed from: -$$Nest$fgetshowLimitWhenFocused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6925$$Nest$fgetshowLimitWhenFocused(EditEmojiTextCell r0) {
        return r0.showLimitWhenFocused;
    }

    /* renamed from: -$$Nest$fputfocused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6926$$Nest$fputfocused(EditEmojiTextCell r0, boolean r1) {
        r0.focused = r1;
    }

    /* renamed from: -$$Nest$fputignoreEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6927$$Nest$fputignoreEditText(EditEmojiTextCell r0, boolean r1) {
        r0.ignoreEditText = r1;
    }

    /* renamed from: -$$Nest$mupdateLimitText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6928$$Nest$mupdateLimitText(EditEmojiTextCell r0) {
        r0.updateLimitText();
    }

    public EditEmojiTextCell(Context r16, SizeNotifierFrameLayout r17, String r18, boolean r19, int r20, int r21, Theme.ResourcesProvider r22) {
        super(r16);
        this.iconImageView = new ImageView[2];
        this.showLimitWhenNear = -1;
        this.allowEntities = true;
        int r12 = 0;
        AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.limit = r0;
        r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.setTextSize(AndroidUtilities.dp(15.33f));
        int r14 = 5;
        r0.setGravity(5);
        this.maxLength = r20;
        AnonymousClass2 r02 = new AnonymousClass2(this, r16, r17, null, r21, true, r22, r19);
        this.editTextEmoji = r02;
        EditTextCaption r2 = r02.getEditText();
        r2.setDelegate(new AnonymousClass3(this, r2));
        r02.setWillNotDraw(false);
        this.limitColor = new AnimatedColor(r02);
        r0.setCallback(r02);
        r2.setTextSize(1, 17.0f);
        r2.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r22));
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        r2.setTextColor(Theme.getColor(r3, r22));
        r2.setBackground(null);
        if (r19 == false) goto L5;
        r2.setMaxLines(5);
        r2.setSingleLine(false);
    L6:
        int r4 = r2.getPaddingLeft();
        int r5 = r2.getPaddingTop();
        if (r21 != 4) goto L9;
        float r6 = 0.0f;
    L13:
        r2.setPadding(r4, r5, AndroidUtilities.dp(r6), r2.getPaddingBottom());
        if (LocaleController.isRTL == true) goto L17;
        r14 = 3;
    L17:
        r2.setGravity(r14 | 48);
        if (r19 == false) goto L20;
        r12 = 131072;
    L20:
        r2.setInputType(r12 | 573441);
        r2.setRawInputType(573441);
        r2.setHint(r18);
        r2.setCursorColor(Theme.getColor(r3, r22));
        r2.setCursorSize(AndroidUtilities.dp(19.0f));
        r2.setCursorWidth(1.5f);
        r2.addTextChangedListener(new AnonymousClass4(this, r20, r2, r19));
        r2.setOnFocusChangeListener(new AnonymousClass5(this));
        addView(r02, LayoutHelper.createFrame(-1, -1, 48));
        updateLimitText();
        return;
    L9:
        if (r20 <= 0) goto L11;
        int r62 = 42;
    L12:
        r6 = r62 + 21;
        goto L13
    L11:
        r62 = 0;
        goto L12
    L5:
        r2.setMaxLines(1);
        r2.setSingleLine(true);
        goto L6
    }

    private /* synthetic */ void lambda$hideKeyboardOnEnter$0() {
        AndroidUtilities.hideKeyboard(this.editTextEmoji.getEditText());
    }

    private void updateLimitText() {
        EditTextEmoji r0 = this.editTextEmoji;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getEditText() == null) goto L26;
        this.limitCount = this.maxLength - getText().length();
        AnimatedTextView.AnimatedTextDrawable r02 = this.limit;
        boolean r1 = TextUtils.isEmpty(getText());
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == false) goto L12;
        if (this.showLimitWhenEmpty == true) goto L12;
    L23:
        r02.setText(r2);
        return;
    L12:
        if (this.showLimitWhenFocused == true) goto L14;
    L17:
        int r12 = this.showLimitWhenNear;
        if (r12 != (-1)) goto L20;
    L22:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        goto L23
    L20:
        if (this.limitCount <= r12) goto L22;
    L14:
        if (this.focused == false) goto L23;
        if (this.autofocused == true) goto L23;
    }

    public int emojiCacheType() {
        return AnimatedEmojiDrawable.getCacheTypeForEnterView();
    }

    public CharSequence getText() {
        return this.editTextEmoji.getText();
    }

    public void hideKeyboardOnEnter() {
        whenHitEnter(new EditEmojiTextCell$$ExternalSyntheticLambda0(this));
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        super.onDraw(r9);
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(22.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(22.0f);
        goto L7
    }

    public void onFocusChanged(boolean r1) {
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public void onTextChanged(CharSequence r1) {
    }

    public EditEmojiTextCell setAllowEntities(boolean r1) {
        this.allowEntities = r1;
        return this;
    }

    public void setDivider(boolean r1) {
        this.needDivider = r1;
        setWillNotDraw(!r1);
    }

    public void setEmojiViewCacheType(int r1) {
        this.editTextEmoji.setEmojiViewCacheType(r1);
    }

    public void setIcon(int r5, boolean r6) {
        this.iconImageView[r6 ? 1 : 0].setImageResource(r5);
        if (r6 == false) goto L5;
        ImageView[] r1 = this.iconImageView;
        ImageView r2 = r1[0];
        r1[0] = r1[1];
        r1[1] = r2;
    L5:
        AndroidUtilities.updateViewVisibilityAnimated(this.iconImageView[0], true, 0.5f, r6);
        AndroidUtilities.updateViewVisibilityAnimated(this.iconImageView[1], false, 0.5f, r6);
    }

    public void setOnChangeIconListener(View.OnClickListener r11) {
        this.editTextEmoji.setLayoutParams(LayoutHelper.createFrame(-1, -1.0f, 53, 44.0f, 0.0f, 0.0f, 0.0f));
        int r1 = 0;
    L3:
        ImageView[] r2 = this.iconImageView;
        if (r1 >= r2.length) goto L10;
        r2[r1] = new ImageView(getContext());
        this.iconImageView[r1].setFocusable(true);
        ImageView r22 = this.iconImageView[r1];
        if (r1 != 0) goto L8;
        int r3 = 0;
    L9:
        r22.setVisibility(r3);
        this.iconImageView[r1].setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector)));
        this.iconImageView[r1].setScaleType(ImageView.ScaleType.FIT_XY);
        int r23 = AndroidUtilities.dp(8.0f);
        this.iconImageView[r1].setPadding(r23, r23, r23, r23);
        this.iconImageView[r1].setOnClickListener(r11);
        this.iconImageView[r1].setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
        this.iconImageView[r1].setImportantForAccessibility(2);
        addView(this.iconImageView[r1], LayoutHelper.createFrame(48, 48.0f, 19, 12.0f, 0.0f, 8.0f, 0.0f));
        r1 = r1 + 1;
        goto L3
    L8:
        r3 = 8;
        goto L9
    }

    public void setShowLimitOnFocus(boolean r1) {
        this.showLimitWhenFocused = r1;
    }

    public void setShowLimitWhenEmpty(boolean r1) {
        this.showLimitWhenEmpty = r1;
        if (r1 == false) goto L6;
        updateLimitText();
        return;
    }

    public void setShowLimitWhenNear(int r1) {
        this.showLimitWhenNear = r1;
        updateLimitText();
    }

    public void setText(CharSequence r2) {
        this.ignoreEditText = true;
        this.editTextEmoji.setText(r2);
        EditTextEmoji r22 = this.editTextEmoji;
        r22.setSelection(r22.getText().length());
        this.ignoreEditText = false;
    }

    public void whenHitEnter(Runnable r3) {
        this.editTextEmoji.getEditText().setImeOptions(6);
        this.editTextEmoji.getEditText().setOnEditorActionListener(new AnonymousClass1(this, r3));
    }
}
