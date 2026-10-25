package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzob extends zzol implements Runnable {
    public static final /* synthetic */ int $r8$clinit = 0;
    ListenableFuture zza;
    Class zzb;
    Object zzc;

    public zzob(ListenableFuture r1, Class r2, Object r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture r0 = this.zza;
        Class r1 = this.zzb;
        Object r2 = this.zzc;
        boolean r3 = false;
        if (r0 != 0) goto L5;
        boolean r5 = true;
    L6:
        if (r1 != null) goto L8;
        boolean r6 = true;
    L9:
        boolean r52 = r5 | r6;
        if (r2 != null) goto L13;
        r3 = true;
    L13:
        if ((r3 | r52) == false) goto L15;
        return;
    L15:
        if (isCancelled() == true) goto L58;
        this.zza = null;
    L23:
        e = move-exception;
        Throwable r53 = e.getCause();
        if (r53 != null) goto L33;
        String r62 = String.valueOf(r0.getClass());
        String r4 = String.valueOf(e.getClass());
        StringBuilder r8 = new StringBuilder(((r62.length() + 19) + r4.length()) + 16);
        r8.append("Future type ");
        r8.append(r62);
        r8.append(" threw ");
        r8.append(r4);
        r8.append(" without a cause");
        r53 = new NullPointerException(r8.toString());
    L33:
        th = r53;
    L29:
        Object r54 = null;
    L34:
        if (th != null) goto L38;
        zzh(r54);
        return;
    L38:
        if (r1.isInstance(th) == true) goto L53;
        zzj(r0);
        return;
    L53:
        Object r02 = zzb(r2, th);     // Catch: Throwable -> L42
        this.zzb = null;
        this.zzc = null;
        zza(r02);
        return;
    L42:
        th = move-exception;
        zzpj.zza(th);     // Catch: Throwable -> L46
        zzi(th);     // Catch: Throwable -> L46
        this.zzb = null;
        this.zzc = null;
        return;
    L46:
        th = move-exception;
        this.zzb = null;
        this.zzc = null;
        throw th;
    L21:
        th = th;
        goto L29
    L19:
        if ((r0 instanceof zzpn) == false) goto L25;
        th = ((zzpn) r0).zzk();     // Catch: Throwable -> L21 ExecutionException -> L23
    L26:
        if (th != null) goto L29;
        r54 = zzot.zzi(r0);     // Catch: Throwable -> L21 ExecutionException -> L23
    L25:
        th = null;
        goto L26
    L58:
        return;
    L8:
        r6 = false;
        goto L9
    L5:
        r5 = false;
        goto L6
    }

    public abstract void zza(Object r1);

    public abstract Object zzb(Object r1, Throwable r2);

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final void zzc() {
        zzl(this.zza);
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final String zzd() {
        ListenableFuture r0 = this.zza;
        Class r1 = this.zzb;
        Object r2 = this.zzc;
        String r5 = super.zzd();
        if (r0 == null) goto L5;
        String r02 = r0.toString();
        StringBuilder r4 = new StringBuilder(r02.length() + 16);
        r4.append("inputFuture=[");
        r4.append(r02);
        r4.append("], ");
        String r03 = r4.toString();
    L6:
        if (r1 == null) goto L11;
        if (r2 == null) goto L11;
        int r52 = r03.length();
        String r12 = r1.toString();
        int r3 = r12.length();
        String r22 = r2.toString();
        StringBuilder r32 = new StringBuilder(((((r52 + 15) + r3) + 13) + r22.length()) + 1);
        r32.append(r03);
        r32.append("exceptionType=[");
        r32.append(r12);
        r32.append("], fallback=[");
        r32.append(r22);
        r32.append("]");
        return r32.toString();
    L11:
        if (r5 != null) goto L13;
        return null;
    L13:
        return r03.concat(r5);
    L5:
        r03 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
