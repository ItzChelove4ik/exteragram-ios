package org.telegram.ui.Components.blur3;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;

/* loaded from: classes3.dex */
public class RenderNodeWithHash {
    private final Blur3HashImpl hashBuilder;
    private long lastHash;
    private int lastHeight;
    private int lastWidth;
    public final RenderNode renderNode;
    private final Renderer renderer;

    public interface Renderer {
        void renderNodeCalculateHash(IBlur3Hash r1);

        void renderNodeUpdateDisplayList(Canvas r1);
    }

    public RenderNodeWithHash(RenderNode r3, Renderer r4) {
        this.hashBuilder = new Blur3HashImpl();
        this.lastHash = 0;
        this.renderNode = r3;
        this.renderer = r4;
    }

    public void invalidate() {
        this.lastHash = -1;
    }

    public void updateDisplayListIfNeeded() {
        int r0 = this.renderNode.getWidth();
        int r1 = this.renderNode.getHeight();
        this.hashBuilder.start();
        this.renderer.renderNodeCalculateHash(this.hashBuilder);
        long r2 = this.hashBuilder.get();
        if (this.renderNode.hasDisplayList() == true) goto L5;
    L14:
        boolean r4 = true;
    L15:
        this.lastWidth = r0;
        this.lastHeight = r1;
        this.lastHash = r2;
        if (r4 == false) goto L19;
        RecordingCanvas r02 = this.renderNode.beginRecording();
        this.renderer.renderNodeUpdateDisplayList(r02);
        this.renderNode.endRecording();
        return;
    L19:
        return;
    L5:
        if (r0 != this.lastWidth) goto L14;
        if (r1 != this.lastHeight) goto L14;
        if (r2 != this.lastHash) goto L14;
        if (r2 == (-1)) goto L14;
        r4 = false;
        goto L15
    }
}
