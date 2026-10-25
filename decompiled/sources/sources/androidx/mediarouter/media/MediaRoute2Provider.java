package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.MediaRouter2$ControllerCallback;
import android.media.MediaRouter2$RouteCallback;
import android.media.MediaRouter2$TransferCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3;
import androidx.media3.exoplayer.audio.DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
class MediaRoute2Provider extends MediaRouteProvider {
    static final boolean DEBUG = false;
    private static final String PACKAGE_NAME_SEPARATOR = "/";
    static final String TAG = "MR2Provider";
    final Callback mCallback;
    private final MediaRouter2$ControllerCallback mControllerCallback;
    final Map<MediaRouter2.RoutingController, GroupRouteController> mControllerMap;
    private final Handler mHandler;
    private final Executor mHandlerExecutor;
    final MediaRouter2 mMediaRouter2;
    private boolean mMediaTransferRestrictedToSelfProviders;
    private String mPendingTransferRouteId;
    private final MediaRouter2$RouteCallback mRouteCallback;
    private Map<String, String> mRouteIdToOriginalRouteIdMap;
    private List<MediaRoute2Info> mRoutes;
    private final MediaRouter2$TransferCallback mTransferCallback;

    /* renamed from: androidx.mediarouter.media.MediaRoute2Provider$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Api34Impl {
        private Api34Impl() {
        }

        public static void setPlatformRouteListingPreference(MediaRouter2 r0, android.media.RouteListingPreference r1) {
            r0.setRouteListingPreference(r1);
        }
    }

    public static abstract class Callback {
        public Callback() {
        }

        public abstract void onReleaseController(MediaRouteProvider.RouteController r1);

        public abstract void onSelectFallbackRoute(int r1);

        public abstract void onSelectRoute(String r1, int r2);
    }

    public class ControllerCallback extends MediaRouter2$ControllerCallback {
        final /* synthetic */ MediaRoute2Provider this$0;

        public ControllerCallback(MediaRoute2Provider r1) {
            this.this$0 = r1;
        }

        public void onControllerUpdated(MediaRouter2.RoutingController r1) {
            this.this$0.setDynamicRouteDescriptors(r1);
        }
    }

    public class GroupRouteController extends MediaRouteProvider.DynamicGroupRouteController {
        private static final long OPTIMISTIC_VOLUME_TIMEOUT_MS = 1000;
        private final Runnable mClearOptimisticVolumeRunnable;
        final Handler mControllerHandler;
        MediaRouteDescriptor mGroupRouteDescriptor;
        final String mInitialMemberRouteId;
        AtomicInteger mNextRequestId;
        int mOptimisticVolume;
        final SparseArray<MediaRouter.ControlRequestCallback> mPendingCallbacks;
        final Messenger mReceiveMessenger;
        final MediaRouter2.RoutingController mRoutingController;
        final Messenger mServiceMessenger;
        final /* synthetic */ MediaRoute2Provider this$0;

        public class ReceiveHandler extends Handler {
            final /* synthetic */ GroupRouteController this$1;

            public ReceiveHandler(GroupRouteController r1) {
                this.this$1 = r1;
                super(Looper.getMainLooper());
            }

            @Override // android.os.Handler
            public void handleMessage(Message r5) {
                int r0 = r5.what;
                int r1 = r5.arg1;
                Object r2 = r5.obj;
                Bundle r52 = r5.peekData();
                MediaRouter.ControlRequestCallback r3 = this.this$1.mPendingCallbacks.get(r1);
                if (r3 != null) goto L6;
                Log.w(MediaRoute2Provider.TAG, "Pending callback not found for control request.");
                return;
            L6:
                this.this$1.mPendingCallbacks.remove(r1);
                if (r0 != 3) goto L9;
                r3.onResult((Bundle) r2);
                return;
            L9:
                if (r0 == 4) goto L11;
                return;
            L11:
                if (r52 != null) goto L13;
                String r4 = null;
            L14:
                r3.onError(r4, (Bundle) r2);
                return;
            L13:
                r4 = r52.getString("error");
                goto L14
            }
        }

        public static /* synthetic */ void $r8$lambda$CLL05TKcFn15XmcVc5MkGqmtbvk(GroupRouteController r1) {
            r1.mOptimisticVolume = -1;
        }

        public GroupRouteController(MediaRoute2Provider r2, MediaRouter2.RoutingController r3, String r4) {
            this.this$0 = r2;
            this.mPendingCallbacks = new SparseArray();
            this.mNextRequestId = new AtomicInteger(1);
            this.mClearOptimisticVolumeRunnable = new MediaRoute2Provider$GroupRouteController$$ExternalSyntheticLambda0(this);
            this.mOptimisticVolume = -1;
            this.mRoutingController = r3;
            this.mInitialMemberRouteId = r4;
            Messenger r22 = MediaRoute2Provider.getMessengerFromRoutingController(r3);
            this.mServiceMessenger = r22;
            if (r22 != null) goto L5;
            Messenger r23 = null;
        L6:
            this.mReceiveMessenger = r23;
            this.mControllerHandler = new Handler(Looper.getMainLooper());
            return;
        L5:
            r23 = new Messenger(new ReceiveHandler(this));
            goto L6
        }

        private void scheduleClearOptimisticVolume() {
            this.mControllerHandler.removeCallbacks(this.mClearOptimisticVolumeRunnable);
            this.mControllerHandler.postDelayed(this.mClearOptimisticVolumeRunnable, 1000);
        }

        public String getGroupRouteId() {
            MediaRouteDescriptor r0 = this.mGroupRouteDescriptor;
            if (r0 == null) goto L7;
            return r0.getId();
        L7:
            return this.mRoutingController.getId();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(String r3) {
            if (r3 != null) goto L5;
        L13:
            Log.w(MediaRoute2Provider.TAG, "onAddMemberRoute: Ignoring null or empty routeId.");
            return;
        L5:
            if (r3.isEmpty() == true) goto L13;
            MediaRoute2Info r1 = this.this$0.getRouteById(r3);
            if (r1 != null) goto L11;
            Log.w(MediaRoute2Provider.TAG, "onAddMemberRoute: Specified route not found. routeId=".concat(r3));
            return;
        L11:
            this.mRoutingController.selectRoute(r1);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(Intent r5, MediaRouter.ControlRequestCallback r6) {
            MediaRouter2.RoutingController r0 = this.mRoutingController;
            if (r0 != null) goto L5;
        L19:
            return false;
        L5:
            if (r0.isReleased() == true) goto L19;
            if (this.mServiceMessenger == null) goto L19;
            int r02 = this.mNextRequestId.getAndIncrement();
            Message r2 = Message.obtain();
            r2.what = 9;
            r2.arg1 = r02;
            r2.obj = r5;
            r2.replyTo = this.mReceiveMessenger;
            this.mServiceMessenger.send(r2);     // Catch: RemoteException -> L14 DeadObjectException -> L20
            if (r6 == null) goto L16;
            this.mPendingCallbacks.put(r02, r6);     // Catch: RemoteException -> L14 DeadObjectException -> L20
            goto L19
        L23:
            return true;
        L16:
            return true;
        L14:
            e = move-exception;
            Log.e(MediaRoute2Provider.TAG, "Could not send control request to service.", e);
            goto L19
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.mRoutingController.release();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(String r3) {
            if (r3 != null) goto L5;
        L13:
            Log.w(MediaRoute2Provider.TAG, "onRemoveMemberRoute: Ignoring null or empty routeId.");
            return;
        L5:
            if (r3.isEmpty() == true) goto L13;
            MediaRoute2Info r1 = this.this$0.getRouteById(r3);
            if (r1 != null) goto L11;
            Log.w(MediaRoute2Provider.TAG, "onRemoveMemberRoute: Specified route not found. routeId=".concat(r3));
            return;
        L11:
            this.mRoutingController.deselectRoute(r1);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int r2) {
            MediaRouter2.RoutingController r0 = this.mRoutingController;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.setVolume(r2);
            this.mOptimisticVolume = r2;
            scheduleClearOptimisticVolume();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(List<String> r3) {
            if (r3 != null) goto L5;
        L13:
            Log.w(MediaRoute2Provider.TAG, "onUpdateMemberRoutes: Ignoring null or empty routeIds.");
            return;
        L5:
            if (r3.isEmpty() == true) goto L13;
            String r32 = r3.get(0);
            MediaRoute2Info r1 = this.this$0.getRouteById(r32);
            if (r1 != null) goto L11;
            Log.w(MediaRoute2Provider.TAG, "onUpdateMemberRoutes: Specified route not found. routeId=" + r32);
            return;
        L11:
            this.this$0.mMediaRouter2.transferTo(r1);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int r3) {
            MediaRouter2.RoutingController r0 = this.mRoutingController;
            if (r0 != null) goto L5;
            return;
        L5:
            int r1 = this.mOptimisticVolume;
            if (r1 >= 0) goto L8;
            r1 = r0.getVolume();
        L8:
            int r32 = Math.max(0, Math.min(r1 + r3, this.mRoutingController.getVolumeMax()));
            this.mOptimisticVolume = r32;
            this.mRoutingController.setVolume(r32);
            scheduleClearOptimisticVolume();
        }

        public void setGroupRouteDescriptor(MediaRouteDescriptor r1) {
            this.mGroupRouteDescriptor = r1;
        }

        public void setMemberRouteVolume(String r4, int r5) {
            MediaRouter2.RoutingController r0 = this.mRoutingController;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isReleased() == false) goto L7;
            return;
        L7:
            if (this.mServiceMessenger == null) goto L20;
            int r02 = this.mNextRequestId.getAndIncrement();
            Message r1 = Message.obtain();
            r1.what = 7;
            r1.arg1 = r02;
            Bundle r03 = new Bundle();
            r03.putInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, r5);
            r03.putString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID, r4);
            r1.setData(r03);
            r1.replyTo = this.mReceiveMessenger;
            this.mServiceMessenger.send(r1);     // Catch: RemoteException -> L12 DeadObjectException -> L15
            return;
        L12:
            e = move-exception;
            Log.e(MediaRoute2Provider.TAG, "Could not send control request to service.", e);
            return;
        L21:
            return;
        }

        public void updateMemberRouteVolume(String r4, int r5) {
            MediaRouter2.RoutingController r0 = this.mRoutingController;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isReleased() == false) goto L7;
            return;
        L7:
            if (this.mServiceMessenger == null) goto L20;
            int r02 = this.mNextRequestId.getAndIncrement();
            Message r1 = Message.obtain();
            r1.what = 8;
            r1.arg1 = r02;
            Bundle r03 = new Bundle();
            r03.putInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, r5);
            r03.putString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID, r4);
            r1.setData(r03);
            r1.replyTo = this.mReceiveMessenger;
            this.mServiceMessenger.send(r1);     // Catch: RemoteException -> L12 DeadObjectException -> L15
            return;
        L12:
            e = move-exception;
            Log.e(MediaRoute2Provider.TAG, "Could not send control request to service.", e);
            return;
        L21:
            return;
        }
    }

    public class MemberRouteController extends MediaRouteProvider.RouteController {
        final GroupRouteController mGroupRouteController;
        final String mOriginalRouteId;
        final /* synthetic */ MediaRoute2Provider this$0;

        public MemberRouteController(MediaRoute2Provider r1, String r2, GroupRouteController r3) {
            this.this$0 = r1;
            this.mOriginalRouteId = r2;
            this.mGroupRouteController = r3;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int r2) {
            String r0 = this.mOriginalRouteId;
            if (r0 == null) goto L9;
            GroupRouteController r1 = this.mGroupRouteController;
            if (r1 == null) goto L10;
            r1.setMemberRouteVolume(r0, r2);
            return;
        L10:
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int r2) {
            String r0 = this.mOriginalRouteId;
            if (r0 == null) goto L9;
            GroupRouteController r1 = this.mGroupRouteController;
            if (r1 == null) goto L10;
            r1.updateMemberRouteVolume(r0, r2);
            return;
        L10:
            return;
        }
    }

    public class RouteCallback extends MediaRouter2$RouteCallback {
        final /* synthetic */ MediaRoute2Provider this$0;

        public RouteCallback(MediaRoute2Provider r1) {
            this.this$0 = r1;
        }

        public void onRoutesAdded(List<MediaRoute2Info> r1) {
            this.this$0.refreshRoutes();
        }

        public void onRoutesChanged(List<MediaRoute2Info> r1) {
            this.this$0.refreshRoutes();
        }

        public void onRoutesRemoved(List<MediaRoute2Info> r1) {
            this.this$0.refreshRoutes();
        }
    }

    public class RouteCallbackUpsideDownCake extends MediaRouter2$RouteCallback {
        final /* synthetic */ MediaRoute2Provider this$0;

        private RouteCallbackUpsideDownCake(MediaRoute2Provider r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ RouteCallbackUpsideDownCake(MediaRoute2Provider r1, AnonymousClass1 r2) {
            this(r1);
        }

        public void onRoutesUpdated(List<MediaRoute2Info> r1) {
            this.this$0.refreshRoutes();
        }
    }

    public class TransferCallback extends MediaRouter2$TransferCallback {
        final /* synthetic */ MediaRoute2Provider this$0;

        private TransferCallback(MediaRoute2Provider r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TransferCallback(MediaRoute2Provider r1, AnonymousClass1 r2) {
            this(r1);
        }

        public void onStop(MediaRouter2.RoutingController r3) {
            MediaRoute2Provider.access$202(this.this$0, null);
            GroupRouteController r0 = this.this$0.mControllerMap.remove(r3);
            if (r0 == null) goto L6;
            this.this$0.mCallback.onReleaseController(r0);
            return;
        L6:
            Log.w(MediaRoute2Provider.TAG, "onStop: No matching routeController found. routingController=" + r3);
        }

        public void onTransfer(MediaRouter2.RoutingController r4, MediaRouter2.RoutingController r5) {
            MediaRoute2Provider.access$202(this.this$0, null);
            this.this$0.mControllerMap.remove(r4);
            if (r5 != this.this$0.mMediaRouter2.getSystemController()) goto L6;
            this.this$0.mCallback.onSelectFallbackRoute(3);
            return;
        L6:
            List<MediaRoute2Info> r42 = r5.getSelectedRoutes();
            if (r42.isEmpty() == false) goto L10;
            Log.w(MediaRoute2Provider.TAG, "Selected routes are empty. This shouldn't happen.");
            return;
        L10:
            String r43 = DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r42.get(0)).getId();
            GroupRouteController r1 = new GroupRouteController(this.this$0, r5, r43);
            this.this$0.mControllerMap.put(r5, r1);
            this.this$0.mCallback.onSelectRoute(r43, 3);
            this.this$0.setDynamicRouteDescriptors(r5);
        }

        public void onTransferFailure(MediaRoute2Info r2) {
            MediaRoute2Provider.access$202(this.this$0, null);
            Log.w(MediaRoute2Provider.TAG, "Transfer failed. requestedRoute=" + r2);
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRoute2Provider(Context r3, Callback r4) {
        super(r3);
        this.mControllerMap = new ArrayMap();
        this.mTransferCallback = new TransferCallback(this, null);
        this.mControllerCallback = new ControllerCallback(this);
        this.mRoutes = new ArrayList();
        this.mRouteIdToOriginalRouteIdMap = new ArrayMap();
        this.mMediaRouter2 = MediaRouter2.getInstance(r3);
        this.mCallback = r4;
        Handler r32 = new Handler(Looper.getMainLooper());
        this.mHandler = r32;
        Objects.requireNonNull(r32);
        this.mHandlerExecutor = new DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0(r32);
        if (Build.VERSION.SDK_INT < 34) goto L6;
        this.mRouteCallback = new RouteCallbackUpsideDownCake(this, null);
        return;
    L6:
        this.mRouteCallback = new RouteCallback(this);
    }

    public static /* synthetic */ String access$202(MediaRoute2Provider r0, String r1) {
        r0.mPendingTransferRouteId = r1;
        return r1;
    }

    public static Messenger getMessengerFromRoutingController(MediaRouter2.RoutingController r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        Bundle r12 = r1.getControlHints();
        if (r12 != null) goto L9;
        return null;
    L9:
        return (Messenger) r12.getParcelable("androidx.mediarouter.media.KEY_MESSENGER");
    }

    public static String getSessionIdForRouteController(MediaRouteProvider.RouteController r2) {
        if ((r2 instanceof GroupRouteController) == true) goto L5;
        return null;
    L5:
        MediaRouter2.RoutingController r22 = ((GroupRouteController) r2).mRoutingController;
        if (r22 != null) goto L9;
        return null;
    L9:
        return r22.getId();
    }

    private MediaRouteDiscoveryRequest updateDiscoveryRequest(MediaRouteDiscoveryRequest r2, boolean r3) {
        if (r2 != null) goto L4;
        r2 = new MediaRouteDiscoveryRequest(MediaRouteSelector.EMPTY, false);
    L4:
        List<String> r1 = r2.getSelector().getControlCategories();
        if (r3 == true) goto L7;
        r1.remove(MediaControlIntent.CATEGORY_LIVE_AUDIO);
    L11:
        return new MediaRouteDiscoveryRequest(new MediaRouteSelector.Builder().addControlCategories(r1).build(), r2.isActiveScan());
    L7:
        if (r1.contains(MediaControlIntent.CATEGORY_LIVE_AUDIO) == true) goto L11;
        r1.add(MediaControlIntent.CATEGORY_LIVE_AUDIO);
        goto L11
    }

    public MediaRoute2Info getRouteById(String r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        Iterator<MediaRoute2Info> r3 = this.mRoutes.iterator();
    L7:
        if (r3.hasNext() == false) goto L11;
        MediaRoute2Info r1 = DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r3.next());
        if (TextUtils.equals(r1.getId(), r4) == false) goto L7;
        return r1;
    L11:
        return null;
    }

    public boolean isMediaTransferRestrictedToSelfProviders() {
        return this.mMediaTransferRestrictedToSelfProviders;
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.DynamicGroupRouteController onCreateDynamicGroupRouteController(String r2, MediaRouteProvider.RouteControllerOptions r3) {
        Iterator<Map.Entry<MediaRouter2.RoutingController, GroupRouteController>> r1 = this.mControllerMap.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        GroupRouteController r32 = r1.next().getValue();
        if (TextUtils.equals(r2, r32.mInitialMemberRouteId) == false) goto L4;
        return r32;
    L8:
        return null;
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String r3) {
        return new MemberRouteController(this, this.mRouteIdToOriginalRouteIdMap.get(r3), null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String r5, String r6) {
        String r0 = this.mRouteIdToOriginalRouteIdMap.get(r5);
        Iterator<GroupRouteController> r1 = this.mControllerMap.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        GroupRouteController r2 = r1.next();
        if (TextUtils.equals(r6, r2.getGroupRouteId()) == false) goto L4;
        return new MemberRouteController(this, r0, r2);
    L9:
        Log.w(TAG, "Could not find the matching GroupRouteController. routeId=" + r5 + ", routeGroupId=" + r6);
        return new MemberRouteController(this, r0, null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest r4) {
        if (MediaRouter.getGlobalCallbackCount() <= 0) goto L6;
        MediaRouteDiscoveryRequest r42 = updateDiscoveryRequest(r4, MediaRouter.isTransferToLocalEnabled());
        this.mMediaRouter2.registerRouteCallback(this.mHandlerExecutor, this.mRouteCallback, MediaRouter2Utils.toDiscoveryPreference(r42));
        this.mMediaRouter2.registerTransferCallback(this.mHandlerExecutor, this.mTransferCallback);
        this.mMediaRouter2.registerControllerCallback(this.mHandlerExecutor, this.mControllerCallback);
        return;
    L6:
        this.mMediaRouter2.unregisterRouteCallback(this.mRouteCallback);
        this.mMediaRouter2.unregisterTransferCallback(this.mTransferCallback);
        this.mMediaRouter2.unregisterControllerCallback(this.mControllerCallback);
    }

    public void refreshRoutes() {
        ArrayList r0 = new ArrayList();
        ArraySet r1 = new ArraySet();
        Iterator<MediaRoute2Info> r2 = this.mMediaRouter2.getRoutes().iterator();
    L4:
        if (r2.hasNext() == false) goto L19;
        MediaRoute2Info r3 = DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r2.next());
        if (r3 == null) goto L4;
        if (r1.contains(r3) == true) goto L4;
        if (r3.isSystemRoute() == true) goto L4;
        if (this.mMediaTransferRestrictedToSelfProviders == false) goto L17;
        if (r3.getId().startsWith(getContext().getPackageName() + PACKAGE_NAME_SEPARATOR) == false) goto L4;
    L17:
        r1.add(r3);
        r0.add(r3);
        goto L4
    L19:
        if (r0.equals(this.mRoutes) == false) goto L21;
        return;
    L21:
        this.mRoutes = r0;
        this.mRouteIdToOriginalRouteIdMap.clear();
        Iterator<MediaRoute2Info> r02 = this.mRoutes.iterator();
    L23:
        if (r02.hasNext() == false) goto L31;
        MediaRoute2Info r12 = DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r02.next());
        Bundle r22 = r12.getExtras();
        if (r22 == null) goto L30;
        if (r22.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID") == null) goto L30;
        this.mRouteIdToOriginalRouteIdMap.put(r12.getId(), r22.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID"));
    L30:
        Log.w(TAG, "Cannot find the original route Id. route=" + r12);
        goto L23
    L31:
        ArrayList r03 = new ArrayList();
        Iterator<MediaRoute2Info> r13 = this.mRoutes.iterator();
    L33:
        if (r13.hasNext() == false) goto L37;
        MediaRouteDescriptor r23 = MediaRouter2Utils.toMediaRouteDescriptor(DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r13.next()));
        if (r23 == null) goto L33;
        r03.add(r23);
        goto L33
    L37:
        setDescriptor(new MediaRouteProviderDescriptor.Builder().setSupportsDynamicGroupRoute(true).addRoutes(r03).build());
    }

    public void setDynamicRouteDescriptors(MediaRouter2.RoutingController r10) {
        GroupRouteController r0 = this.mControllerMap.get(r10);
        if (r0 != null) goto L6;
        Log.w(TAG, "setDynamicRouteDescriptors: No matching routeController found. routingController=" + r10);
        return;
    L6:
        List<MediaRoute2Info> r2 = r10.getSelectedRoutes();
        if (r2.isEmpty() == false) goto L10;
        Log.w(TAG, "setDynamicRouteDescriptors: No selected routes. This may happen when the selected routes become invalid.routingController=" + r10);
        return;
    L10:
        List<String> r3 = MediaRouter2Utils.getRouteIds(r2);
        MediaRouteDescriptor r22 = MediaRouter2Utils.toMediaRouteDescriptor(DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r2.get(0)));
        Bundle r4 = r10.getControlHints();
        String r5 = getContext().getString(R.string.mr_dialog_default_group_name);
        MediaRouteDescriptor r6 = null;
        if (r4 == null) goto L22;
        String r7 = r4.getString("androidx.mediarouter.media.KEY_SESSION_NAME");     // Catch: Exception -> L19
        if (TextUtils.isEmpty(r7) == true) goto L15;
        r5 = r7;
    L15:
        Bundle r42 = r4.getBundle("androidx.mediarouter.media.KEY_GROUP_ROUTE");     // Catch: Exception -> L19
        if (r42 == null) goto L22;
        r6 = MediaRouteDescriptor.fromBundle(r42);     // Catch: Exception -> L19
    L19:
        e = move-exception;
        Log.w(TAG, "Exception while unparceling control hints.", e);
    L22:
        if (r6 != null) goto L24;
        MediaRouteDescriptor.Builder r52 = new MediaRouteDescriptor.Builder(r10.getId(), r5).setConnectionState(2).setPlaybackType(1);
    L25:
        MediaRouteDescriptor r23 = r52.setVolume(r10.getVolume()).setVolumeMax(r10.getVolumeMax()).setVolumeHandling(r10.getVolumeHandling()).clearControlFilters().addControlFilters(r22.getControlFilters()).clearGroupMemberIds().addGroupMemberIds(r3).build();
        List<String> r53 = MediaRouter2Utils.getRouteIds(r10.getSelectableRoutes());
        List<String> r102 = MediaRouter2Utils.getRouteIds(r10.getDeselectableRoutes());
        MediaRouteProviderDescriptor r9 = getDescriptor();
        if (r9 != null) goto L29;
        Log.w(TAG, "setDynamicRouteDescriptors: providerDescriptor is not set.");
        return;
    L29:
        ArrayList r1 = new ArrayList();
        List<MediaRouteDescriptor> r92 = r9.getRoutes();
        if (r92.isEmpty() == true) goto L39;
        Iterator<MediaRouteDescriptor> r93 = r92.iterator();
    L33:
        if (r93.hasNext() == false) goto L39;
        MediaRouteDescriptor r62 = r93.next();
        String r72 = r62.getId();
        MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor.Builder r8 = new MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor.Builder(r62);
        if (r3.contains(r72) == false) goto L37;
        int r63 = 3;
    L38:
        r1.add(r8.setSelectionState(r63).setIsGroupable(r53.contains(r72)).setIsUnselectable(r102.contains(r72)).setIsTransferable(true).build());
        goto L33
    L37:
        r63 = 1;
    L39:
        r0.setGroupRouteDescriptor(r23);
        r0.notifyDynamicRoutesChanged(r23, r1);
        return;
    L24:
        r52 = new MediaRouteDescriptor.Builder(r6);
        goto L25
    }

    public void setMediaTransferRestrictedToSelfProviders(boolean r1) {
        this.mMediaTransferRestrictedToSelfProviders = r1;
        refreshRoutes();
    }

    public void setRouteListingPreference(RouteListingPreference r1) {
        MediaRouter2 r0 = this.mMediaRouter2;
        if (r1 == null) goto L5;
        android.media.RouteListingPreference r12 = r1.toPlatformRouteListingPreference();
    L6:
        Api34Impl.setPlatformRouteListingPreference(r0, r12);
        return;
    L5:
        r12 = null;
        goto L6
    }

    public void transferTo(String r4) {
        MediaRoute2Info r0 = getRouteById(r4);
        if (r0 != null) goto L7;
        Log.w(TAG, "transferTo: Specified route not found. routeId=" + r4);
        return;
    L7:
        if (TextUtils.equals(this.mPendingTransferRouteId, r4) == false) goto L10;
        Log.w(TAG, "Ignoring attempt to transfer to pending transfer route: " + r0);
        return;
    L10:
        this.mPendingTransferRouteId = r4;
        this.mMediaRouter2.transferTo(r0);
    }
}
