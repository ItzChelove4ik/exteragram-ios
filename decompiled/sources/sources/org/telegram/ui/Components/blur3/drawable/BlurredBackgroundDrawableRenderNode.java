package org.telegram.ui.Components.blur3.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.os.Build;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.GlassOutlineStyle;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.LiquidGlassEffect;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;

/* loaded from: classes3.dex */
public class BlurredBackgroundDrawableRenderNode extends BlurredBackgroundDrawable {
    private LiquidGlassEffect liquidGlassEffect;
    private final Outline outline;
    private final Rect outlineRect;
    private final Paint paintShadow;
    private final Paint paintStrokeBottom;
    private final Paint paintStrokeFull;
    private final Paint paintStrokeTop;
    private final RenderNode renderNode;
    private final RenderNode renderNodeFill;
    private boolean renderNodeInvalidated;
    private final BlurredBackgroundSource source;

    public BlurredBackgroundDrawableRenderNode(BlurredBackgroundSource r8) {
        this.outline = new Outline();
        this.outlineRect = new Rect();
        Paint r0 = new Paint(1);
        this.paintShadow = r0;
        Paint r2 = new Paint(1);
        this.paintStrokeTop = r2;
        Paint r3 = new Paint(1);
        this.paintStrokeBottom = r3;
        Paint r4 = new Paint(1);
        this.paintStrokeFull = r4;
        RenderNode r5 = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("BlurredNode");
        this.renderNode = r5;
        this.renderNodeFill = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("BlurredFill");
        r5.setClipToOutline(true);
        r5.setClipToBounds(true);
        updateFillCompositingLayer();
        this.source = r8;
        r0.setColor(0);
        Paint.Style r7 = Paint.Style.STROKE;
        r2.setStyle(r7);
        r3.setStyle(r7);
        r4.setStyle(Paint.Style.FILL);
    }

    private void updateFillCompositingLayer() {
        RenderNode r2 = this.renderNodeFill;
        if (ExteraConfig.getGlassOutlineStyle() != GlassOutlineStyle.HIDDEN) goto L5;
        boolean r0 = true;
    L6:
        r2.setUseCompositingLayer(r0, null);
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r6) {
        if (this.boundProps.boundsWithPadding.isEmpty() == false) goto L6;
        return;
    L6:
        if (r6.isHardwareAccelerated() == true) goto L10;
        drawSource(r6, this.source);
        return;
    L10:
        if (this.renderNode.hasDisplayList() == true) goto L13;
        this.source.dispatchOnDrawablesRelativePositionChange();
        updateDisplayList();
    L15:
        this.renderNodeInvalidated = false;
        int r0 = Theme.multAlpha(this.shadowColor, this.renderNode.getAlpha() * this.shadowAlpha);
        if (this.shadowLayerRadius > 0.0f) goto L18;
    L20:
        r6.save();
        Rect r02 = this.boundProps.boundsWithPadding;
        r6.translate(r02.left, r02.top);
        r6.drawRenderNode(this.renderNode);
        r6.restore();
        return;
    L18:
        if (Color.alpha(r0) == 0) goto L20;
        this.paintShadow.setShadowLayer(this.shadowLayerRadius, this.shadowLayerDx, this.shadowLayerDy, r0);
        this.boundProps.drawShadows(r6, this.paintShadow, this.inAppKeyboardOptimization);
        goto L20
    L13:
        if (this.renderNodeInvalidated == false) goto L15;
        updateDisplayList();
        goto L15
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public BlurredBackgroundSource getSource() {
        return this.source;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public boolean hasDisplayList() {
        return this.renderNode.hasDisplayList();
    }

    public void invalidateDisplayList() {
        this.renderNodeInvalidated = true;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public void onBoundPropsChanged() {
        super.onBoundPropsChanged();
        BlurredBackgroundDrawable.Props r0 = this.boundProps;
        boolean r1 = r0.useFullStroke;
        Paint r2 = this.paintStrokeTop;
        if (r1 == false) goto L5;
        r2.setStrokeWidth(r0.strokeWidthFull);
    L6:
        this.outlineRect.set(0, 0, this.boundProps.boundsWithPadding.width(), this.boundProps.boundsWithPadding.height());
        BlurredBackgroundDrawable.getOutline(this.outline, this.outlineRect, this.boundProps.radii);
        this.outline.setAlpha(1.0f);
        if (this.boundProps.boundsWithPadding.isEmpty() == true) goto L10;
        this.renderNodeFill.setPosition(0, 0, this.boundProps.boundsWithPadding.width(), this.boundProps.boundsWithPadding.height());
        this.renderNode.setPosition(0, 0, this.boundProps.boundsWithPadding.width(), this.boundProps.boundsWithPadding.height());
        this.renderNode.setOutline(this.outline);
        this.renderNodeInvalidated = true;
        return;
    L10:
        return;
    L5:
        r2.setStrokeWidth(r0.strokeWidthTop);
        this.paintStrokeBottom.setStrokeWidth(this.boundProps.strokeWidthBottom);
        goto L6
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public void onSourceOffsetChange(float r1, float r2) {
        super.onSourceOffsetChange(r1, r2);
        this.renderNodeInvalidated = true;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public void onSourceRelativePositionChanged(RectF r1) {
        super.onSourceRelativePositionChanged(r1);
        this.source.dispatchOnDrawablesRelativePositionChange();
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int r5) {
        int r0 = getAlpha();
        super.setAlpha(r5);
        this.renderNode.setAlpha(r5 / 255.0f);
        this.renderNodeInvalidated = true;
        if (r0 != 0) goto L7;
        if (r5 <= 0) goto L8;
        this.source.dispatchOnDrawablesRelativePositionChange();
        return;
    L8:
        return;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public BlurredBackgroundDrawable setClipToOutline(boolean r2) {
        this.renderNode.setClipToOutline(r2);
        return super.setClipToOutline(r2);
    }

    public void setLiquidGlassEffectAllowed() {
        this.liquidGlassEffect = new LiquidGlassEffect(this.renderNodeFill);
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public void updateColors() {
        super.updateColors();
        updateFillCompositingLayer();
        this.paintShadow.setShadowLayer(this.shadowLayerRadius, this.shadowLayerDx, this.shadowLayerDy, this.shadowColor);
        if (this.boundProps.useFullStroke == false) goto L5;
        this.paintStrokeFull.setColor(this.strokeColorFull);
    L6:
        this.renderNodeInvalidated = true;
        return;
    L5:
        this.paintStrokeTop.setColor(this.strokeColorTop);
        this.paintStrokeBottom.setColor(this.strokeColorBottom);
        goto L6
    }

    @Override // org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable
    public void updateDisplayList() {
        float r1 = this.sourceOffsetX;
        float r2 = this.sourceOffsetY;
        Rect r3 = this.boundProps.boundsWithPadding;
        float r7 = r3.left + r1;
        float r8 = r3.top + r2;
        float r9 = r3.right + r1;
        float r10 = r3.bottom + r2;
        RecordingCanvas r6 = this.renderNodeFill.beginRecording();
        r6.save();
        r6.translate(-r7, -r8);
        if (this.liquidGlassEffect != null) goto L5;
    L10:
        this.source.draw(r6, r7, r8, r9, r10);
        r6.restore();
        if (this.liquidGlassEffect == null) goto L13;
    L15:
        this.renderNodeFill.endRecording();
        RecordingCanvas r22 = this.renderNode.beginRecording();
        if (Color.alpha(this.backgroundColor) != 255) goto L18;
        r22.drawColor(this.backgroundColor);
    L20:
        if (this.boundProps.useFullStroke == false) goto L25;
        if (Color.alpha(this.strokeColorFull) == 0) goto L30;
        r22.save();
        Rect r12 = this.boundProps.boundsWithPadding;
        r22.translate(-r12.left, -r12.top);
        r22.drawPath(this.boundProps.strokePathTop, this.paintStrokeFull);
        r22.restore();
    L30:
        this.renderNode.endRecording();
        return;
    L25:
        if (Color.alpha(this.strokeColorTop) == 0) goto L28;
        float r5 = this.boundProps.boundsWithPadding.width();
        float r62 = this.boundProps.boundsWithPadding.height();
        BlurredBackgroundDrawable.Props r13 = this.boundProps;
        BlurredBackgroundDrawable.drawStroke(r22, 0.0f, 0.0f, r5, r62, r13.radii, r13.strokeWidthTop, true, this.paintStrokeTop);
    L28:
        if (Color.alpha(this.strokeColorBottom) == 0) goto L30;
        float r52 = this.boundProps.boundsWithPadding.width();
        float r63 = this.boundProps.boundsWithPadding.height();
        BlurredBackgroundDrawable.Props r14 = this.boundProps;
        BlurredBackgroundDrawable.drawStroke(r22, 0.0f, 0.0f, r52, r63, r14.radii, r14.strokeWidthBottom, false, this.paintStrokeBottom);
        goto L30
    L18:
        r22.drawRenderNode(this.renderNodeFill);
        goto L20
    L13:
        if (Color.alpha(this.backgroundColor) == 0) goto L15;
        r6.drawColor(this.backgroundColor);
        goto L15
    L5:
        if (Build.VERSION.SDK_INT < 33) goto L10;
        int r15 = this.boundProps.liquidThickness;
        if (r15 > 0) goto L9;
        r15 = AndroidUtilities.dp(11.0f);
    L9:
        int r16 = Math.max(Math.min(r15, Math.min(this.boundProps.boundsWithPadding.width(), this.boundProps.boundsWithPadding.height()) / 5), 1);
        LiquidGlassEffect r11 = this.liquidGlassEffect;
        float r142 = this.boundProps.boundsWithPadding.width();
        float r152 = this.boundProps.boundsWithPadding.height();
        BlurredBackgroundDrawable.Props r23 = this.boundProps;
        float[] r32 = r23.shaderRadii;
        r11.update(0.0f, 0.0f, r142, r152, r32[0], r32[2], r32[4], r32[6], r16, r23.liquidIntensity, r23.liquidIndex, this.backgroundColor);
        goto L10
    }
}
