package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzahn;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzac extends zzab {
    final /* synthetic */ zzad zza;
    private final com.google.android.gms.internal.measurement.zzfn zzh;

    public zzac(zzad r1, String r2, int r3, com.google.android.gms.internal.measurement.zzfn r4) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2, r3);
        this.zzh = r4;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    public final int zza() {
        return this.zzh.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    public final boolean zzb() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    public final boolean zzc() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zzd(Long r13, Long r14, com.google.android.gms.internal.measurement.zziu r15, boolean r16) {
        zzahn.zza();
        zzic r0 = this.zza.zzu;
        boolean r1 = r0.zzc().zzp(this.zzb, zzfy.zzaD);
        com.google.android.gms.internal.measurement.zzfn r2 = this.zzh;
        boolean r3 = r2.zze();
        boolean r4 = r2.zzf();
        boolean r5 = r2.zzi();
        if (r3 == true) goto L6;
        if (r4 == true) goto L6;
        if (r5 == true) goto L6;
        Object[] r32 = false;
    L8:
        Boolean r42 = null;
        Integer r43 = null;
        r42 = null;
        r42 = null;
        r42 = null;
        r42 = null;
        if (r16 == false) goto L16;
        if (r32 == true) goto L16;
        zzgs r132 = r0.zzaW().zzk();
        Integer r12 = Integer.valueOf(this.zzc);
        if (r2.zza() == false) goto L14;
        r43 = Integer.valueOf(r2.zzb());
    L14:
        r132.zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", r12, r43);
        return true;
    L16:
        com.google.android.gms.internal.measurement.zzfh r8 = r2.zzd();
        boolean r9 = r8.zzf();
        if (r15.zzf() == false) goto L23;
        if (r8.zzc() == true) goto L21;
        r0.zzaW().zze().zzb("No number filter for long property. property", r0.zzl().zzc(r15.zzc()));
    L41:
        zzgs r02 = r0.zzaW().zzk();
        if (r42 != null) goto L44;
        Object r82 = "null";
    L45:
        r02.zzb("Property filter result", r82);
        if (r42 != null) goto L48;
        return false;
    L48:
        this.zzd = Boolean.TRUE;
        if (r5 == true) goto L51;
    L53:
        if (r16 == true) goto L55;
    L56:
        this.zze = r42;
    L58:
        if (r42.booleanValue() == false) goto L76;
        if (r32 == false) goto L76;
        if (r15.zza() == false) goto L76;
        long r33 = r15.zzb();
        if (r13 == null) goto L65;
        r33 = r13.longValue();
    L65:
        if (r1 == false) goto L73;
        if (r2.zze() == false) goto L73;
        if (r2.zzf() == true) goto L73;
        if (r14 == null) goto L73;
        r33 = r14.longValue();
    L73:
        if (r2.zzf() == false) goto L75;
        this.zzg = Long.valueOf(r33);
        goto L76
    L75:
        this.zzf = Long.valueOf(r33);
    L76:
        return true;
    L55:
        if (r2.zze() == false) goto L58;
    L51:
        if (r42.booleanValue() == true) goto L53;
        return true;
    L44:
        r82 = r42;
        goto L45
    L21:
        r42 = zzab.zze(zzab.zzg(r15.zzh(), r8.zzd()), r9);
        goto L41
    L23:
        if (r15.zzk() == false) goto L29;
        if (r8.zzc() == true) goto L27;
        r0.zzaW().zze().zzb("No number filter for double property. property", r0.zzl().zzc(r15.zzc()));
        goto L41
    L27:
        r42 = zzab.zze(zzab.zzh(r15.zzl(), r8.zzd()), r9);
        goto L41
    L29:
        if (r15.zzd() == true) goto L31;
        r0.zzaW().zze().zzb("User property has no value, property", r0.zzl().zzc(r15.zzc()));
        goto L41
    L31:
        if (r8.zza() == false) goto L33;
        r42 = zzab.zze(zzab.zzf(r15.zze(), r8.zzb(), r0.zzaW()), r9);
        goto L41
    L33:
        if (r8.zzc() == true) goto L36;
        r0.zzaW().zze().zzb("No string or number filter defined. property", r0.zzl().zzc(r15.zzc()));
        goto L41
    L36:
        if (zzpk.zzm(r15.zze()) == false) goto L38;
        r42 = zzab.zze(zzab.zzi(r15.zze(), r8.zzd()), r9);
        goto L41
    L38:
        r0.zzaW().zze().zzc("Invalid user property value for Numeric number filter. property, value", r0.zzl().zzc(r15.zzc()), r15.zze());
    L6:
        r32 = true;
        goto L8
    }
}
