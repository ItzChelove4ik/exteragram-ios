package org.telegram.ui.Components.Reactions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextCaption;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public abstract class CustomReactionEditText extends EditTextCaption {
    private final GestureDetectorCompat gestureDetector;
    private int maxLength;
    private Runnable onFocused;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.Components.Reactions.CustomReactionEditText$1, reason: invalid class name */
    public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ CustomReactionEditText this$0;

        public AnonymousClass1(CustomReactionEditText r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent r1) {
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$QjihQ9Vt6CPxTZOv_VkUBVcXe7I(CustomReactionEditText r0, View r1, boolean r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$b0eHPHLgS24iZdIyTKmqPRjpmzo(CustomReactionEditText r0, AddReactionsSpan r1) {
        r0.lambda$removeReactionsSpan$1(r1);
    }

    public CustomReactionEditText(Context r4, Theme.ResourcesProvider r5, int r6) {
        super(r4, r5);
        this.resourcesProvider = r5;
        this.gestureDetector = new GestureDetectorCompat(getContext(), new AnonymousClass1(this));
        setBackground(null);
        setIncludeFontPadding(true);
        setShowSoftInputOnFocus(false);
        setSingleLine(false);
        setMaxLines(50);
        this.maxLength = r6;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(r6)});
        setTextSize(1, 22.0f);
        setGravity(80);
        setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(12.0f));
        setTextColor(getThemedColor(Theme.key_chat_messagePanelText));
        setLinkTextColor(getThemedColor(Theme.key_chat_messageLinkOut));
        setHighlightColor(getThemedColor(Theme.key_chat_inTextSelectionHighlight));
        int r62 = Theme.key_chat_messagePanelHint;
        setHintColor(getThemedColor(r62));
        setHintTextColor(getThemedColor(r62));
        setCursorColor(getThemedColor(Theme.key_chat_messagePanelCursor));
        setHandlesColor(getThemedColor(Theme.key_chat_TextSelectionCursor));
        if (Build.VERSION.SDK_INT < 28) goto L5;
        setFallbackLineSpacing(false);
    L5:
        setOnFocusChangeListener(new CustomReactionEditText$$ExternalSyntheticLambda0(this));
        setTextIsSelectable(true);
        setLongClickable(false);
        setFocusableInTouchMode(false);
    }

    private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
        if (r2 == false) goto L7;
        removeReactionsSpan(true);
        Runnable r0 = this.onFocused;
        if (r0 == null) goto L9;
        r0.run();
        return;
    L9:
        return;
    L7:
        addReactionsSpan();
    }

    private /* synthetic */ void lambda$removeReactionsSpan$1(AddReactionsSpan r4) {
        getText().delete(getText().getSpanStart(r4), getText().getSpanEnd(r4));
        setCursorVisible(true);
        setLongClickable(true);
    }

    public void addReactionsSpan() {
        setLongClickable(false);
        SpannableStringBuilder r1 = new SpannableStringBuilder(getText());
        if (((AddReactionsSpan[]) r1.getSpans(0, r1.length(), AddReactionsSpan.class)).length != 0) goto L6;
        SpannableStringBuilder r12 = new SpannableStringBuilder("x");
        AddReactionsSpan r2 = new AddReactionsSpan(15.0f, this.resourcesProvider);
        r2.show(this);
        r12.setSpan(r2, 0, r12.length(), 33);
        setText(getText().append(r12));
        return;
    }

    @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.gestureDetector.onTouchEvent(r2) == false) goto L9;
        if (isLongClickable() == true) goto L9;
        return false;
    L9:
        return super.dispatchTouchEvent(r2);
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor
    public void extendActionMode(ActionMode r2, Menu r3) {
        r3.clear();
        int r1 = R.id.menu_delete;
        r3.add(r1, r1, 0, LocaleController.getString(R.string.Delete));
    }

    public int getEditTextSelectionEnd() {
        int r0 = getSelectionEnd();
        if (r0 >= 0) goto L6;
        return 0;
    L6:
        return r0;
    }

    public int getEditTextSelectionStart() {
        int r0 = getSelectionStart();
        if (r0 >= 0) goto L6;
        return 0;
    L6:
        return r0;
    }

    public Paint.FontMetricsInt getFontMetricsInt() {
        return getPaint().getFontMetricsInt();
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onSelectionChanged(int r3, int r4) {
        super.onSelectionChanged(r3, r4);
        if (hasSelection() == true) goto L5;
        return;
    L5:
        if (((AddReactionsSpan[]) getText().getSpans(r3, r4, AddReactionsSpan.class)).length == 0) goto L9;
        setSelection(r3, r4 - 1);
        return;
    }

    public void removeReactionsSpan(boolean r7) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(getText());
        AddReactionsSpan[] r02 = (AddReactionsSpan[]) r0.getSpans(0, r0.length(), AddReactionsSpan.class);
        int r1 = r02.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        AddReactionsSpan r4 = r02[r2];
        CustomReactionEditText$$ExternalSyntheticLambda1 r5 = new CustomReactionEditText$$ExternalSyntheticLambda1(this, r4);
        if (r7 == false) goto L7;
        setCursorVisible(false);
        r4.hide(this, r5);
    L8:
        r2 = r2 + 1;
        goto L3
    L7:
        r5.run();
        goto L8
    }

    public void setMaxLength(int r3) {
        if (this.maxLength == r3) goto L6;
        this.maxLength = r3;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(r3)});
        return;
    }

    public void setOnFocused(Runnable r1) {
        this.onFocused = r1;
    }
}
