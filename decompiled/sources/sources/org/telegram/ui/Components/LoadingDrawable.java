package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class LoadingDrawable extends Drawable {
    private boolean appearByGradient;
    private LinearGradient appearGradient;
    private int appearGradientWidth;
    private Matrix appearMatrix;
    private Paint appearPaint;
    public Paint backgroundPaint;
    public Integer color1;
    public Integer color2;
    public int colorKey1;
    public int colorKey2;
    private LinearGradient disappearGradient;
    private int disappearGradientWidth;
    private Matrix disappearMatrix;
    private Paint disappearPaint;
    private long disappearStart;
    private LinearGradient gradient;
    private int gradientColor1;
    private int gradientColor2;
    private int gradientStrokeColor1;
    private int gradientStrokeColor2;
    private int gradientWidth;
    private float gradientWidthScale;
    private Rect lastBounds;
    private Matrix matrix;
    public Paint paint;
    private Path path;
    private float[] radii;
    private RectF rectF;
    public Theme.ResourcesProvider resourcesProvider;
    private float speed;
    private long start;
    public boolean stroke;
    public Integer strokeColor1;
    public Integer strokeColor2;
    private LinearGradient strokeGradient;
    private Matrix strokeMatrix;
    public Paint strokePaint;
    private Path usePath;

    public LoadingDrawable() {
        this.start = -1;
        this.disappearStart = -1;
        this.matrix = new Matrix();
        this.strokeMatrix = new Matrix();
        this.colorKey1 = Theme.key_dialogBackground;
        this.colorKey2 = Theme.key_dialogBackgroundGray;
        float r0 = 1.0f;
        this.gradientWidthScale = 1.0f;
        this.speed = 1.0f;
        this.paint = new Paint(1);
        this.strokePaint = new Paint(1);
        this.path = new Path();
        this.radii = new float[8];
        this.rectF = new RectF();
        this.strokePaint.setStyle(Paint.Style.STROKE);
        Paint r3 = this.strokePaint;
        if (AndroidUtilities.density <= 2.0f) goto L5;
        r0 = 2.0f;
    L5:
        r3.setStrokeWidth(r0);
    }

    public LoadingDrawable(Theme.ResourcesProvider r1) {
        this();
        this.resourcesProvider = r1;
    }

    public void disappear() {
        if (isDisappeared() == false) goto L5;
        return;
    L5:
        if (isDisappearing() == true) goto L9;
        this.disappearStart = SystemClock.elapsedRealtime();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r30) {
        if (isDisappeared() == true) goto L99;
        Rect r7 = getBounds();
        if (getPaintAlpha() <= 0) goto L100;
        int r2 = r7.width();
        if (r2 > 0) goto L11;
        r2 = AndroidUtilities.dp(200.0f);
    L11:
        int r22 = (int) (Math.min(AndroidUtilities.dp(400.0f), r2) * this.gradientWidthScale);
        Integer r4 = this.color1;
        if (r4 == null) goto L14;
        int r42 = r4.intValue();
    L15:
        Integer r5 = this.color2;
        if (r5 == null) goto L18;
        int r52 = r5.intValue();
    L19:
        Integer r6 = this.strokeColor1;
        if (r6 == null) goto L22;
        int r62 = r6.intValue();
    L23:
        Integer r8 = this.strokeColor2;
        if (r8 == null) goto L26;
        int r82 = r8.intValue();
    L28:
        if (this.gradient != null) goto L30;
    L39:
        this.gradientWidth = r22;
        this.gradientColor1 = r42;
        this.gradientColor2 = r52;
        float r14 = this.gradientWidth;
        int r23 = this.gradientColor1;
        Shader.TileMode r18 = Shader.TileMode.REPEAT;
        LinearGradient r11 = new LinearGradient(0.0f, 0.0f, r14, 0.0f, new int[]{r23, this.gradientColor2, r23}, new float[]{0.0f, 0.67f, 1.0f}, r18);
        this.gradient = r11;
        r11.setLocalMatrix(this.matrix);
        this.paint.setShader(this.gradient);
        this.gradientStrokeColor1 = r62;
        this.gradientStrokeColor2 = r82;
        float r24 = this.gradientWidth;
        int r43 = this.gradientStrokeColor1;
        LinearGradient r17 = new LinearGradient(0.0f, 0.0f, r24, 0.0f, new int[]{r43, r43, this.gradientStrokeColor2, r43}, new float[]{0.0f, 0.4f, 0.67f, 1.0f}, r18);
        this.strokeGradient = r17;
        r17.setLocalMatrix(this.strokeMatrix);
        this.strokePaint.setShader(this.strokeGradient);
    L40:
        long r44 = SystemClock.elapsedRealtime();
        if (this.start >= 0) goto L43;
        this.start = r44;
    L43:
        float r25 = (((float) Math.pow((((r44 - this.start) / 2000.0f) * this.speed) / 4.0f, 0.8500000238418579d)) * 4.0f) * AndroidUtilities.density;
        int r63 = this.gradientWidth;
        float r26 = (r25 * r63) % r63;
        float r64 = (r44 - this.start) / 550.0f;
        if (this.disappearStart <= 0) goto L46;
        float r83 = 1.0f - CubicBezierInterpolator.EASE_OUT.getInterpolation(Math.min(1.0f, (r44 - r8) / 320.0f));
    L48:
        if (isDisappearing() == false) goto L58;
        float r19 = 1.0f;
        int r45 = Math.max(AndroidUtilities.dp(200.0f), r7.width() / 3);
        if (r83 < 1.0f) goto L52;
    L59:
        boolean r12 = false;
    L61:
        if (this.appearByGradient == false) goto L71;
        int r46 = Math.max(AndroidUtilities.dp(200.0f), r7.width() / 3);
        if (r64 >= r19) goto L71;
        if (this.appearPaint != null) goto L68;
        this.appearPaint = new Paint(1);
        this.appearGradientWidth = r46;
        this.appearGradient = new LinearGradient(0.0f, 0.0f, r46, 0.0f, new int[]{16777215, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        Matrix r3 = new Matrix();
        this.appearMatrix = r3;
        this.appearGradient.setLocalMatrix(r3);
        this.appearPaint.setShader(this.appearGradient);
        this.appearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L70:
        this.rectF.set(r7);
        this.rectF.inset(-this.strokePaint.getStrokeWidth(), -this.strokePaint.getStrokeWidth());
        r30.saveLayerAlpha(this.rectF, 255, 31);
        boolean r142 = true;
    L72:
        this.matrix.setTranslate(r26, 0.0f);
        this.gradient.setLocalMatrix(this.matrix);
        this.strokeMatrix.setTranslate(r26, 0.0f);
        this.strokeGradient.setLocalMatrix(this.strokeMatrix);
        Path r27 = this.usePath;
        if (r27 != null) goto L84;
        Rect r28 = this.lastBounds;
        if (r28 != null) goto L78;
    L79:
        this.path.rewind();
        if (this.lastBounds != null) goto L82;
        this.lastBounds = new Rect();
    L82:
        this.lastBounds.set(r7);
        this.rectF.set(this.lastBounds);
        this.path.addRoundRect(this.rectF, this.radii, Path.Direction.CW);
    L83:
        r27 = this.path;
        goto L84
    L78:
        if (r28.equals(r7) == true) goto L83;
    L84:
        Paint r32 = this.backgroundPaint;
        if (r32 == null) goto L87;
        r30.drawPath(r27, r32);
    L87:
        r30.drawPath(r27, this.paint);
        if (this.stroke == false) goto L90;
        r30.drawPath(r27, this.strokePaint);
    L90:
        if (r142 == false) goto L92;
        r30.save();
        int r29 = this.appearGradientWidth + r7.width();
        this.appearMatrix.setTranslate(r7.left + ((r64 * (r29 + r3)) - this.appearGradientWidth), 0.0f);
        this.appearGradient.setLocalMatrix(this.appearMatrix);
        int r210 = (int) this.strokePaint.getStrokeWidth();
        r30.drawRect(r7.left - r210, r7.top - r210, r7.right + r210, r7.bottom + r210, this.appearPaint);
        r30.restore();
        r30.restore();
    L92:
        if (r12 == false) goto L95;
        r30.save();
        int r1 = this.disappearGradientWidth + r7.width();
        this.disappearMatrix.setTranslate(r7.right - ((r83 * (r1 + r2)) - this.disappearGradientWidth), 0.0f);
        this.disappearGradient.setLocalMatrix(this.disappearMatrix);
        int r13 = (int) this.strokePaint.getStrokeWidth();
        r30.drawRect(r7.left - r13, r7.top - r13, r7.right + r13, r7.bottom + r13, this.disappearPaint);
        r30.restore();
        r30.restore();
    L95:
        if (isDisappeared() == true) goto L98;
        invalidateSelf();
        return;
    L98:
        return;
    L68:
        if (this.appearGradientWidth == r46) goto L70;
        this.appearGradientWidth = r46;
        LinearGradient r16 = new LinearGradient(0.0f, 0.0f, r46, 0.0f, new int[]{16777215, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.appearGradient = r16;
        r16.setLocalMatrix(this.appearMatrix);
        this.appearPaint.setShader(this.appearGradient);
    L71:
        r142 = false;
        goto L72
    L52:
        if (this.disappearPaint != null) goto L55;
        this.disappearPaint = new Paint(1);
        this.disappearGradientWidth = r45;
        this.disappearGradient = new LinearGradient(0.0f, 0.0f, r45, 0.0f, new int[]{-1, 16777215}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        Matrix r47 = new Matrix();
        this.disappearMatrix = r47;
        this.disappearGradient.setLocalMatrix(r47);
        this.disappearPaint.setShader(this.disappearGradient);
        this.disappearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L57:
        this.rectF.set(r7);
        this.rectF.inset(-this.strokePaint.getStrokeWidth(), -this.strokePaint.getStrokeWidth());
        r30.saveLayerAlpha(this.rectF, 255, 31);
        r12 = true;
        goto L61
    L55:
        if (this.disappearGradientWidth == r45) goto L57;
        this.disappearGradientWidth = r45;
        LinearGradient r20 = new LinearGradient(0.0f, 0.0f, r45, 0.0f, new int[]{-1, 16777215}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.disappearGradient = r20;
        r20.setLocalMatrix(this.disappearMatrix);
        this.disappearPaint.setShader(this.disappearGradient);
        goto L57
    L58:
        r19 = 1.0f;
        goto L59
    L46:
        r83 = 0.0f;
        goto L48
    L30:
        if (r22 != this.gradientWidth) goto L39;
        if (r42 != this.gradientColor1) goto L39;
        if (r52 != this.gradientColor2) goto L39;
        if (r62 != this.gradientStrokeColor1) goto L39;
        if (r82 == this.gradientStrokeColor2) goto L40;
    L26:
        r82 = Theme.getColor(this.colorKey2, this.resourcesProvider);
        goto L28
    L22:
        r62 = Theme.getColor(this.colorKey1, this.resourcesProvider);
        goto L23
    L18:
        r52 = Theme.getColor(this.colorKey2, this.resourcesProvider);
        goto L19
    L14:
        r42 = Theme.getColor(this.colorKey1, this.resourcesProvider);
        goto L15
    L100:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int getPaintAlpha() {
        return this.paint.getAlpha();
    }

    public boolean isDisappeared() {
        if (this.disappearStart > 0) goto L5;
        return false;
    L5:
        if ((SystemClock.elapsedRealtime() - this.disappearStart) < 320.0f) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isDisappearing() {
        if (this.disappearStart > 0) goto L5;
        return false;
    L5:
        if ((SystemClock.elapsedRealtime() - this.disappearStart) >= 320.0f) goto L10;
        return true;
    L10:
        return false;
    }

    public void reset() {
        this.start = -1;
    }

    public void resetDisappear() {
        this.disappearStart = -1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.paint.setAlpha(r2);
        this.strokePaint.setAlpha(r2);
        if (r2 <= 0) goto L6;
        invalidateSelf();
        return;
    }

    public void setAppearByGradient(boolean r1) {
        this.appearByGradient = r1;
    }

    public void setBounds(RectF r4) {
        super.setBounds((int) r4.left, (int) r4.top, (int) r4.right, (int) r4.bottom);
        this.lastBounds = null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }

    public void setColors(int r1, int r2) {
        this.color1 = Integer.valueOf(r1);
        this.color2 = Integer.valueOf(r2);
        this.stroke = false;
    }

    public void setColors(int r1, int r2, int r3, int r4) {
        this.color1 = Integer.valueOf(r1);
        this.color2 = Integer.valueOf(r2);
        this.stroke = true;
        this.strokeColor1 = Integer.valueOf(r3);
        this.strokeColor2 = Integer.valueOf(r4);
    }

    public void setGradientScale(float r1) {
        this.gradientWidthScale = r1;
    }

    public void setRadii(float r3) {
        if (this.usePath == null) goto L6;
        this.paint.setPathEffect(new CornerPathEffect(r3));
        this.strokePaint.setPathEffect(new CornerPathEffect(r3));
        return;
    L6:
        setRadii(r3, r3, r3, r3);
    }

    public void setRadii(float r8, float r9, float r10, float r11) {
        float[] r0 = this.radii;
        if (r0[0] == r8) goto L5;
    L12:
        boolean r2 = true;
    L13:
        r0[1] = r8;
        r0[0] = r8;
        r0[3] = r9;
        r0[2] = r9;
        r0[5] = r10;
        r0[4] = r10;
        r0[7] = r11;
        r0[6] = r11;
        if (this.lastBounds == null) goto L18;
        if (r2 == false) goto L19;
        this.path.rewind();
        this.rectF.set(this.lastBounds);
        this.path.addRoundRect(this.rectF, this.radii, Path.Direction.CW);
        return;
    L19:
        return;
    L18:
        return;
    L5:
        if (r0[2] != r9) goto L12;
        if (r0[4] != r10) goto L12;
        if (r0[6] != r11) goto L12;
        r2 = false;
        goto L13
    }

    public void setRadii(float[] r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (r7.length != 8) goto L23;
        int r0 = 0;
        boolean r2 = false;
    L7:
        if (r0 >= 8) goto L13;
        float[] r3 = this.radii;
        float r4 = r3[r0];
        float r5 = r7[r0];
        if (r4 == r5) goto L11;
        r3[r0] = r5;
        r2 = true;
    L11:
        r0 = r0 + 1;
        goto L7
    L13:
        if (this.lastBounds == null) goto L21;
        if (r2 == false) goto L22;
        this.path.rewind();
        this.rectF.set(this.lastBounds);
        this.path.addRoundRect(this.rectF, r7, Path.Direction.CW);
        return;
    L22:
        return;
    L21:
        return;
    }

    public void setRadiiDp(float r1) {
        setRadii(AndroidUtilities.dp(r1));
    }

    public void setSpeed(float r1) {
        this.speed = r1;
    }

    public long timeToDisappear() {
        if (this.disappearStart > 0) goto L5;
        return 0;
    L5:
        return 320 - (SystemClock.elapsedRealtime() - this.disappearStart);
    }

    public void updateBounds() {
        Path r0 = this.usePath;
        if (r0 == null) goto L6;
        RectF r1 = AndroidUtilities.rectTmp;
        r0.computeBounds(r1, false);
        setBounds(r1);
        return;
    }

    public void usePath(Path r1) {
        this.usePath = r1;
    }
}
