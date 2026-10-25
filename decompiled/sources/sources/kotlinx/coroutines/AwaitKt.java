package kotlinx.coroutines;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a,\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001H\u0086@¢\u0006\u0004\b\u0004\u0010\u0005\u001a$\u0010\n\u001a\u00020\t2\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0086@¢\u0006\u0004\b\n\u0010\u000b\u001a\u001a\u0010\n\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00070\u0001H\u0086@¢\u0006\u0004\b\n\u0010\u0005¨\u0006\f"}, d2 = {"T", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "awaitAll", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Job;", "jobs", _UrlKt.FRAGMENT_ENCODE_SET, "joinAll", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,121:1\n37#2,2:122\n13805#3,2:124\n1869#4,2:126\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitKt\n*L\n36#1:122,2\n47#1:124,2\n58#1:126,2\n*E\n"})
/* loaded from: classes5.dex */
public abstract class AwaitKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.AwaitKt", f = "Await.kt", i = {0, 0, 0, 0, 0, 0}, l = {47}, m = "joinAll", n = {"jobs", "$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-AwaitKt$joinAll$2"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$3"}, v = 1)
    /* renamed from: kotlinx.coroutines.AwaitKt$joinAll$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return AwaitKt.joinAll(null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.AwaitKt", f = "Await.kt", i = {0, 0, 0, 0, 0, 0}, l = {58}, m = "joinAll", n = {"$this$joinAll", "$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-AwaitKt$joinAll$4"}, s = {"L$0", "L$1", "L$3", "L$4", "I$0", "I$1"}, v = 1)
    /* renamed from: kotlinx.coroutines.AwaitKt$joinAll$3, reason: invalid class name */
    public static final class AnonymousClass3 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(Continuation<? super AnonymousClass3> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return AwaitKt.joinAll(null, this);
        }
    }

    public static final <T> Object awaitAll(Collection<? extends Deferred<? extends T>> r2, Continuation<? super List<? extends T>> r3) {
        if (r2.isEmpty() == false) goto L7;
        return CollectionsKt.emptyList();
    L7:
        return new AwaitAll((Deferred[]) r2.toArray(new Deferred[0])).await(r3);
    }

    public static final Object joinAll(Collection<? extends Job> r9, Continuation<? super Unit> r10) {
        if ((r10 instanceof AnonymousClass3) == false) goto L7;
        AnonymousClass3 r0 = (AnonymousClass3) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        int r92 = r0.I$0;
        Job r22 = (Job) r0.L$4;
        Iterator r23 = (Iterator) r0.L$2;
        Iterable r5 = (Iterable) r0.L$1;
        Collection<? extends Job> r6 = (Collection) r0.L$0;
        ResultKt.throwOnFailure(r102);
        int r103 = r92;
        r9 = r6;
    L16:
        if (r23.hasNext() == false) goto L21;
        Object r62 = r23.next();
        Job r7 = (Job) r62;
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r9);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r5);
        r0.L$2 = r23;
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r62);
        r0.L$4 = SpillingKt.nullOutSpilledVariable(r7);
        r0.I$0 = r103;
        r0.I$1 = 0;
        r0.label = 1;
        if (r7.join(r0) != r12) goto L16;
        return r12;
    L21:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r102);
        Collection<? extends Job> r104 = r9;
        r23 = r104.iterator();
        r5 = r104;
        r103 = 0;
    L7:
        r0 = new AnonymousClass3(r10);
        goto L8
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x007e -> B:20:0x0081). Please report as a decompilation issue!!! */
    public static final Object joinAll(Job[] r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        int r102 = r0.I$2;
        int r22 = r0.I$1;
        int r5 = r0.I$0;
        Job r6 = (Job) r0.L$3;
        Job r62 = (Job) r0.L$2;
        Job[] r63 = (Job[]) r0.L$1;
        Job[] r7 = (Job[]) r0.L$0;
        ResultKt.throwOnFailure(r112);
        Job[] r113 = r63;
        int r64 = r5;
        AnonymousClass1 r52 = r0;
        Job[] r02 = r7;
    L20:
        AnonymousClass1 r9 = r52;
        int r53 = r22 + 1;
        AnonymousClass1 r23 = r9;
    L15:
        if (r53 >= r102) goto L22;
        Job r72 = r113[r53];
        r23.L$0 = SpillingKt.nullOutSpilledVariable(r02);
        r23.L$1 = r113;
        r23.L$2 = SpillingKt.nullOutSpilledVariable(r72);
        r23.L$3 = SpillingKt.nullOutSpilledVariable(r72);
        r23.I$0 = r64;
        r23.I$1 = r53;
        r23.I$2 = r102;
        r23.I$3 = 0;
        r23.label = 1;
        if (r72.join(r23) != r12) goto L19;
        return r12;
    L19:
        int r92 = r53;
        r52 = r23;
        r22 = r92;
        goto L20
    L22:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r112);
        r23 = r0;
        r53 = 0;
        r64 = 0;
        r02 = r10;
        r102 = r10.length;
        r113 = r02;
    L7:
        r0 = new AnonymousClass1(r11);
        goto L8
    }
}
