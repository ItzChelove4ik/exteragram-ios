package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.res.Resources;
import android.media.RemoteControlClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.collection.ArrayMap;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class MediaRouter {
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
    public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    static final boolean DEBUG = false;
    public static final int REASON_DISCONNECT_CALLED = 1;
    public static final int REASON_FAILED_TO_CREATE_DYNAMIC_GROUP_ROUTE_CONTROLLER = 6;
    public static final int REASON_REJECTED_FOR_SELECTED_ROUTE = 4;
    public static final int REASON_ROUTE_CONNECTION_TIMEOUT = 7;
    public static final int REASON_ROUTE_NOT_AVAILABLE = 2;
    public static final int REASON_ROUTE_NOT_ENABLED = 3;
    public static final int REASON_UNSUPPORTED_FOR_NON_DYNAMIC_CONTROLLER = 5;
    static final String TAG = "AxMediaRouter";
    public static final int UNSELECT_REASON_DISCONNECTED = 1;
    public static final int UNSELECT_REASON_ROUTE_CHANGED = 3;
    public static final int UNSELECT_REASON_STOPPED = 2;
    public static final int UNSELECT_REASON_UNKNOWN = 0;
    static GlobalMediaRouter sGlobal;
    final ArrayList<CallbackRecord> mCallbackRecords;
    final Context mContext;

    public static abstract class Callback {
        public Callback() {
        }

        public void onProviderAdded(MediaRouter r1, ProviderInfo r2) {
        }

        public void onProviderChanged(MediaRouter r1, ProviderInfo r2) {
        }

        public void onProviderRemoved(MediaRouter r1, ProviderInfo r2) {
        }

        public void onRouteAdded(MediaRouter r1, RouteInfo r2) {
        }

        public void onRouteChanged(MediaRouter r1, RouteInfo r2) {
        }

        public void onRouteConnected(MediaRouter r1, RouteInfo r2, RouteInfo r3) {
        }

        public void onRouteDisconnected(MediaRouter r1, RouteInfo r2, RouteInfo r3, int r4) {
        }

        public void onRoutePresentationDisplayChanged(MediaRouter r1, RouteInfo r2) {
        }

        public void onRouteRemoved(MediaRouter r1, RouteInfo r2) {
        }

        @Deprecated
        public void onRouteSelected(MediaRouter r1, RouteInfo r2) {
        }

        public void onRouteSelected(MediaRouter r1, RouteInfo r2, int r3) {
            onRouteSelected(r1, r2);
        }

        public void onRouteSelected(MediaRouter r1, RouteInfo r2, int r3, RouteInfo r4) {
            onRouteSelected(r1, r2, r3);
        }

        @Deprecated
        public void onRouteUnselected(MediaRouter r1, RouteInfo r2) {
        }

        public void onRouteUnselected(MediaRouter r1, RouteInfo r2, int r3) {
            onRouteUnselected(r1, r2);
        }

        public void onRouteVolumeChanged(MediaRouter r1, RouteInfo r2) {
        }

        public void onRouterParamsChanged(MediaRouter r1, MediaRouterParams r2) {
        }
    }

    public static final class CallbackRecord {
        public final Callback mCallback;
        public int mFlags;
        public final MediaRouter mRouter;
        public MediaRouteSelector mSelector;
        public long mTimestamp;

        public CallbackRecord(MediaRouter r1, Callback r2) {
            this.mRouter = r1;
            this.mCallback = r2;
            this.mSelector = MediaRouteSelector.EMPTY;
        }

        public boolean filterRouteEvent(RouteInfo r3, int r4, RouteInfo r5, int r6) {
            if ((this.mFlags & 2) == 0) goto L5;
        L20:
            return true;
        L5:
            if (r3.matchesSelector(this.mSelector) == true) goto L20;
            if (MediaRouter.isTransferToLocalEnabled() == true) goto L10;
            return false;
        L10:
            if (r3.isDefaultOrBluetooth() == true) goto L12;
            return false;
        L12:
            if (r4 == 262) goto L14;
            return false;
        L14:
            if (r6 != 3) goto L23;
            if (r5 != null) goto L17;
            return false;
        L17:
            return !r5.isDefaultOrBluetooth();
        L23:
            return false;
        }
    }

    public static abstract class ControlRequestCallback {
        public ControlRequestCallback() {
        }

        public void onError(String r1, Bundle r2) {
        }

        public void onResult(Bundle r1) {
        }
    }

    public static class GroupRouteInfo extends RouteInfo {
        public static final int ADD_ROUTE_FAILED_REASON_ALREADY_IN_GROUP = 3;
        public static final int ADD_ROUTE_FAILED_REASON_NOT_AVAILABLE_ROUTE_CONNECTION = 5;
        public static final int ADD_ROUTE_FAILED_REASON_NOT_GROUPABLE = 2;
        public static final int ADD_ROUTE_FAILED_REASON_UNSUPPORTED_FOR_GROUP_ROUTE = 4;
        public static final int ADD_ROUTE_SUCCESSFUL = 1;
        public static final int REMOVE_ROUTE_FAILED_REASON_LAST_ROUTE_IN_GROUP = 4;
        public static final int REMOVE_ROUTE_FAILED_REASON_NOT_AVAILABLE_ROUTE_CONNECTION = 6;
        public static final int REMOVE_ROUTE_FAILED_REASON_NOT_IN_GROUP = 3;
        public static final int REMOVE_ROUTE_FAILED_REASON_NOT_UNSELECTABLE = 2;
        public static final int REMOVE_ROUTE_FAILED_REASON_UNSUPPORTED_FOR_GROUP_ROUTE = 5;
        public static final int REMOVE_ROUTE_SUCCESSFUL = 1;
        static final int UPDATE_ROUTES_FAILED_REASON_NOT_AVAILABLE_ROUTE_CONNECTION = 4;
        static final int UPDATE_ROUTES_FAILED_REASON_NOT_TRANSFERABLE = 2;
        static final int UPDATE_ROUTES_FAILED_REASON_UNSUPPORTED_FOR_GROUP_ROUTE = 3;
        static final int UPDATE_ROUTES_SUCCESSFUL = 1;
        private final Map<String, MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> mRouteIdToDynamicRouteDescriptorMap;
        private final List<RouteInfo> mRoutesInGroup;

        public GroupRouteInfo(ProviderInfo r1, String r2, String r3) {
            super(r1, r2, r3);
            this.mRoutesInGroup = new ArrayList();
            this.mRouteIdToDynamicRouteDescriptorMap = new ArrayMap();
        }

        public int addRoute(RouteInfo r2) {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().addRouteToGroup(this, r2);
        }

        public List<RouteInfo> getRoutesInGroup() {
            return Collections.unmodifiableList(this.mRoutesInGroup);
        }

        public int getSelectionState(RouteInfo r1) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor r0 = this.mRouteIdToDynamicRouteDescriptorMap.get(r1.getId());
            if (r0 != null) goto L5;
            return 4;
        L5:
            return r0.getSelectionState();
        }

        public boolean isConnected() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getConnectedGroupRoutes().contains(this);
        }

        public boolean isGroupable(RouteInfo r1) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor r0 = this.mRouteIdToDynamicRouteDescriptorMap.get(r1.getId());
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isGroupable() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isTransferable(RouteInfo r1) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor r0 = this.mRouteIdToDynamicRouteDescriptorMap.get(r1.getId());
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isTransferable() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isUnselectable(RouteInfo r1) {
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor r0 = this.mRouteIdToDynamicRouteDescriptorMap.get(r1.getId());
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isUnselectable() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int removeRoute(RouteInfo r2) {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().removeRouteFromGroup(this, r2);
        }

        public void updateDynamicDescriptors(Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r5) {
            this.mSelectedRoutesInGroup.clear();
            this.mRoutesInGroup.clear();
            this.mRouteIdToDynamicRouteDescriptorMap.clear();
            Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r52 = r5.iterator();
        L4:
            if (r52.hasNext() == false) goto L13;
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor r0 = r52.next();
            RouteInfo r1 = findRouteByDynamicRouteDescriptor(r0);
            if (r1 == null) goto L4;
            this.mRoutesInGroup.add(r1);
            this.mRouteIdToDynamicRouteDescriptorMap.put(r1.getId(), r0);
            if (r0.getSelectionState() == 2) goto L12;
            if (r0.getSelectionState() != 3) goto L4;
        L12:
            this.mSelectedRoutesInGroup.add(r1);
            goto L4
        L13:
            MediaRouter.getGlobalRouter().mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, this);
        }

        public int updateRoutes(List<RouteInfo> r2) {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().updateRoutesForGroup(this, r2);
        }
    }

    public interface OnPrepareTransferListener {
        ListenableFuture<Void> onPrepareTransfer(RouteInfo r1, RouteInfo r2);
    }

    public static final class PrepareTransferNotifier {
        private static final long TRANSFER_TIMEOUT_MS = 15000;
        private boolean mCanceled;
        private boolean mFinished;
        private final RouteInfo mFromRoute;
        private ListenableFuture<Void> mFuture;
        final List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> mMemberRoutes;
        final int mReason;
        private final RouteInfo mRequestedRoute;
        private final WeakReference<GlobalMediaRouter> mRouter;
        private final boolean mSyncMediaRoute1Provider;
        final RouteInfo mToRoute;
        final MediaRouteProvider.RouteController mToRouteController;

        public PrepareTransferNotifier(GlobalMediaRouter r3, RouteInfo r4, MediaRouteProvider.RouteController r5, int r6, boolean r7, RouteInfo r8, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r9) {
            ArrayList r0 = null;
            this.mFuture = null;
            this.mFinished = false;
            this.mCanceled = false;
            this.mRouter = new WeakReference(r3);
            this.mToRoute = r4;
            this.mToRouteController = r5;
            this.mReason = r6;
            this.mSyncMediaRoute1Provider = r7;
            this.mFromRoute = r3.mSelectedRoute;
            this.mRequestedRoute = r8;
            if (r9 == null) goto L6;
            r0 = new ArrayList(r9);
        L6:
            this.mMemberRoutes = r0;
            r3.mCallbackHandler.postDelayed(new MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0(this), TRANSFER_TIMEOUT_MS);
        }

        private void selectToRouteAndNotify() {
            GlobalMediaRouter r0 = this.mRouter.get();
            if (r0 == null) goto L17;
            RouteInfo r1 = this.mToRoute;
            r0.mSelectedRoute = r1;
            r0.mSelectedRouteController = this.mToRouteController;
            RouteInfo r2 = this.mRequestedRoute;
            GlobalMediaRouter.CallbackHandler r3 = r0.mCallbackHandler;
            if (r2 != null) goto L8;
            r3.postRouteSelectedMessage(this.mFromRoute, r1, this.mReason, this.mSyncMediaRoute1Provider);
        L9:
            r0.mRouteControllerMap.clear();
            r0.maybeUpdateMemberRouteControllers();
            r0.updatePlaybackInfoFromSelectedRoute();
            if (this.mMemberRoutes == null) goto L15;
            GroupRouteInfo r02 = r0.mSelectedRoute.asGroup();
            if (r02 == null) goto L16;
            r02.updateDynamicDescriptors(this.mMemberRoutes);
            return;
        L16:
            return;
        L15:
            return;
        L8:
            r3.postAnotherRouteSelectedMessage(r2, r1, this.mReason, this.mSyncMediaRoute1Provider);
            goto L9
        }

        private void unselectFromRouteAndNotify() {
            GlobalMediaRouter r0 = this.mRouter.get();
            if (r0 == null) goto L20;
            RouteInfo r1 = r0.mSelectedRoute;
            RouteInfo r2 = this.mFromRoute;
            if (r1 != r2) goto L21;
            r0.mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED, r2, this.mReason);
            MediaRouteProvider.RouteController r12 = r0.mSelectedRouteController;
            if (r12 == null) goto L11;
            r12.onUnselect(this.mReason);
            r0.mSelectedRouteController.onRelease();
        L11:
            if (r0.mRouteControllerMap.isEmpty() == true) goto L17;
            Iterator<MediaRouteProvider.RouteController> r13 = r0.mRouteControllerMap.values().iterator();
        L14:
            if (r13.hasNext() == false) goto L16;
            MediaRouteProvider.RouteController r22 = r13.next();
            r22.onUnselect(this.mReason);
            r22.onRelease();
            goto L14
        L16:
            r0.mRouteControllerMap.clear();
        L17:
            r0.mSelectedRouteController = null;
            return;
        L21:
            return;
        }

        public void cancel() {
            if (this.mFinished == false) goto L5;
            return;
        L5:
            if (this.mCanceled == true) goto L13;
            this.mCanceled = true;
            MediaRouteProvider.RouteController r0 = this.mToRouteController;
            if (r0 == null) goto L12;
            r0.onUnselect(0);
            this.mToRouteController.onRelease();
            return;
        L12:
            return;
        }

        public void finishTransfer() {
            MediaRouter.checkCallingThread();
            if (this.mFinished == false) goto L5;
            return;
        L5:
            if (this.mCanceled == true) goto L21;
            GlobalMediaRouter r0 = this.mRouter.get();
            if (r0 != null) goto L10;
        L18:
            cancel();
            return;
        L10:
            if (r0.mTransferNotifier != this) goto L18;
            ListenableFuture<Void> r1 = this.mFuture;
            if (r1 != null) goto L14;
        L16:
            this.mFinished = true;
            r0.mTransferNotifier = null;
            unselectFromRouteAndNotify();
            selectToRouteAndNotify();
            return;
        L14:
            if (r1.isCancelled() == false) goto L16;
        }

        public void setFuture(ListenableFuture<Void> r3) {
            GlobalMediaRouter r0 = this.mRouter.get();
            if (r0 != null) goto L5;
        L13:
            Log.w(MediaRouter.TAG, "Router is released. Cancel transfer");
            cancel();
            return;
        L5:
            if (r0.mTransferNotifier != this) goto L13;
            if (this.mFuture != null) goto L11;
            this.mFuture = r3;
            MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0 r1 = new MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0(this);
            GlobalMediaRouter.CallbackHandler r2 = r0.mCallbackHandler;
            Objects.requireNonNull(r2);
            r3.addListener(r1, new MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda1(r2));
            return;
        L11:
            Segment$$ExternalSyntheticBUOutline1.m("future is already set");
        }
    }

    public static final class ProviderInfo {
        private MediaRouteProviderDescriptor mDescriptor;
        private final MediaRouteProvider.ProviderMetadata mMetadata;
        final MediaRouteProvider mProviderInstance;
        final List<RouteInfo> mRoutes;
        final boolean mTreatRouteDescriptorIdsAsUnique;

        public ProviderInfo(MediaRouteProvider r2, boolean r3) {
            this.mRoutes = new ArrayList();
            this.mProviderInstance = r2;
            this.mMetadata = r2.getMetadata();
            this.mTreatRouteDescriptorIdsAsUnique = r3;
        }

        public RouteInfo findRouteByDescriptorId(String r3) {
            Iterator<RouteInfo> r2 = this.mRoutes.iterator();
        L4:
            if (r2.hasNext() == false) goto L8;
            RouteInfo r0 = r2.next();
            if (r0.mDescriptorId.equals(r3) == false) goto L4;
            return r0;
        L8:
            return null;
        }

        public int findRouteIndexByDescriptorId(String r4) {
            int r0 = this.mRoutes.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            if (this.mRoutes.get(r1).mDescriptorId.equals(r4) == true) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r1;
        L8:
            return -1;
        }

        public ComponentName getComponentName() {
            return this.mMetadata.getComponentName();
        }

        public String getPackageName() {
            return this.mMetadata.getPackageName();
        }

        public MediaRouteProvider getProviderInstance() {
            MediaRouter.checkCallingThread();
            return this.mProviderInstance;
        }

        public List<RouteInfo> getRoutes() {
            MediaRouter.checkCallingThread();
            return Collections.unmodifiableList(this.mRoutes);
        }

        public boolean supportsDynamicGroup() {
            MediaRouteProviderDescriptor r0 = this.mDescriptor;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.supportsDynamicGroupRoute() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + getPackageName() + " }";
        }

        public boolean updateDescriptor(MediaRouteProviderDescriptor r2) {
            if (this.mDescriptor == r2) goto L6;
            this.mDescriptor = r2;
            return true;
        L6:
            return false;
        }
    }

    public static class RouteInfo {
        static final int CHANGE_GENERAL = 1;
        static final int CHANGE_PRESENTATION_DISPLAY = 4;
        static final int CHANGE_VOLUME = 2;
        public static final int CONNECTION_STATE_CONNECTED = 2;
        public static final int CONNECTION_STATE_CONNECTING = 1;
        public static final int CONNECTION_STATE_DISCONNECTED = 0;
        public static final int DEVICE_TYPE_AUDIO_VIDEO_RECEIVER = 4;
        public static final int DEVICE_TYPE_BLE_HEADSET = 22;
        public static final int DEVICE_TYPE_BLUETOOTH_A2DP = 3;
        public static final int DEVICE_TYPE_BUILTIN_SPEAKER = 12;
        public static final int DEVICE_TYPE_CAR = 9;
        public static final int DEVICE_TYPE_COMPUTER = 7;
        public static final int DEVICE_TYPE_DOCK = 19;
        public static final int DEVICE_TYPE_GAME_CONSOLE = 8;
        public static final int DEVICE_TYPE_GROUP = 1000;
        public static final int DEVICE_TYPE_HDMI = 16;
        public static final int DEVICE_TYPE_HDMI_ARC = 23;
        public static final int DEVICE_TYPE_HDMI_EARC = 24;
        public static final int DEVICE_TYPE_HEARING_AID = 21;
        public static final int DEVICE_TYPE_REMOTE_SPEAKER = 2;
        public static final int DEVICE_TYPE_SMARTPHONE = 11;
        public static final int DEVICE_TYPE_SMARTWATCH = 10;

        @Deprecated
        public static final int DEVICE_TYPE_SPEAKER = 2;
        public static final int DEVICE_TYPE_TABLET = 5;
        public static final int DEVICE_TYPE_TABLET_DOCKED = 6;
        public static final int DEVICE_TYPE_TV = 1;
        public static final int DEVICE_TYPE_UNKNOWN = 0;
        public static final int DEVICE_TYPE_USB_ACCESSORY = 18;
        public static final int DEVICE_TYPE_USB_DEVICE = 17;
        public static final int DEVICE_TYPE_USB_HEADSET = 20;
        public static final int DEVICE_TYPE_WIRED_HEADPHONES = 14;
        public static final int DEVICE_TYPE_WIRED_HEADSET = 13;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        public static final int PRESENTATION_DISPLAY_ID_NONE = -1;
        static final String SYSTEM_MEDIA_ROUTE_PROVIDER_PACKAGE_NAME = "android";
        private boolean mCanDisconnect;
        private int mConnectionState;
        private final ArrayList<IntentFilter> mControlFilters;
        private String mDescription;
        MediaRouteDescriptor mDescriptor;
        final String mDescriptorId;
        private int mDeviceType;
        boolean mEnabled;
        private Bundle mExtras;
        private Uri mIconUri;
        private final boolean mIsSystemRoute;
        private String mName;
        private int mPlaybackStream;
        private int mPlaybackType;
        private Display mPresentationDisplay;
        private int mPresentationDisplayId;
        private final ProviderInfo mProvider;
        protected List<RouteInfo> mSelectedRoutesInGroup;
        private IntentSender mSettingsIntent;
        final String mUniqueId;
        private int mVolume;
        private int mVolumeHandling;
        private int mVolumeMax;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DeviceType {
        }

        public RouteInfo(ProviderInfo r2, String r3, String r4) {
            this(r2, r3, r4, false);
        }

        public RouteInfo(ProviderInfo r2, String r3, String r4, boolean r5) {
            this.mControlFilters = new ArrayList();
            this.mPresentationDisplayId = -1;
            this.mSelectedRoutesInGroup = new ArrayList();
            this.mProvider = r2;
            this.mDescriptorId = r3;
            this.mUniqueId = r4;
            this.mIsSystemRoute = r5;
        }

        private boolean isSameControlFilter(IntentFilter r6, IntentFilter r7) {
            if (r6 != r7) goto L6;
            return true;
        L6:
            if (r6 == null) goto L28;
            if (r7 == null) goto L28;
            int r1 = r6.countActions();
            if (r1 == r7.countActions()) goto L12;
            return false;
        L12:
            int r2 = 0;
        L13:
            if (r2 >= r1) goto L18;
            if (r6.getAction(r2).equals(r7.getAction(r2)) == false) goto L16;
            r2 = r2 + 1;
            goto L13
        L16:
            return false;
        L18:
            int r12 = r6.countCategories();
            if (r12 == r7.countCategories()) goto L21;
            return false;
        L21:
            int r22 = 0;
        L22:
            if (r22 >= r12) goto L27;
            if (r6.getCategory(r22).equals(r7.getCategory(r22)) == false) goto L25;
            r22 = r22 + 1;
            goto L22
        L25:
            return false;
        L27:
            return true;
        L28:
            return false;
        }

        private boolean isSameControlFilters(List<IntentFilter> r5, List<IntentFilter> r6) {
            if (r5 != r6) goto L6;
            return true;
        L6:
            if (r5 == null) goto L22;
            if (r6 == null) goto L22;
            ListIterator<IntentFilter> r52 = r5.listIterator();
            ListIterator<IntentFilter> r62 = r6.listIterator();
        L11:
            if (r52.hasNext() == false) goto L18;
            if (r62.hasNext() == false) goto L18;
            if (isSameControlFilter(r52.next(), r62.next()) == true) goto L11;
            return false;
        L18:
            if (r52.hasNext() == true) goto L22;
            if (r62.hasNext() == true) goto L22;
            return true;
        L22:
            return false;
        }

        private static boolean isSystemMediaRouteProvider(RouteInfo r1) {
            return TextUtils.equals(r1.getProviderInstance().getMetadata().getPackageName(), "android");
        }

        public GroupRouteInfo asGroup() {
            if ((this instanceof GroupRouteInfo) == true) goto L5;
            return null;
        L5:
            return (GroupRouteInfo) this;
        }

        public boolean canDisconnect() {
            return this.mCanDisconnect;
        }

        public void connect() {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().connectRoute(this);
        }

        public void disconnect() {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().disconnectRoute(this);
        }

        public RouteInfo findRouteByDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor r1) {
            String r12 = r1.getRouteDescriptor().getId();
            return getProvider().findRouteByDescriptorId(r12);
        }

        public int getConnectionState() {
            return this.mConnectionState;
        }

        public List<IntentFilter> getControlFilters() {
            return this.mControlFilters;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public String getDescriptorId() {
            return this.mDescriptorId;
        }

        public int getDeviceType() {
            return this.mDeviceType;
        }

        public MediaRouteProvider.DynamicGroupRouteController getDynamicGroupController() {
            MediaRouter.checkCallingThread();
            MediaRouteProvider.RouteController r1 = MediaRouter.getGlobalRouter().mSelectedRouteController;
            if ((r1 instanceof MediaRouteProvider.DynamicGroupRouteController) == true) goto L5;
            return null;
        L5:
            return (MediaRouteProvider.DynamicGroupRouteController) r1;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        public String getId() {
            return this.mUniqueId;
        }

        public MediaRouteDescriptor getMediaRouteDescriptor() {
            return this.mDescriptor;
        }

        public String getName() {
            return this.mName;
        }

        public int getPlaybackStream() {
            return this.mPlaybackStream;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public Display getPresentationDisplay() {
            MediaRouter.checkCallingThread();
            if (this.mPresentationDisplayId < 0) goto L8;
            if (this.mPresentationDisplay != null) goto L8;
            this.mPresentationDisplay = MediaRouter.getGlobalRouter().getDisplay(this.mPresentationDisplayId);
        L8:
            return this.mPresentationDisplay;
        }

        public int getPresentationDisplayId() {
            return this.mPresentationDisplayId;
        }

        public ProviderInfo getProvider() {
            return this.mProvider;
        }

        public MediaRouteProvider getProviderInstance() {
            return this.mProvider.getProviderInstance();
        }

        public List<RouteInfo> getSelectedRoutesInGroup() {
            return Collections.unmodifiableList(this.mSelectedRoutesInGroup);
        }

        public IntentSender getSettingsIntent() {
            return this.mSettingsIntent;
        }

        public int getVolume() {
            return this.mVolume;
        }

        public int getVolumeHandling() {
            if (isGroup() == false) goto L9;
            if (MediaRouter.isGroupVolumeUxEnabled() == true) goto L9;
            return 0;
        L9:
            return this.mVolumeHandling;
        }

        public int getVolumeMax() {
            return this.mVolumeMax;
        }

        public boolean isBluetooth() {
            MediaRouter.checkCallingThread();
            if (MediaRouter.getGlobalRouter().getBluetoothRoute() != this) goto L6;
            return true;
        L6:
            return false;
        }

        @Deprecated
        public boolean isConnecting() {
            if (this.mConnectionState != 1) goto L5;
            return true;
        L5:
            return false;
        }

        public boolean isDefault() {
            MediaRouter.checkCallingThread();
            if (MediaRouter.getGlobalRouter().getDefaultRoute() != this) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isDefaultOrBluetooth() {
            if (isDefault() == false) goto L5;
        L16:
            return true;
        L5:
            if (this.mDeviceType == 3) goto L16;
            if (isSystemMediaRouteProvider(this) == true) goto L10;
            return false;
        L10:
            if (supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) == true) goto L12;
            return false;
        L12:
            if (supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO) == true) goto L18;
            return true;
        L18:
            return false;
        }

        public boolean isDeviceSpeaker() {
            int r0 = Resources.getSystem().getIdentifier("default_audio_route_name", "string", "android");
            if (isDefault() == true) goto L5;
            return false;
        L5:
            if (TextUtils.equals(Resources.getSystem().getText(r0), this.mName) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        public boolean isGroup() {
            return !this.mSelectedRoutesInGroup.isEmpty();
        }

        public boolean isSelectable() {
            if (this.mDescriptor != null) goto L5;
            return false;
        L5:
            if (this.mEnabled == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isSelected() {
            MediaRouter.checkCallingThread();
            if (MediaRouter.getGlobalRouter().getSelectedRoute() != this) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isSystemRoute() {
            return this.mIsSystemRoute;
        }

        public boolean matchesSelector(MediaRouteSelector r1) {
            if (r1 == null) goto L5;
            MediaRouter.checkCallingThread();
            return r1.matchesControlFilters(this.mControlFilters);
        L5:
            f$$ExternalSyntheticBUOutline1.m("selector must not be null");
            return false;
        }

        public int maybeUpdateDescriptor(MediaRouteDescriptor r2) {
            if (this.mDescriptor != r2) goto L5;
            return 0;
        L5:
            return updateDescriptor(r2);
        }

        public void requestSetVolume(int r4) {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, r4)));
        }

        public void requestUpdateVolume(int r2) {
            MediaRouter.checkCallingThread();
            if (r2 == 0) goto L6;
            MediaRouter.getGlobalRouter().requestUpdateVolume(this, r2);
            return;
        }

        public void select() {
            select(true);
        }

        public void select(boolean r3) {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().selectRoute(this, 3, r3);
        }

        public void sendControlRequest(Intent r2, ControlRequestCallback r3) {
            if (r2 == null) goto L5;
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().sendControlRequest(this, r2, r3);
            return;
        L5:
            f$$ExternalSyntheticBUOutline1.m("intent must not be null");
        }

        public boolean supportsControlAction(String r6, String r7) {
            if (r6 == null) goto L16;
            if (r7 == null) goto L14;
            MediaRouter.checkCallingThread();
            ArrayList<IntentFilter> r5 = this.mControlFilters;
            int r1 = r5.size();
            int r2 = 0;
        L6:
            if (r2 >= r1) goto L13;
            IntentFilter r3 = r5.get(r2);
            r2 = r2 + 1;
            IntentFilter r32 = r3;
            if (r32.hasCategory(r6) == false) goto L6;
            if (r32.hasAction(r7) == false) goto L6;
            return true;
        L13:
            return false;
        L14:
            f$$ExternalSyntheticBUOutline1.m("action must not be null");
            return false;
        L16:
            f$$ExternalSyntheticBUOutline1.m("category must not be null");
            return false;
        }

        public boolean supportsControlCategory(String r5) {
            if (r5 == null) goto L11;
            MediaRouter.checkCallingThread();
            ArrayList<IntentFilter> r4 = this.mControlFilters;
            int r1 = r4.size();
            int r2 = 0;
        L5:
            if (r2 >= r1) goto L10;
            IntentFilter r3 = r4.get(r2);
            r2 = r2 + 1;
            if (r3.hasCategory(r5) == false) goto L5;
            return true;
        L10:
            return false;
        L11:
            f$$ExternalSyntheticBUOutline1.m("category must not be null");
            return false;
        }

        public boolean supportsControlRequest(Intent r8) {
            if (r8 == null) goto L10;
            MediaRouter.checkCallingThread();
            ContentResolver r1 = MediaRouter.getGlobalRouter().getContentResolver();
            ArrayList<IntentFilter> r7 = this.mControlFilters;
            int r2 = r7.size();
            int r3 = 0;
        L5:
            if (r3 >= r2) goto L9;
            IntentFilter r4 = r7.get(r3);
            r3 = r3 + 1;
            if (r4.match(r1, r8, true, MediaRouter.TAG) < 0) goto L5;
            return true;
        L9:
            return false;
        L10:
            f$$ExternalSyntheticBUOutline1.m("intent must not be null");
            return false;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("MediaRouter.RouteInfo{ uniqueId=");
            r0.append(this.mUniqueId);
            r0.append(", name=");
            r0.append(this.mName);
            r0.append(", description=");
            r0.append(this.mDescription);
            r0.append(", iconUri=");
            r0.append(this.mIconUri);
            r0.append(", enabled=");
            r0.append(this.mEnabled);
            r0.append(", isSystemRoute=");
            r0.append(this.mIsSystemRoute);
            r0.append(", connectionState=");
            r0.append(this.mConnectionState);
            r0.append(", canDisconnect=");
            r0.append(this.mCanDisconnect);
            r0.append(", playbackType=");
            r0.append(this.mPlaybackType);
            r0.append(", playbackStream=");
            r0.append(this.mPlaybackStream);
            r0.append(", deviceType=");
            r0.append(this.mDeviceType);
            r0.append(", volumeHandling=");
            r0.append(this.mVolumeHandling);
            r0.append(", volume=");
            r0.append(this.mVolume);
            r0.append(", volumeMax=");
            r0.append(this.mVolumeMax);
            r0.append(", presentationDisplayId=");
            r0.append(this.mPresentationDisplayId);
            r0.append(", extras=");
            r0.append(this.mExtras);
            r0.append(", settingsIntent=");
            r0.append(this.mSettingsIntent);
            r0.append(", providerPackageName=");
            r0.append(this.mProvider.getPackageName());
            if (isGroup() == false) goto L13;
            r0.append(", members=[");
            int r1 = this.mSelectedRoutesInGroup.size();
            int r2 = 0;
        L5:
            if (r2 >= r1) goto L12;
            if (r2 <= 0) goto L9;
            r0.append(", ");
        L9:
            if (this.mSelectedRoutesInGroup.get(r2) == this) goto L11;
            r0.append(this.mSelectedRoutesInGroup.get(r2).getId());
        L11:
            r2 = r2 + 1;
            goto L5
        L12:
            r0.append(']');
        L13:
            r0.append(" }");
            return r0.toString();
        }

        public int updateDescriptor(MediaRouteDescriptor r8) {
            this.mDescriptor = r8;
            boolean r0 = false;
            if (r8 != null) goto L5;
            return 0;
        L5:
            if (ObjectsCompat.equals(this.mName, r8.getName()) == true) goto L7;
            this.mName = r8.getName();
            int r1 = 1;
        L9:
            if (ObjectsCompat.equals(this.mDescription, r8.getDescription()) == true) goto L12;
            this.mDescription = r8.getDescription();
            r1 = 1;
        L12:
            if (ObjectsCompat.equals(this.mIconUri, r8.getIconUri()) == true) goto L15;
            this.mIconUri = r8.getIconUri();
            r1 = 1;
        L15:
            if (this.mEnabled == r8.isEnabled()) goto L18;
            this.mEnabled = r8.isEnabled();
            r1 = 1;
        L18:
            if (this.mConnectionState == r8.getConnectionState()) goto L21;
            this.mConnectionState = r8.getConnectionState();
            r1 = 1;
        L21:
            if (isSameControlFilters(this.mControlFilters, r8.getControlFilters()) == true) goto L24;
            this.mControlFilters.clear();
            this.mControlFilters.addAll(r8.getControlFilters());
            r1 = 1;
        L24:
            if (this.mPlaybackType == r8.getPlaybackType()) goto L27;
            this.mPlaybackType = r8.getPlaybackType();
            r1 = 1;
        L27:
            if (this.mPlaybackStream == r8.getPlaybackStream()) goto L30;
            this.mPlaybackStream = r8.getPlaybackStream();
            r1 = 1;
        L30:
            if (this.mDeviceType == r8.getDeviceType()) goto L32;
            this.mDeviceType = r8.getDeviceType();
            r1 = 1;
        L32:
            int r5 = 3;
            if (this.mVolumeHandling == r8.getVolumeHandling()) goto L36;
            this.mVolumeHandling = r8.getVolumeHandling();
            r1 = 3;
        L36:
            if (this.mVolume == r8.getVolume()) goto L39;
            this.mVolume = r8.getVolume();
            r1 = 3;
        L39:
            if (this.mVolumeMax == r8.getVolumeMax()) goto L41;
            this.mVolumeMax = r8.getVolumeMax();
        L43:
            if (this.mPresentationDisplayId == r8.getPresentationDisplayId()) goto L46;
            this.mPresentationDisplayId = r8.getPresentationDisplayId();
            this.mPresentationDisplay = null;
            r5 = r5 | 5;
        L46:
            if (ObjectsCompat.equals(this.mExtras, r8.getExtras()) == true) goto L49;
            this.mExtras = r8.getExtras();
            r5 = r5 | 1;
        L49:
            if (ObjectsCompat.equals(this.mSettingsIntent, r8.getSettingsActivity()) == true) goto L52;
            this.mSettingsIntent = r8.getSettingsActivity();
            r5 = r5 | 1;
        L52:
            if (this.mCanDisconnect == r8.canDisconnectAndKeepPlaying()) goto L54;
            this.mCanDisconnect = r8.canDisconnectAndKeepPlaying();
            r5 = r5 | 5;
        L54:
            List<String> r82 = r8.getGroupMemberIds();
            ArrayList r12 = new ArrayList();
            if (r82.size() == this.mSelectedRoutesInGroup.size()) goto L58;
            r0 = true;
        L58:
            if (r82.isEmpty() == true) goto L69;
            GlobalMediaRouter r3 = MediaRouter.getGlobalRouter();
            Iterator<String> r83 = r82.iterator();
        L61:
            if (r83.hasNext() == false) goto L69;
            RouteInfo r4 = r3.getRoute(r3.getUniqueId(getProvider(), r83.next()));
            if (r4 == null) goto L61;
            r12.add(r4);
            if (r0 == true) goto L61;
            if (this.mSelectedRoutesInGroup.contains(r4) == true) goto L61;
            r0 = true;
        L69:
            if (r0 == false) goto L72;
            this.mSelectedRoutesInGroup = r12;
            return r5 | 1;
        L72:
            return r5;
        L41:
            r5 = r1;
            goto L43
        L7:
            r1 = 0;
            goto L9
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRouter(Context r2) {
        this.mCallbackRecords = new ArrayList();
        this.mContext = r2;
    }

    public static void checkCallingThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("The media router service must only be accessed on the application's main thread.");
    }

    private int findCallbackRecord(Callback r4) {
        int r0 = this.mCallbackRecords.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (this.mCallbackRecords.get(r1).mCallback == r4) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    public static int getGlobalCallbackCount() {
        if (sGlobal != null) goto L7;
        return 0;
    L7:
        return getGlobalRouter().getCallbackCount();
    }

    public static GlobalMediaRouter getGlobalRouter() {
        GlobalMediaRouter r0 = sGlobal;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("getGlobalRouter cannot be called when sGlobal is null");
        return null;
    }

    public static MediaRouter getInstance(Context r2) {
        if (r2 == null) goto L8;
        checkCallingThread();
        if (sGlobal != null) goto L7;
        sGlobal = new GlobalMediaRouter(r2.getApplicationContext());
    L7:
        return sGlobal.getRouter(r2);
    L8:
        f$$ExternalSyntheticBUOutline1.m("context must not be null");
        return null;
    }

    public static boolean isGroupVolumeUxEnabled() {
        if (sGlobal != null) goto L7;
        return false;
    L7:
        return getGlobalRouter().isGroupVolumeUxEnabled();
    }

    public static boolean isMediaTransferEnabled() {
        if (sGlobal != null) goto L7;
        return false;
    L7:
        return getGlobalRouter().isMediaTransferEnabled();
    }

    public static boolean isTransferToLocalEnabled() {
        return getGlobalRouter().isTransferToLocalEnabled();
    }

    public static void resetGlobalRouter() {
        GlobalMediaRouter r0 = sGlobal;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.reset();
        sGlobal = null;
    }

    public void addCallback(MediaRouteSelector r2, Callback r3) {
        addCallback(r2, r3, 0);
    }

    public void addCallback(MediaRouteSelector r4, Callback r5, int r6) {
        if (r4 == null) goto L24;
        if (r5 == null) goto L22;
        checkCallingThread();
        int r0 = findCallbackRecord(r5);
        if (r0 >= 0) goto L7;
        CallbackRecord r02 = new CallbackRecord(this, r5);
        this.mCallbackRecords.add(r02);
    L8:
        boolean r52 = true;
        if (r6 == r02.mFlags) goto L11;
        r02.mFlags = r6;
        boolean r3 = true;
    L12:
        long r1 = SystemClock.elapsedRealtime();
        if ((r6 & 1) == 0) goto L15;
        r3 = true;
    L15:
        r02.mTimestamp = r1;
        if (r02.mSelector.contains(r4) == true) goto L18;
        r02.mSelector = new MediaRouteSelector.Builder(r02.mSelector).addSelector(r4).build();
    L19:
        if (r52 == false) goto L26;
        getGlobalRouter().updateDiscoveryRequest();
        return;
    L26:
        return;
    L18:
        r52 = r3;
        goto L19
    L11:
        r3 = false;
        goto L12
    L7:
        r02 = this.mCallbackRecords.get(r0);
        goto L8
    L22:
        f$$ExternalSyntheticBUOutline1.m("callback must not be null");
        return;
    L24:
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
    }

    public void addProvider(MediaRouteProvider r1) {
        if (r1 == null) goto L5;
        checkCallingThread();
        getGlobalRouter().addProvider(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("providerInstance must not be null");
    }

    @Deprecated
    public void addRemoteControlClient(Object r1) {
        if (r1 == null) goto L5;
        checkCallingThread();
        getGlobalRouter().addRemoteControlClient((RemoteControlClient) r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("remoteControlClient must not be null");
    }

    public void addRouteToSelectedGroup(RouteInfo r1) {
        if (r1 == null) goto L5;
        checkCallingThread();
        getGlobalRouter().addRouteToSelectedGroup(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("route must not be null");
    }

    public RouteInfo getBluetoothRoute() {
        checkCallingThread();
        return getGlobalRouter().getBluetoothRoute();
    }

    public List<GroupRouteInfo> getConnectedGroupRoutes() {
        checkCallingThread();
        return getGlobalRouter().getConnectedGroupRoutes();
    }

    public RouteInfo getDefaultRoute() {
        checkCallingThread();
        return getGlobalRouter().getDefaultRoute();
    }

    public MediaSessionCompat.Token getMediaSessionToken() {
        GlobalMediaRouter r0 = sGlobal;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getMediaSessionToken();
    }

    public List<ProviderInfo> getProviders() {
        checkCallingThread();
        return getGlobalRouter().getProviders();
    }

    public MediaRouterParams getRouterParams() {
        checkCallingThread();
        return getGlobalRouter().getRouterParams();
    }

    public List<RouteInfo> getRoutes() {
        checkCallingThread();
        return getGlobalRouter().getRoutes();
    }

    public RouteInfo getSelectedRoute() {
        checkCallingThread();
        return getGlobalRouter().getSelectedRoute();
    }

    public boolean isRouteAvailable(MediaRouteSelector r1, int r2) {
        if (r1 == null) goto L5;
        checkCallingThread();
        return getGlobalRouter().isRouteAvailable(r1, r2);
    L5:
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
        return false;
    }

    public void removeCallback(Callback r1) {
        if (r1 == null) goto L7;
        checkCallingThread();
        int r12 = findCallbackRecord(r1);
        if (r12 < 0) goto L9;
        this.mCallbackRecords.remove(r12);
        getGlobalRouter().updateDiscoveryRequest();
        return;
    L9:
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("callback must not be null");
    }

    public void removeProvider(MediaRouteProvider r1) {
        if (r1 == null) goto L5;
        checkCallingThread();
        getGlobalRouter().removeProvider(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("providerInstance must not be null");
    }

    @Deprecated
    public void removeRemoteControlClient(Object r1) {
        if (r1 == null) goto L5;
        checkCallingThread();
        getGlobalRouter().removeRemoteControlClient((RemoteControlClient) r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("remoteControlClient must not be null");
    }

    public void removeRouteFromSelectedGroup(RouteInfo r1) {
        if (r1 == null) goto L5;
        checkCallingThread();
        getGlobalRouter().removeRouteFromSelectedGroup(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("route must not be null");
    }

    public void selectRoute(RouteInfo r1) {
        r1.select();
    }

    public void setMediaSession(Object r1) {
        checkCallingThread();
        getGlobalRouter().setMediaSession(r1);
    }

    public void setMediaSessionCompat(MediaSessionCompat r1) {
        checkCallingThread();
        getGlobalRouter().setMediaSessionCompat(r1);
    }

    public void setOnPrepareTransferListener(OnPrepareTransferListener r1) {
        checkCallingThread();
        getGlobalRouter().mOnPrepareTransferListener = r1;
    }

    public void setRouteListingPreference(RouteListingPreference r1) {
        checkCallingThread();
        getGlobalRouter().setRouteListingPreference(r1);
    }

    public void setRouterParams(MediaRouterParams r1) {
        checkCallingThread();
        getGlobalRouter().setRouterParams(r1);
    }

    public void transferToRoute(RouteInfo r1) {
        if (r1 == null) goto L5;
        checkCallingThread();
        getGlobalRouter().transferToRoute(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("route must not be null");
    }

    public void unselect(int r3) {
        if (r3 >= 0) goto L4;
    L9:
        f$$ExternalSyntheticBUOutline1.m("Unsupported reason to unselect route");
        return;
    L4:
        if (r3 > 3) goto L9;
        checkCallingThread();
        GlobalMediaRouter r2 = getGlobalRouter();
        RouteInfo r0 = r2.chooseFallbackRoute();
        if (r2.getSelectedRoute() == r0) goto L11;
        r2.selectRoute(r0, r3, true);
        return;
    }

    public RouteInfo updateSelectedRoute(MediaRouteSelector r3) {
        if (r3 == null) goto L10;
        checkCallingThread();
        GlobalMediaRouter r2 = getGlobalRouter();
        RouteInfo r0 = r2.getSelectedRoute();
        if (r0.isDefaultOrBluetooth() == false) goto L6;
    L9:
        return r0;
    L6:
        if (r0.matchesSelector(r3) == true) goto L9;
        RouteInfo r32 = r2.chooseFallbackRoute();
        r2.selectRoute(r32, 3, true);
        return r32;
    L10:
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
        return null;
    }
}
