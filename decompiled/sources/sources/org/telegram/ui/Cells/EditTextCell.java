package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
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
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.TypefaceSpan;

/* loaded from: classes6.dex */
public class EditTextCell extends FrameLayout {
    public boolean autofocused;
    public final EditTextCaption editText;
    private boolean focused;
    private boolean ignoreEditText;
    AnimatedTextView.AnimatedTextDrawable limit;
    AnimatedColor limitColor;
    private int limitCount;
    private int maxLength;
    private boolean needDivider;
    private boolean showLimitWhenEmpty;
    private boolean showLimitWhenFocused;
    private int showLimitWhenNear;

    /* renamed from: org.telegram.ui.Cells.EditTextCell$1, reason: invalid class name */
    public class AnonymousClass1 implements TextView.OnEditorActionListener {
        final /* synthetic */ EditTextCell this$0;
        final /* synthetic */ Runnable val$whenEnter;

        public AnonymousClass1(EditTextCell r1, Runnable r2) {
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

    /* renamed from: org.telegram.ui.Cells.EditTextCell$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextCaption {
        final /* synthetic */ EditTextCell this$0;
        final /* synthetic */ boolean val$allowEntities;
        final /* synthetic */ int val$maxLength;
        final /* synthetic */ Theme.ResourcesProvider val$resourceProvider;

        public AnonymousClass2(EditTextCell r1, Context r2, Theme.ResourcesProvider r3, int r4, Theme.ResourcesProvider r5, boolean r6) {
            this.this$0 = r1;
            this.val$maxLength = r4;
            this.val$resourceProvider = r5;
            this.val$allowEntities = r6;
            super(r2, r3);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            EditTextCell r0 = this.this$0;
            AnimatedTextView.AnimatedTextDrawable r1 = r0.limit;
            AnimatedColor r2 = r0.limitColor;
            if (EditTextCell.m6930$$Nest$fgetlimitCount(r0) > 0) goto L5;
            int r02 = Theme.key_text_RedRegular;
        L6:
            r1.setTextColor(r2.set(Theme.getColor(r02, this.val$resourceProvider)));
            int r03 = Math.min(AndroidUtilities.dp(52.0f), getHeight());
            this.this$0.limit.setBounds(getScrollX(), getHeight() - r03, ((getScrollX() + getWidth()) - getPaddingRight()) + AndroidUtilities.dp(42.0f), getHeight());
            this.this$0.limit.draw(r6);
            return;
        L5:
            r02 = Theme.key_dialogSearchHint;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor
        public void extendActionMode(ActionMode r5, Menu r6) {
            if (this.val$allowEntities == true) goto L6;
            return;
        L6:
            if (r6.findItem(R.id.menu_bold) == null) goto L8;
            return;
        L8:
            r6.removeItem(android.R.id.shareText);
            SpannableStringBuilder r4 = new SpannableStringBuilder(LocaleController.getString(R.string.Bold));
            r4.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r4.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_bold, 6, r4);
            SpannableStringBuilder r42 = new SpannableStringBuilder(LocaleController.getString(R.string.Italic));
            r42.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC)), 0, r42.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_italic, 7, r42);
            SpannableStringBuilder r43 = new SpannableStringBuilder(LocaleController.getString(R.string.Strike));
            TextStyleSpan.TextStyleRun r52 = new TextStyleSpan.TextStyleRun();
            r52.flags |= 8;
            r43.setSpan(new TextStyleSpan(r52), 0, r43.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_strike, 8, r43);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_regular, 9, LocaleController.getString(R.string.Regular));
        }

        @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
        public void onDraw(Canvas r6) {
            r6.save();
            r6.clipRect(getScrollX() + getPaddingLeft(), getScrollY(), (getScrollX() + getWidth()) - getPaddingRight(), getScrollY() + getHeight());
            super.onDraw(r6);
            r6.restore();
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            super.onTextChanged(r1, r2, r3, r4);
            AnimatedTextView.AnimatedTextDrawable r12 = this.this$0.limit;
            if (r12 != null) goto L5;
            return;
        L5:
            if (this.val$maxLength <= 0) goto L9;
            r12.cancelAnimation();
            EditTextCell.m6934$$Nest$mupdateLimitText(this.this$0);
            return;
        }

        @Override // android.widget.TextView, android.view.View
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

    /* renamed from: org.telegram.ui.Cells.EditTextCell$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ EditTextCell this$0;
        final /* synthetic */ int val$maxLength;
        final /* synthetic */ boolean val$multiline;

        public AnonymousClass3(EditTextCell r1, int r2, boolean r3) {
            this.this$0 = r1;
            this.val$maxLength = r2;
            this.val$multiline = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            if (EditTextCell.m6929$$Nest$fgetignoreEditText(this.this$0) == true) goto L12;
            if (this.val$maxLength <= 0) goto L10;
            if (r4 == null) goto L10;
            if (r4.length() <= this.val$maxLength) goto L10;
            EditTextCell.m6933$$Nest$fputignoreEditText(this.this$0, true);
            this.this$0.editText.setText(r4.subSequence(0, this.val$maxLength));
            EditTextCaption r0 = this.this$0.editText;
            r0.setSelection(r0.length());
            EditTextCell.m6933$$Nest$fputignoreEditText(this.this$0, false);
        L10:
            this.this$0.onTextChanged(r4);
        L12:
            if (this.val$multiline == true) goto L16;
        L13:
            int r3 = r4.toString().indexOf("\n");
            if (r3 < 0) goto L18;
            r4.delete(r3, r3 + 1);
            goto L13
        L18:
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (EditTextCell.m6929$$Nest$fgetignoreEditText(this.this$0) == true) goto L6;
            this.this$0.autofocused = false;
            return;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Cells.EditTextCell$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnFocusChangeListener {
        final /* synthetic */ EditTextCell this$0;

        public AnonymousClass4(EditTextCell r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View r1, boolean r2) {
            EditTextCell.m6932$$Nest$fputfocused(this.this$0, r2);
            if (EditTextCell.m6931$$Nest$fgetshowLimitWhenFocused(this.this$0) == false) goto L5;
            EditTextCell.m6934$$Nest$mupdateLimitText(this.this$0);
        L5:
            this.this$0.onFocusChanged(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$Wr0QRgH671vqGHiloVLgDUriMAk(EditTextCell r0) {
        r0.lambda$hideKeyboardOnEnter$0();
    }

    /* renamed from: -$$Nest$fgetignoreEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6929$$Nest$fgetignoreEditText(EditTextCell r0) {
        return r0.ignoreEditText;
    }

    /* renamed from: -$$Nest$fgetlimitCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6930$$Nest$fgetlimitCount(EditTextCell r0) {
        return r0.limitCount;
    }

    /* renamed from: -$$Nest$fgetshowLimitWhenFocused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6931$$Nest$fgetshowLimitWhenFocused(EditTextCell r0) {
        return r0.showLimitWhenFocused;
    }

    /* renamed from: -$$Nest$fputfocused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6932$$Nest$fputfocused(EditTextCell r0, boolean r1) {
        r0.focused = r1;
    }

    /* renamed from: -$$Nest$fputignoreEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6933$$Nest$fputignoreEditText(EditTextCell r0, boolean r1) {
        r0.ignoreEditText = r1;
    }

    /* renamed from: -$$Nest$mupdateLimitText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6934$$Nest$mupdateLimitText(EditTextCell r0) {
        r0.updateLimitText();
    }

    public EditTextCell(Context r17, String r18, boolean r19, boolean r20, int r21, Theme.ResourcesProvider r22) {
        super(r17);
        this.showLimitWhenNear = -1;
        this.limitColor = new AnimatedColor(this);
        AnimatedTextView.AnimatedTextDrawable r9 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.limit = r9;
        r9.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.limit.setTextSize(AndroidUtilities.dp(15.33f));
        int r92 = 5;
        this.limit.setGravity(5);
        this.maxLength = r21;
        int r10 = 0;
        AnonymousClass2 r0 = new AnonymousClass2(this, r17, r22, r21, r22, r20);
        this.editText = r0;
        this.limit.setCallback(r0);
        r0.setTextSize(1, 17.0f);
        r0.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r22));
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTextColor(Theme.getColor(r2, r22));
        r0.setBackground(null);
        if (r19 == false) goto L5;
        r0.setMaxLines(5);
        r0.setSingleLine(false);
    L6:
        int r5 = AndroidUtilities.dp(21.0f);
        int r11 = AndroidUtilities.dp(15.0f);
        if (r21 <= 0) goto L9;
        int r12 = 42;
    L10:
        r0.setPadding(r5, r11, AndroidUtilities.dp(r12 + 21), AndroidUtilities.dp(15.0f));
        if (LocaleController.isRTL == true) goto L14;
        r92 = 3;
    L14:
        r0.setGravity(r92 | 48);
        if (r19 == false) goto L17;
        int r93 = 131072;
    L18:
        r0.setInputType(r93 | 573441);
        if (r19 == false) goto L21;
        r10 = 131072;
    L21:
        r0.setRawInputType(r10 | 573441);
        r0.setHint(r18);
        r0.setCursorColor(Theme.getColor(r2, r22));
        r0.setCursorSize(AndroidUtilities.dp(19.0f));
        r0.setCursorWidth(1.5f);
        r0.addTextChangedListener(new AnonymousClass3(this, r21, r19));
        r0.setOnFocusChangeListener(new AnonymousClass4(this));
        addView(r0, LayoutHelper.createFrame(-1, -1, 48));
        updateLimitText();
        return;
    L17:
        r93 = 0;
        goto L18
    L9:
        r12 = 0;
        goto L10
    L5:
        r0.setMaxLines(1);
        r0.setSingleLine(true);
        goto L6
    }

    private /* synthetic */ void lambda$hideKeyboardOnEnter$0() {
        AndroidUtilities.hideKeyboard(this.editText);
    }

    private void updateLimitText() {
        if (this.editText != null) goto L5;
        return;
    L5:
        this.limitCount = this.maxLength - getText().length();
        AnimatedTextView.AnimatedTextDrawable r0 = this.limit;
        boolean r1 = TextUtils.isEmpty(getText());
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == false) goto L10;
        if (this.showLimitWhenEmpty == true) goto L10;
    L21:
        r0.setText(r2);
        return;
    L10:
        if (this.showLimitWhenFocused == true) goto L12;
    L15:
        int r12 = this.showLimitWhenNear;
        if (r12 != (-1)) goto L18;
    L20:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        goto L21
    L18:
        if (this.limitCount <= r12) goto L20;
    L12:
        if (this.focused == false) goto L21;
        if (this.autofocused == true) goto L21;
        goto L15
    }

    public CharSequence getText() {
        return this.editText.getText();
    }

    public TLRPC.TL_textWithEntities getTextWithEntities() {
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        CharSequence[] r2 = {getText()};
        r0.entities = MediaDataController.getInstance(UserConfig.selectedAccount).getEntities(r2, true);
        r0.text = r2[0].toString();
        return r0;
    }

    public void hideKeyboardOnEnter() {
        whenHitEnter(new EditTextCell$$ExternalSyntheticLambda0(this));
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

    public void onTextChanged(CharSequence r1) {
    }

    public void setDivider(boolean r1) {
        this.needDivider = r1;
        setWillNotDraw(!r1);
    }

    public ImageView setLeftDrawable(Drawable r10) {
        ImageView r0 = new ImageView(getContext());
        r0.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r0.setImageDrawable(r10);
        addView(r0, LayoutHelper.createFrame(24, 24.0f, 19, 18.0f, 0.0f, 0.0f, 0.0f));
        FrameLayout.LayoutParams r102 = (FrameLayout.LayoutParams) this.editText.getLayoutParams();
        r102.leftMargin = AndroidUtilities.dp(24.0f);
        this.editText.setLayoutParams(r102);
        return r0;
    }

    public void setMaxLength(int r1) {
        this.maxLength = r1;
        updateLimitText();
        this.editText.invalidate();
    }

    public void setMultiline(boolean r2) {
        EditTextCaption r0 = this.editText;
        if (r2 == false) goto L6;
        r0.setMaxLines(5);
        this.editText.setSingleLine(false);
        return;
    L6:
        r0.setMaxLines(1);
        this.editText.setSingleLine(true);
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
        this.editText.setText(r2);
        EditTextCaption r22 = this.editText;
        r22.setSelection(r22.getText().length());
        this.ignoreEditText = false;
    }

    public void setText(TLRPC.TL_textWithEntities r3) {
        this.ignoreEditText = true;
        this.editText.setText(MessageObject.formatTextWithEntities(r3, false));
        EditTextCaption r32 = this.editText;
        r32.setSelection(r32.getText().length());
        this.ignoreEditText = false;
    }

    public boolean validate() {
        if (this.maxLength >= 0) goto L5;
        return true;
    L5:
        if (this.editText.getText().length() <= this.maxLength) goto L11;
        return false;
    L11:
        return true;
    }

    public void whenHitEnter(Runnable r3) {
        this.editText.setImeOptions(6);
        this.editText.setOnEditorActionListener(new AnonymousClass1(this, r3));
    }
}
