package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes7.dex */
public class ButtonSpan extends ReplacementSpan {
    private final Paint backgroundPaint;
    private ButtonBounce bounce;
    public Integer forcedColor;
    private final Runnable onClickListener;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Text text;

    /* loaded from: classes3.dex */
    public static class TextViewButtons extends LinkSpanDrawable.LinksTextView {
        ButtonSpan buttonToBeAdded;
        private ButtonSpan pressedSpan;

        public TextViewButtons(Context r1) {
            super(r1);
        }

        public TextViewButtons(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        public void addButton(ButtonSpan r1) {
            this.buttonToBeAdded = r1;
        }

        public ButtonSpan findSpan(float r9, int r10) {
            if ((getText() instanceof Spanned) == true) goto L5;
            return null;
        L5:
            Layout r0 = getLayout();
            if (r0 != null) goto L8;
            return null;
        L8:
            int r102 = r0.getLineForVertical(r10);
            Spanned r2 = (Spanned) getText();
            ButtonSpan[] r103 = (ButtonSpan[]) r2.getSpans(r0.getLineStart(r102), r0.getLineEnd(r102), ButtonSpan.class);
            float r92 = r9 + getScrollX();
            int r8 = 0;
        L10:
            if (r8 >= r103.length) goto L20;
            ButtonSpan r3 = r103[r8];
            float r4 = r0.getPrimaryHorizontal(r2.getSpanStart(r3));
            float r5 = r0.getPrimaryHorizontal(r2.getSpanEnd(r3));
            if (r5 >= r4) goto L15;
            r5 = r4;
            r4 = r5;
        L15:
            if (r92 < r4) goto L19;
            if (r92 > r5) goto L19;
            return r3;
        L19:
            r8 = r8 + 1;
            goto L10
        L20:
            return null;
        }

        @Override // android.widget.TextView, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            super.onLayout(r2, r3, r4, r5, r6);
            if (this.buttonToBeAdded != null) goto L5;
            return;
        L5:
            if (getMeasuredWidth() <= 0) goto L9;
            SpannableString r22 = new SpannableString(" btn");
            r22.setSpan(this.buttonToBeAdded, 1, r22.length(), 33);
            SpannableStringBuilder r32 = new SpannableStringBuilder(TextUtils.ellipsize(getText(), getPaint(), (((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - this.buttonToBeAdded.getSize()) - AndroidUtilities.dp(4.0f), TextUtils.TruncateAt.END));
            r32.append(r22);
            setText(r32);
            this.buttonToBeAdded = null;
            return;
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            int r0 = r7.getAction();
            ButtonSpan r1 = findSpan(r7.getX() - getPaddingLeft(), ((int) r7.getY()) - getPaddingTop());
            if (r0 != 0) goto L9;
            this.pressedSpan = r1;
            if (r1 == null) goto L28;
            r1.setPressed(this, true);
            return true;
        L28:
            if (this.pressedSpan == null) goto L30;
        L33:
            return true;
        L30:
            if (super.onTouchEvent(r7) == true) goto L33;
            return false;
        L9:
            if (r0 != 1) goto L11;
        L19:
            ButtonSpan r12 = this.pressedSpan;
            if (r12 == null) goto L26;
            r12.setPressed(this, false);
            if (r0 != 1) goto L26;
            if (ButtonSpan.m9100$$Nest$fgetonClickListener(this.pressedSpan) == null) goto L26;
            ButtonSpan.m9100$$Nest$fgetonClickListener(this.pressedSpan).run();
        L26:
            this.pressedSpan = null;
            goto L28
        L11:
            if (r0 == 3) goto L19;
            if (r0 != 2) goto L28;
            ButtonSpan r02 = this.pressedSpan;
            if (r02 == null) goto L28;
            if (r02 == r1) goto L28;
            r02.setPressed(this, false);
            this.pressedSpan = null;
            goto L28
        }
    }

    /* renamed from: -$$Nest$fgetonClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m9100$$Nest$fgetonClickListener(ButtonSpan r0) {
        return r0.onClickListener;
    }

    public ButtonSpan(CharSequence r3, Runnable r4, Theme.ResourcesProvider r5) {
        this.backgroundPaint = new Paint(1);
        this.resourcesProvider = r5;
        this.onClickListener = r4;
        this.text = new Text(r3, 12.0f);
    }

    public static CharSequence make(CharSequence r1, Runnable r2, Theme.ResourcesProvider r3) {
        return make(r1, r2, r3, null);
    }

    public static CharSequence make(CharSequence r2, Runnable r3, Theme.ResourcesProvider r4, Integer r5) {
        SpannableString r0 = new SpannableString("btn");
        ButtonSpan r1 = new ButtonSpan(r2, r3, r4);
        r0.setSpan(r1, 0, r0.length(), 33);
        r1.forcedColor = r5;
        return r0;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r7, CharSequence r8, int r9, int r10, float r11, int r12, int r13, int r14, Paint r15) {
        float r82 = AndroidUtilities.dpf2(17.0f);
        float r3 = (r12 + r14) / 2.0f;
        RectF r92 = AndroidUtilities.rectTmp;
        float r83 = r82 / 2.0f;
        r92.set(r11, r3 - r83, getSize() + r11, r3 + r83);
        ButtonBounce r102 = this.bounce;
        if (r102 != null) goto L5;
        float r103 = 1.0f;
    L6:
        r7.save();
        r7.scale(r103, r103, r92.centerX(), r92.centerY());
        Integer r104 = this.forcedColor;
        if (r104 == null) goto L10;
        int r105 = r104.intValue();
    L9:
        int r4 = r105;
        this.backgroundPaint.setColor(Theme.multAlpha(r4, 0.15f));
        r7.drawRoundRect(r92, r83, r83, this.backgroundPaint);
        this.text.draw(r7, r11 + AndroidUtilities.dp(7.0f), r3, r4, 1.0f);
        r7.restore();
        return;
    L10:
        r105 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        goto L9
    L5:
        r103 = r102.getScale(0.025f);
        goto L6
    }

    public int getSize() {
        return (int) (this.text.getCurrentWidth() + AndroidUtilities.dp(14.0f));
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return getSize();
    }

    public void setPressed(View r2, boolean r3) {
        if (this.bounce != null) goto L5;
        this.bounce = new ButtonBounce(r2);
    L5:
        this.bounce.setPressed(r3);
    }
}
