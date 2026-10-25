package androidx.room;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.room.IMultiInstanceInvalidationService;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"androidx/room/MultiInstanceInvalidationClient$serviceConnection$1", "Landroid/content/ServiceConnection;", "onServiceConnected", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MultiInstanceInvalidationClient$serviceConnection$1 implements ServiceConnection {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    public MultiInstanceInvalidationClient$serviceConnection$1(MultiInstanceInvalidationClient r1) {
        this.this$0 = r1;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName r1, IBinder r2) {
        MultiInstanceInvalidationClient.access$setInvalidationService$p(this.this$0, IMultiInstanceInvalidationService.Stub.asInterface(r2));
        MultiInstanceInvalidationClient.access$registerCallback(this.this$0);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName r1) {
        MultiInstanceInvalidationClient.access$setInvalidationService$p(this.this$0, null);
    }
}
