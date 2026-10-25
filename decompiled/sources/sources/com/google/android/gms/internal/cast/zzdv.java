package com.google.android.gms.internal.cast;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdv extends UIController implements RemoteMediaClient.ProgressListener {
    private final TextView zza;
    private final ImageView zzb;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzc;

    public zzdv(View r5, com.google.android.gms.cast.framework.media.uicontroller.zza r6) {
        TextView r0 = (TextView) r5.findViewById(R.id.live_indicator_text);
        this.zza = r0;
        ImageView r52 = (ImageView) r5.findViewById(R.id.live_indicator_dot);
        this.zzb = r52;
        this.zzc = r6;
        TypedArray r4 = r52.getContext().obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int r62 = r4.getResourceId(R.styleable.CastExpandedController_castLiveIndicatorColor, 0);
        r4.recycle();
        int r42 = r52.getContext().getResources().getColor(r62);
        r52.getDrawable().setColorFilter(r42, PorterDuff.Mode.SRC_IN);
        r0.setVisibility(8);
        r52.setVisibility(8);
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
        int r1 = 8;
        if (r0 != null) goto L5;
    L19:
        this.zza.setVisibility(8);
        this.zzb.setVisibility(8);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L19;
        if (r0.isLiveStream() == false) goto L19;
        if (r0.zzj() == true) goto L12;
        boolean r02 = r0.isPlaying();
    L13:
        this.zza.setVisibility(0);
        ImageView r4 = this.zzb;
        if (true != r02) goto L17;
        r1 = 0;
    L17:
        r4.setVisibility(r1);
        zzr.zzb(zzpm.zzz);
        return;
    L12:
        r02 = this.zzc.zze();
        goto L13
    }
}
