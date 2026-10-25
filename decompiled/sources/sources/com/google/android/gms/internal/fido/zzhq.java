package com.google.android.gms.internal.fido;

import com.google.android.gms.cast.MediaStatus;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

/* loaded from: classes4.dex */
abstract class zzhq {
    public static final zzhp zza(InputStream r0, zzhs r1) {
        zzhp r02 = zzb(r0, r1);     // Catch: Throwable -> L5
        r1.close();     // Catch: IOException -> L8
    L4:
        return r02;
    L5:
        th = move-exception;
        r1.close();     // Catch: IOException -> L9
    L7:
        throw th;
    }

    private static final zzhp zzb(InputStream r11, zzhs r12) {
        zzhr r2 = r12.zzd();     // Catch: IOException -> L78
        if (r2 == null) goto L77;
        byte r3 = r2.zza();     // Catch: RuntimeException -> L22 IOException -> L24
        byte r4 = r2.zzb();     // Catch: RuntimeException -> L22 IOException -> L24
        int r8 = 0;
        if (r4 != Byte.MIN_VALUE) goto L8;
        long r1 = r12.zza();     // Catch: RuntimeException -> L22 IOException -> L24
        if (r1 > 1000) goto L73;
        zzc(r3, r1, r11, r12);     // Catch: RuntimeException -> L22 IOException -> L24
        zzhp[] r32 = new zzhp[(int) r1];     // Catch: RuntimeException -> L22 IOException -> L24
    L68:
        if (r8 >= r1) goto L71;
        r32[r8] = zzb(r11, r12);     // Catch: RuntimeException -> L22 IOException -> L24
        r8 = r8 + 1;     // Catch: RuntimeException -> L22 IOException -> L24
        goto L68
    L71:
        return new zzhg(zzcc.zzi(r32));
    L73:
        throw new zzhj("Parser being asked to read a large CBOR array");     // Catch: RuntimeException -> L22 IOException -> L24
    L8:
        if (r4 != (-96)) goto L10;
        long r13 = r12.zzc();     // Catch: RuntimeException -> L22 IOException -> L24
        if (r13 > 1000) goto L63;
        zzc(r3, r13, r11, r12);     // Catch: RuntimeException -> L22 IOException -> L24
        int r33 = (int) r13;     // Catch: RuntimeException -> L22 IOException -> L24
        zzhl[] r42 = new zzhl[r33];     // Catch: RuntimeException -> L22 IOException -> L24
        zzhp r5 = null;
        int r6 = 0;
    L44:
        if (r6 >= r13) goto L53;
        zzhp r7 = zzb(r11, r12);     // Catch: RuntimeException -> L22 IOException -> L24
        if (r5 == null) goto L52;
        if (r7.compareTo(r5) > 0) goto L52;
        throw new zzhf(String.format("Keys in CBOR Map not in strictly ascending natural order:\nPrevious key: %s\nCurrent key: %s", new Object[]{r5.toString(), r7.toString()}));     // Catch: RuntimeException -> L22 IOException -> L24
    L52:
        r42[r6] = new zzhl(r7, zzb(r11, r12));     // Catch: RuntimeException -> L22 IOException -> L24
        r6 = r6 + 1;     // Catch: RuntimeException -> L22 IOException -> L24
        r5 = r7;
        goto L44
    L53:
        TreeMap r112 = new TreeMap();     // Catch: RuntimeException -> L22 IOException -> L24
    L54:
        if (r8 >= r33) goto L61;
        zzhl r122 = r42[r8];     // Catch: RuntimeException -> L22 IOException -> L24
        if (r112.containsKey(r122.zza()) == true) goto L59;
        r112.put(r122.zza(), r122.zzb());     // Catch: RuntimeException -> L22 IOException -> L24
        r8 = r8 + 1;     // Catch: RuntimeException -> L22 IOException -> L24
        goto L54
    L59:
        throw new zzhf("Attempted to add duplicate key to canonical CBOR Map.");     // Catch: RuntimeException -> L22 IOException -> L24
    L61:
        return new zzhm(zzcj.zzf(r112));
    L63:
        throw new zzhj("Parser being asked to read a large CBOR map");     // Catch: RuntimeException -> L22 IOException -> L24
    L10:
        if (r4 == (-64)) goto L39;
        if (r4 == (-32)) goto L37;
        if (r4 != 0) goto L15;
    L30:
        long r14 = r12.zzb();     // Catch: RuntimeException -> L22 IOException -> L24
        if (r14 <= 0) goto L33;
        long r43 = r14;
    L34:
        zzc(r3, r43, r11, r12);     // Catch: RuntimeException -> L22 IOException -> L24
        return new zzhk(r14);
    L33:
        r43 = ~r14;     // Catch: RuntimeException -> L22 IOException -> L24
        goto L34
    L15:
        if (r4 == 32) goto L30;
        if (r4 != 64) goto L19;
        byte[] r15 = r12.zzg();     // Catch: RuntimeException -> L22 IOException -> L24
        int r22 = r15.length;     // Catch: RuntimeException -> L22 IOException -> L24
        zzc(r3, r22, r11, r12);     // Catch: RuntimeException -> L22 IOException -> L24
        return new zzhi(zzgx.zzl(r15, 0, r22));
    L19:
        if (r4 != 96) goto L27;
        String r16 = r12.zze();     // Catch: RuntimeException -> L22 IOException -> L24
        zzc(r3, r16.length(), r11, r12);     // Catch: RuntimeException -> L22 IOException -> L24
        return new zzhn(r16);
    L27:
        throw new zzhj("Unidentifiable major type: " + r2.zzc());     // Catch: RuntimeException -> L22 IOException -> L24
    L37:
        return new zzhh(r12.zzf());
    L39:
        throw new zzhj("Tags are currently unsupported");     // Catch: RuntimeException -> L22 IOException -> L24
    L24:
        e = e;
    L75:
        throw new zzhj("Error in decoding CborValue from bytes", e);
    L22:
        e = e;
        goto L75
    L77:
        throw new zzhj("Parser being asked to parse an empty input stream");
    L78:
        e = move-exception;
        throw new zzhj("Error in decoding CborValue from bytes", e);
    }

    private static final void zzc(byte r0, long r1, InputStream r3, zzhs r4) {
        switch(r0) {
            case 24: goto L20;
            case 25: goto L15;
            case 26: goto L10;
            case 27: goto L5;
            default: goto L24;
        };
    L24:
        return;
    L5:
        if (r1 >= 4294967296L) goto L25;
        zzhq$$ExternalSyntheticBUOutline0.m(r1, " after add info could have been represented in 0-4 additional bytes, but used 8");
        return;
    L25:
        return;
    L10:
        if (r1 >= MediaStatus.COMMAND_FOLLOW) goto L26;
        zzhq$$ExternalSyntheticBUOutline0.m(r1, " after add info could have been represented in 0-2 additional bytes, but used 4");
        return;
    L26:
        return;
    L15:
        if (r1 >= 256) goto L27;
        zzhq$$ExternalSyntheticBUOutline0.m(r1, " after add info could have been represented in 0-1 additional bytes, but used 2");
        return;
    L27:
        return;
    L20:
        if (r1 < 24) goto L22;
        return;
    L22:
        zzhq$$ExternalSyntheticBUOutline0.m(r1, " after add info could have been represented in 0 additional bytes, but used 1");
    }
}
