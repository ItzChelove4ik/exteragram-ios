package org.telegram.ui.Components.chat.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.View;
import androidx.core.math.MathUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;

/* loaded from: classes3.dex */
public class ChatActivityFadeView extends View implements Theme.Colorable {
    private static final Paint scrimFadeDimPaint = null;
    private static final Paint scrimFadeMaskPaint = null;
    private static float scrimViewDimBottom;
    private static final Paint scrimViewDimPaint = null;
    private static float scrimViewDimTop;
    private int colorKey;
    private BlurredBackgroundDrawableViewFactory factory;
    private BlurredBackgroundWithFadeDrawable fadeDrawableBottom;
    private BlurredBackgroundWithFadeDrawable fadeDrawableTop;
    private int fadeZoneBottom;
    private int fadeZoneTop;
    private BlurredBackgroundSourceColor sourceColor;

    static {
        Paint r0 = new Paint();
        scrimFadeMaskPaint = r0;
        Paint r1 = new Paint();
        scrimFadeDimPaint = r1;
        Paint r2 = new Paint();
        scrimViewDimPaint = r2;
        scrimViewDimTop = Float.NaN;
        scrimViewDimBottom = Float.NaN;
        PorterDuffXfermode r3 = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
        r0.setXfermode(r3);
        r1.setXfermode(r3);
        r1.setColor(-16777216);
        r2.setXfermode(r3);
    }

    public ChatActivityFadeView(Context r1) {
        super(r1);
    }

    private void checkBounds() {
        this.fadeDrawableTop.setBounds(0, 0, getMeasuredWidth(), this.fadeZoneTop);
        this.fadeDrawableBottom.setBounds(0, getMeasuredHeight() - this.fadeZoneBottom, getMeasuredWidth(), getMeasuredHeight());
    }

    public void drawOverScrim(Canvas r17, float r18, float r19, float r20, float r21, float r22, boolean r23) {
        float r5 = Math.min(r21, this.fadeZoneTop);
        if (r19 < r5) goto L5;
        return;
    L5:
        if (r18 >= r20) goto L22;
        int r7 = (int) (MathUtils.clamp(r22, 0.0f, 1.0f) * 255.0f);
        if (r23 == false) goto L16;
        if (r7 <= 0) goto L16;
        if (scrimViewDimTop == r19) goto L13;
    L14:
        scrimViewDimTop = r19;
        scrimViewDimBottom = this.fadeZoneTop;
        scrimViewDimPaint.setShader(new LinearGradient(0.0f, r19, 0.0f, this.fadeZoneTop, -16777216, 0, Shader.TileMode.CLAMP));
    L15:
        Paint r6 = scrimViewDimPaint;
        r6.setAlpha(r7);
        r17.drawRect(r18, r19, r20, r5, r6);
        goto L16
    L13:
        if (scrimViewDimBottom == this.fadeZoneTop) goto L15;
    L16:
        Canvas r1 = r17;
        int r62 = r1.saveLayer(r18, r19, r20, r5, scrimFadeMaskPaint);
        this.fadeDrawableTop.draw(r1);
        if (r7 <= 0) goto L19;
        Paint r52 = scrimFadeDimPaint;
        r52.setAlpha(r7);
        r1.drawRect(r18, r19, r20, r5, r52);
        r1 = r1;
    L19:
        r1.restoreToCount(r62);
        return;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public int getFadeZoneTop() {
        return this.fadeZoneTop;
    }

    @Override // android.view.View
    public void onDraw(Canvas r2) {
        super.onDraw(r2);
        this.fadeDrawableTop.draw(r2);
        this.fadeDrawableBottom.draw(r2);
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        checkBounds();
    }

    public void setFadeHeightBottom(int r2) {
        this.fadeDrawableBottom.setFadeHeight(r2, true);
    }

    public void setFadeHeightTop(int r2) {
        this.fadeDrawableTop.setFadeHeight(-r2, true);
    }

    public void setFadeHeightTop(int r1, boolean r2) {
        this.fadeDrawableTop.setFadeHeight(-r1, r2);
    }

    public void setFadeTopAlpha(int r2) {
        if (this.fadeDrawableTop.getAlpha() == r2) goto L6;
        this.fadeDrawableTop.setAlpha(r2);
        invalidate();
        return;
    }

    public void setFadeZoneBottom(int r2) {
        if (this.fadeZoneBottom == r2) goto L6;
        this.fadeZoneBottom = r2;
        checkBounds();
        invalidate();
        return;
    }

    public void setFadeZoneTop(int r2) {
        if (this.fadeZoneTop == r2) goto L6;
        this.fadeZoneTop = r2;
        checkBounds();
        invalidate();
        return;
    }

    public void setIgnoreFastWay(boolean r2) {
        this.fadeDrawableTop.setIgnoreFastWay(r2);
        this.fadeDrawableBottom.setIgnoreFastWay(r2);
    }

    public void setTopFadeColor(int r2) {
        this.fadeDrawableTop.setOverrideFadeColor(r2);
        invalidate();
    }

    public void setup(BlurredBackgroundDrawableViewFactory r2) {
        setup(r2, null);
    }

    public void setup(BlurredBackgroundDrawableViewFactory r5, BlurredBackgroundColorProvider r6) {
        BlurredBackgroundWithFadeDrawable r0 = new BlurredBackgroundWithFadeDrawable(r5.create(this).setColorProvider(r6));
        this.fadeDrawableTop = r0;
        r0.setFadeHeight(-AndroidUtilities.dp(30.0f), true);
        BlurredBackgroundWithFadeDrawable r02 = new BlurredBackgroundWithFadeDrawable(r5.create(this).setColorProvider(r6));
        this.fadeDrawableBottom = r02;
        r02.setFadeHeight(AndroidUtilities.dp(30.0f), true);
    }

    public void setupColorKey(int r2) {
        this.colorKey = r2;
        if (this.sourceColor != null) goto L6;
        BlurredBackgroundSourceColor r0 = new BlurredBackgroundSourceColor();
        this.sourceColor = r0;
        r0.setColor(Theme.getColor(r2));
        BlurredBackgroundDrawableViewFactory r22 = new BlurredBackgroundDrawableViewFactory(this.sourceColor);
        this.factory = r22;
        setup(r22);
        return;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        BlurredBackgroundSourceColor r0 = this.sourceColor;
        if (r0 == null) goto L8;
        int r1 = this.colorKey;
        if (r1 == (-1)) goto L9;
        r0.setColor(Theme.getColor(r1));
        invalidate();
        return;
    L9:
        return;
    }
}
