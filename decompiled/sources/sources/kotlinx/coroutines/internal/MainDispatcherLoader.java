package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/internal/MainDispatcherLoader;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "loadMainDispatcher", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "dispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMainDispatchers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainDispatchers.kt\nkotlinx/coroutines/internal/MainDispatcherLoader\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,130:1\n1999#2,14:131\n*S KotlinDebug\n*F\n+ 1 MainDispatchers.kt\nkotlinx/coroutines/internal/MainDispatcherLoader\n*L\n34#1:131,14\n*E\n"})
/* loaded from: classes.dex */
public final class MainDispatcherLoader {
    public static final MainDispatcherLoader INSTANCE = null;

    @JvmField
    public static final MainCoroutineDispatcher dispatcher = null;

    static {
        MainDispatcherLoader r0 = new MainDispatcherLoader();
        INSTANCE = r0;
        SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader", true);
        dispatcher = r0.loadMainDispatcher();
    }

    private MainDispatcherLoader() {
    }

    private final MainCoroutineDispatcher loadMainDispatcher() {
        List r6 = SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));     // Catch: Throwable -> L21
        Iterator r1 = r6.iterator();     // Catch: Throwable -> L21
        if (r1.hasNext() == true) goto L6;
        Object r2 = null;
    L15:
        MainDispatcherFactory r22 = (MainDispatcherFactory) r2;     // Catch: Throwable -> L21
        if (r22 == null) goto L23;
        MainCoroutineDispatcher r62 = MainDispatchersKt.tryCreateDispatcher(r22, r6);     // Catch: Throwable -> L21
        if (r62 == null) goto L23;
        return r62;
    L23:
        MainDispatchersKt.createMissingDispatcher$default(null, null, 3, null);     // Catch: Throwable -> L21
        return null;
    L6:
        r2 = r1.next();     // Catch: Throwable -> L21
        if (r1.hasNext() == false) goto L15;
        int r3 = ((MainDispatcherFactory) r2).getLoadPriority();     // Catch: Throwable -> L21
    L10:
        Object r4 = r1.next();     // Catch: Throwable -> L21
        int r5 = ((MainDispatcherFactory) r4).getLoadPriority();     // Catch: Throwable -> L21
        if (r3 >= r5) goto L14;
        r2 = r4;
        r3 = r5;
    L14:
        if (r1.hasNext() == true) goto L10;
    L21:
        th = move-exception;
        MainDispatchersKt.createMissingDispatcher$default(th, null, 2, null);
        return null;
    }
}
