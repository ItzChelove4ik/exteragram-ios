package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00012\u00060\u0003j\u0002`\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00028\u0000H$¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b2\u0006\u0010\n\u001a\u00020\tH$¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u000e\u0010\bJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0011\u0010\u0012R8\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000b2\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000b8\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t8\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u0019R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "S", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/SynchronizedObject;", "<init>", "()V", "createSlot", "()Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "createSlotArray", "(I)[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "allocateSlot", "slot", _UrlKt.FRAGMENT_ENCODE_SET, "freeSlot", "(Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;)V", "value", "slots", "[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "getSlots", "()[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "nCollectors", "I", "getNCollectors", "()I", "nextIndex", "Lkotlinx/coroutines/flow/internal/SubscriptionCountStateFlow;", "_subscriptionCount", "Lkotlinx/coroutines/flow/internal/SubscriptionCountStateFlow;", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "subscriptionCount", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAbstractSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/AbstractSharedFlow\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,130:1\n26#2:131\n26#2:133\n26#2:136\n16#3:132\n16#3:134\n16#3:137\n1#4:135\n13805#5,2:138\n*S KotlinDebug\n*F\n+ 1 AbstractSharedFlow.kt\nkotlinx/coroutines/flow/internal/AbstractSharedFlow\n*L\n27#1:131\n42#1:133\n73#1:136\n27#1:132\n42#1:134\n73#1:137\n92#1:138,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {
    private SubscriptionCountStateFlow _subscriptionCount;
    private int nCollectors;
    private int nextIndex;
    private S[] slots;

    public AbstractSharedFlow() {
    }

    public static final /* synthetic */ int access$getNCollectors(AbstractSharedFlow r0) {
        return r0.nCollectors;
    }

    public static final /* synthetic */ AbstractSharedFlowSlot[] access$getSlots(AbstractSharedFlow r0) {
        return r0.slots;
    }

    public final S allocateSlot() {
        monitor-enter(this);
        S[] r0 = this.slots;     // Catch: Throwable -> L6
        if (r0 != null) goto L9;
        r0 = (S[]) createSlotArray(2);
        this.slots = r0;     // Catch: Throwable -> L6
    L11:
        int r1 = this.nextIndex;     // Catch: Throwable -> L6
    L12:
        S r2 = r0[r1];     // Catch: Throwable -> L6
        if (r2 != null) goto L15;
        r2 = (S) createSlot();
        r0[r1] = r2;     // Catch: Throwable -> L6
    L15:
        r1 = r1 + 1;     // Catch: Throwable -> L6
        if (r1 < r0.length) goto L19;
        r1 = 0;
    L19:
        if (r2.allocateLocked(this) == false) goto L12;
        this.nextIndex = r1;     // Catch: Throwable -> L6
        this.nCollectors++;
        SubscriptionCountStateFlow r02 = this._subscriptionCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        if (r02 == null) goto L24;
        r02.increment(1);
    L24:
        return r2;
    L9:
        if (this.nCollectors < r0.length) goto L11;
        Object[] r03 = Arrays.copyOf(r0, r0.length * 2);     // Catch: Throwable -> L6
        this.slots = (S[]) ((AbstractSharedFlowSlot[]) r03);     // Catch: Throwable -> L6
        r0 = (S[]) ((AbstractSharedFlowSlot[]) r03);
    L6:
        th = move-exception;
        throw th;
    }

    public abstract S createSlot();

    public abstract S[] createSlotArray(int r1);

    public final void freeSlot(S r6) {
        monitor-enter(this);
        int r0 = this.nCollectors - 1;
        this.nCollectors = r0;     // Catch: Throwable -> L6
        SubscriptionCountStateFlow r2 = this._subscriptionCount;     // Catch: Throwable -> L6
        int r3 = 0;
        if (r0 != 0) goto L8;
        this.nextIndex = 0;     // Catch: Throwable -> L6
    L8:
        Continuation<Unit>[] r62 = r6.freeLocked(this);     // Catch: Throwable -> L6
        monitor-exit(this);
        int r5 = r62.length;
    L11:
        if (r3 >= r5) goto L16;
        Continuation<Unit> r02 = r62[r3];
        if (r02 == null) goto L15;
        Result.Companion r4 = Result.Companion;
        r02.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
    L15:
        r3 = r3 + 1;
        goto L11
    L16:
        if (r2 == null) goto L26;
        r2.increment(-1);
        return;
    L26:
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final int getNCollectors() {
        return this.nCollectors;
    }

    public final S[] getSlots() {
        return this.slots;
    }

    public final StateFlow<Integer> getSubscriptionCount() {
        monitor-enter(this);
        SubscriptionCountStateFlow r0 = this._subscriptionCount;     // Catch: Throwable -> L7
        if (r0 != null) goto L9;
        r0 = new SubscriptionCountStateFlow(this.nCollectors);     // Catch: Throwable -> L7
        this._subscriptionCount = r0;     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }
}
