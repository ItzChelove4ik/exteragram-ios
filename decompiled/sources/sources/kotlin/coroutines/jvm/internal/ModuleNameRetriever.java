package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nH\u0086\u0080\u0004J\u001a\u0010\u000b\u001a\u00020\u00052\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nH\u0082\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "notOnJava9", "Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "cache", "getModuleName", _UrlKt.FRAGMENT_ENCODE_SET, "continuation", "Lkotlin/coroutines/Continuation;", "buildCache", "Cache", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDebugMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/ModuleNameRetriever\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,161:1\n1#2:162\n*E\n"})
/* loaded from: classes5.dex */
public final class ModuleNameRetriever {
    public static final ModuleNameRetriever INSTANCE = null;
    private static Cache cache;
    private static final Cache notOnJava9 = null;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B'\bF\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", _UrlKt.FRAGMENT_ENCODE_SET, "getModuleMethod", "Ljava/lang/reflect/Method;", "getDescriptorMethod", "nameMethod", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Cache {

        @JvmField
        public final Method getDescriptorMethod;

        @JvmField
        public final Method getModuleMethod;

        @JvmField
        public final Method nameMethod;

        public Cache(Method r1, Method r2, Method r3) {
            this.getModuleMethod = r1;
            this.getDescriptorMethod = r2;
            this.nameMethod = r3;
        }
    }

    static {
        INSTANCE = new ModuleNameRetriever();
        notOnJava9 = new Cache(null, null, null);
    }

    private ModuleNameRetriever() {
    }

    private final Cache buildCache(Continuation<Object> r4) {
        Cache r1 = new Cache(Class.class.getDeclaredMethod("getModule", null), r4.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), r4.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));     // Catch: Exception -> L4
        cache = r1;     // Catch: Exception -> L4
        return r1;
    L4:
        Cache r3 = notOnJava9;
        cache = r3;
        return r3;
    }

    public final String getModuleName(Continuation<Object> r3) {
        Cache r0 = cache;
        if (r0 != null) goto L6;
        r0 = buildCache(r3);
    L6:
        if (r0 != notOnJava9) goto L8;
        return null;
    L8:
        Method r2 = r0.getModuleMethod;
        if (r2 == null) goto L26;
        Object r22 = r2.invoke(r3.getClass(), null);
        if (r22 == null) goto L26;
        Method r32 = r0.getDescriptorMethod;
        if (r32 == null) goto L26;
        Object r23 = r32.invoke(r22, null);
        if (r23 == null) goto L26;
        Method r33 = r0.nameMethod;
        if (r33 == null) goto L21;
        Object r24 = r33.invoke(r23, null);
    L23:
        if ((r24 instanceof String) == false) goto L26;
        return (String) r24;
    L21:
        r24 = null;
    L26:
        return null;
    }
}
