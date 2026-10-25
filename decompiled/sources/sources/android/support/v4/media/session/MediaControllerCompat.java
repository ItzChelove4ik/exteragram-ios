package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import androidx.core.app.BundleCompat;
import androidx.media.AudioAttributesCompat;
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

/* loaded from: classes3.dex */
public final class MediaControllerCompat {
    private final MediaControllerImpl mImpl;
    private final Set<Callback> mRegisteredCallbacks;
    private final MediaSessionCompat.Token mToken;

    public static abstract class Callback implements IBinder.DeathRecipient {
        final MediaController.Callback mCallbackFwk;
        MessageHandler mHandler;
        IMediaControllerCallback mIControllerCallback;

        public static class MediaControllerCallbackApi21 extends MediaController.Callback {
            private final WeakReference<Callback> mCallback;

            public MediaControllerCallbackApi21(Callback r2) {
                this.mCallback = new WeakReference(r2);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo r7) {
                Callback r6 = this.mCallback.get();
                if (r6 == null) goto L6;
                r6.onAudioInfoChanged(new PlaybackInfo(r7.getPlaybackType(), AudioAttributesCompat.wrap(r7.getAudioAttributes()), r7.getVolumeControl(), r7.getMaxVolume(), r7.getCurrentVolume()));
                return;
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle r1) {
                MediaSessionCompat.ensureClassLoader(r1);
                Callback r0 = this.mCallback.get();
                if (r0 == null) goto L6;
                r0.onExtrasChanged(r1);
                return;
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata r1) {
                Callback r0 = this.mCallback.get();
                if (r0 == null) goto L6;
                r0.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(r1));
                return;
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState r2) {
                Callback r1 = this.mCallback.get();
                if (r1 != null) goto L5;
                return;
            L5:
                if (r1.mIControllerCallback != null) goto L10;
                r1.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(r2));
                return;
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List<MediaSession.QueueItem> r1) {
                Callback r0 = this.mCallback.get();
                if (r0 == null) goto L6;
                r0.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(r1));
                return;
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence r1) {
                Callback r0 = this.mCallback.get();
                if (r0 == null) goto L6;
                r0.onQueueTitleChanged(r1);
                return;
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                Callback r0 = this.mCallback.get();
                if (r0 == null) goto L6;
                r0.onSessionDestroyed();
                return;
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String r1, Bundle r2) {
                MediaSessionCompat.ensureClassLoader(r2);
                Callback r0 = this.mCallback.get();
                if (r0 == null) goto L6;
                r0.onSessionEvent(r1, r2);
                return;
            }
        }

        public class MessageHandler extends Handler {
            boolean mRegistered;
            final /* synthetic */ Callback this$0;

            public MessageHandler(Callback r1, Looper r2) {
                this.this$0 = r1;
                super(r2);
                this.mRegistered = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message r2) {
                if (this.mRegistered == true) goto L6;
                return;
            L6:
                switch(r2.what) {
                    case 1: goto L30;
                    case 2: goto L28;
                    case 3: goto L26;
                    case 4: goto L24;
                    case 5: goto L22;
                    case 6: goto L20;
                    case 7: goto L18;
                    case 8: goto L16;
                    case 9: goto L14;
                    case 10: goto L7;
                    case 11: goto L12;
                    case 12: goto L10;
                    case 13: goto L8;
                    default: goto L7;
                };
            L7:
                return;
            L8:
                this.this$0.onSessionReady();
                return;
            L10:
                this.this$0.onShuffleModeChanged(((Integer) r2.obj).intValue());
                return;
            L12:
                this.this$0.onCaptioningEnabledChanged(((Boolean) r2.obj).booleanValue());
                return;
            L14:
                this.this$0.onRepeatModeChanged(((Integer) r2.obj).intValue());
                return;
            L16:
                this.this$0.onSessionDestroyed();
                return;
            L18:
                Bundle r22 = (Bundle) r2.obj;
                MediaSessionCompat.ensureClassLoader(r22);
                this.this$0.onExtrasChanged(r22);
                return;
            L20:
                this.this$0.onQueueTitleChanged((CharSequence) r2.obj);
                return;
            L22:
                this.this$0.onQueueChanged((List) r2.obj);
                return;
            L24:
                this.this$0.onAudioInfoChanged((PlaybackInfo) r2.obj);
                return;
            L26:
                this.this$0.onMetadataChanged((MediaMetadataCompat) r2.obj);
                return;
            L28:
                this.this$0.onPlaybackStateChanged((PlaybackStateCompat) r2.obj);
                return;
            L30:
                Bundle r0 = r2.getData();
                MediaSessionCompat.ensureClassLoader(r0);
                this.this$0.onSessionEvent((String) r2.obj, r0);
            }
        }

        public static class StubCompat extends IMediaControllerCallback.Stub {
            private final WeakReference<Callback> mCallback;

            public StubCompat(Callback r2) {
                this.mCallback = new WeakReference(r2);
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onCaptioningEnabledChanged(boolean r3) {
                Callback r2 = this.mCallback.get();
                if (r2 == null) goto L6;
                r2.postToHandler(11, Boolean.valueOf(r3), null);
                return;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onEvent(String r2, Bundle r3) {
                Callback r1 = this.mCallback.get();
                if (r1 == null) goto L6;
                r1.postToHandler(1, r2, r3);
                return;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat r3) {
                Callback r2 = this.mCallback.get();
                if (r2 == null) goto L6;
                r2.postToHandler(2, r3, null);
                return;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onRepeatModeChanged(int r3) {
                Callback r2 = this.mCallback.get();
                if (r2 == null) goto L6;
                r2.postToHandler(9, Integer.valueOf(r3), null);
                return;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onSessionReady() {
                Callback r2 = this.mCallback.get();
                if (r2 == null) goto L6;
                r2.postToHandler(13, null, null);
                return;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onShuffleModeChanged(int r3) {
                Callback r2 = this.mCallback.get();
                if (r2 == null) goto L6;
                r2.postToHandler(12, Integer.valueOf(r3), null);
                return;
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onShuffleModeChangedRemoved(boolean r1) {
            }
        }

        public Callback() {
            this.mCallbackFwk = new MediaControllerCallbackApi21(this);
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            postToHandler(8, null, null);
        }

        public IMediaControllerCallback getIControllerCallback() {
            return this.mIControllerCallback;
        }

        public void onAudioInfoChanged(PlaybackInfo r1) {
        }

        public void onCaptioningEnabledChanged(boolean r1) {
        }

        public void onExtrasChanged(Bundle r1) {
        }

        public abstract void onMetadataChanged(MediaMetadataCompat r1);

        public void onPlaybackStateChanged(PlaybackStateCompat r1) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> r1) {
        }

        public void onQueueTitleChanged(CharSequence r1) {
        }

        public void onRepeatModeChanged(int r1) {
        }

        public abstract void onSessionDestroyed();

        public void onSessionEvent(String r1, Bundle r2) {
        }

        public void onSessionReady() {
        }

        public void onShuffleModeChanged(int r1) {
        }

        public void postToHandler(int r1, Object r2, Bundle r3) {
            MessageHandler r0 = this.mHandler;
            if (r0 == null) goto L6;
            Message r02 = r0.obtainMessage(r1, r2);
            r02.setData(r3);
            r02.sendToTarget();
            return;
        }

        public void setHandler(Handler r2) {
            if (r2 != null) goto L7;
            MessageHandler r22 = this.mHandler;
            if (r22 == null) goto L9;
            r22.mRegistered = false;
            r22.removeCallbacksAndMessages(null);
            this.mHandler = null;
            return;
        L9:
            return;
        L7:
            MessageHandler r0 = new MessageHandler(this, r2.getLooper());
            this.mHandler = r0;
            r0.mRegistered = true;
        }
    }

    public interface MediaControllerImpl {
        MediaMetadataCompat getMetadata();

        PlaybackStateCompat getPlaybackState();

        PendingIntent getSessionActivity();

        TransportControls getTransportControls();

        void registerCallback(Callback r1, Handler r2);

        void unregisterCallback(Callback r1);
    }

    public static class MediaControllerImplApi21 implements MediaControllerImpl {
        private HashMap<Callback, ExtraCallback> mCallbackMap;
        protected final MediaController mControllerFwk;
        final Object mLock;
        private final List<Callback> mPendingCallbacks;
        final MediaSessionCompat.Token mSessionToken;

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> mMediaControllerImpl;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 r2) {
                super(null);
                this.mMediaControllerImpl = new WeakReference(r2);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int r3, Bundle r4) {
                MediaControllerImplApi21 r2 = this.mMediaControllerImpl.get();
                if (r2 == null) goto L14;
                if (r4 == null) goto L17;
                Object r32 = r2.mLock;
                monitor-enter(r32);
                r2.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(r4, "android.support.v4.media.session.EXTRA_BINDER")));     // Catch: Throwable -> L11
                r2.mSessionToken.setSession2Token(ParcelUtils.getVersionedParcelable(r4, "android.support.v4.media.session.SESSION_TOKEN2"));     // Catch: Throwable -> L11
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

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onExtrasChanged(Bundle r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onMetadataChanged(MediaMetadataCompat r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onQueueChanged(List<MediaSessionCompat.QueueItem> r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onQueueTitleChanged(CharSequence r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onSessionDestroyed() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onVolumeInfoChanged(ParcelableVolumeInfo r1) {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context r3, MediaSessionCompat.Token r4) {
            this.mLock = new Object();
            this.mPendingCallbacks = new ArrayList();
            this.mCallbackMap = new HashMap();
            this.mSessionToken = r4;
            this.mControllerFwk = new MediaController(r3, (MediaSession.Token) r4.getToken());
            if (r4.getExtraBinder() != null) goto L6;
            requestExtraBinder();
            return;
        }

        private void requestExtraBinder() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public MediaMetadataCompat getMetadata() {
            MediaMetadata r0 = this.mControllerFwk.getMetadata();
            if (r0 != null) goto L5;
            return null;
        L5:
            return MediaMetadataCompat.fromMediaMetadata(r0);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public PlaybackStateCompat getPlaybackState() {
            if (this.mSessionToken.getExtraBinder() != null) goto L13;
        L8:
            PlaybackState r3 = this.mControllerFwk.getPlaybackState();
            if (r3 != null) goto L15;
            return null;
        L15:
            return PlaybackStateCompat.fromPlaybackState(r3);
        L13:
            return this.mSessionToken.getExtraBinder().getPlaybackState();
        L6:
            e = move-exception;
            Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
            goto L8
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public PendingIntent getSessionActivity() {
            return this.mControllerFwk.getSessionActivity();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public TransportControls getTransportControls() {
            MediaController.TransportControls r2 = this.mControllerFwk.getTransportControls();
            if (Build.VERSION.SDK_INT < 29) goto L7;
            return new TransportControlsApi29(r2);
        L7:
            return new TransportControlsApi24(r2);
        }

        public void processPendingCallbacksLocked() {
            if (this.mSessionToken.getExtraBinder() != null) goto L5;
            return;
        L5:
            Iterator<Callback> r0 = this.mPendingCallbacks.iterator();
        L7:
            if (r0.hasNext() == false) goto L13;
            Callback r1 = r0.next();
            ExtraCallback r2 = new ExtraCallback(r1);
            this.mCallbackMap.put(r1, r2);
            r1.mIControllerCallback = r2;
            this.mSessionToken.getExtraBinder().registerCallbackListener(r2);     // Catch: RemoteException -> L11
            r1.postToHandler(13, null, null);
        L11:
            e = move-exception;
            Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
        L13:
            this.mPendingCallbacks.clear();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public final void registerCallback(Callback r4, Handler r5) {
            this.mControllerFwk.registerCallback(r4.mCallbackFwk, r5);
            Object r52 = this.mLock;
            monitor-enter(r52);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (this.mSessionToken.getExtraBinder() == null) goto L13;
            ExtraCallback r0 = new ExtraCallback(r4);     // Catch: Throwable -> L9
            this.mCallbackMap.put(r4, r0);     // Catch: Throwable -> L9
            r4.mIControllerCallback = r0;     // Catch: Throwable -> L9
            this.mSessionToken.getExtraBinder().registerCallbackListener(r0);     // Catch: Throwable -> L9 RemoteException -> L11
            r4.postToHandler(13, null, null);     // Catch: Throwable -> L9 RemoteException -> L11
        L14:
            monitor-exit(r52);     // Catch: Throwable -> L9
            return;
        L11:
            e = move-exception;
            Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);     // Catch: Throwable -> L9
            goto L14
        L13:
            r4.mIControllerCallback = null;     // Catch: Throwable -> L9
            this.mPendingCallbacks.add(r4);     // Catch: Throwable -> L9
            goto L14
        }

        public void sendCommand(String r1, Bundle r2, ResultReceiver r3) {
            this.mControllerFwk.sendCommand(r1, r2, r3);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public final void unregisterCallback(Callback r4) {
            this.mControllerFwk.unregisterCallback(r4.mCallbackFwk);
            Object r0 = this.mLock;
            monitor-enter(r0);
        L10:
            th = move-exception;
            throw th;
        L5:
            if (this.mSessionToken.getExtraBinder() != null) goto L21;
            this.mPendingCallbacks.remove(r4);     // Catch: Throwable -> L10
        L15:
            monitor-exit(r0);     // Catch: Throwable -> L10
            return;
        L21:
            ExtraCallback r1 = this.mCallbackMap.remove(r4);     // Catch: Throwable -> L10 RemoteException -> L12
            if (r1 == null) goto L15;
            r4.mIControllerCallback = null;     // Catch: Throwable -> L10 RemoteException -> L12
            this.mSessionToken.getExtraBinder().unregisterCallbackListener(r1);     // Catch: Throwable -> L10 RemoteException -> L12
        L12:
            e = move-exception;
            Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);     // Catch: Throwable -> L10
            goto L15
        }
    }

    public static class MediaControllerImplApi29 extends MediaControllerImplApi21 {
        public MediaControllerImplApi29(Context r1, MediaSessionCompat.Token r2) {
            super(r1, r2);
        }
    }

    public static final class PlaybackInfo {
        private final AudioAttributesCompat mAudioAttrsCompat;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        public PlaybackInfo(int r1, AudioAttributesCompat r2, int r3, int r4, int r5) {
            this.mPlaybackType = r1;
            this.mAudioAttrsCompat = r2;
            this.mVolumeControl = r3;
            this.mMaxVolume = r4;
            this.mCurrentVolume = r5;
        }
    }

    public static abstract class TransportControls {
        public TransportControls() {
        }

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String r1, Bundle r2);

        public abstract void stop();
    }

    public static class TransportControlsApi21 extends TransportControls {
        protected final MediaController.TransportControls mControlsFwk;

        public TransportControlsApi21(MediaController.TransportControls r1) {
            this.mControlsFwk = r1;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void pause() {
            this.mControlsFwk.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void play() {
            this.mControlsFwk.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void playFromMediaId(String r1, Bundle r2) {
            this.mControlsFwk.playFromMediaId(r1, r2);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void stop() {
            this.mControlsFwk.stop();
        }
    }

    public static class TransportControlsApi23 extends TransportControlsApi21 {
        public TransportControlsApi23(MediaController.TransportControls r1) {
            super(r1);
        }
    }

    public static class TransportControlsApi24 extends TransportControlsApi23 {
        public TransportControlsApi24(MediaController.TransportControls r1) {
            super(r1);
        }
    }

    public static class TransportControlsApi29 extends TransportControlsApi24 {
        public TransportControlsApi29(MediaController.TransportControls r1) {
            super(r1);
        }
    }

    public MediaControllerCompat(Context r3, MediaSessionCompat.Token r4) {
        if (r4 == null) goto L10;
        this.mRegisteredCallbacks = Collections.synchronizedSet(new HashSet());
        this.mToken = r4;
        if (Build.VERSION.SDK_INT < 29) goto L8;
        this.mImpl = new MediaControllerImplApi29(r3, r4);
        return;
    L8:
        this.mImpl = new MediaControllerImplApi21(r3, r4);
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("sessionToken must not be null");
        throw null;
    }

    public MediaControllerCompat(Context r1, MediaSessionCompat r2) {
        this(r1, r2.getSessionToken());
    }

    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.mToken;
    }

    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }

    public void registerCallback(Callback r2) {
        registerCallback(r2, null);
    }

    public void registerCallback(Callback r2, Handler r3) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("callback must not be null");
        return;
    L4:
        if (this.mRegisteredCallbacks.add(r2) == true) goto L7;
        Log.w("MediaControllerCompat", "the callback has already been registered");
        return;
    L7:
        if (r3 != null) goto L9;
        r3 = new Handler();
    L9:
        r2.setHandler(r3);
        this.mImpl.registerCallback(r2, r3);
    }

    public void unregisterCallback(Callback r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("callback must not be null");
        return;
    L4:
        if (this.mRegisteredCallbacks.remove(r2) == true) goto L16;
        Log.w("MediaControllerCompat", "the callback has never been registered");
        return;
    L16:
        this.mImpl.unregisterCallback(r2);     // Catch: Throwable -> L11
        r2.setHandler(null);
        return;
    L11:
        th = move-exception;
        r2.setHandler(null);
        throw th;
    }
}
