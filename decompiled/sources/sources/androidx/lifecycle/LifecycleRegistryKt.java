package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0002\u001a\"\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0002¨\u0006\n"}, d2 = {"min", "Landroidx/lifecycle/Lifecycle$State;", "state1", "state2", "checkLifecycleStateTransition", _UrlKt.FRAGMENT_ENCODE_SET, "owner", "Landroidx/lifecycle/LifecycleOwner;", "current", "next", "lifecycle-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class LifecycleRegistryKt {
    public static final /* synthetic */ void access$checkLifecycleStateTransition(LifecycleOwner r0, Lifecycle.State r1, Lifecycle.State r2) {
        checkLifecycleStateTransition(r0, r1, r2);
    }

    public static final /* synthetic */ Lifecycle.State access$min(Lifecycle.State r0, Lifecycle.State r1) {
        return min(r0, r1);
    }

    private static final void checkLifecycleStateTransition(LifecycleOwner r3, Lifecycle.State r4, Lifecycle.State r5) {
        if (r4 == Lifecycle.State.INITIALIZED) goto L5;
    L9:
        Lifecycle.State r0 = Lifecycle.State.DESTROYED;
        if (r4 != r0) goto L15;
        if (r4 != r5) goto L14;
        return;
    L14:
        throw new IllegalStateException(("State is '" + r0 + "' and cannot be moved to `" + r5 + "` in component " + r3).toString());
    L15:
        return;
    L5:
        if (r5 != Lifecycle.State.DESTROYED) goto L9;
        throw new IllegalStateException(("State must be at least '" + Lifecycle.State.CREATED + "' to be moved to '" + r5 + "' in component " + r3).toString());
    }

    private static final Lifecycle.State min(Lifecycle.State r1, Lifecycle.State r2) {
        if (r2 != null) goto L4;
    L6:
        return r1;
    L4:
        if (r2.compareTo(r1) >= 0) goto L6;
        return r2;
    }
}
