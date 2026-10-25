package com.google.android.gms.internal.cast;

import androidx.mediarouter.media.MediaRouteProviderProtocol;

/* loaded from: classes4.dex */
public final class zzcq {
    private final String zza;
    private final long zzb;
    private final int zzc;
    private final long zzd;
    private final long zze;
    private long zzf;

    public zzcq(zzcp r3) {
        this.zza = r3.zze();
        this.zzb = r3.zzf();
        this.zzc = r3.zzg();
        this.zzd = r3.zzh();
        this.zze = r3.zzi();
    }

    public final void zza(long r1) {
        this.zzf = r1;
    }

    public final zzqt zzb() {
        String r0 = this.zza;
        zzqs r1 = zzqt.zza();
        switch(r0.hashCode()) {
            case -1189611734: goto L69;
            case -1109843021: goto L66;
            case -940430091: goto L63;
            case -936597225: goto L60;
            case -930425472: goto L57;
            case -921113364: goto L54;
            case -900560382: goto L51;
            case -892481550: goto L48;
            case -844665542: goto L45;
            case -810883302: goto L42;
            case -402284771: goto L39;
            case 3327206: goto L36;
            case 3363353: goto L33;
            case 3443508: goto L30;
            case 3526264: goto L27;
            case 3540994: goto L24;
            case 106440182: goto L21;
            case 525402049: goto L18;
            case 913357482: goto L15;
            case 1148867366: goto L12;
            case 1451542318: goto L9;
            case 1873161788: goto L6;
            default: goto L71;
        };
    L71:
        int r02 = 1;
    L72:
        r1.zze(r02);
        r1.zza((int) this.zzb);
        r1.zzb(this.zzc);
        r1.zzc((int) (this.zzd - this.zzf));
        r1.zzd((int) (this.zze - this.zzf));
        return (zzqt) r1.zzu();
    L6:
        if (r0.equals("queueFetchItemIds") == false) goto L71;
        r02 = 17;
        goto L72
    L9:
        if (r0.equals("activeTracks") == false) goto L71;
        r02 = 11;
        goto L72
    L12:
        if (r0.equals("trackStyle") == false) goto L71;
        r02 = 12;
        goto L72
    L15:
        if (r0.equals("queueReorder") == false) goto L71;
        r02 = 16;
        goto L72
    L18:
        if (r0.equals("queueFetchItemRange") == false) goto L71;
        r02 = 18;
        goto L72
    L21:
        if (r0.equals("pause") == false) goto L71;
        r02 = 4;
        goto L72
    L24:
        if (r0.equals("stop") == false) goto L71;
        r02 = 5;
        goto L72
    L27:
        if (r0.equals("seek") == false) goto L71;
        r02 = 6;
        goto L72
    L30:
        if (r0.equals("play") == false) goto L71;
        r02 = 3;
        goto L72
    L33:
        if (r0.equals("mute") == false) goto L71;
        r02 = 8;
        goto L72
    L36:
        if (r0.equals("load") == false) goto L71;
        r02 = 2;
        goto L72
    L39:
        if (r0.equals("setPlaybackRate") == false) goto L71;
        r02 = 20;
        goto L72
    L42:
        if (r0.equals(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME) == false) goto L71;
        r02 = 7;
        goto L72
    L45:
        if (r0.equals("queueUpdate") == false) goto L71;
        r02 = 14;
        goto L72
    L48:
        if (r0.equals("status") == false) goto L71;
        r02 = 10;
        goto L72
    L51:
        if (r0.equals("skipAd") == false) goto L71;
        r02 = 21;
        goto L72
    L54:
        if (r0.equals("volume-mute") == false) goto L71;
        r02 = 9;
        goto L72
    L57:
        if (r0.equals("setPlaybackDevices") == false) goto L71;
        r02 = 23;
        goto L72
    L60:
        if (r0.equals("queueFetchItems") == false) goto L71;
        r02 = 19;
        goto L72
    L63:
        if (r0.equals("queueRemove") == false) goto L71;
        r02 = 15;
        goto L72
    L66:
        if (r0.equals("launch") == false) goto L71;
        r02 = 22;
        goto L72
    L69:
        if (r0.equals("queueInsert") == false) goto L71;
        r02 = 13;
        goto L72
    }
}
