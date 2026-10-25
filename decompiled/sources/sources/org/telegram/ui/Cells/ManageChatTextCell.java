package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class ManageChatTextCell extends FrameLayout {
    private boolean divider;
    private int dividerColor;
    private ImageView imageView;
    private SimpleTextView textView;
    private SimpleTextView valueTextView;

    public ManageChatTextCell(Context r6) {
        super(r6);
        this.dividerColor = 0;
        SimpleTextView r0 = new SimpleTextView(r6);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(16);
        SimpleTextView r02 = this.textView;
        int r3 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 5;
    L6:
        r02.setGravity(r2);
        addView(this.textView);
        SimpleTextView r03 = new SimpleTextView(r6);
        this.valueTextView = r03;
        r03.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
        this.valueTextView.setTextSize(16);
        SimpleTextView r04 = this.valueTextView;
        if (LocaleController.isRTL == true) goto L10;
        r3 = 5;
    L10:
        r04.setGravity(r3);
        addView(this.valueTextView);
        ImageView r05 = new ImageView(r6);
        this.imageView = r05;
        r05.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
        addView(this.imageView);
        return;
    L5:
        r2 = 3;
        goto L6
    }

    public int getFullHeight() {
        return AndroidUtilities.dp(56.0f);
    }

    public SimpleTextView getTextView() {
        return this.textView;
    }

    public SimpleTextView getValueTextView() {
        return this.valueTextView;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.divider == false) goto L14;
        int r0 = this.dividerColor;
        if (r0 == 0) goto L7;
        Theme.dividerExtraPaint.setColor(Theme.getColor(r0));
    L7:
        float r2 = AndroidUtilities.dp(71.0f);
        float r3 = getMeasuredHeight() - 1;
        float r4 = getMeasuredWidth();
        float r5 = getMeasuredHeight() - 1;
        if (this.dividerColor == 0) goto L11;
        Paint r7 = Theme.dividerExtraPaint;
    L12:
        r8.drawLine(r2, r3, r4, r5, r7);
        return;
    L11:
        r7 = Theme.dividerPaint;
        goto L12
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        int r82 = r8 - r6;
        int r72 = r7 - r5;
        int r42 = (r82 - this.valueTextView.getTextHeight()) / 2;
        if (LocaleController.isRTL == false) goto L5;
        int r52 = AndroidUtilities.dp(24.0f);
    L6:
        SimpleTextView r0 = this.valueTextView;
        r0.layout(r52, r42, r0.getMeasuredWidth() + r52, this.valueTextView.getMeasuredHeight() + r42);
        int r83 = (r82 - this.textView.getTextHeight()) / 2;
        if (LocaleController.isRTL == true) goto L9;
        int r43 = AndroidUtilities.dp(71.0f);
    L10:
        SimpleTextView r53 = this.textView;
        r53.layout(r43, r83, r53.getMeasuredWidth() + r43, this.textView.getMeasuredHeight() + r83);
        int r44 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == true) goto L13;
        int r54 = AndroidUtilities.dp(21.0f);
    L14:
        ImageView r62 = this.imageView;
        r62.layout(r54, r44, r62.getMeasuredWidth() + r54, this.imageView.getMeasuredHeight() + r44);
        return;
    L13:
        r54 = (r72 - this.imageView.getMeasuredWidth()) - AndroidUtilities.dp(21.0f);
        goto L14
    L9:
        r43 = AndroidUtilities.dp(24.0f);
        goto L10
    L5:
        r52 = 0;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        int r72 = View.MeasureSpec.getSize(r7);
        int r82 = AndroidUtilities.dp(48.0f);
        this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(r72 - AndroidUtilities.dp(24.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r72 - AndroidUtilities.dp(95.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        this.imageView.measure(View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r82, Integer.MIN_VALUE));
        setMeasuredDimension(r72, getFullHeight() + (this.divider ? 1 : 0));
    }

    public void setColors(int r4, int r5) {
        this.textView.setTextColor(Theme.getColor(r5));
        this.textView.setTag(Integer.valueOf(r5));
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4), PorterDuff.Mode.MULTIPLY));
        this.imageView.setTag(Integer.valueOf(r4));
    }

    public void setDividerColor(int r1) {
        this.dividerColor = r1;
    }

    public void setText(String r2, String r3, int r4, int r5, boolean r6) {
        this.textView.setText(r2);
        SimpleTextView r22 = this.valueTextView;
        if (r3 == null) goto L5;
        r22.setText(r3);
        this.valueTextView.setVisibility(0);
    L6:
        this.imageView.setPadding(0, AndroidUtilities.dp(r5), 0, 0);
        this.imageView.setImageResource(r4);
        this.divider = r6;
        setWillNotDraw(!r6);
        return;
    L5:
        r22.setVisibility(4);
        goto L6
    }

    public void setText(String r7, String r8, int r9, boolean r10) {
        setText(r7, r8, r9, 5, r10);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }
}
