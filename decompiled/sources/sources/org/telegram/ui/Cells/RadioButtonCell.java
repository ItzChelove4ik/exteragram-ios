package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes6.dex */
public class RadioButtonCell extends FrameLayout {
    public int itemId;
    private boolean needDivider;
    private RadioButton radioButton;
    private TextView textView;
    public TextView valueTextView;

    public RadioButtonCell(Context r2) {
        this(r2, false);
    }

    public RadioButtonCell(Context r23, boolean r24) {
        super(r23);
        RadioButton r2 = new RadioButton(r23);
        this.radioButton = r2;
        r2.setSize(AndroidUtilities.dp(20.0f));
        RadioButton r22 = this.radioButton;
        if (r24 == false) goto L5;
        r22.setColor(Theme.getColor(Theme.key_dialogRadioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
    L6:
        RadioButton r25 = this.radioButton;
        boolean r3 = LocaleController.isRTL;
        int r4 = 3;
        if (r3 == false) goto L9;
        int r6 = 5;
    L10:
        int r9 = r6 | 16;
        int r62 = 20;
        if (r3 == false) goto L13;
        int r7 = 0;
    L14:
        float r10 = r7;
        if (r3 == true) goto L18;
        r62 = 0;
    L18:
        addView(r25, LayoutHelper.createFrame(22, 22.0f, r9, r10, 0.0f, r62, 0.0f));
        TextView r26 = new TextView(r23);
        this.textView = r26;
        if (r24 == false) goto L21;
        r26.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
    L22:
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r27 = this.textView;
        if (LocaleController.isRTL == false) goto L25;
        int r32 = 5;
    L26:
        r27.setGravity(r32 | 16);
        TextView r28 = this.textView;
        boolean r33 = LocaleController.isRTL;
        if (r33 == false) goto L29;
        int r72 = 5;
    L30:
        int r17 = r72 | 48;
        int r73 = 23;
        int r8 = 61;
        if (r33 == false) goto L33;
        int r92 = 23;
    L34:
        float r93 = r92;
        if (r33 == false) goto L37;
        r73 = 61;
    L37:
        addView(r28, LayoutHelper.createFrame(-2, -2.0f, r17, r93, 10.0f, r73, 0.0f));
        TextView r29 = new TextView(r23);
        this.valueTextView = r29;
        if (r24 == false) goto L40;
        r29.setTextColor(Theme.getColor(Theme.key_dialogTextGray2));
    L41:
        this.valueTextView.setTextSize(1, 13.0f);
        TextView r1 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L44;
        int r210 = 5;
    L45:
        r1.setGravity(r210);
        this.valueTextView.setLines(0);
        this.valueTextView.setMaxLines(0);
        this.valueTextView.setSingleLine(false);
        this.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(12.0f));
        TextView r12 = this.valueTextView;
        boolean r211 = LocaleController.isRTL;
        if (r211 == false) goto L48;
        r4 = 5;
    L48:
        int r11 = r4 | 48;
        if (r211 == false) goto L51;
        int r42 = 17;
    L52:
        float r122 = r42;
        if (r211 == true) goto L56;
        r8 = 17;
    L56:
        addView(r12, LayoutHelper.createFrame(-2, -2.0f, r11, r122, 35.0f, r8, 0.0f));
        return;
    L51:
        r42 = 61;
        goto L52
    L44:
        r210 = 3;
        goto L45
    L40:
        r29.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        goto L41
    L33:
        r92 = 61;
        goto L34
    L29:
        r72 = 3;
        goto L30
    L25:
        r32 = 3;
        goto L26
    L21:
        r26.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        goto L22
    L13:
        r7 = 20;
        goto L14
    L9:
        r6 = 3;
        goto L10
    L5:
        r22.setColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_radioBackgroundChecked));
        goto L6
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        if (this.needDivider == false) goto L14;
        float r1 = 60.0f;
        if (LocaleController.isRTL == false) goto L7;
        float r0 = 0.0f;
    L8:
        float r4 = AndroidUtilities.dp(r0);
        float r5 = getHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == true) goto L12;
        r1 = 0.0f;
    L12:
        r10.drawLine(r4, r5, r02 - AndroidUtilities.dp(r1), getHeight() - 1, Theme.dividerPaint);
        return;
    L7:
        r0 = 60.0f;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.RadioButton");
        r2.setCheckable(true);
        r2.setChecked(this.radioButton.isChecked());
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r1), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void setChecked(boolean r1, boolean r2) {
        this.radioButton.setChecked(r1, r2);
    }

    public void setTextAndValue(CharSequence r2, CharSequence r3, boolean r4, boolean r5) {
        this.textView.setText(r2);
        this.valueTextView.setText(r3);
        this.radioButton.setChecked(r5, false);
        this.needDivider = r4;
    }
}
