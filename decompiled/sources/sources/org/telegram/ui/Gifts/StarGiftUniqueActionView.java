package org.telegram.ui.Gifts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stars.StarGiftUniqueActionLayout;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class StarGiftUniqueActionView extends View {
    Drawable background;
    private final StarGiftUniqueActionLayout layout;
    private float layoutX;
    private float layoutY;
    private final Theme.ResourcesProvider resourcesProvider;

    public StarGiftUniqueActionView(Context r1, int r2, Theme.ResourcesProvider r3) {
        super(r1);
        this.resourcesProvider = r3;
        StarGiftUniqueActionLayout r12 = new StarGiftUniqueActionLayout(r2, this, r3);
        this.layout = r12;
        r12.getMessageDrawable().setCallback(this);
        NotificationCenter.listenEmojiLoading(this);
    }

    public StarGiftUniqueActionLayout getLayout() {
        return this.layout;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.layout.attach();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.layout.detach();
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        if ((getParent() instanceof View) == false) goto L5;
        int r0 = ((View) getParent()).getHeight();
    L6:
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        if (r1 == null) goto L9;
        r1.applyServiceShaderMatrix(getMeasuredWidth(), r0, 0.0f, getY());
    L10:
        int r02 = (int) this.layout.getWidth();
        this.layoutX = (getWidth() - r02) / 2.0f;
        float r03 = this.layout.getWidth() + AndroidUtilities.dp(8.0f);
        float r3 = (getWidth() - r03) / 2.0f;
        float r12 = this.layoutY - AndroidUtilities.dp(4.0f);
        RectF r4 = AndroidUtilities.rectTmp;
        r4.set(r3, r12, r03 + r3, (this.layout.getHeight() + r12) + AndroidUtilities.dp(8.0f));
        Rect r04 = AndroidUtilities.rectTmp2;
        r4.round(r04);
        this.background.setBounds(r04);
        this.background.draw(r7);
        r7.save();
        r7.translate(this.layoutX, this.layoutY);
        this.layout.draw(r7);
        this.layout.drawOutbounds(r7);
        r7.restore();
        return;
    L9:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), r0, 0.0f, getY());
        goto L10
    L5:
        r0 = 0;
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        int r32 = View.MeasureSpec.getSize(r3);
        int r42 = (int) this.layout.getWidth();
        int r0 = (int) this.layout.getHeight();
        this.layoutX = (r32 - r42) / 2.0f;
        float r43 = getPaddingTop();
        this.layoutY = r43;
        setMeasuredDimension(r32, (((int) r43) + r0) + getPaddingBottom());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        return this.layout.onTouchEvent(this.layoutX, this.layoutY, r3);
    }

    public void set(TL_stars.TL_starGiftUnique r8, long r9, TLRPC.TL_textWithEntities r11, String r12, boolean r13) {
        this.layout.set(r8, r9, r11, r12, r13);
        requestLayout();
        invalidate();
    }

    public void setLayoutBackground(Drawable r1) {
        this.background = r1;
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.layout.getMessageDrawable()) goto L11;
        return false;
    L11:
        return true;
    }
}
