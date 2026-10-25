package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzba {
    public static final zzba zza = null;
    private final int zzb;
    private final String zzc;
    private final Boolean zzd;
    private final String zze;
    private final EnumMap zzf;

    static {
        zza = new zzba(null, 100, null, null);
    }

    public zzba(Boolean r3, int r4, Boolean r5, String r6) {
        EnumMap r0 = new EnumMap(zzjk.class);
        this.zzf = r0;
        r0.put(zzjk.zzc, zzjl.zzh(r3));
        this.zzb = r4;
        this.zzc = zzl();
        this.zzd = r5;
        this.zze = r6;
    }

    private zzba(EnumMap r3, int r4, Boolean r5, String r6) {
        EnumMap r0 = new EnumMap(zzjk.class);
        this.zzf = r0;
        r0.putAll(r3);
        this.zzb = r4;
        this.zzc = zzl();
        this.zzd = r5;
        this.zze = r6;
    }

    public static zzba zza(zzji r2, int r3) {
        EnumMap r32 = new EnumMap(zzjk.class);
        r32.put(zzjk.zzc, r2);
        return new zzba(r32, -10, null, null);
    }

    public static zzba zzg(String r9) {
        if (r9 == null) goto L12;
        if (r9.length() <= 0) goto L12;
        String[] r92 = r9.split(":");
        int r1 = Integer.parseInt(r92[0]);
        EnumMap r2 = new EnumMap(zzjk.class);
        zzjk[] r3 = zzjj.zzb.zza();
        int r4 = r3.length;
        int r5 = 1;
        int r6 = 0;
    L7:
        if (r6 >= r4) goto L10;
        r2.put(r3[r6], zzjl.zzj(r92[r5].charAt(0)));
        r6 = r6 + 1;
        r5 = r5 + 1;
        goto L7
    L10:
        return new zzba(r2, r1, null, null);
    L12:
        return zza;
    }

    public static zzba zzh(Bundle r7, int r8) {
        Boolean r0 = null;
        if (r7 == null) goto L5;
        EnumMap r1 = new EnumMap(zzjk.class);
        zzjk[] r2 = zzjj.zzb.zza();
        int r3 = r2.length;
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L10;
        zzjk r5 = r2[r4];
        r1.put(r5, zzjl.zzg(r7.getString(r5.zze)));
        r4 = r4 + 1;
        goto L7
    L10:
        if (r7.containsKey("is_dma_region") == false) goto L13;
        r0 = Boolean.valueOf(r7.getString("is_dma_region"));
    L13:
        return new zzba(r1, r8, r0, r7.getString("cps_display_str"));
    L5:
        return new zzba(null, r8, null, null);
    }

    public static Boolean zzi(Bundle r1) {
        if (r1 == null) goto L17;
        zzji r12 = zzjl.zzg(r1.getString("ad_personalization"));
        if (r12 == null) goto L15;
        int r13 = r12.ordinal();
        if (r13 == 2) goto L14;
        if (r13 == 3) goto L12;
        return null;
    L12:
        return Boolean.TRUE;
    L14:
        return Boolean.FALSE;
    L15:
        return null;
    L17:
        return null;
    }

    private final String zzl() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.zzb);
        zzjk[] r1 = zzjj.zzb.zza();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L6;
        zzjk r4 = r1[r3];
        r0.append(":");
        r0.append(zzjl.zzm((zzji) this.zzf.get(r4)));
        r3 = r3 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof zzba) == false) goto L14;
        zzba r32 = (zzba) r3;
        if (this.zzc.equalsIgnoreCase(r32.zzc) == true) goto L8;
        return false;
    L8:
        if (Objects.equals(this.zzd, r32.zzd) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.zze, r32.zze);
    L14:
        return false;
    }

    public final int hashCode() {
        Boolean r0 = this.zzd;
        if (r0 != null) goto L6;
        int r02 = 3;
    L9:
        String r1 = this.zze;
        if (r1 != null) goto L12;
        int r12 = 17;
    L13:
        int r2 = this.zzc.hashCode();
        return (r2 + (r02 * 29)) + (r12 * 137);
    L12:
        r12 = r1.hashCode();
        goto L13
    L6:
        if (true == r0.booleanValue()) goto L8;
        r02 = 13;
        goto L9
    L8:
        r02 = 7;
        goto L9
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("source=");
        r0.append(zzjl.zzd(this.zzb));
        zzjk[] r1 = zzjj.zzb.zza();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L21;
        zzjk r4 = r1[r3];
        r0.append(",");
        r0.append(r4.zze);
        r0.append("=");
        zzji r42 = (zzji) this.zzf.get(r4);
        if (r42 != null) goto L7;
        r0.append("uninitialized");
    L20:
        r3 = r3 + 1;
        goto L3
    L7:
        int r43 = r42.ordinal();
        if (r43 != 0) goto L10;
        r0.append("uninitialized");
        goto L20
    L10:
        if (r43 != 1) goto L12;
        r0.append("eu_consent_policy");
        goto L20
    L12:
        if (r43 != 2) goto L14;
        r0.append("denied");
        goto L20
    L14:
        if (r43 != 3) goto L20;
        r0.append("granted");
        goto L20
    L21:
        Boolean r12 = this.zzd;
        if (r12 == null) goto L24;
        r0.append(",isDmaRegion=");
        r0.append(r12);
    L24:
        String r6 = this.zze;
        if (r6 == null) goto L28;
        r0.append(",cpsDisplayStr=");
        r0.append(r6);
    L28:
        return r0.toString();
    }

    public final int zzb() {
        return this.zzb;
    }

    public final zzji zzc() {
        zzji r1 = (zzji) this.zzf.get(zzjk.zzc);
        if (r1 == null) goto L5;
        return r1;
    L5:
        return zzji.zza;
    }

    public final boolean zzd() {
        Iterator r2 = this.zzf.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        if (((zzji) r2.next()) == zzji.zza) goto L4;
        return true;
    L9:
        return false;
    }

    public final String zze() {
        return this.zzc;
    }

    public final Bundle zzf() {
        Bundle r0 = new Bundle();
        Iterator r1 = this.zzf.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        Map.Entry r2 = (Map.Entry) r1.next();
        String r3 = zzjl.zzi((zzji) r2.getValue());
        if (r3 == null) goto L4;
        r0.putString(((zzjk) r2.getKey()).zze, r3);
        goto L4
    L8:
        Boolean r12 = this.zzd;
        if (r12 == null) goto L11;
        r0.putString("is_dma_region", r12.toString());
    L11:
        String r4 = this.zze;
        if (r4 == null) goto L14;
        r0.putString("cps_display_str", r4);
    L14:
        return r0;
    }

    public final Boolean zzj() {
        return this.zzd;
    }

    public final String zzk() {
        return this.zze;
    }
}
