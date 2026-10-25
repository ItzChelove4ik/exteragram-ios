package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzc implements DynamiteModule.VersionPolicy {
    public zzc() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context r1, String r2, DynamiteModule.VersionPolicy.IVersions r3) {
        DynamiteModule.VersionPolicy.SelectionResult r0 = new DynamiteModule.VersionPolicy.SelectionResult();
        int r12 = r3.zzb(r1, r2);
        r0.localVersion = r12;
        if (r12 == 0) goto L5;
        int r13 = -1;
    L6:
        r0.selection = r13;
        return r0;
    L5:
        r13 = 0;
        goto L6
    }
}
