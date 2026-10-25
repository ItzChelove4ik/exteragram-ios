package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.system.Os;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes4.dex */
public abstract class zzux {
    public static IOException zza(zzru r1, Uri r2, IOException r3, String r4) {
        zzsr r0 = zzsr.zzb();     // Catch: IOException -> L37
        r0.zzc();     // Catch: IOException -> L37
        File r12 = (File) r1.zza(r2, r0);     // Catch: IOException -> L37
        if (r12.exists() == false) goto L36;
        if (r12.isFile() == false) goto L22;
        if (r12.canRead() == false) goto L16;
        if (r12.canWrite() == false) goto L14;
        return zzb(r12, r3, r4);
    L14:
        return zzb(r12, r3, r4);
    L16:
        if (r12.canWrite() == false) goto L20;
        return zzb(r12, r3, r4);
    L20:
        return zzb(r12, r3, r4);
    L22:
        if (r12.canRead() == false) goto L30;
        if (r12.canWrite() == false) goto L28;
        return zzb(r12, r3, r4);
    L28:
        return zzb(r12, r3, r4);
    L30:
        if (r12.canWrite() == false) goto L34;
        return zzb(r12, r3, r4);
    L34:
        return zzb(r12, r3, r4);
    L36:
        return zzb(r12, r3, r4);
    L38:
        return new IOException(r3);
    }

    private static IOException zzb(File r2, IOException r3, String r4) {
        File r0 = r2.getParentFile();
        if (r0 != null) goto L7;
        return zzc(r2, r3, r4);
    L7:
        if (r0.exists() == false) goto L39;
        if (r0.isDirectory() == false) goto L25;
        if (r0.canRead() == false) goto L19;
        if (r0.canWrite() == false) goto L17;
        return zzc(r2, r3, r4);
    L17:
        return zzc(r2, r3, r4);
    L19:
        if (r0.canWrite() == false) goto L23;
        return zzc(r2, r3, r4);
    L23:
        return zzc(r2, r3, r4);
    L25:
        if (r0.canRead() == false) goto L33;
        if (r0.canWrite() == false) goto L31;
        return zzc(r2, r3, r4);
    L31:
        return zzc(r2, r3, r4);
    L33:
        if (r0.canWrite() == false) goto L37;
        return zzc(r2, r3, r4);
    L37:
        return zzc(r2, r3, r4);
    L39:
        return zzc(r2, r3, r4);
    }

    private static IOException zzc(File r6, IOException r7, String r8) {
        Locale r1 = Locale.US;     // Catch: IOException -> L6
        String r82 = String.format(r1, " canonical[%s] freeSpace[%d] protoName[%s]", new Object[]{r6.getCanonicalPath(), Long.valueOf(r6.getFreeSpace()), r8});     // Catch: IOException -> L6
        StringBuilder r3 = new StringBuilder(r82.length() + 16);     // Catch: IOException -> L6
        r3.append("Inoperable file:");     // Catch: IOException -> L6
        r3.append(r82);     // Catch: IOException -> L6
        String r83 = r3.toString();     // Catch: IOException -> L6
        String r62 = String.format(r1, " mode[%d]", new Object[]{Integer.valueOf(Os.stat(r6.getCanonicalPath()).st_mode)});     // Catch: Exception -> L9
        StringBuilder r12 = new StringBuilder(r83.length() + r62.length());     // Catch: Exception -> L9
        r12.append(r83);     // Catch: Exception -> L9
        r12.append(r62);     // Catch: Exception -> L9
        r83 = r12.toString();     // Catch: Exception -> L9
    L8:
        return new IOException(r83, r7);
    L6:
        r83 = "Inoperable file:".concat(" failed");
        goto L8
    }
}
