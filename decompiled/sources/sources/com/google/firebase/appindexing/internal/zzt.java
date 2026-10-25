package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseUserActions;

/* loaded from: classes5.dex */
public final class zzt extends FirebaseUserActions {
    private final zzr zza;

    public zzt(Context r2) {
        this.zza = new zzr(r2);
    }

    private final Task<Void> zza(int r3, Action r4) {
        zzc[] r0 = new zzc[1];
        if (r4 == null) goto L10;
        if ((r4 instanceof zzc) == false) goto L7;
        zzc r42 = (zzc) r4;
        r0[0] = r42;
        r42.zza().zza(r3);
        goto L10
    L7:
        return Tasks.forException(new FirebaseAppIndexingInvalidArgumentException("Custom Action objects are not allowed. Please use the 'Actions' or 'ActionBuilder' class for creating Action objects."));
    L10:
        return this.zza.doWrite(new zzq(this, r0));
    }

    @Override // com.google.firebase.appindexing.FirebaseUserActions
    public final Task<Void> end(Action r2) {
        return zza(2, r2);
    }
}
