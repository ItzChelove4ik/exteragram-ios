package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/datastore/core/StorageConnection;", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DataStoreImpl$storageConnectionDelegate$1<T> extends Lambda implements Function0<StorageConnection<T>> {
    final /* synthetic */ DataStoreImpl<T> this$0;

    public DataStoreImpl$storageConnectionDelegate$1(DataStoreImpl<T> r1) {
        this.this$0 = r1;
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final StorageConnection<T> invoke() {
        return DataStoreImpl.access$getStorage$p(this.this$0).createConnection();
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        return invoke();
    }
}
