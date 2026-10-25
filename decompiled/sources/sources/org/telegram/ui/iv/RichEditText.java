package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.FloatingActionMode;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.URLSpanReplacement;

/* loaded from: classes7.dex */
public class RichEditText extends EditTextCaption {
    private boolean accentHint;
    private boolean allowNewlines;
    private boolean applyingEmptyHint;
    private boolean autoBold;
    public TL_iv.PageBlock block;
    private boolean centerEmptyHint;
    private int currentAccount;
    private boolean ignoreTextChange;
    private InlineButtonClickListener inlineButtonClickListener;
    private final Runnable inlineButtonLongPressRunnable;
    private boolean inlineButtonLongPressed;
    private boolean insertingNewline;
    private Layout lastMarkLayout;
    private int lastMarkTextLength;
    private Listener listener;
    private boolean locked;
    private final InputFilter lockingFilter;
    private Paint markPaint;
    private LinkPath markPath;
    private boolean markPathDirty;
    private long mathDownTime;
    private float mathDownX;
    private float mathDownY;
    private RichInlineButtonSpan pressedInlineButton;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean softEnterNewline;
    private int textColorKey;
    private int touchSlop;

    /* renamed from: org.telegram.ui.iv.RichEditText$1, reason: invalid class name */
    public class AnonymousClass1 implements ActionMode.Callback {
        final /* synthetic */ RichEditText this$0;

        public AnonymousClass1(RichEditText r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditText$2, reason: invalid class name */
    public class AnonymousClass2 implements ActionMode.Callback {
        final /* synthetic */ RichEditText this$0;

        public AnonymousClass2(RichEditText r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r3, Menu r4) {
            if (this.this$0.length() == 0) goto L6;
            return false;
        L6:
            int r2 = r4.size() - 1;
        L7:
            if (r2 < 0) goto L14;
            int r0 = r4.getItem(r2).getItemId();
            if (r0 == 16908322) goto L13;
            if (r0 == 16908337) goto L13;
            r4.removeItem(r0);
        L13:
            r2 = r2 - 1;
            goto L7
        L14:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditText$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ RichEditText this$0;

        public AnonymousClass3(RichEditText r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r8) {
            if (RichEditText.m21648$$Nest$fgetignoreTextChange(this.this$0) == false) goto L5;
            return;
        L5:
            if (RichEditText.m21650$$Nest$fgetlistener(this.this$0) != null) goto L8;
            return;
        L8:
            if (RichEditText.m21647$$Nest$fgetautoBold(this.this$0) == true) goto L10;
        L12:
            Editable r1 = r8;
        L14:
            if (RichEditText.m21646$$Nest$fgetallowNewlines(this.this$0) == false) goto L16;
        L32:
            RichEditText.m21650$$Nest$fgetlistener(this.this$0).onTextChanged(this.this$0, r1);
            return;
        L16:
            if (RichEditText.m21649$$Nest$fgetinsertingNewline(this.this$0) == true) goto L32;
            if (RichEditText.m21651$$Nest$fgetsoftEnterNewline(this.this$0) == true) goto L32;
            RichEditText.m21652$$Nest$fputignoreTextChange(this.this$0, true);
            int r82 = r1.length() - 1;
            boolean r3 = false;
        L21:
            if (r82 < 0) goto L26;
            if (r1.charAt(r82) != '\n') goto L25;
            r1.delete(r82, r82 + 1);
            r3 = true;
        L25:
            r82 = r82 - 1;
            goto L21
        L26:
            RichEditText.m21652$$Nest$fputignoreTextChange(this.this$0, false);
            RichEditText r83 = this.this$0;
            if (r3 == false) goto L30;
            RichEditText.m21650$$Nest$fgetlistener(r83).onEnterPressed(this.this$0);
            return;
        L30:
            RichEditText.m21650$$Nest$fgetlistener(r83).onTextChanged(this.this$0, r1);
            return;
        L10:
            if (r8.length() <= 0) goto L12;
            r1 = r8;
            RichTextStyle.setStyle(r1, 0, r8.length(), 1, true, this.this$0.block);
            goto L14
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (RichEditText.m21648$$Nest$fgetignoreTextChange(this.this$0) == false) goto L5;
            return;
        L5:
            if (RichEditText.m21650$$Nest$fgetlistener(this.this$0) == null) goto L10;
            RichEditText.m21650$$Nest$fgetlistener(this.this$0).onTextWillChange(this.this$0, r3, r4);
            return;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            RichEditText.m21653$$Nest$fputmarkPathDirty(this.this$0, true);
            RichEditText.m21654$$Nest$mrefreshEmptyHintGravity(this.this$0);
            RichEditText.m21655$$Nest$mupdateLongClickForEmpty(this.this$0);
        }
    }

    public interface InlineButtonClickListener {
        void onInlineButtonClick(RichEditText r1, RichInlineButtonSpan r2, boolean r3);
    }

    public interface Listener {
        default boolean onBackspaceAtStart(RichEditText r1) {
            return false;
        }

        default void onBackspaceOnEmpty(RichEditText r1) {
        }

        default void onEnterPressed(RichEditText r1) {
        }

        default void onLockedInsert(RichEditText r1, CharSequence r2) {
        }

        default boolean onPaste(RichEditText r1) {
            return false;
        }

        void onRequestWindowFocusable(RichEditText r1, boolean r2);

        default boolean onSelectAll(RichEditText r1) {
            return false;
        }

        void onSelectionChanged(RichEditText r1, int r2, int r3);

        default boolean onTab(RichEditText r1, boolean r2) {
            return false;
        }

        void onTextChanged(RichEditText r1, Editable r2);

        default void onTextWillChange(RichEditText r1, int r2, int r3) {
        }
    }

    public static /* synthetic */ void $r8$lambda$9QvqnQR3IRNP13u9S3MVZEueIoo(RichEditText r0, MathSpan r1, String r2) {
        r0.lambda$openMathEditor$4(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$IR7LS9qjwE7MkDyDDLFdKUVIudc(RichEditText r0, View r1) {
        return r0.lambda$new$1(r1);
    }

    public static /* synthetic */ CharSequence $r8$lambda$Mnv9Rka9bo6LOqQHSeyjojRyfoQ(RichEditText r0, CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
        return r0.lambda$new$0(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$N7Y3lDE6MgnG2oFu-kfXWAm4W_A, reason: not valid java name */
    public static /* synthetic */ boolean m21645$r8$lambda$N7Y3lDE6MgnG2oFukfXWAm4W_A(RichEditText r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$bxcktDL5SCamVD_te4xPZyOaoRE(RichEditText r0) {
        r0.lambda$new$3();
    }

    /* renamed from: -$$Nest$fgetallowNewlines, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21646$$Nest$fgetallowNewlines(RichEditText r0) {
        return r0.allowNewlines;
    }

    /* renamed from: -$$Nest$fgetautoBold, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21647$$Nest$fgetautoBold(RichEditText r0) {
        return r0.autoBold;
    }

    /* renamed from: -$$Nest$fgetignoreTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21648$$Nest$fgetignoreTextChange(RichEditText r0) {
        return r0.ignoreTextChange;
    }

    /* renamed from: -$$Nest$fgetinsertingNewline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21649$$Nest$fgetinsertingNewline(RichEditText r0) {
        return r0.insertingNewline;
    }

    /* renamed from: -$$Nest$fgetlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Listener m21650$$Nest$fgetlistener(RichEditText r0) {
        return r0.listener;
    }

    /* renamed from: -$$Nest$fgetsoftEnterNewline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21651$$Nest$fgetsoftEnterNewline(RichEditText r0) {
        return r0.softEnterNewline;
    }

    /* renamed from: -$$Nest$fputignoreTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21652$$Nest$fputignoreTextChange(RichEditText r0, boolean r1) {
        r0.ignoreTextChange = r1;
    }

    /* renamed from: -$$Nest$fputmarkPathDirty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21653$$Nest$fputmarkPathDirty(RichEditText r0, boolean r1) {
        r0.markPathDirty = r1;
    }

    /* renamed from: -$$Nest$mrefreshEmptyHintGravity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21654$$Nest$mrefreshEmptyHintGravity(RichEditText r0) {
        r0.refreshEmptyHintGravity();
    }

    /* renamed from: -$$Nest$mupdateLongClickForEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21655$$Nest$mupdateLongClickForEmpty(RichEditText r0) {
        r0.updateLongClickForEmpty();
    }

    public RichEditText(Context r2, Theme.ResourcesProvider r3) {
        super(r2, r3);
        this.currentAccount = UserConfig.selectedAccount;
        this.lastMarkTextLength = -1;
        this.markPathDirty = true;
        this.lockingFilter = new RichEditText$$ExternalSyntheticLambda0(this);
        this.textColorKey = Theme.key_windowBackgroundWhiteBlackText;
        this.inlineButtonLongPressRunnable = new RichEditText$$ExternalSyntheticLambda1(this);
        this.resourcesProvider = r3;
        this.adaptiveCreateLinkDialog = true;
        setBackground(null);
        setCursorWidth(1.5f);
        setGravity(8388659);
        setInputType(getInputType() | 147456);
        setImeOptions(5);
        AnonymousClass1 r22 = new AnonymousClass1(this);
        AnonymousClass2 r32 = new AnonymousClass2(this);
        setCustomSelectionActionModeCallback(r22);
        setCustomInsertionActionModeCallback(r32);
        setOnLongClickListener(new RichEditText$$ExternalSyntheticLambda2(this));
        updateLongClickForEmpty();
        setOnEditorActionListener(new RichEditText$$ExternalSyntheticLambda3(this));
        addTextChangedListener(new AnonymousClass3(this));
        updateColors();
    }

    private void bindInlineButtons() {
        Editable r0 = getText();
        if (r0 == null) goto L11;
        int r3 = 0;
        RichInlineButtonSpan[] r1 = (RichInlineButtonSpan[]) r0.getSpans(0, r0.length(), RichInlineButtonSpan.class);
        int r2 = r1.length;
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L8;
        r1[r4].removeNestedReplacementSpans(r0);
        r4 = r4 + 1;
        goto L6
    L8:
        int r02 = r1.length;
    L9:
        if (r3 >= r02) goto L14;
        r1[r3].bind(this, this.currentAccount, this.resourcesProvider);
        r3 = r3 + 1;
        goto L9
    L14:
        return;
    }

    private void buildMarkPath() {
        Layout r0 = getLayout();
        LinkPath r1 = null;
        if (r0 != null) goto L6;
        this.markPath = null;
        this.lastMarkLayout = null;
        this.lastMarkTextLength = -1;
        return;
    L6:
        CharSequence r2 = r0.getText();
        if (this.markPathDirty == false) goto L9;
    L13:
        this.markPathDirty = false;
        this.lastMarkLayout = r0;
        this.lastMarkTextLength = r2.length();
        this.markPath = null;
        if ((r2 instanceof Spanned) == true) goto L16;
        return;
    L16:
        Spanned r22 = (Spanned) r2;
        TextStyleSpan[] r4 = (TextStyleSpan[]) r22.getSpans(0, r22.length(), TextStyleSpan.class);
        int r5 = r4.length;
        int r6 = 0;
    L18:
        if (r6 >= r5) goto L43;
        TextStyleSpan r8 = r4[r6];
        int r9 = r8.getStyleFlags();
        if ((65536 & r9) == 0) goto L42;
        int r10 = r22.getSpanStart(r8);
        int r82 = r22.getSpanEnd(r8);
        r1 = r1;
        if (r10 < 0) goto L42;
        if (r82 <= r10) goto L42;
        if (r1 != null) goto L28;
        LinkPath r12 = new LinkPath(true);
        r12.setAllowReset(false);
        r1 = r12;
    L28:
        r1.setCurrentLayout(r0, r10, 0.0f);
        if ((32768 & r9) == 0) goto L32;
        int r7 = -AndroidUtilities.dp(6.0f);
    L35:
        if (r7 == 0) goto L40;
        if (r7 <= 0) goto L38;
        float r92 = 5.0f;
    L39:
        int r72 = r7 + AndroidUtilities.dp(r92);
    L41:
        r1.setBaselineShift(r72);
        r0.getSelectionPath(r10, r82, r1);
        goto L42
    L38:
        r92 = -2.0f;
        goto L39
    L40:
        r72 = 0;
        goto L41
    L32:
        if ((r9 & 16384) == 0) goto L34;
        r7 = AndroidUtilities.dp(2.0f);
        goto L35
    L34:
        r7 = 0;
    L42:
        r6 = r6 + 1;
        r1 = r1;
        goto L18
    L43:
        if (r1 == null) goto L45;
        r1.setAllowReset(true);
    L45:
        this.markPath = r1;
        return;
    L9:
        if (r0 != this.lastMarkLayout) goto L13;
        if (r2.length() != this.lastMarkTextLength) goto L13;
    }

    private RichInlineButtonSpan inlineButtonSpanAt(float r11, float r12) {
        Layout r0 = getLayout();
        Editable r1 = getText();
        if (r0 == null) goto L25;
        if (r1 == null) goto L25;
        if (r1.length() == 0) goto L25;
        int r122 = (int) ((r12 - getTotalPaddingTop()) + getScrollY());
        if (r122 < 0) goto L25;
        if (r122 > r0.getHeight()) goto L25;
        int r123 = r0.getLineForVertical(r122);
        float r112 = (r11 - getTotalPaddingLeft()) + getScrollX();
        RichInlineButtonSpan[] r10 = (RichInlineButtonSpan[]) r1.getSpans(r0.getLineStart(r123), r0.getLineEnd(r123), RichInlineButtonSpan.class);
        int r124 = r10.length;
        int r3 = 0;
    L14:
        if (r3 >= r124) goto L25;
        RichInlineButtonSpan r4 = r10[r3];
        int r5 = r1.getSpanStart(r4);
        int r6 = r1.getSpanEnd(r4);
        if (r5 < 0) goto L24;
        if (r6 <= r5) goto L24;
        float r52 = r0.getPrimaryHorizontal(r5);
        float r62 = r0.getPrimaryHorizontal(r6);
        if (r112 < (Math.min(r52, r62) - AndroidUtilities.dp(2.0f))) goto L24;
        if (r112 > (Math.max(r52, r62) + AndroidUtilities.dp(2.0f))) goto L24;
        return r4;
    L24:
        r3 = r3 + 1;
    L25:
        return null;
    }

    private void insertNewlineAtSelection() {
        int r0 = Math.max(0, getSelectionStart());
        int r2 = Math.max(0, getSelectionEnd());
        if (r0 <= r2) goto L5;
        r2 = r0;
        r0 = r2;
    L5:
        this.insertingNewline = true;
        getText().replace(r0, r2, "\n");
        this.insertingNewline = false;
        setSelection(r0 + 1);
    }

    private /* synthetic */ CharSequence lambda$new$0(CharSequence r3, int r4, int r5, Spanned r6, int r7, int r8) {
        if (this.locked == true) goto L6;
        return null;
    L6:
        if (this.ignoreTextChange == false) goto L8;
        return null;
    L8:
        Listener r0 = this.listener;
        if (r0 == null) goto L15;
        if (r3 == null) goto L15;
        if (r5 <= r4) goto L15;
        if (r7 != r8) goto L15;
        r0.onLockedInsert(this, r3.subSequence(r4, r5));
    L15:
        return r6.subSequence(r7, r8);
    }

    private /* synthetic */ boolean lambda$new$1(View r1) {
        if (length() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ boolean lambda$new$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L14;
        Listener r12 = this.listener;
        if (r12 != null) goto L7;
        return false;
    L7:
        if (this.allowNewlines == false) goto L9;
        return false;
    L9:
        if (this.softEnterNewline == false) goto L11;
        insertNewlineAtSelection();
        return true;
    L11:
        r12.onEnterPressed(this);
        return true;
    L14:
        return false;
    }

    private /* synthetic */ void lambda$new$3() {
        RichInlineButtonSpan r0 = this.pressedInlineButton;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.inlineButtonClickListener == null) goto L15;
        this.inlineButtonLongPressed = true;
        r0.setPressed(false);
        performHapticFeedback(0);     // Catch: Exception -> L11
    L9:
        this.inlineButtonClickListener.onInlineButtonClick(this, this.pressedInlineButton, true);
        return;
    }

    private /* synthetic */ void lambda$openMathEditor$4(MathSpan r8, String r9) {
        if (TextUtils.isEmpty(r9) == true) goto L21;
        Editable r0 = getText();
        int r1 = r0.getSpanStart(r8);
        int r82 = r0.getSpanEnd(r8);
        if (r1 < 0) goto L19;
        if (r82 < 0) goto L22;
        MathSpan r92 = MathSpan.create(r9, getCurrentTextColor(), AndroidUtilities.dp(SharedConfig.fontSize + 4));
        if (r92 == null) goto L23;
        boolean r2 = this.locked;
        if (r2 == false) goto L15;
        setLocked(false);
    L15:
        SpannableString r4 = new SpannableString(" ");
        r4.setSpan(r92, 0, 1, 33);
        int r93 = Math.max(0, Math.min(r1, length()));
        r0.replace(r93, Math.max(r93, Math.min(r82, length())), r4);
        setSelection(Math.min(r93 + 1, length()));
        if (r2 == false) goto L20;
        setLocked(true);
        return;
    L20:
        return;
    L23:
        return;
    L22:
        return;
    L19:
        return;
    }

    private MathSpan mathSpanAt(float r11, float r12) {
        Layout r0 = getLayout();
        Editable r1 = getText();
        if (r0 == null) goto L29;
        if (r1 == null) goto L29;
        if (r1.length() == 0) goto L29;
        int r122 = r0.getLineForVertical((int) ((r12 - getTotalPaddingTop()) + getScrollY()));
        float r112 = (r11 - getTotalPaddingLeft()) + getScrollX();
        if (r112 < (r0.getLineLeft(r122) - AndroidUtilities.dp(2.0f))) goto L29;
        if (r112 > (r0.getLineRight(r122) + AndroidUtilities.dp(2.0f))) goto L29;
        int r10 = r0.getOffsetForHorizontal(r122, r112);
        int r4 = 0;
        MathSpan[] r102 = (MathSpan[]) r1.getSpans(Math.max(0, r10 - 1), Math.min(r1.length(), r10 + 1), MathSpan.class);
        int r123 = r102.length;
    L14:
        if (r4 >= r123) goto L29;
        MathSpan r5 = r102[r4];
        int r6 = r1.getSpanStart(r5);
        int r7 = r1.getSpanEnd(r5);
        if (r6 < 0) goto L28;
        if (r7 < 0) goto L28;
        float r62 = r0.getPrimaryHorizontal(r6);
        if (r7 > r1.length()) goto L22;
        float r72 = r0.getPrimaryHorizontal(r7);
    L24:
        if (r112 < (Math.min(r62, r72) - AndroidUtilities.dp(2.0f))) goto L28;
        if (r112 > (Math.max(r62, r72) + AndroidUtilities.dp(2.0f))) goto L28;
        return r5;
    L22:
        r72 = r62;
    L28:
        r4 = r4 + 1;
    L29:
        return null;
    }

    private void openMathEditor(MathSpan r4) {
        ChatAttachAlertRichLayout.showEditLatexSheet(getContext(), r4.source, new RichEditText$$ExternalSyntheticLambda4(this, r4), this.resourcesProvider);
    }

    private void refreshEmptyHintGravity() {
        if (this.centerEmptyHint == true) goto L5;
        return;
    L5:
        this.applyingEmptyHint = true;
        int r1 = AndroidUtilities.dp(2.0f);
        CharSequence r2 = getHint();
        if (length() == 0) goto L8;
    L12:
        super.setGravity(17);
        setPadding(r1, getPaddingTop(), r1, getPaddingBottom());
    L13:
        this.applyingEmptyHint = false;
        return;
    L8:
        if (getWidth() <= 0) goto L12;
        if (TextUtils.isEmpty(r2) == true) goto L12;
        int r0 = Math.max(0, Math.round(((getWidth() - (r1 * 2)) - getPaint().measureText(r2.toString())) / 2.0f));
        super.setGravity(51);
        setPadding(r0 + r1, getPaddingTop(), r1, getPaddingBottom());
        goto L13
    }

    private void updateLongClickForEmpty() {
        if (length() != 0) goto L5;
        boolean r0 = true;
    L6:
        setLongClickable(r0);
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.ActionBar.FloatingToolbar.StyleDelegate
    public void addStyle(int r7, int r8, int r9) {
        Editable r0 = getText();
        if (r0 == null) goto L16;
        if (r8 < 0) goto L17;
        if (r9 < 0) goto L18;
        if (r8 >= r9) goto L19;
        int r2 = Math.min(r9, r0.length());
        if (r8 >= r2) goto L20;
        RichTextStyle.setStyle(r0, r8, r2, r7, true, this.block);
        if ((r7 & 256) == 0) goto L14;
        invalidateSpoilers();
    L14:
        notifySpansChanged();
        return;
    L20:
        return;
    L19:
        return;
    L18:
        return;
    L17:
        return;
    }

    public void appendSilently(CharSequence r3) {
        Editable r0 = getText();
        if (r0 == null) goto L10;
        if (r3 != null) goto L6;
        return;
    L6:
        if (r3.length() == 0) goto L12;
        this.ignoreTextChange = true;
        r0.append(r3);
        this.ignoreTextChange = false;
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.Components.EditTextCaption
    public URLSpanReplacement createUrlSpan(String r1) {
        return RichTextStyle.linkSpan(r1);
    }

    public void deleteToEndSilently(int r3) {
        Editable r0 = getText();
        if (r0 == null) goto L10;
        if (r3 >= 0) goto L6;
        return;
    L6:
        if (r3 >= r0.length()) goto L12;
        this.ignoreTextChange = true;
        r0.delete(r3, r0.length());
        this.ignoreTextChange = false;
        return;
    L12:
        return;
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
    public boolean dispatchKeyEvent(KeyEvent r4) {
        if (r4.getKeyCode() == 61) goto L5;
        int r0 = r4.getKeyCode();
        if (r0 == 66) goto L15;
        if (r0 == 160) goto L15;
    L33:
        return super.dispatchKeyEvent(r4);
    L15:
        if (this.listener == null) goto L33;
        if (this.allowNewlines == true) goto L33;
        if (r4.getAction() == 0) goto L21;
    L31:
        return true;
    L21:
        if ((r4.getFlags() & 2) == 0) goto L23;
        boolean r02 = true;
    L25:
        if (this.softEnterNewline == false) goto L30;
        if (r02 == false) goto L28;
    L29:
        insertNewlineAtSelection();
        goto L31
    L28:
        if (r4.isShiftPressed() == true) goto L29;
    L30:
        this.listener.onEnterPressed(this);
        goto L31
    L23:
        r02 = false;
        goto L25
    L5:
        if (r4.getAction() != 0) goto L9;
        Listener r03 = this.listener;
        if (r03 == null) goto L9;
        r03.onTab(this, r4.isShiftPressed());
    L9:
        return true;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor
    public void extendActionMode(ActionMode r1, Menu r2) {
    }

    public void finishActionMode() {
        FloatingActionMode r0 = this.floatingActionMode;
        if (r0 == null) goto L9;
        r0.finish();     // Catch: Exception -> L6
        return;
    L10:
        return;
    }

    @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.ActionBar.FloatingToolbar.StyleDelegate
    public int getCurrentStyle(int r3, int r4) {
        Editable r2 = getText();
        if (r2 != null) goto L5;
        return 0;
    L5:
        int r32 = Math.max(0, r3);
        int r42 = Math.min(r4, r2.length());
        if (r32 < r42) goto L9;
        return 0;
    L9:
        return RichTextStyle.stylesFullyCovering(r2, r32, r42);
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor
    public Theme.ResourcesProvider getResourcesProvider() {
        return this.resourcesProvider;
    }

    public boolean isAutoBold() {
        return this.autoBold;
    }

    public void notifyInlineContentChanged() {
        notifySpansChanged();
        requestLayout();
        invalidateEffects();
    }

    @Override // org.telegram.ui.Components.EditTextCaption
    public void notifySpansChanged() {
        super.notifySpansChanged();
        this.markPathDirty = true;
        invalidate();
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        bindInlineButtons();
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
    public void onDetachedFromWindow() {
        AndroidUtilities.cancelRunOnUIThread(this.inlineButtonLongPressRunnable);
        this.pressedInlineButton = null;
        int r0 = 0;
        this.inlineButtonLongPressed = false;
        Editable r1 = getText();
        if (r1 == null) goto L7;
        RichInlineButtonSpan[] r12 = (RichInlineButtonSpan[]) r1.getSpans(0, r1.length(), RichInlineButtonSpan.class);
        int r2 = r12.length;
    L5:
        if (r0 >= r2) goto L7;
        r12[r0].detach(this);
        r0 = r0 + 1;
    L7:
        super.onDetachedFromWindow();
    }

    @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onDraw(Canvas r4) {
        buildMarkPath();
        if (this.markPath != null) goto L5;
    L8:
        super.onDraw(r4);
        return;
    L5:
        if (this.markPaint != null) goto L7;
        Paint r0 = new Paint(1);
        this.markPaint = r0;
        r0.setPathEffect(LinkPath.getRoundedEffect());
    L7:
        this.markPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkSelection, this.resourcesProvider) & 872415231);
        r4.save();
        r4.translate(getPaddingLeft(), this.offsetY);
        r4.drawPath(this.markPath, this.markPaint);
        r4.restore();
        goto L8
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r3, KeyEvent r4) {
        if (r3 != 67) goto L18;
        if (this.listener == null) goto L18;
        if (length() != 0) goto L11;
        this.listener.onBackspaceOnEmpty(this);
        return true;
    L11:
        if (getSelectionStart() != 0) goto L18;
        if (getSelectionEnd() != 0) goto L18;
        if (this.listener.onBackspaceAtStart(this) == false) goto L18;
        return true;
    L18:
        return super.onKeyDown(r3, r4);
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onSelectionChanged(int r2, int r3) {
        super.onSelectionChanged(r2, r3);
        Listener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onSelectionChanged(this, r2, r3);
        return;
    }

    @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        refreshEmptyHintGravity();
    }

    @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int r3) {
        if (r3 != 16908319) goto L10;
        Listener r0 = this.listener;
        if (r0 == null) goto L10;
        if (r0.onSelectAll(this) == false) goto L10;
        return true;
    L10:
        if (r3 != 16908322) goto L17;
        Listener r02 = this.listener;
        if (r02 == null) goto L17;
        if (r02.onPaste(this) == false) goto L17;
        return true;
    L17:
        return super.onTextContextMenuItem(r3);
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (r8.getAction() != 0) goto L12;
        if (this.listener == null) goto L12;
        if (isEnabled() == false) goto L12;
        if (isFocusable() == false) goto L12;
        this.listener.onRequestWindowFocusable(this, true);
    L12:
        if (this.locked == true) goto L63;
        if (r8.getAction() != 0) goto L22;
        this.mathDownX = r8.getX();
        this.mathDownY = r8.getY();
        this.mathDownTime = r8.getEventTime();
        RichInlineButtonSpan r0 = inlineButtonSpanAt(r8.getX(), r8.getY());
        this.pressedInlineButton = r0;
        if (r0 != null) goto L18;
    L21:
        this.pressedInlineButton = null;
        goto L63
    L18:
        if (this.inlineButtonClickListener == null) goto L21;
        this.inlineButtonLongPressed = false;
        r0.setPressed(true);
        AndroidUtilities.cancelRunOnUIThread(this.inlineButtonLongPressRunnable);
        AndroidUtilities.runOnUIThread(this.inlineButtonLongPressRunnable, ViewConfiguration.getLongPressTimeout());
        return true;
    L22:
        RichInlineButtonSpan r02 = this.pressedInlineButton;
        if (r02 == null) goto L50;
        if (r8.getAction() != 1) goto L27;
    L30:
        boolean r4 = true;
    L32:
        if (r8.getAction() != 3) goto L34;
    L36:
        boolean r5 = false;
    L37:
        if (r5 == false) goto L39;
        if (r4 == true) goto L39;
    L40:
        if (r4 == false) goto L48;
        this.pressedInlineButton = null;
        if (this.inlineButtonLongPressed == true) goto L47;
        if (r5 == false) goto L47;
        if (r8.getAction() != 1) goto L47;
        this.inlineButtonClickListener.onInlineButtonClick(this, r02, false);
    L47:
        this.inlineButtonLongPressed = false;
    L48:
        return true;
    L39:
        r02.setPressed(false);
        AndroidUtilities.cancelRunOnUIThread(this.inlineButtonLongPressRunnable);
        goto L40
    L34:
        if (inlineButtonSpanAt(r8.getX(), r8.getY()) != r02) goto L36;
        r5 = true;
        goto L37
    L27:
        if (r8.getAction() == 3) goto L30;
        r4 = false;
        goto L32
    L50:
        if (r8.getAction() != 1) goto L63;
        if (this.touchSlop != 0) goto L54;
        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    L54:
        float r03 = r8.getX() - this.mathDownX;
        float r2 = r8.getY() - this.mathDownY;
        float r04 = (r03 * r03) + (r2 * r2);
        int r22 = this.touchSlop;
        if (r04 > (r22 * r22)) goto L63;
        if ((r8.getEventTime() - this.mathDownTime) >= ViewConfiguration.getLongPressTimeout()) goto L63;
        MathSpan r05 = mathSpanAt(r8.getX(), r8.getY());
        if (r05 == null) goto L63;
        openMathEditor(r05);
        return true;
    L63:
        return super.onTouchEvent(r8);
    }

    @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.ActionBar.FloatingToolbar.StyleDelegate
    public void removeStyle(int r7, int r8, int r9) {
        Editable r0 = getText();
        if (r0 == null) goto L19;
        if (r8 < 0) goto L20;
        if (r9 < 0) goto L21;
        if (r8 >= r9) goto L22;
        int r2 = Math.min(r9, r0.length());
        if (r8 < r2) goto L12;
        return;
    L12:
        if ((r7 & 1) == 0) goto L14;
        this.autoBold = false;
    L14:
        RichTextStyle.setStyle(r0, r8, r2, r7, false, this.block);
        if ((r7 & 256) == 0) goto L17;
        invalidateSpoilers();
    L17:
        notifySpansChanged();
        return;
    L22:
        return;
    L21:
        return;
    L20:
        return;
    }

    public void requestEditFocus() {
        Listener r0 = this.listener;
        if (r0 == null) goto L5;
        r0.onRequestWindowFocusable(this, true);
    L5:
        requestFocus();
        AndroidUtilities.showKeyboard(this);
    }

    public void requestEditFocusRebuild() {
        finishActionMode();
        if (isFocused() == false) goto L5;
        clearFocus();
    L5:
        requestEditFocus();
        finishActionMode();
        post(new RichEditText$$ExternalSyntheticLambda5(this));
    }

    public void setAccentHint(boolean r2) {
        if (this.accentHint != r2) goto L5;
        return;
    L5:
        this.accentHint = r2;
        updateColors();
    }

    public void setAllowNewlines(boolean r1) {
        this.allowNewlines = r1;
    }

    public void setAutoBold(boolean r1) {
        this.autoBold = r1;
    }

    public void setBlock(TL_iv.PageBlock r1) {
        this.block = r1;
    }

    public void setCenterEmptyHint(boolean r3) {
        if (this.centerEmptyHint != r3) goto L5;
        return;
    L5:
        this.centerEmptyHint = r3;
        if (r3 == false) goto L9;
        refreshEmptyHintGravity();
        return;
    L9:
        int r32 = AndroidUtilities.dp(2.0f);
        setPadding(r32, getPaddingTop(), r32, getPaddingBottom());
    }

    @Override // android.widget.TextView
    public void setGravity(int r2) {
        if (this.applyingEmptyHint == true) goto L5;
        this.centerEmptyHint = false;
    L5:
        super.setGravity(r2);
    }

    public void setInlineButtonClickListener(InlineButtonClickListener r1) {
        this.inlineButtonClickListener = r1;
    }

    public void setInlineButtonContext(int r1) {
        this.currentAccount = r1;
        bindInlineButtons();
    }

    @Override // android.widget.TextView
    public void setInputType(int r2) {
        if (getInputType() == r2) goto L5;
        boolean r0 = true;
    L6:
        super.setInputType(r2);
        if (r0 == true) goto L9;
        return;
    L9:
        if (isFocused() == false) goto L15;
        InputMethodManager r22 = (InputMethodManager) getContext().getSystemService("input_method");
        if (r22 == null) goto L16;
        r22.restartInput(this);
        return;
    L16:
        return;
    L15:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setListener(Listener r1) {
        this.listener = r1;
    }

    public void setLocked(boolean r8) {
        if (this.locked != r8) goto L5;
        return;
    L5:
        this.locked = r8;
        InputFilter[] r0 = getFilters();
        int r1 = r0.length;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L12;
        if (r0[r3] == this.lockingFilter) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        boolean r12 = true;
    L13:
        if (r8 == false) goto L16;
        if (r12 == true) goto L16;
        InputFilter[] r13 = new InputFilter[r0.length + 1];
        System.arraycopy(r0, 0, r13, 0, r0.length);
        r13[r0.length] = this.lockingFilter;
        setFilters(r13);
    L16:
        setAllowDrawCursor(!r8);
        setCursorVisible(!r8);
        return;
    L12:
        r12 = false;
        goto L13
    }

    public void setSoftEnterNewline(boolean r1) {
        this.softEnterNewline = r1;
    }

    public void setTextColorKey(int r1) {
        this.textColorKey = r1;
        updateColors();
    }

    public void setTextSilently(CharSequence r2) {
        this.ignoreTextChange = true;
        setText(r2);
        bindInlineButtons();
        setSelection(length());
        this.ignoreTextChange = false;
    }

    public void updateColors() {
        setTextColor(Theme.getColor(this.textColorKey, this.resourcesProvider));
        setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));
        if (this.accentHint == false) goto L5;
        int r0 = Theme.multAlpha(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), 0.5f);
    L6:
        setHintTextColor(r0);
        setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        setHandlesColor(Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, this.resourcesProvider));
        bindInlineButtons();
        return;
    L5:
        r0 = Theme.getColor(Theme.key_windowBackgroundWhiteHintText, this.resourcesProvider);
        goto L6
    }
}
