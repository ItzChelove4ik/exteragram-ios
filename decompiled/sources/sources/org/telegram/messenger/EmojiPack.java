package org.telegram.messenger;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.UByte;
import kotlin.UShort;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class EmojiPack {
    private static final String ASSET_NAME = "emoji.pack";
    private static final int DEFLATE = 0;
    private static final int ENTRY_SIZE = 20;
    private static final int FULL_IMAGE = 3;
    private static final int HEADER_SIZE = 32;
    private static final int INDEX_WEBP = 1;
    private static final int MAP_REFERENCE = 4;
    private static final int NO_MASK = 65535;
    private static final int PIXELS = 4096;
    private static final int RGBA_WEBP = 2;
    private static final int ROOT_CACHE_SIZE = 8;
    private static final int SIDE = 64;
    private long age;
    private final MappedByteBuffer buffer;
    private final int count;
    private final byte[] decoded;
    private final int emojiCount;
    private final int[] emojiPixels;
    private byte[] encoded;
    private final byte[] indices;
    private final Inflater inflater;
    private final BitmapFactory.Options options;
    private final int[] palette;
    private final byte[] paletteBytes;
    private final int[] pixels;
    private final long[] rootAges;
    private final int[] rootIds;
    private final byte[][] rootMaps;

    public static final class Holder {
        static final EmojiPack INSTANCE = null;

        static {
            INSTANCE = open();
        }

        private Holder() {
        }

        private static EmojiPack open() {
            return new EmojiPack(null);
        L4:
            e = move-exception;
            Constants$$ExternalSyntheticBUOutline0.m("Unable to open emoji.pack", e);
            return null;
        }
    }

    private EmojiPack() {
        this.inflater = new Inflater(true);
        BitmapFactory.Options r1 = new BitmapFactory.Options();
        this.options = r1;
        this.encoded = new byte[4096];
        this.decoded = new byte[4608];
        this.indices = new byte[4096];
        this.pixels = new int[4096];
        this.emojiPixels = new int[4096];
        this.palette = new int[256];
        this.paletteBytes = new byte[1024];
        int[] r4 = new int[8];
        this.rootIds = r4;
        this.rootAges = new long[8];
        this.rootMaps = new byte[8][];
        AssetFileDescriptor r5 = ApplicationLoader.applicationContext.getAssets().openFd(ASSET_NAME);
        FileInputStream r6 = r5.createInputStream();     // Catch: Throwable -> L40
        long r11 = r5.getLength();     // Catch: Throwable -> L42
        if (r11 < 32) goto L45;
        if (r11 > 2147483647L) goto L45;
        MappedByteBuffer r0 = r6.getChannel().map(FileChannel.MapMode.READ_ONLY, r5.getStartOffset(), r11);     // Catch: Throwable -> L42
        this.buffer = r0;     // Catch: Throwable -> L42
        r6.close();     // Catch: Throwable -> L40
        r5.close();
        r0.order(ByteOrder.LITTLE_ENDIAN);
        if (r0.getInt(0) == 860573765) goto L13;
    L38:
        Model$$ExternalSyntheticBUOutline0.m("Unsupported or damaged EPK3 header");
        throw null;
    L13:
        if (u16(4) == 2) goto L17;
        if (u16(4) != 3) goto L38;
    L17:
        if (u16(6) != 20) goto L38;
        if (r0.getInt(8) != 64) goto L38;
        if (r0.getInt(12) != 64) goto L38;
        if (r0.getInt(24) != r0.capacity()) goto L38;
        if (r0.getInt(28) != 0) goto L38;
        int r3 = r0.getInt(16);
        this.count = r3;
        int r62 = r0.getInt(20);
        this.emojiCount = r62;
        if (r3 >= 1) goto L29;
    L36:
        Model$$ExternalSyntheticBUOutline0.m("Invalid EPK3 record count");
        throw null;
    L29:
        if (r3 > 65535) goto L36;
        if (r62 < 0) goto L36;
        if (r62 > r3) goto L36;
        if (((r3 * 20) + 32) > r0.capacity()) goto L36;
        validateMetadata();
        Arrays.fill(r4, -1);
        r1.inScaled = false;
        r1.inPreferredConfig = Bitmap.Config.ARGB_8888;
        r1.inPremultiplied = true;
        return;
    L45:
        throw new IOException("Invalid emoji pack length: " + r11);     // Catch: Throwable -> L42
    L42:
        th = move-exception;
        if (r6 == null) goto L64;
        r6.close();     // Catch: Throwable -> L49
    L65:
        throw th;     // Catch: Throwable -> L40
    L49:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L40
        throw th;     // Catch: Throwable -> L40
    L64:
        throw th;     // Catch: Throwable -> L40
    L40:
        th = move-exception;
        if (r5 == null) goto L66;
        r5.close();     // Catch: Throwable -> L55
        throw th;
    L55:
        th = move-exception;
        th.addSuppressed(th);
        throw th;
    L66:
        throw th;
    }

    public /* synthetic */ EmojiPack(EmojiPackIA r1) {
        this();
    }

    private static IllegalStateException damaged(String r3) {
        return new IllegalStateException("Damaged emoji.pack: " + r3);
    }

    private Bitmap decode(int r6) {
        int r0 = entry(r6);
        int r1 = this.buffer.getInt(r0 + 4);
        int r2 = u16(r0 + 8);
        int r02 = u8(r0 + 16);
        if (r02 != 2) goto L5;
    L9:
        readBytes(r1, r2);
        if (r02 != 2) goto L14;
        return decodeWebp(20, r2);
    L14:
        return decodeBitmap(20, r2);
    L5:
        if (r02 == 3) goto L9;
        decodePixels(r6, this.pixels);
        return Bitmap.createBitmap(this.pixels, 64, 64, Bitmap.Config.ARGB_8888);
    }

    private Bitmap decodeBitmap(int r2, int r3) {
        Bitmap r1 = BitmapFactory.decodeByteArray(this.encoded, r2, r3, this.options);
        if (r1 == null) goto L12;
        if (r1.getWidth() == 64) goto L7;
    L9:
        r1.recycle();
        throw damaged("Unexpected bitmap dimensions");
    L7:
        if (r1.getHeight() != 64) goto L9;
        return r1;
    L12:
        throw damaged("Bitmap decoder rejected image");
    }

    private void decodeIndices(int r28, byte[] r29) {
        int r2 = entry(r28);
        int r3 = u8(r2 + 17);
        if (r3 == 0) goto L11;
        int r9 = u16(r2 + 10);
        byte[] r10 = rootMap(r9);
        int r92 = u16(entry(r9) + 14);
        if (r3 == 3) goto L10;
        if (r3 == 5) goto L10;
        if (r3 == 6) goto L10;
        byte[] r11 = null;
        int r7 = 0;
    L12:
        int r12 = r2 + 19;
        int r13 = (u8(r12) >>> 1) & 7;
        int r122 = (u8(r12) >>> 4) & 7;
        if (r3 != 4) goto L17;
        int r8 = 0;
    L15:
        if (r8 >= 4096) goto L123;
        r29[r8] = r10[sourceIndex(r8, r13)];
        r8 = r8 + 1;
        goto L15
    L123:
        return;
    L17:
        int r15 = r92 + r7;
        int r16 = paletteLength(r2);
        int r4 = this.buffer.getInt(r2 + 4) + r16;
        int r82 = u16(r2 + 8) - r16;
        if (r3 == 5) goto L22;
        if (r3 == 6) goto L22;
        int r6 = 32;
    L28:
        readBytes(r4, r82);
        if (u8(r2 + 16) != 0) goto L31;
        inflate(r82, r15 + 4096);
        byte[] r42 = this.decoded;
        System.arraycopy(r42, r15, r29, 0, 4096);
        int r18 = r2;
        byte[] r22 = r42;
        int r43 = 0;
    L43:
        if (r3 != 1) goto L45;
    L80:
        int r83 = 0;
    L81:
        if (r83 >= 4096) goto L91;
        int r44 = r10[sourceIndex(r83, r13)] & UByte.MAX_VALUE;
        if (r44 >= r92) goto L90;
        int r45 = r22[r44] & UByte.MAX_VALUE;
        if (r3 != 1) goto L87;
        byte r46 = (byte) (r45 ^ (r29[r83] & UByte.MAX_VALUE));
    L88:
        r29[r83] = r46;
        r83 = r83 + 1;
        goto L81
    L87:
        r46 = (byte) ((r29[r83] & UByte.MAX_VALUE) + r45);
        goto L88
    L90:
        throw damaged("Root index out of range");
    L91:
        int r0 = u16(r18 + 14);
        if (r0 >= 256) goto L100;
        int r23 = r29.length;
        int r84 = 0;
    L94:
        if (r84 >= r23) goto L124;
        if ((r29[r84] & UByte.MAX_VALUE) >= r0) goto L99;
        r84 = r84 + 1;
        goto L94
    L99:
        throw damaged("Decoded index out of range");
    L124:
        return;
    L100:
        return;
    L45:
        if (r3 == 2) goto L80;
        if (r3 != 3) goto L50;
    L54:
        int r5 = 6;
    L55:
        int r85 = r43;
    L57:
        if (r85 >= 4096) goto L91;
        if (r3 != r5) goto L64;
        if ((r85 >>> 6) >= r6) goto L62;
    L61:
        int r14 = 1;
    L66:
        if (r14 == 0) goto L68;
        byte r152 = r10[sourceIndex(r85, r13)];
    L69:
        int r153 = r152 & UByte.MAX_VALUE;
        if (r14 == 0) goto L72;
        int r47 = r92;
    L73:
        if (r153 >= r47) goto L79;
        if (r14 == 0) goto L76;
        int r48 = 0;
    L77:
        r29[r85] = (byte) ((r29[r85] & UByte.MAX_VALUE) + (r22[r48 + r153] & UByte.MAX_VALUE));
        r85 = r85 + 1;
        r43 = 0;
        goto L57
    L76:
        r48 = r92;
        goto L77
    L79:
        throw damaged("Half root index out of range");
    L72:
        r47 = r7;
        goto L73
    L68:
        r152 = r11[sourceIndex(r85, r122)];
    L62:
        r14 = r43;
        goto L66
    L64:
        if ((r85 & 63) >= r6) goto L62;
    L50:
        if (r3 == 5) goto L54;
        r5 = 6;
        if (r3 != 6) goto L91;
    L31:
        r43 = 0;
        if (r82 <= r15) goto L104;
        r18 = r2;
        System.arraycopy(this.encoded, 20, this.decoded, 0, r15);
        Bitmap r19 = decodeWebp(r15 + 20, r82 - r15);
        r19.getPixels(this.pixels, 0, 64, 0, 0, 64, 64);     // Catch: Throwable -> L39
        int r86 = 0;
    L36:
        if (r86 >= 4096) goto L41;
        r29[r86] = (byte) this.pixels[r86];     // Catch: Throwable -> L39
        r86 = r86 + 1;
        goto L36
    L41:
        r19.recycle();
        r22 = this.decoded;
    L39:
        th = move-exception;
        r19.recycle();
        throw th;
    L104:
        throw damaged("Truncated index WebP");
    L22:
        if (r82 < 2) goto L108;
        int r17 = r4 + 1;
        int r49 = u8(r4);
        r82 = r82 - 1;
        if (r49 < 1) goto L106;
        if (r49 >= 64) goto L106;
        r6 = r49;
        r4 = r17;
    L106:
        throw damaged("Invalid split position");
    L108:
        throw damaged("Truncated split record");
    L10:
        int r72 = u16(r2 + 12);
        r11 = rootMap(r72);
        r7 = u16(entry(r72) + 14);
        goto L12
    L11:
        r10 = null;
        r11 = null;
        r7 = 0;
        r92 = 0;
        goto L12
    }

    private void decodePixels(int r15, int[] r16) {
        int r0 = entry(r15);
        int r1 = this.buffer.getInt(r0 + 4);
        int r2 = u16(r0 + 8);
        int r3 = u8(r0 + 16);
        int r4 = 0;
        if (r3 != 2) goto L5;
    L58:
        readBytes(r1, r2);
        this.options.inPremultiplied = false;
        Bitmap r12 = null;
        if (r3 != 2) goto L64;
        Bitmap r02 = decodeWebp(20, r2);     // Catch: Throwable -> L62
    L61:
        Bitmap r6 = r02;
        r6.getPixels(r16, 0, 64, 0, 0, 64, 64);     // Catch: Throwable -> L70
        this.options.inPremultiplied = true;
        r6.recycle();
        return;
    L70:
        th = th;
        r12 = r6;
    L72:
        this.options.inPremultiplied = true;
        if (r12 == null) goto L75;
        r12.recycle();
    L75:
        throw th;
    L64:
        r02 = decodeBitmap(20, r2);     // Catch: Throwable -> L62
    L62:
        th = th;
        goto L72
    L5:
        if (r3 == 3) goto L58;
        decodeIndices(r15, this.indices);
        int r22 = u16(r0 + 14);
        int r32 = r0 + 18;
        int r8 = u8(r32) & 127;
        int r9 = r22 * r8;
        if ((u8(r32) & 128) == 0) goto L10;
        boolean r33 = true;
    L11:
        if (r33 == false) goto L18;
        int r10 = paletteLength(r0) - 2;
        readBytes(r1 + 2, r10);
        if ((u8(r0 + 19) & 1) == 0) goto L15;
        int r62 = ((r9 * 7) + 7) / 8;
    L16:
        inflate(r10, r62);
        goto L18
    L15:
        r62 = r9;
    L18:
        if ((u8(r0 + 19) & 1) == 0) goto L32;
        int r03 = 0;
    L20:
        if (r03 >= r9) goto L37;
        int r102 = r03 * 7;
        int r11 = r102 & 7;
        int r103 = r102 >>> 3;
        if (r33 == false) goto L24;
        int r122 = this.decoded[r103] & UByte.MAX_VALUE;
    L25:
        int r123 = r122 >>> r11;
        if (r11 <= 1) goto L31;
        if (r33 == false) goto L29;
        int r104 = this.decoded[r103 + 1] & UByte.MAX_VALUE;
    L30:
        r123 = r123 | (r104 << (8 - r11));
        goto L31
    L29:
        r104 = u8((r103 + r1) + 1);
    L31:
        int r105 = r123 & 127;
        this.paletteBytes[r03] = (byte) ((r105 >>> 6) | (r105 << 1));
        r03 = r03 + 1;
        goto L20
    L24:
        r122 = u8(r1 + r103);
    L37:
        int r04 = 0;
        int r13 = 0;
    L38:
        if (r04 >= r22) goto L51;
        if (r8 != 0) goto L42;
        this.palette[r04] = (65793 * r04) | (-16777216);
    L49:
        r04 = r04 + 1;
        goto L38
    L42:
        if (r8 != 1) goto L44;
        this.palette[r04] = ((this.paletteBytes[r13] & UByte.MAX_VALUE) * 65793) | (-16777216);
        r13 = r13 + 1;
        goto L49
    L44:
        byte[] r34 = this.paletteBytes;
        int r92 = r34[r13] & UByte.MAX_VALUE;
        int r7 = r34[r13 + 1] & UByte.MAX_VALUE;
        int r112 = r13 + 3;
        int r106 = r34[r13 + 2] & UByte.MAX_VALUE;
        if (r8 != 4) goto L47;
        r13 = r13 + 4;
        int r35 = r34[r112] & UByte.MAX_VALUE;
    L48:
        this.palette[r04] = (((r35 << 24) | (r92 << 16)) | (r7 << 8)) | r106;
        goto L49
    L47:
        r35 = 255;
        r13 = r112;
    L51:
        if (r4 >= 4096) goto L57;
        int r05 = this.indices[r4] & UByte.MAX_VALUE;
        if (r05 >= r22) goto L56;
        r16[r4] = this.palette[r05];
        r4 = r4 + 1;
        goto L51
    L56:
        throw damaged("Palette index out of range");
    L57:
        return;
    L32:
        if (r33 == false) goto L34;
        System.arraycopy(this.decoded, 0, this.paletteBytes, 0, r9);
        goto L37
    L34:
        int r06 = 0;
    L35:
        if (r06 >= r9) goto L37;
        this.paletteBytes[r06] = this.buffer.get(r1 + r06);
        r06 = r06 + 1;
        goto L35
    L10:
        r33 = false;
        goto L11
    }

    private Bitmap decodeWebp(int r5, int r6) {
        int r0 = r5 - 20;
        int r1 = r6 & 1;
        putInt(r0, 1179011410);
        putInt(r5 - 16, (r6 + 12) + r1);
        putInt(r5 - 12, 1346520407);
        putInt(r5 - 8, 1278758998);
        putInt(r5 - 4, r6);
        if (r1 == 0) goto L6;
        this.encoded[r5 + r6] = 0;
    L6:
        return decodeBitmap(r0, (r6 + 20) + r1);
    }

    private static int entry(int r0) {
        return (r0 * 20) + 32;
    }

    private int find(int r3, int r4, int r5) {
        int r52 = r5 - 1;
    L3:
        if (r4 > r52) goto L10;
        int r0 = (r4 + r52) >>> 1;
        int r1 = u16(entry(r0));
        if (r1 < r3) goto L6;
        if (r1 <= r3) goto L9;
        r52 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r4 = r0 + 1;
        goto L3
    L10:
        return -1;
    }

    private int findEmoji(int r2, int r3) {
        if (r2 >= 0) goto L4;
        return -1;
    L4:
        if (r2 > 15) goto L13;
        if (r3 >= 0) goto L7;
        return -1;
    L7:
        if (r3 < 4096) goto L10;
        return -1;
    L10:
        return find((r2 * 4096) + r3, 0, this.emojiCount);
    L13:
        return -1;
    }

    public static EmojiPack getInstance() {
        return Holder.INSTANCE;
    }

    private void inflate(int r5, int r6) {
        this.inflater.reset();
        this.inflater.setInput(this.encoded, 20, r5);
        int r0 = 0;
    L3:
        Inflater r1 = this.inflater;
        if (r0 >= r6) goto L11;
        int r12 = r1.inflate(this.decoded, r0, r6 - r0);     // Catch: DataFormatException -> L24
        if (r12 == 0) goto L9;
        r0 = r0 + r12;     // Catch: DataFormatException -> L24
        goto L3
    L9:
        throw damaged("Truncated DEFLATE stream");     // Catch: DataFormatException -> L24
    L24:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Damaged EPK3 DEFLATE stream", e);
        return;
    L11:
        if (r1.finished() == true) goto L20;
        if (this.inflater.inflate(this.indices, 0, 1) != 0) goto L18;
        if (this.inflater.finished() == true) goto L20;
    L18:
        throw damaged("Oversized DEFLATE stream");     // Catch: DataFormatException -> L24
    L20:
        if (this.inflater.getRemaining() != 0) goto L23;
        return;
    L23:
        throw damaged("Trailing DEFLATE bytes");     // Catch: DataFormatException -> L24
    }

    private int paletteLength(int r4) {
        int r0 = r4 + 18;
        if ((u8(r0) & 128) == 0) goto L15;
        int r02 = r4 + 8;
        if (u16(r02) < 3) goto L14;
        int r42 = u16(this.buffer.getInt(r4 + 4));
        if (r42 == 0) goto L12;
        int r43 = r42 + 2;
        if (r43 > u16(r02)) goto L12;
        return r43;
    L12:
        throw damaged("Invalid compressed palette");
    L14:
        throw damaged("Truncated compressed palette");
    L15:
        int r1 = u16(r4 + 14) * (u8(r0) & 127);
        if ((u8(r4 + 19) & 1) != 0) goto L18;
        return r1;
    L18:
        return ((r1 * 7) + 7) / 8;
    }

    private void putInt(int r3, int r4) {
        byte[] r2 = this.encoded;
        r2[r3] = (byte) r4;
        r2[r3 + 1] = (byte) (r4 >>> 8);
        r2[r3 + 2] = (byte) (r4 >>> 16);
        r2[r3 + 3] = (byte) (r4 >>> 24);
    }

    private void readBytes(int r4, int r5) {
        byte[] r0 = this.encoded;
        int r2 = r5 + 21;
        if (r0.length >= r2) goto L8;
        int r02 = r0.length;
    L5:
        if (r02 >= r2) goto L7;
        r02 = r02 << 1;
        goto L5
    L7:
        this.encoded = new byte[r02];
    L8:
        this.buffer.position(r4);
        this.buffer.get(this.encoded, 20, r5);
    }

    private byte[] rootMap(int r8) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= 8) goto L13;
        int r2 = this.rootIds[r0];
        long[] r5 = this.rootAges;
        if (r2 == r8) goto L7;
        if (r5[r0] >= r5[r1]) goto L12;
        r1 = r0;
    L12:
        r0 = r0 + 1;
        goto L4
    L7:
        long r12 = this.age + 1;
        this.age = r12;
        r5[r0] = r12;
        return this.rootMaps[r0];
    L13:
        byte[][] r02 = this.rootMaps;
        if (r02[r1] != null) goto L16;
        r02[r1] = new byte[4096];
    L16:
        this.rootIds[r1] = -1;
        decodeIndices(r8, r02[r1]);
        this.rootIds[r1] = r8;
        long[] r82 = this.rootAges;
        long r52 = this.age + 1;
        this.age = r52;
        r82[r1] = r52;
        return this.rootMaps[r1];
    }

    private static int sourceIndex(int r2, int r3) {
        int r0 = r2 & 63;
        int r22 = r2 >>> 6;
        if ((r3 & 1) == 0) goto L6;
        r0 = 63 - r0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r22 = 63 - r22;
    L9:
        if ((r3 & 4) == 0) goto L13;
        return r22 | (r0 << 6);
    L13:
        return (r22 << 6) | r0;
    }

    private int u16(int r1) {
        return this.buffer.getShort(r1) & UShort.MAX_VALUE;
    }

    private int u8(int r1) {
        return this.buffer.get(r1) & UByte.MAX_VALUE;
    }

    private void validateMetadata() {
        int r1 = (this.count * 20) + 32;
        int r3 = 0;
    L4:
        if (r3 >= this.count) goto L145;
        int r4 = entry(r3);
        if (r3 != 0) goto L8;
    L14:
        int r5 = u16(r4 + 2);
        int r6 = this.emojiCount;
        if (r3 < r6) goto L17;
        if (r5 == 65535) goto L17;
    L22:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("Invalid mask reference at record ", r3);
        return;
    L17:
        if (r3 >= r6) goto L24;
        if (r5 == 65535) goto L24;
        if (find(r5, r6, this.count) < 0) goto L22;
    L24:
        int r52 = this.buffer.getInt(r4 + 4);
        int r62 = u16(r4 + 8);
        int r8 = u16(r4 + 10);
        int r9 = u16(r4 + 12);
        int r10 = u16(r4 + 14);
        int r11 = u8(r4 + 16);
        int r12 = u8(r4 + 17);
        int r13 = r4 + 18;
        int r14 = u8(r13) & 127;
        if (r52 < r1) goto L108;
        if (r62 == 0) goto L108;
        int r15 = r3;
        if ((r52 + r62) > this.buffer.capacity()) goto L109;
        if (r11 > 4) goto L109;
        if (r12 > 6) goto L109;
        if (r11 == 2) goto L96;
        if (r11 == 3) goto L96;
        if (r10 < 1) goto L94;
        if (r10 > 256) goto L94;
        if (r14 == 2) goto L94;
        if (r14 > 4) goto L94;
        if (r14 != 0) goto L46;
        if (r10 != 256) goto L94;
    L46:
        if (r11 != 4) goto L51;
        if (paletteLength(r4) != r62) goto L94;
    L52:
        if (r14 == 0) goto L54;
    L57:
        if (r11 != 4) goto L59;
        boolean r42 = true;
    L60:
        if (r12 != 4) goto L62;
        boolean r53 = true;
    L63:
        if (r42 != r53) goto L92;
        if (r11 != 4) goto L72;
        int r63 = r8;
        if (r63 >= this.count) goto L70;
        if (r10 != u16(entry(r63) + 14)) goto L70;
    L73:
        if (r12 == 0) goto L75;
        validateRoot(r63, r15);
        if (r12 != 3) goto L83;
    L91:
        validateRoot(r9, r15);
    L105:
        r3 = r15 + 1;
        goto L4
    L83:
        if (r12 == 5) goto L91;
        if (r12 == 6) goto L91;
        if (r9 == 65535) goto L105;
        Model$$ExternalSyntheticBUOutline0.m("Unexpected second root");
        return;
    L75:
        if (r63 != 65535) goto L78;
        if (r9 == 65535) goto L105;
    L78:
        Model$$ExternalSyntheticBUOutline0.m("Unexpected root reference");
        return;
    L70:
        Model$$ExternalSyntheticBUOutline0.m("Map reference palette size mismatch");
        return;
    L72:
        r63 = r8;
        goto L73
    L92:
        Model$$ExternalSyntheticBUOutline0.m("Invalid map reference encoding");
        return;
    L62:
        r53 = false;
        goto L63
    L59:
        r42 = false;
        goto L60
    L54:
        if ((u8(r4 + 19) & 1) != 0) goto L94;
        if ((u8(r13) & 128) != 0) goto L94;
    L51:
        if (paletteLength(r4) < r62) goto L52;
    L94:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("Invalid palette at record ", r15);
        return;
    L96:
        if (r10 != 0) goto L106;
        if (u8(r13) != 0) goto L106;
        if (r12 != 0) goto L106;
        if ((u8(r4 + 19) & 127) != 0) goto L106;
        if (r8 != 65535) goto L106;
        if (r9 == 65535) goto L105;
    L106:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("Invalid full image record ", r15);
        return;
    L109:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("Invalid EPK3 record ", r15);
        return;
    L108:
        r15 = r3;
        goto L109
    L8:
        if (r3 == this.emojiCount) goto L14;
        if (u16(r4) > u16(r4 - 20)) goto L14;
        Model$$ExternalSyntheticBUOutline0.m("Unsorted EPK3 IDs");
        return;
    }

    private void validateRoot(int r2, int r3) {
        if (r2 >= this.count) goto L16;
        if (r2 == r3) goto L16;
        int r22 = entry(r2);
        if (u8(r22 + 16) <= 1) goto L8;
    L14:
        Model$$ExternalSyntheticBUOutline0.m("EPK3 dependency chains are forbidden");
        return;
    L8:
        if (u8(r22 + 17) != 0) goto L14;
        if (u16(r22 + 10) != 65535) goto L14;
        if (u16(r22 + 12) != 65535) goto L14;
        return;
    L16:
        Model$$ExternalSyntheticBUOutline0.m("Invalid root reference");
    }

    public synchronized void clearCache() {
        monitor-enter(this);
        Arrays.fill(this.rootIds, -1);     // Catch: Throwable -> L6
        Arrays.fill(this.rootMaps, null);     // Catch: Throwable -> L6
        Arrays.fill(this.rootAges, 0);     // Catch: Throwable -> L6
        this.age = 0;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized Bitmap getEmoji(int r4, int r5) {
        monitor-enter(this);
        int r42 = findEmoji(r4, r5);     // Catch: Throwable -> L13
        if (r42 >= 0) goto L8;
        monitor-exit(this);
        return null;
    L8:
        int r52 = u16(entry(r42) + 2);     // Catch: Throwable -> L13
        if (r52 != 65535) goto L15;
        Bitmap r43 = decode(r42);     // Catch: Throwable -> L13
        monitor-exit(this);
        return r43;
    L15:
        decodePixels(r42, this.emojiPixels);     // Catch: Throwable -> L13
        decodePixels(find(r52, this.emojiCount, this.count), this.pixels);     // Catch: Throwable -> L13
        int r44 = 0;
    L17:
        int[] r53 = this.emojiPixels;
        if (r44 >= 4096) goto L20;
        int r0 = r53[r44];     // Catch: Throwable -> L13
        int r1 = (((r0 >>> 24) * (this.pixels[r44] & 255)) + 127) / 255;     // Catch: Throwable -> L13
        r53[r44] = (r0 & 16777215) | (r1 << 24);     // Catch: Throwable -> L13
        r44 = r44 + 1;     // Catch: Throwable -> L13
        goto L17
    L20:
        Bitmap r45 = Bitmap.createBitmap(r53, 64, 64, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L13
        monitor-exit(this);
        return r45;
    L13:
        th = move-exception;
        throw th;
    }
}
