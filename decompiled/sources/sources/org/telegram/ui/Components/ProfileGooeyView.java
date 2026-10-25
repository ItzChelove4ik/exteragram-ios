package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RecordingCanvas;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotchInfoUtils;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraView$$ExternalSyntheticApiModelOutline0;

/* loaded from: classes7.dex */
public class ProfileGooeyView extends FrameLayout {
    private final Paint blackPaint;
    private float blurIntensity;
    private boolean enabled;
    private final Impl impl;
    private float intensity;
    public NotchInfoUtils.NotchInfo notchInfo;
    private final Path path;
    private float pullProgress;

    public final class CPUImpl implements Impl {
        private Bitmap bitmap;
        private Canvas bitmapCanvas;
        private int bitmapOrigH;
        private int bitmapOrigW;
        private final Paint bitmapPaint;
        private final Paint bitmapPaint2;
        private int optimizedH;
        private int optimizedW;
        private final float scaleConst;
        final /* synthetic */ ProfileGooeyView this$0;

        private CPUImpl(ProfileGooeyView r3) {
            this.this$0 = r3;
            Paint r32 = new Paint();
            this.bitmapPaint = r32;
            Paint r0 = new Paint();
            this.bitmapPaint2 = r0;
            this.scaleConst = 6.0f;
            r32.setFlags(7);
            r32.setFilterBitmap(true);
            r0.setFlags(7);
            r0.setFilterBitmap(true);
            r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
            r32.setColorFilter(new ColorMatrixColorFilter(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 60.0f, -7500.0f}));
        }

        public /* synthetic */ CPUImpl(ProfileGooeyView r1, ProfileGooeyViewIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.Components.ProfileGooeyView.Impl
        public void draw(Drawer r19, Canvas r20) {
            Bitmap r2 = this.bitmap;
            if (r2 != null) goto L5;
            return;
        L5:
            if (r2.isRecycled() == true) goto L32;
            int r7 = (int) ((1.0f - ((MathUtils.clamp(ProfileGooeyView.m12250$$Nest$fgetblurIntensity(this.this$0), 0.2f, 0.3f) - 0.2f) / 0.10000001f)) * 255.0f);
            float r8 = (this.this$0.getWidth() - this.optimizedW) / 2.0f;
            r20.save();
            r20.translate(0.0f, -AndroidUtilities.dp(32.0f));
            if (r7 == 255) goto L22;
            this.bitmap.eraseColor(0);
            this.bitmapCanvas.save();
            this.bitmapCanvas.scale(this.bitmap.getWidth() / this.bitmapOrigW, this.bitmap.getHeight() / this.bitmapOrigH);
            float r5 = -r8;
            this.bitmapCanvas.translate(r5, 0.0f);
            r19.draw(this.bitmapCanvas);
            this.bitmapCanvas.restore();
            this.bitmapCanvas.save();
            this.bitmapCanvas.scale(this.bitmap.getWidth() / this.bitmapOrigW, this.bitmap.getHeight() / this.bitmapOrigH);
            NotchInfoUtils.NotchInfo r4 = this.this$0.notchInfo;
            Canvas r12 = this.bitmapCanvas;
            if (r4 == null) goto L19;
            r12.save();
            this.bitmapCanvas.translate(r5, AndroidUtilities.dp(32.0f));
            ProfileGooeyView r22 = this.this$0;
            NotchInfoUtils.NotchInfo r42 = r22.notchInfo;
            if (r42.isLikelyCircle == false) goto L15;
            float r23 = Math.min(r42.bounds.width(), this.this$0.notchInfo.bounds.height()) / 2.0f;
            Canvas r43 = this.bitmapCanvas;
            float r52 = this.this$0.notchInfo.bounds.centerX();
            RectF r6 = this.this$0.notchInfo.bounds;
            r43.drawCircle(r52, r6.bottom - (r6.width() / 2.0f), r23, ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
        L18:
            this.bitmapCanvas.restore();
        L20:
            this.bitmapCanvas.restore();
            Utilities.stackBlurBitmap(this.bitmap, (int) ((ProfileGooeyView.m12251$$Nest$fgetintensity(this.this$0) * 2.0f) / 6.0f));
            r20.save();
            r20.translate(r8, 0.0f);
            r20.saveLayer(0.0f, 0.0f, this.bitmapOrigW, this.bitmapOrigH, null);
            r20.scale(this.bitmapOrigW / this.bitmap.getWidth(), this.bitmapOrigH / this.bitmap.getHeight());
            r20.drawBitmap(this.bitmap, 0.0f, 0.0f, this.bitmapPaint);
            r20.drawBitmap(this.bitmap, 0.0f, 0.0f, this.bitmapPaint2);
            r20.restore();
            r20.restore();
            goto L22
        L15:
            if (r42.isAccurate == false) goto L17;
            this.bitmapCanvas.drawPath(r42.path, ProfileGooeyView.m12249$$Nest$fgetblackPaint(r22));
            goto L18
        L17:
            float r24 = Math.max(r42.bounds.width(), this.this$0.notchInfo.bounds.height()) / 2.0f;
            Canvas r44 = this.bitmapCanvas;
            ProfileGooeyView r53 = this.this$0;
            r44.drawRoundRect(r53.notchInfo.bounds, r24, r24, ProfileGooeyView.m12249$$Nest$fgetblackPaint(r53));
            goto L18
        L19:
            r12.drawRect(0.0f, 0.0f, this.optimizedW, AndroidUtilities.dp(32.0f), ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
        L22:
            if (r7 == 0) goto L29;
            if (r7 == 255) goto L25;
            int r54 = r7;
            r20.saveLayerAlpha(r8, 0.0f, r8 + this.optimizedW, this.optimizedH, r54);
        L26:
            r19.draw(r20);
            if (r54 == 255) goto L29;
            r20.restore();
            goto L29
        L25:
            r54 = r7;
        L29:
            r20.restore();
            return;
        }

        @Override // org.telegram.ui.Components.ProfileGooeyView.Impl
        public void onSizeChanged(int r2, int r3) {
            Bitmap r0 = this.bitmap;
            if (r0 == null) goto L5;
            r0.recycle();
            this.bitmap = null;
        L5:
            this.optimizedW = Math.min(AndroidUtilities.dp(120.0f), r2);
            int r22 = Math.min(AndroidUtilities.dp(220.0f), r3);
            this.optimizedH = r22;
            this.bitmapOrigW = this.optimizedW;
            int r23 = r22 + AndroidUtilities.dp(32.0f);
            this.bitmapOrigH = r23;
            this.bitmap = Bitmap.createBitmap((int) (this.bitmapOrigW / 6.0f), (int) (r23 / 6.0f), Bitmap.Config.ARGB_8888);
            this.bitmapCanvas = new Canvas(this.bitmap);
        }
    }

    public interface Drawer {
        void draw(Canvas r1);
    }

    public final class GPUImpl implements Impl {
        private final Paint blackNodePaint;
        private final RenderNode blurNode;
        private final RenderNode effectNode;
        private final RenderNode effectNotchNode;
        private final float factorMult;
        private final Paint filter;
        private final RenderNode node;
        private final RectF temp;
        final /* synthetic */ ProfileGooeyView this$0;
        private final RectF whole;
        private final RectF wholeOptimized;

        private GPUImpl(ProfileGooeyView r2, float r3) {
            this.this$0 = r2;
            this.filter = new Paint(1);
            this.node = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("render");
            this.effectNotchNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("effectNotch");
            this.effectNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("effect");
            this.blurNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("blur");
            this.whole = new RectF();
            this.temp = new RectF();
            Paint r22 = new Paint();
            this.blackNodePaint = r22;
            this.wholeOptimized = new RectF();
            this.factorMult = r3;
            r22.setColor(-16777216);
            r22.setBlendMode(ProfileGooeyView$GPUImpl$$ExternalSyntheticApiModelOutline0.m());
        }

        public /* synthetic */ GPUImpl(ProfileGooeyView r1, float r2, ProfileGooeyViewIA r3) {
            this(r1, r2);
        }

        @Override // org.telegram.ui.Components.ProfileGooeyView.Impl
        public void draw(Drawer r28, Canvas r29) {
            if (r29.isHardwareAccelerated() == true) goto L5;
            return;
        L5:
            this.whole.set(0.0f, 0.0f, this.this$0.getWidth(), this.this$0.getHeight());
            if (this.this$0.getChildCount() <= 0) goto L11;
            View r2 = this.this$0.getChildAt(0);
            float r4 = r2.getWidth() * r2.getScaleX();
            float r6 = r2.getHeight() * r2.getScaleY();
            float r7 = r2.getX();
            float r22 = r2.getY();
            this.wholeOptimized.set(r7, r22, r4 + r7, r6 + r22);
            NotchInfoUtils.NotchInfo r23 = this.this$0.notchInfo;
            if (r23 == null) goto L10;
            this.wholeOptimized.union(r23.bounds);
        L10:
            this.wholeOptimized.inset(-AndroidUtilities.dp(20.0f), -AndroidUtilities.dp(20.0f));
            this.wholeOptimized.intersect(this.whole);
            this.wholeOptimized.top = 0.0f;
        L12:
            this.wholeOptimized.bottom += AndroidUtilities.dp(32.0f);
            int r24 = (int) Math.ceil(this.wholeOptimized.width());
            int r42 = (int) Math.ceil(this.wholeOptimized.height());
            RectF r72 = this.wholeOptimized;
            float r8 = r72.left;
            float r73 = r72.top;
            this.node.setPosition(0, 0, r24, r42);
            this.blurNode.setPosition(0, 0, r24, r42);
            this.effectNode.setPosition(0, 0, r24, r42);
            this.effectNotchNode.setPosition(0, 0, r24, r42);
            float r13 = r24;
            float r25 = r42;
            this.wholeOptimized.set(0.0f, 0.0f, r13, r25);
            RecordingCanvas r43 = this.node.beginRecording();
            float r9 = -r8;
            float r10 = -r73;
            r43.translate(r9, r10);
            int r11 = (int) ((1.0f - AndroidUtilities.ilerp(ProfileGooeyView.m12253$$Nest$fgetpullProgress(this.this$0), 0.5f, 1.0f)) * 255.0f);
            int r62 = MathUtils.clamp(r11, 0, 255);
            r28.draw(r43);
            this.node.endRecording();
            float r44 = (this.factorMult / 4.0f) + 1.0f;
            float r14 = ProfileGooeyView.m12250$$Nest$fgetblurIntensity(this.this$0) * 0.5f;
            float r3 = this.factorMult;
            float r45 = (r44 + (r14 * r3)) + ((r3 - 1.0f) * 2.0f);
            RecordingCanvas r32 = this.blurNode.beginRecording();
            float r142 = 1.0f / r45;
            r32.scale(r142, r142, 0.0f, 0.0f);
            r32.drawRenderNode(this.node);
            this.blurNode.endRecording();
            float r33 = this.factorMult + 2.0f;
            RecordingCanvas r143 = this.effectNode.beginRecording();
            float r12 = 1.0f / r33;
            r143.scale(r12, r12, 0.0f, 0.0f);
            if (r62 >= 255) goto L15;
            r143.saveLayer(this.wholeOptimized, null);
            r143.drawRenderNode(this.node);
            r143.drawRect(this.wholeOptimized, this.blackNodePaint);
            r143.restore();
        L15:
            float r74 = AndroidUtilities.lerp(0.0f, AndroidUtilities.dp(7.0f) * r33, 0.0f, 0.5f, ProfileGooeyView.m12253$$Nest$fgetpullProgress(this.this$0));
            if (this.this$0.getChildCount() <= 0) goto L18;
            View r112 = this.this$0.getChildAt(0);
            float r15 = (r112.getX() + ((r112.getWidth() * r112.getScaleX()) / 2.0f)) - r8;
            float r5 = ((r112.getY() + ((r112.getHeight() * r112.getScaleY()) / 2.0f)) + AndroidUtilities.dp(32.0f)) - r73;
            float r52 = (r112.getWidth() / 2.0f) * r112.getScaleX();
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).rewind();
            float r26 = r33;
            float r252 = r45;
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).moveTo(r15 - r52, r5 - (((float) Math.cos(0.7853981633974483d)) * r52));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo(r15, (r5 - r52) - (0.25f * r74));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo(r15 + r52, r5 - (((float) Math.cos(0.7853981633974483d)) * r52));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).close();
            r143.drawPath(ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0), ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
        L20:
            if (r62 <= 0) goto L26;
            if (r62 == 255) goto L23;
            r143.saveLayerAlpha(this.wholeOptimized, r62);
        L23:
            r143.drawRenderNode(this.node);
            if (r62 == 255) goto L26;
            r143.restore();
        L26:
            this.effectNode.endRecording();
            RecordingCanvas r46 = this.effectNotchNode.beginRecording();
            r46.scale(r12, r12, 0.0f, 0.0f);
            if (this.this$0.notchInfo == null) goto L36;
            r46.translate(r9, r10);
            r46.translate(0.0f, AndroidUtilities.dp(32.0f));
            ProfileGooeyView r53 = this.this$0;
            NotchInfoUtils.NotchInfo r63 = r53.notchInfo;
            if (r63.isLikelyCircle == false) goto L33;
            float r54 = Math.min(r63.bounds.width(), this.this$0.notchInfo.bounds.height()) / 2.0f;
            RectF r64 = this.this$0.notchInfo.bounds;
            float r92 = r64.bottom - (r64.width() / 2.0f);
            r46.drawCircle(this.this$0.notchInfo.bounds.centerX(), r92, r54, ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).rewind();
            float r113 = r74 / 2.0f;
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).moveTo(this.this$0.notchInfo.bounds.centerX() - r113, r92);
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo(this.this$0.notchInfo.bounds.centerX(), (r54 + r92) + r74);
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo(this.this$0.notchInfo.bounds.centerX() + r113, r92);
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).close();
            r46.drawPath(ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0), ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
        L31:
            int r47 = 255;
            Paint r34 = null;
        L37:
            this.effectNotchNode.endRecording();
            r29.save();
            r29.translate(r8, r73 - AndroidUtilities.dp(32.0f));
            NotchInfoUtils.NotchInfo r55 = this.this$0.notchInfo;
            if (r55 == null) goto L40;
            r29.clipRect(0.0f, r55.bounds.top, r13, r25);
        L40:
            r29.saveLayer(this.wholeOptimized, this.filter);
            float r27 = r26;
            r29.scale(r27, r27);
            r29.drawRenderNode(this.effectNotchNode);
            r29.drawRenderNode(this.effectNode);
            r29.restore();
            int r210 = MathUtils.clamp((r11 * 3) / 4, 0, r47);
            if (r210 >= r47) goto L47;
            r29.saveLayer(this.wholeOptimized, r34);
            if (ProfileGooeyView.m12250$$Nest$fgetblurIntensity(this.this$0) == 0.0f) goto L45;
            r29.saveLayer(this.wholeOptimized, this.filter);
            float r35 = r252;
            r29.scale(r35, r35);
            r29.drawRenderNode(this.blurNode);
            r29.restore();
        L46:
            r29.drawRect(this.wholeOptimized, this.blackNodePaint);
            r29.restore();
        L48:
            if (r210 <= 0) goto L57;
            if (r210 == r47) goto L52;
            r29.saveLayerAlpha(this.wholeOptimized, r210);
        L52:
            if (ProfileGooeyView.m12250$$Nest$fgetblurIntensity(this.this$0) == 0.0f) goto L54;
            r29.saveLayer(this.wholeOptimized, this.filter);
            r29.scale(r35, r35);
            r29.drawRenderNode(this.blurNode);
            r29.restore();
        L55:
            if (r210 == r47) goto L57;
            r29.restore();
            goto L57
        L54:
            r29.drawRenderNode(this.node);
        L57:
            r29.restore();
            return;
        L45:
            r35 = r252;
            r29.drawRenderNode(this.node);
            goto L46
        L47:
            r35 = r252;
            goto L48
        L33:
            if (r63.isAccurate == false) goto L35;
            r46.drawPath(r63.path, ProfileGooeyView.m12249$$Nest$fgetblackPaint(r53));
            goto L31
        L35:
            float r56 = Math.max(r63.bounds.width(), this.this$0.notchInfo.bounds.height()) / 2.0f;
            this.temp.set(this.this$0.notchInfo.bounds);
            r46.drawRoundRect(this.temp, r56, r56, ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).rewind();
            float r93 = r74 / 2.0f;
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).moveTo(this.temp.centerX() - r93, this.temp.bottom);
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo(this.temp.centerX(), this.temp.bottom + r74);
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo(this.temp.centerX() + r93, this.temp.bottom);
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).close();
            r46.drawPath(ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0), ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
            goto L31
        L36:
            r47 = 255;
            r34 = null;
            r46.drawRect(0.0f, 0.0f, r13, AndroidUtilities.dp(32.0f), ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).rewind();
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).moveTo((r13 - r74) / 2.0f, AndroidUtilities.dp(32.0f));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo(r13 / 2.0f, AndroidUtilities.dp(32.0f) + r74);
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).lineTo((r13 + r74) / 2.0f, AndroidUtilities.dp(32.0f));
            ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0).close();
            r46.drawPath(ProfileGooeyView.m12252$$Nest$fgetpath(this.this$0), ProfileGooeyView.m12249$$Nest$fgetblackPaint(this.this$0));
            goto L37
        L18:
            r26 = r33;
            r252 = r45;
            goto L20
        L11:
            this.wholeOptimized.set(this.whole);
            goto L12
        }

        @Override // org.telegram.ui.Components.ProfileGooeyView.Impl
        public void setBlurIntensity(float r4) {
            RenderNode r1 = this.blurNode;
            if (r4 != 0.0f) goto L6;
            r1.setRenderEffect(null);
            return;
        L6:
            r1.setRenderEffect(RenderEffect.createBlurEffect((ProfileGooeyView.m12251$$Nest$fgetintensity(this.this$0) * r4) / this.factorMult, (r4 * ProfileGooeyView.m12251$$Nest$fgetintensity(this.this$0)) / this.factorMult, CameraView$$ExternalSyntheticApiModelOutline0.m()));
        }

        @Override // org.telegram.ui.Components.ProfileGooeyView.Impl
        public void setIntensity(float r4) {
            RenderNode r0 = this.effectNode;
            Shader.TileMode r1 = Shader.TileMode.CLAMP;
            r0.setRenderEffect(RenderEffect.createBlurEffect(r4, r4, r1));
            this.effectNotchNode.setRenderEffect(RenderEffect.createBlurEffect(r4, r4, r1));
            this.filter.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 51.0f, -6375.0f}));
        }
    }

    public interface Impl {
        void draw(Drawer r1, Canvas r2);

        default void onSizeChanged(int r1, int r2) {
        }

        default void setBlurIntensity(float r1) {
        }

        default void setIntensity(float r1) {
        }
    }

    /* renamed from: $r8$lambda$x9L-nII8ep_QOU34-EA_UHpS5-4, reason: not valid java name */
    public static /* synthetic */ void m12248$r8$lambda$x9LnII8ep_QOU34EA_UHpS54(ProfileGooeyView r0, Canvas r1) {
        r0.lambda$draw$0(r1);
    }

    /* renamed from: -$$Nest$fgetblackPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12249$$Nest$fgetblackPaint(ProfileGooeyView r0) {
        return r0.blackPaint;
    }

    /* renamed from: -$$Nest$fgetblurIntensity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12250$$Nest$fgetblurIntensity(ProfileGooeyView r0) {
        return r0.blurIntensity;
    }

    /* renamed from: -$$Nest$fgetintensity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12251$$Nest$fgetintensity(ProfileGooeyView r0) {
        return r0.intensity;
    }

    /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m12252$$Nest$fgetpath(ProfileGooeyView r0) {
        return r0.path;
    }

    /* renamed from: -$$Nest$fgetpullProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12253$$Nest$fgetpullProgress(ProfileGooeyView r0) {
        return r0.pullProgress;
    }

    public ProfileGooeyView(Context r4) {
        super(r4);
        Paint r42 = new Paint(1);
        this.blackPaint = r42;
        this.path = new Path();
        r42.setColor(-16777216);
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L11:
        this.impl = new CPUImpl(this, null);
    L12:
        setIntensity(15.0f);
        setBlurIntensity(0.0f);
        setWillNotDraw(false);
        return;
    L5:
        if (SharedConfig.getDevicePerformanceClass() < 1) goto L11;
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L9;
        float r0 = 1.0f;
    L10:
        this.impl = new GPUImpl(this, r0, null);
        goto L12
    L9:
        r0 = 1.5f;
        goto L10
    }

    private /* synthetic */ void lambda$draw$0(Canvas r3) {
        r3.save();
        r3.translate(0.0f, AndroidUtilities.dp(32.0f));
        super.draw(r3);
        r3.restore();
    }

    @Override // android.view.View
    public void draw(Canvas r3) {
        if (this.enabled == true) goto L6;
        super.draw(r3);
        return;
    L6:
        this.impl.draw(new ProfileGooeyView$$ExternalSyntheticLambda0(this), r3);
    }

    public float getAvatarEndScale() {
        NotchInfoUtils.NotchInfo r0 = this.notchInfo;
        if (r0 != null) goto L5;
        return 0.8f;
    L5:
        if (r0.isLikelyCircle == false) goto L8;
        float r4 = r0.bounds.width() - AndroidUtilities.dp(2.0f);
        int r02 = AndroidUtilities.dp(100.0f);
    L10:
        return Math.min(0.8f, r4 / r02);
    L8:
        r4 = Math.min(r0.bounds.width(), this.notchInfo.bounds.height());
        r02 = AndroidUtilities.dp(100.0f);
        goto L10
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        NotchInfoUtils.NotchInfo r32 = NotchInfoUtils.getInfo(getContext());
        this.notchInfo = r32;
        if (r32 == null) goto L7;
        if (r32.gravity == 17) goto L7;
    L8:
        this.notchInfo = null;
    L9:
        this.impl.onSizeChanged(r1, r2);
        return;
    L7:
        if (getWidth() <= getHeight()) goto L9;
        goto L8
    }

    public void setBlurIntensity(float r2) {
        this.blurIntensity = r2;
        this.impl.setBlurIntensity(r2);
        invalidate();
    }

    public void setGooeyEnabled(boolean r2) {
        if (this.enabled != r2) goto L5;
        return;
    L5:
        this.enabled = r2;
        invalidate();
    }

    public void setIntensity(float r2) {
        this.intensity = r2;
        this.impl.setIntensity(r2);
        invalidate();
    }

    public void setPullProgress(float r1) {
        this.pullProgress = r1;
        invalidate();
    }
}
