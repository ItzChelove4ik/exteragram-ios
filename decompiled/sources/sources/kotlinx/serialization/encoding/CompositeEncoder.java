package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0017¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H&¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000fH&¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0012H&¢\u0006\u0004\b\u0013\u0010\u0014JA\u0010\u0018\u001a\u00020\u0004\"\n\b\u0000\u0010\u0015*\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\f\u001a\u00028\u0000H&¢\u0006\u0004\b\u0018\u0010\u0019JA\u0010\u001a\u001a\u00020\u0004\"\b\b\u0000\u0010\u0015*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\b\u0010\f\u001a\u0004\u0018\u00018\u0000H'¢\u0006\u0004\b\u001a\u0010\u0019¨\u0006\u001bÀ\u0006\u0003"}, d2 = {"Lkotlinx/serialization/encoding/CompositeEncoder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", _UrlKt.FRAGMENT_ENCODE_SET, "endStructure", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)V", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "shouldEncodeElementDefault", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z", "value", "encodeIntElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;II)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeLongElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;IJ)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeStringElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILjava/lang/String;)V", "T", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "encodeSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeNullableSerializableElement", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface CompositeEncoder {
    void encodeIntElement(SerialDescriptor r1, int r2, int r3);

    void encodeLongElement(SerialDescriptor r1, int r2, long r3);

    <T> void encodeNullableSerializableElement(SerialDescriptor r1, int r2, SerializationStrategy<? super T> r3, T r4);

    <T> void encodeSerializableElement(SerialDescriptor r1, int r2, SerializationStrategy<? super T> r3, T r4);

    void encodeStringElement(SerialDescriptor r1, int r2, String r3);

    void endStructure(SerialDescriptor r1);

    boolean shouldEncodeElementDefault(SerialDescriptor r1, int r2);
}
