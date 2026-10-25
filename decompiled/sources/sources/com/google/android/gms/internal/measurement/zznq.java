package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zznq extends zzadp implements zzafd {
    public /* synthetic */ zznq(byte[] r1) {
        super(zznr.zze());
    }

    public final List zza() {
        return Collections.unmodifiableList(((zznr) this.zza).zza());
    }

    public final zznq zzb(String r2) {
        zzaY();
        ((zznr) this.zza).zzc(_UrlKt.FRAGMENT_ENCODE_SET);
        return this;
    }

    public final zznq zzc(String r2) {
        zzaY();
        ((zznr) this.zza).zzd(_UrlKt.FRAGMENT_ENCODE_SET);
        return this;
    }
}
