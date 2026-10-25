package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaRouter;
import android.media.RemoteControlClient;
import androidx.mediarouter.media.MediaRouterUtils;
import java.lang.ref.WeakReference;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class RemoteControlClientCompat {
    protected final Context mContext;
    protected final RemoteControlClient mRcc;
    protected VolumeCallback mVolumeCallback;

    public static class JellybeanImpl extends RemoteControlClientCompat {
        private boolean mRegistered;
        private final android.media.MediaRouter mRouter;
        private final MediaRouter.UserRouteInfo mUserRoute;
        private final MediaRouter.RouteCategory mUserRouteCategory;

        public static final class VolumeCallbackWrapper implements MediaRouterUtils.VolumeCallback {
            private final WeakReference<JellybeanImpl> mImplWeak;

            public VolumeCallbackWrapper(JellybeanImpl r2) {
                this.mImplWeak = new WeakReference(r2);
            }

            @Override // androidx.mediarouter.media.MediaRouterUtils.VolumeCallback
            public void onVolumeSetRequest(MediaRouter.RouteInfo r1, int r2) {
                JellybeanImpl r0 = this.mImplWeak.get();
                if (r0 == null) goto L8;
                VolumeCallback r02 = r0.mVolumeCallback;
                if (r02 == null) goto L9;
                r02.onVolumeSetRequest(r2);
                return;
            L9:
                return;
            }

            @Override // androidx.mediarouter.media.MediaRouterUtils.VolumeCallback
            public void onVolumeUpdateRequest(MediaRouter.RouteInfo r1, int r2) {
                JellybeanImpl r0 = this.mImplWeak.get();
                if (r0 == null) goto L8;
                VolumeCallback r02 = r0.mVolumeCallback;
                if (r02 == null) goto L9;
                r02.onVolumeUpdateRequest(r2);
                return;
            L9:
                return;
            }
        }

        public JellybeanImpl(Context r2, RemoteControlClient r3) {
            super(r2, r3);
            android.media.MediaRouter r22 = (android.media.MediaRouter) r2.getSystemService("media_router");
            this.mRouter = r22;
            MediaRouter.RouteCategory r32 = r22.createRouteCategory(_UrlKt.FRAGMENT_ENCODE_SET, false);
            this.mUserRouteCategory = r32;
            this.mUserRoute = r22.createUserRoute(r32);
        }

        @Override // androidx.mediarouter.media.RemoteControlClientCompat
        @SuppressLint({"WrongConstant"})
        public void setPlaybackInfo(PlaybackInfo r3) {
            this.mUserRoute.setVolume(r3.volume);
            this.mUserRoute.setVolumeMax(r3.volumeMax);
            this.mUserRoute.setVolumeHandling(r3.volumeHandling);
            this.mUserRoute.setPlaybackStream(r3.playbackStream);
            this.mUserRoute.setPlaybackType(r3.playbackType);
            if (this.mRegistered == true) goto L6;
            this.mRegistered = true;
            this.mUserRoute.setVolumeCallback(MediaRouterUtils.createVolumeCallback(new VolumeCallbackWrapper(this)));
            this.mUserRoute.setRemoteControlClient(this.mRcc);
            return;
        }
    }

    public static final class PlaybackInfo {
        public int playbackStream;
        public int playbackType;
        public int volume;
        public String volumeControlId;
        public int volumeHandling;
        public int volumeMax;

        public PlaybackInfo() {
            this.volumeHandling = 0;
            this.playbackStream = 3;
            this.playbackType = 1;
        }
    }

    public interface VolumeCallback {
        void onVolumeSetRequest(int r1);

        void onVolumeUpdateRequest(int r1);
    }

    public RemoteControlClientCompat(Context r1, RemoteControlClient r2) {
        this.mContext = r1;
        this.mRcc = r2;
    }

    public static RemoteControlClientCompat obtain(Context r1, RemoteControlClient r2) {
        return new JellybeanImpl(r1, r2);
    }

    public RemoteControlClient getRemoteControlClient() {
        return this.mRcc;
    }

    public void setPlaybackInfo(PlaybackInfo r1) {
    }

    public void setVolumeCallback(VolumeCallback r1) {
        this.mVolumeCallback = r1;
    }
}
