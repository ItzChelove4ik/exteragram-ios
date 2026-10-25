package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzj implements DynamiteModule.VersionPolicy {
    public zzj() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context r2, String r3, DynamiteModule.VersionPolicy.IVersions r4) {
        DynamiteModule.VersionPolicy.SelectionResult r1 = new DynamiteModule.VersionPolicy.SelectionResult();
        r1.localVersion = r4.zzb(r2, r3);
        int r0 = 1;
        int r22 = r4.zza(r2, r3, true);
        r1.remoteVersion = r22;
        int r32 = r1.localVersion;
        if (r32 != 0) goto L7;
        r32 = 0;
        if (r22 != 0) goto L7;
        r0 = 0;
    L9:
        r1.selection = r0;
        return r1;
    L7:
        if (r32 < r22) goto L9;
        r0 = -1;
        goto L9
    }
}
