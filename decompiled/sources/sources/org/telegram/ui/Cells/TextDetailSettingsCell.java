package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextDetailSettingsCell extends FrameLayout {
    private ImageView imageView;
    private boolean multiline;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    public TextDetailSettingsCell(Context r13) {
        super(r13);
        TextView r0 = new TextView(r13);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.regular());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r02 = this.textView;
        int r3 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 5;
    L6:
        r02.setGravity(r1 | 16);
        TextView r03 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r12 = 5;
    L10:
        addView(r03, LayoutHelper.createFrame(-2, -2.0f, r12 | 48, 21.0f, 10.0f, 21.0f, 0.0f));
        TextView r04 = new TextView(r13);
        this.valueTextView = r04;
        r04.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        this.valueTextView.setTextSize(1, 13.0f);
        this.valueTextView.setTypeface(AndroidUtilities.regular());
        TextView r05 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r14 = 5;
    L14:
        r05.setGravity(r14);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
        TextView r06 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L17;
        int r15 = 5;
    L18:
        addView(r06, LayoutHelper.createFrame(-2, -2.0f, r15 | 48, 21.0f, 35.0f, 21.0f, 0.0f));
        ImageView r07 = new ImageView(r13);
        this.imageView = r07;
        r07.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
        this.imageView.setVisibility(8);
        ImageView r132 = this.imageView;
        if (LocaleController.isRTL == false) goto L21;
        r3 = 5;
    L21:
        addView(r132, LayoutHelper.createFrame(52, 52.0f, r3 | 48, 8.0f, 6.0f, 8.0f, 0.0f));
        return;
    L17:
        r15 = 3;
        goto L18
    L13:
        r14 = 3;
        goto L14
    L9:
        r12 = 3;
        goto L10
    L5:
        r1 = 3;
        goto L6
    }

    public TextView getTextView() {
        return this.textView;
    }

    public TextView getValueTextView() {
        return this.valueTextView;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.textView.invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (Theme.dividerPaint == null) goto L25;
        float r1 = 20.0f;
        if (LocaleController.isRTL == false) goto L11;
        float r0 = 0.0f;
    L9:
        float r4 = r0;
        float r5 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == true) goto L18;
        int r12 = 0;
    L22:
        r10.drawLine(r4, r5, r02 - r12, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L18:
        if (this.imageView.getVisibility() != 0) goto L20;
        r1 = 71.0f;
    L20:
        r12 = AndroidUtilities.dp(r1);
        goto L22
    L11:
        if (this.imageView.getVisibility() != 0) goto L13;
        float r03 = 71.0f;
    L14:
        r0 = AndroidUtilities.dp(r03);
        goto L9
    L13:
        r03 = 20.0f;
        goto L14
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        if (this.multiline == true) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        return;
    L6:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void setMultilineDetail(boolean r3) {
        this.multiline = r3;
        TextView r0 = this.valueTextView;
        if (r3 == false) goto L6;
        r0.setLines(0);
        this.valueTextView.setMaxLines(0);
        this.valueTextView.setSingleLine(false);
        this.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(12.0f));
        return;
    L6:
        r0.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
    }

    public void setTextAndValue(CharSequence r2, CharSequence r3, boolean r4) {
        this.textView.setText(r2);
        this.valueTextView.setText(r3);
        this.needDivider = r4;
        this.imageView.setVisibility(8);
        setWillNotDraw(!r4);
    }

    public void setTextAndValueAndIcon(String r3, CharSequence r4, int r5, boolean r6) {
        this.textView.setText(r3);
        this.valueTextView.setText(r4);
        this.imageView.setImageResource(r5);
        this.imageView.setVisibility(0);
        TextView r32 = this.textView;
        if (LocaleController.isRTL == false) goto L5;
        int r52 = 0;
    L7:
        if (LocaleController.isRTL == false) goto L9;
        int r1 = AndroidUtilities.dp(50.0f);
    L10:
        r32.setPadding(r52, 0, r1, 0);
        TextView r33 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r53 = 0;
    L15:
        if (LocaleController.isRTL == false) goto L17;
        int r0 = AndroidUtilities.dp(50.0f);
    L19:
        if (this.multiline == false) goto L21;
        int r12 = AndroidUtilities.dp(12.0f);
    L22:
        r33.setPadding(r53, 0, r0, r12);
        this.needDivider = r6;
        setWillNotDraw(!r6);
        return;
    L21:
        r12 = 0;
        goto L22
    L17:
        r0 = 0;
        goto L19
    L13:
        r53 = AndroidUtilities.dp(50.0f);
        goto L15
    L9:
        r1 = 0;
        goto L10
    L5:
        r52 = AndroidUtilities.dp(50.0f);
        goto L7
    }

    public void setValue(CharSequence r1) {
        this.valueTextView.setText(r1);
    }
}
