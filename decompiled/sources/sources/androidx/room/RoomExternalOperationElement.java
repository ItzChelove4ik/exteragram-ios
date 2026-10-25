package androidx.room;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/room/RoomExternalOperationElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext$Key;", "<init>", "()V", PluginsConstants.Settings.KEY, "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RoomExternalOperationElement implements CoroutineContext.Element, CoroutineContext.Key<RoomExternalOperationElement> {
    public static final RoomExternalOperationElement INSTANCE = null;

    static {
        INSTANCE = new RoomExternalOperationElement();
    }

    private RoomExternalOperationElement() {
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r1, Function2<? super R, ? super CoroutineContext.Element, ? extends R> r2) {
        return (R) CoroutineContext.Element.DefaultImpls.fold(this, r1, r2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> r1) {
        return (E) CoroutineContext.Element.DefaultImpls.get(this, r1);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key<RoomExternalOperationElement> getKey() {
        return INSTANCE;
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
