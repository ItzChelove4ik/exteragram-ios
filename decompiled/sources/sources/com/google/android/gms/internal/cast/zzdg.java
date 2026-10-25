package com.google.android.gms.internal.cast;

import android.content.Context;
import android.widget.ImageView;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdg extends UIController {
    private final ImageView zza;
    private final String zzb;
    private final String zzc;
    private final Context zzd;
    private Cast.Listener zze;

    public zzdg(ImageView r2, Context r3) {
        this.zza = r2;
        Context r32 = r3.getApplicationContext();
        this.zzd = r32;
        this.zzb = r32.getString(R.string.cast_mute);
        this.zzc = r32.getString(R.string.cast_unmute);
        r2.setEnabled(false);
        this.zze = null;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.zza.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r2) {
        if (this.zze != null) goto L5;
        this.zze = new zzdf(this);
    L5:
        r2.addCastListener(this.zze);
        super.onSessionConnected(r2);
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setEnabled(false);
        CastSession r0 = CastContext.getSharedInstance(this.zzd).getSessionManager().getCurrentCastSession();
        if (r0 == null) goto L7;
        Cast.Listener r1 = this.zze;
        if (r1 == null) goto L7;
        r0.removeCastListener(r1);
    L7:
        super.onSessionEnded();
    }

    public final void zza() {
        CastSession r0 = CastContext.getSharedInstance(this.zzd).getSessionManager().getCurrentCastSession();
        if (r0 != null) goto L5;
    L19:
        this.zza.setEnabled(false);
        return;
    L5:
        if (r0.isConnected() == false) goto L19;
        RemoteMediaClient r2 = getRemoteMediaClient();
        if (r2 != null) goto L9;
    L12:
        this.zza.setEnabled(false);
    L13:
        boolean r02 = r0.isMute();
        ImageView r1 = this.zza;
        r1.setSelected(r02);
        if (r02 == false) goto L16;
        String r3 = this.zzc;
    L17:
        r1.setContentDescription(r3);
        return;
    L16:
        r3 = this.zzb;
        goto L17
    L9:
        if (r2.hasMediaSession() == false) goto L12;
        this.zza.setEnabled(true);
        goto L13
    }
}
