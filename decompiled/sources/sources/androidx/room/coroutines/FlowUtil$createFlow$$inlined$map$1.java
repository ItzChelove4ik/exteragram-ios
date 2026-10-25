package androidx.room.coroutines;

import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import com.android.dx.io.Opcodes;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [R] */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,111:1\n47#2,5:112\n*E\n"})
/* loaded from: classes.dex */
public final class FlowUtil$createFlow$$inlined$map$1<R> implements Flow<R> {
    final /* synthetic */ Function1 $block$inlined;
    final /* synthetic */ RoomDatabase $db$inlined;
    final /* synthetic */ boolean $inTransaction$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", _UrlKt.FRAGMENT_ENCODE_SET, "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 1, 0})
    @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 FlowBuilder.kt\nandroidx/room/coroutines/FlowUtil\n*L\n1#1,218:1\n50#2:219\n38#3:220\n*E\n"})
    /* renamed from: androidx.room.coroutines.FlowUtil$createFlow$$inlined$map$1$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {
        final /* synthetic */ Function1 $block$inlined;
        final /* synthetic */ RoomDatabase $db$inlined;
        final /* synthetic */ boolean $inTransaction$inlined;
        final /* synthetic */ FlowCollector $this_unsafeFlow;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.coroutines.FlowUtil$createFlow$$inlined$map$1$2", f = "FlowBuilder.kt", i = {}, l = {Opcodes.REM_INT_LIT8, Opcodes.DIV_INT_LIT8}, m = "emit", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1$emit$1\n*L\n1#1,218:1\n*E\n"})
        /* renamed from: androidx.room.coroutines.FlowUtil$createFlow$$inlined$map$1$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
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

        public AnonymousClass2(FlowCollector r1, RoomDatabase r2, boolean r3, Function1 r4) {
            this.$this_unsafeFlow = r1;
            this.$db$inlined = r2;
            this.$inTransaction$inlined = r3;
            this.$block$inlined = r4;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object r8, Continuation r9) {
            if ((r9 instanceof AnonymousClass1) == false) goto L7;
            AnonymousClass1 r0 = (AnonymousClass1) r9;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r92 = r0.result;
            Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            if (r2 == 0) goto L16;
            if (r2 == 1) goto L15;
            if (r2 != 2) goto L13;
            ResultKt.throwOnFailure(r92);
        L24:
            return Unit.INSTANCE;
        L13:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L15:
            FlowCollector r7 = (FlowCollector) r0.L$0;
            ResultKt.throwOnFailure(r92);
            FlowCollector r72 = r7;
        L20:
            r0.L$0 = null;
            r0.label = 2;
            if (r72.emit(r92, r0) != r12) goto L24;
        L22:
            return r12;
        L16:
            ResultKt.throwOnFailure(r92);
            FlowCollector r93 = this.$this_unsafeFlow;
            Set r82 = (Set) r8;
            RoomDatabase r83 = this.$db$inlined;
            boolean r22 = this.$inTransaction$inlined;
            Function1 r73 = this.$block$inlined;
            r0.L$0 = r93;
            r0.label = 1;
            Object r74 = DBUtil.performSuspending(r83, true, r22, r73, r0);
            if (r74 == r12) goto L22;
            r92 = r74;
            r72 = r93;
        L7:
            r0 = new AnonymousClass1(this, r9);
            goto L8
        }
    }

    public FlowUtil$createFlow$$inlined$map$1(Flow r1, RoomDatabase r2, boolean r3, Function1 r4) {
        this.$this_unsafeTransform$inlined = r1;
        this.$db$inlined = r2;
        this.$inTransaction$inlined = r3;
        this.$block$inlined = r4;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector r5, Continuation r6) {
        Object r4 = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(r5, this.$db$inlined, this.$inTransaction$inlined, this.$block$inlined), r6);
        if (r4 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r4;
    L6:
        return Unit.INSTANCE;
    }
}
