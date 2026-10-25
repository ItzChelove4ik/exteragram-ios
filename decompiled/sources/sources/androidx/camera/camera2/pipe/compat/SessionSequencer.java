package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.sync.Mutex;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/pipe/compat/SessionSequencer;", _UrlKt.FRAGMENT_ENCODE_SET, "concurrentSequencer", "Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;", "<init>", "(Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;)V", "state", "Lkotlinx/atomicfu/AtomicRef;", "Landroidx/camera/camera2/pipe/compat/SessionSequencer$State;", "awaitSessionLock", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", BuildConfig.BUILD_TYPE, "State", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SessionSequencer {
    private final ConcurrentSessionSequencer concurrentSequencer;
    private final AtomicRef<State> state;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/pipe/compat/SessionSequencer$State;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "PENDING", "CREATING", "CREATED", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum State extends Enum<State> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State CREATED = null;
        public static final State CREATING = null;
        public static final State PENDING = null;

        private static final /* synthetic */ State[] $values() {
            return new State[]{PENDING, CREATING, CREATED};
        }

        static {
            PENDING = new State("PENDING", 0);
            CREATING = new State("CREATING", 1);
            CREATED = new State("CREATED", 2);
            State[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.SessionSequencer", f = "ConcurrentSessionSequencers.kt", i = {}, l = {98}, m = "awaitSessionLock", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.SessionSequencer$awaitSessionLock$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SessionSequencer this$0;

        public AnonymousClass1(SessionSequencer r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.awaitSessionLock(this);
        }
    }

    public SessionSequencer(ConcurrentSessionSequencer r1) {
        this.concurrentSequencer = r1;
        this.state = AtomicFU.atomic(State.PENDING);
    }

    public final Object awaitSessionLock(Continuation<? super Unit> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r62);
    L18:
        if (this.state.compareAndSet(State.PENDING, State.CREATING) == true) goto L21;
        Mutex.DefaultImpls.unlock$default(this.concurrentSequencer.getSharedMutex(), null, 1, null);
    L21:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        Mutex r63 = this.concurrentSequencer.getSharedMutex();
        r0.label = 1;
        if (Mutex.DefaultImpls.lock$default(r63, null, r0, 1, null) != r12) goto L18;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }

    public final void release() {
        if (this.state.getAndSet(State.CREATED) != State.CREATING) goto L6;
        Mutex.DefaultImpls.unlock$default(this.concurrentSequencer.getSharedMutex(), null, 1, null);
        return;
    }
}
