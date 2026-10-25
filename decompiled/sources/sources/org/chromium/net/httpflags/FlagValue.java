package org.chromium.net.httpflags;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class FlagValue extends GeneratedMessageLite<FlagValue, Builder> implements FlagValueOrBuilder {
    public static final int CONSTRAINED_VALUES_FIELD_NUMBER = 8;
    private static final FlagValue DEFAULT_INSTANCE = null;
    private static volatile Parser<FlagValue> PARSER;
    private Internal.ProtobufList<ConstrainedValue> constrainedValues_;

    /* renamed from: org.chromium.net.httpflags.FlagValue$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = null;

        static {
            int[] r0 = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0;
            r0[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
        L18:
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
        L22:
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
        L30:
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
        L20:
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
        L24:
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;     // Catch: NoSuchFieldError -> L17
            return;
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FlagValue, Builder> implements FlagValueOrBuilder {
        private Builder() {
            super(FlagValue.m3777$$Nest$sfgetDEFAULT_INSTANCE());
        }

        public /* synthetic */ Builder(FlagValueIA r1) {
            this();
        }

        public Builder addAllConstrainedValues(Iterable<? extends ConstrainedValue> r2) {
            copyOnWrite();
            FlagValue.m3771$$Nest$maddAllConstrainedValues((FlagValue) this.instance, r2);
            return this;
        }

        public Builder addConstrainedValues(int r2, ConstrainedValue.Builder r3) {
            copyOnWrite();
            FlagValue.m3772$$Nest$maddConstrainedValues((FlagValue) this.instance, r2, r3.build());
            return this;
        }

        public Builder addConstrainedValues(int r2, ConstrainedValue r3) {
            copyOnWrite();
            FlagValue.m3772$$Nest$maddConstrainedValues((FlagValue) this.instance, r2, r3);
            return this;
        }

        public Builder addConstrainedValues(ConstrainedValue.Builder r2) {
            copyOnWrite();
            FlagValue.m3773$$Nest$maddConstrainedValues((FlagValue) this.instance, r2.build());
            return this;
        }

        public Builder addConstrainedValues(ConstrainedValue r2) {
            copyOnWrite();
            FlagValue.m3773$$Nest$maddConstrainedValues((FlagValue) this.instance, r2);
            return this;
        }

        public Builder clearConstrainedValues() {
            copyOnWrite();
            FlagValue.m3774$$Nest$mclearConstrainedValues((FlagValue) this.instance);
            return this;
        }

        @Override // org.chromium.net.httpflags.FlagValueOrBuilder
        public ConstrainedValue getConstrainedValues(int r1) {
            return ((FlagValue) this.instance).getConstrainedValues(r1);
        }

        @Override // org.chromium.net.httpflags.FlagValueOrBuilder
        public int getConstrainedValuesCount() {
            return ((FlagValue) this.instance).getConstrainedValuesCount();
        }

        @Override // org.chromium.net.httpflags.FlagValueOrBuilder
        public List<ConstrainedValue> getConstrainedValuesList() {
            return Collections.unmodifiableList(((FlagValue) this.instance).getConstrainedValuesList());
        }

        public Builder removeConstrainedValues(int r2) {
            copyOnWrite();
            FlagValue.m3775$$Nest$mremoveConstrainedValues((FlagValue) this.instance, r2);
            return this;
        }

        public Builder setConstrainedValues(int r2, ConstrainedValue.Builder r3) {
            copyOnWrite();
            FlagValue.m3776$$Nest$msetConstrainedValues((FlagValue) this.instance, r2, r3.build());
            return this;
        }

        public Builder setConstrainedValues(int r2, ConstrainedValue r3) {
            copyOnWrite();
            FlagValue.m3776$$Nest$msetConstrainedValues((FlagValue) this.instance, r2, r3);
            return this;
        }
    }

    public static final class ConstrainedValue extends GeneratedMessageLite<ConstrainedValue, Builder> implements ConstrainedValueOrBuilder {
        public static final int APPLY_EVEN_IF_CRONET_TELEMETRY_DISABLED_FIELD_NUMBER = 8;
        public static final int APP_ID_FIELD_NUMBER = 1;
        public static final int BOOL_VALUE_FIELD_NUMBER = 3;
        public static final int BYTES_VALUE_FIELD_NUMBER = 7;
        private static final ConstrainedValue DEFAULT_INSTANCE = null;
        public static final int FLOAT_VALUE_FIELD_NUMBER = 5;
        public static final int INT_VALUE_FIELD_NUMBER = 4;
        public static final int MIN_VERSION_FIELD_NUMBER = 2;
        private static volatile Parser<ConstrainedValue> PARSER = null;
        public static final int STRING_VALUE_FIELD_NUMBER = 6;
        private String appId_;
        private boolean applyEvenIfCronetTelemetryDisabled_;
        private int bitField0_;
        private String minVersion_;
        private int valueCase_;
        private Object value_;

        public static final class Builder extends GeneratedMessageLite.Builder<ConstrainedValue, Builder> implements ConstrainedValueOrBuilder {
            private Builder() {
                super(ConstrainedValue.m3798$$Nest$sfgetDEFAULT_INSTANCE());
            }

            public /* synthetic */ Builder(FlagValueIA r1) {
                this();
            }

            public Builder clearAppId() {
                copyOnWrite();
                ConstrainedValue.m3778$$Nest$mclearAppId((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearApplyEvenIfCronetTelemetryDisabled() {
                copyOnWrite();
                ConstrainedValue.m3779$$Nest$mclearApplyEvenIfCronetTelemetryDisabled((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearBoolValue() {
                copyOnWrite();
                ConstrainedValue.m3780$$Nest$mclearBoolValue((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearBytesValue() {
                copyOnWrite();
                ConstrainedValue.m3781$$Nest$mclearBytesValue((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearFloatValue() {
                copyOnWrite();
                ConstrainedValue.m3782$$Nest$mclearFloatValue((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearIntValue() {
                copyOnWrite();
                ConstrainedValue.m3783$$Nest$mclearIntValue((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearMinVersion() {
                copyOnWrite();
                ConstrainedValue.m3784$$Nest$mclearMinVersion((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearStringValue() {
                copyOnWrite();
                ConstrainedValue.m3785$$Nest$mclearStringValue((ConstrainedValue) this.instance);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ConstrainedValue.m3786$$Nest$mclearValue((ConstrainedValue) this.instance);
                return this;
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public String getAppId() {
                return ((ConstrainedValue) this.instance).getAppId();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public ByteString getAppIdBytes() {
                return ((ConstrainedValue) this.instance).getAppIdBytes();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean getApplyEvenIfCronetTelemetryDisabled() {
                return ((ConstrainedValue) this.instance).getApplyEvenIfCronetTelemetryDisabled();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean getBoolValue() {
                return ((ConstrainedValue) this.instance).getBoolValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public ByteString getBytesValue() {
                return ((ConstrainedValue) this.instance).getBytesValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public float getFloatValue() {
                return ((ConstrainedValue) this.instance).getFloatValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public long getIntValue() {
                return ((ConstrainedValue) this.instance).getIntValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public String getMinVersion() {
                return ((ConstrainedValue) this.instance).getMinVersion();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public ByteString getMinVersionBytes() {
                return ((ConstrainedValue) this.instance).getMinVersionBytes();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public String getStringValue() {
                return ((ConstrainedValue) this.instance).getStringValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public ByteString getStringValueBytes() {
                return ((ConstrainedValue) this.instance).getStringValueBytes();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public ValueCase getValueCase() {
                return ((ConstrainedValue) this.instance).getValueCase();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasAppId() {
                return ((ConstrainedValue) this.instance).hasAppId();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasApplyEvenIfCronetTelemetryDisabled() {
                return ((ConstrainedValue) this.instance).hasApplyEvenIfCronetTelemetryDisabled();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasBoolValue() {
                return ((ConstrainedValue) this.instance).hasBoolValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasBytesValue() {
                return ((ConstrainedValue) this.instance).hasBytesValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasFloatValue() {
                return ((ConstrainedValue) this.instance).hasFloatValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasIntValue() {
                return ((ConstrainedValue) this.instance).hasIntValue();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasMinVersion() {
                return ((ConstrainedValue) this.instance).hasMinVersion();
            }

            @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
            public boolean hasStringValue() {
                return ((ConstrainedValue) this.instance).hasStringValue();
            }

            public Builder setAppId(String r2) {
                copyOnWrite();
                ConstrainedValue.m3787$$Nest$msetAppId((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setAppIdBytes(ByteString r2) {
                copyOnWrite();
                ConstrainedValue.m3788$$Nest$msetAppIdBytes((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setApplyEvenIfCronetTelemetryDisabled(boolean r2) {
                copyOnWrite();
                ConstrainedValue.m3789$$Nest$msetApplyEvenIfCronetTelemetryDisabled((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setBoolValue(boolean r2) {
                copyOnWrite();
                ConstrainedValue.m3790$$Nest$msetBoolValue((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setBytesValue(ByteString r2) {
                copyOnWrite();
                ConstrainedValue.m3791$$Nest$msetBytesValue((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setFloatValue(float r2) {
                copyOnWrite();
                ConstrainedValue.m3792$$Nest$msetFloatValue((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setIntValue(long r2) {
                copyOnWrite();
                ConstrainedValue.m3793$$Nest$msetIntValue((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setMinVersion(String r2) {
                copyOnWrite();
                ConstrainedValue.m3794$$Nest$msetMinVersion((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setMinVersionBytes(ByteString r2) {
                copyOnWrite();
                ConstrainedValue.m3795$$Nest$msetMinVersionBytes((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setStringValue(String r2) {
                copyOnWrite();
                ConstrainedValue.m3796$$Nest$msetStringValue((ConstrainedValue) this.instance, r2);
                return this;
            }

            public Builder setStringValueBytes(ByteString r2) {
                copyOnWrite();
                ConstrainedValue.m3797$$Nest$msetStringValueBytes((ConstrainedValue) this.instance, r2);
                return this;
            }
        }

        public enum ValueCase extends Enum<ValueCase> {
            private static final /* synthetic */ ValueCase[] $VALUES = null;
            public static final ValueCase BOOL_VALUE = null;
            public static final ValueCase BYTES_VALUE = null;
            public static final ValueCase FLOAT_VALUE = null;
            public static final ValueCase INT_VALUE = null;
            public static final ValueCase STRING_VALUE = null;
            public static final ValueCase VALUE_NOT_SET = null;
            private final int value;

            private static /* synthetic */ ValueCase[] $values() {
                return new ValueCase[]{BOOL_VALUE, INT_VALUE, FLOAT_VALUE, STRING_VALUE, BYTES_VALUE, VALUE_NOT_SET};
            }

            static {
                BOOL_VALUE = new ValueCase("BOOL_VALUE", 0, 3);
                INT_VALUE = new ValueCase("INT_VALUE", 1, 4);
                FLOAT_VALUE = new ValueCase("FLOAT_VALUE", 2, 5);
                STRING_VALUE = new ValueCase("STRING_VALUE", 3, 6);
                BYTES_VALUE = new ValueCase("BYTES_VALUE", 4, 7);
                VALUE_NOT_SET = new ValueCase("VALUE_NOT_SET", 5, 0);
                $VALUES = $values();
            }

            ValueCase(String r1, int r2, int r3) {
                this.value = r3;
            }

            public static ValueCase forNumber(int r1) {
                if (r1 == 0) goto L26;
                if (r1 == 3) goto L24;
                if (r1 == 4) goto L22;
                if (r1 == 5) goto L20;
                if (r1 == 6) goto L18;
                if (r1 == 7) goto L16;
                return null;
            L16:
                return BYTES_VALUE;
            L18:
                return STRING_VALUE;
            L20:
                return FLOAT_VALUE;
            L22:
                return INT_VALUE;
            L24:
                return BOOL_VALUE;
            L26:
                return VALUE_NOT_SET;
            }

            @Deprecated
            public static ValueCase valueOf(int r0) {
                return forNumber(r0);
            }

            public static ValueCase valueOf(String r1) {
                return (ValueCase) Enum.valueOf(ValueCase.class, r1);
            }

            public static ValueCase[] values() {
                return (ValueCase[]) $VALUES.clone();
            }

            public int getNumber() {
                return this.value;
            }
        }

        /* renamed from: -$$Nest$mclearAppId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3778$$Nest$mclearAppId(ConstrainedValue r0) {
            r0.clearAppId();
        }

        /* renamed from: -$$Nest$mclearApplyEvenIfCronetTelemetryDisabled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3779$$Nest$mclearApplyEvenIfCronetTelemetryDisabled(ConstrainedValue r0) {
            r0.clearApplyEvenIfCronetTelemetryDisabled();
        }

        /* renamed from: -$$Nest$mclearBoolValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3780$$Nest$mclearBoolValue(ConstrainedValue r0) {
            r0.clearBoolValue();
        }

        /* renamed from: -$$Nest$mclearBytesValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3781$$Nest$mclearBytesValue(ConstrainedValue r0) {
            r0.clearBytesValue();
        }

        /* renamed from: -$$Nest$mclearFloatValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3782$$Nest$mclearFloatValue(ConstrainedValue r0) {
            r0.clearFloatValue();
        }

        /* renamed from: -$$Nest$mclearIntValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3783$$Nest$mclearIntValue(ConstrainedValue r0) {
            r0.clearIntValue();
        }

        /* renamed from: -$$Nest$mclearMinVersion, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3784$$Nest$mclearMinVersion(ConstrainedValue r0) {
            r0.clearMinVersion();
        }

        /* renamed from: -$$Nest$mclearStringValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3785$$Nest$mclearStringValue(ConstrainedValue r0) {
            r0.clearStringValue();
        }

        /* renamed from: -$$Nest$mclearValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3786$$Nest$mclearValue(ConstrainedValue r0) {
            r0.clearValue();
        }

        /* renamed from: -$$Nest$msetAppId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3787$$Nest$msetAppId(ConstrainedValue r0, String r1) {
            r0.setAppId(r1);
        }

        /* renamed from: -$$Nest$msetAppIdBytes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3788$$Nest$msetAppIdBytes(ConstrainedValue r0, ByteString r1) {
            r0.setAppIdBytes(r1);
        }

        /* renamed from: -$$Nest$msetApplyEvenIfCronetTelemetryDisabled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3789$$Nest$msetApplyEvenIfCronetTelemetryDisabled(ConstrainedValue r0, boolean r1) {
            r0.setApplyEvenIfCronetTelemetryDisabled(r1);
        }

        /* renamed from: -$$Nest$msetBoolValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3790$$Nest$msetBoolValue(ConstrainedValue r0, boolean r1) {
            r0.setBoolValue(r1);
        }

        /* renamed from: -$$Nest$msetBytesValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3791$$Nest$msetBytesValue(ConstrainedValue r0, ByteString r1) {
            r0.setBytesValue(r1);
        }

        /* renamed from: -$$Nest$msetFloatValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3792$$Nest$msetFloatValue(ConstrainedValue r0, float r1) {
            r0.setFloatValue(r1);
        }

        /* renamed from: -$$Nest$msetIntValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3793$$Nest$msetIntValue(ConstrainedValue r0, long r1) {
            r0.setIntValue(r1);
        }

        /* renamed from: -$$Nest$msetMinVersion, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3794$$Nest$msetMinVersion(ConstrainedValue r0, String r1) {
            r0.setMinVersion(r1);
        }

        /* renamed from: -$$Nest$msetMinVersionBytes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3795$$Nest$msetMinVersionBytes(ConstrainedValue r0, ByteString r1) {
            r0.setMinVersionBytes(r1);
        }

        /* renamed from: -$$Nest$msetStringValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3796$$Nest$msetStringValue(ConstrainedValue r0, String r1) {
            r0.setStringValue(r1);
        }

        /* renamed from: -$$Nest$msetStringValueBytes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3797$$Nest$msetStringValueBytes(ConstrainedValue r0, ByteString r1) {
            r0.setStringValueBytes(r1);
        }

        /* renamed from: -$$Nest$sfgetDEFAULT_INSTANCE, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ConstrainedValue m3798$$Nest$sfgetDEFAULT_INSTANCE() {
            return DEFAULT_INSTANCE;
        }

        static {
            ConstrainedValue r0 = new ConstrainedValue();
            DEFAULT_INSTANCE = r0;
            GeneratedMessageLite.registerDefaultInstance(ConstrainedValue.class, r0);
        }

        private ConstrainedValue() {
            this.valueCase_ = 0;
            this.appId_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.minVersion_ = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        private void clearAppId() {
            this.bitField0_ &= -2;
            this.appId_ = getDefaultInstance().getAppId();
        }

        private void clearApplyEvenIfCronetTelemetryDisabled() {
            this.bitField0_ &= -5;
            this.applyEvenIfCronetTelemetryDisabled_ = false;
        }

        private void clearBoolValue() {
            if (this.valueCase_ != 3) goto L6;
            this.valueCase_ = 0;
            this.value_ = null;
            return;
        }

        private void clearBytesValue() {
            if (this.valueCase_ != 7) goto L6;
            this.valueCase_ = 0;
            this.value_ = null;
            return;
        }

        private void clearFloatValue() {
            if (this.valueCase_ != 5) goto L6;
            this.valueCase_ = 0;
            this.value_ = null;
            return;
        }

        private void clearIntValue() {
            if (this.valueCase_ != 4) goto L6;
            this.valueCase_ = 0;
            this.value_ = null;
            return;
        }

        private void clearMinVersion() {
            this.bitField0_ &= -3;
            this.minVersion_ = getDefaultInstance().getMinVersion();
        }

        private void clearStringValue() {
            if (this.valueCase_ != 6) goto L6;
            this.valueCase_ = 0;
            this.value_ = null;
            return;
        }

        private void clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
        }

        public static ConstrainedValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ConstrainedValue r1) {
            return DEFAULT_INSTANCE.createBuilder(r1);
        }

        public static ConstrainedValue parseDelimitedFrom(InputStream r1) {
            return (ConstrainedValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1);
        }

        public static ConstrainedValue parseDelimitedFrom(InputStream r1, ExtensionRegistryLite r2) {
            return (ConstrainedValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static ConstrainedValue parseFrom(ByteString r1) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static ConstrainedValue parseFrom(ByteString r1, ExtensionRegistryLite r2) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static ConstrainedValue parseFrom(CodedInputStream r1) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static ConstrainedValue parseFrom(CodedInputStream r1, ExtensionRegistryLite r2) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static ConstrainedValue parseFrom(InputStream r1) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static ConstrainedValue parseFrom(InputStream r1, ExtensionRegistryLite r2) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static ConstrainedValue parseFrom(ByteBuffer r1) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static ConstrainedValue parseFrom(ByteBuffer r1, ExtensionRegistryLite r2) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static ConstrainedValue parseFrom(byte[] r1) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static ConstrainedValue parseFrom(byte[] r1, ExtensionRegistryLite r2) {
            return (ConstrainedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static Parser<ConstrainedValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setAppId(String r2) {
            r2.getClass();
            this.bitField0_ |= 1;
            this.appId_ = r2;
        }

        private void setAppIdBytes(ByteString r1) {
            this.appId_ = r1.toStringUtf8();
            this.bitField0_ |= 1;
        }

        private void setApplyEvenIfCronetTelemetryDisabled(boolean r2) {
            this.bitField0_ |= 4;
            this.applyEvenIfCronetTelemetryDisabled_ = r2;
        }

        private void setBoolValue(boolean r2) {
            this.valueCase_ = 3;
            this.value_ = Boolean.valueOf(r2);
        }

        private void setBytesValue(ByteString r2) {
            r2.getClass();
            this.valueCase_ = 7;
            this.value_ = r2;
        }

        private void setFloatValue(float r2) {
            this.valueCase_ = 5;
            this.value_ = Float.valueOf(r2);
        }

        private void setIntValue(long r2) {
            this.valueCase_ = 4;
            this.value_ = Long.valueOf(r2);
        }

        private void setMinVersion(String r2) {
            r2.getClass();
            this.bitField0_ |= 2;
            this.minVersion_ = r2;
        }

        private void setMinVersionBytes(ByteString r1) {
            this.minVersion_ = r1.toStringUtf8();
            this.bitField0_ |= 2;
        }

        private void setStringValue(String r2) {
            r2.getClass();
            this.valueCase_ = 6;
            this.value_ = r2;
        }

        private void setStringValueBytes(ByteString r1) {
            this.value_ = r1.toStringUtf8();
            this.valueCase_ = 6;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke r7, Object r8, Object r9) {
            switch(AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[r7.ordinal()]) {
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
            Parser<ConstrainedValue> r6 = PARSER;
            if (r6 == null) goto L11;
            return r6;
        L11:
            monitor-enter(ConstrainedValue.class);
            Parser r62 = PARSER;     // Catch: Throwable -> L15
            if (r62 != null) goto L17;
            r62 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
            PARSER = r62;     // Catch: Throwable -> L15
        L17:
            monitor-exit(ConstrainedValue.class);     // Catch: Throwable -> L15
            return r62;
        L15:
            th = move-exception;
            throw th;
        L24:
            ConstrainedValue r82 = DEFAULT_INSTANCE;
            return GeneratedMessageLite.newMessageInfo(r82, "\u0001\b\u0001\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003:\u0000\u00045\u0000\u00054\u0000\u0006;\u0000\u0007=\u0000\bဇ\u0002", new Object[]{"value_", "valueCase_", "bitField0_", "appId_", "minVersion_", "applyEvenIfCronetTelemetryDisabled_"});
        L7:
            return (byte) 1;
        L23:
            return DEFAULT_INSTANCE;
        L27:
            return new Builder(null);
        L29:
            return new ConstrainedValue();
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public String getAppId() {
            return this.appId_;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public ByteString getAppIdBytes() {
            return ByteString.copyFromUtf8(this.appId_);
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean getApplyEvenIfCronetTelemetryDisabled() {
            return this.applyEvenIfCronetTelemetryDisabled_;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean getBoolValue() {
            if (this.valueCase_ == 3) goto L5;
            return false;
        L5:
            return ((Boolean) this.value_).booleanValue();
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public ByteString getBytesValue() {
            if (this.valueCase_ != 7) goto L7;
            return (ByteString) this.value_;
        L7:
            return ByteString.EMPTY;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public float getFloatValue() {
            if (this.valueCase_ == 5) goto L5;
            return 0.0f;
        L5:
            return ((Float) this.value_).floatValue();
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public long getIntValue() {
            if (this.valueCase_ == 4) goto L5;
            return 0;
        L5:
            return ((Long) this.value_).longValue();
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public String getMinVersion() {
            return this.minVersion_;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public ByteString getMinVersionBytes() {
            return ByteString.copyFromUtf8(this.minVersion_);
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public String getStringValue() {
            if (this.valueCase_ == 6) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            return (String) this.value_;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public ByteString getStringValueBytes() {
            if (this.valueCase_ != 6) goto L5;
            String r2 = (String) this.value_;
        L7:
            return ByteString.copyFromUtf8(r2);
        L5:
            r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasAppId() {
            if ((this.bitField0_ & 1) == 0) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasApplyEvenIfCronetTelemetryDisabled() {
            if ((this.bitField0_ & 4) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasBoolValue() {
            if (this.valueCase_ != 3) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasBytesValue() {
            if (this.valueCase_ != 7) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasFloatValue() {
            if (this.valueCase_ != 5) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasIntValue() {
            if (this.valueCase_ != 4) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasMinVersion() {
            if ((this.bitField0_ & 2) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.chromium.net.httpflags.FlagValue.ConstrainedValueOrBuilder
        public boolean hasStringValue() {
            if (this.valueCase_ != 6) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public interface ConstrainedValueOrBuilder extends MessageLiteOrBuilder {
        String getAppId();

        ByteString getAppIdBytes();

        boolean getApplyEvenIfCronetTelemetryDisabled();

        boolean getBoolValue();

        ByteString getBytesValue();

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* synthetic */ MessageLite getDefaultInstanceForType();

        float getFloatValue();

        long getIntValue();

        String getMinVersion();

        ByteString getMinVersionBytes();

        String getStringValue();

        ByteString getStringValueBytes();

        ConstrainedValue.ValueCase getValueCase();

        boolean hasAppId();

        boolean hasApplyEvenIfCronetTelemetryDisabled();

        boolean hasBoolValue();

        boolean hasBytesValue();

        boolean hasFloatValue();

        boolean hasIntValue();

        boolean hasMinVersion();

        boolean hasStringValue();

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* synthetic */ boolean isInitialized();
    }

    /* renamed from: -$$Nest$maddAllConstrainedValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3771$$Nest$maddAllConstrainedValues(FlagValue r0, Iterable r1) {
        r0.addAllConstrainedValues(r1);
    }

    /* renamed from: -$$Nest$maddConstrainedValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3772$$Nest$maddConstrainedValues(FlagValue r0, int r1, ConstrainedValue r2) {
        r0.addConstrainedValues(r1, r2);
    }

    /* renamed from: -$$Nest$maddConstrainedValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3773$$Nest$maddConstrainedValues(FlagValue r0, ConstrainedValue r1) {
        r0.addConstrainedValues(r1);
    }

    /* renamed from: -$$Nest$mclearConstrainedValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3774$$Nest$mclearConstrainedValues(FlagValue r0) {
        r0.clearConstrainedValues();
    }

    /* renamed from: -$$Nest$mremoveConstrainedValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3775$$Nest$mremoveConstrainedValues(FlagValue r0, int r1) {
        r0.removeConstrainedValues(r1);
    }

    /* renamed from: -$$Nest$msetConstrainedValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3776$$Nest$msetConstrainedValues(FlagValue r0, int r1, ConstrainedValue r2) {
        r0.setConstrainedValues(r1, r2);
    }

    /* renamed from: -$$Nest$sfgetDEFAULT_INSTANCE, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FlagValue m3777$$Nest$sfgetDEFAULT_INSTANCE() {
        return DEFAULT_INSTANCE;
    }

    static {
        FlagValue r0 = new FlagValue();
        DEFAULT_INSTANCE = r0;
        GeneratedMessageLite.registerDefaultInstance(FlagValue.class, r0);
    }

    private FlagValue() {
        this.constrainedValues_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void addAllConstrainedValues(Iterable<? extends ConstrainedValue> r1) {
        ensureConstrainedValuesIsMutable();
        AbstractMessageLite.addAll(r1, this.constrainedValues_);
    }

    private void addConstrainedValues(int r1, ConstrainedValue r2) {
        r2.getClass();
        ensureConstrainedValuesIsMutable();
        this.constrainedValues_.add(r1, r2);
    }

    private void addConstrainedValues(ConstrainedValue r1) {
        r1.getClass();
        ensureConstrainedValuesIsMutable();
        this.constrainedValues_.add(r1);
    }

    private void clearConstrainedValues() {
        this.constrainedValues_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureConstrainedValuesIsMutable() {
        Internal.ProtobufList<ConstrainedValue> r0 = this.constrainedValues_;
        if (r0.isModifiable() == true) goto L6;
        this.constrainedValues_ = GeneratedMessageLite.mutableCopy(r0);
        return;
    }

    public static FlagValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(FlagValue r1) {
        return DEFAULT_INSTANCE.createBuilder(r1);
    }

    public static FlagValue parseDelimitedFrom(InputStream r1) {
        return (FlagValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1);
    }

    public static FlagValue parseDelimitedFrom(InputStream r1, ExtensionRegistryLite r2) {
        return (FlagValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static FlagValue parseFrom(ByteString r1) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static FlagValue parseFrom(ByteString r1, ExtensionRegistryLite r2) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static FlagValue parseFrom(CodedInputStream r1) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static FlagValue parseFrom(CodedInputStream r1, ExtensionRegistryLite r2) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static FlagValue parseFrom(InputStream r1) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static FlagValue parseFrom(InputStream r1, ExtensionRegistryLite r2) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static FlagValue parseFrom(ByteBuffer r1) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static FlagValue parseFrom(ByteBuffer r1, ExtensionRegistryLite r2) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static FlagValue parseFrom(byte[] r1) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static FlagValue parseFrom(byte[] r1, ExtensionRegistryLite r2) {
        return (FlagValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Parser<FlagValue> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeConstrainedValues(int r1) {
        ensureConstrainedValuesIsMutable();
        this.constrainedValues_.remove(r1);
    }

    private void setConstrainedValues(int r1, ConstrainedValue r2) {
        r2.getClass();
        ensureConstrainedValuesIsMutable();
        this.constrainedValues_.set(r1, r2);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke r1, Object r2, Object r3) {
        switch(AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[r1.ordinal()]) {
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
        Parser<FlagValue> r0 = PARSER;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(FlagValue.class);
        Parser r02 = PARSER;     // Catch: Throwable -> L15
        if (r02 != null) goto L17;
        r02 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
        PARSER = r02;     // Catch: Throwable -> L15
    L17:
        monitor-exit(FlagValue.class);     // Catch: Throwable -> L15
        return r02;
    L15:
        th = move-exception;
        throw th;
    L24:
        FlagValue r22 = DEFAULT_INSTANCE;
        return GeneratedMessageLite.newMessageInfo(r22, "\u0001\u0001\u0000\u0000\b\b\u0001\u0000\u0001\u0000\b\u001b", new Object[]{"constrainedValues_", ConstrainedValue.class});
    L7:
        return (byte) 1;
    L23:
        return DEFAULT_INSTANCE;
    L27:
        return new Builder(null);
    L29:
        return new FlagValue();
    }

    @Override // org.chromium.net.httpflags.FlagValueOrBuilder
    public ConstrainedValue getConstrainedValues(int r1) {
        return this.constrainedValues_.get(r1);
    }

    @Override // org.chromium.net.httpflags.FlagValueOrBuilder
    public int getConstrainedValuesCount() {
        return this.constrainedValues_.size();
    }

    @Override // org.chromium.net.httpflags.FlagValueOrBuilder
    public List<ConstrainedValue> getConstrainedValuesList() {
        return this.constrainedValues_;
    }

    public ConstrainedValueOrBuilder getConstrainedValuesOrBuilder(int r1) {
        return this.constrainedValues_.get(r1);
    }

    public List<? extends ConstrainedValueOrBuilder> getConstrainedValuesOrBuilderList() {
        return this.constrainedValues_;
    }
}
