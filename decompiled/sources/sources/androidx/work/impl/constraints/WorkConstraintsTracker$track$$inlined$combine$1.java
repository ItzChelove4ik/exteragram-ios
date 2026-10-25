package androidx.work.impl.constraints;

import androidx.work.impl.constraints.ConstraintsState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Zip.kt\nkotlinx/coroutines/flow/FlowKt__ZipKt\n*L\n1#1,108:1\n285#2,5:109\n*E\n"})
/* loaded from: classes4.dex */
public final class WorkConstraintsTracker$track$$inlined$combine$1 implements Flow<ConstraintsState> {
    final /* synthetic */ Flow[] $flowArray$inlined;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nZip.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Zip.kt\nkotlinx/coroutines/flow/FlowKt__ZipKt$combine$6$1\n*L\n1#1,287:1\n*E\n"})
    /* renamed from: androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$2, reason: invalid class name */
    public static final class AnonymousClass2 implements Function0<ConstraintsState[]> {
        final /* synthetic */ Flow[] $flowArray;

        public AnonymousClass2(Flow[] r1) {
            this.$flowArray = r1;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [androidx.work.impl.constraints.ConstraintsState[], java.lang.Object[]] */
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ ConstraintsState[] invoke() {
            return invoke2();
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final ConstraintsState[] invoke2() {
            return new ConstraintsState[this.$flowArray.length];
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0006\b\u0001\u0010\u0001\u0018\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"R", "T", "Lkotlinx/coroutines/flow/FlowCollector;", _UrlKt.FRAGMENT_ENCODE_SET, "it", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/Array;)V"}, k = 3, mv = {2, 1, 0})
    @DebugMetadata(c = "androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3", f = "WorkConstraintsTracker.kt", i = {}, l = {288}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nZip.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Zip.kt\nkotlinx/coroutines/flow/FlowKt__ZipKt$combine$6$2\n+ 2 WorkConstraintsTracker.kt\nandroidx/work/impl/constraints/WorkConstraintsTracker\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,288:1\n84#2:289\n1310#3,2:290\n*S KotlinDebug\n*F\n+ 1 WorkConstraintsTracker.kt\nandroidx/work/impl/constraints/WorkConstraintsTracker\n*L\n84#1:290,2\n*E\n"})
    /* renamed from: androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3, reason: invalid class name */
    public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super ConstraintsState>, ConstraintsState[], Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass3(Continuation r2) {
            super(3, r2);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super ConstraintsState> r1, ConstraintsState[] r2, Continuation<? super Unit> r3) {
            return invoke(r1, r2, r3);
        }

        public final Object invoke(FlowCollector<? super ConstraintsState> r1, ConstraintsState[] r2, Continuation<? super Unit> r3) {
            AnonymousClass3 r0 = new AnonymousClass3(r3);
            r0.L$0 = r1;
            r0.L$1 = r2;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r9) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            ConstraintsState r2 = null;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r9);
        L20:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r9);
            FlowCollector r92 = (FlowCollector) this.L$0;
            ConstraintsState[] r12 = (ConstraintsState[]) ((Object[]) this.L$1);
            int r4 = r12.length;
            int r5 = 0;
        L9:
            if (r5 >= r4) goto L14;
            ConstraintsState r6 = r12[r5];
            if (Intrinsics.areEqual(r6, ConstraintsState.ConstraintsMet.INSTANCE) == false) goto L12;
            r5 = r5 + 1;
            goto L9
        L12:
            r2 = r6;
        L14:
            if (r2 != null) goto L16;
            r2 = ConstraintsState.ConstraintsMet.INSTANCE;
        L16:
            this.label = 1;
            if (r92.emit(r2, this) != r0) goto L20;
            return r0;
        }
    }

    public WorkConstraintsTracker$track$$inlined$combine$1(Flow[] r1) {
        this.$flowArray$inlined = r1;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super ConstraintsState> r4, Continuation r5) {
        Flow[] r3 = this.$flowArray$inlined;
        Object r32 = CombineKt.combineInternal(r4, r3, new AnonymousClass2(r3), new AnonymousClass3(null), r5);
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r32;
    L6:
        return Unit.INSTANCE;
    }
}
