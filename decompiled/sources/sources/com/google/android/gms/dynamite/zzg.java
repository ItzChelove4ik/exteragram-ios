package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzg implements DynamiteModule.VersionPolicy {
    public zzg() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context r3, String r4, DynamiteModule.VersionPolicy.IVersions r5) {
        DynamiteModule.VersionPolicy.SelectionResult r2 = new DynamiteModule.VersionPolicy.SelectionResult();
        int r1 = r5.zza(r3, r4, true);
        r2.remoteVersion = r1;
        if (r1 == 0) goto L6;
        r2.selection = 1;
        return r2;
    L6:
        int r32 = r5.zzb(r3, r4);
        r2.localVersion = r32;
        if (r32 == 0) goto L9;
        r2.selection = -1;
    L9:
        return r2;
    }
}
