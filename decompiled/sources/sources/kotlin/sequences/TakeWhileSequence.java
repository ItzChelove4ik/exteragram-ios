package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B+\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0082\u0004R\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/sequences/TakeWhileSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "predicate", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TakeWhileSequence<T> implements Sequence<T> {
    private final Function1<T, Boolean> predicate;
    private final Sequence<T> sequence;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\n\u0010\u0011\u001a\u00020\u0012H\u0082\u0080\u0004J\u000f\u0010\u0013\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\rJ\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0082\u0004R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u0006X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001f\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0086\u008e\b¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"kotlin/sequences/TakeWhileSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", "getIterator", "()Ljava/util/Iterator;", "nextState", _UrlKt.FRAGMENT_ENCODE_SET, "getNextState", "()I", "setNextState", "(I)V", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "calcNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.sequences.TakeWhileSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        private final Iterator<T> iterator;
        private T nextItem;
        private int nextState;
        final /* synthetic */ TakeWhileSequence<T> this$0;

        public AnonymousClass1(TakeWhileSequence<T> r1) {
            this.this$0 = r1;
            this.iterator = TakeWhileSequence.access$getSequence$p(r1).iterator();
            this.nextState = -1;
        }

        private final void calcNext() {
            if (this.iterator.hasNext() == false) goto L8;
            T r0 = this.iterator.next();
            if (((Boolean) TakeWhileSequence.access$getPredicate$p(this.this$0).invoke(r0)).booleanValue() == false) goto L8;
            this.nextState = 1;
            this.nextItem = r0;
            return;
        L8:
            this.nextState = 0;
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final T getNextItem() {
            return this.nextItem;
        }

        public final int getNextState() {
            return this.nextState;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextState != (-1)) goto L6;
            calcNext();
        L6:
            if (this.nextState != 1) goto L8;
            return true;
        L8:
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.nextState != (-1)) goto L6;
            calcNext();
        L6:
            if (this.nextState == 0) goto L9;
            T r0 = this.nextItem;
            this.nextItem = null;
            this.nextState = -1;
            return r0;
        L9:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setNextItem(T r1) {
            this.nextItem = r1;
        }

        public final void setNextState(int r1) {
            this.nextState = r1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TakeWhileSequence(Sequence<? extends T> r1, Function1<? super T, Boolean> r2) {
        this.sequence = r1;
        this.predicate = r2;
    }

    public static final /* synthetic */ Function1 access$getPredicate$p(TakeWhileSequence r0) {
        return r0.predicate;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(TakeWhileSequence r0) {
        return r0.sequence;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
