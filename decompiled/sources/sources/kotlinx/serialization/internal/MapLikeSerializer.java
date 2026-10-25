package kotlinx.serialization.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0014\b\u0003\u0010\u0005*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006B%\b\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0004\b\u000b\u0010\fJ/\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00028\u00032\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0004¢\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u00032\u0006\u0010\u0018\u001a\u00020\u0017H\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00028\u0002H\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b8\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u0010 \u001a\u0004\b#\u0010\"R\u0014\u0010'\u001a\u00020$8&X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0001\u0001(¨\u0006)"}, d2 = {"Lkotlinx/serialization/internal/MapLikeSerializer;", "Key", "Value", "Collection", _UrlKt.FRAGMENT_ENCODE_SET, "Builder", "Lkotlinx/serialization/internal/AbstractCollectionSerializer;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/KSerializer;", "keySerializer", "valueSerializer", "<init>", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "Lkotlinx/serialization/encoding/CompositeDecoder;", "decoder", "builder", _UrlKt.FRAGMENT_ENCODE_SET, "startIndex", "size", _UrlKt.FRAGMENT_ENCODE_SET, "readAll", "(Lkotlinx/serialization/encoding/CompositeDecoder;Ljava/util/Map;II)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "checkIndex", "readElement", "(Lkotlinx/serialization/encoding/CompositeDecoder;ILjava/util/Map;Z)V", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "value", "serialize", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Object;)V", "Lkotlinx/serialization/KSerializer;", "getKeySerializer", "()Lkotlinx/serialization/KSerializer;", "getValueSerializer", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "Lkotlinx/serialization/internal/LinkedHashMapSerializer;", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCollectionSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/MapLikeSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 4 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,283:1\n1#2:284\n489#3,2:285\n491#3,2:289\n32#4,2:287\n*S KotlinDebug\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/MapLikeSerializer\n*L\n118#1:285,2\n118#1:289,2\n121#1:287,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class MapLikeSerializer<Key, Value, Collection, Builder extends Map<Key, Value>> extends AbstractCollectionSerializer<Map.Entry<? extends Key, ? extends Value>, Collection, Builder> {
    private final KSerializer<Key> keySerializer;
    private final KSerializer<Value> valueSerializer;

    private MapLikeSerializer(KSerializer<Key> r2, KSerializer<Value> r3) {
        super(null);
        this.keySerializer = r2;
        this.valueSerializer = r3;
    }

    public /* synthetic */ MapLikeSerializer(KSerializer r1, KSerializer r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public abstract SerialDescriptor getDescriptor();

    public final KSerializer<Key> getKeySerializer() {
        return this.keySerializer;
    }

    public final KSerializer<Value> getValueSerializer() {
        return this.valueSerializer;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ void readAll(CompositeDecoder r1, Object r2, int r3, int r4) {
        readAll(r1, (Map) r2, r3, r4);
    }

    public final void readAll(CompositeDecoder r5, Builder r6, int r7, int r8) {
        if (r8 < 0) goto L12;
        IntProgression r82 = RangesKt.step(RangesKt.until(0, r8 * 2), 2);
        int r0 = r82.getFirst();
        int r2 = r82.getLast();
        int r83 = r82.getStep();
        if (r83 <= 0) goto L6;
        if (r0 > r2) goto L6;
    L8:
        readElement(r5, r7 + r0, r6, false);
        if (r0 == r2) goto L17;
        r0 = r0 + r83;
        goto L8
    L17:
        return;
    L6:
        if (r83 >= 0) goto L11;
        if (r2 <= r0) goto L8;
        return;
    L11:
        return;
    L12:
        f$$ExternalSyntheticBUOutline1.m("Size must be known in advance when using READ_ALL");
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ void readElement(CompositeDecoder r1, int r2, Object r3, boolean r4) {
        readElement(r1, r2, (Map) r3, r4);
    }

    public final void readElement(CompositeDecoder r8, int r9, Builder r10, boolean r11) {
        Object r82 = CompositeDecoder.decodeSerializableElement$default(r8, getDescriptor(), r9, this.keySerializer, null, 8, null);
        if (r11 == false) goto L9;
        int r92 = r8.decodeElementIndex(getDescriptor());
        if (r92 != (r9 + 1)) goto L7;
    L6:
        int r2 = r92;
        if (r10.containsKey(r82) == true) goto L13;
    L15:
        Object r7 = CompositeDecoder.decodeSerializableElement$default(r8, getDescriptor(), r2, this.valueSerializer, null, 8, null);
    L16:
        r10.put(r82, r7);
        return;
    L13:
        if ((this.valueSerializer.getDescriptor().getKind() instanceof PrimitiveKind) == true) goto L15;
        r7 = r8.decodeSerializableElement(getDescriptor(), r2, this.valueSerializer, MapsKt.getValue(r10, r82));
        goto L16
    L7:
        Utf8$$ExternalSyntheticBUOutline0.m("Value must follow key in a map, index for key: ", r9, ", returned index for value: ", r92);
        return;
    L9:
        r92 = r9 + 1;
        goto L6
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder r8, Collection r9) {
        int r0 = collectionSize(r9);
        SerialDescriptor r1 = getDescriptor();
        CompositeEncoder r82 = r8.beginCollection(r1, r0);
        Iterator<Map.Entry<? extends Key, ? extends Value>> r92 = collectionIterator(r9);
        int r02 = 0;
    L4:
        if (r92.hasNext() == false) goto L6;
        Map.Entry<? extends Key, ? extends Value> r2 = r92.next();
        Key r3 = r2.getKey();
        Value r22 = r2.getValue();
        int r5 = r02 + 1;
        r82.encodeSerializableElement(getDescriptor(), r02, getKeySerializer(), r3);
        r02 = r02 + 2;
        r82.encodeSerializableElement(getDescriptor(), r5, getValueSerializer(), r22);
        goto L4
    L6:
        r82.endStructure(r1);
    }
}
