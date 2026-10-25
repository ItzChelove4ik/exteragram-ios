package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.WireFormat;
import androidx.datastore.preferences.protobuf.Writer;
import com.android.dx.dex.code.LocalList$$ExternalSyntheticBUOutline0;
import com.google.protobuf.MessageSchema$$ExternalSyntheticBUOutline0;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int[] EMPTY_INT_ARRAY = null;
    private static final Unsafe UNSAFE = null;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final int repeatedFieldOffsetStart;
    private final ProtoSyntax syntax;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;

    static {
        EMPTY_INT_ARRAY = new int[0];
        UNSAFE = UnsafeUtil.getUnsafe();
    }

    private MessageSchema(int[] r1, Object[] r2, int r3, int r4, MessageLite r5, ProtoSyntax r6, boolean r7, int[] r8, int r9, int r10, NewInstanceSchema r11, ListFieldSchema r12, UnknownFieldSchema<?, ?> r13, ExtensionSchema<?> r14, MapFieldSchema r15) {
        this.buffer = r1;
        this.objects = r2;
        this.minFieldNumber = r3;
        this.maxFieldNumber = r4;
        this.lite = r5 instanceof GeneratedMessageLite;
        this.syntax = r6;
        if (r14 != null) goto L5;
    L7:
        boolean r16 = false;
    L8:
        this.hasExtensions = r16;
        this.useCachedSizeField = r7;
        this.intArray = r8;
        this.checkInitializedCount = r9;
        this.repeatedFieldOffsetStart = r10;
        this.newInstanceSchema = r11;
        this.listFieldSchema = r12;
        this.unknownFieldSchema = r13;
        this.extensionSchema = r14;
        this.defaultInstance = r5;
        this.mapFieldSchema = r15;
        return;
    L5:
        if (r14.hasExtensions(r5) == false) goto L7;
        r16 = true;
        goto L8
    }

    private boolean arePresentForEquals(T r1, T r2, int r3) {
        if (isFieldPresent(r1, r3) != isFieldPresent(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private static <T> boolean booleanAt(T r0, long r1) {
        return UnsafeUtil.getBoolean(r0, r1);
    }

    private static void checkMutable(Object r1) {
        if (isMutable(r1) == false) goto L5;
        return;
    L5:
        Native$$ExternalSyntheticBUOutline5.m("Mutating immutable message: ", r1);
    }

    private static <T> double doubleAt(T r0, long r1) {
        return UnsafeUtil.getDouble(r0, r1);
    }

    private boolean equals(T r6, T r7, int r8) {
        int r0 = typeAndOffsetAt(r8);
        long r1 = offset(r0);
        switch(type(r0)) {
            case 0: goto L118;
            case 1: goto L112;
            case 2: goto L106;
            case 3: goto L100;
            case 4: goto L94;
            case 5: goto L88;
            case 6: goto L82;
            case 7: goto L76;
            case 8: goto L70;
            case 9: goto L64;
            case 10: goto L58;
            case 11: goto L52;
            case 12: goto L46;
            case 13: goto L40;
            case 14: goto L34;
            case 15: goto L28;
            case 16: goto L22;
            case 17: goto L16;
            case 18: goto L14;
            case 19: goto L14;
            case 20: goto L14;
            case 21: goto L14;
            case 22: goto L14;
            case 23: goto L14;
            case 24: goto L14;
            case 25: goto L14;
            case 26: goto L14;
            case 27: goto L14;
            case 28: goto L14;
            case 29: goto L14;
            case 30: goto L14;
            case 31: goto L14;
            case 32: goto L14;
            case 33: goto L14;
            case 34: goto L14;
            case 35: goto L14;
            case 36: goto L14;
            case 37: goto L14;
            case 38: goto L14;
            case 39: goto L14;
            case 40: goto L14;
            case 41: goto L14;
            case 42: goto L14;
            case 43: goto L14;
            case 44: goto L14;
            case 45: goto L14;
            case 46: goto L14;
            case 47: goto L14;
            case 48: goto L14;
            case 49: goto L14;
            case 50: goto L12;
            case 51: goto L6;
            case 52: goto L6;
            case 53: goto L6;
            case 54: goto L6;
            case 55: goto L6;
            case 56: goto L6;
            case 57: goto L6;
            case 58: goto L6;
            case 59: goto L6;
            case 60: goto L6;
            case 61: goto L6;
            case 62: goto L6;
            case 63: goto L6;
            case 64: goto L6;
            case 65: goto L6;
            case 66: goto L6;
            case 67: goto L6;
            case 68: goto L6;
            default: goto L4;
        };
    L4:
        return true;
    L6:
        if (isOneofCaseEqual(r6, r7, r8) == true) goto L8;
    L10:
        return false;
    L8:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L10;
        return true;
    L12:
        return SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1));
    L14:
        return SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1));
    L16:
        if (arePresentForEquals(r6, r7, r8) == true) goto L18;
    L20:
        return false;
    L18:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L20;
        return true;
    L22:
        if (arePresentForEquals(r6, r7, r8) == true) goto L24;
    L26:
        return false;
    L24:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L26;
        return true;
    L28:
        if (arePresentForEquals(r6, r7, r8) == true) goto L30;
    L32:
        return false;
    L30:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L32;
        return true;
    L34:
        if (arePresentForEquals(r6, r7, r8) == true) goto L36;
    L38:
        return false;
    L36:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L38;
        return true;
    L40:
        if (arePresentForEquals(r6, r7, r8) == true) goto L42;
    L44:
        return false;
    L42:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L44;
        return true;
    L46:
        if (arePresentForEquals(r6, r7, r8) == true) goto L48;
    L50:
        return false;
    L48:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L50;
        return true;
    L52:
        if (arePresentForEquals(r6, r7, r8) == true) goto L54;
    L56:
        return false;
    L54:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L56;
        return true;
    L58:
        if (arePresentForEquals(r6, r7, r8) == true) goto L60;
    L62:
        return false;
    L60:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L62;
        return true;
    L64:
        if (arePresentForEquals(r6, r7, r8) == true) goto L66;
    L68:
        return false;
    L66:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L68;
        return true;
    L70:
        if (arePresentForEquals(r6, r7, r8) == true) goto L72;
    L74:
        return false;
    L72:
        if (SchemaUtil.safeEquals(UnsafeUtil.getObject(r6, r1), UnsafeUtil.getObject(r7, r1)) == false) goto L74;
        return true;
    L76:
        if (arePresentForEquals(r6, r7, r8) == true) goto L78;
    L80:
        return false;
    L78:
        if (UnsafeUtil.getBoolean(r6, r1) != UnsafeUtil.getBoolean(r7, r1)) goto L80;
        return true;
    L82:
        if (arePresentForEquals(r6, r7, r8) == true) goto L84;
    L86:
        return false;
    L84:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L86;
        return true;
    L88:
        if (arePresentForEquals(r6, r7, r8) == true) goto L90;
    L92:
        return false;
    L90:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L92;
        return true;
    L94:
        if (arePresentForEquals(r6, r7, r8) == true) goto L96;
    L98:
        return false;
    L96:
        if (UnsafeUtil.getInt(r6, r1) != UnsafeUtil.getInt(r7, r1)) goto L98;
        return true;
    L100:
        if (arePresentForEquals(r6, r7, r8) == true) goto L102;
    L104:
        return false;
    L102:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L104;
        return true;
    L106:
        if (arePresentForEquals(r6, r7, r8) == true) goto L108;
    L110:
        return false;
    L108:
        if (UnsafeUtil.getLong(r6, r1) != UnsafeUtil.getLong(r7, r1)) goto L110;
        return true;
    L112:
        if (arePresentForEquals(r6, r7, r8) == true) goto L114;
    L116:
        return false;
    L114:
        if (Float.floatToIntBits(UnsafeUtil.getFloat(r6, r1)) != Float.floatToIntBits(UnsafeUtil.getFloat(r7, r1))) goto L116;
        return true;
    L118:
        if (arePresentForEquals(r6, r7, r8) == true) goto L120;
    L122:
        return false;
    L120:
        if (Double.doubleToLongBits(UnsafeUtil.getDouble(r6, r1)) != Double.doubleToLongBits(UnsafeUtil.getDouble(r7, r1))) goto L122;
        return true;
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object r1, int r2, UB r3, UnknownFieldSchema<UT, UB> r4, Object r5) {
        numberAt(r2);
        if (UnsafeUtil.getObject(r1, offset(typeAndOffsetAt(r2))) != null) goto L5;
        return r3;
    L5:
        getEnumFieldVerifier(r2);
        return r3;
    }

    private static <T> float floatAt(T r0, long r1) {
        return UnsafeUtil.getFloat(r0, r1);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.objects[((r1 / 3) * 2) + 1]);
        return null;
    }

    private Object getMapFieldDefaultEntry(int r1) {
        return this.objects[(r1 / 3) * 2];
    }

    private Schema getMessageFieldSchema(int r4) {
        int r42 = (r4 / 3) * 2;
        Schema r0 = (Schema) this.objects[r42];
        if (r0 == null) goto L5;
        return r0;
    L5:
        Schema<T> r02 = Protobuf.getInstance().schemaFor((Class) this.objects[r42 + 1]);
        this.objects[r42] = r02;
        return r02;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> r1, T r2) {
        return r1.getSerializedSize(r1.getFromMessage(r2));
    }

    private static <T> int intAt(T r0, long r1) {
        return UnsafeUtil.getInt(r0, r1);
    }

    private static boolean isEnforceUtf8(int r1) {
        if ((r1 & 536870912) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isFieldPresent(T r7, int r8) {
        int r0 = presenceMaskAndOffsetAt(r8);
        long r1 = 1048575 & r0;
        if (r1 != 1048575) goto L82;
        int r6 = typeAndOffsetAt(r8);
        long r02 = offset(r6);
        switch(type(r6)) {
            case 0: goto L78;
            case 1: goto L74;
            case 2: goto L70;
            case 3: goto L66;
            case 4: goto L62;
            case 5: goto L58;
            case 6: goto L54;
            case 7: goto L52;
            case 8: goto L43;
            case 9: goto L40;
            case 10: goto L36;
            case 11: goto L33;
            case 12: goto L29;
            case 13: goto L25;
            case 14: goto L21;
            case 15: goto L17;
            case 16: goto L13;
            case 17: goto L9;
            default: goto L6;
        };
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L36:
        boolean r62 = ByteString.EMPTY.equals(UnsafeUtil.getObject(r7, r02));
    L38:
        return !r62;
    L43:
        Object r63 = UnsafeUtil.getObject(r7, r02);
        if ((r63 instanceof String) == false) goto L47;
        r62 = ((String) r63).isEmpty();
        goto L38
    L47:
        if ((r63 instanceof ByteString) == false) goto L49;
        r62 = ByteString.EMPTY.equals(r63);
        goto L38
    L49:
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L9:
        if (UnsafeUtil.getObject(r7, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L40:
        if (UnsafeUtil.getObject(r7, r02) == null) goto L42;
        return true;
    L42:
        return false;
    L52:
        return UnsafeUtil.getBoolean(r7, r02);
    L54:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L56;
        return true;
    L56:
        return false;
    L58:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L60;
        return true;
    L60:
        return false;
    L62:
        if (UnsafeUtil.getInt(r7, r02) == 0) goto L64;
        return true;
    L64:
        return false;
    L66:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L68;
        return true;
    L68:
        return false;
    L70:
        if (UnsafeUtil.getLong(r7, r02) == 0) goto L72;
        return true;
    L72:
        return false;
    L74:
        if (Float.floatToRawIntBits(UnsafeUtil.getFloat(r7, r02)) == 0) goto L76;
        return true;
    L76:
        return false;
    L78:
        if (Double.doubleToRawLongBits(UnsafeUtil.getDouble(r7, r02)) == 0) goto L80;
        return true;
    L80:
        return false;
    L82:
        if (((1 << (r0 >>> 20)) & UnsafeUtil.getInt(r7, r1)) == 0) goto L84;
        return true;
    L84:
        return false;
    }

    private boolean isFieldPresent(T r2, int r3, int r4, int r5, int r6) {
        if (r4 != 1048575) goto L7;
        return isFieldPresent(r2, r3);
    L7:
        if ((r5 & r6) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object r2, int r3, Schema r4) {
        return r4.isInitialized(UnsafeUtil.getObject(r2, offset(r3)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object r3, int r4, int r5) {
        List r32 = (List) UnsafeUtil.getObject(r3, offset(r4));
        if (r32.isEmpty() == false) goto L5;
        return true;
    L5:
        Schema r2 = getMessageFieldSchema(r5);
        int r52 = 0;
    L7:
        if (r52 >= r32.size()) goto L12;
        if (r2.isInitialized(r32.get(r52)) == false) goto L10;
        r52 = r52 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [androidx.datastore.preferences.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    private boolean isMapInitialized(T r4, int r5, int r6) {
        Map<?, ?> r42 = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(r4, offset(r5)));
        if (r42.isEmpty() == false) goto L5;
        return true;
    L5:
        Object r52 = getMapFieldDefaultEntry(r6);
        if (this.mapFieldSchema.forMapMetadata(r52).valueType.getJavaType() == WireFormat.JavaType.MESSAGE) goto L8;
        return true;
    L8:
        Iterator<?> r3 = r42.values().iterator();
        ?? r43 = 0;
    L10:
        if (r3.hasNext() == false) goto L18;
        Object r53 = r3.next();
        r43 = r43;
        if (r43 != 0) goto L15;
        r43 = Protobuf.getInstance().schemaFor(r53.getClass());
    L15:
        if (r43.isInitialized(r53) == true) goto L10;
        return false;
    L18:
        return true;
    }

    private static boolean isMutable(Object r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        if ((r1 instanceof GeneratedMessageLite) == true) goto L8;
        return true;
    L8:
        return ((GeneratedMessageLite) r1).isMutable();
    }

    private boolean isOneofCaseEqual(T r3, T r4, int r5) {
        long r0 = presenceMaskAndOffsetAt(r5) & 1048575;
        if (UnsafeUtil.getInt(r3, r0) != UnsafeUtil.getInt(r4, r0)) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isOneofPresent(T r3, int r4, int r5) {
        if (UnsafeUtil.getInt(r3, presenceMaskAndOffsetAt(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isRequired(int r1) {
        if ((r1 & 268435456) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static <T> long longAt(T r0, long r1) {
        return UnsafeUtil.getLong(r0, r1);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private <UT, UB, ET extends androidx.datastore.preferences.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema<UT, UB> r18, androidx.datastore.preferences.protobuf.ExtensionSchema<ET> r19, T r20, androidx.datastore.preferences.protobuf.Reader r21, androidx.datastore.preferences.protobuf.ExtensionRegistryLite r22) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0000: MOVE (r1v0 ?? I:??[OBJECT, ARRAY]) = (r17v0 'this' ?? I:??[OBJECT, ARRAY] A[IMMUTABLE_TYPE, THIS]) A[DECLARE_VAR] in method: androidx.datastore.preferences.protobuf.MessageSchema.mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema<UT, UB>, androidx.datastore.preferences.protobuf.ExtensionSchema<ET extends androidx.datastore.preferences.protobuf.FieldSet$FieldDescriptorLite<ET>>, T, androidx.datastore.preferences.protobuf.Reader, androidx.datastore.preferences.protobuf.ExtensionRegistryLite):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 2000
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, java.lang.Object, androidx.datastore.preferences.protobuf.Reader, androidx.datastore.preferences.protobuf.ExtensionRegistryLite):void");
    }

    private final <K, V> void mergeMap(Object r5, int r6, Object r7, ExtensionRegistryLite r8, Reader r9) {
        long r0 = offset(typeAndOffsetAt(r6));
        Object r62 = UnsafeUtil.getObject(r5, r0);
        MapFieldSchema r2 = this.mapFieldSchema;
        if (r62 != null) goto L6;
        r62 = r2.newMapField(r7);
        UnsafeUtil.putObject(r5, r0, r62);
    L8:
        r9.readMap(this.mapFieldSchema.forMutableMapData(r62), this.mapFieldSchema.forMapMetadata(r7), r8);
        return;
    L6:
        if (r2.isImmutable(r62) == false) goto L8;
        Object r22 = this.mapFieldSchema.newMapField(r7);
        this.mapFieldSchema.mergeFrom(r22, r62);
        UnsafeUtil.putObject(r5, r0, r22);
        r62 = r22;
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T r6, T r7, int r8) {
        if (isFieldPresent(r7, r8) == true) goto L5;
        return;
    L5:
        long r0 = offset(typeAndOffsetAt(r8));
        Unsafe r2 = UNSAFE;
        Object r3 = r2.getObject(r7, r0);
        if (r3 == null) goto L20;
        Schema r72 = getMessageFieldSchema(r8);
        if (isFieldPresent(r6, r8) == false) goto L10;
        Object r5 = r2.getObject(r6, r0);
        if (isMutable(r5) == true) goto L18;
        Object r82 = r72.newInstance();
        r72.mergeFrom(r82, r5);
        r2.putObject(r6, r0, r82);
        r5 = r82;
    L18:
        r72.mergeFrom(r5, r3);
        return;
    L10:
        if (isMutable(r3) == true) goto L12;
        r2.putObject(r6, r0, r3);
    L13:
        setFieldPresent(r6, r8);
        return;
    L12:
        Object r4 = r72.newInstance();
        r72.mergeFrom(r4, r3);
        r2.putObject(r6, r0, r4);
        goto L13
    L20:
        MessageSchema$$ExternalSyntheticBUOutline0.m("Source subfield ", numberAt(r8), " is present but null: ", r7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T r7, T r8, int r9) {
        int r0 = numberAt(r9);
        if (isOneofPresent(r8, r0, r9) == true) goto L5;
        return;
    L5:
        long r1 = offset(typeAndOffsetAt(r9));
        Unsafe r3 = UNSAFE;
        Object r4 = r3.getObject(r8, r1);
        if (r4 == null) goto L20;
        Schema r82 = getMessageFieldSchema(r9);
        if (isOneofPresent(r7, r0, r9) == false) goto L10;
        Object r6 = r3.getObject(r7, r1);
        if (isMutable(r6) == true) goto L18;
        Object r92 = r82.newInstance();
        r82.mergeFrom(r92, r6);
        r3.putObject(r7, r1, r92);
        r6 = r92;
    L18:
        r82.mergeFrom(r6, r4);
        return;
    L10:
        if (isMutable(r4) == true) goto L12;
        r3.putObject(r7, r1, r4);
    L13:
        setOneofPresent(r7, r0, r9);
        return;
    L12:
        Object r5 = r82.newInstance();
        r82.mergeFrom(r5, r4);
        r3.putObject(r7, r1, r5);
        goto L13
    L20:
        MessageSchema$$ExternalSyntheticBUOutline0.m("Source subfield ", numberAt(r9), " is present but null: ", r8);
    }

    private void mergeSingleField(T r6, T r7, int r8) {
        int r0 = typeAndOffsetAt(r8);
        long r1 = offset(r0);
        int r3 = numberAt(r8);
        switch(type(r0)) {
            case 0: goto L86;
            case 1: goto L82;
            case 2: goto L78;
            case 3: goto L74;
            case 4: goto L70;
            case 5: goto L66;
            case 6: goto L62;
            case 7: goto L58;
            case 8: goto L54;
            case 9: goto L51;
            case 10: goto L48;
            case 11: goto L44;
            case 12: goto L40;
            case 13: goto L36;
            case 14: goto L32;
            case 15: goto L28;
            case 16: goto L24;
            case 17: goto L21;
            case 18: goto L19;
            case 19: goto L19;
            case 20: goto L19;
            case 21: goto L19;
            case 22: goto L19;
            case 23: goto L19;
            case 24: goto L19;
            case 25: goto L19;
            case 26: goto L19;
            case 27: goto L19;
            case 28: goto L19;
            case 29: goto L19;
            case 30: goto L19;
            case 31: goto L19;
            case 32: goto L19;
            case 33: goto L19;
            case 34: goto L19;
            case 35: goto L19;
            case 36: goto L19;
            case 37: goto L19;
            case 38: goto L19;
            case 39: goto L19;
            case 40: goto L19;
            case 41: goto L19;
            case 42: goto L19;
            case 43: goto L19;
            case 44: goto L19;
            case 45: goto L19;
            case 46: goto L19;
            case 47: goto L19;
            case 48: goto L19;
            case 49: goto L19;
            case 50: goto L17;
            case 51: goto L14;
            case 52: goto L14;
            case 53: goto L14;
            case 54: goto L14;
            case 55: goto L14;
            case 56: goto L14;
            case 57: goto L14;
            case 58: goto L14;
            case 59: goto L14;
            case 60: goto L11;
            case 61: goto L8;
            case 62: goto L8;
            case 63: goto L8;
            case 64: goto L8;
            case 65: goto L8;
            case 66: goto L8;
            case 67: goto L8;
            case 68: goto L5;
            default: goto L107;
        };
    L5:
        mergeOneofMessage(r6, r7, r8);
        return;
    L11:
        mergeOneofMessage(r6, r7, r8);
        return;
    L17:
        SchemaUtil.mergeMap(this.mapFieldSchema, r6, r7, r1);
        return;
    L19:
        this.listFieldSchema.mergeListsAt(r6, r7, r1);
        return;
    L21:
        mergeMessage(r6, r7, r8);
        return;
    L51:
        mergeMessage(r6, r7, r8);
        return;
    L107:
        return;
    L8:
        if (isOneofPresent(r7, r3, r8) == false) goto L89;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setOneofPresent(r6, r3, r8);
        return;
    L89:
        return;
    L14:
        if (isOneofPresent(r7, r3, r8) == false) goto L90;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setOneofPresent(r6, r3, r8);
        return;
    L90:
        return;
    L24:
        if (isFieldPresent(r7, r8) == false) goto L91;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L91:
        return;
    L28:
        if (isFieldPresent(r7, r8) == false) goto L92;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L92:
        return;
    L32:
        if (isFieldPresent(r7, r8) == false) goto L93;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L93:
        return;
    L36:
        if (isFieldPresent(r7, r8) == false) goto L94;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L94:
        return;
    L40:
        if (isFieldPresent(r7, r8) == false) goto L95;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L95:
        return;
    L44:
        if (isFieldPresent(r7, r8) == false) goto L96;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L96:
        return;
    L48:
        if (isFieldPresent(r7, r8) == false) goto L97;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L97:
        return;
    L54:
        if (isFieldPresent(r7, r8) == false) goto L98;
        UnsafeUtil.putObject(r6, r1, UnsafeUtil.getObject(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L98:
        return;
    L58:
        if (isFieldPresent(r7, r8) == false) goto L99;
        UnsafeUtil.putBoolean(r6, r1, UnsafeUtil.getBoolean(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L99:
        return;
    L62:
        if (isFieldPresent(r7, r8) == false) goto L100;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L100:
        return;
    L66:
        if (isFieldPresent(r7, r8) == false) goto L101;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L101:
        return;
    L70:
        if (isFieldPresent(r7, r8) == false) goto L102;
        UnsafeUtil.putInt(r6, r1, UnsafeUtil.getInt(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L102:
        return;
    L74:
        if (isFieldPresent(r7, r8) == false) goto L103;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L103:
        return;
    L78:
        if (isFieldPresent(r7, r8) == false) goto L104;
        UnsafeUtil.putLong(r6, r1, UnsafeUtil.getLong(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L104:
        return;
    L82:
        if (isFieldPresent(r7, r8) == false) goto L105;
        UnsafeUtil.putFloat(r6, r1, UnsafeUtil.getFloat(r7, r1));
        setFieldPresent(r6, r8);
        return;
    L105:
        return;
    L86:
        if (isFieldPresent(r7, r8) == false) goto L106;
        UnsafeUtil.putDouble(r6, r1, UnsafeUtil.getDouble(r7, r1));
        setFieldPresent(r6, r8);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T r4, int r5) {
        Schema r0 = getMessageFieldSchema(r5);
        long r1 = offset(typeAndOffsetAt(r5));
        if (isFieldPresent(r4, r5) == false) goto L5;
        Object r3 = UNSAFE.getObject(r4, r1);
        if (isMutable(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.newInstance();
        if (r3 == null) goto L12;
        r0.mergeFrom(r42, r3);
    L12:
        return r42;
    L5:
        return r0.newInstance();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T r4, int r5, int r6) {
        Schema r0 = getMessageFieldSchema(r6);
        if (isOneofPresent(r4, r5, r6) == false) goto L5;
        Object r3 = UNSAFE.getObject(r4, offset(typeAndOffsetAt(r6)));
        if (isMutable(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.newInstance();
        if (r3 == null) goto L12;
        r0.mergeFrom(r42, r3);
    L12:
        return r42;
    L5:
        return r0.newInstance();
    }

    public static <T> MessageSchema<T> newSchema(Class<T> r0, MessageInfo r1, NewInstanceSchema r2, ListFieldSchema r3, UnknownFieldSchema<?, ?> r4, ExtensionSchema<?> r5, MapFieldSchema r6) {
        if ((r1 instanceof RawMessageInfo) == true) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        return newSchemaForMessageInfo(null, r2, r3, r4, r5, r6);
    L5:
        return newSchemaForRawMessageInfo((RawMessageInfo) r1, r2, r3, r4, r5, r6);
    }

    public static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo r0, NewInstanceSchema r1, ListFieldSchema r2, UnknownFieldSchema<?, ?> r3, ExtensionSchema<?> r4, MapFieldSchema r5) {
        throw null;
    }

    public static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo r33, NewInstanceSchema r34, ListFieldSchema r35, UnknownFieldSchema<?, ?> r36, ExtensionSchema<?> r37, MapFieldSchema r38) {
        String r0 = r33.getStringInfo();
        int r1 = r0.length();
        char r5 = 55296;
        if (r0.charAt(0) < 55296) goto L8;
        int r3 = 1;
    L5:
        int r6 = r3 + 1;
        if (r0.charAt(r3) < 55296) goto L9;
        r3 = r6;
    L9:
        int r32 = r6 + 1;
        int r62 = r0.charAt(r6);
        if (r62 < 55296) goto L16;
        int r63 = r62 & 8191;
        int r8 = 13;
    L12:
        int r9 = r32 + 1;
        char r39 = r0.charAt(r32);
        if (r39 < 55296) goto L15;
        r63 = r63 | ((r39 & 8191) << r8);
        r8 = r8 + 13;
        r32 = r9;
        goto L12
    L15:
        r62 = r63 | (r39 << r8);
        r32 = r9;
    L16:
        if (r62 != 0) goto L18;
        int r82 = 0;
        int r10 = 0;
        int r11 = 0;
        int r12 = 0;
        int r15 = 0;
        int r17 = 0;
        int[] r16 = EMPTY_INT_ARRAY;
        int r64 = 0;
    L75:
        Unsafe r92 = UNSAFE;
        Object[] r13 = r33.getObjects();
        Class<?> r14 = r33.getDefaultInstance().getClass();
        int[] r2 = new int[r10 * 3];
        Object[] r102 = new Object[r10 * 2];
        int r83 = r17 + r82;
        int r22 = r83;
        int r21 = r17;
        int r19 = 0;
        int r20 = 0;
    L76:
        if (r32 >= r1) goto L201;
        int r23 = r32 + 1;
        int r310 = r0.charAt(r32);
        if (r310 < r5) goto L84;
        int r311 = r310 & 8191;
        int r4 = r23;
        int r232 = 13;
    L80:
        int r25 = r4 + 1;
        char r42 = r0.charAt(r4);
        if (r42 < r5) goto L83;
        r311 = r311 | ((r42 & 8191) << r232);
        r232 = r232 + 13;
        r4 = r25;
        goto L80
    L83:
        r310 = r311 | (r42 << r232);
        int r43 = r25;
    L85:
        int r233 = r43 + 1;
        int r44 = r0.charAt(r43);
        if (r44 < r5) goto L92;
        int r45 = r44 & 8191;
        int r7 = r233;
        int r234 = 13;
    L88:
        int r26 = r7 + 1;
        char r72 = r0.charAt(r7);
        if (r72 < r5) goto L91;
        r45 = r45 | ((r72 & 8191) << r234);
        r234 = r234 + 13;
        r7 = r26;
        goto L88
    L91:
        r44 = r45 | (r72 << r234);
        int r73 = r26;
    L93:
        int r52 = r44 & 255;
        int r262 = r1;
        if ((r44 & 1024) == 0) goto L96;
        r16[r19] = r20;
        r19 = r19 + 1;
    L96:
        int[] r29 = r2;
        if (r52 < 51) goto L128;
        int r18 = r73 + 1;
        int r74 = r0.charAt(r73);
        char r24 = 55296;
        if (r74 < 55296) goto L105;
        int r75 = r74 & 8191;
        int r31 = 13;
    L101:
        int r322 = r18 + 1;
        char r110 = r0.charAt(r18);
        if (r110 < r24) goto L104;
        r75 = r75 | ((r110 & 8191) << r31);
        r31 = r31 + 13;
        r18 = r322;
        r24 = 55296;
        goto L101
    L104:
        r74 = r75 | (r110 << r31);
        r18 = r322;
    L105:
        int r27 = r52 - 51;
        int r312 = r18;
        if (r27 != 9) goto L108;
    L118:
        int r28 = r15 + 1;
        r102[((r20 / 3) * 2) + 1] = r13[r15];
    L117:
        r15 = r28;
    L119:
        int r76 = r74 * 2;
        Object r111 = r13[r76];
        if ((r111 instanceof Field) == false) goto L122;
        Field r112 = (Field) r111;
    L123:
        int r113 = (int) r92.objectFieldOffset(r112);
        int r77 = r76 + 1;
        Object r210 = r13[r77];
        if ((r210 instanceof Field) == false) goto L126;
        Field r211 = (Field) r210;
    L127:
        String r30 = r0;
        int r02 = (int) r92.objectFieldOffset(r211);
        int r114 = r113;
        int r78 = 0;
        int r272 = r310;
        r32 = r312;
    L187:
        int r212 = r20 + 1;
        r29[r20] = r272;
        int r273 = r20 + 2;
        int r282 = r02;
        if ((r44 & 512) == 0) goto L190;
        int r03 = 536870912;
    L191:
        int r313 = r03;
        if ((r44 & 256) == 0) goto L194;
        int r04 = 268435456;
    L195:
        int r05 = r313 | r04;
        if ((r44 & 2048) == 0) goto L198;
        int r46 = Integer.MIN_VALUE;
    L199:
        r29[r212] = ((r05 | r46) | (r52 << 20)) | r114;
        r20 = r20 + 3;
        r29[r273] = (r78 << 20) | r282;
        r1 = r262;
        r2 = r29;
        r0 = r30;
        r5 = 55296;
        goto L76
    L198:
        r46 = 0;
        goto L199
    L194:
        r04 = 0;
        goto L195
    L190:
        r03 = 0;
        goto L191
    L126:
        r211 = reflectField(r14, (String) r210);
        r13[r77] = r211;
        goto L127
    L122:
        r112 = reflectField(r14, (String) r111);
        r13[r76] = r112;
        goto L123
    L108:
        if (r27 == 17) goto L118;
        if (r27 != 12) goto L119;
        if (r33.getSyntax().equals(ProtoSyntax.PROTO2) == false) goto L115;
    L116:
        r28 = r15 + 1;
        r102[((r20 / 3) * 2) + 1] = r13[r15];
        goto L117
    L115:
        if ((r44 & 2048) == 0) goto L119;
    L128:
        int r115 = r15 + 1;
        Field r213 = reflectField(r14, (String) r13[r15]);
        if (r52 != 9) goto L131;
    L132:
        r272 = r310;
        r102[((r20 / 3) * 2) + 1] = r213.getType();
    L160:
        int r116 = r115;
    L161:
        int r214 = (int) r92.objectFieldOffset(r213);
        if ((r44 & 4096) != 0) goto L164;
    L180:
        r30 = r0;
        int r314 = r116;
        int r06 = 1048575;
        int r283 = r73;
        r78 = 0;
    L179:
        int r117 = r06;
        if (r52 >= 18) goto L184;
    L186:
        r02 = r117;
        r114 = r214;
        r15 = r314;
        r32 = r283;
        goto L187
    L184:
        if (r52 > 49) goto L186;
        r16[r22] = r214;
        r22 = r22 + 1;
        goto L186
    L164:
        if (r52 > 17) goto L180;
        int r315 = r73 + 1;
        int r79 = r0.charAt(r73);
        if (r79 < 55296) goto L172;
        int r710 = r79 & 8191;
        int r235 = 13;
    L168:
        r283 = r315 + 1;
        char r316 = r0.charAt(r315);
        if (r316 < 55296) goto L171;
        r710 = r710 | ((r316 & 8191) << r235);
        r235 = r235 + 13;
        r315 = r283;
        goto L168
    L171:
        r79 = r710 | (r316 << r235);
    L173:
        int r317 = (r64 * 2) + (r79 / 32);
        Object r152 = r13[r317];
        r30 = r0;
        if ((r152 instanceof Field) == false) goto L177;
        Field r153 = (Field) r152;
    L176:
        r314 = r116;
        r06 = (int) r92.objectFieldOffset(r153);
        r78 = r79 % 32;
        goto L179
    L177:
        r153 = reflectField(r14, (String) r152);
        r13[r317] = r153;
        goto L176
    L172:
        r283 = r315;
        goto L173
    L131:
        if (r52 == 17) goto L132;
        if (r52 != 27) goto L136;
    L137:
        r272 = r310;
        int r154 = r15 + 2;
        r102[((r20 / 3) * 2) + 1] = r13[r115];
    L157:
        r116 = r154;
        goto L161
    L136:
        if (r52 == 49) goto L137;
        if (r52 != 12) goto L141;
    L152:
        r272 = r310;
        if (r33.getSyntax() != ProtoSyntax.PROTO2) goto L155;
    L156:
        r154 = r15 + 2;
        r102[((r20 / 3) * 2) + 1] = r13[r115];
        goto L157
    L155:
        if ((r44 & 2048) == 0) goto L160;
    L141:
        if (r52 == 30) goto L152;
        if (r52 == 44) goto L152;
        if (r52 != 50) goto L151;
        int r118 = r21 + 1;
        r16[r21] = r20;
        int r215 = (r20 / 3) * 2;
        int r274 = r15 + 2;
        r102[r215] = r13[r115];
        if ((r44 & 2048) == 0) goto L150;
        r116 = r15 + 3;
        r102[r215 + 1] = r13[r274];
        r272 = r310;
        r21 = r118;
        goto L161
    L150:
        r116 = r274;
        r21 = r118;
        r272 = r310;
        goto L161
    L151:
        r272 = r310;
        goto L160
    L92:
        r73 = r233;
        goto L93
    L84:
        r43 = r23;
        goto L85
    L201:
        return new MessageSchema(r2, r102, r11, r12, r33.getDefaultInstance(), r33.getSyntax(), false, r16, r17, r83, r34, r35, r36, r37, r38);
    L18:
        int r65 = r32 + 1;
        int r318 = r0.charAt(r32);
        if (r318 < 55296) goto L25;
        int r319 = r318 & 8191;
        int r84 = 13;
    L21:
        int r93 = r65 + 1;
        char r66 = r0.charAt(r65);
        if (r66 < 55296) goto L24;
        r319 = r319 | ((r66 & 8191) << r84);
        r84 = r84 + 13;
        r65 = r93;
        goto L21
    L24:
        r318 = r319 | (r66 << r84);
        r65 = r93;
    L25:
        int r85 = r65 + 1;
        int r67 = r0.charAt(r65);
        if (r67 < 55296) goto L32;
        int r68 = r67 & 8191;
        int r94 = 13;
    L28:
        int r103 = r85 + 1;
        char r86 = r0.charAt(r85);
        if (r86 < 55296) goto L31;
        r68 = r68 | ((r86 & 8191) << r94);
        r94 = r94 + 13;
        r85 = r103;
        goto L28
    L31:
        r67 = r68 | (r86 << r94);
        r85 = r103;
    L32:
        int r95 = r85 + 1;
        int r87 = r0.charAt(r85);
        if (r87 < 55296) goto L39;
        int r88 = r87 & 8191;
        int r104 = 13;
    L35:
        int r119 = r95 + 1;
        char r96 = r0.charAt(r95);
        if (r96 < 55296) goto L38;
        r88 = r88 | ((r96 & 8191) << r104);
        r104 = r104 + 13;
        r95 = r119;
        goto L35
    L38:
        r87 = r88 | (r96 << r104);
        r95 = r119;
    L39:
        int r105 = r95 + 1;
        int r97 = r0.charAt(r95);
        if (r97 < 55296) goto L46;
        int r98 = r97 & 8191;
        int r1110 = 13;
    L42:
        int r122 = r105 + 1;
        char r106 = r0.charAt(r105);
        if (r106 < 55296) goto L45;
        r98 = r98 | ((r106 & 8191) << r1110);
        r1110 = r1110 + 13;
        r105 = r122;
        goto L42
    L45:
        r97 = r98 | (r106 << r1110);
        r105 = r122;
    L46:
        int r1111 = r105 + 1;
        r10 = r0.charAt(r105);
        if (r10 < 55296) goto L53;
        int r107 = r10 & 8191;
        int r123 = 13;
    L49:
        int r132 = r1111 + 1;
        char r1112 = r0.charAt(r1111);
        if (r1112 < 55296) goto L52;
        r107 = r107 | ((r1112 & 8191) << r123);
        r123 = r123 + 13;
        r1111 = r132;
        goto L49
    L52:
        r10 = r107 | (r1112 << r123);
        r1111 = r132;
    L53:
        int r124 = r1111 + 1;
        int r1113 = r0.charAt(r1111);
        if (r1113 < 55296) goto L60;
        int r1114 = r1113 & 8191;
        int r133 = 13;
    L56:
        int r142 = r124 + 1;
        char r125 = r0.charAt(r124);
        if (r125 < 55296) goto L59;
        r1114 = r1114 | ((r125 & 8191) << r133);
        r133 = r133 + 13;
        r124 = r142;
        goto L56
    L59:
        r1113 = r1114 | (r125 << r133);
        r124 = r142;
    L60:
        int r134 = r124 + 1;
        int r126 = r0.charAt(r124);
        if (r126 < 55296) goto L67;
        int r127 = r126 & 8191;
        int r143 = 13;
    L63:
        int r155 = r134 + 1;
        char r135 = r0.charAt(r134);
        if (r135 < 55296) goto L66;
        r127 = r127 | ((r135 & 8191) << r143);
        r143 = r143 + 13;
        r134 = r155;
        goto L63
    L66:
        r126 = r127 | (r135 << r143);
        r134 = r155;
    L67:
        int r144 = r134 + 1;
        int r136 = r0.charAt(r134);
        if (r136 < 55296) goto L74;
        int r137 = r136 & 8191;
        int r156 = 13;
    L70:
        int r162 = r144 + 1;
        char r145 = r0.charAt(r144);
        if (r145 < 55296) goto L73;
        r137 = r137 | ((r145 & 8191) << r156);
        r156 = r156 + 13;
        r144 = r162;
        goto L70
    L73:
        r136 = r137 | (r145 << r156);
        r144 = r162;
    L74:
        int[] r128 = new int[(r136 + r1113) + r126];
        r15 = (r318 * 2) + r67;
        int r69 = r1113;
        r11 = r87;
        r82 = r69;
        r64 = r318;
        r16 = r128;
        r17 = r136;
        r32 = r144;
        r12 = r97;
        goto L75
    L8:
        r6 = 1;
        goto L9
    }

    private int numberAt(int r1) {
        return this.buffer[r1];
    }

    private static long offset(int r2) {
        return r2 & 1048575;
    }

    private static <T> boolean oneofBooleanAt(T r0, long r1) {
        return ((Boolean) UnsafeUtil.getObject(r0, r1)).booleanValue();
    }

    private static <T> double oneofDoubleAt(T r0, long r1) {
        return ((Double) UnsafeUtil.getObject(r0, r1)).doubleValue();
    }

    private static <T> float oneofFloatAt(T r0, long r1) {
        return ((Float) UnsafeUtil.getObject(r0, r1)).floatValue();
    }

    private static <T> int oneofIntAt(T r0, long r1) {
        return ((Integer) UnsafeUtil.getObject(r0, r1)).intValue();
    }

    private static <T> long oneofLongAt(T r0, long r1) {
        return ((Long) UnsafeUtil.getObject(r0, r1)).longValue();
    }

    private int positionForFieldNumber(int r2) {
        if (r2 >= this.minFieldNumber) goto L5;
        return -1;
    L5:
        if (r2 <= this.maxFieldNumber) goto L7;
        return -1;
    L7:
        return slowPositionForFieldNumber(r2, 0);
    }

    private int presenceMaskAndOffsetAt(int r1) {
        return this.buffer[r1 + 2];
    }

    private <E> void readGroupList(Object r1, long r2, Reader r4, Schema<E> r5, ExtensionRegistryLite r6) {
        r4.readGroupList(this.listFieldSchema.mutableListAt(r1, r2), r5, r6);
    }

    private <E> void readMessageList(Object r3, int r4, Reader r5, Schema<E> r6, ExtensionRegistryLite r7) {
        long r0 = offset(r4);
        r5.readMessageList(this.listFieldSchema.mutableListAt(r3, r0), r6, r7);
    }

    private void readString(Object r3, int r4, Reader r5) {
        if (isEnforceUtf8(r4) == false) goto L7;
        UnsafeUtil.putObject(r3, offset(r4), r5.readStringRequireUtf8());
        return;
    L7:
        if (this.lite == false) goto L10;
        UnsafeUtil.putObject(r3, offset(r4), r5.readString());
        return;
    L10:
        UnsafeUtil.putObject(r3, offset(r4), r5.readBytes());
    }

    private void readStringList(Object r3, int r4, Reader r5) {
        boolean r0 = isEnforceUtf8(r4);
        ListFieldSchema r2 = this.listFieldSchema;
        if (r0 == false) goto L6;
        r5.readStringListRequireUtf8(r2.mutableListAt(r3, offset(r4)));
        return;
    L6:
        r5.readStringList(r2.mutableListAt(r3, offset(r4)));
    }

    private static Field reflectField(Class<?> r9, String r10) {
        return r9.getDeclaredField(r10);
    L4:
        Field[] r0 = r9.getDeclaredFields();
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        Field r3 = r0[r2];
        if (r10.equals(r3.getName()) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        return r3;
    L10:
        LocalList$$ExternalSyntheticBUOutline0.m("Field ", r10, " for ", r9.getName(), " not found. Known fields are ", Arrays.toString(r0));
        return null;
    }

    private void setFieldPresent(T r5, int r6) {
        int r4 = presenceMaskAndOffsetAt(r6);
        long r0 = 1048575 & r4;
        if (r0 != 1048575) goto L5;
        return;
    L5:
        UnsafeUtil.putInt(r5, r0, (1 << (r4 >>> 20)) | UnsafeUtil.getInt(r5, r0));
    }

    private void setOneofPresent(T r3, int r4, int r5) {
        UnsafeUtil.putInt(r3, presenceMaskAndOffsetAt(r5) & 1048575, r4);
    }

    private int slowPositionForFieldNumber(int r5, int r6) {
        int r0 = (this.buffer.length / 3) - 1;
    L3:
        if (r6 > r0) goto L10;
        int r1 = (r0 + r6) >>> 1;
        int r2 = r1 * 3;
        int r3 = numberAt(r2);
        if (r5 == r3) goto L6;
        if (r5 < r3) goto L8;
        r6 = r1 + 1;
        goto L3
    L8:
        r0 = r1 - 1;
        goto L3
    L6:
        return r2;
    L10:
        return -1;
    }

    private void storeMessageField(T r4, int r5, Object r6) {
        UNSAFE.putObject(r4, offset(typeAndOffsetAt(r5)), r6);
        setFieldPresent(r4, r5);
    }

    private void storeOneofMessageField(T r4, int r5, int r6, Object r7) {
        UNSAFE.putObject(r4, offset(typeAndOffsetAt(r6)), r7);
        setOneofPresent(r4, r5, r6);
    }

    private static int type(int r1) {
        return (r1 & 267386880) >>> 20;
    }

    private int typeAndOffsetAt(int r1) {
        return this.buffer[r1 + 1];
    }

    private void writeFieldsInAscendingOrder(T r19, Writer r20) {
        MessageSchema<T> r0 = this;
        if (r0.hasExtensions == false) goto L7;
        FieldSet<T> r2 = r0.extensionSchema.getExtensions(r19);
        if (r2.isEmpty() == true) goto L7;
        Iterator r22 = r2.iterator();
        Map.Entry<?, ?> r3 = (Map.Entry) r22.next();
        Iterator r8 = r22;
    L8:
        int r9 = r0.buffer.length;
        Unsafe r10 = UNSAFE;
        int r4 = 1048575;
        int r23 = 0;
        int r5 = 0;
    L9:
        if (r23 >= r9) goto L174;
        int r13 = r0.typeAndOffsetAt(r23);
        int r14 = r0.numberAt(r23);
        int r15 = type(r13);
        if (r15 > 17) goto L19;
        int r7 = r0.buffer[r23 + 2];
        boolean r16 = true;
        int r12 = r7 & 1048575;
        if (r12 == r4) goto L18;
        if (r12 != 1048575) goto L16;
        r5 = 0;
    L17:
        r4 = r12;
        goto L18
    L16:
        r5 = r10.getInt(r19, r12);
    L18:
        int r72 = 1 << (r7 >>> 20);
        Map.Entry<?, ?> r73 = r3;
        int r32 = r4;
        int r42 = r5;
        int r52 = r72;
    L20:
        if (r73 == null) goto L27;
        if (r0.extensionSchema.extensionNumber(r73) > r14) goto L27;
        r0.extensionSchema.serializeExtension(r20, r73);
        if (r8.hasNext() == true) goto L25;
        r73 = null;
        goto L20
    L25:
        r73 = (Map.Entry) r8.next();
    L27:
        long r122 = offset(r13);
        switch(r15) {
            case 0: goto L171;
            case 1: goto L168;
            case 2: goto L165;
            case 3: goto L162;
            case 4: goto L159;
            case 5: goto L156;
            case 6: goto L153;
            case 7: goto L150;
            case 8: goto L147;
            case 9: goto L144;
            case 10: goto L141;
            case 11: goto L138;
            case 12: goto L135;
            case 13: goto L132;
            case 14: goto L129;
            case 15: goto L126;
            case 16: goto L122;
            case 17: goto L119;
            case 18: goto L117;
            case 19: goto L116;
            case 20: goto L115;
            case 21: goto L114;
            case 22: goto L113;
            case 23: goto L112;
            case 24: goto L111;
            case 25: goto L110;
            case 26: goto L109;
            case 27: goto L108;
            case 28: goto L107;
            case 29: goto L106;
            case 30: goto L105;
            case 31: goto L104;
            case 32: goto L103;
            case 33: goto L102;
            case 34: goto L100;
            case 35: goto L99;
            case 36: goto L98;
            case 37: goto L97;
            case 38: goto L96;
            case 39: goto L95;
            case 40: goto L94;
            case 41: goto L93;
            case 42: goto L92;
            case 43: goto L91;
            case 44: goto L90;
            case 45: goto L89;
            case 46: goto L88;
            case 47: goto L87;
            case 48: goto L86;
            case 49: goto L85;
            case 50: goto L84;
            case 51: goto L82;
            case 52: goto L79;
            case 53: goto L76;
            case 54: goto L73;
            case 55: goto L70;
            case 56: goto L67;
            case 57: goto L64;
            case 58: goto L61;
            case 59: goto L58;
            case 60: goto L55;
            case 61: goto L52;
            case 62: goto L49;
            case 63: goto L46;
            case 64: goto L43;
            case 65: goto L40;
            case 66: goto L37;
            case 67: goto L34;
            case 68: goto L31;
            default: goto L173;
        };
    L84:
        r0.writeMapHelper(r20, r14, r10.getObject(r19, r122), r23);
        goto L173
    L85:
        SchemaUtil.writeGroupList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r0.getMessageFieldSchema(r23));
        goto L173
    L86:
        SchemaUtil.writeSInt64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L87:
        SchemaUtil.writeSInt32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L88:
        SchemaUtil.writeSFixed64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L89:
        SchemaUtil.writeSFixed32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L90:
        SchemaUtil.writeEnumList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L91:
        SchemaUtil.writeUInt32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L92:
        SchemaUtil.writeBoolList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L93:
        SchemaUtil.writeFixed32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L94:
        SchemaUtil.writeFixed64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L95:
        SchemaUtil.writeInt32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L96:
        SchemaUtil.writeUInt64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L97:
        SchemaUtil.writeInt64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L98:
        SchemaUtil.writeFloatList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L99:
        SchemaUtil.writeDoubleList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r16);
        goto L173
    L100:
        boolean r142 = false;
        SchemaUtil.writeSInt64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L102:
        r142 = false;
        SchemaUtil.writeSInt32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L103:
        r142 = false;
        SchemaUtil.writeSFixed64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L104:
        r142 = false;
        SchemaUtil.writeSFixed32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L105:
        r142 = false;
        SchemaUtil.writeEnumList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L106:
        r142 = false;
        SchemaUtil.writeUInt32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L107:
        SchemaUtil.writeBytesList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20);
        goto L173
    L108:
        SchemaUtil.writeMessageList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, r0.getMessageFieldSchema(r23));
        goto L173
    L109:
        SchemaUtil.writeStringList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20);
        goto L173
    L110:
        SchemaUtil.writeBoolList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L111:
        SchemaUtil.writeFixed32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L112:
        SchemaUtil.writeFixed64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L113:
        SchemaUtil.writeInt32List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L114:
        SchemaUtil.writeUInt64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L115:
        SchemaUtil.writeInt64List(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L116:
        SchemaUtil.writeFloatList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
        goto L173
    L117:
        SchemaUtil.writeDoubleList(r0.numberAt(r23), (List) r10.getObject(r19, r122), r20, false);
    L173:
        r23 = r23 + 3;
        r5 = r42;
        r4 = r32;
        r3 = r73;
        goto L9
    L31:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeGroup(r14, r10.getObject(r19, r122), r0.getMessageFieldSchema(r23));
        goto L173
    L34:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeSInt64(r14, oneofLongAt(r19, r122));
        goto L173
    L37:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeSInt32(r14, oneofIntAt(r19, r122));
        goto L173
    L40:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeSFixed64(r14, oneofLongAt(r19, r122));
        goto L173
    L43:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeSFixed32(r14, oneofIntAt(r19, r122));
        goto L173
    L46:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeEnum(r14, oneofIntAt(r19, r122));
        goto L173
    L49:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeUInt32(r14, oneofIntAt(r19, r122));
        goto L173
    L52:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeBytes(r14, (ByteString) r10.getObject(r19, r122));
        goto L173
    L55:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeMessage(r14, r10.getObject(r19, r122), r0.getMessageFieldSchema(r23));
        goto L173
    L58:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r0.writeString(r14, r10.getObject(r19, r122), r20);
        goto L173
    L61:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeBool(r14, oneofBooleanAt(r19, r122));
        goto L173
    L64:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeFixed32(r14, oneofIntAt(r19, r122));
        goto L173
    L67:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeFixed64(r14, oneofLongAt(r19, r122));
        goto L173
    L70:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeInt32(r14, oneofIntAt(r19, r122));
        goto L173
    L73:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeUInt64(r14, oneofLongAt(r19, r122));
        goto L173
    L76:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeInt64(r14, oneofLongAt(r19, r122));
        goto L173
    L79:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeFloat(r14, oneofFloatAt(r19, r122));
        goto L173
    L82:
        if (r0.isOneofPresent(r19, r14, r23) == false) goto L173;
        r20.writeDouble(r14, oneofDoubleAt(r19, r122));
        goto L173
    L119:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L173;
        r20.writeGroup(r14, r10.getObject(r19, r122), r0.getMessageFieldSchema(r23));
        goto L173
    L122:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeSInt64(r14, r10.getLong(r19, r122));
    L124:
        r0 = this;
        goto L173
    L126:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeSInt32(r14, r10.getInt(r19, r122));
        goto L124
    L129:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeSFixed64(r14, r10.getLong(r19, r122));
        goto L124
    L132:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeSFixed32(r14, r10.getInt(r19, r122));
        goto L124
    L135:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeEnum(r14, r10.getInt(r19, r122));
        goto L124
    L138:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeUInt32(r14, r10.getInt(r19, r122));
        goto L124
    L141:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeBytes(r14, (ByteString) r10.getObject(r19, r122));
        goto L124
    L144:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L173;
        r20.writeMessage(r14, r10.getObject(r19, r122), r0.getMessageFieldSchema(r23));
        goto L173
    L147:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L173;
        r0.writeString(r14, r10.getObject(r19, r122), r20);
        goto L173
    L150:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeBool(r14, booleanAt(r19, r122));
        goto L124
    L153:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeFixed32(r14, r10.getInt(r19, r122));
        goto L124
    L156:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeFixed64(r14, r10.getLong(r19, r122));
        goto L124
    L159:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeInt32(r14, r10.getInt(r19, r122));
        goto L124
    L162:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeUInt64(r14, r10.getLong(r19, r122));
        goto L124
    L165:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeInt64(r14, r10.getLong(r19, r122));
        goto L124
    L168:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L124;
        r20.writeFloat(r14, floatAt(r19, r122));
        goto L124
    L171:
        if (r0.isFieldPresent(r19, r23, r32, r42, r52) == false) goto L173;
        r20.writeDouble(r14, doubleAt(r19, r122));
        goto L173
    L19:
        r16 = true;
        r73 = r3;
        r32 = r4;
        r42 = r5;
        r52 = 0;
    L174:
        if (r3 == null) goto L179;
        r0.extensionSchema.serializeExtension(r20, r3);
        if (r8.hasNext() == true) goto L177;
        r3 = null;
        goto L174
    L177:
        r3 = (Map.Entry) r8.next();
        goto L174
    L179:
        r0.writeUnknownInMessageTo(r0.unknownFieldSchema, r19, r20);
        return;
    L7:
        r3 = null;
        r8 = null;
        goto L8
    }

    private void writeFieldsInDescendingOrder(T r11, Writer r12) {
        writeUnknownInMessageTo(this.unknownFieldSchema, r11, r12);
        if (this.hasExtensions == false) goto L7;
        FieldSet<T> r0 = this.extensionSchema.getExtensions(r11);
        if (r0.isEmpty() == true) goto L7;
        Iterator r02 = r0.descendingIterator();
        Map.Entry<?, ?> r2 = (Map.Entry) r02.next();
    L8:
        int r3 = this.buffer.length - 3;
    L9:
        if (r3 < 0) goto L163;
        int r4 = typeAndOffsetAt(r3);
        int r5 = numberAt(r3);
    L11:
        if (r2 == null) goto L19;
        if (this.extensionSchema.extensionNumber(r2) <= r5) goto L19;
        this.extensionSchema.serializeExtension(r12, r2);
        if (r02.hasNext() == true) goto L16;
        r2 = null;
        goto L11
    L16:
        r2 = (Map.Entry) r02.next();
    L19:
        switch(type(r4)) {
            case 0: goto L160;
            case 1: goto L157;
            case 2: goto L154;
            case 3: goto L151;
            case 4: goto L148;
            case 5: goto L145;
            case 6: goto L142;
            case 7: goto L139;
            case 8: goto L136;
            case 9: goto L133;
            case 10: goto L130;
            case 11: goto L127;
            case 12: goto L124;
            case 13: goto L121;
            case 14: goto L118;
            case 15: goto L115;
            case 16: goto L112;
            case 17: goto L109;
            case 18: goto L107;
            case 19: goto L106;
            case 20: goto L105;
            case 21: goto L104;
            case 22: goto L103;
            case 23: goto L102;
            case 24: goto L101;
            case 25: goto L100;
            case 26: goto L99;
            case 27: goto L98;
            case 28: goto L97;
            case 29: goto L96;
            case 30: goto L95;
            case 31: goto L94;
            case 32: goto L93;
            case 33: goto L92;
            case 34: goto L91;
            case 35: goto L90;
            case 36: goto L89;
            case 37: goto L88;
            case 38: goto L87;
            case 39: goto L86;
            case 40: goto L85;
            case 41: goto L84;
            case 42: goto L83;
            case 43: goto L82;
            case 44: goto L81;
            case 45: goto L80;
            case 46: goto L79;
            case 47: goto L78;
            case 48: goto L77;
            case 49: goto L76;
            case 50: goto L75;
            case 51: goto L73;
            case 52: goto L70;
            case 53: goto L67;
            case 54: goto L64;
            case 55: goto L61;
            case 56: goto L58;
            case 57: goto L55;
            case 58: goto L52;
            case 59: goto L49;
            case 60: goto L46;
            case 61: goto L43;
            case 62: goto L40;
            case 63: goto L37;
            case 64: goto L34;
            case 65: goto L31;
            case 66: goto L28;
            case 67: goto L25;
            case 68: goto L22;
            default: goto L162;
        };
    L75:
        writeMapHelper(r12, r5, UnsafeUtil.getObject(r11, offset(r4)), r3);
        goto L162
    L76:
        SchemaUtil.writeGroupList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, getMessageFieldSchema(r3));
        goto L162
    L77:
        SchemaUtil.writeSInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L78:
        SchemaUtil.writeSInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L79:
        SchemaUtil.writeSFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L80:
        SchemaUtil.writeSFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L81:
        SchemaUtil.writeEnumList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L82:
        SchemaUtil.writeUInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L83:
        SchemaUtil.writeBoolList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L84:
        SchemaUtil.writeFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L85:
        SchemaUtil.writeFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L86:
        SchemaUtil.writeInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L87:
        SchemaUtil.writeUInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L88:
        SchemaUtil.writeInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L89:
        SchemaUtil.writeFloatList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L90:
        SchemaUtil.writeDoubleList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, true);
        goto L162
    L91:
        SchemaUtil.writeSInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L92:
        SchemaUtil.writeSInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L93:
        SchemaUtil.writeSFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L94:
        SchemaUtil.writeSFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L95:
        SchemaUtil.writeEnumList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L96:
        SchemaUtil.writeUInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L97:
        SchemaUtil.writeBytesList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L98:
        SchemaUtil.writeMessageList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, getMessageFieldSchema(r3));
        goto L162
    L99:
        SchemaUtil.writeStringList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L100:
        SchemaUtil.writeBoolList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L101:
        SchemaUtil.writeFixed32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L102:
        SchemaUtil.writeFixed64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L103:
        SchemaUtil.writeInt32List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L104:
        SchemaUtil.writeUInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L105:
        SchemaUtil.writeInt64List(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L106:
        SchemaUtil.writeFloatList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L107:
        SchemaUtil.writeDoubleList(numberAt(r3), (List) UnsafeUtil.getObject(r11, offset(r4)), r12, false);
        goto L162
    L22:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeGroup(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L25:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSInt64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L28:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSInt32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L31:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSFixed64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L34:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeSFixed32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L37:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeEnum(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L40:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeUInt32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L43:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeBytes(r5, (ByteString) UnsafeUtil.getObject(r11, offset(r4)));
        goto L162
    L46:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeMessage(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L49:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        writeString(r5, UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L52:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeBool(r5, oneofBooleanAt(r11, offset(r4)));
        goto L162
    L55:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeFixed32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L58:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeFixed64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L61:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeInt32(r5, oneofIntAt(r11, offset(r4)));
        goto L162
    L64:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeUInt64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L67:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeInt64(r5, oneofLongAt(r11, offset(r4)));
        goto L162
    L70:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeFloat(r5, oneofFloatAt(r11, offset(r4)));
        goto L162
    L73:
        if (isOneofPresent(r11, r5, r3) == false) goto L162;
        r12.writeDouble(r5, oneofDoubleAt(r11, offset(r4)));
        goto L162
    L109:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeGroup(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L112:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSInt64(r5, longAt(r11, offset(r4)));
        goto L162
    L115:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSInt32(r5, intAt(r11, offset(r4)));
        goto L162
    L118:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSFixed64(r5, longAt(r11, offset(r4)));
        goto L162
    L121:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeSFixed32(r5, intAt(r11, offset(r4)));
        goto L162
    L124:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeEnum(r5, intAt(r11, offset(r4)));
        goto L162
    L127:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeUInt32(r5, intAt(r11, offset(r4)));
        goto L162
    L130:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeBytes(r5, (ByteString) UnsafeUtil.getObject(r11, offset(r4)));
        goto L162
    L133:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeMessage(r5, UnsafeUtil.getObject(r11, offset(r4)), getMessageFieldSchema(r3));
        goto L162
    L136:
        if (isFieldPresent(r11, r3) == false) goto L162;
        writeString(r5, UnsafeUtil.getObject(r11, offset(r4)), r12);
        goto L162
    L139:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeBool(r5, booleanAt(r11, offset(r4)));
        goto L162
    L142:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeFixed32(r5, intAt(r11, offset(r4)));
        goto L162
    L145:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeFixed64(r5, longAt(r11, offset(r4)));
        goto L162
    L148:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeInt32(r5, intAt(r11, offset(r4)));
        goto L162
    L151:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeUInt64(r5, longAt(r11, offset(r4)));
        goto L162
    L154:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeInt64(r5, longAt(r11, offset(r4)));
        goto L162
    L157:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeFloat(r5, floatAt(r11, offset(r4)));
        goto L162
    L160:
        if (isFieldPresent(r11, r3) == false) goto L162;
        r12.writeDouble(r5, doubleAt(r11, offset(r4)));
    L162:
        r3 = r3 - 3;
    L163:
        if (r2 == null) goto L168;
        this.extensionSchema.serializeExtension(r12, r2);
        if (r02.hasNext() == true) goto L166;
        r2 = null;
        goto L163
    L166:
        r2 = (Map.Entry) r02.next();
        goto L163
    L168:
        return;
    L7:
        r02 = null;
        r2 = null;
        goto L8
    }

    private <K, V> void writeMapHelper(Writer r2, int r3, Object r4, int r5) {
        if (r4 == null) goto L5;
        r2.writeMap(r3, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(r5)), this.mapFieldSchema.forMapData(r4));
        return;
    }

    private void writeString(int r1, Object r2, Writer r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.writeString(r1, (String) r2);
        return;
    L6:
        r3.writeBytes(r1, (ByteString) r2);
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> r1, T r2, Writer r3) {
        r1.writeTo(r1.getFromMessage(r2), r3);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public boolean equals(T r5, T r6) {
        int r0 = this.buffer.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (equals(r5, r6, r2) == false) goto L6;
        r2 = r2 + 3;
        goto L3
    L6:
        return false;
    L9:
        if (this.unknownFieldSchema.getFromMessage(r5).equals(this.unknownFieldSchema.getFromMessage(r6)) == true) goto L12;
        return false;
    L12:
        if (this.hasExtensions == true) goto L14;
        return true;
    L14:
        return this.extensionSchema.getExtensions(r5).equals(this.extensionSchema.getExtensions(r6));
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int getSerializedSize(T r18) {
        MessageSchema<T> r0 = this;
        T r1 = r18;
        Unsafe r6 = UNSAFE;
        int r8 = 1048575;
        int r2 = 0;
        int r4 = 0;
        int r9 = 0;
        int r3 = 1048575;
    L4:
        if (r2 >= r0.buffer.length) goto L249;
        int r5 = r0.typeAndOffsetAt(r2);
        int r10 = type(r5);
        int r11 = r0.numberAt(r2);
        int r12 = r0.buffer[r2 + 2];
        int r13 = r12 & r8;
        if (r10 > 17) goto L14;
        if (r13 == r3) goto L12;
        if (r13 != r8) goto L10;
        r4 = 0;
    L11:
        r3 = r13;
        goto L12
    L10:
        r4 = r6.getInt(r1, r13);
    L12:
        int r122 = 1 << (r12 >>> 20);
    L13:
        int r16 = r9;
        long r82 = offset(r5);
        if (r10 >= FieldType.DOUBLE_LIST_PACKED.id()) goto L18;
    L20:
        r13 = 0;
    L22:
        switch(r10) {
            case 0: goto L245;
            case 1: goto L242;
            case 2: goto L239;
            case 3: goto L236;
            case 4: goto L233;
            case 5: goto L230;
            case 6: goto L227;
            case 7: goto L224;
            case 8: goto L218;
            case 9: goto L215;
            case 10: goto L212;
            case 11: goto L209;
            case 12: goto L206;
            case 13: goto L203;
            case 14: goto L198;
            case 15: goto L195;
            case 16: goto L190;
            case 17: goto L187;
            case 18: goto L185;
            case 19: goto L184;
            case 20: goto L183;
            case 21: goto L182;
            case 22: goto L181;
            case 23: goto L180;
            case 24: goto L179;
            case 25: goto L178;
            case 26: goto L177;
            case 27: goto L176;
            case 28: goto L175;
            case 29: goto L174;
            case 30: goto L173;
            case 31: goto L172;
            case 32: goto L171;
            case 33: goto L170;
            case 34: goto L169;
            case 35: goto L163;
            case 36: goto L157;
            case 37: goto L151;
            case 38: goto L145;
            case 39: goto L139;
            case 40: goto L133;
            case 41: goto L127;
            case 42: goto L121;
            case 43: goto L115;
            case 44: goto L109;
            case 45: goto L103;
            case 46: goto L97;
            case 47: goto L91;
            case 48: goto L84;
            case 49: goto L83;
            case 50: goto L82;
            case 51: goto L80;
            case 52: goto L77;
            case 53: goto L74;
            case 54: goto L71;
            case 55: goto L68;
            case 56: goto L65;
            case 57: goto L62;
            case 58: goto L59;
            case 59: goto L53;
            case 60: goto L50;
            case 61: goto L47;
            case 62: goto L44;
            case 63: goto L41;
            case 64: goto L38;
            case 65: goto L35;
            case 66: goto L32;
            case 67: goto L29;
            case 68: goto L25;
            default: goto L247;
        };
    L82:
        int r52 = r0.mapFieldSchema.getSerializedSize(r11, r6.getObject(r1, r82), r0.getMapFieldDefaultEntry(r2));
    L27:
        r9 = r16 + r52;
    L248:
        r2 = r2 + 3;
        r8 = 1048575;
        goto L4
    L83:
        r52 = SchemaUtil.computeSizeGroupList(r11, (List) r6.getObject(r1, r82), r0.getMessageFieldSchema(r2));
        goto L27
    L84:
        int r53 = SchemaUtil.computeSizeSInt64ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L89;
        r6.putInt(r1, r13, r53);
    L89:
        int r83 = CodedOutputStream.computeTagSize(r11);
        int r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
    L90:
        r9 = r16 + ((r83 + r92) + r53);
        goto L248
    L91:
        r53 = SchemaUtil.computeSizeSInt32ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L96;
        r6.putInt(r1, r13, r53);
    L96:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L97:
        r53 = SchemaUtil.computeSizeFixed64ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L102;
        r6.putInt(r1, r13, r53);
    L102:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L103:
        r53 = SchemaUtil.computeSizeFixed32ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L108;
        r6.putInt(r1, r13, r53);
    L108:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L109:
        r53 = SchemaUtil.computeSizeEnumListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L114;
        r6.putInt(r1, r13, r53);
    L114:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L115:
        r53 = SchemaUtil.computeSizeUInt32ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L120;
        r6.putInt(r1, r13, r53);
    L120:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L121:
        r53 = SchemaUtil.computeSizeBoolListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L126;
        r6.putInt(r1, r13, r53);
    L126:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L127:
        r53 = SchemaUtil.computeSizeFixed32ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L132;
        r6.putInt(r1, r13, r53);
    L132:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L133:
        r53 = SchemaUtil.computeSizeFixed64ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L138;
        r6.putInt(r1, r13, r53);
    L138:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L139:
        r53 = SchemaUtil.computeSizeInt32ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L144;
        r6.putInt(r1, r13, r53);
    L144:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L145:
        r53 = SchemaUtil.computeSizeUInt64ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L150;
        r6.putInt(r1, r13, r53);
    L150:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L151:
        r53 = SchemaUtil.computeSizeInt64ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L156;
        r6.putInt(r1, r13, r53);
    L156:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L157:
        r53 = SchemaUtil.computeSizeFixed32ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L162;
        r6.putInt(r1, r13, r53);
    L162:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L163:
        r53 = SchemaUtil.computeSizeFixed64ListNoTag((List) r6.getObject(r1, r82));
        if (r53 <= 0) goto L247;
        if (r0.useCachedSizeField == false) goto L168;
        r6.putInt(r1, r13, r53);
    L168:
        r83 = CodedOutputStream.computeTagSize(r11);
        r92 = CodedOutputStream.computeUInt32SizeNoTag(r53);
        goto L90
    L169:
        r52 = SchemaUtil.computeSizeSInt64List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L170:
        r52 = SchemaUtil.computeSizeSInt32List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L171:
        r52 = SchemaUtil.computeSizeFixed64List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L172:
        r52 = SchemaUtil.computeSizeFixed32List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L173:
        r52 = SchemaUtil.computeSizeEnumList(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L174:
        r52 = SchemaUtil.computeSizeUInt32List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L175:
        r52 = SchemaUtil.computeSizeByteStringList(r11, (List) r6.getObject(r1, r82));
        goto L27
    L176:
        r52 = SchemaUtil.computeSizeMessageList(r11, (List) r6.getObject(r1, r82), r0.getMessageFieldSchema(r2));
        goto L27
    L177:
        r52 = SchemaUtil.computeSizeStringList(r11, (List) r6.getObject(r1, r82));
        goto L27
    L178:
        r52 = SchemaUtil.computeSizeBoolList(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L179:
        r52 = SchemaUtil.computeSizeFixed32List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L180:
        r52 = SchemaUtil.computeSizeFixed64List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L181:
        r52 = SchemaUtil.computeSizeInt32List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L182:
        r52 = SchemaUtil.computeSizeUInt64List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L183:
        r52 = SchemaUtil.computeSizeInt64List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L184:
        r52 = SchemaUtil.computeSizeFixed32List(r11, (List) r6.getObject(r1, r82), false);
        goto L27
    L185:
        r52 = SchemaUtil.computeSizeFixed64List(r11, (List) r6.getObject(r1, r82), false);
    L247:
        r9 = r16;
        goto L248
    L25:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeGroupSize(r11, (MessageLite) r6.getObject(r1, r82), r0.getMessageFieldSchema(r2));
        goto L27
    L29:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeSInt64Size(r11, oneofLongAt(r1, r82));
        goto L27
    L32:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeSInt32Size(r11, oneofIntAt(r1, r82));
        goto L27
    L35:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeSFixed64Size(r11, 0);
        goto L27
    L38:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeSFixed32Size(r11, 0);
        goto L27
    L41:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeEnumSize(r11, oneofIntAt(r1, r82));
        goto L27
    L44:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeUInt32Size(r11, oneofIntAt(r1, r82));
        goto L27
    L47:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeBytesSize(r11, (ByteString) r6.getObject(r1, r82));
        goto L27
    L50:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = SchemaUtil.computeSizeMessage(r11, r6.getObject(r1, r82), r0.getMessageFieldSchema(r2));
        goto L27
    L53:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        Object r54 = r6.getObject(r1, r82);
        if ((r54 instanceof ByteString) == false) goto L57;
        r52 = CodedOutputStream.computeBytesSize(r11, (ByteString) r54);
        goto L27
    L57:
        r52 = CodedOutputStream.computeStringSize(r11, (String) r54);
        goto L27
    L59:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeBoolSize(r11, true);
        goto L27
    L62:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeFixed32Size(r11, 0);
        goto L27
    L65:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeFixed64Size(r11, 0);
        goto L27
    L68:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeInt32Size(r11, oneofIntAt(r1, r82));
        goto L27
    L71:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeUInt64Size(r11, oneofLongAt(r1, r82));
        goto L27
    L74:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeInt64Size(r11, oneofLongAt(r1, r82));
        goto L27
    L77:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeFloatSize(r11, 0.0f);
        goto L27
    L80:
        if (r0.isOneofPresent(r1, r11, r2) == false) goto L247;
        r52 = CodedOutputStream.computeDoubleSize(r11, 0.0d);
        goto L27
    L187:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L247;
        r52 = CodedOutputStream.computeGroupSize(r11, (MessageLite) r6.getObject(r1, r82), r0.getMessageFieldSchema(r2));
        goto L27
    L190:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        int r02 = CodedOutputStream.computeSInt64Size(r11, r6.getLong(r1, r82));
    L192:
        r9 = r16 + r02;
        r0 = this;
    L193:
        r0 = this;
        goto L247
    L195:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        r02 = CodedOutputStream.computeSInt32Size(r11, r6.getInt(r1, r82));
        goto L192
    L198:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L201;
        int r03 = CodedOutputStream.computeSFixed64Size(r11, 0);
    L200:
        r9 = r16 + r03;
        r0 = this;
        r1 = r18;
    L201:
        r0 = this;
        r1 = r18;
        goto L247
    L203:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L201;
        r03 = CodedOutputStream.computeSFixed32Size(r11, 0);
        goto L200
    L206:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        r02 = CodedOutputStream.computeEnumSize(r11, r6.getInt(r1, r82));
        goto L192
    L209:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        r02 = CodedOutputStream.computeUInt32Size(r11, r6.getInt(r1, r82));
        goto L192
    L212:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        r02 = CodedOutputStream.computeBytesSize(r11, (ByteString) r6.getObject(r1, r82));
        goto L192
    L215:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L247;
        r52 = SchemaUtil.computeSizeMessage(r11, r6.getObject(r1, r82), r0.getMessageFieldSchema(r2));
        goto L27
    L218:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        Object r04 = r6.getObject(r1, r82);
        if ((r04 instanceof ByteString) == false) goto L222;
        r02 = CodedOutputStream.computeBytesSize(r11, (ByteString) r04);
        goto L192
    L222:
        r02 = CodedOutputStream.computeStringSize(r11, (String) r04);
        goto L192
    L224:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L201;
        r03 = CodedOutputStream.computeBoolSize(r11, true);
        goto L200
    L227:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L201;
        r03 = CodedOutputStream.computeFixed32Size(r11, 0);
        goto L200
    L230:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L201;
        r03 = CodedOutputStream.computeFixed64Size(r11, 0);
        goto L200
    L233:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        r02 = CodedOutputStream.computeInt32Size(r11, r6.getInt(r1, r82));
        goto L192
    L236:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        r02 = CodedOutputStream.computeUInt64Size(r11, r6.getLong(r1, r82));
        goto L192
    L239:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L193;
        r02 = CodedOutputStream.computeInt64Size(r11, r6.getLong(r1, r82));
        goto L192
    L242:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L201;
        r03 = CodedOutputStream.computeFloatSize(r11, 0.0f);
        goto L200
    L245:
        if (r0.isFieldPresent(r1, r2, r3, r4, r122) == false) goto L247;
        r52 = CodedOutputStream.computeDoubleSize(r11, 0.0d);
        goto L27
    L18:
        if (r10 > FieldType.SINT64_LIST_PACKED.id()) goto L20;
    L14:
        r122 = 0;
        goto L13
    L249:
        int r93 = r9 + r0.getUnknownFieldsSerializedSize(r0.unknownFieldSchema, r1);
        if (r0.hasExtensions == true) goto L252;
        return r93;
    L252:
        return r93 + r0.extensionSchema.getExtensions(r1).getSerializedSize();
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int hashCode(T r9) {
        int r0 = this.buffer.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L88;
        int r3 = typeAndOffsetAt(r1);
        int r4 = numberAt(r1);
        long r5 = offset(r3);
        int r7 = 37;
        switch(type(r3)) {
            case 0: goto L86;
            case 1: goto L85;
            case 2: goto L84;
            case 3: goto L83;
            case 4: goto L82;
            case 5: goto L81;
            case 6: goto L80;
            case 7: goto L79;
            case 8: goto L78;
            case 9: goto L75;
            case 10: goto L74;
            case 11: goto L73;
            case 12: goto L72;
            case 13: goto L71;
            case 14: goto L70;
            case 15: goto L69;
            case 16: goto L68;
            case 17: goto L64;
            case 18: goto L63;
            case 19: goto L63;
            case 20: goto L63;
            case 21: goto L63;
            case 22: goto L63;
            case 23: goto L63;
            case 24: goto L63;
            case 25: goto L63;
            case 26: goto L63;
            case 27: goto L63;
            case 28: goto L63;
            case 29: goto L63;
            case 30: goto L63;
            case 31: goto L63;
            case 32: goto L63;
            case 33: goto L63;
            case 34: goto L63;
            case 35: goto L63;
            case 36: goto L63;
            case 37: goto L63;
            case 38: goto L63;
            case 39: goto L63;
            case 40: goto L63;
            case 41: goto L63;
            case 42: goto L63;
            case 43: goto L63;
            case 44: goto L63;
            case 45: goto L63;
            case 46: goto L63;
            case 47: goto L63;
            case 48: goto L63;
            case 49: goto L63;
            case 50: goto L62;
            case 51: goto L60;
            case 52: goto L57;
            case 53: goto L54;
            case 54: goto L51;
            case 55: goto L48;
            case 56: goto L45;
            case 57: goto L42;
            case 58: goto L39;
            case 59: goto L36;
            case 60: goto L33;
            case 61: goto L30;
            case 62: goto L27;
            case 63: goto L24;
            case 64: goto L21;
            case 65: goto L18;
            case 66: goto L15;
            case 67: goto L12;
            case 68: goto L8;
            default: goto L87;
        };
    L62:
        int r22 = r2 * 53;
        int r32 = UnsafeUtil.getObject(r9, r5).hashCode();
    L10:
        r2 = r22 + r32;
        goto L87
    L63:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L64:
        Object r33 = UnsafeUtil.getObject(r9, r5);
        if (r33 == null) goto L67;
        r7 = r33.hashCode();
    L67:
        r2 = (r2 * 53) + r7;
        goto L87
    L68:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L69:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L70:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L71:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L72:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L73:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L74:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L75:
        Object r34 = UnsafeUtil.getObject(r9, r5);
        if (r34 == null) goto L67;
        r7 = r34.hashCode();
        goto L67
    L78:
        r22 = r2 * 53;
        r32 = ((String) UnsafeUtil.getObject(r9, r5)).hashCode();
        goto L10
    L79:
        r22 = r2 * 53;
        r32 = Internal.hashBoolean(UnsafeUtil.getBoolean(r9, r5));
        goto L10
    L80:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L81:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L82:
        r22 = r2 * 53;
        r32 = UnsafeUtil.getInt(r9, r5);
        goto L10
    L83:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L84:
        r22 = r2 * 53;
        r32 = Internal.hashLong(UnsafeUtil.getLong(r9, r5));
        goto L10
    L85:
        r22 = r2 * 53;
        r32 = Float.floatToIntBits(UnsafeUtil.getFloat(r9, r5));
        goto L10
    L86:
        r22 = r2 * 53;
        r32 = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(r9, r5)));
        goto L10
    L8:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L12:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L15:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L18:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L21:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L24:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L27:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L30:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L33:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = UnsafeUtil.getObject(r9, r5).hashCode();
        goto L10
    L36:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = ((String) UnsafeUtil.getObject(r9, r5)).hashCode();
        goto L10
    L39:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashBoolean(oneofBooleanAt(r9, r5));
        goto L10
    L42:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L45:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L48:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = oneofIntAt(r9, r5);
        goto L10
    L51:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L54:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(oneofLongAt(r9, r5));
        goto L10
    L57:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Float.floatToIntBits(oneofFloatAt(r9, r5));
        goto L10
    L60:
        if (isOneofPresent(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(r9, r5)));
    L87:
        r1 = r1 + 3;
        goto L3
    L88:
        int r23 = (r2 * 53) + this.unknownFieldSchema.getFromMessage(r9).hashCode();
        if (this.hasExtensions == true) goto L91;
        return r23;
    L91:
        return (r23 * 53) + this.extensionSchema.getExtensions(r9).hashCode();
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public final boolean isInitialized(T r15) {
        int r3 = 1048575;
        int r2 = 0;
        int r4 = 0;
    L4:
        if (r2 >= this.checkInitializedCount) goto L48;
        int r9 = this.intArray[r2];
        int r5 = this.numberAt(r9);
        int r13 = this.typeAndOffsetAt(r9);
        int r7 = this.buffer[r9 + 2];
        int r8 = r7 & 1048575;
        int r12 = 1 << (r7 >>> 20);
        if (r8 == r3) goto L10;
        if (r8 == 1048575) goto L9;
        r4 = UNSAFE.getInt(r15, r8);
    L9:
        int r11 = r4;
        int r10 = r8;
    L11:
        MessageSchema<T> r72 = this;
        T r82 = r15;
        if (isRequired(r13) == true) goto L14;
    L16:
        int r14 = type(r13);
        if (r14 == 9) goto L43;
        if (r14 == 17) goto L43;
        if (r14 == 27) goto L40;
        if (r14 == 60) goto L35;
        if (r14 == 68) goto L35;
        if (r14 == 49) goto L40;
        if (r14 != 50) goto L47;
        if (r72.isMapInitialized(r82, r13, r9) == true) goto L47;
        return false;
    L47:
        r2 = r2 + 1;
        MessageSchema<T> this = r72;
        r15 = r82;
        r3 = r10;
        r4 = r11;
    L35:
        if (r72.isOneofPresent(r82, r5, r9) == false) goto L47;
        if (isInitialized(r82, r13, r72.getMessageFieldSchema(r9)) == true) goto L47;
        return false;
    L40:
        if (r72.isListInitialized(r82, r13, r9) == true) goto L47;
        return false;
    L43:
        if (r72.isFieldPresent(r82, r9, r10, r11, r12) == false) goto L47;
        if (isInitialized(r82, r13, r72.getMessageFieldSchema(r9)) == true) goto L47;
        return false;
    L14:
        if (r72.isFieldPresent(r82, r9, r10, r11, r12) == true) goto L16;
        return false;
    L10:
        r10 = r3;
        r11 = r4;
        goto L11
    L48:
        MessageSchema<T> r73 = this;
        T r83 = r15;
        if (r73.hasExtensions == true) goto L51;
    L53:
        return true;
    L51:
        if (r73.extensionSchema.getExtensions(r83).isInitialized() == true) goto L53;
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.protobuf.Schema
    public void makeImmutable(T r8) {
        if (isMutable(r8) == true) goto L6;
        return;
    L6:
        if ((r8 instanceof GeneratedMessageLite) == false) goto L8;
        GeneratedMessageLite r0 = (GeneratedMessageLite) r8;
        r0.clearMemoizedSerializedSize();
        r0.clearMemoizedHashCode();
        r0.markImmutable();
    L8:
        int r02 = this.buffer.length;
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L29;
        int r2 = typeAndOffsetAt(r1);
        long r3 = offset(r2);
        int r22 = type(r2);
        if (r22 == 9) goto L26;
        if (r22 == 60) goto L23;
        if (r22 == 68) goto L23;
        switch(r22) {
            case 17: goto L26;
            case 18: goto L21;
            case 19: goto L21;
            case 20: goto L21;
            case 21: goto L21;
            case 22: goto L21;
            case 23: goto L21;
            case 24: goto L21;
            case 25: goto L21;
            case 26: goto L21;
            case 27: goto L21;
            case 28: goto L21;
            case 29: goto L21;
            case 30: goto L21;
            case 31: goto L21;
            case 32: goto L21;
            case 33: goto L21;
            case 34: goto L21;
            case 35: goto L21;
            case 36: goto L21;
            case 37: goto L21;
            case 38: goto L21;
            case 39: goto L21;
            case 40: goto L21;
            case 41: goto L21;
            case 42: goto L21;
            case 43: goto L21;
            case 44: goto L21;
            case 45: goto L21;
            case 46: goto L21;
            case 47: goto L21;
            case 48: goto L21;
            case 49: goto L21;
            case 50: goto L18;
            default: goto L28;
        };
    L18:
        Unsafe r23 = UNSAFE;
        Object r5 = r23.getObject(r8, r3);
        if (r5 == null) goto L28;
        r23.putObject(r8, r3, this.mapFieldSchema.toImmutable(r5));
        goto L28
    L21:
        this.listFieldSchema.makeImmutableListAt(r8, r3);
    L28:
        r1 = r1 + 3;
    L23:
        if (isOneofPresent(r8, numberAt(r1), r1) == false) goto L28;
        getMessageFieldSchema(r1).makeImmutable(UNSAFE.getObject(r8, r3));
    L26:
        if (isFieldPresent(r8, r1) == false) goto L28;
        getMessageFieldSchema(r1).makeImmutable(UNSAFE.getObject(r8, r3));
        goto L28
    L29:
        this.unknownFieldSchema.makeImmutable(r8);
        if (this.hasExtensions == false) goto L42;
        this.extensionSchema.makeImmutable(r8);
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T r7, Reader r8, ExtensionRegistryLite r9) {
        r9.getClass();
        checkMutable(r7);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, r7, r8, r9);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T r3, T r4) {
        checkMutable(r3);
        r4.getClass();
        int r0 = 0;
    L4:
        if (r0 >= this.buffer.length) goto L6;
        mergeSingleField(r3, r4, r0);
        r0 = r0 + 3;
        goto L4
    L6:
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, r3, r4);
        if (this.hasExtensions == false) goto L11;
        SchemaUtil.mergeExtensions(this.extensionSchema, r3, r4);
        return;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void writeTo(T r3, Writer r4) {
        if (r4.fieldOrder() != Writer.FieldOrder.DESCENDING) goto L6;
        writeFieldsInDescendingOrder(r3, r4);
        return;
    L6:
        writeFieldsInAscendingOrder(r3, r4);
    }
}
