package org.telegram.ui.Components;

import android.graphics.Path;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class PhotoBubbleClip extends Path {
    private int lastCx;
    private int lastCy;
    private int lastR;

    public PhotoBubbleClip() {
    }

    public void setBounds(int r18, int r19, int r20) {
        if (this.lastCx == r18) goto L5;
    L9:
        rewind();
        RectF r1 = AndroidUtilities.rectTmp;
        float r10 = r18 - r20;
        float r4 = r19 + r20;
        r1.set(r10, r19 - r20, r18 + r20, r4);
        arcTo(r1, -180.0f, 270.0f, false);
        float r12 = r20 / 81.0f;
        float r13 = r18;
        float r2 = r4 - (3.0f * r12);
        cubicTo(r13 - (13.0f * r12), r4, r13 - (25.0f * r12), r2, r13 - (36.0f * r12), r4 - (8.42f * r12));
        float r22 = r4 - r12;
        cubicTo(r13 - (52.0f * r12), r22, r13 - (56.5f * r12), r22, r13 - (78.02f * r12), r22);
        cubicTo(r13 - (80.0f * r12), r22, r13 - (81.0f * r12), r2, r13 - (79.52f * r12), r4 - (4.5f * r12));
        float r3 = r13 - (63.73f * r12);
        cubicTo(r13 - (78.0f * r12), r4 - (6.0f * r12), r3, r4 - (15.0f * r12), r3, r4 - (31.0f * r12));
        float r6 = r19;
        cubicTo(r13 - (74.5f * r12), r4 - (44.75f * r12), r10, r6 + (r12 * 18.87f), r10, r6);
        close();
        this.lastCx = r18;
        this.lastCy = r19;
        this.lastR = r20;
        return;
    L5:
        if (this.lastCy != r19) goto L9;
        if (this.lastR != r20) goto L9;
    }
}
