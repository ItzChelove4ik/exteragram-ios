package com.google.android.gms.cast.framework;

/* loaded from: classes4.dex */
public class MediaNotificationManager {
    private final SessionManager zza;

    public MediaNotificationManager(SessionManager r1) {
        this.zza = r1;
    }

    public void updateNotification() {
        CastSession r1 = this.zza.getCurrentCastSession();
        if (r1 == null) goto L6;
        r1.zzc().zzd(true);
        return;
    }
}
