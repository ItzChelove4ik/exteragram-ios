package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class EditTextSettingsCell extends FrameLayout {
    private boolean needDivider;
    private EditTextBoldCursor textView;

    public EditTextSettingsCell(Context r10) {
        super(r10);
        EditTextBoldCursor r0 = new EditTextBoldCursor(r10);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        EditTextBoldCursor r102 = this.textView;
        int r1 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r02 = 5;
    L6:
        r102.setGravity(r02 | 16);
        this.textView.setBackgroundDrawable(null);
        this.textView.setPadding(0, 0, 0, 0);
        EditTextBoldCursor r103 = this.textView;
        r103.setInputType(r103.getInputType() | 16384);
        EditTextBoldCursor r104 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        r1 = 5;
    L9:
        addView(r104, LayoutHelper.createFrame(-1, -1.0f, r1 | 48, 21.0f, 0.0f, 21.0f, 0.0f));
        return;
    L5:
        r02 = 3;
        goto L6
    }

    public String getText() {
        return this.textView.getText().toString();
    }

    public EditTextBoldCursor getTextView() {
        return this.textView;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
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
        int r1 = AndroidUtilities.dp(20.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(20.0f);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.getSize(r2), AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0));
        int r22 = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(42.0f);
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
    }

    public void setText(String r2, boolean r3) {
        this.textView.setText(r2);
        this.needDivider = r3;
        setWillNotDraw(!r3);
    }

    public void setTextAndHint(String r2, String r3, boolean r4) {
        this.textView.setText(r2);
        this.textView.setHint(r3);
        this.needDivider = r4;
        setWillNotDraw(!r4);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }
}
