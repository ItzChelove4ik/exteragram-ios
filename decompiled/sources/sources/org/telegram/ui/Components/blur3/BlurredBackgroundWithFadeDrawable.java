package org.telegram.ui.Components.blur3;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.debug.DebugConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;

/* loaded from: classes3.dex */
public class BlurredBackgroundWithFadeDrawable extends Drawable {
    private int alpha;
    private final Matrix bitmapMatrix;
    private final Paint bitmapPaint;
    private BitmapShader bitmapShader;
    private int colorStaticLast;
    private final Paint colorStaticPaint;
    private Shader composeShader;
    private final BlurredBackgroundDrawable drawable;
    private int fadeHeight;
    private Shader gradientShader;
    private boolean ignoreFastWay;
    private Bitmap lastBitmap;
    private int lastOverrideFadeColor;
    private int lastOverrideFadeHeight;
    private final Paint maskFadeGradientPaint;
    private final Matrix matrix;
    private final Matrix matrixTmp;
    private boolean opacity;
    private Shader overrideComposeShader;
    private int overrideFadeColor;
    private boolean overrideFadeEnabled;
    private final Paint overrideFadePaint;
    private Shader overrideFadeShader;
    private Shader overrideMaskShader;
    private Shader shader;

    public BlurredBackgroundWithFadeDrawable(BlurredBackgroundDrawable r6) {
        Paint r0 = new Paint(1);
        this.maskFadeGradientPaint = r0;
        this.matrix = new Matrix();
        this.matrixTmp = new Matrix();
        this.bitmapMatrix = new Matrix();
        Paint r2 = new Paint(1);
        this.bitmapPaint = r2;
        this.overrideFadePaint = new Paint(5);
        this.colorStaticPaint = new Paint(1);
        this.alpha = 255;
        this.drawable = r6;
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        r2.setFilterBitmap(true);
        setFadeHeight(AndroidUtilities.dp(40.0f), false);
    }

    private static LinearGradient createGradient(int r11, boolean r12) {
        int r0 = Color.alpha(r11);
        if (r12 == false) goto L7;
        return new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, new int[]{ColorUtils.setAlphaComponent(r11, 0), ColorUtils.setAlphaComponent(r11, (r0 * 96) / 285), ColorUtils.setAlphaComponent(r11, (r0 * 176) / 285), ColorUtils.setAlphaComponent(r11, (r0 * 232) / 285)}, null, Shader.TileMode.CLAMP);
    L7:
        return new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, new int[]{ColorUtils.setAlphaComponent(r11, 0), ColorUtils.setAlphaComponent(r11, (r0 * 96) / 255), ColorUtils.setAlphaComponent(r11, (r0 * 176) / 255), ColorUtils.setAlphaComponent(r11, (r0 * 232) / 255), ColorUtils.setAlphaComponent(r11, (r0 * 255) / 255)}, null, Shader.TileMode.CLAMP);
    }

    private static LinearGradient createOverrideFadeGradient(int r14, int r15, float r16) {
        boolean r0 = Theme.isCurrentThemeDark();
        if (r0 == false) goto L5;
        float r1 = 0.95f;
    L6:
        if (r0 == false) goto L8;
        float r2 = 0.9f;
    L9:
        if (r0 == false) goto L11;
        float r02 = 0.78f;
    L12:
        int r5 = 0;
        float[] r4 = {r1, r2, r02, 0.62f, 0.4f, 0.18f, 0.05f, 0.0f};
        int[] r11 = new int[8];
    L13:
        if (r5 >= 8) goto L16;
        r11[r5] = ColorUtils.setAlphaComponent(r14, Math.round((r4[r5] * 255.0f) * r16));
        r5 = r5 + 1;
        goto L13
    L16:
        return new LinearGradient(0.0f, 0.0f, 0.0f, Math.max(1, r15), r11, new float[]{0.0f, 0.5f, 0.65f, 0.75f, 0.84f, 0.92f, 0.97f, 1.0f}, Shader.TileMode.CLAMP);
    L11:
        r02 = 0.83f;
        goto L12
    L8:
        r2 = 0.93f;
        goto L9
    L5:
        r1 = 1.0f;
        goto L6
    }

    private void drawOverrideFade(Canvas r7, Rect r8) {
        int r0 = Math.max(1, r8.height());
        int r1 = this.lastOverrideFadeColor;
        int r2 = this.overrideFadeColor;
        if (r1 == r2) goto L5;
    L8:
        this.lastOverrideFadeColor = r2;
        this.lastOverrideFadeHeight = r0;
        this.overrideFadeShader = createOverrideFadeGradient(r2, r0, 1.0f);
        this.overrideMaskShader = createOverrideFadeGradient(-16777216, r0, 0.75f);
        this.overrideComposeShader = null;
        this.overrideFadePaint.setShader(this.overrideFadeShader);
    L9:
        this.matrixTmp.reset();
        this.matrixTmp.postTranslate(r8.left, r8.top);
        BlurredBackgroundSource r02 = this.drawable.getUnwrappedSource();
        if (this.ignoreFastWay == false) goto L12;
    L32:
        this.overrideFadeShader.setLocalMatrix(this.matrixTmp);
        this.overrideFadePaint.setAlpha(this.alpha);
        r7.drawRect(r8, this.overrideFadePaint);
        return;
    L12:
        if (DebugConfig.getDisableChatFadeWallpaperBlend() == true) goto L32;
        if ((r02 instanceof BlurredBackgroundSourceBitmap) == false) goto L32;
        BlurredBackgroundSourceBitmap r03 = (BlurredBackgroundSourceBitmap) r02;
        int r12 = Build.VERSION.SDK_INT;
        if (r12 < 28) goto L32;
        Bitmap r22 = r03.getBitmap();
        if (r22 == null) goto L32;
        if (this.bitmapShader != null) goto L22;
    L23:
        this.lastBitmap = r22;
        Shader.TileMode r5 = Shader.TileMode.CLAMP;
        BitmapShader r4 = new BitmapShader(r22, r5, r5);
        this.bitmapShader = r4;
        if (r12 < 33) goto L26;
        r4.setFilterMode(2);
    L26:
        this.overrideComposeShader = null;
    L28:
        if (this.overrideComposeShader != null) goto L30;
        ComposeShader r13 = new ComposeShader(this.bitmapShader, this.overrideMaskShader, PorterDuff.Mode.DST_IN);
        this.overrideComposeShader = r13;
        this.bitmapPaint.setShader(r13);
    L30:
        this.overrideMaskShader.setLocalMatrix(this.matrixTmp);
        this.bitmapMatrix.set(r03.getMatrix());
        this.bitmapMatrix.postTranslate(-this.drawable.getSourceOffsetX(), -this.drawable.getSourceOffsetY());
        this.bitmapShader.setLocalMatrix(this.bitmapMatrix);
        this.bitmapPaint.setAlpha(this.alpha);
        r7.drawRect(r8, this.bitmapPaint);
        this.overrideFadeShader.setLocalMatrix(this.matrixTmp);
        this.overrideFadePaint.setAlpha(Math.round(this.alpha * 0.8f));
        r7.drawRect(r8, this.overrideFadePaint);
        return;
    L22:
        if (this.lastBitmap == r22) goto L28;
    L5:
        if (this.lastOverrideFadeHeight != r0) goto L8;
        if (this.overrideFadeShader != null) goto L9;
        goto L8
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        Rect r0 = getBounds();
        if (r0.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.alpha != 0) goto L8;
        return;
    L8:
        if (this.overrideFadeEnabled == false) goto L11;
        drawOverrideFade(r11, r0);
        return;
    L11:
        BlurredBackgroundSource r1 = this.drawable.getUnwrappedSource();
        boolean r2 = this.ignoreFastWay;
        int r3 = 0;
        if (r2 == false) goto L14;
    L25:
        if (r2 == false) goto L27;
    L58:
        int r112 = r11.saveLayerAlpha(r0.left, r0.top, r0.right, r0.bottom, this.alpha);
        if (this.fadeHeight >= 0) goto L61;
        r3 = r0.height() + this.fadeHeight;
    L61:
        this.drawable.draw(r11);
        r11.translate(r0.left, r0.top + r3);
        r11.drawRect(0.0f, -r3, r0.width(), r0.height() - r3, this.maskFadeGradientPaint);
        r11.restoreToCount(r112);
        return;
    L27:
        if ((r1 instanceof BlurredBackgroundSourceBitmap) == false) goto L58;
        int r22 = Build.VERSION.SDK_INT;
        if (r22 < 28) goto L58;
        BlurredBackgroundSourceBitmap r12 = (BlurredBackgroundSourceBitmap) r1;
        Bitmap r4 = r12.getBitmap();
        if (r4 == null) goto L65;
        boolean r6 = true;
        if (this.colorStaticLast == (-16777216)) goto L36;
    L39:
        this.gradientShader = createGradient(-16777216, this.opacity);
        this.colorStaticLast = -16777216;
        boolean r5 = true;
    L41:
        if (this.bitmapShader != null) goto L43;
    L46:
        this.lastBitmap = r4;
        Shader.TileMode r7 = Shader.TileMode.CLAMP;
        BitmapShader r52 = new BitmapShader(r4, r7, r7);
        this.bitmapShader = r52;
        if (r22 < 33) goto L49;
        r52.setFilterMode(2);
    L49:
        if (r6 == false) goto L51;
    L52:
        ComposeShader r23 = new ComposeShader(this.bitmapShader, this.gradientShader, PorterDuff.Mode.DST_IN);
        this.composeShader = r23;
        this.bitmapPaint.setShader(r23);
    L54:
        if (this.fadeHeight >= 0) goto L56;
        r3 = this.fadeHeight + r0.height();
    L56:
        this.matrixTmp.set(this.matrix);
        this.matrixTmp.postTranslate(r0.left, r0.top + r3);
        this.gradientShader.setLocalMatrix(this.matrixTmp);
        this.bitmapMatrix.set(r12.getMatrix());
        this.bitmapMatrix.postTranslate(-this.drawable.getSourceOffsetX(), -this.drawable.getSourceOffsetY());
        this.bitmapShader.setLocalMatrix(this.bitmapMatrix);
        this.bitmapPaint.setAlpha(this.alpha);
        r11.drawRect(r0, this.bitmapPaint);
        return;
    L51:
        if (this.composeShader != null) goto L54;
    L43:
        if (this.lastBitmap != r4) goto L46;
        r6 = r5;
        goto L49
    L36:
        if (this.gradientShader == null) goto L39;
        r5 = false;
        goto L41
    L65:
        return;
    L14:
        if ((r1 instanceof BlurredBackgroundSourceColor) == false) goto L25;
        int r13 = ((BlurredBackgroundSourceColor) r1).getColor();
        if (this.colorStaticLast == r13) goto L18;
    L19:
        LinearGradient r24 = createGradient(r13, this.opacity);
        this.gradientShader = r24;
        this.colorStaticLast = r13;
        this.colorStaticPaint.setShader(r24);
    L21:
        if (this.fadeHeight >= 0) goto L23;
        r3 = r0.height() + this.fadeHeight;
    L23:
        this.matrixTmp.set(this.matrix);
        this.matrixTmp.postTranslate(r0.left, r0.top + r3);
        this.gradientShader.setLocalMatrix(this.matrixTmp);
        this.colorStaticPaint.setAlpha(this.alpha);
        r11.drawRect(r0, this.colorStaticPaint);
        return;
    L18:
        if (this.gradientShader != null) goto L21;
        goto L19
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        this.drawable.setBounds(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setFadeHeight(int r3, boolean r4) {
        if (this.fadeHeight == r3) goto L5;
    L7:
        this.fadeHeight = r3;
        this.opacity = r4;
        Paint r0 = this.maskFadeGradientPaint;
        LinearGradient r42 = createGradient(-16777216, r4);
        this.shader = r42;
        r0.setShader(r42);
        this.colorStaticPaint.setShader(null);
        this.matrix.reset();
        this.matrix.setScale(1.0f, r3);
        if (r3 >= 0) goto L10;
        this.matrix.postTranslate(0.0f, -r3);
    L10:
        this.shader.setLocalMatrix(this.matrix);
        return;
    L5:
        if (this.opacity != r4) goto L7;
    }

    public void setIgnoreFastWay(boolean r1) {
        this.ignoreFastWay = r1;
    }

    public void setOverrideFadeColor(int r2) {
        if (this.overrideFadeColor == r2) goto L5;
        this.overrideFadeColor = r2;
        this.overrideFadeShader = null;
    L5:
        this.overrideFadeEnabled = true;
        invalidateSelf();
    }
}
