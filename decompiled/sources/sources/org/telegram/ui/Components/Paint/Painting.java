package org.telegram.ui.Components.Paint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.opengl.GLES20;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Paint.Brush;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class Painting {
    private Path activePath;
    private Shape activeShape;
    private RectF activeStrokeBounds;
    private Slice backupSlice;
    private Texture bitmapBlurTexture;
    private Texture bitmapTexture;
    private final BlurringShader.BlurManager blurManager;
    private Bitmap bluredBitmap;
    private Texture bluredTexture;
    private Brush brush;
    private HashMap<Integer, Texture> brushTextures;
    private int[] buffers;
    private ByteBuffer dataBuffer;
    private PaintingDelegate delegate;
    public boolean hasBlur;
    private float helperAlpha;
    private ValueAnimator helperAnimator;
    private float helperApplyAlpha;
    private ValueAnimator helperApplyAnimator;
    private Shape helperShape;
    private boolean helperShown;
    private int helperTexture;
    private Bitmap imageBitmap;
    private Paint imageBitmapPaint;
    private int imageBitmapRotation;
    public boolean masking;
    private Texture originalBitmapTexture;
    private int paintTexture;
    private boolean paused;
    private float[] projection;
    private float[] renderProjection;
    private RenderState renderState;
    private RenderView renderView;
    private int reusableFramebuffer;
    private Map<String, Shader> shaders;
    private Size size;
    private int suppressChangesCounter;
    private ByteBuffer textureBuffer;
    private ByteBuffer vertexBuffer;

    /* renamed from: org.telegram.ui.Components.Paint.Painting$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ Painting this$0;

        public static /* synthetic */ void $r8$lambda$Bpt8pLV9xDTPo5YTpHlgLIgiurU(AnonymousClass1 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass1(Painting r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            if (Painting.m11365$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            Painting.m11365$$Nest$fgetdelegate(this.this$0).contentChanged();
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            Painting.m11369$$Nest$fputhelperAnimator(this.this$0, null);
            Painting.m11367$$Nest$fgetrenderView(this.this$0).performInContext(new Painting$1$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Painting$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ Painting this$0;

        public static /* synthetic */ void $r8$lambda$L9Qp3HcIU1vwTlS_5MIKlTRhxig(AnonymousClass2 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass2(Painting r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            Shape r0 = Painting.m11366$$Nest$fgethelperShape(this.this$0);
            Painting r1 = this.this$0;
            if (r0 != null) goto L6;
            Painting.m11371$$Nest$fputhelperApplyAnimator(r1, null);
            return;
        L6:
            int r02 = Painting.m11367$$Nest$fgetrenderView(r1).getCurrentColor();
            Painting r12 = this.this$0;
            Painting.m11376$$Nest$mpaintStrokeInternal(r12, Painting.m11363$$Nest$fgetactivePath(r12), false, false);
            Painting r13 = this.this$0;
            Slice r14 = Painting.m11374$$Nest$mcommitPathInternal(r13, Painting.m11363$$Nest$fgetactivePath(r13), r02, new RectF(Painting.m11364$$Nest$fgetactiveStrokeBounds(this.this$0)));
            Painting.m11373$$Nest$mclearStrokeInternal(this.this$0);
            Shape r3 = Painting.m11366$$Nest$fgethelperShape(this.this$0);
            Painting r5 = this.this$0;
            RectF r6 = new RectF();
            Painting.m11368$$Nest$fputactiveStrokeBounds(r5, r6);
            r3.getBounds(r6);
            Slice r52 = Painting.m11375$$Nest$mcommitShapeInternal(this.this$0, r3, r02, new RectF(Painting.m11364$$Nest$fgetactiveStrokeBounds(this.this$0)));
            Painting.m11377$$Nest$mrestoreSliceInternal(this.this$0, r52, false);
            Painting.m11377$$Nest$mrestoreSliceInternal(this.this$0, r14, false);
            Painting.m11375$$Nest$mcommitShapeInternal(this.this$0, r3, r02, null);
            Painting.m11372$$Nest$fputhelperShape(this.this$0, null);
            Painting.m11370$$Nest$fputhelperApplyAlpha(this.this$0, 0.0f);
            Painting.m11371$$Nest$fputhelperApplyAnimator(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            Painting.m11367$$Nest$fgetrenderView(this.this$0).performInContext(new Painting$2$$ExternalSyntheticLambda0(this));
        }
    }

    public static class PaintingData {
        public Bitmap bitmap;
        public ByteBuffer data;

        public PaintingData(Bitmap r1, ByteBuffer r2) {
            this.bitmap = r1;
            this.data = r2;
        }
    }

    public interface PaintingDelegate {
        void contentChanged();

        DispatchQueue requestDispatchQueue();

        UndoStore requestUndoStore();
    }

    /* renamed from: $r8$lambda$-QmPvXAxwrQftIec_OBqrLmV8oA, reason: not valid java name */
    public static /* synthetic */ void m11357$r8$lambda$QmPvXAxwrQftIec_OBqrLmV8oA(Painting r0, Runnable r1) {
        r0.lambda$clearStroke$9(r1);
    }

    /* renamed from: $r8$lambda$2NMuSJLbAgBhp-blqqjEZPLCbbc, reason: not valid java name */
    public static /* synthetic */ void m11358$r8$lambda$2NMuSJLbAgBhpblqqjEZPLCbbc(Painting r0, Slice r1, Slice r2, boolean r3) {
        r0.lambda$registerDoubleUndo$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$8dTGGZuY4dd1TOd0wG93zaEiWMA(Painting r0, Runnable r1) {
        r0.lambda$onPause$14(r1);
    }

    /* renamed from: $r8$lambda$8h72w_q-irL4YmgOUKsI8Zq9tCU, reason: not valid java name */
    public static /* synthetic */ void m11359$r8$lambda$8h72w_qirL4YmgOUKsI8Zq9tCU(Painting r0, Shape r1, int r2) {
        r0.lambda$commitShape$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Aw_lekRofsgmovekGlW8pmsHCb8(Painting r0, ValueAnimator r1) {
        r0.lambda$setHelperShape$0(r1);
    }

    /* renamed from: $r8$lambda$D7xv3Zh0ZguN_pl4-lXZnRc5SdY, reason: not valid java name */
    public static /* synthetic */ void m11360$r8$lambda$D7xv3Zh0ZguN_pl4lXZnRc5SdY(Painting r0, Shape r1, Runnable r2) {
        r0.lambda$paintShape$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Mz_DsJFOWUBdzxy8OWOa6DlPIFE(Painting r0, ValueAnimator r1) {
        r0.lambda$applyHelperShape$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$QdT7AV3DgGzovgyNxiyIS1yA91Y(Painting r0, Slice r1) {
        r0.lambda$restoreSlice$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$SWpnSsF3urEavBx7RQWA4njeRH8(Painting r0, Path r1, boolean r2, boolean r3, Runnable r4) {
        r0.lambda$paintStroke$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ZsAwdf1tTPUi_E2_O7tK2tSfKng(Painting r0) {
        r0.lambda$clearShape$10();
    }

    /* renamed from: $r8$lambda$ZxVVUCo-_IQGlekv0owmtZwrwO8, reason: not valid java name */
    public static /* synthetic */ void m11361$r8$lambda$ZxVVUCo_IQGlekv0owmtZwrwO8(Painting r0, ValueAnimator r1) {
        r0.lambda$applyHelperShape$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$_b7zY8p_zIW9XEsWgNmSx_JzXp8(Painting r0, ValueAnimator r1) {
        r0.lambda$setHelperShape$1(r1);
    }

    /* renamed from: $r8$lambda$nsLrWXtEEo0-hTAg_Idtlzp6XaI, reason: not valid java name */
    public static /* synthetic */ void m11362$r8$lambda$nsLrWXtEEo0hTAg_Idtlzp6XaI(Painting r0, Path r1, int r2, boolean r3, Runnable r4) {
        r0.lambda$commitPath$8(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$vse9kNlTqdEnh43BMZFV8pJ3i8U(Painting r0, Slice r1) {
        r0.lambda$registerUndo$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$wXwIZq4jVIvz_VfVy0DhdxsszpY(Painting r0, Shape r1) {
        r0.lambda$setHelperShape$2(r1);
    }

    /* renamed from: -$$Nest$fgetactivePath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m11363$$Nest$fgetactivePath(Painting r0) {
        return r0.activePath;
    }

    /* renamed from: -$$Nest$fgetactiveStrokeBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m11364$$Nest$fgetactiveStrokeBounds(Painting r0) {
        return r0.activeStrokeBounds;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintingDelegate m11365$$Nest$fgetdelegate(Painting r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgethelperShape, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Shape m11366$$Nest$fgethelperShape(Painting r0) {
        return r0.helperShape;
    }

    /* renamed from: -$$Nest$fgetrenderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderView m11367$$Nest$fgetrenderView(Painting r0) {
        return r0.renderView;
    }

    /* renamed from: -$$Nest$fputactiveStrokeBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11368$$Nest$fputactiveStrokeBounds(Painting r0, RectF r1) {
        r0.activeStrokeBounds = r1;
    }

    /* renamed from: -$$Nest$fputhelperAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11369$$Nest$fputhelperAnimator(Painting r0, ValueAnimator r1) {
        r0.helperAnimator = r1;
    }

    /* renamed from: -$$Nest$fputhelperApplyAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11370$$Nest$fputhelperApplyAlpha(Painting r0, float r1) {
        r0.helperApplyAlpha = r1;
    }

    /* renamed from: -$$Nest$fputhelperApplyAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11371$$Nest$fputhelperApplyAnimator(Painting r0, ValueAnimator r1) {
        r0.helperApplyAnimator = r1;
    }

    /* renamed from: -$$Nest$fputhelperShape, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11372$$Nest$fputhelperShape(Painting r0, Shape r1) {
        r0.helperShape = r1;
    }

    /* renamed from: -$$Nest$mclearStrokeInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11373$$Nest$mclearStrokeInternal(Painting r0) {
        r0.clearStrokeInternal();
    }

    /* renamed from: -$$Nest$mcommitPathInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Slice m11374$$Nest$mcommitPathInternal(Painting r0, Path r1, int r2, RectF r3) {
        return r0.commitPathInternal(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mcommitShapeInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Slice m11375$$Nest$mcommitShapeInternal(Painting r0, Shape r1, int r2, RectF r3) {
        return r0.commitShapeInternal(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mpaintStrokeInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11376$$Nest$mpaintStrokeInternal(Painting r0, Path r1, boolean r2, boolean r3) {
        r0.paintStrokeInternal(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mrestoreSliceInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11377$$Nest$mrestoreSliceInternal(Painting r0, Slice r1, boolean r2) {
        r0.restoreSliceInternal(r1, r2);
    }

    public Painting(Size r7, Bitmap r8, int r9, BlurringShader.BlurManager r10) {
        this.brushTextures = new HashMap();
        this.buffers = new int[1];
        this.masking = false;
        this.renderState = new RenderState();
        this.blurManager = r10;
        this.size = r7;
        this.imageBitmap = r8;
        this.imageBitmapRotation = r9;
        this.dataBuffer = ByteBuffer.allocateDirect((((int) r7.width) * ((int) r7.height)) * 4);
        Size r72 = this.size;
        this.projection = GLMatrix.LoadOrtho(0.0f, r72.width, 0.0f, r72.height, -1.0f, 1.0f);
        if (this.vertexBuffer != null) goto L5;
        ByteBuffer r73 = ByteBuffer.allocateDirect(32);
        this.vertexBuffer = r73;
        r73.order(ByteOrder.nativeOrder());
    L5:
        this.vertexBuffer.putFloat(0.0f);
        this.vertexBuffer.putFloat(0.0f);
        this.vertexBuffer.putFloat(this.size.width);
        this.vertexBuffer.putFloat(0.0f);
        this.vertexBuffer.putFloat(0.0f);
        this.vertexBuffer.putFloat(this.size.height);
        this.vertexBuffer.putFloat(this.size.width);
        this.vertexBuffer.putFloat(this.size.height);
        this.vertexBuffer.rewind();
        if (this.textureBuffer != null) goto L9;
        ByteBuffer r74 = ByteBuffer.allocateDirect(32);
        this.textureBuffer = r74;
        r74.order(ByteOrder.nativeOrder());
        this.textureBuffer.putFloat(0.0f);
        this.textureBuffer.putFloat(0.0f);
        this.textureBuffer.putFloat(1.0f);
        this.textureBuffer.putFloat(0.0f);
        this.textureBuffer.putFloat(0.0f);
        this.textureBuffer.putFloat(1.0f);
        this.textureBuffer.putFloat(1.0f);
        this.textureBuffer.putFloat(1.0f);
        this.textureBuffer.rewind();
        return;
    }

    private void beginSuppressingChanges() {
        this.suppressChangesCounter++;
    }

    private void clearStrokeInternal() {
        GLES20.glBindFramebuffer(36160, getReusableFramebuffer());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, getPaintTexture(), 0);
        Utils.HasGLError();
        if (GLES20.glCheckFramebufferStatus(36160) != 36053) goto L5;
        Size r0 = this.size;
        GLES20.glViewport(0, 0, (int) r0.width, (int) r0.height);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
    L5:
        GLES20.glBindFramebuffer(36160, 0);
        PaintingDelegate r02 = this.delegate;
        if (r02 == null) goto L8;
        r02.contentChanged();
    L8:
        this.renderState.reset();
        this.activeStrokeBounds = null;
        this.activePath = null;
        this.helperApplyAlpha = 0.0f;
    }

    private Slice commitPathInternal(Path r27, int r28, RectF r29) {
        Brush r2 = this.brush;
        if (r27 == null) goto L6;
        r2 = r27.getBrush();
    L6:
        if (this.blurManager == null) goto L12;
        boolean r3 = r2 instanceof Brush.Blurer;
        if (r3 == false) goto L10;
    L11:
        Slice r1 = registerDoubleUndo(r29, this.hasBlur);
        this.hasBlur = r3;
    L13:
        beginSuppressingChanges();
        if (this.blurManager != null) goto L16;
    L20:
        int r32 = 1;
    L21:
        int r7 = 0;
    L22:
        Object r9 = null;
        if (r7 >= r32) goto L60;
        GLES20.glBindFramebuffer(36160, getReusableFramebuffer());
        int r10 = getTexture();
        if (this.blurManager != null) goto L27;
    L36:
        if (r7 == 1) goto L38;
    L40:
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, r10, 0);
        Size r8 = this.size;
        GLES20.glViewport(0, 0, (int) r8.width, (int) r8.height);
        Shader r82 = this.shaders.get(r2.getShaderName(1));
        if (r82 == null) goto L42;
        GLES20.glUseProgram(r82.program);
        GLES20.glUniformMatrix4fv(r82.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(this.projection));
        GLES20.glUniform1i(r82.getUniform("texture"), 0);
        GLES20.glUniform1i(r82.getUniform(PluginsConstants.Settings.MASK), 1);
        Shader.SetColorUniform(r82.getUniform("color"), ColorUtils.setAlphaComponent(r28, (int) (Color.alpha(r28) * r2.getOverrideAlpha())));
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r10);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, getPaintTexture());
        if ((r2 instanceof Brush.Blurer) == false) goto L49;
        GLES20.glUniform1i(r82.getUniform("blured"), 2);
        GLES20.glActiveTexture(33986);
        BlurringShader.BlurManager r83 = this.blurManager;
        if (r83 == null) goto L48;
        r9 = r83.getTextureLock();
        GLES20.glBindTexture(3553, this.blurManager.getTexture());
        goto L49
    L48:
        GLES20.glBindTexture(3553, this.bluredTexture.texture());
    L49:
        GLES20.glBlendFunc(1, 0);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        if (r9 == null) goto L58;
        monitor-enter(r9);
        GLES20.glDrawArrays(5, 0, 4);     // Catch: Throwable -> L55
        monitor-exit(r9);     // Catch: Throwable -> L55
    L59:
        GLES20.glBindTexture(3553, getTexture());
        GLES20.glTexParameteri(3553, 10241, 9729);
        r7 = r7 + 1;
    L55:
        th = move-exception;
        throw th;
    L58:
        GLES20.glDrawArrays(5, 0, 4);
        goto L59
    L42:
        return null;
    L38:
        if ((r2 instanceof Brush.Blurer) == false) goto L40;
        r2 = new Brush.Eraser();
        goto L40
    L27:
        if ((r2 instanceof Brush.Blurer) == false) goto L30;
        if (r7 != 0) goto L30;
    L32:
        Texture r102 = this.bitmapBlurTexture;
        if (r102 == null) goto L35;
        r10 = r102.texture();
        goto L36
    L35:
        r10 = 0;
    L30:
        if ((r2 instanceof Brush.Eraser) == false) goto L36;
        if (r7 != 1) goto L36;
    L60:
        GLES20.glBindFramebuffer(36160, 0);
        if (isSuppressingChanges() == true) goto L65;
        PaintingDelegate r22 = this.delegate;
        if (r22 == null) goto L65;
        r22.contentChanged();
    L65:
        endSuppressingChanges();
        this.renderState.reset();
        this.activePath = null;
        this.activeShape = null;
        return r1;
    L16:
        if ((r2 instanceof Brush.Blurer) == false) goto L18;
    L19:
        r32 = 2;
        goto L21
    L18:
        if ((r2 instanceof Brush.Eraser) == false) goto L20;
    L10:
        if ((r2 instanceof Brush.Eraser) == true) goto L11;
    L12:
        r1 = registerUndo(r29, false);
        goto L13
    }

    private Slice commitShapeInternal(Shape r24, int r25, RectF r26) {
        Brush r2 = r24.brush;
        if (r2 != null) goto L6;
        r2 = this.brush;
    L6:
        if (this.blurManager != null) goto L8;
    L11:
        boolean r6 = false;
    L12:
        Slice r3 = registerUndo(r26, r6);
        beginSuppressingChanges();
        GLES20.glBindFramebuffer(36160, getReusableFramebuffer());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, getTexture(), 0);
        Size r62 = this.size;
        GLES20.glViewport(0, 0, (int) r62.width, (int) r62.height);
        Shader r63 = this.shaders.get(r2.getShaderName(1));
        if (r63 != null) goto L15;
        return null;
    L15:
        GLES20.glUseProgram(r63.program);
        GLES20.glUniformMatrix4fv(r63.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(this.projection));
        GLES20.glUniform1i(r63.getUniform("texture"), 0);
        GLES20.glUniform1i(r63.getUniform(PluginsConstants.Settings.MASK), 1);
        Shader.SetColorUniform(r63.getUniform("color"), r25);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, getTexture());
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, getPaintTexture());
        if ((r2 instanceof Brush.Blurer) == false) goto L21;
        if (this.bluredTexture == null) goto L21;
        GLES20.glUniform1i(r63.getUniform("blured"), 2);
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(3553, this.bluredTexture.texture());
    L21:
        if ((r2 instanceof Brush.Shape) == false) goto L23;
        GLES20.glUniform1i(r63.getUniform("type"), r24.getType());
        int r22 = r63.getUniform("resolution");
        Size r12 = this.size;
        GLES20.glUniform2f(r22, r12.width, r12.height);
        GLES20.glUniform2f(r63.getUniform("center"), r24.centerX, r24.centerY);
        GLES20.glUniform2f(r63.getUniform("radius"), r24.radiusX, r24.radiusY);
        GLES20.glUniform1f(r63.getUniform("thickness"), r24.thickness);
        GLES20.glUniform1f(r63.getUniform("rounding"), r24.rounding);
        GLES20.glUniform2f(r63.getUniform("middle"), r24.middleX, r24.middleY);
        GLES20.glUniform1f(r63.getUniform("rotation"), r24.rotation);
        GLES20.glUniform1i(r63.getUniform("fill"), r24.fill ? 1 : 0);
        GLES20.glUniform1f(r63.getUniform("arrowTriangleLength"), r24.arrowTriangleLength);
        GLES20.glUniform1i(r63.getUniform("composite"), 1);
        GLES20.glUniform1i(r63.getUniform("clear"), 0);
    L23:
        GLES20.glBlendFunc(1, 0);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindTexture(3553, getTexture());
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glBindFramebuffer(36160, 0);
        if (this.delegate != null) goto L26;
    L28:
        endSuppressingChanges();
        this.renderState.reset();
        this.helperApplyAlpha = 0.0f;
        this.helperShown = false;
        this.helperAlpha = 0.0f;
        this.helperShape = null;
        this.activePath = null;
        this.activeShape = null;
        return r3;
    L26:
        if (isSuppressingChanges() == true) goto L28;
        this.delegate.contentChanged();
        goto L28
    L8:
        if ((r2 instanceof Brush.Blurer) == false) goto L11;
        r6 = true;
        goto L12
    }

    private void endSuppressingChanges() {
        this.suppressChangesCounter--;
    }

    private int getPaintTexture() {
        if (this.paintTexture != 0) goto L6;
        this.paintTexture = Texture.generateTexture(this.size);
    L6:
        return this.paintTexture;
    }

    private int getReusableFramebuffer() {
        if (this.reusableFramebuffer != 0) goto L6;
        int[] r1 = new int[1];
        GLES20.glGenFramebuffers(1, r1, 0);
        this.reusableFramebuffer = r1[0];
        Utils.HasGLError();
    L6:
        return this.reusableFramebuffer;
    }

    private int getTexture() {
        Texture r0 = this.bitmapTexture;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.texture();
    }

    private boolean isSuppressingChanges() {
        if (this.suppressChangesCounter <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$applyHelperShape$3(ValueAnimator r1) {
        this.helperApplyAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        PaintingDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.contentChanged();
        return;
    }

    private /* synthetic */ void lambda$applyHelperShape$4(ValueAnimator r3) {
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda11(this, r3));
    }

    private /* synthetic */ void lambda$clearShape$10() {
        this.activeShape = null;
        PaintingDelegate r1 = this.delegate;
        if (r1 == null) goto L6;
        r1.contentChanged();
        return;
    }

    private /* synthetic */ void lambda$clearStroke$9(Runnable r1) {
        clearStrokeInternal();
        if (r1 == null) goto L6;
        r1.run();
        return;
    }

    private /* synthetic */ void lambda$commitPath$8(Path r3, int r4, boolean r5, Runnable r6) {
        if (r5 == false) goto L5;
        RectF r1 = this.activeStrokeBounds;
    L6:
        commitPathInternal(r3, r4, r1);
        if (r5 == false) goto L9;
        this.activeStrokeBounds = null;
    L9:
        if (r6 == null) goto L12;
        r6.run();
        return;
    L12:
        return;
    L5:
        r1 = null;
        goto L6
    }

    private /* synthetic */ void lambda$commitShape$7(Shape r2, int r3) {
        commitShapeInternal(r2, r3, this.activeStrokeBounds);
        this.activeStrokeBounds = null;
    }

    private /* synthetic */ void lambda$onPause$14(Runnable r6) {
        this.paused = true;
        this.backupSlice = new Slice(getPaintingData(getBounds(), true, false, false).data, 0, getBounds(), this.delegate.requestDispatchQueue());
        cleanResources(false);
        if (r6 == null) goto L6;
        r6.run();
        return;
    }

    private /* synthetic */ void lambda$paintShape$5(Shape r2, Runnable r3) {
        this.activeShape = r2;
        if (this.activeStrokeBounds != null) goto L5;
        this.activeStrokeBounds = new RectF();
    L5:
        this.activeShape.getBounds(this.activeStrokeBounds);
        PaintingDelegate r1 = this.delegate;
        if (r1 == null) goto L8;
        r1.contentChanged();
    L8:
        if (r3 == null) goto L11;
        r3.run();
        return;
    }

    private /* synthetic */ void lambda$paintStroke$6(Path r1, boolean r2, boolean r3, Runnable r4) {
        paintStrokeInternal(r1, r2, r3);
        if (r4 == null) goto L6;
        r4.run();
        return;
    }

    private /* synthetic */ void lambda$registerDoubleUndo$12(Slice r1, Slice r2, boolean r3) {
        restoreSlice(r1);
        restoreSlice(r2);
        this.hasBlur = r3;
    }

    private /* synthetic */ void lambda$registerUndo$11(Slice r1) {
        restoreSlice(r1);
    }

    private /* synthetic */ void lambda$restoreSlice$13(Slice r2) {
        restoreSliceInternal(r2, true);
    }

    private /* synthetic */ void lambda$setHelperShape$0(ValueAnimator r1) {
        this.helperAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        PaintingDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.contentChanged();
        return;
    }

    private /* synthetic */ void lambda$setHelperShape$1(ValueAnimator r3) {
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda14(this, r3));
    }

    private /* synthetic */ void lambda$setHelperShape$2(Shape r6) {
        if (r6 != null) goto L4;
    L6:
        boolean r0 = this.helperShown;
        if (r6 == null) goto L9;
        boolean r3 = true;
    L10:
        if (r0 == r3) goto L29;
        if (r6 == null) goto L13;
        boolean r02 = true;
    L14:
        this.helperShown = r02;
        ValueAnimator r03 = this.helperAnimator;
        if (r03 == null) goto L17;
        r03.cancel();
        this.helperAnimator = null;
    L17:
        float r04 = this.helperAlpha;
        if (this.helperShown == false) goto L20;
        float r32 = 1.0f;
    L21:
        ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{r04, r32});
        this.helperAnimator = r05;
        r05.addUpdateListener(new Painting$$ExternalSyntheticLambda12(this));
        this.helperAnimator.addListener(new AnonymousClass1(this));
        this.helperAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.helperAnimator.start();
        this.helperShape = r6;
        PaintingDelegate r62 = this.delegate;
        if (r62 == null) goto L25;
        r62.contentChanged();
    L25:
        if (this.helperShown == false) goto L34;
        BotWebViewVibrationEffect.SELECTION_CHANGE.vibrate();
        return;
    L34:
        return;
    L20:
        r32 = 0.0f;
        goto L21
    L13:
        r02 = false;
        goto L14
    L29:
        if (r6 == this.helperShape) goto L35;
        this.helperShape = r6;
        PaintingDelegate r5 = this.delegate;
        if (r5 == null) goto L36;
        r5.contentChanged();
        return;
    L36:
        return;
    L35:
        return;
    L9:
        r3 = false;
        goto L10
    L4:
        if (this.helperTexture != 0) goto L6;
        this.helperTexture = Texture.generateTexture(this.size);
        goto L6
    }

    private void paintStrokeInternal(Path r7, boolean r8, boolean r9) {
        this.activePath = r7;
        if (r7 == null) goto L34;
        GLES20.glBindFramebuffer(36160, getReusableFramebuffer());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, getPaintTexture(), 0);
        Utils.HasGLError();
        if (GLES20.glCheckFramebufferStatus(36160) != 36053) goto L24;
        Size r0 = this.size;
        GLES20.glViewport(0, 0, (int) r0.width, (int) r0.height);
        if (r8 == false) goto L11;
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
    L11:
        if (this.shaders == null) goto L35;
        Brush r82 = r7.getBrush();
        Shader r02 = this.shaders.get(r82.getShaderName(2));
        if (r02 != null) goto L16;
        return;
    L16:
        GLES20.glUseProgram(r02.program);
        Texture r2 = this.brushTextures.get(Integer.valueOf(r82.getStampResId()));
        if (r2 != null) goto L19;
        r2 = new Texture(r82.getStamp());
        this.brushTextures.put(Integer.valueOf(r82.getStampResId()), r2);
    L19:
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r2.texture());
        GLES20.glUniformMatrix4fv(r02.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(this.projection));
        GLES20.glUniform1i(r02.getUniform("texture"), 0);
        RenderState r83 = this.renderState;
        if (r9 == true) goto L22;
        r83.viewportScale = this.renderView.getScaleX();
    L23:
        RectF r72 = Render.RenderPath(r7, this.renderState, r9);
    L25:
        GLES20.glBindFramebuffer(36160, 0);
        PaintingDelegate r84 = this.delegate;
        if (r84 == null) goto L28;
        r84.contentChanged();
    L28:
        RectF r85 = this.activeStrokeBounds;
        if (r85 == null) goto L32;
        r85.union(r72);
        return;
    L32:
        this.activeStrokeBounds = r72;
        return;
    L22:
        r83.viewportScale = 1.0f;
        goto L23
    L35:
        return;
    L24:
        r72 = null;
        goto L25
    }

    private Slice registerDoubleUndo(RectF r6, boolean r7) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if (r6.setIntersect(r6, getBounds()) == true) goto L8;
        return null;
    L8:
        Slice r0 = new Slice(getPaintingData(r6, true, false, false).data, 0, r6, this.delegate.requestDispatchQueue());
        Slice r3 = new Slice(getPaintingData(r6, true, true, false).data, 1, r6, this.delegate.requestDispatchQueue());
        this.delegate.requestUndoStore().registerUndo(UUID.randomUUID(), new Painting$$ExternalSyntheticLambda10(this, r0, r3, r7));
        return r0;
    }

    private Slice registerUndo(RectF r4, boolean r5) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if (r4.setIntersect(r4, getBounds()) == true) goto L8;
        return null;
    L8:
        ByteBuffer r1 = getPaintingData(r4, true, r5, false).data;
        DispatchQueue r2 = this.delegate.requestDispatchQueue();
        Slice r0 = new Slice(r1, r5 ? 1 : 0, r4, r2);
        this.delegate.requestUndoStore().registerUndo(UUID.randomUUID(), new Painting$$ExternalSyntheticLambda7(this, r0));
        return r0;
    }

    private void renderBlit(int r20, float r21) {
        Map<String, Shader> r2 = this.shaders;
        if (this.masking == false) goto L5;
        String r3 = "maskingBlit";
    L6:
        Shader r22 = r2.get(r3);
        if (r20 == 0) goto L16;
        if (r22 == null) goto L17;
        GLES20.glUseProgram(r22.program);
        GLES20.glUniformMatrix4fv(r22.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(this.renderProjection));
        GLES20.glUniform1f(r22.getUniform("alpha"), r21);
        if (this.masking == false) goto L13;
        GLES20.glUniform1i(r22.getUniform("texture"), 1);
        GLES20.glUniform1i(r22.getUniform(PluginsConstants.Settings.MASK), 0);
        GLES20.glUniform1f(r22.getUniform("preview"), 0.4f);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r20);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.originalBitmapTexture.texture());
    L14:
        GLES20.glBlendFunc(1, 771);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        GLES20.glDrawArrays(5, 0, 4);
        Utils.HasGLError();
        return;
    L13:
        GLES20.glUniform1i(r22.getUniform("texture"), 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r20);
        goto L14
    L17:
        return;
    L16:
        return;
    L5:
        r3 = "blit";
        goto L6
    }

    private void renderBlitPath(int r18, Path r19, float r20) {
        if (r19 == null) goto L49;
        Brush r1 = r19.getBrush();
        if (r1 != null) goto L9;
        r1 = this.brush;
    L9:
        if (this.masking == true) goto L11;
    L15:
        boolean r2 = false;
    L16:
        Map<String, Shader> r5 = this.shaders;
        StringBuilder r6 = new StringBuilder();
        r6.append(r1.getShaderName(0));
        if (r2 == false) goto L19;
        String r7 = "_masking";
    L20:
        r6.append(r7);
        Shader r52 = r5.get(r6.toString());
        if (r52 != null) goto L23;
        return;
    L23:
        GLES20.glUseProgram(r52.program);
        GLES20.glUniformMatrix4fv(r52.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(this.renderProjection));
        GLES20.glUniform1i(r52.getUniform("texture"), 0);
        GLES20.glUniform1i(r52.getUniform(PluginsConstants.Settings.MASK), 1);
        Shader.SetColorUniform(r52.getUniform("color"), ColorUtils.setAlphaComponent(r19.getColor(), (int) ((Color.alpha(r6) * r1.getOverrideAlpha()) * r20)));
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, getTexture());
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, r18);
        if (r2 == false) goto L27;
        GLES20.glUniform1i(r52.getUniform("otexture"), 2);
        GLES20.glUniform1f(r52.getUniform("preview"), 0.4f);
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(3553, this.originalBitmapTexture.texture());
    L27:
        if ((r1 instanceof Brush.Blurer) == false) goto L34;
        GLES20.glUniform1i(r52.getUniform("blured"), 2);
        GLES20.glActiveTexture(33986);
        BlurringShader.BlurManager r12 = this.blurManager;
        if (r12 == null) goto L31;
        Object r13 = r12.getTextureLock();
        GLES20.glBindTexture(3553, this.blurManager.getTexture());
    L35:
        GLES20.glBlendFunc(1, 771);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        if (r13 == null) goto L44;
        monitor-enter(r13);
        GLES20.glDrawArrays(5, 0, 4);     // Catch: Throwable -> L41
        monitor-exit(r13);     // Catch: Throwable -> L41
    L45:
        Utils.HasGLError();
        return;
    L41:
        th = move-exception;
        throw th;
    L44:
        GLES20.glDrawArrays(5, 0, 4);
        goto L45
    L31:
        Texture r14 = this.bluredTexture;
        if (r14 == null) goto L34;
        GLES20.glBindTexture(3553, r14.texture());
    L34:
        r13 = null;
        goto L35
    L19:
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L20
    L11:
        if ((r1 instanceof Brush.Radial) == false) goto L13;
    L14:
        r2 = true;
        goto L16
    L13:
        if ((r1 instanceof Brush.Eraser) == false) goto L15;
    }

    private void renderBlitShape(int r22, int r23, Shape r24, float r25) {
        if (r24 == null) goto L27;
        Brush r3 = this.brush;
        Brush.Shape r4 = r24.brush;
        if (r4 != null) goto L8;
    L10:
        if (r3 != null) goto L12;
        return;
    L12:
        if (this.renderView == null) goto L28;
        Shader r42 = this.shaders.get(r3.getShaderName(0));
        if (r42 == null) goto L29;
        GLES20.glUseProgram(r42.program);
        GLES20.glUniformMatrix4fv(r42.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(this.renderProjection));
        GLES20.glUniform1i(r42.getUniform("texture"), 0);
        GLES20.glUniform1i(r42.getUniform(PluginsConstants.Settings.MASK), 1);
        Shader.SetColorUniform(r42.getUniform("color"), ColorUtils.setAlphaComponent(this.renderView.getCurrentColor(), (int) (Color.alpha(r6) * r25)));
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r22);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, r23);
        if ((r3 instanceof Brush.Shape) == false) goto L24;
        GLES20.glUniform1i(r42.getUniform("type"), ((Brush.Shape) r3).getShapeShaderType());
        int r1 = r42.getUniform("resolution");
        Size r32 = this.size;
        GLES20.glUniform2f(r1, r32.width, r32.height);
        GLES20.glUniform2f(r42.getUniform("center"), r24.centerX, r24.centerY);
        GLES20.glUniform2f(r42.getUniform("radius"), r24.radiusX, r24.radiusY);
        GLES20.glUniform1f(r42.getUniform("thickness"), r24.thickness);
        GLES20.glUniform1f(r42.getUniform("rounding"), r24.rounding);
        GLES20.glUniform2f(r42.getUniform("middle"), r24.middleX, r24.middleY);
        GLES20.glUniform1f(r42.getUniform("rotation"), r24.rotation);
        GLES20.glUniform1i(r42.getUniform("fill"), r24.fill ? 1 : 0);
        GLES20.glUniform1f(r42.getUniform("arrowTriangleLength"), r24.arrowTriangleLength);
        GLES20.glUniform1i(r42.getUniform("composite"), 0);
        int r12 = r42.getUniform("clear");
        if (r24 != this.helperShape) goto L22;
        int r2 = 1;
    L23:
        GLES20.glUniform1i(r12, r2);
        goto L24
    L22:
        r2 = 0;
    L24:
        GLES20.glBlendFunc(1, 771);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        GLES20.glDrawArrays(5, 0, 4);
        Utils.HasGLError();
        return;
    L29:
        return;
    L28:
        return;
    L8:
        if (r22 != this.helperTexture) goto L10;
        r3 = r4;
        goto L10
    }

    private void renderBlur() {
        if (this.blurManager != null) goto L5;
        return;
    L5:
        if (this.bitmapBlurTexture != null) goto L7;
        return;
    L7:
        if (this.hasBlur == false) goto L31;
        GLES20.glBindFramebuffer(36160, 0);
        Shader r1 = this.shaders.get("videoBlur");
        if (r1 == null) goto L32;
        GLES20.glUseProgram(r1.program);
        GLES20.glUniformMatrix4fv(r1.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(this.renderProjection));
        GLES20.glUniform1f(r1.getUniform("flipy"), 0.0f);
        GLES20.glUniform1i(r1.getUniform("texture"), 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.bitmapBlurTexture.texture());
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glUniform1i(r1.getUniform("blured"), 1);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.blurManager.getTexture());
        if (this.activePath != null) goto L15;
    L17:
        GLES20.glUniform1f(r1.getUniform("eraser"), 0.0f);
    L18:
        GLES20.glBlendFunc(1, 0);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        Object r12 = this.blurManager.getTextureLock();
        monitor-enter(r12);
        GLES20.glDrawArrays(5, 0, 4);     // Catch: Throwable -> L24
        monitor-exit(r12);     // Catch: Throwable -> L24
        return;
    L24:
        th = move-exception;
        throw th;
    L15:
        if ((this.brush instanceof Brush.Eraser) == false) goto L17;
        GLES20.glUniform1f(r1.getUniform("eraser"), 1.0f);
        GLES20.glUniform1i(r1.getUniform(PluginsConstants.Settings.MASK), 2);
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(3553, getPaintTexture());
        goto L18
    L32:
        return;
    }

    private void restoreSlice(Slice r3) {
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda4(this, r3));
    }

    private void restoreSliceInternal(Slice r10, boolean r11) {
        if (r10 == null) goto L18;
        ByteBuffer r8 = r10.getData();
        int r0 = getTexture();
        if (r10.getTexture() != 1) goto L9;
        Texture r1 = this.bitmapBlurTexture;
        if (r1 == null) goto L9;
        r0 = r1.texture();
    L9:
        GLES20.glBindTexture(3553, r0);
        GLES20.glTexSubImage2D(3553, 0, r10.getX(), r10.getY(), r10.getWidth(), r10.getHeight(), 6408, 5121, r8);
        if (isSuppressingChanges() == true) goto L14;
        PaintingDelegate r9 = this.delegate;
        if (r9 == null) goto L14;
        r9.contentChanged();
    L14:
        if (r11 == false) goto L17;
        r10.cleanResources();
        return;
    L17:
        return;
    }

    public boolean applyHelperShape() {
        if (this.helperShape != null) goto L5;
        return false;
    L5:
        if (this.helperShown == true) goto L7;
        return false;
    L7:
        if (this.helperTexture == 0) goto L17;
        ValueAnimator r0 = this.helperApplyAnimator;
        if (r0 == null) goto L12;
        r0.cancel();
    L12:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.helperApplyAnimator = r02;
        r02.addUpdateListener(new Painting$$ExternalSyntheticLambda5(this));
        this.helperApplyAnimator.addListener(new AnonymousClass2(this));
        this.helperApplyAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.helperApplyAnimator.setDuration(350);
        this.helperApplyAnimator.start();
        BotWebViewVibrationEffect.IMPACT_RIGID.vibrate();
        return true;
    L17:
        return false;
    }

    public Painting asMask() {
        this.masking = true;
        return this;
    }

    public void cleanResources(boolean r5) {
        int r0 = this.reusableFramebuffer;
        if (r0 == 0) goto L5;
        int[] r3 = this.buffers;
        r3[0] = r0;
        GLES20.glDeleteFramebuffers(1, r3, 0);
        this.reusableFramebuffer = 0;
    L5:
        Texture r02 = this.bitmapTexture;
        if (r02 == null) goto L8;
        r02.cleanResources(r5);
    L8:
        Texture r03 = this.bitmapBlurTexture;
        if (r03 == null) goto L11;
        r03.cleanResources(r5);
    L11:
        int r52 = this.paintTexture;
        if (r52 == 0) goto L14;
        int[] r04 = this.buffers;
        r04[0] = r52;
        GLES20.glDeleteTextures(1, r04, 0);
        this.paintTexture = 0;
    L14:
        Iterator<Texture> r53 = this.brushTextures.values().iterator();
    L16:
        if (r53.hasNext() == false) goto L20;
        Texture r05 = r53.next();
        if (r05 == null) goto L16;
        r05.cleanResources(true);
        goto L16
    L20:
        this.brushTextures.clear();
        int r54 = this.helperTexture;
        if (r54 == 0) goto L23;
        int[] r06 = this.buffers;
        r06[0] = r54;
        GLES20.glDeleteTextures(1, r06, 0);
        this.helperTexture = 0;
    L23:
        Texture r55 = this.bluredTexture;
        if (r55 == null) goto L26;
        r55.cleanResources(true);
    L26:
        Texture r56 = this.originalBitmapTexture;
        if (r56 == null) goto L29;
        r56.cleanResources(true);
    L29:
        Map<String, Shader> r57 = this.shaders;
        if (r57 == null) goto L43;
        Iterator<Shader> r58 = r57.values().iterator();
    L33:
        if (r58.hasNext() == false) goto L35;
        r58.next().cleanResources();
        goto L33
    L35:
        this.shaders = null;
        return;
    }

    public void clearShape() {
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda2(this));
    }

    public void clearStroke() {
        clearStroke(null);
    }

    public void clearStroke(Runnable r3) {
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda9(this, r3));
    }

    public void commitPath(Path r3, int r4) {
        commitPath(r3, r4, true, null);
    }

    public void commitPath(Path r8, int r9, boolean r10, Runnable r11) {
        if (this.shaders != null) goto L5;
        return;
    L5:
        if (this.brush == null) goto L10;
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda6(this, r8, r9, r10, r11));
        return;
    }

    public void commitShape(Shape r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (this.shaders == null) goto L9;
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda0(this, r3, r4));
        return;
    }

    public RectF getBounds() {
        Size r3 = this.size;
        return new RectF(0.0f, 0.0f, r3.width, r3.height);
    }

    public PaintingData getPaintingData(RectF r34, boolean r35, boolean r36, boolean r37) {
        int r2 = (int) r34.left;
        int r3 = (int) r34.top;
        int r7 = (int) r34.width();
        int r8 = (int) r34.height();
        GLES20.glGenFramebuffers(1, this.buffers, 0);
        int r1 = this.buffers[0];
        GLES20.glBindFramebuffer(36160, r1);
        GLES20.glGenTextures(1, this.buffers, 0);
        int r5 = this.buffers[0];
        GLES20.glBindTexture(3553, r5);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9728);
        GLES20.glTexImage2D(3553, 0, 6408, r7, r8, 0, 6408, 5121, null);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, r5, 0);
        Size r52 = this.size;
        GLES20.glViewport(0, 0, (int) r52.width, (int) r52.height);
        Map<String, Shader> r53 = this.shaders;
        if (r53 != null) goto L5;
        return null;
    L5:
        if (r35 == false) goto L8;
        String r6 = "nonPremultipliedBlit";
    L11:
        Shader r54 = r53.get(r6);
        if (r54 != null) goto L14;
        return null;
    L14:
        GLES20.glUseProgram(r54.program);
        Matrix r62 = new Matrix();
        r62.preTranslate(-r2, -r3);
        float[] r22 = GLMatrix.MultiplyMat4f(this.projection, GLMatrix.LoadGraphicsMatrix(r62));
        GLES20.glUniformMatrix4fv(r54.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(r22));
        if (r35 == false) goto L17;
    L25:
        GLES20.glUniform1i(r54.getUniform("texture"), 0);
        GLES20.glActiveTexture(33984);
        if (r36 == false) goto L30;
        Texture r55 = this.bitmapBlurTexture;
        if (r55 == null) goto L30;
        int r56 = r55.texture();
    L31:
        GLES20.glBindTexture(3553, r56);
    L32:
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        GLES20.glBlendFunc(1, 0);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        GLES20.glDrawArrays(5, 0, 4);
        if (r37 == false) goto L47;
        if (r36 == true) goto L47;
        Shader r11 = this.shaders.get("videoBlur");
        if (r11 == null) goto L47;
        if (this.blurManager == null) goto L47;
        GLES20.glUseProgram(r11.program);
        GLES20.glUniformMatrix4fv(r11.getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(r22));
        GLES20.glUniform1f(r11.getUniform("flipy"), 0.0f);
        GLES20.glUniform1i(r11.getUniform("texture"), 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.bitmapBlurTexture.texture());
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glUniform1i(r11.getUniform("blured"), 1);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.blurManager.getTexture());
        GLES20.glUniform1f(r11.getUniform("eraser"), 0.0f);
        GLES20.glUniform1i(r11.getUniform(PluginsConstants.Settings.MASK), 2);
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(3553, getTexture());
        GLES20.glBlendFunc(1, 771);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        Object r12 = this.blurManager.getTextureLock();
        monitor-enter(r12);
        GLES20.glDrawArrays(5, 0, 4);     // Catch: Throwable -> L44
        monitor-exit(r12);     // Catch: Throwable -> L44
    L44:
        th = move-exception;
        throw th;
    L47:
        this.dataBuffer.limit((r7 * r8) * 4);
        GLES20.glReadPixels(0, 0, r7, r8, 6408, 5121, this.dataBuffer);
        if (r35 == false) goto L50;
        PaintingData r13 = new PaintingData(null, this.dataBuffer);
    L51:
        this.dataBuffer.rewind();
        int[] r23 = this.buffers;
        r23[0] = r1;
        GLES20.glDeleteFramebuffers(1, r23, 0);
        int[] r0 = this.buffers;
        r0[0] = r5;
        GLES20.glDeleteTextures(1, r0, 0);
        return r13;
    L50:
        Bitmap r14 = Bitmap.createBitmap(r7, r8, Bitmap.Config.ARGB_8888);
        r14.copyPixelsFromBuffer(this.dataBuffer);
        r13 = new PaintingData(r14, null);
    L30:
        r56 = getTexture();
        goto L31
    L17:
        if (this.masking == false) goto L25;
        GLES20.glUniform1i(r54.getUniform("texture"), 1);
        GLES20.glUniform1i(r54.getUniform(PluginsConstants.Settings.MASK), 0);
        GLES20.glUniform1f(r54.getUniform("preview"), 0.0f);
        GLES20.glActiveTexture(33984);
        if (r36 == false) goto L23;
        Texture r57 = this.bitmapBlurTexture;
        if (r57 == null) goto L23;
        int r58 = r57.texture();
    L24:
        GLES20.glBindTexture(3553, r58);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.originalBitmapTexture.texture());
    L23:
        r58 = getTexture();
        goto L24
    L8:
        if (this.masking == false) goto L10;
        r6 = "maskingBlit";
        goto L11
    L10:
        r6 = "blit";
        goto L11
    }

    public Size getSize() {
        return this.size;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void onPause(Runnable r3) {
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda8(this, r3));
    }

    public void onResume() {
        restoreSlice(this.backupSlice);
        this.backupSlice = null;
        this.paused = false;
    }

    public void paintShape(Shape r3, Runnable r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda1(this, r3, r4));
    }

    public void paintStroke(Path r8, boolean r9, boolean r10, Runnable r11) {
        if (this.helperApplyAnimator == null) goto L5;
        return;
    L5:
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda13(this, r8, r9, r10, r11));
    }

    public void render() {
        if (this.shaders != null) goto L6;
        return;
    L6:
        if (this.bitmapBlurTexture == null) goto L9;
        renderBlur();
    L9:
        if (this.activePath == null) goto L12;
        renderBlitPath(getPaintTexture(), this.activePath, (1.0f - (this.helperAlpha * 0.5f)) - (this.helperApplyAlpha * 0.5f));
    L15:
        int r0 = this.helperTexture;
        if (r0 != 0) goto L18;
        return;
    L18:
        if (this.helperShape != null) goto L20;
        return;
    L20:
        if (this.helperAlpha <= 0.0f) goto L25;
        renderBlitShape(r0, getPaintTexture(), this.helperShape, (this.helperAlpha * 0.5f) + (this.helperApplyAlpha * 0.5f));
        return;
    L25:
        return;
    L12:
        if (this.activeShape == null) goto L14;
        renderBlitShape(getTexture(), getPaintTexture(), this.activeShape, 1.0f);
        goto L15
    L14:
        renderBlit(getTexture(), 1.0f);
        goto L15
    }

    public void setBitmap(Bitmap r2, Bitmap r3) {
        if (this.bitmapTexture != null) goto L6;
        this.bitmapTexture = new Texture(r2);
    L6:
        if (this.bitmapBlurTexture != null) goto L9;
        this.bitmapBlurTexture = new Texture(r3);
    L9:
        if (this.masking == true) goto L11;
        return;
    L11:
        if (this.originalBitmapTexture != null) goto L15;
        this.originalBitmapTexture = new Texture(this.imageBitmap);
        return;
    }

    public void setBrush(Brush r8) {
        this.brush = r8;
        if ((r8 instanceof Brush.Blurer) == false) goto L38;
        Bitmap r82 = this.imageBitmap;
        if (r82 != null) goto L7;
        return;
    L7:
        if (this.blurManager != null) goto L40;
        int r83 = r82.getWidth();
        int r0 = this.imageBitmap.getHeight();
        int r1 = this.imageBitmapRotation;
        if (r1 == 90) goto L13;
        if (r1 == 270) goto L13;
        if (r1 == (-90)) goto L13;
    L15:
        if (this.bluredBitmap != null) goto L17;
        this.bluredBitmap = Bitmap.createBitmap((int) (r83 / 8.0f), (int) (r0 / 8.0f), Bitmap.Config.ARGB_8888);
    L17:
        Canvas r12 = new Canvas(this.bluredBitmap);
        r12.save();
        r12.scale(0.125f, 0.125f);
        if (this.imageBitmapPaint == null) goto L20;
        this.imageBitmapPaint = new Paint(1);
    L20:
        r12.save();
        r12.rotate(this.imageBitmapRotation);
        int r4 = this.imageBitmapRotation;
        if (r4 != 90) goto L24;
        r12.translate(0.0f, -r83);
    L28:
        r12.drawBitmap(this.imageBitmap, 0.0f, 0.0f, this.imageBitmapPaint);
        r12.restore();
        RenderView r2 = this.renderView;
        if (r2 == null) goto L33;
        Bitmap r22 = r2.getResultBitmap(false, false);
        if (r22 == null) goto L33;
        r12.scale(r83 / r22.getWidth(), r0 / r22.getHeight());
        r12.drawBitmap(r22, 0.0f, 0.0f, this.imageBitmapPaint);
        r22.recycle();
    L33:
        Utilities.stackBlurBitmap(this.bluredBitmap, 8);
        Texture r84 = this.bluredTexture;
        if (r84 == null) goto L36;
        r84.cleanResources(false);
    L36:
        this.bluredTexture = new Texture(this.bluredBitmap);
        return;
    L24:
        if (r4 != 180) goto L26;
        r12.translate(-r83, -r0);
        goto L28
    L26:
        if (r4 != 270) goto L28;
        r12.translate(-r0, 0.0f);
    L13:
        r0 = r83;
        r83 = r0;
        goto L15
    L40:
        return;
    }

    public void setDelegate(PaintingDelegate r1) {
        this.delegate = r1;
    }

    public void setHelperShape(Shape r3) {
        if (this.helperApplyAnimator == null) goto L5;
        return;
    L5:
        this.renderView.performInContext(new Painting$$ExternalSyntheticLambda3(this, r3));
    }

    public void setRenderProjection(float[] r1) {
        this.renderProjection = r1;
    }

    public void setRenderView(RenderView r1) {
        this.renderView = r1;
    }

    public void setupShaders() {
        this.shaders = ShaderSet.setup();
    }
}
