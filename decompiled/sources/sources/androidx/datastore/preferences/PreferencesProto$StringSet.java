package androidx.datastore.preferences;

import androidx.datastore.preferences.protobuf.AbstractMessageLite;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.MessageLiteOrBuilder;
import androidx.datastore.preferences.protobuf.Parser;
import java.util.List;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class PreferencesProto$StringSet extends GeneratedMessageLite<PreferencesProto$StringSet, Builder> implements MessageLiteOrBuilder {
    private static final PreferencesProto$StringSet DEFAULT_INSTANCE = null;
    private static volatile Parser<PreferencesProto$StringSet> PARSER = null;
    public static final int STRINGS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<String> strings_;

    public static final class Builder extends GeneratedMessageLite.Builder<PreferencesProto$StringSet, Builder> implements MessageLiteOrBuilder {
        private Builder() {
            super(PreferencesProto$StringSet.access$2400());
        }

        public /* synthetic */ Builder(PreferencesProto$1 r1) {
            this();
        }

        public Builder addAllStrings(Iterable<String> r2) {
            copyOnWrite();
            PreferencesProto$StringSet.access$2700((PreferencesProto$StringSet) this.instance, r2);
            return this;
        }
    }

    static {
        PreferencesProto$StringSet r0 = new PreferencesProto$StringSet();
        DEFAULT_INSTANCE = r0;
        GeneratedMessageLite.registerDefaultInstance(PreferencesProto$StringSet.class, r0);
    }

    private PreferencesProto$StringSet() {
        this.strings_ = GeneratedMessageLite.emptyProtobufList();
    }

    public static /* synthetic */ PreferencesProto$StringSet access$2400() {
        return DEFAULT_INSTANCE;
    }

    public static /* synthetic */ void access$2700(PreferencesProto$StringSet r0, Iterable r1) {
        r0.addAllStrings(r1);
    }

    private void addAllStrings(Iterable<String> r1) {
        ensureStringsIsMutable();
        AbstractMessageLite.addAll(r1, this.strings_);
    }

    private void ensureStringsIsMutable() {
        Internal.ProtobufList<String> r0 = this.strings_;
        if (r0.isModifiable() == true) goto L6;
        this.strings_ = GeneratedMessageLite.mutableCopy(r0);
        return;
    }

    public static PreferencesProto$StringSet getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
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
        Parser<PreferencesProto$StringSet> r0 = PARSER;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(PreferencesProto$StringSet.class);
        Parser r02 = PARSER;     // Catch: Throwable -> L15
        if (r02 != null) goto L17;
        r02 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
        PARSER = r02;     // Catch: Throwable -> L15
    L17:
        monitor-exit(PreferencesProto$StringSet.class);     // Catch: Throwable -> L15
        return r02;
    L15:
        th = move-exception;
        throw th;
    L24:
        PreferencesProto$StringSet r22 = DEFAULT_INSTANCE;
        return GeneratedMessageLite.newMessageInfo(r22, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"strings_"});
    L7:
        return (byte) 1;
    L23:
        return DEFAULT_INSTANCE;
    L27:
        return new Builder(null);
    L29:
        return new PreferencesProto$StringSet();
    }

    public List<String> getStringsList() {
        return this.strings_;
    }
}
