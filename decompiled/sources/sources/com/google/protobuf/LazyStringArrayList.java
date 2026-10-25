package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class LazyStringArrayList extends AbstractProtobufList<String> implements LazyStringList, RandomAccess {

    @Deprecated
    public static final LazyStringList EMPTY = null;
    private static final LazyStringArrayList EMPTY_LIST = null;
    private final List<Object> list;

    static {
        LazyStringArrayList r0 = new LazyStringArrayList();
        EMPTY_LIST = r0;
        r0.makeImmutable();
        EMPTY = r0;
    }

    public LazyStringArrayList() {
        this(10);
    }

    public LazyStringArrayList(int r2) {
        this(new ArrayList(r2));
    }

    private LazyStringArrayList(ArrayList<Object> r1) {
        this.list = r1;
    }

    private static String asString(Object r1) {
        if ((r1 instanceof String) == false) goto L7;
        return (String) r1;
    L7:
        if ((r1 instanceof ByteString) == false) goto L11;
        return ((ByteString) r1).toStringUtf8();
    L11:
        return Internal.toStringUtf8((byte[]) r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ void add(int r1, Object r2) {
        add(r1, (String) r2);
    }

    public void add(int r2, String r3) {
        ensureIsMutable();
        this.list.add(r2, r3);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.LazyStringList
    public void add(ByteString r2) {
        ensureIsMutable();
        this.list.add(r2);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean add(Object r1) {
        return super.add(r1);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public boolean addAll(int r2, Collection<? extends String> r3) {
        ensureIsMutable();
        if ((r3 instanceof LazyStringList) == false) goto L5;
        r3 = ((LazyStringList) r3).getUnderlyingElements();
    L5:
        boolean r22 = this.list.addAll(r2, r3);
        ((AbstractList) this).modCount++;
        return r22;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends String> r2) {
        return addAll(size(), r2);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        ensureIsMutable();
        this.list.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean equals(Object r1) {
        return super.equals(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int r3) {
        Object r0 = this.list.get(r3);
        if ((r0 instanceof String) == false) goto L7;
        return (String) r0;
    L7:
        if ((r0 instanceof ByteString) == false) goto L12;
        ByteString r02 = (ByteString) r0;
        String r1 = r02.toStringUtf8();
        if (r02.isValidUtf8() == false) goto L11;
        this.list.set(r3, r1);
    L11:
        return r1;
    L12:
        byte[] r03 = (byte[]) r0;
        String r12 = Internal.toStringUtf8(r03);
        if (Internal.isValidUtf8(r03) == false) goto L15;
        this.list.set(r3, r12);
    L15:
        return r12;
    }

    @Override // com.google.protobuf.LazyStringList
    public Object getRaw(int r1) {
        return this.list.get(r1);
    }

    @Override // com.google.protobuf.LazyStringList
    public List<?> getUnderlyingElements() {
        return Collections.unmodifiableList(this.list);
    }

    @Override // com.google.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        if (isModifiable() == true) goto L5;
        return this;
    L5:
        return new UnmodifiableLazyStringList(this);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.protobuf.AbstractProtobufList, com.google.protobuf.Internal.ProtobufList
    public /* bridge */ /* synthetic */ boolean isModifiable() {
        return super.isModifiable();
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public /* bridge */ /* synthetic */ Internal.ProtobufList mutableCopyWithCapacity(int r1) {
        return mutableCopyWithCapacity(r1);
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public LazyStringArrayList mutableCopyWithCapacity(int r2) {
        if (r2 < size()) goto L6;
        ArrayList r0 = new ArrayList(r2);
        r0.addAll(this.list);
        return new LazyStringArrayList(r0);
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object remove(int r1) {
        return remove(r1);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public String remove(int r2) {
        ensureIsMutable();
        Object r22 = this.list.remove(r2);
        ((AbstractList) this).modCount++;
        return asString(r22);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean remove(Object r1) {
        return super.remove(r1);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean removeAll(Collection r1) {
        return super.removeAll(r1);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean retainAll(Collection r1) {
        return super.retainAll(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object set(int r1, Object r2) {
        return set(r1, (String) r2);
    }

    public String set(int r1, String r2) {
        ensureIsMutable();
        return asString(this.list.set(r1, r2));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.list.size();
    }
}
