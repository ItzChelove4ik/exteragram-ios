package androidx.datastore.preferences.core;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.Serializer;
import androidx.datastore.preferences.PreferencesMapCompat;
import androidx.datastore.preferences.PreferencesProto$PreferenceMap;
import androidx.datastore.preferences.PreferencesProto$StringSet;
import androidx.datastore.preferences.PreferencesProto$Value;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.protobuf.ByteString;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011H\u0096@¢\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0096@¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Landroidx/datastore/preferences/core/PreferencesFileSerializer;", "Landroidx/datastore/core/Serializer;", "Landroidx/datastore/preferences/core/Preferences;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "Landroidx/datastore/preferences/PreferencesProto$Value;", "getValueProto", "(Ljava/lang/Object;)Landroidx/datastore/preferences/PreferencesProto$Value;", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Landroidx/datastore/preferences/core/MutablePreferences;", "mutablePreferences", _UrlKt.FRAGMENT_ENCODE_SET, "addProtoEntryToPreferences", "(Ljava/lang/String;Landroidx/datastore/preferences/PreferencesProto$Value;Landroidx/datastore/preferences/core/MutablePreferences;)V", "Ljava/io/InputStream;", PluginsConstants.Settings.TYPE_INPUT, "readFrom", "(Ljava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "t", "Ljava/io/OutputStream;", "output", "writeTo", "(Landroidx/datastore/preferences/core/Preferences;Ljava/io/OutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultValue", "()Landroidx/datastore/preferences/core/Preferences;", "defaultValue", "datastore-preferences-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPreferencesFileSerializer.jvmAndroid.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreferencesFileSerializer.jvmAndroid.kt\nandroidx/datastore/preferences/core/PreferencesFileSerializer\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,114:1\n215#2,2:115\n*S KotlinDebug\n*F\n+ 1 PreferencesFileSerializer.jvmAndroid.kt\nandroidx/datastore/preferences/core/PreferencesFileSerializer\n*L\n50#1:115,2\n*E\n"})
/* loaded from: classes.dex */
public final class PreferencesFileSerializer implements Serializer<Preferences> {
    public static final PreferencesFileSerializer INSTANCE = null;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[PreferencesProto$Value.ValueCase.values().length];
            r0[PreferencesProto$Value.ValueCase.BOOLEAN.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L25:
            r0[PreferencesProto$Value.ValueCase.FLOAT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
        L39:
            r0[PreferencesProto$Value.ValueCase.DOUBLE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L16
        L29:
            r0[PreferencesProto$Value.ValueCase.INTEGER.ordinal()] = 4;     // Catch: NoSuchFieldError -> L17
        L31:
            r0[PreferencesProto$Value.ValueCase.LONG.ordinal()] = 5;     // Catch: NoSuchFieldError -> L18
        L23:
            r0[PreferencesProto$Value.ValueCase.STRING.ordinal()] = 6;     // Catch: NoSuchFieldError -> L19
        L33:
            r0[PreferencesProto$Value.ValueCase.STRING_SET.ordinal()] = 7;     // Catch: NoSuchFieldError -> L20
        L35:
            r0[PreferencesProto$Value.ValueCase.BYTES.ordinal()] = 8;     // Catch: NoSuchFieldError -> L21
        L27:
            r0[PreferencesProto$Value.ValueCase.VALUE_NOT_SET.ordinal()] = 9;     // Catch: NoSuchFieldError -> L22
        L12:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        INSTANCE = new PreferencesFileSerializer();
    }

    private PreferencesFileSerializer() {
    }

    private final void addProtoEntryToPreferences(String r3, PreferencesProto$Value r4, MutablePreferences r5) {
        PreferencesProto$Value.ValueCase r2 = r4.getValueCase();
        if (r2 != null) goto L5;
        int r22 = -1;
    L7:
        switch(r22) {
            case -1: goto L29;
            case 0: goto L8;
            case 1: goto L26;
            case 2: goto L24;
            case 3: goto L22;
            case 4: goto L20;
            case 5: goto L18;
            case 6: goto L16;
            case 7: goto L14;
            case 8: goto L12;
            case 9: goto L11;
            default: goto L8;
        };
    L8:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L12:
        r5.set(PreferencesKeys.byteArrayKey(r3), r4.getBytes().toByteArray());
        return;
    L14:
        r5.set(PreferencesKeys.stringSetKey(r3), CollectionsKt.toSet(r4.getStringSet().getStringsList()));
        return;
    L16:
        r5.set(PreferencesKeys.stringKey(r3), r4.getString());
        return;
    L18:
        r5.set(PreferencesKeys.longKey(r3), Long.valueOf(r4.getLong()));
        return;
    L20:
        r5.set(PreferencesKeys.intKey(r3), Integer.valueOf(r4.getInteger()));
        return;
    L22:
        r5.set(PreferencesKeys.doubleKey(r3), Double.valueOf(r4.getDouble()));
        return;
    L24:
        r5.set(PreferencesKeys.floatKey(r3), Float.valueOf(r4.getFloat()));
        return;
    L26:
        r5.set(PreferencesKeys.booleanKey(r3), Boolean.valueOf(r4.getBoolean()));
        return;
    L11:
        throw new CorruptionException("Value not set.", null, 2, null);
    L29:
        throw new CorruptionException("Value case is null.", null, 2, null);
    L5:
        r22 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        goto L7
    }

    private final PreferencesProto$Value getValueProto(Object r3) {
        if ((r3 instanceof Boolean) == false) goto L7;
        return PreferencesProto$Value.newBuilder().setBoolean(((Boolean) r3).booleanValue()).build();
    L7:
        if ((r3 instanceof Float) == false) goto L11;
        return PreferencesProto$Value.newBuilder().setFloat(((Number) r3).floatValue()).build();
    L11:
        if ((r3 instanceof Double) == false) goto L15;
        return PreferencesProto$Value.newBuilder().setDouble(((Number) r3).doubleValue()).build();
    L15:
        if ((r3 instanceof Integer) == false) goto L19;
        return PreferencesProto$Value.newBuilder().setInteger(((Number) r3).intValue()).build();
    L19:
        if ((r3 instanceof Long) == false) goto L23;
        return PreferencesProto$Value.newBuilder().setLong(((Number) r3).longValue()).build();
    L23:
        if ((r3 instanceof String) == false) goto L27;
        return PreferencesProto$Value.newBuilder().setString((String) r3).build();
    L27:
        if ((r3 instanceof Set) == false) goto L31;
        return PreferencesProto$Value.newBuilder().setStringSet(PreferencesProto$StringSet.newBuilder().addAllStrings((Set) r3)).build();
    L31:
        if ((r3 instanceof byte[]) == true) goto L33;
        Segment$$ExternalSyntheticBUOutline1.m("PreferencesSerializer does not support type: ".concat(r3.getClass().getName()));
        return null;
    L33:
        return PreferencesProto$Value.newBuilder().setBytes(ByteString.copyFrom((byte[]) r3)).build();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.datastore.core.Serializer
    public Preferences getDefaultValue() {
        return PreferencesFactory.createEmpty();
    }

    @Override // androidx.datastore.core.Serializer
    public /* bridge */ /* synthetic */ Preferences getDefaultValue() {
        return getDefaultValue();
    }

    @Override // androidx.datastore.core.Serializer
    public Object readFrom(InputStream r3, Continuation<? super Preferences> r4) {
        PreferencesProto$PreferenceMap r2 = PreferencesMapCompat.Companion.readFrom(r3);
        MutablePreferences r32 = PreferencesFactory.createMutable(new Preferences.Pair[0]);
        Iterator<Map.Entry<String, PreferencesProto$Value>> r22 = r2.getPreferencesMap().entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        Map.Entry<String, PreferencesProto$Value> r42 = r22.next();
        String r0 = r42.getKey();
        PreferencesProto$Value r43 = r42.getValue();
        INSTANCE.addProtoEntryToPreferences(r0, r43, r32);
        goto L4
    L7:
        return r32.toPreferences();
    }

    /* renamed from: writeTo, reason: avoid collision after fix types in other method */
    public Object writeTo2(Preferences r3, OutputStream r4, Continuation<? super Unit> r5) {
        Map<Preferences.Key<?>, Object> r32 = r3.asMap();
        PreferencesProto$PreferenceMap.Builder r52 = PreferencesProto$PreferenceMap.newBuilder();
        Iterator<Map.Entry<Preferences.Key<?>, Object>> r33 = r32.entrySet().iterator();
    L4:
        if (r33.hasNext() == false) goto L6;
        Map.Entry<Preferences.Key<?>, Object> r0 = r33.next();
        Preferences.Key<?> r1 = r0.getKey();
        Object r02 = r0.getValue();
        r52.putPreferences(r1.getName(), getValueProto(r02));
        goto L4
    L6:
        r52.build().writeTo(r4);
        return Unit.INSTANCE;
    }

    @Override // androidx.datastore.core.Serializer
    public /* bridge */ /* synthetic */ Object writeTo(Preferences r1, OutputStream r2, Continuation r3) {
        return writeTo2(r1, r2, r3);
    }
}
