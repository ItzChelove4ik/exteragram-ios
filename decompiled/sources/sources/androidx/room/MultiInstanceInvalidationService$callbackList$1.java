package androidx.room;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import com.sun.jna.Callback;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"androidx/room/MultiInstanceInvalidationService$callbackList$1", "Landroid/os/RemoteCallbackList;", "Landroidx/room/IMultiInstanceInvalidationCallback;", "onCallbackDied", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "cookie", _UrlKt.FRAGMENT_ENCODE_SET, "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MultiInstanceInvalidationService$callbackList$1 extends RemoteCallbackList<IMultiInstanceInvalidationCallback> {
    final /* synthetic */ MultiInstanceInvalidationService this$0;

    public MultiInstanceInvalidationService$callbackList$1(MultiInstanceInvalidationService r1) {
        this.this$0 = r1;
    }

    @Override // android.os.RemoteCallbackList
    public /* bridge */ /* synthetic */ void onCallbackDied(IInterface r1, Object r2) {
        onCallbackDied((IMultiInstanceInvalidationCallback) r1, r2);
    }

    public void onCallbackDied(IMultiInstanceInvalidationCallback r1, Object r2) {
        this.this$0.getClientNames$room_runtime().remove((Integer) r2);
    }
}
