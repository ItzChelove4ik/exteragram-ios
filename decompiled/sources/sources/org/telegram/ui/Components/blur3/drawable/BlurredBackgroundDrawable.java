package org.telegram.ui.Components.blur3.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.GlassOutlineStyle;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.utils.RadiiUtils;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.Blur3HashImpl;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceWrapped;
import org.telegram.ui.Components.blur3.utils.NinePatchBuilder;

/* loaded from: classes3.dex */
public abstract class BlurredBackgroundDrawable extends Drawable {
    private static Path tmpPath;
    private static final float[] tmpRadii = null;
    protected int alpha;
    private final Paint backgroundBitmapFill;
    private final Paint backgroundBitmapPaint;
    protected int backgroundColor;
    private final Paint backgroundColorPaint;
    private final WeakReference<Bitmap> bitmapInShader;
    private BitmapShader bitmapShader;
    private final Matrix bitmapShaderMatrix;
    protected final Props boundProps;
    private final RectF cmpRectF1;
    private final RectF cmpRectF2;
    protected BlurredBackgroundColorProvider colorProvider;
    protected boolean inAppKeyboardOptimization;
    private NinePatchDrawable ninePatchDrawable;
    private long ninePatchDrawableHash;
    private final Rect ninePatchDrawablePadding;
    private final Blur3HashImpl ninePatchHashBuilder;
    private Bitmap[] ninePatchRef;
    private int outsetX;
    private int outsetY;
    private final Paint paintStrokeFill;
    protected float shadowAlpha;
    protected int shadowColor;
    protected float shadowLayerDx;
    protected float shadowLayerDy;
    protected float shadowLayerRadius;
    private final Paint shadowPaint;
    protected float sourceOffsetX;
    protected float sourceOffsetY;
    protected int strokeColorBottom;
    protected int strokeColorFull;
    protected int strokeColorTop;
    private ViewOutlineProvider viewOutlineProvider;

    /* renamed from: org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ViewOutlineProvider {
        final /* synthetic */ BlurredBackgroundDrawable this$0;

        public AnonymousClass1(BlurredBackgroundDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r1, Outline r2) {
            Props r0 = this.this$0.boundProps;
            BlurredBackgroundDrawable.getOutline(r2, r0.boundsWithPadding, r0.radii);
        }
    }

    public static class Props {
        public final Rect bounds;
        public final Rect boundsWithPadding;
        public boolean hasPadding;
        public float liquidIndex;
        public float liquidIntensity;
        public int liquidThickness;
        public int padding;
        public final Path path;
        public final float[] radii;
        public boolean radiiAreSame;
        public final float[] shaderRadii;
        public final Path strokePathBottom;
        public final Path strokePathTop;
        public float strokeWidthBottom;
        public float strokeWidthFull;
        public float strokeWidthTop;
        public boolean useFullStroke;

        public Props() {
            this.bounds = new Rect();
            this.radii = new float[8];
            this.shaderRadii = new float[8];
            this.liquidIntensity = 0.75f;
            this.liquidIndex = 1.5f;
            this.path = new Path();
            this.radiiAreSame = true;
            this.boundsWithPadding = new Rect();
            this.strokePathTop = new Path();
            this.strokePathBottom = new Path();
            this.useFullStroke = false;
        }

        public void build() {
            this.radiiAreSame = RadiiUtils.radiiAreSame(this.radii);
            this.boundsWithPadding.set(this.bounds);
            Rect r1 = this.boundsWithPadding;
            int r2 = this.padding;
            r1.inset(r2, r2);
            this.path.rewind();
            Path r22 = this.path;
            Rect r12 = this.boundsWithPadding;
            float r3 = r12.left;
            float r4 = r12.top;
            float r5 = r12.right;
            float r6 = r12.bottom;
            float[] r7 = this.radii;
            Path.Direction r14 = Path.Direction.CW;
            r22.addRoundRect(r3, r4, r5, r6, r7, r14);
            this.path.close();
            float r13 = Math.min(this.boundsWithPadding.width(), this.boundsWithPadding.height()) / 2.0f;
            if (this.useFullStroke == false) goto L11;
            System.arraycopy(this.radii, 0, BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), 0, 8);
            if (this.radiiAreSame == true) goto L7;
        L9:
            this.strokePathTop.rewind();
            Path r8 = this.strokePathTop;
            Rect r15 = this.boundsWithPadding;
            r8.addRoundRect(r15.left, r15.top, r15.right, r15.bottom, BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), r14);
            Path r16 = this.strokePathTop;
            float r32 = this.boundsWithPadding.left;
            float r42 = this.strokeWidthFull;
            r16.addRoundRect(r32 + r42, r2.top + r42, r2.right - r42, r2.bottom - r42, BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), Path.Direction.CCW);
            this.strokePathTop.close();
            this.strokePathBottom.rewind();
            this.strokePathBottom.close();
            return;
        L7:
            if (this.radii[0] <= r13) goto L9;
            Arrays.fill(BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), r13);
            goto L9
        L11:
            Arrays.fill(BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), 0.0f);
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[0] = this.radii[0];
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[1] = this.radii[1];
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[2] = this.radii[2];
            float[] r23 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            float[] r52 = this.radii;
            r23[3] = r52[3];
            if (this.radiiAreSame == true) goto L14;
        L16:
            this.strokePathTop.rewind();
            Path r82 = this.strokePathTop;
            Rect r24 = this.boundsWithPadding;
            float r9 = r24.left;
            int r53 = r24.top;
            r82.addRoundRect(r9, r53, r24.right, Math.min(r53 + this.radii[0], r24.bottom), BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), r14);
            Path r54 = this.strokePathTop;
            Rect r25 = this.boundsWithPadding;
            float r62 = r25.left;
            int r72 = r25.top;
            float r83 = r72 + this.strokeWidthTop;
            float r92 = r25.right;
            float r26 = Math.min(r72 + this.radii[0], r25.bottom);
            float[] r10 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            Path.Direction r21 = Path.Direction.CCW;
            r54.addRoundRect(r62, r83, r92, r26, r10, r21);
            this.strokePathTop.close();
            Arrays.fill(BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), 0.0f);
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[4] = this.radii[4];
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[5] = this.radii[5];
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[6] = this.radii[6];
            float[] r27 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            float[] r43 = this.radii;
            r27[7] = r43[7];
            if (this.radiiAreSame == true) goto L19;
        L21:
            this.strokePathBottom.rewind();
            Path r84 = this.strokePathBottom;
            float r93 = this.boundsWithPadding.left;
            float r102 = Math.max(r1.bottom - this.radii[4], r1.top);
            Rect r17 = this.boundsWithPadding;
            r84.addRoundRect(r93, r102, r17.right, r17.bottom, BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), r14);
            Path r152 = this.strokePathBottom;
            float r28 = this.boundsWithPadding.left;
            float r172 = Math.max(r1.bottom - this.radii[4], r1.top);
            Rect r18 = this.boundsWithPadding;
            r152.addRoundRect(r28, r172, r18.right, r18.bottom - this.strokeWidthBottom, BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii(), r21);
            this.strokePathBottom.close();
            return;
        L19:
            if (r43[0] <= r13) goto L21;
            float[] r29 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            float[] r33 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            float[] r44 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[7] = r13;
            r44[6] = r13;
            r33[5] = r13;
            r29[4] = r13;
            goto L21
        L14:
            if (r52[0] <= r13) goto L16;
            float[] r210 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            float[] r55 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            float[] r94 = BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii();
            BlurredBackgroundDrawable.m13660$$Nest$sfgettmpRadii()[3] = r13;
            r94[2] = r13;
            r55[1] = r13;
            r210[0] = r13;
            goto L16
        }

        public void draw(Canvas r11, Paint r12) {
            if (this.radiiAreSame == false) goto L6;
            Rect r0 = this.boundsWithPadding;
            float r3 = r0.left;
            float r4 = r0.top;
            float r5 = r0.right;
            float r6 = r0.bottom;
            float r7 = this.radii[0];
            r11.drawRoundRect(r3, r4, r5, r6, r7, r7, r12);
            return;
        L6:
            r11.drawPath(this.path, r12);
        }

        public void drawShadows(Canvas r13, Paint r14, boolean r15) {
            if (r15 == false) goto L5;
            int r0 = this.boundsWithPadding.top;
            float r8 = MathUtils.clamp(r0 + (this.radii[0] * 2.0f), r0, r15.bottom);
            r13.save();
            Rect r152 = this.bounds;
            r13.clipRect(r152.left, r152.top, r152.right, r8);
            Rect r153 = this.boundsWithPadding;
            float r5 = r153.left;
            float r6 = r153.top;
            float r7 = r153.right;
            float r9 = this.radii[0];
            r13.drawRoundRect(r5, r6, r7, r8, r9, r9, r14);
            r13.restore();
            return;
        L5:
            draw(r13, r14);
        }
    }

    public static /* synthetic */ void $r8$lambda$OMxc8cd5reC5PQ5QLW0SW0wdMOs(BlurredBackgroundDrawable r0, int r1, boolean r2, Canvas r3, RectF r4, float[] r5) {
        r0.lambda$checkNinePatchDrawable$0(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$sfgettmpRadii, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m13660$$Nest$sfgettmpRadii() {
        return tmpRadii;
    }

    static {
        tmpRadii = new float[8];
        tmpPath = new Path();
    }

    public BlurredBackgroundDrawable() {
        Props r0 = new Props();
        this.boundProps = r0;
        this.alpha = 255;
        this.shadowAlpha = 1.0f;
        this.backgroundColorPaint = new Paint(1);
        this.paintStrokeFill = new Paint(1);
        Paint r2 = new Paint(1);
        this.backgroundBitmapPaint = r2;
        this.backgroundBitmapFill = new Paint(1);
        Paint r4 = new Paint(1);
        this.shadowPaint = r4;
        this.bitmapShaderMatrix = new Matrix();
        this.bitmapInShader = new WeakReference(null);
        r4.setColor(0);
        r2.setFilterBitmap(true);
        this.cmpRectF1 = new RectF();
        this.cmpRectF2 = new RectF();
        this.ninePatchHashBuilder = new Blur3HashImpl();
        this.ninePatchDrawablePadding = new Rect();
        r0.strokeWidthTop = AndroidUtilities.dpf2(1.0f);
        r0.strokeWidthBottom = AndroidUtilities.dpf2(0.6666667f);
        r0.strokeWidthFull = 1.0f;
        this.shadowLayerRadius = AndroidUtilities.dpf2(1.0f);
        this.shadowLayerDx = 0.0f;
        this.shadowLayerDy = AndroidUtilities.dpf2(0.33333334f);
    }

    private NinePatchDrawable checkNinePatchDrawable(int r9, boolean r10) {
        this.ninePatchHashBuilder.start();
        this.ninePatchHashBuilder.add(r9);
        this.ninePatchHashBuilder.add(this.shadowColor);
        this.ninePatchHashBuilder.add(this.boundProps.radii);
        this.ninePatchHashBuilder.addF(this.shadowAlpha);
        this.ninePatchHashBuilder.addF(this.shadowLayerRadius);
        this.ninePatchHashBuilder.addF(this.shadowLayerDx);
        this.ninePatchHashBuilder.addF(this.shadowLayerDy);
        this.ninePatchHashBuilder.add(r10);
        if (r10 == false) goto L8;
        this.ninePatchHashBuilder.add(this.boundProps.useFullStroke);
        boolean r0 = this.boundProps.useFullStroke;
        Blur3HashImpl r1 = this.ninePatchHashBuilder;
        if (r0 == false) goto L7;
        r1.add(this.strokeColorFull);
        this.ninePatchHashBuilder.addF(this.boundProps.strokeWidthFull);
        goto L8
    L7:
        r1.add(this.strokeColorTop);
        this.ninePatchHashBuilder.add(this.strokeColorBottom);
        this.ninePatchHashBuilder.addF(this.boundProps.strokeWidthTop);
        this.ninePatchHashBuilder.addF(this.boundProps.strokeWidthBottom);
    L8:
        long r02 = this.ninePatchHashBuilder.get();
        if (this.ninePatchDrawable != null) goto L11;
    L12:
        this.ninePatchDrawableHash = r02;
        if (Color.alpha(r9) != 255) goto L15;
        int r6 = r9;
    L16:
        NinePatchDrawable r92 = NinePatchBuilder.createNinePatch(this.ninePatchRef, this.boundProps.radii, this.shadowLayerRadius, this.shadowLayerDx, this.shadowLayerDy, r6, new BlurredBackgroundDrawable$$ExternalSyntheticLambda0(this, r9, r10));
        this.ninePatchDrawable = r92;
        r92.getPadding(this.ninePatchDrawablePadding);
    L18:
        return this.ninePatchDrawable;
    L15:
        r6 = 1;
        goto L16
    L11:
        if (this.ninePatchDrawableHash == r02) goto L18;
        goto L12
    }

    private void dispatchSourceRelativePositionChange() {
        getPositionRelativeSource(this.cmpRectF1);
        if (this.cmpRectF1.equals(this.cmpRectF2) == true) goto L6;
        this.cmpRectF2.set(this.cmpRectF1);
        onSourceRelativePositionChanged(this.cmpRectF1);
        return;
    }

    private void drawSourceAny(Canvas r17, BlurredBackgroundSource r18) {
        int r2 = this.alpha;
        if (r2 == 0) goto L27;
        int r7 = Theme.multAlpha(this.backgroundColor, r2 / 255.0f);
        if (Color.alpha(this.shadowColor) > 0) goto L8;
    L12:
        float r22 = this.sourceOffsetX;
        float r4 = this.sourceOffsetY;
        Rect r5 = this.boundProps.boundsWithPadding;
        int r6 = r5.left;
        float r8 = r6 + r22;
        int r9 = r5.top;
        float r10 = r9 + r4;
        int r11 = r5.right;
        float r12 = r11 + r22;
        int r23 = r5.bottom;
        float r13 = r23 + r4;
        int r42 = this.alpha;
        if (r42 == 255) goto L16;
        boolean r3 = true;
    L15:
        boolean r14 = r3;
        if (r14 == false) goto L19;
        r17.saveLayerAlpha(r6, r9, r11, r23, r42);
    L19:
        r17.save();
        r17.clipPath(this.boundProps.path);
        Rect r24 = this.boundProps.boundsWithPadding;
        r17.translate(r24.left, r24.top);
        r17.translate(-r8, -r10);
        r18.draw(r17, r8, r10, r12, r13);
        r17.restore();
        if (Color.alpha(r7) <= 0) goto L22;
        this.backgroundColorPaint.setColor(r7);
        this.boundProps.draw(r17, this.backgroundColorPaint);
    L22:
        drawStrokeInternalIfNeeded(r17);
        if (r14 == false) goto L26;
        r17.restore();
        return;
    L26:
        return;
    L16:
        r3 = false;
        goto L15
    L8:
        if (this.alpha != 255) goto L12;
        float r25 = this.shadowAlpha;
        if (r25 <= 0.0f) goto L12;
        this.shadowPaint.setShadowLayer(this.shadowLayerRadius, this.shadowLayerDx, this.shadowLayerDy, Theme.multAlpha(this.shadowColor, r25));
        this.boundProps.drawShadows(r17, this.shadowPaint, this.inAppKeyboardOptimization);
        goto L12
    }

    private void drawSourceBitmap(Canvas r9, BlurredBackgroundSourceBitmap r10) {
        Bitmap r0 = r10.getBitmap();
        if (r0 == this.bitmapInShader.get()) goto L10;
        if (r0 != null) goto L6;
    L8:
        this.bitmapShader = null;
        this.backgroundBitmapPaint.setShader(null);
        goto L10
    L6:
        if (r0.isRecycled() == true) goto L8;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        BitmapShader r1 = new BitmapShader(r0, r2, r2);
        this.bitmapShader = r1;
        this.backgroundBitmapPaint.setShader(r1);
    L10:
        if (this.shadowLayerRadius <= 0.0f) goto L17;
        if (Color.alpha(this.shadowColor) <= 0) goto L17;
        if (this.shadowAlpha <= 0.0f) goto L17;
        NinePatchDrawable r12 = checkNinePatchDrawable(0, false);
        Rect r22 = this.boundProps.boundsWithPadding;
        int r3 = r22.left;
        Rect r4 = this.ninePatchDrawablePadding;
        r12.setBounds(r3 - r4.left, r22.top - r4.top, r22.right + r4.right, r22.bottom + r4.bottom);
        r12.setAlpha(this.alpha);
        r12.draw(r9);
    L17:
        if (this.bitmapShader == null) goto L24;
        if (r0 == null) goto L24;
        if (r0.isRecycled() == true) goto L24;
        if (this.alpha <= 0) goto L24;
        this.bitmapShaderMatrix.set(r10.getMatrix());
        this.bitmapShaderMatrix.postTranslate(-this.sourceOffsetX, -this.sourceOffsetY);
        this.bitmapShader.setLocalMatrix(this.bitmapShaderMatrix);
        this.backgroundBitmapPaint.setAlpha(this.alpha);
        this.boundProps.draw(r9, this.backgroundBitmapPaint);
    L24:
        int r102 = Theme.multAlpha(this.backgroundColor, this.alpha / 255.0f);
        if (Color.alpha(r102) <= 0) goto L27;
        this.backgroundBitmapFill.setColor(r102);
        this.boundProps.draw(r9, this.backgroundBitmapFill);
    L27:
        drawStrokeInternalIfNeeded(r9);
    }

    private void drawSourceColor(Canvas r1, BlurredBackgroundSourceColor r2) {
        drawSourceColorImpl(r1, r2.getColor());
    }

    private void drawSourceColorImpl(Canvas r7, int r8) {
        int r82 = ColorUtils.compositeColors(this.backgroundColor, r8);
        if (Color.alpha(r82) == 0) goto L5;
    L7:
        NinePatchDrawable r83 = checkNinePatchDrawable(r82, true);
        Rect r0 = this.boundProps.boundsWithPadding;
        int r1 = r0.left;
        Rect r2 = this.ninePatchDrawablePadding;
        r83.setBounds(r1 - r2.left, r0.top - r2.top, r0.right + r2.right, r0.bottom + r2.bottom);
        r83.setAlpha(this.alpha);
        r83.draw(r7);
        return;
    L5:
        if (Color.alpha(this.shadowColor) != 0) goto L7;
    }

    private void drawSourceRenderNode(Canvas r2, BlurredBackgroundSourceRenderNode r3) {
        if (r2.isHardwareAccelerated() == true) goto L6;
        drawSource(r2, r3.getFallbackSource());
        return;
    }

    public static void drawStroke(Canvas r8, float r9, float r10, float r11, float r12, float r13, float r14, boolean r15, Paint r16) {
        float r4 = r14 / 2.0f;
        r8.save();
        if (r15 == false) goto L7;
        float r5 = r9 - r4;
        float r3 = r11 + r4;
        if (r8.clipRect(r5, r10, r3, MathUtils.clamp((2.0f * r13) + r10, r10, r12)) == false) goto L10;
        r8.drawRoundRect(r5, r10 + r4, r3, r4 + r12, r13, r13, r16);
    L10:
        r8.restore();
        return;
    L7:
        float r2 = r9 - r4;
        float r52 = r11 + r4;
        if (r8.clipRect(r2, MathUtils.clamp(r12 - (r13 * 2.0f), r10, r12), r52, r12) == false) goto L10;
        r8.drawRoundRect(r2, r10 - r4, r52, r12 - r4, r13, r13, r16);
        goto L10
    }

    public static void drawStroke(Canvas r19, float r20, float r21, float r22, float r23, float[] r24, float r25, boolean r26, Paint r27) {
        if (r26 == false) goto L12;
        float r4 = r24[0];
        float r5 = r24[1];
        if (r4 != r5) goto L11;
        float r42 = r24[2];
        if (r5 != r42) goto L11;
        if (r42 != r24[3]) goto L11;
    L10:
        boolean r43 = true;
    L19:
        float r18 = r25 / 2.0f;
        if (r26 == false) goto L35;
        if (r43 == false) goto L27;
        r19.save();
        if (r19.clipRect(r20, r21, r22, MathUtils.clamp((r24[0] * 2.0f) + r21, r21, r23)) == false) goto L25;
        float r52 = r24[0];
        r19.drawRoundRect(r20 - r18, r21 + r18, r22 + r18, r23 + r18, r52, r52, r27);
    L25:
        r19.restore();
        return;
    L27:
        float r12 = (r20 + r22) / 2.0f;
        r19.save();
        if (r19.clipRect(r20, r21, r12, MathUtils.clamp((r24[0] * 2.0f) + r21, r21, r23)) == false) goto L30;
        r19.drawRoundRect(r20 - r18, r21 + r18, r22 + r18, r23 + r18, r24[0], r24[1], r27);
    L30:
        r19.restore();
        r19.save();
        if (r19.clipRect(r12, r21, r22, MathUtils.clamp((r24[0] * 2.0f) + r21, r21, r23)) == false) goto L33;
        r19.drawRoundRect(r20 - r18, r21 + r18, r22 + r18, r23 + r18, r24[2], r24[3], r27);
    L33:
        r19.restore();
        return;
    L35:
        if (r43 == false) goto L41;
        r19.save();
        if (r19.clipRect(r20, MathUtils.clamp(r23 - (r24[4] * 2.0f), r21, r23), r22, r23) == false) goto L39;
        float r53 = r24[4];
        r19.drawRoundRect(r20 - r18, r21 - r18, r22 + r18, r23 - r18, r53, r53, r27);
    L39:
        r19.restore();
        return;
    L41:
        float r13 = (r20 + r22) / 2.0f;
        r19.save();
        if (r19.clipRect(r20, MathUtils.clamp(r23 - (r24[4] * 2.0f), r21, r23), r13, r23) == false) goto L44;
        r19.drawRoundRect(r20 - r18, r21 - r18, r22 + r18, r23 - r18, r24[6], r24[7], r27);
    L44:
        r19.restore();
        r19.save();
        if (r19.clipRect(r13, MathUtils.clamp(r23 - (r24[4] * 2.0f), r21, r23), r22, r23) == false) goto L47;
        r19.drawRoundRect(r20 - r18, r21 - r18, r22 + r18, r23 - r18, r24[4], r24[5], r27);
    L47:
        r19.restore();
        return;
    L11:
        r43 = false;
        goto L19
    L12:
        float r44 = r24[4];
        float r54 = r24[5];
        if (r44 != r54) goto L11;
        float r45 = r24[6];
        if (r54 != r45) goto L11;
        if (r45 != r24[7]) goto L11;
        goto L11
    }

    public static void drawStroke(Canvas r9, RectF r10, float r11, float r12, boolean r13, Paint r14) {
        drawStroke(r9, r10.left, r10.top, r10.right, r10.bottom, r11, r12, r13, r14);
    }

    private void drawStrokeInternalIfNeeded(Canvas r5) {
        if (this.boundProps.useFullStroke == false) goto L8;
        int r0 = Theme.multAlpha(this.strokeColorFull, this.alpha / 255.0f);
        if (Color.alpha(r0) <= 0) goto L15;
        this.paintStrokeFill.setColor(r0);
        r5.drawPath(this.boundProps.strokePathTop, this.paintStrokeFill);
        return;
    L15:
        return;
    L8:
        int r02 = Theme.multAlpha(this.strokeColorTop, this.alpha / 255.0f);
        int r1 = Theme.multAlpha(this.strokeColorBottom, this.alpha / 255.0f);
        if (Color.alpha(r02) <= 0) goto L12;
        this.paintStrokeFill.setColor(r02);
        r5.drawPath(this.boundProps.strokePathTop, this.paintStrokeFill);
    L12:
        if (Color.alpha(r1) <= 0) goto L16;
        this.paintStrokeFill.setColor(r1);
        r5.drawPath(this.boundProps.strokePathBottom, this.paintStrokeFill);
        return;
    }

    public static void getOutline(Outline r8, Rect r9, float[] r10) {
        if (RadiiUtils.radiiAreSame(r10) == false) goto L6;
        r8.setRoundRect(r9, Math.min(r10[0], Math.min(r9.width(), r9.height()) / 2.0f));
        return;
    L6:
        Path r0 = tmpPath;
        if (r0 != null) goto L9;
        tmpPath = new Path();
    L10:
        tmpPath.addRoundRect(r9.left, r9.top, r9.right, r9.bottom, r10, Path.Direction.CW);
        r8.setConvexPath(tmpPath);
        return;
    L9:
        r0.rewind();
        goto L10
    }

    private /* synthetic */ void lambda$checkNinePatchDrawable$0(int r26, boolean r27, Canvas r28, RectF r29, float[] r30) {
        Path r4 = new Path();
        Path.Direction r11 = Path.Direction.CW;
        r4.addRoundRect(r29, r30, r11);
        Paint r5 = new Paint(1);
        r5.setStyle(Paint.Style.FILL);
        r5.setColor(r26);
        if (this.shadowLayerRadius <= 0.0f) goto L5;
        int r7 = Theme.multAlpha(this.shadowColor, this.shadowAlpha);
        r5.setShadowLayer(this.shadowLayerRadius, this.shadowLayerDx, this.shadowLayerDy, r7);
    L5:
        r28.drawPath(r4, r5);
        if (this.shadowLayerRadius <= 0.0f) goto L8;
        r5.clearShadowLayer();
        r28.drawPath(r4, r5);
    L8:
        if (r27 == false) goto L43;
        Paint r42 = new Paint(1);
        float[] r3 = Arrays.copyOf(r30, 8);
        boolean r13 = RadiiUtils.radiiAreSame(r3);
        float r14 = Math.min(r29.width(), r29.height()) / 2.0f;
        if (this.boundProps.useFullStroke == false) goto L19;
        Path r16 = new Path();
        float[] r10 = tmpRadii;
        System.arraycopy(r3, 0, r10, 0, 8);
        if (r13 == true) goto L14;
    L16:
        r16.addRoundRect(r29.left, r29.top, r29.right, r29.bottom, r10, r11);
        float r32 = r29.left;
        float r52 = this.boundProps.strokeWidthFull;
        r16.addRoundRect(r32 + r52, r29.top + r52, r29.right - r52, r29.bottom - r52, r10, Path.Direction.CCW);
        r16.close();
        r42.setColor(this.strokeColorFull);
        r28.drawPath(r16, r42);
        return;
    L14:
        if (r3[0] <= r14) goto L16;
        Arrays.fill(r10, r14);
        goto L16
    L19:
        if (Color.alpha(this.strokeColorTop) <= 0) goto L31;
        if (r3[0] <= 0.0f) goto L23;
    L24:
        float[] r102 = tmpRadii;
        Arrays.fill(r102, 0.0f);
        r102[0] = r3[0];
        r102[1] = r3[1];
        r102[2] = r3[2];
        r102[3] = r3[3];
        if (r13 == true) goto L27;
    L29:
        Path r17 = new Path();
        float r6 = r29.left;
        float r72 = r29.top;
        r17.addRoundRect(r6, r72, r29.right, Math.min(Math.max(r3[0], r3[2]) + r72, r29.bottom), r102, r11);
        float r53 = r29.left;
        float r62 = r29.top;
        r17.addRoundRect(r53, r62 + this.boundProps.strokeWidthTop, r29.right, Math.min(r62 + Math.max(r3[0], r3[2]), r29.bottom), r102, Path.Direction.CCW);
        r42.setColor(this.strokeColorTop);
        r28.drawPath(r17, r42);
        goto L31
    L27:
        if (r3[0] <= r14) goto L29;
        r102[3] = r14;
        r102[2] = r14;
        r102[1] = r14;
        r102[0] = r14;
        goto L29
    L23:
        if (r3[2] > 0.0f) goto L24;
    L31:
        if (Color.alpha(this.strokeColorBottom) > 0) goto L33;
        return;
    L33:
        if (r3[4] <= 0.0f) goto L35;
    L36:
        float[] r103 = tmpRadii;
        Arrays.fill(r103, 0.0f);
        r103[4] = r3[4];
        r103[5] = r3[5];
        r103[6] = r3[6];
        r103[7] = r3[7];
        if (r13 == true) goto L39;
    L41:
        Path r18 = new Path();
        r18.addRoundRect(r29.left, Math.max(r29.bottom - Math.max(r3[4], r3[6]), r29.top), r29.right, r29.bottom, r103, r11);
        r18.addRoundRect(r29.left, Math.max(r29.bottom - Math.max(r3[4], r3[6]), r29.top), r29.right, r29.bottom - this.boundProps.strokeWidthBottom, r103, Path.Direction.CCW);
        r42.setColor(this.strokeColorBottom);
        r28.drawPath(r18, r42);
        return;
    L39:
        if (r3[0] <= r14) goto L41;
        r103[7] = r14;
        r103[6] = r14;
        r103[5] = r14;
        r103[4] = r14;
        goto L41
    L35:
        if (r3[6] > 0.0f) goto L36;
        return;
    }

    public void drawSource(Canvas r3, BlurredBackgroundSource r4) {
        if (this.boundProps.boundsWithPadding.isEmpty() == false) goto L6;
        return;
    L6:
        if (Color.alpha(this.backgroundColor) != 255) goto L10;
        drawSourceColorImpl(r3, 0);
        return;
    L10:
        if ((r4 instanceof BlurredBackgroundSourceColor) == false) goto L14;
        drawSourceColor(r3, (BlurredBackgroundSourceColor) r4);
        return;
    L14:
        if ((r4 instanceof BlurredBackgroundSourceBitmap) == false) goto L18;
        drawSourceBitmap(r3, (BlurredBackgroundSourceBitmap) r4);
        return;
    L18:
        if (Build.VERSION.SDK_INT < 29) goto L24;
        if ((r4 instanceof BlurredBackgroundSourceRenderNode) == false) goto L24;
        drawSourceRenderNode(r3, (BlurredBackgroundSourceRenderNode) r4);
        return;
    L24:
        if ((r4 instanceof BlurredBackgroundSourceWrapped) == false) goto L27;
        drawSource(r3, ((BlurredBackgroundSourceWrapped) r4).getSource());
        return;
    L27:
        if (r4 == null) goto L30;
        drawSourceAny(r3, r4);
        return;
    }

    public void enableInAppKeyboardOptimization() {
        this.inAppKeyboardOptimization = true;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline r2) {
        Props r1 = this.boundProps;
        getOutline(r2, r1.boundsWithPadding, r1.radii);
    }

    public int getOutsetX() {
        return this.outsetX;
    }

    public int getOutsetY() {
        return this.outsetY;
    }

    public Rect getPaddedBounds() {
        return this.boundProps.boundsWithPadding;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect r2) {
        int r0 = this.boundProps.padding;
        r2.set(r0, r0, r0, r0);
        return this.boundProps.hasPadding;
    }

    public Path getPath() {
        return this.boundProps.path;
    }

    public void getPositionRelativeSource(RectF r2) {
        r2.set(this.boundProps.boundsWithPadding);
        r2.offset(this.sourceOffsetX, this.sourceOffsetY);
    }

    public abstract BlurredBackgroundSource getSource();

    public float getSourceOffsetX() {
        return this.sourceOffsetX;
    }

    public float getSourceOffsetY() {
        return this.sourceOffsetY;
    }

    public BlurredBackgroundSource getUnwrappedSource() {
        BlurredBackgroundSource r1 = getSource();
    L4:
        if ((r1 instanceof BlurredBackgroundSourceWrapped) == false) goto L6;
        r1 = ((BlurredBackgroundSourceWrapped) r1).getSource();
        goto L4
    L6:
        return r1;
    }

    public ViewOutlineProvider getViewOutlineProvider() {
        if (this.viewOutlineProvider != null) goto L6;
        this.viewOutlineProvider = new AnonymousClass1(this);
    L6:
        return this.viewOutlineProvider;
    }

    public boolean hasDisplayList() {
        return false;
    }

    public void onBoundPropsChanged() {
        dispatchSourceRelativePositionChange();
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect r2) {
        super.onBoundsChange(r2);
        this.boundProps.bounds.set(r2);
        this.boundProps.build();
        onBoundPropsChanged();
    }

    public void onSourceOffsetChange(float r1, float r2) {
        dispatchSourceRelativePositionChange();
    }

    public void onSourceRelativePositionChanged(RectF r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    public BlurredBackgroundDrawable setClipToOutline(boolean r1) {
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public BlurredBackgroundDrawable setColorProvider(BlurredBackgroundColorProvider r2) {
        this.colorProvider = r2;
        updateColors();
        if ((r2 instanceof BlurredBackgroundProvider) == false) goto L5;
        BlurredBackgroundProvider r22 = (BlurredBackgroundProvider) r2;
        setStrokeWidth(r22.getStrokeWidthTop(), r22.getStrokeWidthBottom());
    L5:
        return this;
    }

    public BlurredBackgroundDrawable setHasPadding(boolean r2) {
        this.boundProps.hasPadding = r2;
        return this;
    }

    public void setIntensity(float r2) {
        this.boundProps.liquidIntensity = r2;
        onBoundPropsChanged();
    }

    public BlurredBackgroundDrawable setOutset(int r1, int r2) {
        this.outsetX = r1;
        this.outsetY = r2;
        return this;
    }

    public BlurredBackgroundDrawable setPadding(int r3) {
        Props r0 = this.boundProps;
        if (r0.padding == r3) goto L5;
        r0.padding = r3;
        r0.build();
        onBoundPropsChanged();
    L5:
        return this;
    }

    public BlurredBackgroundDrawable setRadius(float r2) {
        Arrays.fill(this.boundProps.radii, r2);
        Arrays.fill(this.boundProps.shaderRadii, r2);
        this.boundProps.build();
        onBoundPropsChanged();
        return this;
    }

    public BlurredBackgroundDrawable setRadius(float r11, float r12, float r13, float r14) {
        Props r0 = this.boundProps;
        float[] r1 = r0.radii;
        r1[1] = r11;
        r1[0] = r11;
        r1[3] = r12;
        r1[2] = r12;
        r1[5] = r13;
        r1[4] = r13;
        r1[7] = r14;
        r1[6] = r14;
        float[] r15 = r0.shaderRadii;
        r15[1] = r11;
        r15[0] = r11;
        r15[3] = r12;
        r15[2] = r12;
        r15[5] = r13;
        r15[4] = r13;
        r15[7] = r14;
        r15[6] = r14;
        r0.build();
        onBoundPropsChanged();
        return this;
    }

    public void setRadius(float r11, float r12, float r13, float r14, boolean r15) {
        Props r0 = this.boundProps;
        float[] r1 = r0.radii;
        r1[1] = r11;
        r1[0] = r11;
        r1[3] = r12;
        r1[2] = r12;
        float r6 = 0.0f;
        if (r15 == false) goto L5;
        float r7 = 0.0f;
    L6:
        r1[5] = r7;
        r1[4] = r7;
        if (r15 == true) goto L10;
        r6 = r14;
    L10:
        r1[7] = r6;
        r1[6] = r6;
        float[] r16 = r0.shaderRadii;
        r16[1] = r11;
        r16[0] = r11;
        r16[3] = r12;
        r16[2] = r12;
        r16[5] = r13;
        r16[4] = r13;
        r16[7] = r14;
        r16[6] = r14;
        r0.build();
        onBoundPropsChanged();
        return;
    L5:
        r7 = r13;
        goto L6
    }

    public void setShadowAlpha(float r2) {
        if (this.shadowAlpha == r2) goto L6;
        this.shadowAlpha = r2;
        invalidateSelf();
        return;
    }

    public void setShadowParams(float r1, float r2, float r3) {
        this.shadowLayerRadius = r1;
        this.shadowLayerDx = r2;
        this.shadowLayerDy = r3;
    }

    public void setSourceOffset(float r2, float r3) {
        if (this.sourceOffsetX == r2) goto L5;
    L8:
        this.sourceOffsetX = r2;
        this.sourceOffsetY = r3;
        onSourceOffsetChange(r2, r3);
        return;
    L5:
        if (this.sourceOffsetY != r3) goto L8;
    }

    public void setStrokeWidth(float r1, float r2) {
        Props r0 = this.boundProps;
        r0.strokeWidthTop = r1;
        r0.strokeWidthBottom = r2;
    }

    public BlurredBackgroundDrawable setThickness(int r2) {
        this.boundProps.liquidThickness = r2;
        onBoundPropsChanged();
        return this;
    }

    public void updateColors() {
        if (this.colorProvider != null) goto L5;
        return;
    L5:
        GlassOutlineStyle r0 = ExteraConfig.getGlassOutlineStyle();
        boolean r2 = true;
        int r3 = 0;
        if (r0 != GlassOutlineStyle.GLARE) goto L8;
        boolean r1 = true;
    L10:
        if (r0 != GlassOutlineStyle.SOLID) goto L12;
        boolean r02 = true;
    L13:
        Props r4 = this.boundProps;
        if (r4.useFullStroke == r02) goto L16;
        r4.useFullStroke = r02;
        r4.build();
    L16:
        if (r1 == true) goto L23;
        BlurredBackgroundColorProvider r42 = this.colorProvider;
        if ((r42 instanceof BlurredBackgroundProvider) == true) goto L20;
    L22:
        r2 = false;
        goto L23
    L20:
        if (((BlurredBackgroundProvider) r42).isShadowAlwaysVisible() == false) goto L22;
    L23:
        this.backgroundColor = this.colorProvider.getBackgroundColor();
        if (r2 == false) goto L26;
        int r43 = this.colorProvider.getShadowColor();
    L27:
        this.shadowColor = r43;
        if (r1 == false) goto L30;
        int r44 = this.colorProvider.getStrokeColorTop();
    L31:
        this.strokeColorTop = r44;
        if (r1 == false) goto L34;
        int r12 = this.colorProvider.getStrokeColorBottom();
    L35:
        this.strokeColorBottom = r12;
        if (r02 == false) goto L38;
        r3 = this.colorProvider.getStrokeColorFull();
    L38:
        this.strokeColorFull = r3;
        BlurredBackgroundColorProvider r03 = this.colorProvider;
        if ((r03 instanceof BlurredBackgroundProvider) == false) goto L44;
        BlurredBackgroundProvider r04 = (BlurredBackgroundProvider) r03;
        if (r2 == false) goto L43;
        setShadowParams(r04.getShadowRadius(), r04.getShadowDx(), r04.getShadowDy());
        goto L44
    L43:
        setShadowParams(0.0f, 0.0f, 0.0f);
    L44:
        invalidateSelf();
        return;
    L34:
        r12 = 0;
        goto L35
    L30:
        r44 = 0;
        goto L31
    L26:
        r43 = 0;
        goto L27
    L12:
        r02 = false;
        goto L13
    L8:
        r1 = false;
        goto L10
    }

    public void updateDisplayList() {
    }
}
