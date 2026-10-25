package kotlin.coroutines;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0087\u0080\u0004¨\u0006\b"}, d2 = {"getPolymorphicElement", "E", "Lkotlin/coroutines/CoroutineContext$Element;", PluginsConstants.Settings.KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Element;Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusPolymorphicKey", "Lkotlin/coroutines/CoroutineContext;", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CoroutineContextImplKt {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final <E extends CoroutineContext.Element> E getPolymorphicElement(CoroutineContext.Element r2, CoroutineContext.Key<E> r3) {
        if ((r3 instanceof AbstractCoroutineContextKey) == false) goto L11;
        AbstractCoroutineContextKey r32 = (AbstractCoroutineContextKey) r3;
        if (r32.isSubKey$kotlin_stdlib(r2.getKey()) == false) goto L9;
        E r22 = (E) r32.tryCast$kotlin_stdlib(r2);
        if (r22 == null) goto L9;
        return r22;
    L9:
        return null;
    L11:
        if (r2.getKey() != r3) goto L13;
        return r2;
    L13:
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final CoroutineContext minusPolymorphicKey(CoroutineContext.Element r1, CoroutineContext.Key<?> r2) {
        if ((r2 instanceof AbstractCoroutineContextKey) == false) goto L11;
        AbstractCoroutineContextKey r22 = (AbstractCoroutineContextKey) r2;
        if (r22.isSubKey$kotlin_stdlib(r1.getKey()) == true) goto L7;
        return r1;
    L7:
        if (r22.tryCast$kotlin_stdlib(r1) != null) goto L9;
        return r1;
    L9:
        return EmptyCoroutineContext.INSTANCE;
    L11:
        if (r1.getKey() == r2) goto L13;
        return r1;
    L13:
        return EmptyCoroutineContext.INSTANCE;
    }
}
