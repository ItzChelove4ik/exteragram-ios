package com.google.android.recaptcha.internal;

import java.util.ArrayDeque;
import java.util.Optional;

/* loaded from: classes5.dex */
public final class zzam {
    public final zzh zza;
    public final zzai zzb;
    public final zzaf zzc;
    public final zzaa zzd;

    public zzam(zzh r10, zzai r11, zzaa r12) {
        int[] r0 = {574682290, 203330227, 828655681, 488281842, 1367368777, 2113644785, 73912900, 1891066487, 382926234};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        this.zza = r10;
        this.zzb = r11;
        this.zzd = r12;
        this.zzc = new zzaf((r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)) ^ (r02 % 382926234));
    }

    public final Optional zza() {
        ArrayDeque r0 = this.zzc.zza;     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
        if (r0.isEmpty() == true) goto L17;
        zzac r02 = (zzac) r0.pop();     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
        long r1 = r02.zza;     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
        long r3 = r02.zzb;     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
        long r5 = r02.zzc;     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
        zzai r03 = this.zzb;     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
        if (r03.zzb < r3) goto L7;
        this.zzd.zza(r1);     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
        if (r5 != 0) goto L15;
    L11:
        if (r03.zzb <= r3) goto L15;
        r03.zzc();     // Catch: Throwable -> L18 zzag -> L21 zzae -> L24
    L15:
        return Optional.empty();
    L7:
        return Optional.of(zzc.zzG);
    L17:
        throw new zzae();     // Catch: Throwable -> L18 Throwable -> L18 zzag -> L21 zzae -> L24
    L18:
        e = move-exception;
        throw new AssertionError(zzt.zza("CEiv6BFfPnitUE+D"), e);
    L24:
        zzc r9 = zzc.zzw;
    L23:
        return Optional.of(r9);
    L21:
        r9 = zzc.zzG;
        goto L23
    }
}
