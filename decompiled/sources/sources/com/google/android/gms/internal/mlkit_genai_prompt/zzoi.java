package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzoi extends zzol implements Runnable {
    public static final /* synthetic */ int $r8$clinit = 0;
    ListenableFuture zza;
    Object zzb;

    public zzoi(ListenableFuture r1, Object r2) {
        r1.getClass();
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture r0 = this.zza;
        Object r1 = this.zzb;
        boolean r2 = isCancelled();
        boolean r3 = true;
        if (r0 != null) goto L5;
        boolean r5 = true;
    L6:
        boolean r22 = r2 | r5;
        if (r1 == null) goto L11;
        r3 = false;
    L11:
        if ((r22 | r3) == false) goto L13;
        return;
    L13:
        this.zza = null;
        if (r0.isCancelled() == false) goto L43;
        zzj(r0);
        return;
    L43:
        Object r02 = zzb(r1, zzot.zzi(r0));     // Catch: Throwable -> L19
        this.zzb = null;
        zza(r02);
        return;
    L19:
        th = move-exception;
        zzpj.zza(th);     // Catch: Throwable -> L23
        zzi(th);     // Catch: Throwable -> L23
        this.zzb = null;
        return;
    L23:
        th = move-exception;
        this.zzb = null;
        throw th;
    L26:
        e = move-exception;
        zzi(e);
        return;
    L35:
        cancel(false);
        return;
    L32:
        e = move-exception;
        zzi(e.getCause());
        return;
    L29:
        e = move-exception;
        zzi(e);
        return;
    L5:
        r5 = false;
        goto L6
    }

    public abstract void zza(Object r1);

    public abstract Object zzb(Object r1, Object r2);

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final void zzc() {
        zzl(this.zza);
        this.zza = null;
        this.zzb = null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final String zzd() {
        ListenableFuture r0 = this.zza;
        Object r1 = this.zzb;
        String r4 = super.zzd();
        if (r0 == null) goto L5;
        String r02 = r0.toString();
        StringBuilder r3 = new StringBuilder(r02.length() + 16);
        r3.append("inputFuture=[");
        r3.append(r02);
        r3.append("], ");
        String r03 = r3.toString();
    L6:
        if (r1 == null) goto L9;
        int r42 = r03.length();
        String r12 = r1.toString();
        StringBuilder r2 = new StringBuilder(((r42 + 10) + r12.length()) + 1);
        r2.append(r03);
        r2.append("function=[");
        r2.append(r12);
        r2.append("]");
        return r2.toString();
    L9:
        if (r4 != null) goto L11;
        return null;
    L11:
        return r03.concat(r4);
    L5:
        r03 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
