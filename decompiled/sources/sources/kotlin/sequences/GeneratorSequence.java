package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B/\bF\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0082\u0004R\u0017\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlin/sequences/GeneratorSequence;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/Sequence;", "getInitialValue", "Lkotlin/Function0;", "getNextValue", "Lkotlin/Function1;", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class GeneratorSequence<T> implements Sequence<T> {
    private final Function0<T> getInitialValue;
    private final Function1<T, T> getNextValue;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\n\u0010\u000e\u001a\u00020\u000fH\u0082\u0080\u0004J\u000f\u0010\u0010\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0082\u0004R\u001f\u0010\u0002\u001a\u0004\u0018\u00018\u0000X\u0086\u008e\b¢\u0006\u0010\n\u0002\u0010\u0007\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u001b\u0010\b\u001a\u00020\tX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"kotlin/sequences/GeneratorSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "nextState", _UrlKt.FRAGMENT_ENCODE_SET, "getNextState", "()I", "setNextState", "(I)V", "calcNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.sequences.GeneratorSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        private T nextItem;
        private int nextState;
        final /* synthetic */ GeneratorSequence<T> this$0;

        public AnonymousClass1(GeneratorSequence<T> r1) {
            this.this$0 = r1;
            this.nextState = -2;
        }

        private final void calcNext() {
            int r0 = this.nextState;
            GeneratorSequence<T> r1 = this.this$0;
            if (r0 != (-2)) goto L5;
            T r02 = (T) GeneratorSequence.access$getGetInitialValue$p(r1).invoke();
        L6:
            this.nextItem = r02;
            if (r02 != null) goto L9;
            int r03 = 0;
        L10:
            this.nextState = r03;
            return;
        L9:
            r03 = 1;
            goto L10
        L5:
            r02 = (T) GeneratorSequence.access$getGetNextValue$p(r1).invoke(this.nextItem);
            goto L6
        }

        public final T getNextItem() {
            return this.nextItem;
        }

        public final int getNextState() {
            return this.nextState;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextState >= 0) goto L6;
            calcNext();
        L6:
            if (this.nextState != 1) goto L8;
            return true;
        L8:
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.nextState >= 0) goto L6;
            calcNext();
        L6:
            if (this.nextState == 0) goto L9;
            T r0 = this.nextItem;
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
    public GeneratorSequence(Function0<? extends T> r1, Function1<? super T, ? extends T> r2) {
        this.getInitialValue = r1;
        this.getNextValue = r2;
    }

    public static final /* synthetic */ Function0 access$getGetInitialValue$p(GeneratorSequence r0) {
        return r0.getInitialValue;
    }

    public static final /* synthetic */ Function1 access$getGetNextValue$p(GeneratorSequence r0) {
        return r0.getNextValue;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
