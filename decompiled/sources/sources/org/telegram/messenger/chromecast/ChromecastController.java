package org.telegram.messenger.chromecast;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.io.File;
import java.util.Objects;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.ui.CastSync;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes5.dex */
public class ChromecastController implements SessionManagerListener<CastSession> {
    private static volatile ChromecastController Instance;
    private final SessionManager sessionManager;
    private final ChromecastControllerState state;

    public static class RemoteMediaClientHandler extends RemoteMediaClient.Callback {
        private int attempt;
        public final RemoteMediaClient client;
        private int index;
        private int lastIdleReason;
        private int lastMediaErrorCode;
        public final SessionManager manager;
        private ChromecastMediaVariations media;
        public final CastSession session;

        public RemoteMediaClientHandler(CastSession r1, SessionManager r2, RemoteMediaClient r3) {
            this.session = r1;
            this.manager = r2;
            this.client = r3;
        }

        private void loadImpl() {
            this.lastMediaErrorCode = -1;
            if (this.media != null) goto L6;
            this.media = null;
            return;
        L6:
            String r0 = ChromecastFileServer.getHost();
            if (this.index >= this.media.getVariationsCount()) goto L9;
            ChromecastMedia r1 = this.media.getVariation(this.index);
        L10:
            this.client.load(r1.buildMediaInfo(r0, "?index=" + this.index + "&attempt=" + this.attempt), new MediaLoadOptions.Builder().setAutoplay(true).build());
            return;
        L9:
            r1 = ChromecastFileServer.ASSET_FALLBACK_FILE;
            goto L10
        }

        private void loadNext(boolean r3) {
            if (r3 == false) goto L4;
            this.index++;
        L7:
            Log.e("CAST_CLIENT", "next attempt " + this.lastMediaErrorCode + " " + this.index + " " + this.attempt);
            loadImpl();
            return;
        L4:
            int r32 = this.attempt + 1;
            this.attempt = r32;
            if (r32 <= 3) goto L7;
            this.attempt = 0;
            this.index++;
            goto L7
        }

        public void close() {
            this.manager.endCurrentSession(true);
        }

        public void load(ChromecastMediaVariations r1) {
            this.media = r1;
            this.index = 0;
            this.attempt = 0;
            loadImpl();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onAdBreakStatusUpdated() {
            Log.d("CAST_CLIENT", "onAdBreakStatusUpdated " + this.session.getSessionId());
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onMediaError(MediaError r4) {
            Log.d("CAST_CLIENT", "onMediaError " + this.session.getSessionId() + " " + r4.getDetailedErrorCode() + " " + r4.getRequestId());
            Integer r42 = r4.getDetailedErrorCode();
            if (r42 == null) goto L5;
            int r43 = r42.intValue();
        L6:
            this.lastMediaErrorCode = r43;
            return;
        L5:
            r43 = -1;
            goto L6
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onMetadataUpdated() {
            Log.d("CAST_CLIENT", "onMetadataUpdated " + this.session.getSessionId());
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onPreloadStatusUpdated() {
            Log.d("CAST_CLIENT", "onPreloadStatusUpdated " + this.session.getSessionId());
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onQueueStatusUpdated() {
            Log.d("CAST_CLIENT", "onQueueStatusUpdated " + this.session.getSessionId());
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onSendingRemoteMediaRequest() {
            Log.d("CAST_CLIENT", "onSendingRemoteMediaRequest " + this.session.getSessionId());
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public void onStatusUpdated() {
            Log.d("CAST_CLIENT", "onStatusUpdated " + this.session.getSessionId());
            int r0 = this.client.getIdleReason();
            if (r0 == this.lastIdleReason) goto L18;
            Log.d("CAST_CLIENT", "idleReason " + r0);
            this.lastIdleReason = r0;
            if (r0 != 2) goto L9;
            close();
            return;
        L9:
            if (r0 != 4) goto L19;
            int r02 = this.lastMediaErrorCode;
            if (r02 != 104) goto L15;
            loadNext(true);
            return;
        L15:
            if (r02 != 102) goto L20;
            loadNext(false);
            return;
        L20:
            return;
        L19:
            return;
        }

        public void register() {
            this.client.registerCallback(this);
        }

        public void unregister() {
            this.client.unregisterCallback(this);
        }
    }

    /* renamed from: $r8$lambda$ooSm-1bHzxo-MbysiHcRg9cE-YA, reason: not valid java name */
    public static /* synthetic */ void m5142$r8$lambda$ooSm1bHzxoMbysiHcRg9cEYA(int r2) {
        Log.d("CAST_STATE", "onCastStateChanged " + r2);
    }

    static {
    }

    private ChromecastController() {
        CastContext r0 = CastContext.getSharedInstance(ApplicationLoader.applicationContext);
        r0.addCastStateListener(new ChromecastController$$ExternalSyntheticLambda0());
        this.state = new ChromecastControllerState();
        SessionManager r02 = r0.getSessionManager();
        this.sessionManager = r02;
        r02.addSessionManagerListener(this, CastSession.class);
        tryInitClient(r02.getCurrentCastSession());
    }

    public static boolean eq(ChromecastMedia r4, ChromecastMedia r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 == null) goto L23;
        if (r5 == null) goto L23;
        if (Objects.equals(r4.mimeType, r5.mimeType) == false) goto L23;
        if (Objects.equals(r4.mediaMetadata, r5.mediaMetadata) == false) goto L23;
        if (Objects.equals(r4.internalUri, r5.internalUri) == false) goto L23;
        if (Objects.equals(r4.externalPath, r5.externalPath) == false) goto L23;
        if (r4.width != r5.width) goto L23;
        if (r4.height != r5.height) goto L23;
        return true;
    L23:
        return false;
    }

    public static boolean eq(ChromecastMediaVariations r5, ChromecastMediaVariations r6) {
        if (r5 != null) goto L7;
        if (r6 != null) goto L7;
        return true;
    L7:
        if (r5 == null) goto L21;
        if (r6 == null) goto L21;
        if (r5.getVariationsCount() == r6.getVariationsCount()) goto L13;
        return false;
    L13:
        int r2 = 0;
    L15:
        if (r2 >= r5.getVariationsCount()) goto L20;
        if (eq(r5.getVariation(r2), r6.getVariation(r2)) == false) goto L18;
        r2 = r2 + 1;
        goto L15
    L18:
        return false;
    L20:
        return true;
    L21:
        return false;
    }

    public static ChromecastController getInstance() {
        ChromecastController r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(ChromecastController.class);
        ChromecastController r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new ChromecastController();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(ChromecastController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private void tryInitClient(CastSession r4) {
        if (r4 == null) goto L20;
        RemoteMediaClient r0 = r4.getRemoteMediaClient();
        String r1 = r4.getSessionId();
        if (TextUtils.isEmpty(r1) == true) goto L19;
        if (r0 == null) goto L21;
        RemoteMediaClientHandler r2 = this.state.getClient();
        if (r2 != null) goto L11;
    L13:
        this.state.setClient(new RemoteMediaClientHandler(r4, this.sessionManager, r0));
        CastDevice r3 = r4.getCastDevice();
        if (r3 == null) goto L16;
        String r32 = r3.getFriendlyName();
    L17:
        PhotoViewer.getInstance().showChromecastBulletin(ChromecastFileServer.getHost(), r32);
        return;
    L16:
        r32 = null;
        goto L17
    L11:
        if (TextUtils.equals(r2.session.getSessionId(), r1) == false) goto L13;
        return;
    L21:
        return;
    L19:
        return;
    }

    public boolean isCasting() {
        if (this.state.getClient() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void onSessionEnded(CastSession r3, int r4) {
        Log.d("CAST_SESSION", "onSessionEnded " + r3.getSessionId() + " " + r4);
        this.state.setClient(null);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionEnded(Session r1, int r2) {
        onSessionEnded((CastSession) r1, r2);
    }

    public void onSessionEnding(CastSession r2) {
        Log.d("CAST_SESSION", "onSessionEnding " + r2.getSessionId());
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionEnding(Session r1) {
        onSessionEnding((CastSession) r1);
    }

    public void onSessionResumeFailed(CastSession r2, int r3) {
        Log.d("CAST_SESSION", "onSessionResumeFailed " + r2.getSessionId() + " " + r3);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionResumeFailed(Session r1, int r2) {
        onSessionResumeFailed((CastSession) r1, r2);
    }

    public void onSessionResumed(CastSession r2, boolean r3) {
        Log.d("CAST_SESSION", "onSessionResumed " + r2.getSessionId() + " " + r3);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionResumed(Session r1, boolean r2) {
        onSessionResumed((CastSession) r1, r2);
    }

    public void onSessionResuming(CastSession r2, String r3) {
        Log.d("CAST_SESSION", "onSessionResuming " + r2.getSessionId() + " " + r3);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionResuming(Session r1, String r2) {
        onSessionResuming((CastSession) r1, r2);
    }

    public void onSessionStartFailed(CastSession r2, int r3) {
        Log.d("CAST_SESSION", "onSessionStartFailed " + r2.getSessionId() + " " + r3);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionStartFailed(Session r1, int r2) {
        onSessionStartFailed((CastSession) r1, r2);
    }

    public void onSessionStarted(CastSession r3, String r4) {
        Log.d("CAST_SESSION", "onSessionStarted " + r3.getSessionId() + " " + r4);
        tryInitClient(r3);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionStarted(Session r1, String r2) {
        onSessionStarted((CastSession) r1, r2);
    }

    public void onSessionStarting(CastSession r3) {
        Log.d("CAST_SESSION", "onSessionStarting " + r3.getSessionId());
        tryInitClient(r3);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionStarting(Session r1) {
        onSessionStarting((CastSession) r1);
    }

    public void onSessionSuspended(CastSession r2, int r3) {
        Log.d("CAST_SESSION", "onSessionStartSuspended " + r2.getSessionId() + " " + r3);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public /* bridge */ /* synthetic */ void onSessionSuspended(Session r1, int r2) {
        onSessionSuspended((CastSession) r1, r2);
    }

    public String setCover(File r1) {
        return this.state.setCoverFile(r1);
    }

    public void setCurrentMediaAndCastIfNeeded(ChromecastMediaVariations r3) {
        Log.d("CAST_CONTROLLER", "set current media");
        ChromecastMediaVariations r0 = this.state.getMedia();
        if (CastSync.isActive() == true) goto L5;
    L7:
        this.state.setMedia(r3);
        return;
    L5:
        if (eq(r0, r3) == false) goto L7;
    }
}
