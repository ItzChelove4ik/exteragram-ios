package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.function.BiFunction;

/* loaded from: classes4.dex */
final /* synthetic */ class zzoi implements BiFunction {
    private final /* synthetic */ byte[] zza;

    public /* synthetic */ zzoi(byte[] r1) {
        this.zza = r1;
    }

    @Override // java.util.function.BiFunction
    public final /* synthetic */ Object apply(Object r1, Object r2) {
        byte[] r22 = (byte[]) r2;
        byte[] r0 = this.zza;
        if (Arrays.equals(r22, r0) == false) goto L5;
        return r22;
    L5:
        return r0;
    }
}
