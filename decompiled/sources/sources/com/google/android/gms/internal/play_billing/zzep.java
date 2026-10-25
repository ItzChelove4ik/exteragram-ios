package com.google.android.gms.internal.play_billing;

import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class zzep extends zzdz {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Logger zzc = null;
    private static final boolean zzd = false;
    zzeq zza;

    static {
        zzc = Logger.getLogger(zzep.class.getName());
        zzd = zzho.zzx();
    }

    public /* synthetic */ zzep(zzeo r1) {
    }

    public static int zzA(zzgl r0, zzgv r1) {
        int r02 = ((zzds) r0).zze(r1);
        return zzC(r02) + r02;
    }

    public static int zzB(String r1) {
        int r12 = zzhr.zzc(r1);     // Catch: zzhq -> L4
    L6:
        return zzC(r12) + r12;
    L4:
        r12 = r1.getBytes(zzfo.zza).length;
        goto L6
    }

    public static int zzC(int r0) {
        return (352 - (Integer.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static int zzD(long r0) {
        return (640 - (Long.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static /* bridge */ /* synthetic */ boolean zzG() {
        return zzd;
    }

    @Deprecated
    public static int zzy(int r0, zzgl r1, zzgv r2) {
        int r02 = zzC(r0 << 3);
        return (r02 + r02) + ((zzds) r1).zze(r2);
    }

    public final void zzE() {
        if (zza() != 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Did not write as much data as expected.");
    }

    public final void zzF(String r7, zzhq r8) {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", r8);
        byte[] r72 = r7.getBytes(zzfo.zza);
        int r82 = r72.length;     // Catch: IndexOutOfBoundsException -> L5
        zzv(r82);     // Catch: IndexOutOfBoundsException -> L5
        zzm(r72, 0, r82);     // Catch: IndexOutOfBoundsException -> L5
        return;
    L5:
        e = move-exception;
        throw new zzen(e);
    }

    public abstract int zza();

    public abstract void zzb(byte r1);

    public abstract void zzd(int r1, boolean r2);

    public abstract void zze(int r1, zzei r2);

    public abstract void zzg(int r1, int r2);

    public abstract void zzh(int r1);

    public abstract void zzi(int r1, long r2);

    public abstract void zzj(long r1);

    public abstract void zzk(int r1, int r2);

    public abstract void zzl(int r1);

    public abstract void zzm(byte[] r1, int r2, int r3);

    public abstract void zzn(int r1, zzgl r2, zzgv r3);

    public abstract void zzr(int r1, String r2);

    public abstract void zzt(int r1, int r2);

    public abstract void zzu(int r1, int r2);

    public abstract void zzv(int r1);

    public abstract void zzw(int r1, long r2);

    public abstract void zzx(long r1);
}
