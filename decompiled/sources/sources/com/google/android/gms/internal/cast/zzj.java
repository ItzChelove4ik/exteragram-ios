package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes4.dex */
public final class zzj {
    public static boolean zza;
    private static final Logger zzc = null;
    Transport zzb;
    private final Context zzd;
    private final com.google.android.gms.cast.internal.zzn zze;
    private final SessionManager zzf;
    private final zzce zzg;
    private final zzax zzh;
    private final String zzi;
    private Long zzj;
    private final ExecutorService zzk;
    private zzcn zzl;
    private int zzm;

    static {
        zzc = new Logger("ClientCastAnalytics");
        zza = true;
    }

    private zzj(Context r1, com.google.android.gms.cast.internal.zzn r2, SessionManager r3, zzce r4, zzax r5) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
        this.zzg = r4;
        this.zzh = r5;
        this.zzm = 1;
        this.zzi = UUID.randomUUID().toString();
        zzfj.zza();
        this.zzk = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
    }

    public static zzj zza(Context r6, com.google.android.gms.cast.internal.zzn r7, SessionManager r8, zzce r9, zzax r10) {
        return new zzj(r6, r7, r8, r9, r10);
    }

    public final void zzb(Bundle r12) {
        int r2 = 1;
        if (r12.containsKey("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE") == false) goto L6;
        int r0 = r12.getInt("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE", 0);
    L11:
        boolean r1 = r12.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_FEATURE_USAGE_ANALYTICS_ENABLED", false);
        boolean r4 = r12.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_ANALYTICS_ENABLED", false);
        zza = r4;
        if (r0 != 0) goto L16;
        if (r1 == true) goto L15;
        if (r4 == true) goto L15;
        return;
    L15:
        r0 = 0;
    L16:
        long r42 = r12.getLong("com.google.android.gms.cast.FLAG_ANALYTICS_CONSENT_TIMEOUT_SECONDS", 5);
        Context r6 = this.zzd;
        this.zzl = new zzcn(r6, r42);
        String r43 = r6.getPackageName();
        String r5 = String.format(Locale.ROOT, "%s.%s", new Object[]{r43, "client_cast_analytics_data"});
        if (r12.getLong("com.google.android.gms.cast.FLAG_FIRELOG_UPLOAD_MODE") == 0) goto L20;
        r2 = 2;
    L20:
        this.zzm = r2;
        TransportRuntime.initialize(r6);
        this.zzb = TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE).getTransport("CAST_SENDER_SDK", zzqr.class, Encoding.of("proto"), zzf.zza);
        if (r12.containsKey("com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE") == false) goto L23;
        this.zzj = Long.valueOf(r12.getLong("com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE"));
    L23:
        SharedPreferences r122 = r6.getApplicationContext().getSharedPreferences(r5, 0);
        if (r0 == 0) goto L26;
        this.zze.zzc(new String[]{"com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_ERROR", "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_CHANGE_REASON"}).addOnSuccessListener(new zzi(this, r43, r0, r122));
    L26:
        if (r1 == false) goto L29;
        Preconditions.checkNotNull(r122);
        zzr.zza(r122, this, r43).zzc();
        zzr.zzb(zzpm.zzf);
    L29:
        if (zza == false) goto L33;
        zzu.zza(this, r43);
        return;
    L33:
        return;
    L6:
        if (r12.containsKey("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED") == true) goto L8;
    L10:
        r0 = 0;
        goto L11
    L8:
        if (r12.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED", false) == false) goto L10;
        r0 = 1;
        goto L11
    }

    public final /* synthetic */ void zzc(String r12, int r13, SharedPreferences r14, Bundle r15) {
        SessionManager r0 = this.zzf;
        Preconditions.checkNotNull(r0);
        zzce r1 = this.zzg;
        if (r13 == 3) goto L6;
        if (r13 != 2) goto L10;
        r13 = 2;
    L10:
        if (r13 == 1) goto L12;
        if (r13 == 2) goto L12;
        return;
    L12:
        zzn r5 = new zzn(r14, this, this.zzh, r15, r12);
        r0.addSessionManagerListener(new zzl(r5), CastSession.class);
        if (r1 == null) goto L17;
        r1.zzc(new zzm(r5));
        return;
    L17:
        return;
    L6:
        zzy r52 = new zzy(this, this.zzh, r12);
        r0.addSessionManagerListener(new zzw(r52), CastSession.class);
        if (r1 == null) goto L10;
        r1.zzc(new zzx(r52));
        goto L10
    }

    @Pure
    public final void zzd(zzqr r2, int r3) {
        zzg r0 = new zzg(this, r2, r3);
        this.zzk.execute(r0);
    }

    public final /* synthetic */ void zze(zzqr r3, int r4) {
        zzcn r0 = this.zzl;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zza().addOnSuccessListener(new zzh(this, r3, r4));
    }

    public final /* synthetic */ void zzf(zzqr r3, int r4, Boolean r5) {
        if (r5.booleanValue() == false) goto L24;
        zzqq r32 = zzqr.zzd(r3);
        String r52 = this.zzi;
        r32.zzc(r52);
        r32.zzd(r52);
        Long r53 = this.zzj;
        if (r53 == null) goto L8;
        r32.zze((int) r53.longValue());
    L8:
        zzqr r33 = (zzqr) r32.zzu();
        int r54 = this.zzm;
        int r0 = r54 - 1;
        if (r54 == 0) goto L22;
        int r42 = r4 - 1;
        if (r0 != 0) goto L13;
        Event r34 = Event.ofTelemetry(r42, r33);
    L17:
        zzc.d("analytics event: %s", new Object[]{r34});
        Preconditions.checkNotNull(r34);
        Transport r2 = this.zzb;
        if (r2 == null) goto L23;
        r2.send(r34);
        return;
    L23:
        return;
    L13:
        if (r0 == 1) goto L15;
        r34 = Event.ofTelemetry(r42, r33);
        goto L17
    L15:
        r34 = Event.ofData(r42, r33);
        goto L17
    L22:
        throw null;
    }
}
