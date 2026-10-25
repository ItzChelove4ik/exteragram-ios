package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public abstract class AndroidUtilsLight {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Object zzd = null;

    static {
        zzd = new Object();
    }

    @Deprecated
    public static byte[] getPackageCertificateHashBytes(Context r1, String r2) {
        PackageInfo r12 = Wrappers.packageManager(r1).getPackageInfo(r2, 64);
        Signature[] r22 = r12.signatures;
        if (r22 != null) goto L5;
        return null;
    L5:
        if (r22.length != 1) goto L13;
        MessageDigest r23 = zza("SHA1");
        if (r23 != null) goto L10;
        return null;
    L10:
        return r23.digest(r12.signatures[0].toByteArray());
    L13:
        return null;
    }

    public static MessageDigest zza(String r2) {
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L10;
        MessageDigest r1 = MessageDigest.getInstance(r2);     // Catch: NoSuchAlgorithmException -> L12
        if (r1 == null) goto L9;
        return r1;
    L9:
        r0 = r0 + 1;
        goto L4
    L10:
        return null;
    }
}
