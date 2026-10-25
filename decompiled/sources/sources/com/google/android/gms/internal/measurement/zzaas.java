package com.google.android.gms.internal.measurement;

import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzaas extends zzaag {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Set zzb = null;
    private static final zzzq zzc = null;
    private static final zzaaq zzd = null;
    private final String zze;
    private final Level zzf;
    private final Set zzg;
    private final zzzq zzh;
    private final int zzi;

    static {
        Set r0 = Collections.unmodifiableSet(new HashSet(Arrays.asList(new zzyl[]{zzxx.zza, zzyw.zza, zzyx.zza})));
        zzb = r0;
        zzc = zzzt.zza(r0).zzc();
        zzd = new zzaaq(null);
    }

    public /* synthetic */ zzaas(String r1, String r2, boolean r3, int r4, Level r5, Set r6, zzzq r7, byte[] r8) {
        super(r2);
        this.zze = zzaal.zza(_UrlKt.FRAGMENT_ENCODE_SET, r2, true);
        this.zzi = 2;
        this.zzf = r5;
        this.zzg = r6;
        this.zzh = r7;
    }

    public static zzaaq zze() {
        return zzd;
    }

    public static /* synthetic */ Set zzf() {
        return zzb;
    }

    public static /* synthetic */ zzzq zzg() {
        return zzc;
    }

    public static /* synthetic */ void zzh(zzzd r0, String r1, int r2, Level r3, Set r4, zzzq r5) {
        zzi(r0, r1, 2, r3, r4, r5);
    }

    private static void zzi(zzzd r3, String r4, int r5, Level r6, Set r7, zzzq r8) {
        Boolean r52 = (Boolean) r3.zzl().zzd(zzyx.zza);
        if (r52 != null) goto L5;
    L7:
        zzaaa r53 = zzaaa.zzh(zzaad.zzj(), r3.zzl());
        if (r3.zze().intValue() >= r6.intValue()) goto L10;
        boolean r62 = true;
    L12:
        if (r62 == true) goto L21;
        int r1 = zzaae.$r8$clinit;
        if (r3.zzh() != null) goto L21;
        if (r53.zzb() > r7.size()) goto L21;
        if (r7.containsAll(r53.zzc()) == false) goto L21;
        String r54 = zzzh.zza(r3.zzj());
    L30:
        Throwable r63 = (Throwable) r3.zzl().zzd(zzxx.zza);
        int r32 = zzaal.zzb(r3.zze());
        if (r32 != 2) goto L33;
        Log.v(r4, r54, r63);
        return;
    L33:
        if (r32 != 3) goto L35;
        Log.d(r4, r54, r63);
        return;
    L35:
        if (r32 != 4) goto L37;
        Log.i(r4, r54, r63);
        return;
    L37:
        if (r32 == 5) goto L40;
        Log.e(r4, r54, r63);
        return;
    L40:
        Log.w(r4, r54, r63);
        return;
    L21:
        StringBuilder r72 = new StringBuilder();
        if (zzze.zza(2, r3.zzg(), r72) == false) goto L24;
        r72.append(" ");
    L24:
        if (r62 == true) goto L26;
    L28:
        zzyy.zza(r3, r72);
        int r64 = zzaae.$r8$clinit;
        zzzc r65 = new zzzc("[CONTEXT ", " ]", r72);
        r53.zza(r8, r65);
        r65.zzb();
    L29:
        r54 = r72.toString();
        goto L30
    L26:
        if (r3.zzh() == null) goto L28;
        r72.append("(REDACTED) ");
        r72.append(r3.zzh().zzb());
        goto L29
    L10:
        r62 = false;
        goto L12
    L5:
        if (r52.booleanValue() == false) goto L7;
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    public final boolean zzb(Level r1) {
        String r0 = this.zze;
        int r12 = zzaal.zzb(r1);
        if (Log.isLoggable(r0, r12) == false) goto L5;
        return true;
    L5:
        if (Log.isLoggable("all", r12) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    public final void zzc(zzzd r7) {
        Level r3 = this.zzf;
        Set r4 = this.zzg;
        zzzq r5 = this.zzh;
        zzi(r7, this.zze, 2, r3, r4, r5);
    }
}
