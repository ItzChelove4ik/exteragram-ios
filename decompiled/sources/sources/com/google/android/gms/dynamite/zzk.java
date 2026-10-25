package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzk implements DynamiteModule.VersionPolicy {
    public zzk() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context r4, String r5, DynamiteModule.VersionPolicy.IVersions r6) {
        DynamiteModule.VersionPolicy.SelectionResult r3 = new DynamiteModule.VersionPolicy.SelectionResult();
        int r0 = r6.zzb(r4, r5);
        r3.localVersion = r0;
        int r1 = 1;
        int r2 = 0;
        if (r0 == 0) goto L5;
        int r42 = r6.zza(r4, r5, false);
        r3.remoteVersion = r42;
    L6:
        int r52 = r3.localVersion;
        if (r52 != 0) goto L10;
        if (r42 != 0) goto L11;
        r1 = 0;
    L13:
        r3.selection = r1;
        return r3;
    L11:
        if (r2 < r42) goto L13;
        r1 = -1;
        goto L13
    L10:
        r2 = r52;
        goto L11
    L5:
        r42 = r6.zza(r4, r5, true);
        r3.remoteVersion = r42;
        goto L6
    }
}
