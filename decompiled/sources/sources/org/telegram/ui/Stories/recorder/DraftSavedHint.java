package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class DraftSavedHint extends View {
    private final Paint backgroundPaint;
    private Runnable hideRunnable;
    private final StaticLayout layout;
    private final float layoutLeft;
    private final float layoutWidth;
    private final Path path;
    private final AnimatedFloat showT;
    private boolean shown;
    private final TextPaint textPaint;

    public static /* synthetic */ void $r8$lambda$V6DaR34CByNdQjIaEd4e0IcPSy0(DraftSavedHint r0) {
        r0.lambda$show$0();
    }

    public DraftSavedHint(Context r11) {
        super(r11);
        Paint r112 = new Paint(1);
        this.backgroundPaint = r112;
        TextPaint r3 = new TextPaint(1);
        this.textPaint = r3;
        this.path = new Path();
        AnimatedFloat r9 = new AnimatedFloat(this);
        this.showT = r9;
        r112.setColor(-869783512);
        r112.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(6.0f)));
        r3.setTextSize(AndroidUtilities.dp(14.0f));
        r3.setColor(-1);
        StaticLayout r1 = new StaticLayout(TextUtils.ellipsize(LocaleController.getString("StoryDraftSaved"), r3, AndroidUtilities.displaySize.x, TextUtils.TruncateAt.END), r3, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.layout = r1;
        if (r1.getLineCount() <= 0) goto L5;
        float r113 = r1.getLineWidth(0);
    L6:
        this.layoutWidth = r113;
        if (r1.getLineCount() <= 0) goto L9;
        float r114 = r1.getLineLeft(0);
    L10:
        this.layoutLeft = r114;
        r9.set(0.0f, true);
        return;
    L9:
        r114 = 0.0f;
        goto L10
    L5:
        r113 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$show$0() {
        hide(true);
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r13) {
        float r0 = this.showT.set(this.shown);
        if (r0 > 0.0f) goto L5;
        return;
    L5:
        r13.save();
        if (this.shown == false) goto L8;
        float r2 = CubicBezierInterpolator.EASE_OUT_BACK.getInterpolation(r0);
    L9:
        r13.translate(0.0f, r2 * AndroidUtilities.dp(12.0f));
        float r02 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r0);
        float r22 = getMeasuredWidth();
        float r5 = getMeasuredHeight();
        float r6 = AndroidUtilities.dp(22.0f) + this.layoutWidth;
        float r23 = (r22 / 2.0f) - Math.min(AndroidUtilities.dp(135.0f), 0.35f * r22);
        float r7 = Math.max(AndroidUtilities.dp(8.0f), r23 - (r6 / 2.0f));
        this.path.rewind();
        this.path.moveTo(r7, 0.0f);
        float r62 = r6 + r7;
        this.path.lineTo(r62, 0.0f);
        this.path.lineTo(r62, r5 - AndroidUtilities.dp(18.0f));
        this.path.lineTo(AndroidUtilities.dp(7.0f) + r23, r5 - AndroidUtilities.dp(18.0f));
        this.path.lineTo(AndroidUtilities.dp(1.0f) + r23, r5 - AndroidUtilities.dp(12.0f));
        this.path.lineTo(r23 - AndroidUtilities.dp(1.0f), r5 - AndroidUtilities.dp(12.0f));
        this.path.lineTo(r23 - AndroidUtilities.dp(7.0f), r5 - AndroidUtilities.dp(18.0f));
        this.path.lineTo(r7, r5 - AndroidUtilities.dp(18.0f));
        this.path.close();
        this.backgroundPaint.setAlpha((int) (204.0f * r02));
        r13.drawPath(this.path, this.backgroundPaint);
        r13.save();
        r13.translate((r7 + AndroidUtilities.dp(11.0f)) - this.layoutLeft, ((r5 - AndroidUtilities.dp(18.0f)) - this.layout.getHeight()) / 2.0f);
        this.textPaint.setAlpha((int) (r02 * 255.0f));
        this.layout.draw(r13);
        r13.restore();
        r13.restore();
        return;
    L8:
        r2 = 1.0f;
        goto L9
    }

    public void hide(boolean r2) {
        show(false, r2);
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(50.0f));
    }

    public void show() {
        this.showT.set(0.0f, true);
        show(true, true);
        Runnable r0 = this.hideRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        DraftSavedHint$$ExternalSyntheticLambda0 r02 = new DraftSavedHint$$ExternalSyntheticLambda0(this);
        this.hideRunnable = r02;
        AndroidUtilities.runOnUIThread(r02, 3500);
    }

    public void show(boolean r2, boolean r3) {
        if (r2 == true) goto L6;
        Runnable r0 = this.hideRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.hideRunnable = null;
    L6:
        this.shown = r2;
        if (r3 == true) goto L9;
        this.showT.set(r2, true);
    L9:
        invalidate();
    }
}
