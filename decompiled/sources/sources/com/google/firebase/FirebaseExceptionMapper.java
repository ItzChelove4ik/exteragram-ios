package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

/* loaded from: classes5.dex */
public class FirebaseExceptionMapper implements StatusExceptionMapper {
    public FirebaseExceptionMapper() {
    }

    @Override // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public final Exception getException(Status r2) {
        if (r2.getStatusCode() != 8) goto L7;
        return new FirebaseException(r2.zza());
    L7:
        return new FirebaseApiNotAvailableException(r2.zza());
    }
}
