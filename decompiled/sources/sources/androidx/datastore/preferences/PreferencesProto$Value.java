package androidx.datastore.preferences;

import androidx.datastore.preferences.PreferencesProto$StringSet;
import androidx.datastore.preferences.protobuf.ByteString;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.MessageLiteOrBuilder;
import androidx.datastore.preferences.protobuf.Parser;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class PreferencesProto$Value extends GeneratedMessageLite<PreferencesProto$Value, Builder> implements MessageLiteOrBuilder {
    public static final int BOOLEAN_FIELD_NUMBER = 1;
    public static final int BYTES_FIELD_NUMBER = 8;
    private static final PreferencesProto$Value DEFAULT_INSTANCE = null;
    public static final int DOUBLE_FIELD_NUMBER = 7;
    public static final int FLOAT_FIELD_NUMBER = 2;
    public static final int INTEGER_FIELD_NUMBER = 3;
    public static final int LONG_FIELD_NUMBER = 4;
    private static volatile Parser<PreferencesProto$Value> PARSER = null;
    public static final int STRING_FIELD_NUMBER = 5;
    public static final int STRING_SET_FIELD_NUMBER = 6;
    private int valueCase_;
    private Object value_;

    public static final class Builder extends GeneratedMessageLite.Builder<PreferencesProto$Value, Builder> implements MessageLiteOrBuilder {
        private Builder() {
            super(PreferencesProto$Value.access$300());
        }

        public /* synthetic */ Builder(PreferencesProto$1 r1) {
            this();
        }

        public Builder setBoolean(boolean r2) {
            copyOnWrite();
            PreferencesProto$Value.access$500((PreferencesProto$Value) this.instance, r2);
            return this;
        }

        public Builder setBytes(ByteString r2) {
            copyOnWrite();
            PreferencesProto$Value.access$2100((PreferencesProto$Value) this.instance, r2);
            return this;
        }

        public Builder setDouble(double r2) {
            copyOnWrite();
            PreferencesProto$Value.access$1900((PreferencesProto$Value) this.instance, r2);
            return this;
        }

        public Builder setFloat(float r2) {
            copyOnWrite();
            PreferencesProto$Value.access$700((PreferencesProto$Value) this.instance, r2);
            return this;
        }

        public Builder setInteger(int r2) {
            copyOnWrite();
            PreferencesProto$Value.access$900((PreferencesProto$Value) this.instance, r2);
            return this;
        }

        public Builder setLong(long r2) {
            copyOnWrite();
            PreferencesProto$Value.access$1100((PreferencesProto$Value) this.instance, r2);
            return this;
        }

        public Builder setString(String r2) {
            copyOnWrite();
            PreferencesProto$Value.access$1300((PreferencesProto$Value) this.instance, r2);
            return this;
        }

        public Builder setStringSet(PreferencesProto$StringSet.Builder r2) {
            copyOnWrite();
            PreferencesProto$Value.access$1600((PreferencesProto$Value) this.instance, r2.build());
            return this;
        }
    }

    public enum ValueCase extends Enum<ValueCase> {
        private static final /* synthetic */ ValueCase[] $VALUES = null;
        public static final ValueCase BOOLEAN = null;
        public static final ValueCase BYTES = null;
        public static final ValueCase DOUBLE = null;
        public static final ValueCase FLOAT = null;
        public static final ValueCase INTEGER = null;
        public static final ValueCase LONG = null;
        public static final ValueCase STRING = null;
        public static final ValueCase STRING_SET = null;
        public static final ValueCase VALUE_NOT_SET = null;
        private final int value;

        private static /* synthetic */ ValueCase[] $values() {
            return new ValueCase[]{BOOLEAN, FLOAT, INTEGER, LONG, STRING, STRING_SET, DOUBLE, BYTES, VALUE_NOT_SET};
        }

        static {
            BOOLEAN = new ValueCase("BOOLEAN", 0, 1);
            FLOAT = new ValueCase("FLOAT", 1, 2);
            INTEGER = new ValueCase("INTEGER", 2, 3);
            LONG = new ValueCase("LONG", 3, 4);
            STRING = new ValueCase("STRING", 4, 5);
            STRING_SET = new ValueCase("STRING_SET", 5, 6);
            DOUBLE = new ValueCase("DOUBLE", 6, 7);
            BYTES = new ValueCase("BYTES", 7, 8);
            VALUE_NOT_SET = new ValueCase("VALUE_NOT_SET", 8, 0);
            $VALUES = $values();
        }

        ValueCase(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static ValueCase forNumber(int r0) {
            switch(r0) {
                case 0: goto L22;
                case 1: goto L20;
                case 2: goto L18;
                case 3: goto L16;
                case 4: goto L14;
                case 5: goto L12;
                case 6: goto L10;
                case 7: goto L8;
                case 8: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return BYTES;
        L8:
            return DOUBLE;
        L10:
            return STRING_SET;
        L12:
            return STRING;
        L14:
            return LONG;
        L16:
            return INTEGER;
        L18:
            return FLOAT;
        L20:
            return BOOLEAN;
        L22:
            return VALUE_NOT_SET;
        }

        public static ValueCase valueOf(String r1) {
            return (ValueCase) Enum.valueOf(ValueCase.class, r1);
        }

        public static ValueCase[] values() {
            return (ValueCase[]) $VALUES.clone();
        }
    }

    static {
        PreferencesProto$Value r0 = new PreferencesProto$Value();
        DEFAULT_INSTANCE = r0;
        GeneratedMessageLite.registerDefaultInstance(PreferencesProto$Value.class, r0);
    }

    private PreferencesProto$Value() {
        this.valueCase_ = 0;
    }

    public static /* synthetic */ void access$1100(PreferencesProto$Value r0, long r1) {
        r0.setLong(r1);
    }

    public static /* synthetic */ void access$1300(PreferencesProto$Value r0, String r1) {
        r0.setString(r1);
    }

    public static /* synthetic */ void access$1600(PreferencesProto$Value r0, PreferencesProto$StringSet r1) {
        r0.setStringSet(r1);
    }

    public static /* synthetic */ void access$1900(PreferencesProto$Value r0, double r1) {
        r0.setDouble(r1);
    }

    public static /* synthetic */ void access$2100(PreferencesProto$Value r0, ByteString r1) {
        r0.setBytes(r1);
    }

    public static /* synthetic */ PreferencesProto$Value access$300() {
        return DEFAULT_INSTANCE;
    }

    public static /* synthetic */ void access$500(PreferencesProto$Value r0, boolean r1) {
        r0.setBoolean(r1);
    }

    public static /* synthetic */ void access$700(PreferencesProto$Value r0, float r1) {
        r0.setFloat(r1);
    }

    public static /* synthetic */ void access$900(PreferencesProto$Value r0, int r1) {
        r0.setInteger(r1);
    }

    public static PreferencesProto$Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    private void setBoolean(boolean r2) {
        this.valueCase_ = 1;
        this.value_ = Boolean.valueOf(r2);
    }

    private void setBytes(ByteString r2) {
        r2.getClass();
        this.valueCase_ = 8;
        this.value_ = r2;
    }

    private void setDouble(double r2) {
        this.valueCase_ = 7;
        this.value_ = Double.valueOf(r2);
    }

    private void setFloat(float r2) {
        this.valueCase_ = 2;
        this.value_ = Float.valueOf(r2);
    }

    private void setInteger(int r2) {
        this.valueCase_ = 3;
        this.value_ = Integer.valueOf(r2);
    }

    private void setLong(long r2) {
        this.valueCase_ = 4;
        this.value_ = Long.valueOf(r2);
    }

    private void setString(String r2) {
        r2.getClass();
        this.valueCase_ = 5;
        this.value_ = r2;
    }

    private void setStringSet(PreferencesProto$StringSet r1) {
        r1.getClass();
        this.value_ = r1;
        this.valueCase_ = 6;
    }

    @Override // androidx.datastore.preferences.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke r1, Object r2, Object r3) {
        switch(PreferencesProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[r1.ordinal()]) {
            case 1: goto L29;
            case 2: goto L27;
            case 3: goto L24;
            case 4: goto L23;
            case 5: goto L8;
            case 6: goto L7;
            case 7: goto L5;
            default: goto L4;
        };
    L4:
        Type$$ExternalSyntheticBUOutline0.m();
    L5:
        return null;
    L8:
        Parser<PreferencesProto$Value> r0 = PARSER;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(PreferencesProto$Value.class);
        Parser r02 = PARSER;     // Catch: Throwable -> L15
        if (r02 != null) goto L17;
        r02 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
        PARSER = r02;     // Catch: Throwable -> L15
    L17:
        monitor-exit(PreferencesProto$Value.class);     // Catch: Throwable -> L15
        return r02;
    L15:
        th = move-exception;
        throw th;
    L24:
        PreferencesProto$Value r22 = DEFAULT_INSTANCE;
        return GeneratedMessageLite.newMessageInfo(r22, "\u0001\b\u0001\u0000\u0001\b\b\u0000\u0000\u0000\u0001:\u0000\u00024\u0000\u00037\u0000\u00045\u0000\u0005;\u0000\u0006<\u0000\u00073\u0000\b=\u0000", new Object[]{"value_", "valueCase_", PreferencesProto$StringSet.class});
    L7:
        return (byte) 1;
    L23:
        return DEFAULT_INSTANCE;
    L27:
        return new Builder(null);
    L29:
        return new PreferencesProto$Value();
    }

    public boolean getBoolean() {
        if (this.valueCase_ == 1) goto L5;
        return false;
    L5:
        return ((Boolean) this.value_).booleanValue();
    }

    public ByteString getBytes() {
        if (this.valueCase_ != 8) goto L7;
        return (ByteString) this.value_;
    L7:
        return ByteString.EMPTY;
    }

    public double getDouble() {
        if (this.valueCase_ == 7) goto L5;
        return 0.0d;
    L5:
        return ((Double) this.value_).doubleValue();
    }

    public float getFloat() {
        if (this.valueCase_ == 2) goto L5;
        return 0.0f;
    L5:
        return ((Float) this.value_).floatValue();
    }

    public int getInteger() {
        if (this.valueCase_ == 3) goto L5;
        return 0;
    L5:
        return ((Integer) this.value_).intValue();
    }

    public long getLong() {
        if (this.valueCase_ == 4) goto L5;
        return 0;
    L5:
        return ((Long) this.value_).longValue();
    }

    public String getString() {
        if (this.valueCase_ == 5) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return (String) this.value_;
    }

    public PreferencesProto$StringSet getStringSet() {
        if (this.valueCase_ != 6) goto L7;
        return (PreferencesProto$StringSet) this.value_;
    L7:
        return PreferencesProto$StringSet.getDefaultInstance();
    }

    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }
}
