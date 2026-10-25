package org.telegram.ui.Components.blur3.source;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.os.Build;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import java.util.Iterator;
import java.util.List;
import me.vkryl.core.reference.ReferenceList;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.RenderNodeWithHash;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableRenderNode;

/* loaded from: classes3.dex */
public class BlurredBackgroundSourceRenderNode implements BlurredBackgroundSource {
    private final ReferenceList<BlurredBackgroundDrawableRenderNode> drawables;
    private final BlurredBackgroundSource fallbackSource;
    private boolean inRecording;
    private boolean noClip;
    private Runnable onDrawablesRelativePositionChangeListener;
    private RecordingCanvas recordingCanvas;
    private final RenderNode renderNode;
    private RenderNodeWithHash renderNodeWithHash;
    private DownscaleScrollableNoiseSuppressor scrollableNoiseSuppressor;
    private int scrollableNoiseSuppressorIndex;
    public BlurredBackgroundSource underSource;

    public BlurredBackgroundSourceRenderNode(BlurredBackgroundSource r2) {
        this.drawables = new ReferenceList();
        this.fallbackSource = r2;
        this.renderNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m(null);
    }

    public RecordingCanvas beginRecording(int r3, int r4) {
        if (this.inRecording == true) goto L6;
        this.inRecording = true;
        this.renderNode.setPosition(0, 0, r3, r4);
        RecordingCanvas r32 = this.renderNode.beginRecording(r3, r4);
        this.recordingCanvas = r32;
        return r32;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public BlurredBackgroundDrawable createDrawable() {
        BlurredBackgroundDrawableRenderNode r0 = new BlurredBackgroundDrawableRenderNode(this);
        this.drawables.add(r0);
        return r0;
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public void dispatchOnDrawablesRelativePositionChange() {
        Runnable r0 = this.onDrawablesRelativePositionChangeListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
    public void draw(Canvas r7, float r8, float r9, float r10, float r11) {
        if (r7.isHardwareAccelerated() == true) goto L9;
        BlurredBackgroundSource r6 = this.fallbackSource;
        if (r6 == null) goto L26;
        r6.draw(r7, r8, r9, r10, r11);
        return;
    L26:
        return;
    L9:
        if (this.inRecording == true) goto L24;
        BlurredBackgroundSource r0 = this.underSource;
        if (r0 == null) goto L13;
        r0.draw(r7, r8, r9, r10, r11);
    L13:
        r7.save();
        if (this.noClip == true) goto L17;
        r7.clipRect(r8, r9, r10, r11);
    L17:
        if (Build.VERSION.SDK_INT < 31) goto L21;
        DownscaleScrollableNoiseSuppressor r82 = this.scrollableNoiseSuppressor;
        if (r82 == null) goto L21;
        r82.drawInline(r7, this.scrollableNoiseSuppressorIndex);
    L22:
        r7.restore();
        return;
    L21:
        r7.drawRenderNode(this.renderNode);
        goto L22
    L24:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public void endRecording() {
        if (this.inRecording == false) goto L6;
        this.renderNode.endRecording();
        this.inRecording = false;
        this.recordingCanvas = null;
        return;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public BlurredBackgroundSource getFallbackSource() {
        return this.fallbackSource;
    }

    public int getVisiblePositions(List<RectF> r4, int r5, int r6) {
        Iterator<BlurredBackgroundDrawableRenderNode> r3 = this.drawables.iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L16;
        BlurredBackgroundDrawableRenderNode r1 = r3.next();
        if (r1.hasDisplayList() == false) goto L4;
        if (r1.getAlpha() <= 0) goto L4;
        if (r1.getPaddedBounds().isEmpty() == true) goto L4;
        if (r5 >= r4.size()) goto L14;
        RectF r2 = r4.get(r5);
    L15:
        r1.getPositionRelativeSource(r2);
        float r12 = -r6;
        r2.inset(r12, r12);
        r5 = r5 + 1;
        r0 = r0 + 1;
        goto L4
    L14:
        r2 = new RectF();
        r4.add(r2);
        goto L15
    L16:
        return r0;
    }

    public boolean inRecording() {
        return this.inRecording;
    }

    public void invalidateDisplayList() {
        RenderNodeWithHash r0 = this.renderNodeWithHash;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void invalidateDisplayListForDrawables() {
        Iterator<BlurredBackgroundDrawableRenderNode> r1 = this.drawables.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().invalidateDisplayList();
        goto L4
    }

    public boolean isRecordingCanvas(Canvas r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1 != this.recordingCanvas) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean needUpdateDisplayList(int r2, int r3) {
        if (this.renderNode.hasDisplayList() == true) goto L5;
        return true;
    L5:
        if (this.renderNode.getWidth() == r2) goto L7;
        return true;
    L7:
        if (this.renderNode.getHeight() != r3) goto L14;
        return false;
    L14:
        return true;
    }

    public void noClip() {
        this.noClip = true;
    }

    public void setBlur(float r2) {
        RenderNode r1 = this.renderNode;
        if (r2 <= 0.0f) goto L5;
        RenderEffect r22 = RenderEffect.createBlurEffect(r2, r2, Shader.TileMode.CLAMP);
    L6:
        r1.setRenderEffect(r22);
        return;
    L5:
        r22 = null;
        goto L6
    }

    public void setBlur(float r2, RenderEffect r3) {
        this.renderNode.setRenderEffect(RenderEffect.createChainEffect(RenderEffect.createBlurEffect(r2, r2, Shader.TileMode.CLAMP), r3));
    }

    public void setOnDrawablesRelativePositionChangeListener(Runnable r1) {
        this.onDrawablesRelativePositionChangeListener = r1;
    }

    public void setScrollableNoiseSuppressor(DownscaleScrollableNoiseSuppressor r1, int r2) {
        this.scrollableNoiseSuppressor = r1;
        this.scrollableNoiseSuppressorIndex = r2;
    }

    public void setSize(int r2, int r3) {
        this.renderNode.setPosition(0, 0, r2, r3);
    }

    public void setUnderSource(BlurredBackgroundSource r1) {
        this.underSource = r1;
    }

    public void setupRenderer(RenderNodeWithHash.Renderer r3) {
        if (this.renderNodeWithHash != null) goto L6;
        this.renderNodeWithHash = new RenderNodeWithHash(this.renderNode, r3);
        return;
    }

    public void updateDisplayListIfNeeded() {
        this.renderNodeWithHash.updateDisplayListIfNeeded();
    }
}
