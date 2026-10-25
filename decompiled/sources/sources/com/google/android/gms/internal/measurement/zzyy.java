package com.google.android.gms.internal.measurement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Formattable;

/* loaded from: classes4.dex */
public final class zzyy extends zzabm implements zzabi {
    protected final Object[] zza;
    protected final StringBuilder zzb;
    private int zzc;

    public zzyy(zzaaf r1, Object[] r2, StringBuilder r3) {
        super(r1);
        this.zzc = 0;
        this.zza = r2;
        this.zzb = r3;
    }

    public static StringBuilder zza(zzzd r3, StringBuilder r4) {
        if (r3.zzh() == null) goto L8;
        zzyy r0 = new zzyy(r3.zzh(), r3.zzi(), r4);
        StringBuilder r42 = (StringBuilder) r0.zzl();
        if (r3.zzi().length <= r0.zzj()) goto L7;
        r42.append(" [ERROR: UNUSED LOG ARGUMENTS]");
    L7:
        return r42;
    L8:
        r4.append(zzzh.zza(r3.zzj()));
        return r4;
    }

    private static void zzm(StringBuilder r1, Object r2, String r3) {
        r1.append("[INVALID: format=");
        r1.append(r3);
        r1.append(", type=");
        r1.append(r2.getClass().getCanonicalName());
        r1.append(", value=");
        r1.append(zzzh.zza(r2));
        r1.append("]");
    }

    @Override // com.google.android.gms.internal.measurement.zzabm
    public final void zzb(int r5, int r6, zzabh r7) {
        zzh().zzd(this.zzb, zzi(), this.zzc, r5);
        r7.zze(this, this.zza);
        this.zzc = r6;
    }

    @Override // com.google.android.gms.internal.measurement.zzabi
    public final void zzc(Object r7, zzyz r8, zzza r9) {
        int r0 = r8.zzc().ordinal();
        if (r0 == 0) goto L45;
        if (r0 == 1) goto L40;
        if (r0 == 2) goto L31;
        if (r0 == 3) goto L20;
        if (r0 != 4) goto L18;
        if ((r7 instanceof Double) == false) goto L12;
    L15:
        boolean r02 = true;
    L41:
        if (r02 == true) goto L45;
        zzm(this.zzb, r7, r8.zze());
        return;
    L12:
        if ((r7 instanceof Float) == true) goto L15;
        if ((r7 instanceof BigDecimal) == true) goto L15;
    L16:
        r02 = false;
        goto L41
    L18:
        throw null;
    L20:
        if ((r7 instanceof Integer) == true) goto L15;
        if ((r7 instanceof Long) == true) goto L15;
        if ((r7 instanceof Byte) == true) goto L15;
        if ((r7 instanceof Short) == true) goto L15;
        if ((r7 instanceof BigInteger) == false) goto L16;
    L31:
        if ((r7 instanceof Character) == true) goto L15;
        if ((r7 instanceof Integer) == false) goto L36;
    L39:
        r02 = Character.isValidCodePoint(((Number) r7).intValue());
        goto L41
    L36:
        if ((r7 instanceof Byte) == true) goto L39;
        if ((r7 instanceof Short) == false) goto L16;
    L40:
        r02 = r7 instanceof Boolean;
    L45:
        StringBuilder r6 = this.zzb;
        int r03 = r8.ordinal();
        if (r03 == 0) goto L74;
        if (r03 == 1) goto L70;
        if (r03 == 2) goto L58;
        if (r03 == 3) goto L70;
        if (r03 == 5) goto L54;
    L79:
        String r04 = r8.zze();
        if (r9.zze() == true) goto L85;
        int r82 = r8.zzb();
        if (r9.zzk() == false) goto L84;
        r82 = r82 & 65503;
    L84:
        StringBuilder r05 = new StringBuilder("%");
        r9.zzl(r05);
        r05.append((char) r82);
        r04 = r05.toString();
    L85:
        r6.append(String.format(zzzh.zza, r04, new Object[]{r7}));
        return;
    L54:
        if (r9.zzd(128, false, false).equals(r9) == false) goto L79;
        zzzh.zzc(r6, (Number) r7, r9);
        return;
    L58:
        if (r9.zze() == false) goto L79;
        if ((r7 instanceof Character) == false) goto L63;
        r6.append(r7);
        return;
    L63:
        int r72 = ((Number) r7).intValue();
        if ((r72 >>> 16) != 0) goto L67;
        r6.append((char) r72);
        return;
    L67:
        r6.append(Character.toChars(r72));
        return;
    L70:
        if (r9.zze() == false) goto L79;
        r6.append(r7);
        return;
    L74:
        if ((r7 instanceof Formattable) == false) goto L76;
        zzzh.zzb((Formattable) r7, r6, r9);
        return;
    L76:
        if (r9.zze() == false) goto L79;
        r6.append(zzzh.zza(r7));
    }

    @Override // com.google.android.gms.internal.measurement.zzabi
    public final void zzd(Object r3, zzabf r4, zzza r5) {
        if ((r3 instanceof Date) == false) goto L5;
    L11:
        StringBuilder r0 = new StringBuilder("%");
        r5.zzl(r0);
        if (true == r5.zzk()) goto L14;
        char r52 = 't';
    L15:
        r0.append(r52);
        r0.append(r4.zzb());
        String r42 = r0.toString();
        this.zzb.append(String.format(zzzh.zza, r42, new Object[]{r3}));
        return;
    L14:
        r52 = 'T';
        goto L15
    L5:
        if ((r3 instanceof Calendar) == true) goto L11;
        if ((r3 instanceof Long) == true) goto L11;
        StringBuilder r2 = this.zzb;
        char r43 = r4.zzb();
        StringBuilder r02 = new StringBuilder(String.valueOf(r43).length() + 2);
        r02.append("%t");
        r02.append(r43);
        zzm(r2, r3, r02.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzabi
    public final void zze() {
        this.zzb.append("[ERROR: MISSING LOG ARGUMENT]");
    }

    @Override // com.google.android.gms.internal.measurement.zzabi
    public final void zzf() {
        this.zzb.append("null");
    }

    @Override // com.google.android.gms.internal.measurement.zzabm
    public final /* bridge */ /* synthetic */ Object zzg() {
        zzabn r0 = zzh();
        String r1 = zzi();
        int r2 = this.zzc;
        int r3 = zzi().length();
        StringBuilder r4 = this.zzb;
        r0.zzd(r4, r1, r2, r3);
        return r4;
    }
}
