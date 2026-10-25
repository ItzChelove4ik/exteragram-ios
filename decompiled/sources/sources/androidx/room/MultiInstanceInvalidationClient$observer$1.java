package androidx.room;

import android.os.RemoteException;
import android.util.Log;
import androidx.room.InvalidationTracker;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016R\u0014\u0010\u0007\u001a\u00020\b8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"androidx/room/MultiInstanceInvalidationClient$observer$1", "Landroidx/room/InvalidationTracker$Observer;", "onInvalidated", _UrlKt.FRAGMENT_ENCODE_SET, "tables", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isRemote", _UrlKt.FRAGMENT_ENCODE_SET, "isRemote$room_runtime", "()Z", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMultiInstanceInvalidationClient.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiInstanceInvalidationClient.android.kt\nandroidx/room/MultiInstanceInvalidationClient$observer$1\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,146:1\n37#2:147\n36#2,3:148\n*S KotlinDebug\n*F\n+ 1 MultiInstanceInvalidationClient.android.kt\nandroidx/room/MultiInstanceInvalidationClient$observer$1\n*L\n72#1:147\n72#1:148,3\n*E\n"})
/* loaded from: classes4.dex */
public final class MultiInstanceInvalidationClient$observer$1 extends InvalidationTracker.Observer {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    public MultiInstanceInvalidationClient$observer$1(MultiInstanceInvalidationClient r1, String[] r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // androidx.room.InvalidationTracker.Observer
    public boolean isRemote$room_runtime() {
        return true;
    }

    @Override // androidx.room.InvalidationTracker.Observer
    public void onInvalidated(Set<String> r3) {
        if (MultiInstanceInvalidationClient.access$getStopped$p(this.this$0).get() == true) goto L15;
        IMultiInstanceInvalidationService r0 = MultiInstanceInvalidationClient.access$getInvalidationService$p(this.this$0);     // Catch: RemoteException -> L9
        if (r0 == null) goto L14;
        r0.broadcastInvalidation(MultiInstanceInvalidationClient.access$getClientId$p(this.this$0), (String[]) r3.toArray(new String[0]));     // Catch: RemoteException -> L9
        return;
    L14:
        return;
    L9:
        e = move-exception;
        Log.w("ROOM", "Cannot broadcast invalidation", e);
        return;
    }
}
