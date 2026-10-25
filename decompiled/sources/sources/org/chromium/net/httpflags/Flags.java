package org.chromium.net.httpflags;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
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
public final class Flags extends GeneratedMessageLite<Flags, Builder> implements FlagsOrBuilder {
    private static final Flags DEFAULT_INSTANCE = null;
    public static final int FLAGS_FIELD_NUMBER = 1;
    private static volatile Parser<Flags> PARSER;
    private MapFieldLite<String, FlagValue> flags_;

    /* renamed from: org.chromium.net.httpflags.Flags$1, reason: invalid class name */
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

    public static final class Builder extends GeneratedMessageLite.Builder<Flags, Builder> implements FlagsOrBuilder {
        private Builder() {
            super(Flags.m3800$$Nest$sfgetDEFAULT_INSTANCE());
        }

        public /* synthetic */ Builder(FlagsIA r1) {
            this();
        }

        public Builder clearFlags() {
            copyOnWrite();
            Flags.m3799$$Nest$mgetMutableFlagsMap((Flags) this.instance).clear();
            return this;
        }

        @Override // org.chromium.net.httpflags.FlagsOrBuilder
        public boolean containsFlags(String r1) {
            r1.getClass();
            return ((Flags) this.instance).getFlagsMap().containsKey(r1);
        }

        @Override // org.chromium.net.httpflags.FlagsOrBuilder
        @Deprecated
        public Map<String, FlagValue> getFlags() {
            return getFlagsMap();
        }

        @Override // org.chromium.net.httpflags.FlagsOrBuilder
        public int getFlagsCount() {
            return ((Flags) this.instance).getFlagsMap().size();
        }

        @Override // org.chromium.net.httpflags.FlagsOrBuilder
        public Map<String, FlagValue> getFlagsMap() {
            return Collections.unmodifiableMap(((Flags) this.instance).getFlagsMap());
        }

        @Override // org.chromium.net.httpflags.FlagsOrBuilder
        public FlagValue getFlagsOrDefault(String r2, FlagValue r3) {
            r2.getClass();
            Map<String, FlagValue> r1 = ((Flags) this.instance).getFlagsMap();
            if (r1.containsKey(r2) == true) goto L5;
            return r3;
        L5:
            return r1.get(r2);
        }

        @Override // org.chromium.net.httpflags.FlagsOrBuilder
        public FlagValue getFlagsOrThrow(String r2) {
            r2.getClass();
            Map<String, FlagValue> r1 = ((Flags) this.instance).getFlagsMap();
            if (r1.containsKey(r2) == true) goto L5;
            Segment$$ExternalSyntheticBUOutline0.m();
            return null;
        L5:
            return r1.get(r2);
        }

        public Builder putAllFlags(Map<String, FlagValue> r2) {
            copyOnWrite();
            Flags.m3799$$Nest$mgetMutableFlagsMap((Flags) this.instance).putAll(r2);
            return this;
        }

        public Builder putFlags(String r2, FlagValue r3) {
            r2.getClass();
            r3.getClass();
            copyOnWrite();
            Flags.m3799$$Nest$mgetMutableFlagsMap((Flags) this.instance).put(r2, r3);
            return this;
        }

        public Builder removeFlags(String r2) {
            r2.getClass();
            copyOnWrite();
            Flags.m3799$$Nest$mgetMutableFlagsMap((Flags) this.instance).remove(r2);
            return this;
        }
    }

    public static final class FlagsDefaultEntryHolder {
        static final MapEntryLite<String, FlagValue> defaultEntry = null;

        static {
            defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, _UrlKt.FRAGMENT_ENCODE_SET, WireFormat.FieldType.MESSAGE, FlagValue.getDefaultInstance());
        }

        private FlagsDefaultEntryHolder() {
        }
    }

    /* renamed from: -$$Nest$mgetMutableFlagsMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m3799$$Nest$mgetMutableFlagsMap(Flags r0) {
        return r0.getMutableFlagsMap();
    }

    /* renamed from: -$$Nest$sfgetDEFAULT_INSTANCE, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Flags m3800$$Nest$sfgetDEFAULT_INSTANCE() {
        return DEFAULT_INSTANCE;
    }

    static {
        Flags r0 = new Flags();
        DEFAULT_INSTANCE = r0;
        GeneratedMessageLite.registerDefaultInstance(Flags.class, r0);
    }

    private Flags() {
        this.flags_ = MapFieldLite.emptyMapField();
    }

    public static Flags getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, FlagValue> getMutableFlagsMap() {
        return internalGetMutableFlags();
    }

    private MapFieldLite<String, FlagValue> internalGetFlags() {
        return this.flags_;
    }

    private MapFieldLite<String, FlagValue> internalGetMutableFlags() {
        if (this.flags_.isMutable() == true) goto L6;
        this.flags_ = this.flags_.mutableCopy();
    L6:
        return this.flags_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Flags r1) {
        return DEFAULT_INSTANCE.createBuilder(r1);
    }

    public static Flags parseDelimitedFrom(InputStream r1) {
        return (Flags) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1);
    }

    public static Flags parseDelimitedFrom(InputStream r1, ExtensionRegistryLite r2) {
        return (Flags) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Flags parseFrom(ByteString r1) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static Flags parseFrom(ByteString r1, ExtensionRegistryLite r2) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Flags parseFrom(CodedInputStream r1) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static Flags parseFrom(CodedInputStream r1, ExtensionRegistryLite r2) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Flags parseFrom(InputStream r1) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static Flags parseFrom(InputStream r1, ExtensionRegistryLite r2) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Flags parseFrom(ByteBuffer r1) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static Flags parseFrom(ByteBuffer r1, ExtensionRegistryLite r2) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Flags parseFrom(byte[] r1) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
    }

    public static Flags parseFrom(byte[] r1, ExtensionRegistryLite r2) {
        return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1, r2);
    }

    public static Parser<Flags> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // org.chromium.net.httpflags.FlagsOrBuilder
    public boolean containsFlags(String r1) {
        r1.getClass();
        return internalGetFlags().containsKey(r1);
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
        Parser<Flags> r0 = PARSER;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(Flags.class);
        Parser r02 = PARSER;     // Catch: Throwable -> L15
        if (r02 != null) goto L17;
        r02 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
        PARSER = r02;     // Catch: Throwable -> L15
    L17:
        monitor-exit(Flags.class);     // Catch: Throwable -> L15
        return r02;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r03 = {"flags_", FlagsDefaultEntryHolder.defaultEntry};
        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", r03);
    L7:
        return (byte) 1;
    L23:
        return DEFAULT_INSTANCE;
    L27:
        return new Builder(null);
    L29:
        return new Flags();
    }

    @Override // org.chromium.net.httpflags.FlagsOrBuilder
    @Deprecated
    public Map<String, FlagValue> getFlags() {
        return getFlagsMap();
    }

    @Override // org.chromium.net.httpflags.FlagsOrBuilder
    public int getFlagsCount() {
        return internalGetFlags().size();
    }

    @Override // org.chromium.net.httpflags.FlagsOrBuilder
    public Map<String, FlagValue> getFlagsMap() {
        return Collections.unmodifiableMap(internalGetFlags());
    }

    @Override // org.chromium.net.httpflags.FlagsOrBuilder
    public FlagValue getFlagsOrDefault(String r2, FlagValue r3) {
        r2.getClass();
        MapFieldLite<String, FlagValue> r1 = internalGetFlags();
        if (r1.containsKey(r2) == true) goto L5;
        return r3;
    L5:
        return r1.get(r2);
    }

    @Override // org.chromium.net.httpflags.FlagsOrBuilder
    public FlagValue getFlagsOrThrow(String r2) {
        r2.getClass();
        MapFieldLite<String, FlagValue> r1 = internalGetFlags();
        if (r1.containsKey(r2) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return r1.get(r2);
    }
}
