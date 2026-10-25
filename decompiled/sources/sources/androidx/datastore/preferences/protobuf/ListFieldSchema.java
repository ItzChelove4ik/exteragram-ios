package androidx.datastore.preferences.protobuf;

import java.util.List;

/* loaded from: classes.dex */
interface ListFieldSchema {
    void makeImmutableListAt(Object r1, long r2);

    <L> void mergeListsAt(Object r1, Object r2, long r3);

    <L> List<L> mutableListAt(Object r1, long r2);
}
