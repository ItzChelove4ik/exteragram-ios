package androidx.exifinterface.media;

import android.content.res.AssetManager;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.Pair;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import androidx.exifinterface.media.ExifInterfaceUtils;
import androidx.mediarouter.media.GlobalMediaRouter;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import j$.util.DesugarTimeZone;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import kotlin.UByte;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline0;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class ExifInterface {
    private static final Charset ASCII = null;
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = null;
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = null;
    public static final int[] BITS_PER_SAMPLE_RGB = null;
    private static final Pattern DATETIME_PRIMARY_FORMAT_PATTERN = null;
    private static final Pattern DATETIME_SECONDARY_FORMAT_PATTERN = null;
    private static final boolean DEBUG = false;
    private static final byte[] EXIF_ASCII_PREFIX = null;
    private static final ExifTag[] EXIF_POINTER_TAGS = null;
    static final ExifTag[][] EXIF_TAGS = null;
    private static final List<Integer> FLIPPED_ROTATION_ORDER = null;
    private static final Pattern GPS_TIMESTAMP_PATTERN = null;
    private static final byte[] HEIF_BRAND_AVIF = null;
    private static final byte[] HEIF_BRAND_AVIS = null;
    private static final byte[] HEIF_BRAND_HEIC = null;
    private static final byte[] HEIF_BRAND_MIF1 = null;
    private static final byte[] HEIF_TYPE_FTYP = null;
    static final byte[] IDENTIFIER_EXIF_APP1 = null;
    private static final byte[] IDENTIFIER_XMP_APP1 = null;
    private static final ExifTag[] IFD_EXIF_TAGS = null;
    private static final int[] IFD_FORMAT_BYTES_PER_FORMAT = null;
    private static final String[] IFD_FORMAT_NAMES = null;
    private static final ExifTag[] IFD_GPS_TAGS = null;
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = null;
    private static final ExifTag[] IFD_THUMBNAIL_TAGS = null;
    private static final ExifTag[] IFD_TIFF_TAGS = null;
    static final byte[] JPEG_SIGNATURE = null;
    private static final Pattern NON_ZERO_TIME_PATTERN = null;
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS = null;
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS = null;
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = null;
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = null;
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS = null;
    private static final ExifTag[] PEF_TAGS = null;
    static final byte[] PNG_ITXT_XMP_KEYWORD = null;
    private static final byte[] PNG_SIGNATURE = null;
    private static final Set<String> RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY = null;
    private static final List<Integer> ROTATION_ORDER = null;
    private static final ExifTag TAG_RAF_IMAGE_SIZE = null;
    private static final byte[] WEBP_CHUNK_TYPE_ANIM = null;
    private static final byte[] WEBP_CHUNK_TYPE_ANMF = null;
    private static final byte[] WEBP_CHUNK_TYPE_EXIF = null;
    private static final byte[] WEBP_CHUNK_TYPE_VP8 = null;
    private static final byte[] WEBP_CHUNK_TYPE_VP8L = null;
    private static final byte[] WEBP_CHUNK_TYPE_VP8X = null;
    private static final byte[] WEBP_SIGNATURE_1 = null;
    private static final byte[] WEBP_SIGNATURE_2 = null;
    private static final byte[] WEBP_VP8_SIGNATURE = null;
    private static final HashMap<Integer, Integer> sExifPointerTagMap = null;
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading = null;
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting = null;
    private static final SimpleDateFormat sFormatterPrimary = null;
    private static final SimpleDateFormat sFormatterSecondary = null;
    private boolean mAreThumbnailStripsConsecutive;
    private AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes;
    private Set<Integer> mAttributesOffsets;
    private ByteOrder mExifByteOrder;
    private boolean mFileOnDiskContainsSeparateXmpMarker;
    private String mFilename;
    private boolean mHasThumbnail;
    private boolean mHasThumbnailStrips;
    private boolean mIsExifDataOnly;
    private int mMimeType;
    private boolean mModified;
    private int mOffsetToExifData;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private FileDescriptor mSeekableFileDescriptor;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;
    private ExifAttribute mXmpFromSeparateMarker;

    /* renamed from: androidx.exifinterface.media.ExifInterface$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 extends MediaDataSource {
        long mPosition;
        final /* synthetic */ ExifInterface this$0;
        final /* synthetic */ SeekableByteOrderedDataInputStream val$in;

        public AnonymousClass1(ExifInterface r1, SeekableByteOrderedDataInputStream r2) {
            this.this$0 = r1;
            this.val$in = r2;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // android.media.MediaDataSource
        public long getSize() {
            return -1;
        }

        @Override // android.media.MediaDataSource
        public int readAt(long r7, byte[] r9, int r10, int r11) {
            if (r11 != 0) goto L6;
            return 0;
        L6:
            if (r7 >= 0) goto L26;
            return -1;
        L26:
            long r4 = this.mPosition;     // Catch: IOException -> L25
            if (r4 == r7) goto L17;
            if (r4 >= 0) goto L13;
        L15:
            this.val$in.seek(r7);     // Catch: IOException -> L25
            this.mPosition = r7;     // Catch: IOException -> L25
            goto L17
        L13:
            if (r7 < (r4 + this.val$in.available())) goto L15;
            return -1;
        L17:
            if (r11 <= this.val$in.available()) goto L19;
            r11 = this.val$in.available();     // Catch: IOException -> L25
        L19:
            int r72 = this.val$in.read(r9, r10, r11);     // Catch: IOException -> L25
            if (r72 < 0) goto L23;
            this.mPosition += r72;
            return r72;
        L23:
            this.mPosition = -1;
            return -1;
        }
    }

    /* loaded from: classes3.dex */
    public static class ByteOrderedDataInputStream extends InputStream implements DataInput, InputStreamRetargetInterface {
        private ByteOrder mByteOrder;
        protected final DataInputStream mDataInputStream;
        private int mLength;
        protected int mPosition;
        private byte[] mSkipBuffer;

        public ByteOrderedDataInputStream(InputStream r2) {
            this(r2, ByteOrder.BIG_ENDIAN);
        }

        public ByteOrderedDataInputStream(InputStream r3, ByteOrder r4) {
            DataInputStream r0 = new DataInputStream(r3);
            this.mDataInputStream = r0;
            r0.mark(0);
            this.mPosition = 0;
            this.mByteOrder = r4;
            if ((r3 instanceof ByteOrderedDataInputStream) == false) goto L5;
            int r32 = ((ByteOrderedDataInputStream) r3).length();
        L6:
            this.mLength = r32;
            return;
        L5:
            r32 = -1;
            goto L6
        }

        public ByteOrderedDataInputStream(byte[] r3) {
            this(new ByteArrayInputStream(r3), ByteOrder.BIG_ENDIAN);
            this.mLength = r3.length;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.mDataInputStream.available();
        }

        public int length() {
            return this.mLength;
        }

        @Override // java.io.InputStream
        public void mark(int r1) {
            throw new UnsupportedOperationException("Mark is currently unsupported");
        }

        public int position() {
            return this.mPosition;
        }

        @Override // java.io.InputStream
        public int read() {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        @Override // java.io.InputStream
        public int read(byte[] r2, int r3, int r4) {
            int r22 = this.mDataInputStream.read(r2, r3, r4);
            this.mPosition += r22;
            return r22;
        }

        @Override // java.io.DataInput
        public boolean readBoolean() {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() {
            this.mPosition++;
            int r1 = this.mDataInputStream.read();
            if (r1 >= 0) goto L5;
            Buffer$$ExternalSyntheticBUOutline1.m();
            return 0;
        L5:
            return (byte) r1;
        }

        @Override // java.io.DataInput
        public char readChar() {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public void readFully(byte[] r3) {
            this.mPosition += r3.length;
            this.mDataInputStream.readFully(r3);
        }

        @Override // java.io.DataInput
        public void readFully(byte[] r2, int r3, int r4) {
            this.mPosition += r4;
            this.mDataInputStream.readFully(r2, r3, r4);
        }

        @Override // java.io.DataInput
        public int readInt() {
            this.mPosition += 4;
            int r0 = this.mDataInputStream.read();
            int r1 = this.mDataInputStream.read();
            int r2 = this.mDataInputStream.read();
            int r3 = this.mDataInputStream.read();
            if ((((r0 | r1) | r2) | r3) < 0) goto L14;
            ByteOrder r4 = this.mByteOrder;
            if (r4 != ByteOrder.LITTLE_ENDIAN) goto L9;
            return (((r3 << 24) + (r2 << 16)) + (r1 << 8)) + r0;
        L9:
            if (r4 == ByteOrder.BIG_ENDIAN) goto L11;
            ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid byte order: ", this.mByteOrder);
            return 0;
        L11:
            return (((r0 << 24) + (r1 << 16)) + (r2 << 8)) + r3;
        L14:
            Buffer$$ExternalSyntheticBUOutline1.m();
            return 0;
        }

        @Override // java.io.DataInput
        public String readLine() {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public long readLong() {
            this.mPosition += 8;
            int r1 = this.mDataInputStream.read();
            int r3 = this.mDataInputStream.read();
            int r4 = this.mDataInputStream.read();
            int r5 = this.mDataInputStream.read();
            int r6 = this.mDataInputStream.read();
            int r7 = this.mDataInputStream.read();
            int r8 = this.mDataInputStream.read();
            int r9 = this.mDataInputStream.read();
            if ((((((((r1 | r3) | r4) | r5) | r6) | r7) | r8) | r9) < 0) goto L14;
            ByteOrder r10 = this.mByteOrder;
            if (r10 != ByteOrder.LITTLE_ENDIAN) goto L9;
            return (((((((r9 << 56) + (r8 << 48)) + (r7 << 40)) + (r6 << 32)) + (r5 << 24)) + (r4 << 16)) + (r3 << 8)) + r1;
        L9:
            if (r10 == ByteOrder.BIG_ENDIAN) goto L11;
            ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid byte order: ", this.mByteOrder);
            return 0;
        L11:
            return (((((((r1 << 56) + (r3 << 48)) + (r4 << 40)) + (r5 << 32)) + (r6 << 24)) + (r7 << 16)) + (r8 << 8)) + r9;
        L14:
            Buffer$$ExternalSyntheticBUOutline1.m();
            return 0;
        }

        @Override // java.io.DataInput
        public short readShort() {
            this.mPosition += 2;
            int r0 = this.mDataInputStream.read();
            int r1 = this.mDataInputStream.read();
            if ((r0 | r1) < 0) goto L14;
            ByteOrder r2 = this.mByteOrder;
            if (r2 != ByteOrder.LITTLE_ENDIAN) goto L9;
            return (short) ((r1 << 8) + r0);
        L9:
            if (r2 == ByteOrder.BIG_ENDIAN) goto L11;
            ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid byte order: ", this.mByteOrder);
            return 0;
        L11:
            return (short) ((r0 << 8) + r1);
        L14:
            Buffer$$ExternalSyntheticBUOutline1.m();
            return 0;
        }

        public byte[] readToEnd() {
            byte[] r0 = new byte[1024];
            int r1 = 0;
        L4:
            if (r1 != r0.length) goto L6;
            r0 = Arrays.copyOf(r0, r0.length * 2);
        L6:
            int r2 = this.mDataInputStream.read(r0, r1, r0.length - r1);
            if (r2 == (-1)) goto L10;
            r1 = r1 + r2;
            this.mPosition += r2;
            goto L4
        L10:
            return Arrays.copyOf(r0, r1);
        }

        @Override // java.io.DataInput
        public String readUTF() {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        public long readUnsignedInt() {
            return readInt() & 4294967295L;
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() {
            this.mPosition += 2;
            int r0 = this.mDataInputStream.read();
            int r1 = this.mDataInputStream.read();
            if ((r0 | r1) < 0) goto L14;
            ByteOrder r2 = this.mByteOrder;
            if (r2 != ByteOrder.LITTLE_ENDIAN) goto L9;
            return (r1 << 8) + r0;
        L9:
            if (r2 == ByteOrder.BIG_ENDIAN) goto L11;
            ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid byte order: ", this.mByteOrder);
            return 0;
        L11:
            return (r0 << 8) + r1;
        L14:
            Buffer$$ExternalSyntheticBUOutline1.m();
            return 0;
        }

        @Override // java.io.InputStream
        public void reset() {
            throw new UnsupportedOperationException("Reset is currently unsupported");
        }

        public void setByteOrder(ByteOrder r1) {
            this.mByteOrder = r1;
        }

        @Override // java.io.DataInput
        public int skipBytes(int r1) {
            throw new UnsupportedOperationException("skipBytes is currently unsupported");
        }

        public void skipFully(int r7) {
            int r1 = 0;
        L3:
            if (r1 >= r7) goto L15;
            int r3 = r7 - r1;
            int r2 = (int) this.mDataInputStream.skip(r3);
            if (r2 > 0) goto L14;
            if (this.mSkipBuffer != null) goto L9;
            this.mSkipBuffer = new byte[8192];
        L9:
            r2 = this.mDataInputStream.read(this.mSkipBuffer, 0, Math.min(8192, r3));
            if (r2 != (-1)) goto L14;
            throw new EOFException("Reached EOF while skipping " + r7 + " bytes.");
        L14:
            r1 = r1 + r2;
            goto L3
        L15:
            this.mPosition += r1;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    /* loaded from: classes3.dex */
    public static class ByteOrderedDataOutputStream extends FilterOutputStream {
        private ByteOrder mByteOrder;
        final DataOutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream r2, ByteOrder r3) {
            super(r2);
            this.mOutputStream = new DataOutputStream(r2);
            this.mByteOrder = r3;
        }

        public void setByteOrder(ByteOrder r1) {
            this.mByteOrder = r1;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] r1) {
            this.mOutputStream.write(r1);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] r1, int r2, int r3) {
            this.mOutputStream.write(r1, r2, r3);
        }

        public void writeByte(int r1) {
            this.mOutputStream.write(r1);
        }

        public void writeInt(int r3) {
            ByteOrder r0 = this.mByteOrder;
            if (r0 != ByteOrder.LITTLE_ENDIAN) goto L7;
            this.mOutputStream.write(r3 & 255);
            this.mOutputStream.write((r3 >>> 8) & 255);
            this.mOutputStream.write((r3 >>> 16) & 255);
            this.mOutputStream.write((r3 >>> 24) & 255);
            return;
        L7:
            if (r0 != ByteOrder.BIG_ENDIAN) goto L10;
            this.mOutputStream.write((r3 >>> 24) & 255);
            this.mOutputStream.write((r3 >>> 16) & 255);
            this.mOutputStream.write((r3 >>> 8) & 255);
            this.mOutputStream.write(r3 & 255);
            return;
        }

        public void writeShort(short r3) {
            ByteOrder r0 = this.mByteOrder;
            if (r0 != ByteOrder.LITTLE_ENDIAN) goto L7;
            this.mOutputStream.write(r3 & 255);
            this.mOutputStream.write((r3 >>> 8) & 255);
            return;
        L7:
            if (r0 != ByteOrder.BIG_ENDIAN) goto L10;
            this.mOutputStream.write((r3 >>> 8) & 255);
            this.mOutputStream.write(r3 & 255);
            return;
        }

        public void writeUnsignedInt(long r3) {
            if (r3 > 4294967295L) goto L6;
            writeInt((int) r3);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("val is larger than the maximum value of a 32-bit unsigned integer");
        }

        public void writeUnsignedShort(int r2) {
            if (r2 > 65535) goto L6;
            writeShort((short) r2);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("val is larger than the maximum value of a 16-bit unsigned integer");
        }
    }

    /* loaded from: classes3.dex */
    public static class ExifAttribute {
        public final byte[] bytes;
        public final long bytesOffset;
        public final int format;
        public final int numberOfComponents;

        public ExifAttribute(int r1, int r2, long r3, byte[] r5) {
            this.format = r1;
            this.numberOfComponents = r2;
            this.bytesOffset = r3;
            this.bytes = r5;
        }

        public ExifAttribute(int r7, int r8, byte[] r9) {
            this(r7, r8, -1, r9);
        }

        public static ExifAttribute createByte(String r5) {
            if (r5.length() == 1) goto L5;
        L10:
            byte[] r52 = r5.getBytes(ExifInterface.access$100());
            return new ExifAttribute(1, r52.length, r52);
        L5:
            if (r5.charAt(0) < '0') goto L10;
            if (r5.charAt(0) > '1') goto L10;
            return new ExifAttribute(1, 1, new byte[]{(byte) (r5.charAt(0) - '0')});
        }

        public static ExifAttribute createDouble(double[] r5, ByteOrder r6) {
            ByteBuffer r0 = ByteBuffer.wrap(new byte[ExifInterface.access$000()[12] * r5.length]);
            r0.order(r6);
            int r62 = r5.length;
            int r2 = 0;
        L3:
            if (r2 >= r62) goto L6;
            r0.putDouble(r5[r2]);
            r2 = r2 + 1;
            goto L3
        L6:
            return new ExifAttribute(12, r5.length, r0.array());
        }

        public static ExifAttribute createSLong(int[] r4, ByteOrder r5) {
            ByteBuffer r0 = ByteBuffer.wrap(new byte[ExifInterface.access$000()[9] * r4.length]);
            r0.order(r5);
            int r52 = r4.length;
            int r2 = 0;
        L3:
            if (r2 >= r52) goto L6;
            r0.putInt(r4[r2]);
            r2 = r2 + 1;
            goto L3
        L6:
            return new ExifAttribute(9, r4.length, r0.array());
        }

        public static ExifAttribute createSRational(Rational[] r6, ByteOrder r7) {
            ByteBuffer r0 = ByteBuffer.wrap(new byte[ExifInterface.access$000()[10] * r6.length]);
            r0.order(r7);
            int r72 = r6.length;
            int r2 = 0;
        L3:
            if (r2 >= r72) goto L6;
            Rational r3 = r6[r2];
            r0.putInt((int) r3.numerator);
            r0.putInt((int) r3.denominator);
            r2 = r2 + 1;
            goto L3
        L6:
            return new ExifAttribute(10, r6.length, r0.array());
        }

        public static ExifAttribute createString(String r3) {
            byte[] r32 = (r3 + 0).getBytes(ExifInterface.access$100());
            return new ExifAttribute(2, r32.length, r32);
        }

        public static ExifAttribute createULong(long r2, ByteOrder r4) {
            return createULong(new long[]{r2}, r4);
        }

        public static ExifAttribute createULong(long[] r5, ByteOrder r6) {
            ByteBuffer r0 = ByteBuffer.wrap(new byte[ExifInterface.access$000()[4] * r5.length]);
            r0.order(r6);
            int r62 = r5.length;
            int r2 = 0;
        L3:
            if (r2 >= r62) goto L6;
            r0.putInt((int) r5[r2]);
            r2 = r2 + 1;
            goto L3
        L6:
            return new ExifAttribute(4, r5.length, r0.array());
        }

        public static ExifAttribute createURational(Rational r0, ByteOrder r1) {
            return createURational(new Rational[]{r0}, r1);
        }

        public static ExifAttribute createURational(Rational[] r6, ByteOrder r7) {
            ByteBuffer r0 = ByteBuffer.wrap(new byte[ExifInterface.access$000()[5] * r6.length]);
            r0.order(r7);
            int r72 = r6.length;
            int r2 = 0;
        L3:
            if (r2 >= r72) goto L6;
            Rational r3 = r6[r2];
            r0.putInt((int) r3.numerator);
            r0.putInt((int) r3.denominator);
            r2 = r2 + 1;
            goto L3
        L6:
            return new ExifAttribute(5, r6.length, r0.array());
        }

        public static ExifAttribute createUShort(int r0, ByteOrder r1) {
            return createUShort(new int[]{r0}, r1);
        }

        public static ExifAttribute createUShort(int[] r4, ByteOrder r5) {
            ByteBuffer r0 = ByteBuffer.wrap(new byte[ExifInterface.access$000()[3] * r4.length]);
            r0.order(r5);
            int r52 = r4.length;
            int r2 = 0;
        L3:
            if (r2 >= r52) goto L6;
            r0.putShort((short) r4[r2]);
            r2 = r2 + 1;
            goto L3
        L6:
            return new ExifAttribute(3, r4.length, r0.array());
        }

        public double getDoubleValue(ByteOrder r4) {
            Object r3 = getValue(r4);
            if (r3 == null) goto L43;
            if ((r3 instanceof String) == false) goto L9;
            return Double.parseDouble((String) r3);
        L9:
            if ((r3 instanceof long[]) == false) goto L17;
            if (((long[]) r3).length != 1) goto L15;
            return r3[0];
        L15:
            throw new NumberFormatException("There are more than one component");
        L17:
            if ((r3 instanceof int[]) == false) goto L25;
            if (((int[]) r3).length != 1) goto L23;
            return r3[0];
        L23:
            throw new NumberFormatException("There are more than one component");
        L25:
            if ((r3 instanceof double[]) == false) goto L33;
            double[] r32 = (double[]) r3;
            if (r32.length != 1) goto L31;
            return r32[0];
        L31:
            throw new NumberFormatException("There are more than one component");
        L33:
            if ((r3 instanceof Rational[]) == false) goto L41;
            Rational[] r33 = (Rational[]) r3;
            if (r33.length != 1) goto L39;
            return r33[0].calculate();
        L39:
            throw new NumberFormatException("There are more than one component");
        L41:
            throw new NumberFormatException("Couldn't find a double value");
        L43:
            throw new NumberFormatException("NULL can't be converted to a double value");
        }

        public int getIntValue(ByteOrder r4) {
            Object r3 = getValue(r4);
            if (r3 == null) goto L27;
            if ((r3 instanceof String) == false) goto L9;
            return Integer.parseInt((String) r3);
        L9:
            if ((r3 instanceof long[]) == false) goto L17;
            long[] r32 = (long[]) r3;
            if (r32.length != 1) goto L15;
            return (int) r32[0];
        L15:
            throw new NumberFormatException("There are more than one component");
        L17:
            if ((r3 instanceof int[]) == false) goto L25;
            int[] r33 = (int[]) r3;
            if (r33.length != 1) goto L23;
            return r33[0];
        L23:
            throw new NumberFormatException("There are more than one component");
        L25:
            throw new NumberFormatException("Couldn't find a integer value");
        L27:
            throw new NumberFormatException("NULL can't be converted to a integer value");
        }

        public String getStringValue(ByteOrder r7) {
            Object r6 = getValue(r7);
            if (r6 != null) goto L6;
            return null;
        L6:
            if ((r6 instanceof String) == true) goto L8;
            StringBuilder r0 = new StringBuilder();
            int r3 = 0;
            if ((r6 instanceof long[]) == false) goto L20;
            long[] r62 = (long[]) r6;
        L13:
            if (r3 >= r62.length) goto L18;
            r0.append(r62[r3]);
            r3 = r3 + 1;
            if (r3 == r62.length) goto L13;
            r0.append(",");
            goto L13
        L18:
            return r0.toString();
        L20:
            if ((r6 instanceof int[]) == false) goto L30;
            int[] r63 = (int[]) r6;
        L23:
            if (r3 >= r63.length) goto L28;
            r0.append(r63[r3]);
            r3 = r3 + 1;
            if (r3 == r63.length) goto L23;
            r0.append(",");
            goto L23
        L28:
            return r0.toString();
        L30:
            if ((r6 instanceof double[]) == false) goto L40;
            double[] r64 = (double[]) r6;
        L33:
            if (r3 >= r64.length) goto L38;
            r0.append(r64[r3]);
            r3 = r3 + 1;
            if (r3 == r64.length) goto L33;
            r0.append(",");
            goto L33
        L38:
            return r0.toString();
        L40:
            if ((r6 instanceof Rational[]) == false) goto L49;
            Rational[] r65 = (Rational[]) r6;
        L43:
            if (r3 >= r65.length) goto L48;
            r0.append(r65[r3].numerator);
            r0.append('/');
            r0.append(r65[r3].denominator);
            r3 = r3 + 1;
            if (r3 == r65.length) goto L43;
            r0.append(",");
            goto L43
        L48:
            return r0.toString();
        L49:
            return null;
        L8:
            return (String) r6;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 4, insn: 0x0032: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:18:0x0031 */
        /* JADX WARN: Type inference failed for: r13v17, types: [int[]] */
        /* JADX WARN: Type inference failed for: r13v19, types: [long[]] */
        /* JADX WARN: Type inference failed for: r13v21, types: [androidx.exifinterface.media.ExifInterface$Rational[]] */
        /* JADX WARN: Type inference failed for: r13v23, types: [int[]] */
        /* JADX WARN: Type inference failed for: r13v25, types: [int[]] */
        /* JADX WARN: Type inference failed for: r13v27, types: [androidx.exifinterface.media.ExifInterface$Rational[]] */
        /* JADX WARN: Type inference failed for: r13v29, types: [double[]] */
        /* JADX WARN: Type inference failed for: r13v32, types: [double[]] */
        public Object getValue(ByteOrder r13) {
            InputStream r3 = null;
            ByteOrderedDataInputStream r4 = new ByteOrderedDataInputStream(this.bytes);     // Catch: Throwable -> L89 IOException -> L91
            r4.setByteOrder(r13);     // Catch: Throwable -> L17 IOException -> L19
            int r0 = 0;
            switch(this.format) {
                case 1: goto L81;
                case 2: goto L55;
                case 3: goto L50;
                case 4: goto L46;
                case 5: goto L42;
                case 6: goto L81;
                case 7: goto L55;
                case 8: goto L38;
                case 9: goto L34;
                case 10: goto L30;
                case 11: goto L26;
                case 12: goto L12;
                default: goto L106;
            };
        L12:
            ?? r132 = new double[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L13:
            String r133 = r132;
            if (r0 >= this.numberOfComponents) goto L114;
            r132[r0] = r4.readDouble();     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;
        L114:
            r4.close();     // Catch: IOException -> L23
            return r133;
        L23:
            e = move-exception;
            Log.e("ExifInterface", "IOException occurred while closing InputStream", e);
            return r133;
        L26:
            ?? r134 = new double[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L27:
            r133 = r134;
            if (r0 >= this.numberOfComponents) goto L114;
            r134[r0] = r4.readFloat();     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L27
        L30:
            ?? r135 = new Rational[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L31:
            r133 = r135;
            if (r0 >= this.numberOfComponents) goto L114;
            r135[r0] = new Rational(r4.readInt(), r4.readInt(), null);     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L31
        L34:
            ?? r136 = new int[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L35:
            r133 = r136;
            if (r0 >= this.numberOfComponents) goto L114;
            r136[r0] = r4.readInt();     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L35
        L38:
            ?? r137 = new int[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L39:
            r133 = r137;
            if (r0 >= this.numberOfComponents) goto L114;
            r137[r0] = r4.readShort();     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L39
        L42:
            ?? r138 = new Rational[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L43:
            r133 = r138;
            if (r0 >= this.numberOfComponents) goto L114;
            r138[r0] = new Rational(r4.readUnsignedInt(), r4.readUnsignedInt(), null);     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L43
        L46:
            ?? r139 = new long[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L47:
            r133 = r139;
            if (r0 >= this.numberOfComponents) goto L114;
            r139[r0] = r4.readUnsignedInt();     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L47
        L50:
            ?? r1310 = new int[this.numberOfComponents];     // Catch: Throwable -> L17 IOException -> L19
        L51:
            r133 = r1310;
            if (r0 >= this.numberOfComponents) goto L114;
            r1310[r0] = r4.readUnsignedShort();     // Catch: Throwable -> L17 IOException -> L19
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L51
        L81:
            byte[] r12 = this.bytes;     // Catch: Throwable -> L17 IOException -> L19
            if (r12.length != 1) goto L87;
            byte r1311 = r12[0];     // Catch: Throwable -> L17 IOException -> L19
            if (r1311 < 0) goto L87;
            if (r1311 > 1) goto L87;
            String r122 = new String(new char[]{(char) (r1311 + 48)});     // Catch: Throwable -> L17 IOException -> L19
        L112:
            r4.close();     // Catch: IOException -> L78
            return r122;
        L78:
            e = move-exception;
            Log.e("ExifInterface", "IOException occurred while closing InputStream", e);
            return r122;
        L87:
            r133 = new String(r12, ExifInterface.access$100());     // Catch: Throwable -> L17 IOException -> L19
            goto L114
        L106:
            r4.close();     // Catch: IOException -> L9
            return null;
        L9:
            e = move-exception;
            Log.e("ExifInterface", "IOException occurred while closing InputStream", e);
            return null;
        L55:
            if (this.numberOfComponents < ExifInterface.access$300().length) goto L64;
            int r1312 = 0;
        L58:
            if (r1312 >= ExifInterface.access$300().length) goto L63;
            if (this.bytes[r1312] != ExifInterface.access$300()[r1312]) goto L64;
            r1312 = r1312 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L58
        L63:
            r0 = ExifInterface.access$300().length;     // Catch: Throwable -> L17 IOException -> L19
        L64:
            StringBuilder r1313 = new StringBuilder();     // Catch: Throwable -> L17 IOException -> L19
        L66:
            if (r0 >= this.numberOfComponents) goto L75;
            byte r5 = this.bytes[r0];     // Catch: Throwable -> L17 IOException -> L19
            if (r5 == 0) goto L75;
            if (r5 < 32) goto L73;
            r1313.append((char) r5);     // Catch: Throwable -> L17 IOException -> L19
        L74:
            r0 = r0 + 1;     // Catch: Throwable -> L17 IOException -> L19
            goto L66
        L73:
            r1313.append('?');     // Catch: Throwable -> L17 IOException -> L19
        L75:
            r122 = r1313.toString();     // Catch: Throwable -> L17 IOException -> L19
        L19:
            e = move-exception;
            IOException r123 = e;
        L93:
            Log.w("ExifInterface", "IOException occurred during reading a value", r123);     // Catch: Throwable -> L17
            if (r4 != null) goto L116;
        L99:
            return null;
        L116:
            r4.close();     // Catch: IOException -> L97
        L97:
            e = move-exception;
            Log.e("ExifInterface", "IOException occurred while closing InputStream", e);
            goto L99
        L17:
            th = move-exception;
            Throwable r124 = th;
            r3 = r4;
        L100:
            if (r3 == null) goto L125;
            r3.close();     // Catch: IOException -> L103
            throw r124;
        L103:
            e = move-exception;
            Log.e("ExifInterface", "IOException occurred while closing InputStream", e);
            throw r124;
        L125:
            throw r124;
        L91:
            e = move-exception;
            r123 = e;
            r4 = null;
        L89:
            th = move-exception;
            r124 = th;
            goto L100
        }

        public int size() {
            return ExifInterface.access$000()[this.format] * this.numberOfComponents;
        }

        public String toString() {
            return "(" + ExifInterface.access$200()[this.format] + ", data length:" + this.bytes.length + ")";
        }
    }

    /* loaded from: classes3.dex */
    public static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        public ExifTag(String r1, int r2, int r3) {
            this.name = r1;
            this.number = r2;
            this.primaryFormat = r3;
            this.secondaryFormat = -1;
        }

        public ExifTag(String r1, int r2, int r3, int r4) {
            this.name = r1;
            this.number = r2;
            this.primaryFormat = r3;
            this.secondaryFormat = r4;
        }

        public boolean isFormatCompatible(int r4) {
            int r0 = this.primaryFormat;
            if (r0 == 7) goto L30;
            if (r4 == 7) goto L30;
            if (r0 == r4) goto L30;
            int r3 = this.secondaryFormat;
            if (r3 == r4) goto L30;
            if (r0 == 4) goto L14;
            if (r3 == 4) goto L14;
        L17:
            if (r0 == 9) goto L20;
            if (r3 == 9) goto L20;
        L23:
            if (r0 == 12) goto L26;
            if (r3 == 12) goto L26;
            return false;
        L26:
            if (r4 != 11) goto L31;
            return true;
        L31:
            return false;
        L20:
            if (r4 != 8) goto L23;
            return true;
        L14:
            if (r4 != 3) goto L17;
            return true;
        L30:
            return true;
        }
    }

    /* loaded from: classes3.dex */
    public static class Rational {
        public final long denominator;
        public final long numerator;

        private Rational(long r4, long r6) {
            if (r6 != 0) goto L6;
            this.numerator = 0;
            this.denominator = 1;
            return;
        L6:
            this.numerator = r4;
            this.denominator = r6;
        }

        public /* synthetic */ Rational(long r1, long r3, AnonymousClass1 r5) {
            this(r1, r3);
        }

        public static Rational createFromDouble(double r23) {
            long r3 = 1;
            if (r23 >= 9.223372036854776E18d) goto L17;
            if (r23 <= (-9.223372036854776E18d)) goto L17;
            double r5 = Math.abs(r23);
            long r9 = 0;
            long r11 = 1;
            double r15 = r5;
            long r13 = 0;
        L8:
            double r19 = r15 % 1.0d;
            long r0 = (long) (r15 - r19);
            long r132 = r13 + (r0 * r3);
            long r02 = (r0 * r9) + r11;
            r15 = 1.0d / r19;
            long r17 = r3;
            if (Math.abs(r5 - (r132 / r02)) <= (1.0E-8d * r5)) goto L11;
            r11 = r9;
            r3 = r132;
            r13 = r17;
            r9 = r02;
            goto L8
        L11:
            if (r23 >= 0.0d) goto L14;
            r132 = -r132;
        L14:
            return new Rational(r132, r02);
        L17:
            if (r23 <= 0.0d) goto L19;
            long r1 = LongCompanionObject.MAX_VALUE;
        L21:
            return new Rational(r1, 1);
        L19:
            r1 = Long.MIN_VALUE;
            goto L21
        }

        public double calculate() {
            return this.numerator / this.denominator;
        }

        public String toString() {
            return this.numerator + "/" + this.denominator;
        }
    }

    /* loaded from: classes3.dex */
    public static class SeekableByteOrderedDataInputStream extends ByteOrderedDataInputStream {
        public SeekableByteOrderedDataInputStream(InputStream r1) {
            super(r1);
            if (r1.markSupported() == false) goto L6;
            this.mDataInputStream.mark(Integer.MAX_VALUE);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
            throw null;
        }

        public SeekableByteOrderedDataInputStream(byte[] r1) {
            super(r1);
            this.mDataInputStream.mark(Integer.MAX_VALUE);
        }

        public void seek(long r4) {
            int r0 = this.mPosition;
            if (r0 <= r4) goto L5;
            this.mPosition = 0;
            this.mDataInputStream.reset();
        L6:
            skipFully((int) r4);
            return;
        L5:
            r4 = r4 - r0;
            goto L6
        }
    }

    static {
        DEBUG = Log.isLoggable("ExifInterface", 3);
        ROTATION_ORDER = Arrays.asList(new Integer[]{1, 6, 3, 8});
        FLIPPED_ROTATION_ORDER = Arrays.asList(new Integer[]{2, 7, 4, 5});
        BITS_PER_SAMPLE_RGB = new int[]{8, 8, 8};
        BITS_PER_SAMPLE_GREYSCALE_1 = new int[]{4};
        BITS_PER_SAMPLE_GREYSCALE_2 = new int[]{8};
        JPEG_SIGNATURE = new byte[]{-1, -40, -1};
        HEIF_TYPE_FTYP = new byte[]{102, 116, 121, 112};
        HEIF_BRAND_MIF1 = new byte[]{109, 105, 102, 49};
        HEIF_BRAND_HEIC = new byte[]{104, 101, 105, 99};
        HEIF_BRAND_AVIF = new byte[]{97, 118, 105, 102};
        HEIF_BRAND_AVIS = new byte[]{97, 118, 105, 115};
        ORF_MAKER_NOTE_HEADER_1 = new byte[]{79, 76, 89, 77, 80, 0};
        ORF_MAKER_NOTE_HEADER_2 = new byte[]{79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
        PNG_SIGNATURE = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
        PNG_ITXT_XMP_KEYWORD = "XML:com.adobe.xmp\u0000\u0000\u0000\u0000\u0000".getBytes(StandardCharsets.UTF_8);
        WEBP_SIGNATURE_1 = new byte[]{82, 73, 70, 70};
        WEBP_SIGNATURE_2 = new byte[]{87, 69, 66, 80};
        WEBP_CHUNK_TYPE_EXIF = new byte[]{69, 88, 73, 70};
        WEBP_VP8_SIGNATURE = new byte[]{-99, 1, 42};
        WEBP_CHUNK_TYPE_VP8X = "VP8X".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_VP8L = "VP8L".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_VP8 = "VP8 ".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_ANIM = "ANIM".getBytes(Charset.defaultCharset());
        WEBP_CHUNK_TYPE_ANMF = "ANMF".getBytes(Charset.defaultCharset());
        IFD_FORMAT_NAMES = new String[]{_UrlKt.FRAGMENT_ENCODE_SET, "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
        IFD_FORMAT_BYTES_PER_FORMAT = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
        EXIF_ASCII_PREFIX = new byte[]{65, 83, 67, 73, 73, 0, 0, 0};
        ExifTag[] r64 = {new ExifTag("NewSubfileType", Opcodes.CONST_METHOD_HANDLE, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ImageWidth", 256, 3, 4), new ExifTag("ImageLength", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED, 3, 4), new ExifTag("BitsPerSample", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, 3), new ExifTag("Compression", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, 3), new ExifTag("PhotometricInterpretation", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", MediaError.DetailedErrorCode.SEGMENT_NETWORK, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_ADDED, 4), new ExifTag("JPEGInterchangeFormatLength", GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("SensorTopBorder", 4, 4), new ExifTag("SensorLeftBorder", 5, 4), new ExifTag("SensorBottomBorder", 6, 4), new ExifTag("SensorRightBorder", 7, 4), new ExifTag("ISO", 23, 3), new ExifTag("JpgFromRaw", 46, 7), new ExifTag("Xmp", 700, 1)};
        IFD_TIFF_TAGS = r64;
        ExifTag[] r65 = {new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("SpectralSensitivity", 34852, 2), new ExifTag("PhotographicSensitivity", 34855, 3), new ExifTag("OECF", 34856, 7), new ExifTag("SensitivityType", 34864, 3), new ExifTag("StandardOutputSensitivity", 34865, 4), new ExifTag("RecommendedExposureIndex", 34866, 4), new ExifTag("ISOSpeed", 34867, 4), new ExifTag("ISOSpeedLatitudeyyy", 34868, 4), new ExifTag("ISOSpeedLatitudezzz", 34869, 4), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag("OffsetTime", 36880, 2), new ExifTag("OffsetTimeOriginal", 36881, 2), new ExifTag("OffsetTimeDigitized", 36882, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("CompressedBitsPerPixel", 37122, 5), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("SubjectDistance", 37382, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, 3), new ExifTag("MakerNote", 37500, 7), new ExifTag("UserComment", 37510, 7), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), new ExifTag("PixelXDimension", 40962, 3, 4), new ExifTag("PixelYDimension", 40963, 3, 4), new ExifTag("RelatedSoundFile", 40964, 2), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, 5), new ExifTag("FocalPlaneYResolution", 41487, 5), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SubjectLocation", 41492, 3), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CFAPattern", 41730, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("GainControl", 41991, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, 3), new ExifTag("ImageUniqueID", 42016, 2), new ExifTag("CameraOwnerName", 42032, 2), new ExifTag("BodySerialNumber", 42033, 2), new ExifTag("LensSpecification", 42034, 5), new ExifTag("LensMake", 42035, 2), new ExifTag("LensModel", 42036, 2), new ExifTag("Gamma", 42240, 5), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        IFD_EXIF_TAGS = r65;
        ExifTag[] r66 = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5, 10), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5, 10), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSatellites", 8, 2), new ExifTag("GPSStatus", 9, 2), new ExifTag("GPSMeasureMode", 10, 2), new ExifTag("GPSDOP", 11, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSSpeed", 13, 5), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSTrack", 15, 5), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSImgDirection", 17, 5), new ExifTag("GPSMapDatum", 18, 2), new ExifTag("GPSDestLatitudeRef", 19, 2), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, 2), new ExifTag("GPSDestLongitude", 22, 5), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestBearing", 24, 5), new ExifTag("GPSDestDistanceRef", 25, 2), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, 7), new ExifTag("GPSAreaInformation", 28, 7), new ExifTag("GPSDateStamp", 29, 2), new ExifTag("GPSDifferential", 30, 3), new ExifTag("GPSHPositioningError", 31, 5)};
        IFD_GPS_TAGS = r66;
        ExifTag[] r67 = {new ExifTag("InteroperabilityIndex", 1, 2)};
        IFD_INTEROPERABILITY_TAGS = r67;
        ExifTag[] r68 = {new ExifTag("NewSubfileType", Opcodes.CONST_METHOD_HANDLE, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ThumbnailImageWidth", 256, 3, 4), new ExifTag("ThumbnailImageLength", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED, 3, 4), new ExifTag("BitsPerSample", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, 3), new ExifTag("Compression", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, 3), new ExifTag("PhotometricInterpretation", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("ThumbnailOrientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", MediaError.DetailedErrorCode.SEGMENT_NETWORK, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_ADDED, 4), new ExifTag("JPEGInterchangeFormatLength", GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        IFD_THUMBNAIL_TAGS = r68;
        TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, 3);
        ExifTag[] r70 = {new ExifTag("ThumbnailImage", 256, 7), new ExifTag("CameraSettingsIFDPointer", 8224, 4), new ExifTag("ImageProcessingIFDPointer", 8256, 4)};
        ORF_MAKER_NOTE_TAGS = r70;
        ExifTag[] r71 = {new ExifTag("PreviewImageStart", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED, 4), new ExifTag("PreviewImageLength", GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, 4)};
        ORF_CAMERA_SETTINGS_TAGS = r71;
        ExifTag[] r72 = {new ExifTag("AspectFrame", 4371, 3)};
        ORF_IMAGE_PROCESSING_TAGS = r72;
        ExifTag[] r73 = {new ExifTag("ColorSpace", 55, 3)};
        PEF_TAGS = r73;
        ExifTag[][] r2 = {r64, r65, r66, r67, r68, r64, r70, r71, r72, r73};
        EXIF_TAGS = r2;
        EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("CameraSettingsIFDPointer", 8224, 1), new ExifTag("ImageProcessingIFDPointer", 8256, 1)};
        sExifTagMapsForReading = new HashMap[r2.length];
        sExifTagMapsForWriting = new HashMap[r2.length];
        RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance"})));
        sExifPointerTagMap = new HashMap();
        Charset r0 = Charset.forName("US-ASCII");
        ASCII = r0;
        IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(r0);
        IDENTIFIER_XMP_APP1 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(r0);
        Locale r22 = Locale.US;
        SimpleDateFormat r02 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", r22);
        sFormatterPrimary = r02;
        r02.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        SimpleDateFormat r03 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", r22);
        sFormatterSecondary = r03;
        r03.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        int r04 = 0;
    L3:
        ExifTag[][] r23 = EXIF_TAGS;
        if (r04 >= r23.length) goto L9;
        sExifTagMapsForReading[r04] = new HashMap();
        sExifTagMapsForWriting[r04] = new HashMap();
        ExifTag[] r24 = r23[r04];
        int r4 = r24.length;
        int r5 = 0;
    L6:
        if (r5 >= r4) goto L8;
        ExifTag r6 = r24[r5];
        sExifTagMapsForReading[r04].put(Integer.valueOf(r6.number), r6);
        sExifTagMapsForWriting[r04].put(r6.name, r6);
        r5 = r5 + 1;
        goto L6
    L8:
        r04 = r04 + 1;
        goto L3
    L9:
        HashMap<Integer, Integer> r05 = sExifPointerTagMap;
        ExifTag[] r25 = EXIF_POINTER_TAGS;
        r05.put(Integer.valueOf(r25[0].number), 5);
        r05.put(Integer.valueOf(r25[1].number), 1);
        r05.put(Integer.valueOf(r25[2].number), 2);
        r05.put(Integer.valueOf(r25[3].number), 3);
        r05.put(Integer.valueOf(r25[4].number), 7);
        r05.put(Integer.valueOf(r25[5].number), 8);
        NON_ZERO_TIME_PATTERN = Pattern.compile(".*[1-9].*");
        GPS_TIMESTAMP_PATTERN = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
        DATETIME_PRIMARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
        DATETIME_SECONDARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
    }

    public ExifInterface(File r3) {
        ExifTag[][] r0 = EXIF_TAGS;
        this.mAttributes = new HashMap[r0.length];
        this.mAttributesOffsets = new HashSet(r0.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (r3 == null) goto L6;
        initForFilename(r3.getAbsolutePath());
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("file cannot be null");
        throw null;
    }

    public ExifInterface(InputStream r2) {
        this(r2, 0);
    }

    public ExifInterface(InputStream r3, int r4) {
        ExifTag[][] r0 = EXIF_TAGS;
        this.mAttributes = new HashMap[r0.length];
        this.mAttributesOffsets = new HashSet(r0.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (r3 == null) goto L22;
        this.mFilename = null;
        boolean r1 = true;
        if (r4 == 1) goto L8;
        r1 = false;
    L8:
        this.mIsExifDataOnly = r1;
        if (r1 == false) goto L12;
        this.mAssetInputStream = null;
        this.mSeekableFileDescriptor = null;
    L20:
        loadAttributes(r3);
        return;
    L12:
        if ((r3 instanceof AssetManager.AssetInputStream) == false) goto L15;
        this.mAssetInputStream = (AssetManager.AssetInputStream) r3;
        this.mSeekableFileDescriptor = null;
        goto L20
    L15:
        if ((r3 instanceof FileInputStream) == false) goto L19;
        FileInputStream r42 = (FileInputStream) r3;
        if (isSeekableFD(r42.getFD()) == false) goto L19;
        this.mAssetInputStream = null;
        this.mSeekableFileDescriptor = r42.getFD();
    L19:
        this.mAssetInputStream = null;
        this.mSeekableFileDescriptor = null;
        goto L20
    L22:
        f$$ExternalSyntheticBUOutline2.m("inputStream cannot be null");
        throw null;
    }

    public ExifInterface(String r3) {
        ExifTag[][] r0 = EXIF_TAGS;
        this.mAttributes = new HashMap[r0.length];
        this.mAttributesOffsets = new HashSet(r0.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (r3 == null) goto L6;
        initForFilename(r3);
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("filename cannot be null");
        throw null;
    }

    public static /* synthetic */ int[] access$000() {
        return IFD_FORMAT_BYTES_PER_FORMAT;
    }

    public static /* synthetic */ Charset access$100() {
        return ASCII;
    }

    public static /* synthetic */ String[] access$200() {
        return IFD_FORMAT_NAMES;
    }

    public static /* synthetic */ byte[] access$300() {
        return EXIF_ASCII_PREFIX;
    }

    private void addDefaultValuesForCompatibility() {
        String r0 = getAttribute("DateTimeOriginal");
        if (r0 == null) goto L8;
        if (getAttribute("DateTime") != null) goto L8;
        this.mAttributes[0].put("DateTime", ExifAttribute.createString(r0));
    L8:
        if (getAttribute("ImageWidth") != null) goto L11;
        this.mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0, this.mExifByteOrder));
    L11:
        if (getAttribute("ImageLength") != null) goto L14;
        this.mAttributes[0].put("ImageLength", ExifAttribute.createULong(0, this.mExifByteOrder));
    L14:
        if (getAttribute("Orientation") != null) goto L17;
        this.mAttributes[0].put("Orientation", ExifAttribute.createULong(0, this.mExifByteOrder));
    L17:
        if (getAttribute("LightSource") != null) goto L20;
        this.mAttributes[1].put("LightSource", ExifAttribute.createULong(0, this.mExifByteOrder));
        return;
    }

    private static double convertRationalLatLonToDouble(String r11, String r12) {
        String[] r112 = r11.split(",", -1);     // Catch: Throwable -> L18
        String[] r3 = r112[0].split("/", -1);     // Catch: Throwable -> L18
        double r4 = Double.parseDouble(r3[0].trim()) / Double.parseDouble(r3[1].trim());     // Catch: Throwable -> L18
        String[] r32 = r112[1].split("/", -1);     // Catch: Throwable -> L18
        double r7 = Double.parseDouble(r32[0].trim()) / Double.parseDouble(r32[1].trim());     // Catch: Throwable -> L18
        String[] r113 = r112[2].split("/", -1);     // Catch: Throwable -> L18
        double r42 = (r4 + (r7 / 60.0d)) + ((Double.parseDouble(r113[0].trim()) / Double.parseDouble(r113[1].trim())) / 3600.0d);     // Catch: Throwable -> L18
        if (r12.equals("S") == true) goto L17;
        if (r12.equals("W") == true) goto L17;
        if (r12.equals("N") == false) goto L11;
    L15:
        return r42;
    L11:
        if (r12.equals("E") == true) goto L15;
        throw new IllegalArgumentException();     // Catch: Throwable -> L18
    L17:
        return -r42;
    L18:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return 0.0d;
    }

    private void copyChunksUpToGivenChunkType(ByteOrderedDataInputStream r3, ByteOrderedDataOutputStream r4, byte[] r5, byte[] r6) {
    L2:
        byte[] r0 = new byte[4];
        r3.readFully(r0);
        copyWebPChunk(r3, r4, r0);
        if (Arrays.equals(r0, r5) == true) goto L7;
        if (r6 == null) goto L2;
        if (Arrays.equals(r0, r6) == false) goto L2;
        return;
    }

    private void copyWebPChunk(ByteOrderedDataInputStream r2, ByteOrderedDataOutputStream r3, byte[] r4) {
        int r1 = r2.readInt();
        r3.write(r4);
        r3.writeInt(r1);
        if ((r1 % 2) != 1) goto L5;
        r1 = r1 + 1;
    L5:
        ExifInterfaceUtils.copy(r2, r3, r1);
    }

    private ExifAttribute getExifAttribute(String r5) {
        if (r5 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("tag shouldn't be null");
        return null;
    L5:
        if ("ISOSpeedRatings".equals(r5) == false) goto L11;
        if (DEBUG == false) goto L9;
        Log.d("ExifInterface", "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
    L9:
        r5 = "PhotographicSensitivity";
    L11:
        if ("Xmp".equals(r5) == true) goto L13;
    L17:
        int r2 = 0;
    L19:
        if (r2 >= EXIF_TAGS.length) goto L25;
        ExifAttribute r3 = this.mAttributes[r2].get(r5);
        if (r3 != null) goto L22;
        r2 = r2 + 1;
        goto L19
    L22:
        return r3;
    L25:
        if ("Xmp".equals(r5) == false) goto L29;
        ExifAttribute r4 = this.mXmpFromSeparateMarker;
        if (r4 == null) goto L29;
        return r4;
    L29:
        return null;
    L13:
        if (getXmpHandlingForImageType(this.mMimeType) != 2) goto L17;
        ExifAttribute r22 = this.mXmpFromSeparateMarker;
        if (r22 == null) goto L17;
        return r22;
    }

    private void getHeifAttributes(SeekableByteOrderedDataInputStream r18, int r19) {
        int r4 = Build.VERSION.SDK_INT;
        if (r4 >= 28) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Reading EXIF from HEIC files is supported from SDK 28 and above");
        return;
    L5:
        if (r19 != 15) goto L10;
        if (r4 >= 31) goto L10;
        ByteString$$ExternalSyntheticBUOutline0.m("Reading EXIF from AVIF files is supported from SDK 31 and above");
        return;
    L10:
        MediaMetadataRetriever r42 = new MediaMetadataRetriever();
        ExifInterfaceUtils.Api23Impl.setDataSource(r42, new AnonymousClass1(this, r18));     // Catch: Throwable -> L14 RuntimeException -> L16
        String r5 = r42.extractMetadata(33);     // Catch: Throwable -> L14 RuntimeException -> L16
        String r7 = r42.extractMetadata(34);     // Catch: Throwable -> L14 RuntimeException -> L16
        String r8 = r42.extractMetadata(26);     // Catch: Throwable -> L14 RuntimeException -> L16
        String r9 = r42.extractMetadata(17);     // Catch: Throwable -> L14 RuntimeException -> L16
        if ("yes".equals(r8) == false) goto L19;
        String r2 = r42.extractMetadata(29);     // Catch: Throwable -> L14 RuntimeException -> L16
        String r82 = r42.extractMetadata(30);     // Catch: Throwable -> L14 RuntimeException -> L16
        String r6 = r42.extractMetadata(31);     // Catch: Throwable -> L14 RuntimeException -> L16
    L23:
        if (r2 == null) goto L25;
        this.mAttributes[0].put("ImageWidth", ExifAttribute.createUShort(Integer.parseInt(r2), this.mExifByteOrder));     // Catch: Throwable -> L14 RuntimeException -> L16
    L25:
        if (r82 == null) goto L28;
        this.mAttributes[0].put("ImageLength", ExifAttribute.createUShort(Integer.parseInt(r82), this.mExifByteOrder));     // Catch: Throwable -> L14 RuntimeException -> L16
    L28:
        if (r6 == null) goto L40;
        int r12 = Integer.parseInt(r6);     // Catch: Throwable -> L14 RuntimeException -> L16
        if (r12 != 90) goto L32;
        int r122 = 6;
    L39:
        this.mAttributes[0].put("Orientation", ExifAttribute.createUShort(r122, this.mExifByteOrder));     // Catch: Throwable -> L14 RuntimeException -> L16
        goto L40
    L32:
        if (r12 != 180) goto L34;
        r122 = 3;
        goto L39
    L34:
        if (r12 == 270) goto L36;
        r122 = 1;
        goto L39
    L36:
        r122 = 8;
    L40:
        if (r5 == null) goto L51;
        if (r7 == null) goto L51;
        int r52 = Integer.parseInt(r5);     // Catch: Throwable -> L14 RuntimeException -> L16
        int r72 = Integer.parseInt(r7);     // Catch: Throwable -> L14 RuntimeException -> L16
        if (r72 <= 6) goto L50;
        r18.seek(r52);     // Catch: Throwable -> L14 RuntimeException -> L16
        byte[] r123 = new byte[6];     // Catch: Throwable -> L14 RuntimeException -> L16
        r18.readFully(r123);     // Catch: Throwable -> L14 RuntimeException -> L16
        int r53 = r52 + 6;     // Catch: Throwable -> L14 RuntimeException -> L16
        int r73 = r72 - 6;
        if (Arrays.equals(r123, IDENTIFIER_EXIF_APP1) == false) goto L48;
        byte[] r74 = new byte[r73];     // Catch: Throwable -> L14 RuntimeException -> L16
        r18.readFully(r74);     // Catch: Throwable -> L14 RuntimeException -> L16
        this.mOffsetToExifData = r53;     // Catch: Throwable -> L14 RuntimeException -> L16
        readExifSegment(r74, 0);     // Catch: Throwable -> L14 RuntimeException -> L16
        goto L51
    L48:
        throw new IOException("Invalid identifier");     // Catch: Throwable -> L14 RuntimeException -> L16
    L50:
        throw new IOException("Invalid exif length");     // Catch: Throwable -> L14 RuntimeException -> L16
    L51:
        String r54 = r42.extractMetadata(41);     // Catch: Throwable -> L14 RuntimeException -> L16
        String r75 = r42.extractMetadata(42);     // Catch: Throwable -> L14 RuntimeException -> L16
        if (r54 == null) goto L56;
        if (r75 == null) goto L56;
        int r55 = Integer.parseInt(r54);     // Catch: Throwable -> L14 RuntimeException -> L16
        int r13 = Integer.parseInt(r75);     // Catch: Throwable -> L14 RuntimeException -> L16
        long r14 = r55;
        r18.seek(r14);     // Catch: Throwable -> L14 RuntimeException -> L16
        byte[] r56 = new byte[r13];     // Catch: Throwable -> L14 RuntimeException -> L16
        r18.readFully(r56);     // Catch: Throwable -> L14 RuntimeException -> L16
        this.mXmpFromSeparateMarker = new ExifAttribute(1, r13, r14, r56);     // Catch: Throwable -> L14 RuntimeException -> L16
        this.mFileOnDiskContainsSeparateXmpMarker = true;     // Catch: Throwable -> L14 RuntimeException -> L16
    L56:
        if (DEBUG == false) goto L68;
        Log.d("ExifInterface", "Heif meta: " + r2 + "x" + r82 + ", rotation " + r6);     // Catch: Throwable -> L14 RuntimeException -> L16
    L68:
        r42.release();     // Catch: IOException -> L66
        return;
    L74:
        return;
    L19:
        if ("yes".equals(r9) == false) goto L21;
        r2 = r42.extractMetadata(18);     // Catch: Throwable -> L14 RuntimeException -> L16
        r82 = r42.extractMetadata(19);     // Catch: Throwable -> L14 RuntimeException -> L16
        r6 = r42.extractMetadata(24);     // Catch: Throwable -> L14 RuntimeException -> L16
        goto L23
    L21:
        r2 = null;
        r6 = null;
        r82 = null;
    L16:
        e = move-exception;
        throw new UnsupportedOperationException("Failed to read EXIF from HEIF file. Given stream is either malformed or unsupported.", e);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        r42.release();     // Catch: IOException -> L67
    L63:
        throw th;
    }

    private void getJpegAttributes(ByteOrderedDataInputStream r20, int r21, int r22) {
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "getJpegAttributes starting with: " + r20);
    L5:
        r20.setByteOrder(ByteOrder.BIG_ENDIAN);
        byte r3 = r20.readByte();
        if (r3 == (-1)) goto L8;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid marker: ", Integer.toHexString(r3 & UByte.MAX_VALUE));
        return;
    L8:
        if (r20.readByte() != (-40)) goto L67;
        int r32 = 2;
    L10:
        byte r5 = r20.readByte();
        if (r5 != (-1)) goto L65;
    L12:
        int r52 = r32 + 1;
        byte r7 = r20.readByte();
        if (r7 != (-1)) goto L14;
        r32 = r52;
        goto L12
    L14:
        boolean r53 = DEBUG;
        if (r53 == false) goto L18;
        Log.d("ExifInterface", "Found JPEG segment indicator: " + Integer.toHexString(r7 & UByte.MAX_VALUE));
    L18:
        if (r7 == (-39)) goto L62;
        if (r7 == (-38)) goto L62;
        int r8 = r20.readUnsignedShort();
        int r9 = r8 - 2;
        int r33 = r32 + 4;
        if (r53 == false) goto L26;
        Log.d("ExifInterface", "JPEG segment: " + Integer.toHexString(r7 & UByte.MAX_VALUE) + " (length: " + r8 + ")");
    L26:
        if (r9 < 0) goto L60;
        if (r7 != (-31)) goto L30;
        byte[] r72 = new byte[r9];
        r20.readFully(r72);
        int r82 = r33 + r9;
        byte[] r10 = IDENTIFIER_EXIF_APP1;
        if (ExifInterfaceUtils.startsWith(r72, r10) == false) goto L52;
        byte[] r73 = Arrays.copyOfRange(r72, r10.length, r9);
        this.mOffsetToExifData = (r21 + r33) + r10.length;
        readExifSegment(r73, r22);
        setThumbnailData(new ByteOrderedDataInputStream(r73));
    L55:
        r33 = r82;
    L48:
        r9 = 0;
    L56:
        if (r9 < 0) goto L58;
        r20.skipFully(r9);
        r32 = r33 + r9;
        goto L10
    L58:
        Model$$ExternalSyntheticBUOutline0.m("Invalid length");
        return;
    L52:
        byte[] r102 = IDENTIFIER_XMP_APP1;
        if (ExifInterfaceUtils.startsWith(r72, r102) == false) goto L55;
        int r34 = r33 + r102.length;
        byte[] r74 = Arrays.copyOfRange(r72, r102.length, r9);
        this.mXmpFromSeparateMarker = new ExifAttribute(1, r74.length, r34, r74);
        this.mFileOnDiskContainsSeparateXmpMarker = true;
        goto L55
    L30:
        if (r7 == (-2)) goto L45;
        switch(r7) {
            case -64: goto L36;
            case -63: goto L36;
            case -62: goto L36;
            case -61: goto L36;
            default: goto L32;
        };
    L32:
        switch(r7) {
            case -59: goto L36;
            case -58: goto L36;
            case -57: goto L36;
            default: goto L33;
        };
    L33:
        switch(r7) {
            case -55: goto L36;
            case -54: goto L36;
            case -53: goto L36;
            default: goto L34;
        };
    L34:
        switch(r7) {
            case -51: goto L36;
            case -50: goto L36;
            case -49: goto L36;
            default: goto L56;
        };
    L36:
        r20.skipFully(1);
        HashMap<String, ExifAttribute> r75 = this.mAttributes[r22];
        if (r22 == 4) goto L39;
        String r103 = "ImageLength";
    L40:
        r75.put(r103, ExifAttribute.createULong(r20.readUnsignedShort(), this.mExifByteOrder));
        HashMap<String, ExifAttribute> r76 = this.mAttributes[r22];
        if (r22 == 4) goto L43;
        String r92 = "ImageWidth";
    L44:
        r76.put(r92, ExifAttribute.createULong(r20.readUnsignedShort(), this.mExifByteOrder));
        r9 = r8 - 7;
        goto L56
    L43:
        r92 = "ThumbnailImageWidth";
        goto L44
    L39:
        r103 = "ThumbnailImageLength";
        goto L40
    L45:
        byte[] r77 = new byte[r9];
        r20.readFully(r77);
        if (getAttribute("UserComment") != null) goto L48;
        this.mAttributes[1].put("UserComment", ExifAttribute.createString(new String(r77, ASCII)));
        goto L48
    L60:
        Model$$ExternalSyntheticBUOutline0.m("Invalid length");
        return;
    L62:
        r20.setByteOrder(this.mExifByteOrder);
        return;
    L65:
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid marker:", Integer.toHexString(r5 & UByte.MAX_VALUE));
        return;
    L67:
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid marker: ", Integer.toHexString(r3 & UByte.MAX_VALUE));
    }

    private int getMimeType(BufferedInputStream r2) {
        r2.mark(5000);
        byte[] r0 = new byte[5000];
        r2.read(r0);
        r2.reset();
        if (isJpegFormat(r0) == false) goto L7;
        return 4;
    L7:
        if (isRafFormat(r0) == false) goto L10;
        return 9;
    L10:
        int r22 = isHeicOrAvifFormat(r0);
        if (r22 == 0) goto L14;
        return r22;
    L14:
        if (isOrfFormat(r0) == false) goto L18;
        return 7;
    L18:
        if (isRw2Format(r0) == false) goto L22;
        return 10;
    L22:
        if (isPngFormat(r0) == false) goto L26;
        return 13;
    L26:
        if (isWebpFormat(r0) == false) goto L29;
        return 14;
    L29:
        return 0;
    }

    private void getOrfAttributes(SeekableByteOrderedDataInputStream r6) {
        getRawAttributes(r6);
        ExifAttribute r62 = this.mAttributes[1].get("MakerNote");
        if (r62 == null) goto L32;
        SeekableByteOrderedDataInputStream r1 = new SeekableByteOrderedDataInputStream(r62.bytes);
        r1.setByteOrder(this.mExifByteOrder);
        byte[] r63 = ORF_MAKER_NOTE_HEADER_1;
        byte[] r2 = new byte[r63.length];
        r1.readFully(r2);
        r1.seek(0);
        byte[] r3 = ORF_MAKER_NOTE_HEADER_2;
        byte[] r4 = new byte[r3.length];
        r1.readFully(r4);
        if (Arrays.equals(r2, r63) == false) goto L8;
        r1.seek(8);
    L10:
        readImageFileDirectory(r1, 6);
        ExifAttribute r64 = this.mAttributes[7].get("PreviewImageStart");
        ExifAttribute r12 = this.mAttributes[7].get("PreviewImageLength");
        if (r64 == null) goto L14;
        if (r12 == null) goto L14;
        this.mAttributes[5].put("JPEGInterchangeFormat", r64);
        this.mAttributes[5].put("JPEGInterchangeFormatLength", r12);
    L14:
        ExifAttribute r65 = this.mAttributes[8].get("AspectFrame");
        if (r65 == null) goto L33;
        int[] r66 = (int[]) r65.getValue(this.mExifByteOrder);
        if (r66 != null) goto L19;
    L30:
        Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(r66));
        return;
    L19:
        if (r66.length != 4) goto L30;
        int r13 = r66[2];
        int r32 = r66[0];
        if (r13 <= r32) goto L34;
        int r42 = r66[3];
        int r67 = r66[1];
        if (r42 <= r67) goto L35;
        int r14 = (r13 - r32) + 1;
        int r43 = (r42 - r67) + 1;
        if (r14 >= r43) goto L28;
        int r15 = r14 + r43;
        r43 = r15 - r43;
        r14 = r15 - r43;
    L28:
        ExifAttribute r68 = ExifAttribute.createUShort(r14, this.mExifByteOrder);
        ExifAttribute r0 = ExifAttribute.createUShort(r43, this.mExifByteOrder);
        this.mAttributes[0].put("ImageWidth", r68);
        this.mAttributes[0].put("ImageLength", r0);
        return;
    L35:
        return;
    L34:
        return;
    L33:
        return;
    L8:
        if (Arrays.equals(r4, r3) == false) goto L10;
        r1.seek(12);
        goto L10
    }

    private void getPngAttributes(ByteOrderedDataInputStream r18) {
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "getPngAttributes starting with: " + r18);
    L5:
        r18.setByteOrder(ByteOrder.BIG_ENDIAN);
        int r2 = r18.position();
        r18.skipFully(PNG_SIGNATURE.length);
        boolean r4 = false;
        boolean r5 = false;
    L6:
        if (r4 == false) goto L40;
        if (r5 == false) goto L40;
    L17:
        this.mFileOnDiskContainsSeparateXmpMarker = r5;     // Catch: EOFException -> L37
        return;
    L40:
        int r6 = r18.readInt();     // Catch: EOFException -> L37
        int r7 = r18.readInt();     // Catch: EOFException -> L37
        int r8 = (r18.position() + r6) + 4;     // Catch: EOFException -> L37
        if ((r18.position() - r2) != 16) goto L16;
        if (r7 == 1229472850) goto L16;
        throw new IOException("Encountered invalid PNG file--IHDR chunk should appear as the first chunk");     // Catch: EOFException -> L37
    L16:
        if (r7 == 1229278788) goto L17;
        if (r7 != 1700284774) goto L28;
        if (r4 == true) goto L28;
        this.mOffsetToExifData = r18.position() - r2;     // Catch: EOFException -> L37
        byte[] r42 = new byte[r6];     // Catch: EOFException -> L37
        r18.readFully(r42);     // Catch: EOFException -> L37
        int r62 = r18.readInt();     // Catch: EOFException -> L37
        CRC32 r9 = new CRC32();     // Catch: EOFException -> L37
        updateCrcWithInt(r9, r7);     // Catch: EOFException -> L37
        r9.update(r42);     // Catch: EOFException -> L37
        if (((int) r9.getValue()) != r62) goto L26;
        readExifSegment(r42, 0);     // Catch: EOFException -> L37
        validateImages();     // Catch: EOFException -> L37
        setThumbnailData(new ByteOrderedDataInputStream(r42));     // Catch: EOFException -> L37
        r4 = true;
    L35:
        r18.skipFully(r8 - r18.position());     // Catch: EOFException -> L37
        goto L6
    L26:
        throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + r62 + ", calculated CRC value: " + r9.getValue());     // Catch: EOFException -> L37
    L28:
        if (r7 != 1767135348) goto L35;
        if (r5 == true) goto L35;
        byte[] r72 = PNG_ITXT_XMP_KEYWORD;     // Catch: EOFException -> L37
        if (r6 < r72.length) goto L35;
        int r92 = r72.length;     // Catch: EOFException -> L37
        byte[] r11 = new byte[r92];     // Catch: EOFException -> L37
        r18.readFully(r11);     // Catch: EOFException -> L37
        if (Arrays.equals(r11, r72) == false) goto L35;
        int r52 = r18.position() - r2;     // Catch: EOFException -> L37
        int r13 = r6 - r92;     // Catch: EOFException -> L37
        byte[] r63 = new byte[r13];     // Catch: EOFException -> L37
        r18.readFully(r63);     // Catch: EOFException -> L37
        this.mXmpFromSeparateMarker = new ExifAttribute(1, r13, r52, r63);     // Catch: EOFException -> L37
        r5 = true;
    L37:
        e = move-exception;
        throw new IOException("Encountered corrupt PNG file.", e);
    }

    private void getRafAttributes(ByteOrderedDataInputStream r8) {
        boolean r0 = DEBUG;
        if (r0 == false) goto L5;
        Log.d("ExifInterface", "getRafAttributes starting with: " + r8);
    L5:
        r8.skipFully(84);
        byte[] r3 = new byte[4];
        byte[] r4 = new byte[4];
        byte[] r2 = new byte[4];
        r8.readFully(r3);
        r8.readFully(r4);
        r8.readFully(r2);
        int r32 = ByteBuffer.wrap(r3).getInt();
        int r42 = ByteBuffer.wrap(r4).getInt();
        int r22 = ByteBuffer.wrap(r2).getInt();
        byte[] r43 = new byte[r42];
        r8.skipFully(r32 - r8.position());
        r8.readFully(r43);
        getJpegAttributes(new ByteOrderedDataInputStream(r43), r32, 5);
        r8.skipFully(r22 - r8.position());
        r8.setByteOrder(ByteOrder.BIG_ENDIAN);
        int r23 = r8.readInt();
        if (r0 == false) goto L8;
        Log.d("ExifInterface", "numberOfDirectoryEntry: " + r23);
    L8:
        int r33 = 0;
    L9:
        if (r33 >= r23) goto L20;
        int r44 = r8.readUnsignedShort();
        int r5 = r8.readUnsignedShort();
        if (r44 == TAG_RAF_IMAGE_SIZE.number) goto L12;
        r8.skipFully(r5);
        r33 = r33 + 1;
        goto L9
    L12:
        short r24 = r8.readShort();
        short r82 = r8.readShort();
        ExifAttribute r34 = ExifAttribute.createUShort(r24, this.mExifByteOrder);
        ExifAttribute r45 = ExifAttribute.createUShort(r82, this.mExifByteOrder);
        this.mAttributes[0].put("ImageLength", r34);
        this.mAttributes[0].put("ImageWidth", r45);
        if (DEBUG == false) goto L17;
        Log.d("ExifInterface", "Updated to length: " + r24 + ", width: " + r82);
        return;
    L17:
        return;
    }

    private void getRawAttributes(SeekableByteOrderedDataInputStream r3) {
        parseTiffHeaders(r3);
        readImageFileDirectory(r3, 0);
        updateImageSizeValues(r3, 0);
        updateImageSizeValues(r3, 5);
        updateImageSizeValues(r3, 4);
        validateImages();
        if (this.mMimeType != 8) goto L10;
        ExifAttribute r32 = this.mAttributes[1].get("MakerNote");
        if (r32 == null) goto L11;
        SeekableByteOrderedDataInputStream r1 = new SeekableByteOrderedDataInputStream(r32.bytes);
        r1.setByteOrder(this.mExifByteOrder);
        r1.skipFully(6);
        readImageFileDirectory(r1, 9);
        ExifAttribute r33 = this.mAttributes[9].get("ColorSpace");
        if (r33 == null) goto L12;
        this.mAttributes[1].put("ColorSpace", r33);
        return;
    L12:
        return;
    L11:
        return;
    }

    private void getRw2Attributes(SeekableByteOrderedDataInputStream r5) {
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "getRw2Attributes starting with: " + r5);
    L5:
        getRawAttributes(r5);
        ExifAttribute r52 = this.mAttributes[0].get("JpgFromRaw");
        if (r52 == null) goto L8;
        getJpegAttributes(new ByteOrderedDataInputStream(r52.bytes), (int) r52.bytesOffset, 5);
    L8:
        ExifAttribute r53 = this.mAttributes[0].get("ISO");
        ExifAttribute r0 = this.mAttributes[1].get("PhotographicSensitivity");
        if (r53 == null) goto L13;
        if (r0 != null) goto L14;
        this.mAttributes[1].put("PhotographicSensitivity", r53);
        return;
    L14:
        return;
    }

    private boolean getStandaloneAttributes(SeekableByteOrderedDataInputStream r4) {
        byte[] r0 = IDENTIFIER_EXIF_APP1;
        byte[] r1 = new byte[r0.length];
        r4.readFully(r1);
        if (Arrays.equals(r1, r0) == true) goto L6;
        Log.w("ExifInterface", "Given data is not EXIF-only.");
        return false;
    L6:
        byte[] r42 = r4.readToEnd();
        this.mOffsetToExifData = r0.length;
        readExifSegment(r42, 0);
        return true;
    }

    private void getWebpAttributes(ByteOrderedDataInputStream r6) {
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "getWebpAttributes starting with: " + r6);
    L5:
        r6.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        r6.skipFully(WEBP_SIGNATURE_1.length);
        int r0 = r6.readInt() + 8;
        byte[] r1 = WEBP_SIGNATURE_2;
        r6.skipFully(r1.length);
        int r12 = r1.length + 8;
    L27:
        byte[] r2 = new byte[4];     // Catch: EOFException -> L24
        r6.readFully(r2);     // Catch: EOFException -> L24
        int r3 = r6.readInt();     // Catch: EOFException -> L24
        int r13 = r12 + 8;     // Catch: EOFException -> L24
        if (Arrays.equals(WEBP_CHUNK_TYPE_EXIF, r2) == true) goto L9;
        if ((r3 % 2) != 1) goto L17;
        r3 = r3 + 1;     // Catch: EOFException -> L24
    L17:
        r12 = r13 + r3;     // Catch: EOFException -> L24
        if (r12 == r0) goto L19;
        if (r12 > r0) goto L23;
        r6.skipFully(r3);     // Catch: EOFException -> L24
        goto L27
    L23:
        throw new IOException("Encountered WebP file with invalid chunk size");     // Catch: EOFException -> L24
    L19:
        return;
    L9:
        byte[] r02 = new byte[r3];     // Catch: EOFException -> L24
        r6.readFully(r02);     // Catch: EOFException -> L24
        byte[] r62 = IDENTIFIER_EXIF_APP1;     // Catch: EOFException -> L24
        if (ExifInterfaceUtils.startsWith(r02, r62) == false) goto L12;
        r02 = Arrays.copyOfRange(r02, r62.length, r3);     // Catch: EOFException -> L24
    L12:
        this.mOffsetToExifData = r13;     // Catch: EOFException -> L24
        readExifSegment(r02, 0);     // Catch: EOFException -> L24
        setThumbnailData(new ByteOrderedDataInputStream(r02));     // Catch: EOFException -> L24
        return;
    L24:
        e = move-exception;
        throw new IOException("Encountered corrupt WebP file.", e);
    }

    private static int getXmpHandlingForImageType(int r1) {
        if (r1 != 4) goto L5;
        return 3;
    L5:
        if (r1 != 9) goto L7;
        return 2;
    L7:
        if (r1 != 15) goto L9;
        return 2;
    L9:
        if (r1 != 12) goto L11;
        return 2;
    L11:
        if (r1 == 13) goto L20;
        return 1;
    L20:
        return 2;
    }

    private static Pair<Integer, Integer> guessDataFormat(String r13) {
        int r10 = 1;
        if (r13.contains(",") == false) goto L35;
        String[] r132 = r13.split(",", -1);
        Pair<Integer, Integer> r0 = guessDataFormat(r132[0]);
        if (((Integer) r0.first).intValue() != 2) goto L8;
        return r0;
    L8:
        if (r10 >= r132.length) goto L33;
        Pair<Integer, Integer> r1 = guessDataFormat(r132[r10]);
        if (((Integer) r1.first).equals(r0.first) == false) goto L12;
    L15:
        int r2 = ((Integer) r0.first).intValue();
    L17:
        if (((Integer) r0.second).intValue() != (-1)) goto L19;
    L23:
        int r12 = -1;
    L24:
        if (r2 != (-1)) goto L28;
        if (r12 != (-1)) goto L28;
        return new Pair(2, -1);
    L28:
        if (r2 != (-1)) goto L30;
        r0 = new Pair(Integer.valueOf(r12), -1);
    L32:
        r10 = r10 + 1;
        goto L8
    L30:
        if (r12 != (-1)) goto L32;
        r0 = new Pair(Integer.valueOf(r2), -1);
        goto L32
    L19:
        if (((Integer) r1.first).equals(r0.second) == false) goto L21;
    L22:
        r12 = ((Integer) r0.second).intValue();
        goto L24
    L21:
        if (((Integer) r1.second).equals(r0.second) == false) goto L23;
    L12:
        if (((Integer) r1.second).equals(r0.first) == true) goto L15;
        r2 = -1;
        goto L17
    L33:
        return r0;
    L35:
        if (r13.contains("/") == false) goto L72;
        String[] r133 = r13.split("/", -1);
        if (r133.length != 2) goto L55;
        long r7 = (long) Double.parseDouble(r133[0]);     // Catch: NumberFormatException -> L71
        long r9 = (long) Double.parseDouble(r133[1]);     // Catch: NumberFormatException -> L71
        if (r7 < 0) goto L53;
        if (r9 < 0) goto L53;
        if (r7 > 2147483647L) goto L51;
        if (r9 > 2147483647L) goto L51;
        return new Pair(10, 5);
    L51:
        return new Pair(5, -1);
    L53:
        return new Pair(10, -1);
    L55:
        return new Pair(2, -1);
    L72:
        long r14 = Long.parseLong(r13);     // Catch: NumberFormatException -> L67
        if (r14 >= 0) goto L59;
    L62:
        if (r14 >= 0) goto L66;
        return new Pair(9, -1);
    L66:
        return new Pair(4, -1);
    L59:
        if (r14 > WebSocketProtocol.PAYLOAD_SHORT_MAX) goto L62;
        return new Pair(3, 4);
    L67:
        Double.parseDouble(r13);     // Catch: NumberFormatException -> L69
        return new Pair(12, -1);
    L70:
        return new Pair(2, -1);
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream r4, HashMap<String, ExifAttribute> r5) {
        ExifAttribute r0 = r5.get("JPEGInterchangeFormat");
        ExifAttribute r52 = r5.get("JPEGInterchangeFormatLength");
        if (r0 == null) goto L22;
        if (r52 == null) goto L23;
        int r02 = r0.getIntValue(this.mExifByteOrder);
        int r53 = r52.getIntValue(this.mExifByteOrder);
        if (this.mMimeType != 7) goto L8;
        r02 = r02 + this.mOrfMakerNoteOffset;
    L8:
        if (r02 <= 0) goto L19;
        if (r53 <= 0) goto L19;
        this.mHasThumbnail = true;
        if (this.mFilename == null) goto L13;
    L17:
        this.mThumbnailOffset = r02;
        this.mThumbnailLength = r53;
        goto L19
    L13:
        if (this.mAssetInputStream != null) goto L17;
        if (this.mSeekableFileDescriptor != null) goto L17;
        byte[] r1 = new byte[r53];
        r4.skipFully(r02);
        r4.readFully(r1);
        this.mThumbnailBytes = r1;
    L19:
        if (DEBUG == false) goto L24;
        Log.d("ExifInterface", "Setting thumbnail attributes with offset: " + r02 + ", length: " + r53);
        return;
    L24:
        return;
    L23:
        return;
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream r20, HashMap<String, ExifAttribute> r21) {
        ExifAttribute r4 = r21.get("StripOffsets");
        ExifAttribute r2 = r21.get("StripByteCounts");
        if (r4 == null) goto L57;
        if (r2 == null) goto L58;
        long[] r42 = ExifInterfaceUtils.convertToLongArray(r4.getValue(this.mExifByteOrder));
        long[] r22 = ExifInterfaceUtils.convertToLongArray(r2.getValue(this.mExifByteOrder));
        if (r42 != null) goto L8;
    L48:
        Log.w("ExifInterface", "stripOffsets should not be null or have zero length.");
        return;
    L8:
        if (r42.length == 0) goto L48;
        if (r22 != null) goto L12;
    L46:
        Log.w("ExifInterface", "stripByteCounts should not be null or have zero length.");
        return;
    L12:
        if (r22.length == 0) goto L46;
        if (r42.length == r22.length) goto L18;
        Log.w("ExifInterface", "stripOffsets and stripByteCounts should have same length.");
        return;
    L18:
        int r6 = r22.length;
        long r8 = 0;
        int r10 = 0;
    L19:
        if (r10 >= r6) goto L21;
        r8 = r8 + r22[r10];
        r10 = r10 + 1;
        goto L19
    L21:
        int r62 = (int) r8;
        byte[] r82 = new byte[r62];
        int r9 = 1;
        this.mAreThumbnailStripsConsecutive = true;
        this.mHasThumbnailStrips = true;
        this.mHasThumbnail = true;
        int r102 = 0;
        int r11 = 0;
        int r12 = 0;
    L23:
        if (r102 >= r42.length) goto L42;
        int r13 = (int) r42[r102];
        int r14 = (int) r22[r102];
        if (r102 >= (r42.length - r9)) goto L29;
        int r16 = r102;
        if ((r13 + r14) == r42[r16 + 1]) goto L30;
        this.mAreThumbnailStripsConsecutive = false;
    L30:
        int r132 = r13 - r11;
        if (r132 < 0) goto L32;
        r20.skipFully(r132);     // Catch: EOFException -> L40
        int r112 = r11 + r132;
        byte[] r92 = new byte[r14];
        r20.readFully(r92);     // Catch: EOFException -> L38
        r11 = r112 + r14;
        System.arraycopy(r92, 0, r82, r12, r14);
        r12 = r12 + r14;
        r102 = r16 + 1;
        r9 = 1;
    L38:
        Log.d("ExifInterface", "Failed to read " + r14 + " bytes.");
        return;
    L40:
        Log.d("ExifInterface", "Failed to skip " + r132 + " bytes.");
        return;
    L32:
        Log.d("ExifInterface", "Invalid strip offset value");
        return;
    L29:
        r16 = r102;
        goto L30
    L42:
        this.mThumbnailBytes = r82;
        if (this.mAreThumbnailStripsConsecutive == false) goto L59;
        this.mThumbnailOffset = (int) r42[0];
        this.mThumbnailLength = r62;
        return;
    L59:
        return;
    L58:
        return;
    }

    private void initForFilename(String r3) {
        if (r3 == null) goto L17;
        FileInputStream r0 = null;
        this.mAssetInputStream = null;
        this.mFilename = r3;
        FileInputStream r1 = new FileInputStream(r3);     // Catch: Throwable -> L14
    L8:
        th = th;
        r0 = r1;
    L15:
        ExifInterfaceUtils.closeQuietly(r0);
        throw th;
    L6:
        if (isSeekableFD(r1.getFD()) == false) goto L10;
        this.mSeekableFileDescriptor = r1.getFD();     // Catch: Throwable -> L8
    L11:
        loadAttributes(r1);     // Catch: Throwable -> L8
        ExifInterfaceUtils.closeQuietly(r1);
        return;
    L10:
        this.mSeekableFileDescriptor = null;     // Catch: Throwable -> L8
    L14:
        th = th;
        goto L15
    L17:
        f$$ExternalSyntheticBUOutline2.m("filename cannot be null");
    }

    private int isHeicOrAvifFormat(byte[] r14) {
        ByteOrderedDataInputStream r0 = null;
        ByteOrderedDataInputStream r1 = new ByteOrderedDataInputStream(r14);     // Catch: Throwable -> L58 Exception -> L60
        long r2 = r1.readInt();     // Catch: Throwable -> L15 Exception -> L17
        byte[] r4 = new byte[4];     // Catch: Throwable -> L15 Exception -> L17
        r1.readFully(r4);     // Catch: Throwable -> L15 Exception -> L17
        if (Arrays.equals(r4, HEIF_TYPE_FTYP) == true) goto L9;
        r1.close();
        return 0;
    L9:
        if (r2 != 1) goto L19;
        r2 = r1.readLong();     // Catch: Throwable -> L15 Exception -> L17
        long r9 = 16;
        if (r2 >= 16) goto L21;
        r1.close();
        return 0;
    L21:
        if (r2 > r14.length) goto L23;
    L24:
        long r22 = r2 - r9;
        if (r22 >= 8) goto L28;
        r1.close();
        return 0;
    L28:
        byte[] r142 = new byte[4];     // Catch: Throwable -> L15 Exception -> L17
        long r6 = 0;
        boolean r02 = false;
        boolean r8 = false;
        boolean r92 = false;
    L29:
    L31:
        if (r6 >= (r22 / 4)) goto L57;
        r1.readFully(r142);     // Catch: Throwable -> L15 Exception -> L17 EOFException -> L55
        if (r6 == 1) goto L54;
        if (Arrays.equals(r142, HEIF_BRAND_MIF1) == false) goto L40;
        r02 = true;
    L47:
        if (r02 == false) goto L54;
        if (r8 == true) goto L49;
        if (r92 == false) goto L54;
        r1.close();
        return 15;
    L49:
        r1.close();
        return 12;
    L40:
        if (Arrays.equals(r142, HEIF_BRAND_HEIC) == false) goto L43;
        r8 = true;
        goto L47
    L43:
        if (Arrays.equals(r142, HEIF_BRAND_AVIF) == false) goto L45;
    L46:
        r92 = true;
        goto L47
    L45:
        if (Arrays.equals(r142, HEIF_BRAND_AVIS) == false) goto L47;
    L54:
        r6 = r6 + 1;
    L55:
        r1.close();
        return 0;
    L57:
        r1.close();
    L66:
        return 0;
    L23:
        r2 = r14.length;     // Catch: Throwable -> L15 Exception -> L17
        goto L24
    L19:
        r9 = 8;
    L17:
        e = e;
        r0 = r1;
    L62:
        if (DEBUG == false) goto L64;
        Log.d("ExifInterface", "Exception parsing HEIF file type box.", e);     // Catch: Throwable -> L58
    L64:
        if (r0 == null) goto L66;
        r0.close();
    L15:
        th = th;
        r0 = r1;
    L67:
        if (r0 == null) goto L69;
        r0.close();
    L69:
        throw th;
    L60:
        e = e;
    L58:
        th = th;
        goto L67
    }

    private static boolean isJpegFormat(byte[] r4) {
        int r1 = 0;
    L3:
        byte[] r2 = JPEG_SIGNATURE;
        if (r1 >= r2.length) goto L9;
        if (r4[r1] != r2[r1]) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return false;
    L9:
        return true;
    }

    private boolean isOrfFormat(byte[] r4) {
        boolean r0 = false;
        ByteOrderedDataInputStream r1 = null;
        ByteOrderedDataInputStream r2 = new ByteOrderedDataInputStream(r4);     // Catch: Throwable -> L15 Exception -> L22
        ByteOrder r42 = readByteOrder(r2);     // Catch: Throwable -> L12 Exception -> L14
        this.mExifByteOrder = r42;     // Catch: Throwable -> L12 Exception -> L14
        r2.setByteOrder(r42);     // Catch: Throwable -> L12 Exception -> L14
        short r3 = r2.readShort();     // Catch: Throwable -> L12 Exception -> L14
    L6:
        if (r3 != 20306) goto L8;
    L9:
        r0 = true;
    L10:
        r2.close();
        return r0;
    L8:
        if (r3 != 21330) goto L10;
    L14:
        r1 = r2;
    L12:
        th = th;
        r1 = r2;
    L16:
        if (r1 == null) goto L18;
        r1.close();
    L18:
        throw th;
    L15:
        th = th;
    L19:
        if (r1 == null) goto L21;
        r1.close();
    L21:
        return false;
    }

    private boolean isPngFormat(byte[] r4) {
        int r0 = 0;
    L3:
        byte[] r1 = PNG_SIGNATURE;
        if (r0 >= r1.length) goto L9;
        if (r4[r0] != r1[r0]) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return false;
    L9:
        return true;
    }

    private boolean isRafFormat(byte[] r5) {
        byte[] r4 = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L9;
        if (r5[r1] != r4[r1]) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return false;
    L9:
        return true;
    }

    private boolean isRw2Format(byte[] r4) {
        boolean r0 = false;
        ByteOrderedDataInputStream r1 = null;
        ByteOrderedDataInputStream r2 = new ByteOrderedDataInputStream(r4);     // Catch: Throwable -> L13 Exception -> L20
        ByteOrder r42 = readByteOrder(r2);     // Catch: Throwable -> L10 Exception -> L12
        this.mExifByteOrder = r42;     // Catch: Throwable -> L10 Exception -> L12
        r2.setByteOrder(r42);     // Catch: Throwable -> L10 Exception -> L12
    L6:
        if (r2.readShort() != 85) goto L8;
        r0 = true;
    L8:
        r2.close();
        return r0;
    L12:
        r1 = r2;
    L10:
        th = th;
        r1 = r2;
    L14:
        if (r1 == null) goto L16;
        r1.close();
    L16:
        throw th;
    L13:
        th = th;
    L17:
        if (r1 == null) goto L19;
        r1.close();
    L19:
        return false;
    }

    private static boolean isSeekableFD(FileDescriptor r3) {
        Os.lseek(r3, 0, OsConstants.SEEK_CUR);     // Catch: Exception -> L5
        return true;
    L6:
        if (DEBUG == false) goto L12;
        Log.d("ExifInterface", "The file descriptor for the given input is not seekable");
        return false;
    L12:
        return false;
    }

    private boolean isSupportedDataType(HashMap<String, ExifAttribute> r6) {
        ExifAttribute r0 = r6.get("BitsPerSample");
        if (r0 == null) goto L21;
        int[] r02 = (int[]) r0.getValue(this.mExifByteOrder);
        int[] r1 = BITS_PER_SAMPLE_RGB;
        if (Arrays.equals(r1, r02) == false) goto L8;
        return true;
    L8:
        if (this.mMimeType != 3) goto L21;
        ExifAttribute r62 = r6.get("PhotometricInterpretation");
        if (r62 == null) goto L21;
        int r5 = r62.getIntValue(this.mExifByteOrder);
        if (r5 != 1) goto L16;
        if (Arrays.equals(r02, BITS_PER_SAMPLE_GREYSCALE_2) == false) goto L16;
    L19:
        return true;
    L16:
        if (r5 != 6) goto L21;
        if (Arrays.equals(r02, r1) == true) goto L19;
    L21:
        if (DEBUG == false) goto L25;
        Log.d("ExifInterface", "Unsupported data type value");
        return false;
    L25:
        return false;
    }

    private static boolean isSupportedFormatForSavingAttributes(int r1) {
        if (r1 != 4) goto L5;
        return true;
    L5:
        if (r1 != 13) goto L7;
        return true;
    L7:
        if (r1 == 14) goto L14;
        return false;
    L14:
        return true;
    }

    private boolean isThumbnail(HashMap<String, ExifAttribute> r3) {
        ExifAttribute r0 = r3.get("ImageLength");
        ExifAttribute r32 = r3.get("ImageWidth");
        if (r0 == null) goto L10;
        if (r32 == null) goto L12;
        int r02 = r0.getIntValue(this.mExifByteOrder);
        int r2 = r32.getIntValue(this.mExifByteOrder);
        if (r02 > 512) goto L13;
        if (r2 > 512) goto L14;
        return true;
    L14:
        return false;
    L13:
        return false;
    L12:
        return false;
    L10:
        return false;
    }

    private boolean isWebpFormat(byte[] r4) {
        int r0 = 0;
    L3:
        byte[] r1 = WEBP_SIGNATURE_1;
        if (r0 >= r1.length) goto L9;
        if (r4[r0] != r1[r0]) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return false;
    L9:
        int r02 = 0;
    L10:
        byte[] r12 = WEBP_SIGNATURE_2;
        if (r02 >= r12.length) goto L16;
        if (r4[(WEBP_SIGNATURE_1.length + r02) + 4] != r12[r02]) goto L14;
        r02 = r02 + 1;
        goto L10
    L14:
        return false;
    L16:
        return true;
    }

    private void loadAttributes(InputStream r5) {
        int r1 = 0;
    L66:
    L6:
        th = move-exception;
        addDefaultValuesForCompatibility();
        if (DEBUG == false) goto L65;
        printAttributes();
    L65:
        throw th;
    L10:
        e = e;
    L55:
        boolean r0 = DEBUG;     // Catch: Throwable -> L6
        if (r0 == false) goto L58;
        Log.w("ExifInterface", "Invalid image: ExifInterface got an unsupported image format file (ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e);     // Catch: Throwable -> L6
    L58:
        addDefaultValuesForCompatibility();
        if (r0 == false) goto L70;
        printAttributes();
        return;
    L70:
        return;
    L8:
        e = e;
        goto L55
    L4:
        if (r1 >= EXIF_TAGS.length) goto L13;
        this.mAttributes[r1] = new HashMap();     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        r1 = r1 + 1;     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L66
    L13:
        if (this.mIsExifDataOnly == true) goto L16;
        BufferedInputStream r12 = new BufferedInputStream(r5, 5000);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        this.mMimeType = getMimeType(r12);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        r5 = r12;
    L16:
        if (shouldSupportSeek(this.mMimeType) == false) goto L39;
        SeekableByteOrderedDataInputStream r02 = new SeekableByteOrderedDataInputStream(r5);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        if (this.mIsExifDataOnly == true) goto L20;
        int r52 = this.mMimeType;     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        if (r52 != 12) goto L28;
    L37:
        getHeifAttributes(r02, r52);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
    L38:
        r02.seek(this.mOffsetToExifData);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        setThumbnailData(r02);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
    L51:
        addDefaultValuesForCompatibility();
        if (DEBUG == false) goto L69;
        printAttributes();
        return;
    L69:
        return;
    L28:
        if (r52 == 15) goto L37;
        if (r52 != 7) goto L34;
        getOrfAttributes(r02);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L38
    L34:
        if (r52 != 10) goto L36;
        getRw2Attributes(r02);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L38
    L36:
        getRawAttributes(r02);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L38
    L20:
        if (getStandaloneAttributes(r02) == true) goto L38;
        addDefaultValuesForCompatibility();
        if (DEBUG == false) goto L68;
        printAttributes();
        return;
    L68:
        return;
    L39:
        ByteOrderedDataInputStream r13 = new ByteOrderedDataInputStream(r5);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        int r53 = this.mMimeType;     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        if (r53 != 4) goto L43;
        getJpegAttributes(r13, 0, 0);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L51
    L43:
        if (r53 != 13) goto L46;
        getPngAttributes(r13);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L51
    L46:
        if (r53 != 9) goto L49;
        getRafAttributes(r13);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L51
    L49:
        if (r53 != 14) goto L51;
        getWebpAttributes(r13);     // Catch: Throwable -> L6 UnsupportedOperationException -> L8 IOException -> L10
        goto L51
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream r3) {
        ByteOrder r0 = readByteOrder(r3);
        this.mExifByteOrder = r0;
        r3.setByteOrder(r0);
        int r02 = r3.readUnsignedShort();
        int r2 = this.mMimeType;
        if (r2 != 7) goto L5;
    L11:
        int r22 = r3.readInt();
        if (r22 < 8) goto L17;
        int r23 = r22 - 8;
        if (r23 <= 0) goto L19;
        r3.skipFully(r23);
        return;
    L19:
        return;
    L17:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("Invalid first Ifd offset: ", r22);
        return;
    L5:
        if (r2 == 10) goto L11;
        if (r02 == 42) goto L11;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid start code: ", Integer.toHexString(r02));
    }

    private void printAttributes() {
        int r0 = 0;
    L4:
        if (r0 >= this.mAttributes.length) goto L10;
        Log.d("ExifInterface", "The size of tag group[" + r0 + "]: " + this.mAttributes[r0].size());
        Iterator<Map.Entry<String, ExifAttribute>> r1 = this.mAttributes[r0].entrySet().iterator();
    L7:
        if (r1.hasNext() == false) goto L9;
        Map.Entry<String, ExifAttribute> r3 = r1.next();
        ExifAttribute r4 = r3.getValue();
        Log.d("ExifInterface", "tagName: " + r3.getKey() + ", tagType: " + r4.toString() + ", tagValue: '" + r4.getStringValue(this.mExifByteOrder) + "'");
        goto L7
    L9:
        r0 = r0 + 1;
        goto L4
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream r2) {
        short r1 = r2.readShort();
        if (r1 == 18761) goto L14;
        if (r1 == 19789) goto L7;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Invalid byte order: ", Integer.toHexString(r1));
        return null;
    L7:
        if (DEBUG == false) goto L10;
        Log.d("ExifInterface", "readExifSegment: Byte Align MM");
    L10:
        return ByteOrder.BIG_ENDIAN;
    L14:
        if (DEBUG == false) goto L17;
        Log.d("ExifInterface", "readExifSegment: Byte Align II");
    L17:
        return ByteOrder.LITTLE_ENDIAN;
    }

    private void readExifSegment(byte[] r2, int r3) {
        SeekableByteOrderedDataInputStream r0 = new SeekableByteOrderedDataInputStream(r2);
        parseTiffHeaders(r0);
        readImageFileDirectory(r0, r3);
    }

    private void readImageFileDirectory(SeekableByteOrderedDataInputStream r26, int r27) {
        int r2 = r27;
        this.mAttributesOffsets.add(Integer.valueOf(r26.position()));
        short r3 = r26.readShort();
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "numberOfDirectoryEntry: " + r3);
    L5:
        if (r3 <= 0) goto L152;
        short r6 = 0;
    L8:
        if (r6 >= r3) goto L123;
        int r10 = r26.readUnsignedShort();
        int r11 = r26.readUnsignedShort();
        int r14 = r26.readInt();
        long r12 = r26.position() + 4;
        ExifTag r4 = sExifTagMapsForReading[r2].get(Integer.valueOf(r10));
        boolean r7 = DEBUG;
        if (r7 == false) goto L17;
        Integer r8 = Integer.valueOf(r2);
        long r20 = 4;
        Integer r15 = Integer.valueOf(r10);
        int r16 = 4;
        if (r4 == null) goto L15;
        String r9 = r4.name;
    L14:
        short r22 = r3;
        short r23 = r6;
        Log.d("ExifInterface", String.format("ifdType: %d, tagNumber: %d, tagName: %s, dataFormat: %d, numberOfComponents: %d", new Object[]{r8, r15, r9, Integer.valueOf(r11), Integer.valueOf(r14)}));
    L19:
        if (r4 != null) goto L23;
        if (r7 == false) goto L22;
        Log.d("ExifInterface", "Skip the tag entry since tag number is not defined: " + r10);
    L22:
        ExifTag r24 = r4;
    L44:
        long r82 = 0;
    L41:
        boolean r32 = false;
    L45:
        if (r32 == true) goto L48;
        r26.seek(r12);
    L122:
        r6 = (short) (r23 + 1);
        r2 = r27;
        r3 = r22;
        goto L8
    L48:
        if (r82 <= r20) goto L65;
        int r33 = r26.readInt();
        if (r7 == false) goto L53;
        Log.d("ExifInterface", "seek to data offset: " + r33);
    L53:
        if (this.mMimeType != 7) goto L63;
        boolean r17 = r7;
        ExifTag r152 = r24;
        if ("MakerNote".equals(r152.name) == false) goto L59;
        this.mOrfMakerNoteOffset = r33;
    L57:
        long r202 = r12;
    L64:
        r26.seek(r33);
    L66:
        Integer r25 = sExifPointerTagMap.get(Integer.valueOf(r10));
        if (r17 == false) goto L70;
        Log.d("ExifInterface", "nextIfdType: " + r25 + " byteCount: " + r82);
    L70:
        if (r25 == null) goto L105;
        if (r11 != 3) goto L73;
        int r34 = r26.readUnsignedShort();
    L81:
        long r35 = r34;
    L85:
        if (r17 == false) goto L88;
        Log.d("ExifInterface", String.format("Offset: %d, tagName: %s", new Object[]{Long.valueOf(r35), r152.name}));
    L88:
        if (r35 > 0) goto L90;
    L99:
        if (r17 == false) goto L104;
        String r28 = "Skip jump into the IFD since its offset is invalid: " + r35;
        if (r26.length() == (-1)) goto L103;
        r28 = r28 + " (total length: " + r26.length() + ")";
    L103:
        Log.d("ExifInterface", r28);
    L104:
        r26.seek(r202);
        goto L122
    L90:
        if (r26.length() == (-1)) goto L94;
        if (r35 >= r26.length()) goto L99;
    L94:
        if (this.mAttributesOffsets.contains(Integer.valueOf((int) r35)) == true) goto L97;
        r26.seek(r35);
        readImageFileDirectory(r26, r25.intValue());
        goto L104
    L97:
        if (r17 == false) goto L104;
        Log.d("ExifInterface", "Skip jump into the IFD since it has already been read: IfdType " + r25 + " (at " + r35 + ")");
        goto L104
    L73:
        if (r11 == r16) goto L83;
        if (r11 != 8) goto L76;
        r34 = r26.readShort();
        goto L81
    L76:
        if (r11 != 9) goto L78;
    L80:
        r34 = r26.readInt();
        goto L81
    L78:
        if (r11 == 13) goto L80;
        r35 = -1;
        goto L85
    L83:
        r35 = r26.readUnsignedInt();
        goto L85
    L105:
        int r29 = r26.position() + this.mOffsetToExifData;
        byte[] r72 = new byte[(int) r82];
        r26.readFully(r72);
        long r83 = r29;
        ExifTag r210 = r152;
        long r73 = r202;
        ExifAttribute r122 = new ExifAttribute(r11, r14, r83, r72);
        this.mAttributes[r27].put(r210.name, r122);
        if ("DNGVersion".equals(r210.name) == false) goto L109;
        this.mMimeType = 3;
    L109:
        if ("Make".equals(r210.name) == true) goto L113;
        if ("Model".equals(r210.name) == true) goto L113;
    L115:
        if ("Compression".equals(r210.name) == false) goto L120;
        if (r122.getIntValue(this.mExifByteOrder) != 65535) goto L120;
    L118:
        this.mMimeType = 8;
    L120:
        if (r26.position() == r73) goto L122;
        r26.seek(r73);
    L113:
        if (r122.getStringValue(this.mExifByteOrder).contains("PENTAX") == true) goto L118;
    L59:
        if (r2 != 6) goto L57;
        if ("ThumbnailImage".equals(r152.name) == false) goto L57;
        this.mOrfThumbnailOffset = r33;
        this.mOrfThumbnailLength = r14;
        ExifAttribute r62 = ExifAttribute.createUShort(6, this.mExifByteOrder);
        r202 = r12;
        ExifAttribute r74 = ExifAttribute.createULong(this.mOrfThumbnailOffset, this.mExifByteOrder);
        ExifAttribute r211 = ExifAttribute.createULong(this.mOrfThumbnailLength, this.mExifByteOrder);
        this.mAttributes[r16].put("Compression", r62);
        this.mAttributes[r16].put("JPEGInterchangeFormat", r74);
        this.mAttributes[r16].put("JPEGInterchangeFormatLength", r211);
        goto L64
    L63:
        r17 = r7;
        r202 = r12;
        r152 = r24;
        goto L64
    L65:
        r17 = r7;
        r202 = r12;
        r152 = r24;
        goto L66
    L23:
        if (r11 > 0) goto L25;
    L26:
        r24 = r4;
        if (r7 == false) goto L44;
        Log.d("ExifInterface", "Skip the tag entry since data format is invalid: " + r11);
        goto L44
    L25:
        if (r11 >= IFD_FORMAT_BYTES_PER_FORMAT.length) goto L26;
        if (r4.isFormatCompatible(r11) == true) goto L31;
        if (r7 == false) goto L22;
        Log.d("ExifInterface", "Skip the tag entry since data format (" + IFD_FORMAT_NAMES[r11] + ") is unexpected for tag: " + r4.name);
        goto L22
    L31:
        if (r11 != 7) goto L33;
        r11 = r4.primaryFormat;
    L33:
        r24 = r4;
        r82 = r14 * r6[r11];
        if (r82 >= 0) goto L36;
    L39:
        if (r7 == false) goto L41;
        Log.d("ExifInterface", "Skip the tag entry since the number of components is invalid: " + r14);
        goto L41
    L36:
        if (r82 > 2147483647L) goto L39;
        r32 = true;
        goto L45
    L15:
        r9 = null;
        goto L14
    L17:
        r22 = r3;
        r23 = r6;
        r20 = 4;
        r16 = 4;
        goto L19
    L123:
        int r212 = r26.readInt();
        boolean r36 = DEBUG;
        if (r36 == false) goto L126;
        Log.d("ExifInterface", String.format("nextIfdOffset: %d", new Object[]{Integer.valueOf(r212)}));
    L126:
        long r63 = r212;
        if (r63 > 0) goto L129;
        if (r36 == false) goto L151;
        Log.d("ExifInterface", "Stop reading file since a wrong offset may cause an infinite loop: " + r212);
        return;
    L151:
        return;
    L129:
        if (this.mAttributesOffsets.contains(Integer.valueOf(r212)) == true) goto L138;
        r26.seek(r63);
        if (this.mAttributes[4].isEmpty() == false) goto L135;
        readImageFileDirectory(r26, 4);
        return;
    L135:
        if (this.mAttributes[5].isEmpty() == false) goto L149;
        readImageFileDirectory(r26, 5);
        return;
    L149:
        return;
    L138:
        if (r36 == false) goto L150;
        Log.d("ExifInterface", "Stop reading file since re-reading an IFD may cause an infinite loop: " + r212);
        return;
    L150:
        return;
    }

    private void removeAttribute(String r3) {
        int r0 = 0;
    L4:
        if (r0 >= EXIF_TAGS.length) goto L6;
        this.mAttributes[r0].remove(r3);
        r0 = r0 + 1;
        goto L4
    }

    private void replaceInvalidTags(int r3, String r4, String r5) {
        if (this.mAttributes[r3].isEmpty() == false) goto L5;
        return;
    L5:
        if (this.mAttributes[r3].get(r4) == null) goto L9;
        HashMap<String, ExifAttribute> r0 = this.mAttributes[r3];
        r0.put(r5, r0.get(r4));
        this.mAttributes[r3].remove(r4);
        return;
    }

    private void retrieveJpegImageSize(SeekableByteOrderedDataInputStream r5, int r6) {
        ExifAttribute r0 = this.mAttributes[r6].get("ImageLength");
        ExifAttribute r1 = this.mAttributes[r6].get("ImageWidth");
        if (r0 == null) goto L5;
        if (r1 == null) goto L5;
        return;
    L5:
        ExifAttribute r02 = this.mAttributes[r6].get("JPEGInterchangeFormat");
        ExifAttribute r12 = this.mAttributes[r6].get("JPEGInterchangeFormatLength");
        if (r02 == null) goto L11;
        if (r12 == null) goto L12;
        int r13 = r02.getIntValue(this.mExifByteOrder);
        int r03 = r02.getIntValue(this.mExifByteOrder);
        r5.seek(r13);
        byte[] r04 = new byte[r03];
        r5.readFully(r04);
        getJpegAttributes(new ByteOrderedDataInputStream(r04), r13, r6);
        return;
    L12:
        return;
    }

    private void saveJpegAttributes(InputStream r12, OutputStream r13) {
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "saveJpegAttributes starting with (inputStream: " + r12 + ", outputStream: " + r13 + ")");
    L5:
        ByteOrderedDataInputStream r0 = new ByteOrderedDataInputStream(r12);
        ByteOrderedDataOutputStream r122 = new ByteOrderedDataOutputStream(r13, ByteOrder.BIG_ENDIAN);
        if (r0.readByte() != (-1)) goto L61;
        r122.writeByte(-1);
        if (r0.readByte() != (-40)) goto L59;
        r122.writeByte(-40);
        r122.writeByte(-1);
        r122.writeByte(-31);
        this.mOffsetToExifData = writeExifSegment(r122);
        if (this.mXmpFromSeparateMarker == null) goto L12;
        r122.write(-1);
        r122.writeByte(-31);
        byte[] r3 = IDENTIFIER_XMP_APP1;
        r122.writeUnsignedShort((r3.length + 2) + this.mXmpFromSeparateMarker.bytes.length);
        r122.write(r3);
        r122.write(this.mXmpFromSeparateMarker.bytes);
        this.mFileOnDiskContainsSeparateXmpMarker = true;
    L12:
        byte[] r32 = new byte[4096];
    L14:
        if (r0.readByte() != (-1)) goto L57;
    L15:
        byte r4 = r0.readByte();
        if (r4 == (-1)) goto L15;
        if (r4 == (-39)) goto L55;
        if (r4 == (-38)) goto L55;
        if (r4 != (-31)) goto L23;
        int r7 = r0.readUnsignedShort();
        int r8 = r7 - 2;
        if (r8 < 0) goto L53;
        byte[] r5 = IDENTIFIER_XMP_APP1;
        if (r8 < r5.length) goto L36;
        byte[] r9 = new byte[r5.length];
    L40:
        if (r9 == null) goto L46;
        r0.readFully(r9);
        if (ExifInterfaceUtils.startsWith(r9, IDENTIFIER_EXIF_APP1) == true) goto L45;
        if (ExifInterfaceUtils.startsWith(r9, r5) == false) goto L46;
    L45:
        r0.skipFully(r8 - r9.length);
    L46:
        r122.writeByte(-1);
        r122.writeByte(r4);
        r122.writeUnsignedShort(r7);
        if (r9 == null) goto L49;
        r8 = r8 - r9.length;
        r122.write(r9);
    L49:
        if (r8 <= 0) goto L14;
        int r42 = r0.read(r32, 0, Math.min(r8, 4096));
        if (r42 < 0) goto L14;
        r122.write(r32, 0, r42);
        r8 = r8 - r42;
        goto L49
    L36:
        byte[] r92 = IDENTIFIER_EXIF_APP1;
        if (r8 < r92.length) goto L39;
        r9 = new byte[r92.length];
        goto L40
    L39:
        r9 = null;
        goto L40
    L53:
        Model$$ExternalSyntheticBUOutline0.m("Invalid length");
        return;
    L23:
        r122.writeByte(-1);
        r122.writeByte(r4);
        int r43 = r0.readUnsignedShort();
        r122.writeUnsignedShort(r43);
        int r44 = r43 - 2;
        if (r44 < 0) goto L29;
    L25:
        if (r44 <= 0) goto L14;
        int r52 = r0.read(r32, 0, Math.min(r44, 4096));
        if (r52 < 0) goto L14;
        r122.write(r32, 0, r52);
        r44 = r44 - r52;
        goto L25
    L29:
        Model$$ExternalSyntheticBUOutline0.m("Invalid length");
        return;
    L55:
        r122.writeByte(-1);
        r122.writeByte(r4);
        ExifInterfaceUtils.copy(r0, r122);
        return;
    L57:
        Model$$ExternalSyntheticBUOutline0.m("Invalid marker");
        return;
    L59:
        Model$$ExternalSyntheticBUOutline0.m("Invalid marker");
        return;
    L61:
        Model$$ExternalSyntheticBUOutline0.m("Invalid marker");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0076 -> B:10:0x0042). Please report as a decompilation issue!!! */
    private void savePngAttributes(InputStream r9, OutputStream r10) {
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "savePngAttributes starting with (inputStream: " + r9 + ", outputStream: " + r10 + ")");
    L5:
        ByteOrderedDataInputStream r0 = new ByteOrderedDataInputStream(r9);
        ByteOrderedDataOutputStream r92 = new ByteOrderedDataOutputStream(r10, ByteOrder.BIG_ENDIAN);
        ExifInterfaceUtils.copy(r0, r92, PNG_SIGNATURE.length);
        boolean r1 = true;
        if (this.mXmpFromSeparateMarker == null) goto L8;
    L9:
        boolean r102 = true;
    L10:
        if (r1 == true) goto L15;
        if (r102 == true) goto L15;
        ExifInterfaceUtils.copy(r0, r92);
        return;
    L15:
        int r3 = r0.readInt();
        int r4 = r0.readInt();
        if (r4 == 1229472850) goto L17;
        if (r4 != 1700284774) goto L31;
        if (r1 == false) goto L31;
        writePngExifChunk(r92);
        r0.skipFully(r3 + 4);
        r1 = false;
    L31:
        if (r4 != 1767135348) goto L41;
        byte[] r5 = PNG_ITXT_XMP_KEYWORD;
        if (r3 < r5.length) goto L41;
        int r6 = r5.length;
        byte[] r7 = new byte[r6];
        r0.readFully(r7);
        int r62 = (r3 - r6) + 4;
        if (Arrays.equals(r7, r5) == true) goto L37;
        r92.writeInt(r3);
        r92.writeInt(r4);
        r92.write(r7);
        ExifInterfaceUtils.copy(r0, r92, r62);
        goto L10
    L37:
        if (this.mXmpFromSeparateMarker == null) goto L39;
        writePngXmpItxtChunk(r92);
    L39:
        r0.skipFully(r62);
    L25:
        r102 = false;
    L41:
        r92.writeInt(r3);
        r92.writeInt(r4);
        ExifInterfaceUtils.copy(r0, r92, r3 + 4);
        goto L10
    L17:
        r92.writeInt(r3);
        r92.writeInt(r4);
        ExifInterfaceUtils.copy(r0, r92, r3 + 4);
        if (this.mOffsetToExifData != 0) goto L21;
        writePngExifChunk(r92);
        r1 = false;
    L21:
        if (this.mXmpFromSeparateMarker == null) goto L10;
        if (this.mFileOnDiskContainsSeparateXmpMarker == true) goto L10;
        writePngXmpItxtChunk(r92);
        goto L25
    L8:
        if (this.mFileOnDiskContainsSeparateXmpMarker == false) goto L25;
        goto L9
    }

    private void saveWebpAttributes(InputStream r22, OutputStream r23) {
        if (DEBUG == false) goto L5;
        Log.d("ExifInterface", "saveWebpAttributes starting with (inputStream: " + r22 + ", outputStream: " + r23 + ")");
    L5:
        ByteOrder r4 = ByteOrder.LITTLE_ENDIAN;
        ByteOrderedDataInputStream r3 = new ByteOrderedDataInputStream(r22, r4);
        ByteOrderedDataOutputStream r1 = new ByteOrderedDataOutputStream(r23, r4);
        byte[] r2 = WEBP_SIGNATURE_1;
        ExifInterfaceUtils.copy(r3, r1, r2.length);
        int r5 = r3.readInt();
        byte[] r6 = WEBP_SIGNATURE_2;
        r3.skipFully(r6.length);
        ByteArrayOutputStream r7 = null;
        ByteArrayOutputStream r8 = new ByteArrayOutputStream();     // Catch: Throwable -> L86 Exception -> L88
        ByteOrderedDataOutputStream r9 = new ByteOrderedDataOutputStream(r8, r4);     // Catch: Throwable -> L15 Exception -> L17
        int r42 = this.mOffsetToExifData;     // Catch: Throwable -> L15 Exception -> L17
        if (r42 == 0) goto L19;
        ExifInterfaceUtils.copy(r3, r9, (r42 - ((r2.length + 4) + r6.length)) - 8);     // Catch: Throwable -> L15 Exception -> L17
        r3.skipFully(4);     // Catch: Throwable -> L15 Exception -> L17
        int r24 = r3.readInt();     // Catch: Throwable -> L15 Exception -> L17
        if ((r24 % 2) == 0) goto L12;
        r24 = r24 + 1;     // Catch: Throwable -> L15 Exception -> L17
    L12:
        r3.skipFully(r24);     // Catch: Throwable -> L15 Exception -> L17
        int r25 = writeExifSegment(r9);     // Catch: Throwable -> L15 Exception -> L17
    L14:
        int r16 = -1;
    L80:
        ExifInterfaceUtils.copy(r3, r9, (r5 + 8) - r3.position());     // Catch: Throwable -> L15 Exception -> L17
        int r43 = r8.size();     // Catch: Throwable -> L15 Exception -> L17
        byte[] r52 = WEBP_SIGNATURE_2;     // Catch: Throwable -> L15 Exception -> L17
        r1.writeInt(r43 + r52.length);     // Catch: Throwable -> L15 Exception -> L17
        r1.write(r52);     // Catch: Throwable -> L15 Exception -> L17
        if (r25 == r16) goto L83;
        this.mOffsetToExifData = r1.mOutputStream.size() + r25;     // Catch: Throwable -> L15 Exception -> L17
    L83:
        r8.writeTo(r1);     // Catch: Throwable -> L15 Exception -> L17
        ExifInterfaceUtils.copy(r3, r1);     // Catch: Throwable -> L15 Exception -> L17
        ExifInterfaceUtils.closeQuietly(r8);
        return;
    L19:
        byte[] r26 = new byte[4];     // Catch: Throwable -> L15 Exception -> L17
        r3.readFully(r26);     // Catch: Throwable -> L15 Exception -> L17
        byte[] r44 = WEBP_CHUNK_TYPE_VP8X;     // Catch: Throwable -> L15 Exception -> L17
        boolean r13 = false;
        boolean r14 = true;
        if (Arrays.equals(r26, r44) == false) goto L39;
        int r27 = r3.readInt();     // Catch: Throwable -> L15 Exception -> L17
        if ((r27 % 2) != 1) goto L24;
        int r62 = r27 + 1;     // Catch: Throwable -> L15 Exception -> L17
    L25:
        byte[] r63 = new byte[r62];     // Catch: Throwable -> L15 Exception -> L17
        r3.readFully(r63);     // Catch: Throwable -> L15 Exception -> L17
        byte r15 = (byte) (r63[0] | 8);     // Catch: Throwable -> L15 Exception -> L17
        r63[0] = r15;     // Catch: Throwable -> L15 Exception -> L17
        if (((r15 >> 1) & 1) != 1) goto L28;
        r13 = true;
    L28:
        r9.write(r44);     // Catch: Throwable -> L15 Exception -> L17
        r9.writeInt(r27);     // Catch: Throwable -> L15 Exception -> L17
        r9.write(r63);     // Catch: Throwable -> L15 Exception -> L17
        if (r13 == false) goto L38;
        copyChunksUpToGivenChunkType(r3, r9, WEBP_CHUNK_TYPE_ANIM, null);     // Catch: Throwable -> L15 Exception -> L17
    L31:
        byte[] r28 = new byte[4];     // Catch: Throwable -> L15 Exception -> L17
        r3.readFully(r28);     // Catch: Throwable -> L15 Exception -> L17 EOFException -> L34
        boolean r45 = !Arrays.equals(r28, WEBP_CHUNK_TYPE_ANMF);
    L35:
        if (r45 == true) goto L36;
        copyWebPChunk(r3, r9, r28);     // Catch: Throwable -> L15 Exception -> L17
        goto L31
    L36:
        r25 = writeExifSegment(r9);     // Catch: Throwable -> L15 Exception -> L17
    L34:
        r45 = true;
        goto L35
    L38:
        copyChunksUpToGivenChunkType(r3, r9, WEBP_CHUNK_TYPE_VP8, WEBP_CHUNK_TYPE_VP8L);     // Catch: Throwable -> L15 Exception -> L17
        r25 = writeExifSegment(r9);     // Catch: Throwable -> L15 Exception -> L17
        goto L14
    L24:
        r62 = r27;
        goto L25
    L39:
        byte[] r64 = WEBP_CHUNK_TYPE_VP8;     // Catch: Throwable -> L15 Exception -> L17
        if (Arrays.equals(r26, r64) == false) goto L42;
    L45:
        int r72 = r3.readInt();     // Catch: Throwable -> L15 Exception -> L17
        if ((r72 % 2) != 1) goto L49;
        int r152 = r72 + 1;     // Catch: Throwable -> L15 Exception -> L17
    L50:
        byte[] r12 = new byte[3];     // Catch: Throwable -> L15 Exception -> L17
        if (Arrays.equals(r26, r64) == false) goto L57;
        r3.readFully(r12);     // Catch: Throwable -> L15 Exception -> L17
        byte[] r11 = new byte[3];     // Catch: Throwable -> L15 Exception -> L17
        r3.readFully(r11);     // Catch: Throwable -> L15 Exception -> L17
        if (Arrays.equals(WEBP_VP8_SIGNATURE, r11) == false) goto L56;
        int r112 = r3.readInt();     // Catch: Throwable -> L15 Exception -> L17
        r16 = -1;
        int r10 = (r112 >> 16) & 16383;     // Catch: Throwable -> L15 Exception -> L17
        int r20 = r152 - 10;
        int r153 = r112 & 16383;     // Catch: Throwable -> L15 Exception -> L17
        r14 = false;
    L69:
        r9.write(r44);     // Catch: Throwable -> L15 Exception -> L17
        r9.writeInt(10);     // Catch: Throwable -> L15 Exception -> L17
        byte[] r46 = new byte[10];     // Catch: Throwable -> L15 Exception -> L17
        if (r14 == false) goto L72;
        r46[0] = (byte) (r46[0] | 16);     // Catch: Throwable -> L15 Exception -> L17
    L72:
        r46[0] = (byte) (r46[0] | 8);     // Catch: Throwable -> L15 Exception -> L17
        int r154 = r153 - 1;
        int r102 = r10 - 1;
        r46[4] = (byte) r154;     // Catch: Throwable -> L15 Exception -> L17
        r46[5] = (byte) (r154 >> 8);     // Catch: Throwable -> L15 Exception -> L17
        r46[6] = (byte) (r154 >> 16);     // Catch: Throwable -> L15 Exception -> L17
        r46[7] = (byte) r102;     // Catch: Throwable -> L15 Exception -> L17
        r46[8] = (byte) (r102 >> 8);     // Catch: Throwable -> L15 Exception -> L17
        r46[9] = (byte) (r102 >> 16);     // Catch: Throwable -> L15 Exception -> L17
        r9.write(r46);     // Catch: Throwable -> L15 Exception -> L17
        r9.write(r26);     // Catch: Throwable -> L15 Exception -> L17
        r9.writeInt(r72);     // Catch: Throwable -> L15 Exception -> L17
        if (Arrays.equals(r26, r64) == false) goto L77;
        r9.write(r12);     // Catch: Throwable -> L15 Exception -> L17
        r9.write(WEBP_VP8_SIGNATURE);     // Catch: Throwable -> L15 Exception -> L17
        r9.writeInt(r112);     // Catch: Throwable -> L15 Exception -> L17
    L79:
        ExifInterfaceUtils.copy(r3, r9, r20);     // Catch: Throwable -> L15 Exception -> L17
        r25 = writeExifSegment(r9);     // Catch: Throwable -> L15 Exception -> L17
        goto L80
    L77:
        if (Arrays.equals(r26, WEBP_CHUNK_TYPE_VP8L) == false) goto L79;
        r9.write(47);     // Catch: Throwable -> L15 Exception -> L17
        r9.writeInt(r112);     // Catch: Throwable -> L15 Exception -> L17
        goto L79
    L56:
        throw new IOException("Error checking VP8 signature");     // Catch: Throwable -> L15 Exception -> L17
    L57:
        r16 = -1;
        if (Arrays.equals(r26, WEBP_CHUNK_TYPE_VP8L) == true) goto L60;
        r20 = r152;
        r10 = 0;
        r112 = 0;
        r14 = false;
        r153 = 0;
        goto L69
    L60:
        if (r3.readByte() != 47) goto L67;
        r112 = r3.readInt();     // Catch: Throwable -> L15 Exception -> L17
        int r103 = (r112 & 16383) + 1;     // Catch: Throwable -> L15 Exception -> L17
        int r18 = ((r112 & 268419072) >>> 14) + 1;     // Catch: Throwable -> L15 Exception -> L17
        if ((r112 & 268435456) != 0) goto L65;
        r14 = false;
    L65:
        r20 = r152 - 5;
        r153 = r103;
        r10 = r18;
        goto L69
    L67:
        throw new IOException("Error checking VP8L signature");     // Catch: Throwable -> L15 Exception -> L17
    L49:
        r152 = r72;
        goto L50
    L42:
        if (Arrays.equals(r26, WEBP_CHUNK_TYPE_VP8L) == true) goto L45;
        r25 = -1;
    L17:
        e = e;
    L90:
        throw new IOException("Failed to save WebP file", e);     // Catch: Throwable -> L86
    L15:
        th = th;
        r7 = r8;
    L91:
        ExifInterfaceUtils.closeQuietly(r7);
        throw th;
    L86:
        th = th;
    L88:
        e = e;
        goto L90
    }

    private void setThumbnailData(ByteOrderedDataInputStream r5) {
        HashMap<String, ExifAttribute> r0 = this.mAttributes[4];
        ExifAttribute r1 = r0.get("Compression");
        if (r1 == null) goto L16;
        int r12 = r1.getIntValue(this.mExifByteOrder);
        this.mThumbnailCompression = r12;
        if (r12 == 1) goto L13;
        if (r12 != 6) goto L8;
        handleThumbnailFromJfif(r5, r0);
        return;
    L8:
        if (r12 == 7) goto L13;
        return;
    L13:
        if (isSupportedDataType(r0) == false) goto L18;
        handleThumbnailFromStrips(r5, r0);
        return;
    L18:
        return;
    L16:
        this.mThumbnailCompression = 6;
        handleThumbnailFromJfif(r5, r0);
    }

    private static boolean shouldSupportSeek(int r1) {
        if (r1 != 4) goto L5;
        return false;
    L5:
        if (r1 != 9) goto L7;
        return false;
    L7:
        if (r1 != 13) goto L9;
        return false;
    L9:
        if (r1 == 14) goto L17;
        return true;
    L17:
        return false;
    }

    private void swapBasedOnImageSize(int r7, int r8) {
        if (this.mAttributes[r7].isEmpty() == true) goto L28;
        if (this.mAttributes[r8].isEmpty() == true) goto L28;
        ExifAttribute r0 = this.mAttributes[r7].get("ImageLength");
        ExifAttribute r3 = this.mAttributes[r7].get("ImageWidth");
        ExifAttribute r2 = this.mAttributes[r8].get("ImageLength");
        ExifAttribute r4 = this.mAttributes[r8].get("ImageWidth");
        if (r0 == null) goto L24;
        if (r3 == null) goto L24;
        if (r2 == null) goto L20;
        if (r4 == null) goto L20;
        int r02 = r0.getIntValue(this.mExifByteOrder);
        int r1 = r3.getIntValue(this.mExifByteOrder);
        int r22 = r2.getIntValue(this.mExifByteOrder);
        int r32 = r4.getIntValue(this.mExifByteOrder);
        if (r02 >= r22) goto L31;
        if (r1 >= r32) goto L32;
        HashMap<String, ExifAttribute>[] r6 = this.mAttributes;
        HashMap<String, ExifAttribute> r03 = r6[r7];
        r6[r7] = r6[r8];
        r6[r8] = r03;
        return;
    L32:
        return;
    L31:
        return;
    L20:
        if (DEBUG == false) goto L33;
        Log.d("ExifInterface", "Second image does not contain valid size information");
        return;
    L33:
        return;
    L24:
        if (DEBUG == false) goto L34;
        Log.d("ExifInterface", "First image does not contain valid size information");
        return;
    L34:
        return;
    L28:
        if (DEBUG == false) goto L35;
        Log.d("ExifInterface", "Cannot perform swap since only one image data exists");
        return;
    }

    private static void updateCrcWithInt(CRC32 r1, int r2) {
        r1.update(r2 >>> 24);
        r1.update(r2 >>> 16);
        r1.update(r2 >>> 8);
        r1.update(r2);
    }

    private void updateImageSizeValues(SeekableByteOrderedDataInputStream r11, int r12) {
        ExifAttribute r0 = this.mAttributes[r12].get("DefaultCropSize");
        ExifAttribute r1 = this.mAttributes[r12].get("SensorTopBorder");
        ExifAttribute r2 = this.mAttributes[r12].get("SensorLeftBorder");
        ExifAttribute r3 = this.mAttributes[r12].get("SensorBottomBorder");
        ExifAttribute r4 = this.mAttributes[r12].get("SensorRightBorder");
        if (r0 == null) goto L24;
        int r112 = r0.format;
        ByteOrder r13 = this.mExifByteOrder;
        if (r112 != 5) goto L14;
        Rational[] r113 = (Rational[]) r0.getValue(r13);
        if (r113 != null) goto L9;
    L12:
        Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(r113));
        return;
    L9:
        if (r113.length != 2) goto L12;
        ExifAttribute r02 = ExifAttribute.createURational(r113[0], this.mExifByteOrder);
        ExifAttribute r114 = ExifAttribute.createURational(r113[1], this.mExifByteOrder);
    L20:
        this.mAttributes[r12].put("ImageWidth", r02);
        this.mAttributes[r12].put("ImageLength", r114);
        return;
    L14:
        int[] r115 = (int[]) r0.getValue(r13);
        if (r115 != null) goto L17;
    L22:
        Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(r115));
        return;
    L17:
        if (r115.length != 2) goto L22;
        r02 = ExifAttribute.createUShort(r115[0], this.mExifByteOrder);
        r114 = ExifAttribute.createUShort(r115[1], this.mExifByteOrder);
        goto L20
    L24:
        if (r1 == null) goto L33;
        if (r2 == null) goto L33;
        if (r3 == null) goto L33;
        if (r4 == null) goto L33;
        int r116 = r1.getIntValue(this.mExifByteOrder);
        int r03 = r3.getIntValue(this.mExifByteOrder);
        int r14 = r4.getIntValue(this.mExifByteOrder);
        int r22 = r2.getIntValue(this.mExifByteOrder);
        if (r03 <= r116) goto L35;
        if (r14 <= r22) goto L36;
        ExifAttribute r117 = ExifAttribute.createUShort(r03 - r116, this.mExifByteOrder);
        ExifAttribute r04 = ExifAttribute.createUShort(r14 - r22, this.mExifByteOrder);
        this.mAttributes[r12].put("ImageLength", r117);
        this.mAttributes[r12].put("ImageWidth", r04);
        return;
    L36:
        return;
    L35:
        return;
    L33:
        retrieveJpegImageSize(r11, r12);
    }

    private void validateImages() {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute r3 = this.mAttributes[1].get("PixelXDimension");
        ExifAttribute r4 = this.mAttributes[1].get("PixelYDimension");
        if (r3 == null) goto L7;
        if (r4 == null) goto L7;
        this.mAttributes[0].put("ImageWidth", r3);
        this.mAttributes[0].put("ImageLength", r4);
    L7:
        if (this.mAttributes[4].isEmpty() == false) goto L12;
        if (isThumbnail(this.mAttributes[5]) == false) goto L12;
        HashMap<String, ExifAttribute>[] r32 = this.mAttributes;
        r32[4] = r32[5];
        r32[5] = new HashMap();
    L12:
        if (isThumbnail(this.mAttributes[4]) == true) goto L14;
        Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
    L14:
        replaceInvalidTags(0, "ThumbnailOrientation", "Orientation");
        replaceInvalidTags(0, "ThumbnailImageLength", "ImageLength");
        replaceInvalidTags(0, "ThumbnailImageWidth", "ImageWidth");
        replaceInvalidTags(5, "ThumbnailOrientation", "Orientation");
        replaceInvalidTags(5, "ThumbnailImageLength", "ImageLength");
        replaceInvalidTags(5, "ThumbnailImageWidth", "ImageWidth");
        replaceInvalidTags(4, "Orientation", "ThumbnailOrientation");
        replaceInvalidTags(4, "ImageLength", "ThumbnailImageLength");
        replaceInvalidTags(4, "ImageWidth", "ThumbnailImageWidth");
    }

    private int writeExifSegment(ByteOrderedDataOutputStream r20) {
        ExifTag[][] r2 = EXIF_TAGS;
        int[] r3 = new int[r2.length];
        int[] r22 = new int[r2.length];
        ExifTag[] r4 = EXIF_POINTER_TAGS;
        int r5 = r4.length;
        int r7 = 0;
    L3:
        if (r7 >= r5) goto L6;
        removeAttribute(r4[r7].name);
        r7 = r7 + 1;
        goto L3
    L6:
        if (this.mHasThumbnail == true) goto L8;
    L11:
        int r42 = 0;
    L12:
        int r10 = EXIF_TAGS.length;
        HashMap<String, ExifAttribute>[] r11 = this.mAttributes;
        if (r42 >= r10) goto L22;
        Iterator<Map.Entry<String, ExifAttribute>> r102 = r11[r42].entrySet().iterator();
    L16:
        if (r102.hasNext() == false) goto L20;
        if (r102.next().getValue() != null) goto L16;
        r102.remove();
        goto L16
    L20:
        r42 = r42 + 1;
        goto L12
    L22:
        if (r11[1].isEmpty() == true) goto L25;
        this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0, this.mExifByteOrder));
    L25:
        if (this.mAttributes[2].isEmpty() == true) goto L28;
        this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0, this.mExifByteOrder));
    L28:
        if (this.mAttributes[3].isEmpty() == true) goto L30;
        char r16 = 2;
        this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0, this.mExifByteOrder));
    L32:
        if (this.mHasThumbnail == false) goto L36;
        boolean r103 = this.mHasThumbnailStrips;
        HashMap<String, ExifAttribute>[] r15 = this.mAttributes;
        if (r103 == false) goto L37;
        r15[4].put("StripOffsets", ExifAttribute.createUShort(0, this.mExifByteOrder));
        this.mAttributes[4].put("StripByteCounts", ExifAttribute.createUShort(this.mThumbnailLength, this.mExifByteOrder));
        goto L36
    L37:
        r15[4].put("JPEGInterchangeFormat", ExifAttribute.createULong(0, this.mExifByteOrder));
        char r17 = 3;
        this.mAttributes[4].put("JPEGInterchangeFormatLength", ExifAttribute.createULong(this.mThumbnailLength, this.mExifByteOrder));
    L38:
        int r52 = 0;
    L40:
        if (r52 >= EXIF_TAGS.length) goto L48;
        Iterator<Map.Entry<String, ExifAttribute>> r72 = this.mAttributes[r52].entrySet().iterator();
        int r104 = 0;
    L43:
        if (r72.hasNext() == false) goto L47;
        int r14 = r72.next().getValue().size();
        if (r14 <= 4) goto L43;
        r104 = r104 + r14;
        goto L43
    L47:
        r22[r52] = r22[r52] + r104;
        r52 = r52 + 1;
        goto L40
    L48:
        int r53 = 8;
        int r73 = 0;
    L50:
        if (r73 >= EXIF_TAGS.length) goto L56;
        if (this.mAttributes[r73].isEmpty() == true) goto L54;
        r3[r73] = r53;
        r53 = r53 + (((this.mAttributes[r73].size() * 12) + 6) + r22[r73]);
    L54:
        r73 = r73 + 1;
        goto L50
    L56:
        if (this.mHasThumbnail == false) goto L63;
        boolean r74 = this.mHasThumbnailStrips;
        HashMap<String, ExifAttribute>[] r105 = this.mAttributes;
        if (r74 == false) goto L60;
        r105[4].put("StripOffsets", ExifAttribute.createUShort(r53, this.mExifByteOrder));
    L61:
        this.mThumbnailOffset = r53;
        r53 = r53 + this.mThumbnailLength;
        goto L63
    L60:
        r105[4].put("JPEGInterchangeFormat", ExifAttribute.createULong(r53, this.mExifByteOrder));
    L63:
        if (this.mMimeType != 4) goto L66;
        r53 = r53 + 8;
    L66:
        if (DEBUG == false) goto L72;
        int r75 = 0;
    L69:
        if (r75 >= EXIF_TAGS.length) goto L72;
        Log.d("ExifInterface", String.format("index: %d, offsets: %d, tag count: %d, data sizes: %d, total size: %d", new Object[]{Integer.valueOf(r75), Integer.valueOf(r3[r75]), Integer.valueOf(this.mAttributes[r75].size()), Integer.valueOf(r22[r75]), Integer.valueOf(r53)}));
        r75 = r75 + 1;
    L72:
        if (this.mAttributes[1].isEmpty() == true) goto L75;
        this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(r3[1], this.mExifByteOrder));
    L75:
        if (this.mAttributes[r16].isEmpty() == true) goto L78;
        this.mAttributes[0].put(EXIF_POINTER_TAGS[r16].name, ExifAttribute.createULong(r3[r16], this.mExifByteOrder));
    L78:
        if (this.mAttributes[r17].isEmpty() == true) goto L80;
        this.mAttributes[1].put(EXIF_POINTER_TAGS[r17].name, ExifAttribute.createULong(r3[r17], this.mExifByteOrder));
    L80:
        int r23 = this.mMimeType;
        if (r23 == 4) goto L89;
        if (r23 == 13) goto L87;
        if (r23 != 14) goto L91;
        r20.write(WEBP_CHUNK_TYPE_EXIF);
        r20.writeInt(r53);
    L91:
        int r24 = r20.mOutputStream.size();
        if (this.mExifByteOrder != ByteOrder.BIG_ENDIAN) goto L94;
        short r8 = 19789;
    L95:
        r20.writeShort(r8);
        r20.setByteOrder(this.mExifByteOrder);
        r20.writeUnsignedShort(42);
        r20.writeUnsignedInt(8);
        int r82 = 0;
    L97:
        if (r82 >= EXIF_TAGS.length) goto L126;
        if (this.mAttributes[r82].isEmpty() == true) goto L123;
        r20.writeUnsignedShort(this.mAttributes[r82].size());
        int r9 = ((r3[r82] + 2) + (this.mAttributes[r82].size() * 12)) + 4;
        Iterator<Map.Entry<String, ExifAttribute>> r106 = this.mAttributes[r82].entrySet().iterator();
    L102:
        if (r106.hasNext() == false) goto L111;
        Map.Entry<String, ExifAttribute> r142 = r106.next();
        int r43 = sExifTagMapsForWriting[r82].get(r142.getKey()).number;
        ExifAttribute r143 = r142.getValue();
        int r152 = r143.size();
        r20.writeUnsignedShort(r43);
        r20.writeUnsignedShort(r143.format);
        r20.writeInt(r143.numberOfComponents);
        if (r152 <= 4) goto L106;
        int r18 = r82;
        r20.writeUnsignedInt(r9);
        r9 = r9 + r152;
    L110:
        r82 = r18;
        goto L102
    L106:
        r18 = r82;
        r20.write(r143.bytes);
        if (r152 >= 4) goto L110;
    L108:
        if (r152 >= 4) goto L110;
        r20.writeByte(0);
        r152 = r152 + 1;
        goto L108
    L111:
        int r182 = r82;
        if (r182 == 0) goto L114;
    L116:
        r20.writeUnsignedInt(0);
    L117:
        Iterator<Map.Entry<String, ExifAttribute>> r76 = this.mAttributes[r182].entrySet().iterator();
    L119:
        if (r76.hasNext() == false) goto L124;
        byte[] r83 = r76.next().getValue().bytes;
        if (r83.length <= 4) goto L119;
        r20.write(r83, 0, r83.length);
    L124:
        r82 = r182 + 1;
        goto L97
    L114:
        if (this.mAttributes[4].isEmpty() == true) goto L116;
        r20.writeUnsignedInt(r3[4]);
        goto L117
    L123:
        r182 = r82;
        goto L124
    L126:
        if (this.mHasThumbnail == false) goto L129;
        r20.write(getThumbnailBytes());
    L129:
        if (this.mMimeType == 14) goto L131;
    L133:
        r20.setByteOrder(ByteOrder.BIG_ENDIAN);
        return r24;
    L131:
        if ((r53 % 2) != 1) goto L133;
        r20.writeByte(0);
        goto L133
    L94:
        r8 = 18761;
        goto L95
    L87:
        r20.writeInt(r53);
        r20.writeInt(1700284774);
        goto L91
    L89:
        if (r53 > 65535) goto L135;
        r20.writeUnsignedShort(r53);
        r20.write(IDENTIFIER_EXIF_APP1);
        goto L91
    L135:
        ExifInterface$$ExternalSyntheticBUOutline0.m("Size of exif data (", r53, " bytes) exceeds the max size of a JPEG APP1 segment (65536 bytes)");
        return 0;
    L36:
        r17 = 3;
        goto L38
    L30:
        r16 = 2;
        goto L32
    L8:
        if (this.mHasThumbnailStrips == false) goto L10;
        removeAttribute("StripOffsets");
        removeAttribute("StripByteCounts");
        goto L11
    L10:
        removeAttribute("JPEGInterchangeFormat");
        removeAttribute("JPEGInterchangeFormatLength");
        goto L11
    }

    private void writePngExifChunk(ByteOrderedDataOutputStream r4) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        int r1 = writeExifSegment(new ByteOrderedDataOutputStream(r0, ByteOrder.BIG_ENDIAN));
        this.mOffsetToExifData = r4.mOutputStream.size() + r1;
        byte[] r3 = r0.toByteArray();
        r4.write(r3);
        CRC32 r02 = new CRC32();
        r02.update(r3, 4, r3.length - 4);
        r4.writeInt((int) r02.getValue());
    }

    private void writePngXmpItxtChunk(ByteOrderedDataOutputStream r3) {
        r3.writeInt(this.mXmpFromSeparateMarker.bytes.length + 22);
        CRC32 r0 = new CRC32();
        r3.writeInt(1767135348);
        updateCrcWithInt(r0, 1767135348);
        byte[] r1 = PNG_ITXT_XMP_KEYWORD;
        r3.write(r1);
        r0.update(r1);
        r3.write(this.mXmpFromSeparateMarker.bytes);
        r0.update(this.mXmpFromSeparateMarker.bytes);
        r3.writeInt((int) r0.getValue());
        this.mFileOnDiskContainsSeparateXmpMarker = true;
    }

    public double getAltitude(double r7) {
        double r0 = getAttributeDouble("GPSAltitude", -1.0d);
        int r3 = -1;
        int r6 = getAttributeInt("GPSAltitudeRef", -1);
        if (r0 < 0.0d) goto L11;
        if (r6 < 0) goto L11;
        if (r6 == 1) goto L10;
        r3 = 1;
    L10:
        return r0 * r3;
    L11:
        return r7;
    }

    public String getAttribute(String r5) {
        if (r5 == null) goto L31;
        ExifAttribute r1 = getExifAttribute(r5);
        if (r1 != null) goto L8;
        return null;
    L8:
        if (r5.equals("GPSTimeStamp") == false) goto L24;
        int r52 = r1.format;
        if (r52 != 5) goto L12;
    L15:
        Rational[] r4 = (Rational[]) r1.getValue(this.mExifByteOrder);
        if (r4 != null) goto L18;
    L22:
        Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(r4));
        return null;
    L18:
        if (r4.length != 3) goto L22;
        Rational r53 = r4[0];
        Integer r54 = Integer.valueOf((int) (r53.numerator / r53.denominator));
        Rational r0 = r4[1];
        Integer r02 = Integer.valueOf((int) (r0.numerator / r0.denominator));
        Rational r42 = r4[2];
        return String.format("%02d:%02d:%02d", new Object[]{r54, r02, Integer.valueOf((int) (r42.numerator / r42.denominator))});
    L12:
        if (r52 == 10) goto L15;
        Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + r1.format);
        return null;
    L24:
        boolean r55 = RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY.contains(r5);
        ByteOrder r43 = this.mExifByteOrder;
        if (r55 == false) goto L30;
        return Double.toString(r1.getDoubleValue(r43));
    L28:
        return null;
    L30:
        return r1.getStringValue(r43);
    L31:
        f$$ExternalSyntheticBUOutline2.m("tag shouldn't be null");
        return null;
    }

    public double getAttributeDouble(String r1, double r2) {
        if (r1 == null) goto L9;
        ExifAttribute r12 = getExifAttribute(r1);
        if (r12 != null) goto L12;
    L8:
        return r2;
    L12:
        return r12.getDoubleValue(this.mExifByteOrder);
    L9:
        f$$ExternalSyntheticBUOutline2.m("tag shouldn't be null");
        return 0.0d;
    }

    public int getAttributeInt(String r1, int r2) {
        if (r1 == null) goto L9;
        ExifAttribute r12 = getExifAttribute(r1);
        if (r12 != null) goto L12;
    L8:
        return r2;
    L12:
        return r12.getIntValue(this.mExifByteOrder);
    L9:
        f$$ExternalSyntheticBUOutline2.m("tag shouldn't be null");
        return 0;
    }

    public double[] getLatLong() {
        String r0 = getAttribute("GPSLatitude");
        String r1 = getAttribute("GPSLatitudeRef");
        String r2 = getAttribute("GPSLongitude");
        String r9 = getAttribute("GPSLongitudeRef");
        if (r0 == null) goto L14;
        if (r1 == null) goto L15;
        if (r2 == null) goto L16;
        if (r9 == null) goto L17;
        return new double[]{convertRationalLatLonToDouble(r0, r1), convertRationalLatLonToDouble(r2, r9)};
    L9:
        Log.w("ExifInterface", "Latitude/longitude values are not parsable. ".concat(String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", new Object[]{r0, r1, r2, r9})));
        return null;
    L17:
        return null;
    L16:
        return null;
    L15:
        return null;
    L14:
        return null;
    }

    public int getRotationDegrees() {
        switch(getAttributeInt("Orientation", 1)) {
            case 3: goto L10;
            case 4: goto L10;
            case 5: goto L8;
            case 6: goto L6;
            case 7: goto L6;
            case 8: goto L8;
            default: goto L4;
        };
    L4:
        return 0;
    L6:
        return 90;
    L8:
        return 270;
    L10:
        return 180;
    }

    public byte[] getThumbnail() {
        int r0 = this.mThumbnailCompression;
        if (r0 == 6) goto L10;
        if (r0 == 7) goto L10;
        return null;
    L10:
        return getThumbnailBytes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.content.res.AssetManager$AssetInputStream, java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.Closeable, java.io.InputStream] */
    public byte[] getThumbnailBytes() {
        Closeable r2 = null;
        if (this.mHasThumbnail == true) goto L5;
        return null;
    L5:
        ?? r1 = this.mThumbnailBytes;
        if (r1 == 0) goto L59;
        return r1;
    L59:
        r1 = this.mAssetInputStream;     // Catch: Throwable -> L25 Exception -> L27
        if (r1 == 0) goto L23;
    L17:
        e = e;
        FileDescriptor r3 = null;
    L45:
        Log.d("ExifInterface", "Encountered exception while getting thumbnail", e);     // Catch: Throwable -> L37
        ExifInterfaceUtils.closeQuietly(r1);
        if (r3 == null) goto L49;
        ExifInterfaceUtils.closeFileDescriptor(r3);
    L49:
        return null;
    L14:
        th = th;
        r3 = null;
    L16:
        r2 = r1;
    L50:
        ExifInterfaceUtils.closeQuietly(r2);
        if (r3 == null) goto L53;
        ExifInterfaceUtils.closeFileDescriptor(r3);
    L53:
        throw th;
    L11:
        if (r1.markSupported() == false) goto L19;
        r1.reset();     // Catch: Throwable -> L14 Exception -> L17
        FileInputStream r12 = r1;
    L13:
        r3 = null;
        r1 = r12;
    L57:
        ByteOrderedDataInputStream r4 = new ByteOrderedDataInputStream(r1);     // Catch: Throwable -> L37 Exception -> L39
        r4.skipFully(this.mThumbnailOffset + this.mOffsetToExifData);     // Catch: Throwable -> L37 Exception -> L39
        byte[] r5 = new byte[this.mThumbnailLength];     // Catch: Throwable -> L37 Exception -> L39
        r4.readFully(r5);     // Catch: Throwable -> L37 Exception -> L39
        this.mThumbnailBytes = r5;     // Catch: Throwable -> L37 Exception -> L39
        ExifInterfaceUtils.closeQuietly(r1);
        if (r3 == null) goto L36;
        ExifInterfaceUtils.closeFileDescriptor(r3);
    L36:
        return r5;
    L39:
        e = e;
        goto L45
    L19:
        Log.d("ExifInterface", "Cannot read thumbnail from inputstream without mark/reset support");     // Catch: Throwable -> L14 Exception -> L17
        ExifInterfaceUtils.closeQuietly(r1);
        return null;
    L23:
        if (this.mFilename == null) goto L29;
        r12 = new FileInputStream(this.mFilename);     // Catch: Throwable -> L25 Exception -> L27
        goto L13
    L29:
        FileDescriptor r13 = Os.dup(this.mSeekableFileDescriptor);     // Catch: Throwable -> L25 Exception -> L27
        Os.lseek(r13, 0, OsConstants.SEEK_SET);     // Catch: Throwable -> L41 Exception -> L43
        r3 = r13;
        r1 = new FileInputStream(r13);     // Catch: Throwable -> L41 Exception -> L43
        goto L57
    L43:
        e = e;
        r3 = r13;
        r1 = 0;
    L41:
        th = th;
        r3 = r13;
    L27:
        e = e;
        r1 = 0;
        r3 = null;
    L25:
        th = th;
        r3 = null;
    L37:
        th = th;
        goto L16
    }

    public void saveAttributes() {
        if (isSupportedFormatForSavingAttributes(this.mMimeType) == true) goto L5;
        Model$$ExternalSyntheticBUOutline0.m("ExifInterface only supports saving attributes for JPEG, PNG, and WebP formats.");
        return;
    L5:
        if (this.mSeekableFileDescriptor != null) goto L12;
        if (this.mFilename != null) goto L12;
        Model$$ExternalSyntheticBUOutline0.m("ExifInterface does not support saving attributes for the current input.");
        return;
    L12:
        if (this.mHasThumbnail == true) goto L14;
    L20:
        boolean r1 = true;
        this.mModified = true;
        this.mThumbnailBytes = getThumbnail();
        InputStream r2 = null;
        File r3 = File.createTempFile("temp", "tmp");     // Catch: Throwable -> L24 Exception -> L26
        if (this.mFilename == null) goto L28;
        FileInputStream r4 = new FileInputStream(this.mFilename);     // Catch: Throwable -> L24 Exception -> L26
    L131:
        FileOutputStream r7 = new FileOutputStream(r3);     // Catch: Throwable -> L98 Exception -> L100
        ExifInterfaceUtils.copy(r4, r7);     // Catch: Throwable -> L94 Exception -> L96
        ExifInterfaceUtils.closeQuietly(r4);
        ExifInterfaceUtils.closeQuietly(r7);
        boolean r42 = false;
        FileInputStream r72 = new FileInputStream(r3);     // Catch: Throwable -> L36 Exception -> L65
    L38:
        e = e;
        BufferedInputStream r9 = null;
        BufferedOutputStream r10 = null;
        r2 = r72;
    L40:
        Exception r73 = e;
        OutputStream r8 = r10;
    L122:
        FileInputStream r11 = new FileInputStream(r3);     // Catch: Throwable -> L80 Exception -> L82
    L129:
    L74:
        e = e;
        r2 = r11;
    L119:
    L85:
        th = th;
    L109:
        ExifInterfaceUtils.closeQuietly(r2);     // Catch: Throwable -> L88
        ExifInterfaceUtils.closeQuietly(r8);     // Catch: Throwable -> L88
        throw th;     // Catch: Throwable -> L88
    L88:
        th = th;
        r42 = r1;
    L48:
        r2 = r9;
    L90:
        ExifInterfaceUtils.closeQuietly(r2);
        ExifInterfaceUtils.closeQuietly(r10);
        if (r42 == true) goto L93;
        r3.delete();
    L93:
        throw th;
    L84:
        throw new IOException("Failed to save new file. Original file is stored in " + r3.getAbsolutePath(), e);     // Catch: Throwable -> L85
    L72:
        th = th;
        r1 = false;
        r2 = r11;
        goto L109
    L69:
        if (this.mFilename == null) goto L76;
        FileOutputStream r22 = new FileOutputStream(this.mFilename);     // Catch: Throwable -> L72 Exception -> L74
    L71:
        r8 = r22;
        ExifInterfaceUtils.copy(r11, r8);     // Catch: Throwable -> L72 Exception -> L74
        ExifInterfaceUtils.closeQuietly(r11);     // Catch: Throwable -> L47
        ExifInterfaceUtils.closeQuietly(r8);     // Catch: Throwable -> L47
        throw new IOException("Failed to save new file", r73);     // Catch: Throwable -> L47
    L76:
        Os.lseek(this.mSeekableFileDescriptor, 0, OsConstants.SEEK_SET);     // Catch: Throwable -> L72 Exception -> L74
        r22 = new FileOutputStream(this.mSeekableFileDescriptor);     // Catch: Throwable -> L72 Exception -> L74
    L82:
        e = e;
    L80:
        th = th;
        r1 = false;
        goto L109
    L34:
        if (this.mFilename == null) goto L41;
        r8 = new FileOutputStream(this.mFilename);     // Catch: Throwable -> L36 Exception -> L38
    L117:
        r9 = new BufferedInputStream(r72);     // Catch: Throwable -> L36 Exception -> L63
    L124:
        r10 = new BufferedOutputStream(r8);     // Catch: Throwable -> L59 Exception -> L61
        int r112 = this.mMimeType;     // Catch: Throwable -> L47 Exception -> L49
        if (r112 != 4) goto L52;
        saveJpegAttributes(r9, r10);     // Catch: Throwable -> L47 Exception -> L49
    L57:
        ExifInterfaceUtils.closeQuietly(r9);
        ExifInterfaceUtils.closeQuietly(r10);
        r3.delete();
        this.mThumbnailBytes = null;
        return;
    L52:
        if (r112 != 13) goto L55;
        savePngAttributes(r9, r10);     // Catch: Throwable -> L47 Exception -> L49
        goto L57
    L55:
        if (r112 != 14) goto L57;
        saveWebpAttributes(r9, r10);     // Catch: Throwable -> L47 Exception -> L49
    L49:
        e = move-exception;
        r73 = e;
        r2 = r72;
        goto L122
    L61:
        e = move-exception;
        r10 = null;
        r2 = r72;
        r73 = e;
    L59:
        th = th;
        r10 = null;
    L63:
        e = move-exception;
        r10 = null;
        r2 = r72;
        r73 = e;
        r9 = null;
        goto L122
    L41:
        Os.lseek(this.mSeekableFileDescriptor, 0, OsConstants.SEEK_SET);     // Catch: Throwable -> L36 Exception -> L38
        r8 = new FileOutputStream(this.mSeekableFileDescriptor);     // Catch: Throwable -> L36 Exception -> L38
    L36:
        th = th;
        r10 = null;
    L65:
        e = e;
        r9 = null;
        r10 = null;
    L47:
        th = th;
        goto L48
    L96:
        e = e;
    L97:
        r2 = r4;
    L113:
    L104:
        th = th;
    L105:
        ExifInterfaceUtils.closeQuietly(r2);
        ExifInterfaceUtils.closeQuietly(r7);
        throw th;
    L103:
        throw new IOException("Failed to copy original file to temp file", e);     // Catch: Throwable -> L104
    L94:
        th = th;
    L95:
        r2 = r4;
        goto L105
    L100:
        e = e;
        r7 = null;
    L98:
        th = th;
        r7 = null;
        goto L95
    L28:
        Os.lseek(this.mSeekableFileDescriptor, 0, OsConstants.SEEK_SET);     // Catch: Throwable -> L24 Exception -> L26
        r4 = new FileInputStream(this.mSeekableFileDescriptor);     // Catch: Throwable -> L24 Exception -> L26
    L26:
        e = e;
        r7 = null;
    L24:
        th = th;
        r7 = null;
        goto L105
    L14:
        if (this.mHasThumbnailStrips == false) goto L20;
        if (this.mAreThumbnailStripsConsecutive == true) goto L20;
        Model$$ExternalSyntheticBUOutline0.m("ExifInterface does not support saving attributes when the image file has non-consecutive thumbnail strips");
    }

    public void setAttribute(String r24, String r25) {
        String r1 = r24;
        String r2 = r25;
        if (r1 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("tag shouldn't be null");
        return;
    L5:
        if ("ISOSpeedRatings".equals(r1) == true) goto L7;
    L10:
        int r5 = 2;
        String r6 = "/";
        int r7 = 1;
        if (r2 != null) goto L13;
    L42:
        int r10 = 0;
        if ("Xmp".equals(r1) == true) goto L45;
    L63:
        int r3 = 0;
    L65:
        if (r3 >= EXIF_TAGS.length) goto L145;
        if (r3 == 4) goto L69;
    L71:
        ExifTag r8 = sExifTagMapsForWriting[r3].get(r1);
        if (r8 != null) goto L73;
    L70:
        String r22 = r6;
        int r242 = r7;
        int r20 = r10;
    L144:
        r3 = r3 + 1;
        r7 = r242;
        r10 = r20;
        r6 = r22;
        r5 = 2;
        goto L65
    L73:
        if (r2 != null) goto L75;
        this.mAttributes[r3].remove(r1);
        goto L70
    L75:
        Pair<Integer, Integer> r9 = guessDataFormat(r2);
        if (r8.primaryFormat != ((Integer) r9.first).intValue()) goto L78;
    L105:
        int r82 = r8.primaryFormat;
    L107:
        switch(r82) {
            case 1: goto L143;
            case 2: goto L142;
            case 3: goto L137;
            case 4: goto L132;
            case 5: goto L127;
            case 6: goto L109;
            case 7: goto L142;
            case 8: goto L109;
            case 9: goto L122;
            case 10: goto L116;
            case 11: goto L109;
            case 12: goto L111;
            default: goto L109;
        };
    L111:
        String[] r83 = r2.split(",", -1);
        double[] r92 = new double[r83.length];
        int r11 = r10;
    L113:
        if (r11 >= r83.length) goto L115;
        r92[r11] = Double.parseDouble(r83[r11]);
        r11 = r11 + 1;
        goto L113
    L115:
        this.mAttributes[r3].put(r1, ExifAttribute.createDouble(r92, this.mExifByteOrder));
        goto L70
    L116:
        String[] r84 = r2.split(",", -1);
        Rational[] r93 = new Rational[r84.length];
        int r112 = r10;
    L118:
        if (r112 >= r84.length) goto L120;
        String[] r12 = r84[r112].split(r6, -1);
        int r243 = r7;
        int r21 = r112;
        r93[r21] = new Rational((long) Double.parseDouble(r12[r10]), (long) Double.parseDouble(r12[r243]), null);
        r112 = r21 + 1;
        r7 = r243;
        r84 = r84;
        r10 = r10;
        goto L118
    L120:
        r242 = r7;
        r20 = r10;
        this.mAttributes[r3].put(r1, ExifAttribute.createSRational(r93, this.mExifByteOrder));
    L121:
        r22 = r6;
        goto L144
    L122:
        r242 = r7;
        r20 = r10;
        String[] r72 = r2.split(",", -1);
        int[] r85 = new int[r72.length];
        int r94 = r20;
    L124:
        if (r94 >= r72.length) goto L126;
        r85[r94] = Integer.parseInt(r72[r94]);
        r94 = r94 + 1;
        goto L124
    L126:
        this.mAttributes[r3].put(r1, ExifAttribute.createSLong(r85, this.mExifByteOrder));
        goto L121
    L127:
        r242 = r7;
        r20 = r10;
        String[] r73 = r2.split(",", -1);
        Rational[] r86 = new Rational[r73.length];
        int r95 = r20;
    L129:
        if (r95 >= r73.length) goto L131;
        String[] r102 = r73[r95].split(r6, -1);
        r86[r95] = new Rational((long) Double.parseDouble(r102[r20]), (long) Double.parseDouble(r102[r242]), null);
        r95 = r95 + 1;
        r6 = r6;
        goto L129
    L131:
        r22 = r6;
        this.mAttributes[r3].put(r1, ExifAttribute.createURational(r86, this.mExifByteOrder));
        goto L144
    L132:
        r22 = r6;
        r242 = r7;
        r20 = r10;
        String[] r52 = r2.split(",", -1);
        long[] r62 = new long[r52.length];
        int r74 = r20;
    L134:
        if (r74 >= r52.length) goto L136;
        r62[r74] = Long.parseLong(r52[r74]);
        r74 = r74 + 1;
        goto L134
    L136:
        this.mAttributes[r3].put(r1, ExifAttribute.createULong(r62, this.mExifByteOrder));
        goto L144
    L137:
        r22 = r6;
        r242 = r7;
        r20 = r10;
        String[] r53 = r2.split(",", -1);
        int[] r63 = new int[r53.length];
        int r75 = r20;
    L139:
        if (r75 >= r53.length) goto L141;
        r63[r75] = Integer.parseInt(r53[r75]);
        r75 = r75 + 1;
        goto L139
    L141:
        this.mAttributes[r3].put(r1, ExifAttribute.createUShort(r63, this.mExifByteOrder));
        goto L144
    L142:
        r22 = r6;
        r242 = r7;
        r20 = r10;
        this.mAttributes[r3].put(r1, ExifAttribute.createString(r2));
        goto L144
    L143:
        r22 = r6;
        r242 = r7;
        r20 = r10;
        this.mAttributes[r3].put(r1, ExifAttribute.createByte(r2));
        goto L144
    L109:
        if (DEBUG == false) goto L70;
        Log.d("ExifInterface", "Data format isn't one of expected formats: " + r82);
        goto L70
    L78:
        if (r8.primaryFormat == ((Integer) r9.second).intValue()) goto L105;
        int r113 = r8.secondaryFormat;
        if (r113 != (-1)) goto L83;
    L87:
        int r114 = r8.primaryFormat;
        if (r114 != r7) goto L90;
    L104:
        r82 = r114;
        goto L107
    L90:
        if (r114 == 7) goto L104;
        if (r114 == r5) goto L104;
        if (DEBUG == false) goto L70;
        StringBuilder r115 = new StringBuilder("Given tag (");
        r115.append(r1);
        r115.append(") value didn't match with one of expected formats: ");
        String[] r122 = IFD_FORMAT_NAMES;
        r115.append(r122[r8.primaryFormat]);
        int r14 = r8.secondaryFormat;
        String r16 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r14 != (-1)) goto L98;
        String r87 = _UrlKt.FRAGMENT_ENCODE_SET;
    L99:
        r115.append(r87);
        r115.append(" (guess: ");
        r115.append(r122[((Integer) r9.first).intValue()]);
        if (((Integer) r9.second).intValue() == (-1)) goto L103;
        r16 = ", " + r122[((Integer) r9.second).intValue()];
    L103:
        r115.append(r16);
        r115.append(")");
        Log.d("ExifInterface", r115.toString());
        goto L70
    L98:
        r87 = ", " + r122[r8.secondaryFormat];
        goto L99
    L83:
        if (r113 != ((Integer) r9.first).intValue()) goto L85;
    L86:
        r82 = r8.secondaryFormat;
        goto L107
    L85:
        if (r8.secondaryFormat != ((Integer) r9.second).intValue()) goto L87;
    L69:
        if (this.mHasThumbnail == true) goto L71;
    L145:
        return;
    L45:
        if (this.mAttributes[0].containsKey("Xmp") == false) goto L47;
    L50:
        boolean r88 = true;
    L51:
        int r96 = getXmpHandlingForImageType(this.mMimeType);
        if (r96 == 2) goto L54;
    L56:
        if (r96 != 3) goto L63;
        if (r88 == true) goto L63;
    L58:
        if (r2 == null) goto L60;
        ExifAttribute r13 = ExifAttribute.createByte(r2);
    L61:
        this.mXmpFromSeparateMarker = r13;
        return;
    L60:
        r13 = null;
        goto L61
    L54:
        if (this.mXmpFromSeparateMarker != null) goto L58;
        if (r88 == false) goto L58;
    L47:
        if (this.mAttributes[5].containsKey("Xmp") == true) goto L50;
        r88 = false;
        goto L51
    L13:
        if (RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY.contains(r1) == false) goto L21;
        if (r2.contains("/") == true) goto L21;
        r2 = Rational.createFromDouble(Double.parseDouble(r2)).toString();     // Catch: NumberFormatException -> L18
    L18:
        Log.w("ExifInterface", "Invalid value for " + r1 + " : " + r2);
        return;
    L21:
        if (r1.equals("GPSTimeStamp") == false) goto L28;
        Matcher r89 = GPS_TIMESTAMP_PATTERN.matcher(r2);
        if (r89.find() == true) goto L26;
        Log.w("ExifInterface", "Invalid value for " + r1 + " : " + r2);
        return;
    L26:
        r2 = Integer.parseInt(r89.group(1)) + "/1," + Integer.parseInt(r89.group(2)) + "/1," + Integer.parseInt(r89.group(3)) + "/1";
        goto L42
    L28:
        if ("DateTime".equals(r1) == false) goto L30;
    L33:
        boolean r810 = DATETIME_PRIMARY_FORMAT_PATTERN.matcher(r2).find();
        boolean r116 = DATETIME_SECONDARY_FORMAT_PATTERN.matcher(r2).find();
        if (r2.length() != 19) goto L40;
        if (r810 == true) goto L38;
        if (r116 == false) goto L40;
    L38:
        if (r116 == false) goto L42;
        r2 = r2.replaceAll("-", ":");
    L40:
        Log.w("ExifInterface", "Invalid value for " + r1 + " : " + r2);
        return;
    L30:
        if ("DateTimeOriginal".equals(r1) == true) goto L33;
        if ("DateTimeDigitized".equals(r1) == false) goto L42;
    L7:
        if (DEBUG == false) goto L9;
        Log.d("ExifInterface", "setAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
    L9:
        r1 = "PhotographicSensitivity";
        goto L10
    }
}
