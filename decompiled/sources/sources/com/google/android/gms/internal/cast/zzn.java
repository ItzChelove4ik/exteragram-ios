package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes4.dex */
public final class zzn {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Logger zzb = null;
    private final zzj zzc;
    private final zzax zzd;
    private final zzp zze;
    private final Runnable zzf;
    private final Handler zzg;
    private final SharedPreferences zzh;
    private zzo zzi;
    private CastSession zzj;
    private boolean zzk;

    static {
        zzb = new Logger("ApplicationAnalytics");
    }

    public zzn(SharedPreferences r1, zzj r2, zzax r3, Bundle r4, String r5) {
        this.zzh = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = new zzp(r4, r5);
        this.zzg = new zzfk(Looper.getMainLooper());
        this.zzf = new zzk(this);
    }

    public static /* synthetic */ Logger zzi() {
        return zzb;
    }

    private final void zzq() {
        ((Handler) Preconditions.checkNotNull(this.zzg)).postDelayed((Runnable) Preconditions.checkNotNull(this.zzf), 300000);
    }

    private final void zzr() {
        this.zzg.removeCallbacks(this.zzf);
    }

    @EnsuresNonNull({"analyticsSession"})
    private final void zzs() {
        int r1 = 0;
        zzb.d("Create a new ApplicationAnalyticsSession based on CastSession", new Object[0]);
        zzo r0 = zzo.zza(this.zzd);
        this.zzi = r0;
        zzo r02 = (zzo) Preconditions.checkNotNull(r0);
        CastSession r2 = this.zzj;
        if (r2 != null) goto L5;
    L7:
        boolean r22 = false;
    L8:
        r02.zzo = r22;
        ((zzo) Preconditions.checkNotNull(this.zzi)).zzb = zzx();
        CastSession r03 = this.zzj;
        if (r03 != null) goto L11;
        CastDevice r04 = null;
    L12:
        if (r04 == null) goto L14;
        zzu(r04);
    L14:
        zzo r05 = (zzo) Preconditions.checkNotNull(this.zzi);
        CastSession r23 = this.zzj;
        if (r23 == null) goto L18;
        r1 = r23.zzm();
    L18:
        r05.zzp = r1;
        Preconditions.checkNotNull(this.zzi);
        return;
    L11:
        r04 = r03.getCastDevice();
        goto L12
    L5:
        if (r2.zzd() == false) goto L7;
        r22 = true;
        goto L8
    }

    @EnsuresNonNull({"analyticsSession"})
    private final void zzt() {
        if (zzv() == false) goto L14;
        CastSession r0 = this.zzj;
        if (r0 == null) goto L7;
        CastDevice r02 = r0.getCastDevice();
    L8:
        if (r02 != null) goto L10;
    L12:
        Preconditions.checkNotNull(this.zzi);
        return;
    L10:
        if (TextUtils.equals(this.zzi.zzc, r02.zza()) == true) goto L12;
        zzu(r02);
        goto L12
    L7:
        r02 = null;
        goto L8
    L14:
        zzb.w("The analyticsSession should not be null for logging. Create a dummy one.", new Object[0]);
        zzs();
    }

    private final void zzu(CastDevice r2) {
        zzo r1 = this.zzi;
        if (r1 == null) goto L25;
        r1.zzc = r2.zza();
        r1.zzg = r2.zzc();
        r1.zzh = r2.getModelName();
        r1.zzn = r2.zzd();
        com.google.android.gms.cast.internal.zzaa r22 = r2.zzb();
        if (r22 == null) goto L23;
        String r0 = r22.zza();
        if (r0 == null) goto L10;
        r1.zzi = r0;
    L10:
        String r02 = r22.zzb();
        if (r02 == null) goto L13;
        r1.zzj = r02;
    L13:
        String r03 = r22.zzc();
        if (r03 == null) goto L16;
        r1.zzk = r03;
    L16:
        String r04 = r22.zzd();
        if (r04 == null) goto L19;
        r1.zzl = r04;
    L19:
        String r23 = r22.zze();
        if (r23 == null) goto L24;
        r1.zzm = r23;
        return;
    L24:
        return;
    L23:
        return;
    }

    @EnsuresNonNullIf(expression = {"analyticsSession"}, result = true)
    private final boolean zzv() {
        if (this.zzi != null) goto L6;
        zzb.d("The analytics session is null when matching with application ID.", new Object[0]);
        return false;
    L6:
        String r0 = zzx();
        if (r0 == null) goto L15;
        String r2 = this.zzi.zzb;
        if (r2 == null) goto L15;
        if (TextUtils.equals(r2, r0) == false) goto L15;
        Preconditions.checkNotNull(this.zzi);
        return true;
    L15:
        zzb.d("The analytics session doesn't match the application ID %s", new Object[]{r0});
        return false;
    }

    private final boolean zzw(String r3) {
        if (zzv() == true) goto L5;
        return false;
    L5:
        Preconditions.checkNotNull(this.zzi);
        if (r3 == null) goto L14;
        String r2 = this.zzi.zzf;
        if (r2 == null) goto L14;
        if (TextUtils.equals(r2, r3) == false) goto L14;
        return true;
    L14:
        zzb.d("The analytics session doesn't match the receiver session ID %s.", new Object[]{r3});
        return false;
    }

    @Pure
    private static String zzx() {
        return ((CastContext) Preconditions.checkNotNull(CastContext.getSharedInstance())).getCastOptions().getReceiverApplicationId();
    }

    public final /* synthetic */ void zza() {
        zzo r0 = this.zzi;
        if (r0 == null) goto L5;
        zzp r1 = this.zze;
        this.zzc.zzd(r1.zza(r0), Opcodes.XOR_INT_LIT8);
    L5:
        zzq();
    }

    public final /* synthetic */ void zzb() {
        zzq();
    }

    public final /* synthetic */ void zzc() {
        zzr();
    }

    public final /* synthetic */ void zzd() {
        zzs();
    }

    public final /* synthetic */ void zze() {
        zzt();
    }

    public final /* synthetic */ void zzf(SharedPreferences r5, String r6) {
        boolean r1 = false;
        if (zzw(r6) == false) goto L6;
        zzb.d("Use the existing ApplicationAnalyticsSession if it is available and valid.", new Object[0]);
        Preconditions.checkNotNull(this.zzi);
        return;
    L6:
        zzax r0 = this.zzd;
        this.zzi = zzo.zzc(r5, r0);
        if (zzw(r6) == false) goto L10;
        zzb.d("Use the restored ApplicationAnalyticsSession if it is valid.", new Object[0]);
        Preconditions.checkNotNull(this.zzi);
        zzo.zza = this.zzi.zzd + 1;
        return;
    L10:
        zzb.d("The restored ApplicationAnalyticsSession is not valid, create a new one.", new Object[0]);
        zzo r52 = zzo.zza(r0);
        this.zzi = r52;
        zzo r53 = (zzo) Preconditions.checkNotNull(r52);
        CastSession r02 = this.zzj;
        if (r02 != null) goto L13;
    L15:
        r53.zzo = r1;
        zzo r54 = (zzo) Preconditions.checkNotNull(this.zzi);
        r54.zzb = zzx();
        ((zzo) Preconditions.checkNotNull(this.zzi)).zzf = r6;
        return;
    L13:
        if (r02.zzd() == false) goto L15;
        r1 = true;
        goto L15
    }

    public final /* synthetic */ void zzg() {
        this.zzi.zzd(this.zzh);
    }

    public final /* synthetic */ void zzh(int r4) {
        zzb.d("log session ended with error = %d", new Object[]{Integer.valueOf(r4)});
        zzt();
        zzqr r42 = this.zze.zze(this.zzi, r4);
        this.zzc.zzd(r42, 228);
        zzr();
        if (this.zzk == true) goto L6;
        this.zzi = null;
        return;
    }

    public final /* synthetic */ zzj zzj() {
        return this.zzc;
    }

    public final /* synthetic */ zzp zzk() {
        return this.zze;
    }

    public final /* synthetic */ SharedPreferences zzl() {
        return this.zzh;
    }

    public final /* synthetic */ zzo zzm() {
        return this.zzi;
    }

    public final /* synthetic */ void zzn(zzo r1) {
        this.zzi = null;
    }

    public final /* synthetic */ void zzo(CastSession r1) {
        this.zzj = r1;
    }

    public final /* synthetic */ void zzp(boolean r1) {
        this.zzk = r1;
    }
}
