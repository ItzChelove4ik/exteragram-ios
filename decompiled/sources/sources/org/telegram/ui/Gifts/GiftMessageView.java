package org.telegram.ui.Gifts;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.view.View;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public class GiftMessageView extends View {
    private final GiftMessageDrawable drawable;

    public GiftMessageView(Context r1) {
        super(r1);
        GiftMessageDrawable r12 = new GiftMessageDrawable();
        this.drawable = r12;
        r12.setParentView(this);
    }

    public GiftMessageDrawable getDrawable() {
        return this.drawable;
    }

    public TextPaint getTextPaint() {
        return this.drawable.getTextPaint();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.drawable.attach();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.drawable.detach();
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        this.drawable.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        this.drawable.draw(r7);
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        int r22 = (View.MeasureSpec.getSize(r2) - getPaddingLeft()) - getPaddingRight();
        this.drawable.measure(r22);
        setMeasuredDimension((this.drawable.getMinimumWidth() + getPaddingLeft()) + getPaddingRight(), (this.drawable.getMinimumHeight() + getPaddingTop()) + getPaddingBottom());
    }

    public void setMessage(CharSequence r2) {
        this.drawable.setMessage(r2);
        requestLayout();
    }

    public void setUser(TLObject r2) {
        this.drawable.setUser(r2);
        invalidate();
    }
}
