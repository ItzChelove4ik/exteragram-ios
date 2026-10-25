package androidx.lifecycle;

import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H'¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H'¢\u0006\u0004\b\t\u0010\bR*\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n8\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0014\u001a\u00020\u00118'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Landroidx/lifecycle/Lifecycle;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/lifecycle/LifecycleObserver;", "observer", _UrlKt.FRAGMENT_ENCODE_SET, "addObserver", "(Landroidx/lifecycle/LifecycleObserver;)V", "removeObserver", "Landroidx/lifecycle/AtomicReference;", "internalScopeRef", "Landroidx/lifecycle/AtomicReference;", "getInternalScopeRef", "()Landroidx/lifecycle/AtomicReference;", "setInternalScopeRef", "(Landroidx/lifecycle/AtomicReference;)V", "Landroidx/lifecycle/Lifecycle$State;", "getCurrentState", "()Landroidx/lifecycle/Lifecycle$State;", "currentState", "Event", "State", "lifecycle-common"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLifecycle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Lifecycle.kt\nandroidx/lifecycle/Lifecycle\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,400:1\n1#2:401\n*E\n"})
/* loaded from: classes.dex */
public abstract class Lifecycle {
    private AtomicReference<Object> internalScopeRef;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000ej\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u0010"}, d2 = {"Landroidx/lifecycle/Lifecycle$Event;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "ON_CREATE", "ON_START", "ON_RESUME", "ON_PAUSE", "ON_STOP", "ON_DESTROY", "ON_ANY", "targetState", "Landroidx/lifecycle/Lifecycle$State;", "getTargetState", "()Landroidx/lifecycle/Lifecycle$State;", "Companion", "lifecycle-common"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum Event extends Enum<Event> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ Event[] $VALUES = null;
        public static final Companion Companion = null;
        public static final Event ON_ANY = null;
        public static final Event ON_CREATE = null;
        public static final Event ON_DESTROY = null;
        public static final Event ON_PAUSE = null;
        public static final Event ON_RESUME = null;
        public static final Event ON_START = null;
        public static final Event ON_STOP = null;

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\t\u0010\bJ\u0019\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/lifecycle/Lifecycle$Event$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/lifecycle/Lifecycle$State;", "state", "Landroidx/lifecycle/Lifecycle$Event;", "downFrom", "(Landroidx/lifecycle/Lifecycle$State;)Landroidx/lifecycle/Lifecycle$Event;", "upFrom", "upTo", "lifecycle-common"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {

            @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

                static {
                    int[] r0 = new int[State.values().length];
                    r0[State.CREATED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
                L15:
                    r0[State.STARTED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
                L23:
                    r0[State.RESUMED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
                L17:
                    r0[State.DESTROYED.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
                L19:
                    r0[State.INITIALIZED.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
                L8:
                    $EnumSwitchMapping$0 = r0;
                }
            }

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final Event downFrom(State r1) {
                int r0 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
                if (r0 == 1) goto L15;
                if (r0 == 2) goto L13;
                if (r0 == 3) goto L11;
                return null;
            L11:
                return Event.ON_PAUSE;
            L13:
                return Event.ON_STOP;
            L15:
                return Event.ON_DESTROY;
            }

            @JvmStatic
            public final Event upFrom(State r1) {
                int r0 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
                if (r0 == 1) goto L15;
                if (r0 == 2) goto L13;
                if (r0 == 5) goto L11;
                return null;
            L11:
                return Event.ON_CREATE;
            L13:
                return Event.ON_RESUME;
            L15:
                return Event.ON_START;
            }

            @JvmStatic
            public final Event upTo(State r1) {
                int r0 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
                if (r0 == 1) goto L15;
                if (r0 == 2) goto L13;
                if (r0 == 3) goto L11;
                return null;
            L11:
                return Event.ON_RESUME;
            L13:
                return Event.ON_START;
            L15:
                return Event.ON_CREATE;
            }
        }

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[Event.values().length];
                r0[Event.ON_CREATE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
            L21:
                r0[Event.ON_STOP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
            L31:
                r0[Event.ON_START.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
            L23:
                r0[Event.ON_PAUSE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
            L25:
                r0[Event.ON_RESUME.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
            L19:
                r0[Event.ON_DESTROY.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
            L27:
                r0[Event.ON_ANY.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
            L10:
                $EnumSwitchMapping$0 = r0;
            }
        }

        private static final /* synthetic */ Event[] $values() {
            return new Event[]{ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_DESTROY, ON_ANY};
        }

        static {
            ON_CREATE = new Event("ON_CREATE", 0);
            ON_START = new Event("ON_START", 1);
            ON_RESUME = new Event("ON_RESUME", 2);
            ON_PAUSE = new Event("ON_PAUSE", 3);
            ON_STOP = new Event("ON_STOP", 4);
            ON_DESTROY = new Event("ON_DESTROY", 5);
            ON_ANY = new Event("ON_ANY", 6);
            Event[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
            Companion = new Companion(null);
        }

        Event(String r1, int r2) {
        }

        @JvmStatic
        public static final Event downFrom(State r1) {
            return Companion.downFrom(r1);
        }

        @JvmStatic
        public static final Event upTo(State r1) {
            return Companion.upTo(r1);
        }

        public static Event valueOf(String r1) {
            return (Event) Enum.valueOf(Event.class, r1);
        }

        public static Event[] values() {
            return (Event[]) $VALUES.clone();
        }

        public final State getTargetState() {
            switch(WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
                case 1: goto L15;
                case 2: goto L15;
                case 3: goto L13;
                case 4: goto L13;
                case 5: goto L11;
                case 6: goto L9;
                case 7: goto L7;
                default: goto L4;
            };
        L4:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L7:
            Lifecycle$Event$$ExternalSyntheticBUOutline0.m(this, " has no target state");
            return null;
        L9:
            return State.DESTROYED;
        L11:
            return State.RESUMED;
        L13:
            return State.STARTED;
        L15:
            return State.CREATED;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0000j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\f"}, d2 = {"Landroidx/lifecycle/Lifecycle$State;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "DESTROYED", "INITIALIZED", "CREATED", "STARTED", "RESUMED", "isAtLeast", _UrlKt.FRAGMENT_ENCODE_SET, "state", "lifecycle-common"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum State extends Enum<State> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State CREATED = null;
        public static final State DESTROYED = null;
        public static final State INITIALIZED = null;
        public static final State RESUMED = null;
        public static final State STARTED = null;

        private static final /* synthetic */ State[] $values() {
            return new State[]{DESTROYED, INITIALIZED, CREATED, STARTED, RESUMED};
        }

        static {
            DESTROYED = new State("DESTROYED", 0);
            INITIALIZED = new State("INITIALIZED", 1);
            CREATED = new State("CREATED", 2);
            STARTED = new State("STARTED", 3);
            RESUMED = new State("RESUMED", 4);
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

        public final boolean isAtLeast(State r1) {
            if (compareTo(r1) < 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public Lifecycle() {
        this.internalScopeRef = new AtomicReference(null);
    }

    public abstract void addObserver(LifecycleObserver r1);

    public abstract State getCurrentState();

    public abstract void removeObserver(LifecycleObserver r1);
}
