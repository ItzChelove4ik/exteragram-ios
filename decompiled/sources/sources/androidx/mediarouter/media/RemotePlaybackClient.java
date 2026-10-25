package androidx.mediarouter.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.MediaRouter;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class RemotePlaybackClient {
    static final boolean DEBUG = false;
    static final String TAG = "RemotePlaybackClient";
    private final ActionReceiver mActionReceiver;
    private final Context mContext;
    private final PendingIntent mItemStatusPendingIntent;
    private final PendingIntent mMessagePendingIntent;
    OnMessageReceivedListener mOnMessageReceivedListener;
    private final MediaRouter.RouteInfo mRoute;
    private boolean mRouteSupportsMessaging;
    private boolean mRouteSupportsQueuing;
    private boolean mRouteSupportsRemotePlayback;
    private boolean mRouteSupportsSessionManagement;
    String mSessionId;
    private final PendingIntent mSessionStatusPendingIntent;
    StatusCallback mStatusCallback;

    /* renamed from: androidx.mediarouter.media.RemotePlaybackClient$1, reason: invalid class name */
    public class AnonymousClass1 extends MediaRouter.ControlRequestCallback {
        final /* synthetic */ RemotePlaybackClient this$0;
        final /* synthetic */ ItemActionCallback val$callback;
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ String val$itemId;
        final /* synthetic */ String val$sessionId;

        public AnonymousClass1(RemotePlaybackClient r1, String r2, String r3, Intent r4, ItemActionCallback r5) {
            this.this$0 = r1;
            this.val$sessionId = r2;
            this.val$itemId = r3;
            this.val$intent = r4;
            this.val$callback = r5;
        }

        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
        public void onError(String r3, Bundle r4) {
            this.this$0.handleError(this.val$intent, this.val$callback, r3, r4);
        }

        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
        public void onResult(Bundle r9) {
            if (r9 == null) goto L11;
            String r4 = RemotePlaybackClient.inferMissingResult(this.val$sessionId, r9.getString(MediaControlIntent.EXTRA_SESSION_ID));
            MediaSessionStatus r5 = MediaSessionStatus.fromBundle(r9.getBundle(MediaControlIntent.EXTRA_SESSION_STATUS));
            String r6 = RemotePlaybackClient.inferMissingResult(this.val$itemId, r9.getString(MediaControlIntent.EXTRA_ITEM_ID));
            MediaItemStatus r7 = MediaItemStatus.fromBundle(r9.getBundle(MediaControlIntent.EXTRA_ITEM_STATUS));
            this.this$0.adoptSession(r4);
            if (r4 == null) goto L11;
            if (r6 == null) goto L11;
            if (r7 == null) goto L11;
            ItemActionCallback r2 = this.val$callback;
            if (r2 == null) goto L13;
            r2.onResult(r9, r4, r5, r6, r7);
            return;
        L13:
            return;
        L11:
            this.this$0.handleInvalidResult(this.val$intent, this.val$callback, r9);
        }
    }

    /* renamed from: androidx.mediarouter.media.RemotePlaybackClient$2, reason: invalid class name */
    public class AnonymousClass2 extends MediaRouter.ControlRequestCallback {
        final /* synthetic */ RemotePlaybackClient this$0;
        final /* synthetic */ SessionActionCallback val$callback;
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ String val$sessionId;

        public AnonymousClass2(RemotePlaybackClient r1, String r2, Intent r3, SessionActionCallback r4) {
            this.this$0 = r1;
            this.val$sessionId = r2;
            this.val$intent = r3;
            this.val$callback = r4;
        }

        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
        public void onError(String r3, Bundle r4) {
            this.this$0.handleError(this.val$intent, this.val$callback, r3, r4);
        }

        @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
        public void onResult(Bundle r6) {
            if (r6 == null) goto L21;
            String r1 = RemotePlaybackClient.inferMissingResult(this.val$sessionId, r6.getString(MediaControlIntent.EXTRA_SESSION_ID));
            MediaSessionStatus r2 = MediaSessionStatus.fromBundle(r6.getBundle(MediaControlIntent.EXTRA_SESSION_STATUS));
            this.this$0.adoptSession(r1);
            if (r1 == null) goto L21;
            this.val$callback.onResult(r6, r1, r2);     // Catch: Throwable -> L14
            if (this.val$intent.getAction().equals(MediaControlIntent.ACTION_END_SESSION) == true) goto L11;
            return;
        L11:
            if (r1.equals(this.this$0.mSessionId) == false) goto L26;
            this.this$0.setSessionId(null);
            return;
        L26:
            return;
        L14:
            th = move-exception;
            if (this.val$intent.getAction().equals(MediaControlIntent.ACTION_END_SESSION) == true) goto L18;
        L20:
            throw th;
        L18:
            if (r1.equals(this.this$0.mSessionId) == false) goto L20;
            this.this$0.setSessionId(null);
        L21:
            this.this$0.handleInvalidResult(this.val$intent, this.val$callback, r6);
        }
    }

    public static abstract class ActionCallback {
        public ActionCallback() {
        }

        public void onError(String r1, int r2, Bundle r3) {
        }
    }

    public final class ActionReceiver extends BroadcastReceiver {
        public static final String ACTION_ITEM_STATUS_CHANGED = "androidx.mediarouter.media.actions.ACTION_ITEM_STATUS_CHANGED";
        public static final String ACTION_MESSAGE_RECEIVED = "androidx.mediarouter.media.actions.ACTION_MESSAGE_RECEIVED";
        public static final String ACTION_SESSION_STATUS_CHANGED = "androidx.mediarouter.media.actions.ACTION_SESSION_STATUS_CHANGED";
        final /* synthetic */ RemotePlaybackClient this$0;

        public ActionReceiver(RemotePlaybackClient r1) {
            this.this$0 = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r7, Intent r8) {
            String r2 = r8.getStringExtra(MediaControlIntent.EXTRA_SESSION_ID);
            if (r2 != null) goto L5;
        L36:
            Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing or invalid session id: sessionId=" + r2);
            return;
        L5:
            if (r2.equals(this.this$0.mSessionId) == false) goto L36;
            MediaSessionStatus r3 = MediaSessionStatus.fromBundle(r8.getBundleExtra(MediaControlIntent.EXTRA_SESSION_STATUS));
            String r0 = r8.getAction();
            if (r0.equals(ACTION_ITEM_STATUS_CHANGED) == false) goto L22;
            String r4 = r8.getStringExtra(MediaControlIntent.EXTRA_ITEM_ID);
            if (r4 != null) goto L13;
            Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item id.");
            return;
        L13:
            MediaItemStatus r5 = MediaItemStatus.fromBundle(r8.getBundleExtra(MediaControlIntent.EXTRA_ITEM_STATUS));
            if (r5 != null) goto L17;
            Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item status.");
            return;
        L17:
            String r72 = RemotePlaybackClient.TAG;
            StatusCallback r02 = this.this$0.mStatusCallback;
            if (r02 == null) goto L38;
            r02.onItemStatusChanged(r8.getExtras(), r2, r3, r4, r5);
            return;
        L38:
            return;
        L22:
            if (r0.equals(ACTION_SESSION_STATUS_CHANGED) == false) goto L31;
            if (r3 != null) goto L26;
            Log.w("RemotePlaybackClient", "Discarding spurious media status callback with missing session status.");
            return;
        L26:
            String r73 = RemotePlaybackClient.TAG;
            StatusCallback r6 = this.this$0.mStatusCallback;
            if (r6 == null) goto L39;
            r6.onSessionStatusChanged(r8.getExtras(), r2, r3);
            return;
        L39:
            return;
        L31:
            if (r0.equals(ACTION_MESSAGE_RECEIVED) == false) goto L40;
            String r74 = RemotePlaybackClient.TAG;
            OnMessageReceivedListener r62 = this.this$0.mOnMessageReceivedListener;
            if (r62 == null) goto L41;
            r62.onMessageReceived(r2, r8.getBundleExtra(MediaControlIntent.EXTRA_MESSAGE));
            return;
        L41:
            return;
        }
    }

    public static class Api33 {
        private Api33() {
        }

        public static void registerReceiver(Context r0, BroadcastReceiver r1, IntentFilter r2, int r3) {
            r0.registerReceiver(r1, r2, r3);
        }
    }

    public static abstract class ItemActionCallback extends ActionCallback {
        public ItemActionCallback() {
        }

        public void onResult(Bundle r1, String r2, MediaSessionStatus r3, String r4, MediaItemStatus r5) {
        }
    }

    public interface OnMessageReceivedListener {
        void onMessageReceived(String r1, Bundle r2);
    }

    public static abstract class SessionActionCallback extends ActionCallback {
        public SessionActionCallback() {
        }

        public void onResult(Bundle r1, String r2, MediaSessionStatus r3) {
        }
    }

    public static abstract class StatusCallback {
        public StatusCallback() {
        }

        public void onItemStatusChanged(Bundle r1, String r2, MediaSessionStatus r3, String r4, MediaItemStatus r5) {
        }

        public void onSessionChanged(String r1) {
        }

        public void onSessionStatusChanged(Bundle r1, String r2, MediaSessionStatus r3) {
        }
    }

    static {
        Log.isLoggable("RemotePlaybackClient", 3);
    }

    public RemotePlaybackClient(Context r7, MediaRouter.RouteInfo r8) {
        if (r7 == null) goto L13;
        if (r8 == null) goto L11;
        this.mContext = r7;
        this.mRoute = r8;
        IntentFilter r82 = new IntentFilter();
        r82.addAction(ActionReceiver.ACTION_ITEM_STATUS_CHANGED);
        r82.addAction(ActionReceiver.ACTION_SESSION_STATUS_CHANGED);
        r82.addAction(ActionReceiver.ACTION_MESSAGE_RECEIVED);
        ActionReceiver r3 = new ActionReceiver(this);
        this.mActionReceiver = r3;
        if (Build.VERSION.SDK_INT >= 33) goto L8;
        r7.registerReceiver(r3, r82);
    L9:
        Intent r83 = new Intent(ActionReceiver.ACTION_ITEM_STATUS_CHANGED);
        r83.setPackage(r7.getPackageName());
        this.mItemStatusPendingIntent = PendingIntent.getBroadcast(r7, 0, r83, 67108864);
        Intent r84 = new Intent(ActionReceiver.ACTION_SESSION_STATUS_CHANGED);
        r84.setPackage(r7.getPackageName());
        this.mSessionStatusPendingIntent = PendingIntent.getBroadcast(r7, 0, r84, 67108864);
        Intent r85 = new Intent(ActionReceiver.ACTION_MESSAGE_RECEIVED);
        r85.setPackage(r7.getPackageName());
        this.mMessagePendingIntent = PendingIntent.getBroadcast(r7, 0, r85, 67108864);
        detectFeatures();
        return;
    L8:
        Api33.registerReceiver(r7, r3, r82, 4);
        goto L9
    L11:
        f$$ExternalSyntheticBUOutline1.m("route must not be null");
        throw null;
    L13:
        f$$ExternalSyntheticBUOutline1.m("context must not be null");
        throw null;
    }

    public static String bundleToString(Bundle r0) {
        if (r0 == null) goto L5;
        r0.size();
        return r0.toString();
    L5:
        return "null";
    }

    private void detectFeatures() {
        boolean r1 = false;
        if (routeSupportsAction(MediaControlIntent.ACTION_PLAY) == true) goto L5;
    L15:
        boolean r0 = false;
    L16:
        this.mRouteSupportsRemotePlayback = r0;
        if (r0 == true) goto L19;
    L23:
        boolean r02 = false;
    L24:
        this.mRouteSupportsQueuing = r02;
        if (this.mRouteSupportsRemotePlayback == true) goto L27;
    L33:
        this.mRouteSupportsSessionManagement = r1;
        this.mRouteSupportsMessaging = doesRouteSupportMessaging();
        return;
    L27:
        if (routeSupportsAction(MediaControlIntent.ACTION_START_SESSION) == false) goto L33;
        if (routeSupportsAction(MediaControlIntent.ACTION_GET_SESSION_STATUS) == false) goto L33;
        if (routeSupportsAction(MediaControlIntent.ACTION_END_SESSION) == false) goto L33;
        r1 = true;
        goto L33
    L19:
        if (routeSupportsAction(MediaControlIntent.ACTION_ENQUEUE) == false) goto L23;
        if (routeSupportsAction(MediaControlIntent.ACTION_REMOVE) == false) goto L23;
        r02 = true;
        goto L24
    L5:
        if (routeSupportsAction(MediaControlIntent.ACTION_SEEK) == false) goto L15;
        if (routeSupportsAction(MediaControlIntent.ACTION_GET_STATUS) == false) goto L15;
        if (routeSupportsAction(MediaControlIntent.ACTION_PAUSE) == false) goto L15;
        if (routeSupportsAction(MediaControlIntent.ACTION_RESUME) == false) goto L15;
        if (routeSupportsAction(MediaControlIntent.ACTION_STOP) == false) goto L15;
        r0 = true;
        goto L16
    }

    private boolean doesRouteSupportMessaging() {
        Iterator<IntentFilter> r2 = this.mRoute.getControlFilters().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        if (r2.next().hasAction(MediaControlIntent.ACTION_SEND_MESSAGE) == false) goto L4;
        return true;
    L9:
        return false;
    }

    public static String inferMissingResult(String r0, String r1) {
        if (r1 != null) goto L4;
        return r0;
    L4:
        if (r0 != null) goto L6;
    L10:
        return r1;
    L6:
        if (r0.equals(r1) == true) goto L10;
        return null;
    }

    private static void logRequest(Intent r0) {
    }

    private void performItemAction(Intent r7, String r8, String r9, Bundle r10, ItemActionCallback r11) {
        r7.addCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK);
        if (r8 == null) goto L5;
        r7.putExtra(MediaControlIntent.EXTRA_SESSION_ID, r8);
    L5:
        if (r9 == null) goto L7;
        r7.putExtra(MediaControlIntent.EXTRA_ITEM_ID, r9);
    L7:
        if (r10 == null) goto L9;
        r7.putExtras(r10);
    L9:
        logRequest(r7);
        this.mRoute.sendControlRequest(r7, new AnonymousClass1(this, r8, r9, r7, r11));
    }

    private void performSessionAction(Intent r2, String r3, Bundle r4, SessionActionCallback r5) {
        r2.addCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK);
        if (r3 == null) goto L5;
        r2.putExtra(MediaControlIntent.EXTRA_SESSION_ID, r3);
    L5:
        if (r4 == null) goto L7;
        r2.putExtras(r4);
    L7:
        logRequest(r2);
        this.mRoute.sendControlRequest(r2, new AnonymousClass2(this, r3, r2, r5));
    }

    private void playOrEnqueue(Uri r2, String r3, Bundle r4, long r5, Bundle r7, ItemActionCallback r8, String r9) {
        if (r2 == null) goto L14;
        throwIfRemotePlaybackNotSupported();
        if (r9.equals(MediaControlIntent.ACTION_ENQUEUE) == false) goto L6;
        throwIfQueuingNotSupported();
    L6:
        Intent r22 = new Intent(r9);
        r22.setDataAndType(r2, r3);
        r22.putExtra(MediaControlIntent.EXTRA_ITEM_STATUS_UPDATE_RECEIVER, this.mItemStatusPendingIntent);
        if (r4 == null) goto L10;
        r22.putExtra(MediaControlIntent.EXTRA_ITEM_METADATA, r4);
    L10:
        if (r5 == 0) goto L12;
        r22.putExtra(MediaControlIntent.EXTRA_ITEM_CONTENT_POSITION, r5);
    L12:
        performItemAction(r22, this.mSessionId, null, r7, r8);
        return;
    L14:
        f$$ExternalSyntheticBUOutline1.m("contentUri must not be null");
    }

    private boolean routeSupportsAction(String r2) {
        return this.mRoute.supportsControlAction(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK, r2);
    }

    private void throwIfMessageNotSupported() {
        if (this.mRouteSupportsMessaging == false) goto L5;
        return;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("The route does not support message.");
    }

    private void throwIfNoCurrentSession() {
        if (this.mSessionId == null) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("There is no current session.");
    }

    private void throwIfQueuingNotSupported() {
        if (this.mRouteSupportsQueuing == false) goto L5;
        return;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("The route does not support queuing.");
    }

    private void throwIfRemotePlaybackNotSupported() {
        if (this.mRouteSupportsRemotePlayback == false) goto L5;
        return;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("The route does not support remote playback.");
    }

    private void throwIfSessionManagementNotSupported() {
        if (this.mRouteSupportsSessionManagement == false) goto L5;
        return;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("The route does not support session management.");
    }

    public void adoptSession(String r1) {
        if (r1 == null) goto L5;
        setSessionId(r1);
        return;
    }

    public void endSession(Bundle r3, SessionActionCallback r4) {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_END_SESSION), this.mSessionId, r3, r4);
    }

    public void enqueue(Uri r10, String r11, Bundle r12, long r13, Bundle r15, ItemActionCallback r16) {
        playOrEnqueue(r10, r11, r12, r13, r15, r16, MediaControlIntent.ACTION_ENQUEUE);
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public void getSessionStatus(Bundle r3, SessionActionCallback r4) {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_GET_SESSION_STATUS), this.mSessionId, r3, r4);
    }

    public void getStatus(String r7, Bundle r8, ItemActionCallback r9) {
        if (r7 == null) goto L5;
        throwIfNoCurrentSession();
        performItemAction(new Intent(MediaControlIntent.ACTION_GET_STATUS), this.mSessionId, r7, r8, r9);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("itemId must not be null");
    }

    public void handleError(Intent r1, ActionCallback r2, String r3, Bundle r4) {
        int r0 = 0;
        if (r4 == null) goto L5;
        r0 = r4.getInt(MediaControlIntent.EXTRA_ERROR_CODE, 0);
    L5:
        if (r2 == null) goto L8;
        r2.onError(r3, r0, r4);
        return;
    }

    public void handleInvalidResult(Intent r2, ActionCallback r3, Bundle r4) {
        Log.w("RemotePlaybackClient", "Received invalid result data from " + r2.getAction() + ": data=" + bundleToString(r4));
        if (r3 == null) goto L6;
        r3.onError(null, 0, r4);
        return;
    }

    public boolean hasSession() {
        if (this.mSessionId == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isMessagingSupported() {
        return this.mRouteSupportsMessaging;
    }

    public boolean isQueuingSupported() {
        return this.mRouteSupportsQueuing;
    }

    public boolean isRemotePlaybackSupported() {
        return this.mRouteSupportsRemotePlayback;
    }

    public boolean isSessionManagementSupported() {
        return this.mRouteSupportsSessionManagement;
    }

    public void pause(Bundle r3, SessionActionCallback r4) {
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_PAUSE), this.mSessionId, r3, r4);
    }

    public void play(Uri r10, String r11, Bundle r12, long r13, Bundle r15, ItemActionCallback r16) {
        playOrEnqueue(r10, r11, r12, r13, r15, r16, MediaControlIntent.ACTION_PLAY);
    }

    public void release() {
        this.mContext.unregisterReceiver(this.mActionReceiver);
    }

    public void remove(String r7, Bundle r8, ItemActionCallback r9) {
        if (r7 == null) goto L5;
        throwIfQueuingNotSupported();
        throwIfNoCurrentSession();
        performItemAction(new Intent(MediaControlIntent.ACTION_REMOVE), this.mSessionId, r7, r8, r9);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("itemId must not be null");
    }

    public void resume(Bundle r3, SessionActionCallback r4) {
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_RESUME), this.mSessionId, r3, r4);
    }

    public void seek(String r3, long r4, Bundle r6, ItemActionCallback r7) {
        if (r3 == null) goto L5;
        throwIfNoCurrentSession();
        Intent r32 = new Intent(MediaControlIntent.ACTION_SEEK);
        r32.putExtra(MediaControlIntent.EXTRA_ITEM_CONTENT_POSITION, r4);
        performItemAction(r32, this.mSessionId, r3, r6, r7);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("itemId must not be null");
    }

    public void sendMessage(Bundle r3, SessionActionCallback r4) {
        throwIfNoCurrentSession();
        throwIfMessageNotSupported();
        performSessionAction(new Intent(MediaControlIntent.ACTION_SEND_MESSAGE), this.mSessionId, r3, r4);
    }

    public void setOnMessageReceivedListener(OnMessageReceivedListener r1) {
        this.mOnMessageReceivedListener = r1;
    }

    public void setSessionId(String r2) {
        if (ObjectsCompat.equals(this.mSessionId, r2) == true) goto L8;
        this.mSessionId = r2;
        StatusCallback r1 = this.mStatusCallback;
        if (r1 == null) goto L9;
        r1.onSessionChanged(r2);
        return;
    L9:
        return;
    }

    public void setStatusCallback(StatusCallback r1) {
        this.mStatusCallback = r1;
    }

    public void startSession(Bundle r4, SessionActionCallback r5) {
        throwIfSessionManagementNotSupported();
        Intent r0 = new Intent(MediaControlIntent.ACTION_START_SESSION);
        r0.putExtra(MediaControlIntent.EXTRA_SESSION_STATUS_UPDATE_RECEIVER, this.mSessionStatusPendingIntent);
        if (this.mRouteSupportsMessaging == false) goto L5;
        r0.putExtra(MediaControlIntent.EXTRA_MESSAGE_RECEIVER, this.mMessagePendingIntent);
    L5:
        performSessionAction(r0, null, r4, r5);
    }

    public void stop(Bundle r3, SessionActionCallback r4) {
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_STOP), this.mSessionId, r3, r4);
    }
}
