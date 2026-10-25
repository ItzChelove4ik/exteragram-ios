package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.zaaz$$ExternalSyntheticBUOutline0;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class zzmw {
    private static final zzmw zza = null;
    private final ImmutableSortedSet zzb;

    static {
        zza = new zzmw(ImmutableSortedSet.of());
    }

    public zzmw(ImmutableSortedSet r1) {
        this.zzb = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzmw zza(zzmw r26, ImmutableMap r27) {
        if (r27.isEmpty() == false) goto L5;
        return r26;
    L5:
        HashMap r1 = Maps.newHashMap(r27);
        ImmutableSortedSet r0 = r26.zzb;
        ImmutableSortedSet.Builder r2 = ImmutableSortedSet.naturalOrder();
        UnmodifiableIterator r02 = r0.iterator();
    L6:
        Object r4 = null;
        if (r02.hasNext() == false) goto L28;
        zzmv r3 = (zzmv) r02.next();
        Object r14 = r1.remove(r3.zza());
        if (r14 == null) goto L10;
        if ((r14 instanceof String) == true) goto L13;
        if ((r14 instanceof byte[]) == true) goto L16;
        if ((r14 instanceof Boolean) == true) goto L19;
        if ((r14 instanceof Long) == true) goto L22;
        if ((r14 instanceof Double) == false) goto L26;
        r2.add(new zzmv(r3.zza, r3.zzb, 3, Double.doubleToRawLongBits(((Double) r14).doubleValue()), null));
        goto L6
    L26:
        String r03 = r3.zza();
        String r12 = r14.toString();
        zaaz$$ExternalSyntheticBUOutline0.m((String.valueOf(r03).length() + 46) + r12.length(), "Cannot serialize override for existing flag ", r03, ": ", r12);
        return null;
    L22:
        r2.add(new zzmv(r3.zza, r3.zzb, 2, ((Long) r14).longValue(), null));
        goto L6
    L19:
        r2.add(new zzmv(r3.zza, r3.zzb, ((Boolean) r14).booleanValue() ? 1 : 0, 0, null));
        goto L6
    L16:
        r2.add(new zzmv(r3.zza, r3.zzb, 5, 0, r14));
        goto L6
    L13:
        r2.add(new zzmv(r3.zza, r3.zzb, 4, 0, r14));
        goto L6
    L10:
        r2.add(r3);
        goto L6
    L28:
        Iterator r04 = r1.keySet().iterator();
    L30:
        if (r04.hasNext() == false) goto L86;
        String r32 = (String) r04.next();
        Object r13 = r1.get(r32);
        int r6 = r32.length();
        if (r6 > 19) goto L62;
        if (r6 != 0) goto L36;
    L34:
        Object r262 = r4;
    L35:
        long r16 = 0;
        long r19 = 0;
        zzmw r263 = r262;
    L64:
        if (r19 != r16) goto L66;
        String r21 = r32;
    L68:
        if ((r13 instanceof String) == false) goto L72;
        r2.add(new zzmv(r19, r21, 4, 0, r13));
    L70:
        r4 = r263;
        goto L30
    L72:
        if ((r13 instanceof byte[]) == false) goto L75;
        r2.add(new zzmv(r19, r21, 5, 0, r13));
        goto L70
    L75:
        if ((r13 instanceof Boolean) == false) goto L78;
        r2.add(new zzmv(r19, r21, ((Boolean) r13).booleanValue() ? 1 : 0, 0, null));
        goto L70
    L78:
        if ((r13 instanceof Long) == false) goto L81;
        r2.add(new zzmv(r19, r21, 2, ((Long) r13).longValue(), null));
        goto L70
    L81:
        if ((r13 instanceof Double) == false) goto L83;
        r2.add(new zzmv(r19, r21, 3, Double.doubleToRawLongBits(((Double) r13).doubleValue()), null));
        goto L70
    L83:
        String r05 = String.valueOf(r13);
        zaaz$$ExternalSyntheticBUOutline0.m((r32.length() + 28) + r05.length(), "Cannot serialize override ", r32, ": ", r05);
        return r263;
    L66:
        r21 = r263;
        goto L68
    L36:
        boolean r7 = false;
        long r10 = r32.charAt(0) - '0';
        if (r10 < 1) goto L62;
        if (r10 > 9) goto L34;
        int r142 = 1;
    L42:
        if (r142 >= r6) goto L56;
        int r15 = r32.charAt(r142) - '0';
        if (r15 >= 0) goto L47;
        boolean r162 = true;
    L46:
        r262 = r4;
        if (r15 <= 9) goto L51;
        boolean r42 = true;
    L53:
        if ((r162 | r42) == true) goto L35;
        r10 = (r10 * 10) + r15;
        r142 = r142 + 1;
        r7 = false;
        r4 = r262;
        goto L42
    L51:
        r42 = r7;
        goto L53
    L47:
        r162 = r7;
        goto L46
    L56:
        Object r264 = r4;
        r16 = 0;
        Object r265 = r264;
        if (r10 < 0) goto L60;
        r265 = r264;
        if (r10 > 2305843009213693951L) goto L60;
        r19 = r10;
        r263 = r264;
    L60:
        r19 = r16;
        r263 = r265;
    L62:
        r265 = r4;
        r16 = 0;
        goto L60
    L86:
        return new zzmw(r2.build());
    }

    public static zzmw zzb() {
        return zza;
    }

    public static zzmw zzd(zzacv r21) {
        int r0 = r21.zzx();
        if (r0 < 0) goto L38;
        ImmutableSortedSet.Builder r2 = ImmutableSortedSet.naturalOrder();
        int r5 = 0;
        long r6 = 0;
    L5:
        if (r5 >= r0) goto L37;
        long r8 = r21.zzz();
        int r10 = (int) r8;
        long r82 = r8 >>> 3;
        if (r82 != 0) goto L9;
        long r14 = 0;
        String r16 = r21.zzl();
    L12:
        int r83 = r10 & 7;
        if (r83 != 0) goto L15;
    L29:
        zzmv r13 = new zzmv(r14, r16, r83, 0, null);
    L30:
        long r84 = r13.zza;
        if (r84 == 0) goto L33;
        r6 = r84;
    L33:
        r2.add(r13);
        r5 = r5 + 1;
        goto L5
    L15:
        if (r83 == 1) goto L29;
        if (r83 == 2) goto L28;
        if (r83 != 3) goto L20;
        r13 = new zzmv(r14, r16, r83, Double.doubleToRawLongBits(r21.zzd()), null);
        goto L30
    L20:
        if (r83 != 4) goto L22;
        r13 = new zzmv(r14, r16, r83, 0, r21.zzl());
        goto L30
    L22:
        if (r83 != 5) goto L24;
        r13 = new zzmv(r14, r16, r83, 0, r21.zzo());
        goto L30
    L24:
        StringBuilder r22 = new StringBuilder(String.valueOf(r83).length() + 23);
        r22.append("Unrecognized flag type ");
        r22.append(r83);
        throw new zzaeh(r22.toString());
    L28:
        r13 = new zzmv(r14, r16, r83, r21.zzz(), null);
        goto L30
    L9:
        long r85 = r82 + r6;
        if (r85 > 2305843009213693951L) goto L34;
        r16 = null;
        r14 = r85;
        goto L12
    L34:
        zzmw$$ExternalSyntheticBUOutline0.m("Flag name larger than max size");
        return null;
    L37:
        return new zzmw(r2.build());
    L38:
        zzmw$$ExternalSyntheticBUOutline0.m("Negative number of flags");
        return null;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzmw) == true) goto L5;
        return false;
    L5:
        return this.zzb.equals(((zzmw) r2).zzb);
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzc(ImmutableMap.Builder r3) {
        UnmodifiableIterator r2 = this.zzb.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        zzmv r0 = (zzmv) r2.next();
        r3.put(r0.zza(), r0.zzb());
        goto L4
    }

    public final ImmutableSortedSet zze() {
        return this.zzb;
    }

    public final int zzf() {
        return this.zzb.size();
    }
}
