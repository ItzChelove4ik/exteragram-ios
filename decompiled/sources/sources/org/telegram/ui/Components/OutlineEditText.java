package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class OutlineEditText extends OutlineTextContainerView {
    EditTextBoldCursor editText;

    /* renamed from: org.telegram.ui.Components.OutlineEditText$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        final /* synthetic */ OutlineEditText this$0;

        public AnonymousClass1(OutlineEditText r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean r1, int r2, Rect r3) {
            super.onFocusChanged(r1, r2, r3);
            OutlineEditText r22 = this.this$0;
            if (r1 == false) goto L5;
        L8:
            float r0 = 1.0f;
        L9:
            r22.animateSelection(r0);
            return;
        L5:
            if (isFocused() == true) goto L8;
            r0 = 0.0f;
            goto L9
        }
    }

    public OutlineEditText(Context r4) {
        super(r4);
        AnonymousClass1 r0 = new AnonymousClass1(this, r4);
        this.editText = r0;
        r0.setTextSize(1, 18.0f);
        this.editText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.editText.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        this.editText.setBackground(null);
        this.editText.setSingleLine(true);
        this.editText.setInputType(1);
        this.editText.setTypeface(Typeface.DEFAULT);
        this.editText.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated));
        this.editText.setCursorWidth(1.5f);
        this.editText.setPadding(AndroidUtilities.dp(15.0f), 0, AndroidUtilities.dp(15.0f), 0);
        attachEditText(this.editText);
        addView(this.editText, LayoutHelper.createFrame(-1, -2, 16));
    }

    public EditTextBoldCursor getEditText() {
        return this.editText;
    }

    public void setHint(String r1) {
        setText(r1);
    }
}
