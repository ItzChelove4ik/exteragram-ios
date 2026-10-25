package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintsState;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0011\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u0005\u0010\u0003J!\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J;\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u001e2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00062\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00040\u001aj\u0002`\u001c¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R0\u0010%\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00040\u001aj\u0002`\u001c\u0012\u0004\u0012\u00020\u00060$8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b%\u0010&R$\u0010'\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u0010-\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b3\u00104¨\u00065"}, d2 = {"Landroidx/work/impl/constraints/SharedNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "dispatchOnConstraintState", "Landroid/net/NetworkRequest;", PluginsConstants.REQUEST, "Landroid/net/NetworkCapabilities;", "capabilities", _UrlKt.FRAGMENT_ENCODE_SET, "areNetworkConstraintsSatisfied", "(Landroid/net/NetworkRequest;Landroid/net/NetworkCapabilities;)Z", "Landroid/net/Network;", "network", "networkCapabilities", "onCapabilitiesChanged", "(Landroid/net/Network;Landroid/net/NetworkCapabilities;)V", "blocked", "onBlockedStatusChanged", "(Landroid/net/Network;Z)V", "onLost", "(Landroid/net/Network;)V", "Landroid/net/ConnectivityManager;", "connManager", "networkRequest", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "Landroidx/work/impl/constraints/OnConstraintState;", "onConstraintState", "Lkotlin/Function0;", "addCallback", "(Landroid/net/ConnectivityManager;Landroid/net/NetworkRequest;Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "requestsLock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "requests", "Ljava/util/Map;", "cachedCapabilities", "Landroid/net/NetworkCapabilities;", "getCachedCapabilities", "()Landroid/net/NetworkCapabilities;", "setCachedCapabilities", "(Landroid/net/NetworkCapabilities;)V", "capabilitiesInitialized", "Z", "getCapabilitiesInitialized", "()Z", "setCapabilitiesInitialized", "(Z)V", "isBlocked", "Ljava/lang/Boolean;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkConstraintsTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkConstraintsTracker.kt\nandroidx/work/impl/constraints/SharedNetworkCallback\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,394:1\n1869#2,2:395\n1869#2,2:397\n1869#2,2:399\n*S KotlinDebug\n*F\n+ 1 WorkConstraintsTracker.kt\nandroidx/work/impl/constraints/SharedNetworkCallback\n*L\n312#1:395,2\n331#1:397,2\n341#1:399,2\n*E\n"})
/* loaded from: classes4.dex */
final class SharedNetworkCallback extends ConnectivityManager.NetworkCallback {
    public static final SharedNetworkCallback INSTANCE = null;
    private static NetworkCapabilities cachedCapabilities;
    private static boolean capabilitiesInitialized;
    private static Boolean isBlocked;
    private static final Map<Function1<ConstraintsState, Unit>, NetworkRequest> requests = null;
    private static final Object requestsLock = null;

    public static Unit $r8$lambda$Q7h7Cx5HQAZzRkjy7fwCyD8Gxsg(Function1 r3, ConnectivityManager r4) {
        Object r0 = requestsLock;
        monitor-enter(r0);
        Map<Function1<ConstraintsState, Unit>, NetworkRequest> r1 = requests;     // Catch: Throwable -> L8
        r1.remove(r3);     // Catch: Throwable -> L8
        if (r1.isEmpty() == false) goto L10;
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController unregister shared callback");     // Catch: Throwable -> L8
        r4.unregisterNetworkCallback(INSTANCE);     // Catch: Throwable -> L8
        isBlocked = null;     // Catch: Throwable -> L8
        cachedCapabilities = null;     // Catch: Throwable -> L8
        capabilitiesInitialized = false;     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);
        return Unit.INSTANCE;
    L8:
        th = move-exception;
        throw th;
    }

    static {
        INSTANCE = new SharedNetworkCallback();
        requestsLock = new Object();
        requests = new LinkedHashMap();
    }

    private SharedNetworkCallback() {
    }

    private final boolean areNetworkConstraintsSatisfied(NetworkRequest r1, NetworkCapabilities r2) {
        if (isBlocked.booleanValue() == false) goto L5;
        return false;
    L5:
        if (r1.canBeSatisfiedBy(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private final void dispatchOnConstraintState() {
        ArrayList r0 = new ArrayList();
        Object r1 = requestsLock;
        monitor-enter(r1);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (capabilitiesInitialized == true) goto L7;
    L25:
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "Not dispatching constraint state yet: isBlocked=" + isBlocked + ", capabilitiesInitialized=" + capabilitiesInitialized);     // Catch: Throwable -> L15
        monitor-exit(r1);
        return;
    L7:
        if (isBlocked == null) goto L25;
        Iterator<T> r6 = requests.entrySet().iterator();     // Catch: Throwable -> L15
    L11:
        if (r6.hasNext() == false) goto L19;
        Map.Entry r2 = (Map.Entry) r6.next();     // Catch: Throwable -> L15
        Function1 r3 = (Function1) r2.getKey();     // Catch: Throwable -> L15
        if (INSTANCE.areNetworkConstraintsSatisfied((NetworkRequest) r2.getValue(), cachedCapabilities) == false) goto L17;
        Object r22 = ConstraintsState.ConstraintsMet.INSTANCE;     // Catch: Throwable -> L15
    L18:
        r0.add(TuplesKt.to(r3, r22));     // Catch: Throwable -> L15
        goto L11
    L17:
        r22 = new ConstraintsState.ConstraintsNotMet(7);     // Catch: Throwable -> L15
        goto L18
    L19:
        Unit r62 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r1);
        int r63 = r0.size();
        int r12 = 0;
    L22:
        if (r12 >= r63) goto L24;
        Object r23 = r0.get(r12);
        r12 = r12 + 1;
        Pair r24 = (Pair) r23;
        ((Function1) r24.component1()).invoke((ConstraintsState) r24.component2());
        goto L22
    }

    public final Function0<Unit> addCallback(ConnectivityManager r4, NetworkRequest r5, Function1<? super ConstraintsState, Unit> r6) {
        Object r3 = requestsLock;
        monitor-enter(r3);
        Map<Function1<ConstraintsState, Unit>, NetworkRequest> r0 = requests;     // Catch: Throwable -> L7
        boolean r1 = r0.isEmpty();     // Catch: Throwable -> L7
        r0.put(r6, r5);     // Catch: Throwable -> L7
        if (r1 == false) goto L10;
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController register shared callback");     // Catch: Throwable -> L7
        r4.registerDefaultNetworkCallback(INSTANCE);     // Catch: Throwable -> L7
    L18:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r3);
        return new SharedNetworkCallback$$ExternalSyntheticLambda0(r6, r4);
    L10:
        if (capabilitiesInitialized == false) goto L18;
        if (isBlocked == null) goto L18;
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController send initial capabilities");     // Catch: Throwable -> L7
        NetworkCapabilities r02 = cachedCapabilities;     // Catch: Throwable -> L7
        if (INSTANCE.areNetworkConstraintsSatisfied(r5, r02) == false) goto L16;
        ConstraintsState r53 = ConstraintsState.ConstraintsMet.INSTANCE;     // Catch: Throwable -> L7
    L17:
        r6.invoke(r53);     // Catch: Throwable -> L7
        goto L18
    L16:
        r53 = new ConstraintsState.ConstraintsNotMet(7);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onBlockedStatusChanged(Network r4, boolean r5) {
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController onBlockedStatusChanged callback " + r5);
        Object r42 = requestsLock;
        monitor-enter(r42);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (Intrinsics.areEqual(isBlocked, Boolean.valueOf(r5)) == false) goto L8;
        monitor-exit(r42);
        return;
    L8:
        isBlocked = Boolean.valueOf(r5);     // Catch: Throwable -> L12
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r42);
        dispatchOnConstraintState();
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network r3, NetworkCapabilities r4) {
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController onCapabilitiesChanged callback");
        Object r32 = requestsLock;
        monitor-enter(r32);
        cachedCapabilities = r4;     // Catch: Throwable -> L8
        capabilitiesInitialized = true;     // Catch: Throwable -> L8
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r32);
        dispatchOnConstraintState();
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network r4) {
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "NetworkRequestConstraintController onLost callback");
        Object r3 = requestsLock;
        monitor-enter(r3);
        cachedCapabilities = null;     // Catch: Throwable -> L9
        Iterator<T> r42 = requests.keySet().iterator();     // Catch: Throwable -> L9
    L7:
        if (r42.hasNext() == false) goto L11;
        ((Function1) r42.next()).invoke(new ConstraintsState.ConstraintsNotMet(7));     // Catch: Throwable -> L9
        goto L7
    L11:
        Unit r43 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r3);
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
