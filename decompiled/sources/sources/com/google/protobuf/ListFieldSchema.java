package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
abstract class ListFieldSchema {
    private static final ListFieldSchema FULL_INSTANCE = null;
    private static final ListFieldSchema LITE_INSTANCE = null;

    /* renamed from: com.google.protobuf.ListFieldSchema$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ListFieldSchemaFull extends ListFieldSchema {
        private static final Class<?> UNMODIFIABLE_LIST_CLASS = null;

        static {
            UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();
        }

        private ListFieldSchemaFull() {
            super(null);
        }

        public /* synthetic */ ListFieldSchemaFull(AnonymousClass1 r1) {
            this();
        }

        public static <E> List<E> getList(Object r0, long r1) {
            return (List) UnsafeUtil.getObject(r0, r1);
        }

        private static <L> List<L> mutableListAt(Object r3, long r4, int r6) {
            List<L> r0 = getList(r3, r4);
            if (r0.isEmpty() == false) goto L16;
            if ((r0 instanceof LazyStringList) == false) goto L8;
            List<L> r02 = new LazyStringArrayList(r6);
        L13:
            UnsafeUtil.putObject(r3, r4, r02);
            return r02;
        L8:
            if ((r0 instanceof PrimitiveNonBoxingCollection) == true) goto L10;
        L12:
            r02 = new ArrayList(r6);
            goto L13
        L10:
            if ((r0 instanceof Internal.ProtobufList) == false) goto L12;
            r02 = ((Internal.ProtobufList) r0).mutableCopyWithCapacity(r6);
            goto L13
        L16:
            if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(r0.getClass()) == false) goto L20;
            ArrayList r1 = new ArrayList(r0.size() + r6);
            r1.addAll(r0);
            UnsafeUtil.putObject(r3, r4, r1);
            return r1;
        L20:
            if ((r0 instanceof UnmodifiableLazyStringList) == false) goto L24;
            LazyStringArrayList r12 = new LazyStringArrayList(r0.size() + r6);
            r12.addAll((UnmodifiableLazyStringList) r0);
            UnsafeUtil.putObject(r3, r4, r12);
            return r12;
        L24:
            if ((r0 instanceof PrimitiveNonBoxingCollection) == true) goto L26;
        L31:
            return r0;
        L26:
            if ((r0 instanceof Internal.ProtobufList) == false) goto L31;
            Internal.ProtobufList r13 = (Internal.ProtobufList) r0;
            if (r13.isModifiable() == true) goto L31;
            Internal.ProtobufList r62 = r13.mutableCopyWithCapacity(r0.size() + r6);
            UnsafeUtil.putObject(r3, r4, r62);
            return r62;
        }

        @Override // com.google.protobuf.ListFieldSchema
        public void makeImmutableListAt(Object r3, long r4) {
            List r2 = (List) UnsafeUtil.getObject(r3, r4);
            if ((r2 instanceof LazyStringList) == false) goto L6;
            Object r22 = ((LazyStringList) r2).getUnmodifiableView();
        L17:
            UnsafeUtil.putObject(r3, r4, r22);
            return;
        L6:
            if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(r2.getClass()) == false) goto L9;
            return;
        L9:
            if ((r2 instanceof PrimitiveNonBoxingCollection) == true) goto L11;
        L16:
            r22 = Collections.unmodifiableList(r2);
            goto L17
        L11:
            if ((r2 instanceof Internal.ProtobufList) == false) goto L16;
            Internal.ProtobufList r23 = (Internal.ProtobufList) r2;
            if (r23.isModifiable() == false) goto L19;
            r23.makeImmutable();
            return;
        }

        @Override // com.google.protobuf.ListFieldSchema
        public <E> void mergeListsAt(Object r3, Object r4, long r5) {
            List r2 = getList(r4, r5);
            List r42 = mutableListAt(r3, r5, r2.size());
            int r0 = r42.size();
            int r1 = r2.size();
            if (r0 <= 0) goto L6;
            if (r1 <= 0) goto L6;
            r42.addAll(r2);
        L6:
            if (r0 <= 0) goto L8;
            r2 = r42;
        L8:
            UnsafeUtil.putObject(r3, r5, r2);
        }

        @Override // com.google.protobuf.ListFieldSchema
        public <L> List<L> mutableListAt(Object r1, long r2) {
            return mutableListAt(r1, r2, 10);
        }
    }

    public static final class ListFieldSchemaLite extends ListFieldSchema {
        private ListFieldSchemaLite() {
            super(null);
        }

        public /* synthetic */ ListFieldSchemaLite(AnonymousClass1 r1) {
            this();
        }

        public static <E> Internal.ProtobufList<E> getProtobufList(Object r0, long r1) {
            return (Internal.ProtobufList) UnsafeUtil.getObject(r0, r1);
        }

        @Override // com.google.protobuf.ListFieldSchema
        public void makeImmutableListAt(Object r1, long r2) {
            getProtobufList(r1, r2).makeImmutable();
        }

        @Override // com.google.protobuf.ListFieldSchema
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

        @Override // com.google.protobuf.ListFieldSchema
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

    static {
        FULL_INSTANCE = new ListFieldSchemaFull(null);
        LITE_INSTANCE = new ListFieldSchemaLite(null);
    }

    private ListFieldSchema() {
    }

    public /* synthetic */ ListFieldSchema(AnonymousClass1 r1) {
        this();
    }

    public static ListFieldSchema full() {
        return FULL_INSTANCE;
    }

    public static ListFieldSchema lite() {
        return LITE_INSTANCE;
    }

    public abstract void makeImmutableListAt(Object r1, long r2);

    public abstract <L> void mergeListsAt(Object r1, Object r2, long r3);

    public abstract <L> List<L> mutableListAt(Object r1, long r2);
}
