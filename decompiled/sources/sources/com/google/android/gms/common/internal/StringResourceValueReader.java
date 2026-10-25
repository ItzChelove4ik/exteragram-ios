package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R$string;

/* loaded from: classes.dex */
public class StringResourceValueReader {
    private final Resources zza;
    private final String zzb;

    public StringResourceValueReader(Context r2) {
        Preconditions.checkNotNull(r2);
        Resources r22 = r2.getResources();
        this.zza = r22;
        this.zzb = r22.getResourcePackageName(R$string.common_google_play_services_unknown_issue);
    }

    public String getString(String r3) {
        String r0 = this.zzb;
        Resources r2 = this.zza;
        int r32 = r2.getIdentifier(r3, "string", r0);
        if (r32 != 0) goto L7;
        return null;
    L7:
        return r2.getString(r32);
    }
}
