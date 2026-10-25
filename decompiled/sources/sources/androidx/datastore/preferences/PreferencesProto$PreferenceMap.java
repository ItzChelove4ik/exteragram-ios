package androidx.datastore.preferences;

import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.MapFieldLite;
import androidx.datastore.preferences.protobuf.MessageLiteOrBuilder;
import androidx.datastore.preferences.protobuf.Parser;
import androidx.datastore.preferences.protobuf.WireFormat;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class PreferencesProto$PreferenceMap extends GeneratedMessageLite<PreferencesProto$PreferenceMap, Builder> implements MessageLiteOrBuilder {
    private static final PreferencesProto$PreferenceMap DEFAULT_INSTANCE = null;
    private static volatile Parser<PreferencesProto$PreferenceMap> PARSER = null;
    public static final int PREFERENCES_FIELD_NUMBER = 1;
    private MapFieldLite<String, PreferencesProto$Value> preferences_;

    public static final class Builder extends GeneratedMessageLite.Builder<PreferencesProto$PreferenceMap, Builder> implements MessageLiteOrBuilder {
        private Builder() {
            super(PreferencesProto$PreferenceMap.access$000());
        }

        public /* synthetic */ Builder(PreferencesProto$1 r1) {
            this();
        }

        public Builder putPreferences(String r2, PreferencesProto$Value r3) {
            r2.getClass();
            r3.getClass();
            copyOnWrite();
            PreferencesProto$PreferenceMap.access$100((PreferencesProto$PreferenceMap) this.instance).put(r2, r3);
            return this;
        }
    }

    public static final class PreferencesDefaultEntryHolder {
        static final MapEntryLite<String, PreferencesProto$Value> defaultEntry = null;

        static {
            defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, _UrlKt.FRAGMENT_ENCODE_SET, WireFormat.FieldType.MESSAGE, PreferencesProto$Value.getDefaultInstance());
        }
    }

    static {
        PreferencesProto$PreferenceMap r0 = new PreferencesProto$PreferenceMap();
        DEFAULT_INSTANCE = r0;
        GeneratedMessageLite.registerDefaultInstance(PreferencesProto$PreferenceMap.class, r0);
    }

    private PreferencesProto$PreferenceMap() {
        this.preferences_ = MapFieldLite.emptyMapField();
    }

    public static /* synthetic */ PreferencesProto$PreferenceMap access$000() {
        return DEFAULT_INSTANCE;
    }

    public static /* synthetic */ Map access$100(PreferencesProto$PreferenceMap r0) {
        return r0.getMutablePreferencesMap();
    }

    private Map<String, PreferencesProto$Value> getMutablePreferencesMap() {
        return internalGetMutablePreferences();
    }

    private MapFieldLite<String, PreferencesProto$Value> internalGetMutablePreferences() {
        if (this.preferences_.isMutable() == true) goto L6;
        this.preferences_ = this.preferences_.mutableCopy();
    L6:
        return this.preferences_;
    }

    private MapFieldLite<String, PreferencesProto$Value> internalGetPreferences() {
        return this.preferences_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static PreferencesProto$PreferenceMap parseFrom(InputStream r1) {
        return (PreferencesProto$PreferenceMap) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, r1);
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
        Parser<PreferencesProto$PreferenceMap> r0 = PARSER;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(PreferencesProto$PreferenceMap.class);
        Parser r02 = PARSER;     // Catch: Throwable -> L15
        if (r02 != null) goto L17;
        r02 = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);     // Catch: Throwable -> L15
        PARSER = r02;     // Catch: Throwable -> L15
    L17:
        monitor-exit(PreferencesProto$PreferenceMap.class);     // Catch: Throwable -> L15
        return r02;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r03 = {"preferences_", PreferencesDefaultEntryHolder.defaultEntry};
        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", r03);
    L7:
        return (byte) 1;
    L23:
        return DEFAULT_INSTANCE;
    L27:
        return new Builder(null);
    L29:
        return new PreferencesProto$PreferenceMap();
    }

    public Map<String, PreferencesProto$Value> getPreferencesMap() {
        return Collections.unmodifiableMap(internalGetPreferences());
    }
}
