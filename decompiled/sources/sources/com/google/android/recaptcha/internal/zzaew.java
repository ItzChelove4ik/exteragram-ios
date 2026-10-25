package com.google.android.recaptcha.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.List;

/* loaded from: classes5.dex */
final class zzaew {
    private final zzaev zza;
    private int zzb;
    private int zzc;
    private int zzd;

    private zzaew(zzaev r2) {
        this.zzd = 0;
        this.zza = r2;
        r2.zzd = this;
    }

    private final void zzO(int r1) {
        if ((this.zzb & 7) != r1) goto L5;
        return;
    L5:
        zzaew$$ExternalSyntheticBUOutline0.m();
    }

    private final void zzP(Object r4, zzahn r5, zzafh r6) {
        zzaev r0 = this.zza;
        int r1 = r0.zzn();
        r0.zzF();
        int r12 = r0.zzA(r1);
        r0.zza++;
        r5.zzg(r4, this, r6);
        r0.zzb(0);
        r0.zza--;
        r0.zzB(r12);
    }

    private final void zzQ(Object r4, zzahn r5, zzafh r6) {
        zzaev r0 = this.zza;
        r0.zzF();
        int r1 = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        r0.zzb++;
        r5.zzg(r4, this, r6);     // Catch: Throwable -> L9
        if (this.zzb != this.zzc) goto L8;
        zzaev r42 = this.zza;
        r42.zzb--;
        this.zzc = r1;
        return;
    L8:
        throw new zzagk("Failed to parse the message.");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        zzaev r52 = this.zza;
        r52.zzb--;
        this.zzc = r1;
        throw th;
    }

    private final void zzR(int r1) {
        if (this.zza.zzD() != r1) goto L5;
        return;
    L5:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    private static final void zzS(int r0) {
        if ((r0 & 3) != 0) goto L5;
        return;
    L5:
        zzaeh$$ExternalSyntheticBUOutline0.m("Failed to parse the message.");
    }

    private static final void zzT(int r0) {
        if ((r0 & 7) != 0) goto L5;
        return;
    L5:
        zzaeh$$ExternalSyntheticBUOutline0.m("Failed to parse the message.");
    }

    public static zzaew zza(zzaev r1) {
        Object r0 = r1.zzd;
        if (r0 == null) goto L7;
        return (zzaew) r0;
    L7:
        return new zzaew(r1);
    }

    public final void zzA(List r4) {
        boolean r0 = r4 instanceof zzafw;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzafw r02 = (zzafw) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzaev r43 = this.zza;
        int r12 = r43.zzn();
        int r2 = r43.zzD() + r12;
    L8:
        r02.zzh(r43.zzg());
        if (r43.zzD() < r2) goto L8;
        zzR(r2);
        return;
    L12:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzaev r44 = this.zza;
        r02.zzh(r44.zzg());
        if (r44.zzC() == true) goto L47;
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
        zzaev r04 = this.zza;
        int r13 = r04.zzn();
        int r22 = r04.zzD() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzg()));
        if (r04.zzD() < r22) goto L24;
        zzR(r22);
        return;
    L28:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzaev r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzg()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    public final void zzB(List r5) {
        boolean r0 = r5 instanceof zzagq;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzagq r02 = (zzagq) r5;
        int r52 = r1 & 7;
        if (r52 == 1) goto L13;
        if (r52 != 2) goto L11;
        zzaev r4 = this.zza;
        int r53 = r4.zzn();
        zzT(r53);
        int r12 = r4.zzD() + r53;
    L8:
        r02.zzf(r4.zzh());
        if (r4.zzD() < r12) goto L8;
        return;
    L11:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        zzaev r54 = this.zza;
        r02.zzf(r54.zzh());
        if (r54.zzC() == true) goto L46;
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
        zzaev r42 = this.zza;
        int r04 = r42.zzn();
        zzT(r04);
        int r13 = r42.zzD() + r04;
    L23:
        r5.add(Long.valueOf(r42.zzh()));
        if (r42.zzD() < r13) goto L23;
        return;
    L26:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L28:
        zzaev r05 = this.zza;
        r5.add(Long.valueOf(r05.zzh()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L28;
        r55 = r06;
        goto L33
    }

    public final void zzC(List r7) {
        boolean r0 = r7 instanceof zzafw;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzafw r02 = (zzafw) r7;
        int r72 = r1 & 7;
        if (r72 == 2) goto L15;
        if (r72 != 5) goto L13;
    L7:
        zzaev r73 = this.zza;
        r02.zzh(r73.zzi());
        if (r73.zzC() == true) goto L34;
        int r74 = r73.zza();
        if (r74 == this.zzb) goto L7;
    L27:
        this.zzd = r74;
        return;
    L34:
        return;
    L13:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L15:
        zzaev r4 = this.zza;
        int r6 = r4.zzn();
        zzS(r6);
        int r5 = r4.zzD() + r6;
    L16:
        r02.zzh(r4.zzi());
        if (r4.zzD() < r5) goto L16;
        return;
    L19:
        int r03 = r1 & 7;
        if (r03 == 2) goto L31;
        if (r03 != 5) goto L29;
    L22:
        zzaev r04 = this.zza;
        r7.add(Integer.valueOf(r04.zzi()));
        if (r04.zzC() == true) goto L46;
        int r05 = r04.zza();
        if (r05 == this.zzb) goto L22;
        r74 = r05;
        goto L27
    L46:
        return;
    L29:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L31:
        zzaev r62 = this.zza;
        int r06 = r62.zzn();
        zzS(r06);
        int r12 = r62.zzD() + r06;
    L32:
        r7.add(Integer.valueOf(r62.zzi()));
        if (r62.zzD() < r12) goto L32;
    }

    public final void zzD(List r4) {
        if ((r4 instanceof zzaei) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        int r42 = this.zzb & 7;
        if (r42 == 0) goto L11;
        if (r42 == 2) goto L9;
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        zzaev r3 = this.zza;
        r3.zzn();
        r3.zzD();
        r3.zzj();
        throw null;
    L11:
        this.zza.zzj();
        throw null;
    L13:
        int r0 = this.zzb & 7;
        if (r0 == 0) goto L23;
        if (r0 != 2) goto L21;
        zzaev r02 = this.zza;
        int r1 = r02.zzn();
        int r2 = r02.zzD() + r1;
    L17:
        r4.add(Boolean.valueOf(r02.zzj()));
        if (r02.zzD() < r2) goto L17;
        zzR(r2);
        return;
    L21:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L23:
        zzaev r03 = this.zza;
        r4.add(Boolean.valueOf(r03.zzj()));
        if (r03.zzC() == true) goto L34;
        int r04 = r03.zza();
        if (r04 == this.zzb) goto L23;
        this.zzd = r04;
        return;
    }

    public final void zzE(List r3, boolean r4) {
        if ((this.zzb & 7) == 2) goto L5;
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L5:
        if ((r3 instanceof zzagn) == false) goto L14;
        if (r4 == true) goto L14;
        zzagn r32 = (zzagn) r3;
    L9:
        zzp();
        r32.zzb();
        zzaev r42 = this.zza;
        if (r42.zzC() == true) goto L19;
        int r43 = r42.zza();
        if (r43 == this.zzb) goto L9;
    L23:
        this.zzd = r43;
        return;
    L19:
        return;
    L14:
        if (r4 == false) goto L16;
        String r0 = zzm();
    L17:
        r3.add(r0);
        zzaev r02 = this.zza;
        if (r02.zzC() == true) goto L34;
        int r03 = r02.zza();
        if (r03 == this.zzb) goto L14;
        r43 = r03;
        goto L23
    L34:
        return;
    L16:
        r0 = zzl();
        goto L17
    }

    public final void zzF(List r4, zzahn r5, zzafh r6) {
        int r0 = this.zzb;
        if ((r0 & 7) != 2) goto L13;
    L4:
        Object r1 = r5.zza();
        zzP(r1, r5, r6);
        r5.zzk(r1);
        r4.add(r1);
        zzaev r12 = this.zza;
        if (r12.zzC() == true) goto L19;
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
        zzaew$$ExternalSyntheticBUOutline0.m();
    }

    @Deprecated
    public final void zzG(List r4, zzahn r5, zzafh r6) {
        int r0 = this.zzb;
        if ((r0 & 7) != 3) goto L13;
    L4:
        Object r1 = r5.zza();
        zzQ(r1, r5, r6);
        r5.zzk(r1);
        r4.add(r1);
        zzaev r12 = this.zza;
        if (r12.zzC() == true) goto L19;
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
        zzaew$$ExternalSyntheticBUOutline0.m();
    }

    public final void zzH(List r3) {
        if ((this.zzb & 7) != 2) goto L11;
    L4:
        r3.add(zzp());
        zzaev r0 = this.zza;
        if (r0.zzC() == true) goto L6;
        int r02 = r0.zza();
        if (r02 == this.zzb) goto L4;
        this.zzd = r02;
        return;
    L6:
        return;
    L11:
        zzaew$$ExternalSyntheticBUOutline0.m();
    }

    public final void zzI(List r4) {
        boolean r0 = r4 instanceof zzafw;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzafw r02 = (zzafw) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzaev r43 = this.zza;
        int r12 = r43.zzn();
        int r2 = r43.zzD() + r12;
    L8:
        r02.zzh(r43.zzn());
        if (r43.zzD() < r2) goto L8;
        zzR(r2);
        return;
    L12:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzaev r44 = this.zza;
        r02.zzh(r44.zzn());
        if (r44.zzC() == true) goto L47;
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
        zzaev r04 = this.zza;
        int r13 = r04.zzn();
        int r22 = r04.zzD() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzn()));
        if (r04.zzD() < r22) goto L24;
        zzR(r22);
        return;
    L28:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzaev r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzn()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    public final void zzJ(List r4) {
        boolean r0 = r4 instanceof zzafw;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzafw r02 = (zzafw) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzaev r43 = this.zza;
        int r12 = r43.zzn();
        int r2 = r43.zzD() + r12;
    L8:
        r02.zzh(r43.zzo());
        if (r43.zzD() < r2) goto L8;
        zzR(r2);
        return;
    L12:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzaev r44 = this.zza;
        r02.zzh(r44.zzo());
        if (r44.zzC() == true) goto L47;
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
        zzaev r04 = this.zza;
        int r13 = r04.zzn();
        int r22 = r04.zzD() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzo()));
        if (r04.zzD() < r22) goto L24;
        zzR(r22);
        return;
    L28:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzaev r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzo()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    public final void zzK(List r7) {
        boolean r0 = r7 instanceof zzafw;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzafw r02 = (zzafw) r7;
        int r72 = r1 & 7;
        if (r72 == 2) goto L15;
        if (r72 != 5) goto L13;
    L7:
        zzaev r73 = this.zza;
        r02.zzh(r73.zzp());
        if (r73.zzC() == true) goto L34;
        int r74 = r73.zza();
        if (r74 == this.zzb) goto L7;
    L27:
        this.zzd = r74;
        return;
    L34:
        return;
    L13:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L15:
        zzaev r4 = this.zza;
        int r6 = r4.zzn();
        zzS(r6);
        int r5 = r4.zzD() + r6;
    L16:
        r02.zzh(r4.zzp());
        if (r4.zzD() < r5) goto L16;
        return;
    L19:
        int r03 = r1 & 7;
        if (r03 == 2) goto L31;
        if (r03 != 5) goto L29;
    L22:
        zzaev r04 = this.zza;
        r7.add(Integer.valueOf(r04.zzp()));
        if (r04.zzC() == true) goto L46;
        int r05 = r04.zza();
        if (r05 == this.zzb) goto L22;
        r74 = r05;
        goto L27
    L46:
        return;
    L29:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L31:
        zzaev r62 = this.zza;
        int r06 = r62.zzn();
        zzS(r06);
        int r12 = r62.zzD() + r06;
    L32:
        r7.add(Integer.valueOf(r62.zzp()));
        if (r62.zzD() < r12) goto L32;
    }

    public final void zzL(List r5) {
        boolean r0 = r5 instanceof zzagq;
        int r1 = this.zzb;
        if (r0 == false) goto L19;
        zzagq r02 = (zzagq) r5;
        int r52 = r1 & 7;
        if (r52 == 1) goto L13;
        if (r52 != 2) goto L11;
        zzaev r4 = this.zza;
        int r53 = r4.zzn();
        zzT(r53);
        int r12 = r4.zzD() + r53;
    L8:
        r02.zzf(r4.zzq());
        if (r4.zzD() < r12) goto L8;
        return;
    L11:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        zzaev r54 = this.zza;
        r02.zzf(r54.zzq());
        if (r54.zzC() == true) goto L46;
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
        zzaev r42 = this.zza;
        int r04 = r42.zzn();
        zzT(r04);
        int r13 = r42.zzD() + r04;
    L23:
        r5.add(Long.valueOf(r42.zzq()));
        if (r42.zzD() < r13) goto L23;
        return;
    L26:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L28:
        zzaev r05 = this.zza;
        r5.add(Long.valueOf(r05.zzq()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L28;
        r55 = r06;
        goto L33
    }

    public final void zzM(List r4) {
        boolean r0 = r4 instanceof zzafw;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzafw r02 = (zzafw) r4;
        int r42 = r1 & 7;
        if (r42 == 0) goto L14;
        if (r42 != 2) goto L12;
        zzaev r43 = this.zza;
        int r12 = r43.zzn();
        int r2 = r43.zzD() + r12;
    L8:
        r02.zzh(r43.zzr());
        if (r43.zzD() < r2) goto L8;
        zzR(r2);
        return;
    L12:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzaev r44 = this.zza;
        r02.zzh(r44.zzr());
        if (r44.zzC() == true) goto L47;
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
        zzaev r04 = this.zza;
        int r13 = r04.zzn();
        int r22 = r04.zzD() + r13;
    L24:
        r4.add(Integer.valueOf(r04.zzr()));
        if (r04.zzD() < r22) goto L24;
        zzR(r22);
        return;
    L28:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzaev r05 = this.zza;
        r4.add(Integer.valueOf(r05.zzr()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r45 = r06;
        goto L35
    }

    public final void zzN(List r6) {
        boolean r0 = r6 instanceof zzagq;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzagq r02 = (zzagq) r6;
        int r62 = r1 & 7;
        if (r62 == 0) goto L14;
        if (r62 != 2) goto L12;
        zzaev r63 = this.zza;
        int r12 = r63.zzn();
        int r2 = r63.zzD() + r12;
    L8:
        r02.zzf(r63.zzs());
        if (r63.zzD() < r2) goto L8;
        zzR(r2);
        return;
    L12:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzaev r64 = this.zza;
        r02.zzf(r64.zzs());
        if (r64.zzC() == true) goto L47;
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
        zzaev r04 = this.zza;
        int r13 = r04.zzn();
        int r22 = r04.zzD() + r13;
    L24:
        r6.add(Long.valueOf(r04.zzs()));
        if (r04.zzD() < r22) goto L24;
        zzR(r22);
        return;
    L28:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzaev r05 = this.zza;
        r6.add(Long.valueOf(r05.zzs()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r65 = r06;
        goto L35
    }

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

    public final int zzc() {
        return this.zzb;
    }

    public final double zzd() {
        zzO(1);
        return this.zza.zzc();
    }

    public final float zze() {
        zzO(5);
        return this.zza.zzd();
    }

    public final long zzf() {
        zzO(0);
        return this.zza.zze();
    }

    public final long zzg() {
        zzO(0);
        return this.zza.zzf();
    }

    public final int zzh() {
        zzO(0);
        return this.zza.zzg();
    }

    public final long zzi() {
        zzO(1);
        return this.zza.zzh();
    }

    public final int zzj() {
        zzO(5);
        return this.zza.zzi();
    }

    public final boolean zzk() {
        zzO(0);
        return this.zza.zzj();
    }

    public final String zzl() {
        zzO(2);
        return this.zza.zzk();
    }

    public final String zzm() {
        zzO(2);
        return this.zza.zzl();
    }

    public final void zzn(Object r2, zzahn r3, zzafh r4) {
        zzO(2);
        zzP(r2, r3, r4);
    }

    public final void zzo(Object r2, zzahn r3, zzafh r4) {
        zzO(3);
        zzQ(r2, r3, r4);
    }

    public final zzaer zzp() {
        zzO(2);
        return this.zza.zzm();
    }

    public final int zzq() {
        zzO(0);
        return this.zza.zzn();
    }

    public final int zzr() {
        zzO(0);
        return this.zza.zzo();
    }

    public final int zzs() {
        zzO(5);
        return this.zza.zzp();
    }

    public final long zzt() {
        zzO(1);
        return this.zza.zzq();
    }

    public final int zzu() {
        zzO(0);
        return this.zza.zzr();
    }

    public final long zzv() {
        zzO(0);
        return this.zza.zzs();
    }

    public final void zzw(List r5) {
        if ((r5 instanceof zzafc) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        int r52 = this.zzb & 7;
        if (r52 == 1) goto L11;
        if (r52 == 2) goto L9;
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        zzaev r4 = this.zza;
        zzT(r4.zzn());
        r4.zzD();
        r4.zzc();
        throw null;
    L11:
        this.zza.zzc();
        throw null;
    L13:
        int r0 = this.zzb & 7;
        if (r0 == 1) goto L22;
        if (r0 != 2) goto L20;
        zzaev r42 = this.zza;
        int r02 = r42.zzn();
        zzT(r02);
        int r1 = r42.zzD() + r02;
    L17:
        r5.add(Double.valueOf(r42.zzc()));
        if (r42.zzD() < r1) goto L17;
        return;
    L20:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L22:
        zzaev r03 = this.zza;
        r5.add(Double.valueOf(r03.zzc()));
        if (r03.zzC() == true) goto L34;
        int r04 = r03.zza();
        if (r04 == this.zzb) goto L22;
        this.zzd = r04;
        return;
    }

    public final void zzx(List r4) {
        if ((r4 instanceof zzafo) == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        int r42 = this.zzb & 7;
        if (r42 == 2) goto L11;
        if (r42 == 5) goto L9;
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        this.zza.zzd();
        throw null;
    L11:
        zzaev r3 = this.zza;
        zzS(r3.zzn());
        r3.zzD();
        r3.zzd();
        throw null;
    L13:
        int r0 = this.zzb & 7;
        if (r0 == 2) goto L24;
        if (r0 != 5) goto L22;
    L16:
        zzaev r02 = this.zza;
        r4.add(Float.valueOf(r02.zzd()));
        if (r02.zzC() == true) goto L27;
        int r03 = r02.zza();
        if (r03 == this.zzb) goto L16;
        this.zzd = r03;
        return;
    L27:
        return;
    L22:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L24:
        zzaev r32 = this.zza;
        int r04 = r32.zzn();
        zzS(r04);
        int r1 = r32.zzD() + r04;
    L25:
        r4.add(Float.valueOf(r32.zzd()));
        if (r32.zzD() < r1) goto L25;
    }

    public final void zzy(List r6) {
        boolean r0 = r6 instanceof zzagq;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzagq r02 = (zzagq) r6;
        int r62 = r1 & 7;
        if (r62 == 0) goto L14;
        if (r62 != 2) goto L12;
        zzaev r63 = this.zza;
        int r12 = r63.zzn();
        int r2 = r63.zzD() + r12;
    L8:
        r02.zzf(r63.zze());
        if (r63.zzD() < r2) goto L8;
        zzR(r2);
        return;
    L12:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzaev r64 = this.zza;
        r02.zzf(r64.zze());
        if (r64.zzC() == true) goto L47;
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
        zzaev r04 = this.zza;
        int r13 = r04.zzn();
        int r22 = r04.zzD() + r13;
    L24:
        r6.add(Long.valueOf(r04.zze()));
        if (r04.zzD() < r22) goto L24;
        zzR(r22);
        return;
    L28:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzaev r05 = this.zza;
        r6.add(Long.valueOf(r05.zze()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r65 = r06;
        goto L35
    }

    public final void zzz(List r6) {
        boolean r0 = r6 instanceof zzagq;
        int r1 = this.zzb;
        if (r0 == false) goto L20;
        zzagq r02 = (zzagq) r6;
        int r62 = r1 & 7;
        if (r62 == 0) goto L14;
        if (r62 != 2) goto L12;
        zzaev r63 = this.zza;
        int r12 = r63.zzn();
        int r2 = r63.zzD() + r12;
    L8:
        r02.zzf(r63.zzf());
        if (r63.zzD() < r2) goto L8;
        zzR(r2);
        return;
    L12:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        zzaev r64 = this.zza;
        r02.zzf(r64.zzf());
        if (r64.zzC() == true) goto L47;
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
        zzaev r04 = this.zza;
        int r13 = r04.zzn();
        int r22 = r04.zzD() + r13;
    L24:
        r6.add(Long.valueOf(r04.zzf()));
        if (r04.zzD() < r22) goto L24;
        zzR(r22);
        return;
    L28:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        zzaev r05 = this.zza;
        r6.add(Long.valueOf(r05.zzf()));
        if (r05.zzC() == true) goto L48;
        int r06 = r05.zza();
        if (r06 == this.zzb) goto L30;
        r65 = r06;
        goto L35
    }
}
