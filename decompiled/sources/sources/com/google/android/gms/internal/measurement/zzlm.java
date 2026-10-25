package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import java.util.ArrayList;

/* loaded from: classes4.dex */
final /* synthetic */ class zzlm implements Supplier {
    private final /* synthetic */ ArrayList zza;

    public /* synthetic */ zzlm(ArrayList r1) {
        this.zza = r1;
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        int r0 = zzlk.$r8$clinit;
        return new zzru(this.zza);
    }
}
