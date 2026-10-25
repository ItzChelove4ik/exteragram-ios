package com.google.android.gms.clearcut;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.clearcut.zzaa;
import com.google.android.gms.internal.clearcut.zzge$zzv$zzb;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzj;
import com.google.android.gms.internal.clearcut.zzp;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.ArrayList;
import java.util.TimeZone;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class ClearcutLogger {

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API = null;
    private static final Api.AbstractClientBuilder<zzj, Api.ApiOptions.NoOptions> CLIENT_BUILDER = null;
    private static final Api.ClientKey<zzj> CLIENT_KEY = null;
    private static final ExperimentTokens[] zze = null;
    private static final String[] zzf = null;
    private static final byte[][] zzg = null;
    private final String packageName;
    private final Context zzh;
    private final int zzi;
    private String zzj;
    private int zzk;
    private String zzl;
    private String zzm;
    private final boolean zzn;
    private zzge$zzv$zzb zzo;
    private final com.google.android.gms.clearcut.zzb zzp;
    private final Clock zzq;
    private zzc zzr;
    private final zza zzs;

    public class LogEventBuilder {
        private final zzha zzaa;
        private boolean zzab;
        private final /* synthetic */ ClearcutLogger zzac;
        private String zzj;
        private int zzk;
        private String zzl;
        private String zzm;
        private zzge$zzv$zzb zzo;
        private ArrayList<Integer> zzu;
        private ArrayList<String> zzv;
        private ArrayList<Integer> zzw;
        private ArrayList<ExperimentTokens> zzx;
        private ArrayList<byte[]> zzy;
        private boolean zzz;

        private LogEventBuilder(ClearcutLogger r2, byte[] r3) {
            this(r2, r3, null);
        }

        private LogEventBuilder(ClearcutLogger r4, byte[] r5, zzb r6) {
            this.zzac = r4;
            this.zzk = ClearcutLogger.zza(r4);
            this.zzj = ClearcutLogger.zzb(r4);
            this.zzl = ClearcutLogger.zzc(r4);
            this.zzm = null;
            this.zzo = ClearcutLogger.zzd(r4);
            this.zzu = null;
            this.zzv = null;
            this.zzw = null;
            this.zzx = null;
            this.zzy = null;
            this.zzz = true;
            zzha r0 = new zzha();
            this.zzaa = r0;
            this.zzab = false;
            this.zzl = ClearcutLogger.zzc(r4);
            this.zzm = null;
            r0.zzbkc = zzaa.zze(ClearcutLogger.zze(r4));
            r0.zzbjf = ClearcutLogger.zzf(r4).currentTimeMillis();
            r0.zzbjg = ClearcutLogger.zzf(r4).elapsedRealtime();
            ClearcutLogger.zzg(r4);
            long r3 = r0.zzbjf;
            r0.zzbju = TimeZone.getDefault().getOffset(r3) / 1000;
            if (r5 == null) goto L6;
            r0.zzbjp = r5;
            return;
        }

        public /* synthetic */ LogEventBuilder(ClearcutLogger r1, byte[] r2, com.google.android.gms.clearcut.zza r3) {
            this(r1, r2);
        }

        public void log() {
            if (this.zzab == true) goto L10;
            this.zzab = true;
            zze r1 = new zze(new zzr(ClearcutLogger.zzi(this.zzac), ClearcutLogger.zzj(this.zzac), this.zzk, this.zzj, this.zzl, this.zzm, ClearcutLogger.zzh(this.zzac), this.zzo), this.zzaa, null, null, ClearcutLogger.zzb(null), null, ClearcutLogger.zzb(null), null, null, this.zzz);
            if (ClearcutLogger.zzk(this.zzac).zza(r1) == false) goto L8;
            ClearcutLogger.zzl(this.zzac).zzb(r1);
            return;
        L8:
            PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, null);
            return;
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("do not reuse LogEventBuilder");
        }

        public LogEventBuilder setEventCode(int r2) {
            this.zzaa.zzbji = r2;
            return this;
        }
    }

    public interface zza {
        boolean zza(zze r1);
    }

    public interface zzb {
    }

    public static class zzc {
        public zzc() {
        }
    }

    static {
        Api.ClientKey<zzj> r0 = new Api.ClientKey();
        CLIENT_KEY = r0;
        com.google.android.gms.clearcut.zza r1 = new com.google.android.gms.clearcut.zza();
        CLIENT_BUILDER = r1;
        API = new Api("ClearcutLogger.API", r1, r0);
        zze = new ExperimentTokens[0];
        zzf = new String[0];
        zzg = new byte[0][];
    }

    @VisibleForTesting
    private ClearcutLogger(Context r1, int r2, String r3, String r4, String r5, boolean r6, com.google.android.gms.clearcut.zzb r7, Clock r8, zzc r9, zza r10) {
        this.zzk = -1;
        zzge$zzv$zzb r52 = zzge$zzv$zzb.zzbhk;
        this.zzo = r52;
        this.zzh = r1;
        this.packageName = r1.getPackageName();
        this.zzi = zza(r1);
        this.zzk = -1;
        this.zzj = r3;
        this.zzl = r4;
        this.zzm = null;
        this.zzn = r6;
        this.zzp = r7;
        this.zzq = r8;
        this.zzr = new zzc();
        this.zzo = r52;
        this.zzs = r10;
        if (r6 == false) goto L9;
        if (r4 != null) goto L6;
        boolean r0 = true;
    L7:
        Preconditions.checkArgument(r0, "can't be anonymous with an upload account");
        return;
    L6:
        r0 = false;
        goto L7
    }

    public ClearcutLogger(Context r12, String r13, String r14) {
        this(r12, -1, r13, r14, null, false, com.google.android.gms.internal.clearcut.zze.zzb(r12), DefaultClock.getInstance(), null, new zzp(r12));
    }

    private static int zza(Context r3) {
        return r3.getPackageManager().getPackageInfo(r3.getPackageName(), 0).versionCode;
    L5:
        e = move-exception;
        Log.wtf("ClearcutLogger", "This can't happen.", e);
        return 0;
    }

    public static /* synthetic */ int zza(ClearcutLogger r0) {
        return r0.zzk;
    }

    private static int[] zza(ArrayList<Integer> r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int[] r0 = new int[r6.size()];
        int r1 = r6.size();
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r1) goto L8;
        Integer r4 = r6.get(r2);
        r2 = r2 + 1;
        r0[r3] = r4.intValue();
        r3 = r3 + 1;
        goto L6
    L8:
        return r0;
    }

    public static /* synthetic */ String zzb(ClearcutLogger r0) {
        return r0.zzj;
    }

    public static /* synthetic */ int[] zzb(ArrayList r0) {
        return zza(null);
    }

    public static /* synthetic */ String zzc(ClearcutLogger r0) {
        return r0.zzl;
    }

    public static /* synthetic */ zzge$zzv$zzb zzd(ClearcutLogger r0) {
        return r0.zzo;
    }

    public static /* synthetic */ Context zze(ClearcutLogger r0) {
        return r0.zzh;
    }

    public static /* synthetic */ Clock zzf(ClearcutLogger r0) {
        return r0.zzq;
    }

    public static /* synthetic */ zzc zzg(ClearcutLogger r0) {
        return r0.zzr;
    }

    public static /* synthetic */ boolean zzh(ClearcutLogger r0) {
        return r0.zzn;
    }

    public static /* synthetic */ String zzi(ClearcutLogger r0) {
        return r0.packageName;
    }

    public static /* synthetic */ int zzj(ClearcutLogger r0) {
        return r0.zzi;
    }

    public static /* synthetic */ zza zzk(ClearcutLogger r0) {
        return r0.zzs;
    }

    public static /* synthetic */ com.google.android.gms.clearcut.zzb zzl(ClearcutLogger r0) {
        return r0.zzp;
    }

    public final LogEventBuilder newEvent(byte[] r3) {
        return new LogEventBuilder(this, r3, null);
    }
}
