package com.exteragram.messenger.backup;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public abstract /* synthetic */ class InvisibleEncryptor$$ExternalSyntheticBackport0 {
    public static /* synthetic */ String m(CharSequence r3, CharSequence[] r4) {
        if (r3 == null) goto L11;
        StringBuilder r0 = new StringBuilder();
        if (r4.length <= 0) goto L10;
        r0.append(r4[0]);
        int r1 = 1;
    L7:
        if (r1 >= r4.length) goto L10;
        r0.append(r3);
        r0.append(r4[r1]);
        r1 = r1 + 1;
    L10:
        return r0.toString();
    L11:
        f$$ExternalSyntheticBUOutline2.m("delimiter");
        return null;
    }
}
