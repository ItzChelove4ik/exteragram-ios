package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zbj extends zba {
    final /* synthetic */ zbk zba;

    public zbj(zbk r1) {
        Objects.requireNonNull(r1);
        this.zba = r1;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zba, com.google.android.gms.auth.api.signin.internal.zbr
    public final void zbd(Status r1) {
        this.zba.setResult(r1);
    }
}
