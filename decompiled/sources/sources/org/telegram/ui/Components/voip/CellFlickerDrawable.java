package org.telegram.ui.Components.voip;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.android.dx.io.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SvgHelper;

/* loaded from: classes3.dex */
public class CellFlickerDrawable {
    public float animationSpeedScale;
    public boolean drawFrame;
    public boolean frameInside;
    private Shader gradientShader;
    private Shader gradientShader2;
    long lastUpdateTime;
    Matrix matrix;
    Runnable onRestartCallback;
    private Paint paint;
    private Paint paintOutline;
    View parentView;
    int parentWidth;
    public float progress;
    public boolean repeatEnabled;
    public float repeatProgress;
    int size;

    /* loaded from: classes7.dex */
    public class DrawableInterface extends Drawable {
        public float radius;
        SvgHelper.SvgDrawable svgDrawable;
        final /* synthetic */ CellFlickerDrawable this$0;

        public DrawableInterface(CellFlickerDrawable r1, SvgHelper.SvgDrawable r2) {
            this.this$0 = r1;
            this.svgDrawable = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r8) {
            this.this$0.setParentWidth(getBounds().width());
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getBounds());
            this.this$0.draw(r8, r0, this.radius, null);
            SvgHelper.SvgDrawable r02 = this.svgDrawable;
            if (r02 == null) goto L5;
            r02.setPaint(CellFlickerDrawable.m13812$$Nest$fgetpaint(this.this$0));
            CellFlickerDrawable r03 = this.this$0;
            int r1 = r03.parentWidth;
            float r3 = (((r2 * 2) + r1) * r03.progress) - r03.size;
            int r04 = (int) (r1 * 0.5f);
            float r12 = this.svgDrawable.getScale(getBounds().width(), getBounds().height());
            this.this$0.matrix.reset();
            this.this$0.matrix.setScale(1.0f / r12, 0.0f, r2.size / 2.0f, 0.0f);
            this.this$0.matrix.setTranslate((r3 - this.svgDrawable.getBounds().left) - (this.this$0.size / r12), 0.0f);
            CellFlickerDrawable.m13811$$Nest$fgetgradientShader(this.this$0).setLocalMatrix(this.this$0.matrix);
            int r05 = r04 / 2;
            this.svgDrawable.setBounds(getBounds().centerX() - r05, getBounds().centerY() - r05, getBounds().centerX() + r05, getBounds().centerY() + r05);
            this.svgDrawable.draw(r8);
        L5:
            this.this$0.parentView.invalidate();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            CellFlickerDrawable.m13812$$Nest$fgetpaint(this.this$0).setAlpha(r2);
            CellFlickerDrawable.m13813$$Nest$fgetpaintOutline(this.this$0).setAlpha(r2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: -$$Nest$fgetgradientShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Shader m13811$$Nest$fgetgradientShader(CellFlickerDrawable r0) {
        return r0.gradientShader;
    }

    /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13812$$Nest$fgetpaint(CellFlickerDrawable r0) {
        return r0.paint;
    }

    /* renamed from: -$$Nest$fgetpaintOutline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13813$$Nest$fgetpaintOutline(CellFlickerDrawable r0) {
        return r0.paintOutline;
    }

    public CellFlickerDrawable() {
        this(64, Opcodes.SUB_DOUBLE_2ADDR, 160);
    }

    public CellFlickerDrawable(int r2, int r3) {
        this(r2, r3, 160);
    }

    public CellFlickerDrawable(int r18, int r19, int r20) {
        this.paint = new Paint(1);
        this.paintOutline = new Paint(1);
        this.matrix = new Matrix();
        this.repeatEnabled = true;
        this.drawFrame = true;
        this.frameInside = false;
        this.repeatProgress = 1.2f;
        this.animationSpeedScale = 1.0f;
        this.size = AndroidUtilities.dp(r20);
        float r6 = this.size;
        int[] r8 = {0, ColorUtils.setAlphaComponent(-1, r18), 0};
        Shader.TileMode r16 = Shader.TileMode.CLAMP;
        this.gradientShader = new LinearGradient(0.0f, 0.0f, r6, 0.0f, r8, null, r16);
        this.gradientShader2 = new LinearGradient(0.0f, 0.0f, this.size, 0.0f, new int[]{0, ColorUtils.setAlphaComponent(-1, r19), 0}, null, r16);
        this.paint.setShader(this.gradientShader);
        this.paintOutline.setShader(this.gradientShader2);
        this.paintOutline.setStyle(Paint.Style.STROKE);
        this.paintOutline.setStrokeWidth(AndroidUtilities.dp(2.0f));
    }

    private void update(View r9) {
        if (this.repeatEnabled == false) goto L5;
    L6:
        if (r9 == null) goto L8;
        r9.invalidate();
    L8:
        long r2 = System.currentTimeMillis();
        long r4 = this.lastUpdateTime;
        if (r4 == 0) goto L18;
        long r42 = r2 - r4;
        if (r42 <= 10) goto L19;
        float r92 = this.progress + ((r42 / 1200.0f) * this.animationSpeedScale);
        this.progress = r92;
        if (r92 <= this.repeatProgress) goto L17;
        this.progress = 0.0f;
        Runnable r93 = this.onRestartCallback;
        if (r93 == null) goto L17;
        r93.run();
    L17:
        this.lastUpdateTime = r2;
    L19:
        int r94 = this.parentWidth;
        float r95 = ((r94 + (r0 * 2)) * this.progress) - this.size;
        this.matrix.reset();
        this.matrix.setTranslate(r95, 0.0f);
        this.gradientShader.setLocalMatrix(this.matrix);
        this.gradientShader2.setLocalMatrix(this.matrix);
        return;
    L18:
        this.lastUpdateTime = r2;
        goto L19
    L5:
        if (this.progress >= 1.0f) goto L19;
        goto L6
    }

    public void draw(Canvas r1, Path r2, View r3) {
        update(r3);
        r1.drawPath(r2, this.paint);
        if (this.drawFrame == false) goto L6;
        r1.drawPath(r2, this.paintOutline);
        return;
    }

    public void draw(Canvas r3, RectF r4, float r5, View r6) {
        update(r6);
        r3.drawRoundRect(r4, r5, r5, this.paint);
        if (this.drawFrame == true) goto L5;
        return;
    L5:
        if (this.frameInside == false) goto L7;
        r4.inset(this.paintOutline.getStrokeWidth() / 2.0f, this.paintOutline.getStrokeWidth() / 2.0f);
    L7:
        r3.drawRoundRect(r4, r5, r5, this.paintOutline);
    }

    public void draw(Canvas r9, GroupCallMiniTextureView r10) {
        long r0 = System.currentTimeMillis();
        long r2 = this.lastUpdateTime;
        if (r2 == 0) goto L12;
        long r22 = r0 - r2;
        if (r22 <= 10) goto L13;
        float r4 = this.progress + (r22 / 500.0f);
        this.progress = r4;
        if (r4 <= 4.0f) goto L11;
        this.progress = 0.0f;
        Runnable r23 = this.onRestartCallback;
        if (r23 == null) goto L11;
        r23.run();
    L11:
        this.lastUpdateTime = r0;
    L13:
        float r02 = this.progress;
        if (r02 > 1.0f) goto L24;
        int r1 = this.parentWidth;
        this.matrix.setTranslate((((r1 + (r2 * 2)) * r02) - this.size) - r10.getX(), 0.0f);
        this.gradientShader.setLocalMatrix(this.matrix);
        this.gradientShader2.setLocalMatrix(this.matrix);
        RectF r03 = AndroidUtilities.rectTmp;
        VoIPTextureView r12 = r10.textureView;
        r03.set(r12.currentClipHorizontal, r12.currentClipVertical, r12.getMeasuredWidth() - r10.textureView.currentClipHorizontal, r4.getMeasuredHeight() - r10.textureView.currentClipVertical);
        r9.drawRect(r03, this.paint);
        if (this.drawFrame == true) goto L19;
        return;
    L19:
        if (this.frameInside == false) goto L21;
        r03.inset(this.paintOutline.getStrokeWidth() / 2.0f, this.paintOutline.getStrokeWidth() / 2.0f);
    L21:
        float r102 = r10.textureView.roundRadius;
        r9.drawRoundRect(r03, r102, r102, this.paintOutline);
        return;
    L24:
        return;
    L12:
        this.lastUpdateTime = r0;
        goto L13
    }

    public DrawableInterface getDrawableInterface(View r1, SvgHelper.SvgDrawable r2) {
        this.parentView = r1;
        return new DrawableInterface(this, r2);
    }

    public float getProgress() {
        return this.progress;
    }

    public void setAlpha(int r2) {
        this.paint.setAlpha(r2);
        this.paintOutline.setAlpha(r2);
    }

    public void setColors(int r3) {
        setColors(r3, 64, Opcodes.SUB_DOUBLE_2ADDR);
    }

    public void setColors(int r19, int r20, int r21) {
        float r4 = this.size;
        int[] r6 = {0, ColorUtils.setAlphaComponent(r19, r20), 0};
        Shader.TileMode r17 = Shader.TileMode.CLAMP;
        this.gradientShader = new LinearGradient(0.0f, 0.0f, r4, 0.0f, r6, null, r17);
        this.gradientShader2 = new LinearGradient(0.0f, 0.0f, this.size, 0.0f, new int[]{0, ColorUtils.setAlphaComponent(r19, r21), 0}, null, r17);
        this.paint.setShader(this.gradientShader);
        this.paintOutline.setShader(this.gradientShader2);
    }

    public void setOnRestartCallback(Runnable r1) {
        this.onRestartCallback = r1;
    }

    public void setParentWidth(int r1) {
        this.parentWidth = r1;
    }

    public void setProgress(float r1) {
        this.progress = r1;
    }

    public void setStrokeWidth(float r1) {
        this.paintOutline.setStrokeWidth(r1);
    }
}
