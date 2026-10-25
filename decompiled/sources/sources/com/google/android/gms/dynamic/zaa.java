package com.google.android.gms.dynamic;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaa implements OnDelegateCreatedListener {
    final /* synthetic */ DeferredLifecycleHelper zaa;

    public zaa(DeferredLifecycleHelper r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate r3) {
        DeferredLifecycleHelper r2 = this.zaa;
        r2.zab(r3);
        Iterator r32 = r2.zad().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        ((zah) r32.next()).zab(r2.zaa());
        goto L4
    L6:
        r2.zad().clear();
        r2.zac(null);
    }
}
