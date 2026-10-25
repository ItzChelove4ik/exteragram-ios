package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.cast.MediaError;
import j$.time.Duration;

/* loaded from: classes4.dex */
public final class zzdo extends Exception {
    private final int zza;
    private final zzbq zzb;

    private zzdo(int r6, int r7, String r8, Throwable r9, zzbq r10) {
        Integer r0 = Integer.valueOf(r6);
        String r2 = "UNKNOWN";
        if (r6 != 1) goto L5;
        String r62 = "DOWNLOAD_ERROR";
    L18:
        Integer r1 = Integer.valueOf(r7);
        if (r7 != 18) goto L21;
    L40:
        r2 = "INTERNAL_ERROR";
    L54:
        super(String.format("AICore failed with error type %s-%s and error code %s-%s: %s", new Object[]{r0, r62, r1, r2, r8}), r9);
        this.zza = r7;
        this.zzb = r10;
        return;
    L21:
        if (r7 == 20) goto L40;
        if (r7 != 501) goto L25;
        r2 = "NOT_ENOUGH_DISK_SPACE";
        goto L54
    L25:
        if (r7 == 29) goto L40;
        if (r7 == 30) goto L52;
        switch(r7) {
            case 2: goto L51;
            case 3: goto L50;
            case 4: goto L49;
            case 5: goto L48;
            case 6: goto L47;
            case 7: goto L46;
            case 8: goto L45;
            case 9: goto L44;
            case 10: goto L43;
            case 11: goto L42;
            case 12: goto L41;
            default: goto L29;
        };
    L29:
        switch(r7) {
            case 14: goto L40;
            case 15: goto L39;
            case 16: goto L38;
            default: goto L30;
        };
    L30:
        switch(r7) {
            case 601: goto L37;
            case 602: goto L36;
            case 603: goto L35;
            case 604: goto L34;
            case 605: goto L33;
            case 606: goto L32;
            default: goto L54;
        };
    L32:
        r2 = "FEATURE_NOT_FOUND";
        goto L54
    L33:
        r2 = "NULL_BINDING";
        goto L54
    L34:
        r2 = "NEEDS_SYSTEM_UPDATE";
        goto L54
    L35:
        r2 = "BINDING_DIED";
        goto L54
    L36:
        r2 = "SERVICE_DISCONNECTED";
        goto L54
    L37:
        r2 = "BINDING_FAILURE";
        goto L54
    L38:
        r2 = MediaError.ERROR_REASON_NOT_SUPPORTED;
        goto L54
    L39:
        r2 = "RESPONSE_GENERATION_ERROR";
        goto L54
    L41:
        r2 = "REQUEST_TOO_LARGE";
        goto L54
    L42:
        r2 = "RESPONSE_PROCESSING_ERROR";
        goto L54
    L43:
        r2 = "SERVICE_PROCESSING_ERROR";
        goto L54
    L44:
        r2 = "BUSY";
        goto L54
    L45:
        r2 = "NOT_AVAILABLE";
        goto L54
    L46:
        r2 = "CANCELLED";
        goto L54
    L47:
        r2 = "IPC_ERROR";
        goto L54
    L48:
        r2 = "COMPUTE_ERROR";
        goto L54
    L49:
        r2 = "REQUEST_PROCESSING_ERROR";
        goto L54
    L50:
        r2 = "BAD_REQUEST";
        goto L54
    L51:
        r2 = "BAD_DATA";
        goto L54
    L52:
        r2 = "BACKGROUND_USE_BLOCKED";
        goto L54
    L5:
        if (r6 != 2) goto L7;
        r62 = "INFERENCE_ERROR";
        goto L18
    L7:
        if (r6 != 3) goto L9;
        r62 = "PREPARATION_ERROR";
        goto L18
    L9:
        if (r6 != 4) goto L11;
        r62 = "CONNECTION_ERROR";
        goto L18
    L11:
        if (r6 == 7) goto L13;
        r62 = "UNKNOWN";
        goto L18
    L13:
        r62 = "METADATA_ERROR";
        goto L18
    }

    public static zzdo zza(int r6, String r7, Throwable r8) {
        return new zzdo(1, r6, r7, r8, null);
    }

    public static zzdo zzb(int r6, String r7, Throwable r8) {
        return new zzdo(2, r6, r7, r8, null);
    }

    public static zzdo zzc(int r6, String r7, Throwable r8) {
        return new zzdo(3, r6, r7, r8, null);
    }

    public static zzdo zzd(int r6, String r7, Throwable r8) {
        return new zzdo(5, r6, r7, r8, null);
    }

    public static zzdo zze(int r6, String r7, Throwable r8) {
        return new zzdo(4, r6, r7, r8, null);
    }

    public static zzdo zzf(String r6) {
        return new zzdo(3, 606, r6, null, null);
    }

    public static zzdo zzi(zzq r6) {
        return new zzdo(r6.zza(), r6.zzb(), r6.getMessage(), null, r6.zzc());
    }

    public final int zzg() {
        return this.zza;
    }

    public final Duration zzh() {
        zzbq r2 = this.zzb;
        if (r2 == null) goto L9;
        if (r2.zza() == null) goto L9;
        return zznz.zza(r2.zza());
    L9:
        return Duration.ZERO;
    }
}
