package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class ImageReceiverSpan extends ReplacementSpan {
    private final int currentAccount;
    public final ImageReceiver imageReceiver;
    private View parent;
    private final View.OnAttachStateChangeListener parentAttachListener;
    private float radius;
    private boolean shadowEnabled;
    private final Paint shadowPaint;
    private int shadowPaintAlpha;
    private float sz;
    private float translateX;
    private float translateY;

    /* renamed from: org.telegram.ui.ImageReceiverSpan$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ ImageReceiverSpan this$0;

        public AnonymousClass1(ImageReceiverSpan r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.this$0.imageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.this$0.imageReceiver.onDetachedFromWindow();
        }
    }

    public ImageReceiverSpan(View r4, int r5, float r6) {
        this.parentAttachListener = new AnonymousClass1(this);
        this.shadowEnabled = true;
        this.shadowPaintAlpha = 255;
        this.currentAccount = r5;
        ImageReceiver r1 = new ImageReceiver(r4);
        this.imageReceiver = r1;
        r1.setCurrentAccount(r5);
        setSize(r6);
        Paint r52 = new Paint(1);
        this.shadowPaint = r52;
        r52.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(0.66f), 855638016);
        setParent(r4);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r3, CharSequence r4, int r5, int r6, float r7, int r8, int r9, int r10, Paint r11) {
        if (this.shadowEnabled == true) goto L5;
    L7:
        float r42 = this.translateX + r7;
        float r62 = (this.translateY + ((r8 + r10) / 2.0f)) - (AndroidUtilities.dp(this.sz) / 2.0f);
        if (this.shadowEnabled == false) goto L10;
        RectF r72 = AndroidUtilities.rectTmp;
        r72.set(r42, r62, AndroidUtilities.dp(this.sz) + r42, AndroidUtilities.dp(this.sz) + r62);
        float r82 = this.radius;
        r3.drawRoundRect(r72, r82, r82, this.shadowPaint);
    L10:
        this.imageReceiver.setImageCoords(r42, r62, AndroidUtilities.dp(this.sz), AndroidUtilities.dp(this.sz));
        this.imageReceiver.setAlpha(r11.getAlpha() / 255.0f);
        this.imageReceiver.draw(r3);
        return;
    L5:
        if (this.shadowPaintAlpha == r11.getAlpha()) goto L7;
        Paint r43 = this.shadowPaint;
        int r63 = r11.getAlpha();
        this.shadowPaintAlpha = r63;
        r43.setAlpha(r63);
        this.shadowPaint.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(0.66f), Theme.multAlpha(855638016, this.shadowPaintAlpha / 255.0f));
        goto L7
    }

    public void enableShadow(boolean r1) {
        this.shadowEnabled = r1;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return AndroidUtilities.dp(this.sz);
    }

    public void setParent(View r3) {
        View r0 = this.parent;
        if (r0 == r3) goto L24;
        if (r0 == null) goto L11;
        r0.removeOnAttachStateChangeListener(this.parentAttachListener);
        if (this.parent.isAttachedToWindow() == false) goto L11;
        if (r3.isAttachedToWindow() == true) goto L11;
        this.imageReceiver.onDetachedFromWindow();
    L11:
        View r02 = this.parent;
        if (r02 != null) goto L14;
    L15:
        if (r3 != null) goto L17;
    L19:
        this.parent = r3;
        this.imageReceiver.setParentView(r3);
        if (r3 == null) goto L23;
        r3.addOnAttachStateChangeListener(this.parentAttachListener);
        return;
    L23:
        return;
    L17:
        if (r3.isAttachedToWindow() == false) goto L19;
        this.imageReceiver.onAttachedToWindow();
        goto L19
    L14:
        if (r02.isAttachedToWindow() == true) goto L19;
    }

    public void setRoundRadius(float r2) {
        ImageReceiver r0 = this.imageReceiver;
        float r22 = AndroidUtilities.dp(r2);
        this.radius = r22;
        r0.setRoundRadius((int) r22);
    }

    public void setSize(float r1) {
        this.sz = r1;
    }

    public void translate(float r1, float r2) {
        this.translateX = r1;
        this.translateY = r2;
    }
}
