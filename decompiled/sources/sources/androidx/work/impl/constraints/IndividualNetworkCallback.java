package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintsState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB!\b\u0002\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/constraints/IndividualNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "onConstraintState", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/constraints/OnConstraintState;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "onCapabilitiesChanged", "network", "Landroid/net/Network;", "networkCapabilities", "Landroid/net/NetworkCapabilities;", "onLost", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
final class IndividualNetworkCallback extends ConnectivityManager.NetworkCallback {
    public static final Companion Companion = null;
    private final Function1<ConstraintsState, Unit> onConstraintState;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\fj\u0002`\u000e¨\u0006\u000f"}, d2 = {"Landroidx/work/impl/constraints/IndividualNetworkCallback$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "addCallback", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "connManager", "Landroid/net/ConnectivityManager;", "networkRequest", "Landroid/net/NetworkRequest;", "onConstraintState", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "Landroidx/work/impl/constraints/OnConstraintState;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        /* renamed from: $r8$lambda$1NfazpWnJwNEPqnnR-ro2tZElFI, reason: not valid java name */
        public static Unit m982$r8$lambda$1NfazpWnJwNEPqnnRro2tZElFI(Ref.BooleanRef r2, ConnectivityManager r3, IndividualNetworkCallback r4) {
            if (r2.element == false) goto L6;
            Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController unregister callback");
            r3.unregisterNetworkCallback(r4);
        L6:
            return Unit.INSTANCE;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Function0<Unit> addCallback(ConnectivityManager r7, NetworkRequest r8, Function1<? super ConstraintsState, Unit> r9) {
            IndividualNetworkCallback r6 = new IndividualNetworkCallback(r9, null);
            Ref.BooleanRef r1 = new Ref.BooleanRef();
            Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController register callback");     // Catch: RuntimeException -> L5
            r7.registerNetworkCallback(r8, r6);     // Catch: RuntimeException -> L5
            r1.element = true;     // Catch: RuntimeException -> L5
        L10:
            return new IndividualNetworkCallback$Companion$$ExternalSyntheticLambda0(r1, r7, r6);
        L5:
            e = move-exception;
            if (StringsKt.endsWith$default(e.getClass().getName(), "TooManyRequestsException", false, 2, null) == false) goto L11;
            Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController couldn't register callback", e);
            r9.invoke(new ConstraintsState.ConstraintsNotMet(7));
            goto L10
        L11:
            throw e;
        }
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private IndividualNetworkCallback(Function1<? super ConstraintsState, Unit> r1) {
        this.onConstraintState = r1;
    }

    public /* synthetic */ IndividualNetworkCallback(Function1 r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network r2, NetworkCapabilities r3) {
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController onCapabilitiesChanged callback");
        this.onConstraintState.invoke(ConstraintsState.ConstraintsMet.INSTANCE);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network r3) {
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController onLost callback");
        this.onConstraintState.invoke(new ConstraintsState.ConstraintsNotMet(7));
    }
}
