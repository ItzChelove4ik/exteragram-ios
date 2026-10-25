package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzacw implements zzafo {
    private final zzacv zza;
    private int zzb;
    private int zzc;
    private int zzd;

    private zzacw(zzacv r2) {
        this.zzd = 0;
        this.zza = r2;
        r2.zzd = this;
    }

    private final void zzQ(int r1) {
        if ((this.zzb & 7) != r1) goto L5;
        return;
    L5:
        zzacs$$ExternalSyntheticBUOutline0.m();
    }

    private final void zzR(Object r4, zzafp r5, zzadf r6) {
        zzacv r0 = this.zza;
        int r1 = r0.zzp();
        r0.zzO();
        int r12 = r0.zzD(r1);
        r0.zza++;
        r5.zzg(r4, this, r6);
        r0.zzb(0);
        r0.zza--;
        r0.zzE(r12);
    }

    private final Object zzS(zzafp r2, zzadf r3) {
        Object r0 = r2.zza();
        zzR(r0, r2, r3);
        r2.zzk(r0);
        return r0;
    }

    private final void zzT(Object r3, zzafp r4, zzadf r5) {
        int r0 = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        r4.zzg(r3, this, r5);     // Catch: Throwable -> L9
        if (this.zzb != this.zzc) goto L8;
        this.zzc = r0;
        return;
    L8:
        throw new zzaeh("Failed to parse the message.");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        this.zzc = r0;
        throw th;
    }

    private final Object zzU(zzagm r2, Class r3, zzadf r4) {
        zzagm r0 = zzagm.zza;
        switch(r2.ordinal()) {
            case 0: goto L39;
            case 1: goto L37;
            case 2: goto L35;
            case 3: goto L33;
            case 4: goto L31;
            case 5: goto L29;
            case 6: goto L27;
            case 7: goto L25;
            case 8: goto L23;
            case 9: goto L4;
            case 10: goto L20;
            case 11: goto L19;
            case 12: goto L17;
            case 13: goto L15;
            case 14: goto L13;
            case 15: goto L11;
            case 16: goto L9;
            case 17: goto L7;
            default: goto L4;
        };
    L4:
        f$$ExternalSyntheticBUOutline1.m("unsupported field type.");
        return null;
    L20:
        zzQ(2);
        return zzS(zzafl.zza().zzb(r3), r4);
    L7:
        return Long.valueOf(zzw());
    L9:
        return Integer.valueOf(zzv());
    L11:
        return Long.valueOf(zzu());
    L13:
        return Integer.valueOf(zzt());
    L15:
        return Integer.valueOf(zzs());
    L17:
        return Integer.valueOf(zzr());
    L19:
        return zzq();
    L23:
        return zzn();
    L25:
        return Boolean.valueOf(zzl());
    L27:
        return Integer.valueOf(zzk());
    L29:
        return Long.valueOf(zzj());
    L31:
        return Integer.valueOf(zzi());
    L33:
        return Long.valueOf(zzg());
    L35:
        return Long.valueOf(zzh());
    L37:
        return Float.valueOf(zzf());
    L39:
        return Double.valueOf(zze());
    }

    private final void zzV(int r1) {
        if (this.zza.zzH() != r1) goto L5;
        return;
    L5:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    private static final void zzW(int r0) {
        if ((r0 & 3) != 0) goto L5;
        return;
    L5:
        zzmw$$ExternalSyntheticBUOutline0.m("Failed to parse the message.");
    }

    private static final void zzX(int r0) {
        if ((r0 & 7) != 0) goto L5;
        return;
    L5:
        zzmw$$ExternalSyntheticBUOutline0.m("Failed to parse the message.");
    }

    public static zzacw zza(zzacv r1) {
        Object r0 = r1.zzd;
        if (r0 == null) goto L7;
        return (zzacw) r0;
    L7:
        return new zzacw(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzA(List r6) {
        boolean r0 = r6 instanceof zzaeq;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzaeq r02 = (zzaeq) r6;
        int r62 = r1 & 7;
        if (r62 == 0) goto L14;
        if (r62 != 2) goto L12;
        zzacv r63 = this.zza;
        int r12 = r63.zzp();
        int r2 = r63.zzH() + r12;
    L8:
        r02.zzf(r63.zzg());
        if (r63.zzH() < r2) goto L8;
        zzV(r2);
        return;
    L12:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzacv r64 = this.zza;
        r02.zzf(r64.zzg());
        if (r64.zzG() == true) goto L47;
        int r65 = r64.zza();
        if (r65 == this.zzb) goto L14;
    L35:
        this.zzd = r65;
        return;
    L47:
        return;
    L20:
        int r03 = r1 & 7;
        if (r03 == 0) goto L30;
        if (r03 != 2) goto L28;
        zzacv r04 = this.zza;
        int r13 = r04.zzp();
        int r22 = r04.zzH() + r13;
    L24:
        r6.add(Long.valueOf(r04.zzg()));
        if (r04.zzH() < r22) goto L24;
        zzV(r22);
        return;
    L28:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzacv r05 = this.zza;
        r6.add(Long.valueOf(r05.zzg()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r65 = r06;
        goto L35
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzB(List r4) {
        boolean r0 = r4 instanceof zzadv;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzadv r02 = (zzadv) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzacv r43 = this.zza;
        int r12 = r43.zzp();
        int r2 = r43.zzH() + r12;
    L8:
        r02.zzh(r43.zzh());
        if (r43.zzH() < r2) goto L8;
        zzV(r2);
        return;
    L12:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzacv r44 = this.zza;
        r02.zzh(r44.zzh());
        if (r44.zzG() == true) goto L47;
        int r45 = r44.zza();
        if (r45 == this.zzb) goto L14;
    L35:
        this.zzd = r45;
        return;
    L47:
        return;
    L20:
        int r03 = r1 & 7;
        if (r03 == 0) goto L30;
        if (r03 != 2) goto L28;
        zzacv r04 = this.zza;
        int r13 = r04.zzp();
        int r22 = r04.zzH() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzh()));
        if (r04.zzH() < r22) goto L24;
        zzV(r22);
        return;
    L28:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzacv r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzh()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzC(List r5) {
        boolean r0 = r5 instanceof zzaeq;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzaeq r02 = (zzaeq) r5;
        int r52 = r1 & 7;
        if (r52 == 1) goto L13;
        if (r52 != 2) goto L11;
        zzacv r4 = this.zza;
        int r53 = r4.zzp();
        zzX(r53);
        int r12 = r4.zzH() + r53;
    L8:
        r02.zzf(r4.zzi());
        if (r4.zzH() < r12) goto L8;
        return;
    L11:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        zzacv r54 = this.zza;
        r02.zzf(r54.zzi());
        if (r54.zzG() == true) goto L46;
        int r55 = r54.zza();
        if (r55 == this.zzb) goto L13;
    L33:
        this.zzd = r55;
        return;
    L46:
        return;
    L19:
        int r03 = r1 & 7;
        if (r03 == 1) goto L28;
        if (r03 != 2) goto L26;
        zzacv r42 = this.zza;
        int r04 = r42.zzp();
        zzX(r04);
        int r13 = r42.zzH() + r04;
    L23:
        r5.add(Long.valueOf(r42.zzi()));
        if (r42.zzH() < r13) goto L23;
        return;
    L26:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L28:
        zzacv r05 = this.zza;
        r5.add(Long.valueOf(r05.zzi()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L28;
        r55 = r06;
        goto L33
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzD(List r7) {
        boolean r0 = r7 instanceof zzadv;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzadv r02 = (zzadv) r7;
        int r72 = r1 & 7;
        if (r72 == 2) goto L15;
        if (r72 != 5) goto L13;
    L7:
        zzacv r73 = this.zza;
        r02.zzh(r73.zzj());
        if (r73.zzG() == true) goto L34;
        int r74 = r73.zza();
        if (r74 == this.zzb) goto L7;
    L27:
        this.zzd = r74;
        return;
    L34:
        return;
    L13:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L15:
        zzacv r4 = this.zza;
        int r6 = r4.zzp();
        zzW(r6);
        int r5 = r4.zzH() + r6;
    L16:
        r02.zzh(r4.zzj());
        if (r4.zzH() < r5) goto L16;
        return;
    L19:
        int r03 = r1 & 7;
        if (r03 == 2) goto L31;
        if (r03 != 5) goto L29;
    L22:
        zzacv r04 = this.zza;
        r7.add(Integer.valueOf(r04.zzj()));
        if (r04.zzG() == true) goto L46;
        int r05 = r04.zza();
        if (r05 == this.zzb) goto L22;
        r74 = r05;
        goto L27
    L46:
        return;
    L29:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L31:
        zzacv r62 = this.zza;
        int r06 = r62.zzp();
        zzW(r06);
        int r12 = r62.zzH() + r06;
    L32:
        r7.add(Integer.valueOf(r62.zzj()));
        if (r62.zzH() < r12) goto L32;
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzE(List r4) {
        if ((r4 instanceof zzaci) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        int r42 = this.zzb & 7;
        if (r42 == 0) goto L11;
        if (r42 == 2) goto L9;
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        zzacv r3 = this.zza;
        r3.zzp();
        r3.zzH();
        r3.zzk();
        throw null;
    L11:
        this.zza.zzk();
        throw null;
    L13:
        int r0 = this.zzb & 7;
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L21;
        zzacv r02 = this.zza;
        int r1 = r02.zzp();
        int r2 = r02.zzH() + r1;
    L17:
        r4.add(Boolean.valueOf(r02.zzk()));
        if (r02.zzH() < r2) goto L17;
        zzV(r2);
        return;
    L21:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L23:
        zzacv r03 = this.zza;
        r4.add(Boolean.valueOf(r03.zzk()));
        if (r03.zzG() == true) goto L34;
        int r04 = r03.zza();
        if (r04 == this.zzb) goto L23;
        this.zzd = r04;
        return;
    }

    public final void zzF(List r3, boolean r4) {
        if ((this.zzb & 7) == 2) goto L5;
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L5:
        if ((r3 instanceof zzaen) == false) goto L14;
        if (r4 == true) goto L14;
        zzaen r32 = (zzaen) r3;
    L9:
        zzq();
        r32.zzb();
        zzacv r42 = this.zza;
        if (r42.zzG() == true) goto L19;
        int r43 = r42.zza();
        if (r43 == this.zzb) goto L9;
    L23:
        this.zzd = r43;
        return;
    L19:
        return;
    L14:
        if (r4 == false) goto L16;
        String r0 = zzn();
    L17:
        r3.add(r0);
        zzacv r02 = this.zza;
        if (r02.zzG() == true) goto L34;
        int r03 = r02.zza();
        if (r03 == this.zzb) goto L14;
        r43 = r03;
        goto L23
    L34:
        return;
    L16:
        r0 = zzm();
        goto L17
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzG(List r4, zzafp r5, zzadf r6) {
        int r0 = this.zzb;
        if ((r0 & 7) != 2) goto L13;
    L4:
        r4.add(zzS(r5, r6));
        zzacv r1 = this.zza;
        if (r1.zzG() == true) goto L19;
        if (this.zzd != 0) goto L20;
        int r12 = r1.zza();
        if (r12 == r0) goto L4;
        this.zzd = r12;
        return;
    L20:
        return;
    L19:
        return;
    L13:
        zzacs$$ExternalSyntheticBUOutline0.m();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    @Deprecated
    public final void zzH(List r4, zzafp r5, zzadf r6) {
        int r0 = this.zzb;
        if ((r0 & 7) != 3) goto L13;
    L4:
        Object r1 = r5.zza();
        zzT(r1, r5, r6);
        r5.zzk(r1);
        r4.add(r1);
        zzacv r12 = this.zza;
        if (r12.zzG() == true) goto L19;
        if (this.zzd != 0) goto L20;
        int r13 = r12.zza();
        if (r13 == r0) goto L4;
        this.zzd = r13;
        return;
    L20:
        return;
    L19:
        return;
    L13:
        zzacs$$ExternalSyntheticBUOutline0.m();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzI(List r3) {
        if ((this.zzb & 7) != 2) goto L11;
    L4:
        r3.add(zzq());
        zzacv r0 = this.zza;
        if (r0.zzG() == true) goto L6;
        int r02 = r0.zza();
        if (r02 == this.zzb) goto L4;
        this.zzd = r02;
        return;
    L6:
        return;
    L11:
        zzacs$$ExternalSyntheticBUOutline0.m();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzJ(List r4) {
        boolean r0 = r4 instanceof zzadv;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzadv r02 = (zzadv) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzacv r43 = this.zza;
        int r12 = r43.zzp();
        int r2 = r43.zzH() + r12;
    L8:
        r02.zzh(r43.zzp());
        if (r43.zzH() < r2) goto L8;
        zzV(r2);
        return;
    L12:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzacv r44 = this.zza;
        r02.zzh(r44.zzp());
        if (r44.zzG() == true) goto L47;
        int r45 = r44.zza();
        if (r45 == this.zzb) goto L14;
    L35:
        this.zzd = r45;
        return;
    L47:
        return;
    L20:
        int r03 = r1 & 7;
        if (r03 == 0) goto L30;
        if (r03 != 2) goto L28;
        zzacv r04 = this.zza;
        int r13 = r04.zzp();
        int r22 = r04.zzH() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzp()));
        if (r04.zzH() < r22) goto L24;
        zzV(r22);
        return;
    L28:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzacv r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzp()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzK(List r4) {
        boolean r0 = r4 instanceof zzadv;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzadv r02 = (zzadv) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzacv r43 = this.zza;
        int r12 = r43.zzp();
        int r2 = r43.zzH() + r12;
    L8:
        r02.zzh(r43.zzq());
        if (r43.zzH() < r2) goto L8;
        zzV(r2);
        return;
    L12:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzacv r44 = this.zza;
        r02.zzh(r44.zzq());
        if (r44.zzG() == true) goto L47;
        int r45 = r44.zza();
        if (r45 == this.zzb) goto L14;
    L35:
        this.zzd = r45;
        return;
    L47:
        return;
    L20:
        int r03 = r1 & 7;
        if (r03 == 0) goto L30;
        if (r03 != 2) goto L28;
        zzacv r04 = this.zza;
        int r13 = r04.zzp();
        int r22 = r04.zzH() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzq()));
        if (r04.zzH() < r22) goto L24;
        zzV(r22);
        return;
    L28:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzacv r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzq()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzL(List r7) {
        boolean r0 = r7 instanceof zzadv;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzadv r02 = (zzadv) r7;
        int r72 = r1 & 7;
        if (r72 == 2) goto L15;
        if (r72 != 5) goto L13;
    L7:
        zzacv r73 = this.zza;
        r02.zzh(r73.zzr());
        if (r73.zzG() == true) goto L34;
        int r74 = r73.zza();
        if (r74 == this.zzb) goto L7;
    L27:
        this.zzd = r74;
        return;
    L34:
        return;
    L13:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L15:
        zzacv r4 = this.zza;
        int r6 = r4.zzp();
        zzW(r6);
        int r5 = r4.zzH() + r6;
    L16:
        r02.zzh(r4.zzr());
        if (r4.zzH() < r5) goto L16;
        return;
    L19:
        int r03 = r1 & 7;
        if (r03 == 2) goto L31;
        if (r03 != 5) goto L29;
    L22:
        zzacv r04 = this.zza;
        r7.add(Integer.valueOf(r04.zzr()));
        if (r04.zzG() == true) goto L46;
        int r05 = r04.zza();
        if (r05 == this.zzb) goto L22;
        r74 = r05;
        goto L27
    L46:
        return;
    L29:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L31:
        zzacv r62 = this.zza;
        int r06 = r62.zzp();
        zzW(r06);
        int r12 = r62.zzH() + r06;
    L32:
        r7.add(Integer.valueOf(r62.zzr()));
        if (r62.zzH() < r12) goto L32;
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzM(List r5) {
        boolean r0 = r5 instanceof zzaeq;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzaeq r02 = (zzaeq) r5;
        int r52 = r1 & 7;
        if (r52 == 1) goto L13;
        if (r52 != 2) goto L11;
        zzacv r4 = this.zza;
        int r53 = r4.zzp();
        zzX(r53);
        int r12 = r4.zzH() + r53;
    L8:
        r02.zzf(r4.zzs());
        if (r4.zzH() < r12) goto L8;
        return;
    L11:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        zzacv r54 = this.zza;
        r02.zzf(r54.zzs());
        if (r54.zzG() == true) goto L46;
        int r55 = r54.zza();
        if (r55 == this.zzb) goto L13;
    L33:
        this.zzd = r55;
        return;
    L46:
        return;
    L19:
        int r03 = r1 & 7;
        if (r03 == 1) goto L28;
        if (r03 != 2) goto L26;
        zzacv r42 = this.zza;
        int r04 = r42.zzp();
        zzX(r04);
        int r13 = r42.zzH() + r04;
    L23:
        r5.add(Long.valueOf(r42.zzs()));
        if (r42.zzH() < r13) goto L23;
        return;
    L26:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L28:
        zzacv r05 = this.zza;
        r5.add(Long.valueOf(r05.zzs()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L28;
        r55 = r06;
        goto L33
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzN(List r4) {
        boolean r0 = r4 instanceof zzadv;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzadv r02 = (zzadv) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzacv r43 = this.zza;
        int r12 = r43.zzp();
        int r2 = r43.zzH() + r12;
    L8:
        r02.zzh(r43.zzt());
        if (r43.zzH() < r2) goto L8;
        zzV(r2);
        return;
    L12:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzacv r44 = this.zza;
        r02.zzh(r44.zzt());
        if (r44.zzG() == true) goto L47;
        int r45 = r44.zza();
        if (r45 == this.zzb) goto L14;
    L35:
        this.zzd = r45;
        return;
    L47:
        return;
    L20:
        int r03 = r1 & 7;
        if (r03 == 0) goto L30;
        if (r03 != 2) goto L28;
        zzacv r04 = this.zza;
        int r13 = r04.zzp();
        int r22 = r04.zzH() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzt()));
        if (r04.zzH() < r22) goto L24;
        zzV(r22);
        return;
    L28:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzacv r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzt()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzO(List r6) {
        boolean r0 = r6 instanceof zzaeq;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzaeq r02 = (zzaeq) r6;
        int r62 = r1 & 7;
        if (r62 == 0) goto L14;
        if (r62 != 2) goto L12;
        zzacv r63 = this.zza;
        int r12 = r63.zzp();
        int r2 = r63.zzH() + r12;
    L8:
        r02.zzf(r63.zzu());
        if (r63.zzH() < r2) goto L8;
        zzV(r2);
        return;
    L12:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzacv r64 = this.zza;
        r02.zzf(r64.zzu());
        if (r64.zzG() == true) goto L47;
        int r65 = r64.zza();
        if (r65 == this.zzb) goto L14;
    L35:
        this.zzd = r65;
        return;
    L47:
        return;
    L20:
        int r03 = r1 & 7;
        if (r03 == 0) goto L30;
        if (r03 != 2) goto L28;
        zzacv r04 = this.zza;
        int r13 = r04.zzp();
        int r22 = r04.zzH() + r13;
    L24:
        r6.add(Long.valueOf(r04.zzu()));
        if (r04.zzH() < r22) goto L24;
        zzV(r22);
        return;
    L28:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzacv r05 = this.zza;
        r6.add(Long.valueOf(r05.zzu()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r65 = r06;
        goto L35
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzP(Map r10, zzaeu r11, zzadf r12) {
        zzQ(2);
        zzacv r1 = this.zza;
        int r2 = r1.zzD(r1.zzp());
        Object r3 = r11.zzd;
        Object r4 = r11.zzb;
        Object r5 = r3;
    L35:
        int r6 = zzb();     // Catch: Throwable -> L16
        if (r6 == Integer.MAX_VALUE) goto L28;
        if (r1.zzG() == true) goto L28;
        if (r6 != 1) goto L10;
        r4 = zzU(r11.zza, null, null);     // Catch: Throwable -> L16 zzaeg -> L18
        goto L35
    L10:
        if (r6 == 2) goto L20;
    L18:
        e = move-exception;
        if (zzd() == true) goto L35;
        throw new zzaeh("Unable to parse map entry.", e);     // Catch: Throwable -> L16
    L12:
        if (zzd() == true) goto L35;
        throw new zzaeh("Unable to parse map entry.");     // Catch: Throwable -> L16 zzaeg -> L18
    L20:
        r5 = zzU(r11.zzc, r3.getClass(), r12);     // Catch: Throwable -> L16 zzaeg -> L18
    L28:
        r10.put(r4, r5);     // Catch: Throwable -> L16
        this.zza.zzE(r2);
        return;
    L16:
        th = move-exception;
        this.zza.zzE(r2);
        throw th;
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzb() {
        int r0 = this.zzd;
        if (r0 == 0) goto L5;
        this.zzb = r0;
        this.zzd = 0;
    L6:
        if (r0 != 0) goto L8;
        return Integer.MAX_VALUE;
    L8:
        if (r0 != this.zzc) goto L11;
        return Integer.MAX_VALUE;
    L11:
        return r0 >>> 3;
    L5:
        r0 = this.zza.zza();
        this.zzb = r0;
        goto L6
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzc() {
        return this.zzb;
    }

    public final boolean zzd() {
        zzacv r0 = this.zza;
        if (r0.zzG() == true) goto L9;
        int r1 = this.zzb;
        if (r1 != this.zzc) goto L8;
        return false;
    L8:
        return r0.zzc(r1);
    L9:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final double zze() {
        zzQ(1);
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final float zzf() {
        zzQ(5);
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final long zzg() {
        zzQ(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final long zzh() {
        zzQ(0);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzi() {
        zzQ(0);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final long zzj() {
        zzQ(1);
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzk() {
        zzQ(5);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final boolean zzl() {
        zzQ(0);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final String zzm() {
        zzQ(2);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final String zzn() {
        zzQ(2);
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzo(Object r2, zzafp r3, zzadf r4) {
        zzQ(2);
        zzR(r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzp(Object r2, zzafp r3, zzadf r4) {
        zzQ(3);
        zzT(r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final zzacr zzq() {
        zzQ(2);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzr() {
        zzQ(0);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzs() {
        zzQ(0);
        return this.zza.zzq();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzt() {
        zzQ(5);
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final long zzu() {
        zzQ(1);
        return this.zza.zzs();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final int zzv() {
        zzQ(0);
        return this.zza.zzt();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final long zzw() {
        zzQ(0);
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzx(List r5) {
        if ((r5 instanceof zzadc) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = this.zzb & 7;
        if (r52 == 1) goto L11;
        if (r52 == 2) goto L9;
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        zzacv r4 = this.zza;
        zzX(r4.zzp());
        r4.zzH();
        r4.zzd();
        throw null;
    L11:
        this.zza.zzd();
        throw null;
    L13:
        int r0 = this.zzb & 7;
        if (r0 == 1) goto L22;
        if (r0 != 2) goto L20;
        zzacv r42 = this.zza;
        int r02 = r42.zzp();
        zzX(r02);
        int r1 = r42.zzH() + r02;
    L17:
        r5.add(Double.valueOf(r42.zzd()));
        if (r42.zzH() < r1) goto L17;
        return;
    L20:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L22:
        zzacv r03 = this.zza;
        r5.add(Double.valueOf(r03.zzd()));
        if (r03.zzG() == true) goto L34;
        int r04 = r03.zza();
        if (r04 == this.zzb) goto L22;
        this.zzd = r04;
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzy(List r4) {
        if ((r4 instanceof zzadm) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        int r42 = this.zzb & 7;
        if (r42 == 2) goto L11;
        if (r42 == 5) goto L9;
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        this.zza.zze();
        throw null;
    L11:
        zzacv r3 = this.zza;
        zzW(r3.zzp());
        r3.zzH();
        r3.zze();
        throw null;
    L13:
        int r0 = this.zzb & 7;
        if (r0 == 2) goto L24;
        if (r0 != 5) goto L22;
    L16:
        zzacv r02 = this.zza;
        r4.add(Float.valueOf(r02.zze()));
        if (r02.zzG() == true) goto L27;
        int r03 = r02.zza();
        if (r03 == this.zzb) goto L16;
        this.zzd = r03;
        return;
    L27:
        return;
    L22:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L24:
        zzacv r32 = this.zza;
        int r04 = r32.zzp();
        zzW(r04);
        int r1 = r32.zzH() + r04;
    L25:
        r4.add(Float.valueOf(r32.zze()));
        if (r32.zzH() < r1) goto L25;
    }

    @Override // com.google.android.gms.internal.measurement.zzafo
    public final void zzz(List r6) {
        boolean r0 = r6 instanceof zzaeq;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzaeq r02 = (zzaeq) r6;
        int r62 = r1 & 7;
        if (r62 == 0) goto L14;
        if (r62 != 2) goto L12;
        zzacv r63 = this.zza;
        int r12 = r63.zzp();
        int r2 = r63.zzH() + r12;
    L8:
        r02.zzf(r63.zzf());
        if (r63.zzH() < r2) goto L8;
        zzV(r2);
        return;
    L12:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzacv r64 = this.zza;
        r02.zzf(r64.zzf());
        if (r64.zzG() == true) goto L47;
        int r65 = r64.zza();
        if (r65 == this.zzb) goto L14;
    L35:
        this.zzd = r65;
        return;
    L47:
        return;
    L20:
        int r03 = r1 & 7;
        if (r03 == 0) goto L30;
        if (r03 != 2) goto L28;
        zzacv r04 = this.zza;
        int r13 = r04.zzp();
        int r22 = r04.zzH() + r13;
    L24:
        r6.add(Long.valueOf(r04.zzf()));
        if (r04.zzH() < r22) goto L24;
        zzV(r22);
        return;
    L28:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzacv r05 = this.zza;
        r6.add(Long.valueOf(r05.zzf()));
        if (r05.zzG() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r65 = r06;
        goto L35
    }
}
