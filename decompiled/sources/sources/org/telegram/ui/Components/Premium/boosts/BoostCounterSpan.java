package org.telegram.ui.Components.Premium.boosts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.util.Pair;
import android.view.View;
import androidx.core.content.ContextCompat;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class BoostCounterSpan extends ReplacementSpan {
    private final Drawable boostProfileBadge;
    private final Drawable boostProfileBadge2;
    private final AnimatedTextView.AnimatedTextDrawable countText;
    private int currentCount;
    public boolean isRtl;
    public boolean margin;
    private final TextPaint namePaint;
    private final View parent;

    public BoostCounterSpan(View r9, TextPaint r10, int r11) {
        this.namePaint = r10;
        this.parent = r9;
        AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, false, true);
        this.countText = r0;
        r0.setAnimationProperties(0.3f, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.setCallback(r9);
        r0.setTextSize(AndroidUtilities.dp(11.5f));
        r0.setTypeface(AndroidUtilities.bold());
        r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r0.setGravity(17);
        Drawable r102 = ContextCompat.getDrawable(r9.getContext(), R.drawable.mini_boost_profile_badge).mutate();
        this.boostProfileBadge = r102;
        Drawable r92 = ContextCompat.getDrawable(r9.getContext(), R.drawable.mini_boost_profile_badge2).mutate();
        this.boostProfileBadge2 = r92;
        r102.setBounds(0, 0, r102.getIntrinsicWidth(), r102.getIntrinsicHeight());
        r92.setBounds(0, 0, r92.getIntrinsicWidth(), r92.getIntrinsicHeight());
        setCount(r11, false);
    }

    public static Pair<SpannableString, BoostCounterSpan> create(View r2, TextPaint r3, int r4) {
        SpannableString r0 = new SpannableString("d");
        BoostCounterSpan r1 = new BoostCounterSpan(r2, r3, r4);
        r0.setSpan(r1, 0, 1, 33);
        return new Pair(r0, r1);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        if (this.namePaint.getColor() == this.countText.getTextColor()) goto L5;
        this.countText.setTextColor(this.namePaint.getColor());
        Drawable r22 = this.boostProfileBadge;
        int r42 = this.countText.getTextColor();
        PorterDuff.Mode r62 = PorterDuff.Mode.MULTIPLY;
        r22.setColorFilter(new PorterDuffColorFilter(r42, r62));
        this.boostProfileBadge2.setColorFilter(new PorterDuffColorFilter(this.countText.getTextColor(), r62));
    L5:
        r1.save();
        if (this.margin == true) goto L8;
    L10:
        int r23 = 0;
    L11:
        r1.translate(r5 + r23, -AndroidUtilities.dp(0.2f));
        if (this.currentCount != 1) goto L14;
        r1.translate(AndroidUtilities.dp(1.5f), 0.0f);
        this.boostProfileBadge.draw(r1);
    L15:
        r1.translate(AndroidUtilities.dp(16.0f), 0.0f);
        Rect r24 = AndroidUtilities.rectTmp2;
        r24.set(0, 0, (int) this.countText.getCurrentWidth(), (int) this.countText.getHeight());
        this.countText.setBounds(r24);
        this.countText.draw(r1);
        r1.restore();
        return;
    L14:
        this.boostProfileBadge2.draw(r1);
        goto L15
    L8:
        if (this.isRtl == true) goto L10;
        r23 = AndroidUtilities.dp(8.0f);
        goto L11
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return getWidth();
    }

    public int getWidth() {
        if (this.margin == false) goto L5;
        int r0 = 8;
    L7:
        return (int) (AndroidUtilities.dp(r0 + 16) + this.countText.getWidth());
    L5:
        r0 = 0;
        goto L7
    }

    public void setCount(int r2, boolean r3) {
        this.currentCount = r2;
        AnimatedTextView.AnimatedTextDrawable r1 = this.countText;
        if (r2 > 1) goto L5;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r1.setText(r22, r3);
        return;
    L5:
        r22 = String.valueOf(r2);
        goto L6
    }
}
