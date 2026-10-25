package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class Lists {

    /* loaded from: classes5.dex */
    public static final class RandomAccessReverseList<T> extends ReverseList<T> implements RandomAccess {
        public RandomAccessReverseList(List<T> r1) {
            super(r1);
        }
    }

    /* loaded from: classes5.dex */
    public static class ReverseList<T> extends AbstractList<T> {
        private final List<T> forwardList;

        /* renamed from: com.google.common.collect.Lists$ReverseList$1, reason: invalid class name */
        public class AnonymousClass1 implements ListIterator<T> {
            boolean canRemoveOrSet;
            final /* synthetic */ ReverseList this$0;
            final /* synthetic */ ListIterator val$forwardIterator;

            public AnonymousClass1(ReverseList r1, ListIterator r2) {
                this.val$forwardIterator = r2;
                this.this$0 = r1;
            }

            @Override // java.util.ListIterator
            public void add(T r2) {
                this.val$forwardIterator.add(r2);
                this.val$forwardIterator.previous();
                this.canRemoveOrSet = false;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return this.val$forwardIterator.hasPrevious();
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return this.val$forwardIterator.hasNext();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public T next() {
                if (hasNext() == false) goto L6;
                this.canRemoveOrSet = true;
                return (T) this.val$forwardIterator.previous();
            L6:
                Utils$$ExternalSyntheticBUOutline0.m();
                return null;
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return ReverseList.access$000(this.this$0, this.val$forwardIterator.nextIndex());
            }

            @Override // java.util.ListIterator
            public T previous() {
                if (hasPrevious() == false) goto L6;
                this.canRemoveOrSet = true;
                return (T) this.val$forwardIterator.next();
            L6:
                Utils$$ExternalSyntheticBUOutline0.m();
                return null;
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return nextIndex() - 1;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.canRemoveOrSet);
                this.val$forwardIterator.remove();
                this.canRemoveOrSet = false;
            }

            @Override // java.util.ListIterator
            public void set(T r2) {
                Preconditions.checkState(this.canRemoveOrSet);
                this.val$forwardIterator.set(r2);
            }
        }

        public ReverseList(List<T> r1) {
            this.forwardList = (List) Preconditions.checkNotNull(r1);
        }

        public static /* synthetic */ int access$000(ReverseList r0, int r1) {
            return r0.reversePosition(r1);
        }

        private int reverseIndex(int r1) {
            int r0 = size();
            Preconditions.checkElementIndex(r1, r0);
            return (r0 - 1) - r1;
        }

        private int reversePosition(int r1) {
            int r0 = size();
            Preconditions.checkPositionIndex(r1, r0);
            return r0 - r1;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int r2, T r3) {
            this.forwardList.add(reversePosition(r2), r3);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.forwardList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int r2) {
            return this.forwardList.get(reverseIndex(r2));
        }

        public List<T> getForwardList() {
            return this.forwardList;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int r2) {
            int r22 = reversePosition(r2);
            return new AnonymousClass1(this, this.forwardList.listIterator(r22));
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int r2) {
            return this.forwardList.remove(reverseIndex(r2));
        }

        @Override // java.util.AbstractList
        public void removeRange(int r1, int r2) {
            subList(r1, r2).clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T set(int r2, T r3) {
            return this.forwardList.set(reverseIndex(r2), r3);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> subList(int r2, int r3) {
            Preconditions.checkPositionIndexes(r2, r3, size());
            return Lists.reverse(this.forwardList.subList(reversePosition(r3), reversePosition(r2)));
        }
    }

    /* loaded from: classes5.dex */
    public static final class TransformingRandomAccessList<F, T> extends AbstractList<T> implements RandomAccess, Serializable {
        final List<F> fromList;
        final Function<? super F, ? extends T> function;

        /* renamed from: com.google.common.collect.Lists$TransformingRandomAccessList$1, reason: invalid class name */
        public class AnonymousClass1 extends TransformedListIterator<F, T> {
            final /* synthetic */ TransformingRandomAccessList this$0;

            public AnonymousClass1(TransformingRandomAccessList r1, ListIterator r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // com.google.common.collect.TransformedIterator
            public T transform(F r1) {
                return this.this$0.function.apply(r1);
            }
        }

        public TransformingRandomAccessList(List<F> r1, Function<? super F, ? extends T> r2) {
            this.fromList = (List) Preconditions.checkNotNull(r1);
            this.function = (Function) Preconditions.checkNotNull(r2);
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int r2) {
            return this.function.apply(this.fromList.get(r2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.fromList.isEmpty();
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int r3) {
            return new AnonymousClass1(this, this.fromList.listIterator(r3));
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int r2) {
            return this.function.apply(this.fromList.remove(r2));
        }

        @Override // java.util.AbstractList
        public void removeRange(int r1, int r2) {
            this.fromList.subList(r1, r2).clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    /* loaded from: classes5.dex */
    public static final class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
        final List<F> fromList;
        final Function<? super F, ? extends T> function;

        /* renamed from: com.google.common.collect.Lists$TransformingSequentialList$1, reason: invalid class name */
        public class AnonymousClass1 extends TransformedListIterator<F, T> {
            final /* synthetic */ TransformingSequentialList this$0;

            public AnonymousClass1(TransformingSequentialList r1, ListIterator r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // com.google.common.collect.TransformedIterator
            public T transform(F r1) {
                return this.this$0.function.apply(r1);
            }
        }

        public TransformingSequentialList(List<F> r1, Function<? super F, ? extends T> r2) {
            this.fromList = (List) Preconditions.checkNotNull(r1);
            this.function = (Function) Preconditions.checkNotNull(r2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.fromList.isEmpty();
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int r3) {
            return new AnonymousClass1(this, this.fromList.listIterator(r3));
        }

        @Override // java.util.AbstractList
        public void removeRange(int r1, int r2) {
            this.fromList.subList(r1, r2).clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    public static int computeArrayListCapacity(int r4) {
        CollectPreconditions.checkNonnegative(r4, "arraySize");
        return Ints.saturatedCast((r4 + 5) + (r4 / 10));
    }

    public static boolean equalsImpl(List<?> r6, Object r7) {
        if (r7 != Preconditions.checkNotNull(r6)) goto L6;
        return true;
    L6:
        if ((r7 instanceof List) == true) goto L8;
        return false;
    L8:
        List r72 = (List) r7;
        int r0 = r6.size();
        if (r0 == r72.size()) goto L12;
        return false;
    L12:
        if ((r6 instanceof RandomAccess) == false) goto L23;
        if ((r72 instanceof RandomAccess) == false) goto L23;
        int r3 = 0;
    L16:
        if (r3 >= r0) goto L21;
        if (Objects.equals(r6.get(r3), r72.get(r3)) == false) goto L19;
        r3 = r3 + 1;
        goto L16
    L19:
        return false;
    L21:
        return true;
    L23:
        return Iterators.elementsEqual(r6.iterator(), r72.iterator());
    }

    public static int indexOfImpl(List<?> r1, Object r2) {
        if ((r1 instanceof RandomAccess) == true) goto L5;
        ListIterator<?> r12 = r1.listIterator();
    L8:
        if (r12.hasNext() == false) goto L13;
        if (Objects.equals(r2, r12.next()) == false) goto L8;
        return r12.previousIndex();
    L13:
        return -1;
    L5:
        return indexOfRandomAccess(r1, r2);
    }

    private static int indexOfRandomAccess(List<?> r3, Object r4) {
        int r0 = r3.size();
        int r1 = 0;
        if (r4 != null) goto L9;
    L4:
        if (r1 >= r0) goto L14;
        if (r3.get(r1) == null) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r1;
    L14:
        return -1;
    L9:
        if (r1 >= r0) goto L20;
        if (r4.equals(r3.get(r1)) == true) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return r1;
    L20:
        return -1;
    }

    public static int lastIndexOfImpl(List<?> r1, Object r2) {
        if ((r1 instanceof RandomAccess) == true) goto L5;
        ListIterator<?> r12 = r1.listIterator(r1.size());
    L8:
        if (r12.hasPrevious() == false) goto L13;
        if (Objects.equals(r2, r12.previous()) == false) goto L8;
        return r12.nextIndex();
    L13:
        return -1;
    L5:
        return lastIndexOfRandomAccess(r1, r2);
    }

    private static int lastIndexOfRandomAccess(List<?> r2, Object r3) {
        if (r3 != null) goto L9;
        int r32 = r2.size() - 1;
    L4:
        if (r32 < 0) goto L15;
        if (r2.get(r32) == null) goto L7;
        r32 = r32 - 1;
        goto L4
    L7:
        return r32;
    L15:
        return -1;
    L9:
        int r0 = r2.size() - 1;
    L10:
        if (r0 < 0) goto L21;
        if (r3.equals(r2.get(r0)) == true) goto L13;
        r0 = r0 - 1;
        goto L10
    L13:
        return r0;
    L21:
        return -1;
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> r1) {
        ArrayList<E> r0 = new ArrayList();
        Iterators.addAll(r0, r1);
        return r0;
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... r2) {
        Preconditions.checkNotNull(r2);
        ArrayList<E> r1 = new ArrayList(computeArrayListCapacity(r2.length));
        Collections.addAll(r1, r2);
        return r1;
    }

    public static <T> List<T> reverse(List<T> r1) {
        if ((r1 instanceof ImmutableList) == false) goto L7;
        return ((ImmutableList) r1).reverse();
    L7:
        if ((r1 instanceof ReverseList) == false) goto L11;
        return ((ReverseList) r1).getForwardList();
    L11:
        if ((r1 instanceof RandomAccess) == false) goto L15;
        return new RandomAccessReverseList(r1);
    L15:
        return new ReverseList(r1);
    }

    public static <F, T> List<T> transform(List<F> r1, Function<? super F, ? extends T> r2) {
        if ((r1 instanceof RandomAccess) == false) goto L7;
        return new TransformingRandomAccessList(r1, r2);
    L7:
        return new TransformingSequentialList(r1, r2);
    }
}
