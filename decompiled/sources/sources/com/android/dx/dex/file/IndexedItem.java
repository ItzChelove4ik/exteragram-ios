package com.android.dx.dex.file;

import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class IndexedItem extends Item {
    private int index;

    public IndexedItem() {
        this.index = -1;
    }

    public final int getIndex() {
        int r0 = this.index;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("index not yet set");
        return 0;
    }

    public final boolean hasIndex() {
        if (this.index < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String indexString() {
        return "[" + Integer.toHexString(this.index) + ']';
    }

    public final void setIndex(int r3) {
        if (this.index != (-1)) goto L6;
        this.index = r3;
        return;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("index already set");
    }
}
