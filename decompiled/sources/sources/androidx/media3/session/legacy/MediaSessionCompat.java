package androidx.media3.session.legacy;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
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
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi29$$ExternalSyntheticApiModelOutline0;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.media3.common.util.Assertions;
import androidx.media3.session.legacy.IMediaSession;
import androidx.media3.session.legacy.MediaSessionManager;
import androidx.media3.session.legacy.PlaybackStateCompat;
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

/* loaded from: classes4.dex */
public class MediaSessionCompat {
    private final MediaControllerCompat controller;
    private final MediaSessionImpl impl;

    /* renamed from: androidx.media3.session.legacy.MediaSessionCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends Callback {
        final /* synthetic */ MediaSessionCompat this$0;

        public AnonymousClass1(MediaSessionCompat r1) {
            this.this$0 = r1;
        }
    }

    public static abstract class Callback {
        final MediaSession.Callback callbackFwk;
        CallbackHandler callbackHandler;
        final Object lock;
        private boolean mediaPlayPausePendingOnHandler;
        WeakReference<MediaSessionImpl> sessionImpl;

        public class CallbackHandler extends Handler {
            final /* synthetic */ Callback this$0;

            public CallbackHandler(Callback r1, Looper r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.os.Handler
            public void handleMessage(Message r5) {
                if (r5.what != 1) goto L18;
                Object r0 = this.this$0.lock;
                monitor-enter(r0);
                MediaSessionImpl r1 = this.this$0.sessionImpl.get();     // Catch: Throwable -> L15
                Callback r2 = this.this$0;     // Catch: Throwable -> L15
                CallbackHandler r3 = r2.callbackHandler;     // Catch: Throwable -> L15
                monitor-exit(r0);     // Catch: Throwable -> L15
                if (r1 != null) goto L10;
                return;
            L10:
                if (r2 != r1.getCallback()) goto L22;
                if (r3 == null) goto L23;
                r1.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo) r5.obj);
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
                Object r0 = this.this$0.lock;
                monitor-enter(r0);
                MediaSessionImplApi21 r1 = (MediaSessionImplApi21) this.this$0.sessionImpl.get();     // Catch: Throwable -> L12
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
                r3.setCurrentControllerInfo(new MediaSessionManager.RemoteUserInfo(r2, -1, -1));
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
                if (r6.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER") == false) goto L16;
                if (r8 == null) goto L43;
                Bundle r62 = new Bundle();     // Catch: BadParcelableException -> L42
                Token r72 = r0.getSessionToken();     // Catch: BadParcelableException -> L42
                IMediaSession r1 = r72.getExtraBinder();     // Catch: BadParcelableException -> L42
                if (r1 == null) goto L13;
                r22 = r1.asBinder();     // Catch: BadParcelableException -> L42
            L13:
                r62.putBinder("android.support.v4.media.session.EXTRA_BINDER", r22);     // Catch: BadParcelableException -> L42
                ParcelUtils.putVersionedParcelable(r62, "android.support.v4.media.session.SESSION_TOKEN2", r72.getSession2Token());     // Catch: BadParcelableException -> L42
                r8.send(0, r62);     // Catch: BadParcelableException -> L42
            L43:
                clearCurrentControllerInfo(r0);
                return;
            L16:
                if (r6.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM") == false) goto L21;
                if (r7 == null) goto L43;
                this.this$0.onAddQueueItem((MediaDescriptionCompat) LegacyParcelableUtil.convert(r7.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), MediaDescriptionCompat.CREATOR));     // Catch: BadParcelableException -> L42
                goto L43
            L21:
                if (r6.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT") == false) goto L25;
                if (r7 == null) goto L43;
                this.this$0.onAddQueueItem((MediaDescriptionCompat) LegacyParcelableUtil.convert(r7.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), MediaDescriptionCompat.CREATOR), r7.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));     // Catch: BadParcelableException -> L42
                goto L43
            L25:
                if (r6.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM") == false) goto L29;
                if (r7 == null) goto L43;
                this.this$0.onRemoveQueueItem((MediaDescriptionCompat) LegacyParcelableUtil.convert(r7.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), MediaDescriptionCompat.CREATOR));     // Catch: BadParcelableException -> L42
                goto L43
            L29:
                if (r6.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT") == false) goto L40;
                List<QueueItem> r63 = r0.queue;     // Catch: BadParcelableException -> L42
                if (r63 == null) goto L43;
                if (r7 == null) goto L43;
                int r73 = r7.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);     // Catch: BadParcelableException -> L42
                if (r73 >= 0) goto L36;
            L38:
                if (r2 == null) goto L43;
                this.this$0.onRemoveQueueItem(r2.getDescription());     // Catch: BadParcelableException -> L42
                goto L43
            L36:
                if (r73 >= r63.size()) goto L38;
                r2 = r63.get(r73);     // Catch: BadParcelableException -> L42
                goto L38
            L40:
                this.this$0.onCommand(r6, r7, r8);     // Catch: BadParcelableException -> L42
            L42:
                Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                goto L43
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCustomAction(String r5, Bundle r6) {
                MediaSessionImplApi21 r0 = getSessionImplIfCallbackIsSet();
                if (r0 != null) goto L5;
                return;
            L5:
                MediaSessionCompat.ensureClassLoader(r6);
                setCurrentControllerInfo(r0);
            L48:
                Log.e("MediaSessionCompat", "Could not unparcel the data.");
            L49:
                clearCurrentControllerInfo(r0);
                return;
            L8:
                if (r5.equals("android.support.v4.media.session.action.PLAY_FROM_URI") == false) goto L12;
                if (r6 == null) goto L51;
                Uri r52 = (Uri) r6.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");     // Catch: BadParcelableException -> L48
                Bundle r62 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L48
                MediaSessionCompat.ensureClassLoader(r62);     // Catch: BadParcelableException -> L48
                this.this$0.onPlayFromUri(r52, r62);     // Catch: BadParcelableException -> L48
            L51:
                goto L49
            L12:
                if (r5.equals("android.support.v4.media.session.action.PREPARE") == false) goto L15;
                this.this$0.onPrepare();     // Catch: BadParcelableException -> L48
                goto L51
            L15:
                if (r5.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID") == false) goto L19;
                if (r6 == null) goto L51;
                String r53 = r6.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");     // Catch: BadParcelableException -> L48
                Bundle r63 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L48
                MediaSessionCompat.ensureClassLoader(r63);     // Catch: BadParcelableException -> L48
                this.this$0.onPrepareFromMediaId(r53, r63);     // Catch: BadParcelableException -> L48
                goto L51
            L19:
                if (r5.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH") == false) goto L23;
                if (r6 == null) goto L51;
                String r54 = r6.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");     // Catch: BadParcelableException -> L48
                Bundle r64 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L48
                MediaSessionCompat.ensureClassLoader(r64);     // Catch: BadParcelableException -> L48
                this.this$0.onPrepareFromSearch(r54, r64);     // Catch: BadParcelableException -> L48
                goto L51
            L23:
                if (r5.equals("android.support.v4.media.session.action.PREPARE_FROM_URI") == false) goto L27;
                if (r6 == null) goto L51;
                Uri r55 = (Uri) r6.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");     // Catch: BadParcelableException -> L48
                Bundle r65 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L48
                MediaSessionCompat.ensureClassLoader(r65);     // Catch: BadParcelableException -> L48
                this.this$0.onPrepareFromUri(r55, r65);     // Catch: BadParcelableException -> L48
                goto L51
            L27:
                if (r5.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED") == false) goto L31;
                if (r6 == null) goto L51;
                boolean r56 = r6.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");     // Catch: BadParcelableException -> L48
                this.this$0.onSetCaptioningEnabled(r56);     // Catch: BadParcelableException -> L48
                goto L51
            L31:
                if (r5.equals("android.support.v4.media.session.action.SET_REPEAT_MODE") == false) goto L35;
                if (r6 == null) goto L51;
                int r57 = r6.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");     // Catch: BadParcelableException -> L48
                this.this$0.onSetRepeatMode(r57);     // Catch: BadParcelableException -> L48
                goto L51
            L35:
                if (r5.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE") == false) goto L39;
                if (r6 == null) goto L51;
                int r58 = r6.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");     // Catch: BadParcelableException -> L48
                this.this$0.onSetShuffleMode(r58);     // Catch: BadParcelableException -> L48
                goto L51
            L39:
                if (r5.equals("android.support.v4.media.session.action.SET_RATING") == false) goto L43;
                if (r6 == null) goto L51;
                RatingCompat r59 = (RatingCompat) LegacyParcelableUtil.convert(r6.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING"), RatingCompat.CREATOR);     // Catch: BadParcelableException -> L48
                Bundle r66 = r6.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");     // Catch: BadParcelableException -> L48
                MediaSessionCompat.ensureClassLoader(r66);     // Catch: BadParcelableException -> L48
                this.this$0.onSetRating(r59, r66);     // Catch: BadParcelableException -> L48
                goto L51
            L43:
                if (r5.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED") == false) goto L46;
                if (r6 == null) goto L51;
                float r510 = r6.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f);     // Catch: BadParcelableException -> L48
                this.this$0.onSetPlaybackSpeed(r510);     // Catch: BadParcelableException -> L48
                goto L51
            L46:
                this.this$0.onCustomAction(r5, r6);     // Catch: BadParcelableException -> L48
                goto L51
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
            this.lock = new Object();
            this.callbackFwk = new MediaSessionCallbackApi21(this);
            this.sessionImpl = new WeakReference(null);
        }

        public void handleMediaPlayPauseIfPendingOnHandler(MediaSessionImpl r9, Handler r10) {
            if (this.mediaPlayPausePendingOnHandler == false) goto L31;
            boolean r0 = false;
            this.mediaPlayPausePendingOnHandler = false;
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
            Object r0 = this.lock;
            monitor-enter(r0);
            MediaSessionImpl r1 = this.sessionImpl.get();     // Catch: Throwable -> L38
            CallbackHandler r3 = this.callbackHandler;     // Catch: Throwable -> L38
            monitor-exit(r0);     // Catch: Throwable -> L38
            if (r1 == null) goto L37;
            if (r3 == null) goto L37;
            KeyEvent r82 = (KeyEvent) r8.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (r82 == null) goto L37;
            if (r82.getAction() != 0) goto L37;
            MediaSessionManager.RemoteUserInfo r02 = r1.getCurrentControllerInfo();
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
            if (this.mediaPlayPausePendingOnHandler == false) goto L34;
            r3.removeMessages(1);
            this.mediaPlayPausePendingOnHandler = false;
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
            this.mediaPlayPausePendingOnHandler = true;
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

        public void setSessionImpl(MediaSessionImpl r3, Handler r4) {
            Object r0 = this.lock;
            monitor-enter(r0);
            this.sessionImpl = new WeakReference(r3);     // Catch: Throwable -> L7
            CallbackHandler r32 = this.callbackHandler;     // Catch: Throwable -> L7
            CallbackHandler r1 = null;
            if (r32 == null) goto L9;
            r32.removeCallbacksAndMessages(null);     // Catch: Throwable -> L7
        L9:
            if (r4 == null) goto L12;
            r1 = new CallbackHandler(this, r4.getLooper());     // Catch: Throwable -> L7
        L12:
            this.callbackHandler = r1;     // Catch: Throwable -> L7
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

        MediaSessionManager.RemoteUserInfo getCurrentControllerInfo();

        Object getMediaSession();

        PlaybackStateCompat getPlaybackState();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String r1, Bundle r2);

        void setActive(boolean r1);

        void setCallback(Callback r1, Handler r2);

        void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo r1);

        void setExtras(Bundle r1);

        void setFlags(int r1);

        void setMediaButtonReceiver(PendingIntent r1);

        void setMetadata(MediaMetadataCompat r1);

        void setPlaybackState(PlaybackStateCompat r1);

        void setPlaybackToLocal(int r1);

        void setPlaybackToRemote(VolumeProviderCompat r1);

        void setQueue(List<QueueItem> r1);

        void setQueueTitle(CharSequence r1);

        void setRatingType(int r1);

        void setRepeatMode(int r1);

        void setSessionActivity(PendingIntent r1);

        void setShuffleMode(int r1);
    }

    public static class MediaSessionImplApi21 implements MediaSessionImpl {
        Callback callback;
        boolean captioningEnabled;
        boolean destroyed;
        final RemoteCallbackList<IMediaControllerCallback> extraControllerCallbacks;
        final ExtraSession extraSession;
        final Object lock;
        MediaMetadataCompat metadata;
        PlaybackStateCompat playbackState;
        List<QueueItem> queue;
        int ratingType;
        MediaSessionManager.RemoteUserInfo remoteUserInfo;
        int repeatMode;
        final MediaSession sessionFwk;
        Bundle sessionInfo;
        int shuffleMode;
        final Token token;

        public static class ExtraSession extends IMediaSession.Stub {
            private final WeakReference<MediaSessionImplApi21> mediaSessionImplRef;

            public ExtraSession(MediaSessionImplApi21 r2) {
                this.mediaSessionImplRef = new WeakReference(r2);
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void addQueueItem(MediaDescriptionCompat r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void addQueueItemAt(MediaDescriptionCompat r1, int r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void adjustVolume(int r1, int r2, String r3) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void fastForward() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public Bundle getExtras() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public long getFlags() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public String getPackageName() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                MediaSessionImplApi21 r1 = this.mediaSessionImplRef.get();
                if (r1 != null) goto L5;
                return null;
            L5:
                return MediaSessionCompat.getStateWithUpdatedPosition(r1.playbackState, r1.metadata);
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public List<QueueItem> getQueue() {
                return null;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getRatingType() {
                MediaSessionImplApi21 r0 = this.mediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return 0;
            L5:
                return r0.ratingType;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getRepeatMode() {
                MediaSessionImplApi21 r0 = this.mediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return -1;
            L5:
                return r0.repeatMode;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public Bundle getSessionInfo() {
                MediaSessionImplApi21 r1 = this.mediaSessionImplRef.get();
                if (r1 != null) goto L5;
                return null;
            L5:
                if (r1.sessionInfo != null) goto L7;
                return null;
            L7:
                return new Bundle(r1.sessionInfo);
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getShuffleMode() {
                MediaSessionImplApi21 r0 = this.mediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return -1;
            L5:
                return r0.shuffleMode;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public String getTag() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isCaptioningEnabled() {
                MediaSessionImplApi21 r0 = this.mediaSessionImplRef.get();
                if (r0 != null) goto L5;
                return false;
            L5:
                if (r0.captioningEnabled == false) goto L10;
                return true;
            L10:
                return false;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void next() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void pause() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void play() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void playFromMediaId(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void playFromSearch(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void playFromUri(Uri r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepare() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepareFromMediaId(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepareFromSearch(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void prepareFromUri(Uri r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void previous() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void rate(RatingCompat r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void rateWithExtras(RatingCompat r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback r5) {
                MediaSessionImplApi21 r4 = this.mediaSessionImplRef.get();
                if (r4 == null) goto L13;
                if (r5 == null) goto L16;
                MediaSessionManager.RemoteUserInfo r2 = new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid());
                r4.extraControllerCallbacks.register(r5, r2);
                Object r42 = r4.lock;
                monitor-enter(r42);
                monitor-exit(r42);     // Catch: Throwable -> L10
                return;
            L10:
                th = move-exception;
                throw th;
            L16:
                return;
            }

            public void release() {
                this.mediaSessionImplRef.clear();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void removeQueueItem(MediaDescriptionCompat r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void removeQueueItemAt(int r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void rewind() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void seekTo(long r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void sendCommand(String r1, Bundle r2, ResultReceiverWrapper r3) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void sendCustomAction(String r1, Bundle r2) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean sendMediaButton(KeyEvent r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setCaptioningEnabled(boolean r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setPlaybackSpeed(float r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setRepeatMode(int r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setShuffleMode(int r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setShuffleModeEnabledRemoved(boolean r1) {
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void setVolumeTo(int r1, int r2, String r3) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void skipToQueueItem(long r1) {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void stop() {
                throw new AssertionError();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback r2) {
                MediaSessionImplApi21 r1 = this.mediaSessionImplRef.get();
                if (r1 == null) goto L13;
                if (r2 == null) goto L16;
                r1.extraControllerCallbacks.unregister(r2);
                Binder.getCallingPid();
                Binder.getCallingUid();
                Object r12 = r1.lock;
                monitor-enter(r12);
                monitor-exit(r12);     // Catch: Throwable -> L10
                return;
            L10:
                th = move-exception;
                throw th;
            L16:
                return;
            }
        }

        public MediaSessionImplApi21(Context r2, String r3, Bundle r4) {
            this.lock = new Object();
            this.destroyed = false;
            this.extraControllerCallbacks = new RemoteCallbackList();
            MediaSession r22 = createFwkMediaSession(r2, r3, r4);
            this.sessionFwk = r22;
            ExtraSession r32 = new ExtraSession(this);
            this.extraSession = r32;
            this.token = new Token(r22.getSessionToken(), r32);
            this.sessionInfo = r4;
            setFlags(3);
        }

        public MediaSession createFwkMediaSession(Context r1, String r2, Bundle r3) {
            return new MediaSession(r1, r2);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public Callback getCallback() {
            Object r0 = this.lock;
            monitor-enter(r0);
            Callback r1 = this.callback;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public String getCallingPackage() {
            return (String) this.sessionFwk.getClass().getMethod("getCallingPackage", null).invoke(this.sessionFwk, null);
        L5:
            e = move-exception;
            Log.e("MediaSessionCompat", "Cannot execute MediaSession.getCallingPackage()", e);
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            Object r0 = this.lock;
            monitor-enter(r0);
            MediaSessionManager.RemoteUserInfo r1 = this.remoteUserInfo;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public Object getMediaSession() {
            return this.sessionFwk;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public PlaybackStateCompat getPlaybackState() {
            return this.playbackState;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public Token getSessionToken() {
            return this.token;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public boolean isActive() {
            return this.sessionFwk.isActive();
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void release() {
            this.destroyed = true;
            this.extraControllerCallbacks.kill();
            if (Build.VERSION.SDK_INT == 27) goto L12;
        L10:
            this.sessionFwk.setCallback(null);
            this.extraSession.release();
            this.sessionFwk.release();
            return;
        L12:
            Field r1 = this.sessionFwk.getClass().getDeclaredField("mCallback");     // Catch: Exception -> L8
            r1.setAccessible(true);     // Catch: Exception -> L8
            Handler r0 = (Handler) r1.get(this.sessionFwk);     // Catch: Exception -> L8
            if (r0 == null) goto L10;
            r0.removeCallbacksAndMessages(null);     // Catch: Exception -> L8
        L8:
            e = move-exception;
            Log.w("MediaSessionCompat", "Exception happened while accessing MediaSession.mCallback.", e);
            goto L10
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void sendSessionEvent(String r1, Bundle r2) {
            this.sessionFwk.sendSessionEvent(r1, r2);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setActive(boolean r1) {
            this.sessionFwk.setActive(r1);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setCallback(Callback r4, Handler r5) {
            Object r0 = this.lock;
            monitor-enter(r0);
            this.callback = r4;     // Catch: Throwable -> L11
            MediaSession r1 = this.sessionFwk;     // Catch: Throwable -> L11
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
            r2 = r4.callbackFwk;     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            throw th;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo r2) {
            Object r0 = this.lock;
            monitor-enter(r0);
            this.remoteUserInfo = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setExtras(Bundle r1) {
            this.sessionFwk.setExtras(r1);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        @SuppressLint({"WrongConstant"})
        public void setFlags(int r1) {
            this.sessionFwk.setFlags(r1 | 3);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setMediaButtonReceiver(PendingIntent r1) {
            this.sessionFwk.setMediaButtonReceiver(r1);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setMetadata(MediaMetadataCompat r1) {
            this.metadata = r1;
            MediaSession r0 = this.sessionFwk;
            if (r1 != null) goto L5;
            MediaMetadata r12 = null;
        L6:
            r0.setMetadata(r12);
            return;
        L5:
            r12 = r1.getMediaMetadata();
            goto L6
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackState(PlaybackStateCompat r6) {
            this.playbackState = r6;
            Object r0 = this.lock;
            monitor-enter(r0);
        L11:
            th = move-exception;
            throw th;
        L5:
            int r1 = this.extraControllerCallbacks.beginBroadcast() - 1;
        L6:
            RemoteCallbackList<IMediaControllerCallback> r2 = this.extraControllerCallbacks;
            if (r1 < 0) goto L18;
            ((IMediaControllerCallback) r2.getBroadcastItem(r1)).onPlaybackStateChanged(r6);     // Catch: Throwable -> L11 SecurityException -> L13 Throwable -> L15
        L17:
            r1 = r1 - 1;
        L15:
            e = move-exception;
            Log.e("MediaSessionCompat", "Dead object in setPlaybackState.", e);     // Catch: Throwable -> L11
            goto L17
        L18:
            r2.finishBroadcast();     // Catch: Throwable -> L11
            monitor-exit(r0);     // Catch: Throwable -> L11
            this.sessionFwk.setPlaybackState(r6.getPlaybackState());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToLocal(int r2) {
            AudioAttributes.Builder r0 = new AudioAttributes.Builder();
            r0.setLegacyStreamType(r2);
            this.sessionFwk.setPlaybackToLocal(r0.build());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToRemote(VolumeProviderCompat r1) {
            this.sessionFwk.setPlaybackToRemote((VolumeProvider) r1.getVolumeProvider());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setQueue(List<QueueItem> r3) {
            this.queue = r3;
            if (r3 != null) goto L6;
            this.sessionFwk.setQueue(null);
            return;
        L6:
            ArrayList r0 = new ArrayList(r3.size());
            Iterator<QueueItem> r32 = r3.iterator();
        L8:
            if (r32.hasNext() == false) goto L10;
            r0.add(r32.next().getQueueItem());
            goto L8
        L10:
            this.sessionFwk.setQueue(r0);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setQueueTitle(CharSequence r1) {
            this.sessionFwk.setQueueTitle(r1);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setRepeatMode(int r6) {
            if (this.repeatMode == r6) goto L25;
            this.repeatMode = r6;
            Object r0 = this.lock;
            monitor-enter(r0);
        L13:
            th = move-exception;
            throw th;
        L7:
            int r1 = this.extraControllerCallbacks.beginBroadcast() - 1;
        L8:
            RemoteCallbackList<IMediaControllerCallback> r2 = this.extraControllerCallbacks;
            if (r1 < 0) goto L20;
            ((IMediaControllerCallback) r2.getBroadcastItem(r1)).onRepeatModeChanged(r6);     // Catch: Throwable -> L13 Throwable -> L15 RemoteException -> L17
        L19:
            r1 = r1 - 1;
        L15:
            e = move-exception;
            Log.e("MediaSessionCompat", "Dead object in setRepeatMode.", e);     // Catch: Throwable -> L13
            goto L19
        L20:
            r2.finishBroadcast();     // Catch: Throwable -> L13
            monitor-exit(r0);     // Catch: Throwable -> L13
            return;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setSessionActivity(PendingIntent r1) {
            this.sessionFwk.setSessionActivity(r1);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setShuffleMode(int r6) {
            if (this.shuffleMode == r6) goto L25;
            this.shuffleMode = r6;
            Object r0 = this.lock;
            monitor-enter(r0);
        L13:
            th = move-exception;
            throw th;
        L7:
            int r1 = this.extraControllerCallbacks.beginBroadcast() - 1;
        L8:
            RemoteCallbackList<IMediaControllerCallback> r2 = this.extraControllerCallbacks;
            if (r1 < 0) goto L20;
            ((IMediaControllerCallback) r2.getBroadcastItem(r1)).onShuffleModeChanged(r6);     // Catch: Throwable -> L13 Throwable -> L15 RemoteException -> L17
        L19:
            r1 = r1 - 1;
        L15:
            e = move-exception;
            Log.e("MediaSessionCompat", "Dead object in setShuffleMode.", e);     // Catch: Throwable -> L13
            goto L19
        L20:
            r2.finishBroadcast();     // Catch: Throwable -> L13
            monitor-exit(r0);     // Catch: Throwable -> L13
            return;
        }
    }

    public static class MediaSessionImplApi22 extends MediaSessionImplApi21 {
        public MediaSessionImplApi22(Context r1, String r2, Bundle r3) {
            super(r1, r2, r3);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setRatingType(int r1) {
            this.sessionFwk.setRatingType(r1);
        }
    }

    public static class MediaSessionImplApi28 extends MediaSessionImplApi22 {
        public MediaSessionImplApi28(Context r1, String r2, Bundle r3) {
            super(r1, r2, r3);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImplApi21, androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            return new MediaSessionManager.RemoteUserInfo(this.sessionFwk.getCurrentControllerInfo());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImplApi21, androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo r1) {
        }
    }

    public static class MediaSessionImplApi29 extends MediaSessionImplApi28 {
        public MediaSessionImplApi29(Context r1, String r2, Bundle r3) {
            super(r1, r2, r3);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImplApi21
        public MediaSession createFwkMediaSession(Context r1, String r2, Bundle r3) {
            return MediaSessionCompat$MediaSessionImplApi29$$ExternalSyntheticApiModelOutline0.m(r1, r2, r3);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = null;
        private final MediaDescriptionCompat description;
        private final long id;
        private MediaSession.QueueItem itemFwk;

        /* renamed from: androidx.media3.session.legacy.MediaSessionCompat$QueueItem$1, reason: invalid class name */
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

        static {
            CREATOR = new AnonymousClass1();
        }

        private QueueItem(MediaSession.QueueItem r3, MediaDescriptionCompat r4, long r5) {
            if (r5 == (-1)) goto L6;
            this.description = r4;
            this.id = r5;
            this.itemFwk = r3;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Id cannot be QueueItem.UNKNOWN_ID");
            throw null;
        }

        public QueueItem(Parcel r3) {
            this.description = MediaDescriptionCompat.CREATOR.createFromParcel(r3);
            this.id = r3.readLong();
        }

        public QueueItem(MediaDescriptionCompat r2, long r3) {
            this(null, r2, r3);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.description;
        }

        public long getQueueId() {
            return this.id;
        }

        public MediaSession.QueueItem getQueueItem() {
            MediaSession.QueueItem r0 = this.itemFwk;
            if (r0 == null) goto L5;
            return r0;
        L5:
            MediaSession.QueueItem r1 = new MediaSession.QueueItem(this.description.getMediaDescription(), this.id);
            this.itemFwk = r1;
            return r1;
        }

        public String toString() {
            return "MediaSession.QueueItem { Description=" + this.description + ", Id=" + this.id + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r3, int r4) {
            this.description.writeToParcel(r3, r4);
            r3.writeLong(this.id);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = null;
        ResultReceiver resultReceiver;

        /* renamed from: androidx.media3.session.legacy.MediaSessionCompat$ResultReceiverWrapper$1, reason: invalid class name */
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
            this.resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(r2);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            this.resultReceiver.writeToParcel(r1, r2);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = null;
        private IMediaSession extraBinder;
        private final MediaSession.Token inner;
        private final Object lock;
        private VersionedParcelable session2Token;

        /* renamed from: androidx.media3.session.legacy.MediaSessionCompat$Token$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<Token> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Token createFromParcel(Parcel r1) {
                return new Token((MediaSession.Token) Assertions.checkNotNull((MediaSession.Token) r1.readParcelable(null)));
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

        public Token(MediaSession.Token r2) {
            this(r2, null);
        }

        public Token(MediaSession.Token r2, IMediaSession r3) {
            this(r2, r3, null);
        }

        public Token(MediaSession.Token r2, IMediaSession r3, VersionedParcelable r4) {
            this.lock = new Object();
            this.inner = r2;
            this.extraBinder = r3;
            this.session2Token = r4;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object r2) {
            if (this != r2) goto L6;
            return true;
        L6:
            if ((r2 instanceof Token) == true) goto L10;
            return false;
        L10:
            return this.inner.equals(((Token) r2).inner);
        }

        public IMediaSession getExtraBinder() {
            Object r0 = this.lock;
            monitor-enter(r0);
            IMediaSession r1 = this.extraBinder;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        public VersionedParcelable getSession2Token() {
            Object r0 = this.lock;
            monitor-enter(r0);
            VersionedParcelable r1 = this.session2Token;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }

        public MediaSession.Token getToken() {
            return this.inner;
        }

        public int hashCode() {
            return this.inner.hashCode();
        }

        public void setExtraBinder(IMediaSession r2) {
            Object r0 = this.lock;
            monitor-enter(r0);
            this.extraBinder = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        public void setSession2Token(VersionedParcelable r2) {
            Object r0 = this.lock;
            monitor-enter(r0);
            this.session2Token = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeParcelable(this.inner, r2);
        }
    }

    public MediaSessionCompat(Context r3, String r4, ComponentName r5, PendingIntent r6, Bundle r7) {
        if (TextUtils.isEmpty(r4) == true) goto L28;
        if (r5 != null) goto L8;
        r5 = MediaButtonReceiver.getMediaButtonReceiverComponent(r3);
        if (r5 != null) goto L8;
        Log.i("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
    L8:
        if (r5 == null) goto L15;
        if (r6 != null) goto L15;
        Intent r62 = new Intent("android.intent.action.MEDIA_BUTTON");
        r62.setComponent(r5);
        if (Build.VERSION.SDK_INT < 31) goto L13;
        int r52 = 33554432;
    L14:
        r6 = PendingIntent.getBroadcast(r3, 0, r62, r52);
        goto L15
    L13:
        r52 = 0;
    L15:
        int r53 = Build.VERSION.SDK_INT;
        if (r53 < 29) goto L19;
        this.impl = new MediaSessionImplApi29(r3, r4, r7);
    L22:
        Looper r42 = Looper.myLooper();
        if (r42 != null) goto L26;
        r42 = Looper.getMainLooper();
    L26:
        setCallback(new AnonymousClass1(this), new Handler(r42));
        this.impl.setMediaButtonReceiver(r6);
        this.controller = new MediaControllerCompat(r3, this);
        return;
    L19:
        if (r53 < 28) goto L21;
        this.impl = new MediaSessionImplApi28(r3, r4, r7);
        goto L22
    L21:
        this.impl = new MediaSessionImplApi22(r3, r4, r7);
        goto L22
    L28:
        f$$ExternalSyntheticBUOutline1.m("tag must not be null or empty");
        throw null;
    }

    public static void ensureClassLoader(Bundle r1) {
        if (r1 == null) goto L5;
        r1.setClassLoader((ClassLoader) Assertions.checkNotNull(MediaSessionCompat.class.getClassLoader()));
        return;
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
        return this.controller;
    }

    public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
        return this.impl.getCurrentControllerInfo();
    }

    public Object getMediaSession() {
        return this.impl.getMediaSession();
    }

    public Token getSessionToken() {
        return this.impl.getSessionToken();
    }

    public boolean isActive() {
        return this.impl.isActive();
    }

    public void release() {
        this.impl.release();
    }

    public void sendSessionEvent(String r2, Bundle r3) {
        if (TextUtils.isEmpty(r2) == true) goto L6;
        this.impl.sendSessionEvent(r2, r3);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("event cannot be null or empty");
    }

    public void setActive(boolean r1) {
        this.impl.setActive(r1);
    }

    public void setCallback(Callback r1, Handler r2) {
        this.impl.setCallback(r1, r2);
    }

    public void setExtras(Bundle r1) {
        this.impl.setExtras(r1);
    }

    public void setFlags(int r1) {
        this.impl.setFlags(r1);
    }

    public void setMediaButtonReceiver(PendingIntent r1) {
        this.impl.setMediaButtonReceiver(r1);
    }

    public void setMetadata(MediaMetadataCompat r1) {
        this.impl.setMetadata(r1);
    }

    public void setPlaybackState(PlaybackStateCompat r1) {
        this.impl.setPlaybackState(r1);
    }

    public void setPlaybackToLocal(int r1) {
        this.impl.setPlaybackToLocal(r1);
    }

    public void setPlaybackToRemote(VolumeProviderCompat r1) {
        this.impl.setPlaybackToRemote(r1);
    }

    public void setQueue(List<QueueItem> r7) {
        if (r7 == null) goto L10;
        HashSet r0 = new HashSet();
        Iterator<QueueItem> r1 = r7.iterator();
    L5:
        if (r1.hasNext() == false) goto L10;
        QueueItem r2 = r1.next();
        if (r0.contains(Long.valueOf(r2.getQueueId())) == false) goto L9;
        Log.e("MediaSessionCompat", "Found duplicate queue id: " + r2.getQueueId(), new IllegalArgumentException("id of each queue item should be unique"));
    L9:
        r0.add(Long.valueOf(r2.getQueueId()));
    L10:
        this.impl.setQueue(r7);
    }

    public void setQueueTitle(CharSequence r1) {
        this.impl.setQueueTitle(r1);
    }

    public void setRatingType(int r1) {
        this.impl.setRatingType(r1);
    }

    public void setRepeatMode(int r1) {
        this.impl.setRepeatMode(r1);
    }

    public void setSessionActivity(PendingIntent r1) {
        this.impl.setSessionActivity(r1);
    }

    public void setShuffleMode(int r1) {
        this.impl.setShuffleMode(r1);
    }
}
