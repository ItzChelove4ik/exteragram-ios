package com.exteragram.messenger.badges;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes.dex */
public abstract /* synthetic */ class CachedRemoteSet$$ExternalSyntheticBackport0 {
    public static /* synthetic */ Set m(Collection r2) {
        HashSet r0 = new HashSet(r2.size());
        Iterator r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        Object r1 = r22.next();
        Objects.requireNonNull(r1);
        r0.add(r1);
        goto L4
    L7:
        return Collections.unmodifiableSet(r0);
    }
}
