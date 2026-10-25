package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextPriceCell extends FrameLayout {
    private TextView textView;
    private TextView valueTextView;

    public TextPriceCell(Context r15) {
        super(r15);
        setWillNotDraw(false);
        TextView r0 = new TextView(r15);
        this.textView = r0;
        r0.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r02 = this.textView;
        TextUtils.TruncateAt r3 = TextUtils.TruncateAt.END;
        r02.setEllipsize(r3);
        TextView r03 = this.textView;
        int r5 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r4 = 5;
    L6:
        r03.setGravity(r4 | 16);
        TextView r04 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r42 = 5;
    L10:
        addView(r04, LayoutHelper.createFrame(-2, -1.0f, r42 | 48, 21.0f, 0.0f, 21.0f, 0.0f));
        TextView r05 = new TextView(r15);
        this.valueTextView = r05;
        r05.setTextSize(1, 16.0f);
        this.valueTextView.setTypeface(AndroidUtilities.bold());
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setEllipsize(r3);
        TextView r152 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r06 = 3;
    L14:
        r152.setGravity(r06 | 16);
        TextView r153 = this.valueTextView;
        if (LocaleController.isRTL == true) goto L18;
        r5 = 5;
    L18:
        addView(r153, LayoutHelper.createFrame(-2, -1.0f, r5 | 48, 21.0f, 0.0f, 21.0f, 0.0f));
        return;
    L13:
        r06 = 5;
        goto L14
    L9:
        r42 = 3;
        goto L10
    L5:
        r4 = 3;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        setMeasuredDimension(View.MeasureSpec.getSize(r5), AndroidUtilities.dp(40.0f));
        int r52 = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(34.0f);
        this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(r52 / 2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        int r53 = (r52 - this.valueTextView.getMeasuredWidth()) - AndroidUtilities.dp(8.0f);
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r53, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
    }

    public void setTextAndValue(String r2, String r3, boolean r4) {
        this.textView.setText(r2);
        TextView r22 = this.valueTextView;
        if (r3 == null) goto L5;
        r22.setText(r3);
        this.valueTextView.setVisibility(0);
    L6:
        if (r4 == false) goto L8;
        int r23 = Theme.key_windowBackgroundWhiteBlackText;
        setTag(Integer.valueOf(r23));
        this.textView.setTextColor(Theme.getColor(r23));
        this.valueTextView.setTextColor(Theme.getColor(r23));
        this.textView.setTypeface(AndroidUtilities.bold());
        this.valueTextView.setTypeface(AndroidUtilities.bold());
    L9:
        requestLayout();
        return;
    L8:
        int r24 = Theme.key_windowBackgroundWhiteGrayText2;
        setTag(Integer.valueOf(r24));
        this.textView.setTextColor(Theme.getColor(r24));
        this.valueTextView.setTextColor(Theme.getColor(r24));
        TextView r25 = this.textView;
        Typeface r32 = Typeface.DEFAULT;
        r25.setTypeface(r32);
        this.valueTextView.setTypeface(r32);
        goto L9
    L5:
        r22.setVisibility(4);
        goto L6
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }

    public void setTextValueColor(int r1) {
        this.valueTextView.setTextColor(r1);
    }
}
