package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.URLSpan;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.CopyUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialogDecor;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.FloatingActionMode;
import org.telegram.ui.ActionBar.FloatingToolbar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class EditTextCaption extends EditTextBoldCursor implements FloatingToolbar.StyleDelegate {
    private static final int ACCESSIBILITY_ACTION_SHARE = 268435456;
    private static final int[] STYLE_FLAGS = null;
    public boolean adaptiveCreateLinkDialog;
    private boolean allowTextEntitiesIntersection;
    private String caption;
    private StaticLayout captionLayout;
    private boolean copyPasteShowed;
    private AlertDialog creationLinkDialog;
    private EditTextCaptionDelegate delegate;
    private int hintColor;
    private boolean isInitLineCount;
    private CharSequence lastLayoutText;
    private int lastLayoutWidth;
    private int lineCount;
    private final Theme.ResourcesProvider resourcesProvider;
    private Text rightText;
    private int selectionEnd;
    private int selectionStart;
    private int userNameLength;
    private int xOffset;
    private int yOffset;

    /* renamed from: org.telegram.ui.Components.EditTextCaption$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ EditTextCaption this$0;

        public AnonymousClass1(EditTextCaption r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            if (EditTextCaption.m10146$$Nest$fgetlineCount(this.this$0) != this.this$0.getLineCount()) goto L5;
            return;
        L5:
            if (EditTextCaption.m10145$$Nest$fgetisInitLineCount(this.this$0) == false) goto L7;
        L9:
            EditTextCaption r2 = this.this$0;
            EditTextCaption.m10148$$Nest$fputlineCount(r2, r2.getLineCount());
            return;
        L7:
            if (this.this$0.getMeasuredWidth() <= 0) goto L9;
            EditTextCaption r32 = this.this$0;
            r32.onLineCountChanged(EditTextCaption.m10146$$Nest$fgetlineCount(r32), this.this$0.getLineCount());
            goto L9
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextCaption$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextBoldCursor {
        final /* synthetic */ EditTextCaption this$0;

        public AnonymousClass2(EditTextCaption r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextCaption$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextBoldCursor {
        final /* synthetic */ EditTextCaption this$0;

        public AnonymousClass3(EditTextCaption r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextCaption$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ EditTextCaption this$0;
        final /* synthetic */ Runnable val$checkPaste;

        public AnonymousClass4(EditTextCaption r1, Runnable r2) {
            this.this$0 = r1;
            this.val$checkPaste = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            this.val$checkPaste.run();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextCaption$5, reason: invalid class name */
    public class AnonymousClass5 implements ActionMode.Callback {
        final /* synthetic */ EditTextCaption this$0;
        final /* synthetic */ ActionMode.Callback val$callback;

        public AnonymousClass5(EditTextCaption r1, ActionMode.Callback r2) {
            this.this$0 = r1;
            this.val$callback = r2;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r3, MenuItem r4) {
            if (this.this$0.performMenuAction(r4.getItemId()) == false) goto L9;
            r3.finish();
            return true;
        L9:
            return this.val$callback.onActionItemClicked(r3, r4);
        L8:
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r3, Menu r4) {
            EditTextCaption.m10147$$Nest$fputcopyPasteShowed(this.this$0, true);
            this.this$0.onContextMenuOpen();
            return this.val$callback.onCreateActionMode(r3, r4);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r3) {
            EditTextCaption.m10147$$Nest$fputcopyPasteShowed(this.this$0, false);
            this.this$0.onContextMenuClose();
            this.val$callback.onDestroyActionMode(r3);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return this.val$callback.onPrepareActionMode(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextCaption$6, reason: invalid class name */
    public class AnonymousClass6 extends ActionMode.Callback2 {
        final /* synthetic */ EditTextCaption this$0;
        final /* synthetic */ ActionMode.Callback val$callback;
        final /* synthetic */ ActionMode.Callback val$wrap;

        public AnonymousClass6(EditTextCaption r1, ActionMode.Callback r2, ActionMode.Callback r3) {
            this.this$0 = r1;
            this.val$wrap = r2;
            this.val$callback = r3;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return this.val$wrap.onActionItemClicked(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return this.val$wrap.onCreateActionMode(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
            this.val$wrap.onDestroyActionMode(r1);
        }

        @Override // android.view.ActionMode.Callback2
        public void onGetContentRect(ActionMode r3, View r4, Rect r5) {
            ActionMode.Callback r0 = this.val$callback;
            if ((r0 instanceof ActionMode.Callback2) == false) goto L6;
            ((ActionMode.Callback2) r0).onGetContentRect(r3, r4, r5);
            return;
        L6:
            super.onGetContentRect(r3, r4, r5);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return this.val$wrap.onPrepareActionMode(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextCaption$7, reason: invalid class name */
    public class AnonymousClass7 extends InputConnectionWrapper {
        final /* synthetic */ EditTextCaption this$0;

        public AnonymousClass7(EditTextCaption r1, InputConnection r2, boolean r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean commitText(CharSequence r5, int r6) {
            if (r5 == null) goto L25;
            if (r5.length() <= 1) goto L25;
            ClipboardManager r0 = (ClipboardManager) this.this$0.getContext().getSystemService("clipboard");     // Catch: Exception -> L22
            if (r0 == null) goto L25;
            if (r0.hasPrimaryClip() == false) goto L25;
            if (r0.getPrimaryClipDescription().hasMimeType("text/html") == false) goto L25;
            ClipData r02 = r0.getPrimaryClip();     // Catch: Exception -> L22
            if (r02 == null) goto L25;
            if (r02.getItemCount() <= 0) goto L25;
            CharSequence r2 = r02.getItemAt(0).coerceToText(this.this$0.getContext());     // Catch: Exception -> L22
            if (r2 == null) goto L25;
            if (TextUtils.equals(r5, r2) == false) goto L25;
            if (EditTextCaption.m10149$$Nest$mhandleRichPaste(this.this$0, r02) == false) goto L25;
            return true;
        L22:
            e = move-exception;
            FileLog.e(e);
        L25:
            return super.commitText(r5, r6);
        }
    }

    public interface EditTextCaptionDelegate {
        void onSpansChanged();
    }

    public interface InputDialogCallback {
        void run(String r1);
    }

    /* renamed from: $r8$lambda$-Wr58muwTVnYlG-XcO0y69jjpcw, reason: not valid java name */
    public static /* synthetic */ void m10140$r8$lambda$Wr58muwTVnYlGXcO0y69jjpcw(EditTextCaption r0, DialogInterface r1) {
        r0.lambda$showInputDialog$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$1swod7mxhoES81vqde73vPHv6NU(InputDialogCallback r0, EditTextBoldCursor r1, AlertDialog r2, int r3) {
        r0.run(r1.getText().toString().trim());
    }

    public static /* synthetic */ void $r8$lambda$3rcpwF6Mh7ikoiG1MknyFUczbVs() {
    }

    public static /* synthetic */ void $r8$lambda$4YRcQP4S4ps5ELm_vMRkIZ3g9KE(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$BB2rauizC6gGvABvrm3LVEg7rXs(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$DMG66VGHjeDpOkjQuIbBz9ERXds(EditTextCaption r0, int r1, int r2, int r3, int r4) {
        r0.lambda$makeSelectedDate$3(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$JZ1sDh_Ol2Ec9fG6ryg--egMUXw, reason: not valid java name */
    public static /* synthetic */ void m10141$r8$lambda$JZ1sDh_Ol2Ec9fG6rygegMUXw(EditTextCaption r0, int r1, int r2, Runnable r3, String r4) {
        r0.lambda$makeSelectedUrl$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$NMBzkD_PfCaeId2be5C_uTfb_TM(EditTextCaption r0, boolean r1, EditTextBoldCursor r2, String r3, TextView r4) {
        r0.lambda$showInputDialog$7(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$Tc70B2BzhmuidgKds-evi27a1gI, reason: not valid java name */
    public static /* synthetic */ void m10142$r8$lambda$Tc70B2BzhmuidgKdsevi27a1gI(EditTextCaption r0, int r1, int r2, CharSequence r3) {
        r0.lambda$translateSelected$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$aBOjHGqHBZl7dKL3j-HTWBggnpA, reason: not valid java name */
    public static /* synthetic */ void m10143$r8$lambda$aBOjHGqHBZl7dKL3jHTWBggnpA(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$bpxl9oNy1XpoQyWe8JyYloiAyK4(EditTextCaption r0, int r1, int r2, EditTextBoldCursor r3, AlertDialog r4, int r5) {
        r0.lambda$makeSelectedCode$0(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$h9dulOZTnwlnq_r_U2QzDz9PeTw(EditTextCaption r0, EditTextBoldCursor r1, Runnable r2, View r3) {
        r0.lambda$showInputDialog$8(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$lpZKKGYTU-clDVtuoqLy1R0J3fM, reason: not valid java name */
    public static /* synthetic */ void m10144$r8$lambda$lpZKKGYTUclDVtuoqLy1R0J3fM(EditTextCaption r0, DialogInterface r1) {
        r0.lambda$makeSelectedCode$2(r1);
    }

    /* renamed from: -$$Nest$fgetisInitLineCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10145$$Nest$fgetisInitLineCount(EditTextCaption r0) {
        return r0.isInitLineCount;
    }

    /* renamed from: -$$Nest$fgetlineCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10146$$Nest$fgetlineCount(EditTextCaption r0) {
        return r0.lineCount;
    }

    /* renamed from: -$$Nest$fputcopyPasteShowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10147$$Nest$fputcopyPasteShowed(EditTextCaption r0, boolean r1) {
        r0.copyPasteShowed = r1;
    }

    /* renamed from: -$$Nest$fputlineCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10148$$Nest$fputlineCount(EditTextCaption r0, int r1) {
        r0.lineCount = r1;
    }

    /* renamed from: -$$Nest$mhandleRichPaste, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10149$$Nest$mhandleRichPaste(EditTextCaption r0, ClipData r1) {
        return r0.handleRichPaste(r1);
    }

    static {
        STYLE_FLAGS = new int[]{1, 2, 4, 8, 16, 256, 16384, 32768};
    }

    public EditTextCaption(Context r1, Theme.ResourcesProvider r2) {
        super(r1);
        this.selectionStart = -1;
        this.selectionEnd = -1;
        this.resourcesProvider = r2;
        this.quoteColor = Theme.getColor(Theme.key_chat_inQuote, r2);
        addTextChangedListener(new AnonymousClass1(this));
        setClipToPadding(true);
    }

    private void applyCodeBlock(int r10, int r11, String r12) {
        Editable r0 = getText();
        CharacterStyle[] r1 = (CharacterStyle[]) r0.getSpans(r10, r11, CharacterStyle.class);     // Catch: Exception -> L10
        if (r1 == null) goto L15;
        int r3 = r1.length;     // Catch: Exception -> L10
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L15;
        CharacterStyle r5 = r1[r4];     // Catch: Exception -> L10
        int r6 = r0.getSpanStart(r5);     // Catch: Exception -> L10
        int r7 = r0.getSpanEnd(r5);     // Catch: Exception -> L10
        r0.removeSpan(r5);     // Catch: Exception -> L10
        if (r6 >= r10) goto L12;
        r0.setSpan(r5, r6, r10, 33);     // Catch: Exception -> L10
    L12:
        if (r7 <= r11) goto L14;
        r0.setSpan(r5, r11, r7, 33);     // Catch: Exception -> L10
    L14:
        r4 = r4 + 1;     // Catch: Exception -> L10
    L15:
        r0.setSpan(new CodeHighlighting.Span(true, 0, null, CodeHighlighting.normalizeLanguage(r12), r0.subSequence(r10, r11).toString()), r10, r11, 33);     // Catch: Exception -> L10
    L18:
        EditTextCaptionDelegate r9 = this.delegate;
        if (r9 == null) goto L27;
        r9.onSpansChanged();
        return;
    L27:
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L18
    }

    private void applyTextStyleToSelection(TextStyleSpan r7) {
        int r0 = this.selectionStart;
        if (r0 < 0) goto L7;
        int r1 = this.selectionEnd;
        if (r1 < 0) goto L7;
        this.selectionEnd = -1;
        this.selectionStart = -1;
    L8:
        MediaDataController.addStyleToText(r7, r0, r1, getText(), this.allowTextEntitiesIntersection);
        if (r7 != null) goto L24;
        Editable r72 = getText();
        CodeHighlighting.Span[] r2 = (CodeHighlighting.Span[]) r72.getSpans(r0, r1, CodeHighlighting.Span.class);
        int r3 = 0;
        int r4 = 0;
    L12:
        if (r4 >= r2.length) goto L14;
        r72.removeSpan(r2[r4]);
        r4 = r4 + 1;
        goto L12
    L14:
        QuoteSpan[] r02 = (QuoteSpan[]) r72.getSpans(r0, r1, QuoteSpan.class);
    L16:
        if (r3 >= r02.length) goto L22;
        r72.removeSpan(r02[r3]);
        r72.removeSpan(r02[r3].styleSpan);
        QuoteSpan.QuoteCollapsedPart r12 = r02[r3].collapsedSpan;
        if (r12 == null) goto L20;
        r72.removeSpan(r12);
    L20:
        r3 = r3 + 1;
        goto L16
    L22:
        if (r02.length <= 0) goto L24;
        invalidateQuotes(true);
    L24:
        EditTextCaptionDelegate r6 = this.delegate;
        if (r6 == null) goto L32;
        r6.onSpansChanged();
        return;
    L32:
        return;
    L7:
        r0 = getSelectionStart();
        r1 = getSelectionEnd();
        goto L8
    }

    private EditTextBoldCursor createCodeLanguageEditText() {
        AnonymousClass2 r0 = new AnonymousClass2(this, getContext());
        r0.setTextSize(1, 18.0f);
        r0.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        int r1 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r0.setCursorColor(getThemedColor(r1));
        r0.setHintText(LocaleController.getString(R.string.CreateCodeLanguage));
        r0.setHeaderHintColor(getThemedColor(Theme.key_windowBackgroundWhiteBlueHeader));
        r0.setSingleLine(true);
        r0.setFocusable(true);
        r0.setTransformHintToHeader(true);
        r0.setLineColors(getThemedColor(Theme.key_windowBackgroundWhiteInputField), getThemedColor(r1), getThemedColor(Theme.key_text_RedRegular));
        r0.setImeOptions(6);
        r0.setBackground(null);
        r0.requestFocus();
        r0.setPadding(0, 0, 0, 0);
        return r0;
    }

    private void fillSelectedCodeLanguage(EditTextBoldCursor r3, int r4, int r5) {
        CodeHighlighting.Span[] r2 = (CodeHighlighting.Span[]) getText().getSpans(r4, r5, CodeHighlighting.Span.class);
        if (r2 == null) goto L14;
        int r42 = r2.length;
        int r52 = 0;
    L6:
        if (r52 >= r42) goto L19;
        CodeHighlighting.Span r0 = r2[r52];
        if (r0 == null) goto L13;
        if (TextUtils.isEmpty(r0.lng) == true) goto L13;
        r3.setText(r0.lng);
        return;
    L13:
        r52 = r52 + 1;
        goto L6
    L19:
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private boolean handleRichPaste(ClipData r10) {
        if (r10 != null) goto L5;
    L45:
        return false;
    L5:
        if (r10.getItemCount() == 0) goto L45;
        ClipDescription r2 = r10.getDescription();
        if (r2.hasMimeType("image/*") == true) goto L45;
        if (r2.hasMimeType("video/*") == true) goto L45;
        if (r2.hasMimeType("audio/*") == true) goto L45;
        boolean r22 = r10.getDescription().hasMimeType("text/html");
        if (r10.getItemAt(0).getUri() == null) goto L17;
        boolean r3 = true;
    L18:
        if (r22 == true) goto L46;
        if (r3 == false) goto L45;
    L46:
        ClipData.Item r102 = r10.getItemAt(0);     // Catch: Exception -> L25
        String r23 = r102.getHtmlText();     // Catch: Exception -> L25
        if (r23 == null) goto L23;
    L27:
        if (r23 != null) goto L29;
        return false;
    L29:
        SpannableStringBuilder r103 = new SpannableStringBuilder(CopyUtilities.fromHTML(r23));     // Catch: Exception -> L25
        Emoji.replaceEmoji(r103, getPaint().getFontMetricsInt(), false, null);     // Catch: Exception -> L25
        AnimatedEmojiSpan[] r24 = (AnimatedEmojiSpan[]) r103.getSpans(0, r103.length(), AnimatedEmojiSpan.class);     // Catch: Exception -> L25
        if (r24 == null) goto L34;
        int r32 = r24.length;     // Catch: Exception -> L25
        int r5 = 0;
    L32:
        if (r5 >= r32) goto L34;
        r24[r5].applyFontMetrics(getPaint().getFontMetricsInt(), AnimatedEmojiDrawable.getCacheTypeForEnterView());     // Catch: Exception -> L25
        r5 = r5 + 1;     // Catch: Exception -> L25
    L34:
        int r25 = Math.max(0, getSelectionStart());     // Catch: Exception -> L25
        int r33 = Math.min(getText().length(), getSelectionEnd());     // Catch: Exception -> L25
        QuoteSpan.QuoteStyleSpan[] r52 = (QuoteSpan.QuoteStyleSpan[]) getText().getSpans(r25, r33, QuoteSpan.QuoteStyleSpan.class);     // Catch: Exception -> L25
        if (r52 != null) goto L37;
    L41:
        QuoteSpan.normalizeQuotes(r103);     // Catch: Exception -> L25
    L42:
        setText(getText().replace(r25, r33, r103));     // Catch: Exception -> L25
        setSelection(r103.length() + r25, r25 + r103.length());     // Catch: Exception -> L25
        return true;
    L37:
        if (r52.length <= 0) goto L41;
        QuoteSpan.QuoteStyleSpan[] r0 = (QuoteSpan.QuoteStyleSpan[]) r103.getSpans(0, r103.length(), QuoteSpan.QuoteStyleSpan.class);     // Catch: Exception -> L25
        int r53 = r0.length;     // Catch: Exception -> L25
        int r6 = 0;
    L39:
        if (r6 >= r53) goto L42;
        QuoteSpan.QuoteStyleSpan r7 = r0[r6];     // Catch: Exception -> L25
        r103.removeSpan(r7);     // Catch: Exception -> L25
        r103.removeSpan(r7.span);     // Catch: Exception -> L25
        r6 = r6 + 1;     // Catch: Exception -> L25
        goto L39
    L23:
        if (r102.getUri() == null) goto L27;
        r23 = readHtmlFromUri(r102.getUri());     // Catch: Exception -> L25
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L45
    L17:
        r3 = false;
        goto L18
    }

    private /* synthetic */ void lambda$makeSelectedCode$0(int r1, int r2, EditTextBoldCursor r3, AlertDialog r4, int r5) {
        applyCodeBlock(r1, r2, r3.getText().toString());
    }

    private /* synthetic */ void lambda$makeSelectedCode$2(DialogInterface r1) {
        this.creationLinkDialog = null;
        requestFocus();
    }

    private /* synthetic */ void lambda$makeSelectedDate$3(int r4, int r5, int r6, int r7) {
        Editable r0 = getText();
        TextStyleSpan.TextStyleRun r1 = new TextStyleSpan.TextStyleRun();
        r1.flags |= 128;
        r1.start = r4;
        r1.end = r5;
        TLRPC.TL_messageEntityFormattedDate r2 = new TLRPC.TL_messageEntityFormattedDate();
        r2.date = r6;
        r2.flags = r7;
        r2.applyFlags();
        r0.setSpan(new FormattedDateSpan(r0.subSequence(r4, r5).toString(), r1, r2), r4, r5, 33);     // Catch: Exception -> L8
    L4:
        EditTextCaptionDelegate r3 = this.delegate;
        if (r3 == null) goto L11;
        r3.onSpansChanged();
        return;
    }

    private /* synthetic */ void lambda$makeSelectedUrl$6(int r9, int r10, Runnable r11, String r12) {
        Editable r0 = getText();
        CharacterStyle[] r1 = (CharacterStyle[]) r0.getSpans(r9, r10, CharacterStyle.class);
        if (r1 != null) goto L5;
    L26:
        r0.setSpan(createUrlSpan(r12), r9, r10, 33);     // Catch: Exception -> L25
    L19:
        EditTextCaptionDelegate r8 = this.delegate;
        if (r8 == null) goto L22;
        r8.onSpansChanged();
    L22:
        if (r11 == null) goto L32;
        r11.run();
        return;
    L32:
        return;
    L5:
        if (r1.length <= 0) goto L26;
        int r3 = r1.length;
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L26;
        CharacterStyle r5 = r1[r4];
        if ((r5 instanceof AnimatedEmojiSpan) == true) goto L17;
        if ((r5 instanceof QuoteSpan.QuoteStyleSpan) == true) goto L17;
        int r6 = r0.getSpanStart(r5);
        int r7 = r0.getSpanEnd(r5);
        r0.removeSpan(r5);
        if (r6 >= r9) goto L15;
        r0.setSpan(r5, r6, r9, 33);
    L15:
        if (r7 <= r10) goto L17;
        r0.setSpan(r5, r10, r7, 33);
    L17:
        r4 = r4 + 1;
        goto L7
    }

    private /* synthetic */ void lambda$showInputDialog$10(DialogInterface r1) {
        this.creationLinkDialog = null;
        requestFocus();
    }

    private /* synthetic */ void lambda$showInputDialog$7(boolean r2, EditTextBoldCursor r3, String r4, TextView r5) {
        ClipboardManager r1 = (ClipboardManager) getContext().getSystemService("clipboard");
        if (r2 == true) goto L5;
    L12:
        boolean r12 = false;
    L13:
        ViewPropertyAnimator r22 = r5.animate();
        float r32 = 1.0f;
        if (r12 == false) goto L16;
        float r42 = 1.0f;
    L17:
        ViewPropertyAnimator r23 = r22.alpha(r42);
        if (r12 == false) goto L20;
        float r52 = 1.0f;
    L21:
        ViewPropertyAnimator r24 = r23.scaleX(r52);
        if (r12 == true) goto L25;
        r32 = 0.7f;
    L25:
        r24.scaleY(r32).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(300).start();
        return;
    L20:
        r52 = 0.7f;
        goto L21
    L16:
        r42 = 0.0f;
        goto L17
    L5:
        if (TextUtils.isEmpty(r3.getText()) == false) goto L7;
    L8:
        if (r1 == null) goto L12;
        if (r1.hasPrimaryClip() == false) goto L12;
        r12 = true;
        goto L13
    L7:
        if (TextUtils.equals(r3.getText().toString(), r4) == false) goto L12;
        goto L8
    }

    private /* synthetic */ void lambda$showInputDialog$8(EditTextBoldCursor r2, Runnable r3, View r4) {
        CharSequence r1 = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip().getItemAt(0).coerceToText(getContext());     // Catch: Exception -> L5
    L7:
        if (r1 == null) goto L9;
        r2.setText(r1);
        r2.setSelection(0, r2.getText().length());
    L9:
        r3.run();
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        r1 = null;
        goto L7
    }

    private /* synthetic */ void lambda$translateSelected$5(int r2, int r3, CharSequence r4) {
        getText().replace(r2, r3, r4);
        setSelection(r2, r4.length() + r2);
    }

    private ActionMode.Callback overrideCallback(ActionMode.Callback r3) {
        return new AnonymousClass6(this, new AnonymousClass5(this, r3), r3);
    }

    private String readHtmlFromUri(Uri r7) {
        InputStream r6 = getContext().getContentResolver().openInputStream(r7);     // Catch: Exception -> L8
        if (r6 != null) goto L31;
        if (r6 == null) goto L10;
        r6.close();     // Catch: Exception -> L8
        return null;
    L10:
        return null;
    L31:
        StringBuilder r72 = new StringBuilder();     // Catch: Throwable -> L17
        byte[] r1 = new byte[4096];     // Catch: Throwable -> L17
        int r3 = 0;
    L12:
        int r4 = r6.read(r1);     // Catch: Throwable -> L17
        if (r4 == (-1)) goto L19;
        r72.append(new String(r1, 0, r4));     // Catch: Throwable -> L17
        r3 = r3 + r4;     // Catch: Throwable -> L17
        if (r3 < 32768) goto L12;
    L19:
        String r73 = r72.toString();     // Catch: Throwable -> L17
        r6.close();     // Catch: Exception -> L8
        return r73;
    L17:
        th = move-exception;
        r6.close();     // Catch: Throwable -> L24
    L26:
        throw th;     // Catch: Exception -> L8
    L24:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static int spanStyleFlags(TextStyleSpan r1) {
        int r12 = r1.getStyleFlags();
        if ((r12 & 512) != 0) goto L5;
        return r12;
    L5:
        return r12 | 256;
    }

    @Override // org.telegram.ui.ActionBar.FloatingToolbar.StyleDelegate
    public void addStyle(int r4, int r5, int r6) {
        Editable r0 = getText();
        if (r0 == null) goto L18;
        if (r5 < 0) goto L19;
        if (r6 < 0) goto L20;
        if (r5 >= r6) goto L22;
        int r62 = Math.min(r6, r0.length());
        if (r5 >= r62) goto L23;
        TextStyleSpan.TextStyleRun r1 = new TextStyleSpan.TextStyleRun();
        r1.flags = r4;
        MediaDataController.addStyleToText(new TextStyleSpan(r1), r5, r62, r0, true);
        if ((r4 & 256) == 0) goto L14;
        invalidateSpoilers();
    L14:
        EditTextCaptionDelegate r3 = this.delegate;
        if (r3 == null) goto L21;
        r3.onSpansChanged();
        return;
    L21:
        return;
    L23:
        return;
    L22:
        return;
    L20:
        return;
    L19:
        return;
    }

    public boolean closeCreationLinkDialog(boolean r2) {
        AlertDialog r0 = this.creationLinkDialog;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShowing() == false) goto L13;
        if (r2 == false) goto L12;
        this.creationLinkDialog.dismiss();
        return true;
    L12:
        return true;
    L13:
        return false;
    }

    public URLSpanReplacement createUrlSpan(String r1) {
        return new URLSpanReplacement(r1);
    }

    public boolean getAllowTextEntitiesIntersection() {
        return this.allowTextEntitiesIntersection;
    }

    public String getCaption() {
        return this.caption;
    }

    @Override // org.telegram.ui.ActionBar.FloatingToolbar.StyleDelegate
    public int getCurrentStyle(int r14, int r15) {
        Editable r13 = getText();
        if (r13 != null) goto L5;
        return 0;
    L5:
        int r142 = Math.max(0, r14);
        int r152 = Math.min(r15, r13.length());
        if (r142 < 0) goto L30;
        if (r152 < 0) goto L30;
        if (r142 >= r152) goto L30;
        TextStyleSpan[] r1 = (TextStyleSpan[]) r13.getSpans(r142, r152, TextStyleSpan.class);
        int[] r2 = STYLE_FLAGS;
        int r3 = r2.length;
        int r4 = 0;
        int r5 = 0;
    L11:
        if (r4 >= r3) goto L29;
        int r6 = r2[r4];
        int r9 = r142;
        boolean r8 = true;
    L13:
        if (r8 == false) goto L26;
        if (r9 >= r152) goto L26;
        r8 = false;
        int r10 = 0;
    L17:
        if (r10 >= r1.length) goto L13;
        if ((spanStyleFlags(r1[r10]) & r6) == 0) goto L25;
        int r11 = r13.getSpanStart(r1[r10]);
        int r12 = r13.getSpanEnd(r1[r10]);
        if (r11 > r9) goto L25;
        if (r12 <= r9) goto L25;
        r8 = true;
        r9 = r12;
    L25:
        r10 = r10 + 1;
    L26:
        if (r9 < r152) goto L28;
        r5 = r5 | r6;
    L28:
        r4 = r4 + 1;
        goto L11
    L29:
        return r5;
    L30:
        return 0;
    }

    public boolean isNearRightCaption(int r5) {
        Layout r0 = getLayout();
        if (r0 != null) goto L5;
    L13:
        return false;
    L5:
        if (r0.getLineCount() <= 0) goto L13;
        if (r0.getLineCount() <= 1) goto L11;
        return true;
    L11:
        if ((r0.getLineRight(0) + r5) < ((getWidth() - getPaddingLeft()) - getPaddingRight())) goto L13;
        return true;
    }

    public void makeSelectedBold() {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags |= 1;
        applyTextStyleToSelection(new TextStyleSpan(r0));
    }

    public void makeSelectedCode() {
        int r0 = this.selectionStart;
        if (r0 < 0) goto L7;
        int r1 = this.selectionEnd;
        if (r1 < 0) goto L7;
        this.selectionEnd = -1;
        this.selectionStart = -1;
    L8:
        if (r0 < r1) goto L10;
        return;
    L10:
        EditTextBoldCursor r2 = createCodeLanguageEditText();
        fillSelectedCodeLanguage(r2, r0, r1);
        if (this.adaptiveCreateLinkDialog == false) goto L13;
        AlertDialog.Builder r3 = new AlertDialogDecor.Builder(getContext(), this.resourcesProvider);
    L14:
        r3.setTitle(LocaleController.getString(R.string.CreateCode));
        FrameLayout r4 = new FrameLayout(getContext());
        r4.addView(r2, LayoutHelper.createFrame(-1, 36.0f, 1, 24.0f, 0.0f, 24.0f, 0.0f));
        r3.setView(r4);
        r3.setWidth(AndroidUtilities.dp(292.0f));
        r3.setPositiveButton(LocaleController.getString(R.string.OK), new EditTextCaption$$ExternalSyntheticLambda1(this, r0, r1, r2));
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r02 = r3.create();
        r02.setOnShowListener(new EditTextCaption$$ExternalSyntheticLambda2(r2));
        if (this.adaptiveCreateLinkDialog == false) goto L17;
        this.creationLinkDialog = r02;
        r02.setOnDismissListener(new EditTextCaption$$ExternalSyntheticLambda3(this));
        r02.showDelayed(250);
    L18:
        r2.setSelection(0, r2.getText().length());
        return;
    L17:
        r02.show();
        goto L18
    L13:
        r3 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
    L7:
        r0 = getSelectionStart();
        r1 = getSelectionEnd();
        goto L8
    }

    public void makeSelectedDate() {
        int r0 = this.selectionStart;
        if (r0 < 0) goto L7;
        int r1 = this.selectionEnd;
        if (r1 < 0) goto L7;
        this.selectionEnd = -1;
        this.selectionStart = -1;
    L8:
        AlertsCreator.createFormattedDatePickerDialog(getContext(), new EditTextCaption$$ExternalSyntheticLambda5(this, r0, r1), new EditTextCaption$$ExternalSyntheticLambda6(), this.resourcesProvider);
        return;
    L7:
        r0 = getSelectionStart();
        r1 = getSelectionEnd();
        goto L8
    }

    public void makeSelectedItalic() {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags |= 2;
        applyTextStyleToSelection(new TextStyleSpan(r0));
    }

    public void makeSelectedMono() {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags |= 4;
        applyTextStyleToSelection(new TextStyleSpan(r0));
    }

    public void makeSelectedQuote() {
        makeSelectedQuote(false);
    }

    public void makeSelectedQuote(boolean r4) {
        int r0 = this.selectionStart;
        if (r0 < 0) goto L7;
        int r1 = this.selectionEnd;
        if (r1 < 0) goto L7;
        this.selectionEnd = -1;
        this.selectionStart = -1;
    L8:
        int r42 = QuoteSpan.putQuoteToEditable(getText(), r0, r1, r4);
        if (r42 < 0) goto L11;
        setSelection(r42);
        resetFontMetricsCache();
    L11:
        invalidateQuotes(true);
        invalidateSpoilers();
        return;
    L7:
        r0 = getSelectionStart();
        r1 = getSelectionEnd();
        goto L8
    }

    public void makeSelectedRegular() {
        applyTextStyleToSelection(null);
    }

    public void makeSelectedSpoiler() {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags |= 256;
        applyTextStyleToSelection(new TextStyleSpan(r0));
        invalidateSpoilers();
    }

    public void makeSelectedStrike() {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags |= 8;
        applyTextStyleToSelection(new TextStyleSpan(r0));
    }

    public void makeSelectedUnderline() {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags |= 16;
        applyTextStyleToSelection(new TextStyleSpan(r0));
    }

    public void makeSelectedUrl() {
        makeSelectedUrl(null);
    }

    public void makeSelectedUrl(Runnable r11) {
        int r0 = this.selectionStart;
        if (r0 < 0) goto L7;
        int r1 = this.selectionEnd;
        if (r1 < 0) goto L7;
        this.selectionEnd = -1;
        this.selectionStart = -1;
    L8:
        String r2 = null;
        if (r0 < 0) goto L23;
        if (r1 <= r0) goto L23;
        Editable r3 = getText();
        URLSpan[] r4 = (URLSpan[]) r3.getSpans(r0, r1, URLSpan.class);
        int r5 = r4.length;
        int r7 = 0;
    L12:
        if (r7 >= r5) goto L19;
        URLSpan r8 = r4[r7];
        if (r3.getSpanStart(r8) > r0) goto L18;
        if (r3.getSpanEnd(r8) < r1) goto L18;
        r2 = r8.getURL();
    L18:
        r7 = r7 + 1;
    L19:
        if (r2 != null) goto L23;
        if (r4.length != 1) goto L23;
        r2 = r4[0].getURL();
    L23:
        boolean r72 = TextUtils.isEmpty(r2);
        String r42 = LocaleController.getString(R.string.CreateLink);
        String r52 = LocaleController.getString(R.string.URL);
        if (r72 == false) goto L27;
        r2 = "https://";
    L27:
        showInputDialog(r42, r52, r2, r72, new EditTextCaption$$ExternalSyntheticLambda4(this, r0, r1, r11));
        return;
    L7:
        r0 = getSelectionStart();
        r1 = getSelectionEnd();
        goto L8
    }

    public void notifySpansChanged() {
        EditTextCaptionDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onSpansChanged();
        return;
    }

    public void onContextMenuClose() {
    }

    public void onContextMenuOpen() {
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo r4) {
        InputConnection r0 = super.onCreateInputConnection(r4);
        if (r0 != null) goto L6;
        return null;
    L6:
        String[] r1 = EditorInfoCompat.getContentMimeTypes(r4);
        LinkedHashSet r2 = new LinkedHashSet();
        Collections.addAll(r2, r1);
        r2.add("text/html");
        r2.add("text/plain");
        EditorInfoCompat.setContentMimeTypes(r4, (String[]) r2.toArray(new String[0]));
        return new AnonymousClass7(this, r0, true);
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onDraw(Canvas r9) {
        r9.save();
        r9.translate(0.0f, this.offsetY);
        super.onDraw(r9);
    L9:
        e = move-exception;
        FileLog.e(e);
    L12:
        if (this.rightText != null) goto L14;
    L20:
        Canvas r3 = r9;
    L21:
        r3.restore();
        return;
    L14:
        if (length() == 0) goto L20;
        Layout r0 = getLayout();
        if (r0 == null) goto L20;
        if (r0.getLineCount() <= 0) goto L20;
        r3 = r9;
        this.rightText.draw(r3, r0.getLineRight(0), (getHeight() / 2.0f) + AndroidUtilities.dp(1.0f), this.hintColor, 1.0f);
        goto L21
    L4:
        if (this.captionLayout == null) goto L12;
        if (this.userNameLength != length()) goto L12;
        TextPaint r02 = getPaint();     // Catch: Exception -> L9
        int r1 = getPaint().getColor();     // Catch: Exception -> L9
        r02.setColor(this.hintColor);     // Catch: Exception -> L9
        r9.save();     // Catch: Exception -> L9
        r9.translate(this.xOffset, this.yOffset);     // Catch: Exception -> L9
        this.captionLayout.draw(r9);     // Catch: Exception -> L9
        r9.restore();     // Catch: Exception -> L9
        r02.setColor(r1);     // Catch: Exception -> L9
        goto L12
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r7) {
        super.onInitializeAccessibilityNodeInfo(r7);
        AccessibilityNodeInfoCompat r72 = AccessibilityNodeInfoCompat.wrap(r7);
        if (TextUtils.isEmpty(this.caption) == true) goto L5;
        r72.setHintText(this.caption);
    L5:
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> r0 = r72.getActionList();
        int r1 = r0.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat r3 = r0.get(r2);
        if (r3.getId() == 268435456) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        r72.removeAction(r3);
    L12:
        if (hasSelection() == false) goto L17;
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_spoiler, LocaleController.getString(R.string.Spoiler)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_bold, LocaleController.getString(R.string.Bold)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_italic, LocaleController.getString(R.string.Italic)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_bold_italic, LocaleController.getString(R.string.BoldItalic)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_mono, LocaleController.getString(R.string.Mono)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_code, LocaleController.getString(R.string.CodeBlock)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_strike, LocaleController.getString(R.string.Strike)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_underline, LocaleController.getString(R.string.Underline)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_link, LocaleController.getString(R.string.CreateLink)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_regular, LocaleController.getString(R.string.Regular)));
        r72.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.menu_date, LocaleController.getString(R.string.FormattedDate)));
        return;
    }

    public void onLineCountChanged(int r1, int r2) {
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onMeasure(int r13, int r14) {
    L8:
        e = move-exception;
        setMeasuredDimension(View.MeasureSpec.getSize(r13), AndroidUtilities.dp(51.0f));
        FileLog.e(e);
    L17:
        int r132 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        Editable r142 = getText();
        this.captionLayout = null;
        String r0 = this.caption;
        this.lastLayoutText = r0;
        this.lastLayoutWidth = r132;
        if (r0 != null) goto L20;
        return;
    L20:
        if (r0.length() > 0) goto L22;
        return;
    L22:
        if (r142.length() > 1) goto L24;
        return;
    L24:
        if (r142.charAt(0) != '@') goto L43;
        int r02 = TextUtils.indexOf(r142, ' ');
        if (r02 == (-1)) goto L44;
        TextPaint r6 = getPaint();
        r6.setTypeface(AndroidUtilities.regular());
        CharSequence r1 = r142.subSequence(0, r02 + 1);
        int r143 = (int) Math.ceil(r6.measureText(r142, 0, r0));
        int r7 = Math.max(0, r132 - r143);
        this.userNameLength = r1.length();
        CharSequence r5 = TextUtils.ellipsize(this.caption, r6, r7, TextUtils.TruncateAt.END);
        this.xOffset = r143;
        StaticLayout r4 = new StaticLayout(r5, r6, r7, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L33
        this.captionLayout = r4;     // Catch: Exception -> L33
        if (r4.getLineCount() <= 0) goto L31;
        this.xOffset = (int) (this.xOffset + (-this.captionLayout.getLineLeft(0)));     // Catch: Exception -> L33
    L31:
        this.yOffset = ((getMeasuredHeight() - this.captionLayout.getLineBottom(0)) / 2) + AndroidUtilities.dp(0.5f);     // Catch: Exception -> L33
        return;
    L33:
        e = move-exception;
        FileLog.e(e);
        return;
    L44:
        return;
    L43:
        return;
    L4:
        if (getMeasuredWidth() == 0) goto L6;
    L10:
        boolean r03 = false;
    L11:
        this.isInitLineCount = r03;     // Catch: Exception -> L8
        super.onMeasure(r13, r14);     // Catch: Exception -> L8
        if (this.isInitLineCount == false) goto L14;
        this.lineCount = getLineCount();     // Catch: Exception -> L8
    L14:
        this.isInitLineCount = false;     // Catch: Exception -> L8
        goto L17
    L6:
        if (getMeasuredHeight() != 0) goto L10;
        r03 = true;
        goto L11
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int r7) {
        if (r7 != 16908322) goto L29;
        if (handleRichPaste(((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip()) == false) goto L27;
        return true;
    L27:
        return super.onTextContextMenuItem(r7);
    L29:
        if (r7 != 16908321) goto L16;
        int r0 = Math.max(0, getSelectionStart());
        int r2 = Math.min(getText().length(), getSelectionEnd());
        AndroidUtilities.addToClipboard(getText().subSequence(r0, r2));     // Catch: Exception -> L28
        AndroidUtilities.findActivity(getContext()).closeContextMenu();     // Catch: Exception -> L28
        FloatingActionMode r3 = this.floatingActionMode;     // Catch: Exception -> L28
        if (r3 == null) goto L13;
        r3.finish();     // Catch: Exception -> L28
    L13:
        setSelection(r0, r2);     // Catch: Exception -> L28
        return true;
    L16:
        if (r7 != 16908320) goto L27;
        int r02 = Math.max(0, getSelectionStart());
        int r32 = Math.min(getText().length(), getSelectionEnd());
        AndroidUtilities.addToClipboard(getText().subSequence(r02, r32));     // Catch: Exception -> L28
        SpannableStringBuilder r4 = new SpannableStringBuilder();     // Catch: Exception -> L28
        if (r02 == 0) goto L22;
        r4.append(getText().subSequence(0, r02));     // Catch: Exception -> L28
    L22:
        if (r32 == getText().length()) goto L24;
        r4.append(getText().subSequence(r32, getText().length()));     // Catch: Exception -> L28
    L24:
        setText(r4);     // Catch: Exception -> L28
        setSelection(r02);     // Catch: Exception -> L28
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean r1) {
        super.onWindowFocusChanged(r1);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        FileLog.e(th);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r2, Bundle r3) {
        if (performMenuAction(r2) == false) goto L5;
        return true;
    L5:
        if (super.performAccessibilityAction(r2, r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean performMenuAction(int r3) {
        if (r3 != R.id.menu_regular) goto L7;
        makeSelectedRegular();
        return true;
    L7:
        if (r3 != R.id.menu_bold) goto L11;
        makeSelectedBold();
        return true;
    L11:
        if (r3 != R.id.menu_italic) goto L15;
        makeSelectedItalic();
        return true;
    L15:
        if (r3 != R.id.menu_bold_italic) goto L19;
        makeSelectedBold();
        makeSelectedItalic();
        return true;
    L19:
        if (r3 != R.id.menu_mono) goto L23;
        makeSelectedMono();
        return true;
    L23:
        if (r3 != R.id.menu_code) goto L27;
        makeSelectedCode();
        return true;
    L27:
        if (r3 != R.id.menu_link) goto L31;
        makeSelectedUrl();
        return true;
    L31:
        if (r3 != R.id.menu_strike) goto L35;
        makeSelectedStrike();
        return true;
    L35:
        if (r3 != R.id.menu_underline) goto L39;
        makeSelectedUnderline();
        return true;
    L39:
        if (r3 != R.id.menu_spoiler) goto L43;
        makeSelectedSpoiler();
        return true;
    L43:
        if (r3 != R.id.menu_quote) goto L47;
        makeSelectedQuote();
        return true;
    L47:
        if (r3 != R.id.menu_date) goto L51;
        makeSelectedDate();
        return true;
    L51:
        if (r3 != R.id.menu_translate) goto L54;
        translateSelected();
        return true;
    L54:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.FloatingToolbar.StyleDelegate
    public void removeStyle(int r12, int r13, int r14) {
        Editable r0 = getText();
        if (r0 == null) goto L38;
        if (r13 < 0) goto L39;
        if (r14 < 0) goto L40;
        if (r13 >= r14) goto L42;
        int r142 = Math.min(r14, r0.length());
        int r1 = r12 & 256;
        if (r1 == 0) goto L11;
        r12 = r12 | 512;
    L11:
        TextStyleSpan[] r2 = (TextStyleSpan[]) r0.getSpans(r13, r142, TextStyleSpan.class);
        int r3 = 0;
    L13:
        if (r3 >= r2.length) goto L27;
        TextStyleSpan r4 = r2[r3];
        int r5 = r4.getStyleFlags();
        if ((r5 & r12) == 0) goto L26;
        int r6 = r0.getSpanStart(r4);
        int r7 = r0.getSpanEnd(r4);
        r0.removeSpan(r4);
        if (r6 >= r13) goto L20;
        r0.setSpan(new TextStyleSpan(new TextStyleSpan.TextStyleRun(r4.getTextStyleRun())), r6, r13, 33);
    L20:
        if (r7 <= r142) goto L22;
        r0.setSpan(new TextStyleSpan(new TextStyleSpan.TextStyleRun(r4.getTextStyleRun())), r142, r7, 33);
    L22:
        int r62 = Math.max(r6, r13);
        int r72 = Math.min(r7, r142);
        int r52 = r5 & (~r12);
        if (r52 == 0) goto L26;
        if (r62 >= r72) goto L26;
        TextStyleSpan.TextStyleRun r9 = new TextStyleSpan.TextStyleRun(r4.getTextStyleRun());
        r9.flags = r52;
        r0.setSpan(new TextStyleSpan(r9), r62, r72, 33);
    L26:
        r3 = r3 + 1;
        goto L13
    L27:
        if (r1 == 0) goto L29;
        invalidateSpoilers();
    L29:
        EditTextCaptionDelegate r11 = this.delegate;
        if (r11 == null) goto L41;
        r11.onSpansChanged();
        return;
    L41:
        return;
    L42:
        return;
    L40:
        return;
    L39:
        return;
    }

    public void setAllowTextEntitiesIntersection(boolean r1) {
        this.allowTextEntitiesIntersection = r1;
    }

    public void setCaption(String r3) {
        String r0 = this.caption;
        if (r0 != null) goto L5;
    L6:
        if (r3 != null) goto L8;
        return;
    L8:
        if (r3.length() == 0) goto L20;
    L9:
        String r02 = this.caption;
        if (r02 != null) goto L12;
    L14:
        this.caption = r3;
        if (r3 == null) goto L17;
        this.caption = r3.replace('\n', ' ');
    L17:
        requestLayout();
        return;
    L12:
        if (r02.equals(r3) == false) goto L14;
        return;
    L20:
        return;
    L5:
        if (r0.length() != 0) goto L9;
        goto L6
    }

    public void setDelegate(EditTextCaptionDelegate r1) {
        this.delegate = r1;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor
    public void setHintColor(int r1) {
        super.setHintColor(r1);
        this.hintColor = r1;
        invalidate();
    }

    public void setRightText(CharSequence r4) {
        this.rightText = new Text(r4, 16.0f, getTypeface());
    }

    public void setSelectionOverride(int r1, int r2) {
        this.selectionStart = r1;
        this.selectionEnd = r2;
    }

    public void showInputDialog(String r8, String r9, String r10, boolean r11, InputDialogCallback r12) {
        showInputDialog(r8, r9, r10, r11, this.adaptiveCreateLinkDialog, r12);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [org.telegram.ui.ActionBar.AlertDialog$Builder] */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.view.View, android.view.ViewGroup, android.widget.FrameLayout] */
    public void showInputDialog(String r20, String r21, String r22, boolean r23, boolean r24, InputDialogCallback r25) {
        if (r24 == false) goto L6;
        Object r0 = new AlertDialogDecor.Builder(getContext(), this.resourcesProvider);
    L5:
        ?? r7 = r0;
        r7.setTitle(r20);
        ?? r8 = new FrameLayout(getContext());
        AnonymousClass3 r4 = new AnonymousClass3(this, getContext());
        if (r22 != null) goto L10;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        r4.setTextSize(1, 18.0f);
        r4.setText(r5);
        r4.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r4.setHintText(r21);
        r4.setHeaderHintColor(getThemedColor(Theme.key_windowBackgroundWhiteBlueHeader));
        r4.setSingleLine(true);
        r4.setFocusable(true);
        r4.setTransformHintToHeader(true);
        r4.setLineColors(getThemedColor(Theme.key_windowBackgroundWhiteInputField), getThemedColor(Theme.key_windowBackgroundWhiteInputFieldActivated), getThemedColor(Theme.key_text_RedRegular));
        r4.setImeOptions(6);
        r4.setBackgroundDrawable(null);
        r4.requestFocus();
        r4.setPadding(0, 0, 0, 0);
        r4.setHighlightColor(getThemedColor(Theme.key_chat_inTextSelectionHighlight));
        r4.setHandlesColor(getThemedColor(Theme.key_chat_TextSelectionCursor));
        r8.addView(r4, LayoutHelper.createFrame(-1, -1, 119));
        TextView r6 = new TextView(getContext());
        r6.setTextSize(1, 12.0f);
        r6.setTypeface(AndroidUtilities.bold());
        r6.setText(LocaleController.getString(R.string.Paste));
        r6.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        r6.setGravity(17);
        int r02 = getThemedColor(Theme.key_windowBackgroundWhiteBlueText2);
        r6.setTextColor(r02);
        r6.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(r02, 0.12f), Theme.multAlpha(r02, 0.15f)));
        ScaleStateListAnimator.apply(r6, 0.1f, 1.5f);
        r8.addView(r6, LayoutHelper.createFrame(-2, 26.0f, 21, 0.0f, 0.0f, 24.0f, 3.0f));
        if (r23 == false) goto L14;
        int r03 = 0;
    L15:
        r6.setVisibility(r03);
        EditTextCaption$$ExternalSyntheticLambda7 r1 = new EditTextCaption$$ExternalSyntheticLambda7(this, r23, r4, r5, r6);
        r6.setOnClickListener(new EditTextCaption$$ExternalSyntheticLambda8(this, r4, r1));
        r4.addTextChangedListener(new AnonymousClass4(this, r1));
        ClipboardManager r04 = (ClipboardManager) getContext().getSystemService("clipboard");
        if (r23 == true) goto L18;
    L30:
        r1.run();
        r7.setView(r8);
        r7.setPositiveButton(LocaleController.getString(R.string.OK), new EditTextCaption$$ExternalSyntheticLambda9(r25, r4));
        r7.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        if (r24 == false) goto L33;
        AlertDialog r05 = r7.create();
        this.creationLinkDialog = r05;
        r05.setOnDismissListener(new EditTextCaption$$ExternalSyntheticLambda10(this));
        this.creationLinkDialog.setOnShowListener(new EditTextCaption$$ExternalSyntheticLambda11(r4));
        this.creationLinkDialog.showDelayed(250);
    L34:
        ViewGroup.MarginLayoutParams r06 = (ViewGroup.MarginLayoutParams) r4.getLayoutParams();
        if (r06 != null) goto L37;
    L40:
        r4.setSelection(0, r4.getText().length());
        return;
    L37:
        if ((r06 instanceof FrameLayout.LayoutParams) == false) goto L39;
        ((FrameLayout.LayoutParams) r06).gravity = 1;
    L39:
        int r12 = AndroidUtilities.dp(24.0f);
        r06.leftMargin = r12;
        r06.rightMargin = r12;
        r06.height = AndroidUtilities.dp(36.0f);
        r4.setLayoutParams(r06);
        goto L40
    L33:
        r7.show().setOnShowListener(new EditTextCaption$$ExternalSyntheticLambda12(r4));
        goto L34
    L18:
        if (TextUtils.equals(r5, "https://") == false) goto L20;
    L21:
        if (r04 == null) goto L30;
        if (r04.hasPrimaryClip() == false) goto L30;
        CharSequence r07 = r04.getPrimaryClip().getItemAt(0).coerceToText(getContext());     // Catch: Exception -> L26
    L28:
        if (r07 == null) goto L30;
        r4.setText(r07);
        r4.setSelection(0, r4.getText().length());
    L26:
        e = move-exception;
        FileLog.e(e);
        r07 = null;
        goto L28
    L20:
        if (TextUtils.equals(r5, "http://") == false) goto L30;
    L14:
        r03 = 8;
        goto L15
    L10:
        r5 = r22;
        goto L11
    L6:
        r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        goto L5
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, android.view.View
    public ActionMode startActionMode(ActionMode.Callback r1) {
        return super.startActionMode(overrideCallback(r1));
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, android.view.View
    public ActionMode startActionMode(ActionMode.Callback r1, int r2) {
        return super.startActionMode(overrideCallback(r1), r2);
    }

    public void toggleStyleForSelection(int r6) {
        if (getText() == null) goto L29;
        int r0 = getSelectionStart();
        int r1 = getSelectionEnd();
        if (r0 < 0) goto L28;
        if (r1 < 0) goto L30;
        if (r0 <= r1) goto L11;
        r1 = r0;
        r0 = r1;
    L11:
        if (r0 < r1) goto L14;
        return;
    L14:
        if ((getCurrentStyle(r0, r1) & r6) != 0) goto L26;
        int r2 = 4;
        if (r6 != 4) goto L19;
        r2 = 49435;
    L24:
        removeStyle(r2, r0, r1);
        addStyle(r6, r0, r1);
        return;
    L19:
        if (r6 != 16384) goto L22;
        r2 = 32772;
        goto L24
    L22:
        if (r6 != 32768) goto L24;
        r2 = LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD;
        goto L24
    L26:
        removeStyle(r6, r0, r1);
        return;
    L30:
        return;
    L28:
        return;
    }

    public void translateSelected() {
        int r0 = this.selectionStart;
        if (r0 < 0) goto L7;
        int r1 = this.selectionEnd;
        if (r1 < 0) goto L7;
        this.selectionEnd = -1;
        this.selectionStart = -1;
    L8:
        CharSequence r2 = getText().subSequence(r0, r1);
        BaseFragment r3 = LaunchActivity.getSafeLastFragment();
        Context r5 = getContext();
        if (r3 == null) goto L11;
        Theme.ResourcesProvider r32 = r3.getResourceProvider();
    L12:
        new TranslateAlert3(r5, r32).setText(r2).setOnUse(new EditTextCaption$$ExternalSyntheticLambda0(this, r0, r1)).show();
        setSelection(r0, r1);
        return;
    L11:
        r32 = null;
    L7:
        r0 = getSelectionStart();
        r1 = getSelectionEnd();
        goto L8
    }
}
