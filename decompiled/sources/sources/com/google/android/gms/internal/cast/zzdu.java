package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdu extends UIController implements RemoteMediaClient.ProgressListener {
    private final TextView zza;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzb;

    public zzdu(TextView r1, com.google.android.gms.cast.framework.media.uicontroller.zza r2) {
        this.zza = r1;
        this.zzb = r2;
        r1.setText(r1.getContext().getString(R.string.cast_invalid_stream_duration_text));
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long r1, long r3) {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r3) {
        super.onSessionConnected(r3);
        RemoteMediaClient r32 = getRemoteMediaClient();
        if (r32 == null) goto L5;
        r32.addProgressListener(this, 1000);
    L5:
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 == null) goto L5;
        r0.removeProgressListener(this);
    L5:
        super.onSessionEnded();
        zza();
    }

    public final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L15:
        TextView r5 = this.zza;
        r5.setText(r5.getContext().getString(R.string.cast_invalid_stream_duration_text));
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L15;
        if (r0.isLiveStream() == true) goto L10;
    L13:
        TextView r02 = this.zza;
        r02.setVisibility(0);
        com.google.android.gms.cast.framework.media.uicontroller.zza r52 = this.zzb;
        r02.setText(r52.zzm(r52.zzb() + r52.zzh()));
        return;
    L10:
        if (this.zzb.zzj() != null) goto L13;
        this.zza.setVisibility(8);
    }
}
