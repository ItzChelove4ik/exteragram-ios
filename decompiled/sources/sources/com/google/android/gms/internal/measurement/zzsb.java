package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.google.common.collect.ImmutableList;
import java.io.File;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzsb {
    private final Uri.Builder zza;
    private final ImmutableList.Builder zzb;

    public /* synthetic */ zzsb(byte[] r2) {
        this.zza = new Uri.Builder().scheme("file").authority(_UrlKt.FRAGMENT_ENCODE_SET).path("/");
        this.zzb = ImmutableList.builder();
    }

    public final zzsb zza(File r2) {
        this.zza.path(r2.getAbsolutePath());
        return this;
    }

    public final Uri zzb() {
        String r0 = zzsp.zzb(this.zzb.build());
        return this.zza.encodedFragment(r0).build();
    }
}
