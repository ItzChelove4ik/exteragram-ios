package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class LoginOrView extends View {
    private Paint linePaint;
    private View measureAfter;
    private String string;
    private Rect textBounds;
    private TextPaint textPaint;

    public LoginOrView(Context r2) {
        super(r2);
        this.textPaint = new TextPaint(1);
        this.linePaint = new Paint(1);
        this.textBounds = new Rect();
        this.string = LocaleController.getString(R.string.LoginOrSingInWithGoogle);
        this.textPaint.setTextSize(AndroidUtilities.dp(14.0f));
        updateColors();
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        super.onDraw(r18);
        if (this.measureAfter == null) goto L5;
        float r1 = ((((getWidth() - this.textBounds.width()) - AndroidUtilities.dp(8.0f)) - this.measureAfter.getPaddingLeft()) - this.measureAfter.getPaddingRight()) / 2.0f;
    L6:
        r18.drawLine((((getWidth() - this.textBounds.width()) / 2.0f) - AndroidUtilities.dp(8.0f)) - r1, getHeight() / 2.0f, ((getWidth() - this.textBounds.width()) / 2.0f) - AndroidUtilities.dp(8.0f), getHeight() / 2.0f, this.linePaint);
        r18.drawLine(((getWidth() + this.textBounds.width()) / 2.0f) + AndroidUtilities.dp(8.0f), getHeight() / 2.0f, (((getWidth() + this.textBounds.width()) / 2.0f) + AndroidUtilities.dp(8.0f)) + r1, getHeight() / 2.0f, this.linePaint);
        r18.drawText(this.string, (getWidth() - this.textBounds.width()) / 2.0f, (getHeight() + this.textBounds.height()) / 2.0f, this.textPaint);
        return;
    L5:
        r1 = AndroidUtilities.dp(64.0f);
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        View r0 = this.measureAfter;
        if (r0 == null) goto L5;
        r3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r0.getMeasuredWidth()), TLObject.FLAG_30);
    L5:
        super.onMeasure(r3, r4);
        TextPaint r32 = this.textPaint;
        String r42 = this.string;
        r32.getTextBounds(r42, 0, r42.length(), this.textBounds);
    }

    public void setMeasureAfter(View r1) {
        this.measureAfter = r1;
    }

    public void updateColors() {
        this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        this.linePaint.setColor(Theme.getColor(Theme.key_sheet_scrollUp));
        invalidate();
    }
}
