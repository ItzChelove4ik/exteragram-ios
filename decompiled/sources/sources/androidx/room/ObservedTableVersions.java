package androidx.room;

import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fJ\u001c\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0010H\u0086@¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/room/ObservedTableVersions;", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "versions", "Lkotlinx/coroutines/flow/MutableStateFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "increment", _UrlKt.FRAGMENT_ENCODE_SET, "tableIds", _UrlKt.FRAGMENT_ENCODE_SET, "collect", _UrlKt.FRAGMENT_ENCODE_SET, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInvalidationTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/ObservedTableVersions\n+ 2 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowKt\n*L\n1#1,640:1\n226#2,5:641\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/ObservedTableVersions\n*L\n626#1:641,5\n*E\n"})
/* loaded from: classes.dex */
public final class ObservedTableVersions {
    private final MutableStateFlow<int[]> versions;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.ObservedTableVersions", f = "InvalidationTracker.kt", i = {}, l = {638}, m = "collect", n = {}, s = {})
    /* renamed from: androidx.room.ObservedTableVersions$collect$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ ObservedTableVersions this$0;

        public AnonymousClass1(ObservedTableVersions r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.collect(null, this);
        }
    }

    public ObservedTableVersions(int r1) {
        this.versions = StateFlowKt.MutableStateFlow(new int[r1]);
    }

    public final Object collect(FlowCollector<? super int[]> r6, Continuation<?> r7) {
        if ((r7 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L13;
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L13:
        ResultKt.throwOnFailure(r72);
    L17:
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        ResultKt.throwOnFailure(r72);
        MutableStateFlow<int[]> r5 = this.versions;
        r0.label = 1;
        if (r5.collect(r6, r0) != r12) goto L17;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r7);
        goto L8
    }

    public final void increment(Set<Integer> r7) {
        if (r7.isEmpty() == true) goto L15;
        MutableStateFlow<int[]> r6 = this.versions;
    L6:
        int[] r0 = r6.getValue();
        int[] r1 = r0;
        int r2 = r1.length;
        int[] r3 = new int[r2];
        int r4 = 0;
    L7:
        if (r4 >= r2) goto L14;
        if (r7.contains(Integer.valueOf(r4)) == false) goto L11;
        int r5 = r1[r4] + 1;
    L12:
        r3[r4] = r5;
        r4 = r4 + 1;
        goto L7
    L11:
        r5 = r1[r4];
        goto L12
    L14:
        if (r6.compareAndSet(r0, r3) == false) goto L6;
        return;
    }
}
