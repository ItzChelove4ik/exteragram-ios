package androidx.mediarouter.media;

import android.media.MediaRouter;

/* loaded from: classes4.dex */
class MediaRouterUtils {

    public interface Callback {
        void onRouteAdded(MediaRouter.RouteInfo r1);

        void onRouteChanged(MediaRouter.RouteInfo r1);

        void onRouteGrouped(MediaRouter.RouteInfo r1, MediaRouter.RouteGroup r2, int r3);

        void onRoutePresentationDisplayChanged(MediaRouter.RouteInfo r1);

        void onRouteRemoved(MediaRouter.RouteInfo r1);

        void onRouteSelected(int r1, MediaRouter.RouteInfo r2);

        void onRouteUngrouped(MediaRouter.RouteInfo r1, MediaRouter.RouteGroup r2);

        void onRouteUnselected(int r1, MediaRouter.RouteInfo r2);

        void onRouteVolumeChanged(MediaRouter.RouteInfo r1);
    }

    public static class CallbackProxy<T extends Callback> extends MediaRouter.Callback {
        protected final T mCallback;

        public CallbackProxy(T r1) {
            this.mCallback = r1;
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteAdded(android.media.MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.mCallback.onRouteAdded(r2);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteChanged(android.media.MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.mCallback.onRouteChanged(r2);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteGrouped(android.media.MediaRouter r1, MediaRouter.RouteInfo r2, MediaRouter.RouteGroup r3, int r4) {
            this.mCallback.onRouteGrouped(r2, r3, r4);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRoutePresentationDisplayChanged(android.media.MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.mCallback.onRoutePresentationDisplayChanged(r2);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteRemoved(android.media.MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.mCallback.onRouteRemoved(r2);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteSelected(android.media.MediaRouter r1, int r2, MediaRouter.RouteInfo r3) {
            this.mCallback.onRouteSelected(r2, r3);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteUngrouped(android.media.MediaRouter r1, MediaRouter.RouteInfo r2, MediaRouter.RouteGroup r3) {
            this.mCallback.onRouteUngrouped(r2, r3);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteUnselected(android.media.MediaRouter r1, int r2, MediaRouter.RouteInfo r3) {
            this.mCallback.onRouteUnselected(r2, r3);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteVolumeChanged(android.media.MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.mCallback.onRouteVolumeChanged(r2);
        }
    }

    public interface VolumeCallback {
        void onVolumeSetRequest(MediaRouter.RouteInfo r1, int r2);

        void onVolumeUpdateRequest(MediaRouter.RouteInfo r1, int r2);
    }

    public static class VolumeCallbackProxy<T extends VolumeCallback> extends MediaRouter.VolumeCallback {
        protected final T mCallback;

        public VolumeCallbackProxy(T r1) {
            this.mCallback = r1;
        }

        @Override // android.media.MediaRouter.VolumeCallback
        public void onVolumeSetRequest(MediaRouter.RouteInfo r1, int r2) {
            this.mCallback.onVolumeSetRequest(r1, r2);
        }

        @Override // android.media.MediaRouter.VolumeCallback
        public void onVolumeUpdateRequest(MediaRouter.RouteInfo r1, int r2) {
            this.mCallback.onVolumeUpdateRequest(r1, r2);
        }
    }

    private MediaRouterUtils() {
    }

    public static MediaRouter.Callback createCallback(Callback r1) {
        return new CallbackProxy(r1);
    }

    public static MediaRouter.VolumeCallback createVolumeCallback(VolumeCallback r1) {
        return new VolumeCallbackProxy(r1);
    }
}
