package com.google.android.gms.internal.mlkit_genai_prompt;

import android.annotation.SuppressLint;
import j$.time.Duration;

/* loaded from: classes4.dex */
public interface zzaty {
    @SuppressLint({"NonSdkVisibleApi"})
    static zzant zzk(int r1) {
        if (r1 == 27) goto L76;
        if (r1 == 28) goto L74;
        if (r1 == 30) goto L72;
        if (r1 == 501) goto L70;
        switch(r1) {
            case -102: goto L68;
            case -101: goto L66;
            case -100: goto L64;
            default: goto L11;
        };
    L11:
        switch(r1) {
            case 2: goto L62;
            case 3: goto L60;
            case 4: goto L58;
            case 5: goto L56;
            case 6: goto L54;
            case 7: goto L52;
            case 8: goto L50;
            case 9: goto L48;
            case 10: goto L46;
            case 11: goto L44;
            case 12: goto L42;
            case 13: goto L40;
            case 14: goto L38;
            case 15: goto L36;
            case 16: goto L34;
            case 17: goto L32;
            case 18: goto L30;
            default: goto L12;
        };
    L12:
        switch(r1) {
            case 601: goto L28;
            case 602: goto L26;
            case 603: goto L24;
            case 604: goto L22;
            case 605: goto L20;
            case 606: goto L18;
            case 607: goto L16;
            default: goto L14;
        };
    L14:
        return zzant.zzaG;
    L16:
        return zzant.zzaA;
    L18:
        return zzant.zzaz;
    L20:
        return zzant.zzay;
    L22:
        return zzant.zzad;
    L24:
        return zzant.zzax;
    L26:
        return zzant.zzaw;
    L28:
        return zzant.zzav;
    L30:
        return zzant.zzau;
    L32:
        return zzant.zzat;
    L34:
        return zzant.zzas;
    L36:
        return zzant.zzai;
    L38:
        return zzant.zzar;
    L40:
        return zzant.zzaq;
    L42:
        return zzant.zzah;
    L44:
        return zzant.zzaj;
    L46:
        return zzant.zzap;
    L48:
        return zzant.zzab;
    L50:
        return zzant.zzae;
    L52:
        return zzant.zzac;
    L54:
        return zzant.zzao;
    L56:
        return zzant.zzan;
    L58:
        return zzant.zzag;
    L60:
        return zzant.zzam;
    L62:
        return zzant.zzal;
    L64:
        return zzant.zzak;
    L66:
        return zzant.zzaB;
    L68:
        return zzant.zzaC;
    L70:
        return zzant.zzaf;
    L72:
        return zzant.zzaF;
    L74:
        return zzant.zzaE;
    L76:
        return zzant.zzaD;
    }

    void zza();

    void zzb(int r1, int r2);

    void zzc();

    void zzd(zzars r1, Object r2, Object r3);

    default void zzg(int r1, int r2) {
        throw new UnsupportedOperationException("Count tokens not supported for this logger");
    }

    default void zzh(Object r1, int r2, int r3) {
        zzb(r2, r3);
    }

    default void zzi(zzars r1, Object r2, int r3, Duration r4) {
        throw new UnsupportedOperationException("Count tokens not supported for this logger");
    }

    default void zzj(Object r1, int r2, int r3) {
        zzg(r2, r3);
    }
}
