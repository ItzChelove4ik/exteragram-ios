package kotlin.concurrent;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aN\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086\u0080\u0004\u001a4\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\fH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"thread", "Ljava/lang/Thread;", "start", _UrlKt.FRAGMENT_ENCODE_SET, "isDaemon", "contextClassLoader", "Ljava/lang/ClassLoader;", "name", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.MenuItemProperties.PRIORITY, _UrlKt.FRAGMENT_ENCODE_SET, "block", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "getOrSet", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/ThreadLocal;", PluginsConstants.Settings.DEFAULT, "(Ljava/lang/ThreadLocal;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "ThreadsKt")
@SourceDebugExtension({"SMAP\nThread.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Thread.kt\nkotlin/concurrent/ThreadsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"})
/* loaded from: classes5.dex */
public final class ThreadsKt {
    @InlineOnly
    private static final <T> T getOrSet(ThreadLocal<T> r1, Function0<? extends T> r2) {
        T r0 = r1.get();
        if (r0 != null) goto L6;
        T r22 = r2.invoke();
        r1.set(r22);
        return r22;
    L6:
        return r0;
    }

    public static final Thread thread(boolean r1, boolean r2, ClassLoader r3, String r4, int r5, Function0<Unit> r6) {
        ThreadsKt$thread$thread$1 r0 = new ThreadsKt$thread$thread$1(r6);
        if (r2 == false) goto L5;
        r0.setDaemon(true);
    L5:
        if (r5 <= 0) goto L7;
        r0.setPriority(r5);
    L7:
        if (r4 == null) goto L9;
        r0.setName(r4);
    L9:
        if (r3 == null) goto L11;
        r0.setContextClassLoader(r3);
    L11:
        if (r1 == false) goto L13;
        r0.start();
    L13:
        return r0;
    }

    public static /* synthetic */ Thread thread$default(boolean r1, boolean r2, ClassLoader r3, String r4, int r5, Function0 r6, int r7, Object r8) {
        if ((r7 & 1) == 0) goto L6;
        r1 = true;
    L6:
        if ((r7 & 2) == 0) goto L9;
        r2 = false;
    L9:
        if ((r7 & 4) == 0) goto L12;
        r3 = null;
    L12:
        if ((r7 & 8) == 0) goto L15;
        r4 = null;
    L15:
        if ((r7 & 16) == 0) goto L17;
        r5 = -1;
    L17:
        int r62 = r5;
        String r52 = r4;
        ClassLoader r42 = r3;
        return thread(r1, r2, r42, r52, r62, r6);
    }
}
