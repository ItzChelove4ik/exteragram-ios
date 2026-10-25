package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class CreationTextCell extends FrameLayout {
    boolean divider;
    private ImageView imageView;
    public int startPadding;
    private SimpleTextView textView;

    public CreationTextCell(Context r2, int r3, Theme.ResourcesProvider r4) {
        super(r2);
        this.startPadding = r3;
        SimpleTextView r32 = new SimpleTextView(r2);
        this.textView = r32;
        r32.setTextSize(16);
        SimpleTextView r33 = this.textView;
        if (LocaleController.isRTL == false) goto L5;
        int r0 = 5;
    L6:
        r33.setGravity(r0);
        SimpleTextView r34 = this.textView;
        int r02 = Theme.key_windowBackgroundWhiteBlueText2;
        r34.setTextColor(Theme.getColor(r02, r4));
        this.textView.setTag(Integer.valueOf(r02));
        addView(this.textView);
        ImageView r35 = new ImageView(r2);
        this.imageView = r35;
        r35.setScaleType(ImageView.ScaleType.CENTER);
        addView(this.imageView);
        setWillNotDraw(false);
        return;
    L5:
        r0 = 3;
        goto L6
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        if (this.divider == false) goto L6;
        r8.drawLine(AndroidUtilities.dp(this.startPadding), getMeasuredHeight() - 1, getMeasuredWidth() + AndroidUtilities.dp(23.0f), getMeasuredHeight(), Theme.dividerPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        int r62 = r6 - r4;
        int r72 = ((r7 - r5) - this.textView.getTextHeight()) / 2;
        if (LocaleController.isRTL == false) goto L10;
        int r32 = getMeasuredWidth() - this.textView.getMeasuredWidth();
        if (this.imageView.getVisibility() != 0) goto L7;
        float r52 = this.startPadding;
    L8:
        int r33 = r32 - AndroidUtilities.dp(r52);
    L14:
        SimpleTextView r53 = this.textView;
        r53.layout(r33, r72, r53.getMeasuredWidth() + r33, this.textView.getMeasuredHeight() + r72);
        if (LocaleController.isRTL == true) goto L17;
        int r34 = (AndroidUtilities.dp(this.startPadding) - this.imageView.getMeasuredWidth()) / 2;
    L18:
        ImageView r42 = this.imageView;
        r42.layout(r34, 0, r42.getMeasuredWidth() + r34, this.imageView.getMeasuredHeight());
        return;
    L17:
        r34 = (r62 - this.imageView.getMeasuredWidth()) - AndroidUtilities.dp(25.0f);
        goto L18
    L7:
        r52 = 25.0f;
        goto L8
    L10:
        if (this.imageView.getVisibility() != 0) goto L12;
        float r35 = this.startPadding;
    L13:
        r33 = AndroidUtilities.dp(r35);
        goto L14
    L12:
        r35 = 25.0f;
        goto L13
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r52 = View.MeasureSpec.getSize(r5);
        AndroidUtilities.dp(48.0f);
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r52 - AndroidUtilities.dp(94.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        this.imageView.measure(View.MeasureSpec.makeMeasureSpec(r52, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        setMeasuredDimension(r52, AndroidUtilities.dp(50.0f));
    }

    public void setTextAndIcon(String r2, Drawable r3, boolean r4) {
        this.textView.setText(r2);
        this.imageView.setImageDrawable(r3);
        this.divider = r4;
    }
}
