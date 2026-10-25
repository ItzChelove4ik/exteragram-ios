package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
class MapFieldSchemaLite implements MapFieldSchema {
    public MapFieldSchemaLite() {
    }

    private static <K, V> int getSerializedSizeLite(int r3, Object r4, Object r5) {
        MapFieldLite r42 = (MapFieldLite) r4;
        MapEntryLite r52 = (MapEntryLite) r5;
        int r1 = 0;
        if (r42.isEmpty() == false) goto L5;
        return 0;
    L5:
        Iterator<Map.Entry<K, V>> r43 = r42.entrySet().iterator();
    L7:
        if (r43.hasNext() == false) goto L9;
        Map.Entry<K, V> r0 = r43.next();
        r1 = r1 + r52.computeMessageSize(r3, r0.getKey(), r0.getValue());
        goto L7
    L9:
        return r1;
    }

    private static <K, V> MapFieldLite<K, V> mergeFromLite(Object r1, Object r2) {
        MapFieldLite<K, V> r12 = (MapFieldLite) r1;
        MapFieldLite<K, V> r22 = (MapFieldLite) r2;
        if (r22.isEmpty() == false) goto L5;
    L8:
        return r12;
    L5:
        if (r12.isMutable() == true) goto L7;
        r12 = r12.mutableCopy();
    L7:
        r12.mergeFrom(r22);
        goto L8
    }

    @Override // com.google.protobuf.MapFieldSchema
    public Map<?, ?> forMapData(Object r1) {
        return (MapFieldLite) r1;
    }

    @Override // com.google.protobuf.MapFieldSchema
    public MapEntryLite.Metadata<?, ?> forMapMetadata(Object r1) {
        return ((MapEntryLite) r1).getMetadata();
    }

    @Override // com.google.protobuf.MapFieldSchema
    public Map<?, ?> forMutableMapData(Object r1) {
        return (MapFieldLite) r1;
    }

    @Override // com.google.protobuf.MapFieldSchema
    public int getSerializedSize(int r1, Object r2, Object r3) {
        return getSerializedSizeLite(r1, r2, r3);
    }

    @Override // com.google.protobuf.MapFieldSchema
    public boolean isImmutable(Object r1) {
        return !((MapFieldLite) r1).isMutable();
    }

    @Override // com.google.protobuf.MapFieldSchema
    public Object mergeFrom(Object r1, Object r2) {
        return mergeFromLite(r1, r2);
    }

    @Override // com.google.protobuf.MapFieldSchema
    public Object newMapField(Object r1) {
        return MapFieldLite.emptyMapField().mutableCopy();
    }

    @Override // com.google.protobuf.MapFieldSchema
    public Object toImmutable(Object r1) {
        ((MapFieldLite) r1).makeImmutable();
        return r1;
    }
}
