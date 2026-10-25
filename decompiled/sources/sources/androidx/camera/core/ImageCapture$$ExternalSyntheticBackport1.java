package androidx.camera.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract /* synthetic */ class ImageCapture$$ExternalSyntheticBackport1 {
    public static /* synthetic */ List m(Object[] r4) {
        ArrayList r0 = new ArrayList(r4.length);
        int r1 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        Object r3 = r4[r2];
        Objects.requireNonNull(r3);
        r0.add(r3);
        r2 = r2 + 1;
        goto L3
    L6:
        return Collections.unmodifiableList(r0);
    }
}
