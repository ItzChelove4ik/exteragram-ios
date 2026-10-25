package androidx.datastore.core;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000eB7\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/datastore/core/FileStorage;", "T", "Landroidx/datastore/core/Storage;", "serializer", "Landroidx/datastore/core/Serializer;", "coordinatorProducer", "Lkotlin/Function1;", "Ljava/io/File;", "Landroidx/datastore/core/InterProcessCoordinator;", "produceFile", "Lkotlin/Function0;", "(Landroidx/datastore/core/Serializer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "createConnection", "Landroidx/datastore/core/StorageConnection;", "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FileStorage<T> implements Storage<T> {
    public static final Companion Companion = null;
    private static final Set<String> activeFiles = null;
    private static final Object activeFilesLock = null;
    private final Function1<File, InterProcessCoordinator> coordinatorProducer;
    private final Function0<File> produceFile;
    private final Serializer<T> serializer;

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/datastore/core/InterProcessCoordinator;", "T", "it", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.datastore.core.FileStorage$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Lambda implements Function1<File, InterProcessCoordinator> {
        public static final AnonymousClass1 INSTANCE = null;

        static {
            INSTANCE = new AnonymousClass1();
        }

        public AnonymousClass1() {
            super(1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final InterProcessCoordinator invoke2(File r1) {
            return InterProcessCoordinator_jvmKt.createSingleProcessCoordinator(r1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ InterProcessCoordinator invoke(File r1) {
            return invoke2(r1);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/datastore/core/FileStorage$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "activeFiles", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getActiveFiles$datastore_core_release", "()Ljava/util/Set;", "activeFilesLock", "getActiveFilesLock$datastore_core_release", "()Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Set<String> getActiveFiles$datastore_core_release() {
            return FileStorage.access$getActiveFiles$cp();
        }

        public final Object getActiveFilesLock$datastore_core_release() {
            return FileStorage.access$getActiveFilesLock$cp();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.datastore.core.FileStorage$createConnection$2, reason: invalid class name */
    public static final class AnonymousClass2 extends Lambda implements Function0<Unit> {
        final /* synthetic */ File $file;

        public AnonymousClass2(File r1) {
            this.$file = r1;
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            Companion r0 = FileStorage.Companion;
            Object r1 = r0.getActiveFilesLock$datastore_core_release();
            File r2 = this.$file;
            monitor-enter(r1);
            r0.getActiveFiles$datastore_core_release().remove(r2.getAbsolutePath());     // Catch: Throwable -> L7
            Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L7
            monitor-exit(r1);
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    static {
        Companion = new Companion(null);
        activeFiles = new LinkedHashSet();
        activeFilesLock = new Object();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FileStorage(Serializer<T> r1, Function1<? super File, ? extends InterProcessCoordinator> r2, Function0<? extends File> r3) {
        this.serializer = r1;
        this.coordinatorProducer = r2;
        this.produceFile = r3;
    }

    public /* synthetic */ FileStorage(Serializer r1, Function1 r2, Function0 r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 2) == 0) goto L5;
        r2 = AnonymousClass1.INSTANCE;
    L5:
        this(r1, r2, r3);
    }

    public static final /* synthetic */ Set access$getActiveFiles$cp() {
        return activeFiles;
    }

    public static final /* synthetic */ Object access$getActiveFilesLock$cp() {
        return activeFilesLock;
    }

    @Override // androidx.datastore.core.Storage
    public StorageConnection<T> createConnection() {
        File r1 = this.produceFile.invoke().getCanonicalFile();
        Object r2 = activeFilesLock;
        monitor-enter(r2);
        String r3 = r1.getAbsolutePath();     // Catch: Throwable -> L10
        Set<String> r4 = activeFiles;     // Catch: Throwable -> L10
        if (r4.contains(r3) == true) goto L13;
        r4.add(r3);     // Catch: Throwable -> L10
        monitor-exit(r2);
        return new FileStorageConnection(r1, this.serializer, this.coordinatorProducer.invoke(r1), new AnonymousClass2(r1));
    L13:
        throw new IllegalStateException(("There are multiple DataStores active for the same file: " + r3 + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }
}
