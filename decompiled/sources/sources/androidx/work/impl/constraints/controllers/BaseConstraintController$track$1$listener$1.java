package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.ConstraintsState;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0015\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"androidx/work/impl/constraints/controllers/BaseConstraintController$track$1$listener$1", "Landroidx/work/impl/constraints/ConstraintListener;", "onConstraintChanged", _UrlKt.FRAGMENT_ENCODE_SET, "newValue", "(Ljava/lang/Object;)V", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BaseConstraintController$track$1$listener$1<T> implements ConstraintListener<T> {
    final /* synthetic */ ProducerScope<ConstraintsState> $$this$callbackFlow;
    final /* synthetic */ BaseConstraintController<T> this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseConstraintController$track$1$listener$1(BaseConstraintController<T> r1, ProducerScope<? super ConstraintsState> r2) {
        this.this$0 = r1;
        this.$$this$callbackFlow = r2;
    }

    @Override // androidx.work.impl.constraints.ConstraintListener
    public void onConstraintChanged(T r2) {
        if (this.this$0.isConstrained(r2) == false) goto L5;
        ConstraintsState r22 = new ConstraintsState.ConstraintsNotMet(this.this$0.getReason());
    L6:
        this.$$this$callbackFlow.getChannel().mo3532trySendJP2dKIU(r22);
        return;
    L5:
        r22 = ConstraintsState.ConstraintsMet.INSTANCE;
        goto L6
    }
}
