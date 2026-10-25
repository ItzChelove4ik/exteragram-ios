package androidx.room.coroutines;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/room/coroutines/ConnectionElement;", "Lkotlin/coroutines/CoroutineContext$Element;", PluginsConstants.Settings.KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "connectionWrapper", "Landroidx/room/coroutines/PooledConnectionImpl;", "<init>", "(Lkotlin/coroutines/CoroutineContext$Key;Landroidx/room/coroutines/PooledConnectionImpl;)V", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "getConnectionWrapper", "()Landroidx/room/coroutines/PooledConnectionImpl;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
final class ConnectionElement implements CoroutineContext.Element {
    private final PooledConnectionImpl connectionWrapper;
    private final CoroutineContext.Key<ConnectionElement> key;

    public ConnectionElement(CoroutineContext.Key<ConnectionElement> r1, PooledConnectionImpl r2) {
        this.key = r1;
        this.connectionWrapper = r2;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r1, Function2<? super R, ? super CoroutineContext.Element, ? extends R> r2) {
        return (R) CoroutineContext.Element.DefaultImpls.fold(this, r1, r2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> r1) {
        return (E) CoroutineContext.Element.DefaultImpls.get(this, r1);
    }

    public final PooledConnectionImpl getConnectionWrapper() {
        return this.connectionWrapper;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key<ConnectionElement> getKey() {
        return this.key;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> r1) {
        return CoroutineContext.Element.DefaultImpls.minusKey(this, r1);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext r1) {
        return CoroutineContext.Element.DefaultImpls.plus(this, r1);
    }
}
