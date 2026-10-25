package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextCheckBoxCell extends FrameLayout {
    private CheckBoxSquare checkBox;
    private boolean needDivider;
    private TextView textView;

    public TextCheckBoxCell(Context r12, boolean r13, boolean r14) {
        super(r12);
        boolean r0 = LocaleController.isRTL;
        if (r14 == false) goto L5;
        r0 = !r0;
    L5:
        TextView r142 = new TextView(r12);
        this.textView = r142;
        if (r13 == false) goto L8;
        int r1 = Theme.key_dialogTextBlack;
    L9:
        r142.setTextColor(Theme.getColor(r1));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r143 = this.textView;
        int r2 = 3;
        if (LocaleController.isRTL == false) goto L12;
        int r15 = 5;
    L13:
        r143.setGravity(r15 | 16);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r144 = this.textView;
        if (r0 == false) goto L16;
        int r16 = 5;
    L17:
        int r6 = r16 | 48;
        if (r0 == false) goto L20;
        float r7 = 66.0f;
    L21:
        if (r0 == false) goto L23;
        float r9 = 21.0f;
    L24:
        addView(r144, LayoutHelper.createFrame(-1, -1.0f, r6, r7, 0.0f, r9, 0.0f));
        CheckBoxSquare r145 = new CheckBoxSquare(r12, r13);
        this.checkBox = r145;
        r145.setDuplicateParentStateEnabled(false);
        this.checkBox.setFocusable(false);
        this.checkBox.setFocusableInTouchMode(false);
        this.checkBox.setClickable(false);
        CheckBoxSquare r122 = this.checkBox;
        if (r0 == true) goto L28;
        r2 = 5;
    L28:
        addView(r122, LayoutHelper.createFrame(18, 18.0f, r2 | 16, 21.0f, 0.0f, 21.0f, 0.0f));
        return;
    L23:
        r9 = 66.0f;
        goto L24
    L20:
        r7 = 21.0f;
        goto L21
    L16:
        r16 = 3;
        goto L17
    L12:
        r15 = 3;
        goto L13
    L8:
        r1 = Theme.key_windowBackgroundWhiteBlackText;
        goto L9
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.checkBox.invalidate();
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
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

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.CheckBox");
        r2.setCheckable(true);
        r2.setChecked(isChecked());
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void setChecked(boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    public void setTextAndCheck(String r2, boolean r3, boolean r4) {
        this.textView.setText(r2);
        this.checkBox.setChecked(r3, false);
        this.needDivider = r4;
        setWillNotDraw(!r4);
    }
}
