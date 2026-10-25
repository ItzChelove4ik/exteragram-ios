package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH&¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0010H&¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0013H&¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H&¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ1\u0010!\u001a\u00020\u0002\"\n\b\u0000\u0010\u001e*\u0004\u0018\u00010\u00012\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0004\b!\u0010\"J1\u0010#\u001a\u00020\u0002\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000H\u0017¢\u0006\u0004\b#\u0010\"¨\u0006$À\u0006\u0003"}, d2 = {"Lkotlinx/serialization/encoding/Encoder;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "encodeNotNullMark", "()V", "encodeNull", _UrlKt.FRAGMENT_ENCODE_SET, "value", "encodeBoolean", "(Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeInt", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeLong", "(J)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeDouble", "(D)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeString", "(Ljava/lang/String;)V", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "Lkotlinx/serialization/encoding/CompositeEncoder;", "beginStructure", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/CompositeEncoder;", "collectionSize", "beginCollection", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Lkotlinx/serialization/encoding/CompositeEncoder;", "T", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "encodeSerializableValue", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeNullableSerializableValue", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface Encoder {
    default CompositeEncoder beginCollection(SerialDescriptor r1, int r2) {
        return beginStructure(r1);
    }

    CompositeEncoder beginStructure(SerialDescriptor r1);

    void encodeBoolean(boolean r1);

    void encodeDouble(double r1);

    void encodeInt(int r1);

    void encodeLong(long r1);

    default void encodeNotNullMark() {
    }

    void encodeNull();

    default <T> void encodeNullableSerializableValue(SerializationStrategy<? super T> r2, T r3) {
        if (r2.getDescriptor().isNullable() == false) goto L6;
        encodeSerializableValue(r2, r3);
        return;
    L6:
        if (r3 != null) goto L9;
        encodeNull();
        return;
    L9:
        encodeNotNullMark();
        encodeSerializableValue(r2, r3);
    }

    <T> void encodeSerializableValue(SerializationStrategy<? super T> r1, T r2);

    void encodeString(String r1);
}
