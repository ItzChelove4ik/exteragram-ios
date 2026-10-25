package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0}, l = {306}, m = "syncTriggers$room_runtime", n = {"$this$ifNotClosed$iv"}, s = {"L$0"})
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$syncTriggers$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TriggerBasedInvalidationTracker this$0;

    public TriggerBasedInvalidationTracker$syncTriggers$1(TriggerBasedInvalidationTracker r1, Continuation<? super TriggerBasedInvalidationTracker$syncTriggers$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.syncTriggers$room_runtime(this);
    }
}
