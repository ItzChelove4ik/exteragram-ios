package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzi implements DynamiteModule.VersionPolicy {
    public zzi() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context r2, String r3, DynamiteModule.VersionPolicy.IVersions r4) {
        DynamiteModule.VersionPolicy.SelectionResult r1 = new DynamiteModule.VersionPolicy.SelectionResult();
        int r0 = 0;
        int r22 = r4.zza(r2, r3, false);
        r1.remoteVersion = r22;
        if (r22 == 0) goto L6;
        r0 = 1;
    L6:
        r1.selection = r0;
        return r1;
    }
}
