package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.media.MediaRouter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class PlatformMediaRouter1RouteProvider extends MediaRouteProvider {
    public static final int ALL_ROUTE_TYPES = 8388611;
    public static final String DEFAULT_ROUTE_ID = "DEFAULT_ROUTE";
    public static final String PACKAGE_NAME = "android";
    public static final int ROUTE_TYPE_LIVE_AUDIO = 1;
    public static final int ROUTE_TYPE_LIVE_VIDEO = 2;
    public static final int ROUTE_TYPE_USER = 8388608;
    public static final String TAG = "AxSysMediaRouteProvider";

    public static class Api24Impl extends JellybeanMr2Impl {
        public Api24Impl(Context r1, SyncCallback r2) {
            super(r1, r2);
        }

        @Override // androidx.mediarouter.media.PlatformMediaRouter1RouteProvider.JellybeanMr2Impl
        @SuppressLint({"WrongConstant"})
        public void onBuildSystemRouteDescriptor(JellybeanMr2Impl.SystemRouteRecord r1, MediaRouteDescriptor.Builder r2) {
            super.onBuildSystemRouteDescriptor(r1, r2);
            r2.setDeviceType(r1.mRoute.getDeviceType());
        }
    }

    public static class JellybeanMr2Impl extends PlatformMediaRouter1RouteProvider implements MediaRouterUtils.Callback, MediaRouterUtils.VolumeCallback {
        private static final ArrayList<IntentFilter> LIVE_AUDIO_CONTROL_FILTERS = null;
        private static final ArrayList<IntentFilter> LIVE_VIDEO_CONTROL_FILTERS = null;
        protected boolean mActiveScan;
        protected final MediaRouter.Callback mCallback;
        protected boolean mCallbackRegistered;
        protected int mRouteTypes;
        protected final android.media.MediaRouter mRouter;
        private final SyncCallback mSyncCallback;
        protected final ArrayList<SystemRouteRecord> mSystemRouteRecords;
        protected final MediaRouter.RouteCategory mUserRouteCategory;
        protected final ArrayList<UserRouteRecord> mUserRouteRecords;
        protected final MediaRouter.VolumeCallback mVolumeCallback;

        public static final class SystemRouteController extends MediaRouteProvider.RouteController {
            private final MediaRouter.RouteInfo mRoute;

            public SystemRouteController(MediaRouter.RouteInfo r1) {
                this.mRoute = r1;
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
            public void onSetVolume(int r1) {
                this.mRoute.requestSetVolume(r1);
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
            public void onUpdateVolume(int r1) {
                this.mRoute.requestUpdateVolume(r1);
            }
        }

        public static final class SystemRouteRecord {
            public final MediaRouter.RouteInfo mRoute;
            public MediaRouteDescriptor mRouteDescriptor;
            public final String mRouteDescriptorId;

            public SystemRouteRecord(MediaRouter.RouteInfo r1, String r2) {
                this.mRoute = r1;
                this.mRouteDescriptorId = r2;
            }
        }

        public static final class UserRouteRecord {
            public final MediaRouter.RouteInfo mRoute;
            public final MediaRouter.UserRouteInfo mUserRoute;

            public UserRouteRecord(MediaRouter.RouteInfo r1, MediaRouter.UserRouteInfo r2) {
                this.mRoute = r1;
                this.mUserRoute = r2;
            }
        }

        static {
            IntentFilter r0 = new IntentFilter();
            r0.addCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO);
            ArrayList<IntentFilter> r1 = new ArrayList();
            LIVE_AUDIO_CONTROL_FILTERS = r1;
            r1.add(r0);
            IntentFilter r02 = new IntentFilter();
            r02.addCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
            ArrayList<IntentFilter> r12 = new ArrayList();
            LIVE_VIDEO_CONTROL_FILTERS = r12;
            r12.add(r02);
        }

        public JellybeanMr2Impl(Context r2, SyncCallback r3) {
            super(r2);
            this.mSystemRouteRecords = new ArrayList();
            this.mUserRouteRecords = new ArrayList();
            this.mSyncCallback = r3;
            android.media.MediaRouter r32 = (android.media.MediaRouter) r2.getSystemService("media_router");
            this.mRouter = r32;
            this.mCallback = MediaRouterUtils.createCallback(this);
            this.mVolumeCallback = MediaRouterUtils.createVolumeCallback(this);
            this.mUserRouteCategory = r32.createRouteCategory(r2.getResources().getString(R.string.mr_user_route_category_name), false);
            updateSystemRoutes();
        }

        private boolean addSystemRouteNoPublish(MediaRouter.RouteInfo r3) {
            if (getUserRouteRecord(r3) == null) goto L5;
            return false;
        L5:
            if (findSystemRouteRecord(r3) >= 0) goto L10;
            SystemRouteRecord r1 = new SystemRouteRecord(r3, assignRouteId(r3));
            updateSystemRouteDescriptor(r1);
            this.mSystemRouteRecords.add(r1);
            return true;
        L10:
            return false;
        }

        private String assignRouteId(MediaRouter.RouteInfo r5) {
            if (getDefaultRoute() != r5) goto L5;
            String r52 = PlatformMediaRouter1RouteProvider.DEFAULT_ROUTE_ID;
        L7:
            if (findSystemRouteRecordByDescriptorId(r52) >= 0) goto L9;
            return r52;
        L9:
            int r0 = 2;
        L10:
            String r1 = String.format(Locale.US, "%s_%d", new Object[]{r52, Integer.valueOf(r0)});
            if (findSystemRouteRecordByDescriptorId(r1) < 0) goto L12;
            r0 = r0 + 1;
            goto L10
        L12:
            return r1;
        L5:
            r52 = String.format(Locale.US, "ROUTE_%08x", new Object[]{Integer.valueOf(getRouteName(r5).hashCode())});
            goto L7
        }

        private List<MediaRouter.RouteInfo> getRoutes() {
            int r0 = this.mRouter.getRouteCount();
            ArrayList r1 = new ArrayList(r0);
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            r1.add(this.mRouter.getRouteAt(r2));
            r2 = r2 + 1;
            goto L3
        L5:
            return r1;
        }

        private static int getStringResourceIdForType(int r1) {
            if (r1 == 1) goto L15;
            if (r1 == 2) goto L13;
            if (r1 == 3) goto L11;
            return R.string.mr_route_name_unknown;
        L11:
            return R.string.mr_route_name_bluetooth;
        L13:
            return R.string.mr_route_name_speaker;
        L15:
            return R.string.mr_route_name_tv;
        }

        private void updateSystemRoutes() {
            updateCallback();
            Iterator<MediaRouter.RouteInfo> r0 = getRoutes().iterator();
            boolean r1 = false;
        L4:
            if (r0.hasNext() == false) goto L6;
            r1 = r1 | addSystemRouteNoPublish(r0.next());
            goto L4
        L6:
            if (r1 == false) goto L10;
            publishRoutes();
            return;
        }

        public int findSystemRouteRecord(MediaRouter.RouteInfo r4) {
            int r0 = this.mSystemRouteRecords.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            if (this.mSystemRouteRecords.get(r1).mRoute == r4) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r1;
        L8:
            return -1;
        }

        public int findSystemRouteRecordByDescriptorId(String r4) {
            int r0 = this.mSystemRouteRecords.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            if (this.mSystemRouteRecords.get(r1).mRouteDescriptorId.equals(r4) == true) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r1;
        L8:
            return -1;
        }

        public int findUserRouteRecord(MediaRouter.RouteInfo r4) {
            int r0 = this.mUserRouteRecords.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            if (this.mUserRouteRecords.get(r1).mRoute == r4) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r1;
        L8:
            return -1;
        }

        public MediaRouter.RouteInfo getDefaultRoute() {
            return this.mRouter.getDefaultRoute();
        }

        public String getRouteName(MediaRouter.RouteInfo r3) {
            CharSequence r0 = r3.getName(getContext());
            if (TextUtils.isEmpty(r0) == true) goto L7;
            return r0.toString();
        L7:
            if ((r3.getSupportedTypes() & 8388608) != 0) goto L10;
            int r32 = getStringResourceIdForType(r3.getDeviceType());
            return getContext().getString(r32);
        L10:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public UserRouteRecord getUserRouteRecord(MediaRouter.RouteInfo r1) {
            Object r0 = r1.getTag();
            if ((r0 instanceof UserRouteRecord) == true) goto L5;
            return null;
        L5:
            return (UserRouteRecord) r0;
        }

        public boolean isConnecting(SystemRouteRecord r1) {
            return r1.mRoute.isConnecting();
        }

        public void onBuildSystemRouteDescriptor(SystemRouteRecord r4, MediaRouteDescriptor.Builder r5) {
            int r0 = r4.mRoute.getSupportedTypes();
            if ((r0 & 1) == 0) goto L6;
            r5.addControlFilters(LIVE_AUDIO_CONTROL_FILTERS);
        L6:
            if ((r0 & 2) == 0) goto L8;
            r5.addControlFilters(LIVE_VIDEO_CONTROL_FILTERS);
        L8:
            r5.setPlaybackType(r4.mRoute.getPlaybackType());
            r5.setPlaybackStream(r4.mRoute.getPlaybackStream());
            r5.setVolume(r4.mRoute.getVolume());
            r5.setVolumeMax(r4.mRoute.getVolumeMax());
            r5.setVolumeHandling(r4.mRoute.getVolumeHandling());
            if ((r0 & 8388608) != 0) goto L11;
            boolean r02 = true;
        L12:
            r5.setIsSystemRoute(r02);
            if (r4.mRoute.isEnabled() == true) goto L16;
            r5.setEnabled(false);
        L16:
            if (isConnecting(r4) == false) goto L18;
            r5.setConnectionState(1);
        L18:
            Display r3 = r4.mRoute.getPresentationDisplay();
            if (r3 == null) goto L21;
            r5.setPresentationDisplayId(r3.getDisplayId());
        L21:
            CharSequence r32 = r4.mRoute.getDescription();
            if (r32 == null) goto L25;
            r5.setDescription(r32.toString());
            return;
        L25:
            return;
        L11:
            r02 = false;
            goto L12
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public MediaRouteProvider.RouteController onCreateRouteController(String r1) {
            int r12 = findSystemRouteRecordByDescriptorId(r1);
            if (r12 >= 0) goto L5;
            return null;
        L5:
            return new SystemRouteController(this.mSystemRouteRecords.get(r12).mRoute);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest r7) {
            int r0 = 0;
            if (r7 == null) goto L15;
            List<String> r1 = r7.getSelector().getControlCategories();
            int r2 = r1.size();
            int r3 = 0;
        L5:
            if (r0 >= r2) goto L14;
            String r4 = r1.get(r0);
            if (r4.equals(MediaControlIntent.CATEGORY_LIVE_AUDIO) == false) goto L10;
            r3 = r3 | 1;
        L13:
            r0 = r0 + 1;
            goto L5
        L10:
            if (r4.equals(MediaControlIntent.CATEGORY_LIVE_VIDEO) == false) goto L12;
            r3 = r3 | 2;
            goto L13
        L12:
            r3 = r3 | 8388608;
            goto L13
        L14:
            boolean r72 = r7.isActiveScan();
            r0 = r3;
        L17:
            if (this.mRouteTypes == r0) goto L19;
        L22:
            this.mRouteTypes = r0;
            this.mActiveScan = r72;
            updateSystemRoutes();
            return;
        L19:
            if (this.mActiveScan != r72) goto L22;
            return;
        L15:
            r72 = false;
            goto L17
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteAdded(MediaRouter.RouteInfo r1) {
            if (addSystemRouteNoPublish(r1) == false) goto L6;
            publishRoutes();
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteChanged(MediaRouter.RouteInfo r2) {
            if (getUserRouteRecord(r2) != null) goto L8;
            int r22 = findSystemRouteRecord(r2);
            if (r22 < 0) goto L9;
            updateSystemRouteDescriptor(this.mSystemRouteRecords.get(r22));
            publishRoutes();
            return;
        L9:
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteGrouped(MediaRouter.RouteInfo r1, MediaRouter.RouteGroup r2, int r3) {
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRoutePresentationDisplayChanged(MediaRouter.RouteInfo r4) {
            int r0 = findSystemRouteRecord(r4);
            if (r0 < 0) goto L12;
            SystemRouteRecord r02 = this.mSystemRouteRecords.get(r0);
            Display r42 = r4.getPresentationDisplay();
            if (r42 == null) goto L7;
            int r43 = r42.getDisplayId();
        L9:
            if (r43 == r02.mRouteDescriptor.getPresentationDisplayId()) goto L13;
            r02.mRouteDescriptor = new MediaRouteDescriptor.Builder(r02.mRouteDescriptor).setPresentationDisplayId(r43).build();
            publishRoutes();
            return;
        L13:
            return;
        L7:
            r43 = -1;
            goto L9
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteRemoved(MediaRouter.RouteInfo r2) {
            if (getUserRouteRecord(r2) != null) goto L8;
            int r22 = findSystemRouteRecord(r2);
            if (r22 < 0) goto L9;
            this.mSystemRouteRecords.remove(r22);
            publishRoutes();
            return;
        L9:
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteSelected(int r2, MediaRouter.RouteInfo r3) {
            if (r3 != this.mRouter.getSelectedRoute(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES)) goto L14;
            UserRouteRecord r22 = getUserRouteRecord(r3);
            if (r22 == null) goto L9;
            r22.mRoute.select(false);
            return;
        L9:
            int r23 = findSystemRouteRecord(r3);
            if (r23 < 0) goto L13;
            SystemRouteRecord r24 = this.mSystemRouteRecords.get(r23);
            this.mSyncCallback.onPlatformRouteSelectedByDescriptorId(r24.mRouteDescriptorId);
            return;
        L13:
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteUngrouped(MediaRouter.RouteInfo r1, MediaRouter.RouteGroup r2) {
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteUnselected(int r1, MediaRouter.RouteInfo r2) {
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.Callback
        public void onRouteVolumeChanged(MediaRouter.RouteInfo r4) {
            if (getUserRouteRecord(r4) != null) goto L10;
            int r0 = findSystemRouteRecord(r4);
            if (r0 < 0) goto L11;
            SystemRouteRecord r02 = this.mSystemRouteRecords.get(r0);
            int r42 = r4.getVolume();
            if (r42 == r02.mRouteDescriptor.getVolume()) goto L12;
            r02.mRouteDescriptor = new MediaRouteDescriptor.Builder(r02.mRouteDescriptor).setVolume(r42).build();
            publishRoutes();
            return;
        L12:
            return;
        L11:
            return;
        }

        @Override // androidx.mediarouter.media.PlatformMediaRouter1RouteProvider
        public void onSyncRouteAdded(MediaRouter.RouteInfo r3) {
            MediaRouteProvider r0 = r3.getProviderInstance();
            android.media.MediaRouter r1 = this.mRouter;
            if (r0 == this) goto L6;
            MediaRouter.UserRouteInfo r02 = r1.createUserRoute(this.mUserRouteCategory);
            UserRouteRecord r12 = new UserRouteRecord(r3, r02);
            r02.setTag(r12);
            r02.setVolumeCallback(this.mVolumeCallback);
            updateUserRouteProperties(r12);
            this.mUserRouteRecords.add(r12);
            this.mRouter.addUserRoute(r02);
            return;
        L6:
            int r03 = findSystemRouteRecord(r1.getSelectedRoute(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES));
            if (r03 >= 0) goto L9;
            return;
        L9:
            if (this.mSystemRouteRecords.get(r03).mRouteDescriptorId.equals(r3.getDescriptorId()) == false) goto L13;
            r3.select(false);
            return;
        }

        @Override // androidx.mediarouter.media.PlatformMediaRouter1RouteProvider
        public void onSyncRouteChanged(MediaRouter.RouteInfo r2) {
            if (r2.getProviderInstance() == this) goto L8;
            int r22 = findUserRouteRecord(r2);
            if (r22 < 0) goto L9;
            updateUserRouteProperties(this.mUserRouteRecords.get(r22));
            return;
        L9:
            return;
        }

        @Override // androidx.mediarouter.media.PlatformMediaRouter1RouteProvider
        public void onSyncRouteRemoved(MediaRouter.RouteInfo r3) {
            if (r3.getProviderInstance() == this) goto L14;
            int r32 = findUserRouteRecord(r3);
            if (r32 < 0) goto L15;
            UserRouteRecord r33 = this.mUserRouteRecords.remove(r32);
            r33.mUserRoute.setTag(null);
            r33.mUserRoute.setVolumeCallback(null);
            this.mRouter.removeUserRoute(r33.mUserRoute);     // Catch: IllegalArgumentException -> L9
            return;
        L9:
            e = move-exception;
            Log.w(PlatformMediaRouter1RouteProvider.TAG, "Failed to remove user route", e);
            return;
        L15:
            return;
        }

        @Override // androidx.mediarouter.media.PlatformMediaRouter1RouteProvider
        public void onSyncRouteSelected(MediaRouter.RouteInfo r2) {
            if (r2.isSelected() == true) goto L6;
            return;
        L6:
            if (r2.getProviderInstance() == this) goto L11;
            int r22 = findUserRouteRecord(r2);
            if (r22 < 0) goto L15;
            selectRoute(this.mUserRouteRecords.get(r22).mUserRoute);
            return;
        L15:
            return;
        L11:
            int r23 = findSystemRouteRecordByDescriptorId(r2.getDescriptorId());
            if (r23 < 0) goto L16;
            selectRoute(this.mSystemRouteRecords.get(r23).mRoute);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.VolumeCallback
        public void onVolumeSetRequest(MediaRouter.RouteInfo r1, int r2) {
            UserRouteRecord r0 = getUserRouteRecord(r1);
            if (r0 == null) goto L6;
            r0.mRoute.requestSetVolume(r2);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouterUtils.VolumeCallback
        public void onVolumeUpdateRequest(MediaRouter.RouteInfo r1, int r2) {
            UserRouteRecord r0 = getUserRouteRecord(r1);
            if (r0 == null) goto L6;
            r0.mRoute.requestUpdateVolume(r2);
            return;
        }

        public void publishRoutes() {
            MediaRouteProviderDescriptor.Builder r0 = new MediaRouteProviderDescriptor.Builder();
            int r1 = this.mSystemRouteRecords.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            r0.addRoute(this.mSystemRouteRecords.get(r2).mRouteDescriptor);
            r2 = r2 + 1;
            goto L3
        L5:
            setDescriptor(r0.build());
        }

        public void selectRoute(MediaRouter.RouteInfo r2) {
            this.mRouter.selectRoute(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, r2);
        }

        public void updateCallback() {
            if (this.mCallbackRegistered == false) goto L5;
            this.mRouter.removeCallback(this.mCallback);
        L5:
            this.mCallbackRegistered = true;
            int r0 = (this.mActiveScan ? 1 : 0) | 2;
            this.mRouter.addCallback(this.mRouteTypes, this.mCallback, r0);
        }

        public void updateSystemRouteDescriptor(SystemRouteRecord r4) {
            MediaRouteDescriptor.Builder r0 = new MediaRouteDescriptor.Builder(r4.mRouteDescriptorId, getRouteName(r4.mRoute));
            onBuildSystemRouteDescriptor(r4, r0);
            r4.mRouteDescriptor = r0.build();
        }

        @SuppressLint({"WrongConstant"})
        public void updateUserRouteProperties(UserRouteRecord r2) {
            MediaRouter.UserRouteInfo r1 = r2.mUserRoute;
            MediaRouter.RouteInfo r22 = r2.mRoute;
            r1.setName(r22.getName());
            r1.setPlaybackType(r22.getPlaybackType());
            r1.setPlaybackStream(r22.getPlaybackStream());
            r1.setVolume(r22.getVolume());
            r1.setVolumeMax(r22.getVolumeMax());
            r1.setVolumeHandling(r22.getVolumeHandling());
            r1.setDescription(r22.getDescription());
        }
    }

    public interface SyncCallback {
        void onPlatformRouteSelectedByDescriptorId(String r1);
    }

    public PlatformMediaRouter1RouteProvider(Context r5) {
        super(r5, new MediaRouteProvider.ProviderMetadata(new ComponentName("android", PlatformMediaRouter1RouteProvider.class.getName())));
    }

    public static PlatformMediaRouter1RouteProvider obtain(Context r1, SyncCallback r2) {
        return new Api24Impl(r1, r2);
    }

    public void onSyncRouteAdded(MediaRouter.RouteInfo r1) {
    }

    public void onSyncRouteChanged(MediaRouter.RouteInfo r1) {
    }

    public void onSyncRouteRemoved(MediaRouter.RouteInfo r1) {
    }

    public void onSyncRouteSelected(MediaRouter.RouteInfo r1) {
    }
}
