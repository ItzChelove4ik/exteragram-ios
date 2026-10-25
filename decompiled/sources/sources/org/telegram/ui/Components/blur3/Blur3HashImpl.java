package org.telegram.ui.Components.blur3;

import org.telegram.messenger.MediaDataController;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;

/* loaded from: classes3.dex */
public class Blur3HashImpl implements IBlur3Hash {
    private long hash;
    private boolean unsupported;

    public Blur3HashImpl() {
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Hash
    public void add(long r3) {
        this.hash = MediaDataController.calcHash(this.hash, r3);
    }

    public long get() {
        if (this.unsupported == false) goto L7;
        return -1;
    L7:
        return this.hash;
    }

    public boolean isUnsupported() {
        return this.unsupported;
    }

    public void start() {
        this.hash = 0;
        this.unsupported = false;
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Hash
    public void unsupported() {
        this.unsupported = true;
    }
}
