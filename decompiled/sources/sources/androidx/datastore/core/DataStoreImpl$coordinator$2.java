package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/datastore/core/InterProcessCoordinator;", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DataStoreImpl$coordinator$2 extends Lambda implements Function0<InterProcessCoordinator> {
    final /* synthetic */ DataStoreImpl<T> this$0;

    public DataStoreImpl$coordinator$2(DataStoreImpl<T> r1) {
        this.this$0 = r1;
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final InterProcessCoordinator invoke() {
        return this.this$0.getStorageConnection$datastore_core_release().getCoordinator();
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ InterProcessCoordinator invoke() {
        return invoke();
    }
}
