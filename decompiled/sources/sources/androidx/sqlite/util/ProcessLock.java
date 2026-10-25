package androidx.sqlite.util;

import android.util.Log;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\u0011\u001a\u00020\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/sqlite/util/ProcessLock;", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "lockDir", "Ljava/io/File;", "processLock", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Ljava/io/File;Z)V", "lockFile", "threadLock", "Ljava/util/concurrent/locks/Lock;", "lockChannel", "Ljava/nio/channels/FileChannel;", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "unlock", "Companion", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProcessLock.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessLock.android.kt\nandroidx/sqlite/util/ProcessLock\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,97:1\n1#2:98\n*E\n"})
/* loaded from: classes.dex */
public final class ProcessLock {
    private static final Companion Companion = null;
    private static final Map<String, Lock> threadLocksMap = null;
    private FileChannel lockChannel;
    private final File lockFile;
    private final boolean processLock;
    private final Lock threadLock;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/sqlite/util/ProcessLock$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "threadLocksMap", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/locks/Lock;", "getThreadLock", PluginsConstants.Settings.KEY, "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nProcessLock.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessLock.android.kt\nandroidx/sqlite/util/ProcessLock$Companion\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,97:1\n384#2,7:98\n*S KotlinDebug\n*F\n+ 1 ProcessLock.android.kt\nandroidx/sqlite/util/ProcessLock$Companion\n*L\n93#1:98,7\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ Lock access$getThreadLock(Companion r0, String r1) {
            return r0.getThreadLock(r1);
        }

        private final Lock getThreadLock(String r3) {
            Map r2 = ProcessLock.access$getThreadLocksMap$cp();
            monitor-enter(r2);
            Map r0 = ProcessLock.access$getThreadLocksMap$cp();     // Catch: Throwable -> L7
            Object r1 = r0.get(r3);     // Catch: Throwable -> L7
            if (r1 != null) goto L9;
            r1 = new ReentrantLock();     // Catch: Throwable -> L7
            r0.put(r3, r1);     // Catch: Throwable -> L7
        L9:
            Lock r12 = (Lock) r1;     // Catch: Throwable -> L7
            monitor-exit(r2);
            return r12;
        L7:
            th = move-exception;
            throw th;
        }
    }

    static {
        Companion = new Companion(null);
        threadLocksMap = new HashMap();
    }

    public ProcessLock(String r3, File r4, boolean r5) {
        this.processLock = r5;
        if (r4 == null) goto L5;
        File r52 = new File(r4, r3 + ".lck");
    L6:
        this.lockFile = r52;
        this.threadLock = Companion.access$getThreadLock(Companion, r3);
        return;
    L5:
        r52 = null;
        goto L6
    }

    public static final /* synthetic */ Map access$getThreadLocksMap$cp() {
        return threadLocksMap;
    }

    public static /* synthetic */ void lock$default(ProcessLock r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = r0.processLock;
    L5:
        r0.lock(r1);
    }

    public final void lock(boolean r2) {
        this.threadLock.lock();
        if (r2 == false) goto L18;
        File r22 = this.lockFile;     // Catch: IOException -> L9
        if (r22 == null) goto L14;
        File r23 = r22.getParentFile();     // Catch: IOException -> L9
        if (r23 == null) goto L11;
        r23.mkdirs();     // Catch: IOException -> L9
    L11:
        FileChannel r24 = new FileOutputStream(this.lockFile).getChannel();     // Catch: IOException -> L9
        r24.lock();     // Catch: IOException -> L9
        this.lockChannel = r24;     // Catch: IOException -> L9
        return;
    L14:
        throw new IOException("No lock directory was provided.");     // Catch: IOException -> L9
    L9:
        e = move-exception;
        this.lockChannel = null;
        Log.w("SupportSQLiteLock", "Unable to grab file lock.", e);
        return;
    }

    public final void unlock() {
        FileChannel r0 = this.lockChannel;     // Catch: IOException -> L7
        if (r0 == null) goto L5;
        r0.close();     // Catch: IOException -> L7
    L5:
        this.threadLock.unlock();
    }
}
