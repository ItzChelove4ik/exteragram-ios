package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import java.io.FileOutputStream;

/* loaded from: classes4.dex */
final /* synthetic */ class zzgn implements Runnable {
    private final /* synthetic */ Pair zza;
    private final /* synthetic */ zzwh zzb;

    public /* synthetic */ zzgn(zzgp r1, Pair r2, zzwh r3) {
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        Pair r0 = this.zza;
        boolean r1 = false;
        ParcelFileDescriptor r2 = (ParcelFileDescriptor) r0.first;     // Catch: Exception -> L12
        FileOutputStream r3 = new FileOutputStream(((ParcelFileDescriptor) r0.second).getFileDescriptor());     // Catch: Exception -> L12
        ParcelFileDescriptor.AutoCloseInputStream r02 = new ParcelFileDescriptor.AutoCloseInputStream(r2);     // Catch: Throwable -> L14
        int r22 = zznv.$r8$clinit;     // Catch: Throwable -> L18
        byte[] r23 = new byte[8192];     // Catch: Throwable -> L18
    L6:
        int r4 = r02.read(r23);     // Catch: Throwable -> L18
    L8:
        if (r4 == (-1)) goto L9;
        r3.write(r23, 0, r4);     // Catch: Throwable -> L18
        goto L6
    L9:
        r02.close();     // Catch: Throwable -> L14
        r3.close();     // Catch: Exception -> L12
        r1 = true;
    L30:
        zzwh r6 = this.zzb;
        if (r6 == null) goto L43;
        r6.zza(r1);
        return;
    L43:
        return;
    L18:
        th = move-exception;
        r02.close();     // Catch: Throwable -> L21
    L23:
        throw th;     // Catch: Throwable -> L14
    L21:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L26
    L28:
        throw th;     // Catch: Exception -> L12
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L12
    L12:
        e = move-exception;
        Log.e("DataTransporter", "Failed to transport the file", e);
        goto L30
    }
}
