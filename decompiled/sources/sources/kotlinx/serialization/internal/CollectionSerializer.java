package kotlinx.serialization.internal;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlinx.serialization.KSerializer;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\b!\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003*\u0004\b\u0002\u0010\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0005B\u0017\bF\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b*\u00028\u0001H\u0094\u0080\u0004¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e*\u00028\u0001H\u0094\u0080\u0004¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/internal/CollectionSerializer;", "E", "C", _UrlKt.FRAGMENT_ENCODE_SET, "B", "Lkotlinx/serialization/internal/CollectionLikeSerializer;", "element", "Lkotlinx/serialization/KSerializer;", "<init>", "(Lkotlinx/serialization/KSerializer;)V", "collectionSize", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Collection;)I", "collectionIterator", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Collection;)Ljava/util/Iterator;", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@PublishedApi
/* loaded from: classes5.dex */
public abstract class CollectionSerializer<E, C extends Collection<? extends E>, B> extends CollectionLikeSerializer<E, C, B> {
    public CollectionSerializer(KSerializer<E> r2) {
        super(r2, null);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ Iterator collectionIterator(Object r1) {
        return collectionIterator((Collection) r1);
    }

    public Iterator<E> collectionIterator(C r1) {
        return r1.iterator();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ int collectionSize(Object r1) {
        return collectionSize((Collection) r1);
    }

    public int collectionSize(C r1) {
        return r1.size();
    }
}
