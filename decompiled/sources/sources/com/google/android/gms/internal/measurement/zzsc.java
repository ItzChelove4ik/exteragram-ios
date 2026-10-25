package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes4.dex */
public abstract class zzsc {
    public static final File zza(Uri r2) {
        if (r2.getScheme().equals("file") == false) goto L15;
        if (TextUtils.isEmpty(r2.getQuery()) == false) goto L13;
        if (TextUtils.isEmpty(r2.getAuthority()) == false) goto L11;
        return new File(r2.getPath());
    L11:
        throw new zzsi("Did not expect uri to have authority");
    L13:
        throw new zzsi("Did not expect uri to have query");
    L15:
        throw new zzsi("Scheme must be 'file'");
    }
}
