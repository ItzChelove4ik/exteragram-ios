package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedTextView;

/* loaded from: classes7.dex */
public class VideoCompressButton extends View {
    private final Paint clearPaint;
    private boolean disabled;
    private final AnimatedFloat disabledT;
    private final Paint fillPaint;
    private final AnimatedTextView.AnimatedTextDrawable sizeTextDrawable;
    private final int[] sizes;
    private final Paint strokePaint;
    private final AnimatedTextView.AnimatedTextDrawable textDrawable;

    public VideoCompressButton(Context r14) {
        super(r14);
        Paint r142 = new Paint(1);
        this.strokePaint = r142;
        Paint r1 = new Paint(1);
        this.fillPaint = r1;
        Paint r2 = new Paint(1);
        this.clearPaint = r2;
        CubicBezierInterpolator r10 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.disabledT = new AnimatedFloat(this, 0, 300, r10);
        this.sizes = new int[]{144, 240, 360, 480, 720, 1080, 1440, 2160};
        AnimatedTextView.AnimatedTextDrawable r4 = new AnimatedTextView.AnimatedTextDrawable(true, false, false);
        this.textDrawable = r4;
        r4.setAnimationProperties(0.4f, 0, 360, r10);
        r4.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r4.setTextColor(-1);
        r4.setTextSize(AndroidUtilities.dpf2(10.6f));
        r4.setCallback(this);
        r4.setGravity(17);
        AnimatedTextView.AnimatedTextDrawable r42 = new AnimatedTextView.AnimatedTextDrawable(true, false, false);
        this.sizeTextDrawable = r42;
        r42.setAnimationProperties(0.2f, 0, 360, r10);
        r42.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r42.setTextColor(-1);
        r42.setTextSize(AndroidUtilities.dpf2(8.6f));
        r42.setCallback(this);
        r42.setGravity(5);
        TextPaint r13 = r42.getPaint();
        PorterDuff.Mode r3 = PorterDuff.Mode.CLEAR;
        r13.setXfermode(new PorterDuffXfermode(r3));
        r42.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        r142.setColor(-1);
        r142.setStyle(Paint.Style.STROKE);
        r1.setColor(-1);
        r2.setXfermode(new PorterDuffXfermode(r3));
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r15) {
        super.dispatchDraw(r15);
        r15.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        float r8 = (1.0f - (this.disabledT.set(this.disabled) * 0.35f)) * 255.0f;
        int r2 = (int) r8;
        this.strokePaint.setAlpha(r2);
        this.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.33f));
        float r3 = Math.max(AndroidUtilities.dpf2(21.33f), AndroidUtilities.dpf2(6.0f) + this.textDrawable.getCurrentWidth());
        float r4 = AndroidUtilities.dpf2(17.33f);
        RectF r10 = AndroidUtilities.rectTmp;
        r10.set((getWidth() - r3) / 2.0f, (getHeight() - r4) / 2.0f, (getWidth() + r3) / 2.0f, (getHeight() + r4) / 2.0f);
        r15.drawRoundRect(r10, AndroidUtilities.dpf2(4.0f), AndroidUtilities.dpf2(4.0f), this.strokePaint);
        Rect r11 = AndroidUtilities.rectTmp2;
        r11.set(0, (int) ((getHeight() - r4) / 2.0f), getWidth(), (int) ((getHeight() + r4) / 2.0f));
        this.textDrawable.setBounds(r11);
        this.textDrawable.setAlpha(r2);
        this.textDrawable.draw(r15);
        r11.set((int) (((getWidth() / 2.0f) + AndroidUtilities.dpf2(16.0f)) - ((this.sizeTextDrawable.isNotEmpty() * AndroidUtilities.dpf2(2.0f)) + this.sizeTextDrawable.getCurrentWidth())), (int) ((getHeight() / 2.0f) - AndroidUtilities.dpf2(14.0f)), (int) ((getWidth() / 2.0f) + AndroidUtilities.dpf2(16.0f)), (int) (((getHeight() / 2.0f) - AndroidUtilities.dpf2(14.0f)) + AndroidUtilities.dpf2(8.33f)));
        r10.set(r11);
        r10.inset(-AndroidUtilities.dpf2(1.33f), -AndroidUtilities.dpf2(1.33f));
        r15.drawRoundRect(r10, AndroidUtilities.dpf2(1.66f), AndroidUtilities.dpf2(1.66f), this.clearPaint);
        r15.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        r10.set(r11);
        this.fillPaint.setAlpha((int) (r8 * this.sizeTextDrawable.isNotEmpty()));
        r15.drawRoundRect(r10, AndroidUtilities.dpf2(1.66f), AndroidUtilities.dpf2(1.66f), this.fillPaint);
        r11.offset((int) (-AndroidUtilities.dpf2(1.33f)), 0);
        r15.save();
        this.sizeTextDrawable.setBounds(r11);
        this.sizeTextDrawable.draw(r15);
        r15.restore();
        r15.restore();
        r15.restore();
    }

    public void setPhotoState(boolean r3) {
        this.disabled = false;
        AnimatedTextView.AnimatedTextDrawable r1 = this.textDrawable;
        if (r3 == false) goto L5;
        String r32 = "HD";
    L6:
        r1.setText(r32);
        this.sizeTextDrawable.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
        return;
    L5:
        r32 = "SD";
        goto L6
    }

    public void setState(boolean r3, boolean r4, int r5) {
        if (r3 == false) goto L7;
        if (r4 == true) goto L7;
        boolean r32 = false;
    L8:
        this.disabled = r32;
        AnimatedTextView.AnimatedTextDrawable r33 = this.textDrawable;
        if (r4 == false) goto L12;
        r33.setText("GIF");
        this.sizeTextDrawable.setText(_UrlKt.FRAGMENT_ENCODE_SET, true);
    L31:
        setClickable(!this.disabled);
        invalidate();
        return;
    L12:
        if (r5 < 720) goto L14;
        String r42 = "HD";
    L15:
        r33.setText(r42);
        int r34 = this.sizes.length - 1;
    L16:
        if (r34 < 0) goto L21;
        if (r5 >= this.sizes[r34]) goto L22;
        r34 = r34 - 1;
    L22:
        if (r34 >= 0) goto L25;
        this.sizeTextDrawable.setText(_UrlKt.FRAGMENT_ENCODE_SET, true);
        goto L31
    L25:
        if (r34 != 6) goto L27;
        AnimatedTextView.AnimatedTextDrawable r35 = this.sizeTextDrawable;
        r35.setText("2K", TextUtils.isEmpty(r35.getText()));
        goto L31
    L27:
        AnimatedTextView.AnimatedTextDrawable r43 = this.sizeTextDrawable;
        if (r34 != 7) goto L30;
        r43.setText("4K", TextUtils.isEmpty(r43.getText()));
        goto L31
    L30:
        r43.setText(_UrlKt.FRAGMENT_ENCODE_SET + this.sizes[r34], TextUtils.isEmpty(this.sizeTextDrawable.getText()));
        goto L31
    L21:
        r34 = -1;
        goto L22
    L14:
        r42 = "SD";
    L7:
        r32 = true;
        goto L8
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.textDrawable != r2) goto L5;
        return true;
    L5:
        if (this.sizeTextDrawable != r2) goto L7;
        return true;
    L7:
        if (super.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
