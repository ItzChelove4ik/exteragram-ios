package com.google.android.recaptcha.internal;

import com.google.android.play.core.integrity.StandardIntegrityException;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzfd implements zzev {
    private final zzfp zza;
    private boolean zzb;
    private final zzhr zzc;

    public zzfd(zzhu r1, StandardIntegrityManager r2, zzhr r3, zzfp r4) {
        this.zzc = r3;
        this.zza = r4;
        this.zzb = true;
    }

    public static final /* synthetic */ zzfp zzi(zzfd r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzhr zzj(zzfd r0) {
        return r0.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final int zza() {
        return 2;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final boolean zzb() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzc(String r1, zzaka r2, Continuation r3) {
        return new zzmc(new zzfb(r2, this, null));
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzd(Exception r1, Continuation r2) {
        Throwable r22 = r1.getCause();
        if (r22 == null) goto L7;
        r1 = r22;
    L7:
        if ((r1 instanceof StandardIntegrityException) == false) goto L37;
        int r12 = ((StandardIntegrityException) r1).getErrorCode();
        if (r12 != (-100)) goto L11;
        int r13 = 44;
    L38:
        zzalw r23 = zzalx.zza();
        r23.zzc(r13);
        r23.zzb(15);
        return zzew.zzb(this, (zzalx) r23.zzp());
    L11:
        if (r12 != (-12)) goto L13;
        r13 = 39;
        goto L38
    L13:
        if (r12 != (-3)) goto L15;
        r13 = 30;
        goto L38
    L15:
        if (r12 != (-2)) goto L17;
        r13 = 29;
        goto L38
    L17:
        if (r12 == (-1)) goto L32;
        switch(r12) {
            case -19: goto L31;
            case -18: goto L30;
            case -17: goto L29;
            case -16: goto L28;
            case -15: goto L27;
            case -14: goto L26;
            default: goto L19;
        };
    L19:
        switch(r12) {
            case -9: goto L25;
            case -8: goto L24;
            case -7: goto L23;
            case -6: goto L22;
            case -5: goto L21;
            default: goto L20;
        };
    L20:
        r13 = 2;
        goto L38
    L21:
        r13 = 32;
        goto L38
    L22:
        r13 = 33;
        goto L38
    L23:
        r13 = 34;
        goto L38
    L24:
        r13 = 35;
        goto L38
    L25:
        r13 = 36;
        goto L38
    L26:
        r13 = 41;
        goto L38
    L27:
        r13 = 42;
        goto L38
    L28:
        r13 = 43;
        goto L38
    L29:
        r13 = 52;
        goto L38
    L30:
        r13 = 53;
        goto L38
    L31:
        r13 = 54;
        goto L38
    L32:
        r13 = 28;
        goto L38
    L37:
        r13 = 45;
        goto L38
    }

    public final void zze(boolean r1) {
        this.zzb = false;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzk(zzakk r2, Continuation r3) {
        return new zzmc(new zzfc(this, r2, null));
    }
}
