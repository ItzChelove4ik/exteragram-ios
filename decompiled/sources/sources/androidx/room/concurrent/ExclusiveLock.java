package androidx.room.concurrent;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J3\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\u0002\u0010\u0016R\u0014\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/room/concurrent/ExclusiveLock;", _UrlKt.FRAGMENT_ENCODE_SET, "filename", _UrlKt.FRAGMENT_ENCODE_SET, "useFileLock", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Z)V", "threadLock", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "Ljava/util/concurrent/locks/ReentrantLock;", "fileLock", "Landroidx/room/concurrent/FileLock;", "withLock", "T", "onLocked", "Lkotlin/Function0;", "onLockError", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Companion", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ExclusiveLock {
    public static final Companion Companion = null;
    private static final Map<String, ReentrantLock> threadLocksMap = null;
    private final FileLock fileLock;
    private final ReentrantLock threadLock;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\n\u001a\u00060\bj\u0002`\t2\u0006\u0010\u000b\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0007H\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u00060\bj\u0002`\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/room/concurrent/ExclusiveLock$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/concurrent/SynchronizedObject;", "<init>", "()V", "threadLocksMap", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "getThreadLock", PluginsConstants.Settings.KEY, "(Ljava/lang/String;)Ljava/util/concurrent/locks/ReentrantLock;", "getFileLock", "Landroidx/room/concurrent/FileLock;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nExclusiveLock.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExclusiveLock.kt\nandroidx/room/concurrent/ExclusiveLock$Companion\n+ 2 Synchronized.jvmAndroid.kt\nandroidx/room/concurrent/Synchronized_jvmAndroidKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,77:1\n22#2:78\n384#3,7:79\n*S KotlinDebug\n*F\n+ 1 ExclusiveLock.kt\nandroidx/room/concurrent/ExclusiveLock$Companion\n*L\n70#1:78\n71#1:79,7\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ FileLock access$getFileLock(Companion r0, String r1) {
            return r0.getFileLock(r1);
        }

        public static final /* synthetic */ ReentrantLock access$getThreadLock(Companion r0, String r1) {
            return r0.getThreadLock(r1);
        }

        private final FileLock getFileLock(String r1) {
            return new FileLock(r1);
        }

        private final ReentrantLock getThreadLock(String r3) {
            monitor-enter(this);
            Map r0 = ExclusiveLock.access$getThreadLocksMap$cp();     // Catch: Throwable -> L6
            Object r1 = r0.get(r3);     // Catch: Throwable -> L6
            if (r1 != null) goto L8;
            r1 = new ReentrantLock();     // Catch: Throwable -> L6
            r0.put(r3, r1);     // Catch: Throwable -> L6
        L8:
            ReentrantLock r12 = (ReentrantLock) r1;     // Catch: Throwable -> L6
            monitor-exit(this);
            return r12;
        L6:
            th = move-exception;
            throw th;
        }
    }

    static {
        Companion = new Companion(null);
        threadLocksMap = new LinkedHashMap();
    }

    public ExclusiveLock(String r3, boolean r4) {
        Companion r0 = Companion;
        this.threadLock = Companion.access$getThreadLock(r0, r3);
        if (r4 == false) goto L5;
        FileLock r32 = Companion.access$getFileLock(r0, r3);
    L6:
        this.fileLock = r32;
        return;
    L5:
        r32 = null;
        goto L6
    }

    public static final /* synthetic */ Map access$getThreadLocksMap$cp() {
        return threadLocksMap;
    }

    public final <T> T withLock(Function0<? extends T> r3, Function1 r4) {
        this.threadLock.lock();
        boolean r0 = false;
        FileLock r1 = this.fileLock;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.lock();     // Catch: Throwable -> L7
    L9:
        r0 = true;
        T r32 = r3.invoke();     // Catch: Throwable -> L16
        FileLock r12 = this.fileLock;     // Catch: Throwable -> L7
        if (r12 == null) goto L14;
        r12.unlock();     // Catch: Throwable -> L7
    L14:
        this.threadLock.unlock();
        return r32;
    L16:
        th = move-exception;
        FileLock r13 = this.fileLock;     // Catch: Throwable -> L7
        if (r13 == null) goto L20;
        r13.unlock();     // Catch: Throwable -> L7
    L20:
        throw th;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        if (r0 == false) goto L25;
        throw th;     // Catch: Throwable -> L23
    L25:
        r4.invoke(th);     // Catch: Throwable -> L23
        throw new KotlinNothingValueException();     // Catch: Throwable -> L23
    L23:
        th = move-exception;
        this.threadLock.unlock();
        throw th;
    }
}
