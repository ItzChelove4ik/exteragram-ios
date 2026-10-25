package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public class LivePhotoButton extends View {
    private final AnimatedFloat animatedValue;
    private final Paint cutPaint;
    private final Drawable icon;
    private boolean value;
    private final Paint whitePaint;

    public LivePhotoButton(Context r11) {
        super(r11);
        Paint r0 = new Paint(1);
        this.whitePaint = r0;
        Paint r2 = new Paint(1);
        this.cutPaint = r2;
        this.animatedValue = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        ScaleStateListAnimator.apply(this);
        this.icon = r11.getResources().getDrawable(R.drawable.media_live_on).mutate();
        Paint.Style r10 = Paint.Style.STROKE;
        r2.setStyle(r10);
        r2.setColor(Opcodes.V_PREVIEW);
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r0.setStyle(r10);
        r0.setColor(-1);
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        float r7 = this.animatedValue.set(!this.value);
        this.icon.setBounds((getWidth() - this.icon.getIntrinsicWidth()) / 2, (getHeight() - this.icon.getIntrinsicHeight()) / 2, (getWidth() + this.icon.getIntrinsicWidth()) / 2, (getHeight() + this.icon.getIntrinsicHeight()) / 2);
        Rect r0 = this.icon.getBounds();
        float r8 = r0.left + (r0.width() * 0.325f);
        float r9 = r0.top + (r0.height() * 0.152f);
        float r10 = r0.bottom - (r0.height() * 0.152f);
        float r11 = r0.right - (r0.width() * 0.101f);
        if (r7 <= 0.0f) goto L9;
        this.cutPaint.setStrokeWidth(AndroidUtilities.dp(4.0f));
        r15.saveLayerAlpha(r0.left, r0.top, r0.right, r0.bottom, 255, 31);
        this.icon.draw(r15);
        if (this.value == false) goto L7;
        r15.drawLine(r11 - AndroidUtilities.dp(4.0f), r10 - AndroidUtilities.dp(4.0f), AndroidUtilities.lerp(r11 - AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f) + r8, r7), AndroidUtilities.lerp(r10 - AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f) + r9, r7), this.cutPaint);
    L8:
        r15.restore();
    L10:
        if (r7 <= 0.0f) goto L17;
        this.whitePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        if (this.value == false) goto L15;
        r15.drawLine(r11, r10, AndroidUtilities.lerp(r11, r8, r7), AndroidUtilities.lerp(r10, r9, r7), this.whitePaint);
        return;
    L15:
        r15.drawLine(r8, r9, AndroidUtilities.lerp(r8, r11, r7), AndroidUtilities.lerp(r9, r10, r7), this.whitePaint);
        return;
    L17:
        return;
    L7:
        r15.drawLine(r8 + AndroidUtilities.dp(4.0f), r9 + AndroidUtilities.dp(4.0f), AndroidUtilities.lerp(AndroidUtilities.dp(4.0f) + r8, r11 - AndroidUtilities.dp(4.0f), r7), AndroidUtilities.lerp(AndroidUtilities.dp(4.0f) + r9, r10 - AndroidUtilities.dp(4.0f), r7), this.cutPaint);
        goto L8
    L9:
        this.icon.draw(r15);
        goto L10
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(45.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(45.0f), TLObject.FLAG_30));
    }

    public void setValue(boolean r2, boolean r3) {
        if (this.value != r2) goto L5;
        return;
    L5:
        this.value = r2;
        if (r3 == true) goto L8;
        this.animatedValue.force(r2);
    L8:
        invalidate();
    }
}
