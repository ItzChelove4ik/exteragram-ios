package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes4.dex */
public final class zzlj {
    private Context zza;
    private Supplier zzb;
    private Supplier zzc;
    private Supplier zzd;
    private Supplier zze;
    private Supplier zzf;

    public /* synthetic */ zzlj(byte[] r1) {
    }

    public final zzlj zza(Context r1) {
        this.zza = r1;
        return this;
    }

    public final zzlk zzb() {
        Preconditions.checkNotNull(this.zza);
        if (this.zzb != null) goto L6;
        this.zzb = zzlk.zzm();
    L6:
        if (this.zzc != null) goto L9;
        Context r0 = this.zza;
        int r1 = zzlk.$r8$clinit;
        this.zzc = Suppliers.memoize(new zzln(r0));
    L9:
        if (this.zzd != null) goto L12;
        this.zzd = new zzli(this);
    L12:
        if (this.zze != null) goto L15;
        Context r02 = this.zza;
        int r12 = zzlk.$r8$clinit;
        ArrayList r13 = new ArrayList();
        Collections.addAll(r13, new zzsx[]{zzrx.zza(r02).zza(), new zzsd()});
        this.zze = Suppliers.memoize(new zzlm(r13));
    L15:
        if (this.zzf != null) goto L18;
        this.zzf = new zzlh(this);
    L18:
        return new zzlk(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, null);
    }

    public final /* synthetic */ Optional zzc() {
        return Optional.of(new zzqi(this.zzb, 10));
    }

    public final /* synthetic */ Optional zzd() {
        Context r2 = this.zza;
        int r0 = zzlk.$r8$clinit;
        return Optional.of(r2.getPackageManager().getApplicationInfo("com.google.android.gms", 0));
    L6:
        return Optional.absent();
    }
}
