package org.telegram.ui.Components.Paint.Views;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Keep;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.Paint.Swatch;

/* loaded from: classes7.dex */
public class ColorPicker extends FrameLayout {
    private static final int[] COLORS = null;
    private static final float[] LOCATIONS = null;
    private Paint backgroundPaint;
    private boolean changingWeight;
    private boolean dragging;
    private float draggingFactor;
    private Paint gradientPaint;
    private boolean interacting;
    private OvershootInterpolator interpolator;
    private float location;
    private RectF rectF;
    public ImageView settingsButton;
    private Paint swatchPaint;
    private Paint swatchStrokePaint;
    private ImageView undoButton;
    private boolean wasChangingWeight;
    private float weight;

    public interface ColorPickerDelegate {
    }

    static {
        COLORS = new int[]{-1431751, -2409774, -13610525, -11942419, -8337308, -205211, -223667, -16777216, -1};
        LOCATIONS = new float[]{0.0f, 0.14f, 0.24f, 0.39f, 0.49f, 0.62f, 0.73f, 0.85f, 1.0f};
    }

    private int interpolateColors(int r5, int r6, float r7) {
        float r4 = Math.min(Math.max(r7, 0.0f), 1.0f);
        int r72 = Color.red(r5);
        int r0 = Color.red(r6);
        int r1 = Color.green(r5);
        int r2 = Color.green(r6);
        int r52 = Color.blue(r5);
        int r62 = Color.blue(r6);
        return Color.argb(255, Math.min(255, (int) (r72 + ((r0 - r72) * r4))), Math.min(255, (int) (r1 + ((r2 - r1) * r4))), Math.min(255, (int) (r52 + ((r62 - r52) * r4))));
    }

    private void setDragging(boolean r3, boolean r4) {
        if (this.dragging != r3) goto L5;
        return;
    L5:
        this.dragging = r3;
        if (r3 == false) goto L8;
        float r32 = 1.0f;
    L9:
        if (r4 == false) goto L16;
        ObjectAnimator r33 = ObjectAnimator.ofFloat(this, "draggingFactor", new float[]{this.draggingFactor, r32});
        r33.setInterpolator(this.interpolator);
        if (this.wasChangingWeight == false) goto L13;
        int r2 = (int) (300.0f + (this.weight * 75.0f));
    L14:
        r33.setDuration(r2);
        r33.start();
        return;
    L13:
        r2 = 300;
        goto L14
    L16:
        setDraggingFactor(r32);
        return;
    L8:
        r32 = 0.0f;
        goto L9
    }

    @Keep
    private void setDraggingFactor(float r1) {
        this.draggingFactor = r1;
        invalidate();
    }

    public int colorForLocation(float r6) {
        if (r6 <= 0.0f) goto L5;
        int r1 = 1;
        if (r6 < 1.0f) goto L10;
        int[] r5 = COLORS;
        return r5[r5.length - 1];
    L10:
        float[] r0 = LOCATIONS;
        if (r1 >= r0.length) goto L16;
        if (r0[r1] >= r6) goto L14;
        r1 = r1 + 1;
        goto L10
    L14:
        int r2 = r1 - 1;
    L17:
        float r3 = r0[r2];
        int[] r4 = COLORS;
        return interpolateColors(r4[r2], r4[r1], (r6 - r3) / (r0[r1] - r3));
    L16:
        r1 = -1;
        r2 = -1;
        goto L17
    L5:
        return COLORS[0];
    }

    @Keep
    public float getDraggingFactor() {
        return this.draggingFactor;
    }

    public View getSettingsButton() {
        return this.settingsButton;
    }

    public Swatch getSwatch() {
        return new Swatch(colorForLocation(this.location), this.location, this.weight);
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        r9.drawRoundRect(this.rectF, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.gradientPaint);
        RectF r0 = this.rectF;
        int r02 = (int) (r0.left + (r0.width() * this.location));
        float r1 = this.rectF.centerY() + (this.draggingFactor * (-AndroidUtilities.dp(70.0f)));
        if (this.changingWeight == false) goto L5;
        float r2 = this.weight * AndroidUtilities.dp(190.0f);
    L6:
        int r12 = (int) (r1 - r2);
        AndroidUtilities.dp(24.0f);
        float r22 = (((int) Math.floor(AndroidUtilities.dp(4.0f) + ((AndroidUtilities.dp(19.0f) - AndroidUtilities.dp(4.0f)) * this.weight))) * (this.draggingFactor + 1.0f)) / 2.0f;
        float r03 = r02;
        float r13 = r12;
        r9.drawCircle(r03, r13, ((AndroidUtilities.dp(22.0f) / 2) * (this.draggingFactor + 1.0f)) + 0.5f, this.swatchStrokePaint);
        r9.drawCircle(r03, r13, (AndroidUtilities.dp(22.0f) / 2) * (this.draggingFactor + 1.0f), this.backgroundPaint);
        r9.drawCircle(r03, r13, r22, this.swatchPaint);
        r9.drawCircle(r03, r13, r22 - AndroidUtilities.dp(0.5f), this.swatchStrokePaint);
        return;
    L5:
        r2 = 0.0f;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
        int r122 = r12 - r10;
        int r132 = r13 - r11;
        this.gradientPaint.setShader(new LinearGradient(AndroidUtilities.dp(56.0f), 0.0f, r122 - AndroidUtilities.dp(56.0f), 0.0f, COLORS, LOCATIONS, Shader.TileMode.REPEAT));
        this.rectF.set(AndroidUtilities.dp(56.0f), r132 - AndroidUtilities.dp(32.0f), r122 - AndroidUtilities.dp(56.0f), r9 + AndroidUtilities.dp(12.0f));
        ImageView r92 = this.settingsButton;
        r92.layout(r122 - r92.getMeasuredWidth(), r132 - AndroidUtilities.dp(52.0f), r122, r132);
        this.undoButton.layout(0, r132 - AndroidUtilities.dp(52.0f), this.settingsButton.getMeasuredWidth(), r132);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        if (r7.getPointerCount() <= 1) goto L5;
        return false;
    L5:
        float r0 = r7.getX() - this.rectF.left;
        float r3 = r7.getY() - this.rectF.top;
        if (this.interacting == false) goto L8;
    L10:
        int r72 = r7.getActionMasked();
        if (r72 == 3) goto L26;
        if (r72 == 1) goto L26;
        if (r72 == 6) goto L26;
        if (r72 == 0) goto L20;
        if (r72 == 2) goto L20;
    L27:
        return false;
    L20:
        if (this.interacting == true) goto L22;
        this.interacting = true;
    L22:
        setLocation(Math.max(0.0f, Math.min(1.0f, r0 / this.rectF.width())));
        setDragging(true, true);
        if (r3 >= (-AndroidUtilities.dp(10.0f))) goto L25;
        this.changingWeight = true;
        setWeight(Math.max(0.0f, Math.min(1.0f, ((-r3) - AndroidUtilities.dp(10.0f)) / AndroidUtilities.dp(190.0f))));
    L25:
        return true;
    L26:
        this.interacting = false;
        this.wasChangingWeight = this.changingWeight;
        this.changingWeight = false;
        setDragging(false, true);
        goto L27
    L8:
        if (r3 >= (-AndroidUtilities.dp(10.0f))) goto L10;
        return false;
    }

    public void setDelegate(ColorPickerDelegate r1) {
    }

    public void setLocation(float r6) {
        this.location = r6;
        int r62 = colorForLocation(r6);
        this.swatchPaint.setColor(r62);
        float[] r0 = new float[3];
        Color.colorToHSV(r62, r0);
        if (r0[0] < 0.001d) goto L5;
    L9:
        this.swatchStrokePaint.setColor(r62);
    L10:
        invalidate();
        return;
    L5:
        if (r0[1] >= 0.001d) goto L9;
        float r02 = r0[2];
        if (r02 <= 0.92f) goto L9;
        int r63 = (int) ((1.0f - (((r02 - 0.92f) / 0.08f) * 0.22f)) * 255.0f);
        this.swatchStrokePaint.setColor(Color.rgb(r63, r63, r63));
        goto L10
    }

    public void setSettingsButtonImage(int r1) {
        this.settingsButton.setImageResource(r1);
    }

    public void setSwatch(Swatch r2) {
        setLocation(r2.colorLocation);
        setWeight(r2.brushWeight);
    }

    public void setUndoEnabled(boolean r3) {
        ImageView r0 = this.undoButton;
        if (r3 == false) goto L5;
        float r1 = 1.0f;
    L6:
        r0.setAlpha(r1);
        this.undoButton.setEnabled(r3);
        return;
    L5:
        r1 = 0.3f;
        goto L6
    }

    public void setWeight(float r1) {
        this.weight = r1;
        invalidate();
    }
}
