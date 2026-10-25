package org.telegram.ui.Components.Paint;

import android.graphics.PointF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes7.dex */
public class RenderState {
    private int allocatedCount;
    public float alpha;
    public float angle;
    public float baseWeight;
    private ByteBuffer buffer;
    private int count;
    public double remainder;
    public float scale;
    public float spacing;
    public float viewportScale;

    public RenderState() {
    }

    public boolean addPoint(PointF r4, float r5, float r6, float r7, int r8) {
        if (r8 == (-1)) goto L7;
        if (r8 < this.allocatedCount) goto L7;
    L8:
        resizeBuffer();
        return false;
    L7:
        if (this.buffer.position() == this.buffer.limit()) goto L8;
        if (r8 == (-1)) goto L12;
        this.buffer.position(r8 * 20);
    L12:
        this.buffer.putFloat(r4.x);
        this.buffer.putFloat(r4.y);
        this.buffer.putFloat(r5);
        this.buffer.putFloat(r6);
        this.buffer.putFloat(r7);
        return true;
    }

    public void appendValuesCount(int r2) {
        int r0 = this.count + r2;
        if (r0 <= this.allocatedCount) goto L5;
    L6:
        resizeBuffer();
    L7:
        this.count = r0;
        return;
    L5:
        if (this.buffer != null) goto L7;
        goto L6
    }

    public int getCount() {
        return this.count;
    }

    public void prepare() {
        this.count = 0;
        if (this.buffer == null) goto L5;
        return;
    L5:
        this.allocatedCount = 256;
        ByteBuffer r1 = ByteBuffer.allocateDirect((256 * 5) * 4);
        this.buffer = r1;
        r1.order(ByteOrder.nativeOrder());
        this.buffer.position(0);
    }

    public float read() {
        return this.buffer.getFloat();
    }

    public void reset() {
        this.count = 0;
        this.remainder = 0.0d;
        ByteBuffer r3 = this.buffer;
        if (r3 == null) goto L6;
        r3.position(0);
        return;
    }

    public void resizeBuffer() {
        if (this.buffer == null) goto L5;
        this.buffer = null;
    L5:
        int r0 = Math.max(this.allocatedCount * 2, 256);
        this.allocatedCount = r0;
        ByteBuffer r02 = ByteBuffer.allocateDirect(r0 * 20);
        this.buffer = r02;
        r02.order(ByteOrder.nativeOrder());
        this.buffer.position(0);
    }

    public void setPosition(int r2) {
        ByteBuffer r0 = this.buffer;
        if (r0 == null) goto L10;
        if (r2 >= 0) goto L6;
        return;
    L6:
        if (r2 >= this.allocatedCount) goto L12;
        r0.position(r2 * 20);
        return;
    L12:
        return;
    }
}
