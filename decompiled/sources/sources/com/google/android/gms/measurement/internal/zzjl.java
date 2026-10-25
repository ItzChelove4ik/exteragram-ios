package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public final class zzjl {
    public static final zzjl zza = null;
    private final EnumMap zzb;
    private final int zzc;

    static {
        zza = new zzjl(null, null, 100);
    }

    public zzjl(Boolean r3, Boolean r4, int r5) {
        EnumMap r32 = new EnumMap(zzjk.class);
        this.zzb = r32;
        r32.put(zzjk.zza, zzh(null));
        r32.put(zzjk.zzb, zzh(null));
        this.zzc = r5;
    }

    private zzjl(EnumMap r3, int r4) {
        EnumMap r0 = new EnumMap(zzjk.class);
        this.zzb = r0;
        r0.putAll(r3);
        this.zzc = r4;
    }

    public static zzjl zza(zzji r1, zzji r2, int r3) {
        EnumMap r32 = new EnumMap(zzjk.class);
        r32.put(zzjk.zza, r1);
        r32.put(zzjk.zzb, r2);
        return new zzjl(r32, -10);
    }

    public static String zzd(int r1) {
        if (r1 != (-30)) goto L5;
        return "TCF";
    L5:
        if (r1 != (-20)) goto L7;
        return "API";
    L7:
        if (r1 == (-10)) goto L25;
        if (r1 != 0) goto L10;
        return "1P_API";
    L10:
        if (r1 != 30) goto L12;
        return "1P_INIT";
    L12:
        if (r1 != 90) goto L14;
        return "REMOTE_CONFIG";
    L14:
        if (r1 == 100) goto L17;
        return "OTHER";
    L17:
        return "UNKNOWN";
    L25:
        return "MANIFEST";
    }

    public static zzjl zze(Bundle r6, int r7) {
        if (r6 == null) goto L4;
        EnumMap r0 = new EnumMap(zzjk.class);
        zzjk[] r1 = zzjj.zza.zzb();
        int r2 = r1.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L9;
        zzjk r4 = r1[r3];
        r0.put(r4, zzg(r6.getString(r4.zze)));
        r3 = r3 + 1;
        goto L6
    L9:
        return new zzjl(r0, r7);
    L4:
        return new zzjl(null, null, r7);
    }

    public static zzjl zzf(String r7, int r8) {
        EnumMap r0 = new EnumMap(zzjk.class);
        zzjk[] r1 = zzjj.zza.zza();
        int r2 = 0;
    L4:
        if (r2 >= r1.length) goto L14;
        if (r7 != null) goto L7;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        zzjk r4 = r1[r2];
        int r5 = r2 + 2;
        if (r5 >= r3.length()) goto L11;
        r0.put(r4, zzj(r3.charAt(r5)));
    L12:
        r2 = r2 + 1;
        goto L4
    L11:
        r0.put(r4, zzji.zza);
        goto L12
    L7:
        r3 = r7;
        goto L8
    L14:
        return new zzjl(r0, r8);
    }

    public static zzji zzg(String r1) {
        if (r1 != null) goto L6;
        return zzji.zza;
    L6:
        if (r1.equals("granted") == false) goto L10;
        return zzji.zzd;
    L10:
        if (r1.equals("denied") == false) goto L14;
        return zzji.zzc;
    L14:
        return zzji.zza;
    }

    public static zzji zzh(Boolean r0) {
        if (r0 != null) goto L6;
        return zzji.zza;
    L6:
        if (r0.booleanValue() == false) goto L10;
        return zzji.zzd;
    L10:
        return zzji.zzc;
    }

    public static String zzi(zzji r1) {
        int r12 = r1.ordinal();
        if (r12 != 2) goto L5;
        return "denied";
    L5:
        if (r12 == 3) goto L8;
        return null;
    L8:
        return "granted";
    }

    public static zzji zzj(char r1) {
        if (r1 == '+') goto L15;
        if (r1 == '0') goto L13;
        if (r1 == '1') goto L11;
        return zzji.zza;
    L11:
        return zzji.zzd;
    L13:
        return zzji.zzc;
    L15:
        return zzji.zzb;
    }

    public static char zzm(zzji r1) {
        if (r1 == null) goto L16;
        int r12 = r1.ordinal();
        if (r12 != 1) goto L6;
        return SignatureVisitor.EXTENDS;
    L6:
        if (r12 != 2) goto L8;
        return '0';
    L8:
        if (r12 != 3) goto L18;
        return '1';
    L18:
        return SignatureVisitor.SUPER;
    L16:
        return SignatureVisitor.SUPER;
    }

    public static boolean zzu(int r2, int r3) {
        int r0 = -30;
        if (r2 != (-20)) goto L6;
        if (r3 == (-30)) goto L15;
        r2 = -20;
        goto L6
    L15:
        return true;
    L6:
        if (r2 != (-30)) goto L9;
        if (r3 == (-20)) goto L17;
    L10:
        if (r0 == r3) goto L19;
        if (r2 < r3) goto L18;
        return false;
    L18:
        return true;
    L19:
        return true;
    L17:
        return true;
    L9:
        r0 = r2;
        goto L10
    }

    public final boolean equals(Object r8) {
        if ((r8 instanceof zzjl) == true) goto L5;
        return false;
    L5:
        zzjl r82 = (zzjl) r8;
        zzjk[] r0 = zzjj.zza.zzb();
        int r2 = r0.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L12;
        zzjk r4 = r0[r3];
        if (this.zzb.get(r4) != r82.zzb.get(r4)) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return false;
    L12:
        if (this.zzc != r82.zzc) goto L15;
        return true;
    L15:
        return false;
    }

    public final int hashCode() {
        Iterator r0 = this.zzb.values().iterator();
        int r2 = this.zzc * 17;
    L4:
        if (r0.hasNext() == false) goto L6;
        r2 = (r2 * 31) + ((zzji) r0.next()).hashCode();
        goto L4
    L6:
        return r2;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("source=");
        r0.append(zzd(this.zzc));
        zzjk[] r1 = zzjj.zza.zzb();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        zzjk r4 = r1[r3];
        r0.append(",");
        r0.append(r4.zze);
        r0.append("=");
        zzji r42 = (zzji) this.zzb.get(r4);
        if (r42 != null) goto L7;
        r42 = zzji.zza;
    L7:
        r0.append(r42);
        r3 = r3 + 1;
        goto L3
    L9:
        return r0.toString();
    }

    public final int zzb() {
        return this.zzc;
    }

    public final boolean zzc() {
        Iterator r2 = this.zzb.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        if (((zzji) r2.next()) == zzji.zza) goto L4;
        return true;
    L9:
        return false;
    }

    public final String zzk() {
        StringBuilder r0 = new StringBuilder("G1");
        zzjk[] r1 = zzjj.zza.zza();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L19;
        zzjk r4 = r1[r3];
        zzji r42 = (zzji) this.zzb.get(r4);
        char r5 = SignatureVisitor.SUPER;
        if (r42 == null) goto L17;
        int r43 = r42.ordinal();
        if (r43 == 0) goto L17;
        if (r43 != 1) goto L11;
    L16:
        r5 = '1';
        goto L17
    L11:
        if (r43 != 2) goto L13;
        r5 = '0';
        goto L17
    L13:
        if (r43 == 3) goto L16;
    L17:
        r0.append(r5);
        r3 = r3 + 1;
        goto L3
    L19:
        return r0.toString();
    }

    public final String zzl() {
        StringBuilder r0 = new StringBuilder("G1");
        zzjk[] r1 = zzjj.zza.zza();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L6;
        zzjk r4 = r1[r3];
        r0.append(zzm((zzji) this.zzb.get(r4)));
        r3 = r3 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    public final Bundle zzn() {
        Bundle r0 = new Bundle();
        Iterator r3 = this.zzb.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        Map.Entry r1 = (Map.Entry) r3.next();
        String r2 = zzi((zzji) r1.getValue());
        if (r2 == null) goto L4;
        r0.putString(((zzjk) r1.getKey()).zze, r2);
        goto L4
    L8:
        return r0;
    }

    public final boolean zzo(zzjk r1) {
        if (((zzji) this.zzb.get(r1)) != zzji.zzc) goto L6;
        return false;
    L6:
        return true;
    }

    public final zzji zzp() {
        zzji r1 = (zzji) this.zzb.get(zzjk.zza);
        if (r1 == null) goto L5;
        return r1;
    L5:
        return zzji.zza;
    }

    public final zzji zzq() {
        zzji r1 = (zzji) this.zzb.get(zzjk.zzb);
        if (r1 == null) goto L5;
        return r1;
    L5:
        return zzji.zza;
    }

    public final boolean zzr(zzjl r8) {
        EnumMap r7 = this.zzb;
        zzjk[] r0 = (zzjk[]) r7.keySet().toArray(new zzjk[0]);
        int r2 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L10;
        zzjk r4 = r0[r3];
        zzji r5 = (zzji) r7.get(r4);
        zzji r42 = (zzji) r8.zzb.get(r4);
        zzji r6 = zzji.zzc;
        if (r5 != r6) goto L9;
        if (r42 == r6) goto L9;
        return true;
    L9:
        r3 = r3 + 1;
        goto L3
    L10:
        return false;
    }

    public final zzjl zzs(zzjl r9) {
        EnumMap r0 = new EnumMap(zzjk.class);
        zzjk[] r1 = zzjj.zza.zzb();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L26;
        zzjk r4 = r1[r3];
        zzji r5 = (zzji) this.zzb.get(r4);
        zzji r6 = (zzji) r9.zzb.get(r4);
        if (r5 == null) goto L14;
        if (r6 == null) goto L22;
        zzji r7 = zzji.zza;
        if (r5 == r7) goto L14;
        if (r6 == r7) goto L22;
        zzji r72 = zzji.zzb;
        if (r5 == r72) goto L14;
        if (r6 == r72) goto L22;
        zzji r73 = zzji.zzc;
        if (r5 == r73) goto L21;
        if (r6 == r73) goto L21;
        r5 = zzji.zzd;
    L21:
        r5 = r73;
    L22:
        if (r5 == null) goto L24;
        r0.put(r4, r5);
    L24:
        r3 = r3 + 1;
    L14:
        r5 = r6;
        goto L22
    L26:
        return new zzjl(r0, 100);
    }

    public final zzjl zzt(zzjl r8) {
        EnumMap r0 = new EnumMap(zzjk.class);
        zzjk[] r1 = zzjj.zza.zzb();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L11;
        zzjk r4 = r1[r3];
        zzji r5 = (zzji) this.zzb.get(r4);
        if (r5 != zzji.zza) goto L7;
        r5 = (zzji) r8.zzb.get(r4);
    L7:
        if (r5 == null) goto L9;
        r0.put(r4, r5);
    L9:
        r3 = r3 + 1;
        goto L3
    L11:
        return new zzjl(r0, this.zzc);
    }
}
