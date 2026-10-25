package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class Id3Decoder extends SimpleMetadataDecoder {
    public static final FramePredicate NO_FRAMES_PREDICATE = null;
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int r1, int r2, int r3, int r4, int r5);
    }

    public static final class Id3Header {
        private final int framesSize;
        private final boolean isUnsynchronized;
        private final int majorVersion;

        public Id3Header(int r1, boolean r2, int r3) {
            this.majorVersion = r1;
            this.isUnsynchronized = r2;
            this.framesSize = r3;
        }

        public static /* synthetic */ int access$000(Id3Header r0) {
            return r0.majorVersion;
        }

        public static /* synthetic */ int access$100(Id3Header r0) {
            return r0.framesSize;
        }

        public static /* synthetic */ boolean access$200(Id3Header r0) {
            return r0.isUnsynchronized;
        }
    }

    public static /* synthetic */ boolean $r8$lambda$OHbcD1hYkGZ4XWUc9y6URLEaOao(int r0, int r1, int r2, int r3, int r4) {
        return false;
    }

    static {
        NO_FRAMES_PREDICATE = new Id3Decoder$$ExternalSyntheticLambda0();
    }

    public Id3Decoder() {
        this(null);
    }

    public Id3Decoder(FramePredicate r1) {
        this.framePredicate = r1;
    }

    private static byte[] copyOfRangeIfValid(byte[] r0, int r1, int r2) {
        if (r2 > r1) goto L6;
        return Util.EMPTY_BYTE_ARRAY;
    L6:
        return Arrays.copyOfRange(r0, r1, r2);
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray r7, int r8, int r9) {
        int r0 = r7.readUnsignedByte();
        Charset r1 = getCharset(r0);
        int r82 = r8 - 1;
        byte[] r2 = new byte[r82];
        r7.readBytes(r2, 0, r82);
        if (r9 != 2) goto L8;
        String r72 = "image/" + Ascii.toLowerCase(new String(r2, 0, 3, StandardCharsets.ISO_8859_1));
        if ("image/jpg".equals(r72) == false) goto L7;
        r72 = "image/jpeg";
    L7:
        int r92 = 2;
    L12:
        int r3 = r2[r92 + 1] & UByte.MAX_VALUE;
        int r93 = r92 + 2;
        int r4 = indexOfTerminator(r2, r93, r0);
        return new ApicFrame(r72, new String(r2, r93, r4 - r93, r1), r3, copyOfRangeIfValid(r2, r4 + delimiterLength(r0), r82));
    L8:
        r92 = indexOfZeroByte(r2, 0);
        String r32 = Ascii.toLowerCase(new String(r2, 0, r92, StandardCharsets.ISO_8859_1));
        if (r32.indexOf(47) != (-1)) goto L11;
        r72 = "image/".concat(r32);
        goto L12
    L11:
        r72 = r32;
        goto L12
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray r2, int r3, String r4) {
        byte[] r0 = new byte[r3];
        r2.readBytes(r0, 0, r3);
        return new BinaryFrame(r4, r0);
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray r14, int r15, int r16, boolean r17, int r18, FramePredicate r19) {
        int r0 = r14.getPosition();
        int r1 = indexOfZeroByte(r14.getData(), r0);
        String r3 = new String(r14.getData(), r0, r1 - r0, StandardCharsets.ISO_8859_1);
        r14.setPosition(r1 + 1);
        int r4 = r14.readInt();
        int r5 = r14.readInt();
        long r12 = r14.readUnsignedInt();
        if (r12 != 4294967295L) goto L5;
        r12 = -1;
    L5:
        long r11 = r14.readUnsignedInt();
        if (r11 != 4294967295L) goto L8;
        long r8 = -1;
    L9:
        ArrayList r6 = new ArrayList();
        int r02 = r0 + r15;
    L11:
        if (r14.getPosition() >= r02) goto L16;
        Id3Frame r13 = decodeFrame(r16, r14, r17, r18, r19);
        if (r13 == null) goto L11;
        r6.add(r13);
        goto L11
    L16:
        return new ChapterFrame(r3, r4, r5, r12, r8, (Id3Frame[]) r6.toArray(new Id3Frame[0]));
    L8:
        r8 = r11;
        goto L9
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray r16, int r17, int r18, boolean r19, int r20, FramePredicate r21) {
        int r1 = r16.getPosition();
        int r2 = indexOfZeroByte(r16.getData(), r1);
        String r3 = new String(r16.getData(), r1, r2 - r1, StandardCharsets.ISO_8859_1);
        r16.setPosition(r2 + 1);
        int r22 = r16.readUnsignedByte();
        if ((r22 & 2) == 0) goto L5;
        boolean r5 = true;
    L7:
        if ((r22 & 1) == 0) goto L9;
        boolean r23 = true;
    L10:
        int r7 = r16.readUnsignedByte();
        String[] r8 = new String[r7];
        int r9 = 0;
    L11:
        if (r9 >= r7) goto L13;
        int r10 = r16.getPosition();
        int r11 = indexOfZeroByte(r16.getData(), r10);
        r8[r9] = new String(r16.getData(), r10, r11 - r10, StandardCharsets.ISO_8859_1);
        r16.setPosition(r11 + 1);
        r9 = r9 + 1;
        goto L11
    L13:
        ArrayList r4 = new ArrayList();
        int r12 = r1 + r17;
    L15:
        if (r16.getPosition() >= r12) goto L20;
        Id3Frame r122 = decodeFrame(r18, r16, r19, r20, r21);
        if (r122 == null) goto L15;
        r4.add(r122);
        goto L15
    L20:
        return new ChapterTocFrame(r3, r5, r23, r8, (Id3Frame[]) r4.toArray(new Id3Frame[0]));
    L9:
        r23 = false;
        goto L10
    L5:
        r5 = false;
        goto L7
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray r7, int r8) {
        if (r8 >= 4) goto L6;
        return null;
    L6:
        int r1 = r7.readUnsignedByte();
        Charset r2 = getCharset(r1);
        byte[] r4 = new byte[3];
        r7.readBytes(r4, 0, 3);
        String r6 = new String(r4, 0, 3);
        int r82 = r8 - 4;
        byte[] r0 = new byte[r82];
        r7.readBytes(r0, 0, r82);
        int r72 = indexOfTerminator(r0, 0, r1);
        String r83 = new String(r0, 0, r72, r2);
        int r73 = r72 + delimiterLength(r1);
        return new CommentFrame(r6, r83, decodeStringIfValid(r0, r73, indexOfTerminator(r0, r73, r1), r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2, types: [androidx.media3.extractor.metadata.id3.Id3Frame] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29, types: [androidx.media3.common.util.ParsableByteArray] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    private static Id3Frame decodeFrame(int r19, ParsableByteArray r20, boolean r21, int r22, FramePredicate r23) {
        int r3 = r19;
        int r2 = r20.readUnsignedByte();
        int r1 = r20.readUnsignedByte();
        int r4 = r20.readUnsignedByte();
        boolean r7 = false;
        if (r3 < 3) goto L5;
        int r5 = r20.readUnsignedByte();
    L7:
        if (r3 != 4) goto L12;
        int r0 = r20.readUnsignedIntToInt();
        if (r21 == true) goto L11;
        r0 = (((r0 >> 24) & 255) << 21) | (((r0 & 255) | (((r0 >> 8) & 255) << 7)) | (((r0 >> 16) & 255) << 14));
    L11:
        int r10 = r0;
        if (r3 < 3) goto L17;
        int r11 = r20.readUnsignedShort();
    L19:
        if (r2 != 0) goto L27;
        if (r1 != 0) goto L27;
        if (r4 != 0) goto L27;
        if (r5 != 0) goto L27;
        if (r10 != 0) goto L27;
        if (r11 != 0) goto L27;
        r20.setPosition(r20.limit());
        return null;
    L27:
        int r13 = r20.getPosition() + r10;
        if (r13 <= r20.limit()) goto L31;
        Log.w("Id3Decoder", "Frame size exceeds remaining tag data");
        r20.setPosition(r20.limit());
        return null;
    L31:
        if (r23 == null) goto L36;
        boolean r15 = r23.evaluate(r3, r2, r1, r4, r5);
        r3 = r3;
        ?? r12 = r2;
        int r24 = r1;
        if (r15 == true) goto L38;
        r20.setPosition(r13);
        return null;
    L38:
        if (r3 == 3) goto L40;
        if (r3 == 4) goto L54;
        boolean r8 = false;
        boolean r112 = false;
        boolean r152 = false;
        boolean r17 = false;
    L74:
        if (r7 == true) goto L194;
        if (r152 == true) goto L194;
        if (r112 == false) goto L79;
        r10 = r10 - 1;
        r20.skipBytes(1);
    L79:
        if (r8 == false) goto L81;
        r10 = r10 - 4;
        r20.skipBytes(4);
    L81:
        if (r17 == false) goto L196;
        r10 = removeUnsynchronization(r20, r10);
    L196:
        if (r12 != 84) goto L99;
        if (r24 != 88) goto L99;
        if (r4 != 88) goto L99;
        if (r3 == 2) goto L89;
        if (r5 != 88) goto L99;
    L89:
        Id3Frame r02 = decodeTxxxFrame(r20, r10);     // Catch: Throwable -> L92 Exception -> L94 OutOfMemoryError -> L97
    L90:
        int r82 = r12;
        int r9 = r24;
        int r113 = r5;
        r12 = r20;
        r24 = r10;
        Throwable r16 = null;
        r12 = r12;
    L91:
        int r102 = r4;
    L187:
        r12.setPosition(r13);
        ?? r122 = r02;
        e = r16;
    L191:
        if (r122 != 0) goto L193;
        Log.w("Id3Decoder", "Failed to decode frame: id=" + getFrameId(r3, r82, r9, r102, r113) + ", frameSize=" + r24, e);
    L193:
        return r122;
    L99:
        if (r12 != 84) goto L102;
        r02 = decodeTextInformationFrame(r20, r10, getFrameId(r3, r12, r24, r4, r5));     // Catch: Throwable -> L92 Exception -> L94 OutOfMemoryError -> L97
        goto L90
    L102:
        if (r12 != 87) goto L108;
        if (r24 != 88) goto L108;
        if (r4 != 88) goto L108;
        if (r3 == 2) goto L107;
        if (r5 != 88) goto L108;
    L107:
        r02 = decodeWxxxFrame(r20, r10);     // Catch: Throwable -> L92 Exception -> L94 OutOfMemoryError -> L97
    L108:
        if (r12 != 87) goto L111;
        r02 = decodeUrlLinkFrame(r20, r10, getFrameId(r3, r12, r24, r4, r5));     // Catch: Throwable -> L92 Exception -> L94 OutOfMemoryError -> L97
        goto L90
    L111:
        if (r12 != 80) goto L119;
        if (r24 != 82) goto L119;
        if (r4 != 73) goto L119;
        if (r5 != 86) goto L119;
        r02 = decodePrivFrame(r20, r10);     // Catch: Throwable -> L92 Exception -> L94 OutOfMemoryError -> L97
    L119:
        if (r12 == 71) goto L121;
    L128:
        r16 = null;
        if (r3 != 2) goto L134;
        if (r12 != 80) goto L145;
        if (r24 != 73) goto L145;
        if (r4 != 67) goto L145;
    L138:
        r02 = decodeApicFrame(r20, r10, r3);     // Catch: Throwable -> L92 Exception -> L140 OutOfMemoryError -> L142
    L139:
        r82 = r12;
        r9 = r24;
        r113 = r5;
        r12 = r20;
        r24 = r10;
        r12 = r12;
    L145:
        if (r12 != 67) goto L152;
        if (r24 != 79) goto L152;
        if (r4 != 77) goto L152;
        if (r5 == 77) goto L150;
        if (r3 != 2) goto L152;
    L150:
        r02 = decodeCommentFrame(r20, r10);     // Catch: Throwable -> L92 Exception -> L140 OutOfMemoryError -> L142
    L152:
        if (r12 == 67) goto L154;
    L166:
        r82 = r12;
        r9 = r24;
        r113 = r5;
        r24 = r10;
        r102 = r4;
        if (r82 != 67) goto L179;
        if (r9 != 84) goto L179;
        if (r102 != 79) goto L179;
        if (r113 != 67) goto L179;
        r3 = r19;
        ParsableByteArray r14 = r20;
        r02 = decodeChapterTOCFrame(r14, r24, r3, r21, r22, r23);     // Catch: Throwable -> L173 Exception -> L175 OutOfMemoryError -> L177
        r12 = r14;
    L202:
    L179:
        r3 = r19;
        ParsableByteArray r18 = r20;
        if (r82 == 77) goto L182;
    L186:
        r02 = decodeBinaryFrame(r18, r24, getFrameId(r3, r82, r9, r102, r113));     // Catch: Throwable -> L173 Exception -> L175 OutOfMemoryError -> L177
        r12 = r18;
        goto L202
    L182:
        if (r9 != 76) goto L186;
        if (r102 != 76) goto L186;
        if (r113 != 84) goto L186;
        r02 = decodeMlltFrame(r18, r24);     // Catch: Throwable -> L173 Exception -> L175 OutOfMemoryError -> L177
        r12 = r18;
    L175:
        e = e;
        ParsableByteArray r110 = r12;
    L190:
        r110.setPosition(r13);
        r122 = r16;
    L177:
        e = e;
        r110 = r12;
    L173:
        th = th;
        ParsableByteArray r111 = r12;
    L188:
        r111.setPosition(r13);
        throw th;
    L154:
        if (r24 != 72) goto L166;
        if (r4 != 65) goto L166;
        if (r5 != 80) goto L166;
        r82 = r12;
        r9 = r24;
        r113 = r5;
        r24 = r10;
        r102 = r4;
        r02 = decodeChapterFrame(r20, r24, r3, r21, r22, r23);     // Catch: Throwable -> L160 Exception -> L162 OutOfMemoryError -> L164
        r3 = r19;
        r12 = r20;
        goto L187
    L162:
        e = e;
    L163:
        r3 = r19;
        r110 = r20;
    L164:
        e = e;
    L160:
        th = th;
        r111 = r20;
        goto L188
    L134:
        if (r12 != 65) goto L145;
        if (r24 != 80) goto L145;
        if (r4 != 73) goto L145;
        if (r5 != 67) goto L145;
    L140:
        e = e;
    L141:
        r82 = r12;
        r9 = r24;
        r113 = r5;
        ParsableByteArray r114 = r20;
        r24 = r10;
    L96:
        r102 = r4;
        r110 = r114;
    L142:
        e = e;
        goto L141
    L121:
        if (r24 != 69) goto L128;
        if (r4 != 79) goto L128;
        if (r5 == 66) goto L126;
        if (r3 != 2) goto L128;
    L126:
        r02 = decodeGeobFrame(r20, r10);     // Catch: Throwable -> L92 Exception -> L94 OutOfMemoryError -> L97
    L92:
        th = th;
        r111 = r20;
    L94:
        e = e;
    L95:
        r82 = r12;
        r9 = r24;
        r113 = r5;
        r114 = r20;
        r24 = r10;
        r16 = null;
    L97:
        e = e;
    L194:
        Log.w("Id3Decoder", "Skipping unsupported compressed or encrypted frame");
        r20.setPosition(r13);
        return null;
    L54:
        if ((r11 & 64) == 0) goto L56;
        boolean r83 = true;
    L58:
        if ((r11 & 8) == 0) goto L60;
        boolean r153 = true;
    L62:
        if ((r11 & 4) == 0) goto L64;
        boolean r162 = true;
    L66:
        if ((r11 & 2) == 0) goto L68;
        r17 = true;
    L70:
        if ((r11 & 1) == 0) goto L72;
        r7 = true;
    L72:
        r112 = r83;
        r8 = r7;
        r7 = r153;
        r152 = r162;
        goto L74
    L68:
        r17 = false;
        goto L70
    L64:
        r162 = false;
        goto L66
    L60:
        r153 = false;
        goto L62
    L56:
        r83 = false;
        goto L58
    L40:
        if ((r11 & 128) == 0) goto L42;
        r8 = true;
    L44:
        if ((r11 & 64) == 0) goto L46;
        r152 = true;
    L48:
        if ((r11 & 32) == 0) goto L50;
        r112 = true;
    L51:
        r17 = false;
        r7 = r8;
        goto L74
    L50:
        r112 = false;
        goto L51
    L46:
        r152 = false;
        goto L48
    L42:
        r8 = false;
        goto L44
    L36:
        r24 = r1;
        r12 = r2;
        goto L38
    L17:
        r11 = 0;
        goto L19
    L12:
        if (r3 != 3) goto L14;
        r0 = r20.readUnsignedIntToInt();
        goto L11
    L14:
        r0 = r20.readUnsignedInt24();
        goto L11
    L5:
        r5 = 0;
        goto L7
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray r6, int r7) {
        int r0 = r6.readUnsignedByte();
        Charset r1 = getCharset(r0);
        int r72 = r7 - 1;
        byte[] r2 = new byte[r72];
        r6.readBytes(r2, 0, r72);
        int r62 = indexOfZeroByte(r2, 0);
        String r3 = MimeTypes.normalizeMimeType(new String(r2, 0, r62, StandardCharsets.ISO_8859_1));
        int r63 = r62 + 1;
        int r4 = indexOfTerminator(r2, r63, r0);
        String r64 = decodeStringIfValid(r2, r63, r4, r1);
        int r42 = r4 + delimiterLength(r0);
        int r5 = indexOfTerminator(r2, r42, r0);
        return new GeobFrame(r3, r64, decodeStringIfValid(r2, r42, r5, r1), copyOfRangeIfValid(r2, r5 + delimiterLength(r0), r72));
    }

    private static Id3Header decodeHeader(ParsableByteArray r8) {
        if (r8.bytesLeft() >= 10) goto L6;
        Log.w("Id3Decoder", "Data too short to be an ID3 tag");
        return null;
    L6:
        int r0 = r8.readUnsignedInt24();
        if (r0 == 4801587) goto L10;
        Log.w("Id3Decoder", "Unexpected first three bytes of ID3 tag header: 0x".concat(String.format("%06X", new Object[]{Integer.valueOf(r0)})));
        return null;
    L10:
        int r02 = r8.readUnsignedByte();
        boolean r1 = true;
        r8.skipBytes(1);
        int r4 = r8.readUnsignedByte();
        int r5 = r8.readSynchSafeInt();
        if (r02 != 2) goto L17;
        if ((r4 & 64) == 0) goto L28;
        Log.w("Id3Decoder", "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
        return null;
    L28:
        if (r02 < 4) goto L30;
    L32:
        r1 = false;
    L34:
        return new Id3Header(r02, r1, r5);
    L30:
        if ((r4 & 128) == 0) goto L32;
    L17:
        if (r02 == 3) goto L19;
        if (r02 == 4) goto L23;
        Log.w("Id3Decoder", "Skipped ID3 tag with unsupported majorVersion=" + r02);
        return null;
    L23:
        if ((r4 & 64) == 0) goto L26;
        int r2 = r8.readSynchSafeInt();
        r8.skipBytes(r2 - 4);
        r5 = r5 - r2;
    L26:
        if ((r4 & 16) == 0) goto L28;
        r5 = r5 - 10;
        goto L28
    L19:
        if ((r4 & 64) == 0) goto L28;
        int r22 = r8.readInt();
        r8.skipBytes(r22);
        r5 = r5 - (r22 + 4);
        goto L28
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray r10, int r11) {
        int r1 = r10.readUnsignedShort();
        int r2 = r10.readUnsignedInt24();
        int r3 = r10.readUnsignedInt24();
        int r0 = r10.readUnsignedByte();
        int r4 = r10.readUnsignedByte();
        ParsableBitArray r5 = new ParsableBitArray();
        r5.reset(r10);
        int r112 = ((r11 - 10) * 8) / (r0 + r4);
        int[] r42 = new int[r112];
        int[] r52 = new int[r112];
        int r7 = 0;
    L3:
        if (r7 >= r112) goto L6;
        int r8 = r5.readBits(r0);
        int r9 = r5.readBits(r4);
        r42[r7] = r8;
        r52[r7] = r9;
        r7 = r7 + 1;
        goto L3
    L6:
        return new MlltFrame(r1, r2, r3, r42, r52);
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray r4, int r5) {
        byte[] r0 = new byte[r5];
        r4.readBytes(r0, 0, r5);
        int r42 = indexOfZeroByte(r0, 0);
        return new PrivFrame(new String(r0, 0, r42, StandardCharsets.ISO_8859_1), copyOfRangeIfValid(r0, r42 + 1, r5));
    }

    private static String decodeStringIfValid(byte[] r1, int r2, int r3, Charset r4) {
        if (r3 > r2) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        if (r3 <= r1.length) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return new String(r1, r2, r3 - r2, r4);
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray r4, int r5, String r6) {
        if (r5 >= 1) goto L5;
        return null;
    L5:
        int r2 = r4.readUnsignedByte();
        int r52 = r5 - 1;
        byte[] r1 = new byte[r52];
        r4.readBytes(r1, 0, r52);
        return new TextInformationFrame(r6, null, decodeTextInformationFrameValues(r1, r2, 0));
    }

    private static ImmutableList<String> decodeTextInformationFrameValues(byte[] r6, int r7, int r8) {
        if (r8 >= r6.length) goto L5;
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r2 = indexOfTerminator(r6, r8, r7);
    L7:
        if (r8 >= r2) goto L9;
        r0.add(new String(r6, r8, r2 - r8, getCharset(r7)));
        r8 = delimiterLength(r7) + r2;
        r2 = indexOfTerminator(r6, r8, r7);
        goto L7
    L9:
        ImmutableList<String> r62 = r0.build();
        if (r62.isEmpty() == true) goto L12;
        return r62;
    L12:
        return ImmutableList.of(_UrlKt.FRAGMENT_ENCODE_SET);
    L5:
        return ImmutableList.of(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray r4, int r5) {
        if (r5 >= 1) goto L6;
        return null;
    L6:
        int r1 = r4.readUnsignedByte();
        int r52 = r5 - 1;
        byte[] r0 = new byte[r52];
        r4.readBytes(r0, 0, r52);
        int r42 = indexOfTerminator(r0, 0, r1);
        return new TextInformationFrame("TXXX", new String(r0, 0, r42, getCharset(r1)), decodeTextInformationFrameValues(r0, r1, r42 + delimiterLength(r1)));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray r3, int r4, String r5) {
        byte[] r0 = new byte[r4];
        r3.readBytes(r0, 0, r4);
        return new UrlLinkFrame(r5, null, new String(r0, 0, indexOfZeroByte(r0, 0), StandardCharsets.ISO_8859_1));
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray r4, int r5) {
        if (r5 >= 1) goto L6;
        return null;
    L6:
        int r1 = r4.readUnsignedByte();
        int r52 = r5 - 1;
        byte[] r0 = new byte[r52];
        r4.readBytes(r0, 0, r52);
        int r42 = indexOfTerminator(r0, 0, r1);
        String r53 = new String(r0, 0, r42, getCharset(r1));
        int r43 = r42 + delimiterLength(r1);
        return new UrlLinkFrame("WXXX", r53, decodeStringIfValid(r0, r43, indexOfZeroByte(r0, r43), StandardCharsets.ISO_8859_1));
    }

    private static int delimiterLength(int r1) {
        if (r1 != 0) goto L4;
        return 1;
    L4:
        if (r1 == 3) goto L10;
        return 2;
    L10:
        return 1;
    }

    private static Charset getCharset(int r1) {
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L11;
        return StandardCharsets.ISO_8859_1;
    L11:
        return StandardCharsets.UTF_8;
    L13:
        return StandardCharsets.UTF_16BE;
    L15:
        return StandardCharsets.UTF_16;
    }

    private static String getFrameId(int r1, int r2, int r3, int r4, int r5) {
        if (r1 != 2) goto L7;
        return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r4)});
    L7:
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r4), Integer.valueOf(r5)});
    }

    private static int indexOfTerminator(byte[] r2, int r3, int r4) {
        int r0 = indexOfZeroByte(r2, r3);
        if (r4 != 0) goto L5;
    L17:
        return r0;
    L5:
        if (r4 == 3) goto L17;
    L8:
        if (r0 >= (r2.length - 1)) goto L16;
        if (((r0 - r3) % 2) != 0) goto L14;
        if (r2[r0 + 1] != 0) goto L14;
        return r0;
    L14:
        r0 = indexOfZeroByte(r2, r0 + 1);
        goto L8
    L16:
        return r2.length;
    }

    private static int indexOfZeroByte(byte[] r1, int r2) {
    L3:
        if (r2 >= r1.length) goto L9;
        if (r1[r2] == 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r2;
    L9:
        return r1.length;
    }

    private static int removeUnsynchronization(ParsableByteArray r5, int r6) {
        byte[] r0 = r5.getData();
        int r52 = r5.getPosition();
        int r1 = r52;
    L3:
        int r2 = r1 + 1;
        if (r2 >= (r52 + r6)) goto L11;
        if ((r0[r1] & UByte.MAX_VALUE) != 255) goto L10;
        if (r0[r2] != 0) goto L10;
        int r3 = r1 - r52;
        System.arraycopy(r0, r1 + 2, r0, r2, (r6 - r3) - 2);
        r6 = r6 - 1;
    L10:
        r1 = r2;
        goto L3
    L11:
        return r6;
    }

    private static boolean validateFrames(ParsableByteArray r18, int r19, int r20, boolean r21) {
        int r2 = r18.getPosition();
    L63:
        boolean r4 = true;
        if (r18.bytesLeft() < r20) goto L59;
        if (r19 < 3) goto L10;
        int r7 = r18.readInt();     // Catch: Throwable -> L8
        long r8 = r18.readUnsignedInt();     // Catch: Throwable -> L8
        int r10 = r18.readUnsignedShort();     // Catch: Throwable -> L8
    L13:
        if (r7 != 0) goto L20;
        if (r8 != 0) goto L20;
        if (r10 != 0) goto L20;
        r18.setPosition(r2);
        return true;
    L20:
        if (r19 != 4) goto L27;
        if (r21 == true) goto L27;
        if ((8421504 & r8) != 0) goto L24;
        r8 = (((r8 >> 24) & 255) << 21) | (((r8 & 255) | (((r8 >> 8) & 255) << 7)) | (((r8 >> 16) & 255) << 14));
        goto L27
    L24:
        r18.setPosition(r2);
        return false;
    L27:
        if (r19 == 4) goto L29;
        if (r19 == 3) goto L38;
        int r3 = 0;
        r4 = false;
    L45:
        if (r4 == false) goto L48;
        r3 = r3 + 4;
    L48:
        if (r8 < r3) goto L49;
        if (r18.bytesLeft() < r8) goto L54;
        r18.skipBytes((int) r8);     // Catch: Throwable -> L8
        goto L63
    L54:
        r18.setPosition(r2);
        return false;
    L49:
        r18.setPosition(r2);
        return false;
    L38:
        if ((r10 & 32) == 0) goto L40;
        r3 = 1;
    L42:
        if ((r10 & 128) != 0) goto L45;
    L35:
        r4 = false;
        goto L45
    L40:
        r3 = 0;
        goto L42
    L29:
        if ((r10 & 64) == 0) goto L31;
        r3 = 1;
    L33:
        if ((r10 & 1) == 0) goto L35;
    L31:
        r3 = 0;
        goto L33
    L10:
        r7 = r18.readUnsignedInt24();     // Catch: Throwable -> L8
        r8 = r18.readUnsignedInt24();     // Catch: Throwable -> L8
        r10 = 0;
        goto L13
    L59:
        r18.setPosition(r2);
        return true;
    L8:
        th = move-exception;
        r18.setPosition(r2);
        throw th;
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer r1, ByteBuffer r2) {
        return decode(r2.array(), r2.limit());
    }

    public Metadata decode(byte[] r7, int r8) {
        ArrayList r0 = new ArrayList();
        ParsableByteArray r1 = new ParsableByteArray(r7, r8);
        Id3Header r72 = decodeHeader(r1);
        if (r72 != null) goto L5;
        return null;
    L5:
        int r2 = r1.getPosition();
        if (Id3Header.access$000(r72) != 2) goto L8;
        int r3 = 6;
    L9:
        int r4 = Id3Header.access$100(r72);
        if (Id3Header.access$200(r72) == false) goto L12;
        r4 = removeUnsynchronization(r1, Id3Header.access$100(r72));
    L12:
        r1.setLimit(r2 + r4);
        boolean r42 = false;
        if (validateFrames(r1, Id3Header.access$000(r72), r3, false) == true) goto L22;
        if (Id3Header.access$000(r72) == 4) goto L17;
    L19:
        Log.w("Id3Decoder", "Failed to validate ID3 tag with majorVersion=" + Id3Header.access$000(r72));
        return null;
    L17:
        if (validateFrames(r1, 4, r3, true) == false) goto L19;
        r42 = true;
    L22:
        if (r1.bytesLeft() < r3) goto L27;
        Id3Frame r82 = decodeFrame(Id3Header.access$000(r72), r1, r42, r3, this.framePredicate);
        if (r82 == null) goto L22;
        r0.add(r82);
        goto L22
    L27:
        return new Metadata(r0);
    L8:
        r3 = 10;
        goto L9
    }
}
