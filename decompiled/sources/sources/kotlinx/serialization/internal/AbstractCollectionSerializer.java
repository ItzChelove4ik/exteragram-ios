package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u00028\u00010\u0004B\t\b\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00028\u0002H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\n*\u00028\u0001H$¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f*\u00028\u0001H$¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\t\u001a\u00028\u0002H$¢\u0006\u0004\b\t\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\n*\u00028\u0002H$¢\u0006\u0004\b\u0013\u0010\u000eJ\u0013\u0010\u0014\u001a\u00028\u0001*\u00028\u0002H$¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00028\u0002*\u00028\u0001H$¢\u0006\u0004\b\u0016\u0010\u0015J\u001b\u0010\u0019\u001a\u00020\u0018*\u00028\u00022\u0006\u0010\u0017\u001a\u00020\nH$¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001d\u001a\u00028\u00012\u0006\u0010\b\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00018\u0001H\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00028\u00012\u0006\u0010\b\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001f\u0010 J1\u0010$\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u00022\b\b\u0002\u0010#\u001a\u00020\"H$¢\u0006\u0004\b$\u0010%J/\u0010'\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00028\u00022\u0006\u0010&\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH$¢\u0006\u0004\b'\u0010(\u0082\u0001\u0002)*¨\u0006+"}, d2 = {"Lkotlinx/serialization/internal/AbstractCollectionSerializer;", "Element", "Collection", "Builder", "Lkotlinx/serialization/KSerializer;", "<init>", "()V", "Lkotlinx/serialization/encoding/CompositeDecoder;", "decoder", "builder", _UrlKt.FRAGMENT_ENCODE_SET, "readSize", "(Lkotlinx/serialization/encoding/CompositeDecoder;Ljava/lang/Object;)I", "collectionSize", "(Ljava/lang/Object;)I", _UrlKt.FRAGMENT_ENCODE_SET, "collectionIterator", "(Ljava/lang/Object;)Ljava/util/Iterator;", "()Ljava/lang/Object;", "builderSize", "toResult", "(Ljava/lang/Object;)Ljava/lang/Object;", "toBuilder", "size", _UrlKt.FRAGMENT_ENCODE_SET, "checkCapacity", "(Ljava/lang/Object;I)V", "Lkotlinx/serialization/encoding/Decoder;", "previous", "merge", "(Lkotlinx/serialization/encoding/Decoder;Ljava/lang/Object;)Ljava/lang/Object;", "deserialize", "(Lkotlinx/serialization/encoding/Decoder;)Ljava/lang/Object;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "checkIndex", "readElement", "(Lkotlinx/serialization/encoding/CompositeDecoder;ILjava/lang/Object;Z)V", "startIndex", "readAll", "(Lkotlinx/serialization/encoding/CompositeDecoder;Ljava/lang/Object;II)V", "Lkotlinx/serialization/internal/CollectionLikeSerializer;", "Lkotlinx/serialization/internal/MapLikeSerializer;", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractCollectionSerializer<Element, Collection, Builder> implements KSerializer<Collection> {
    private AbstractCollectionSerializer() {
    }

    public /* synthetic */ AbstractCollectionSerializer(DefaultConstructorMarker r1) {
        this();
    }

    public static /* synthetic */ void readElement$default(AbstractCollectionSerializer r0, CompositeDecoder r1, int r2, Object r3, boolean r4, int r5, Object r6) {
        if (r6 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: readElement");
        return;
    L4:
        if ((r5 & 8) == 0) goto L6;
        r4 = true;
    L6:
        r0.readElement(r1, r2, r3, r4);
    }

    private final int readSize(CompositeDecoder r2, Builder r3) {
        int r22 = r2.decodeCollectionSize(getDescriptor());
        checkCapacity(r3, r22);
        return r22;
    }

    public abstract Builder builder();

    public abstract int builderSize(Builder r1);

    public abstract void checkCapacity(Builder r1, int r2);

    public abstract Iterator<Element> collectionIterator(Collection r1);

    public abstract int collectionSize(Collection r1);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Collection deserialize(Decoder r2) {
        return merge(r2, null);
    }

    public final Collection merge(Decoder r8, Collection r9) {
        if (r9 == null) goto L7;
        Builder r92 = toBuilder(r9);
        if (r92 == null) goto L7;
    L6:
        Builder r3 = r92;
        int r93 = builderSize(r3);
        CompositeDecoder r1 = r8.beginStructure(getDescriptor());
        if (r1.decodeSequentially() == false) goto L12;
        readAll(r1, r3, r93, readSize(r1, r3));
    L15:
        r1.endStructure(getDescriptor());
        return toResult(r3);
    L12:
        int r82 = r1.decodeElementIndex(getDescriptor());
        if (r82 == (-1)) goto L15;
        readElement$default(this, r1, r93 + r82, r3, false, 8, null);
    L7:
        r92 = builder();
        goto L6
    }

    public abstract void readAll(CompositeDecoder r1, Builder r2, int r3, int r4);

    public abstract void readElement(CompositeDecoder r1, int r2, Builder r3, boolean r4);

    public abstract Builder toBuilder(Collection r1);

    public abstract Collection toResult(Builder r1);
}
