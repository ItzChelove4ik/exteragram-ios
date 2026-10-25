package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001\fBE\bF\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\tH\u0096\u0082\u0004R\u0015\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R!\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/FlatteningSequence;", "T", "R", "E", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function1;", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "State", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FlatteningSequence<T, R, E> implements Sequence<E> {
    private final Function1<R, Iterator<E>> iterator;
    private final Sequence<T> sequence;
    private final Function1<T, R> transformer;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/sequences/FlatteningSequence$State;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "UNDEFINED", _UrlKt.FRAGMENT_ENCODE_SET, "READY", "DONE", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class State {
        public static final int DONE = 2;
        public static final State INSTANCE = null;
        public static final int READY = 1;
        public static final int UNDEFINED = 0;

        static {
            INSTANCE = new State();
        }

        private State() {
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u000f\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0010J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0082\u0004J\n\u0010\u0013\u001a\u00020\u0012H\u0082\u0080\u0004R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R#\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0001X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0004\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\nX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"kotlin/sequences/FlatteningSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", "getIterator", "()Ljava/util/Iterator;", "itemIterator", "getItemIterator", "setItemIterator", "(Ljava/util/Iterator;)V", "state", _UrlKt.FRAGMENT_ENCODE_SET, "getState", "()I", "setState", "(I)V", "next", "()Ljava/lang/Object;", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "ensureItemIterator", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.sequences.FlatteningSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<E>, KMappedMarker {
        private Iterator<? extends E> itemIterator;
        private final Iterator<T> iterator;
        private int state;
        final /* synthetic */ FlatteningSequence<T, R, E> this$0;

        public AnonymousClass1(FlatteningSequence<T, R, E> r1) {
            this.this$0 = r1;
            this.iterator = FlatteningSequence.access$getSequence$p(r1).iterator();
        }

        private final boolean ensureItemIterator() {
            Iterator<? extends E> r0 = this.itemIterator;
            if (r0 == null) goto L9;
            if (r0.hasNext() == false) goto L9;
            this.state = 1;
            return true;
        L9:
            if (this.iterator.hasNext() == false) goto L14;
            T r02 = this.iterator.next();
            Iterator<? extends E> r03 = (Iterator) FlatteningSequence.access$getIterator$p(this.this$0).invoke(FlatteningSequence.access$getTransformer$p(this.this$0).invoke(r02));
            if (r03.hasNext() == false) goto L9;
            this.itemIterator = r03;
            this.state = 1;
            return true;
        L14:
            this.state = 2;
            this.itemIterator = null;
            return false;
        }

        public final Iterator<E> getItemIterator() {
            return this.itemIterator;
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final int getState() {
            return this.state;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            int r0 = this.state;
            if (r0 != 1) goto L6;
            return true;
        L6:
            if (r0 != 2) goto L10;
            return false;
        L10:
            return ensureItemIterator();
        }

        @Override // java.util.Iterator
        public E next() {
            int r0 = this.state;
            if (r0 == 2) goto L12;
            if (r0 == 0) goto L6;
        L10:
            this.state = 0;
            return this.itemIterator.next();
        L6:
            if (ensureItemIterator() == true) goto L10;
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        L12:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setItemIterator(Iterator<? extends E> r1) {
            this.itemIterator = r1;
        }

        public final void setState(int r1) {
            this.state = r1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FlatteningSequence(Sequence<? extends T> r1, Function1<? super T, ? extends R> r2, Function1<? super R, ? extends Iterator<? extends E>> r3) {
        this.sequence = r1;
        this.transformer = r2;
        this.iterator = r3;
    }

    public static final /* synthetic */ Function1 access$getIterator$p(FlatteningSequence r0) {
        return r0.iterator;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(FlatteningSequence r0) {
        return r0.sequence;
    }

    public static final /* synthetic */ Function1 access$getTransformer$p(FlatteningSequence r0) {
        return r0.transformer;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<E> iterator() {
        return new AnonymousClass1(this);
    }
}
