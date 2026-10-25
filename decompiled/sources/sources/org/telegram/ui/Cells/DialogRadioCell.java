package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes6.dex */
public class DialogRadioCell extends FrameLayout {
    public int itemId;
    private boolean needDivider;
    private RadioButton radioButton;
    private TextView textView;
    private TextView valueTextView;

    public DialogRadioCell(Context r2) {
        this(r2, false);
    }

    public DialogRadioCell(Context r18, boolean r19) {
        super(r18);
        TextView r2 = new TextView(r18);
        this.textView = r2;
        if (r19 == false) goto L5;
        r2.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
    L6:
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r22 = this.textView;
        TextUtils.TruncateAt r5 = TextUtils.TruncateAt.END;
        r22.setEllipsize(r5);
        TextView r23 = this.textView;
        int r7 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r6 = 5;
    L10:
        r23.setGravity(r6 | 16);
        TextView r24 = this.textView;
        boolean r62 = LocaleController.isRTL;
        if (r62 == false) goto L13;
        int r9 = 5;
    L14:
        int r12 = r9 | 48;
        if (r62 == false) goto L17;
        float r13 = 61.0f;
    L18:
        if (r62 == false) goto L20;
        float r15 = 23.0f;
    L21:
        addView(r24, LayoutHelper.createFrame(-1, -1.0f, r12, r13, 0.0f, r15, 0.0f));
        TextView r25 = new TextView(r18);
        this.valueTextView = r25;
        if (r19 == false) goto L24;
        r25.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
    L25:
        this.valueTextView.setTextSize(1, 16.0f);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setEllipsize(r5);
        TextView r26 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L28;
        int r3 = 3;
    L29:
        r26.setGravity(r3 | 16);
        this.valueTextView.setVisibility(8);
        TextView r27 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L32;
        int r32 = 3;
    L33:
        addView(r27, LayoutHelper.createFrame(-2, -1.0f, r32 | 48, 23.0f, 0.0f, 23.0f, 0.0f));
        RadioButton r28 = new RadioButton(r18);
        this.radioButton = r28;
        r28.setSize(AndroidUtilities.dp(20.0f));
        RadioButton r1 = this.radioButton;
        if (r19 == false) goto L36;
        r1.setColor(Theme.getColor(Theme.key_dialogRadioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
    L37:
        RadioButton r14 = this.radioButton;
        if (LocaleController.isRTL == true) goto L41;
        r7 = 5;
    L41:
        addView(r14, LayoutHelper.createFrame(22, 22.0f, r7 | 48, 20.0f, 15.0f, 20.0f, 0.0f));
        updateLayout();
        return;
    L36:
        r1.setColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_radioBackgroundChecked));
        goto L37
    L32:
        r32 = 5;
        goto L33
    L28:
        r3 = 5;
        goto L29
    L24:
        r25.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
        goto L25
    L20:
        r15 = 61.0f;
        goto L21
    L17:
        r13 = 23.0f;
        goto L18
    L13:
        r9 = 3;
        goto L14
    L9:
        r6 = 3;
        goto L10
    L5:
        r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        goto L6
    }

    private void updateLayout() {
        TextView r0 = this.textView;
        int r4 = 3;
        if (this.valueTextView.getVisibility() != 0) goto L16;
        boolean r1 = LocaleController.isRTL;
        if (r1 == false) goto L7;
        int r6 = 5;
    L8:
        int r9 = r6 | 48;
        if (r1 == false) goto L11;
        float r10 = 23.0f;
    L12:
        if (r1 == false) goto L14;
        float r12 = 61.0f;
    L15:
        FrameLayout.LayoutParams r13 = LayoutHelper.createFrame(-1, -1.0f, r9, r10, 0.0f, r12, 0.0f);
    L28:
        r0.setLayoutParams(r13);
        RadioButton r02 = this.radioButton;
        if (this.valueTextView.getVisibility() != 0) goto L35;
        if (LocaleController.isRTL == false) goto L33;
        r4 = 5;
    L33:
        FrameLayout.LayoutParams r14 = LayoutHelper.createFrame(22, 22.0f, r4 | 48, 20.0f, 15.0f, 20.0f, 0.0f);
    L39:
        r02.setLayoutParams(r14);
        return;
    L35:
        if (LocaleController.isRTL == true) goto L38;
        r4 = 5;
    L38:
        r14 = LayoutHelper.createFrame(22, 22.0f, r4 | 48, 20.0f, 15.0f, 20.0f, 0.0f);
        goto L39
    L14:
        r12 = 23.0f;
        goto L15
    L11:
        r10 = 61.0f;
        goto L12
    L7:
        r6 = 3;
        goto L8
    L16:
        boolean r15 = LocaleController.isRTL;
        if (r15 == false) goto L19;
        int r62 = 5;
    L20:
        int r92 = r62 | 48;
        if (r15 == false) goto L23;
        float r102 = 61.0f;
    L24:
        if (r15 == false) goto L26;
        float r122 = 23.0f;
    L27:
        r13 = LayoutHelper.createFrame(-1, -1.0f, r92, r102, 0.0f, r122, 0.0f);
        goto L28
    L26:
        r122 = 61.0f;
        goto L27
    L23:
        r102 = 23.0f;
        goto L24
    L19:
        r62 = 3;
        goto L20
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        if (this.needDivider == false) goto L14;
        float r1 = 23.0f;
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
        r0 = 23.0f;
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        setMeasuredDimension(View.MeasureSpec.getSize(r5), AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0));
        int r52 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        if (this.valueTextView.getVisibility() != 0) goto L5;
        int r62 = 12;
    L6:
        int r53 = r52 - AndroidUtilities.dp(r62 + 84);
        this.radioButton.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22.0f), TLObject.FLAG_30));
        if (this.valueTextView.getVisibility() != 0) goto L9;
        this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(r53, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        r53 = r53 - (this.valueTextView.getMeasuredWidth() + AndroidUtilities.dp(12.0f));
    L9:
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r53, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        return;
    L5:
        r62 = 0;
        goto L6
    }

    public void setChecked(boolean r1, boolean r2) {
        this.radioButton.setChecked(r1, r2);
    }

    public void setEnabled(boolean r4, boolean r5) {
        super.setEnabled(r4);
        TextView r0 = this.textView;
        float r1 = 0.5f;
        if (r5 == false) goto L17;
        ViewPropertyAnimator r52 = r0.animate();
        if (r4 == false) goto L7;
        float r02 = 1.0f;
    L8:
        r52.alpha(r02).start();
        ViewPropertyAnimator r53 = this.valueTextView.animate();
        if (r4 == false) goto L11;
        float r03 = 1.0f;
    L12:
        r53.alpha(r03).start();
        ViewPropertyAnimator r3 = this.radioButton.animate();
        if (r4 == false) goto L15;
        r1 = 1.0f;
    L15:
        r3.alpha(r1).start();
        return;
    L11:
        r03 = 0.5f;
        goto L12
    L7:
        r02 = 0.5f;
        goto L8
    L17:
        if (r4 == false) goto L19;
        float r54 = 1.0f;
    L20:
        r0.setAlpha(r54);
        TextView r55 = this.valueTextView;
        if (r4 == false) goto L23;
        float r04 = 1.0f;
    L24:
        r55.setAlpha(r04);
        RadioButton r32 = this.radioButton;
        if (r4 == false) goto L27;
        r1 = 1.0f;
    L27:
        r32.setAlpha(r1);
        return;
    L23:
        r04 = 0.5f;
        goto L24
    L19:
        r54 = 0.5f;
        goto L20
    }

    public void setText(CharSequence r3, boolean r4, boolean r5) {
        this.valueTextView.setVisibility(8);
        this.textView.setText(r3);
        this.radioButton.setChecked(r4, false);
        this.needDivider = r5;
        updateLayout();
        setWillNotDraw(!r5);
    }

    public void setTextAndValue(CharSequence r3, CharSequence r4, boolean r5, boolean r6) {
        this.valueTextView.setVisibility(0);
        this.valueTextView.setText(r4);
        this.textView.setText(r3);
        this.radioButton.setChecked(r5, false);
        this.needDivider = r6;
        updateLayout();
        setWillNotDraw(!r6);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }
}
