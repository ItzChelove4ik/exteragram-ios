package androidx.media3.session.legacy;

import android.content.Context;
import android.media.session.MediaController;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.KeyEvent;
import androidx.media3.session.legacy.IMediaControllerCallback;
import androidx.media3.session.legacy.IMediaSession;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.versionedparcelable.ParcelUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public final class MediaControllerCompat {
    private final MediaControllerImpl impl;
    private final Set<Callback> registeredCallbacks;
    private final MediaSessionCompat.Token token;

    public static abstract class Callback implements IBinder.DeathRecipient {

        public static class StubCompat extends IMediaControllerCallback.Stub {
            private final WeakReference<Callback> callback;

            public StubCompat(Callback r2) {
                this.callback = new WeakReference(r2);
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onCaptioningEnabledChanged(boolean r1) {
                MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callback.get());
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onEvent(String r1, Bundle r2) {
                MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callback.get());
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat r1) {
                MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callback.get());
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onRepeatModeChanged(int r1) {
                MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callback.get());
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onSessionReady() {
                MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callback.get());
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onShuffleModeChanged(int r1) {
                MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callback.get());
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onShuffleModeChangedRemoved(boolean r1) {
            }
        }
    }

    public interface MediaControllerImpl {
        boolean dispatchMediaButtonEvent(KeyEvent r1);

        CharSequence getQueueTitle();
    }

    public static class MediaControllerImplApi21 implements MediaControllerImpl {
        private final HashMap<Callback, ExtraCallback> callbackMap;
        protected final MediaController controllerFwk;
        final Object lock;
        private final List<Callback> pendingCallbacks;
        final MediaSessionCompat.Token sessionToken;

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private final WeakReference<MediaControllerImplApi21> mediaControllerImpl;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 r2) {
                super(null);
                this.mediaControllerImpl = new WeakReference(r2);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int r3, Bundle r4) {
                MediaControllerImplApi21 r2 = this.mediaControllerImpl.get();
                if (r2 == null) goto L14;
                if (r4 == null) goto L17;
                Object r32 = r2.lock;
                monitor-enter(r32);
                r2.sessionToken.setExtraBinder(IMediaSession.Stub.asInterface(r4.getBinder("android.support.v4.media.session.EXTRA_BINDER")));     // Catch: Throwable -> L11
                r2.sessionToken.setSession2Token(ParcelUtils.getVersionedParcelable(r4, "android.support.v4.media.session.SESSION_TOKEN2"));     // Catch: Throwable -> L11
                r2.processPendingCallbacksLocked();     // Catch: Throwable -> L11
                monitor-exit(r32);     // Catch: Throwable -> L11
                return;
            L11:
                th = move-exception;
                throw th;
            L17:
                return;
            }
        }

        public static class ExtraCallback extends Callback.StubCompat {
            public ExtraCallback(Callback r1) {
                super(r1);
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onExtrasChanged(Bundle r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onMetadataChanged(MediaMetadataCompat r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onQueueChanged(List<MediaSessionCompat.QueueItem> r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onQueueTitleChanged(CharSequence r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onSessionDestroyed() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onVolumeInfoChanged(ParcelableVolumeInfo r1) {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context r3, MediaSessionCompat.Token r4) {
            this.lock = new Object();
            this.pendingCallbacks = new ArrayList();
            this.callbackMap = new HashMap();
            this.sessionToken = r4;
            this.controllerFwk = new MediaController(r3, r4.getToken());
            if (r4.getExtraBinder() != null) goto L6;
            requestExtraBinder();
            return;
        }

        private void requestExtraBinder() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public boolean dispatchMediaButtonEvent(KeyEvent r1) {
            return this.controllerFwk.dispatchMediaButtonEvent(r1);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public CharSequence getQueueTitle() {
            return this.controllerFwk.getQueueTitle();
        }

        public void processPendingCallbacksLocked() {
            if (this.sessionToken.getExtraBinder() != null) goto L5;
            return;
        L5:
            Iterator<Callback> r0 = this.pendingCallbacks.iterator();
            if (r0.hasNext() == true) goto L9;
            this.pendingCallbacks.clear();
            return;
        L9:
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next());
            ExtraCallback r02 = new ExtraCallback(null);
            this.callbackMap.put(null, r02);
            throw null;
        }

        public void sendCommand(String r1, Bundle r2, ResultReceiver r3) {
            this.controllerFwk.sendCommand(r1, r2, r3);
        }
    }

    public static class MediaControllerImplApi29 extends MediaControllerImplApi21 {
        public MediaControllerImplApi29(Context r1, MediaSessionCompat.Token r2) {
            super(r1, r2);
        }
    }

    public MediaControllerCompat(Context r3, MediaSessionCompat.Token r4) {
        this.registeredCallbacks = Collections.synchronizedSet(new HashSet());
        this.token = r4;
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.impl = new MediaControllerImplApi29(r3, r4);
        return;
    L6:
        this.impl = new MediaControllerImplApi21(r3, r4);
    }

    public MediaControllerCompat(Context r1, MediaSessionCompat r2) {
        this(r1, r2.getSessionToken());
    }

    public boolean dispatchMediaButtonEvent(KeyEvent r1) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("KeyEvent may not be null");
        return false;
    L4:
        return this.impl.dispatchMediaButtonEvent(r1);
    }

    public CharSequence getQueueTitle() {
        return this.impl.getQueueTitle();
    }
}
