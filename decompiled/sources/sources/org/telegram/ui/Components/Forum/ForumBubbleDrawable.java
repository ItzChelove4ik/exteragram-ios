package org.telegram.ui.Components.Forum;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ForumBubbleDrawable extends Drawable {
    static final SparseArray<int[]> colorsMap = null;
    private static SvgHelper.SvgDrawable mainDrawable;
    public static final int[] serverSupportedColor = null;
    int color;
    int colorIndex;
    private int[] currentColors;
    LinearGradient gradient;
    Matrix gradientMatrix;
    ArrayList<View> parents;
    private final Paint strokePaint;
    SvgHelper.SvgDrawable svgDrawable;
    private final Paint topPaint;

    public static /* synthetic */ void $r8$lambda$PEekEsFPUzCap4XQHvQHzaQCLw0(ForumBubbleDrawable r0, int[] r1, ValueAnimator r2) {
        r0.lambda$moveNexColor$0(r1, r2);
    }

    static {
        serverSupportedColor = new int[]{7322096, 16766590, 13338331, 9367192, 16749490, 16478047};
        SparseArray<int[]> r0 = new SparseArray();
        colorsMap = r0;
        r0.put(7322096, new int[]{-16687423, -11814913});
        r0.put(16766590, new int[]{-1419264, -9380});
        r0.put(13338331, new int[]{-6014789, -1737985});
        r0.put(9367192, new int[]{-15617007, -6823116});
        r0.put(16749490, new int[]{-1826470, -34407});
        r0.put(16478047, new int[]{-3795707, -36532});
    }

    public ForumBubbleDrawable(int r5) {
        this.gradientMatrix = new Matrix();
        this.parents = new ArrayList();
        this.color = -1;
        if (mainDrawable != null) goto L5;
        mainDrawable = SvgHelper.getDrawable(R.raw.topic_bubble, -1);
    L5:
        SvgHelper.SvgDrawable r0 = mainDrawable.m4993clone();
        this.svgDrawable = r0;
        r0.copyCommandFromPosition(0);
        Paint r02 = new Paint(1);
        this.topPaint = r02;
        Paint r2 = new Paint(1);
        this.strokePaint = r2;
        r2.setStrokeWidth(AndroidUtilities.dp(1.0f));
        r2.setStyle(Paint.Style.STROKE);
        this.svgDrawable.setPaint(r02, 1);
        this.svgDrawable.setPaint(r2, 2);
        setColor(r5);
    }

    private /* synthetic */ void lambda$moveNexColor$0(int[] r12, ValueAnimator r13) {
        float r132 = ((Float) r13.getAnimatedValue()).floatValue();
        Paint r0 = new Paint(1);
        LinearGradient r2 = new LinearGradient(0.0f, 100.0f, 0.0f, 0.0f, new int[]{ColorUtils.blendARGB(r12[0], this.currentColors[0], r132), ColorUtils.blendARGB(r12[1], this.currentColors[1], r132)}, null, Shader.TileMode.CLAMP);
        this.gradient = r2;
        r2.setLocalMatrix(this.gradientMatrix);
        r0.setShader(this.gradient);
        this.svgDrawable.setPaint(r0, 0);
        this.topPaint.setColor(ColorUtils.blendARGB(ColorUtils.blendARGB(r12[1], this.currentColors[1], r132), -1, 0.1f));
        this.strokePaint.setColor(ColorUtils.blendARGB(ColorUtils.blendARGB(r12[0], this.currentColors[0], r132), -16777216, 0.1f));
        invalidateSelf();
    }

    public void addParent(View r1) {
        this.parents.add(r1);
    }

    public int colorDistance(int r3, int r4) {
        return (Math.abs(Color.red(r3) - Color.red(r4)) + Math.abs(Color.green(r3) - Color.green(r4))) + Math.abs(Color.blue(r3) - Color.blue(r4));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r4) {
        this.gradientMatrix.reset();
        this.gradientMatrix.setScale(1.0f, getBounds().height() / 100.0f);
        this.gradient.setLocalMatrix(this.gradientMatrix);
        this.svgDrawable.setBounds(getBounds());
        this.svgDrawable.draw(r4);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        int r0 = 0;
    L4:
        if (r0 >= this.parents.size()) goto L6;
        this.parents.get(r0).invalidate();
        r0 = r0 + 1;
        goto L4
    }

    public int moveNexColor() {
        int r0 = this.colorIndex + 1;
        this.colorIndex = r0;
        int[] r2 = serverSupportedColor;
        if (r0 <= (r2.length - 1)) goto L5;
        this.colorIndex = 0;
    L5:
        int[] r02 = this.currentColors;
        int r3 = r2[this.colorIndex];
        this.color = r3;
        this.currentColors = colorsMap.get(r3);
        if (Theme.isCurrentThemeDark() == false) goto L8;
        this.currentColors = new int[]{ColorUtils.blendARGB(this.currentColors[0], -1, 0.2f), ColorUtils.blendARGB(this.currentColors[1], -1, 0.2f)};
    L8:
        invalidateSelf();
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r1.addUpdateListener(new ForumBubbleDrawable$$ExternalSyntheticLambda0(this, r02));
        r1.setDuration(200);
        r1.start();
        return r2[this.colorIndex];
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.svgDrawable.setAlpha(r1);
    }

    public void setColor(int r13) {
        int r0 = this.color;
        if (r0 != r13) goto L6;
        if (r0 != (-1)) goto L6;
        return;
    L6:
        this.color = r13;
        int r02 = colorDistance(serverSupportedColor[0], r13);
        this.colorIndex = 0;
        int r3 = 0;
    L7:
        int[] r4 = serverSupportedColor;
        if (r3 >= r4.length) goto L13;
        int r42 = colorDistance(r4[r3], r13);
        if (r42 >= r02) goto L12;
        this.colorIndex = r3;
        r02 = r42;
    L12:
        r3 = r3 + 1;
        goto L7
    L13:
        int[] r132 = colorsMap.get(r4[this.colorIndex]);
        if (Theme.isCurrentThemeDark() == false) goto L16;
        int[] r9 = {ColorUtils.blendARGB(r132[0], -1, 0.2f), ColorUtils.blendARGB(r132[1], -1, 0.2f)};
    L17:
        this.currentColors = r9;
        Paint r133 = new Paint(1);
        LinearGradient r43 = new LinearGradient(0.0f, 100.0f, 0.0f, 0.0f, r9, null, Shader.TileMode.CLAMP);
        this.gradient = r43;
        r43.setLocalMatrix(this.gradientMatrix);
        r133.setShader(this.gradient);
        this.svgDrawable.setPaint(r133, 0);
        this.topPaint.setColor(ColorUtils.blendARGB(r9[1], -1, 0.1f));
        this.strokePaint.setColor(ColorUtils.blendARGB(r9[0], -16777216, 0.1f));
        return;
    L16:
        r9 = r132;
        goto L17
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
