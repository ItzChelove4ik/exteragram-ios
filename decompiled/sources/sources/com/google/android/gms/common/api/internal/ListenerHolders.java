package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes4.dex */
public class ListenerHolders {
    private final Set zaa;

    public ListenerHolders() {
        this.zaa = Collections.newSetFromMap(new WeakHashMap());
    }

    public static <L> ListenerHolder<L> createListenerHolder(L r1, Looper r2, String r3) {
        Preconditions.checkNotNull(r1, "Listener must not be null");
        Preconditions.checkNotNull(r2, "Looper must not be null");
        Preconditions.checkNotNull(r3, "Listener type must not be null");
        return new ListenerHolder(r2, r1, r3);
    }

    public static <L> ListenerHolder.ListenerKey<L> createListenerKey(L r1, String r2) {
        Preconditions.checkNotNull(r1, "Listener must not be null");
        Preconditions.checkNotNull(r2, "Listener type must not be null");
        Preconditions.checkNotEmpty(r2, "Listener type must not be empty");
        return new ListenerHolder.ListenerKey(r1, r2);
    }

    public final void zab() {
        Set r2 = this.zaa;
        Iterator r0 = r2.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((ListenerHolder) r0.next()).clear();
        goto L4
    L6:
        r2.clear();
    }
}
