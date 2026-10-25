package androidx.mediarouter.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.work.WorkerFactory$$ExternalSyntheticBUOutline0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class MediaRouteProviderService extends Service {
    static final boolean DEBUG = false;
    static final int PRIVATE_MSG_CLIENT_DIED = 1;
    public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
    static final String TAG = "MediaRouteProviderSrv";
    final MediaRouteProviderServiceImpl mImpl;
    final PrivateHandler mPrivateHandler;
    MediaRouteProvider mProvider;
    private final MediaRouteProvider.Callback mProviderCallback;
    private final ReceiveHandler mReceiveHandler;
    final Messenger mReceiveMessenger;

    /* renamed from: androidx.mediarouter.media.MediaRouteProviderService$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ClientInfo {
        private final String packageName;

        public static final class Builder {
            private final String packageName;

            public Builder(String r1) {
                this.packageName = r1;
            }

            public ClientInfo build() {
                return new ClientInfo(this.packageName, null);
            }
        }

        private ClientInfo(String r1) {
            this.packageName = r1;
        }

        public /* synthetic */ ClientInfo(String r1, AnonymousClass1 r2) {
            this(r1);
        }

        public String getPackageName() {
            return this.packageName;
        }
    }

    public interface MediaRouteProviderServiceImpl {
        void addClientInfoListener(Executor r1, Consumer<List<ClientInfo>> r2);

        void attachBaseContext(Context r1);

        MediaRouteProvider.Callback getProviderCallback();

        boolean onAddMemberRoute(Messenger r1, int r2, int r3, String r4);

        IBinder onBind(Intent r1);

        void onBinderDied(Messenger r1);

        boolean onCreateDynamicGroupRouteController(Messenger r1, int r2, int r3, String r4, MediaRouteProvider.RouteControllerOptions r5);

        boolean onCreateRouteController(Messenger r1, int r2, int r3, String r4, String r5, MediaRouteProvider.RouteControllerOptions r6);

        boolean onRegisterClient(Messenger r1, int r2, int r3, String r4);

        boolean onReleaseRouteController(Messenger r1, int r2, int r3);

        boolean onRemoveMemberRoute(Messenger r1, int r2, int r3, String r4);

        boolean onRouteControlRequest(Messenger r1, int r2, int r3, Intent r4);

        boolean onSelectRoute(Messenger r1, int r2, int r3);

        boolean onSetDiscoveryRequest(Messenger r1, int r2, MediaRouteDiscoveryRequest r3);

        boolean onSetRouteVolume(Messenger r1, int r2, int r3, int r4);

        boolean onUnregisterClient(Messenger r1, int r2);

        boolean onUnselectRoute(Messenger r1, int r2, int r3, int r4);

        boolean onUpdateMemberRoutes(Messenger r1, int r2, int r3, List<String> r4);

        boolean onUpdateRouteVolume(Messenger r1, int r2, int r3, int r4);

        void removeAllClientInfoListeners();

        void removeClientInfoListener(Consumer<List<ClientInfo>> r1);
    }

    public static class MediaRouteProviderServiceImplApi30 extends MediaRouteProviderServiceImplBase {
        final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener;
        MediaRoute2ProviderServiceAdapter mMR2ProviderServiceAdapter;

        public class ClientRecord extends MediaRouteProviderServiceImplBase.ClientRecord {
            private static final long DISABLE_ROUTE_FOR_RELEASED_CONTROLLER_TIMEOUT_MS = 5000;
            private final Handler mClientHandler;
            private final Map<String, Integer> mReleasedControllerIds;
            private final Map<String, MediaRouteProvider.RouteController> mRouteIdToControllerMap;
            final /* synthetic */ MediaRouteProviderServiceImplApi30 this$0;

            public static /* synthetic */ void $r8$lambda$zi_KdVF7PqJCT4wkwNddNq6W0SA(ClientRecord r0, String r1) {
                r0.enableRouteForReleasedRouteController(r1);
            }

            public ClientRecord(MediaRouteProviderServiceImplApi30 r1, Messenger r2, int r3, String r4) {
                this.this$0 = r1;
                super(r1, r2, r3, r4);
                this.mRouteIdToControllerMap = new ArrayMap();
                this.mClientHandler = new Handler(Looper.getMainLooper());
                if (r3 >= 4) goto L6;
                this.mReleasedControllerIds = new ArrayMap();
                return;
            L6:
                this.mReleasedControllerIds = Collections.EMPTY_MAP;
            }

            private void disableRouteForReleasedRouteController(String r4, int r5) {
                this.mReleasedControllerIds.put(r4, Integer.valueOf(r5));
                this.mClientHandler.postDelayed(new MediaRouteProviderService$MediaRouteProviderServiceImplApi30$ClientRecord$$ExternalSyntheticLambda0(this, r4), DISABLE_ROUTE_FOR_RELEASED_CONTROLLER_TIMEOUT_MS);
                sendDescriptor();
            }

            private void enableRouteForReleasedRouteController(String r2) {
                if (this.mReleasedControllerIds.remove(r2) != null) goto L5;
                return;
            L5:
                sendDescriptor();
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public Bundle createDescriptorBundle(MediaRouteProviderDescriptor r6) {
                if (this.mReleasedControllerIds.isEmpty() == true) goto L5;
                ArrayList r0 = new ArrayList();
                Iterator<MediaRouteDescriptor> r1 = r6.getRoutes().iterator();
            L8:
                if (r1.hasNext() == false) goto L14;
                MediaRouteDescriptor r2 = r1.next();
                if (this.mReleasedControllerIds.containsKey(r2.getId()) == true) goto L11;
                r0.add(r2);
                goto L8
            L11:
                r0.add(new MediaRouteDescriptor.Builder(r2).setEnabled(false).build());
                goto L8
            L14:
                return super.createDescriptorBundle(new MediaRouteProviderDescriptor.Builder(r6).setRoutes(r0).build());
            L5:
                return super.createDescriptorBundle(r6);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public Bundle createDynamicGroupRouteController(String r8, MediaRouteProvider.RouteControllerOptions r9, int r10) {
                Bundle r92 = super.createDynamicGroupRouteController(r8, r9, r10);
                if (r92 != null) goto L5;
            L7:
                return r92;
            L5:
                if (this.mPackageName == null) goto L7;
                this.this$0.mMR2ProviderServiceAdapter.notifyRouteControllerAdded(this, this.mControllers.get(r10), r10, this.mPackageName, r8);
                goto L7
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean createRouteController(String r7, String r8, MediaRouteProvider.RouteControllerOptions r9, int r10) {
                MediaRouteProvider.RouteController r0 = this.mRouteIdToControllerMap.get(r7);
                if (r0 == null) goto L6;
                this.mControllers.put(r10, r0);
                return true;
            L6:
                boolean r92 = super.createRouteController(r7, r8, r9, r10);
                if (r8 != null) goto L12;
                if (r92 == false) goto L12;
                if (this.mPackageName == null) goto L12;
                ClientRecord r1 = this;
                String r5 = r7;
                int r3 = r10;
                this.this$0.mMR2ProviderServiceAdapter.notifyRouteControllerAdded(r1, this.mControllers.get(r10), r3, this.mPackageName, r5);
            L13:
                if (r92 == false) goto L15;
                r1.mRouteIdToControllerMap.put(r5, r1.mControllers.get(r3));
            L15:
                return r92;
            L12:
                r1 = this;
                r5 = r7;
                r3 = r10;
                goto L13
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public void dispose() {
                int r0 = this.mControllers.size();
                int r1 = 0;
            L3:
                if (r1 >= r0) goto L5;
                int r2 = this.mControllers.keyAt(r1);
                this.this$0.mMR2ProviderServiceAdapter.notifyRouteControllerRemoved(r2);
                r1 = r1 + 1;
                goto L3
            L5:
                this.mRouteIdToControllerMap.clear();
                super.dispose();
            }

            public MediaRouteProvider.RouteController findControllerByRouteId(String r1) {
                return this.mRouteIdToControllerMap.get(r1);
            }

            public int findControllerIdByController(MediaRouteProvider.RouteController r2) {
                int r22 = this.mControllers.indexOfValue(r2);
                if (r22 >= 0) goto L7;
                return -1;
            L7:
                return this.mControllers.keyAt(r22);
            }

            public void releaseControllerByProvider(MediaRouteProvider.RouteController r7, String r8) {
                int r3 = findControllerIdByController(r7);
                releaseRouteController(r3);
                if (this.mVersion < 4) goto L9;
                if (r3 >= 0) goto L7;
                Log.w(MediaRouteProviderService.TAG, "releaseControllerByProvider: Can't find the controller. route ID=" + r8);
                return;
            L7:
                MediaRouteProviderService.sendMessage(this.mMessenger, 8, 0, r3, null, null);
                return;
            L9:
                disableRouteForReleasedRouteController(r8, r3);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean releaseRouteController(int r5) {
                this.this$0.mMR2ProviderServiceAdapter.notifyRouteControllerRemoved(r5);
                MediaRouteProvider.RouteController r0 = this.mControllers.get(r5);
                if (r0 == null) goto L10;
                Iterator<Map.Entry<String, MediaRouteProvider.RouteController>> r1 = this.mRouteIdToControllerMap.entrySet().iterator();
            L6:
                if (r1.hasNext() == false) goto L10;
                Map.Entry<String, MediaRouteProvider.RouteController> r2 = r1.next();
                if (r2.getValue() != r0) goto L6;
                this.mRouteIdToControllerMap.remove(r2.getKey());
            L10:
                Iterator<Map.Entry<String, Integer>> r02 = this.mReleasedControllerIds.entrySet().iterator();
            L12:
                if (r02.hasNext() == false) goto L17;
                Map.Entry<String, Integer> r12 = r02.next();
                if (r12.getValue().intValue() != r5) goto L12;
                enableRouteForReleasedRouteController(r12.getKey());
            L17:
                return super.releaseRouteController(r5);
            }

            public void sendDescriptor() {
                MediaRouteProviderDescriptor r0 = this.this$0.getService().getMediaRouteProvider().getDescriptor();
                if (r0 == null) goto L6;
                MediaRouteProviderService.sendMessage(this.mMessenger, 5, 0, 0, createDescriptorBundle(r0), null);
                return;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController r1, MediaRouteDescriptor r2, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r3) {
                super.sendDynamicRouteDescriptors(r1, r2, r3);
                MediaRoute2ProviderServiceAdapter r0 = this.this$0.mMR2ProviderServiceAdapter;
                if (r0 == null) goto L6;
                r0.setDynamicRouteDescriptor(r1, r2, r3);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$sCqM5kOJxpe5eKv4XuEiLuGCEu8(MediaRouteProviderServiceImplApi30 r0, MediaRouteProvider.DynamicGroupRouteController r1, MediaRouteDescriptor r2, Collection r3) {
            r0.mMR2ProviderServiceAdapter.setDynamicRouteDescriptor(r1, r2, r3);
        }

        public MediaRouteProviderServiceImplApi30(MediaRouteProviderService r1) {
            super(r1);
            this.mDynamicRoutesChangedListener = new MediaRouteProviderService$MediaRouteProviderServiceImplApi30$$ExternalSyntheticLambda0(this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context r1) {
            MediaRoute2ProviderServiceAdapter r0 = this.mMR2ProviderServiceAdapter;
            if (r0 == null) goto L6;
            r0.attachBaseContext(r1);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        public MediaRouteProviderServiceImplBase.ClientRecord createClientRecord(Messenger r2, int r3, String r4) {
            return new ClientRecord(this, r2, r3, r4);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent r3) {
            this.mService.ensureProvider();
            if (this.mMR2ProviderServiceAdapter != null) goto L7;
            this.mMR2ProviderServiceAdapter = new MediaRoute2ProviderServiceAdapter(this);
            if (this.mService.getBaseContext() == null) goto L7;
            this.mMR2ProviderServiceAdapter.attachBaseContext(this.mService);
        L7:
            IBinder r0 = super.onBind(r3);
            if (r0 == null) goto L11;
            return r0;
        L11:
            return this.mMR2ProviderServiceAdapter.onBind(r3);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        public void sendDescriptorChanged(MediaRouteProviderDescriptor r1) {
            super.sendDescriptorChanged(r1);
            this.mMR2ProviderServiceAdapter.setProviderDescriptor(r1);
        }

        public void setDynamicRoutesChangedListener(MediaRouteProvider.DynamicGroupRouteController r2) {
            r2.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
        }
    }

    public static class MediaRouteProviderServiceImplBase implements MediaRouteProviderServiceImpl {
        private final MediaRouterActiveScanThrottlingHelper mActiveScanThrottlingHelper;
        MediaRouteDiscoveryRequest mBaseDiscoveryRequest;
        long mBaseDiscoveryRequestTimestamp;
        private final Map<Consumer<List<ClientInfo>>, Executor> mClientInfoListeners;
        private final Object mClientInfoListenersLock;
        final ArrayList<ClientRecord> mClients;
        MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
        final MediaRouteProviderService mService;

        /* renamed from: androidx.mediarouter.media.MediaRouteProviderService$MediaRouteProviderServiceImplBase$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ MediaRouteProviderServiceImplBase this$0;

            public AnonymousClass1(MediaRouteProviderServiceImplBase r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$0.updateCompositeDiscoveryRequest();
            }
        }

        /* renamed from: androidx.mediarouter.media.MediaRouteProviderService$MediaRouteProviderServiceImplBase$2, reason: invalid class name */
        public class AnonymousClass2 extends MediaRouter.ControlRequestCallback {
            final /* synthetic */ MediaRouteProviderServiceImplBase this$0;
            final /* synthetic */ ClientRecord val$client;
            final /* synthetic */ int val$controllerId;
            final /* synthetic */ Intent val$intent;
            final /* synthetic */ Messenger val$messenger;
            final /* synthetic */ int val$requestId;

            public AnonymousClass2(MediaRouteProviderServiceImplBase r1, ClientRecord r2, int r3, Intent r4, Messenger r5, int r6) {
                this.this$0 = r1;
                this.val$client = r2;
                this.val$controllerId = r3;
                this.val$intent = r4;
                this.val$messenger = r5;
                this.val$requestId = r6;
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onError(String r14, Bundle r15) {
                String r0 = MediaRouteProviderService.SERVICE_INTERFACE;
                if (this.this$0.findClient(this.val$messenger) < 0) goto L9;
                if (r14 == null) goto L7;
                Bundle r6 = new Bundle();
                r6.putString("error", r14);
                MediaRouteProviderService.sendMessage(this.val$messenger, 4, this.val$requestId, 0, r15, r6);
                return;
            L7:
                MediaRouteProviderService.sendMessage(this.val$messenger, 4, this.val$requestId, 0, r15, null);
                return;
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onResult(Bundle r8) {
                String r0 = MediaRouteProviderService.SERVICE_INTERFACE;
                if (this.this$0.findClient(this.val$messenger) < 0) goto L6;
                MediaRouteProviderService.sendMessage(this.val$messenger, 3, this.val$requestId, 0, r8, null);
                return;
            }
        }

        public class ClientRecord implements IBinder.DeathRecipient {
            final SparseArray<MediaRouteProvider.RouteController> mControllers;
            public MediaRouteDiscoveryRequest mDiscoveryRequest;
            public long mDiscoveryRequestTimestamp;
            final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener;
            public final Messenger mMessenger;
            public final String mPackageName;
            public final int mVersion;
            final /* synthetic */ MediaRouteProviderServiceImplBase this$0;

            /* renamed from: androidx.mediarouter.media.MediaRouteProviderService$MediaRouteProviderServiceImplBase$ClientRecord$1, reason: invalid class name */
            public class AnonymousClass1 implements MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener {
                final /* synthetic */ ClientRecord this$1;

                public AnonymousClass1(ClientRecord r1) {
                    this.this$1 = r1;
                }

                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
                public void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController r1, MediaRouteDescriptor r2, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r3) {
                    this.this$1.sendDynamicRouteDescriptors(r1, r2, r3);
                }
            }

            public ClientRecord(MediaRouteProviderServiceImplBase r1, Messenger r2, int r3, String r4) {
                this.this$0 = r1;
                this.mControllers = new SparseArray();
                this.mDynamicRoutesChangedListener = new AnonymousClass1(this);
                this.mMessenger = r2;
                this.mVersion = r3;
                this.mPackageName = r4;
            }

            public static /* synthetic */ ClientInfo access$100(ClientRecord r0) {
                return r0.getClientInfo();
            }

            private ClientInfo getClientInfo() {
                return new ClientInfo.Builder(this.mPackageName).build();
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                this.this$0.mService.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
            }

            public Bundle createDescriptorBundle(MediaRouteProviderDescriptor r1) {
                return MediaRouteProviderService.createDescriptorBundleForClientVersion(r1, this.mVersion);
            }

            public Bundle createDynamicGroupRouteController(String r2, MediaRouteProvider.RouteControllerOptions r3, int r4) {
                if (this.mControllers.indexOfKey(r4) >= 0) goto L8;
                MediaRouteProvider.DynamicGroupRouteController r22 = this.this$0.mService.getMediaRouteProvider().onCreateDynamicGroupRouteController(r2, r3);
                if (r22 == null) goto L10;
                r22.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.this$0.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
                this.mControllers.put(r4, r22);
                Bundle r1 = new Bundle();
                r1.putString(MediaRouteProviderProtocol.DATA_KEY_GROUPABLE_SECTION_TITLE, r22.getGroupableSelectionTitle());
                r1.putString(MediaRouteProviderProtocol.DATA_KEY_TRANSFERABLE_SECTION_TITLE, r22.getTransferableSectionTitle());
                return r1;
            L10:
                return null;
            L8:
                return null;
            }

            public boolean createRouteController(String r2, String r3, MediaRouteProvider.RouteControllerOptions r4, int r5) {
                if (this.mControllers.indexOfKey(r5) >= 0) goto L11;
                MediaRouteProviderServiceImplBase r0 = this.this$0;
                if (r3 != null) goto L7;
                MediaRouteProvider.RouteController r22 = r0.mService.getMediaRouteProvider().onCreateRouteController(r2, r4);
            L8:
                if (r22 == null) goto L13;
                this.mControllers.put(r5, r22);
                return true;
            L13:
                return false;
            L7:
                r22 = r0.mService.getMediaRouteProvider().onCreateRouteController(r2, r3);
                goto L8
            L11:
                return false;
            }

            public void dispose() {
                int r0 = this.mControllers.size();
                int r2 = 0;
            L3:
                SparseArray<MediaRouteProvider.RouteController> r3 = this.mControllers;
                if (r2 >= r0) goto L6;
                r3.valueAt(r2).onRelease();
                r2 = r2 + 1;
                goto L3
            L6:
                r3.clear();
                this.mMessenger.getBinder().unlinkToDeath(this, 0);
                setDiscoveryRequest(null);
            }

            public MediaRouteProvider.RouteController getRouteController(int r1) {
                return this.mControllers.get(r1);
            }

            public boolean hasMessenger(Messenger r1) {
                if (this.mMessenger.getBinder() != r1.getBinder()) goto L6;
                return true;
            L6:
                return false;
            }

            public boolean register() {
                this.mMessenger.getBinder().linkToDeath(this, 0);     // Catch: RemoteException -> L6
                return true;
            L6:
                binderDied();
                return false;
            }

            public boolean releaseRouteController(int r2) {
                MediaRouteProvider.RouteController r0 = this.mControllers.get(r2);
                if (r0 == null) goto L6;
                this.mControllers.remove(r2);
                r0.onRelease();
                return true;
            L6:
                return false;
            }

            public void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController r8, MediaRouteDescriptor r9, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r10) {
                int r0 = this.mControllers.indexOfValue(r8);
                if (r0 >= 0) goto L6;
                Log.w(MediaRouteProviderService.TAG, "Ignoring unknown dynamic group route controller: " + r8);
                return;
            L6:
                int r4 = this.mControllers.keyAt(r0);
                ArrayList<? extends Parcelable> r82 = new ArrayList();
                Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r102 = r10.iterator();
            L8:
                if (r102.hasNext() == false) goto L10;
                r82.add(r102.next().toBundle());
                goto L8
            L10:
                Bundle r5 = new Bundle();
                if (r9 == null) goto L13;
                r5.putParcelable(MediaRouteProviderProtocol.DATA_KEY_GROUP_ROUTE_DESCRIPTOR, r9.asBundle());
            L13:
                r5.putParcelableArrayList(MediaRouteProviderProtocol.DATA_KEY_DYNAMIC_ROUTE_DESCRIPTORS, r82);
                MediaRouteProviderService.sendMessage(this.mMessenger, 7, 0, r4, r5, null);
            }

            public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest r4) {
                long r0 = SystemClock.elapsedRealtime();
                if (ObjectsCompat.equals(this.mDiscoveryRequest, r4) == true) goto L6;
                this.mDiscoveryRequest = r4;
                this.mDiscoveryRequestTimestamp = r0;
                return this.this$0.updateCompositeDiscoveryRequest();
            L6:
                return false;
            }

            public String toString() {
                return MediaRouteProviderService.getClientId(this.mMessenger);
            }
        }

        public class ProviderCallbackBase extends MediaRouteProvider.Callback {
            final /* synthetic */ MediaRouteProviderServiceImplBase this$0;

            public ProviderCallbackBase(MediaRouteProviderServiceImplBase r1) {
                this.this$0 = r1;
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.Callback
            public void onDescriptorChanged(MediaRouteProvider r1, MediaRouteProviderDescriptor r2) {
                this.this$0.sendDescriptorChanged(r2);
            }
        }

        /* renamed from: $r8$lambda$o9d69-qIThxzSl0SwmFrvVrJFeY, reason: not valid java name */
        public static /* synthetic */ void m930$r8$lambda$o9d69qIThxzSl0SwmFrvVrJFeY(Consumer r0, ArrayList r1) {
            r0.accept(r1);
        }

        public MediaRouteProviderServiceImplBase(MediaRouteProviderService r3) {
            this.mClients = new ArrayList();
            this.mClientInfoListeners = new HashMap();
            this.mClientInfoListenersLock = new Object();
            this.mActiveScanThrottlingHelper = new MediaRouterActiveScanThrottlingHelper(new AnonymousClass1(this));
            this.mService = r3;
        }

        private void addClient(ClientRecord r2) {
            this.mClients.add(r2);
            notifyClientRecordsChanged();
        }

        private ClientRecord getClient(Messenger r1) {
            int r12 = findClient(r1);
            if (r12 >= 0) goto L5;
            return null;
        L5:
            return this.mClients.get(r12);
        }

        private void notifyClientRecordsChanged() {
            if (this.mClientInfoListeners.isEmpty() == true) goto L19;
            ArrayList r0 = new ArrayList();
            ArrayList r1 = new ArrayList(this.mClients);
            int r2 = r1.size();
            int r3 = 0;
        L5:
            if (r3 >= r2) goto L7;
            Object r4 = r1.get(r3);
            r3 = r3 + 1;
            r0.add(ClientRecord.access$100((ClientRecord) r4));
            goto L5
        L7:
            Object r12 = this.mClientInfoListenersLock;
            monitor-enter(r12);
            Iterator<Map.Entry<Consumer<List<ClientInfo>>, Executor>> r5 = this.mClientInfoListeners.entrySet().iterator();     // Catch: Throwable -> L13
        L11:
            if (r5.hasNext() == false) goto L15;
            Map.Entry<Consumer<List<ClientInfo>>, Executor> r22 = r5.next();     // Catch: Throwable -> L13
            Consumer<List<ClientInfo>> r32 = r22.getKey();     // Catch: Throwable -> L13
            r22.getValue().execute(new MediaRouteProviderService$MediaRouteProviderServiceImplBase$$ExternalSyntheticLambda0(r32, r0));     // Catch: Throwable -> L13
            goto L11
        L15:
            monitor-exit(r12);     // Catch: Throwable -> L13
            return;
        L13:
            th = move-exception;
            throw th;
        }

        private ClientRecord removeClient(int r2) {
            ClientRecord r22 = this.mClients.remove(r2);
            notifyClientRecordsChanged();
            return r22;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void addClientInfoListener(Executor r3, Consumer<List<ClientInfo>> r4) {
            Object r0 = this.mClientInfoListenersLock;
            monitor-enter(r0);
            this.mClientInfoListeners.put(r4, r3);     // Catch: Throwable -> L7
            notifyClientRecordsChanged();     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context r1) {
        }

        public ClientRecord createClientRecord(Messenger r2, int r3, String r4) {
            return new ClientRecord(this, r2, r3, r4);
        }

        public int findClient(Messenger r4) {
            int r0 = this.mClients.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            if (this.mClients.get(r1).hasMessenger(r4) == true) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r1;
        L8:
            return -1;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public MediaRouteProvider.Callback getProviderCallback() {
            return new ProviderCallbackBase(this);
        }

        public MediaRouteProviderService getService() {
            return this.mService;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onAddMemberRoute(Messenger r1, int r2, int r3, String r4) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L8;
            MediaRouteProvider.RouteController r02 = r0.getRouteController(r3);
            if ((r02 instanceof MediaRouteProvider.DynamicGroupRouteController) == false) goto L10;
            ((MediaRouteProvider.DynamicGroupRouteController) r02).onAddMemberRoute(r4);
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent r2) {
            if (r2.getAction().equals(MediaRouteProviderProtocol.SERVICE_INTERFACE) == false) goto L8;
            this.mService.ensureProvider();
            if (this.mService.getMediaRouteProvider() != null) goto L7;
            return null;
        L7:
            return this.mService.mReceiveMessenger.getBinder();
        L8:
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void onBinderDied(Messenger r1) {
            int r12 = findClient(r1);
            if (r12 < 0) goto L6;
            ClientRecord r0 = removeClient(r12);
            String r13 = MediaRouteProviderService.SERVICE_INTERFACE;
            r0.dispose();
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateDynamicGroupRouteController(Messenger r7, int r8, int r9, String r10, MediaRouteProvider.RouteControllerOptions r11) {
            ClientRecord r6 = getClient(r7);
            if (r6 == null) goto L8;
            Bundle r4 = r6.createDynamicGroupRouteController(r10, new MediaRouteProvider.RouteControllerOptions.Builder(r11).setClientPackageName(r6.mPackageName).build(), r9);
            if (r4 == null) goto L10;
            MediaRouteProviderService.sendMessage(r7, 6, r8, 3, r4, null);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateRouteController(Messenger r2, int r3, int r4, String r5, String r6, MediaRouteProvider.RouteControllerOptions r7) {
            ClientRecord r1 = getClient(r2);
            if (r1 != null) goto L5;
            return false;
        L5:
            if (r1.createRouteController(r5, r6, new MediaRouteProvider.RouteControllerOptions.Builder(r7).setClientPackageName(r1.mPackageName).build(), r4) == false) goto L10;
            MediaRouteProviderService.sendGenericSuccess(r2, r3);
            return true;
        L10:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRegisterClient(Messenger r8, int r9, int r10, String r11) {
            if (r10 >= 1) goto L5;
            return false;
        L5:
            if (findClient(r8) >= 0) goto L14;
            ClientRecord r102 = createClientRecord(r8, r10, r11);
            if (r102.register() == false) goto L15;
            addClient(r102);
            String r112 = MediaRouteProviderService.SERVICE_INTERFACE;
            if (r9 == 0) goto L11;
            MediaRouteProviderService.sendMessage(r8, 2, r9, 3, MediaRouteProviderService.createDescriptorBundleForClientVersion(this.mService.getMediaRouteProvider().getDescriptor(), r102.mVersion), null);
        L11:
            return true;
        L15:
            return false;
        L14:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onReleaseRouteController(Messenger r1, int r2, int r3) {
            ClientRecord r0 = getClient(r1);
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.releaseRouteController(r3) == false) goto L10;
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRemoveMemberRoute(Messenger r1, int r2, int r3, String r4) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L8;
            MediaRouteProvider.RouteController r02 = r0.getRouteController(r3);
            if ((r02 instanceof MediaRouteProvider.DynamicGroupRouteController) == false) goto L10;
            ((MediaRouteProvider.DynamicGroupRouteController) r02).onRemoveMemberRoute(r4);
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRouteControlRequest(Messenger r9, int r10, int r11, Intent r12) {
            ClientRecord r2 = getClient(r9);
            if (r2 == null) goto L13;
            MediaRouteProvider.RouteController r7 = r2.getRouteController(r11);
            if (r7 == null) goto L15;
            if (r10 == 0) goto L8;
            Intent r4 = r12;
            AnonymousClass2 r0 = new AnonymousClass2(this, r2, r11, r4, r9, r10);
        L10:
            if (r7.onControlRequest(r4, r0) == false) goto L16;
            String r8 = MediaRouteProviderService.SERVICE_INTERFACE;
            return true;
        L16:
            return false;
        L8:
            r4 = r12;
            r0 = null;
            goto L10
        L15:
            return false;
        L13:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSelectRoute(Messenger r1, int r2, int r3) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L8;
            MediaRouteProvider.RouteController r02 = r0.getRouteController(r3);
            if (r02 == null) goto L10;
            r02.onSelect();
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetDiscoveryRequest(Messenger r1, int r2, MediaRouteDiscoveryRequest r3) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L6;
            r0.setDiscoveryRequest(r3);
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L6:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetRouteVolume(Messenger r1, int r2, int r3, int r4) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L8;
            MediaRouteProvider.RouteController r02 = r0.getRouteController(r3);
            if (r02 == null) goto L10;
            r02.onSetVolume(r4);
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnregisterClient(Messenger r2, int r3) {
            int r0 = findClient(r2);
            if (r0 < 0) goto L6;
            ClientRecord r1 = removeClient(r0);
            String r02 = MediaRouteProviderService.SERVICE_INTERFACE;
            r1.dispose();
            MediaRouteProviderService.sendGenericSuccess(r2, r3);
            return true;
        L6:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnselectRoute(Messenger r1, int r2, int r3, int r4) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L8;
            MediaRouteProvider.RouteController r02 = r0.getRouteController(r3);
            if (r02 == null) goto L10;
            r02.onUnselect(r4);
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateMemberRoutes(Messenger r1, int r2, int r3, List<String> r4) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L8;
            MediaRouteProvider.RouteController r02 = r0.getRouteController(r3);
            if ((r02 instanceof MediaRouteProvider.DynamicGroupRouteController) == false) goto L10;
            ((MediaRouteProvider.DynamicGroupRouteController) r02).onUpdateMemberRoutes(r4);
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateRouteVolume(Messenger r1, int r2, int r3, int r4) {
            ClientRecord r0 = getClient(r1);
            if (r0 == null) goto L8;
            MediaRouteProvider.RouteController r02 = r0.getRouteController(r3);
            if (r02 == null) goto L10;
            r02.onUpdateVolume(r4);
            MediaRouteProviderService.sendGenericSuccess(r1, r2);
            return true;
        L10:
            return false;
        L8:
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void removeAllClientInfoListeners() {
            Object r0 = this.mClientInfoListenersLock;
            monitor-enter(r0);
            this.mClientInfoListeners.clear();     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void removeClientInfoListener(Consumer<List<ClientInfo>> r2) {
            Object r0 = this.mClientInfoListenersLock;
            monitor-enter(r0);
            this.mClientInfoListeners.remove(r2);     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        public void sendDescriptorChanged(MediaRouteProviderDescriptor r10) {
            int r0 = this.mClients.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            ClientRecord r2 = this.mClients.get(r1);
            MediaRouteProviderService.sendMessage(r2.mMessenger, 5, 0, 0, r2.createDescriptorBundle(r10), null);
            r1 = r1 + 1;
            goto L3
        }

        public boolean setBaseDiscoveryRequest(MediaRouteDiscoveryRequest r4) {
            long r0 = SystemClock.elapsedRealtime();
            if (ObjectsCompat.equals(this.mBaseDiscoveryRequest, r4) == true) goto L5;
        L9:
            this.mBaseDiscoveryRequest = r4;
            this.mBaseDiscoveryRequestTimestamp = r0;
            return updateCompositeDiscoveryRequest();
        L5:
            if (r4.isActiveScan() == true) goto L9;
            return false;
        }

        public boolean updateCompositeDiscoveryRequest() {
            this.mActiveScanThrottlingHelper.reset();
            MediaRouteDiscoveryRequest r0 = this.mBaseDiscoveryRequest;
            MediaRouteDiscoveryRequest r1 = null;
            if (r0 == null) goto L5;
            this.mActiveScanThrottlingHelper.requestActiveScan(r0.isActiveScan(), this.mBaseDiscoveryRequestTimestamp);
            MediaRouteSelector.Builder r02 = new MediaRouteSelector.Builder(this.mBaseDiscoveryRequest.getSelector());
        L6:
            int r2 = this.mClients.size();
            int r4 = 0;
        L7:
            if (r4 >= r2) goto L19;
            ClientRecord r5 = this.mClients.get(r4);
            MediaRouteDiscoveryRequest r6 = r5.mDiscoveryRequest;
            if (r6 == null) goto L18;
            if (r6.getSelector().isEmpty() == true) goto L13;
        L14:
            this.mActiveScanThrottlingHelper.requestActiveScan(r6.isActiveScan(), r5.mDiscoveryRequestTimestamp);
            if (r02 != null) goto L17;
            r02 = new MediaRouteSelector.Builder(r6.getSelector());
            goto L18
        L17:
            r02.addSelector(r6.getSelector());
            goto L18
        L13:
            if (r6.isActiveScan() == true) goto L14;
        L18:
            r4 = r4 + 1;
            goto L7
        L19:
            boolean r22 = this.mActiveScanThrottlingHelper.finalizeActiveScanAndScheduleSuppressActiveScanRunnable();
            if (r02 == null) goto L24;
            r1 = new MediaRouteDiscoveryRequest(r02.build(), r22);
        L24:
            if (ObjectsCompat.equals(this.mCompositeDiscoveryRequest, r1) == true) goto L30;
            this.mCompositeDiscoveryRequest = r1;
            MediaRouteProvider r11 = this.mService.getMediaRouteProvider();
            if (r11 == null) goto L36;
            r11.setDiscoveryRequest(r1);
            return true;
        L36:
            return true;
        L30:
            return false;
        L5:
            r02 = null;
            goto L6
        }
    }

    public final class PrivateHandler extends Handler {
        final /* synthetic */ MediaRouteProviderService this$0;

        public PrivateHandler(MediaRouteProviderService r1) {
            this.this$0 = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r3) {
            if (r3.what == 1) goto L5;
            return;
        L5:
            this.this$0.mImpl.onBinderDied((Messenger) r3.obj);
        }
    }

    public static final class ReceiveHandler extends Handler {
        private final WeakReference<MediaRouteProviderService> mServiceRef;

        public ReceiveHandler(MediaRouteProviderService r2) {
            this.mServiceRef = new WeakReference(r2);
        }

        private boolean processMessage(int r5, Messenger r6, int r7, int r8, Object r9, Bundle r10, String r11) {
            MediaRouteProviderService r4 = this.mServiceRef.get();
            int r0 = 0;
            if (r4 != null) goto L5;
        L69:
            return false;
        L5:
            switch(r5) {
                case 1: goto L68;
                case 2: goto L66;
                case 3: goto L58;
                case 4: goto L57;
                case 5: goto L55;
                case 6: goto L49;
                case 7: goto L45;
                case 8: goto L41;
                case 9: goto L38;
                case 10: goto L26;
                case 11: goto L19;
                case 12: goto L15;
                case 13: goto L11;
                case 14: goto L7;
                default: goto L69;
            };
        L7:
            ArrayList<String> r52 = r10.getStringArrayList(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_IDS);
            if (r52 == null) goto L69;
            return r4.mImpl.onUpdateMemberRoutes(r6, r7, r8, r52);
        L11:
            String r53 = r10.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
            if (r53 == null) goto L69;
            return r4.mImpl.onRemoveMemberRoute(r6, r7, r8, r53);
        L15:
            String r54 = r10.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
            if (r54 == null) goto L69;
            return r4.mImpl.onAddMemberRoute(r6, r7, r8, r54);
        L19:
            String r92 = r10.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
            Bundle r55 = (Bundle) r10.getParcelable(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_CONTROLLER_OPTIONS);
            if (r55 == null) goto L22;
            MediaRouteProvider.RouteControllerOptions r102 = new MediaRouteProvider.RouteControllerOptions(r55);
        L23:
            if (r92 == null) goto L69;
            return r4.mImpl.onCreateDynamicGroupRouteController(r6, r7, r8, r92, r102);
        L22:
            r102 = MediaRouteProvider.RouteControllerOptions.EMPTY;
            goto L23
        L26:
            if (r9 != null) goto L28;
        L29:
            MediaRouteDiscoveryRequest r56 = MediaRouteDiscoveryRequest.fromBundle((Bundle) r9);
            MediaRouteProviderServiceImpl r42 = r4.mImpl;
            if (r56 != null) goto L32;
        L34:
            r56 = null;
        L36:
            return r42.onSetDiscoveryRequest(r6, r7, r56);
        L32:
            if (r56.isValid() == false) goto L34;
        L28:
            if ((r9 instanceof Bundle) == false) goto L69;
        L41:
            int r57 = r10.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, 0);
            if (r57 == 0) goto L69;
            return r4.mImpl.onUpdateRouteVolume(r6, r7, r8, r57);
        L45:
            int r58 = r10.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, -1);
            if (r58 < 0) goto L69;
            return r4.mImpl.onSetRouteVolume(r6, r7, r8, r58);
        L49:
            if (r10 == null) goto L53;
            r0 = r10.getInt(MediaRouteProviderProtocol.CLIENT_DATA_UNSELECT_REASON, 0);
        L53:
            return r4.mImpl.onUnselectRoute(r6, r7, r8, r0);
        L58:
            String r93 = r10.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
            String r59 = r10.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_LIBRARY_GROUP);
            Bundle r103 = (Bundle) r10.getParcelable(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_CONTROLLER_OPTIONS);
            if (r103 == null) goto L61;
            MediaRouteProvider.RouteControllerOptions r112 = new MediaRouteProvider.RouteControllerOptions(r103);
        L62:
            if (r93 == null) goto L69;
            return r4.mImpl.onCreateRouteController(r6, r7, r8, r93, r59, r112);
        L61:
            r112 = MediaRouteProvider.RouteControllerOptions.EMPTY;
            goto L62
        L38:
            if ((r9 instanceof Intent) == false) goto L69;
            return r4.mImpl.onRouteControlRequest(r6, r7, r8, (Intent) r9);
        L55:
            return r4.mImpl.onSelectRoute(r6, r7, r8);
        L57:
            return r4.mImpl.onReleaseRouteController(r6, r7, r8);
        L66:
            return r4.mImpl.onUnregisterClient(r6, r7);
        L68:
            return r4.mImpl.onRegisterClient(r6, r7, r8, r11);
        }

        @Override // android.os.Handler
        public void handleMessage(Message r9) {
            Messenger r2 = r9.replyTo;
            if (MediaRouteProviderProtocol.isValidRemoteMessenger(r2) == false) goto L17;
            int r1 = r9.what;
            int r3 = r9.arg1;
            int r4 = r9.arg2;
            Object r5 = r9.obj;
            Bundle r6 = r9.peekData();
            if (r1 != 1) goto L12;
            String[] r92 = this.mServiceRef.get().getPackageManager().getPackagesForUid(r9.sendingUid);
            if (r92 == null) goto L12;
            if (r92.length <= 0) goto L12;
            String r93 = r92[0];
        L14:
            if (processMessage(r1, r2, r3, r4, r5, r6, r93) == true) goto L19;
            MediaRouteProviderService.sendGenericFailure(r2, r3);
            return;
        L19:
            return;
        L12:
            r93 = null;
            goto L14
        L17:
            String r8 = MediaRouteProviderService.SERVICE_INTERFACE;
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRouteProviderService() {
        ReceiveHandler r0 = new ReceiveHandler(this);
        this.mReceiveHandler = r0;
        this.mReceiveMessenger = new Messenger(r0);
        this.mPrivateHandler = new PrivateHandler(this);
        if (Build.VERSION.SDK_INT < 30) goto L5;
        this.mImpl = new MediaRouteProviderServiceImplApi30(this);
    L6:
        this.mProviderCallback = this.mImpl.getProviderCallback();
        return;
    L5:
        this.mImpl = new MediaRouteProviderServiceImplBase(this);
        goto L6
    }

    public static Bundle createDescriptorBundleForClientVersion(MediaRouteProviderDescriptor r3, int r4) {
        if (r3 != null) goto L5;
        return null;
    L5:
        MediaRouteProviderDescriptor.Builder r1 = new MediaRouteProviderDescriptor.Builder(r3);
        r1.setRoutes(null);
        if (r4 >= 4) goto L8;
        r1.setSupportsDynamicGroupRoute(false);
    L8:
        Iterator<MediaRouteDescriptor> r32 = r3.getRoutes().iterator();
    L10:
        if (r32.hasNext() == false) goto L17;
        MediaRouteDescriptor r0 = r32.next();
        if (r4 < r0.getMinClientVersion()) goto L10;
        if (r4 > r0.getMaxClientVersion()) goto L10;
        r1.addRoute(r0);
        goto L10
    L17:
        return r1.build().asBundle();
    }

    public static String getClientId(Messenger r2) {
        return "Client connection " + r2.getBinder().toString();
    }

    public static void sendGenericFailure(Messenger r6, int r7) {
        if (r7 == 0) goto L5;
        sendMessage(r6, 0, r7, 0, null, null);
        return;
    }

    public static void sendGenericSuccess(Messenger r6, int r7) {
        if (r7 == 0) goto L5;
        sendMessage(r6, 1, r7, 0, null, null);
        return;
    }

    public static void sendMessage(Messenger r1, int r2, int r3, int r4, Object r5, Bundle r6) {
        Message r0 = Message.obtain();
        r0.what = r2;
        r0.arg1 = r3;
        r0.arg2 = r4;
        r0.obj = r5;
        r0.setData(r6);
        r1.send(r0);     // Catch: RemoteException -> L5 DeadObjectException -> L8
        return;
    L5:
        e = move-exception;
        Log.e(TAG, "Could not send message to " + getClientId(r1), e);
        return;
    }

    public void addClientInfoListener(Executor r1, Consumer<List<ClientInfo>> r2) {
        this.mImpl.addClientInfoListener(r1, r2);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public void attachBaseContext(Context r1) {
        super.attachBaseContext(r1);
        this.mImpl.attachBaseContext(r1);
    }

    public void ensureProvider() {
        if (this.mProvider != null) goto L12;
        MediaRouteProvider r0 = onCreateMediaRouteProvider();
        if (r0 == null) goto L13;
        String r1 = r0.getMetadata().getPackageName();
        if (r1.equals(getPackageName()) == false) goto L10;
        this.mProvider = r0;
        r0.setCallback(this.mProviderCallback);
        return;
    L10:
        WorkerFactory$$ExternalSyntheticBUOutline0.m("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: ", r1, ".  Service package name: ", getPackageName(), ".");
        return;
    L13:
        return;
    }

    public MediaRouteProvider getMediaRouteProvider() {
        return this.mProvider;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return this.mImpl.onBind(r1);
    }

    public abstract MediaRouteProvider onCreateMediaRouteProvider();

    @Override // android.app.Service
    public void onDestroy() {
        MediaRouteProvider r0 = this.mProvider;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.mImpl.removeAllClientInfoListeners();
        super.onDestroy();
    }

    public void removeClientInfoListener(Consumer<List<ClientInfo>> r1) {
        this.mImpl.removeClientInfoListener(r1);
    }
}
