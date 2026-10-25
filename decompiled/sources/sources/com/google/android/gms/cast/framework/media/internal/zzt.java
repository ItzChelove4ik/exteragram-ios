package com.google.android.gms.cast.framework.media.internal;

import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.internal.Logger;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzt {
    private static final Logger zza = null;

    static {
        zza = new Logger("MediaSessionUtils");
    }

    public static String zza(MediaMetadata r4) {
        String r0 = MediaMetadata.KEY_SUBTITLE;
        if (r4.containsKey(MediaMetadata.KEY_SUBTITLE) == true) goto L26;
        int r1 = r4.getMediaType();
        if (r1 != 1) goto L7;
        r0 = MediaMetadata.KEY_STUDIO;
        goto L26
    L7:
        if (r1 != 2) goto L9;
        r0 = MediaMetadata.KEY_SERIES_TITLE;
        goto L26
    L9:
        if (r1 == 3) goto L15;
        if (r1 != 4) goto L26;
    L13:
        r0 = MediaMetadata.KEY_ARTIST;
        goto L26
    L15:
        if (r4.containsKey(MediaMetadata.KEY_ARTIST) == true) goto L13;
        String r12 = MediaMetadata.KEY_ALBUM_ARTIST;
        if (r4.containsKey(MediaMetadata.KEY_ALBUM_ARTIST) == false) goto L20;
    L19:
        r0 = r12;
        goto L26
    L20:
        r12 = MediaMetadata.KEY_COMPOSER;
        if (r4.containsKey(MediaMetadata.KEY_COMPOSER) == true) goto L19;
    L26:
        return r4.getString(r0);
    }

    public static List zzb(com.google.android.gms.cast.framework.media.zzg r3) {
        return r3.zzf();
    L4:
        e = move-exception;
        zza.e(e, "Unable to call %s on %s.", new Object[]{"getNotificationActions", com.google.android.gms.cast.framework.media.zzg.class.getSimpleName()});
        return null;
    }

    public static int[] zzc(com.google.android.gms.cast.framework.media.zzg r3) {
        return r3.zzg();
    L4:
        e = move-exception;
        zza.e(e, "Unable to call %s on %s.", new Object[]{"getCompactViewActionIndices", com.google.android.gms.cast.framework.media.zzg.class.getSimpleName()});
        return null;
    }

    public static int zzd(NotificationOptions r4, long r5) {
        int r1 = r4.getForwardDrawableResId();
        if (r5 != NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L7;
        return r4.getForward10DrawableResId();
    L7:
        if (r5 == NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L10;
        return r1;
    L10:
        return r4.getForward30DrawableResId();
    }

    public static int zze(NotificationOptions r4, long r5) {
        int r1 = r4.zzf();
        if (r5 != NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L7;
        return r4.zzg();
    L7:
        if (r5 == NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L10;
        return r1;
    L10:
        return r4.zzh();
    }

    public static int zzf(NotificationOptions r4, long r5) {
        int r1 = r4.getRewindDrawableResId();
        if (r5 != NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L7;
        return r4.getRewind10DrawableResId();
    L7:
        if (r5 == NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L10;
        return r1;
    L10:
        return r4.getRewind30DrawableResId();
    }

    public static int zzg(NotificationOptions r4, long r5) {
        int r1 = r4.zzi();
        if (r5 != NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L7;
        return r4.zzj();
    L7:
        if (r5 == NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L10;
        return r1;
    L10:
        return r4.zzk();
    }
}
