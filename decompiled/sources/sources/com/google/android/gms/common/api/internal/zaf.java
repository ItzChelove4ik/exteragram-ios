package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final class zaf extends zad {
    public final zacd zab;

    public zaf(zacd r2, TaskCompletionSource r3) {
        super(3, r3);
        this.zab = r2;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] zaa(zabk r1) {
        return this.zab.zaa.getRequiredFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean zab(zabk r1) {
        return this.zab.zaa.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final int zac(zabk r1) {
        return this.zab.zaa.zab();
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final /* bridge */ /* synthetic */ void zaf(zaaa r1, boolean r2) {
    }

    @Override // com.google.android.gms.common.api.internal.zad
    public final void zah(zabk r4) {
        zacd r0 = this.zab;
        RegisterListenerMethod r1 = r0.zaa;
        r1.registerListener(r4.zaf(), this.zaa);
        ListenerHolder.ListenerKey r3 = r1.getListenerKey();
        if (r3 == null) goto L6;
        r4.zag().put(r3, r0);
        return;
    }
}
