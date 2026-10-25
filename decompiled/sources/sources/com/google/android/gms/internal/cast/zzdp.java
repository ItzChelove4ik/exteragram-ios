package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdp extends UIController implements RemoteMediaClient.ProgressListener {
    private final TextView zza;
    private final String zzb;
    private final View zzc;

    public zzdp(TextView r1, String r2, View r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    private final void zza(long r5, boolean r7) {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L18:
        TextView r52 = this.zza;
        r52.setVisibility(0);
        r52.setText(this.zzb);
        View r4 = this.zzc;
        if (r4 == null) goto L24;
        r4.setVisibility(4);
        return;
    L24:
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L18;
        if (r0.isLiveStream() == true) goto L14;
        if (r7 == false) goto L10;
        r5 = r0.getStreamDuration();
    L10:
        TextView r72 = this.zza;
        r72.setVisibility(0);
        r72.setText(DateUtils.formatElapsedTime(r5 / 1000));
        View r42 = this.zzc;
        if (r42 == null) goto L22;
        r42.setVisibility(4);
        return;
    L22:
        return;
    L14:
        TextView r53 = this.zza;
        r53.setText(this.zzb);
        View r43 = this.zzc;
        if (r43 == null) goto L23;
        r53.setVisibility(4);
        r43.setVisibility(0);
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza(-1, true);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long r1, long r3) {
        zza(r3, false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r3) {
        super.onSessionConnected(r3);
        RemoteMediaClient r32 = getRemoteMediaClient();
        if (r32 == null) goto L5;
        r32.addProgressListener(this, 1000);
    L5:
        zza(-1, true);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setText(this.zzb);
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 == null) goto L5;
        r0.removeProgressListener(this);
    L5:
        super.onSessionEnded();
    }
}
