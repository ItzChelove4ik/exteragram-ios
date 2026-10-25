package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.random.RandomKt$$ExternalSyntheticBUOutline0;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0080\u0080\u0004\u001aF\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0080\u0080\u0004\u001aJ\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\r\"\u0004\b\u0000\u0010\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\b0\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0080\u0080\u0004¨\u0006\u000f"}, d2 = {"checkWindowSizeStep", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "step", "windowedSequence", "Lkotlin/sequences/Sequence;", _UrlKt.FRAGMENT_ENCODE_SET, "T", "partialWindows", _UrlKt.FRAGMENT_ENCODE_SET, "reuseBuffer", "windowedIterator", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SlidingWindowKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/sequences/SequenceScope;", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4}, l = {34, 40, 49, 55, 58}, m = "invokeSuspend", n = {"$this$iterator", "buffer", "e", "bufferInitialCapacity", "gap", "skip", "$this$iterator", "buffer", "bufferInitialCapacity", "gap", "skip", "$this$iterator", "buffer", "e", "bufferInitialCapacity", "gap", "$this$iterator", "buffer", "bufferInitialCapacity", "gap", "$this$iterator", "buffer", "bufferInitialCapacity", "gap"}, nl = {35, 43, 50, 56, 61}, s = {"L$0", "L$1", "L$3", "I$0", "I$1", "I$2", "L$0", "L$1", "I$0", "I$1", "I$2", "L$0", "L$1", "L$3", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"}, v = 2)
    /* renamed from: kotlin.collections.SlidingWindowKt$windowedIterator$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Iterator<T> $iterator;
        final /* synthetic */ boolean $partialWindows;
        final /* synthetic */ boolean $reuseBuffer;
        final /* synthetic */ int $size;
        final /* synthetic */ int $step;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(int r1, int r2, Iterator<? extends T> r3, boolean r4, boolean r5, Continuation<? super AnonymousClass1> r6) {
            this.$size = r1;
            this.$step = r2;
            this.$iterator = r3;
            this.$reuseBuffer = r4;
            this.$partialWindows = r5;
            super(2, r6);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, r9);
            r0.L$0 = r8;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
            return invoke((SequenceScope) r1, r2);
        }

        public final Object invoke(SequenceScope<? super List<? extends T>> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00af -> B:17:0x005d). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0141 -> B:60:0x0144). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0175 -> B:73:0x0178). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r14) {
            SequenceScope r0 = (SequenceScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L18;
            if (r2 == 1) goto L16;
            if (r2 == 2) goto L14;
            if (r2 == 3) goto L13;
            if (r2 == 4) goto L12;
            if (r2 != 5) goto L10;
            RingBuffer r13 = (RingBuffer) this.L$1;
        L15:
            ResultKt.throwOnFailure(r14);
        L80:
            return Unit.INSTANCE;
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L12:
            int r22 = this.I$1;
            int r5 = this.I$0;
            RingBuffer r6 = (RingBuffer) this.L$1;
            ResultKt.throwOnFailure(r14);
        L73:
            r6.removeFirst(this.$step);
        L65:
            if (r6.size() <= this.$step) goto L75;
            if (this.$reuseBuffer == false) goto L69;
            RandomAccess r142 = r6;
        L70:
            this.L$0 = r0;
            this.L$1 = r6;
            this.L$2 = null;
            this.L$3 = null;
            this.I$0 = r5;
            this.I$1 = r22;
            this.label = 4;
            if (r0.yield(r142, this) != r1) goto L73;
        L78:
            return r1;
        L69:
            r142 = new ArrayList(r6);
            goto L70
        L75:
            if (r6.isEmpty() == true) goto L80;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r6);
            this.L$2 = null;
            this.L$3 = null;
            this.I$0 = r5;
            this.I$1 = r22;
            this.label = 5;
            if (r0.yield(r6, this) != r1) goto L80;
        L13:
            r22 = this.I$1;
            int r62 = this.I$0;
            Iterator<T> r7 = (Iterator) this.L$2;
            RingBuffer r9 = (RingBuffer) this.L$1;
            ResultKt.throwOnFailure(r14);
        L60:
            r9.removeFirst(this.$step);
        L47:
            if (r7.hasNext() == false) goto L62;
            T r143 = r7.next();
            r9.add(r143);
            if (r9.isFull() == false) goto L47;
            int r10 = r9.size();
            int r11 = this.$size;
            if (r10 >= r11) goto L54;
            r9 = r9.expanded(r11);
            goto L47
        L54:
            if (this.$reuseBuffer == false) goto L56;
            RandomAccess r102 = r9;
        L57:
            this.L$0 = r0;
            this.L$1 = r9;
            this.L$2 = r7;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r143);
            this.I$0 = r62;
            this.I$1 = r22;
            this.label = 3;
            if (r0.yield(r102, this) != r1) goto L60;
        L56:
            r102 = new ArrayList(r9);
            goto L57
        L62:
            if (this.$partialWindows == false) goto L80;
            r5 = r62;
            r6 = r9;
            goto L65
        L14:
            ArrayList r132 = (ArrayList) this.L$1;
            goto L15
        L16:
            r22 = this.I$1;
            int r3 = this.I$0;
            Iterator<T> r4 = (Iterator) this.L$2;
            ArrayList r52 = (ArrayList) this.L$1;
            ResultKt.throwOnFailure(r14);
        L17:
            int r144 = r22;
            if (this.$reuseBuffer == false) goto L34;
            r52.clear();
        L35:
            r22 = r144;
        L22:
            if (r4.hasNext() == false) goto L37;
            T r92 = r4.next();
            if (r144 > 0) goto L25;
            r52.add(r92);
            if (r52.size() != this.$size) goto L22;
            this.L$0 = r0;
            this.L$1 = r52;
            this.L$2 = r4;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r92);
            this.I$0 = r3;
            this.I$1 = r22;
            this.I$2 = r144;
            this.label = 1;
            if (r0.yield(r52, this) != r1) goto L17;
        L25:
            r144 = r144 - 1;
            goto L22
        L37:
            if (r52.isEmpty() == true) goto L80;
            if (this.$partialWindows == false) goto L41;
        L42:
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r52);
            this.L$2 = null;
            this.L$3 = null;
            this.I$0 = r3;
            this.I$1 = r22;
            this.I$2 = r144;
            this.label = 2;
            if (r0.yield(r52, this) != r1) goto L80;
        L41:
            if (r52.size() != this.$size) goto L80;
        L34:
            r52 = new ArrayList(this.$size);
            goto L35
        L18:
            ResultKt.throwOnFailure(r14);
            int r145 = RangesKt.coerceAtMost(this.$size, 1024);
            r22 = this.$step - this.$size;
            if (r22 < 0) goto L45;
            ArrayList r32 = new ArrayList(r145);
            r4 = this.$iterator;
            r3 = r145;
            r144 = 0;
            r52 = r32;
            goto L22
        L45:
            RingBuffer r63 = new RingBuffer(r145);
            r7 = this.$iterator;
            r9 = r63;
            r62 = r145;
            goto L47
        }
    }

    public static final void checkWindowSizeStep(int r3, int r4) {
        if (r3 <= 0) goto L6;
        if (r4 <= 0) goto L6;
        return;
    L6:
        if (r3 == r4) goto L8;
        String r32 = "Both size " + r3 + " and step " + r4 + " must be greater than zero.";
    L9:
        RandomKt$$ExternalSyntheticBUOutline0.m(r32);
        return;
    L8:
        r32 = "size " + r3 + " must be greater than zero.";
        goto L9
    }

    public static final <T> Iterator<List<T>> windowedIterator(Iterator<? extends T> r7, int r8, int r9, boolean r10, boolean r11) {
        if (r7.hasNext() == true) goto L7;
        return EmptyIterator.INSTANCE;
    L7:
        return SequencesKt.iterator(new AnonymousClass1(r8, r9, r7, r11, r10, null));
    }

    public static final <T> Sequence<List<T>> windowedSequence(Sequence<? extends T> r6, int r7, int r8, boolean r9, boolean r10) {
        checkWindowSizeStep(r7, r8);
        return new SlidingWindowKt$windowedSequence$$inlined$Sequence$1(r6, r7, r8, r9, r10);
    }
}
