package androidx.datastore.core;

import android.os.FileObserver;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR,\u0010\u0011\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\b0\u000fj\u0002`\u00100\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/datastore/core/MulticastFileObserver;", "Landroid/os/FileObserver;", _UrlKt.FRAGMENT_ENCODE_SET, "path", "<init>", "(Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "event", _UrlKt.FRAGMENT_ENCODE_SET, "onEvent", "(ILjava/lang/String;)V", "Ljava/lang/String;", "getPath", "()Ljava/lang/String;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "Landroidx/datastore/core/FileMoveObserver;", "delegates", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMulticastFileObserver.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MulticastFileObserver.android.kt\nandroidx/datastore/core/MulticastFileObserver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,145:1\n1855#2,2:146\n*S KotlinDebug\n*F\n+ 1 MulticastFileObserver.android.kt\nandroidx/datastore/core/MulticastFileObserver\n*L\n50#1:146,2\n*E\n"})
/* loaded from: classes.dex */
public final class MulticastFileObserver extends FileObserver {
    public static final Companion Companion = null;
    private static final Object LOCK = null;
    private static final Map<String, MulticastFileObserver> fileObservers = null;
    private final CopyOnWriteArrayList<Function1<String, Unit>> delegates;
    private final String path;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0018\u0010\n\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\tH\u0003¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u000e\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\u0010R,\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u00118\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u0012\u0004\b\u0017\u0010\u0003\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/datastore/core/MulticastFileObserver$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Ljava/io/File;", "parent", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/core/FileMoveObserver;", "observer", "Lkotlinx/coroutines/DisposableHandle;", "observe", "(Ljava/io/File;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "file", "Lkotlinx/coroutines/flow/Flow;", "(Ljava/io/File;)Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/core/MulticastFileObserver;", "fileObservers", "Ljava/util/Map;", "getFileObservers$datastore_core_release", "()Ljava/util/Map;", "getFileObservers$datastore_core_release$annotations", "LOCK", "Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMulticastFileObserver.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MulticastFileObserver.android.kt\nandroidx/datastore/core/MulticastFileObserver$Companion\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,145:1\n372#2,7:146\n1855#3,2:153\n*S KotlinDebug\n*F\n+ 1 MulticastFileObserver.android.kt\nandroidx/datastore/core/MulticastFileObserver$Companion\n*L\n103#1:146,7\n137#1:153,2\n*E\n"})
    public static final class Companion {
        public static void $r8$lambda$RJDrtcZMUHSzp9sK3YDnx03MGSE(String r4, Function1 r5) {
            Object r0 = MulticastFileObserver.access$getLOCK$cp();
            monitor-enter(r0);
            Companion r1 = MulticastFileObserver.Companion;     // Catch: Throwable -> L9
            MulticastFileObserver r2 = r1.getFileObservers$datastore_core_release().get(r4);     // Catch: Throwable -> L9
            if (r2 == null) goto L11;
            MulticastFileObserver.access$getDelegates$p(r2).remove(r5);     // Catch: Throwable -> L9
            if (MulticastFileObserver.access$getDelegates$p(r2).isEmpty() == false) goto L11;
            r1.getFileObservers$datastore_core_release().remove(r4);     // Catch: Throwable -> L9
            r2.stopWatching();     // Catch: Throwable -> L9
        L11:
            Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r0);
            return;
        L9:
            th = move-exception;
            throw th;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ DisposableHandle access$observe(Companion r0, File r1, Function1 r2) {
            return r0.observe(r1, r2);
        }

        private final DisposableHandle observe(File r4, Function1<? super String, Unit> r5) {
            String r3 = r4.getCanonicalFile().getPath();
            Object r42 = MulticastFileObserver.access$getLOCK$cp();
            monitor-enter(r42);
            Map<String, MulticastFileObserver> r0 = MulticastFileObserver.Companion.getFileObservers$datastore_core_release();     // Catch: Throwable -> L7
            MulticastFileObserver r1 = r0.get(r3);     // Catch: Throwable -> L7
            if (r1 != null) goto L9;
            r1 = new MulticastFileObserver(r3, null);     // Catch: Throwable -> L7
            r0.put(r3, r1);     // Catch: Throwable -> L7
        L9:
            MulticastFileObserver r12 = r1;     // Catch: Throwable -> L7
            MulticastFileObserver.access$getDelegates$p(r12).add(r5);     // Catch: Throwable -> L7
            if (MulticastFileObserver.access$getDelegates$p(r12).size() != 1) goto L12;
            r12.startWatching();     // Catch: Throwable -> L7
        L12:
            Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L7
            monitor-exit(r42);
            return new MulticastFileObserver$Companion$$ExternalSyntheticLambda0(r3, r5);
        L7:
            th = move-exception;
            throw th;
        }

        public final Map<String, MulticastFileObserver> getFileObservers$datastore_core_release() {
            return MulticastFileObserver.access$getFileObservers$cp();
        }

        public final Flow<Unit> observe(File r2) {
            return FlowKt.channelFlow(new MulticastFileObserver$Companion$observe$1(r2, null));
        }
    }

    static {
        Companion = new Companion(null);
        LOCK = new Object();
        fileObservers = new LinkedHashMap();
    }

    private MulticastFileObserver(String r2) {
        super(r2, 128);
        this.path = r2;
        this.delegates = new CopyOnWriteArrayList();
    }

    public /* synthetic */ MulticastFileObserver(String r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static final /* synthetic */ CopyOnWriteArrayList access$getDelegates$p(MulticastFileObserver r0) {
        return r0.delegates;
    }

    public static final /* synthetic */ Map access$getFileObservers$cp() {
        return fileObservers;
    }

    public static final /* synthetic */ Object access$getLOCK$cp() {
        return LOCK;
    }

    @Override // android.os.FileObserver
    public void onEvent(int r1, String r2) {
        Iterator<T> r0 = this.delegates.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((Function1) r0.next()).invoke(r2);
        goto L4
    }
}
