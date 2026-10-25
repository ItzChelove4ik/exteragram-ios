package androidx.camera.core;

/* loaded from: classes3.dex */
public abstract class CameraState {

    public static abstract class StateError {
        public StateError() {
        }

        public static StateError create(int r1) {
            return create(r1, null);
        }

        public static StateError create(int r1, Throwable r2) {
            return new AutoValue_CameraState_StateError(r1, r2);
        }

        public abstract Throwable getCause();

        public abstract int getCode();
    }

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type CLOSED = null;
        public static final Type CLOSING = null;
        public static final Type OPEN = null;
        public static final Type OPENING = null;
        public static final Type PENDING_OPEN = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{PENDING_OPEN, OPENING, OPEN, CLOSING, CLOSED};
        }

        static {
            PENDING_OPEN = new Type("PENDING_OPEN", 0);
            OPENING = new Type("OPENING", 1);
            OPEN = new Type("OPEN", 2);
            CLOSING = new Type("CLOSING", 3);
            CLOSED = new Type("CLOSED", 4);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public CameraState() {
    }

    public static CameraState create(Type r1, StateError r2) {
        return new AutoValue_CameraState(r1, r2);
    }

    public abstract StateError getError();

    public abstract Type getType();
}
