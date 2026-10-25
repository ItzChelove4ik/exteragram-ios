package okio.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0019\n\u0002\b\u0005\u001a+\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001f\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\f\u0010\r\"\u001a\u0010\u000f\u001a\u00020\u000e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lokio/ByteString;", "Lokio/Buffer;", "buffer", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "commonWrite", "(Lokio/ByteString;Lokio/Buffer;II)V", _UrlKt.FRAGMENT_ENCODE_SET, "s", "codePointCount", "codePointIndexToCharIndex", "([BI)I", _UrlKt.FRAGMENT_ENCODE_SET, "HEX_DIGIT_CHARS", "[C", "getHEX_DIGIT_CHARS", "()[C", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "-ByteString")
@SourceDebugExtension({"SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/internal/-ByteString\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Utf8.kt\nokio/Utf8\n*L\n1#1,342:1\n129#1,2:348\n131#1,9:351\n67#2:343\n73#2:344\n73#2:346\n73#2:347\n67#2:375\n73#2:387\n1#3:345\n1#3:350\n212#4,7:360\n122#4:367\n219#4,5:368\n122#4:373\n226#4:374\n228#4:376\n397#4,2:377\n122#4:379\n400#4,6:380\n127#4:386\n406#4:388\n122#4:389\n407#4,13:390\n122#4:403\n422#4:404\n122#4:405\n425#4:406\n230#4,3:407\n440#4,3:410\n122#4:413\n443#4:414\n127#4:415\n446#4,10:416\n127#4:426\n456#4:427\n122#4:428\n457#4,4:429\n127#4:433\n461#4:434\n122#4:435\n462#4,14:436\n122#4:450\n477#4,2:451\n122#4:453\n481#4:454\n122#4:455\n484#4:456\n234#4,3:457\n500#4,3:460\n122#4:463\n503#4:464\n127#4:465\n506#4,2:466\n127#4:468\n510#4,10:469\n127#4:479\n520#4:480\n122#4:481\n521#4,4:482\n127#4:486\n525#4:487\n122#4:488\n526#4,4:489\n127#4:493\n530#4:494\n122#4:495\n531#4,15:496\n122#4:511\n547#4,2:512\n122#4:514\n550#4,2:515\n122#4:517\n554#4:518\n122#4:519\n557#4:520\n241#4:521\n122#4:522\n242#4,5:523\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/internal/-ByteString\n*L\n308#1:348,2\n308#1:351,9\n65#1:343\n66#1:344\n256#1:346\n257#1:347\n327#1:375\n327#1:387\n308#1:350\n327#1:360,7\n332#1:367\n327#1:368,5\n332#1:373\n327#1:374\n327#1:376\n327#1:377,2\n332#1:379\n327#1:380,6\n327#1:386\n327#1:388\n332#1:389\n327#1:390,13\n332#1:403\n327#1:404\n332#1:405\n327#1:406\n327#1:407,3\n327#1:410,3\n332#1:413\n327#1:414\n327#1:415\n327#1:416,10\n327#1:426\n327#1:427\n332#1:428\n327#1:429,4\n327#1:433\n327#1:434\n332#1:435\n327#1:436,14\n332#1:450\n327#1:451,2\n332#1:453\n327#1:454\n332#1:455\n327#1:456\n327#1:457,3\n327#1:460,3\n332#1:463\n327#1:464\n327#1:465\n327#1:466,2\n327#1:468\n327#1:469,10\n327#1:479\n327#1:480\n332#1:481\n327#1:482,4\n327#1:486\n327#1:487\n332#1:488\n327#1:489,4\n327#1:493\n327#1:494\n332#1:495\n327#1:496,15\n332#1:511\n327#1:512,2\n332#1:514\n327#1:515,2\n332#1:517\n327#1:518\n332#1:519\n327#1:520\n327#1:521\n332#1:522\n327#1:523,5\n*E\n"})
/* renamed from: okio.internal.-ByteString, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class ByteString {
    private static final char[] HEX_DIGIT_CHARS = null;

    static {
        HEX_DIGIT_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public static final /* synthetic */ int access$codePointIndexToCharIndex(byte[] r0, int r1) {
        return codePointIndexToCharIndex(r0, r1);
    }

    private static final int codePointIndexToCharIndex(byte[] r18, int r19) {
        int r2 = r18.length;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r3 >= r2) goto L191;
        byte r6 = r18[r3];
        if (r6 >= 0) goto L6;
        if ((r6 >> 5) == (-2)) goto L47;
        if ((r6 >> 4) != (-2)) goto L130;
        int r14 = r3 + 2;
        if (r2 <= r14) goto L84;
        byte r15 = r18[r3 + 1];
        if ((r15 & 192) != 128) goto L126;
        byte r9 = r18[r14];
        if ((r9 & 192) != 128) goto L123;
        int r62 = (r6 << 12) ^ ((r9 ^ ByteCompanionObject.MIN_VALUE) ^ (r15 << 6));
        if (r62 < 2048) goto L93;
        if (55296 > r62) goto L101;
        if (r62 >= 57344) goto L101;
        if (r5 == r19) goto L191;
        return -1;
    L101:
        int r7 = r5 + 1;
        if (r5 == r19) goto L191;
        if (r62 == 10) goto L115;
        if (r62 == 13) goto L115;
        if (r62 < 0) goto L110;
        if (r62 >= 32) goto L110;
    L116:
        return -1;
    L110:
        if (127 > r62) goto L115;
        if (r62 < 160) goto L116;
    L115:
        if (r62 == 65533) goto L116;
        if (r62 >= 65536) goto L120;
        int r142 = 1;
    L121:
        r4 = r4 + r142;
        Unit r52 = Unit.INSTANCE;
        r3 = r3 + 3;
    L122:
        r5 = r7;
        goto L3
    L120:
        r142 = 2;
        goto L121
    L93:
        if (r5 == r19) goto L191;
        return -1;
    L123:
        if (r5 == r19) goto L191;
        return -1;
    L126:
        if (r5 == r19) goto L191;
        return -1;
    L84:
        if (r5 == r19) goto L191;
        return -1;
    L130:
        if ((r6 >> 3) != (-2)) goto L188;
        int r92 = r3 + 3;
        if (r2 <= r92) goto L133;
        byte r143 = r18[r3 + 1];
        if ((r143 & 192) != 128) goto L185;
        byte r152 = r18[r3 + 2];
        if ((r152 & 192) != 128) goto L182;
        byte r93 = r18[r92];
        if ((r93 & 192) != 128) goto L179;
        int r63 = (r6 << 18) ^ (((r93 ^ ByteCompanionObject.MIN_VALUE) ^ (r152 << 6)) ^ (r143 << 12));
        if (r63 > 1114111) goto L144;
        if (55296 > r63) goto L153;
        if (r63 >= 57344) goto L153;
        if (r5 == r19) goto L191;
        return -1;
    L153:
        if (r63 < 65536) goto L154;
        r7 = r5 + 1;
        if (r5 == r19) goto L191;
        if (r63 == 10) goto L172;
        if (r63 == 13) goto L172;
        if (r63 < 0) goto L167;
        if (r63 >= 32) goto L167;
    L173:
        return -1;
    L167:
        if (127 > r63) goto L172;
        if (r63 < 160) goto L173;
    L172:
        if (r63 == 65533) goto L173;
        if (r63 >= 65536) goto L177;
        int r144 = 1;
    L178:
        r4 = r4 + r144;
        Unit r53 = Unit.INSTANCE;
        r3 = r3 + 4;
        goto L122
    L177:
        r144 = 2;
        goto L178
    L154:
        if (r5 == r19) goto L191;
        return -1;
    L144:
        if (r5 == r19) goto L191;
        return -1;
    L179:
        if (r5 == r19) goto L191;
        return -1;
    L182:
        if (r5 == r19) goto L191;
        return -1;
    L185:
        if (r5 == r19) goto L191;
        return -1;
    L133:
        if (r5 == r19) goto L191;
        return -1;
    L188:
        if (r5 == r19) goto L191;
        return -1;
    L47:
        int r145 = r3 + 1;
        if (r2 <= r145) goto L49;
        byte r146 = r18[r145];
        if ((r146 & 192) != 128) goto L77;
        int r64 = (r6 << 6) ^ (r146 ^ ByteCompanionObject.MIN_VALUE);
        if (r64 < 128) goto L56;
        int r13 = r5 + 1;
        if (r5 == r19) goto L191;
        if (r64 == 10) goto L70;
        if (r64 == 13) goto L70;
        if (r64 < 0) goto L67;
        if (r64 >= 32) goto L67;
    L71:
        return -1;
    L67:
        if (127 > r64) goto L70;
        if (r64 < 160) goto L71;
    L70:
        if (r64 == 65533) goto L71;
        if (r64 >= 65536) goto L75;
        int r147 = 1;
    L76:
        r4 = r4 + r147;
        Unit r54 = Unit.INSTANCE;
        r3 = r3 + 2;
        r5 = r13;
        goto L3
    L75:
        r147 = 2;
        goto L76
    L56:
        if (r5 == r19) goto L191;
        return -1;
    L77:
        if (r5 == r19) goto L191;
        return -1;
    L49:
        if (r5 == r19) goto L191;
        return -1;
    L6:
        int r17 = r5 + 1;
        if (r5 == r19) goto L191;
        if (r6 == 10) goto L17;
        if (r6 == 13) goto L17;
        if (r6 < 0) goto L14;
        if (r6 >= 32) goto L14;
    L18:
        return -1;
    L14:
        if (Byte.MAX_VALUE > r6) goto L17;
        if (r6 < 160) goto L18;
    L17:
        if (r6 == 65533) goto L18;
        if (r6 >= 65536) goto L21;
        int r55 = 1;
    L22:
        r4 = r4 + r55;
        r3 = r3 + 1;
    L23:
        r5 = r17;
        if (r3 >= r2) goto L44;
        byte r65 = r18[r3];
        if (r65 < 0) goto L44;
        r3 = r3 + 1;
        r17 = r5 + 1;
        if (r5 == r19) goto L29;
        if (r65 == 10) goto L38;
        if (r65 == 13) goto L38;
        if (r65 < 0) goto L35;
        if (r65 >= 32) goto L35;
    L39:
        return -1;
    L35:
        if (Byte.MAX_VALUE > r65) goto L38;
        if (r65 < 160) goto L39;
    L38:
        if (r65 == 65533) goto L39;
        if (r65 >= 65536) goto L42;
        int r56 = 1;
    L43:
        r4 = r4 + r56;
        goto L23
    L42:
        r56 = 2;
        goto L43
    L29:
        return r4;
    L44:
        Unit r66 = Unit.INSTANCE;
        goto L3
    L21:
        r55 = 2;
    L191:
        return r4;
    }

    public static final void commonWrite(okio.ByteString r0, Buffer r1, int r2, int r3) {
        r1.write(r0.getData$okio(), r2, r3);
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }
}
