package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.recorder.FlashViews;

/* loaded from: classes7.dex */
public class VideoTimerView extends View implements FlashViews.Invertable {
    private Paint backgroundPaint;
    private Paint recordPaint;
    private boolean recording;
    private AnimatedFloat recordingT;
    private AnimatedTextView.AnimatedTextDrawable textDrawable;

    public VideoTimerView(Context r10) {
        super(r10);
        this.backgroundPaint = new Paint(1);
        this.recordPaint = new Paint(1);
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.recordingT = new AnimatedFloat(this, 0, 250, r7);
        this.recordPaint.setColor(-907224);
        this.backgroundPaint.setColor(1056964608);
        AnimatedTextView.AnimatedTextDrawable r2 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.textDrawable = r2;
        r2.setAnimationProperties(0.3f, 0, 250, r7);
        this.textDrawable.setTextSize(AndroidUtilities.dp(13.0f));
        this.textDrawable.setTextColor(-1);
        this.textDrawable.setTypeface(AndroidUtilities.bold());
        this.textDrawable.setCallback(this);
        this.textDrawable.setGravity(1);
        setDuration(0, false);
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        super.onDraw(r13);
        AnimatedFloat r0 = this.recordingT;
        if (this.recording == false) goto L5;
        float r1 = 1.0f;
    L6:
        float r02 = r0.set(r1);
        float r12 = AndroidUtilities.dp(12.66f) * r02;
        float r4 = this.textDrawable.getCurrentWidth() + r12;
        RectF r5 = AndroidUtilities.rectTmp;
        r5.set(((getWidth() - r4) / 2.0f) - AndroidUtilities.dp(8.0f), AndroidUtilities.dp(18.0f), ((getWidth() + r4) / 2.0f) + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(40.0f));
        r13.drawRoundRect(r5, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), this.backgroundPaint);
        if (r02 <= 0.0f) goto L9;
        long r6 = System.currentTimeMillis() % 2000;
        this.recordPaint.setAlpha((int) (Utilities.clamp((((float) Math.sin((r6 / 1000.0f) * 3.141592653589793d)) / 4.0f) + 0.75f, 1.0f, 0.0f) * 255.0f));
        invalidate();
        r13.drawCircle(r5.left + AndroidUtilities.dp(10.66f), r5.centerY(), AndroidUtilities.dp(4.0f) * r02, this.recordPaint);
    L9:
        this.textDrawable.setBounds((int) (r5.left + r12), ((int) r5.top) - AndroidUtilities.dp(1.0f), (int) r5.right, (int) r5.bottom);
        this.textDrawable.draw(r13);
        return;
    L5:
        r1 = 0.0f;
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(45.0f), TLObject.FLAG_30));
    }

    public void setDuration(long r8, boolean r10) {
        long r2 = r8 % 60;
        long r82 = (r8 - r2) / 60;
        StringBuilder r0 = new StringBuilder(5);
        if (r82 >= 10) goto L5;
        r0.append('0');
    L5:
        r0.append(r82);
        r0.append(':');
        if (r2 >= 10) goto L8;
        r0.append('0');
    L8:
        r0.append(r2);
        this.textDrawable.setText(r0, r10);
    }

    @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
    public void setInvert(float r4) {
        this.backgroundPaint.setColor(ColorUtils.blendARGB(1056964608, 268435456, r4));
        this.textDrawable.setTextColor(ColorUtils.blendARGB(-1, -16777216, r4));
    }

    public void setRecording(boolean r2, boolean r3) {
        this.recording = r2;
        if (r3 == true) goto L9;
        AnimatedFloat r32 = this.recordingT;
        if (r2 == false) goto L7;
        float r22 = 1.0f;
    L8:
        r32.set(r22, true);
        goto L9
    L7:
        r22 = 0.0f;
    L9:
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.textDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
