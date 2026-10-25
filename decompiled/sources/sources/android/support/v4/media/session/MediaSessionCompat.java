package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.core.app.BundleCompat;
import androidx.media.MediaSessionManager$RemoteUserInfo;
import androidx.media.VolumeProviderCompat;
import androidx.media.session.MediaButtonReceiver;
import androidx.mediarouter.media.MediaItemMetadata;
import androidx.versionedparcelable.ParcelUtils;
import androidx.versionedparcelable.VersionedParcelable;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public class MediaSessionCompat {
    static int sMaxBitmapSize;
    private final ArrayList<Object> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;

    /* renamed from: android.support.v4.media.session.MediaSessionCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends Callback {
        final /* synthetic */ MediaSessionCompat this$0;

        public AnonymousClass1(MediaSessionCompat r1) {
            this.this$0 = r1;
        }
    }

    public static abstract class Callback {
        final MediaSession.Callback mCallbackFwk;
        CallbackHandler mCallbackHandler;
        final Object mLock;
        private boolean mMediaPlayPausePendingOnHandler;
        WeakReference<MediaSessionImpl> mSessionImpl;

        public class CallbackHandler extends Handler {
            final /* synthetic */ Callback this$0;

            public CallbackHandler(Callback r1, Looper r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.os.Handler
            public void handleMessage(Message r5) {
                if (r5.what != 1) goto L18;
                Object r0 = this.this$0.mLock;
                monitor-enter(r0);
                MediaSessionImpl r1 = this.this$0.mSessionImpl.get();     // Catch: Throwable -> L15
                Callback r2 = this.this$0;     // Catch: Throwable -> L15
                CallbackHandler r3 = r2.mCallbackHandler;     // Catch: Throwable -> L15
                monitor-exit(r0);     // Catch: Throwable -> L15
                if (r1 != null) goto L10;
                return;
            L10:
                if (r2 != r1.getCallback()) goto L22;
                if (r3 == null) goto L23;
                r1.setCurrentControllerInfo((MediaSessionManager$RemoteUserInfo) r5.obj);
                this.this$0.handleMediaPlayPauseIfPendingOnHandler(r1, r3);
                r1.setCurrentControllerInfo(null);
                return;
            L23:
                return;
            L22:
                return;
            L15:
                th = move-exception;
                throw th;
            }
        }

        public class MediaSessionCallbackApi21 extends MediaSession.Callback {
            final /* synthetic */ Callback this$0;

            public MediaSessionCallbackApi21(Callback r1) {
                this.this$0 = r1;
            }

            private void clearCurrentControllerInfo(MediaSessionImpl r1) {
                r1.setCurrentControllerInfo(null);
            }

            private MediaSessionImplApi21 getSessionImplIfCallbackIsSet() {
                Object r0 = this.this$0.mLock;
                monitor-enter(r0);
                MediaSessionImplApi21 r1 = (MediaSessionImplApi21) this.this$0.mSessionImpl.get();     // Catch: Throwable -> L12
                monitor-exit(r0);     // Catch: Throwable -> L12
                if (r1 != null) goto L8;
                return null;
            L8:
                if (this.this$0 != r1.getCallback()) goto L17;
                return r1;
            L17:
                return null;
            L12:
                th = move-exception;
                throw th;
            }

            private void setCurrentControllerInfo(MediaSessionImpl r3) {
                if (Build.VERSION.SDK_INT < 28) goto L5;
                return;
            L5:
                String r2 = r3.getCallingPackage();
                if (TextUtils.isEmpty(r2) == false) goto L8;
                r2 = "android.media.session.MediaController";
            L8:
                r3.setCurrentControllerInfo(new MediaSessionManager$RemoteUserInfo(r2, -1, -1));
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCommand(String r6, Bundle r7, ResultReceiver r8) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r7);
                setCurrentControllerInfo(r0);
                QueueItem r2 = null;
                IBinder r22 = null;
                r2 = null;
                if (r6.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER") == false) goto L15;
                Bundle r62 = new Bundle();     // Catch: BadParcelableException -> L37
                Token r72 = r0.getSessionToken();     // Catch: BadParcelableException -> L37
                IMediaSession r1 = r72.getExtraBinder();     // Catch: BadParcelableException -> L37
                if (r1 == null) goto L12;
                r22 = r1.asBinder();     // Catch: BadParcelableException -> L37
            L12:
                BundleCompat.putBinder(r62, "android.support.v4.media.session.EXTRA_BINDER", r22);     // Catch: BadParcelableException -> L37
                ParcelUtils.putVersionedParcelable(r62, "android.support.v4.media.session.SESSION_TOKEN2", r72.getSession2Token());     // Catch: BadParcelableException -> L37
                r8.send(0, r62);     // Catch: BadParcelableException -> L37
            L38:
                clearCurrentControllerInfo(r0);
                return;
            L15:
                if (r6.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM") == false) goto L19;
                this.this$0.onAddQueueItem((MediaDescriptionCompat) r7.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));     // Catch: BadParcelableException -> L37
                goto L38
            L19:
                if (r6.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT") == false) goto L22;
                this.this$0.onAddQueueItem((MediaDescriptionCompat) r7.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), r7.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));     // Catch: BadParcelableException -> L37
                goto L38
            L22:
                if (r6.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM") == false) goto L25;
                this.this$0.onRemoveQueueItem((MediaDescriptionCompat) r7.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));     // Catch: BadParcelableException -> L37
                goto L38
            L25:
                if (r6.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT") == true) goto L27;
                this.this$0.onCommand(r6, r7, r8);     // Catch: BadParcelableException -> L37
                goto L38
            L27:
                if (r0.mQueue == null) goto L38;
                int r63 = r7.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);     // Catch: BadParcelableException -> L37
                if (r63 >= 0) goto L31;
            L33:
                if (r2 == null) goto L38;
                this.this$0.onRemoveQueueItem(r2.getDescription());     // Catch: BadParcelableException -> L37
                goto L38
            L31:
                if (r63 >= r0.mQueue.size()) goto L33;
                r2 = r0.mQueue.get(r63);     // Catch: BadParcelableException -> L37
            L37:
                Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                goto L38
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCustomAction(String r5, Bundle r6) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r6);
                setCurrentControllerInfo(r0);
            L39:
                Log.e("MediaSessionCompat", "Could not unparcel the data.");
            L40:
                clearCurrentControllerInfo(r0);
                return;
            L8:
                if (r5.equals("android.support.v4.media.session.action.PLAY_FROM_URI") == false) goto L11;
                Uri r52 = (Uri) r6.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");     // Catch: BadParcelableException -> L39
                Bundle r62 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L39
                MediaSessionCompat.ensureClassLoader(r62);     // Catch: BadParcelableException -> L39
                this.this$0.onPlayFromUri(r52, r62);     // Catch: BadParcelableException -> L39
            L42:
                goto L40
            L11:
                if (r5.equals("android.support.v4.media.session.action.PREPARE") == false) goto L14;
                this.this$0.onPrepare();     // Catch: BadParcelableException -> L39
                goto L42
            L14:
                if (r5.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID") == false) goto L17;
                String r53 = r6.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");     // Catch: BadParcelableException -> L39
                Bundle r63 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L39
                MediaSessionCompat.ensureClassLoader(r63);     // Catch: BadParcelableException -> L39
                this.this$0.onPrepareFromMediaId(r53, r63);     // Catch: BadParcelableException -> L39
                goto L42
            L17:
                if (r5.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH") == false) goto L20;
                String r54 = r6.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");     // Catch: BadParcelableException -> L39
                Bundle r64 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L39
                MediaSessionCompat.ensureClassLoader(r64);     // Catch: BadParcelableException -> L39
                this.this$0.onPrepareFromSearch(r54, r64);     // Catch: BadParcelableException -> L39
                goto L42
            L20:
                if (r5.equals("android.support.v4.media.session.action.PREPARE_FROM_URI") == false) goto L23;
                Uri r55 = (Uri) r6.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");     // Catch: BadParcelableException -> L39
                Bundle r65 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L39
                MediaSessionCompat.ensureClassLoader(r65);     // Catch: BadParcelableException -> L39
                this.this$0.onPrepareFromUri(r55, r65);     // Catch: BadParcelableException -> L39
                goto L42
            L23:
                if (r5.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED") == false) goto L26;
                boolean r56 = r6.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");     // Catch: BadParcelableException -> L39
                this.this$0.onSetCaptioningEnabled(r56);     // Catch: BadParcelableException -> L39
                goto L42
            L26:
                if (r5.equals("android.support.v4.media.session.action.SET_REPEAT_MODE") == false) goto L29;
                int r57 = r6.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");     // Catch: BadParcelableException -> L39
                this.this$0.onSetRepeatMode(r57);     // Catch: BadParcelableException -> L39
                goto L42
            L29:
                if (r5.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE") == false) goto L32;
                int r58 = r6.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");     // Catch: BadParcelableException -> L39
                this.this$0.onSetShuffleMode(r58);     // Catch: BadParcelableException -> L39
                goto L42
            L32:
                if (r5.equals("android.support.v4.media.session.action.SET_RATING") == false) goto L35;
                RatingCompat r59 = (RatingCompat) r6.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");     // Catch: BadParcelableException -> L39
                Bundle r66 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L39
                MediaSessionCompat.ensureClassLoader(r66);     // Catch: BadParcelableException -> L39
                this.this$0.onSetRating(r59, r66);     // Catch: BadParcelableException -> L39
                goto L42
            L35:
                if (r5.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED") == false) goto L37;
                float r510 = r6.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f);     // Catch: BadParcelableException -> L39
                this.this$0.onSetPlaybackSpeed(r510);     // Catch: BadParcelableException -> L39
                goto L42
            L37:
                this.this$0.onCustomAction(r5, r6);     // Catch: BadParcelableException -> L39
                goto L42
            }

            @Override // android.media.session.MediaSession.Callback
            public void onFastForward() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onFastForward();
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public boolean onMediaButtonEvent(Intent r4) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return false;
            L5:
                setCurrentControllerInfo(r0);
                boolean r2 = this.this$0.onMediaButtonEvent(r4);
                clearCurrentControllerInfo(r0);
                if (r2 == false) goto L8;
                return true;
            L8:
                if (super.onMediaButtonEvent(r4) == true) goto L13;
                return false;
            L13:
                return true;
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPause() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onPause();
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlay() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onPlay();
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromMediaId(String r3, Bundle r4) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r4);
                setCurrentControllerInfo(r0);
                this.this$0.onPlayFromMediaId(r3, r4);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromSearch(String r3, Bundle r4) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r4);
                setCurrentControllerInfo(r0);
                this.this$0.onPlayFromSearch(r3, r4);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromUri(Uri r3, Bundle r4) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r4);
                setCurrentControllerInfo(r0);
                this.this$0.onPlayFromUri(r3, r4);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepare() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onPrepare();
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromMediaId(String r3, Bundle r4) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r4);
                setCurrentControllerInfo(r0);
                this.this$0.onPrepareFromMediaId(r3, r4);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromSearch(String r3, Bundle r4) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r4);
                setCurrentControllerInfo(r0);
                this.this$0.onPrepareFromSearch(r3, r4);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromUri(Uri r3, Bundle r4) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r4);
                setCurrentControllerInfo(r0);
                this.this$0.onPrepareFromUri(r3, r4);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onRewind() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onRewind();
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSeekTo(long r3) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onSeekTo(r3);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetPlaybackSpeed(float r3) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onSetPlaybackSpeed(r3);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetRating(Rating r3) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onSetRating(RatingCompat.fromRating(r3));
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToNext() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onSkipToNext();
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToPrevious() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onSkipToPrevious();
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToQueueItem(long r3) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onSkipToQueueItem(r3);
                clearCurrentControllerInfo(r0);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onStop() {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                setCurrentControllerInfo(r0);
                this.this$0.onStop();
                clearCurrentControllerInfo(r0);
            }
        }

        public Callback() {
            this.mLock = new Object();
            this.mCallbackFwk = new MediaSessionCallbackApi21(this);
            this.mSessionImpl = new WeakReference(null);
        }

        public void handleMediaPlayPauseIfPendingOnHandler(MediaSessionImpl r9, Handler r10) {
            if (this.mMediaPlayPausePendingOnHandler == false) goto L31;
            boolean r0 = false;
            this.mMediaPlayPausePendingOnHandler = false;
            r10.removeMessages(1);
            PlaybackStateCompat r92 = r9.getPlaybackState();
            if (r92 != null) goto L8;
            long r4 = 0;
        L9:
            if (r92 != null) goto L11;
        L13:
            boolean r93 = false;
        L15:
            if ((516 & r4) == 0) goto L17;
            boolean r102 = true;
        L19:
            if ((r4 & 514) == 0) goto L21;
            r0 = true;
        L21:
            if (r93 == false) goto L25;
            if (r0 == false) goto L25;
            onPause();
            return;
        L25:
            if (r93 == true) goto L29;
            if (r102 == false) goto L30;
            onPlay();
            return;
        L30:
            return;
        L29:
            return;
        L17:
            r102 = false;
            goto L19
        L11:
            if (r92.getState() != 3) goto L13;
            r93 = true;
            goto L15
        L8:
            r4 = r92.getActions();
            goto L9
        }

        public void onAddQueueItem(MediaDescriptionCompat r1) {
        }

        public void onAddQueueItem(MediaDescriptionCompat r1, int r2) {
        }

        public void onCommand(String r1, Bundle r2, ResultReceiver r3) {
        }

        public void onCustomAction(String r1, Bundle r2) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent r8) {
            if (Build.VERSION.SDK_INT < 27) goto L5;
            return false;
        L5:
            Object r0 = this.mLock;
            monitor-enter(r0);
            MediaSessionImpl r1 = this.mSessionImpl.get();     // Catch: Throwable -> L38
            CallbackHandler r3 = this.mCallbackHandler;     // Catch: Throwable -> L38
            monitor-exit(r0);     // Catch: Throwable -> L38
            if (r1 == null) goto L37;
            if (r3 == null) goto L37;
            KeyEvent r82 = (KeyEvent) r8.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (r82 == null) goto L37;
            if (r82.getAction() != 0) goto L37;
            MediaSessionManager$RemoteUserInfo r02 = r1.getCurrentControllerInfo();
            int r4 = r82.getKeyCode();
            if (r4 == 79) goto L24;
            if (r4 == 85) goto L24;
            handleMediaPlayPauseIfPendingOnHandler(r1, r3);
            return false;
        L24:
            if (r82.getRepeatCount() == 0) goto L26;
            handleMediaPlayPauseIfPendingOnHandler(r1, r3);
        L36:
            return true;
        L26:
            if (this.mMediaPlayPausePendingOnHandler == false) goto L34;
            r3.removeMessages(1);
            this.mMediaPlayPausePendingOnHandler = false;
            PlaybackStateCompat r83 = r1.getPlaybackState();
            if (r83 != null) goto L30;
            long r2 = 0;
        L32:
            if ((r2 & 32) == 0) goto L36;
            onSkipToNext();
            goto L36
        L30:
            r2 = r83.getActions();
            goto L32
        L34:
            this.mMediaPlayPausePendingOnHandler = true;
            r3.sendMessageDelayed(r3.obtainMessage(1, r02), ViewConfiguration.getDoubleTapTimeout());
        L37:
            return false;
        L38:
            th = move-exception;
            throw th;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String r1, Bundle r2) {
        }

        public void onPlayFromSearch(String r1, Bundle r2) {
        }

        public void onPlayFromUri(Uri r1, Bundle r2) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String r1, Bundle r2) {
        }

        public void onPrepareFromSearch(String r1, Bundle r2) {
        }

        public void onPrepareFromUri(Uri r1, Bundle r2) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat r1) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int r1) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long r1) {
        }

        public void onSetCaptioningEnabled(boolean r1) {
        }

        public void onSetPlaybackSpeed(float r1) {
        }

        public void onSetRating(RatingCompat r1) {
        }

        public void onSetRating(RatingCompat r1, Bundle r2) {
        }

        public void onSetRepeatMode(int r1) {
        }

        public void onSetShuffleMode(int r1) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long r1) {
        }

        public void onStop() {
        }

        public void setSessionImpl(MediaSessionImpl r4, Handler r5) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mSessionImpl = new WeakReference(r4);     // Catch: Throwable -> L7
            CallbackHandler r1 = this.mCallbackHandler;     // Catch: Throwable -> L7
            CallbackHandler r2 = null;
            if (r1 == null) goto L9;
            r1.removeCallbacksAndMessages(null);     // Catch: Throwable -> L7
        L9:
            if (r4 == null) goto L13;
            if (r5 == null) goto L13;
            r2 = new CallbackHandler(this, r5.getLooper());     // Catch: Throwable -> L7
        L13:
            this.mCallbackHandler = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    public interface MediaSessionImpl {
        Callback getCallback();

        String getCallingPackage();

        MediaSessionManager$RemoteUserInfo getCurrentControllerInfo();

        PlaybackStateCompat getPlaybackState();

        Token getSessionToken();

        boolean isActive();

        void release();

        void setActive(boolean r1);

        void setCallback(Callback r1, Handler r2);

        void setCurrentControllerInfo(MediaSessionManager$RemoteUserInfo r1);

        void setExtras(Bundle r1);

        void setFlags(int r1);

        void setMediaButtonReceiver(PendingIntent r1);

        void setMetadata(MediaMetadataCompat r1);

        void setPlaybackState(PlaybackStateCompat r1);

        void setPlaybackToLocal(int r1);

        void setPlaybackToRemote(VolumeProviderCompat r1);

        void setQueue(List<QueueItem> r1);

        void setQueueTitle(CharSequence r1);

        void setRepeatMode(int r1);

        void setSessionActivity(PendingIntent r1);

        void setShuffleMode(int r1);
    }

    public static class MediaSessionImplApi21 implements MediaSessionImpl {
        Callback mCallback;
        boolean mCaptioningEnabled;
        boolean mDestroyed;
        final RemoteCallbackList<IMediaControllerCallback> mExtraControllerCallbacks;
        final ExtraSession mExtraSession;
        final Object mLock;
        MediaMetadataCompat mMetadata;
        PlaybackStateCompat mPlaybackState;
        List<QueueItem> mQueue;
        int mRatingType;
        MediaSessionManager$RemoteUserInfo mRemoteUserInfo;
        int mRepeatMode;
        final MediaSession mSessionFwk;
        Bundle mSessionInfo;
        int mShuffleMode;
        final Token mToken;

        public static class ExtraSession extends IMediaSession.Stub {
            private final AtomicReference<MediaSessionImplApi21> mMediaSessionImplRef;

            public ExtraSession(MediaSessionImplApi21 r2) {
                this.mMediaSessionImplRef = new AtomicReference(r2);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItem(MediaDescriptionCompat r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItemAt(MediaDescriptionCompat r1, int r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void adjustVolume(int r1, int r2, String r3) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void fastForward() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle getExtras() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public long getFlags() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getPackageName() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                MediaSessionImplApi21 r1 = this.mMediaSessionImplRef.get();
                if (r1 != null) goto L5;
                return null;
            L5:
                return MediaSessionCompat.getStateWithUpdatedPosition(r1.mPlaybackState, r1.mMetadata);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public List<QueueItem> getQueue() {
                return null;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRatingType() {
                MediaSessionImplApi21 r0 = this.mMediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return 0;
            L5:
                return r0.mRatingType;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRepeatMode() {
                MediaSessionImplApi21 r0 = this.mMediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return -1;
            L5:
                return r0.mRepeatMode;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle getSessionInfo() {
                MediaSessionImplApi21 r1 = this.mMediaSessionImplRef.get();
                if (r1.mSessionInfo != null) goto L7;
                return null;
            L7:
                return new Bundle(r1.mSessionInfo);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getShuffleMode() {
                MediaSessionImplApi21 r0 = this.mMediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return -1;
            L5:
                return r0.mShuffleMode;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getTag() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isCaptioningEnabled() {
                MediaSessionImplApi21 r0 = this.mMediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return false;
            L5:
                if (r0.mCaptioningEnabled == false) goto L10;
                return true;
            L10:
                return false;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void next() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void pause() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void play() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromMediaId(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromSearch(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromUri(Uri r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepare() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromMediaId(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromSearch(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromUri(Uri r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void previous() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rate(RatingCompat r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rateWithExtras(RatingCompat r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback r5) {
                MediaSessionImplApi21 r4 = this.mMediaSessionImplRef.get();
                if (r4 != null) goto L5;
                return;
            L5:
                MediaSessionManager$RemoteUserInfo r2 = new MediaSessionManager$RemoteUserInfo("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid());
                r4.mExtraControllerCallbacks.register(r5, r2);
                Object r42 = r4.mLock;
                monitor-enter(r42);
                monitor-exit(r42);     // Catch: Throwable -> L9
                return;
            L9:
                th = move-exception;
                throw th;
            }

            public void release() {
                this.mMediaSessionImplRef.set(null);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItem(MediaDescriptionCompat r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItemAt(int r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rewind() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void seekTo(long r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCommand(String r1, Bundle r2, ResultReceiverWrapper r3) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCustomAction(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean sendMediaButton(KeyEvent r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setCaptioningEnabled(boolean r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setPlaybackSpeed(float r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setRepeatMode(int r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleMode(int r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleModeEnabledRemoved(boolean r1) {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setVolumeTo(int r1, int r2, String r3) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void skipToQueueItem(long r1) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void stop() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback r2) {
                MediaSessionImplApi21 r1 = this.mMediaSessionImplRef.get();
                if (r1 != null) goto L5;
                return;
            L5:
                r1.mExtraControllerCallbacks.unregister(r2);
                Binder.getCallingPid();
                Binder.getCallingUid();
                Object r12 = r1.mLock;
                monitor-enter(r12);
                monitor-exit(r12);     // Catch: Throwable -> L9
                return;
            L9:
                th = move-exception;
                throw th;
            }
        }

        public MediaSessionImplApi21(Context r2, String r3, VersionedParcelable r4, Bundle r5) {
            this.mLock = new Object();
            this.mDestroyed = false;
            this.mExtraControllerCallbacks = new RemoteCallbackList();
            MediaSession r22 = createFwkMediaSession(r2, r3, r5);
            this.mSessionFwk = r22;
            ExtraSession r32 = new ExtraSession(this);
            this.mExtraSession = r32;
            this.mToken = new Token(r22.getSessionToken(), r32, r4);
            this.mSessionInfo = r5;
            setFlags(3);
        }

        public MediaSessionImplApi21(Object r4) {
            this.mLock = new Object();
            this.mDestroyed = false;
            this.mExtraControllerCallbacks = new RemoteCallbackList();
            if ((r4 instanceof MediaSession) == false) goto L6;
            MediaSession r42 = (MediaSession) r4;
            this.mSessionFwk = r42;
            ExtraSession r0 = new ExtraSession(this);
            this.mExtraSession = r0;
            this.mToken = new Token(r42.getSessionToken(), r0);
            this.mSessionInfo = null;
            setFlags(3);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("mediaSession is not a valid MediaSession object");
            throw null;
        }

        public MediaSession createFwkMediaSession(Context r1, String r2, Bundle r3) {
            return new MediaSession(r1, r2);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Callback getCallback() {
            Object r0 = this.mLock;
            monitor-enter(r0);
            Callback r1 = this.mCallback;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public String getCallingPackage() {
            return (String) this.mSessionFwk.getClass().getMethod("getCallingPackage", null).invoke(this.mSessionFwk, null);
        L5:
            e = move-exception;
            Log.e("MediaSessionCompat", "Cannot execute MediaSession.getCallingPackage()", e);
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public MediaSessionManager$RemoteUserInfo getCurrentControllerInfo() {
            Object r0 = this.mLock;
            monitor-enter(r0);
            MediaSessionManager$RemoteUserInfo r1 = this.mRemoteUserInfo;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public PlaybackStateCompat getPlaybackState() {
            return this.mPlaybackState;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Token getSessionToken() {
            return this.mToken;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public boolean isActive() {
            return this.mSessionFwk.isActive();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void release() {
            this.mDestroyed = true;
            this.mExtraControllerCallbacks.kill();
            if (Build.VERSION.SDK_INT == 27) goto L12;
        L10:
            this.mSessionFwk.setCallback(null);
            this.mExtraSession.release();
            this.mSessionFwk.release();
            return;
        L12:
            Field r1 = this.mSessionFwk.getClass().getDeclaredField("mCallback");     // Catch: Exception -> L8
            r1.setAccessible(true);     // Catch: Exception -> L8
            Handler r0 = (Handler) r1.get(this.mSessionFwk);     // Catch: Exception -> L8
            if (r0 == null) goto L10;
            r0.removeCallbacksAndMessages(null);     // Catch: Exception -> L8
        L8:
            e = move-exception;
            Log.w("MediaSessionCompat", "Exception happened while accessing MediaSession.mCallback.", e);
            goto L10
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setActive(boolean r1) {
            this.mSessionFwk.setActive(r1);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCallback(Callback r4, Handler r5) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mCallback = r4;     // Catch: Throwable -> L11
            MediaSession r1 = this.mSessionFwk;     // Catch: Throwable -> L11
            if (r4 != null) goto L7;
            MediaSession.Callback r2 = null;
        L8:
            r1.setCallback(r2, r5);     // Catch: Throwable -> L11
            if (r4 == null) goto L13;
            r4.setSessionImpl(this, r5);     // Catch: Throwable -> L11
        L13:
            monitor-exit(r0);     // Catch: Throwable -> L11
            return;
        L7:
            r2 = r4.mCallbackFwk;     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            throw th;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager$RemoteUserInfo r2) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mRemoteUserInfo = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setExtras(Bundle r1) {
            this.mSessionFwk.setExtras(r1);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        @SuppressLint({"WrongConstant"})
        public void setFlags(int r1) {
            this.mSessionFwk.setFlags(r1 | 3);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setMediaButtonReceiver(PendingIntent r1) {
            this.mSessionFwk.setMediaButtonReceiver(r1);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setMetadata(MediaMetadataCompat r1) {
            this.mMetadata = r1;
            MediaSession r0 = this.mSessionFwk;
            if (r1 != null) goto L5;
            MediaMetadata r12 = null;
        L6:
            r0.setMetadata(r12);
            return;
        L5:
            r12 = (MediaMetadata) r1.getMediaMetadata();
            goto L6
        }

        /*  JADX ERROR: NullPointerException in pass: BlockProcessor
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPredecessors()" because "to" is null
            	at jadx.core.dex.visitors.blocks.BlockSplitter.connect(BlockSplitter.java:159)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectSplittersAndHandlers(BlockExceptionHandler.java:480)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.wrapBlocksWithTryCatch(BlockExceptionHandler.java:381)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectExcHandlers(BlockExceptionHandler.java:90)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.process(BlockExceptionHandler.java:61)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.independentBlockTreeMod(BlockProcessor.java:372)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:56)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:49)
            */
        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackState(android.support.v4.media.session.PlaybackStateCompat r4) {
            /*  JADX ERROR: Simple mode code generation failed
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPos()" because "block" is null
                	at jadx.core.utils.blocks.BlockSet.addChecked(BlockSet.java:69)
                	at jadx.core.utils.blocks.DFSIteration.next(DFSIteration.java:35)
                	at jadx.core.utils.BlockUtils.visitDFS(BlockUtils.java:528)
                	at jadx.core.utils.BlockUtils.visitDFS(BlockUtils.java:517)
                	at jadx.core.codegen.SimpleModeHelper.getSortedBlocks(SimpleModeHelper.java:148)
                	at jadx.core.codegen.SimpleModeHelper.prepareBlocks(SimpleModeHelper.java:38)
                	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:334)
                	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                */
            /*
                r3 = this;
                r3.mPlaybackState = r4
                java.lang.Object r0 = r3.mLock
                monitor-enter(r0)
                android.os.RemoteCallbackList<android.support.v4.media.session.IMediaControllerCallback> r1 = r3.mExtraControllerCallbacks     // Catch: java.lang.Throwable -> L1b
                int r1 = r1.beginBroadcast()     // Catch: java.lang.Throwable -> L1b
                int r1 = r1 + (-1)
            Ld:
                android.os.RemoteCallbackList<android.support.v4.media.session.IMediaControllerCallback> r2 = r3.mExtraControllerCallbacks
                if (r1 < 0) goto L20
                android.os.IInterface r2 = r2.getBroadcastItem(r1)     // Catch: java.lang.Throwable -> L1b
                android.support.v4.media.session.IMediaControllerCallback r2 = (android.support.v4.media.session.IMediaControllerCallback) r2     // Catch: java.lang.Throwable -> L1b
                r2.onPlaybackStateChanged(r4)     // Catch: java.lang.Throwable -> L1b android.os.RemoteException -> L1d
                goto L1d
            L1b:
                r3 = move-exception
                goto L34
            L1d:
                int r1 = r1 + (-1)
                goto Ld
            L20:
                r2.finishBroadcast()     // Catch: java.lang.Throwable -> L1b
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L1b
                android.media.session.MediaSession r3 = r3.mSessionFwk
                if (r4 != 0) goto L2a
                r4 = 0
                goto L30
            L2a:
                java.lang.Object r4 = r4.getPlaybackState()
                android.media.session.PlaybackState r4 = (android.media.session.PlaybackState) r4
            L30:
                r3.setPlaybackState(r4)
                return
            L34:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L1b
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21.setPlaybackState(android.support.v4.media.session.PlaybackStateCompat):void");
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToLocal(int r2) {
            AudioAttributes.Builder r0 = new AudioAttributes.Builder();
            r0.setLegacyStreamType(r2);
            this.mSessionFwk.setPlaybackToLocal(r0.build());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToRemote(VolumeProviderCompat r1) {
            this.mSessionFwk.setPlaybackToRemote((VolumeProvider) r1.getVolumeProvider());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setQueue(List<QueueItem> r3) {
            this.mQueue = r3;
            if (r3 != null) goto L6;
            this.mSessionFwk.setQueue(null);
            return;
        L6:
            ArrayList r0 = new ArrayList(r3.size());
            Iterator<QueueItem> r32 = r3.iterator();
        L8:
            if (r32.hasNext() == false) goto L10;
            r0.add((MediaSession.QueueItem) r32.next().getQueueItem());
            goto L8
        L10:
            this.mSessionFwk.setQueue(r0);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setQueueTitle(CharSequence r1) {
            this.mSessionFwk.setQueueTitle(r1);
        }

        /*  JADX ERROR: NullPointerException in pass: BlockProcessor
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPredecessors()" because "to" is null
            	at jadx.core.dex.visitors.blocks.BlockSplitter.connect(BlockSplitter.java:159)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectSplittersAndHandlers(BlockExceptionHandler.java:480)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.wrapBlocksWithTryCatch(BlockExceptionHandler.java:381)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectExcHandlers(BlockExceptionHandler.java:90)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.process(BlockExceptionHandler.java:61)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.independentBlockTreeMod(BlockProcessor.java:372)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:56)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:49)
            */
        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setRepeatMode(int r4) {
            /*  JADX ERROR: Simple mode code generation failed
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPos()" because "block" is null
                	at jadx.core.utils.blocks.BlockSet.addChecked(BlockSet.java:69)
                	at jadx.core.utils.blocks.DFSIteration.next(DFSIteration.java:35)
                	at jadx.core.utils.BlockUtils.visitDFS(BlockUtils.java:528)
                	at jadx.core.utils.BlockUtils.visitDFS(BlockUtils.java:517)
                	at jadx.core.codegen.SimpleModeHelper.getSortedBlocks(SimpleModeHelper.java:148)
                	at jadx.core.codegen.SimpleModeHelper.prepareBlocks(SimpleModeHelper.java:38)
                	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:334)
                	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                */
            /*
                r3 = this;
                int r0 = r3.mRepeatMode
                if (r0 == r4) goto L2b
                r3.mRepeatMode = r4
                java.lang.Object r0 = r3.mLock
                monitor-enter(r0)
                android.os.RemoteCallbackList<android.support.v4.media.session.IMediaControllerCallback> r1 = r3.mExtraControllerCallbacks     // Catch: java.lang.Throwable -> L1f
                int r1 = r1.beginBroadcast()     // Catch: java.lang.Throwable -> L1f
                int r1 = r1 + (-1)
            L11:
                android.os.RemoteCallbackList<android.support.v4.media.session.IMediaControllerCallback> r2 = r3.mExtraControllerCallbacks
                if (r1 < 0) goto L24
                android.os.IInterface r2 = r2.getBroadcastItem(r1)     // Catch: java.lang.Throwable -> L1f
                android.support.v4.media.session.IMediaControllerCallback r2 = (android.support.v4.media.session.IMediaControllerCallback) r2     // Catch: java.lang.Throwable -> L1f
                r2.onRepeatModeChanged(r4)     // Catch: java.lang.Throwable -> L1f android.os.RemoteException -> L21
                goto L21
            L1f:
                r3 = move-exception
                goto L29
            L21:
                int r1 = r1 + (-1)
                goto L11
            L24:
                r2.finishBroadcast()     // Catch: java.lang.Throwable -> L1f
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L1f
                goto L2b
            L29:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L1f
                throw r3
            L2b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21.setRepeatMode(int):void");
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setSessionActivity(PendingIntent r1) {
            this.mSessionFwk.setSessionActivity(r1);
        }

        /*  JADX ERROR: NullPointerException in pass: BlockProcessor
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPredecessors()" because "to" is null
            	at jadx.core.dex.visitors.blocks.BlockSplitter.connect(BlockSplitter.java:159)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectSplittersAndHandlers(BlockExceptionHandler.java:480)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.wrapBlocksWithTryCatch(BlockExceptionHandler.java:381)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectExcHandlers(BlockExceptionHandler.java:90)
            	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.process(BlockExceptionHandler.java:61)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.independentBlockTreeMod(BlockProcessor.java:372)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:56)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:49)
            */
        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setShuffleMode(int r4) {
            /*  JADX ERROR: Simple mode code generation failed
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPos()" because "block" is null
                	at jadx.core.utils.blocks.BlockSet.addChecked(BlockSet.java:69)
                	at jadx.core.utils.blocks.DFSIteration.next(DFSIteration.java:35)
                	at jadx.core.utils.BlockUtils.visitDFS(BlockUtils.java:528)
                	at jadx.core.utils.BlockUtils.visitDFS(BlockUtils.java:517)
                	at jadx.core.codegen.SimpleModeHelper.getSortedBlocks(SimpleModeHelper.java:148)
                	at jadx.core.codegen.SimpleModeHelper.prepareBlocks(SimpleModeHelper.java:38)
                	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:334)
                	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                */
            /*
                r3 = this;
                int r0 = r3.mShuffleMode
                if (r0 == r4) goto L2b
                r3.mShuffleMode = r4
                java.lang.Object r0 = r3.mLock
                monitor-enter(r0)
                android.os.RemoteCallbackList<android.support.v4.media.session.IMediaControllerCallback> r1 = r3.mExtraControllerCallbacks     // Catch: java.lang.Throwable -> L1f
                int r1 = r1.beginBroadcast()     // Catch: java.lang.Throwable -> L1f
                int r1 = r1 + (-1)
            L11:
                android.os.RemoteCallbackList<android.support.v4.media.session.IMediaControllerCallback> r2 = r3.mExtraControllerCallbacks
                if (r1 < 0) goto L24
                android.os.IInterface r2 = r2.getBroadcastItem(r1)     // Catch: java.lang.Throwable -> L1f
                android.support.v4.media.session.IMediaControllerCallback r2 = (android.support.v4.media.session.IMediaControllerCallback) r2     // Catch: java.lang.Throwable -> L1f
                r2.onShuffleModeChanged(r4)     // Catch: java.lang.Throwable -> L1f android.os.RemoteException -> L21
                goto L21
            L1f:
                r3 = move-exception
                goto L29
            L21:
                int r1 = r1 + (-1)
                goto L11
            L24:
                r2.finishBroadcast()     // Catch: java.lang.Throwable -> L1f
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L1f
                goto L2b
            L29:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L1f
                throw r3
            L2b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21.setShuffleMode(int):void");
        }
    }

    public static class MediaSessionImplApi22 extends MediaSessionImplApi21 {
        public MediaSessionImplApi22(Context r1, String r2, VersionedParcelable r3, Bundle r4) {
            super(r1, r2, r3, r4);
        }

        public MediaSessionImplApi22(Object r1) {
            super(r1);
        }
    }

    public static class MediaSessionImplApi28 extends MediaSessionImplApi22 {
        public MediaSessionImplApi28(Context r1, String r2, VersionedParcelable r3, Bundle r4) {
            super(r1, r2, r3, r4);
        }

        public MediaSessionImplApi28(Object r1) {
            super(r1);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21, android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public final MediaSessionManager$RemoteUserInfo getCurrentControllerInfo() {
            return new MediaSessionManager$RemoteUserInfo(this.mSessionFwk.getCurrentControllerInfo());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21, android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager$RemoteUserInfo r1) {
        }
    }

    public static class MediaSessionImplApi29 extends MediaSessionImplApi28 {
        public MediaSessionImplApi29(Context r1, String r2, VersionedParcelable r3, Bundle r4) {
            super(r1, r2, r3, r4);
        }

        public MediaSessionImplApi29(Object r1) {
            super(r1);
            this.mSessionInfo = ((MediaSession) r1).getController().getSessionInfo();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21
        public MediaSession createFwkMediaSession(Context r1, String r2, Bundle r3) {
            return MediaSessionCompat$MediaSessionImplApi29$$ExternalSyntheticApiModelOutline0.m(r1, r2, r3);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = null;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private MediaSession.QueueItem mItemFwk;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$QueueItem$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<QueueItem> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QueueItem createFromParcel(Parcel r1) {
                return new QueueItem(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ QueueItem createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QueueItem[] newArray(int r1) {
                return new QueueItem[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ QueueItem[] newArray(int r1) {
                return newArray(r1);
            }
        }

        public static class Api21Impl {
            public static MediaSession.QueueItem createQueueItem(MediaDescription r1, long r2) {
                return new MediaSession.QueueItem(r1, r2);
            }

            public static MediaDescription getDescription(MediaSession.QueueItem r0) {
                return r0.getDescription();
            }

            public static long getQueueId(MediaSession.QueueItem r2) {
                return r2.getQueueId();
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        private QueueItem(MediaSession.QueueItem r4, MediaDescriptionCompat r5, long r6) {
            if (r5 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Description cannot be null");
            throw null;
        L5:
            if (r6 == (-1)) goto L8;
            this.mDescription = r5;
            this.mId = r6;
            this.mItemFwk = r4;
            return;
        L8:
            f$$ExternalSyntheticBUOutline1.m("Id cannot be QueueItem.UNKNOWN_ID");
            throw null;
        }

        public QueueItem(Parcel r3) {
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(r3);
            this.mId = r3.readLong();
        }

        public QueueItem(MediaDescriptionCompat r2, long r3) {
            this(null, r2, r3);
        }

        public static QueueItem fromQueueItem(Object r4) {
            if (r4 == null) goto L5;
            MediaSession.QueueItem r42 = (MediaSession.QueueItem) r4;
            return new QueueItem(r42, MediaDescriptionCompat.fromMediaDescription(Api21Impl.getDescription(r42)), Api21Impl.getQueueId(r42));
        L5:
            return null;
        }

        public static List<QueueItem> fromQueueItemList(List<?> r2) {
            if (r2 == null) goto L8;
            ArrayList r0 = new ArrayList(r2.size());
            Iterator<?> r22 = r2.iterator();
        L5:
            if (r22.hasNext() == false) goto L7;
            r0.add(fromQueueItem(r22.next()));
            goto L5
        L7:
            return r0;
        L8:
            return null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public long getQueueId() {
            return this.mId;
        }

        public Object getQueueItem() {
            MediaSession.QueueItem r0 = this.mItemFwk;
            if (r0 != null) goto L6;
            MediaSession.QueueItem r02 = Api21Impl.createQueueItem((MediaDescription) this.mDescription.getMediaDescription(), this.mId);
            this.mItemFwk = r02;
            return r02;
        L6:
            return r0;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r3, int r4) {
            this.mDescription.writeToParcel(r3, r4);
            r3.writeLong(this.mId);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = null;
        ResultReceiver mResultReceiver;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<ResultReceiverWrapper> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ResultReceiverWrapper createFromParcel(Parcel r1) {
                return new ResultReceiverWrapper(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ ResultReceiverWrapper createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ResultReceiverWrapper[] newArray(int r1) {
                return new ResultReceiverWrapper[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ ResultReceiverWrapper[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public ResultReceiverWrapper(Parcel r2) {
            this.mResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(r2);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            this.mResultReceiver.writeToParcel(r1, r2);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = null;
        private IMediaSession mExtraBinder;
        private final Object mInner;
        private final Object mLock;
        private VersionedParcelable mSession2Token;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$Token$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<Token> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Token createFromParcel(Parcel r1) {
                return new Token(r1.readParcelable(null));
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Token createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Token[] newArray(int r1) {
                return new Token[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Token[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public Token(Object r2) {
            this(r2, null, null);
        }

        public Token(Object r2, IMediaSession r3) {
            this(r2, r3, null);
        }

        public Token(Object r2, IMediaSession r3, VersionedParcelable r4) {
            this.mLock = new Object();
            this.mInner = r2;
            this.mExtraBinder = r3;
            this.mSession2Token = r4;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object r4) {
            if (this != r4) goto L6;
            return true;
        L6:
            if ((r4 instanceof Token) == true) goto L8;
            return false;
        L8:
            Object r3 = this.mInner;
            Object r42 = ((Token) r4).mInner;
            if (r3 != null) goto L13;
            if (r42 != null) goto L12;
            return true;
        L12:
            return false;
        L13:
            if (r42 != null) goto L16;
            return false;
        L16:
            return r3.equals(r42);
        }

        public IMediaSession getExtraBinder() {
            Object r0 = this.mLock;
            monitor-enter(r0);
            IMediaSession r1 = this.mExtraBinder;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        public VersionedParcelable getSession2Token() {
            Object r0 = this.mLock;
            monitor-enter(r0);
            VersionedParcelable r1 = this.mSession2Token;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        public Object getToken() {
            return this.mInner;
        }

        public int hashCode() {
            Object r0 = this.mInner;
            if (r0 != null) goto L7;
            return 0;
        L7:
            return r0.hashCode();
        }

        public void setExtraBinder(IMediaSession r2) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mExtraBinder = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        public void setSession2Token(VersionedParcelable r2) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mSession2Token = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeParcelable((Parcelable) this.mInner, r2);
        }
    }

    private MediaSessionCompat(Context r2, MediaSessionImpl r3) {
        this.mActiveListeners = new ArrayList();
        this.mImpl = r3;
        this.mController = new MediaControllerCompat(r2, this);
    }

    public MediaSessionCompat(Context r2, String r3) {
        this(r2, r3, null, null);
    }

    public MediaSessionCompat(Context r7, String r8, ComponentName r9, PendingIntent r10) {
        this(r7, r8, r9, r10, null);
    }

    public MediaSessionCompat(Context r8, String r9, ComponentName r10, PendingIntent r11, Bundle r12) {
        this(r8, r9, r10, r11, r12, null);
    }

    public MediaSessionCompat(Context r3, String r4, ComponentName r5, PendingIntent r6, Bundle r7, VersionedParcelable r8) {
        this.mActiveListeners = new ArrayList();
        if (r3 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("context must not be null");
        throw null;
    L5:
        if (TextUtils.isEmpty(r4) == true) goto L32;
        if (r5 != null) goto L10;
        r5 = MediaButtonReceiver.getMediaButtonReceiverComponent(r3);
        if (r5 != null) goto L10;
        Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
    L10:
        if (r5 == null) goto L17;
        if (r6 != null) goto L17;
        Intent r62 = new Intent("android.intent.action.MEDIA_BUTTON");
        r62.setComponent(r5);
        if (Build.VERSION.SDK_INT < 31) goto L15;
        int r52 = 33554432;
    L16:
        r6 = PendingIntent.getBroadcast(r3, 0, r62, r52);
        goto L17
    L15:
        r52 = 0;
    L17:
        int r53 = Build.VERSION.SDK_INT;
        if (r53 < 29) goto L21;
        this.mImpl = new MediaSessionImplApi29(r3, r4, r8, r7);
    L25:
        if (Looper.myLooper() == null) goto L27;
        Looper r54 = Looper.myLooper();
    L28:
        setCallback(new AnonymousClass1(this), new Handler(r54));
        this.mImpl.setMediaButtonReceiver(r6);
        this.mController = new MediaControllerCompat(r3, this);
        if (sMaxBitmapSize != 0) goto L36;
        sMaxBitmapSize = (int) (TypedValue.applyDimension(1, 320.0f, r3.getResources().getDisplayMetrics()) + 0.5f);
        return;
    L36:
        return;
    L27:
        r54 = Looper.getMainLooper();
        goto L28
    L21:
        if (r53 < 28) goto L23;
        this.mImpl = new MediaSessionImplApi28(r3, r4, r8, r7);
        goto L25
    L23:
        this.mImpl = new MediaSessionImplApi22(r3, r4, r8, r7);
        goto L25
    L32:
        f$$ExternalSyntheticBUOutline1.m("tag must not be null or empty");
        throw null;
    }

    public static void ensureClassLoader(Bundle r1) {
        if (r1 == null) goto L5;
        r1.setClassLoader(MediaSessionCompat.class.getClassLoader());
        return;
    }

    public static MediaSessionCompat fromMediaSession(Context r2, Object r3) {
        if (r2 == null) goto L14;
        if (r3 == null) goto L16;
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 29) goto L9;
        MediaSessionImpl r02 = new MediaSessionImplApi29(r3);
    L13:
        return new MediaSessionCompat(r2, r02);
    L9:
        if (r0 < 28) goto L11;
        r02 = new MediaSessionImplApi28(r3);
        goto L13
    L11:
        r02 = new MediaSessionImplApi21(r3);
        goto L13
    L16:
        return null;
    L14:
        return null;
    }

    public static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat r14, MediaMetadataCompat r15) {
        if (r14 == null) goto L30;
        long r2 = -1;
        if (r14.getPosition() != (-1)) goto L7;
        return r14;
    L7:
        if (r14.getState() == 3) goto L13;
        if (r14.getState() == 4) goto L13;
        if (r14.getState() == 5) goto L13;
        return r14;
    L13:
        if (r14.getLastPositionUpdateTime() <= 0) goto L32;
        long r12 = SystemClock.elapsedRealtime();
        long r0 = ((long) (r14.getPlaybackSpeed() * (r12 - r0))) + r14.getPosition();
        if (r15 == null) goto L20;
        if (r15.containsKey(MediaItemMetadata.KEY_DURATION) == false) goto L20;
        r2 = r15.getLong(MediaItemMetadata.KEY_DURATION);
    L20:
        if (r2 < 0) goto L25;
        if (r0 <= r2) goto L25;
        long r9 = r2;
    L29:
        return new PlaybackStateCompat.Builder(r14).setState(r14.getState(), r9, r14.getPlaybackSpeed(), r12).build();
    L25:
        if (r0 >= 0) goto L27;
        r9 = 0;
        goto L29
    L27:
        r9 = r0;
        goto L29
    L32:
        return r14;
    L30:
        return r14;
    }

    public static Bundle unparcelWithClassLoader(Bundle r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        ensureClassLoader(r2);
        r2.isEmpty();     // Catch: BadParcelableException -> L8
        return r2;
    L8:
        Log.e("MediaSessionCompat", "Could not unparcel the data.");
        return null;
    }

    public MediaControllerCompat getController() {
        return this.mController;
    }

    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public boolean isActive() {
        return this.mImpl.isActive();
    }

    public void release() {
        this.mImpl.release();
    }

    public void setActive(boolean r2) {
        this.mImpl.setActive(r2);
        Iterator<Object> r1 = this.mActiveListeners.iterator();
        if (r1.hasNext() == true) goto L5;
        return;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    }

    public void setCallback(Callback r2) {
        setCallback(r2, null);
    }

    public void setCallback(Callback r1, Handler r2) {
        MediaSessionImpl r0 = this.mImpl;
        if (r1 != null) goto L6;
        r0.setCallback(null, null);
        return;
    L6:
        if (r2 != null) goto L9;
        r2 = new Handler();
    L9:
        r0.setCallback(r1, r2);
    }

    public void setExtras(Bundle r1) {
        this.mImpl.setExtras(r1);
    }

    public void setFlags(int r1) {
        this.mImpl.setFlags(r1);
    }

    public void setMetadata(MediaMetadataCompat r1) {
        this.mImpl.setMetadata(r1);
    }

    public void setPlaybackState(PlaybackStateCompat r1) {
        this.mImpl.setPlaybackState(r1);
    }

    public void setPlaybackToLocal(int r1) {
        this.mImpl.setPlaybackToLocal(r1);
    }

    public void setPlaybackToRemote(VolumeProviderCompat r1) {
        if (r1 == null) goto L5;
        this.mImpl.setPlaybackToRemote(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("volumeProvider may not be null!");
    }

    public void setQueue(List<QueueItem> r7) {
        if (r7 == null) goto L14;
        HashSet r0 = new HashSet();
        Iterator<QueueItem> r1 = r7.iterator();
    L5:
        if (r1.hasNext() == false) goto L14;
        QueueItem r2 = r1.next();
        if (r2 == null) goto L12;
        if (r0.contains(Long.valueOf(r2.getQueueId())) == false) goto L11;
        Log.e("MediaSessionCompat", "Found duplicate queue id: " + r2.getQueueId(), new IllegalArgumentException("id of each queue item should be unique"));
    L11:
        r0.add(Long.valueOf(r2.getQueueId()));
        goto L5
    L12:
        f$$ExternalSyntheticBUOutline1.m("queue shouldn't have null items");
        return;
    L14:
        this.mImpl.setQueue(r7);
    }

    public void setQueueTitle(CharSequence r1) {
        this.mImpl.setQueueTitle(r1);
    }

    public void setRepeatMode(int r1) {
        this.mImpl.setRepeatMode(r1);
    }

    public void setSessionActivity(PendingIntent r1) {
        this.mImpl.setSessionActivity(r1);
    }

    public void setShuffleMode(int r1) {
        this.mImpl.setShuffleMode(r1);
    }
}
