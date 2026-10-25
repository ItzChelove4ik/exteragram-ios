package androidx.room;

import androidx.room.IMultiInstanceInvalidationCallback;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005H\u0016¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"androidx/room/MultiInstanceInvalidationClient$invalidationCallback$1", "Landroidx/room/IMultiInstanceInvalidationCallback$Stub;", "onInvalidation", _UrlKt.FRAGMENT_ENCODE_SET, "tables", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/String;)V", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MultiInstanceInvalidationClient$invalidationCallback$1 extends IMultiInstanceInvalidationCallback.Stub {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    public MultiInstanceInvalidationClient$invalidationCallback$1(MultiInstanceInvalidationClient r1) {
        this.this$0 = r1;
    }

    @Override // androidx.room.IMultiInstanceInvalidationCallback
    public void onInvalidation(String[] r8) {
        BuildersKt.launch$default(MultiInstanceInvalidationClient.access$getCoroutineScope$p(this.this$0), null, null, new MultiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1(r8, this.this$0, null), 3, null);
    }
}
