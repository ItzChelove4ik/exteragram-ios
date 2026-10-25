package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.RemoteControlClient;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.display.DisplayManagerCompat;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import androidx.media.VolumeProviderCompat;
import androidx.mediarouter.media.MediaRoute2Provider;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher;
import androidx.mediarouter.media.RemoteControlClientCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class GlobalMediaRouter implements PlatformMediaRouter1RouteProvider.SyncCallback, RegisteredMediaRouteProviderWatcher.Callback {
    static final boolean DEBUG = false;
    static final String TAG = "AxMediaRouter";
    private MediaRouterActiveScanThrottlingHelper mActiveScanThrottlingHelper;
    private final Context mApplicationContext;
    private MediaRouter.RouteInfo mBluetoothRoute;
    private int mCallbackCount;
    final CallbackHandler mCallbackHandler;
    private MediaSessionCompat mCompatSession;
    private MediaRouter.RouteInfo mDefaultRoute;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private MediaRouteDiscoveryRequest mDiscoveryRequestForMr2Provider;
    private DisplayManagerCompat mDisplayManager;
    MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesListener;
    private final boolean mLowRam;
    private MediaSessionRecord mMediaSession;
    private MediaRoute2Provider mMr2Provider;
    MediaRouter.OnPrepareTransferListener mOnPrepareTransferListener;
    private PlatformMediaRouter1RouteProvider mPlatformMediaRouter1RouteProvider;
    private final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo;
    private final ProviderCallback mProviderCallback;
    private final ArrayList<MediaRouter.ProviderInfo> mProviders;
    RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
    private final ArrayList<RemoteControlClientRecord> mRemoteControlClients;
    private MediaRouter.RouteInfo mRequestedRoute;
    private MediaRouteProvider.RouteController mRequestedRouteController;
    final Map<String, MediaRouteProvider.RouteController> mRouteControllerMap;
    private final Map<String, RouteConnection> mRouteIdToRouteConnectionMap;
    private MediaRouterParams mRouterParams;
    private final ArrayList<WeakReference<MediaRouter>> mRouters;
    private final ArrayList<MediaRouter.RouteInfo> mRoutes;
    MediaRouter.RouteInfo mSelectedRoute;
    MediaRouteProvider.RouteController mSelectedRouteController;
    MediaRouter.PrepareTransferNotifier mTransferNotifier;
    private final boolean mTransferReceiverDeclared;
    private final Map<Pair<String, String>, String> mUniqueIdMap;
    private boolean mUseMediaRouter2ForSystemRouting;

    /* renamed from: androidx.mediarouter.media.GlobalMediaRouter$1, reason: invalid class name */
    public class AnonymousClass1 implements MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener {
        final /* synthetic */ GlobalMediaRouter this$0;

        public AnonymousClass1(GlobalMediaRouter r1) {
            this.this$0 = r1;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
        public void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController r11, MediaRouteDescriptor r12, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r13) {
            if (r11 != GlobalMediaRouter.access$300(this.this$0)) goto L10;
            if (r12 == null) goto L10;
            MediaRouter.ProviderInfo r112 = GlobalMediaRouter.access$400(this.this$0).getProvider();
            String r0 = r12.getId();
            MediaRouter.GroupRouteInfo r4 = new MediaRouter.GroupRouteInfo(r112, r0, this.this$0.assignRouteUniqueId(r112, r0));
            r4.maybeUpdateDescriptor(r12);
            GlobalMediaRouter r2 = this.this$0;
            if (r2.mSelectedRoute == r4) goto L20;
            r2.notifyTransfer(r2, r4, GlobalMediaRouter.access$300(r2), 3, true, GlobalMediaRouter.access$400(this.this$0), r13);
            GlobalMediaRouter.access$402(this.this$0, null);
            GlobalMediaRouter.access$302(this.this$0, null);
            return;
        L20:
            return;
        L10:
            GlobalMediaRouter r132 = this.this$0;
            if (r11 != r132.mSelectedRouteController) goto L18;
            if (r12 == null) goto L14;
            r132.updateRouteDescriptorAndNotify(r132.mSelectedRoute, r12);
        L14:
            MediaRouter.GroupRouteInfo r10 = this.this$0.mSelectedRoute.asGroup();
            if (r10 == null) goto L19;
            r10.updateDynamicDescriptors(r13);
            return;
        L19:
            return;
        }
    }

    public final class CallbackHandler extends Handler {
        public static final int MSG_PROVIDER_ADDED = 513;
        public static final int MSG_PROVIDER_CHANGED = 515;
        public static final int MSG_PROVIDER_REMOVED = 514;
        public static final int MSG_ROUTER_PARAMS_CHANGED = 769;
        public static final int MSG_ROUTE_ADDED = 257;
        public static final int MSG_ROUTE_ANOTHER_SELECTED = 264;
        public static final int MSG_ROUTE_CHANGED = 259;
        public static final int MSG_ROUTE_CONNECTED = 265;
        public static final int MSG_ROUTE_DISCONNECTED = 266;
        public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
        public static final int MSG_ROUTE_REMOVED = 258;
        public static final int MSG_ROUTE_SELECTED = 262;
        public static final int MSG_ROUTE_UNSELECTED = 263;
        public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
        private static final int MSG_TYPE_MASK = 65280;
        private static final int MSG_TYPE_PROVIDER = 512;
        private static final int MSG_TYPE_ROUTE = 256;
        private static final int MSG_TYPE_ROUTER = 768;
        private final List<MediaRouter.RouteInfo> mDynamicGroupRoutes;
        private final ArrayList<MediaRouter.CallbackRecord> mTempCallbackRecords;
        final /* synthetic */ GlobalMediaRouter this$0;

        public CallbackHandler(GlobalMediaRouter r1) {
            this.this$0 = r1;
            this.mTempCallbackRecords = new ArrayList();
            this.mDynamicGroupRoutes = new ArrayList();
        }

        private void invokeCallback(MediaRouter.CallbackRecord r5, int r6, Object r7, int r8) {
            MediaRouter r4 = r5.mRouter;
            MediaRouter.Callback r0 = r5.mCallback;
            int r1 = MSG_TYPE_MASK & r6;
            if (r1 == 256) goto L24;
            if (r1 != 512) goto L7;
            MediaRouter.ProviderInfo r72 = (MediaRouter.ProviderInfo) r7;
            switch(r6) {
                case 513: goto L21;
                case 514: goto L19;
                case 515: goto L17;
                default: goto L66;
            };
        L17:
            r0.onProviderChanged(r4, r72);
            return;
        L19:
            r0.onProviderRemoved(r4, r72);
            return;
        L21:
            r0.onProviderAdded(r4, r72);
            return;
        L66:
            return;
        L7:
            if (r1 == 768) goto L10;
            return;
        L10:
            if (r6 != 769) goto L65;
            r0.onRouterParamsChanged(r4, (MediaRouterParams) r7);
            return;
        L65:
            return;
        L24:
            if (r6 != 264) goto L26;
        L36:
            RouteSelectedMessageParams r73 = (RouteSelectedMessageParams) r7;
            MediaRouter.RouteInfo r12 = r73.mTargetRoute;
            MediaRouter.RouteInfo r74 = r73.mFromOrRequestedRoute;
        L35:
            MediaRouter.RouteInfo r3 = r12;
            MediaRouter.RouteInfo r13 = r74;
            MediaRouter.RouteInfo r75 = r3;
        L37:
            if (r75 != null) goto L39;
            return;
        L39:
            if (r5.filterRouteEvent(r75, r6, r13, r8) == false) goto L67;
            switch(r6) {
                case 257: goto L61;
                case 258: goto L59;
                case 259: goto L57;
                case 260: goto L55;
                case 261: goto L53;
                case 262: goto L51;
                case 263: goto L49;
                case 264: goto L47;
                case 265: goto L45;
                case 266: goto L43;
                default: goto L68;
            };
        L43:
            r0.onRouteDisconnected(r4, r13, r75, r8);
            return;
        L45:
            r0.onRouteConnected(r4, r13, r75);
            return;
        L47:
            r0.onRouteSelected(r4, r75, r8, r13);
            return;
        L49:
            r0.onRouteUnselected(r4, r75, r8);
            return;
        L51:
            r0.onRouteSelected(r4, r75, r8, r75);
            return;
        L53:
            r0.onRoutePresentationDisplayChanged(r4, r75);
            return;
        L55:
            r0.onRouteVolumeChanged(r4, r75);
            return;
        L57:
            r0.onRouteChanged(r4, r75);
            return;
        L59:
            r0.onRouteRemoved(r4, r75);
            return;
        L61:
            r0.onRouteAdded(r4, r75);
            return;
        L68:
            return;
        L67:
            return;
        L26:
            if (r6 == 262) goto L36;
            if (r6 != 265) goto L31;
        L34:
            RouteConnectionMessageParams r76 = (RouteConnectionMessageParams) r7;
            r12 = r76.mRequestedRoute;
            r74 = r76.mTargetRoute;
            goto L35
        L31:
            if (r6 == 266) goto L34;
            r75 = (MediaRouter.RouteInfo) r7;
            r13 = null;
            goto L37
        }

        private void syncWithPlatformMediaRouter1RouteProvider(int r2, Object r3) {
            if (r2 != 262) goto L5;
            RouteSelectedMessageParams r32 = (RouteSelectedMessageParams) r3;
            MediaRouter.RouteInfo r22 = r32.mTargetRoute;
            if (r32.mSyncMediaRoute1Provider == false) goto L22;
            GlobalMediaRouter.access$1100(this.this$0).onSyncRouteSelected(r22);
        L22:
            if (GlobalMediaRouter.access$1200(this.this$0) != null) goto L24;
            return;
        L24:
            if (r22.isDefaultOrBluetooth() == false) goto L34;
            Iterator<MediaRouter.RouteInfo> r23 = this.mDynamicGroupRoutes.iterator();
        L27:
            if (r23.hasNext() == false) goto L29;
            MediaRouter.RouteInfo r33 = r23.next();
            GlobalMediaRouter.access$1100(this.this$0).onSyncRouteRemoved(r33);
            goto L27
        L29:
            this.mDynamicGroupRoutes.clear();
            return;
        L34:
            return;
        L5:
            if (r2 == 264) goto L14;
            switch(r2) {
                case 257: goto L12;
                case 258: goto L10;
                case 259: goto L8;
                default: goto L35;
            };
        L8:
            GlobalMediaRouter.access$1100(this.this$0).onSyncRouteChanged((MediaRouter.RouteInfo) r3);
            return;
        L10:
            GlobalMediaRouter.access$1100(this.this$0).onSyncRouteRemoved((MediaRouter.RouteInfo) r3);
            return;
        L12:
            GlobalMediaRouter.access$1100(this.this$0).onSyncRouteAdded((MediaRouter.RouteInfo) r3);
            return;
        L35:
            return;
        L14:
            RouteSelectedMessageParams r34 = (RouteSelectedMessageParams) r3;
            MediaRouter.RouteInfo r24 = r34.mTargetRoute;
            this.mDynamicGroupRoutes.add(r24);
            GlobalMediaRouter.access$1100(this.this$0).onSyncRouteAdded(r24);
            if (r34.mSyncMediaRoute1Provider == false) goto L32;
            GlobalMediaRouter.access$1100(this.this$0).onSyncRouteSelected(r24);
            return;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r7) {
            int r0 = r7.what;
            Object r1 = r7.obj;
            int r72 = r7.arg1;
            if (r0 == 259) goto L5;
        L7:
            syncWithPlatformMediaRouter1RouteProvider(r0, r1);
            int r2 = GlobalMediaRouter.access$1000(this.this$0).size();     // Catch: Throwable -> L14
        L9:
            r2 = r2 - 1;
            if (r2 < 0) goto L17;
            MediaRouter r3 = (MediaRouter) ((WeakReference) GlobalMediaRouter.access$1000(this.this$0).get(r2)).get();     // Catch: Throwable -> L14
            if (r3 == null) goto L13;
            this.mTempCallbackRecords.addAll(r3.mCallbackRecords);     // Catch: Throwable -> L14
            goto L9
        L13:
            GlobalMediaRouter.access$1000(this.this$0).remove(r2);     // Catch: Throwable -> L14
            goto L9
        L17:
            ArrayList<MediaRouter.CallbackRecord> r22 = this.mTempCallbackRecords;     // Catch: Throwable -> L14
            int r32 = r22.size();     // Catch: Throwable -> L14
            int r4 = 0;
        L18:
            if (r4 >= r32) goto L21;
            MediaRouter.CallbackRecord r5 = r22.get(r4);     // Catch: Throwable -> L14
            r4 = r4 + 1;     // Catch: Throwable -> L14
            invokeCallback(r5, r0, r1, r72);     // Catch: Throwable -> L14
            goto L18
        L21:
            this.mTempCallbackRecords.clear();
            return;
        L14:
            th = move-exception;
            this.mTempCallbackRecords.clear();
            throw th;
        L5:
            if (this.this$0.getSelectedRoute().getId().equals(((MediaRouter.RouteInfo) r1).getId()) == false) goto L7;
            this.this$0.updateSelectedRouteIfNeeded(true);
            goto L7
        }

        public void post(int r1, Object r2) {
            obtainMessage(r1, r2).sendToTarget();
        }

        public void post(int r1, Object r2, int r3) {
            Message r0 = obtainMessage(r1, r2);
            r0.arg1 = r3;
            r0.sendToTarget();
        }

        public void postAnotherRouteSelectedMessage(MediaRouter.RouteInfo r3, MediaRouter.RouteInfo r4, int r5, boolean r6) {
            Message r2 = obtainMessage(MSG_ROUTE_ANOTHER_SELECTED, new RouteSelectedMessageParams(r3, r4, r6, null));
            r2.arg1 = r5;
            r2.sendToTarget();
        }

        public void postRouteConnectedMessage(MediaRouter.RouteInfo r3, MediaRouter.RouteInfo r4) {
            obtainMessage(MSG_ROUTE_CONNECTED, new RouteConnectionMessageParams(r3, r4, null)).sendToTarget();
        }

        public void postRouteDisconnectedMessage(MediaRouter.RouteInfo r3, MediaRouter.RouteInfo r4, int r5) {
            Message r2 = obtainMessage(MSG_ROUTE_DISCONNECTED, new RouteConnectionMessageParams(r3, r4, null));
            r2.arg1 = r5;
            r2.sendToTarget();
        }

        public void postRouteSelectedMessage(MediaRouter.RouteInfo r3, MediaRouter.RouteInfo r4, int r5, boolean r6) {
            Message r2 = obtainMessage(MSG_ROUTE_SELECTED, new RouteSelectedMessageParams(r3, r4, r6, null));
            r2.arg1 = r5;
            r2.sendToTarget();
        }
    }

    public final class MediaSessionRecord {
        private int mControlType;
        private int mMaxVolume;
        private final MediaSessionCompat mMsCompat;
        private VolumeProviderCompat mVpCompat;
        final /* synthetic */ GlobalMediaRouter this$0;

        /* renamed from: androidx.mediarouter.media.GlobalMediaRouter$MediaSessionRecord$1, reason: invalid class name */
        public class AnonymousClass1 extends VolumeProviderCompat {
            final /* synthetic */ MediaSessionRecord this$1;

            /* renamed from: $r8$lambda$RwT-hTJ1_-jY_0YuYRdD-WofhYI, reason: not valid java name */
            public static /* synthetic */ void m928$r8$lambda$RwThTJ1_jY_0YuYRdDWofhYI(AnonymousClass1 r0, int r1) {
                MediaRouter.RouteInfo r02 = r0.this$1.this$0.mSelectedRoute;
                if (r02 == null) goto L6;
                r02.requestSetVolume(r1);
                return;
            }

            public static /* synthetic */ void $r8$lambda$wrZvJ8MvyiExqf3cdiLr1GYD96A(AnonymousClass1 r0, int r1) {
                MediaRouter.RouteInfo r02 = r0.this$1.this$0.mSelectedRoute;
                if (r02 == null) goto L6;
                r02.requestUpdateVolume(r1);
                return;
            }

            public AnonymousClass1(MediaSessionRecord r1, int r2, int r3, int r4, String r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // androidx.media.VolumeProviderCompat
            public void onAdjustVolume(int r3) {
                this.this$1.this$0.mCallbackHandler.post(new GlobalMediaRouter$MediaSessionRecord$1$$ExternalSyntheticLambda1(this, r3));
            }

            @Override // androidx.media.VolumeProviderCompat
            public void onSetVolumeTo(int r3) {
                this.this$1.this$0.mCallbackHandler.post(new GlobalMediaRouter$MediaSessionRecord$1$$ExternalSyntheticLambda0(this, r3));
            }
        }

        public MediaSessionRecord(GlobalMediaRouter r1, MediaSessionCompat r2) {
            this.this$0 = r1;
            this.mMsCompat = r2;
        }

        public MediaSessionRecord(GlobalMediaRouter r2, Object r3) {
            this(r2, MediaSessionCompat.fromMediaSession(GlobalMediaRouter.access$600(r2), r3));
        }

        public void clearVolumeHandling() {
            MediaSessionCompat r0 = this.mMsCompat;
            if (r0 == null) goto L6;
            r0.setPlaybackToLocal(GlobalMediaRouter.access$700(this.this$0).playbackStream);
            this.mVpCompat = null;
            return;
        }

        public void configureVolume(int r9, int r10, int r11, String r12) {
            if (this.mMsCompat == null) goto L14;
            VolumeProviderCompat r0 = this.mVpCompat;
            if (r0 != null) goto L7;
        L12:
            AnonymousClass1 r2 = new AnonymousClass1(this, r9, r10, r11, r12);
            this.mVpCompat = r2;
            this.mMsCompat.setPlaybackToRemote(r2);
            return;
        L7:
            if (r9 != this.mControlType) goto L12;
            if (r10 != this.mMaxVolume) goto L12;
            r0.setCurrentVolume(r11);
            return;
        }

        public MediaSessionCompat.Token getToken() {
            MediaSessionCompat r0 = this.mMsCompat;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getSessionToken();
        }
    }

    public final class Mr2ProviderCallback extends MediaRoute2Provider.Callback {
        final /* synthetic */ GlobalMediaRouter this$0;

        public Mr2ProviderCallback(GlobalMediaRouter r1) {
            this.this$0 = r1;
        }

        @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
        public void onReleaseController(MediaRouteProvider.RouteController r2) {
            if (r2 != this.this$0.mSelectedRouteController) goto L6;
            selectRouteToFallbackRoute(2);
            return;
        L6:
            String r1 = GlobalMediaRouter.TAG;
        }

        @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
        public void onSelectFallbackRoute(int r1) {
            selectRouteToFallbackRoute(r1);
        }

        @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
        public void onSelectRoute(String r5, int r6) {
            Iterator<MediaRouter.RouteInfo> r0 = this.this$0.getRoutes().iterator();
        L4:
            if (r0.hasNext() == false) goto L11;
            MediaRouter.RouteInfo r1 = r0.next();
            if (r1.getProviderInstance() != GlobalMediaRouter.access$500(this.this$0)) goto L4;
            if (TextUtils.equals(r5, r1.getDescriptorId()) == false) goto L4;
        L12:
            if (r1 != null) goto L15;
            Log.w("AxMediaRouter", "onSelectRoute: The target RouteInfo is not found for descriptorId=" + r5);
            return;
        L15:
            this.this$0.selectRouteInternal(r1, r6, true);
            return;
        L11:
            r1 = null;
            goto L12
        }

        public void selectRouteToFallbackRoute(int r3) {
            MediaRouter.RouteInfo r0 = this.this$0.chooseFallbackRoute();
            if (this.this$0.getSelectedRoute() == r0) goto L6;
            this.this$0.selectRouteInternal(r0, r3, true);
            return;
        }
    }

    public final class ProviderCallback extends MediaRouteProvider.Callback {
        final /* synthetic */ GlobalMediaRouter this$0;

        public ProviderCallback(GlobalMediaRouter r1) {
            this.this$0 = r1;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.Callback
        public void onDescriptorChanged(MediaRouteProvider r1, MediaRouteProviderDescriptor r2) {
            this.this$0.updateProviderDescriptor(r1, r2);
        }
    }

    public final class RemoteControlClientRecord implements RemoteControlClientCompat.VolumeCallback {
        private boolean mDisconnected;
        private final RemoteControlClientCompat mRccCompat;
        final /* synthetic */ GlobalMediaRouter this$0;

        public RemoteControlClientRecord(GlobalMediaRouter r1, RemoteControlClient r2) {
            this.this$0 = r1;
            RemoteControlClientCompat r12 = RemoteControlClientCompat.obtain(GlobalMediaRouter.access$600(r1), r2);
            this.mRccCompat = r12;
            r12.setVolumeCallback(this);
            updatePlaybackInfo();
        }

        public void disconnect() {
            this.mDisconnected = true;
            this.mRccCompat.setVolumeCallback(null);
        }

        public RemoteControlClient getRemoteControlClient() {
            return this.mRccCompat.getRemoteControlClient();
        }

        @Override // androidx.mediarouter.media.RemoteControlClientCompat.VolumeCallback
        public void onVolumeSetRequest(int r2) {
            if (this.mDisconnected == true) goto L8;
            MediaRouter.RouteInfo r1 = this.this$0.mSelectedRoute;
            if (r1 == null) goto L9;
            r1.requestSetVolume(r2);
            return;
        L9:
            return;
        }

        @Override // androidx.mediarouter.media.RemoteControlClientCompat.VolumeCallback
        public void onVolumeUpdateRequest(int r2) {
            if (this.mDisconnected == true) goto L8;
            MediaRouter.RouteInfo r1 = this.this$0.mSelectedRoute;
            if (r1 == null) goto L9;
            r1.requestUpdateVolume(r2);
            return;
        L9:
            return;
        }

        public void updatePlaybackInfo() {
            this.mRccCompat.setPlaybackInfo(GlobalMediaRouter.access$700(this.this$0));
        }
    }

    public class RouteConnection implements MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener {
        private static final long ROUTE_CONNECTION_TIMEOUT_MS = 20000;
        private final MediaRouteProvider.DynamicGroupRouteController mController;
        private MediaRouter.GroupRouteInfo mGroupRoute;
        private final Handler mHandler;
        private final MediaRouter.RouteInfo mRequestedRoute;
        private final Runnable mRouteConnectionTimeoutRunnable;
        private final Map<String, MediaRouteProvider.RouteController> mRouteIdToMemberControllerMap;
        final /* synthetic */ GlobalMediaRouter this$0;

        /* renamed from: $r8$lambda$MJjsbslfZOw1iZZnAVrDz8XX-sc, reason: not valid java name */
        public static /* synthetic */ void m929$r8$lambda$MJjsbslfZOw1iZZnAVrDz8XXsc(RouteConnection r0) {
            r0.routeConnectionTimeout();
        }

        public RouteConnection(GlobalMediaRouter r1, MediaRouter.RouteInfo r2, MediaRouteProvider.DynamicGroupRouteController r3) {
            this.this$0 = r1;
            this.mRequestedRoute = r2;
            this.mController = r3;
            this.mRouteIdToMemberControllerMap = new HashMap();
            this.mHandler = new Handler(Looper.getMainLooper());
            this.mRouteConnectionTimeoutRunnable = new GlobalMediaRouter$RouteConnection$$ExternalSyntheticLambda0(this);
        }

        public static /* synthetic */ MediaRouteProvider.DynamicGroupRouteController access$000(RouteConnection r0) {
            return r0.mController;
        }

        public static /* synthetic */ Map access$100(RouteConnection r0) {
            return r0.mRouteIdToMemberControllerMap;
        }

        public static /* synthetic */ MediaRouter.GroupRouteInfo access$200(RouteConnection r0) {
            return r0.mGroupRoute;
        }

        private void cancelRouteConnectionTimeoutRunnable() {
            this.mHandler.removeCallbacks(this.mRouteConnectionTimeoutRunnable);
        }

        private MediaRouter.GroupRouteInfo convertFromRouteDescriptorToRouteInfo(MediaRouteDescriptor r4) {
            MediaRouter.ProviderInfo r0 = this.mRequestedRoute.getProvider();
            String r1 = r4.getId();
            MediaRouter.GroupRouteInfo r2 = new MediaRouter.GroupRouteInfo(r0, r1, this.this$0.assignRouteUniqueId(r0, r1));
            r2.maybeUpdateDescriptor(r4);
            return r2;
        }

        private void createAndConnectMemberRouteController(MediaRouter.RouteInfo r4) {
            if (this.mRouteIdToMemberControllerMap.containsKey(r4.mUniqueId) == false) goto L5;
            return;
        L5:
            if (this.mGroupRoute == null) goto L13;
            MediaRouteProvider.RouteController r0 = this.mRequestedRoute.getProviderInstance().onCreateRouteController(r4.getDescriptorId(), this.mGroupRoute.getDescriptorId());
            if (r0 == null) goto L12;
            this.mRouteIdToMemberControllerMap.put(r4.mUniqueId, r0);
            r0.onSelect();
            return;
        L12:
            return;
        }

        private void disconnectAndRemoveMemberRouteController(String r3) {
            MediaRouteProvider.RouteController r0 = this.mRouteIdToMemberControllerMap.get(r3);
            if (r0 != null) goto L5;
            return;
        L5:
            r0.onUnselect(1);
            r0.onRelease();
            this.mRouteIdToMemberControllerMap.remove(r3);
        }

        private void notifyRouteConnected() {
            cancelRouteConnectionTimeoutRunnable();
            this.this$0.mCallbackHandler.postRouteConnectedMessage(this.mRequestedRoute, (MediaRouter.RouteInfo) Preconditions.checkNotNull(this.mGroupRoute));
        }

        private void notifyRouteDisconnected(int r3) {
            cancelRouteConnectionTimeoutRunnable();
            this.this$0.mCallbackHandler.postRouteDisconnectedMessage(this.mRequestedRoute, this.mGroupRoute, r3);
        }

        private void routeConnectionTimeout() {
            this.mController.onUnselect(1);
            this.mController.onRelease();
            notifyRouteDisconnected(7);
        }

        private void scheduleRouteConnectionTimeoutRunnable() {
            this.mHandler.postDelayed(this.mRouteConnectionTimeoutRunnable, ROUTE_CONNECTION_TIMEOUT_MS);
        }

        private MediaRouteDescriptor updateGroupMemberIdsIfNeeded(MediaRouteDescriptor r5, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r6) {
            if (r5 != null) goto L4;
            return r5;
        L4:
            boolean r1 = false;
            if (r5.getGroupMemberIds().size() != r6.size()) goto L7;
            boolean r4 = true;
        L8:
            if (r4 == false) goto L15;
            Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r0 = r6.iterator();
        L11:
            if (r0.hasNext() == false) goto L15;
            if (r5.getGroupMemberIds().contains(r0.next().getRouteDescriptor().getId()) == true) goto L11;
        L16:
            if (r1 == false) goto L18;
            return r5;
        L18:
            ArrayList r42 = new ArrayList();
            Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r62 = r6.iterator();
        L20:
            if (r62.hasNext() == false) goto L23;
            r42.add(r62.next().getRouteDescriptor().getId());
            goto L20
        L23:
            return new MediaRouteDescriptor.Builder(r5).clearGroupMemberIds().addGroupMemberIds(r42).build();
        L15:
            r1 = r4;
            goto L16
        L7:
            r4 = false;
            goto L8
        }

        private void updateMemberRouteControllers() {
            if (this.mGroupRoute == null) goto L15;
            HashSet r0 = new HashSet(this.mRouteIdToMemberControllerMap.keySet());
            Iterator<MediaRouter.RouteInfo> r1 = this.mGroupRoute.mSelectedRoutesInGroup.iterator();
        L7:
            if (r1.hasNext() == false) goto L11;
            MediaRouter.RouteInfo r2 = r1.next();
            r0.remove(r2.mUniqueId);
            if (this.mRouteIdToMemberControllerMap.containsKey(r2.mUniqueId) == true) goto L7;
            createAndConnectMemberRouteController(r2);
            goto L7
        L11:
            Iterator r02 = r0.iterator();
        L13:
            if (r02.hasNext() == false) goto L22;
            disconnectAndRemoveMemberRouteController((String) r02.next());
            goto L13
        L22:
            return;
        }

        public void connect() {
            this.mController.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(GlobalMediaRouter.access$600(this.this$0)), this);
            this.mController.onSelect();
            scheduleRouteConnectionTimeoutRunnable();
        }

        public void disconnect() {
            Iterator<MediaRouteProvider.RouteController> r0 = this.mRouteIdToMemberControllerMap.values().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            MediaRouteProvider.RouteController r1 = r0.next();
            r1.onUnselect(1);
            r1.onRelease();
            goto L4
        L6:
            this.mRouteIdToMemberControllerMap.clear();
            this.mController.onUnselect(1);
            this.mController.onRelease();
            notifyRouteDisconnected(1);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
        public void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController r2, MediaRouteDescriptor r3, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r4) {
            if (this.mController == r2) goto L5;
            return;
        L5:
            MediaRouteDescriptor r22 = updateGroupMemberIdsIfNeeded(r3, r4);
            if (r22 == null) goto L8;
        L11:
            MediaRouter.GroupRouteInfo r32 = this.mGroupRoute;
            if (r32 != null) goto L14;
            MediaRouter.GroupRouteInfo r23 = convertFromRouteDescriptorToRouteInfo(r22);
            this.mGroupRoute = r23;
            r23.updateDynamicDescriptors(r4);
            notifyRouteConnected();
        L15:
            updateMemberRouteControllers();
            return;
        L14:
            this.this$0.updateRouteDescriptorAndNotify(r32, r22);
            this.mGroupRoute.updateDynamicDescriptors(r4);
            goto L15
        L8:
            if (this.mGroupRoute != null) goto L11;
            Log.e("AxMediaRouter", "Failed route connection will null groupRouteDescriptor. Requested route: " + this.mRequestedRoute);
        }
    }

    public static final class RouteConnectionMessageParams {
        public final MediaRouter.RouteInfo mRequestedRoute;
        public final MediaRouter.RouteInfo mTargetRoute;

        private RouteConnectionMessageParams(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2) {
            this.mRequestedRoute = r1;
            this.mTargetRoute = r2;
        }

        public /* synthetic */ RouteConnectionMessageParams(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2, AnonymousClass1 r3) {
            this(r1, r2);
        }
    }

    public static final class RouteSelectedMessageParams {
        public final MediaRouter.RouteInfo mFromOrRequestedRoute;
        public final boolean mSyncMediaRoute1Provider;
        public final MediaRouter.RouteInfo mTargetRoute;

        private RouteSelectedMessageParams(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2, boolean r3) {
            this.mFromOrRequestedRoute = r1;
            this.mTargetRoute = r2;
            this.mSyncMediaRoute1Provider = r3;
        }

        public /* synthetic */ RouteSelectedMessageParams(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2, boolean r3, AnonymousClass1 r4) {
            this(r1, r2, r3);
        }
    }

    static {
        Log.isLoggable("AxMediaRouter", 3);
    }

    public GlobalMediaRouter(Context r5) {
        this.mCallbackHandler = new CallbackHandler(this);
        this.mRouteControllerMap = new HashMap();
        this.mRouters = new ArrayList();
        this.mRoutes = new ArrayList();
        this.mRouteIdToRouteConnectionMap = new HashMap();
        this.mUniqueIdMap = new HashMap();
        this.mProviders = new ArrayList();
        this.mRemoteControlClients = new ArrayList();
        this.mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
        this.mProviderCallback = new ProviderCallback(this);
        this.mDynamicRoutesListener = new AnonymousClass1(this);
        this.mApplicationContext = r5;
        this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager) r5.getSystemService("activity"));
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 30) goto L5;
    L7:
        boolean r2 = false;
    L8:
        this.mTransferReceiverDeclared = r2;
        this.mUseMediaRouter2ForSystemRouting = SystemRoutingUsingMediaRouter2Receiver.isDeclared(r5);
        if (r0 < 30) goto L12;
        if (r2 == false) goto L12;
        MediaRoute2Provider r02 = new MediaRoute2Provider(r5, new Mr2ProviderCallback(this));
    L13:
        this.mMr2Provider = r02;
        this.mPlatformMediaRouter1RouteProvider = PlatformMediaRouter1RouteProvider.obtain(r5, this);
        start();
        return;
    L12:
        r02 = null;
        goto L13
    L5:
        if (MediaTransferReceiver.isDeclared(r5) == false) goto L7;
        r2 = true;
        goto L8
    }

    public static /* synthetic */ ArrayList access$1000(GlobalMediaRouter r0) {
        return r0.mRouters;
    }

    public static /* synthetic */ PlatformMediaRouter1RouteProvider access$1100(GlobalMediaRouter r0) {
        return r0.mPlatformMediaRouter1RouteProvider;
    }

    public static /* synthetic */ MediaRouter.RouteInfo access$1200(GlobalMediaRouter r0) {
        return r0.mDefaultRoute;
    }

    public static /* synthetic */ MediaRouteProvider.RouteController access$300(GlobalMediaRouter r0) {
        return r0.mRequestedRouteController;
    }

    public static /* synthetic */ MediaRouteProvider.RouteController access$302(GlobalMediaRouter r0, MediaRouteProvider.RouteController r1) {
        r0.mRequestedRouteController = r1;
        return r1;
    }

    public static /* synthetic */ MediaRouter.RouteInfo access$400(GlobalMediaRouter r0) {
        return r0.mRequestedRoute;
    }

    public static /* synthetic */ MediaRouter.RouteInfo access$402(GlobalMediaRouter r0, MediaRouter.RouteInfo r1) {
        r0.mRequestedRoute = r1;
        return r1;
    }

    public static /* synthetic */ MediaRoute2Provider access$500(GlobalMediaRouter r0) {
        return r0.mMr2Provider;
    }

    public static /* synthetic */ Context access$600(GlobalMediaRouter r0) {
        return r0.mApplicationContext;
    }

    public static /* synthetic */ RemoteControlClientCompat.PlaybackInfo access$700(GlobalMediaRouter r0) {
        return r0.mPlaybackInfo;
    }

    private void addProvider(MediaRouteProvider r3, boolean r4) {
        if (findProviderInfo(r3) != null) goto L6;
        MediaRouter.ProviderInfo r0 = new MediaRouter.ProviderInfo(r3, r4);
        this.mProviders.add(r0);
        this.mCallbackHandler.post(CallbackHandler.MSG_PROVIDER_ADDED, r0);
        updateProviderContents(r0, r3.getDescriptor());
        r3.setCallback(this.mProviderCallback);
        r3.setDiscoveryRequest(this.mDiscoveryRequest);
        return;
    }

    private MediaRouter.ProviderInfo findProviderInfo(MediaRouteProvider r5) {
        ArrayList<MediaRouter.ProviderInfo> r4 = this.mProviders;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        MediaRouter.ProviderInfo r2 = r4.get(r1);
        r1 = r1 + 1;
        MediaRouter.ProviderInfo r22 = r2;
        if (r22.mProviderInstance != r5) goto L3;
        return r22;
    L7:
        return null;
    }

    private int findRemoteControlClientRecord(RemoteControlClient r4) {
        int r0 = this.mRemoteControlClients.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (this.mRemoteControlClients.get(r1).getRemoteControlClient() == r4) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    private int findRouteByUniqueId(String r4) {
        int r0 = this.mRoutes.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (this.mRoutes.get(r1).mUniqueId.equals(r4) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    private RouteConnection getRouteConnection(MediaRouter.GroupRouteInfo r3) {
        Iterator<RouteConnection> r2 = this.mRouteIdToRouteConnectionMap.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        RouteConnection r0 = r2.next();
        if (RouteConnection.access$200(r0) != r3) goto L4;
        return r0;
    L8:
        return null;
    }

    private MediaRouteProvider.RouteController getRouteController(MediaRouter.RouteInfo r3) {
        if (r3 != this.mSelectedRoute) goto L8;
        MediaRouteProvider.RouteController r0 = this.mSelectedRouteController;
        if (r0 == null) goto L8;
        return r0;
    L8:
        if ((r3 instanceof MediaRouter.GroupRouteInfo) == false) goto L17;
        MediaRouter.GroupRouteInfo r02 = (MediaRouter.GroupRouteInfo) r3;
        if (r02.isConnected() == false) goto L17;
        RouteConnection r2 = getRouteConnection(r02);
        if (r2 != null) goto L14;
        return null;
    L14:
        return RouteConnection.access$000(r2);
    L17:
        MediaRouteProvider.RouteController r03 = this.mRouteControllerMap.get(r3.mUniqueId);
        if (r03 == null) goto L20;
        return r03;
    L20:
        Iterator<RouteConnection> r22 = this.mRouteIdToRouteConnectionMap.values().iterator();
    L22:
        if (r22.hasNext() == false) goto L25;
        r03 = (MediaRouteProvider.RouteController) RouteConnection.access$100(r22.next()).get(r3.mUniqueId);
        if (r03 == null) goto L22;
    L25:
        return r03;
    }

    private boolean isRouteSelected(MediaRouter.RouteInfo r4) {
        MediaRouter.RouteInfo r3 = this.mSelectedRoute;
        if (r3 != r4) goto L5;
        return true;
    L5:
        if (r3 == null) goto L7;
        MediaRouter.GroupRouteInfo r32 = r3.asGroup();
    L9:
        if (r32 != null) goto L11;
    L19:
        return false;
    L11:
        if (r32.getSelectedRoutesInGroup().size() != 1) goto L19;
        int r33 = r32.getSelectionState(r4);
        if (r33 != 3) goto L15;
    L18:
        return true;
    L15:
        if (r33 == 2) goto L18;
        return false;
    L7:
        r32 = null;
        goto L9
    }

    private boolean isSystemDefaultRoute(MediaRouter.RouteInfo r2) {
        if (r2.getProviderInstance() == this.mPlatformMediaRouter1RouteProvider) goto L5;
        return false;
    L5:
        if (r2.mDescriptorId.equals(PlatformMediaRouter1RouteProvider.DEFAULT_ROUTE_ID) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isSystemLiveAudioOnlyRoute(MediaRouter.RouteInfo r2) {
        if (r2.getProviderInstance() == this.mPlatformMediaRouter1RouteProvider) goto L5;
        return false;
    L5:
        if (r2.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) == true) goto L7;
        return false;
    L7:
        if (r2.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO) == true) goto L13;
        return true;
    L13:
        return false;
    }

    private void notifyRouteConnectionFailed(MediaRouter.RouteInfo r2, int r3) {
        this.mCallbackHandler.postRouteDisconnectedMessage(r2, null, r3);
    }

    private void setMediaSessionRecord(MediaSessionRecord r2) {
        MediaSessionRecord r0 = this.mMediaSession;
        if (r0 == null) goto L5;
        r0.clearVolumeHandling();
    L5:
        this.mMediaSession = r2;
        if (r2 == null) goto L9;
        updatePlaybackInfoFromSelectedRoute();
        return;
    }

    private void start() {
        this.mActiveScanThrottlingHelper = new MediaRouterActiveScanThrottlingHelper(new GlobalMediaRouter$$ExternalSyntheticLambda0(this));
        addProvider(this.mPlatformMediaRouter1RouteProvider, true);
        MediaRoute2Provider r0 = this.mMr2Provider;
        if (r0 == null) goto L5;
        addProvider(r0, true);
    L5:
        RegisteredMediaRouteProviderWatcher r02 = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, this);
        this.mRegisteredProviderWatcher = r02;
        r02.start();
    }

    private void updateMr2ProviderDiscoveryRequest(MediaRouteSelector r2, boolean r3) {
        if (isMediaTransferEnabled() == false) goto L22;
        MediaRouteDiscoveryRequest r0 = this.mDiscoveryRequestForMr2Provider;
        if (r0 == null) goto L13;
        if (r0.getSelector().equals(r2) == false) goto L13;
        if (this.mDiscoveryRequestForMr2Provider.isActiveScan() != r3) goto L13;
        return;
    L13:
        if (r2.isEmpty() == false) goto L19;
        if (r3 == true) goto L19;
        if (this.mDiscoveryRequestForMr2Provider != null) goto L18;
        return;
    L18:
        this.mDiscoveryRequestForMr2Provider = null;
    L20:
        this.mMr2Provider.setDiscoveryRequest(this.mDiscoveryRequestForMr2Provider);
        return;
    L19:
        this.mDiscoveryRequestForMr2Provider = new MediaRouteDiscoveryRequest(r2, r3);
        goto L20
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateProviderContents(MediaRouter.ProviderInfo r14, MediaRouteProviderDescriptor r15) {
        if (r14.updateDescriptor(r15) == true) goto L5;
        return;
    L5:
        int r1 = 0;
        if (r15 != null) goto L8;
    L49:
        if (r15 == null) goto L51;
        String r152 = "Ignoring invalid provider descriptor: " + r15;
    L52:
        Log.w("AxMediaRouter", r152);
        boolean r153 = false;
    L53:
        int r0 = r14.mRoutes.size() - 1;
    L54:
        if (r0 < r1) goto L56;
        MediaRouter.RouteInfo r3 = r14.mRoutes.get(r0);
        r3.maybeUpdateDescriptor(null);
        this.mRoutes.remove(r3);
        r0 = r0 - 1;
        goto L54
    L56:
        updateSelectedRouteIfNeeded(r153);
        int r154 = r14.mRoutes.size() - 1;
    L57:
        if (r154 < r1) goto L59;
        this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_REMOVED, r14.mRoutes.remove(r154));
        r154 = r154 - 1;
        goto L57
    L59:
        this.mCallbackHandler.post(CallbackHandler.MSG_PROVIDER_CHANGED, r14);
        return;
    L51:
        r152 = "Ignoring null provider descriptor from " + r14.getComponentName();
        goto L52
    L8:
        if (r15.isValid() == false) goto L10;
    L11:
        List<MediaRouteDescriptor> r155 = r15.getRoutes();
        ArrayList r32 = new ArrayList();
        ArrayList r4 = new ArrayList();
        Iterator<MediaRouteDescriptor> r156 = r155.iterator();
        int r5 = 0;
        boolean r6 = false;
    L13:
        if (r156.hasNext() == false) goto L38;
        MediaRouteDescriptor r7 = r156.next();
        if (r7 == null) goto L37;
        if (r7.isValid() == false) goto L37;
        String r9 = r7.getId();
        int r10 = r14.findRouteIndexByDescriptorId(r9);
        if (r10 < 0) goto L21;
        if (r10 < r5) goto L27;
        MediaRouter.RouteInfo r8 = r14.mRoutes.get(r10);
        int r11 = r5 + 1;
        Collections.swap(r14.mRoutes, r10, r5);
        if (r7.getGroupMemberIds().isEmpty() == true) goto L32;
        r4.add(new Pair(r8, r7));
    L36:
        r5 = r11;
        goto L13
    L32:
        if (updateRouteDescriptorAndNotify(r8, r7) == 0) goto L36;
        if (r8 != this.mSelectedRoute) goto L36;
        r6 = true;
        goto L36
    L27:
        Log.w("AxMediaRouter", "Ignoring route descriptor with duplicate id: " + r7);
        goto L13
    L21:
        MediaRouter.RouteInfo r112 = new MediaRouter.RouteInfo(r14, r9, assignRouteUniqueId(r14, r9), r7.isSystemRoute());
        int r102 = r5 + 1;
        r14.mRoutes.add(r5, r112);
        this.mRoutes.add(r112);
        if (r7.getGroupMemberIds().isEmpty() == true) goto L24;
        r32.add(new Pair(r112, r7));
    L25:
        r5 = r102;
        goto L13
    L24:
        r112.maybeUpdateDescriptor(r7);
        this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_ADDED, r112);
    L37:
        Log.w("AxMediaRouter", "Ignoring invalid route descriptor: " + r7);
        goto L13
    L38:
        int r157 = r32.size();
        int r02 = 0;
    L39:
        if (r02 >= r157) goto L41;
        Object r72 = r32.get(r02);
        r02 = r02 + 1;
        Pair r73 = (Pair) r72;
        MediaRouter.RouteInfo r92 = (MediaRouter.RouteInfo) r73.first;
        r92.maybeUpdateDescriptor((MediaRouteDescriptor) r73.second);
        this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_ADDED, r92);
        goto L39
    L41:
        int r158 = r4.size();
        int r03 = 0;
        boolean r12 = r6;
    L42:
        if (r03 >= r158) goto L48;
        Object r33 = r4.get(r03);
        r03 = r03 + 1;
        Pair r34 = (Pair) r33;
        MediaRouter.RouteInfo r62 = (MediaRouter.RouteInfo) r34.first;
        if (updateRouteDescriptorAndNotify(r62, (MediaRouteDescriptor) r34.second) == 0) goto L42;
        if (r62 != this.mSelectedRoute) goto L42;
        r12 = true;
        goto L42
    L48:
        r153 = r12;
        r1 = r5;
        goto L53
    L10:
        if (r15 != this.mPlatformMediaRouter1RouteProvider.getDescriptor()) goto L49;
        goto L11
    }

    @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
    public void addProvider(MediaRouteProvider r2) {
        addProvider(r2, false);
    }

    public void addRemoteControlClient(RemoteControlClient r2) {
        if (findRemoteControlClientRecord(r2) >= 0) goto L6;
        RemoteControlClientRecord r0 = new RemoteControlClientRecord(this, r2);
        this.mRemoteControlClients.add(r0);
        return;
    }

    public int addRouteToGroup(MediaRouter.GroupRouteInfo r3, MediaRouter.RouteInfo r4) {
        if (r3.isGroupable(r4) == true) goto L7;
        Log.w("AxMediaRouter", "Ignoring attempt to add a non-groupable member route: " + r4);
        return 2;
    L7:
        if (r3.getSelectedRoutesInGroup().contains(r4) == false) goto L11;
        Log.w("AxMediaRouter", "Ignoring attempt to add an existing member route: " + r4);
        return 3;
    L11:
        if (r3.isSelected() == false) goto L18;
        MediaRouteProvider.RouteController r2 = this.mSelectedRouteController;
        if ((r2 instanceof MediaRouteProvider.DynamicGroupRouteController) == false) goto L15;
        ((MediaRouteProvider.DynamicGroupRouteController) r2).onAddMemberRoute(r4.getDescriptorId());
        return 1;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("There is no currently selected dynamic group route.");
        return 0;
    L18:
        if (r3.isConnected() == false) goto L26;
        RouteConnection r22 = getRouteConnection(r3);
        if (r22 != null) goto L23;
        Log.w("AxMediaRouter", "Ignoring attempt to add a route to a non-available connected route: " + r3);
        return 5;
    L23:
        RouteConnection.access$000(r22).onAddMemberRoute(r4.getDescriptorId());
        return 1;
    L26:
        Log.w("AxMediaRouter", "Ignoring attempt to add a route to an unsupported group route:" + r3);
        return 4;
    }

    public void addRouteToSelectedGroup(MediaRouter.RouteInfo r2) {
        MediaRouter.GroupRouteInfo r0 = this.mSelectedRoute.asGroup();
        if (r0 != null) goto L6;
        Log.w("AxMediaRouter", "Ignoring attempt to add a member route to a selected non-group route");
        return;
    L6:
        addRouteToGroup(r0, r2);
    }

    public String assignRouteUniqueId(MediaRouter.ProviderInfo r6, String r7) {
        String r0 = r6.getComponentName().flattenToShortString();
        if (r6.mTreatRouteDescriptorIdsAsUnique == false) goto L5;
        String r1 = r7;
    L7:
        if (r6.mTreatRouteDescriptorIdsAsUnique == false) goto L9;
    L17:
        this.mUniqueIdMap.put(new Pair(r0, r7), r1);
        return r1;
    L9:
        if (findRouteByUniqueId(r1) < 0) goto L17;
        Log.w("AxMediaRouter", "Either " + r7 + " isn't unique in " + r0 + " or we're trying to assign a unique ID for an already added route");
        int r62 = 2;
    L12:
        String r2 = String.format(Locale.US, "%s_%d", new Object[]{r1, Integer.valueOf(r62)});
        if (findRouteByUniqueId(r2) < 0) goto L14;
        r62 = r62 + 1;
        goto L12
    L14:
        this.mUniqueIdMap.put(new Pair(r0, r7), r2);
        return r2;
    L5:
        r1 = r0 + ":" + r7;
        goto L7
    }

    public MediaRouter.RouteInfo chooseFallbackRoute() {
        ArrayList<MediaRouter.RouteInfo> r0 = this.mRoutes;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L12;
        MediaRouter.RouteInfo r3 = r0.get(r2);
        r2 = r2 + 1;
        MediaRouter.RouteInfo r32 = r3;
        if (r32 == this.mDefaultRoute) goto L3;
        if (isSystemLiveAudioOnlyRoute(r32) == false) goto L3;
        if (r32.isSelectable() == false) goto L3;
        return r32;
    L12:
        return this.mDefaultRoute;
    }

    public void connectRoute(MediaRouter.RouteInfo r6) {
        if (this.mRoutes.contains(r6) == true) goto L7;
        Log.w("AxMediaRouter", "connectRoute: Failed for removed route: " + r6);
        notifyRouteConnectionFailed(r6, 2);
        return;
    L7:
        if (r6.mEnabled == true) goto L11;
        Log.w("AxMediaRouter", "connectRoute: Failed for disabled route: " + r6);
        notifyRouteConnectionFailed(r6, 3);
        return;
    L11:
        if (this.mSelectedRoute != r6) goto L14;
        Log.w("AxMediaRouter", "connectRoute: Ignoring for selected route: " + r6);
        notifyRouteConnectionFailed(r6, 4);
        return;
    L14:
        MediaRouter.ProviderInfo r0 = r6.getProvider();
        if (r0.supportsDynamicGroup() == true) goto L19;
        Log.w("AxMediaRouter", "connectRoute: Ignoring connection to non-dynamic provider: " + r0);
        notifyRouteConnectionFailed(r6, 5);
        return;
    L19:
        if (this.mRouteIdToRouteConnectionMap.containsKey(r6.getId()) == false) goto L21;
        return;
    L21:
        MediaRouteProvider.DynamicGroupRouteController r02 = r6.getProviderInstance().onCreateDynamicGroupRouteController(r6.mDescriptorId, new MediaRouteProvider.RouteControllerOptions.Builder().setClientPackageName(this.mApplicationContext.getPackageName()).build());
        if (r02 != null) goto L25;
        Log.w("AxMediaRouter", "connectRoute: controller == null for : " + r6);
        notifyRouteConnectionFailed(r6, 6);
        return;
    L25:
        RouteConnection r1 = new RouteConnection(this, r6, r02);
        r1.connect();
        this.mRouteIdToRouteConnectionMap.put(r6.getId(), r1);
    }

    public void disconnectRoute(MediaRouter.RouteInfo r3) {
        RouteConnection r0 = this.mRouteIdToRouteConnectionMap.get(r3.getId());
        if (r0 == null) goto L6;
        r0.disconnect();
        this.mRouteIdToRouteConnectionMap.remove(r3.getId());
        return;
    }

    public MediaRouter.RouteInfo getBluetoothRoute() {
        return this.mBluetoothRoute;
    }

    public int getCallbackCount() {
        return this.mCallbackCount;
    }

    public List<MediaRouter.GroupRouteInfo> getConnectedGroupRoutes() {
        ArrayList r0 = new ArrayList();
        Iterator<RouteConnection> r3 = this.mRouteIdToRouteConnectionMap.values().iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        RouteConnection r1 = r3.next();
        if (RouteConnection.access$200(r1) == null) goto L4;
        r0.add(RouteConnection.access$200(r1));
        goto L4
    L8:
        return r0;
    }

    public ContentResolver getContentResolver() {
        return this.mApplicationContext.getContentResolver();
    }

    public MediaRouter.RouteInfo getDefaultRoute() {
        MediaRouter.RouteInfo r0 = this.mDefaultRoute;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("There is no default route.  The media router has not yet been fully initialized.");
        return null;
    }

    public Display getDisplay(int r2) {
        if (this.mDisplayManager != null) goto L6;
        this.mDisplayManager = DisplayManagerCompat.getInstance(this.mApplicationContext);
    L6:
        return this.mDisplayManager.getDisplay(r2);
    }

    public MediaRoute2Provider getMediaRoute2ProviderForTesting() {
        return this.mMr2Provider;
    }

    public MediaSessionCompat.Token getMediaSessionToken() {
        MediaSessionRecord r0 = this.mMediaSession;
        if (r0 != null) goto L5;
        MediaSessionCompat r1 = this.mCompatSession;
        if (r1 != null) goto L9;
        return null;
    L9:
        return r1.getSessionToken();
    L5:
        return r0.getToken();
    }

    public List<MediaRouter.ProviderInfo> getProviders() {
        return this.mProviders;
    }

    public MediaRouter.RouteInfo getRoute(String r5) {
        ArrayList<MediaRouter.RouteInfo> r4 = this.mRoutes;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        MediaRouter.RouteInfo r2 = r4.get(r1);
        r1 = r1 + 1;
        MediaRouter.RouteInfo r22 = r2;
        if (r22.mUniqueId.equals(r5) == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public MediaRouter getRouter(Context r4) {
        int r0 = this.mRouters.size();
    L3:
        r0 = r0 - 1;
        if (r0 < 0) goto L11;
        MediaRouter r1 = this.mRouters.get(r0).get();
        if (r1 == null) goto L7;
        if (r1.mContext != r4) goto L3;
        return r1;
    L7:
        this.mRouters.remove(r0);
        goto L3
    L11:
        MediaRouter r02 = new MediaRouter(r4);
        this.mRouters.add(new WeakReference(r02));
        return r02;
    }

    public MediaRouterParams getRouterParams() {
        return this.mRouterParams;
    }

    public List<MediaRouter.RouteInfo> getRoutes() {
        return this.mRoutes;
    }

    public MediaRouter.RouteInfo getSelectedRoute() {
        MediaRouter.RouteInfo r0 = this.mSelectedRoute;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("There is no currently selected route.  The media router has not yet been fully initialized.");
        return null;
    }

    public String getUniqueId(MediaRouter.ProviderInfo r2, String r3) {
        String r22 = r2.getComponentName().flattenToShortString();
        return this.mUniqueIdMap.get(new Pair(r22, r3));
    }

    public boolean isGroupVolumeUxEnabled() {
        MediaRouterParams r2 = this.mRouterParams;
        if (r2 == null) goto L11;
        Bundle r22 = r2.mExtras;
        if (r22 == null) goto L11;
        if (r22.getBoolean(MediaRouterParams.ENABLE_GROUP_VOLUME_UX, true) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isMediaTransferEnabled() {
        if (this.mTransferReceiverDeclared == false) goto L10;
        MediaRouterParams r1 = this.mRouterParams;
        if (r1 != null) goto L7;
        return true;
    L7:
        if (r1.isMediaTransferReceiverEnabled() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isRouteAvailable(MediaRouteSelector r9, int r10) {
        if (r9.isEmpty() == false) goto L6;
        return false;
    L6:
        if ((r10 & 2) == 0) goto L8;
    L10:
        MediaRouterParams r0 = this.mRouterParams;
        if (r0 != null) goto L13;
    L17:
        boolean r02 = false;
    L18:
        int r3 = this.mRoutes.size();
        int r4 = 0;
    L19:
        if (r4 >= r3) goto L35;
        MediaRouter.RouteInfo r5 = this.mRoutes.get(r4);
        if ((r10 & 1) != 0) goto L23;
    L25:
        if (r02 == false) goto L32;
        if (r5.isDefaultOrBluetooth() == true) goto L32;
        if (r5.getProviderInstance() == this.mMr2Provider) goto L32;
    L34:
        r4 = r4 + 1;
    L32:
        if (r5.matchesSelector(r9) == false) goto L34;
        return true;
    L23:
        if (r5.isDefaultOrBluetooth() == false) goto L25;
    L35:
        return false;
    L13:
        if (r0.isOutputSwitcherEnabled() == false) goto L17;
        if (isMediaTransferEnabled() == false) goto L17;
        r02 = true;
        goto L18
    L8:
        if (this.mLowRam == false) goto L10;
        return true;
    }

    public boolean isTransferToLocalEnabled() {
        MediaRouterParams r0 = this.mRouterParams;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.isTransferToLocalEnabled();
    }

    public void maybeUpdateMemberRouteControllers() {
        if (this.mSelectedRoute.isGroup() == false) goto L23;
        List<MediaRouter.RouteInfo> r0 = this.mSelectedRoute.getSelectedRoutesInGroup();
        HashSet r1 = new HashSet();
        Iterator<MediaRouter.RouteInfo> r2 = r0.iterator();
    L7:
        if (r2.hasNext() == false) goto L9;
        r1.add(r2.next().mUniqueId);
        goto L7
    L9:
        Iterator<Map.Entry<String, MediaRouteProvider.RouteController>> r22 = this.mRouteControllerMap.entrySet().iterator();
    L11:
        if (r22.hasNext() == false) goto L15;
        Map.Entry<String, MediaRouteProvider.RouteController> r3 = r22.next();
        if (r1.contains(r3.getKey()) == true) goto L11;
        MediaRouteProvider.RouteController r32 = r3.getValue();
        r32.onUnselect(0);
        r32.onRelease();
        r22.remove();
        goto L11
    L15:
        Iterator<MediaRouter.RouteInfo> r02 = r0.iterator();
    L17:
        if (r02.hasNext() == false) goto L37;
        MediaRouter.RouteInfo r12 = r02.next();
        if (this.mRouteControllerMap.containsKey(r12.mUniqueId) == true) goto L17;
        MediaRouteProvider.RouteController r23 = r12.getProviderInstance().onCreateRouteController(r12.mDescriptorId, this.mSelectedRoute.mDescriptorId);
        if (r23 == null) goto L17;
        r23.onSelect();
        this.mRouteControllerMap.put(r12.mUniqueId, r23);
        goto L17
    L37:
        return;
    }

    public void notifyTransfer(GlobalMediaRouter r10, MediaRouter.RouteInfo r11, MediaRouteProvider.RouteController r12, int r13, boolean r14, MediaRouter.RouteInfo r15, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r16) {
        MediaRouter.PrepareTransferNotifier r0 = this.mTransferNotifier;
        if (r0 == null) goto L5;
        r0.cancel();
        this.mTransferNotifier = null;
    L5:
        MediaRouter.PrepareTransferNotifier r1 = new MediaRouter.PrepareTransferNotifier(r10, r11, r12, r13, r14, r15, r16);
        this.mTransferNotifier = r1;
        if (r1.mReason != 3) goto L16;
        MediaRouter.OnPrepareTransferListener r102 = this.mOnPrepareTransferListener;
        if (r102 == null) goto L16;
        ListenableFuture<Void> r103 = r102.onPrepareTransfer(this.mSelectedRoute, r1.mToRoute);
        MediaRouter.PrepareTransferNotifier r9 = this.mTransferNotifier;
        if (r103 != null) goto L14;
        r9.finishTransfer();
        return;
    L14:
        r9.setFuture(r103);
        return;
    L16:
        r1.finishTransfer();
    }

    @Override // androidx.mediarouter.media.PlatformMediaRouter1RouteProvider.SyncCallback
    public void onPlatformRouteSelectedByDescriptorId(String r3) {
        this.mCallbackHandler.removeMessages(CallbackHandler.MSG_ROUTE_SELECTED);
        MediaRouter.ProviderInfo r2 = findProviderInfo(this.mPlatformMediaRouter1RouteProvider);
        if (r2 == null) goto L8;
        MediaRouter.RouteInfo r22 = r2.findRouteByDescriptorId(r3);
        if (r22 == null) goto L9;
        r22.select(false);
        return;
    L9:
        return;
    }

    @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
    public void releaseProviderController(RegisteredMediaRouteProvider r2, MediaRouteProvider.RouteController r3) {
        if (this.mSelectedRouteController != r3) goto L6;
        selectRoute(chooseFallbackRoute(), 2, true);
        return;
    }

    @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
    public void removeProvider(MediaRouteProvider r3) {
        MediaRouter.ProviderInfo r0 = findProviderInfo(r3);
        if (r0 == null) goto L6;
        r3.setCallback(null);
        r3.setDiscoveryRequest(null);
        updateProviderContents(r0, null);
        this.mCallbackHandler.post(CallbackHandler.MSG_PROVIDER_REMOVED, r0);
        this.mProviders.remove(r0);
        return;
    }

    public void removeRemoteControlClient(RemoteControlClient r1) {
        int r12 = findRemoteControlClientRecord(r1);
        if (r12 < 0) goto L6;
        this.mRemoteControlClients.remove(r12).disconnect();
        return;
    }

    public int removeRouteFromGroup(MediaRouter.GroupRouteInfo r4, MediaRouter.RouteInfo r5) {
        if (r4.isUnselectable(r5) == true) goto L7;
        Log.w("AxMediaRouter", "Ignoring attempt to remove a non-unselectable member route: " + r5);
        return 2;
    L7:
        if (r4.getSelectedRoutesInGroup().contains(r5) == true) goto L11;
        Log.w("AxMediaRouter", "Ignoring attempt to remove a non-in-group member route: " + r5);
        return 3;
    L11:
        if (r4.getSelectedRoutesInGroup().size() > 1) goto L15;
        Log.w("AxMediaRouter", "Ignoring attempt to remove the last member route.");
        return 4;
    L15:
        if (r4.isSelected() == false) goto L22;
        MediaRouteProvider.RouteController r3 = this.mSelectedRouteController;
        if ((r3 instanceof MediaRouteProvider.DynamicGroupRouteController) == false) goto L19;
        ((MediaRouteProvider.DynamicGroupRouteController) r3).onRemoveMemberRoute(r5.getDescriptorId());
    L28:
        return 1;
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("There is no currently selected dynamic group route.");
        return 0;
    L22:
        if (r4.isConnected() == false) goto L29;
        RouteConnection r32 = getRouteConnection(r4);
        if (r32 != null) goto L27;
        Log.w("AxMediaRouter", "Ignoring attempt to update routes for a non-available connected route: " + r4);
        return 6;
    L27:
        RouteConnection.access$000(r32).onRemoveMemberRoute(r5.getDescriptorId());
        goto L28
    L29:
        Log.w("AxMediaRouter", "Ignoring attempt to remove a route from an unsupported group route:" + r4);
        return 5;
    }

    public void removeRouteFromSelectedGroup(MediaRouter.RouteInfo r2) {
        MediaRouter.GroupRouteInfo r0 = this.mSelectedRoute.asGroup();
        if (r0 != null) goto L6;
        Log.w("AxMediaRouter", "Ignoring attempt to remove a member route from a selected non-group route");
        return;
    L6:
        removeRouteFromGroup(r0, r2);
    }

    public void requestSetVolume(MediaRouter.RouteInfo r1, int r2) {
        MediaRouteProvider.RouteController r0 = getRouteController(r1);
        if (r0 == null) goto L6;
        r0.onSetVolume(r2);
        return;
    }

    public void requestUpdateVolume(MediaRouter.RouteInfo r1, int r2) {
        MediaRouteProvider.RouteController r0 = getRouteController(r1);
        if (r0 == null) goto L6;
        r0.onUpdateVolume(r2);
        return;
    }

    public void reset() {
        this.mActiveScanThrottlingHelper.reset();
        setRouteListingPreference(null);
        setMediaSessionCompat(null);
        this.mRegisteredProviderWatcher.stop();
        ArrayList<RemoteControlClientRecord> r1 = this.mRemoteControlClients;
        int r2 = r1.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        RemoteControlClientRecord r5 = r1.get(r4);
        r4 = r4 + 1;
        r5.disconnect();
        goto L3
    L5:
        ArrayList r12 = new ArrayList(this.mProviders);
        int r22 = r12.size();
    L6:
        if (r3 >= r22) goto L8;
        Object r42 = r12.get(r3);
        r3 = r3 + 1;
        removeProvider(((MediaRouter.ProviderInfo) r42).mProviderInstance);
        goto L6
    L8:
        this.mCallbackHandler.removeCallbacksAndMessages(null);
    }

    public void selectRoute(MediaRouter.RouteInfo r3, int r4, boolean r5) {
        if (this.mRoutes.contains(r3) == true) goto L7;
        Log.w("AxMediaRouter", "Ignoring attempt to select removed route: " + r3);
        return;
    L7:
        if (r3.mEnabled == true) goto L11;
        Log.w("AxMediaRouter", "Ignoring attempt to select disabled route: " + r3);
        return;
    L11:
        if (isRouteSelected(r3) == false) goto L15;
        Log.w("AxMediaRouter", "Ignoring attempt to select selected route: " + r3);
        return;
    L15:
        if (Build.VERSION.SDK_INT < 30) goto L22;
        MediaRouteProvider r0 = r3.getProviderInstance();
        MediaRoute2Provider r1 = this.mMr2Provider;
        if (r0 != r1) goto L22;
        if (this.mSelectedRoute == r3) goto L22;
        r1.transferTo(r3.getDescriptorId());
        return;
    L22:
        selectRouteInternal(r3, r4, r5);
    }

    public void selectRouteInternal(MediaRouter.RouteInfo r11, int r12, boolean r13) {
        if (this.mSelectedRoute != r11) goto L6;
        return;
    L6:
        if (r11 != this.mDefaultRoute) goto L8;
        boolean r1 = true;
    L10:
        if (this.mBluetoothRoute == null) goto L24;
        if (r1 == false) goto L24;
        StackTraceElement[] r14 = Thread.currentThread().getStackTrace();
        StringBuilder r3 = new StringBuilder("- Stacktrace: [");
        int r7 = 3;
    L14:
        if (r7 >= r14.length) goto L18;
        StackTraceElement r8 = r14[r7];
        r3.append(r8.getClassName());
        r3.append(".");
        r3.append(r8.getMethodName());
        r3.append(":");
        r3.append(r8.getLineNumber());
        r7 = r7 + 1;
        if (r7 >= r14.length) goto L14;
        r3.append(", ");
        goto L14
    L18:
        r3.append("]");
        MediaRouter.RouteInfo r15 = this.mSelectedRoute;
        if (r15 == null) goto L21;
        String r16 = String.format(Locale.US, "%s(BT=%b, syncMediaRoute1Provider=%b)", new Object[]{r15.getName(), Boolean.valueOf(this.mSelectedRoute.isBluetooth()), Boolean.valueOf(r13)});
    L22:
        Log.w("AxMediaRouter", "Changing selection(" + r16 + ") to default while BT is available: pkgName=" + this.mApplicationContext.getPackageName() + r3);
        goto L24
    L21:
        r16 = null;
    L24:
        if (this.mRequestedRoute == null) goto L29;
        this.mRequestedRoute = null;
        MediaRouteProvider.RouteController r17 = this.mRequestedRouteController;
        if (r17 == null) goto L29;
        r17.onUnselect(3);
        this.mRequestedRouteController.onRelease();
        this.mRequestedRouteController = null;
    L29:
        if (isMediaTransferEnabled() == true) goto L31;
    L37:
        MediaRouteProvider.RouteController r32 = r11.getProviderInstance().onCreateRouteController(r11.mDescriptorId, new MediaRouteProvider.RouteControllerOptions.Builder().setClientPackageName(this.mApplicationContext.getPackageName()).build());
        if (r32 == null) goto L41;
        r32.onSelect();
    L41:
        if (this.mSelectedRoute != null) goto L44;
        this.mSelectedRoute = r11;
        this.mSelectedRouteController = r32;
        this.mCallbackHandler.postRouteSelectedMessage(null, r11, r12, r13);
        return;
    L44:
        notifyTransfer(this, r11, r32, r12, r13, null, null);
        return;
    L31:
        if (r11.getProvider().supportsDynamicGroup() == false) goto L37;
        MediaRouteProvider.DynamicGroupRouteController r18 = r11.getProviderInstance().onCreateDynamicGroupRouteController(r11.mDescriptorId, new MediaRouteProvider.RouteControllerOptions.Builder().setClientPackageName(this.mApplicationContext.getPackageName()).build());
        if (r18 == null) goto L36;
        r18.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.mApplicationContext), this.mDynamicRoutesListener);
        this.mRequestedRoute = r11;
        this.mRequestedRouteController = r18;
        r18.onSelect();
        return;
    L36:
        Log.w("AxMediaRouter", "setSelectedRouteInternal: Failed to create dynamic group route controller. route=" + r11);
        goto L37
    L8:
        r1 = false;
        goto L10
    }

    public void sendControlRequest(MediaRouter.RouteInfo r2, Intent r3, MediaRouter.ControlRequestCallback r4) {
        MediaRouteProvider.RouteController r0 = getRouteController(r2);
        if (r0 != null) goto L5;
    L7:
        MediaRouter.PrepareTransferNotifier r1 = this.mTransferNotifier;
        if (r1 != null) goto L10;
    L16:
        if (r4 == null) goto L19;
        r4.onError(null, null);
        return;
    L19:
        return;
    L10:
        if (r2 != r1.mToRoute) goto L16;
        MediaRouteProvider.RouteController r12 = r1.mToRouteController;
        if (r12 == null) goto L16;
        if (r12.onControlRequest(r3, r4) == false) goto L16;
        return;
    L5:
        if (r0.onControlRequest(r3, r4) == false) goto L7;
    }

    public void setMediaSession(Object r2) {
        if (r2 == null) goto L4;
        MediaSessionRecord r0 = new MediaSessionRecord(this, r2);
    L5:
        setMediaSessionRecord(r0);
        return;
    L4:
        r0 = null;
        goto L5
    }

    public void setMediaSessionCompat(MediaSessionCompat r2) {
        this.mCompatSession = r2;
        if (r2 == null) goto L5;
        MediaSessionRecord r0 = new MediaSessionRecord(this, r2);
    L6:
        setMediaSessionRecord(r0);
        return;
    L5:
        r0 = null;
        goto L6
    }

    public void setRouteListingPreference(RouteListingPreference r3) {
        MediaRoute2Provider r2 = this.mMr2Provider;
        if (r2 != null) goto L5;
        return;
    L5:
        if (Build.VERSION.SDK_INT < 34) goto L9;
        r2.setRouteListingPreference(r3);
        return;
    }

    @SuppressLint({"NewApi"})
    public void setRouterParams(MediaRouterParams r6) {
        MediaRouterParams r0 = this.mRouterParams;
        this.mRouterParams = r6;
        boolean r1 = isMediaTransferEnabled();
        MediaRouteProvider r2 = this.mMr2Provider;
        if (r1 == false) goto L26;
        boolean r12 = true;
        if (r2 != null) goto L8;
        MediaRoute2Provider r22 = new MediaRoute2Provider(this.mApplicationContext, new Mr2ProviderCallback(this));
        this.mMr2Provider = r22;
        addProvider(r22, true);
        updateDiscoveryRequest();
    L8:
        if (r6 != null) goto L10;
    L12:
        boolean r3 = false;
    L13:
        this.mMr2Provider.setMediaTransferRestrictedToSelfProviders(r3);
        this.mRegisteredProviderWatcher.setMediaTransferRestrictedToSelfProviders(r3);
        if (r0 != null) goto L16;
    L18:
        boolean r02 = false;
    L19:
        if (r6 != null) goto L21;
    L23:
        r12 = false;
    L24:
        if (r02 == r12) goto L28;
        this.mMr2Provider.setDiscoveryRequestInternal(this.mDiscoveryRequestForMr2Provider);
    L28:
        this.mCallbackHandler.post(CallbackHandler.MSG_ROUTER_PARAMS_CHANGED, r6);
        return;
    L21:
        if (r6.isTransferToLocalEnabled() == false) goto L23;
    L16:
        if (r0.isTransferToLocalEnabled() == false) goto L18;
        r02 = true;
        goto L19
    L10:
        if (r6.isMediaTransferRestrictedToSelfProviders() == false) goto L12;
        r3 = true;
        goto L13
    L26:
        if (r2 == null) goto L28;
        removeProvider(r2);
        this.mMr2Provider = null;
        this.mRegisteredProviderWatcher.rescan();
        goto L28
    }

    public void transferToRoute(MediaRouter.RouteInfo r2) {
        MediaRouter.GroupRouteInfo r0 = this.mSelectedRoute.asGroup();
        if (r0 != null) goto L6;
        Log.w("AxMediaRouter", "Ignoring attempt to transfer for a selected non-group route");
        return;
    L6:
        updateRoutesForGroup(r0, Collections.singletonList(r2));
    }

    public void updateDiscoveryRequest() {
        MediaRouteSelector.Builder r0 = new MediaRouteSelector.Builder();
        this.mActiveScanThrottlingHelper.reset();
        int r1 = this.mRouters.size();
        int r2 = 0;
        int r3 = 0;
        boolean r4 = false;
    L3:
        r1 = r1 - 1;
        if (r1 < 0) goto L26;
        MediaRouter r5 = this.mRouters.get(r1).get();
        if (r5 == null) goto L7;
        int r6 = r5.mCallbackRecords.size();
        r3 = r3 + r6;
        int r7 = 0;
    L9:
        if (r7 >= r6) goto L3;
        MediaRouter.CallbackRecord r8 = r5.mCallbackRecords.get(r7);
        r0.addSelector(r8.mSelector);
        if ((r8.mFlags & 1) == 0) goto L13;
        boolean r9 = true;
    L14:
        this.mActiveScanThrottlingHelper.requestActiveScan(r9, r8.mTimestamp);
        if (r9 == false) goto L17;
        r4 = true;
    L17:
        int r82 = r8.mFlags;
        if ((r82 & 4) == 0) goto L23;
        if (this.mLowRam == true) goto L23;
        r4 = true;
    L23:
        if ((r82 & 8) == 0) goto L25;
        r4 = true;
    L25:
        r7 = r7 + 1;
        goto L9
    L13:
        r9 = false;
        goto L14
    L7:
        this.mRouters.remove(r1);
        goto L3
    L26:
        boolean r12 = this.mActiveScanThrottlingHelper.finalizeActiveScanAndScheduleSuppressActiveScanRunnable();
        this.mCallbackCount = r3;
        if (r4 == false) goto L29;
        MediaRouteSelector r32 = r0.build();
    L30:
        updateMr2ProviderDiscoveryRequest(r0.build(), r12);
        MediaRouteDiscoveryRequest r02 = this.mDiscoveryRequest;
        if (r02 == null) goto L38;
        if (r02.getSelector().equals(r32) == false) goto L38;
        if (this.mDiscoveryRequest.isActiveScan() != r12) goto L38;
        return;
    L38:
        if (r32.isEmpty() == false) goto L44;
        if (r12 == true) goto L44;
        if (this.mDiscoveryRequest == null) goto L70;
        this.mDiscoveryRequest = null;
    L45:
        if (r4 == false) goto L50;
        if (r12 == true) goto L50;
        if (this.mLowRam == false) goto L50;
        Log.i("AxMediaRouter", "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
    L50:
        ArrayList<MediaRouter.ProviderInfo> r03 = this.mProviders;
        int r13 = r03.size();
    L51:
        if (r2 >= r13) goto L71;
        MediaRouter.ProviderInfo r33 = r03.get(r2);
        r2 = r2 + 1;
        MediaRouteProvider r34 = r33.mProviderInstance;
        if (r34 == this.mMr2Provider) goto L51;
        r34.setDiscoveryRequest(this.mDiscoveryRequest);
        goto L51
    L71:
        return;
    L70:
        return;
    L44:
        this.mDiscoveryRequest = new MediaRouteDiscoveryRequest(r32, r12);
        goto L45
    L29:
        r32 = MediaRouteSelector.EMPTY;
        goto L30
    }

    @SuppressLint({"NewApi"})
    public void updatePlaybackInfoFromSelectedRoute() {
        MediaRouter.RouteInfo r0 = this.mSelectedRoute;
        if (r0 == null) goto L27;
        this.mPlaybackInfo.volume = r0.getVolume();
        this.mPlaybackInfo.volumeMax = this.mSelectedRoute.getVolumeMax();
        this.mPlaybackInfo.volumeHandling = this.mSelectedRoute.getVolumeHandling();
        this.mPlaybackInfo.playbackStream = this.mSelectedRoute.getPlaybackStream();
        this.mPlaybackInfo.playbackType = this.mSelectedRoute.getPlaybackType();
        if (isMediaTransferEnabled() == true) goto L7;
    L9:
        this.mPlaybackInfo.volumeControlId = null;
    L10:
        ArrayList<RemoteControlClientRecord> r02 = this.mRemoteControlClients;
        int r1 = r02.size();
        int r2 = 0;
        int r3 = 0;
    L11:
        if (r3 >= r1) goto L14;
        RemoteControlClientRecord r4 = r02.get(r3);
        r3 = r3 + 1;
        r4.updatePlaybackInfo();
        goto L11
    L14:
        if (this.mMediaSession != null) goto L16;
        return;
    L16:
        if (this.mSelectedRoute != getDefaultRoute()) goto L18;
    L25:
        this.mMediaSession.clearVolumeHandling();
        return;
    L18:
        if (this.mSelectedRoute == getBluetoothRoute()) goto L25;
        RemoteControlClientCompat.PlaybackInfo r03 = this.mPlaybackInfo;
        if (r03.volumeHandling != 1) goto L23;
        r2 = 2;
    L23:
        this.mMediaSession.configureVolume(r2, r03.volumeMax, r03.volume, r03.volumeControlId);
        return;
    L7:
        if (this.mSelectedRoute.getProviderInstance() != this.mMr2Provider) goto L9;
        this.mPlaybackInfo.volumeControlId = MediaRoute2Provider.getSessionIdForRouteController(this.mSelectedRouteController);
        goto L10
    L27:
        MediaSessionRecord r5 = this.mMediaSession;
        if (r5 == null) goto L33;
        r5.clearVolumeHandling();
        return;
    }

    public void updateProviderDescriptor(MediaRouteProvider r1, MediaRouteProviderDescriptor r2) {
        MediaRouter.ProviderInfo r12 = findProviderInfo(r1);
        if (r12 == null) goto L6;
        updateProviderContents(r12, r2);
        return;
    }

    public int updateRouteDescriptorAndNotify(MediaRouter.RouteInfo r3, MediaRouteDescriptor r4) {
        int r42 = r3.maybeUpdateDescriptor(r4);
        if (r42 != 0) goto L5;
    L13:
        return r42;
    L5:
        if ((r42 & 1) == 0) goto L8;
        this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_CHANGED, r3);
    L8:
        if ((r42 & 2) == 0) goto L11;
        this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_VOLUME_CHANGED, r3);
    L11:
        if ((r42 & 4) == 0) goto L13;
        this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED, r3);
        goto L13
    }

    public int updateRoutesForGroup(MediaRouter.GroupRouteInfo r6, List<MediaRouter.RouteInfo> r7) {
        ArrayList r0 = new ArrayList();
        Iterator<MediaRouter.RouteInfo> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L10;
        MediaRouter.RouteInfo r1 = r72.next();
        if (r6.isTransferable(r1) == false) goto L7;
        r0.add(r1.getDescriptorId());
        goto L4
    L7:
        Log.w("AxMediaRouter", "Ignoring attempt to update the group with a non-transferable route: " + r1);
        goto L4
    L10:
        if (r0.isEmpty() == false) goto L14;
        Log.w("AxMediaRouter", "Ignoring attempt to update the group with non-transferable routes");
        return 2;
    L14:
        if (r6.isSelected() == false) goto L21;
        MediaRouteProvider.RouteController r5 = this.mSelectedRouteController;
        if ((r5 instanceof MediaRouteProvider.DynamicGroupRouteController) == false) goto L18;
        ((MediaRouteProvider.DynamicGroupRouteController) r5).onUpdateMemberRoutes(r0);
        return 1;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("There is no currently selected dynamic group route.");
        return 0;
    L21:
        if (r6.isConnected() == false) goto L29;
        RouteConnection r52 = getRouteConnection(r6);
        if (r52 != null) goto L26;
        Log.w("AxMediaRouter", "Ignoring attempt to update routes for a non-available connected route: " + r6);
        return 4;
    L26:
        RouteConnection.access$000(r52).onUpdateMemberRoutes(r0);
        return 1;
    L29:
        Log.w("AxMediaRouter", "Ignoring attempt to update routes for an unsupported group route:" + r6);
        return 3;
    }

    public void updateSelectedRouteIfNeeded(boolean r9) {
        MediaRouter.RouteInfo r0 = this.mDefaultRoute;
        if (r0 == null) goto L8;
        if (r0.isSelectable() == true) goto L8;
        Log.i("AxMediaRouter", "Clearing the default route because it is no longer selectable: " + this.mDefaultRoute);
        this.mDefaultRoute = null;
    L8:
        if (this.mDefaultRoute != null) goto L16;
        ArrayList<MediaRouter.RouteInfo> r02 = this.mRoutes;
        int r4 = r02.size();
        int r5 = 0;
    L10:
        if (r5 >= r4) goto L16;
        MediaRouter.RouteInfo r6 = r02.get(r5);
        r5 = r5 + 1;
        MediaRouter.RouteInfo r62 = r6;
        if (isSystemDefaultRoute(r62) == false) goto L10;
        if (r62.isSelectable() == false) goto L10;
        this.mDefaultRoute = r62;
        Log.i("AxMediaRouter", "Found default route: " + this.mDefaultRoute);
    L16:
        MediaRouter.RouteInfo r03 = this.mBluetoothRoute;
        if (r03 == null) goto L22;
        if (r03.isSelectable() == true) goto L22;
        Log.i("AxMediaRouter", "Clearing the bluetooth route because it is no longer selectable: " + this.mBluetoothRoute);
        this.mBluetoothRoute = null;
    L22:
        if (this.mBluetoothRoute != null) goto L30;
        ArrayList<MediaRouter.RouteInfo> r04 = this.mRoutes;
        int r1 = r04.size();
        int r42 = 0;
    L24:
        if (r42 >= r1) goto L30;
        MediaRouter.RouteInfo r52 = r04.get(r42);
        r42 = r42 + 1;
        MediaRouter.RouteInfo r53 = r52;
        if (isSystemLiveAudioOnlyRoute(r53) == false) goto L24;
        if (r53.isSelectable() == false) goto L24;
        this.mBluetoothRoute = r53;
        Log.i("AxMediaRouter", "Found bluetooth route: " + this.mBluetoothRoute);
    L30:
        MediaRouter.RouteInfo r05 = this.mSelectedRoute;
        if (r05 != null) goto L33;
    L38:
        Log.i("AxMediaRouter", "Unselecting the current route because it is no longer selectable: " + this.mSelectedRoute);
        selectRouteInternal(chooseFallbackRoute(), 0, true);
        return;
    L33:
        if (r05.isEnabled() == false) goto L38;
        if (r9 == false) goto L52;
        maybeUpdateMemberRouteControllers();
        updatePlaybackInfoFromSelectedRoute();
        return;
    }
}
