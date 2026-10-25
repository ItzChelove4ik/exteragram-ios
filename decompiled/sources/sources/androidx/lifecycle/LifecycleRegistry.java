package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000 <2\u00020\u0001:\u0002=<B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0013\u0010\bJ\u0017\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0014\u0010\bJ\u000f\u0010\u0015\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0017¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0017¢\u0006\u0004\b!\u0010 R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\"R\"\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020$0#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010(R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010\"R\u0016\u0010-\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010\"R\u001c\u0010/\u001a\b\u0012\u0004\u0012\u00020\t0.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\t038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R$\u0010;\u001a\u00020\t2\u0006\u00101\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b8\u00109\"\u0004\b:\u0010\r¨\u0006>"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry;", "Landroidx/lifecycle/Lifecycle;", "Landroidx/lifecycle/LifecycleOwner;", "provider", _UrlKt.FRAGMENT_ENCODE_SET, "enforceMainThread", "<init>", "(Landroidx/lifecycle/LifecycleOwner;Z)V", "(Landroidx/lifecycle/LifecycleOwner;)V", "Landroidx/lifecycle/Lifecycle$State;", "next", _UrlKt.FRAGMENT_ENCODE_SET, "moveToState", "(Landroidx/lifecycle/Lifecycle$State;)V", "Landroidx/lifecycle/LifecycleObserver;", "observer", "calculateTargetState", "(Landroidx/lifecycle/LifecycleObserver;)Landroidx/lifecycle/Lifecycle$State;", "lifecycleOwner", "forwardPass", "backwardPass", "sync", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "methodName", "enforceMainThreadIfNeeded", "(Ljava/lang/String;)V", "Landroidx/lifecycle/Lifecycle$Event;", "event", "handleLifecycleEvent", "(Landroidx/lifecycle/Lifecycle$Event;)V", "addObserver", "(Landroidx/lifecycle/LifecycleObserver;)V", "removeObserver", "Z", "Landroidx/lifecycle/FastSafeIterableMap;", "Landroidx/lifecycle/LifecycleRegistry$ObserverWithState;", "observerMap", "Landroidx/lifecycle/FastSafeIterableMap;", "Landroidx/lifecycle/WeakReference;", "Landroidx/lifecycle/WeakReference;", _UrlKt.FRAGMENT_ENCODE_SET, "addingObserverCounter", "I", "handlingEvent", "newEventOccurred", _UrlKt.FRAGMENT_ENCODE_SET, "parentStates", "Ljava/util/List;", "state", "Landroidx/lifecycle/Lifecycle$State;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "_currentStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "isSynced", "()Z", "getCurrentState", "()Landroidx/lifecycle/Lifecycle$State;", "setCurrentState", "currentState", "Companion", "ObserverWithState", "lifecycle-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLifecycleRegistry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LifecycleRegistry.kt\nandroidx/lifecycle/LifecycleRegistry\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,445:1\n1#2:446\n*E\n"})
/* loaded from: classes.dex */
public class LifecycleRegistry extends Lifecycle {
    public static final Companion Companion = null;
    private final MutableStateFlow<Lifecycle.State> _currentStateFlow;
    private int addingObserverCounter;
    private final boolean enforceMainThread;
    private boolean handlingEvent;
    private final WeakReference<LifecycleOwner> lifecycleOwner;
    private boolean newEventOccurred;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> observerMap;
    private List<Lifecycle.State> parentStates;
    private Lifecycle.State state;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "lifecycle-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry$ObserverWithState;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/lifecycle/LifecycleObserver;", "observer", "Landroidx/lifecycle/Lifecycle$State;", "initialState", "<init>", "(Landroidx/lifecycle/LifecycleObserver;Landroidx/lifecycle/Lifecycle$State;)V", "Landroidx/lifecycle/LifecycleOwner;", "owner", "Landroidx/lifecycle/Lifecycle$Event;", "event", _UrlKt.FRAGMENT_ENCODE_SET, "dispatchEvent", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$Event;)V", "state", "Landroidx/lifecycle/Lifecycle$State;", "getState", "()Landroidx/lifecycle/Lifecycle$State;", "setState", "(Landroidx/lifecycle/Lifecycle$State;)V", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycleObserver", "Landroidx/lifecycle/LifecycleEventObserver;", "getLifecycleObserver", "()Landroidx/lifecycle/LifecycleEventObserver;", "setLifecycleObserver", "(Landroidx/lifecycle/LifecycleEventObserver;)V", "lifecycle-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ObserverWithState {
        private LifecycleEventObserver lifecycleObserver;
        private Lifecycle.State state;

        public ObserverWithState(LifecycleObserver r1, Lifecycle.State r2) {
            this.state = r2;
            this.lifecycleObserver = Lifecycling.lifecycleEventObserver(r1);
        }

        public final void dispatchEvent(LifecycleOwner r3, Lifecycle.Event r4) {
            Lifecycle.State r0 = r4.getTargetState();
            this.state = LifecycleRegistryKt.access$min(this.state, r0);
            this.lifecycleObserver.onStateChanged(r3, r4);
            this.state = r0;
        }

        public final Lifecycle.State getState() {
            return this.state;
        }
    }

    /* renamed from: $r8$lambda$0D5eFGnUA40HVp-9uW0LVcCRwjE, reason: not valid java name */
    public static Unit m749$r8$lambda$0D5eFGnUA40HVp9uW0LVcCRwjE(LifecycleRegistry r4, LifecycleOwner r5, Map.Entry r6) {
        LifecycleObserver r0 = (LifecycleObserver) r6.getKey();
        ObserverWithState r62 = (ObserverWithState) r6.getValue();
    L4:
        if (r62.getState().compareTo(r4.state) <= 0) goto L15;
        if (r4.newEventOccurred == true) goto L15;
        if (r4.observerMap.contains(r0) == false) goto L15;
        Lifecycle.Event r1 = Lifecycle.Event.Companion.downFrom(r62.getState());
        if (r1 == null) goto L12;
        r4.parentStates.add(r1.getTargetState());
        r62.dispatchEvent(r5, r1);
        CollectionsKt.removeLastOrNull(r4.parentStates);
        goto L4
    L12:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("no event down from ", r62.getState());
        return null;
    L15:
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$sIRNHHyujojK-OxRELgp4WNMal8, reason: not valid java name */
    public static Unit m750$r8$lambda$sIRNHHyujojKOxRELgp4WNMal8(LifecycleRegistry r3, LifecycleOwner r4, Map.Entry r5) {
        LifecycleObserver r0 = (LifecycleObserver) r5.getKey();
        ObserverWithState r52 = (ObserverWithState) r5.getValue();
    L4:
        if (r52.getState().compareTo(r3.state) >= 0) goto L15;
        if (r3.newEventOccurred == true) goto L15;
        if (r3.observerMap.contains(r0) == false) goto L15;
        r3.parentStates.add(r52.getState());
        Lifecycle.Event r1 = Lifecycle.Event.Companion.upFrom(r52.getState());
        if (r1 == null) goto L12;
        r52.dispatchEvent(r4, r1);
        CollectionsKt.removeLastOrNull(r3.parentStates);
        goto L4
    L12:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("no event up from ", r52.getState());
        return null;
    L15:
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public LifecycleRegistry(LifecycleOwner r2) {
        this(r2, true);
    }

    private LifecycleRegistry(LifecycleOwner r1, boolean r2) {
        this.enforceMainThread = r2;
        this.observerMap = new FastSafeIterableMap();
        this.lifecycleOwner = new WeakReference(r1);
        this.parentStates = new ArrayList();
        Lifecycle.State r12 = Lifecycle.State.INITIALIZED;
        this.state = r12;
        this._currentStateFlow = StateFlowKt.MutableStateFlow(r12);
    }

    private final void backwardPass(LifecycleOwner r3) {
        this.observerMap.forEachReversed(new LifecycleRegistry$$ExternalSyntheticLambda1(this, r3));
    }

    private final Lifecycle.State calculateTargetState(LifecycleObserver r3) {
        Map.Entry<LifecycleObserver, ObserverWithState> r32 = this.observerMap.ceil(r3);
        Lifecycle.State r0 = null;
        if (r32 == null) goto L7;
        ObserverWithState r33 = r32.getValue();
        if (r33 == null) goto L7;
        Lifecycle.State r34 = r33.getState();
    L9:
        if (this.parentStates.isEmpty() == true) goto L12;
        r0 = this.parentStates.get(r0.size() - 1);
    L12:
        return LifecycleRegistryKt.access$min(LifecycleRegistryKt.access$min(this.state, r34), r0);
    L7:
        r34 = null;
        goto L9
    }

    private final void enforceMainThreadIfNeeded(String r2) {
        if (this.enforceMainThread == true) goto L5;
        return;
    L5:
        if (LifecycleRegistry_androidKt.isMainThread() == true) goto L10;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Method ", r2, " must be called on the main thread");
        return;
    }

    private final void forwardPass(LifecycleOwner r3) {
        this.observerMap.forEachWithAdditions(new LifecycleRegistry$$ExternalSyntheticLambda2(this, r3));
    }

    private final boolean isSynced() {
        if (this.observerMap.size() != 0) goto L5;
        return true;
    L5:
        Lifecycle.State r0 = this.observerMap.first().getValue().getState();
        Lifecycle.State r2 = this.observerMap.last().getValue().getState();
        if (r0 == r2) goto L8;
        return false;
    L8:
        if (this.state != r2) goto L12;
        return true;
    L12:
        return false;
    }

    private final void moveToState(Lifecycle.State r3) {
        if (this.state == r3) goto L17;
        LifecycleRegistryKt.access$checkLifecycleStateTransition(this.lifecycleOwner.get(), this.state, r3);
        this.state = r3;
        if (this.handlingEvent == false) goto L8;
    L14:
        this.newEventOccurred = true;
        return;
    L8:
        if (this.addingObserverCounter != 0) goto L14;
        this.handlingEvent = true;
        sync();
        this.handlingEvent = false;
        if (this.state != Lifecycle.State.DESTROYED) goto L16;
        this.observerMap = new FastSafeIterableMap();
        return;
    L16:
        return;
    }

    private final void sync() {
        LifecycleOwner r0 = this.lifecycleOwner.get();
        if (r0 == null) goto L18;
        LifecycleOwner r02 = r0;
    L6:
        if (isSynced() == true) goto L16;
        this.newEventOccurred = false;
        if (this.state.compareTo(this.observerMap.first().getValue().getState()) >= 0) goto L10;
        backwardPass(r02);
    L10:
        Map.Entry<LifecycleObserver, ObserverWithState> r1 = this.observerMap.lastOrNull();
        if (this.newEventOccurred == true) goto L6;
        if (r1 == null) goto L6;
        if (this.state.compareTo(r1.getValue().getState()) <= 0) goto L6;
        forwardPass(r02);
        goto L6
    L16:
        this.newEventOccurred = false;
        this._currentStateFlow.setValue(getCurrentState());
        return;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
    }

    @Override // androidx.lifecycle.Lifecycle
    public void addObserver(LifecycleObserver r7) {
        enforceMainThreadIfNeeded("addObserver");
        Lifecycle.State r0 = this.state;
        Lifecycle.State r1 = Lifecycle.State.DESTROYED;
        if (r0 == r1) goto L6;
        r1 = Lifecycle.State.INITIALIZED;
    L6:
        ObserverWithState r02 = new ObserverWithState(r7, r1);
        if (this.observerMap.putIfAbsent(r7, r02) != null) goto L36;
        LifecycleOwner r12 = this.lifecycleOwner.get();
        if (r12 != null) goto L13;
        return;
    L13:
        if (this.addingObserverCounter == 0) goto L15;
    L18:
        boolean r2 = true;
    L19:
        Lifecycle.State r4 = calculateTargetState(r7);
        this.addingObserverCounter++;
    L21:
        if (r02.getState().compareTo(r4) >= 0) goto L29;
        if (this.observerMap.contains(r7) == false) goto L29;
        this.parentStates.add(r02.getState());
        Lifecycle.Event r3 = Lifecycle.Event.Companion.upFrom(r02.getState());
        if (r3 == null) goto L27;
        r02.dispatchEvent(r12, r3);
        CollectionsKt.removeLastOrNull(this.parentStates);
        r4 = calculateTargetState(r7);
        goto L21
    L27:
        CstInsn$$ExternalSyntheticBUOutline0.m("no event up from ", r02.getState());
        return;
    L29:
        if (r2 == true) goto L31;
        sync();
    L31:
        this.addingObserverCounter--;
        return;
    L15:
        if (this.handlingEvent == true) goto L18;
        r2 = false;
        goto L19
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State getCurrentState() {
        return this.state;
    }

    public void handleLifecycleEvent(Lifecycle.Event r2) {
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(r2.getTargetState());
    }

    @Override // androidx.lifecycle.Lifecycle
    public void removeObserver(LifecycleObserver r2) {
        enforceMainThreadIfNeeded("removeObserver");
        this.observerMap.remove(r2);
    }

    public void setCurrentState(Lifecycle.State r2) {
        enforceMainThreadIfNeeded("setCurrentState");
        moveToState(r2);
    }
}
