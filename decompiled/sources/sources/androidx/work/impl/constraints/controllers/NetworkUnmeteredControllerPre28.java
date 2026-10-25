package androidx.work.impl.constraints.controllers;

import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Landroidx/work/impl/constraints/controllers/NetworkUnmeteredControllerPre28;", "Landroidx/work/impl/constraints/controllers/BaseConstraintController;", "Landroidx/work/impl/constraints/NetworkState;", "tracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "<init>", "(Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "reason", _UrlKt.FRAGMENT_ENCODE_SET, "getReason", "()I", "hasConstraint", _UrlKt.FRAGMENT_ENCODE_SET, "workSpec", "Landroidx/work/impl/model/WorkSpec;", "isConstrained", "value", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NetworkUnmeteredControllerPre28 extends BaseConstraintController<NetworkState> {
    private final int reason;

    public NetworkUnmeteredControllerPre28(ConstraintTracker<NetworkState> r1) {
        super(r1);
        this.reason = 7;
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public int getReason() {
        return this.reason;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec r1) {
        if (r1.constraints.getRequiredNetworkType() != NetworkType.UNMETERED) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: isConstrained, reason: avoid collision after fix types in other method */
    public boolean isConstrained2(NetworkState r1) {
        if (r1.isConnected() == true) goto L5;
        return true;
    L5:
        if (r1.isMetered() == false) goto L7;
        return true;
    L7:
        if (r1.isBlocked() == true) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public /* bridge */ /* synthetic */ boolean isConstrained(NetworkState r1) {
        return isConstrained2(r1);
    }
}
