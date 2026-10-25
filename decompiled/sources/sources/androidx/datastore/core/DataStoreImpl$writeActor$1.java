package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "it", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DataStoreImpl$writeActor$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ DataStoreImpl<T> this$0;

    public DataStoreImpl$writeActor$1(DataStoreImpl<T> r1) {
        this.this$0 = r1;
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r3) {
        if (r3 == null) goto L5;
        DataStoreImpl.access$getInMemoryCache$p(this.this$0).tryUpdate(new Final(r3));
    L5:
        if (DataStoreImpl.access$getStorageConnectionDelegate$p(this.this$0).isInitialized() == false) goto L8;
        this.this$0.getStorageConnection$datastore_core_release().close();
        return;
    }
}
