package com.google.android.recaptcha.internal;

import java.util.Comparator;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzan implements Comparator {
    private final boolean zza;

    public zzan(boolean r1) {
        this.zza = r1;
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object r12, Object r13) {
        int[] r0 = {1238241306, 1544407528, 274970277, -295806647, -1552805835, 183382075, 57564258, 871328242, 247849492};
        int r2 = r0[0];
        int r4 = r0[1];
        int r5 = r0[2];
        int r6 = r0[3];
        int r7 = r0[4];
        int r8 = r0[5];
        int r9 = r0[6];
        int r02 = r0[7];
        int r92 = r9 + ((((r4 & (~r2)) | r5) + ((r2 & r6) | r7)) - r8);
        int r03 = r02 % 247849492;
        zzar r122 = (zzar) r12;
        zzar r132 = (zzar) r13;
        int r22 = r122.zza;
        if (r22 != r132.zza) goto L39;
        int r04 = r03 ^ r92;
        if (r22 == 0) goto L35;
        switch((r22 + r04)) {
            case 0: goto L33;
            case 1: goto L26;
            case 2: goto L24;
            case 3: goto L20;
            case 4: goto L19;
            case 5: goto L12;
            case 6: goto L10;
            default: goto L8;
        };
    L8:
        return 0;
    L19:
        Object r123 = r122.zzo();     // Catch: zzao -> L36
        Object r133 = r132.zzo();     // Catch: zzao -> L36
        Comparator r11 = zzpl.zza(this);     // Catch: zzao -> L36
    L22:
        return r11.compare(r123, r133);
    L20:
        r11 = zzv.zzc;     // Catch: zzao -> L36
        r123 = r122.zzn();     // Catch: zzao -> L36
        r133 = r132.zzn();     // Catch: zzao -> L36
        goto L22
    L33:
        return 0;
    L10:
        return Double.compare(r122.zzq(), r132.zzq());
    L12:
        if (this.zza == false) goto L18;
        if (r122.zzp() == r132.zzp()) goto L16;
        return 1;
    L16:
        return 0;
    L18:
        throw new IllegalArgumentException();     // Catch: zzao -> L36
    L24:
        return Long.compare(r122.zzm(), r132.zzm());
    L26:
        if (this.zza == false) goto L32;
        if (r122.zzl() == r132.zzl()) goto L30;
        return 1;
    L30:
        return 0;
    L32:
        throw new IllegalArgumentException();     // Catch: zzao -> L36
    L35:
        throw null;     // Catch: zzao -> L36
    L36:
        e = move-exception;
        throw new AssertionError(zzt.zza("CEiv6BFfPnitUE+D"), e);
    L39:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
