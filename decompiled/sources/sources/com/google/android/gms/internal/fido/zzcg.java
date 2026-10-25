package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzcg implements Comparator {
    public final /* synthetic */ Comparator zza;

    public /* synthetic */ zzcg(Comparator r1) {
        this.zza = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        Map.Entry r12 = (Map.Entry) r1;
        Map.Entry r22 = (Map.Entry) r2;
        Objects.requireNonNull(r12);
        Objects.requireNonNull(r22);
        Object r13 = r12.getKey();
        Object r23 = r22.getKey();
        return this.zza.compare(r13, r23);
    }
}
