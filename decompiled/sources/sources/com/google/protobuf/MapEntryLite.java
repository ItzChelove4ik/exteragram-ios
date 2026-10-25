package com.google.protobuf;

import com.google.protobuf.WireFormat;

/* loaded from: classes5.dex */
public class MapEntryLite<K, V> {
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    public static class Metadata<K, V> {
        public final K defaultKey;
        public final V defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType r1, K r2, WireFormat.FieldType r3, V r4) {
            this.keyType = r1;
            this.defaultKey = r2;
            this.valueType = r3;
            this.defaultValue = r4;
        }
    }

    private MapEntryLite(WireFormat.FieldType r2, K r3, WireFormat.FieldType r4, V r5) {
        this.metadata = new Metadata(r2, r3, r4, r5);
        this.key = r3;
        this.value = r5;
    }

    public static <K, V> int computeSerializedSize(Metadata<K, V> r2, K r3, V r4) {
        return FieldSet.computeElementSize(r2.keyType, 1, r3) + FieldSet.computeElementSize(r2.valueType, 2, r4);
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(WireFormat.FieldType r1, K r2, WireFormat.FieldType r3, V r4) {
        return new MapEntryLite(r1, r2, r3, r4);
    }

    public static <K, V> void writeTo(CodedOutputStream r2, Metadata<K, V> r3, K r4, V r5) {
        FieldSet.writeElement(r2, r3.keyType, 1, r4);
        FieldSet.writeElement(r2, r3.valueType, 2, r5);
    }

    public int computeMessageSize(int r1, K r2, V r3) {
        return CodedOutputStream.computeTagSize(r1) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSerializedSize(this.metadata, r2, r3));
    }

    public Metadata<K, V> getMetadata() {
        return this.metadata;
    }
}
