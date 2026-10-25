package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* loaded from: classes4.dex */
final class zzcf extends BasePendingResult {
    public zzcf(zzcg r1) {
        super(null);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final Result createFailedResult(Status r1) {
        int r0 = CastSession.zza;
        return r1;
    }
}
