package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzf implements DynamiteModule.VersionPolicy.IVersions {
    public zzf() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int zza(Context r1, String r2, boolean r3) {
        return DynamiteModule.zza(r1, r2, r3);
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int zzb(Context r1, String r2) {
        return DynamiteModule.getLocalVersion(r1, r2);
    }
}
