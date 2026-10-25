package androidx.work;

/* loaded from: classes.dex */
public interface Operation {
    public static final State.IN_PROGRESS IN_PROGRESS = null;
    public static final State.SUCCESS SUCCESS = null;

    /* renamed from: androidx.work.Operation$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static abstract class State {

        /* loaded from: classes4.dex */
        public static final class FAILURE extends State {
            private final Throwable mThrowable;

            public FAILURE(Throwable r1) {
                this.mThrowable = r1;
            }

            public String toString() {
                return "FAILURE (" + this.mThrowable.getMessage() + ")";
            }
        }

        public static final class IN_PROGRESS extends State {
            private IN_PROGRESS() {
            }

            public /* synthetic */ IN_PROGRESS(AnonymousClass1 r1) {
                this();
            }

            public String toString() {
                return "IN_PROGRESS";
            }
        }

        public static final class SUCCESS extends State {
            private SUCCESS() {
            }

            public /* synthetic */ SUCCESS(AnonymousClass1 r1) {
                this();
            }

            public String toString() {
                return "SUCCESS";
            }
        }

        public State() {
        }
    }

    static {
        SUCCESS = new State.SUCCESS(null);
        IN_PROGRESS = new State.IN_PROGRESS(null);
    }
}
