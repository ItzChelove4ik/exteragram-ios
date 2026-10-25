package androidx.room;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,111:1\n47#2,5:112\n*E\n"})
/* loaded from: classes4.dex */
public final class MultiInstanceInvalidationClient$createFlow$$inlined$mapNotNull$1 implements Flow<Set<? extends String>> {
    final /* synthetic */ String[] $resolvedTableNames$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", _UrlKt.FRAGMENT_ENCODE_SET, "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 1, 0})
    @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 MultiInstanceInvalidationClient.android.kt\nandroidx/room/MultiInstanceInvalidationClient\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,218:1\n57#2:219\n58#2:235\n134#3,2:220\n136#3:223\n137#3,4:225\n141#3:230\n142#3,2:232\n13472#4:222\n13473#4:231\n1869#5:224\n1870#5:229\n1#6:234\n*S KotlinDebug\n*F\n+ 1 MultiInstanceInvalidationClient.android.kt\nandroidx/room/MultiInstanceInvalidationClient\n*L\n135#1:222\n135#1:231\n136#1:224\n136#1:229\n*E\n"})
    /* renamed from: androidx.room.MultiInstanceInvalidationClient$createFlow$$inlined$mapNotNull$1$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {
        final /* synthetic */ String[] $resolvedTableNames$inlined;
        final /* synthetic */ FlowCollector $this_unsafeFlow;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.MultiInstanceInvalidationClient$createFlow$$inlined$mapNotNull$1$2", f = "MultiInstanceInvalidationClient.android.kt", i = {}, l = {235}, m = "emit", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1$emit$1\n*L\n1#1,218:1\n*E\n"})
        /* renamed from: androidx.room.MultiInstanceInvalidationClient$createFlow$$inlined$mapNotNull$1$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends ContinuationImpl {
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ AnonymousClass2 this$0;

            public AnonymousClass1(AnonymousClass2 r1, Continuation r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r2) {
                this.result = r2;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector r1, String[] r2) {
            this.$this_unsafeFlow = r1;
            this.$resolvedTableNames$inlined = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v2, types: [java.util.Collection, java.util.Set] */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object r11, Continuation r12) {
            if ((r12 instanceof AnonymousClass1) == false) goto L7;
            AnonymousClass1 r0 = (AnonymousClass1) r12;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r122 = r0.result;
            Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            T r3 = null;
            if (r2 == 0) goto L14;
            if (r2 != 1) goto L12;
            ResultKt.throwOnFailure(r122);
        L32:
            return Unit.INSTANCE;
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L14:
            ResultKt.throwOnFailure(r122);
            FlowCollector r123 = this.$this_unsafeFlow;
            Set r112 = (Set) r11;
            Set r22 = SetsKt.createSetBuilder();
            String[] r10 = this.$resolvedTableNames$inlined;
            int r5 = r10.length;
            int r6 = 0;
        L15:
            if (r6 >= r5) goto L23;
            String r7 = r10[r6];
            Iterator<T> r8 = r112.iterator();
        L18:
            if (r8.hasNext() == false) goto L22;
            if (StringsKt.equals(r7, (String) r8.next(), true) == false) goto L18;
            r22.add(r7);
            goto L18
        L22:
            r6 = r6 + 1;
            goto L15
        L23:
            ?? r102 = SetsKt.build(r22);
            if (r102.isEmpty() == true) goto L27;
            r3 = r102;
        L27:
            if (r3 == null) goto L32;
            r0.label = 1;
            if (r123.emit(r3, r0) != r13) goto L32;
            return r13;
        L7:
            r0 = new AnonymousClass1(this, r12);
            goto L8
        }
    }

    public MultiInstanceInvalidationClient$createFlow$$inlined$mapNotNull$1(Flow r1, String[] r2) {
        this.$this_unsafeTransform$inlined = r1;
        this.$resolvedTableNames$inlined = r2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super Set<? extends String>> r3, Continuation r4) {
        Object r2 = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(r3, this.$resolvedTableNames$inlined), r4);
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r2;
    L6:
        return Unit.INSTANCE;
    }
}
