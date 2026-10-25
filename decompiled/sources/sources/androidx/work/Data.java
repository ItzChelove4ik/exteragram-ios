package androidx.work;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.Response$Builder$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001f\u001eB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004B\u001b\b\u0010\u0012\u0010\u0010\u0007\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0003\u0010\bJ\u0017\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ)\u0010\u0010\u001a\u00020\u000f\"\u0004\b\u0000\u0010\f2\u0006\u0010\t\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0017\u0010\u0014J\u000f\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001aR\u001f\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Landroidx/work/Data;", _UrlKt.FRAGMENT_ENCODE_SET, "other", "<init>", "(Landroidx/work/Data;)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "values", "(Ljava/util/Map;)V", PluginsConstants.Settings.KEY, "getString", "(Ljava/lang/String;)Ljava/lang/String;", "T", "Ljava/lang/Class;", "klass", _UrlKt.FRAGMENT_ENCODE_SET, "hasKeyWithValueOfType", "(Ljava/lang/String;Ljava/lang/Class;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "size", "()I", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "()Ljava/lang/String;", "Ljava/util/Map;", "getKeyValueMap", "()Ljava/util/Map;", "keyValueMap", "Companion", "Builder", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nData_.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Data_.kt\nandroidx/work/Data\n*L\n1#1,846:1\n55#1,2:847\n63#1,4:849\n55#1,2:853\n63#1,4:855\n55#1,2:859\n63#1,4:861\n55#1,2:865\n63#1,4:867\n55#1,2:871\n63#1,4:873\n55#1,2:877\n63#1,4:879\n63#1,4:883\n*S KotlinDebug\n*F\n+ 1 Data_.kt\nandroidx/work/Data\n*L\n77#1:847,2\n85#1:849,4\n94#1:853,2\n102#1:855,4\n111#1:859,2\n119#1:861,4\n128#1:865,2\n136#1:867,4\n145#1:871,2\n153#1:873,4\n163#1:877,2\n171#1:879,4\n187#1:883,4\n*E\n"})
/* loaded from: classes.dex */
public final class Data {
    public static final Companion Companion = null;

    @JvmField
    public static final Data EMPTY = null;
    private final Map<String, Object> values;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\t\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\r\u001a\u00020\u00002\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f¢\u0006\u0004\b\r\u0010\u0011J!\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\u0012\u0010\bJ\r\u0010\u0013\u001a\u00020\u000b¢\u0006\u0004\b\u0013\u0010\u0014R\"\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/work/Data$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "value", "putDirect", "(Ljava/lang/String;Ljava/lang/Object;)Landroidx/work/Data$Builder;", "putString", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/work/Data$Builder;", "Landroidx/work/Data;", "data", "putAll", "(Landroidx/work/Data;)Landroidx/work/Data$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "values", "(Ljava/util/Map;)Landroidx/work/Data$Builder;", "put", "build", "()Landroidx/work/Data;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/Map;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nData_.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Data_.kt\nandroidx/work/Data$Builder\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,846:1\n216#2,2:847\n*S KotlinDebug\n*F\n+ 1 Data_.kt\nandroidx/work/Data$Builder\n*L\n474#1:847,2\n*E\n"})
    public static final class Builder {
        private final Map<String, Object> values;

        public Builder() {
            this.values = new LinkedHashMap();
        }

        private final Builder putDirect(String r2, Object r3) {
            this.values.put(r2, r3);
            return this;
        }

        public final Data build() {
            Data r0 = new Data(this.values);
            Data.Companion.toByteArrayInternalV1(r0);
            return r0;
        }

        public final Builder put(String r5, Object r6) {
            Map<String, Object> r0 = this.values;
            if (r6 != null) goto L5;
            r6 = null;
        L54:
            r0.put(r5, r6);
            return this;
        L5:
            KClass r2 = Reflection.getOrCreateKotlinClass(r6.getClass());
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Byte.TYPE)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Integer.TYPE)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Long.TYPE)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Float.TYPE)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Double.TYPE)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(String.class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Boolean[].class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Byte[].class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Integer[].class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Long[].class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Float[].class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Double[].class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(String[].class)) == true) goto L54;
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(boolean[].class)) == false) goto L38;
            r6 = Data_Kt.access$convertPrimitiveArray((boolean[]) r6);
            goto L54
        L38:
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(byte[].class)) == false) goto L41;
            r6 = Data_Kt.access$convertPrimitiveArray((byte[]) r6);
            goto L54
        L41:
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(int[].class)) == false) goto L44;
            r6 = Data_Kt.access$convertPrimitiveArray((int[]) r6);
            goto L54
        L44:
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(long[].class)) == false) goto L47;
            r6 = Data_Kt.access$convertPrimitiveArray((long[]) r6);
            goto L54
        L47:
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(float[].class)) == false) goto L50;
            r6 = Data_Kt.access$convertPrimitiveArray((float[]) r6);
            goto L54
        L50:
            if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(double[].class)) == false) goto L52;
            r6 = Data_Kt.access$convertPrimitiveArray((double[]) r6);
            goto L54
        L52:
            Utils$$ExternalSyntheticBUOutline2.m("Key ", r5, " has invalid type ", r2);
            return null;
        }

        public final Builder putAll(Data r1) {
            putAll(Data.access$getValues$p(r1));
            return this;
        }

        public final Builder putAll(Map<String, ? extends Object> r3) {
            Iterator<Map.Entry<String, ? extends Object>> r32 = r3.entrySet().iterator();
        L4:
            if (r32.hasNext() == false) goto L6;
            Map.Entry<String, ? extends Object> r0 = r32.next();
            put(r0.getKey(), r0.getValue());
            goto L4
        L6:
            return this;
        }

        public final Builder putString(String r1, String r2) {
            return putDirect(r1, r2);
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0006X\u0087T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0013R\u0014\u0010\u0019\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0013R\u0014\u0010\u001a\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0013R\u0014\u0010\u001b\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001b\u0010\u0013R\u0014\u0010\u001c\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001c\u0010\u0013R\u0014\u0010\u001d\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001d\u0010\u0013R\u0014\u0010\u001e\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001e\u0010\u0013R\u0014\u0010\u001f\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001f\u0010\u0013R\u0014\u0010 \u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b \u0010\u0013R\u0014\u0010!\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010\u0013R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082T¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082T¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020%8\u0002X\u0082T¢\u0006\u0006\n\u0004\b(\u0010'¨\u0006)"}, d2 = {"Landroidx/work/Data$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/work/Data;", "data", _UrlKt.FRAGMENT_ENCODE_SET, "toByteArrayInternalV1", "(Landroidx/work/Data;)[B", "bytes", "fromByteArray", "([B)Landroidx/work/Data;", "EMPTY", "Landroidx/work/Data;", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_DATA_BYTES", "I", _UrlKt.FRAGMENT_ENCODE_SET, "TYPE_NULL", "B", "TYPE_BOOLEAN", "TYPE_BYTE", "TYPE_INTEGER", "TYPE_LONG", "TYPE_FLOAT", "TYPE_DOUBLE", "TYPE_STRING", "TYPE_BOOLEAN_ARRAY", "TYPE_BYTE_ARRAY", "TYPE_INTEGER_ARRAY", "TYPE_LONG_ARRAY", "TYPE_FLOAT_ARRAY", "TYPE_DOUBLE_ARRAY", "TYPE_STRING_ARRAY", _UrlKt.FRAGMENT_ENCODE_SET, "NULL_STRING_V1", "Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "STREAM_MAGIC", "S", "STREAM_VERSION", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nData_.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Data_.kt\nandroidx/work/Data$Companion\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,846:1\n32#2:847\n32#2:848\n32#2:850\n32#2:851\n1#3:849\n*S KotlinDebug\n*F\n+ 1 Data_.kt\nandroidx/work/Data$Companion\n*L\n604#1:847\n715#1:848\n819#1:850\n821#1:851\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private static final boolean fromByteArray$isObjectStream(ByteArrayInputStream r4) {
            byte[] r0 = new byte[2];
            r4.read(r0);
            boolean r1 = false;
            if (r0[0] == (-84)) goto L5;
        L7:
            r4.reset();
            return r1;
        L5:
            if (r0[1] != (-19)) goto L7;
            r1 = true;
            goto L7
        }

        private static final void fromByteArray$readHeader(DataInputStream r2) {
            short r0 = r2.readShort();
            if (r0 != (-21521)) goto L9;
            short r22 = r2.readShort();
            if (r22 != 1) goto L7;
            return;
        L7:
            Response$Builder$$ExternalSyntheticBUOutline1.m("Unsupported version number: ", r22);
            return;
        L9:
            Response$Builder$$ExternalSyntheticBUOutline1.m("Magic number doesn't match: ", r0);
        }

        private static final Object fromByteArray$readValue(DataInputStream r5, byte r6) {
            if (r6 != 0) goto L6;
            return null;
        L6:
            if (r6 != 1) goto L10;
            return Boolean.valueOf(r5.readBoolean());
        L10:
            if (r6 != 2) goto L14;
            return Byte.valueOf(r5.readByte());
        L14:
            if (r6 != 3) goto L18;
            return Integer.valueOf(r5.readInt());
        L18:
            if (r6 != 4) goto L22;
            return Long.valueOf(r5.readLong());
        L22:
            if (r6 != 5) goto L26;
            return Float.valueOf(r5.readFloat());
        L26:
            if (r6 != 6) goto L30;
            return Double.valueOf(r5.readDouble());
        L30:
            if (r6 == 7) goto L32;
            int r2 = 0;
            if (r6 != 8) goto L40;
            int r62 = r5.readInt();
            Boolean[] r0 = new Boolean[r62];
        L36:
            if (r2 >= r62) goto L38;
            r0[r2] = Boolean.valueOf(r5.readBoolean());
            r2 = r2 + 1;
            goto L36
        L38:
            return r0;
        L40:
            if (r6 != 9) goto L46;
            int r63 = r5.readInt();
            Byte[] r02 = new Byte[r63];
        L42:
            if (r2 >= r63) goto L44;
            r02[r2] = Byte.valueOf(r5.readByte());
            r2 = r2 + 1;
            goto L42
        L44:
            return r02;
        L46:
            if (r6 != 10) goto L52;
            int r64 = r5.readInt();
            Integer[] r03 = new Integer[r64];
        L48:
            if (r2 >= r64) goto L50;
            r03[r2] = Integer.valueOf(r5.readInt());
            r2 = r2 + 1;
            goto L48
        L50:
            return r03;
        L52:
            if (r6 != 11) goto L58;
            int r65 = r5.readInt();
            Long[] r04 = new Long[r65];
        L54:
            if (r2 >= r65) goto L56;
            r04[r2] = Long.valueOf(r5.readLong());
            r2 = r2 + 1;
            goto L54
        L56:
            return r04;
        L58:
            if (r6 != 12) goto L64;
            int r66 = r5.readInt();
            Float[] r05 = new Float[r66];
        L60:
            if (r2 >= r66) goto L62;
            r05[r2] = Float.valueOf(r5.readFloat());
            r2 = r2 + 1;
            goto L60
        L62:
            return r05;
        L64:
            if (r6 != 13) goto L70;
            int r67 = r5.readInt();
            Double[] r06 = new Double[r67];
        L66:
            if (r2 >= r67) goto L68;
            r06[r2] = Double.valueOf(r5.readDouble());
            r2 = r2 + 1;
            goto L66
        L68:
            return r06;
        L70:
            if (r6 != 14) goto L78;
            int r68 = r5.readInt();
            String[] r1 = new String[r68];
        L72:
            if (r2 >= r68) goto L77;
            String r3 = r5.readUTF();
            if (Intrinsics.areEqual(r3, "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d") == false) goto L76;
            r3 = null;
        L76:
            r1[r2] = r3;
            r2 = r2 + 1;
            goto L72
        L77:
            return r1;
        L78:
            JsonArray$$ExternalSyntheticBUOutline0.m("Unsupported type ", r6);
            return null;
        L32:
            return r5.readUTF();
        }

        private static final void toByteArrayInternalV1$writeArray(DataOutputStream r14, Object[] r15) {
            KClass r0 = Reflection.getOrCreateKotlinClass(r15.getClass());
            if (Intrinsics.areEqual(r0, Reflection.getOrCreateKotlinClass(Boolean[].class)) == false) goto L6;
            int r02 = 8;
        L23:
            r14.writeByte(r02);
            r14.writeInt(r15.length);
            int r1 = r15.length;
            int r10 = 0;
        L24:
            if (r10 >= r1) goto L82;
            Object r11 = r15[r10];
            String r12 = null;
            Boolean r122 = null;
            Byte r123 = null;
            Integer r124 = null;
            Long r125 = null;
            Float r126 = null;
            Double r127 = null;
            if (r02 == 8) goto L28;
            if (r02 == 9) goto L36;
            if (r02 == 10) goto L44;
            if (r02 == 11) goto L52;
            if (r02 == 12) goto L60;
            if (r02 == 13) goto L68;
            if (r02 != 14) goto L81;
            if ((r11 instanceof String) == false) goto L78;
            r12 = (String) r11;
        L78:
            if (r12 != null) goto L80;
            r12 = "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d";
        L80:
            r14.writeUTF(r12);
        L81:
            r10 = r10 + 1;
            goto L24
        L68:
            if ((r11 instanceof Double) == false) goto L70;
            r127 = (Double) r11;
        L70:
            if (r127 == null) goto L72;
            double r112 = r127.doubleValue();
        L73:
            r14.writeDouble(r112);
            goto L81
        L72:
            r112 = 0.0d;
            goto L73
        L60:
            if ((r11 instanceof Float) == false) goto L62;
            r126 = (Float) r11;
        L62:
            if (r126 == null) goto L64;
            float r113 = r126.floatValue();
        L65:
            r14.writeFloat(r113);
            goto L81
        L64:
            r113 = 0.0f;
            goto L65
        L52:
            if ((r11 instanceof Long) == false) goto L54;
            r125 = (Long) r11;
        L54:
            if (r125 == null) goto L56;
            long r114 = r125.longValue();
        L57:
            r14.writeLong(r114);
            goto L81
        L56:
            r114 = 0;
            goto L57
        L44:
            if ((r11 instanceof Integer) == false) goto L46;
            r124 = (Integer) r11;
        L46:
            if (r124 == null) goto L48;
            int r115 = r124.intValue();
        L49:
            r14.writeInt(r115);
            goto L81
        L48:
            r115 = 0;
            goto L49
        L36:
            if ((r11 instanceof Byte) == false) goto L38;
            r123 = (Byte) r11;
        L38:
            if (r123 == null) goto L40;
            byte r116 = r123.byteValue();
        L41:
            r14.writeByte(r116);
            goto L81
        L40:
            r116 = 0;
            goto L41
        L28:
            if ((r11 instanceof Boolean) == false) goto L30;
            r122 = (Boolean) r11;
        L30:
            if (r122 == null) goto L32;
            boolean r117 = r122.booleanValue();
        L33:
            r14.writeBoolean(r117);
            goto L81
        L32:
            r117 = false;
            goto L33
        L82:
            return;
        L6:
            if (Intrinsics.areEqual(r0, Reflection.getOrCreateKotlinClass(Byte[].class)) == false) goto L9;
            r02 = 9;
            goto L23
        L9:
            if (Intrinsics.areEqual(r0, Reflection.getOrCreateKotlinClass(Integer[].class)) == false) goto L12;
            r02 = 10;
            goto L23
        L12:
            if (Intrinsics.areEqual(r0, Reflection.getOrCreateKotlinClass(Long[].class)) == false) goto L15;
            r02 = 11;
            goto L23
        L15:
            if (Intrinsics.areEqual(r0, Reflection.getOrCreateKotlinClass(Float[].class)) == false) goto L18;
            r02 = 12;
            goto L23
        L18:
            if (Intrinsics.areEqual(r0, Reflection.getOrCreateKotlinClass(Double[].class)) == false) goto L21;
            r02 = 13;
            goto L23
        L21:
            if (Intrinsics.areEqual(r0, Reflection.getOrCreateKotlinClass(String[].class)) == false) goto L83;
            r02 = 14;
            goto L23
        L83:
            Buffer$$ExternalSyntheticBUOutline4.m("Unsupported value type ", Reflection.getOrCreateKotlinClass(r15.getClass()).getQualifiedName());
        }

        private static final void toByteArrayInternalV1$writeEntry(DataOutputStream r2, String r3, Object r4) {
            if (r4 != null) goto L5;
            r2.writeByte(0);
        L28:
            r2.writeUTF(r3);
            return;
        L5:
            if ((r4 instanceof Boolean) == false) goto L8;
            r2.writeByte(1);
            r2.writeBoolean(((Boolean) r4).booleanValue());
            goto L28
        L8:
            if ((r4 instanceof Byte) == false) goto L11;
            r2.writeByte(2);
            r2.writeByte(((Number) r4).byteValue());
            goto L28
        L11:
            if ((r4 instanceof Integer) == false) goto L14;
            r2.writeByte(3);
            r2.writeInt(((Number) r4).intValue());
            goto L28
        L14:
            if ((r4 instanceof Long) == false) goto L17;
            r2.writeByte(4);
            r2.writeLong(((Number) r4).longValue());
            goto L28
        L17:
            if ((r4 instanceof Float) == false) goto L20;
            r2.writeByte(5);
            r2.writeFloat(((Number) r4).floatValue());
            goto L28
        L20:
            if ((r4 instanceof Double) == false) goto L23;
            r2.writeByte(6);
            r2.writeDouble(((Number) r4).doubleValue());
            goto L28
        L23:
            if ((r4 instanceof String) == false) goto L26;
            r2.writeByte(7);
            r2.writeUTF((String) r4);
            goto L28
        L26:
            if ((r4 instanceof Object[]) == false) goto L30;
            toByteArrayInternalV1$writeArray(r2, (Object[]) r4);
            goto L28
        L30:
            Buffer$$ExternalSyntheticBUOutline4.m("Unsupported value type ", Reflection.getOrCreateKotlinClass(r4.getClass()).getSimpleName());
        }

        private static final void toByteArrayInternalV1$writeHeader(DataOutputStream r1) {
            r1.writeShort(-21521);
            r1.writeShort(1);
        }

        @JvmStatic
        public final Data fromByteArray(byte[] r7) {
            if (r7.length <= 10240) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Data cannot occupy more than 10240 bytes when serialized");
            return null;
        L5:
            if (r7.length == 0) goto L7;
            LinkedHashMap r0 = new LinkedHashMap();
            ByteArrayInputStream r1 = new ByteArrayInputStream(r7);     // Catch: ClassNotFoundException -> L20 IOException -> L22
            int r3 = 0;
            if (fromByteArray$isObjectStream(r1) == false) goto L28;
            ObjectInputStream r72 = new ObjectInputStream(r1);     // Catch: ClassNotFoundException -> L20 IOException -> L22
            int r12 = r72.readInt();     // Catch: Throwable -> L16
        L13:
            if (r3 >= r12) goto L18;
            r0.put(r72.readUTF(), r72.readObject());     // Catch: Throwable -> L16
            r3 = r3 + 1;
            goto L13
        L18:
            CloseableKt.closeFinally(r72, null);     // Catch: ClassNotFoundException -> L20 IOException -> L22
        L44:
            return new Data(r0);
        L16:
            th = move-exception;
            throw th;     // Catch: Throwable -> L25
        L25:
            th = move-exception;
            CloseableKt.closeFinally(r72, th);     // Catch: ClassNotFoundException -> L20 IOException -> L22
            throw th;     // Catch: ClassNotFoundException -> L20 IOException -> L22
        L28:
            DataInputStream r73 = new DataInputStream(r1);     // Catch: ClassNotFoundException -> L20 IOException -> L22
            fromByteArray$readHeader(r73);     // Catch: Throwable -> L33
            int r13 = r73.readInt();     // Catch: Throwable -> L33
        L30:
            if (r3 >= r13) goto L35;
            Object r4 = fromByteArray$readValue(r73, r73.readByte());     // Catch: Throwable -> L33
            r0.put(r73.readUTF(), r4);     // Catch: Throwable -> L33
            r3 = r3 + 1;
            goto L30
        L35:
            CloseableKt.closeFinally(r73, null);     // Catch: ClassNotFoundException -> L20 IOException -> L22
        L33:
            th = move-exception;
            throw th;     // Catch: Throwable -> L38
        L38:
            th = move-exception;
            CloseableKt.closeFinally(r73, th);     // Catch: ClassNotFoundException -> L20 IOException -> L22
            throw th;     // Catch: ClassNotFoundException -> L20 IOException -> L22
        L22:
            e = move-exception;
            String r14 = Data_Kt.access$getTAG$p();
            Logger.get().error(r14, "Error in Data#fromByteArray: ", e);
        L20:
            e = move-exception;
            String r15 = Data_Kt.access$getTAG$p();
            Logger.get().error(r15, "Error in Data#fromByteArray: ", e);
            goto L44
        L7:
            return Data.EMPTY;
        }

        @JvmStatic
        public final byte[] toByteArrayInternalV1(Data r4) {
            ByteArrayOutputStream r3 = new ByteArrayOutputStream();     // Catch: IOException -> L21
            DataOutputStream r0 = new DataOutputStream(r3);     // Catch: IOException -> L21
            toByteArrayInternalV1$writeHeader(r0);     // Catch: Throwable -> L7
            r0.writeInt(r4.size());     // Catch: Throwable -> L7
            Iterator r42 = Data.access$getValues$p(r4).entrySet().iterator();     // Catch: Throwable -> L7
        L5:
            if (r42.hasNext() == false) goto L9;
            Map.Entry r1 = (Map.Entry) r42.next();     // Catch: Throwable -> L7
            toByteArrayInternalV1$writeEntry(r0, (String) r1.getKey(), r1.getValue());     // Catch: Throwable -> L7
            goto L5
        L9:
            r0.flush();     // Catch: Throwable -> L7
            if (r0.size() > 10240) goto L16;
            byte[] r32 = r3.toByteArray();     // Catch: Throwable -> L7
            CloseableKt.closeFinally(r0, null);     // Catch: IOException -> L21
            return r32;
        L16:
            throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");     // Catch: Throwable -> L7
        L7:
            th = move-exception;
            throw th;     // Catch: Throwable -> L18
        L18:
            th = move-exception;
            CloseableKt.closeFinally(r0, th);     // Catch: IOException -> L21
            throw th;     // Catch: IOException -> L21
        L21:
            e = move-exception;
            String r43 = Data_Kt.access$getTAG$p();
            Logger.get().error(r43, "Error in Data#toByteArray: ", e);
            return new byte[0];
        }
    }

    public static CharSequence $r8$lambda$CYyY16ThCkJ_rLJonZYIKWG1Uyk(Map.Entry r2) {
        String r0 = (String) r2.getKey();
        Object r22 = r2.getValue();
        StringBuilder r1 = new StringBuilder();
        r1.append(r0);
        r1.append(" : ");
        if ((r22 instanceof Object[]) == false) goto L5;
        r22 = Arrays.toString((Object[]) r22);
    L5:
        r1.append(r22);
        return r1.toString();
    }

    static {
        Companion = new Companion(null);
        EMPTY = new Builder().build();
    }

    public Data(Data r2) {
        this.values = new HashMap(r2.values);
    }

    public Data(Map<String, ?> r2) {
        this.values = new HashMap(r2);
    }

    public static final /* synthetic */ Map access$getValues$p(Data r0) {
        return r0.values;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if (r8 != null) goto L8;
    L32:
        return false;
    L8:
        if (Intrinsics.areEqual(Data.class, r8.getClass()) == false) goto L32;
        Data r82 = (Data) r8;
        Set<String> r2 = this.values.keySet();
        if (Intrinsics.areEqual(r2, r82.values.keySet()) == true) goto L13;
        return false;
    L13:
        Iterator<String> r22 = r2.iterator();
    L15:
        if (r22.hasNext() == false) goto L31;
        String r3 = r22.next();
        Object r4 = this.values.get(r3);
        Object r32 = r82.values.get(r3);
        if (r4 == null) goto L26;
        if (r32 == null) goto L26;
        if ((r4 instanceof Object[]) == false) goto L25;
        Object[] r5 = (Object[]) r4;
        if ((r32 instanceof Object[]) == false) goto L25;
        boolean r33 = ArraysKt.contentDeepEquals(r5, (Object[]) r32);
    L29:
        if (r33 == true) goto L15;
        return false;
    L25:
        r33 = Intrinsics.areEqual(r4, r32);
    L26:
        if (r4 != r32) goto L28;
        r33 = true;
        goto L29
    L28:
        r33 = false;
        goto L29
    L31:
        return true;
    }

    public final Map<String, Object> getKeyValueMap() {
        return Collections.unmodifiableMap(this.values);
    }

    public final String getString(String r1) {
        Object r0 = this.values.get(r1);
        if ((r0 instanceof String) == true) goto L5;
        return null;
    L5:
        return (String) r0;
    }

    public final <T> boolean hasKeyWithValueOfType(String r1, Class<T> r2) {
        Object r0 = this.values.get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r2.isAssignableFrom(r0.getClass()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public int hashCode() {
        Iterator<Map.Entry<String, Object>> r4 = this.values.entrySet().iterator();
        int r0 = 0;
    L4:
        if (r4.hasNext() == false) goto L11;
        Map.Entry<String, Object> r1 = r4.next();
        Object r2 = r1.getValue();
        if ((r2 instanceof Object[]) == false) goto L8;
        int r12 = Objects.hashCode(r1.getKey()) ^ ArraysKt.contentDeepHashCode((Object[]) r2);
    L9:
        r0 = r0 + r12;
        goto L4
    L8:
        r12 = r1.hashCode();
        goto L9
    L11:
        return r0 * 31;
    }

    public final int size() {
        return this.values.size();
    }

    public String toString() {
        return "Data {" + CollectionsKt.joinToString$default(this.values.entrySet(), null, null, null, 0, null, new Data$$ExternalSyntheticLambda0(), 31, null) + "}";
    }
}
