package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J%\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0010¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0013¢\u0006\u0004\b\u001a\u0010\u001bJA\u0010 \u001a\u00020\r\"\n\b\u0000\u0010\u001d*\u0004\u0018\u00010\u001c2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b \u0010!JA\u0010\"\u001a\u00020\r\"\b\b\u0000\u0010\u001d*\u00020\u001c2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\b\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b\"\u0010!¨\u0006#"}, d2 = {"Lkotlinx/serialization/encoding/AbstractEncoder;", "Lkotlinx/serialization/encoding/Encoder;", "Lkotlinx/serialization/encoding/CompositeEncoder;", "<init>", "()V", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "encodeElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z", "value", _UrlKt.FRAGMENT_ENCODE_SET, "encodeInt", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeLong", "(J)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeString", "(Ljava/lang/String;)V", "encodeIntElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;II)V", "encodeLongElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;IJ)V", "encodeStringElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILjava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "encodeSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeNullableSerializableElement", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractEncoder implements Encoder, CompositeEncoder {
    public AbstractEncoder() {
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* bridge */ CompositeEncoder beginCollection(SerialDescriptor r1, int r2) {
        return super.beginCollection(r1, r2);
    }

    public abstract boolean encodeElement(SerialDescriptor r1, int r2);

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeInt(int r1);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeIntElement(SerialDescriptor r1, int r2, int r3) {
        if (encodeElement(r1, r2) == false) goto L6;
        encodeInt(r3);
        return;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeLong(long r1);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeLongElement(SerialDescriptor r1, int r2, long r3) {
        if (encodeElement(r1, r2) == false) goto L6;
        encodeLong(r3);
        return;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* bridge */ void encodeNotNullMark() {
        super.encodeNotNullMark();
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(SerialDescriptor r1, int r2, SerializationStrategy<? super T> r3, T r4) {
        if (encodeElement(r1, r2) == false) goto L6;
        encodeNullableSerializableValue(r3, r4);
        return;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* bridge */ <T> void encodeNullableSerializableValue(SerializationStrategy<? super T> r1, T r2) {
        super.encodeNullableSerializableValue(r1, r2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeSerializableElement(SerialDescriptor r1, int r2, SerializationStrategy<? super T> r3, T r4) {
        if (encodeElement(r1, r2) == false) goto L6;
        encodeSerializableValue(r3, r4);
        return;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract /* bridge */ <T> void encodeSerializableValue(SerializationStrategy<? super T> r1, T r2);

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeString(String r1);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeStringElement(SerialDescriptor r1, int r2, String r3) {
        if (encodeElement(r1, r2) == false) goto L6;
        encodeString(r3);
        return;
    }
}
