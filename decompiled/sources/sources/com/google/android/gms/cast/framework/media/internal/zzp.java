package com.google.android.gms.cast.framework.media.internal;

import android.app.BroadcastOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzp extends MediaSessionCompat.Callback {
    final /* synthetic */ zzs zza;

    public zzp(zzs r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    private final void zza(long r4) {
        RemoteMediaClient r0 = this.zza.zzl();
        if (r0 != null) goto L5;
        return;
    L5:
        long r42 = Math.max(0, r0.getApproximateStreamPosition() + r4);
        zzb(Math.min(r0.getStreamDuration(), r42));
    }

    private final void zzb(long r2) {
        RemoteMediaClient r1 = this.zza.zzl();
        if (r1 != null) goto L5;
        return;
    L5:
        MediaSeekOptions.Builder r0 = new MediaSeekOptions.Builder();
        r0.setPosition(r2);
        r1.seek(r0.build());
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onCustomAction(String r3, Bundle r4) {
        Logger r0 = zzs.zzg();
        r0.d("onCustomAction with action = %s", new Object[]{r3});
        switch(r3.hashCode()) {
            case -1699820260: goto L22;
            case -668151673: goto L16;
            case -124479363: goto L10;
            case 1362116196: goto L6;
            default: goto L25;
        };
    L25:
        Intent r42 = new Intent(r3);
        zzs r2 = this.zza;
        r42.setComponent(r2.zzk());
        int r32 = Build.VERSION.SDK_INT;
        Context r22 = r2.zzh();
        if (r32 >= 34) goto L29;
        r22.sendBroadcast(r42);
        return;
    L29:
        r22.sendBroadcast(r42, null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
        return;
    L6:
        if (r3.equals(MediaIntentReceiver.ACTION_FORWARD) == false) goto L25;
        zza(this.zza.zzj().getSkipStepMs());
        return;
    L10:
        if (r3.equals(MediaIntentReceiver.ACTION_DISCONNECT) == false) goto L25;
        zzs r23 = this.zza;
        if (r23.zzi() == null) goto L31;
        r23.zzi().endCurrentSession(false);
        return;
    L31:
        return;
    L16:
        if (r3.equals(MediaIntentReceiver.ACTION_STOP_CASTING) == false) goto L25;
        zzs r24 = this.zza;
        if (r24.zzi() == null) goto L32;
        r24.zzi().endCurrentSession(true);
        return;
    L32:
        return;
    L22:
        if (r3.equals(MediaIntentReceiver.ACTION_REWIND) == false) goto L25;
        zza(-this.zza.zzj().getSkipStepMs());
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final boolean onMediaButtonEvent(Intent r4) {
        zzs.zzg().d("onMediaButtonEvent", new Object[0]);
        KeyEvent r42 = (KeyEvent) r4.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (r42 != null) goto L5;
        return true;
    L5:
        if (r42.getKeyCode() != 127) goto L7;
    L8:
        zzs r3 = this.zza;
        if (r3.zzl() == null) goto L15;
        r3.zzl().togglePlayback();
        return true;
    L15:
        return true;
    L7:
        if (r42.getKeyCode() == 126) goto L8;
        return true;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPause() {
        zzs.zzg().d("onPause", new Object[0]);
        zzs r3 = this.zza;
        if (r3.zzl() == null) goto L6;
        r3.zzl().togglePlayback();
        return;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPlay() {
        zzs.zzg().d("onPlay", new Object[0]);
        zzs r3 = this.zza;
        if (r3.zzl() == null) goto L6;
        r3.zzl().togglePlayback();
        return;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onSeekTo(long r4) {
        int r0 = zzs.zza;
        Object[] r02 = {Long.valueOf(r4)};
        zzs.zzg().d("onSeekTo %d", r02);
        zzb(r4);
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onSkipToNext() {
        zzs.zzg().d("onSkipToNext", new Object[0]);
        zzs r3 = this.zza;
        if (r3.zzl() == null) goto L6;
        r3.zzl().queueNext(null);
        return;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onSkipToPrevious() {
        zzs.zzg().d("onSkipToPrevious", new Object[0]);
        zzs r3 = this.zza;
        if (r3.zzl() == null) goto L6;
        r3.zzl().queuePrev(null);
        return;
    }
}
