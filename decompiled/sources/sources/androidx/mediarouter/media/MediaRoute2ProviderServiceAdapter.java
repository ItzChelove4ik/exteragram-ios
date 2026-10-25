package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRoute2ProviderService;
import android.media.RouteDiscoveryPreference;
import android.media.RoutingSessionInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderService;
import androidx.mediarouter.media.MediaRouter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes4.dex */
class MediaRoute2ProviderServiceAdapter extends MediaRoute2ProviderService {
    static final boolean DEBUG = false;

    @SuppressLint({"InlinedApi"})
    public static final String SERVICE_INTERFACE = "android.media.MediaRoute2ProviderService";
    private static final String TAG = "MR2ProviderService";
    private final Object mLock;
    private volatile MediaRouteProviderDescriptor mProviderDescriptor;
    final MediaRouteProviderService.MediaRouteProviderServiceImplApi30 mServiceImpl;
    final SparseArray<String> mSessionIdMap;
    final Map<String, SessionRecord> mSessionRecords;

    /* renamed from: androidx.mediarouter.media.MediaRoute2ProviderServiceAdapter$1, reason: invalid class name */
    public class AnonymousClass1 extends MediaRouter.ControlRequestCallback {
        final /* synthetic */ MediaRoute2ProviderServiceAdapter this$0;
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ Messenger val$messenger;
        final /* synthetic */ int val$requestId;
        final /* synthetic */ String val$sessionId;

        public AnonymousClass1(MediaRoute2ProviderServiceAdapter r1, String r2, Intent r3, Messenger r4, int r5) {
            this.this$0 = r1;
            this.val$sessionId = r2;
            this.val$intent = r3;
            this.val$messenger = r4;
            this.val$requestId = r5;
        }

        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
        public void onError(String r8, Bundle r9) {
            String r2 = MediaRoute2ProviderServiceAdapter.SERVICE_INTERFACE;
            if (r8 == null) goto L6;
            Bundle r6 = new Bundle();
            r6.putString("error", r8);
            sendReply(this.val$messenger, 4, this.val$requestId, 0, r9, r6);
            return;
        L6:
            sendReply(this.val$messenger, 4, this.val$requestId, 0, r9, null);
        }

        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
        public void onResult(Bundle r9) {
            String r0 = MediaRoute2ProviderServiceAdapter.SERVICE_INTERFACE;
            sendReply(this.val$messenger, 3, this.val$requestId, 0, r9, null);
        }

        public void sendReply(Messenger r1, int r2, int r3, int r4, Object r5, Bundle r6) {
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
            Log.e(MediaRoute2ProviderServiceAdapter.TAG, "Could not send message to the client.", e);
            return;
        }
    }

    public static class DynamicGroupRouteControllerProxy extends MediaRouteProvider.DynamicGroupRouteController {
        final MediaRouteProvider.RouteController mRouteController;
        private final String mRouteId;

        public DynamicGroupRouteControllerProxy(String r1, MediaRouteProvider.RouteController r2) {
            this.mRouteId = r1;
            this.mRouteController = r2;
        }

        public String getRouteId() {
            return this.mRouteId;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(String r1) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(Intent r1, MediaRouter.ControlRequestCallback r2) {
            return this.mRouteController.onControlRequest(r1, r2);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.mRouteController.onRelease();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(String r1) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mRouteController.onSelect();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int r1) {
            this.mRouteController.onSetVolume(r1);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int r1) {
            this.mRouteController.onUnselect(r1);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(List<String> r1) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int r1) {
            this.mRouteController.onUpdateVolume(r1);
        }
    }

    public static class IncomingHandler extends Handler {
        private final MediaRoute2ProviderServiceAdapter mServiceAdapter;
        private final String mSessionId;

        public IncomingHandler(MediaRoute2ProviderServiceAdapter r2, String r3) {
            super(Looper.myLooper());
            this.mServiceAdapter = r2;
            this.mSessionId = r3;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r8) {
            Messenger r0 = r8.replyTo;
            int r1 = r8.what;
            int r2 = r8.arg1;
            Object r3 = r8.obj;
            Bundle r82 = r8.getData();
            if (r1 != 7) goto L5;
            int r02 = r82.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, -1);
            String r83 = r82.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
            if (r02 < 0) goto L26;
            if (r83 == null) goto L27;
            this.mServiceAdapter.setRouteVolume(r83, r02);
            return;
        L27:
            return;
        L26:
            return;
        L5:
            if (r1 != 8) goto L7;
            int r03 = r82.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, 0);
            String r84 = r82.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
            if (r03 == 0) goto L24;
            if (r84 == null) goto L25;
            this.mServiceAdapter.updateRouteVolume(r84, r03);
            return;
        L25:
            return;
        L24:
            return;
        L7:
            if (r1 == 9) goto L10;
            return;
        L10:
            if ((r3 instanceof Intent) == false) goto L23;
            this.mServiceAdapter.onControlRequest(r0, r2, this.mSessionId, (Intent) r3);
            return;
        }
    }

    public final class SessionRecord {
        static final int SESSION_FLAG_DYNAMIC = 4;
        static final int SESSION_FLAG_GROUP = 2;
        static final int SESSION_FLAG_MR2 = 1;
        private final WeakReference<MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord> mClientRecord;
        private final MediaRouteProvider.DynamicGroupRouteController mController;
        private final int mFlags;
        private boolean mIsCreated;
        private boolean mIsReleased;
        private final long mRequestId;
        String mRouteId;
        private final Map<String, MediaRouteProvider.RouteController> mRouteIdToControllerMap;
        String mSessionId;
        private RoutingSessionInfo mSessionInfo;
        final /* synthetic */ MediaRoute2ProviderServiceAdapter this$0;

        public SessionRecord(MediaRoute2ProviderServiceAdapter r8, MediaRouteProvider.DynamicGroupRouteController r9, long r10, int r12) {
            this(r8, r9, r10, r12, null);
        }

        public SessionRecord(MediaRoute2ProviderServiceAdapter r1, MediaRouteProvider.DynamicGroupRouteController r2, long r3, int r5, MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord r6) {
            this.this$0 = r1;
            this.mRouteIdToControllerMap = new ArrayMap();
            this.mIsCreated = false;
            this.mController = r2;
            this.mRequestId = r3;
            this.mFlags = r5;
            this.mClientRecord = new WeakReference(r6);
        }

        private MediaRouteProvider.RouteController getOrCreateRouteController(String r2, String r3, MediaRouteProvider.RouteControllerOptions r4) {
            MediaRouteProvider.RouteController r0 = this.mRouteIdToControllerMap.get(r2);
            if (r0 == null) goto L5;
            return r0;
        L5:
            MediaRoute2ProviderServiceAdapter r02 = this.this$0;
            if (r3 != null) goto L8;
            MediaRouteProvider.RouteController r32 = r02.getMediaRouteProvider().onCreateRouteController(r2, r4);
        L9:
            if (r32 == null) goto L11;
            this.mRouteIdToControllerMap.put(r2, r32);
        L11:
            return r32;
        L8:
            r32 = r02.getMediaRouteProvider().onCreateRouteController(r2, r3);
            goto L9
        }

        private void notifySessionCreated() {
            if (this.mIsCreated == false) goto L6;
            Log.w(MediaRoute2ProviderServiceAdapter.TAG, "notifySessionCreated: Routing session is already created.");
            return;
        L6:
            this.mIsCreated = true;
            this.this$0.notifySessionCreated(this.mRequestId, this.mSessionInfo);
        }

        private boolean releaseRouteControllerByRouteId(String r1) {
            MediaRouteProvider.RouteController r0 = this.mRouteIdToControllerMap.remove(r1);
            if (r0 == null) goto L6;
            r0.onUnselect(0);
            r0.onRelease();
            return true;
        L6:
            return false;
        }

        public MediaRouteProvider.RouteController findControllerByRouteId(String r2) {
            MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord r0 = this.mClientRecord.get();
            if (r0 == null) goto L7;
            return r0.findControllerByRouteId(r2);
        L7:
            return this.mRouteIdToControllerMap.get(r2);
        }

        public int getFlags() {
            return this.mFlags;
        }

        public MediaRouteProvider.DynamicGroupRouteController getGroupController() {
            return this.mController;
        }

        public void release(boolean r4) {
            if (this.mIsReleased == false) goto L5;
            return;
        L5:
            if ((this.mFlags & 3) != 3) goto L8;
            updateMemberRouteControllers(null, this.mSessionInfo, null, MediaRouteProvider.RouteControllerOptions.EMPTY);
        L8:
            if (r4 == false) goto L17;
            this.mController.onUnselect(2);
            this.mController.onRelease();
            if ((this.mFlags & 1) != 0) goto L17;
            MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord r42 = this.mClientRecord.get();
            if (r42 == null) goto L17;
            MediaRouteProvider.RouteController r1 = this.mController;
            if ((r1 instanceof DynamicGroupRouteControllerProxy) == false) goto L16;
            r1 = ((DynamicGroupRouteControllerProxy) r1).mRouteController;
        L16:
            r42.releaseControllerByProvider(r1, this.mRouteId);
        L17:
            this.mIsReleased = true;
            this.this$0.notifySessionReleased(this.mSessionId);
        }

        public void setSessionInfo(RoutingSessionInfo r5) {
            if (this.mSessionInfo == null) goto L6;
            Log.w(MediaRoute2ProviderServiceAdapter.TAG, "setSessionInfo: This shouldn't be called after sessionInfo is set");
            return;
        L6:
            Messenger r0 = new Messenger(new IncomingHandler(this.this$0, this.mSessionId));
            RoutingSessionInfo.Builder r1 = MediaRoute2ProviderServiceAdapter$SessionRecord$$ExternalSyntheticApiModelOutline0.m(r5);
            Bundle r2 = new Bundle();
            r2.putParcelable("androidx.mediarouter.media.KEY_MESSENGER", r0);
            if (r5.getName() == null) goto L9;
            String r52 = r5.getName().toString();
        L10:
            r2.putString("androidx.mediarouter.media.KEY_SESSION_NAME", r52);
            this.mSessionInfo = r1.setControlHints(r2).build();
            return;
        L9:
            r52 = null;
            goto L10
        }

        public void setStaticMemberRouteId(String r2) {
            this.mRouteIdToControllerMap.put(r2, this.mController);
        }

        public void updateMemberRouteControllers(String r4, RoutingSessionInfo r5, RoutingSessionInfo r6, MediaRouteProvider.RouteControllerOptions r7) {
            if (r5 != null) goto L4;
            List<String> r52 = Collections.EMPTY_LIST;
        L5:
            if (r6 != null) goto L7;
            List<String> r62 = Collections.EMPTY_LIST;
        L8:
            Iterator<String> r0 = r62.iterator();
        L10:
            if (r0.hasNext() == false) goto L16;
            String r1 = r0.next();
            if (findControllerByRouteId(r1) != null) goto L10;
            MediaRouteProvider.RouteController r12 = getOrCreateRouteController(r1, r4, r7);
            if (r12 == null) goto L10;
            r12.onSelect();
            goto L10
        L16:
            Iterator<String> r42 = r52.iterator();
        L18:
            if (r42.hasNext() == false) goto L22;
            String r53 = r42.next();
            if (r62.contains(r53) == true) goto L18;
            releaseRouteControllerByRouteId(r53);
            goto L18
        L22:
            return;
        L7:
            r62 = r6.getSelectedRoutes();
            goto L8
        L4:
            r52 = r5.getSelectedRoutes();
            goto L5
        }

        public void updateSessionInfo(MediaRouteDescriptor r8, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r9) {
            RoutingSessionInfo r0 = this.mSessionInfo;
            if (r0 != null) goto L6;
            Log.w(MediaRoute2ProviderServiceAdapter.TAG, "updateSessionInfo: mSessionInfo is null. This shouldn't happen.");
            return;
        L6:
            if (r8 != null) goto L8;
        L11:
            RoutingSessionInfo.Builder r2 = MediaRoute2ProviderServiceAdapter$SessionRecord$$ExternalSyntheticApiModelOutline0.m(r0);
            if (r8 == null) goto L24;
            this.mRouteId = r8.getId();
            r2.setName(r8.getName()).setVolume(r8.getVolume()).setVolumeMax(r8.getVolumeMax()).setVolumeHandling(r8.getVolumeHandling());
            r2.clearSelectedRoutes();
            if (r8.getGroupMemberIds().isEmpty() == false) goto L16;
            r2.addSelectedRoute(this.mRouteId);
        L20:
            Bundle r3 = r0.getControlHints();
            if (r3 != null) goto L23;
            Log.w(MediaRoute2ProviderServiceAdapter.TAG, "updateSessionInfo: controlHints is null. This shouldn't happen.");
            r3 = new Bundle();
        L23:
            r3.putString("androidx.mediarouter.media.KEY_SESSION_NAME", r8.getName());
            r3.putBundle("androidx.mediarouter.media.KEY_GROUP_ROUTE", r8.asBundle());
            r2.setControlHints(r3);
            goto L24
        L16:
            Iterator<String> r32 = r8.getGroupMemberIds().iterator();
        L18:
            if (r32.hasNext() == false) goto L20;
            r2.addSelectedRoute(r32.next());
        L24:
            this.mSessionInfo = r2.build();
            if (r9 != null) goto L27;
        L47:
            String r92 = MediaRoute2ProviderServiceAdapter.SERVICE_INTERFACE;
            if ((this.mFlags & 5) != 5) goto L52;
            if (r8 == null) goto L52;
            updateMemberRouteControllers(r8.getId(), r0, this.mSessionInfo, MediaRouteProvider.RouteControllerOptions.EMPTY);
        L52:
            if (this.mIsCreated == true) goto L55;
            notifySessionCreated();
            return;
        L55:
            this.this$0.notifySessionUpdated(this.mSessionInfo);
            return;
        L27:
            if (r9.isEmpty() == true) goto L47;
            r2.clearSelectedRoutes();
            r2.clearSelectableRoutes();
            r2.clearDeselectableRoutes();
            r2.clearTransferableRoutes();
            Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r93 = r9.iterator();
            boolean r1 = false;
        L30:
            if (r93.hasNext() == false) goto L45;
            MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor r33 = r93.next();
            String r4 = r33.getRouteDescriptor().getId();
            int r5 = r33.mSelectionState;
            if (r5 != 2) goto L34;
        L35:
            r2.addSelectedRoute(r4);
            r1 = true;
        L37:
            if (r33.isGroupable() == false) goto L40;
            r2.addSelectableRoute(r4);
        L40:
            if (r33.isUnselectable() == false) goto L43;
            r2.addDeselectableRoute(r4);
        L43:
            if (r33.isTransferable() == false) goto L30;
            r2.addTransferableRoute(r4);
            goto L30
        L34:
            if (r5 != 3) goto L37;
        L45:
            if (r1 == false) goto L47;
            this.mSessionInfo = r2.build();
            goto L47
        L8:
            if (r8.isEnabled() == true) goto L11;
            this.this$0.onReleaseSession(0, this.mSessionId);
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRoute2ProviderServiceAdapter(MediaRouteProviderService.MediaRouteProviderServiceImplApi30 r2) {
        this.mLock = new Object();
        this.mSessionRecords = new ArrayMap();
        this.mSessionIdMap = new SparseArray();
        this.mServiceImpl = r2;
    }

    private String assignSessionId(SessionRecord r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L12:
        String r1 = UUID.randomUUID().toString();     // Catch: Throwable -> L9
        if (this.mSessionRecords.containsKey(r1) == true) goto L12;
        r4.mSessionId = r1;     // Catch: Throwable -> L9
        this.mSessionRecords.put(r1, r4);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r1;
    L9:
        th = move-exception;
        throw th;
    }

    private MediaRouteProvider.RouteController findControllerByRouteId(String r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList(this.mSessionRecords.values());     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        int r3 = r1.size();
        int r02 = 0;
    L7:
        if (r02 >= r3) goto L11;
        Object r2 = r1.get(r02);
        r02 = r02 + 1;
        MediaRouteProvider.RouteController r22 = ((SessionRecord) r2).findControllerByRouteId(r4);
        if (r22 == null) goto L7;
        return r22;
    L11:
        return null;
    L13:
        th = move-exception;
        throw th;
    }

    private MediaRouteProvider.DynamicGroupRouteController findControllerBySessionId(String r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        SessionRecord r1 = this.mSessionRecords.get(r2);     // Catch: Throwable -> L10
        if (r1 != null) goto L7;
        MediaRouteProvider.DynamicGroupRouteController r12 = null;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r12;
    L7:
        r12 = r1.getGroupController();     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    private SessionRecord findSessionRecordByController(MediaRouteProvider.DynamicGroupRouteController r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Iterator<Map.Entry<String, SessionRecord>> r3 = this.mSessionRecords.entrySet().iterator();     // Catch: Throwable -> L11
    L6:
        if (r3.hasNext() == false) goto L13;
        SessionRecord r1 = r3.next().getValue();     // Catch: Throwable -> L11
        if (r1.getGroupController() != r4) goto L6;
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r1;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return null;
    L11:
        th = move-exception;
        throw th;
    }

    private MediaRouteDescriptor getRouteDescriptor(String r5, String r6) {
        if (getMediaRouteProvider() != null) goto L5;
    L15:
        Log.w(TAG, r6 + ": no provider info");
        return null;
    L5:
        if (this.mProviderDescriptor == null) goto L15;
        Iterator<MediaRouteDescriptor> r4 = this.mProviderDescriptor.getRoutes().iterator();
    L9:
        if (r4.hasNext() == false) goto L13;
        MediaRouteDescriptor r0 = r4.next();
        if (TextUtils.equals(r0.getId(), r5) == false) goto L9;
        return r0;
    L13:
        Log.w(TAG, r6 + ": Couldn't find a route : " + r5);
        return null;
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public void attachBaseContext(Context r1) {
        super.attachBaseContext(r1);
    }

    public MediaRouteProvider getMediaRouteProvider() {
        MediaRouteProviderService r0 = this.mServiceImpl.getService();
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getMediaRouteProvider();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v4, types: [androidx.mediarouter.media.MediaRouteProvider$DynamicGroupRouteController] */
    public void notifyRouteControllerAdded(MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord r11, MediaRouteProvider.RouteController r12, int r13, String r14, String r15) {
        MediaRouteDescriptor r0 = getRouteDescriptor(r15, "notifyRouteControllerAdded");
        if (r0 != null) goto L6;
        return;
    L6:
        if ((r12 instanceof MediaRouteProvider.DynamicGroupRouteController) == false) goto L10;
        int r1 = 6;
        DynamicGroupRouteControllerProxy r5 = (MediaRouteProvider.DynamicGroupRouteController) r12;
    L14:
        SessionRecord r3 = new SessionRecord(this, r5, 0, r1, r11);
        r3.mRouteId = r15;
        String r10 = assignSessionId(r3);
        this.mSessionIdMap.put(r13, r10);
        RoutingSessionInfo.Builder r102 = MediaRoute2ProviderServiceAdapter$$ExternalSyntheticApiModelOutline0.m(r10, r14).setName(r0.getName()).setVolumeHandling(r0.getVolumeHandling()).setVolume(r0.getVolume()).setVolumeMax(r0.getVolumeMax());
        if (r0.getGroupMemberIds().isEmpty() == false) goto L17;
        r102.addSelectedRoute(r15);
    L21:
        r3.setSessionInfo(r102.build());
        return;
    L17:
        Iterator<String> r112 = r0.getGroupMemberIds().iterator();
    L19:
        if (r112.hasNext() == false) goto L21;
        r102.addSelectedRoute(r112.next());
        goto L19
    L10:
        if (r0.getGroupMemberIds().isEmpty() == true) goto L12;
        r1 = 2;
    L13:
        r5 = new DynamicGroupRouteControllerProxy(r15, r12);
        goto L14
    L12:
        r1 = 0;
        goto L13
    }

    public void notifyRouteControllerRemoved(int r3) {
        String r0 = this.mSessionIdMap.get(r3);
        if (r0 == null) goto L18;
        this.mSessionIdMap.remove(r3);
        Object r32 = this.mLock;
        monitor-enter(r32);
        SessionRecord r2 = this.mSessionRecords.remove(r0);     // Catch: Throwable -> L12
        monitor-exit(r32);     // Catch: Throwable -> L12
        if (r2 == null) goto L17;
        r2.release(false);
        return;
    L17:
        return;
    L12:
        th = move-exception;
        throw th;
    }

    public void onControlRequest(Messenger r8, int r9, String r10, Intent r11) {
        if (getSessionInfo(r10) != null) goto L6;
        Log.w(TAG, "onCustomCommand: Couldn't find a session");
        return;
    L6:
        MediaRouteProvider.DynamicGroupRouteController r0 = findControllerBySessionId(r10);
        if (r0 != null) goto L10;
        Log.w(TAG, "onControlRequest: Couldn't find a controller");
        notifyRequestFailed(r9, 3);
        return;
    L10:
        r0.onControlRequest(r11, new AnonymousClass1(this, r10, r11, r8, r9));
    }

    public void onCreateSession(long r11, String r13, String r14, Bundle r15) {
        MediaRouteProvider r0 = getMediaRouteProvider();
        MediaRouteDescriptor r1 = getRouteDescriptor(r14, "onCreateSession");
        int r2 = 3;
        if (r1 != null) goto L6;
        notifyRequestFailed(r11, 3);
        return;
    L6:
        MediaRouteProvider.RouteControllerOptions r152 = new MediaRouteProvider.RouteControllerOptions.Builder().setControlHints(r15).setClientPackageName(r13).build();
        if (this.mProviderDescriptor.supportsDynamicGroupRoute() == false) goto L14;
        MediaRouteProvider.DynamicGroupRouteController r02 = r0.onCreateDynamicGroupRouteController(r14, r152);
        if (r02 != null) goto L12;
        Log.w(TAG, "onCreateSession: Couldn't create a dynamic controller");
        notifyRequestFailed(r11, 1);
        return;
    L12:
        r2 = 7;
        MediaRouteProvider.DynamicGroupRouteController r6 = r02;
    L13:
        int r9 = r2;
        r6.onSelect();
        SessionRecord r4 = new SessionRecord(this, r6, r11, r9);
        RoutingSessionInfo.Builder r10 = MediaRoute2ProviderServiceAdapter$$ExternalSyntheticApiModelOutline0.m(assignSessionId(r4), r13).setName(r1.getName()).setVolumeHandling(r1.getVolumeHandling()).setVolume(r1.getVolume()).setVolumeMax(r1.getVolumeMax());
        if (r1.getGroupMemberIds().isEmpty() == false) goto L26;
        r10.addSelectedRoute(r14);
    L30:
        RoutingSessionInfo r102 = r10.build();
        r4.setSessionInfo(r102);
        if ((r9 & 4) == 0) goto L33;
    L36:
        this.mServiceImpl.setDynamicRoutesChangedListener(r6);
        return;
    L33:
        if ((r9 & 2) == 0) goto L35;
        r4.updateMemberRouteControllers(r14, null, r102, r152);
        goto L36
    L35:
        r4.setStaticMemberRouteId(r14);
        goto L36
    L26:
        Iterator<String> r112 = r1.getGroupMemberIds().iterator();
    L28:
        if (r112.hasNext() == false) goto L30;
        r10.addSelectedRoute(r112.next());
        goto L28
    L14:
        MediaRouteProvider.RouteController r03 = r0.onCreateRouteController(r14, r152);
        if (r03 != null) goto L19;
        Log.w(TAG, "onCreateSession: Couldn't create a controller");
        notifyRequestFailed(r11, 1);
        return;
    L19:
        if (r1.getGroupMemberIds().isEmpty() == false) goto L22;
        r2 = 1;
    L22:
        r6 = new DynamicGroupRouteControllerProxy(r14, r03);
        goto L13
    }

    public void onDeselectRoute(long r4, String r6, String r7) {
        if (getSessionInfo(r6) != null) goto L7;
        Log.w(TAG, "onDeselectRoute: Couldn't find a session");
        notifyRequestFailed(r4, 4);
        return;
    L7:
        if (getRouteDescriptor(r7, "onDeselectRoute") != null) goto L10;
        notifyRequestFailed(r4, 3);
        return;
    L10:
        MediaRouteProvider.DynamicGroupRouteController r62 = findControllerBySessionId(r6);
        if (r62 != null) goto L14;
        Log.w(TAG, "onDeselectRoute: Couldn't find a controller");
        notifyRequestFailed(r4, 3);
        return;
    L14:
        r62.onRemoveMemberRoute(r7);
    }

    public void onDiscoveryPreferenceChanged(RouteDiscoveryPreference r1) {
        this.mServiceImpl.setBaseDiscoveryRequest(MediaRouter2Utils.toMediaRouteDiscoveryRequest(r1));
    }

    public void onReleaseSession(long r3, String r5) {
        if (getSessionInfo(r5) != null) goto L5;
        return;
    L5:
        Object r0 = this.mLock;
        monitor-enter(r0);
        SessionRecord r52 = this.mSessionRecords.remove(r5);     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        if (r52 != null) goto L12;
        Log.w(TAG, "onReleaseSession: Couldn't find a session");
        notifyRequestFailed(r3, 4);
        return;
    L12:
        r52.release(true);
        return;
    L14:
        th = move-exception;
        throw th;
    }

    public void onSelectRoute(long r4, String r6, String r7) {
        if (getSessionInfo(r6) != null) goto L7;
        Log.w(TAG, "onSelectRoute: Couldn't find a session");
        notifyRequestFailed(r4, 4);
        return;
    L7:
        if (getRouteDescriptor(r7, "onSelectRoute") != null) goto L10;
        notifyRequestFailed(r4, 3);
        return;
    L10:
        MediaRouteProvider.DynamicGroupRouteController r62 = findControllerBySessionId(r6);
        if (r62 != null) goto L14;
        Log.w(TAG, "onSelectRoute: Couldn't find a controller");
        notifyRequestFailed(r4, 3);
        return;
    L14:
        r62.onAddMemberRoute(r7);
    }

    public void onSetRouteVolume(long r2, String r4, int r5) {
        MediaRouteProvider.RouteController r0 = findControllerByRouteId(r4);
        if (r0 != null) goto L6;
        Log.w(TAG, "onSetRouteVolume: Couldn't find a controller for routeId=" + r4);
        notifyRequestFailed(r2, 3);
        return;
    L6:
        r0.onSetVolume(r5);
    }

    public void onSetSessionVolume(long r3, String r5, int r6) {
        if (getSessionInfo(r5) != null) goto L6;
        Log.w(TAG, "onSetSessionVolume: Couldn't find a session");
        notifyRequestFailed(r3, 4);
        return;
    L6:
        MediaRouteProvider.DynamicGroupRouteController r52 = findControllerBySessionId(r5);
        if (r52 != null) goto L10;
        Log.w(TAG, "onSetSessionVolume: Couldn't find a controller");
        notifyRequestFailed(r3, 3);
        return;
    L10:
        r52.onSetVolume(r6);
    }

    public void onTransferToRoute(long r4, String r6, String r7) {
        if (getSessionInfo(r6) != null) goto L7;
        Log.w(TAG, "onTransferToRoute: Couldn't find a session");
        notifyRequestFailed(r4, 4);
        return;
    L7:
        if (getRouteDescriptor(r7, "onTransferToRoute") != null) goto L10;
        notifyRequestFailed(r4, 3);
        return;
    L10:
        MediaRouteProvider.DynamicGroupRouteController r62 = findControllerBySessionId(r6);
        if (r62 != null) goto L14;
        Log.w(TAG, "onTransferToRoute: Couldn't find a controller");
        notifyRequestFailed(r4, 3);
        return;
    L14:
        r62.onUpdateMemberRoutes(Collections.singletonList(r7));
    }

    public void setDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController r1, MediaRouteDescriptor r2, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> r3) {
        SessionRecord r0 = findSessionRecordByController(r1);
        if (r0 != null) goto L6;
        Log.w(TAG, "setDynamicRouteDescriptor: Ignoring unknown controller");
        return;
    L6:
        r0.updateSessionInfo(r2, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setProviderDescriptor(MediaRouteProviderDescriptor r4) {
        this.mProviderDescriptor = r4;
        if (r4 != null) goto L5;
        List<MediaRouteDescriptor> r42 = Collections.EMPTY_LIST;
    L6:
        Map<String, MediaRouteDescriptor> r0 = new ArrayMap();
        Iterator<MediaRouteDescriptor> r43 = r42.iterator();
    L8:
        if (r43.hasNext() == false) goto L13;
        MediaRouteDescriptor r1 = r43.next();
        if (r1 == null) goto L8;
        r0.put(r1.getId(), r1);
        goto L8
    L13:
        updateStaticSessions(r0);
        ArrayList r44 = new ArrayList();
        Iterator r02 = r0.values().iterator();
    L15:
        if (r02.hasNext() == false) goto L19;
        MediaRoute2Info r12 = MediaRouter2Utils.toFwkMediaRoute2Info((MediaRouteDescriptor) r02.next());
        if (r12 == null) goto L15;
        r44.add(r12);
        goto L15
    L19:
        notifyRoutes(r44);
        return;
    L5:
        r42 = r4.getRoutes();
        goto L6
    }

    public void setRouteVolume(String r1, int r2) {
        MediaRouteProvider.RouteController r0 = findControllerByRouteId(r1);
        if (r0 != null) goto L6;
        Log.w(TAG, "setRouteVolume: Couldn't find a controller for routeId=" + r1);
        return;
    L6:
        r0.onSetVolume(r2);
    }

    public void updateRouteVolume(String r1, int r2) {
        MediaRouteProvider.RouteController r0 = findControllerByRouteId(r1);
        if (r0 != null) goto L6;
        Log.w(TAG, "updateRouteVolume: Couldn't find a controller for routeId=" + r1);
        return;
    L6:
        r0.onUpdateVolume(r2);
    }

    public void updateStaticSessions(Map<String, MediaRouteDescriptor> r6) {
        ArrayList r0 = new ArrayList();
        Object r1 = this.mLock;
        monitor-enter(r1);
        Iterator<SessionRecord> r5 = this.mSessionRecords.values().iterator();     // Catch: Throwable -> L10
    L6:
        if (r5.hasNext() == false) goto L12;
        SessionRecord r2 = r5.next();     // Catch: Throwable -> L10
        if ((r2.getFlags() & 4) != 0) goto L6;
        r0.add(r2);     // Catch: Throwable -> L10
        goto L6
    L12:
        monitor-exit(r1);     // Catch: Throwable -> L10
        int r52 = r0.size();
        int r12 = 0;
    L14:
        if (r12 >= r52) goto L18;
        Object r22 = r0.get(r12);
        r12 = r12 + 1;
        SessionRecord r23 = (SessionRecord) r22;
        DynamicGroupRouteControllerProxy r3 = (DynamicGroupRouteControllerProxy) r23.getGroupController();
        if (r6.containsKey(r3.getRouteId()) == false) goto L14;
        r23.updateSessionInfo(r6.get(r3.getRouteId()), null);
        goto L14
    L18:
        return;
    L10:
        th = move-exception;
        throw th;
    }
}
