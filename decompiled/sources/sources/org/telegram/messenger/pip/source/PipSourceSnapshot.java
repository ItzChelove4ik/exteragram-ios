package org.telegram.messenger.pip.source;

import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.RenderNode;
import android.os.Build;
import android.view.View;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
class PipSourceSnapshot {
    private final RenderNode node;
    private final Picture picture;

    public PipSourceSnapshot(int r3, int r4, Utilities.Callback<Canvas> r5) {
        Picture r0 = new Picture();
        this.picture = r0;
        r5.run(r0.beginRecording(r3, r4));
        r0.endRecording();
        if (Build.VERSION.SDK_INT < 29) goto L6;
        PipSourceSnapshot$$ExternalSyntheticApiModelOutline0.m();
        RenderNode r52 = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("pip-node-" + View.generateViewId());
        this.node = r52;
        r52.setPosition(0, 0, r3, r4);
        r52.beginRecording().drawPicture(r0);
        r52.endRecording();
        return;
    L6:
        this.node = null;
    }

    public void draw(Canvas r10, float r11) {
        if (Build.VERSION.SDK_INT < 29) goto L9;
        RenderNode r0 = this.node;
        if (r0 == null) goto L23;
        r0.setAlpha(r11);
        r10.drawRenderNode(this.node);
        return;
    L23:
        return;
    L9:
        if (this.picture != null) goto L11;
        return;
    L11:
        if (r11 > 0.001f) goto L13;
        return;
    L13:
        if (r11 >= 0.999f) goto L15;
        boolean r1 = true;
    L16:
        if (r1 == false) goto L18;
        Canvas r2 = r10;
        r2.saveLayerAlpha(0.0f, 0.0f, r0.getWidth(), this.picture.getHeight(), (int) (r11 * 255.0f), 31);
    L19:
        r2.drawPicture(this.picture);
        if (r1 == false) goto L26;
        r2.restore();
        return;
    L26:
        return;
    L18:
        r2 = r10;
        goto L19
    L15:
        r1 = false;
        goto L16
    }

    public void release() {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.node.discardDisplayList();
        return;
    }
}
