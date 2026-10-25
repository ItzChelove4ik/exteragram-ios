package androidx.room;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationService;
import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0003H\u0016J%\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\rH\u0016¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"androidx/room/MultiInstanceInvalidationService$binder$1", "Landroidx/room/IMultiInstanceInvalidationService$Stub;", "registerCallback", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "Landroidx/room/IMultiInstanceInvalidationCallback;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "unregisterCallback", _UrlKt.FRAGMENT_ENCODE_SET, "clientId", "broadcastInvalidation", "tables", _UrlKt.FRAGMENT_ENCODE_SET, "(I[Ljava/lang/String;)V", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MultiInstanceInvalidationService$binder$1 extends IMultiInstanceInvalidationService.Stub {
    final /* synthetic */ MultiInstanceInvalidationService this$0;

    public MultiInstanceInvalidationService$binder$1(MultiInstanceInvalidationService r1) {
        this.this$0 = r1;
    }

    @Override // androidx.room.IMultiInstanceInvalidationService
    public void broadcastInvalidation(int r8, String[] r9) {
        RemoteCallbackList<IMultiInstanceInvalidationCallback> r0 = this.this$0.getCallbackList$room_runtime();
        MultiInstanceInvalidationService r7 = this.this$0;
        monitor-enter(r0);
        String r1 = r7.getClientNames$room_runtime().get(Integer.valueOf(r8));     // Catch: Throwable -> L9
        if (r1 != null) goto L11;
        Log.w("ROOM", "Remote invalidation client ID not registered");     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L11:
        int r2 = r7.getCallbackList$room_runtime().beginBroadcast();     // Catch: Throwable -> L9
        int r3 = 0;
    L13:
        if (r3 >= r2) goto L28;
        Integer r4 = (Integer) r7.getCallbackList$room_runtime().getBroadcastCookie(r3);     // Catch: Throwable -> L21
        int r5 = r4.intValue();     // Catch: Throwable -> L21
        String r42 = r7.getClientNames$room_runtime().get(r4);     // Catch: Throwable -> L21
        if (r8 == r5) goto L25;
        if (Intrinsics.areEqual(r1, r42) == false) goto L25;
        ((IMultiInstanceInvalidationCallback) r7.getCallbackList$room_runtime().getBroadcastItem(r3)).onInvalidation(r9);     // Catch: Throwable -> L21 RemoteException -> L23
        Unit r43 = Unit.INSTANCE;     // Catch: Throwable -> L21 RemoteException -> L23
    L23:
        e = move-exception;
        Log.w("ROOM", "Error invoking a remote callback", e);     // Catch: Throwable -> L21
    L25:
        r3 = r3 + 1;
        goto L13
    L21:
        th = move-exception;
        r7.getCallbackList$room_runtime().finishBroadcast();     // Catch: Throwable -> L9
        throw th;     // Catch: Throwable -> L9
    L28:
        r7.getCallbackList$room_runtime().finishBroadcast();     // Catch: Throwable -> L9
        Unit r72 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    @Override // androidx.room.IMultiInstanceInvalidationService
    public int registerCallback(IMultiInstanceInvalidationCallback r6, String r7) {
        int r0 = 0;
        if (r7 != null) goto L5;
        return 0;
    L5:
        RemoteCallbackList<IMultiInstanceInvalidationCallback> r1 = this.this$0.getCallbackList$room_runtime();
        MultiInstanceInvalidationService r5 = this.this$0;
        monitor-enter(r1);
        r5.setMaxClientId$room_runtime(r5.getMaxClientId$room_runtime() + 1);     // Catch: Throwable -> L10
        int r2 = r5.getMaxClientId$room_runtime();     // Catch: Throwable -> L10
        if (r5.getCallbackList$room_runtime().register(r6, Integer.valueOf(r2)) == false) goto L12;
        r5.getClientNames$room_runtime().put(Integer.valueOf(r2), r7);     // Catch: Throwable -> L10
        r0 = r2;
    L13:
        monitor-exit(r1);
        return r0;
    L12:
        r5.setMaxClientId$room_runtime(r5.getMaxClientId$room_runtime() - 1);     // Catch: Throwable -> L10
        r5.getMaxClientId$room_runtime();     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    @Override // androidx.room.IMultiInstanceInvalidationService
    public void unregisterCallback(IMultiInstanceInvalidationCallback r3, int r4) {
        RemoteCallbackList<IMultiInstanceInvalidationCallback> r0 = this.this$0.getCallbackList$room_runtime();
        MultiInstanceInvalidationService r2 = this.this$0;
        monitor-enter(r0);
        r2.getCallbackList$room_runtime().unregister(r3);     // Catch: Throwable -> L7
        String r22 = r2.getClientNames$room_runtime().remove(Integer.valueOf(r4));     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
