package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes5.dex */
final class zzz implements Continuation {
    final /* synthetic */ Collection zza;

    public zzz(Collection r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task r1) {
        ArrayList r12 = new ArrayList();
        r12.addAll(this.zza);
        return Tasks.forResult(r12);
    }
}
