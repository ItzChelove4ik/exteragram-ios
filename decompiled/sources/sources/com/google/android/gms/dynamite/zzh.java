package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzh implements DynamiteModule.VersionPolicy {
    public zzh() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context r2, String r3, DynamiteModule.VersionPolicy.IVersions r4) {
        DynamiteModule.VersionPolicy.SelectionResult r1 = new DynamiteModule.VersionPolicy.SelectionResult();
        int r0 = r4.zzb(r2, r3);
        r1.localVersion = r0;
        if (r0 == 0) goto L6;
        r1.selection = -1;
        return r1;
    L6:
        int r22 = r4.zza(r2, r3, true);
        r1.remoteVersion = r22;
        if (r22 == 0) goto L9;
        r1.selection = 1;
    L9:
        return r1;
    }
}
