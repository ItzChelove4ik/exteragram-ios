package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdh extends UIController {
    private final ImageView zza;
    private final View zzb;
    private final boolean zzc;
    private final Drawable zzd;
    private final String zze;
    private final Drawable zzf;
    private final String zzg;
    private final Drawable zzh;
    private final String zzi;
    private boolean zzj;

    public zzdh(ImageView r2, Context r3, Drawable r4, Drawable r5, Drawable r6, View r7, boolean r8) {
        this.zzj = false;
        this.zza = r2;
        this.zzd = r4;
        this.zzf = r5;
        if (r6 == null) goto L6;
        r5 = r6;
    L6:
        this.zzh = r5;
        this.zze = r3.getString(R.string.cast_play);
        this.zzg = r3.getString(R.string.cast_pause);
        this.zzi = r3.getString(R.string.cast_stop);
        this.zzb = r7;
        this.zzc = r8;
        r2.setEnabled(false);
    }

    private final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L27:
        this.zza.setEnabled(false);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L27;
        if (r0.isPlaying() == false) goto L16;
        if (r0.isLiveStream() == false) goto L13;
        zzb(this.zzh, this.zzi);
        return;
    L13:
        zzb(this.zzf, this.zzg);
        return;
    L16:
        if (r0.isBuffering() == false) goto L20;
        zzc(false);
        return;
    L20:
        if (r0.isPaused() == false) goto L24;
        zzb(this.zzd, this.zze);
        return;
    L24:
        if (r0.isLoadingNextItem() == false) goto L29;
        zzc(true);
        return;
    }

    private final void zzb(Drawable r3, String r4) {
        ImageView r0 = this.zza;
        boolean r1 = r3.equals(r0.getDrawable());
        r0.setImageDrawable(r3);
        r0.setContentDescription(r4);
        r0.setVisibility(0);
        r0.setEnabled(true);
        View r32 = this.zzb;
        if (r32 == null) goto L5;
        r32.setVisibility(8);
    L5:
        if (r1 == false) goto L7;
        return;
    L7:
        if (this.zzj == false) goto L11;
        r0.sendAccessibilityEvent(8);
        return;
    }

    private final void zzc(boolean r5) {
        ImageView r0 = this.zza;
        this.zzj = r0.isAccessibilityFocused();
        View r1 = this.zzb;
        int r2 = 0;
        if (r1 == null) goto L8;
        r1.setVisibility(0);
        if (this.zzj == false) goto L8;
        r1.sendAccessibilityEvent(8);
    L8:
        if (true != this.zzc) goto L11;
        r2 = 4;
    L11:
        r0.setVisibility(r2);
        r0.setEnabled(!r5);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        zzc(true);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r1) {
        super.onSessionConnected(r1);
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setEnabled(false);
        super.onSessionEnded();
    }
}
