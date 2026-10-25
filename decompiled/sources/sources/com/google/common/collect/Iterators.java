package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.text.Typography;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class Iterators {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.Iterators$5, reason: invalid class name */
    public class AnonymousClass5<T> extends AbstractIterator<T> {
        final /* synthetic */ Predicate val$retainIfTrue;
        final /* synthetic */ Iterator val$unfiltered;

        public AnonymousClass5(Iterator r1, Predicate r2) {
            this.val$unfiltered = r1;
            this.val$retainIfTrue = r2;
        }

        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
        L3:
            if (this.val$unfiltered.hasNext() == false) goto L8;
            T r0 = (T) this.val$unfiltered.next();
            if (this.val$retainIfTrue.apply(r0) == false) goto L3;
            return r0;
        L8:
            return endOfData();
        }
    }

    public enum EmptyModifiableIterator extends Enum<EmptyModifiableIterator> implements Iterator<Object> {
        private static final /* synthetic */ EmptyModifiableIterator[] $VALUES = null;
        public static final EmptyModifiableIterator INSTANCE = null;

        private static /* synthetic */ EmptyModifiableIterator[] $values() {
            return new EmptyModifiableIterator[]{INSTANCE};
        }

        static {
            INSTANCE = new EmptyModifiableIterator("INSTANCE", 0);
            $VALUES = $values();
        }

        EmptyModifiableIterator(String r1, int r2) {
        }

        public static EmptyModifiableIterator valueOf(String r1) {
            return (EmptyModifiableIterator) Enum.valueOf(EmptyModifiableIterator.class, r1);
        }

        public static EmptyModifiableIterator[] values() {
            return (EmptyModifiableIterator[]) $VALUES.clone();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(false);
        }
    }

    public static final class SingletonIterator<T> extends UnmodifiableIterator<T> {
        private boolean done;
        private final T value;

        public SingletonIterator(T r1) {
            this.value = r1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.done;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.done == true) goto L6;
            this.done = true;
            return this.value;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    public static <T> boolean addAll(Collection<T> r2, Iterator<? extends T> r3) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r3);
        boolean r0 = false;
    L4:
        if (r3.hasNext() == false) goto L6;
        r0 = r0 | r2.add(r3.next());
        goto L4
    L6:
        return r0;
    }

    public static <T> boolean any(Iterator<T> r0, Predicate<? super T> r1) {
        if (indexOf(r0, r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static void clear(Iterator<?> r1) {
        Preconditions.checkNotNull(r1);
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next();
        r1.remove();
        goto L4
    }

    public static boolean contains(Iterator<?> r2, Object r3) {
        if (r3 != null) goto L10;
    L5:
        if (r2.hasNext() == false) goto L14;
        if (r2.next() != null) goto L5;
        return true;
    L14:
        return false;
    L10:
        if (r2.hasNext() == false) goto L22;
        if (r3.equals(r2.next()) == false) goto L10;
        return true;
    L22:
        return false;
    }

    public static boolean elementsEqual(Iterator<?> r3, Iterator<?> r4) {
    L3:
        if (r3.hasNext() == false) goto L11;
        if (r4.hasNext() == false) goto L6;
        if (Objects.equals(r3.next(), r4.next()) == true) goto L3;
        return false;
    L6:
        return false;
    L11:
        return !r4.hasNext();
    }

    public static <T> Iterator<T> emptyModifiableIterator() {
        return EmptyModifiableIterator.INSTANCE;
    }

    public static <T> UnmodifiableIterator<T> filter(Iterator<T> r1, Predicate<? super T> r2) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkNotNull(r2);
        return new AnonymousClass5(r1, r2);
    }

    public static <T> T find(Iterator<T> r2, Predicate<? super T> r3) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r3);
    L4:
        if (r2.hasNext() == false) goto L8;
        T r0 = r2.next();
        if (r3.apply(r0) == false) goto L4;
        return r0;
    L8:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public static <T> T getLast(Iterator<T> r2) {
    L2:
        T r0 = r2.next();
        if (r2.hasNext() == true) goto L2;
        return r0;
    }

    public static <T> T getLast(Iterator<? extends T> r1, T r2) {
        if (r1.hasNext() == true) goto L5;
        return r2;
    L5:
        return (T) getLast(r1);
    }

    public static <T> T getNext(Iterator<? extends T> r1, T r2) {
        if (r1.hasNext() == true) goto L5;
        return r2;
    L5:
        return r1.next();
    }

    public static <T> T getOnlyElement(Iterator<T> r3) {
        T r0 = r3.next();
        if (r3.hasNext() == true) goto L5;
        return r0;
    L5:
        StringBuilder r1 = new StringBuilder("expected one element but was: <");
        r1.append(r0);
        int r02 = 0;
    L7:
        if (r02 >= 4) goto L12;
        if (r3.hasNext() == false) goto L12;
        r1.append(", ");
        r1.append(r3.next());
        r02 = r02 + 1;
    L12:
        if (r3.hasNext() == false) goto L14;
        r1.append(", ...");
    L14:
        r1.append(Typography.greater);
        throw new IllegalArgumentException(r1.toString());
    }

    public static <T> int indexOf(Iterator<T> r2, Predicate<? super T> r3) {
        Preconditions.checkNotNull(r3, "predicate");
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L9;
        if (r3.apply(r2.next()) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    public static <T> T pollNext(Iterator<T> r1) {
        if (r1.hasNext() == false) goto L6;
        T r0 = r1.next();
        r1.remove();
        return r0;
    L6:
        return null;
    }

    public static boolean removeAll(Iterator<?> r2, Collection<?> r3) {
        Preconditions.checkNotNull(r3);
        boolean r0 = false;
    L4:
        if (r2.hasNext() == false) goto L8;
        if (r3.contains(r2.next()) == false) goto L4;
        r2.remove();
        r0 = true;
        goto L4
    L8:
        return r0;
    }

    public static <T> boolean removeIf(Iterator<T> r2, Predicate<? super T> r3) {
        Preconditions.checkNotNull(r3);
        boolean r0 = false;
    L4:
        if (r2.hasNext() == false) goto L8;
        if (r3.apply(r2.next()) == false) goto L4;
        r2.remove();
        r0 = true;
        goto L4
    L8:
        return r0;
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(T r1) {
        return new SingletonIterator(r1);
    }
}
