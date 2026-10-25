package com.google.android.recaptcha.internal;

import java.util.ArrayDeque;
import java.util.Optional;

/* loaded from: classes5.dex */
public final class zzaf {
    public final ArrayDeque zza;

    public zzaf(int r1) {
        this.zza = new ArrayDeque();
    }

    public final void zza(long r16, long r18, long r20) {
        int[] r0 = {2077486715, 1348529152, 196553492, 1547748370, 218381171, -1556364309, 1117142618, 1713258270, 1573363368};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r72 = r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6);
        zzac r8 = new zzac(r16, r18, r20);
        ArrayDeque r15 = this.zza;
        if (r15.size() >= ((r02 % 1573363368) ^ r72)) goto L7;
        r15.push(r8);
        return;
    L7:
        throw new zzad();
    }

    public final zzac zzb() {
        return (zzac) Optional.ofNullable((zzac) this.zza.peek()).orElseThrow(zzab.zza);
    }
}
