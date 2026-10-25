package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0017¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\fJ\u001f\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH&¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH&¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH&¢\u0006\u0004\b\u0015\u0010\u0016JE\u0010\u001b\u001a\u00028\u0000\"\n\b\u0000\u0010\u0017*\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0004\b\u001b\u0010\u001cJG\u0010\u001d\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0017*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\n2\u000e\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00018\u0000H'¢\u0006\u0004\b\u001d\u0010\u001c¨\u0006\u001fÀ\u0006\u0003"}, d2 = {"Lkotlinx/serialization/encoding/CompositeDecoder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", _UrlKt.FRAGMENT_ENCODE_SET, "endStructure", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)V", _UrlKt.FRAGMENT_ENCODE_SET, "decodeSequentially", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "decodeElementIndex", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)I", "decodeCollectionSize", "index", "decodeIntElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)I", _UrlKt.FRAGMENT_ENCODE_SET, "decodeLongElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)J", _UrlKt.FRAGMENT_ENCODE_SET, "decodeStringElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/String;", "T", "Lkotlinx/serialization/DeserializationStrategy;", "deserializer", "previousValue", "decodeSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeNullableSerializableElement", "Companion", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface CompositeDecoder {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/serialization/encoding/CompositeDecoder$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    static /* synthetic */ Object decodeSerializableElement$default(CompositeDecoder r0, SerialDescriptor r1, int r2, DeserializationStrategy r3, Object r4, int r5, Object r6) {
        if (r6 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: decodeSerializableElement");
        return null;
    L4:
        if ((r5 & 8) == 0) goto L7;
        r4 = null;
    L7:
        return r0.decodeSerializableElement(r1, r2, r3, r4);
    }

    default int decodeCollectionSize(SerialDescriptor r1) {
        return -1;
    }

    int decodeElementIndex(SerialDescriptor r1);

    int decodeIntElement(SerialDescriptor r1, int r2);

    long decodeLongElement(SerialDescriptor r1, int r2);

    <T> T decodeNullableSerializableElement(SerialDescriptor r1, int r2, DeserializationStrategy<? extends T> r3, T r4);

    default boolean decodeSequentially() {
        return false;
    }

    <T> T decodeSerializableElement(SerialDescriptor r1, int r2, DeserializationStrategy<? extends T> r3, T r4);

    String decodeStringElement(SerialDescriptor r1, int r2);

    void endStructure(SerialDescriptor r1);
}
