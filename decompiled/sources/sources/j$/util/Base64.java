package j$.util;

import j$.nio.file.b;
import j$.sun.nio.cs.c;
import java.util.Arrays;
import kotlin.UByte;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class Base64 {

    /* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
    public static class Decoder {

        /* renamed from: a, reason: collision with root package name */
        public static final int[] f485a = null;

        /* renamed from: b, reason: collision with root package name */
        public static final int[] f486b = null;

        /* renamed from: c, reason: collision with root package name */
        public static final Decoder f487c = null;

        static {
            int[] r1 = new int[256];
            f485a = r1;
            Arrays.fill(r1, -1);
            int r12 = 0;
            int r3 = 0;
        L4:
            if (r3 >= 64) goto L6;
            f485a[Encoder.f488a[r3]] = r3;
            r3 = r3 + 1;
            goto L4
        L6:
            f485a[61] = -2;
            int[] r0 = new int[256];
            f486b = r0;
            Arrays.fill(r0, -1);
        L7:
            if (r12 >= 64) goto L9;
            f486b[Encoder.f489b[r12]] = r12;
            r12 = r12 + 1;
            goto L7
        L9:
            f486b[61] = -2;
            f487c = new Decoder();
        }

        public byte[] decode(String r21) {
            byte[] r0 = r21.getBytes(c.f274a);
            int r1 = r0.length;
            char r5 = 2;
            if (r1 != 0) goto L5;
            int r12 = 0;
        L18:
            byte[] r6 = new byte[r12];
            int r7 = r0.length;
            int r8 = 18;
            int r10 = 18;
            int r9 = 0;
            int r11 = 0;
            int r122 = 0;
        L20:
            if (r9 >= r7) goto L57;
            int[] r15 = f485a;
            if (r10 != r8) goto L35;
            byte[] r20 = null;
            if ((r9 + 4) >= r7) goto L34;
            int r2 = ((r7 - r9) & (-4)) + r9;
        L26:
            if (r9 >= r2) goto L31;
            int r3 = r15[r0[r9] & UByte.MAX_VALUE];
            char r18 = r5;
            int r52 = r15[r0[r9 + 1] & UByte.MAX_VALUE];
            int r14 = r15[r0[r9 + 2] & UByte.MAX_VALUE];
            int r17 = r9 + 4;
            int r82 = r15[r0[r9 + 3] & UByte.MAX_VALUE];
            if ((((r3 | r52) | r14) | r82) < 0) goto L32;
            int r32 = (((r3 << 18) | (r52 << 12)) | (r14 << 6)) | r82;
            r6[r11] = (byte) (r32 >> 16);
            int r83 = r11 + 2;
            r6[r11 + 1] = (byte) (r32 >> 8);
            r11 = r11 + 3;
            r6[r83] = (byte) r32;
            r9 = r17;
            r5 = r18;
        L32:
            if (r9 >= r7) goto L58;
        L36:
            int r22 = r9 + 1;
            byte r33 = r0[r9];
            int r53 = r15[r33 & UByte.MAX_VALUE];
            if (r53 < 0) goto L39;
            int r54 = (r53 << r10) | r122;
            r10 = r10 - 6;
            if (r10 >= 0) goto L55;
            r6[r11] = (byte) (r54 >> 16);
            int r92 = r11 + 2;
            r6[r11 + 1] = (byte) (r54 >> 8);
            r11 = r11 + 3;
            r6[r92] = (byte) r54;
            r10 = 18;
            r122 = 0;
        L56:
            r9 = r22;
            r8 = 18;
            r5 = r18;
            goto L20
        L55:
            r122 = r54;
            goto L56
        L39:
            if (r53 != (-2)) goto L51;
            if (r10 != 6) goto L45;
            if (r22 == r7) goto L48;
            r9 = r9 + 2;
            if (r0[r22] != 61) goto L48;
        L46:
            if (r10 != 18) goto L58;
        L48:
            b.a("Input byte array has wrong 4-byte ending unit");
            return r20;
        L45:
            r9 = r22;
            goto L46
        L51:
            throw new IllegalArgumentException("Illegal base64 character " + Integer.toString(r33, 16));
        L58:
            if (r10 != 6) goto L60;
            r6[r11] = (byte) (r122 >> 16);
            r11 = r11 + 1;
        L64:
            if (r9 < r7) goto L69;
            if (r11 != r12) goto L67;
            return r6;
        L67:
            return Arrays.copyOf(r6, r11);
        L69:
            b.i("Input byte array has incorrect ending byte at ", r9);
            return r20;
        L60:
            if (r10 != 0) goto L63;
            int r02 = r11 + 1;
            r6[r11] = (byte) (r122 >> 16);
            r11 = r11 + 2;
            r6[r02] = (byte) (r122 >> 8);
            goto L64
        L63:
            if (r10 != 12) goto L64;
            b.a("Last unit does not have enough valid bits");
            return r20;
        L31:
            r18 = r5;
        L34:
            r18 = r5;
            goto L36
        L35:
            r20 = null;
            goto L34
        L57:
            r20 = null;
            goto L58
        L5:
            if (r1 >= 2) goto L7;
            b.a("Input byte[] should at least have 2 bytes for base64 bytes");
            return null;
        L7:
            if (r0[r1 - 1] == 61) goto L9;
            int r62 = 0;
        L13:
            if (r62 != 0) goto L17;
            int r72 = r1 & 3;
            if (r72 == 0) goto L17;
            r62 = 4 - r72;
        L17:
            r12 = (((r1 + 3) / 4) * 3) - r62;
            goto L18
        L9:
            if (r0[r1 - 2] != 61) goto L11;
            r62 = 2;
            goto L13
        L11:
            r62 = 1;
            goto L13
        }
    }

    /* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
    public static class Encoder {

        /* renamed from: a, reason: collision with root package name */
        public static final char[] f488a = null;

        /* renamed from: b, reason: collision with root package name */
        public static final char[] f489b = null;

        /* renamed from: c, reason: collision with root package name */
        public static final Encoder f490c = null;

        static {
            f488a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', SignatureVisitor.EXTENDS, '/'};
            f489b = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', SignatureVisitor.SUPER, '_'};
            f490c = new Encoder();
        }

        public String encodeToString(byte[] r15) {
            int r14 = ((r15.length + 2) / 3) * 4;
            byte[] r0 = new byte[r14];
            int r1 = r15.length;
            int r2 = (r1 / 3) * 3;
            int r4 = 0;
            int r5 = 0;
        L3:
            char[] r6 = f488a;
            if (r4 >= r2) goto L15;
            int r7 = Math.min(r4 + r2, r2);
            int r8 = r4;
            int r9 = r5;
        L6:
            if (r8 >= r7) goto L8;
            int r12 = r8 + 2;
            int r10 = ((r15[r8 + 1] & UByte.MAX_VALUE) << 8) | ((r15[r8] & UByte.MAX_VALUE) << 16);
            r8 = r8 + 3;
            int r102 = r10 | (r15[r12] & UByte.MAX_VALUE);
            r0[r9] = (byte) r6[(r102 >>> 18) & 63];
            r0[r9 + 1] = (byte) r6[(r102 >>> 12) & 63];
            int r11 = r9 + 3;
            r0[r9 + 2] = (byte) r6[(r102 >>> 6) & 63];
            r9 = r9 + 4;
            r0[r11] = (byte) r6[r102 & 63];
            goto L6
        L8:
            int r42 = ((r7 - r4) / 3) * 4;
            r5 = r5 + r42;
            if (r42 != (-1)) goto L14;
            if (r7 >= r1) goto L14;
            throw null;
        L14:
            r4 = r7;
            goto L3
        L15:
            if (r4 >= r1) goto L20;
            int r22 = r4 + 1;
            int r43 = r15[r4] & UByte.MAX_VALUE;
            int r72 = r5 + 1;
            r0[r5] = (byte) r6[r43 >> 2];
            if (r22 != r1) goto L19;
            r0[r72] = (byte) r6[(r43 << 4) & 63];
            int r13 = r5 + 3;
            r0[r5 + 2] = kotlin.io.encoding.Base64.padSymbol;
            r5 = r5 + 4;
            r0[r13] = kotlin.io.encoding.Base64.padSymbol;
            goto L20
        L19:
            int r152 = r15[r22] & UByte.MAX_VALUE;
            r0[r72] = (byte) r6[((r43 << 4) & 63) | (r152 >> 4)];
            int r23 = r5 + 3;
            r0[r5 + 2] = (byte) r6[(r152 << 2) & 63];
            r5 = r5 + 4;
            r0[r23] = kotlin.io.encoding.Base64.padSymbol;
        L20:
            if (r5 == r14) goto L23;
            r0 = Arrays.copyOf(r0, r5);
        L23:
            return new String(r0, 0, 0, r0.length);
        }
    }

    public static Decoder getDecoder() {
        return Decoder.f487c;
    }

    public static Encoder getEncoder() {
        return Encoder.f490c;
    }
}
