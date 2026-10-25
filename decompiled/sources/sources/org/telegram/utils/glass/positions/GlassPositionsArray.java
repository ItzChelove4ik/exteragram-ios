package org.telegram.utils.glass.positions;

import android.graphics.RectF;
import java.util.ArrayList;
import kotlin.text.Regex$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class GlassPositionsArray {
    private final ArrayList<RectF> positions;
    private int size;

    public GlassPositionsArray() {
        this.positions = new ArrayList();
    }

    public RectF add(float r3, float r4, float r5, float r6) {
        if (this.size >= this.positions.size()) goto L5;
        RectF r0 = this.positions.get(this.size);
        r0.set(r3, r4, r5, r6);
    L6:
        this.size++;
        return r0;
    L5:
        r0 = new RectF(r3, r4, r5, r6);
        this.positions.add(r0);
        goto L6
    }

    public RectF add(RectF r4) {
        return add(r4.left, r4.top, r4.right, r4.bottom);
    }

    public void clear() {
        this.size = 0;
    }

    public void clip(float r8, float r9, float r10, float r11) {
        int r0 = 0;
    L4:
        if (r0 >= this.size) goto L28;
        RectF r1 = this.positions.get(r0);
        float r2 = r1.right;
        if (r2 <= r8) goto L27;
        float r3 = r1.bottom;
        if (r3 <= r9) goto L27;
        float r4 = r1.left;
        if (r4 >= r10) goto L27;
        float r5 = r1.top;
        if (r5 >= r11) goto L27;
        if (r4 >= r8) goto L18;
        r1.left = r8;
    L18:
        if (r5 >= r9) goto L21;
        r1.top = r9;
    L21:
        if (r2 <= r10) goto L24;
        r1.right = r10;
    L24:
        if (r3 <= r11) goto L26;
        r1.bottom = r11;
    L26:
        r0 = r0 + 1;
    L27:
        removeUnordered(r0);
        goto L4
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof GlassPositionsArray) == true) goto L8;
        return false;
    L8:
        GlassPositionsArray r62 = (GlassPositionsArray) r6;
        if (this.size == r62.size) goto L11;
        return false;
    L11:
        int r1 = 0;
    L13:
        if (r1 >= this.size) goto L18;
        if (this.positions.get(r1).equals(r62.positions.get(r1)) == false) goto L16;
        r1 = r1 + 1;
        goto L13
    L16:
        return false;
    L18:
        return true;
    }

    public RectF get(int r3) {
        if (r3 >= 0) goto L4;
    L7:
        Regex$$ExternalSyntheticBUOutline0.m("index=", r3, ", size=", this.size);
        return null;
    L4:
        if (r3 >= this.size) goto L7;
        return this.positions.get(r3);
    }

    public int hashCode() {
        int r0 = 1;
        int r1 = 0;
    L4:
        if (r1 >= this.size) goto L6;
        r0 = (r0 * 31) + this.positions.get(r1).hashCode();
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public void removeUnordered(int r5) {
        if (r5 < 0) goto L10;
        int r0 = this.size;
        if (r5 >= r0) goto L10;
        int r02 = r0 - 1;
        RectF r1 = this.positions.get(r5);
        if (r5 == r02) goto L8;
        ArrayList<RectF> r2 = this.positions;
        r2.set(r5, r2.get(r02));
        this.positions.set(r02, r1);
    L8:
        this.size = r02;
        return;
    L10:
        Regex$$ExternalSyntheticBUOutline0.m("index=", r5, ", size=", this.size);
    }

    public void set(GlassPositionsArray r4) {
        if (this == r4) goto L7;
        clear();
        int r0 = r4.size;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        add(r4.positions.get(r1));
        r1 = r1 + 1;
        goto L5
    L9:
        return;
    }

    public int size() {
        return this.size;
    }
}
