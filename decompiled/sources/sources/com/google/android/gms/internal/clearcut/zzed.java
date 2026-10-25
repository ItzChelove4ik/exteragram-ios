package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzed {
    private final int flags;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final int[] zzms;
    private final zzee zznh;
    private Class<?> zzni;
    private final int zznj;
    private final int zznk;
    private final int zznl;
    private final int zznm;
    private final int zznn;
    private final int zzno;
    private int zznp;
    private int zznq;
    private int zznr;
    private int zzns;
    private int zznt;
    private int zznu;
    private int zznv;
    private int zznw;
    private int zznx;
    private int zzny;
    private int zznz;
    private int zzoa;
    private int zzob;
    private int zzoc;
    private Field zzod;
    private Object zzoe;
    private Object zzof;
    private Object zzog;

    public zzed(Class<?> r3, String r4, Object[] r5) {
        this.zznr = Integer.MAX_VALUE;
        this.zzns = Integer.MIN_VALUE;
        this.zznt = 0;
        this.zznu = 0;
        this.zznv = 0;
        this.zznw = 0;
        this.zznx = 0;
        this.zzni = r3;
        zzee r32 = new zzee(r4);
        this.zznh = r32;
        this.zzmj = r5;
        this.flags = r32.next();
        int r42 = r32.next();
        this.zznj = r42;
        int[] r52 = null;
        if (r42 != 0) goto L6;
        this.zznk = 0;
        this.zznl = 0;
        this.zzmk = 0;
        this.zzml = 0;
        this.zznm = 0;
        this.zznn = 0;
        this.zzmm = 0;
        this.zzno = 0;
        this.zzms = null;
        return;
    L6:
        int r43 = r32.next();
        this.zznk = r43;
        int r0 = r32.next();
        this.zznl = r0;
        this.zzmk = r32.next();
        this.zzml = r32.next();
        this.zznn = r32.next();
        this.zzmm = r32.next();
        this.zznm = r32.next();
        this.zzno = r32.next();
        int r33 = r32.next();
        if (r33 == 0) goto L10;
        r52 = new int[r33];
    L10:
        this.zzms = r52;
        this.zznp = (r43 << 1) + r0;
    }

    public static /* synthetic */ int zza(zzed r0) {
        return r0.flags;
    }

    private static Field zza(Class<?> r5, String r6) {
        return r5.getDeclaredField(r6);
    L4:
        Field[] r0 = r5.getDeclaredFields();
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        Field r3 = r0[r2];
        if (r6.equals(r3.getName()) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        return r3;
    L10:
        String r52 = r5.getName();
        String r02 = Arrays.toString(r0);
        StringBuilder r32 = new StringBuilder(((String.valueOf(r6).length() + 40) + r52.length()) + String.valueOf(r02).length());
        r32.append("Field ");
        r32.append(r6);
        r32.append(" for ");
        r32.append(r52);
        r32.append(" not found. Known fields are ");
        r32.append(r02);
        throw new RuntimeException(r32.toString());
    }

    public static /* synthetic */ int zzb(zzed r0) {
        return r0.zzmk;
    }

    public static /* synthetic */ int zzc(zzed r0) {
        return r0.zzml;
    }

    private final Object zzcw() {
        Object[] r0 = this.zzmj;
        int r1 = this.zznp;
        this.zznp = r1 + 1;
        return r0[r1];
    }

    private final boolean zzcz() {
        if ((this.flags & 1) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public static /* synthetic */ int zzd(zzed r0) {
        return r0.zznj;
    }

    public static /* synthetic */ int zze(zzed r0) {
        return r0.zznm;
    }

    public static /* synthetic */ int zzf(zzed r0) {
        return r0.zzno;
    }

    public static /* synthetic */ int[] zzg(zzed r0) {
        return r0.zzms;
    }

    public static /* synthetic */ int zzh(zzed r0) {
        return r0.zznn;
    }

    public static /* synthetic */ int zzi(zzed r0) {
        return r0.zzmm;
    }

    public final boolean next() {
        if (this.zznh.hasNext() == true) goto L6;
        return false;
    L6:
        this.zzny = this.zznh.next();
        int r0 = this.zznh.next();
        this.zznz = r0;
        int r02 = r0 & 255;
        this.zzoa = r02;
        int r1 = this.zzny;
        if (r1 >= this.zznr) goto L10;
        this.zznr = r1;
    L10:
        if (r1 <= this.zzns) goto L12;
        this.zzns = r1;
    L12:
        zzcb r12 = zzcb.zziw;
        if (r02 != r12.id()) goto L16;
        this.zznt++;
    L20:
        int r03 = this.zznx + 1;
        this.zznx = r03;
        if (zzeh.zzc(this.zznr, this.zzny, r03) == false) goto L24;
        int r04 = this.zzny + 1;
        this.zznw = r04;
        int r05 = r04 - this.zznr;
    L23:
        this.zznv = r05;
        if ((this.zznz & 1024) == 0) goto L28;
        int[] r06 = this.zzms;
        int r2 = this.zznq;
        this.zznq = r2 + 1;
        r06[r2] = this.zzny;
    L28:
        this.zzoe = null;
        this.zzof = null;
        this.zzog = null;
        if (zzda() == false) goto L42;
        this.zzob = this.zznh.next();
        if (this.zzoa != (zzcb.zzhh.id() + 51)) goto L33;
    L40:
        Object r07 = zzcw();
    L41:
        this.zzoe = r07;
    L71:
        return true;
    L33:
        if (this.zzoa == (zzcb.zzhp.id() + 51)) goto L40;
        if (this.zzoa != (zzcb.zzhk.id() + 51)) goto L71;
        if (zzcz() == false) goto L71;
    L39:
        this.zzof = zzcw();
        goto L71
    L42:
        this.zzod = zza(this.zzni, (String) zzcw());
        if (zzde() == false) goto L46;
        this.zzoc = this.zznh.next();
    L46:
        if (this.zzoa != zzcb.zzhh.id()) goto L48;
    L70:
        r07 = this.zzod.getType();
        goto L41
    L48:
        if (this.zzoa == zzcb.zzhp.id()) goto L70;
        if (this.zzoa == zzcb.zzhz.id()) goto L40;
        if (this.zzoa == zzcb.zziv.id()) goto L40;
        if (this.zzoa == zzcb.zzhk.id()) goto L68;
        if (this.zzoa == zzcb.zzic.id()) goto L68;
        if (this.zzoa == zzcb.zziq.id()) goto L68;
        if (this.zzoa != r12.id()) goto L71;
        this.zzog = zzcw();
        if ((this.zznz & 2048) == 0) goto L71;
    L68:
        if (zzcz() == false) goto L71;
    L24:
        r05 = this.zznv + 1;
        goto L23
    L16:
        if (this.zzoa < zzcb.zzhq.id()) goto L20;
        if (this.zzoa > zzcb.zziv.id()) goto L20;
        this.zznu++;
        goto L20
    }

    public final int zzcx() {
        return this.zzny;
    }

    public final int zzcy() {
        return this.zzoa;
    }

    public final boolean zzda() {
        if (this.zzoa <= zzcb.zziw.id()) goto L6;
        return true;
    L6:
        return false;
    }

    public final Field zzdb() {
        int r0 = this.zzob << 1;
        Object r1 = this.zzmj[r0];
        if ((r1 instanceof Field) == true) goto L5;
        Field r12 = zza(this.zzni, (String) r1);
        this.zzmj[r0] = r12;
        return r12;
    L5:
        return (Field) r1;
    }

    public final Field zzdc() {
        int r0 = (this.zzob << 1) + 1;
        Object r1 = this.zzmj[r0];
        if ((r1 instanceof Field) == true) goto L5;
        Field r12 = zza(this.zzni, (String) r1);
        this.zzmj[r0] = r12;
        return r12;
    L5:
        return (Field) r1;
    }

    public final Field zzdd() {
        return this.zzod;
    }

    public final boolean zzde() {
        if (zzcz() == true) goto L5;
        return false;
    L5:
        if (this.zzoa > zzcb.zzhp.id()) goto L10;
        return true;
    L10:
        return false;
    }

    public final Field zzdf() {
        int r0 = (this.zznk << 1) + (this.zzoc / 32);
        Object r1 = this.zzmj[r0];
        if ((r1 instanceof Field) == true) goto L5;
        Field r12 = zza(this.zzni, (String) r1);
        this.zzmj[r0] = r12;
        return r12;
    L5:
        return (Field) r1;
    }

    public final int zzdg() {
        return this.zzoc % 32;
    }

    public final boolean zzdh() {
        if ((this.zznz & 256) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzdi() {
        if ((this.zznz & 512) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final Object zzdj() {
        return this.zzoe;
    }

    public final Object zzdk() {
        return this.zzof;
    }

    public final Object zzdl() {
        return this.zzog;
    }
}
