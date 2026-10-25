package com.google.android.gms.cast.framework;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.internal.cast.zzpm;

/* loaded from: classes4.dex */
public class PrecacheManager {
    private final Logger zza;
    private final CastOptions zzb;
    private final SessionManager zzc;
    private final com.google.android.gms.cast.internal.zzn zzd;

    public PrecacheManager(CastOptions r3, SessionManager r4, com.google.android.gms.cast.internal.zzn r5) {
        this.zza = new Logger("PrecacheManager");
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    public void precache(String r5) {
        com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzr);
        Session r0 = this.zzc.getCurrentSession();
        if (r5 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("No precache data found to be precached");
        return;
    L5:
        if (r0 != null) goto L9;
        this.zzd.zza(new String[]{this.zzb.getReceiverApplicationId()}, r5, null);
        return;
    L9:
        if ((r0 instanceof CastSession) == false) goto L16;
        RemoteMediaClient r02 = ((CastSession) r0).getRemoteMediaClient();
        if (r02 == null) goto L14;
        r02.zzh(r5, null);
        return;
    L14:
        this.zza.e("Failed to get RemoteMediaClient from current cast session.", new Object[0]);
        return;
    L16:
        this.zza.e("Current session is not a CastSession. Precache is not supported.", new Object[0]);
    }
}
