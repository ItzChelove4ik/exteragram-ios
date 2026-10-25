package org.chromium.net.httpflags;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.ByteString;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.httpflags.FlagValue;

/* loaded from: classes5.dex */
public final class ResolvedFlags {
    private final Map<String, Value> mFlags;

    /* renamed from: org.chromium.net.httpflags.ResolvedFlags$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase = null;

        static {
            int[] r0 = new int[FlagValue.ConstrainedValue.ValueCase.values().length];
            $SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase = r0;
            r0[FlagValue.ConstrainedValue.ValueCase.BOOL_VALUE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L16:
            $SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase[FlagValue.ConstrainedValue.ValueCase.INT_VALUE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L20:
            $SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase[FlagValue.ConstrainedValue.ValueCase.FLOAT_VALUE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L26:
            $SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase[FlagValue.ConstrainedValue.ValueCase.STRING_VALUE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L18:
            $SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase[FlagValue.ConstrainedValue.ValueCase.BYTES_VALUE.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L22:
            $SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase[FlagValue.ConstrainedValue.ValueCase.VALUE_NOT_SET.ordinal()] = 6;     // Catch: NoSuchFieldError -> L15
            return;
        }
    }

    public static final class Value {
        private final Object mValue;

        public enum Type extends Enum<Type> {
            private static final /* synthetic */ Type[] $VALUES = null;
            public static final Type BOOL = null;
            public static final Type BYTES = null;
            public static final Type FLOAT = null;
            public static final Type INT = null;
            public static final Type STRING = null;

            private static /* synthetic */ Type[] $values() {
                return new Type[]{BOOL, INT, FLOAT, STRING, BYTES};
            }

            static {
                BOOL = new Type("BOOL", 0);
                INT = new Type("INT", 1);
                FLOAT = new Type("FLOAT", 2);
                STRING = new Type("STRING", 3);
                BYTES = new Type("BYTES", 4);
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

        /* renamed from: -$$Nest$smresolve, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Value m3802$$Nest$smresolve(FlagValue r0, String r1, int[] r2, boolean r3) {
            return resolve(r0, r1, r2, r3);
        }

        public Value(float r1) {
            this.mValue = Float.valueOf(r1);
        }

        public Value(long r1) {
            this.mValue = Long.valueOf(r1);
        }

        public Value(ByteString r1) {
            this.mValue = r1;
        }

        public Value(String r1) {
            this.mValue = r1;
        }

        public Value(boolean r1) {
            this.mValue = Boolean.valueOf(r1);
        }

        private void checkType(Type r4) {
            Type r3 = getType();
            if (r4 != r3) goto L6;
            return;
        L6:
            throw new IllegalStateException("Attempted to access flag value as " + String.valueOf(r4) + ", but actual type is " + String.valueOf(r3));
        }

        private static Value fromConstrainedValue(FlagValue.ConstrainedValue r3) {
            FlagValue.ConstrainedValue.ValueCase r0 = r3.getValueCase();
            switch(AnonymousClass1.$SwitchMap$org$chromium$net$httpflags$FlagValue$ConstrainedValue$ValueCase[r0.ordinal()]) {
                case 1: goto L15;
                case 2: goto L13;
                case 3: goto L11;
                case 4: goto L9;
                case 5: goto L7;
                case 6: goto L5;
                default: goto L4;
            };
        L4:
            f$$ExternalSyntheticBUOutline1.m("Flag value uses unknown value type ".concat(String.valueOf(r0)));
        L5:
            return null;
        L7:
            return new Value(r3.getBytesValue());
        L9:
            return new Value(r3.getStringValue());
        L11:
            return new Value(r3.getFloatValue());
        L13:
            return new Value(r3.getIntValue());
        L15:
            return new Value(r3.getBoolValue());
        }

        private static boolean matchesVersion(int[] r5, int[] r6) {
            int r1 = 0;
        L4:
            if (r1 >= Math.max(r5.length, r6.length)) goto L18;
            if (r1 >= r5.length) goto L8;
            int r2 = r5[r1];
        L10:
            if (r1 >= r6.length) goto L12;
            int r4 = r6[r1];
        L13:
            if (r2 > r4) goto L14;
            if (r2 < r4) goto L16;
            r1 = r1 + 1;
            goto L4
        L16:
            return false;
        L14:
            return true;
        L12:
            r4 = 0;
            goto L13
        L8:
            r2 = 0;
            goto L10
        L18:
            return true;
        }

        private static Value resolve(FlagValue r2, String r3, int[] r4, boolean r5) {
            Iterator<FlagValue.ConstrainedValue> r22 = r2.getConstrainedValuesList().iterator();
        L4:
            if (r22.hasNext() == false) goto L20;
            FlagValue.ConstrainedValue r0 = r22.next();
            if (r5 == true) goto L10;
            if (r0.getApplyEvenIfCronetTelemetryDisabled() == false) goto L4;
        L10:
            if (r0.hasAppId() == false) goto L14;
            if (r0.getAppId().equals(r3) == false) goto L4;
        L14:
            if (r0.hasMinVersion() == false) goto L19;
            if (matchesVersion(r4, ResolvedFlags.m3801$$Nest$smparseVersionString(r0.getMinVersion())) == false) goto L4;
        L19:
            return fromConstrainedValue(r0);
        L20:
            return null;
        }

        public boolean getBoolValue() {
            checkType(Type.BOOL);
            return ((Boolean) this.mValue).booleanValue();
        }

        public ByteString getBytesValue() {
            checkType(Type.BYTES);
            return (ByteString) this.mValue;
        }

        public float getFloatValue() {
            checkType(Type.FLOAT);
            return ((Float) this.mValue).floatValue();
        }

        public long getIntValue() {
            checkType(Type.INT);
            return ((Long) this.mValue).longValue();
        }

        public String getStringValue() {
            checkType(Type.STRING);
            return (String) this.mValue;
        }

        public Type getType() {
            Object r1 = this.mValue;
            if ((r1 instanceof Boolean) == false) goto L7;
            return Type.BOOL;
        L7:
            if ((r1 instanceof Long) == false) goto L11;
            return Type.INT;
        L11:
            if ((r1 instanceof Float) == false) goto L15;
            return Type.FLOAT;
        L15:
            if ((r1 instanceof String) == false) goto L19;
            return Type.STRING;
        L19:
            if ((r1 instanceof ByteString) == true) goto L21;
            Segment$$ExternalSyntheticBUOutline1.m("Unexpected flag value type: ".concat(r1.getClass().getName()));
            return null;
        L21:
            return Type.BYTES;
        }

        public String toString() {
            return this.mValue.toString();
        }
    }

    /* renamed from: -$$Nest$smparseVersionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m3801$$Nest$smparseVersionString(String r0) {
        return parseVersionString(r0);
    }

    public ResolvedFlags(Map<String, Value> r1) {
        this.mFlags = r1;
    }

    private static int[] parseVersionString(String r5) {
    L7:
        e = move-exception;
        throw new IllegalArgumentException("Unable to parse HTTP flags version string: `" + r5 + "`", e);
    L3:
        if (r5.isEmpty() == true) goto L11;
        StringTokenizer r0 = new StringTokenizer(r5, ".");     // Catch: RuntimeException -> L7
        int r1 = r0.countTokens();     // Catch: RuntimeException -> L7
        int[] r2 = new int[r1];     // Catch: RuntimeException -> L7
        int r3 = 0;
    L5:
        if (r3 >= r1) goto L9;
        r2[r3] = Integer.parseInt(r0.nextToken());     // Catch: RuntimeException -> L7
        r3 = r3 + 1;     // Catch: RuntimeException -> L7
        goto L5
    L9:
        return r2;
    L11:
        throw new IllegalArgumentException("Version string is empty");     // Catch: RuntimeException -> L7
    }

    public static ResolvedFlags resolve(Flags r5, String r6, String r7, boolean r8) {
        ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("Cronet ResolvedFlags#resolve");
        int[] r72 = parseVersionString(r7);     // Catch: Throwable -> L12
        HashMap r1 = new HashMap();     // Catch: Throwable -> L12
        Iterator<Map.Entry<String, FlagValue>> r52 = r5.getFlagsMap().entrySet().iterator();     // Catch: Throwable -> L12
    L5:
        if (r52.hasNext() == false) goto L17;
        Map.Entry<String, FlagValue> r2 = r52.next();     // Catch: Throwable -> L12
        Value r3 = Value.m3802$$Nest$smresolve(r2.getValue(), r6, r72, r8);     // Catch: Throwable -> L12 RuntimeException -> L14
        if (r3 == null) goto L5;
        r1.put(r2.getKey(), r3);     // Catch: Throwable -> L12 RuntimeException -> L14
        goto L5
    L14:
        e = move-exception;
        throw new IllegalArgumentException("Unable to resolve HTTP flag `" + r2.getKey() + "`", e);     // Catch: Throwable -> L12
    L17:
        ResolvedFlags r53 = new ResolvedFlags(r1);     // Catch: Throwable -> L12
        if (r0 == null) goto L20;
        r0.close();
    L20:
        return r53;
    L12:
        th = move-exception;
        if (r0 != null) goto L27;
    L26:
        throw th;
    L27:
        r0.close();     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        th.addSuppressed(th);
        goto L26
    }

    public Map<String, Value> flags() {
        return Collections.unmodifiableMap(this.mFlags);
    }
}
