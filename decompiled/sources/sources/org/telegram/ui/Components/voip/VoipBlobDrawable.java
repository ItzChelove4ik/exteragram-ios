package org.telegram.ui.Components.voip;

import org.telegram.messenger.LiteMode;
import org.telegram.ui.Components.BlobDrawable;

/* loaded from: classes7.dex */
public class VoipBlobDrawable extends BlobDrawable {
    public VoipBlobDrawable(int r1) {
        super(r1);
    }

    public void generateBlob(float[] r7, float[] r8, int r9, float r10) {
        float r0 = (360.0f / this.N) * 0.05f;
        float r2 = this.maxRadius;
        float r3 = this.minRadius;
        r7[r9] = r3 + ((Math.abs((this.random.nextInt() % 100.0f) / 100.0f) * (r2 - r3)) * r10);
        r8[r9] = ((360.0f / this.N) * r9) + ((((this.random.nextInt() * r10) % 100.0f) / 100.0f) * r0);
        this.speed[r9] = (float) (((Math.abs(this.random.nextInt() % 100.0f) / 100.0f) * 0.003d) + 0.017d);
    }

    public void update(float r7, float r8, float r9) {
        if (LiteMode.isEnabled(this.liteFlag) == false) goto L15;
        int r0 = 0;
    L7:
        if (r0 >= this.N) goto L20;
        float[] r1 = this.progress;
        float r2 = r1[r0];
        float r3 = this.speed[r0];
        float r22 = r2 + ((BlobDrawable.MIN_SPEED * r3) + (((r3 * r7) * BlobDrawable.MAX_SPEED) * r8));
        r1[r0] = r22;
        if (r22 < 1.0f) goto L14;
        r1[r0] = 0.0f;
        float[] r12 = this.radius;
        float[] r23 = this.radiusNext;
        r12[r0] = r23[r0];
        float[] r13 = this.angle;
        float[] r4 = this.angleNext;
        r13[r0] = r4[r0];
        if (r9 >= 1.0f) goto L13;
        generateBlob(r23, r4, r0, r9);
        goto L14
    L13:
        generateBlob(r23, r4, r0);
    L14:
        r0 = r0 + 1;
        goto L7
    L20:
        return;
    }
}
