package org.telegram.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.Utilities;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public abstract class CastSync {
    private static boolean listened;
    public static AtomicInteger pending;
    private static int savedVolume;
    private static ContentObserver syncingVolume;
    public static int type;

    /* renamed from: org.telegram.ui.CastSync$1, reason: invalid class name */
    public class AnonymousClass1 implements SessionManagerListener<CastSession> {
        final /* synthetic */ int val$type;

        /* renamed from: org.telegram.ui.CastSync$1$1, reason: invalid class name and collision with other inner class name */
        public class C00381 extends RemoteMediaClient.Callback {
            final /* synthetic */ AnonymousClass1 this$0;

            public C00381(AnonymousClass1 r1) {
                this.this$0 = r1;
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onMediaError(MediaError r2) {
                FileLog.e("Chromecast Media Error: " + r2);
            }

            @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
            public void onStatusUpdated() {
                FileLog.d("onStatusUpdated");
                CastSync.syncInterface();
            }
        }

        public AnonymousClass1(int r1) {
            this.val$type = r1;
        }

        public void onSessionEnded(CastSession r1, int r2) {
            CastSync.doSyncVolume(false);
            CastSync.syncInterface();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionEnded(Session r1, int r2) {
            onSessionEnded((CastSession) r1, r2);
        }

        public void onSessionEnding(CastSession r1) {
            CastSync.doSyncVolume(false);
            CastSync.syncInterface();
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionEnding(Session r1) {
            onSessionEnding((CastSession) r1);
        }

        public void onSessionResumeFailed(CastSession r1, int r2) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionResumeFailed(Session r1, int r2) {
            onSessionResumeFailed((CastSession) r1, r2);
        }

        public void onSessionResumed(CastSession r1, boolean r2) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionResumed(Session r1, boolean r2) {
            onSessionResumed((CastSession) r1, r2);
        }

        public void onSessionResuming(CastSession r1, String r2) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionResuming(Session r1, String r2) {
            onSessionResuming((CastSession) r1, r2);
        }

        public void onSessionStartFailed(CastSession r1, int r2) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionStartFailed(Session r1, int r2) {
            onSessionStartFailed((CastSession) r1, r2);
        }

        public void onSessionStarted(CastSession r5, String r6) {
            if (r5 == null) goto L21;
            RemoteMediaClient r52 = r5.getRemoteMediaClient();
            if (r52 != null) goto L7;
            return;
        L7:
            AtomicInteger r62 = CastSync.pending;
            if (r62 == null) goto L10;
            r62.set(0);
        L10:
            r52.registerCallback(new C00381(this));
            r52.queueSetRepeatMode(2, null);
            int r4 = this.val$type;
            if (r4 != 0) goto L13;
            long r0 = PhotoViewer.getInstance().getCurrentPosition();
        L17:
            if (r0 < 0) goto L19;
            CastSync.seekTo(r0);
        L19:
            CastSync.doSyncVolume(true);
            return;
        L13:
            if (r4 != 1) goto L15;
            r0 = MediaController.getInstance().getCurrentPosition();
            goto L17
        L15:
            r0 = -1;
            goto L17
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionStarted(Session r1, String r2) {
            onSessionStarted((CastSession) r1, r2);
        }

        public void onSessionStarting(CastSession r1) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionStarting(Session r1) {
            onSessionStarting((CastSession) r1);
        }

        public void onSessionSuspended(CastSession r1, int r2) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public /* bridge */ /* synthetic */ void onSessionSuspended(Session r1, int r2) {
            onSessionSuspended((CastSession) r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.CastSync$2, reason: invalid class name */
    public class AnonymousClass2 extends ContentObserver {
        public AnonymousClass2(Handler r1) {
            super(r1);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean r1) {
            CastSync.setVolume(CastSync.getDeviceVolume());
        }
    }

    /* renamed from: $r8$lambda$5nOGJw-BwEMrusgmT3ZYYs51e-s, reason: not valid java name */
    public static /* synthetic */ void m6594$r8$lambda$5nOGJwBwEMrusgmT3ZYYs51es(Status r0) {
        pending.decrementAndGet();
    }

    public static /* synthetic */ void $r8$lambda$U_toVnQ1AFn2f5IeqI5A6b9qx6A(Status r0) {
        pending.decrementAndGet();
    }

    public static /* synthetic */ void $r8$lambda$lShuTNJ_vkDn5R0dt4xl_BHryvs(Status r0) {
        pending.decrementAndGet();
    }

    /* renamed from: $r8$lambda$p9-hMEAUNcGw3JvNMZE4172Xymo, reason: not valid java name */
    public static /* synthetic */ void m6595$r8$lambda$p9hMEAUNcGw3JvNMZE4172Xymo(Status r0) {
        pending.decrementAndGet();
    }

    public static /* synthetic */ void $r8$lambda$xjZZj9vOc2udwx0SRi0QNRjRdpc(Status r0) {
        pending.decrementAndGet();
    }

    public static void check(int r2) {
        type = r2;
        if (listened == true) goto L19;
    L13:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        if (getContext() == null) goto L10;
        CastContext r0 = CastContext.getSharedInstance(getContext());     // Catch: Exception -> L13
        if (r0 == null) goto L18;
        r0.getSessionManager().addSessionManagerListener(new AnonymousClass1(r2), CastSession.class);     // Catch: Exception -> L13
        listened = true;     // Catch: Exception -> L13
        return;
    L18:
        return;
    L10:
        return;
    }

    public static void doSyncVolume(boolean r7) {
        ContentObserver r0 = syncingVolume;
        if (r0 == null) goto L5;
        boolean r3 = true;
    L6:
        if (r3 != r7) goto L8;
        return;
    L8:
        if (r7 == false) goto L17;
        Context r72 = getContext();
        if (r72 == null) goto L28;
        AudioManager r02 = (AudioManager) r72.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r02 == null) goto L29;
        savedVolume = r02.getStreamVolume(3);
        ContentResolver r73 = r72.getContentResolver();
        Uri r32 = Settings.System.CONTENT_URI;
        AnonymousClass2 r5 = new AnonymousClass2(new Handler());
        syncingVolume = r5;
        r73.registerContentObserver(r32, true, r5);
        setVolume(getDeviceVolume());
        r02.adjustStreamVolume(3, 0, 1);
        return;
    L29:
        return;
    L28:
        return;
    L17:
        if (r0 == null) goto L27;
        Context r74 = getContext();
        if (r74 == null) goto L30;
        r74.getContentResolver().unregisterContentObserver(syncingVolume);
        syncingVolume = null;
        AudioManager r75 = (AudioManager) r74.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r75 == null) goto L31;
        r75.setStreamVolume(3, savedVolume, 0);
        syncInterface();
        return;
    L31:
        return;
    L30:
        return;
    L27:
        return;
    L5:
        r3 = false;
        goto L6
    }

    public static RemoteMediaClient getClient() {
        if (getContext() != null) goto L17;
        return null;
    L17:
        CastContext r0 = CastContext.getSharedInstance(getContext());     // Catch: Exception -> L14
        if (r0 != null) goto L8;
        return null;
    L8:
        CastSession r02 = r0.getSessionManager().getCurrentCastSession();     // Catch: Exception -> L14
        if (r02 != null) goto L11;
    L16:
        return null;
    L11:
        if (r02.isConnected() == false) goto L16;
        return r02.getRemoteMediaClient();
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    public static Context getContext() {
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return ApplicationLoader.applicationContext;
    }

    public static float getDeviceVolume() {
        Context r0 = getContext();
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        AudioManager r02 = (AudioManager) r0.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r02 != null) goto L8;
        return 0.0f;
    L8:
        int r2 = r02.getStreamVolume(3);
        int r3 = r02.getStreamMaxVolume(3);
        if (Build.VERSION.SDK_INT < 28) goto L11;
        int r03 = r02.getStreamMinVolume(3);
    L13:
        return Utilities.clamp01((r2 - r03) / (r3 - r03));
    L11:
        r03 = 0;
        goto L13
    }

    public static long getPosition() {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.getApproximateStreamPosition();
    }

    public static float getSpeed() {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L5;
        return 1.0f;
    L5:
        MediaStatus r02 = r0.getMediaStatus();
        if (r02 != null) goto L9;
        return 1.0f;
    L9:
        return (float) r02.getPlaybackRate();
    }

    public static float getVolume() {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L5;
        return 0.5f;
    L5:
        MediaStatus r02 = r0.getMediaStatus();
        if (r02 != null) goto L9;
        return 0.5f;
    L9:
        return (float) r02.getStreamVolume();
    }

    public static boolean isActive() {
        if (getContext() != null) goto L22;
        return false;
    L22:
        CastContext r0 = CastContext.getSharedInstance(getContext());     // Catch: Exception -> L15
        if (r0 != null) goto L8;
        return false;
    L8:
        CastSession r02 = r0.getSessionManager().getCurrentCastSession();     // Catch: Exception -> L15
        if (r02 != null) goto L11;
    L19:
        return false;
    L11:
        if (r02.isConnecting() == true) goto L17;
        if (r02.isConnected() == false) goto L19;
        return true;
    L17:
        return true;
    L15:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean isPlaying() {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L7;
        return false;
    L7:
        if (type != 0) goto L11;
        return !r0.isPaused();
    L11:
        return r0.isPlaying();
    }

    public static boolean isUpdatePending() {
        AtomicInteger r0 = pending;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.get() <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static void seekTo(long r3) {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L6;
        return;
    L6:
        if (pending != null) goto L8;
        pending = new AtomicInteger(0);
    L8:
        pending.incrementAndGet();
        r0.seek(new MediaSeekOptions.Builder().setPosition(r3).build()).addStatusListener(new CastSync$$ExternalSyntheticLambda3());
    }

    public static void setPlaying(boolean r3) {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L6;
        return;
    L6:
        if (r3 != r0.isPlaying()) goto L8;
        return;
    L8:
        if (pending != null) goto L10;
        pending = new AtomicInteger(0);
    L10:
        pending.incrementAndGet();
        if (r3 == false) goto L14;
        r0.play().addStatusListener(new CastSync$$ExternalSyntheticLambda0());
        return;
    L14:
        r0.pause().addStatusListener(new CastSync$$ExternalSyntheticLambda1());
    }

    public static void setSpeed(float r3) {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L6;
        return;
    L6:
        if (pending != null) goto L8;
        pending = new AtomicInteger(0);
    L8:
        pending.incrementAndGet();
        r0.setPlaybackRate(r3).addStatusListener(new CastSync$$ExternalSyntheticLambda4());
    }

    public static void setVolume(float r3) {
        RemoteMediaClient r0 = getClient();
        if (r0 != null) goto L6;
        return;
    L6:
        if (pending != null) goto L8;
        pending = new AtomicInteger(0);
    L8:
        pending.incrementAndGet();
        r0.setStreamVolume(r3).addStatusListener(new CastSync$$ExternalSyntheticLambda2());
    }

    public static void stop() {
        if (getContext() == null) goto L15;
        CastContext r0 = CastContext.getSharedInstance(getContext());     // Catch: Exception -> L10
        if (r0 != null) goto L8;
        return;
    L8:
        r0.getSessionManager().endCurrentSession(true);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public static void syncInterface() {
        int r0 = type;
        if (r0 != 0) goto L7;
        PhotoViewer.getInstance().syncCastedPlayer();
        return;
    L7:
        if (r0 != 1) goto L10;
        MediaController.getInstance().syncCastedPlayer();
        return;
    }

    public static void syncPosition(long r4) {
        if (r4 < 0) goto L13;
        long r0 = getPosition();
        if (r0 != (-1)) goto L8;
    L11:
        seekTo(r4);
        return;
    L8:
        if (Math.abs(r0 - r4) > 1500) goto L11;
        return;
    }
}
