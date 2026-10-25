package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.PlayPauseDrawable;

/* loaded from: classes7.dex */
public class PlayPauseButton extends View {
    private final Paint circlePaint;
    public final PlayPauseDrawable drawable;

    public PlayPauseButton(Context r6) {
        super(r6);
        Paint r62 = new Paint(1);
        this.circlePaint = r62;
        PlayPauseDrawable r0 = new PlayPauseDrawable(10);
        this.drawable = r0;
        r62.setColor(-1);
        r62.setShadowLayer(1.0f, 0.0f, 0.0f, 419430400);
        r62.setStyle(Paint.Style.STROKE);
        r0.setCallback(this);
        r0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        this.circlePaint.setStrokeWidth(AndroidUtilities.dpf2(1.66f));
        r7.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, AndroidUtilities.dp(10.0f), this.circlePaint);
        this.drawable.setBounds(0, 0, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r7.save();
        r7.translate((getWidth() - AndroidUtilities.dp(10.0f)) / 2.0f, (getHeight() - AndroidUtilities.dp(10.0f)) / 2.0f);
        this.drawable.draw(r7);
        r7.restore();
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.drawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
