package com.google.android.gms.cast.framework.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.annotation.Keep;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;

@Keep
/* loaded from: classes4.dex */
public class MediaIntentReceiver extends BroadcastReceiver {
    public static final String ACTION_DISCONNECT = "com.google.android.gms.cast.framework.action.DISCONNECT";
    public static final String ACTION_FORWARD = "com.google.android.gms.cast.framework.action.FORWARD";
    public static final String ACTION_REWIND = "com.google.android.gms.cast.framework.action.REWIND";
    public static final String ACTION_SKIP_NEXT = "com.google.android.gms.cast.framework.action.SKIP_NEXT";
    public static final String ACTION_SKIP_PREV = "com.google.android.gms.cast.framework.action.SKIP_PREV";
    public static final String ACTION_STOP_CASTING = "com.google.android.gms.cast.framework.action.STOP_CASTING";
    public static final String ACTION_TOGGLE_PLAYBACK = "com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK";
    public static final String EXTRA_SKIP_STEP_MS = "googlecast-extra_skip_step_ms";
    private static final String TAG = "MediaIntentReceiver";
    private static final Logger log = null;

    static {
        log = new Logger(TAG);
    }

    public MediaIntentReceiver() {
    }

    private static RemoteMediaClient getRemoteMediaClient(CastSession r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.isConnected() == true) goto L7;
        return null;
    L7:
        return r1.getRemoteMediaClient();
    }

    private void seek(CastSession r3, long r4) {
        if (r4 == 0) goto L16;
        RemoteMediaClient r2 = getRemoteMediaClient(r3);
        if (r2 != null) goto L8;
        return;
    L8:
        if (r2.isLiveStream() == false) goto L10;
        return;
    L10:
        if (r2.isPlayingAd() == true) goto L15;
        r2.seek(r2.getApproximateStreamPosition() + r4);
        return;
    L15:
        return;
    }

    private void togglePlayback(CastSession r1) {
        RemoteMediaClient r0 = getRemoteMediaClient(r1);
        if (r0 != null) goto L5;
        return;
    L5:
        r0.togglePlayback();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r8, Intent r9) {
        String r0 = r9.getAction();
        log.d("onReceive action: %s", new Object[]{r0});
        if (r0 == null) goto L45;
        SessionManager r1 = CastContext.getSharedInstance(r8).getSessionManager();
        Session r2 = r1.getCurrentSession();
        if (r2 != null) goto L8;
        return;
    L8:
        switch(r0.hashCode()) {
            case -1699820260: goto L39;
            case -945151566: goto L35;
            case -945080078: goto L31;
            case -668151673: goto L27;
            case -124479363: goto L23;
            case 235550565: goto L19;
            case 1362116196: goto L15;
            case 1997055314: goto L11;
            default: goto L42;
        };
    L42:
        onReceiveOtherAction(r8, r0, r9);
        return;
    L11:
        if (r0.equals("android.intent.action.MEDIA_BUTTON") == false) goto L42;
        onReceiveActionMediaButton(r2, r9);
        return;
    L15:
        if (r0.equals(ACTION_FORWARD) == false) goto L42;
        onReceiveActionForward(r2, r9.getLongExtra(EXTRA_SKIP_STEP_MS, 0));
        return;
    L19:
        if (r0.equals(ACTION_TOGGLE_PLAYBACK) == false) goto L42;
        onReceiveActionTogglePlayback(r2);
        return;
    L23:
        if (r0.equals(ACTION_DISCONNECT) == false) goto L42;
        r1.endCurrentSession(false);
        return;
    L27:
        if (r0.equals(ACTION_STOP_CASTING) == false) goto L42;
        r1.endCurrentSession(true);
        return;
    L31:
        if (r0.equals(ACTION_SKIP_PREV) == false) goto L42;
        onReceiveActionSkipPrev(r2);
        return;
    L35:
        if (r0.equals(ACTION_SKIP_NEXT) == false) goto L42;
        onReceiveActionSkipNext(r2);
        return;
    L39:
        if (r0.equals(ACTION_REWIND) == false) goto L42;
        onReceiveActionRewind(r2, r9.getLongExtra(EXTRA_SKIP_STEP_MS, 0));
        return;
    }

    public void onReceiveActionForward(Session r2, long r3) {
        if ((r2 instanceof CastSession) == false) goto L6;
        seek((CastSession) r2, r3);
        return;
    }

    public void onReceiveActionMediaButton(Session r3, Intent r4) {
        if ((r3 instanceof CastSession) == true) goto L5;
        return;
    L5:
        if (r4.hasExtra("android.intent.extra.KEY_EVENT") == false) goto L19;
        KeyEvent r42 = (KeyEvent) ((Bundle) Preconditions.checkNotNull(r4.getExtras())).get("android.intent.extra.KEY_EVENT");
        if (r42 != null) goto L10;
        return;
    L10:
        if (r42.getAction() == 0) goto L12;
        return;
    L12:
        if (r42.getKeyCode() != 85) goto L18;
        togglePlayback((CastSession) r3);
        return;
    L18:
        return;
    }

    public void onReceiveActionRewind(Session r2, long r3) {
        if ((r2 instanceof CastSession) == false) goto L6;
        seek((CastSession) r2, -r3);
        return;
    }

    public void onReceiveActionSkipNext(Session r1) {
        if ((r1 instanceof CastSession) == false) goto L11;
        RemoteMediaClient r0 = getRemoteMediaClient((CastSession) r1);
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isPlayingAd() == true) goto L13;
        r0.queueNext(null);
        return;
    L13:
        return;
    }

    public void onReceiveActionSkipPrev(Session r1) {
        if ((r1 instanceof CastSession) == false) goto L11;
        RemoteMediaClient r0 = getRemoteMediaClient((CastSession) r1);
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isPlayingAd() == true) goto L13;
        r0.queuePrev(null);
        return;
    L13:
        return;
    }

    public void onReceiveActionTogglePlayback(Session r2) {
        if ((r2 instanceof CastSession) == false) goto L6;
        togglePlayback((CastSession) r2);
        return;
    }

    public void onReceiveOtherAction(Context r1, String r2, Intent r3) {
    }

    @Deprecated
    public void onReceiveOtherAction(String r2, Intent r3) {
        onReceiveOtherAction(null, r2, r3);
    }
}
