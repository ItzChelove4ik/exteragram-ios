package androidx.datastore.core;

import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Ji\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00072\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n0\t2\b\b\u0002\u0010\r\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0007¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/datastore/core/MultiProcessDataStoreFactory;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "T", "Landroidx/datastore/core/Serializer;", "serializer", "Landroidx/datastore/core/handlers/ReplaceFileCorruptionHandler;", "corruptionHandler", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/core/DataMigration;", "migrations", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlin/Function0;", "Ljava/io/File;", "produceFile", "Landroidx/datastore/core/DataStore;", "create", "(Landroidx/datastore/core/Serializer;Landroidx/datastore/core/handlers/ReplaceFileCorruptionHandler;Ljava/util/List;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;)Landroidx/datastore/core/DataStore;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MultiProcessDataStoreFactory {
    public static final MultiProcessDataStoreFactory INSTANCE = null;

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/datastore/core/InterProcessCoordinator;", "T", "it", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.datastore.core.MultiProcessDataStoreFactory$create$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Lambda implements Function1<File, InterProcessCoordinator> {
        final /* synthetic */ CoroutineScope $scope;

        public AnonymousClass1(CoroutineScope r1) {
            this.$scope = r1;
            super(1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final InterProcessCoordinator invoke2(File r2) {
            return new MultiProcessCoordinator(this.$scope.getCoroutineContext(), r2);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ InterProcessCoordinator invoke(File r1) {
            return invoke2(r1);
        }
    }

    static {
        INSTANCE = new MultiProcessDataStoreFactory();
    }

    private MultiProcessDataStoreFactory() {
    }

    @JvmOverloads
    public final <T> DataStore<T> create(Serializer<T> r3, ReplaceFileCorruptionHandler<T> r4, List<? extends DataMigration<T>> r5, CoroutineScope r6, Function0<? extends File> r7) {
        FileStorage r0 = new FileStorage(r3, new AnonymousClass1(r6), r7);
        List r32 = CollectionsKt.listOf(DataMigrationInitializer.Companion.getInitializer(r5));
        if (r4 != null) goto L7;
        r4 = (ReplaceFileCorruptionHandler<T>) new NoOpCorruptionHandler();
    L7:
        return new DataStoreImpl(r0, r32, r4, r6);
    }
}
