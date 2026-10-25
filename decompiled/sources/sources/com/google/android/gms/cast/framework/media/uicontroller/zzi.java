package com.google.android.gms.cast.framework.media.uicontroller;

import android.widget.SeekBar;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzi implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ SeekBar zza;
    final /* synthetic */ UIMediaController zzb;

    public zzi(UIMediaController r1, SeekBar r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar r5, int r6, boolean r7) {
        UIMediaController r0 = this.zzb;
        RemoteMediaClient r1 = r0.getRemoteMediaClient();
        if (r1 != null) goto L5;
    L20:
        r0.onSeekBarProgressChanged(r5, r6, r7);
        return;
    L5:
        if (r1.hasMediaSession() == false) goto L20;
        if (r1.zzj() == false) goto L20;
        if (r7 == false) goto L15;
        zza r2 = r0.zza;
        if (r6 >= r2.zzf()) goto L15;
        SeekBar r4 = this.zza;
        int r62 = r2.zzf();
        r4.setProgress(r62);
        r0.onSeekBarProgressChanged(r5, r62, true);
        return;
    L15:
        if (r7 == false) goto L20;
        zza r22 = r0.zza;
        if (r6 <= r22.zzg()) goto L20;
        SeekBar r42 = this.zza;
        int r63 = r22.zzg();
        r42.setProgress(r63);
        r0.onSeekBarProgressChanged(r5, r63, true);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar r1) {
        this.zzb.onSeekBarStartTrackingTouch(r1);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar r1) {
        this.zzb.onSeekBarStopTrackingTouch(r1);
    }
}
