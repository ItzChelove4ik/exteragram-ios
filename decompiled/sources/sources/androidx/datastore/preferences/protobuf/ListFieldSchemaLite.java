package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.Internal;
import java.util.List;

/* loaded from: classes.dex */
final class ListFieldSchemaLite implements ListFieldSchema {
    public ListFieldSchemaLite() {
    }

    public static <E> Internal.ProtobufList<E> getProtobufList(Object r0, long r1) {
        return (Internal.ProtobufList) UnsafeUtil.getObject(r0, r1);
    }

    @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
    public void makeImmutableListAt(Object r1, long r2) {
        getProtobufList(r1, r2).makeImmutable();
    }

    @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
    public <E> void mergeListsAt(Object r4, Object r5, long r6) {
        Internal.ProtobufList r3 = getProtobufList(r4, r6);
        Internal.ProtobufList r52 = getProtobufList(r5, r6);
        int r0 = r3.size();
        int r1 = r52.size();
        if (r0 <= 0) goto L9;
        if (r1 <= 0) goto L9;
        if (r3.isModifiable() == true) goto L8;
        r3 = r3.mutableCopyWithCapacity(r1 + r0);
    L8:
        r3.addAll(r52);
    L9:
        if (r0 <= 0) goto L11;
        r52 = r3;
    L11:
        UnsafeUtil.putObject(r4, r6, r52);
    }

    @Override // androidx.datastore.preferences.protobuf.ListFieldSchema
    public <L> List<L> mutableListAt(Object r2, long r3) {
        Internal.ProtobufList r1 = getProtobufList(r2, r3);
        if (r1.isModifiable() == true) goto L10;
        int r0 = r1.size();
        if (r0 != 0) goto L7;
        int r02 = 10;
    L8:
        Internal.ProtobufList r12 = r1.mutableCopyWithCapacity(r02);
        UnsafeUtil.putObject(r2, r3, r12);
        return r12;
    L7:
        r02 = r0 * 2;
        goto L8
    L10:
        return r1;
    }
}
