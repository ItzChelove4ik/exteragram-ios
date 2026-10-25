package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public abstract class GroupCallTextCell extends FrameLayout {
    private Paint dividerPaint;
    private int imageLeft;
    private ImageView imageView;
    private int leftPadding;
    private boolean needDivider;
    private int offsetFromImage;
    private SimpleTextView textView;
    private ImageView valueImageView;
    private SimpleTextView valueTextView;

    public GroupCallTextCell(Context r3) {
        this(r3, 23, false);
    }

    public GroupCallTextCell(Context r6, int r7, boolean r8) {
        super(r6);
        this.offsetFromImage = 67;
        this.imageLeft = 18;
        Paint r0 = new Paint();
        this.dividerPaint = r0;
        r0.setColor(Theme.getColor(Theme.key_voipgroup_actionBar));
        this.leftPadding = r7;
        SimpleTextView r72 = new SimpleTextView(r6);
        this.textView = r72;
        if (r8 == false) goto L5;
        int r02 = Theme.key_dialogTextBlack;
    L6:
        r72.setTextColor(Theme.getColor(r02));
        this.textView.setTextSize(16);
        SimpleTextView r73 = this.textView;
        int r2 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r1 = 5;
    L10:
        r73.setGravity(r1);
        this.textView.setImportantForAccessibility(2);
        addView(this.textView);
        SimpleTextView r74 = new SimpleTextView(r6);
        this.valueTextView = r74;
        if (r8 == false) goto L13;
        int r4 = Theme.key_dialogTextBlue2;
    L14:
        r74.setTextColor(Theme.getColor(r4));
        this.valueTextView.setTextSize(16);
        SimpleTextView r75 = this.valueTextView;
        if (LocaleController.isRTL == true) goto L18;
        r2 = 5;
    L18:
        r75.setGravity(r2);
        this.valueTextView.setImportantForAccessibility(2);
        addView(this.valueTextView);
        ImageView r76 = new ImageView(r6);
        this.imageView = r76;
        ImageView.ScaleType r03 = ImageView.ScaleType.CENTER;
        r76.setScaleType(r03);
        ImageView r77 = this.imageView;
        if (r8 == false) goto L21;
        int r82 = Theme.key_dialogIcon;
    L22:
        r77.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r82), PorterDuff.Mode.MULTIPLY));
        addView(this.imageView);
        ImageView r78 = new ImageView(r6);
        this.valueImageView = r78;
        r78.setScaleType(r03);
        addView(this.valueImageView);
        setFocusable(true);
        return;
    L21:
        r82 = Theme.key_windowBackgroundWhiteGrayIcon;
        goto L22
    L13:
        r4 = Theme.key_windowBackgroundWhiteValueText;
        goto L14
    L9:
        r1 = 3;
        goto L10
    L5:
        r02 = Theme.key_windowBackgroundWhiteBlackText;
        goto L6
    }

    public SimpleTextView getTextView() {
        return this.textView;
    }

    public ImageView getValueImageView() {
        return this.valueImageView;
    }

    public SimpleTextView getValueTextView() {
        return this.valueTextView;
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        if (this.needDivider == false) goto L22;
        float r1 = 20.0f;
        if (LocaleController.isRTL == false) goto L9;
        float r0 = 0.0f;
    L7:
        float r4 = r0;
        float r5 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == true) goto L16;
        int r12 = 0;
    L20:
        r10.drawLine(r4, r5, r02 - r12, getMeasuredHeight() - 1, this.dividerPaint);
        return;
    L16:
        if (this.imageView.getVisibility() != 0) goto L18;
        r1 = 68.0f;
    L18:
        r12 = AndroidUtilities.dp(r1);
        goto L20
    L9:
        if (this.imageView.getVisibility() != 0) goto L11;
        float r03 = 68.0f;
    L12:
        r0 = AndroidUtilities.dp(r03);
        goto L7
    L11:
        r03 = 20.0f;
        goto L12
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        CharSequence r0 = this.textView.getText();
        if (TextUtils.isEmpty(r0) == true) goto L10;
        CharSequence r2 = this.valueTextView.getText();
        if (TextUtils.isEmpty(r2) == true) goto L8;
        r3.setText(r0 + ": " + r2);
        return;
    L8:
        r3.setText(r0);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        int r72 = r7 - r5;
        int r62 = r6 - r4;
        int r32 = (r72 - this.valueTextView.getTextHeight()) / 2;
        if (LocaleController.isRTL == false) goto L5;
        int r42 = AndroidUtilities.dp(this.leftPadding);
    L6:
        SimpleTextView r52 = this.valueTextView;
        r52.layout(r42, r32, r52.getMeasuredWidth() + r42, this.valueTextView.getMeasuredHeight() + r32);
        int r33 = (r72 - this.textView.getTextHeight()) / 2;
        if (LocaleController.isRTL == false) goto L15;
        int r43 = getMeasuredWidth() - this.textView.getMeasuredWidth();
        if (this.imageView.getVisibility() != 0) goto L12;
        int r53 = this.offsetFromImage;
    L13:
        int r44 = r43 - AndroidUtilities.dp(r53);
    L20:
        SimpleTextView r54 = this.textView;
        r54.layout(r44, r33, r54.getMeasuredWidth() + r44, this.textView.getMeasuredHeight() + r33);
        if (this.imageView.getVisibility() != 0) goto L28;
        int r34 = AndroidUtilities.dp(5.0f);
        if (LocaleController.isRTL == true) goto L25;
        int r45 = AndroidUtilities.dp(this.imageLeft);
    L26:
        ImageView r55 = this.imageView;
        r55.layout(r45, r34, r55.getMeasuredWidth() + r45, this.imageView.getMeasuredHeight() + r34);
        goto L28
    L25:
        r45 = (r62 - this.imageView.getMeasuredWidth()) - AndroidUtilities.dp(this.imageLeft);
    L28:
        if (this.valueImageView.getVisibility() != 0) goto L35;
        int r73 = (r72 - this.valueImageView.getMeasuredHeight()) / 2;
        if (LocaleController.isRTL == false) goto L32;
        int r35 = AndroidUtilities.dp(23.0f);
    L33:
        ImageView r46 = this.valueImageView;
        r46.layout(r35, r73, r46.getMeasuredWidth() + r35, this.valueImageView.getMeasuredHeight() + r73);
        return;
    L32:
        r35 = (r62 - this.valueImageView.getMeasuredWidth()) - AndroidUtilities.dp(23.0f);
        goto L33
    L35:
        return;
    L12:
        r53 = this.leftPadding;
        goto L13
    L15:
        if (this.imageView.getVisibility() != 0) goto L18;
        int r47 = this.offsetFromImage;
    L19:
        r44 = AndroidUtilities.dp(r47);
        goto L20
    L18:
        r47 = this.leftPadding;
        goto L19
    L5:
        r42 = 0;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        int r72 = View.MeasureSpec.getSize(r7);
        int r82 = AndroidUtilities.dp(48.0f);
        this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(r72 - AndroidUtilities.dp(this.leftPadding), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        this.textView.measure(View.MeasureSpec.makeMeasureSpec((r72 - AndroidUtilities.dp(this.leftPadding + 71)) - this.valueTextView.getTextWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        if (this.imageView.getVisibility() != 0) goto L6;
        this.imageView.measure(View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r82, Integer.MIN_VALUE));
    L6:
        if (this.valueImageView.getVisibility() != 0) goto L8;
        this.valueImageView.measure(View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r82, Integer.MIN_VALUE));
    L8:
        setMeasuredDimension(r72, AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0));
    }

    public void setColors(int r4, int r5) {
        this.textView.setTextColor(r5);
        this.textView.setTag(null);
        this.imageView.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        this.imageView.setTag(null);
    }

    public void setOffsetFromImage(int r1) {
        this.offsetFromImage = r1;
    }

    public void setTextAndIcon(String r2, int r3, boolean r4) {
        this.textView.setText(r2);
        this.valueTextView.setText(null);
        this.imageView.setImageResource(r3);
        this.imageView.setVisibility(0);
        this.valueTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.needDivider = r4;
        setWillNotDraw(!r4);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }
}
