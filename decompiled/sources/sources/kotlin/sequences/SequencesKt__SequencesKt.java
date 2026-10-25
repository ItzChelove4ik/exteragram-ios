package kotlin.sequences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\u0088\u0004ø\u0001\u0000\u001a \u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086\u0080\u0004\u001a/\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\t\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\n\u001a#\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000b\u001a\u0002H\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\f\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0086\u0080\u0004\u001a\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\u0088\u0004\u001a4\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0004H\u0087\u0080\u0004\u001a&\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001H\u0086\u0080\u0004\u001a+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00120\u0001H\u0087\u0080\u0004¢\u0006\u0002\b\u0013\u001aE\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00050\u0015H\u0082\u0080\u0004¢\u0006\u0002\b\u0016\u001aD\u0010\u0017\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00190\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0014*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00140\u00180\u0001H\u0086\u0080\u0004\u001a \u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\u0080\u0004\u001a(\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0087\u0080\u0004\u001ad\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u001e\"\u0004\b\u0002\u0010\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001e0!2\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00050\u0015H\u0080\u0080\u0004\u001a \u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0086\u0080\u0004\u001a*\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020%2\u000e\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0004H\u0086\u0080\u0004\u001a?\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020%2\b\u0010'\u001a\u0004\u0018\u0001H\u00022\u0014\u0010&\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0015H\u0087\u0080\u0004¢\u0006\u0002\u0010(\u001a@\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020%2\u000e\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00042\u0014\u0010&\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0015H\u0086\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"}, d2 = {"Sequence", "Lkotlin/sequences/Sequence;", "T", "iterator", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "asSequence", "sequenceOf", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "element", "(Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "emptySequence", "orEmpty", "ifEmpty", "defaultValue", "flatten", _UrlKt.FRAGMENT_ENCODE_SET, "flattenSequenceOfIterable", "R", "Lkotlin/Function1;", "flatten$SequencesKt__SequencesKt", "unzip", "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "shuffled", "random", "Lkotlin/random/Random;", "flatMapIndexed", "C", "source", "transform", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "constrainOnce", "generateSequence", _UrlKt.FRAGMENT_ENCODE_SET, "nextFunction", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "seedFunction", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/sequences/SequencesKt")
/* loaded from: classes.dex */
class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0010\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0082\u0004¨\u0006\u0004"}, d2 = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SequencesKt__SequencesKt$Sequence$1\n*L\n1#1,730:1\n*E\n"})
    /* renamed from: kotlin.sequences.SequencesKt__SequencesKt$Sequence$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1<T> implements Sequence<T> {
        final /* synthetic */ Function0<Iterator<T>> $iterator;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function0<? extends Iterator<? extends T>> r1) {
            this.$iterator = r1;
        }

        @Override // kotlin.sequences.Sequence
        public Iterator<T> iterator() {
            return this.$iterator.invoke();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "R", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$flatMapIndexed$1", f = "Sequences.kt", i = {0, 0, 0, 0}, l = {383}, m = "invokeSuspend", n = {"$this$sequence", "element", "result", "index"}, nl = {385}, s = {"L$0", "L$2", "L$3", "I$0"}, v = 2)
    /* renamed from: kotlin.sequences.SequencesKt__SequencesKt$flatMapIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01761<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<C, Iterator<R>> $iterator;
        final /* synthetic */ Sequence<T> $source;
        final /* synthetic */ Function2<Integer, T, C> $transform;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public C01761(Sequence<? extends T> r1, Function2<? super Integer, ? super T, ? extends C> r2, Function1<? super C, ? extends Iterator<? extends R>> r3, Continuation<? super C01761> r4) {
            this.$source = r1;
            this.$transform = r2;
            this.$iterator = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            C01761 r0 = new C01761(this.$source, this.$transform, this.$iterator, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
            return invoke((SequenceScope) r1, r2);
        }

        public final Object invoke(SequenceScope<? super R> r1, Continuation<? super Unit> r2) {
            return ((C01761) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            SequenceScope r0 = (SequenceScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L8;
            if (r2 != 1) goto L6;
            int r22 = this.I$0;
            Iterator r4 = (Iterator) this.L$1;
            ResultKt.throwOnFailure(r8);
        L10:
            if (r4.hasNext() == false) goto L19;
            Object r82 = r4.next();
            Function2<Integer, T, C> r5 = this.$transform;
            int r6 = r22 + 1;
            if (r22 >= 0) goto L14;
            CollectionsKt.throwIndexOverflow();
        L14:
            Object r23 = r5.invoke(Boxing.boxInt(r22), r82);
            Iterator<R> r52 = this.$iterator.invoke(r23);
            this.L$0 = r0;
            this.L$1 = r4;
            this.L$2 = SpillingKt.nullOutSpilledVariable(r82);
            this.L$3 = SpillingKt.nullOutSpilledVariable(r23);
            this.I$0 = r6;
            this.label = 1;
            if (r0.yieldAll(r52, this) == r1) goto L16;
            r22 = r6;
            goto L10
        L16:
            return r1;
        L19:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r8);
            r22 = 0;
            r4 = this.$source.iterator();
            goto L10
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$ifEmpty$1", f = "Sequences.kt", i = {0, 0, 1, 1}, l = {102, 104}, m = "invokeSuspend", n = {"$this$sequence", "iterator", "$this$sequence", "iterator"}, nl = {104, 106}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 2)
    /* renamed from: kotlin.sequences.SequencesKt__SequencesKt$ifEmpty$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01771<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Sequence<T>> $defaultValue;
        final /* synthetic */ Sequence<T> $this_ifEmpty;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public C01771(Sequence<? extends T> r1, Function0<? extends Sequence<? extends T>> r2, Continuation<? super C01771> r3) {
            this.$this_ifEmpty = r1;
            this.$defaultValue = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            C01771 r0 = new C01771(this.$this_ifEmpty, this.$defaultValue, r4);
            r0.L$0 = r3;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
            return invoke((SequenceScope) r1, r2);
        }

        public final Object invoke(SequenceScope<? super T> r1, Continuation<? super Unit> r2) {
            return ((C01771) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            SequenceScope r0 = (SequenceScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L10;
            if (r2 == 1) goto L9;
            if (r2 == 2) goto L9;
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            Iterator r5 = (Iterator) this.L$1;
            ResultKt.throwOnFailure(r6);
        L19:
            return Unit.INSTANCE;
        L10:
            ResultKt.throwOnFailure(r6);
            Iterator<? extends T> r62 = this.$this_ifEmpty.iterator();
            if (r62.hasNext() == false) goto L15;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r62);
            this.label = 1;
            if (r0.yieldAll(r62, this) != r1) goto L19;
        L17:
            return r1;
        L15:
            Sequence<T> r22 = this.$defaultValue.invoke();
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r62);
            this.label = 2;
            if (r0.yieldAll(r22, this) != r1) goto L19;
            goto L17
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$shuffled$1", f = "Sequences.kt", i = {0, 0, 0, 0, 0}, l = {178}, m = "invokeSuspend", n = {"$this$sequence", "buffer", "last", "value", "j"}, nl = {180}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 2)
    /* renamed from: kotlin.sequences.SequencesKt__SequencesKt$shuffled$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01781<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Random $random;
        final /* synthetic */ Sequence<T> $this_shuffled;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public C01781(Sequence<? extends T> r1, Random r2, Continuation<? super C01781> r3) {
            this.$this_shuffled = r1;
            this.$random = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            C01781 r0 = new C01781(this.$this_shuffled, this.$random, r4);
            r0.L$0 = r3;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
            return invoke((SequenceScope) r1, r2);
        }

        public final Object invoke(SequenceScope<? super T> r1, Continuation<? super Unit> r2) {
            return ((C01781) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            SequenceScope r0 = (SequenceScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L8;
            if (r2 != 1) goto L6;
            List r22 = (List) this.L$1;
            ResultKt.throwOnFailure(r7);
        L10:
            if (r22.isEmpty() == true) goto L19;
            int r72 = this.$random.nextInt(r22.size());
            Object r4 = CollectionsKt.removeLast(r22);
            if (r72 >= r22.size()) goto L14;
            Object r5 = r22.set(r72, r4);
        L15:
            this.L$0 = r0;
            this.L$1 = r22;
            this.L$2 = SpillingKt.nullOutSpilledVariable(r4);
            this.L$3 = SpillingKt.nullOutSpilledVariable(r5);
            this.I$0 = r72;
            this.label = 1;
            if (r0.yield(r5, this) != r1) goto L10;
            return r1;
        L14:
            r5 = r4;
            goto L15
        L19:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            r22 = SequencesKt___SequencesKt.toMutableList(this.$this_shuffled);
            goto L10
        }
    }

    /* renamed from: $r8$lambda$8HblPkoO8vE-XEU8raYk3YnY_qE, reason: not valid java name */
    public static /* synthetic */ Object m3306$r8$lambda$8HblPkoO8vEXEU8raYk3YnY_qE(Function0 r0, Object r1) {
        return generateSequence$lambda$0$SequencesKt__SequencesKt(r0, r1);
    }

    /* renamed from: $r8$lambda$cseudtdU4zbCeV6Mg-lp-bs1JX0, reason: not valid java name */
    public static /* synthetic */ Object m3307$r8$lambda$cseudtdU4zbCeV6Mglpbs1JX0(Object r0) {
        return flatten$lambda$2$SequencesKt__SequencesKt(r0);
    }

    public static /* synthetic */ Object $r8$lambda$eiRiKARCOo2Ht_MuMlAffn7d6hs(Object r0) {
        return generateSequence$lambda$1$SequencesKt__SequencesKt(r0);
    }

    public static /* synthetic */ Iterator $r8$lambda$hPordbPC5Xw5w6MUApwugbNlZ1I(Sequence r0) {
        return flatten$lambda$0$SequencesKt__SequencesKt(r0);
    }

    public static /* synthetic */ Iterator $r8$lambda$nt0hwWaA4ppGj455MJPoEqIa3SU(Iterable r0) {
        return flatten$lambda$1$SequencesKt__SequencesKt(r0);
    }

    public SequencesKt__SequencesKt() {
    }

    @InlineOnly
    private static final <T> Sequence<T> Sequence(Function0<? extends Iterator<? extends T>> r1) {
        return new AnonymousClass1(r1);
    }

    public static <T> Sequence<T> asSequence(Iterator<? extends T> r1) {
        return constrainOnce(new SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1(r1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Sequence<T> constrainOnce(Sequence<? extends T> r1) {
        if ((r1 instanceof ConstrainedOnceSequence) == false) goto L6;
        return r1;
    L6:
        return new ConstrainedOnceSequence(r1);
    }

    public static <T> Sequence<T> emptySequence() {
        return EmptySequence.INSTANCE;
    }

    public static final <T, C, R> Sequence<R> flatMapIndexed(Sequence<? extends T> r2, Function2<? super Integer, ? super T, ? extends C> r3, Function1<? super C, ? extends Iterator<? extends R>> r4) {
        return SequencesKt__SequenceBuilderKt.sequence(new C01761(r2, r3, r4, null));
    }

    public static final <T> Sequence<T> flatten(Sequence<? extends Sequence<? extends T>> r1) {
        return flatten$SequencesKt__SequencesKt(r1, new SequencesKt__SequencesKt$$ExternalSyntheticLambda2());
    }

    private static final <T, R> Sequence<R> flatten$SequencesKt__SequencesKt(Sequence<? extends T> r2, Function1<? super T, ? extends Iterator<? extends R>> r3) {
        if ((r2 instanceof TransformingSequence) == false) goto L7;
        return ((TransformingSequence) r2).flatten$kotlin_stdlib(r3);
    L7:
        return new FlatteningSequence(r2, new SequencesKt__SequencesKt$$ExternalSyntheticLambda4(), r3);
    }

    private static final Iterator flatten$lambda$0$SequencesKt__SequencesKt(Sequence r0) {
        return r0.iterator();
    }

    private static final Iterator flatten$lambda$1$SequencesKt__SequencesKt(Iterable r0) {
        return r0.iterator();
    }

    private static final Object flatten$lambda$2$SequencesKt__SequencesKt(Object r0) {
        return r0;
    }

    @JvmName(name = "flattenSequenceOfIterable")
    public static final <T> Sequence<T> flattenSequenceOfIterable(Sequence<? extends Iterable<? extends T>> r1) {
        return flatten$SequencesKt__SequencesKt(r1, new SequencesKt__SequencesKt$$ExternalSyntheticLambda3());
    }

    @LowPriorityInOverloadResolution
    public static <T> Sequence<T> generateSequence(T r2, Function1<? super T, ? extends T> r3) {
        if (r2 != null) goto L6;
        return EmptySequence.INSTANCE;
    L6:
        return new GeneratorSequence(new SequencesKt__SequencesKt$$ExternalSyntheticLambda0(r2), r3);
    }

    public static final <T> Sequence<T> generateSequence(Function0<? extends T> r2) {
        return constrainOnce(new GeneratorSequence(r2, new SequencesKt__SequencesKt$$ExternalSyntheticLambda1(r2)));
    }

    public static <T> Sequence<T> generateSequence(Function0<? extends T> r1, Function1<? super T, ? extends T> r2) {
        return new GeneratorSequence(r1, r2);
    }

    private static final Object generateSequence$lambda$0$SequencesKt__SequencesKt(Function0 r0, Object r1) {
        return r0.invoke();
    }

    private static final Object generateSequence$lambda$1$SequencesKt__SequencesKt(Object r0) {
        return r0;
    }

    @SinceKotlin(version = "1.3")
    public static final <T> Sequence<T> ifEmpty(Sequence<? extends T> r2, Function0<? extends Sequence<? extends T>> r3) {
        return SequencesKt__SequenceBuilderKt.sequence(new C01771(r2, r3, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Sequence<T> orEmpty(Sequence<? extends T> r0) {
        if (r0 == 0) goto L4;
        return r0;
    L4:
        return emptySequence();
    }

    @SinceKotlin(version = "2.2")
    @InlineOnly
    private static final <T> Sequence<T> sequenceOf() {
        return emptySequence();
    }

    @SinceKotlin(version = "2.2")
    public static <T> Sequence<T> sequenceOf(T r1) {
        return new SequencesKt__SequencesKt$sequenceOf$$inlined$Sequence$1(r1);
    }

    public static final <T> Sequence<T> sequenceOf(T... r0) {
        return ArraysKt.asSequence(r0);
    }

    @SinceKotlin(version = "1.4")
    public static final <T> Sequence<T> shuffled(Sequence<? extends T> r1) {
        return shuffled(r1, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final <T> Sequence<T> shuffled(Sequence<? extends T> r2, Random r3) {
        return SequencesKt__SequenceBuilderKt.sequence(new C01781(r2, r3, null));
    }

    public static final <T, R> Pair<List<T>, List<R>> unzip(Sequence<? extends Pair<? extends T, ? extends R>> r4) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator<? extends Pair<? extends T, ? extends R>> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L7;
        Pair<? extends T, ? extends R> r2 = r42.next();
        r0.add(r2.getFirst());
        r1.add(r2.getSecond());
        goto L4
    L7:
        return TuplesKt.to(r0, r1);
    }
}
