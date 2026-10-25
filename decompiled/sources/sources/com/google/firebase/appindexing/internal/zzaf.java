package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.FirebaseAppIndexingException;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseAppIndexingTooManyArgumentsException;
import com.google.firebase.appindexing.zza;

/* loaded from: classes5.dex */
public abstract class zzaf {
    public static FirebaseAppIndexingException zza(Status r2, String r3) {
        Preconditions.checkNotNull(r2);
        String r0 = r2.getStatusMessage();
        if (r0 == null) goto L8;
        if (r0.isEmpty() == true) goto L8;
        r3 = r0;
    L8:
        switch(r2.getStatusCode()) {
            case 17510: goto L18;
            case 17511: goto L16;
            case 17512: goto L10;
            case 17513: goto L14;
            case 17514: goto L12;
            default: goto L10;
        };
    L10:
        return new FirebaseAppIndexingException(r3);
    L12:
        return new zza(r3);
    L14:
        return new com.google.firebase.appindexing.zzb(r3);
    L16:
        return new FirebaseAppIndexingTooManyArgumentsException(r3);
    L18:
        return new FirebaseAppIndexingInvalidArgumentException(r3);
    }
}
