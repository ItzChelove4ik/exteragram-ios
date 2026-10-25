package kotlin;

import kotlin.jvm.functions.Function0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0086\u0080\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0086\u0080\u0004\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0086\u0080\u0004¨\u0006\t"}, d2 = {"lazy", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "mode", "Lkotlin/LazyThreadSafetyMode;", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/LazyKt")
/* loaded from: classes.dex */
class LazyKt__LazyJVMKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[LazyThreadSafetyMode.values().length];
            r0[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[LazyThreadSafetyMode.NONE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public LazyKt__LazyJVMKt() {
    }

    public static final <T> Lazy<T> lazy(Object r1, Function0<? extends T> r2) {
        return new SynchronizedLazyImpl(r2, r1);
    }

    public static <T> Lazy<T> lazy(LazyThreadSafetyMode r2, Function0<? extends T> r3) {
        int r22 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r22 == 1) goto L14;
        if (r22 == 2) goto L12;
        if (r22 == 3) goto L8;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L8:
        return new UnsafeLazyImpl(r3);
    L12:
        return new SafePublicationLazyImpl(r3);
    L14:
        return new SynchronizedLazyImpl(r3, null, 2, null);
    }

    public static <T> Lazy<T> lazy(Function0<? extends T> r3) {
        return new SynchronizedLazyImpl(r3, null, 2, null);
    }
}
