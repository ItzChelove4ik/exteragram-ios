package org.chromium.net.httpflags;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class BaseFeatureOverrides extends GeneratedMessageLite<BaseFeatureOverrides, Builder> implements BaseFeatureOverridesOrBuilder {
    private static final BaseFeatureOverrides DEFAULT_INSTANCE = null;
    public static final int FEATURE_STATES_FIELD_NUMBER = 1;
    private static volatile Parser<BaseFeatureOverrides> PARSER;
    private MapFieldLite<String, FeatureState> featureStates_;

    /* renamed from: org.chromium.net.httpflags.BaseFeatureOverrides$1, reason: invalid class name */
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

    public static final class Builder extends GeneratedMessageLite.Builder<BaseFeatureOverrides, Builder> implements BaseFeatureOverridesOrBuilder {
        private Builder() {
            super(BaseFeatureOverrides.m3766$$Nest$sfgetDEFAULT_INSTANCE());
        }

        public /* synthetic */ Builder(BaseFeatureOverridesIA r1) {
            this();
        }

        public Builder clearFeatureStates() {
            copyOnWrite();
            BaseFeatureOverrides.m3765$$Nest$mgetMutableFeatureStatesMap((BaseFeatureOverrides) this.instance).clear();
            return this;
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
        public boolean containsFeatureStates(String r1) {
            r1.getClass();
            return ((BaseFeatureOverrides) this.instance).getFeatureStatesMap().containsKey(r1);
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
        @Deprecated
        public Map<String, FeatureState> getFeatureStates() {
            return getFeatureStatesMap();
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
        public int getFeatureStatesCount() {
            return ((BaseFeatureOverrides) this.instance).getFeatureStatesMap().size();
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
        public Map<String, FeatureState> getFeatureStatesMap() {
            return Collections.unmodifiableMap(((BaseFeatureOverrides) this.instance).getFeatureStatesMap());
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
        public FeatureState getFeatureStatesOrDefault(String r2, FeatureState r3) {
            r2.getClass();
            Map<String, FeatureState> r1 = ((BaseFeatureOverrides) this.instance).getFeatureStatesMap();
            if (r1.containsKey(r2) == true) goto L5;
            return r3;
        L5:
            return r1.get(r2);
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
        public FeatureState getFeatureStatesOrThrow(String r2) {
            r2.getClass();
            Map<String, FeatureState> r1 = ((BaseFeatureOverrides) this.instance).getFeatureStatesMap();
            if (r1.containsKey(r2) == true) goto L5;
            Segment$$ExternalSyntheticBUOutline0.m();
            return null;
        L5:
            return r1.get(r2);
        }

        public Builder putAllFeatureStates(Map<String, FeatureState> r2) {
            copyOnWrite();
            BaseFeatureOverrides.m3765$$Nest$mgetMutableFeatureStatesMap((BaseFeatureOverrides) this.instance).putAll(r2);
            return this;
        }

        public Builder putFeatureStates(String r2, FeatureState r3) {
            r2.getClass();
            r3.getClass();
            copyOnWrite();
            BaseFeatureOverrides.m3765$$Nest$mgetMutableFeatureStatesMap((BaseFeatureOverrides) this.instance).put(r2, r3);
            return this;
        }

        public Builder removeFeatureStates(String r2) {
            r2.getClass();
            copyOnWrite();
            BaseFeatureOverrides.m3765$$Nest$mgetMutableFeatureStatesMap((BaseFeatureOverrides) this.instance).remove(r2);
            return this;
        }
    }

    public static final class FeatureState extends GeneratedMessageLite<FeatureState, Builder> implements FeatureStateOrBuilder {
        private static final FeatureState DEFAULT_INSTANCE = null;
        public static final int ENABLED_FIELD_NUMBER = 1;
        public static final int PARAMS_FIELD_NUMBER = 2;
        private static volatile Parser<FeatureState> PARSER;
        private int bitField0_;
        private boolean enabled_;
        private MapFieldLite<String, ByteString> params_;

        public static final class Builder extends GeneratedMessageLite.Builder<FeatureState, Builder> implements FeatureStateOrBuilder {
            private Builder() {
                super(FeatureState.m3770$$Nest$sfgetDEFAULT_INSTANCE());
            }

            public /* synthetic */ Builder(BaseFeatureOverridesIA r1) {
                this();
            }

            public Builder clearEnabled() {
                copyOnWrite();
                FeatureState.m3767$$Nest$mclearEnabled((FeatureState) this.instance);
                return this;
            }

            public Builder clearParams() {
                copyOnWrite();
                FeatureState.m3768$$Nest$mgetMutableParamsMap((FeatureState) this.instance).clear();
                return this;
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            public boolean containsParams(String r1) {
                r1.getClass();
                return ((FeatureState) this.instance).getParamsMap().containsKey(r1);
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            public boolean getEnabled() {
                return ((FeatureState) this.instance).getEnabled();
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            @Deprecated
            public Map<String, ByteString> getParams() {
                return getParamsMap();
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            public int getParamsCount() {
                return ((FeatureState) this.instance).getParamsMap().size();
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            public Map<String, ByteString> getParamsMap() {
                return Collections.unmodifiableMap(((FeatureState) this.instance).getParamsMap());
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            public ByteString getParamsOrDefault(String r2, ByteString r3) {
                r2.getClass();
                Map<String, ByteString> r1 = ((FeatureState) this.instance).getParamsMap();
                if (r1.containsKey(r2) == true) goto L5;
                return r3;
            L5:
                return r1.get(r2);
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            public ByteString getParamsOrThrow(String r2) {
                r2.getClass();
                Map<String, ByteString> r1 = ((FeatureState) this.instance).getParamsMap();
                if (r1.containsKey(r2) == true) goto L5;
                Segment$$ExternalSyntheticBUOutline0.m();
                return null;
            L5:
                return r1.get(r2);
            }

            @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
            public boolean hasEnabled() {
                return ((FeatureState) this.instance).hasEnabled();
            }

            public Builder putAllParams(Map<String, ByteString> r2) {
                copyOnWrite();
                FeatureState.m3768$$Nest$mgetMutableParamsMap((FeatureState) this.instance).putAll(r2);
                return this;
            }

            public Builder putParams(String r2, ByteString r3) {
                r2.getClass();
                r3.getClass();
                copyOnWrite();
                FeatureState.m3768$$Nest$mgetMutableParamsMap((FeatureState) this.instance).put(r2, r3);
                return this;
            }

            public Builder removeParams(String r2) {
                r2.getClass();
                copyOnWrite();
                FeatureState.m3768$$Nest$mgetMutableParamsMap((FeatureState) this.instance).remove(r2);
                return this;
            }

            public Builder setEnabled(boolean r2) {
                copyOnWrite();
                FeatureState.m3769$$Nest$msetEnabled((FeatureState) this.instance, r2);
                return this;
            }
        }

        public static final class ParamsDefaultEntryHolder {
            static final MapEntryLite<String, ByteString> defaultEntry = null;

            static {
                defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, _UrlKt.FRAGMENT_ENCODE_SET, WireFormat.FieldType.BYTES, ByteString.EMPTY);
            }

            private ParamsDefaultEntryHolder() {
            }
        }

        /* renamed from: -$$Nest$mclearEnabled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3767$$Nest$mclearEnabled(FeatureState r0) {
            r0.clearEnabled();
        }

        /* renamed from: -$$Nest$mgetMutableParamsMap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Map m3768$$Nest$mgetMutableParamsMap(FeatureState r0) {
            return r0.getMutableParamsMap();
        }

        /* renamed from: -$$Nest$msetEnabled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3769$$Nest$msetEnabled(FeatureState r0, boolean r1) {
            r0.setEnabled(r1);
        }

        /* renamed from: -$$Nest$sfgetDEFAULT_INSTANCE, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FeatureState m3770$$Nest$sfgetDEFAULT_INSTANCE() {
            return DEFAULT_INSTANCE;
        }

        static {
            FeatureState r0 = new FeatureState();
            DEFAULT_INSTANCE = r0;
            GeneratedMessageLite.registerDefaultInstance(FeatureState.class, r0);
        }

        private FeatureState() {
            this.params_ = MapFieldLite.emptyMapField();
        }

        private void clearEnabled() {
            this.bitField0_ &= -2;
            this.enabled_ = false;
        }

        public static FeatureState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private Map<String, ByteString> getMutableParamsMap() {
            return internalGetMutableParams();
        }

        private MapFieldLite<String, ByteString> internalGetMutableParams() {
            if (this.params_.isMutable() == true) goto L6;
            this.params_ = this.params_.mutableCopy();
        L6:
            return this.params_;
        }

        private MapFieldLite<String, ByteString> internalGetParams() {
            return this.params_;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FeatureState r1) {
            return DEFAULT_INSTANCE.createBuilder(r1);
        }

        public static FeatureState parseDelimitedFrom(InputStream r1) {
            return (FeatureState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1);
        }

        public static FeatureState parseDelimitedFrom(InputStream r1, ExtensionRegistryLite r2) {
            return (FeatureState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static FeatureState parseFrom(ByteString r1) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static FeatureState parseFrom(ByteString r1, ExtensionRegistryLite r2) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static FeatureState parseFrom(CodedInputStream r1) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static FeatureState parseFrom(CodedInputStream r1, ExtensionRegistryLite r2) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static FeatureState parseFrom(InputStream r1) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static FeatureState parseFrom(InputStream r1, ExtensionRegistryLite r2) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static FeatureState parseFrom(ByteBuffer r1) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static FeatureState parseFrom(ByteBuffer r1, ExtensionRegistryLite r2) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static FeatureState parseFrom(byte[] r1) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
        }

        public static FeatureState parseFrom(byte[] r1, ExtensionRegistryLite r2) {
            return (FeatureState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
        }

        public static Parser<FeatureState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setEnabled(boolean r2) {
            this.bitField0_ |= 1;
            this.enabled_ = r2;
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        public boolean containsParams(String r1) {
            r1.getClass();
            return internalGetParams().containsKey(r1);
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
            Parser<FeatureState> r0 = PARSER;
            if (r0 == null) goto L11;
            return r0;
        L11:
            monitor-enter(FeatureState.class);
            Parser r02 = PARSER;     // Catch: Throwable -> L15
            if (r02 != null) goto L17;
            r02 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
            PARSER = r02;     // Catch: Throwable -> L15
        L17:
            monitor-exit(FeatureState.class);     // Catch: Throwable -> L15
            return r02;
        L15:
            th = move-exception;
            throw th;
        L24:
            Object[] r03 = {"bitField0_", "enabled_", "params_", ParamsDefaultEntryHolder.defaultEntry};
            return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0001\u0000\u0000\u0001ဇ\u0000\u00022", r03);
        L7:
            return (byte) 1;
        L23:
            return DEFAULT_INSTANCE;
        L27:
            return new Builder(null);
        L29:
            return new FeatureState();
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        public boolean getEnabled() {
            return this.enabled_;
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        @Deprecated
        public Map<String, ByteString> getParams() {
            return getParamsMap();
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        public int getParamsCount() {
            return internalGetParams().size();
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        public Map<String, ByteString> getParamsMap() {
            return Collections.unmodifiableMap(internalGetParams());
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        public ByteString getParamsOrDefault(String r2, ByteString r3) {
            r2.getClass();
            MapFieldLite<String, ByteString> r1 = internalGetParams();
            if (r1.containsKey(r2) == true) goto L5;
            return r3;
        L5:
            return r1.get(r2);
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        public ByteString getParamsOrThrow(String r2) {
            r2.getClass();
            MapFieldLite<String, ByteString> r1 = internalGetParams();
            if (r1.containsKey(r2) == true) goto L5;
            Segment$$ExternalSyntheticBUOutline0.m();
            return null;
        L5:
            return r1.get(r2);
        }

        @Override // org.chromium.net.httpflags.BaseFeatureOverrides.FeatureStateOrBuilder
        public boolean hasEnabled() {
            if ((this.bitField0_ & 1) == 0) goto L5;
            return true;
        L5:
            return false;
        }
    }

    public interface FeatureStateOrBuilder extends MessageLiteOrBuilder {
        boolean containsParams(String r1);

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* synthetic */ MessageLite getDefaultInstanceForType();

        boolean getEnabled();

        @Deprecated
        Map<String, ByteString> getParams();

        int getParamsCount();

        Map<String, ByteString> getParamsMap();

        ByteString getParamsOrDefault(String r1, ByteString r2);

        ByteString getParamsOrThrow(String r1);

        boolean hasEnabled();

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* synthetic */ boolean isInitialized();
    }

    public static final class FeatureStatesDefaultEntryHolder {
        static final MapEntryLite<String, FeatureState> defaultEntry = null;

        static {
            defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, _UrlKt.FRAGMENT_ENCODE_SET, WireFormat.FieldType.MESSAGE, FeatureState.getDefaultInstance());
        }

        private FeatureStatesDefaultEntryHolder() {
        }
    }

    /* renamed from: -$$Nest$mgetMutableFeatureStatesMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m3765$$Nest$mgetMutableFeatureStatesMap(BaseFeatureOverrides r0) {
        return r0.getMutableFeatureStatesMap();
    }

    /* renamed from: -$$Nest$sfgetDEFAULT_INSTANCE, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFeatureOverrides m3766$$Nest$sfgetDEFAULT_INSTANCE() {
        return DEFAULT_INSTANCE;
    }

    static {
        BaseFeatureOverrides r0 = new BaseFeatureOverrides();
        DEFAULT_INSTANCE = r0;
        GeneratedMessageLite.registerDefaultInstance(BaseFeatureOverrides.class, r0);
    }

    private BaseFeatureOverrides() {
        this.featureStates_ = MapFieldLite.emptyMapField();
    }

    public static BaseFeatureOverrides getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, FeatureState> getMutableFeatureStatesMap() {
        return internalGetMutableFeatureStates();
    }

    private MapFieldLite<String, FeatureState> internalGetFeatureStates() {
        return this.featureStates_;
    }

    private MapFieldLite<String, FeatureState> internalGetMutableFeatureStates() {
        if (this.featureStates_.isMutable() == true) goto L6;
        this.featureStates_ = this.featureStates_.mutableCopy();
    L6:
        return this.featureStates_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BaseFeatureOverrides r1) {
        return DEFAULT_INSTANCE.createBuilder(r1);
    }

    public static BaseFeatureOverrides parseDelimitedFrom(InputStream r1) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1);
    }

    public static BaseFeatureOverrides parseDelimitedFrom(InputStream r1, ExtensionRegistryLite r2) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static BaseFeatureOverrides parseFrom(ByteString r1) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static BaseFeatureOverrides parseFrom(ByteString r1, ExtensionRegistryLite r2) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static BaseFeatureOverrides parseFrom(CodedInputStream r1) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static BaseFeatureOverrides parseFrom(CodedInputStream r1, ExtensionRegistryLite r2) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static BaseFeatureOverrides parseFrom(InputStream r1) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static BaseFeatureOverrides parseFrom(InputStream r1, ExtensionRegistryLite r2) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static BaseFeatureOverrides parseFrom(ByteBuffer r1) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static BaseFeatureOverrides parseFrom(ByteBuffer r1, ExtensionRegistryLite r2) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static BaseFeatureOverrides parseFrom(byte[] r1) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static BaseFeatureOverrides parseFrom(byte[] r1, ExtensionRegistryLite r2) {
        return (BaseFeatureOverrides) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Parser<BaseFeatureOverrides> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
    public boolean containsFeatureStates(String r1) {
        r1.getClass();
        return internalGetFeatureStates().containsKey(r1);
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
        Parser<BaseFeatureOverrides> r0 = PARSER;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(BaseFeatureOverrides.class);
        Parser r02 = PARSER;     // Catch: Throwable -> L15
        if (r02 != null) goto L17;
        r02 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
        PARSER = r02;     // Catch: Throwable -> L15
    L17:
        monitor-exit(BaseFeatureOverrides.class);     // Catch: Throwable -> L15
        return r02;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r03 = {"featureStates_", FeatureStatesDefaultEntryHolder.defaultEntry};
        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", r03);
    L7:
        return (byte) 1;
    L23:
        return DEFAULT_INSTANCE;
    L27:
        return new Builder(null);
    L29:
        return new BaseFeatureOverrides();
    }

    @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
    @Deprecated
    public Map<String, FeatureState> getFeatureStates() {
        return getFeatureStatesMap();
    }

    @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
    public int getFeatureStatesCount() {
        return internalGetFeatureStates().size();
    }

    @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
    public Map<String, FeatureState> getFeatureStatesMap() {
        return Collections.unmodifiableMap(internalGetFeatureStates());
    }

    @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
    public FeatureState getFeatureStatesOrDefault(String r2, FeatureState r3) {
        r2.getClass();
        MapFieldLite<String, FeatureState> r1 = internalGetFeatureStates();
        if (r1.containsKey(r2) == true) goto L5;
        return r3;
    L5:
        return r1.get(r2);
    }

    @Override // org.chromium.net.httpflags.BaseFeatureOverridesOrBuilder
    public FeatureState getFeatureStatesOrThrow(String r2) {
        r2.getClass();
        MapFieldLite<String, FeatureState> r1 = internalGetFeatureStates();
        if (r1.containsKey(r2) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return r1.get(r2);
    }
}
