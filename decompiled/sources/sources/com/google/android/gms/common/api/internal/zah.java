package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final class zah extends zad {
    public final ListenerHolder.ListenerKey zab;

    public zah(ListenerHolder.ListenerKey r2, TaskCompletionSource r3) {
        super(4, r3);
        this.zab = r2;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] zaa(zabk r1) {
        zacd r0 = (zacd) r1.zag().get(this.zab);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zaa.getRequiredFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean zab(zabk r1) {
        zacd r0 = (zacd) r1.zag().get(this.zab);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.zaa.zaa() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final int zac(zabk r1) {
        zacd r0 = (zacd) r1.zag().get(this.zab);
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.zaa.zab();
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final /* bridge */ /* synthetic */ void zaf(zaaa r1, boolean r2) {
    }

    @Override // com.google.android.gms.common.api.internal.zad
    public final void zah(zabk r3) {
        zacd r0 = (zacd) r3.zag().remove(this.zab);
        if (r0 == null) goto L6;
        Api.Client r32 = r3.zaf();
        TaskCompletionSource<Boolean> r2 = this.zaa;
        r0.zab.unregisterListener(r32, r2);
        r0.zaa.clearListener();
        return;
    L6:
        this.zaa.trySetResult(Boolean.FALSE);
    }
}
