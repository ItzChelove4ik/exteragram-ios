package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* loaded from: classes4.dex */
public final class zzdq extends zzdr implements RemoteMediaClient.ProgressListener {
    private final TextView zza;
    private final long zzb;
    private final String zzc;
    private boolean zzd;

    public zzdq(TextView r2, long r3, String r5) {
        this.zzd = true;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r5;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long r3, long r5) {
        if (this.zzd == false) goto L9;
        TextView r2 = this.zza;
        if (r3 != (-1000)) goto L7;
        r3 = r5;
    L7:
        r2.setText(DateUtils.formatElapsedTime(r3 / 1000));
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r5) {
        super.onSessionConnected(r5);
        RemoteMediaClient r52 = getRemoteMediaClient();
        if (r52 == null) goto L10;
        r52.addProgressListener(this, this.zzb);
        boolean r0 = r52.hasMediaSession();
        TextView r1 = this.zza;
        if (r0 == false) goto L8;
        r1.setText(DateUtils.formatElapsedTime(r52.getApproximateStreamPosition() / 1000));
        return;
    L8:
        r1.setText(this.zzc);
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setText(this.zzc);
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 == null) goto L5;
        r0.removeProgressListener(this);
    L5:
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zza(long r3) {
        this.zza.setText(DateUtils.formatElapsedTime(r3 / 1000));
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzb(boolean r1) {
        this.zzd = r1;
    }
}
