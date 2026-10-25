package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.MapEntryLite;
import java.util.Map;

/* loaded from: classes.dex */
interface MapFieldSchema {
    Map<?, ?> forMapData(Object r1);

    MapEntryLite.Metadata<?, ?> forMapMetadata(Object r1);

    Map<?, ?> forMutableMapData(Object r1);

    int getSerializedSize(int r1, Object r2, Object r3);

    boolean isImmutable(Object r1);

    Object mergeFrom(Object r1, Object r2);

    Object newMapField(Object r1);

    Object toImmutable(Object r1);
}
