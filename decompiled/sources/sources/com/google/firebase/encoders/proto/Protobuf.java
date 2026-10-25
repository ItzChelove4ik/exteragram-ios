package com.google.firebase.encoders.proto;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes5.dex */
public @interface Protobuf {

    public enum IntEncoding extends Enum<IntEncoding> {
        private static final /* synthetic */ IntEncoding[] $VALUES = null;
        public static final IntEncoding DEFAULT = null;
        public static final IntEncoding FIXED = null;
        public static final IntEncoding SIGNED = null;

        static {
            IntEncoding r0 = new IntEncoding(PluginsConstants.Strategy.DEFAULT, 0);
            DEFAULT = r0;
            IntEncoding r1 = new IntEncoding("SIGNED", 1);
            SIGNED = r1;
            IntEncoding r2 = new IntEncoding("FIXED", 2);
            FIXED = r2;
            $VALUES = new IntEncoding[]{r0, r1, r2};
        }

        IntEncoding(String r1, int r2) {
        }

        public static IntEncoding valueOf(String r1) {
            return (IntEncoding) Enum.valueOf(IntEncoding.class, r1);
        }

        public static IntEncoding[] values() {
            return (IntEncoding[]) $VALUES.clone();
        }
    }

    IntEncoding intEncoding() default IntEncoding.DEFAULT;

    int tag();
}
