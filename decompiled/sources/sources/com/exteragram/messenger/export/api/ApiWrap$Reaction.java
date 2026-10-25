package com.exteragram.messenger.export.api;

/* loaded from: classes4.dex */
public abstract class ApiWrap$Reaction {

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type CustomEmoji = null;
        public static final Type Emoji = null;
        public static final Type Empty = null;
        public static final Type Paid = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{Empty, Emoji, CustomEmoji, Paid};
        }

        static {
            Empty = new Type("Empty", 0);
            Emoji = new Type("Emoji", 1);
            CustomEmoji = new Type("CustomEmoji", 2);
            Paid = new Type("Paid", 3);
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

    public static String TypeToString(ApiWrap$Reaction r0) {
        int[] r02 = ApiWrap$1.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type;
        throw null;
    }
}
