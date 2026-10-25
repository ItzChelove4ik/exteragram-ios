package org.telegram.ui.Components.voip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class VoIPTimerView extends View {
    Paint activePaint;
    private final Drawable callsDeclineDrawable;
    String currentTimeStr;
    Paint inactivePaint;
    private boolean isDrawCallIcon;
    RectF rectF;
    private int signalBarCount;
    TextPaint textPaint;
    StaticLayout timerLayout;
    Runnable updater;

    public static /* synthetic */ void $r8$lambda$wkEUcSXrmFjNCgkruIOdsGJuclg(VoIPTimerView r0) {
        r0.lambda$new$0();
    }

    public VoIPTimerView(Context r5) {
        super(r5);
        this.rectF = new RectF();
        this.activePaint = new Paint(1);
        this.inactivePaint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.signalBarCount = 4;
        this.isDrawCallIcon = false;
        this.updater = new VoIPTimerView$$ExternalSyntheticLambda0(this);
        this.textPaint.setTextSize(AndroidUtilities.dp(15.0f));
        this.textPaint.setColor(-1);
        this.activePaint.setColor(ColorUtils.setAlphaComponent(-1, TLRPC.LAYER));
        this.inactivePaint.setColor(ColorUtils.setAlphaComponent(-1, 102));
        Drawable r52 = ContextCompat.getDrawable(r5, R.drawable.calls_decline);
        this.callsDeclineDrawable = r52;
        r52.setBounds(0, 0, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
    }

    private /* synthetic */ void lambda$new$0() {
        if (getVisibility() != 0) goto L6;
        updateTimer();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        StaticLayout r0 = this.timerLayout;
        int r2 = 0;
        if (r0 != null) goto L5;
        int r3 = 0;
    L6:
        r14.save();
        r14.translate((getMeasuredWidth() - r3) / 2.0f, 0.0f);
        r14.save();
        if (this.isDrawCallIcon == false) goto L9;
        r14.translate(-AndroidUtilities.dp(7.0f), -AndroidUtilities.dp(3.0f));
        this.callsDeclineDrawable.draw(r14);
    L17:
        r14.restore();
        if (r0 == null) goto L20;
        r14.translate(AndroidUtilities.dp(21.0f), 0.0f);
        r0.draw(r14);
    L20:
        r14.restore();
        return;
    L9:
        r14.translate(0.0f, (getMeasuredHeight() - AndroidUtilities.dp(11.0f)) / 2.0f);
    L11:
        if (r2 >= 4) goto L17;
        int r32 = r2 + 1;
        if (r32 <= this.signalBarCount) goto L15;
        Paint r4 = this.inactivePaint;
    L16:
        float r10 = r2;
        this.rectF.set(AndroidUtilities.dpf2(4.16f) * r10, AndroidUtilities.dpf2(2.75f) * (3 - r2), (AndroidUtilities.dpf2(4.16f) * r10) + AndroidUtilities.dpf2(2.75f), AndroidUtilities.dp(11.0f));
        r14.drawRoundRect(this.rectF, AndroidUtilities.dpf2(0.7f), AndroidUtilities.dpf2(0.7f), r4);
        r2 = r32;
        goto L11
    L15:
        r4 = this.activePaint;
        goto L16
    L5:
        r3 = r0.getWidth() + AndroidUtilities.dp(21.0f);
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        StaticLayout r22 = this.timerLayout;
        if (r22 == null) goto L6;
        setMeasuredDimension(View.MeasureSpec.getSize(r1), r22.getHeight());
        return;
    L6:
        setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(15.0f));
    }

    public void setDrawCallIcon() {
        this.isDrawCallIcon = true;
        invalidate();
    }

    public void setSignalBarCount(int r1) {
        this.signalBarCount = r1;
        invalidate();
    }

    @Override // android.view.View
    public void setVisibility(int r10) {
        if (getVisibility() == r10) goto L7;
        if (r10 != 0) goto L6;
        this.currentTimeStr = "00:00";
        String r2 = this.currentTimeStr;
        TextPaint r3 = this.textPaint;
        this.timerLayout = new StaticLayout(r2, r3, (int) r3.measureText(r2), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        updateTimer();
        goto L7
    L6:
        this.currentTimeStr = null;
        this.timerLayout = null;
    L7:
        super.setVisibility(r10);
    }

    public void updateTimer() {
        removeCallbacks(this.updater);
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return;
    L5:
        String r02 = AndroidUtilities.formatLongDuration((int) (r0.getCallDuration() / 1000));
        String r1 = this.currentTimeStr;
        if (r1 != null) goto L8;
    L9:
        this.currentTimeStr = r02;
        if (this.timerLayout != null) goto L12;
        requestLayout();
    L12:
        String r2 = this.currentTimeStr;
        TextPaint r3 = this.textPaint;
        this.timerLayout = new StaticLayout(r2, r3, (int) r3.measureText(r2), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    L13:
        postDelayed(this.updater, 300);
        invalidate();
        return;
    L8:
        if (r1.equals(r02) == true) goto L13;
        goto L9
    }
}
