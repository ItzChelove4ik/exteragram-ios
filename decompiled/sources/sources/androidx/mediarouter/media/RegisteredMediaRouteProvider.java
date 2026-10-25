package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouter;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
final class RegisteredMediaRouteProvider extends MediaRouteProvider implements ServiceConnection {
    static final boolean DEBUG = false;
    static final String TAG = "MediaRouteProviderProxy";
    private Connection mActiveConnection;
    private boolean mBound;
    private final ComponentName mComponentName;
    private boolean mConnectionReady;
    private ControllerCallback mControllerCallback;
    private final ArrayList<ControllerConnection> mControllerConnections;
    final PrivateHandler mPrivateHandler;
    private boolean mStarted;

    public final class Connection implements IBinder.DeathRecipient {
        private int mNextControllerId;
        private int mNextRequestId;
        private final SparseArray<MediaRouter.ControlRequestCallback> mPendingCallbacks;
        private int mPendingRegisterRequestId;
        private final ReceiveHandler mReceiveHandler;
        private final Messenger mReceiveMessenger;
        private final Messenger mServiceMessenger;
        private int mServiceVersion;
        final /* synthetic */ RegisteredMediaRouteProvider this$0;

        /* renamed from: androidx.mediarouter.media.RegisteredMediaRouteProvider$Connection$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ Connection this$1;

            public AnonymousClass1(Connection r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.failPendingCallbacks();
            }
        }

        /* renamed from: androidx.mediarouter.media.RegisteredMediaRouteProvider$Connection$2, reason: invalid class name */
        public class AnonymousClass2 implements Runnable {
            final /* synthetic */ Connection this$1;

            public AnonymousClass2(Connection r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Connection r1 = this.this$1;
                r1.this$0.onConnectionDied(r1);
            }
        }

        public Connection(RegisteredMediaRouteProvider r1, Messenger r2) {
            this.this$0 = r1;
            this.mNextRequestId = 1;
            this.mNextControllerId = 1;
            this.mPendingCallbacks = new SparseArray();
            this.mServiceMessenger = r2;
            ReceiveHandler r12 = new ReceiveHandler(this);
            this.mReceiveHandler = r12;
            this.mReceiveMessenger = new Messenger(r12);
        }

        private boolean sendRequest(int r2, int r3, int r4, Object r5, Bundle r6) {
            Message r0 = Message.obtain();
            r0.what = r2;
            r0.arg1 = r3;
            r0.arg2 = r4;
            r0.obj = r5;
            r0.setData(r6);
            r0.replyTo = this.mReceiveMessenger;
            this.mServiceMessenger.send(r0);     // Catch: RemoteException -> L6 DeadObjectException -> L12
            return true;
        L6:
            e = move-exception;
            if (r2 == 2) goto L15;
            Log.e("MediaRouteProviderProxy", "Could not send message to service.", e);
            return false;
        L15:
            return false;
        L16:
            return false;
        }

        public void addMemberRoute(int r7, String r8) {
            Bundle r5 = new Bundle();
            r5.putString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID, r8);
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(12, r2, r7, null, r5);
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.this$0.mPrivateHandler.post(new AnonymousClass2(this));
        }

        public int createDynamicGroupRouteController(String r7, MediaRouteProvider.RouteControllerOptions r8, MediaRouter.ControlRequestCallback r9) {
            int r3 = this.mNextControllerId;
            this.mNextControllerId = r3 + 1;
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            Bundle r5 = new Bundle();
            r5.putString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID, r7);
            r5.putParcelable(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_CONTROLLER_OPTIONS, r8.asBundle());
            sendRequest(11, r2, r3, null, r5);
            this.mPendingCallbacks.put(r2, r9);
            return r3;
        }

        public int createRouteController(String r7, String r8, MediaRouteProvider.RouteControllerOptions r9) {
            int r3 = this.mNextControllerId;
            this.mNextControllerId = r3 + 1;
            Bundle r5 = new Bundle();
            r5.putString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID, r7);
            r5.putString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_LIBRARY_GROUP, r8);
            r5.putParcelable(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_CONTROLLER_OPTIONS, r9.asBundle());
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(3, r2, r3, null, r5);
            return r3;
        }

        public void dispose() {
            sendRequest(2, 0, 0, null, null);
            this.mReceiveHandler.dispose();
            this.mServiceMessenger.getBinder().unlinkToDeath(this, 0);
            this.this$0.mPrivateHandler.post(new AnonymousClass1(this));
        }

        public void failPendingCallbacks() {
            int r0 = this.mPendingCallbacks.size();
            int r1 = 0;
        L3:
            SparseArray<MediaRouter.ControlRequestCallback> r2 = this.mPendingCallbacks;
            if (r1 >= r0) goto L6;
            r2.valueAt(r1).onError(null, null);
            r1 = r1 + 1;
            goto L3
        L6:
            r2.clear();
        }

        public boolean onControlRequestFailed(int r2, String r3, Bundle r4) {
            MediaRouter.ControlRequestCallback r0 = this.mPendingCallbacks.get(r2);
            if (r0 == null) goto L6;
            this.mPendingCallbacks.remove(r2);
            r0.onError(r3, r4);
            return true;
        L6:
            return false;
        }

        public boolean onControlRequestSucceeded(int r2, Bundle r3) {
            MediaRouter.ControlRequestCallback r0 = this.mPendingCallbacks.get(r2);
            if (r0 == null) goto L6;
            this.mPendingCallbacks.remove(r2);
            r0.onResult(r3);
            return true;
        L6:
            return false;
        }

        public void onControllerReleasedByProvider(int r2) {
            this.this$0.onConnectionControllerReleasedByProvider(this, r2);
        }

        public boolean onDescriptorChanged(Bundle r2) {
            if (this.mServiceVersion == 0) goto L6;
            this.this$0.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(r2));
            return true;
        L6:
            return false;
        }

        public void onDynamicGroupRouteControllerCreated(int r3, Bundle r4) {
            MediaRouter.ControlRequestCallback r0 = this.mPendingCallbacks.get(r3);
            if (r4 != null) goto L5;
        L8:
            r0.onError("DynamicGroupRouteController is created without valid route id.", r4);
            return;
        L5:
            if (r4.containsKey(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID) == false) goto L8;
            this.mPendingCallbacks.remove(r3);
            r0.onResult(r4);
        }

        public boolean onDynamicRouteDescriptorsChanged(int r6, Bundle r7) {
            int r1 = 0;
            if (this.mServiceVersion == 0) goto L13;
            Bundle r0 = (Bundle) r7.getParcelable(MediaRouteProviderProtocol.DATA_KEY_GROUP_ROUTE_DESCRIPTOR);
            if (r0 == null) goto L7;
            MediaRouteDescriptor r02 = MediaRouteDescriptor.fromBundle(r0);
        L8:
            ArrayList r72 = r7.getParcelableArrayList(MediaRouteProviderProtocol.DATA_KEY_DYNAMIC_ROUTE_DESCRIPTORS);
            ArrayList r2 = new ArrayList();
            int r3 = r72.size();
        L9:
            if (r1 >= r3) goto L11;
            Object r4 = r72.get(r1);
            r1 = r1 + 1;
            r2.add(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor.fromBundle((Bundle) r4));
            goto L9
        L11:
            this.this$0.onDynamicRouteDescriptorChanged(this, r6, r02, r2);
            return true;
        L7:
            r02 = null;
            goto L8
        L13:
            return false;
        }

        public void onGenericFailure(int r3) {
            if (r3 != this.mPendingRegisterRequestId) goto L5;
            this.mPendingRegisterRequestId = 0;
            this.this$0.onConnectionError(this, "Registration failed");
        L5:
            MediaRouter.ControlRequestCallback r0 = this.mPendingCallbacks.get(r3);
            if (r0 == null) goto L9;
            this.mPendingCallbacks.remove(r3);
            r0.onError(null, null);
            return;
        }

        public boolean onRegistered(int r3, int r4, Bundle r5) {
            if (this.mServiceVersion == 0) goto L5;
        L10:
            return false;
        L5:
            if (r3 != this.mPendingRegisterRequestId) goto L10;
            if (r4 < 1) goto L10;
            this.mPendingRegisterRequestId = 0;
            this.mServiceVersion = r4;
            this.this$0.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(r5));
            this.this$0.onConnectionReady(this);
            return true;
        }

        public boolean register() {
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            this.mPendingRegisterRequestId = r2;
            if (sendRequest(1, r2, 4, null, null) == true) goto L10;
            return false;
        L10:
            this.mServiceMessenger.getBinder().linkToDeath(this, 0);     // Catch: RemoteException -> L8
            return true;
        L8:
            binderDied();
            return false;
        }

        public void releaseRouteController(int r7) {
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(4, r2, r7, null, null);
        }

        public void removeMemberRoute(int r7, String r8) {
            Bundle r5 = new Bundle();
            r5.putString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID, r8);
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(13, r2, r7, null, r5);
        }

        public void selectRoute(int r7) {
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(5, r2, r7, null, null);
        }

        public boolean sendControlRequest(int r7, Intent r8, MediaRouter.ControlRequestCallback r9) {
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            if (sendRequest(9, r2, r7, r8, null) == false) goto L8;
            if (r9 == null) goto L10;
            this.mPendingCallbacks.put(r2, r9);
            return true;
        L10:
            return true;
        L8:
            return false;
        }

        public void setDiscoveryRequest(MediaRouteDiscoveryRequest r7) {
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            if (r7 == null) goto L6;
            Bundle r72 = r7.asBundle();
        L7:
            sendRequest(10, r2, 0, r72, null);
            return;
        L6:
            r72 = null;
            goto L7
        }

        public void setVolume(int r7, int r8) {
            Bundle r5 = new Bundle();
            r5.putInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, r8);
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(7, r2, r7, null, r5);
        }

        public void unselectRoute(int r7, int r8) {
            Bundle r5 = new Bundle();
            r5.putInt(MediaRouteProviderProtocol.CLIENT_DATA_UNSELECT_REASON, r8);
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(6, r2, r7, null, r5);
        }

        public void updateMemberRoutes(int r7, List<String> r8) {
            Bundle r5 = new Bundle();
            r5.putStringArrayList(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_IDS, new ArrayList(r8));
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(14, r2, r7, null, r5);
        }

        public void updateVolume(int r7, int r8) {
            Bundle r5 = new Bundle();
            r5.putInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, r8);
            int r2 = this.mNextRequestId;
            this.mNextRequestId = r2 + 1;
            sendRequest(8, r2, r7, null, r5);
        }
    }

    public interface ControllerCallback {
        void onControllerReleasedByProvider(MediaRouteProvider.RouteController r1);
    }

    public interface ControllerConnection {
        void attachConnection(Connection r1);

        void detachConnection();

        int getControllerId();
    }

    public static final class PrivateHandler extends Handler {
        public PrivateHandler() {
        }
    }

    public static final class ReceiveHandler extends Handler {
        private final WeakReference<Connection> mConnectionRef;

        public ReceiveHandler(Connection r2) {
            this.mConnectionRef = new WeakReference(r2);
        }

        private boolean processMessage(Connection r1, int r2, int r3, int r4, Object r5, Bundle r6) {
            switch(r2) {
                case 0: goto L41;
                case 1: goto L43;
                case 2: goto L33;
                case 3: goto L28;
                case 4: goto L20;
                case 5: goto L15;
                case 6: goto L12;
                case 7: goto L6;
                case 8: goto L5;
                default: goto L51;
            };
        L5:
            r1.onControllerReleasedByProvider(r4);
            return false;
        L6:
            if (r5 == null) goto L10;
            if ((r5 instanceof Bundle) == true) goto L10;
            return false;
        L10:
            return r1.onDynamicRouteDescriptorsChanged(r4, (Bundle) r5);
        L15:
            if (r5 == null) goto L19;
            if ((r5 instanceof Bundle) == true) goto L19;
            return false;
        L19:
            return r1.onDescriptorChanged((Bundle) r5);
        L20:
            if (r5 != null) goto L22;
        L23:
            if (r6 != null) goto L25;
            String r0 = null;
        L27:
            return r1.onControlRequestFailed(r3, r0, (Bundle) r5);
        L25:
            r0 = r6.getString("error");
            goto L27
        L22:
            if ((r5 instanceof Bundle) == true) goto L23;
            return false;
        L28:
            if (r5 == null) goto L32;
            if ((r5 instanceof Bundle) == true) goto L32;
            return false;
        L32:
            return r1.onControlRequestSucceeded(r3, (Bundle) r5);
        L33:
            if (r5 == null) goto L40;
            if ((r5 instanceof Bundle) == true) goto L40;
            return false;
        L40:
            return r1.onRegistered(r3, r4, (Bundle) r5);
        L41:
            r1.onGenericFailure(r3);
            return true;
        L43:
            return true;
        L51:
            return false;
        L12:
            if ((r5 instanceof Bundle) == false) goto L14;
            r1.onDynamicGroupRouteControllerCreated(r3, (Bundle) r5);
            return false;
        L14:
            Log.w("MediaRouteProviderProxy", "No further information on the dynamic group controller");
            return false;
        }

        public void dispose() {
            this.mConnectionRef.clear();
        }

        @Override // android.os.Handler
        public void handleMessage(Message r9) {
            Connection r2 = this.mConnectionRef.get();
            if (r2 != null) goto L5;
            return;
        L5:
            if (processMessage(r2, r9.what, r9.arg1, r9.arg2, r9.obj, r9.peekData()) == true) goto L9;
            String r8 = RegisteredMediaRouteProvider.TAG;
            return;
        }
    }

    public final class RegisteredDynamicController extends MediaRouteProvider.DynamicGroupRouteController implements ControllerConnection {
        private Connection mConnection;
        private int mControllerId;
        String mGroupableSectionTitle;
        private final String mInitialMemberRouteId;
        private int mPendingSetVolume;
        private int mPendingUpdateVolumeDelta;
        private final MediaRouteProvider.RouteControllerOptions mRouteControllerOptions;
        private boolean mSelected;
        String mTransferableSectionTitle;
        final /* synthetic */ RegisteredMediaRouteProvider this$0;

        /* renamed from: androidx.mediarouter.media.RegisteredMediaRouteProvider$RegisteredDynamicController$1, reason: invalid class name */
        public class AnonymousClass1 extends MediaRouter.ControlRequestCallback {
            final /* synthetic */ RegisteredDynamicController this$1;

            public AnonymousClass1(RegisteredDynamicController r1) {
                this.this$1 = r1;
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onError(String r2, Bundle r3) {
                Log.d("MediaRouteProviderProxy", "Error: " + r2 + ", data: " + r3);
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onResult(Bundle r3) {
                RegisteredDynamicController r0 = this.this$1;
                r0.mGroupableSectionTitle = r3.getString(MediaRouteProviderProtocol.DATA_KEY_GROUPABLE_SECTION_TITLE);
                RegisteredDynamicController r2 = this.this$1;
                r2.mTransferableSectionTitle = r3.getString(MediaRouteProviderProtocol.DATA_KEY_TRANSFERABLE_SECTION_TITLE);
            }
        }

        public RegisteredDynamicController(RegisteredMediaRouteProvider r1, String r2, MediaRouteProvider.RouteControllerOptions r3) {
            this.this$0 = r1;
            this.mPendingSetVolume = -1;
            this.mControllerId = -1;
            this.mInitialMemberRouteId = r2;
            this.mRouteControllerOptions = r3;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void attachConnection(Connection r4) {
            AnonymousClass1 r0 = new AnonymousClass1(this);
            this.mConnection = r4;
            int r02 = r4.createDynamicGroupRouteController(this.mInitialMemberRouteId, this.mRouteControllerOptions, r0);
            this.mControllerId = r02;
            if (this.mSelected == false) goto L11;
            r4.selectRoute(r02);
            int r03 = this.mPendingSetVolume;
            if (r03 < 0) goto L7;
            r4.setVolume(this.mControllerId, r03);
            this.mPendingSetVolume = -1;
        L7:
            int r04 = this.mPendingUpdateVolumeDelta;
            if (r04 == 0) goto L12;
            r4.updateVolume(this.mControllerId, r04);
            this.mPendingUpdateVolumeDelta = 0;
            return;
        L12:
            return;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void detachConnection() {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.releaseRouteController(this.mControllerId);
            this.mConnection = null;
            this.mControllerId = 0;
            return;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public int getControllerId() {
            return this.mControllerId;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public String getGroupableSelectionTitle() {
            return this.mGroupableSectionTitle;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public String getTransferableSectionTitle() {
            return this.mTransferableSectionTitle;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(String r2) {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.addMemberRoute(this.mControllerId, r2);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(Intent r2, MediaRouter.ControlRequestCallback r3) {
            Connection r0 = this.mConnection;
            if (r0 != null) goto L5;
            return false;
        L5:
            return r0.sendControlRequest(this.mControllerId, r2, r3);
        }

        public void onDynamicRoutesChanged(MediaRouteDescriptor r1, List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r2) {
            notifyDynamicRoutesChanged(r1, r2);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.this$0.onControllerReleased(this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(String r2) {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.removeMemberRoute(this.mControllerId, r2);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mSelected = true;
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.selectRoute(this.mControllerId);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int r2) {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.setVolume(this.mControllerId, r2);
            return;
        L6:
            this.mPendingSetVolume = r2;
            this.mPendingUpdateVolumeDelta = 0;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect() {
            onUnselect(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int r2) {
            this.mSelected = false;
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.unselectRoute(this.mControllerId, r2);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(List<String> r2) {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.updateMemberRoutes(this.mControllerId, r2);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int r2) {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.updateVolume(this.mControllerId, r2);
            return;
        L6:
            this.mPendingUpdateVolumeDelta += r2;
        }
    }

    public final class RegisteredRouteController extends MediaRouteProvider.RouteController implements ControllerConnection {
        private Connection mConnection;
        private int mControllerId;
        private int mPendingSetVolume;
        private int mPendingUpdateVolumeDelta;
        private final MediaRouteProvider.RouteControllerOptions mRouteControllerOptions;
        private final String mRouteGroupId;
        private final String mRouteId;
        private boolean mSelected;
        final /* synthetic */ RegisteredMediaRouteProvider this$0;

        public RegisteredRouteController(RegisteredMediaRouteProvider r1, String r2, String r3, MediaRouteProvider.RouteControllerOptions r4) {
            this.this$0 = r1;
            this.mPendingSetVolume = -1;
            this.mRouteId = r2;
            this.mRouteGroupId = r3;
            this.mRouteControllerOptions = r4;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void attachConnection(Connection r4) {
            this.mConnection = r4;
            int r0 = r4.createRouteController(this.mRouteId, this.mRouteGroupId, this.mRouteControllerOptions);
            this.mControllerId = r0;
            if (this.mSelected == false) goto L11;
            r4.selectRoute(r0);
            int r02 = this.mPendingSetVolume;
            if (r02 < 0) goto L7;
            r4.setVolume(this.mControllerId, r02);
            this.mPendingSetVolume = -1;
        L7:
            int r03 = this.mPendingUpdateVolumeDelta;
            if (r03 == 0) goto L12;
            r4.updateVolume(this.mControllerId, r03);
            this.mPendingUpdateVolumeDelta = 0;
            return;
        L12:
            return;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void detachConnection() {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.releaseRouteController(this.mControllerId);
            this.mConnection = null;
            this.mControllerId = 0;
            return;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public int getControllerId() {
            return this.mControllerId;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(Intent r2, MediaRouter.ControlRequestCallback r3) {
            Connection r0 = this.mConnection;
            if (r0 != null) goto L5;
            return false;
        L5:
            return r0.sendControlRequest(this.mControllerId, r2, r3);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.this$0.onControllerReleased(this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mSelected = true;
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.selectRoute(this.mControllerId);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int r2) {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.setVolume(this.mControllerId, r2);
            return;
        L6:
            this.mPendingSetVolume = r2;
            this.mPendingUpdateVolumeDelta = 0;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect() {
            onUnselect(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int r2) {
            this.mSelected = false;
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.unselectRoute(this.mControllerId, r2);
            return;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int r2) {
            Connection r0 = this.mConnection;
            if (r0 == null) goto L6;
            r0.updateVolume(this.mControllerId, r2);
            return;
        L6:
            this.mPendingUpdateVolumeDelta += r2;
        }
    }

    static {
        Log.isLoggable("MediaRouteProviderProxy", 3);
    }

    public RegisteredMediaRouteProvider(Context r2, ComponentName r3) {
        super(r2, new MediaRouteProvider.ProviderMetadata(r3));
        this.mControllerConnections = new ArrayList();
        this.mComponentName = r3;
        this.mPrivateHandler = new PrivateHandler();
    }

    private void attachControllersToConnection() {
        int r0 = this.mControllerConnections.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mControllerConnections.get(r1).attachConnection(this.mActiveConnection);
        r1 = r1 + 1;
        goto L3
    }

    private void bind() {
        if (this.mBound == true) goto L14;
        Intent r0 = new Intent(MediaRouteProviderProtocol.SERVICE_INTERFACE);
        r0.setComponent(this.mComponentName);
        if (Build.VERSION.SDK_INT < 29) goto L8;
        int r1 = 4097;
    L9:
        this.mBound = getContext().bindService(r0, this, r1);     // Catch: SecurityException -> L11
        return;
    L8:
        r1 = 1;
    L15:
        return;
    }

    private MediaRouteProvider.DynamicGroupRouteController createDynamicGroupRouteController(String r5, MediaRouteProvider.RouteControllerOptions r6) {
        MediaRouteProviderDescriptor r0 = getDescriptor();
        if (r0 == null) goto L14;
        List<MediaRouteDescriptor> r02 = r0.getRoutes();
        int r1 = r02.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L18;
        if (r02.get(r2).getId().equals(r5) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        RegisteredDynamicController r03 = new RegisteredDynamicController(this, r5, r6);
        this.mControllerConnections.add(r03);
        if (this.mConnectionReady == false) goto L11;
        r03.attachConnection(this.mActiveConnection);
    L11:
        updateBinding();
        return r03;
    L18:
        return null;
    L14:
        return null;
    }

    private MediaRouteProvider.RouteController createRouteController(String r5, String r6, MediaRouteProvider.RouteControllerOptions r7) {
        MediaRouteProviderDescriptor r0 = getDescriptor();
        if (r0 == null) goto L14;
        List<MediaRouteDescriptor> r02 = r0.getRoutes();
        int r1 = r02.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L18;
        if (r02.get(r2).getId().equals(r5) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        RegisteredRouteController r03 = new RegisteredRouteController(this, r5, r6, r7);
        this.mControllerConnections.add(r03);
        if (this.mConnectionReady == false) goto L11;
        r03.attachConnection(this.mActiveConnection);
    L11:
        updateBinding();
        return r03;
    L18:
        return null;
    L14:
        return null;
    }

    private void detachControllersFromConnection() {
        int r0 = this.mControllerConnections.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mControllerConnections.get(r1).detachConnection();
        r1 = r1 + 1;
        goto L3
    }

    private void disconnect() {
        if (this.mActiveConnection == null) goto L6;
        setDescriptor(null);
        this.mConnectionReady = false;
        detachControllersFromConnection();
        this.mActiveConnection.dispose();
        this.mActiveConnection = null;
        return;
    }

    private ControllerConnection findControllerById(int r5) {
        ArrayList<ControllerConnection> r4 = this.mControllerConnections;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        ControllerConnection r2 = r4.get(r1);
        r1 = r1 + 1;
        ControllerConnection r22 = r2;
        if (r22.getControllerId() != r5) goto L3;
        return r22;
    L7:
        return null;
    }

    private boolean shouldBind() {
        if (this.mStarted == true) goto L5;
        return false;
    L5:
        if (getDiscoveryRequest() == null) goto L8;
        return true;
    L8:
        if (this.mControllerConnections.isEmpty() == true) goto L12;
        return true;
    L12:
        return false;
    }

    private void unbind() {
        if (this.mBound == false) goto L12;
        this.mBound = false;
        disconnect();
        getContext().unbindService(this);     // Catch: IllegalArgumentException -> L7
        return;
    L7:
        e = move-exception;
        Log.e("MediaRouteProviderProxy", this + ": unbindService failed", e);
        return;
    }

    private void updateBinding() {
        if (shouldBind() == false) goto L6;
        bind();
        return;
    L6:
        unbind();
    }

    public boolean hasComponentName(String r2, String r3) {
        if (this.mComponentName.getPackageName().equals(r2) == true) goto L5;
        return false;
    L5:
        if (this.mComponentName.getClassName().equals(r3) == false) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConnectionControllerReleasedByProvider(Connection r2, int r3) {
        if (this.mActiveConnection != r2) goto L12;
        ControllerConnection r22 = findControllerById(r3);
        ControllerCallback r32 = this.mControllerCallback;
        if (r32 != null) goto L7;
    L9:
        if (r22 == 0) goto L13;
        onControllerReleased(r22);
        return;
    L13:
        return;
    L7:
        if ((r22 instanceof MediaRouteProvider.RouteController) == false) goto L9;
        r32.onControllerReleasedByProvider((MediaRouteProvider.RouteController) r22);
        goto L9
    }

    public void onConnectionDescriptorChanged(Connection r2, MediaRouteProviderDescriptor r3) {
        if (this.mActiveConnection != r2) goto L6;
        setDescriptor(r3);
        return;
    }

    public void onConnectionDied(Connection r2) {
        if (this.mActiveConnection != r2) goto L6;
        disconnect();
        return;
    }

    public void onConnectionError(Connection r1, String r2) {
        if (this.mActiveConnection != r1) goto L6;
        unbind();
        return;
    }

    public void onConnectionReady(Connection r2) {
        if (this.mActiveConnection != r2) goto L8;
        this.mConnectionReady = true;
        attachControllersToConnection();
        MediaRouteDiscoveryRequest r22 = getDiscoveryRequest();
        if (r22 == null) goto L9;
        this.mActiveConnection.setDiscoveryRequest(r22);
        return;
    L9:
        return;
    }

    public void onControllerReleased(ControllerConnection r2) {
        this.mControllerConnections.remove(r2);
        r2.detachConnection();
        updateBinding();
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.DynamicGroupRouteController onCreateDynamicGroupRouteController(String r1, MediaRouteProvider.RouteControllerOptions r2) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("initialMemberRouteId cannot be null.");
        return null;
    L4:
        return createDynamicGroupRouteController(r1, r2);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String r2, MediaRouteProvider.RouteControllerOptions r3) {
        if (r2 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("routeId cannot be null");
        return null;
    L5:
        return createRouteController(r2, null, r3);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String r2, String r3) {
        if (r2 == null) goto L9;
        if (r3 != null) goto L6;
        f$$ExternalSyntheticBUOutline1.m("routeGroupId cannot be null");
        return null;
    L6:
        return createRouteController(r2, r3, MediaRouteProvider.RouteControllerOptions.EMPTY);
    L9:
        f$$ExternalSyntheticBUOutline1.m("routeId cannot be null");
        return null;
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest r2) {
        if (this.mConnectionReady == false) goto L5;
        this.mActiveConnection.setDiscoveryRequest(r2);
    L5:
        updateBinding();
    }

    public void onDynamicRouteDescriptorChanged(Connection r2, int r3, MediaRouteDescriptor r4, List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r5) {
        if (this.mActiveConnection != r2) goto L8;
        ControllerConnection r1 = findControllerById(r3);
        if ((r1 instanceof RegisteredDynamicController) == false) goto L9;
        ((RegisteredDynamicController) r1).onDynamicRoutesChanged(r4, r5);
        return;
    L9:
        return;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName r1, IBinder r2) {
        if (this.mBound == false) goto L16;
        disconnect();
        if (r2 == null) goto L7;
        Messenger r12 = new Messenger(r2);
    L9:
        if (MediaRouteProviderProtocol.isValidRemoteMessenger(r12) == false) goto L14;
        Connection r22 = new Connection(this, r12);
        if (r22.register() == false) goto L17;
        this.mActiveConnection = r22;
        return;
    L17:
        return;
    L14:
        Log.e("MediaRouteProviderProxy", this + ": Service returned invalid messenger binder");
        return;
    L7:
        r12 = null;
        goto L9
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName r1) {
        disconnect();
    }

    public void rebindIfDisconnected() {
        if (this.mActiveConnection == null) goto L5;
        return;
    L5:
        if (shouldBind() == false) goto L9;
        unbind();
        bind();
        return;
    }

    public void setControllerCallback(ControllerCallback r1) {
        this.mControllerCallback = r1;
    }

    public void start() {
        if (this.mStarted == true) goto L6;
        this.mStarted = true;
        updateBinding();
        return;
    }

    public void stop() {
        if (this.mStarted == false) goto L6;
        this.mStarted = false;
        updateBinding();
        return;
    }

    public String toString() {
        return "Service connection " + this.mComponentName.flattenToShortString();
    }
}
