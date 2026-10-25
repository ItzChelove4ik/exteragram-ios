package org.webrtc;

import java.util.Locale;

/* loaded from: classes6.dex */
public class SessionDescription {
    public final String description;
    public final Type type;

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type ANSWER = null;
        public static final Type OFFER = null;
        public static final Type PRANSWER = null;
        public static final Type ROLLBACK = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{OFFER, PRANSWER, ANSWER, ROLLBACK};
        }

        static {
            OFFER = new Type("OFFER", 0);
            PRANSWER = new Type("PRANSWER", 1);
            ANSWER = new Type("ANSWER", 2);
            ROLLBACK = new Type("ROLLBACK", 3);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        @CalledByNative("Type")
        public static Type fromCanonicalForm(String r1) {
            return (Type) Enum.valueOf(Type.class, r1.toUpperCase(Locale.US));
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }

        public String canonicalForm() {
            return name().toLowerCase(Locale.US);
        }
    }

    @CalledByNative
    public SessionDescription(Type r1, String r2) {
        this.type = r1;
        this.description = r2;
    }

    @CalledByNative
    public String getDescription() {
        return this.description;
    }

    @CalledByNative
    public String getTypeInCanonicalForm() {
        return this.type.canonicalForm();
    }
}
