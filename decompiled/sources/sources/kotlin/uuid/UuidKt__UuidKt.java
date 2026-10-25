package kotlin.uuid;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.HexExtensionsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\n\u001a\n\u0010\u0003\u001a\u00020\u0004H\u0081\u0080\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0081\u0080\u0004\u001a\u0016\u0010\b\u001a\u00020\t*\u00020\u00072\u0006\u0010\n\u001a\u00020\u0001H\u0080\u0080\u0004\u001a.\u0010\u000b\u001a\u00020\f*\u00020\t2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0081\u0080\u0004\u001a\u001e\u0010\u0011\u001a\u00020\f*\u00020\u00072\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\tH\u0080\u0080\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001af\u0010\u0017\u001a\u00020\f*\u00020\u00152\u0006\u0010\n\u001a\u00020\u00012K\u0010\u0018\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0019H\u0080\u0088\u0004ø\u0001\u0000\u001ab\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152K\u0010\u0018\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u0019H\u0081\u0088\u0004ø\u0001\u0000\u001a\u0012\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020\u0015H\u0081\u0080\u0004\u001ab\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00152K\u0010\u0018\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u001f0\u0019H\u0081\u0088\u0004ø\u0001\u0000\u001a\u001b\u0010$\u001a\u00020\u0015*\u00020\u00152\u0006\u0010%\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b&\u001a\u001b\u0010$\u001a\u00020\u0015*\u00020\u00072\u0006\u0010'\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b&\u001a\"\u0010(\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0001H\u0080\u0080\u0004\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0002\u001a\u00020\u0001X\u0082Ô\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006)"}, d2 = {"UUID_HEX_LENGTH", _UrlKt.FRAGMENT_ENCODE_SET, "UUID_HEX_DASH_LENGTH", "secureRandomUuid", "Lkotlin/uuid/Uuid;", "uuidFromRandomBytes", "randomBytes", _UrlKt.FRAGMENT_ENCODE_SET, "getLongAtCommonImpl", _UrlKt.FRAGMENT_ENCODE_SET, "index", "formatBytesIntoCommonImpl", _UrlKt.FRAGMENT_ENCODE_SET, "dst", "dstOffset", "startIndex", "endIndex", "setLongAtCommonImpl", "value", "uuidParseHexDashCommonImpl", "hexDashString", _UrlKt.FRAGMENT_ENCODE_SET, "uuidParseHexDashOrNullCommonImpl", "uuidCheckHyphenAt", "onError", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "inputString", "errorDescription", "errorPosition", _UrlKt.FRAGMENT_ENCODE_SET, "uuidParseHexCommonImpl", "hexString", "uuidParseHexOrNullCommonImpl", "errorIndex", "truncateForErrorMessage", "maxLength", "truncateForErrorMessage$UuidKt__UuidKt", "maxSize", "uuidThrowUnexpectedCharacterException", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/uuid/UuidKt")
@SourceDebugExtension({"SMAP\nUuid.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Uuid.kt\nkotlin/uuid/UuidKt__UuidKt\n+ 2 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n*L\n1#1,1051:1\n893#1,5:1052\n898#1:1066\n885#1,2:1067\n899#1:1069\n900#1:1076\n885#1,2:1077\n901#1:1079\n902#1:1086\n885#1,2:1087\n903#1:1089\n904#1:1096\n885#1,2:1097\n905#1:1099\n907#1,3:1106\n893#1,5:1109\n898#1:1123\n885#1,2:1124\n899#1:1126\n900#1:1133\n885#1,2:1134\n901#1:1136\n902#1:1143\n885#1,2:1144\n903#1:1146\n904#1:1153\n885#1,2:1154\n905#1:1156\n907#1,3:1163\n885#1,2:1175\n885#1,2:1186\n885#1,2:1197\n885#1,2:1208\n939#1:1219\n940#1:1229\n941#1:1236\n939#1:1237\n940#1:1247\n941#1:1254\n1201#2,3:1057\n1229#2,5:1060\n1205#2:1065\n1229#2,5:1070\n1205#2:1075\n1229#2,5:1080\n1205#2:1085\n1229#2,5:1090\n1205#2:1095\n1229#2,5:1100\n1205#2:1105\n1201#2,3:1114\n1229#2,5:1117\n1205#2:1122\n1229#2,5:1127\n1205#2:1132\n1229#2,5:1137\n1205#2:1142\n1229#2,5:1147\n1205#2:1152\n1229#2,5:1157\n1205#2:1162\n1201#2,3:1166\n1229#2,5:1169\n1205#2:1174\n1201#2,3:1177\n1229#2,5:1180\n1205#2:1185\n1201#2,3:1188\n1229#2,5:1191\n1205#2:1196\n1201#2,3:1199\n1229#2,5:1202\n1205#2:1207\n1201#2,3:1210\n1229#2,5:1213\n1205#2:1218\n1201#2,3:1220\n1229#2,5:1223\n1205#2:1228\n1229#2,5:1230\n1205#2:1235\n1201#2,3:1238\n1229#2,5:1241\n1205#2:1246\n1229#2,5:1248\n1205#2:1253\n1201#2,3:1255\n1229#2,5:1258\n1205#2:1263\n1201#2,3:1264\n1229#2,5:1267\n1205#2:1272\n*S KotlinDebug\n*F\n+ 1 Uuid.kt\nkotlin/uuid/UuidKt__UuidKt\n*L\n869#1:1052,5\n869#1:1066\n869#1:1067,2\n869#1:1069\n869#1:1076\n869#1:1077,2\n869#1:1079\n869#1:1086\n869#1:1087,2\n869#1:1089\n869#1:1096\n869#1:1097,2\n869#1:1099\n869#1:1106,3\n876#1:1109,5\n876#1:1123\n876#1:1124,2\n876#1:1126\n876#1:1133\n876#1:1134,2\n876#1:1136\n876#1:1143\n876#1:1144,2\n876#1:1146\n876#1:1153\n876#1:1154,2\n876#1:1156\n876#1:1163,3\n898#1:1175,2\n900#1:1186,2\n902#1:1197,2\n904#1:1208,2\n921#1:1219\n921#1:1229\n921#1:1236\n928#1:1237\n928#1:1247\n928#1:1254\n869#1:1057,3\n869#1:1060,5\n869#1:1065\n869#1:1070,5\n869#1:1075\n869#1:1080,5\n869#1:1085\n869#1:1090,5\n869#1:1095\n869#1:1100,5\n869#1:1105\n876#1:1114,3\n876#1:1117,5\n876#1:1122\n876#1:1127,5\n876#1:1132\n876#1:1137,5\n876#1:1142\n876#1:1147,5\n876#1:1152\n876#1:1157,5\n876#1:1162\n897#1:1166,3\n897#1:1169,5\n897#1:1174\n899#1:1177,3\n899#1:1180,5\n899#1:1185\n901#1:1188,3\n901#1:1191,5\n901#1:1196\n903#1:1199,3\n903#1:1202,5\n903#1:1207\n905#1:1210,3\n905#1:1213,5\n905#1:1218\n921#1:1220,3\n921#1:1223,5\n921#1:1228\n921#1:1230,5\n921#1:1235\n928#1:1238,3\n928#1:1241,5\n928#1:1246\n928#1:1248,5\n928#1:1253\n939#1:1255,3\n939#1:1258,5\n939#1:1263\n940#1:1264,3\n940#1:1267,5\n940#1:1272\n*E\n"})
/* loaded from: classes5.dex */
class UuidKt__UuidKt extends UuidKt__UuidJVMKt {
    private static final int UUID_HEX_DASH_LENGTH = 36;
    private static final int UUID_HEX_LENGTH = 32;

    public UuidKt__UuidKt() {
    }

    public static final /* synthetic */ String access$truncateForErrorMessage(String r0, int r1) {
        return truncateForErrorMessage$UuidKt__UuidKt(r0, r1);
    }

    public static final /* synthetic */ String access$truncateForErrorMessage(byte[] r0, int r1) {
        return truncateForErrorMessage$UuidKt__UuidKt(r0, r1);
    }

    @ExperimentalUuidApi
    public static final void formatBytesIntoCommonImpl(long r4, byte[] r6, int r7, int r8, int r9) {
        int r82 = 7 - r8;
        int r92 = 8 - r9;
        if (r92 > r82) goto L7;
    L4:
        int r0 = HexExtensionsKt.getBYTE_TO_LOWER_CASE_HEX_DIGITS()[(int) ((r4 >> (r82 << 3)) & 255)];
        int r1 = r7 + 1;
        r6[r7] = (byte) (r0 >> 8);
        r7 = r7 + 2;
        r6[r1] = (byte) r0;
        if (r82 == r92) goto L9;
        r82 = r82 - 1;
        goto L4
    L9:
        return;
    }

    public static final long getLongAtCommonImpl(byte[] r7, int r8) {
        return (r7[r8 + 7] & 255) | ((((((((r7[r8] & 255) << 56) | ((r7[r8 + 1] & 255) << 48)) | ((r7[r8 + 2] & 255) << 40)) | ((r7[r8 + 3] & 255) << 32)) | ((r7[r8 + 4] & 255) << 24)) | ((r7[r8 + 5] & 255) << 16)) | ((r7[r8 + 6] & 255) << 8));
    }

    @ExperimentalUuidApi
    public static final Uuid secureRandomUuid() {
        byte[] r0 = new byte[16];
        UuidKt__UuidJVMKt.secureRandomBytes(r0);
        return uuidFromRandomBytes(r0);
    }

    public static final void setLongAtCommonImpl(byte[] r5, int r6, long r7) {
        int r0 = 7;
    L4:
        if ((-1) >= r0) goto L6;
        r5[r6] = (byte) (r7 >> (r0 << 3));
        r0 = r0 - 1;
        r6 = r6 + 1;
        goto L4
    }

    private static final String truncateForErrorMessage$UuidKt__UuidKt(String r1, int r2) {
        if (r1.length() > r2) goto L6;
        return r1;
    L6:
        return r1.substring(0, r2).concat("...");
    }

    private static final String truncateForErrorMessage$UuidKt__UuidKt(byte[] r9, int r10) {
        return ArraysKt.joinToString$default(r9, null, "[", "]", r10, null, null, 49, null);
    }

    public static final void uuidCheckHyphenAt(String r2, int r3, Function3<? super String, ? super String, ? super Integer, Unit> r4) {
        if (r2.charAt(r3) == '-') goto L6;
        r4.invoke(r2, "'-' (hyphen)", Integer.valueOf(r3));
        return;
    }

    @ExperimentalUuidApi
    public static final Uuid uuidFromRandomBytes(byte[] r2) {
        byte r1 = (byte) (r2[6] & 15);
        r2[6] = r1;
        r2[6] = (byte) (r1 | 64);
        byte r12 = (byte) (r2[8] & 63);
        r2[8] = r12;
        r2[8] = (byte) (r12 | ByteCompanionObject.MIN_VALUE);
        return Uuid.Companion.fromByteArray(r2);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexCommonImpl(String r14) {
        int r0 = 0;
        long r3 = 0;
    L3:
        int r8 = 16;
        if (r0 >= 16) goto L13;
        long r32 = r3 << 4;
        char r7 = r14.charAt(r0);
        if ((r7 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r7] < 0) goto L10;
        r3 = r32 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r7];
        r0 = r0 + 1;
    L10:
        uuidThrowUnexpectedCharacterException(r14, "a hexadecimal digit", r0);
    L11:
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L13:
        long r9 = 0;
    L15:
        if (r8 >= 32) goto L23;
        long r92 = r9 << 4;
        char r02 = r14.charAt(r8);
        if ((r02 >>> '\b') != 0) goto L21;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r02] < 0) goto L21;
        r9 = r92 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r02];
        r8 = r8 + 1;
    L21:
        uuidThrowUnexpectedCharacterException(r14, "a hexadecimal digit", r8);
        goto L11
    L23:
        return Uuid.Companion.fromLongs(r3, r9);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexCommonImpl(String r14, Function3 r15) {
        int r0 = 0;
        long r3 = 0;
    L3:
        int r8 = 16;
        if (r0 >= 16) goto L12;
        long r32 = r3 << 4;
        char r7 = r14.charAt(r0);
        if ((r7 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r7] < 0) goto L10;
        r3 = r32 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r7];
        r0 = r0 + 1;
    L10:
        r15.invoke(r14, "a hexadecimal digit", Integer.valueOf(r0));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L12:
        long r9 = 0;
    L14:
        if (r8 >= 32) goto L23;
        long r92 = r9 << 4;
        char r02 = r14.charAt(r8);
        if ((r02 >>> '\b') != 0) goto L20;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r02] < 0) goto L20;
        r9 = r92 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r02];
        r8 = r8 + 1;
    L20:
        r15.invoke(r14, "a hexadecimal digit", Integer.valueOf(r8));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L23:
        return Uuid.Companion.fromLongs(r3, r9);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexDashCommonImpl(String r21) {
        int r1 = 0;
        long r4 = 0;
    L3:
        int r9 = 8;
        if (r1 >= 8) goto L14;
        long r42 = r4 << 4;
        char r7 = r21.charAt(r1);
        if ((r7 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r7] < 0) goto L10;
        r4 = r42 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r7];
        r1 = r1 + 1;
    L10:
        uuidThrowUnexpectedCharacterException(r21, "a hexadecimal digit", r1);
    L11:
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        if (r21.charAt(8) != '-') goto L55;
        r1 = 9;
        long r12 = 0;
    L16:
        r9 = 13;
        if (r1 >= 13) goto L24;
        long r122 = r12 << 4;
        char r92 = r21.charAt(r1);
        if ((r92 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r92] < 0) goto L10;
        r12 = r122 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r92];
        r1 = r1 + 1;
        goto L16
    L24:
        if (r21.charAt(13) != '-') goto L55;
        r1 = 14;
        long r14 = 0;
    L26:
        r9 = 18;
        if (r1 >= 18) goto L34;
        long r142 = r14 << 4;
        char r93 = r21.charAt(r1);
        if ((r93 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r93] < 0) goto L10;
        r14 = r142 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r93];
        r1 = r1 + 1;
        goto L26
    L34:
        if (r21.charAt(18) != '-') goto L55;
        r1 = 19;
        long r16 = 0;
    L36:
        r9 = 23;
        if (r1 >= 23) goto L44;
        long r162 = r16 << 4;
        char r94 = r21.charAt(r1);
        if ((r94 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r94] < 0) goto L10;
        r16 = r162 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r94];
        r1 = r1 + 1;
        goto L36
    L44:
        if (r21.charAt(23) != '-') goto L55;
        r1 = 24;
        long r95 = 0;
    L47:
        if (r1 >= 36) goto L54;
        long r96 = r95 << 4;
        char r11 = r21.charAt(r1);
        if ((r11 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r11] < 0) goto L10;
        r95 = r96 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r11];
        r1 = r1 + 1;
        goto L47
    L54:
        return Uuid.Companion.fromLongs(((r4 << 32) | (r12 << 16)) | r14, (r16 << 48) | r95);
    L55:
        uuidThrowUnexpectedCharacterException(r21, "'-' (hyphen)", r9);
        goto L11
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexDashCommonImpl(String r22, Function3 r23) {
        int r2 = 0;
        long r5 = 0;
    L4:
        if (r2 >= 8) goto L13;
        long r52 = r5 << 4;
        char r8 = r22.charAt(r2);
        if ((r8 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r8] < 0) goto L10;
        r5 = r52 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r8];
        r2 = r2 + 1;
    L10:
        r23.invoke(r22, "a hexadecimal digit", Integer.valueOf(r2));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L13:
        if (r22.charAt(8) == '-') goto L15;
        r23.invoke(r22, "'-' (hyphen)", 8);
    L15:
        int r24 = 9;
        long r13 = 0;
    L17:
        if (r24 >= 13) goto L26;
        long r132 = r13 << 4;
        char r10 = r22.charAt(r24);
        if ((r10 >>> '\b') != 0) goto L23;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r10] < 0) goto L23;
        r13 = r132 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r10];
        r24 = r24 + 1;
    L23:
        r23.invoke(r22, "a hexadecimal digit", Integer.valueOf(r24));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L26:
        if (r22.charAt(13) == '-') goto L28;
        r23.invoke(r22, "'-' (hyphen)", 13);
    L28:
        int r25 = 14;
        long r15 = 0;
    L30:
        if (r25 >= 18) goto L39;
        long r152 = r15 << 4;
        char r102 = r22.charAt(r25);
        if ((r102 >>> '\b') != 0) goto L36;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r102] < 0) goto L36;
        r15 = r152 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r102];
        r25 = r25 + 1;
    L36:
        r23.invoke(r22, "a hexadecimal digit", Integer.valueOf(r25));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L39:
        if (r22.charAt(18) == '-') goto L41;
        r23.invoke(r22, "'-' (hyphen)", 18);
    L41:
        int r26 = 19;
        long r17 = 0;
    L43:
        if (r26 >= 23) goto L52;
        long r172 = r17 << 4;
        char r103 = r22.charAt(r26);
        if ((r103 >>> '\b') != 0) goto L49;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r103] < 0) goto L49;
        r17 = r172 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r103];
        r26 = r26 + 1;
    L49:
        r23.invoke(r22, "a hexadecimal digit", Integer.valueOf(r26));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L52:
        if (r22.charAt(23) == '-') goto L54;
        r23.invoke(r22, "'-' (hyphen)", 23);
    L54:
        int r27 = 24;
        long r104 = 0;
    L56:
        if (r27 >= 36) goto L65;
        long r105 = r104 << 4;
        char r12 = r22.charAt(r27);
        if ((r12 >>> '\b') != 0) goto L62;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r12] < 0) goto L62;
        r104 = r105 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r12];
        r27 = r27 + 1;
    L62:
        r23.invoke(r22, "a hexadecimal digit", Integer.valueOf(r27));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L65:
        return Uuid.Companion.fromLongs(((r5 << 32) | (r13 << 16)) | r15, (r17 << 48) | r104);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexDashOrNullCommonImpl(String r20) {
        int r1 = 0;
        long r4 = 0;
    L4:
        if (r1 >= 8) goto L12;
        long r42 = r4 << 4;
        char r6 = r20.charAt(r1);
        if ((r6 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r6] < 0) goto L10;
        r4 = r42 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r6];
        r1 = r1 + 1;
    L10:
        return null;
    L12:
        if (r20.charAt(8) == '-') goto L14;
        return null;
    L14:
        int r12 = 9;
        long r9 = 0;
    L16:
        if (r12 >= 13) goto L24;
        long r92 = r9 << 4;
        char r11 = r20.charAt(r12);
        if ((r11 >>> '\b') != 0) goto L22;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r11] < 0) goto L22;
        r9 = r92 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r11];
        r12 = r12 + 1;
    L22:
        return null;
    L24:
        if (r20.charAt(13) == '-') goto L26;
        return null;
    L26:
        int r13 = 14;
        long r112 = 0;
    L28:
        if (r13 >= 18) goto L36;
        long r113 = r112 << 4;
        char r132 = r20.charAt(r13);
        if ((r132 >>> '\b') != 0) goto L34;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r132] < 0) goto L34;
        r112 = r113 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r132];
        r13 = r13 + 1;
    L34:
        return null;
    L36:
        if (r20.charAt(18) == '-') goto L38;
        return null;
    L38:
        int r14 = 19;
        long r133 = 0;
    L40:
        if (r14 >= 23) goto L48;
        long r134 = r133 << 4;
        char r15 = r20.charAt(r14);
        if ((r15 >>> '\b') != 0) goto L46;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r15] < 0) goto L46;
        r133 = r134 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r15];
        r14 = r14 + 1;
    L46:
        return null;
    L48:
        if (r20.charAt(23) == '-') goto L50;
        return null;
    L50:
        int r16 = 24;
        long r152 = 0;
    L52:
        if (r16 >= 36) goto L60;
        long r153 = r152 << 4;
        char r8 = r20.charAt(r16);
        if ((r8 >>> '\b') != 0) goto L58;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r8] < 0) goto L58;
        r152 = r153 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r8];
        r16 = r16 + 1;
    L58:
        return null;
    L60:
        return Uuid.Companion.fromLongs(((r4 << 32) | (r9 << 16)) | r112, (r133 << 48) | r152);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexOrNullCommonImpl(String r13) {
        int r0 = 0;
        long r3 = 0;
    L3:
        int r7 = 16;
        if (r0 >= 16) goto L11;
        long r32 = r3 << 4;
        char r6 = r13.charAt(r0);
        if ((r6 >>> '\b') != 0) goto L10;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r6] < 0) goto L10;
        r3 = r32 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r6];
        r0 = r0 + 1;
    L10:
        return null;
    L11:
        long r8 = 0;
    L13:
        if (r7 >= 32) goto L21;
        long r82 = r8 << 4;
        char r02 = r13.charAt(r7);
        if ((r02 >>> '\b') != 0) goto L19;
        if (HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r02] < 0) goto L19;
        r8 = r82 | HexExtensionsKt.access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r02];
        r7 = r7 + 1;
    L19:
        return null;
    L21:
        return Uuid.Companion.fromLongs(r3, r8);
    }

    public static final Void uuidThrowUnexpectedCharacterException(String r3, String r4, int r5) {
        throw new IllegalArgumentException("Expected " + r4 + " at index " + r5 + ", but was '" + r3.charAt(r5) + '\'');
    }
}
