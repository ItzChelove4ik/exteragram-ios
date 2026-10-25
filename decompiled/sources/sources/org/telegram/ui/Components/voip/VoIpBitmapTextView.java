package org.telegram.ui.Components.voip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIpBitmapTextView extends View {
    private volatile Bitmap bitmap;
    private final Paint paint;
    private final String text;
    private final TextPaint textPaint;
    private final float textWidth;

    public static /* synthetic */ void $r8$lambda$xtNJy4NIOu9JvWyvZ8D333AJLCs(VoIpBitmapTextView r0) {
        r0.lambda$onLayout$0();
    }

    public VoIpBitmapTextView(Context r3, String r4) {
        super(r3);
        TextPaint r32 = new TextPaint(1);
        this.textPaint = r32;
        this.paint = new Paint(1);
        r32.setTextAlign(Paint.Align.CENTER);
        r32.setTextSize(AndroidUtilities.dp(13.0f));
        r32.setColor(-1);
        r32.setTypeface(AndroidUtilities.bold());
        this.textWidth = r32.measureText(r4);
        this.text = r4;
    }

    private /* synthetic */ void lambda$onLayout$0() {
        this.bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(this.bitmap).drawText(this.text, getMeasuredWidth() / 2, (int) ((getMeasuredHeight() / 2) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f)), this.textPaint);
        postInvalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        super.onDraw(r3);
        if (this.bitmap == null) goto L6;
        r3.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paint);
        return;
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (r1 == false) goto L6;
        Utilities.globalQueue.postRunnable(new VoIpBitmapTextView$$ExternalSyntheticLambda0(this));
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((((int) this.textWidth) + getPaddingLeft()) + getPaddingRight(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
    }
}
