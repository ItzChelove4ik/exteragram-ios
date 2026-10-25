package androidx.camera.core.impl.utils;

import androidx.camera.core.impl.utils.ExifData;
import androidx.core.util.Preconditions;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Map;
import kotlin.UShort;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class ExifOutputStream extends FilterOutputStream {
    private static final byte[] IDENTIFIER_EXIF_APP1 = null;
    private final ByteBuffer mBuffer;
    private int mByteToCopy;
    private int mByteToSkip;
    private final ExifData mExifData;
    private final byte[] mSingleByteArray;
    private int mState;

    public static final class JpegHeader {
        public static boolean isSofMarker(short r1) {
            if (r1 >= (-64)) goto L5;
            return false;
        L5:
            if (r1 <= (-49)) goto L7;
            return false;
        L7:
            if (r1 != (-60)) goto L9;
            return false;
        L9:
            if (r1 != (-56)) goto L11;
            return false;
        L11:
            if (r1 == (-52)) goto L19;
            return true;
        L19:
            return false;
        }
    }

    static {
        IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ExifAttribute.ASCII);
    }

    public ExifOutputStream(OutputStream r3, ExifData r4) {
        super(new BufferedOutputStream(r3, 65536));
        this.mSingleByteArray = new byte[1];
        this.mBuffer = ByteBuffer.allocate(4);
        this.mState = 0;
        this.mExifData = r4;
    }

    private int requestByteToBuffer(int r2, byte[] r3, int r4, int r5) {
        int r22 = Math.min(r5, r2 - this.mBuffer.position());
        this.mBuffer.put(r3, r4, r22);
        return r22;
    }

    private void writeExifSegment(ByteOrderedDataOutputStream r15) {
        ExifTag[][] r0 = ExifData.EXIF_TAGS;
        int[] r1 = new int[r0.length];
        int[] r02 = new int[r0.length];
        ExifTag[] r2 = ExifData.EXIF_POINTER_TAGS;
        int r3 = r2.length;
        int r5 = 0;
    L3:
        if (r5 >= r3) goto L10;
        ExifTag r6 = r2[r5];
        int r7 = 0;
    L6:
        if (r7 >= ExifData.EXIF_TAGS.length) goto L8;
        this.mExifData.getAttributes(r7).remove(r6.name);
        r7 = r7 + 1;
        goto L6
    L8:
        r5 = r5 + 1;
        goto L3
    L10:
        if (this.mExifData.getAttributes(1).isEmpty() == true) goto L13;
        this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0, this.mExifData.getByteOrder()));
    L13:
        if (this.mExifData.getAttributes(2).isEmpty() == true) goto L16;
        this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0, this.mExifData.getByteOrder()));
    L16:
        if (this.mExifData.getAttributes(3).isEmpty() == true) goto L18;
        this.mExifData.getAttributes(1).put(ExifData.EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0, this.mExifData.getByteOrder()));
    L18:
        int r22 = 0;
    L20:
        if (r22 >= ExifData.EXIF_TAGS.length) goto L28;
        Iterator<Map.Entry<String, ExifAttribute>> r9 = this.mExifData.getAttributes(r22).entrySet().iterator();
        int r11 = 0;
    L23:
        if (r9.hasNext() == false) goto L27;
        int r12 = r9.next().getValue().size();
        if (r12 <= 4) goto L23;
        r11 = r11 + r12;
        goto L23
    L27:
        r02[r22] = r02[r22] + r11;
        r22 = r22 + 1;
        goto L20
    L28:
        int r112 = 8;
        int r92 = 0;
    L29:
        int r122 = ExifData.EXIF_TAGS.length;
        ExifData r13 = this.mExifData;
        if (r92 >= r122) goto L35;
        if (r13.getAttributes(r92).isEmpty() == true) goto L34;
        r1[r92] = r112;
        r112 = r112 + (((this.mExifData.getAttributes(r92).size() * 12) + 6) + r02[r92]);
    L34:
        r92 = r92 + 1;
        goto L29
    L35:
        int r113 = r112 + 8;
        if (r13.getAttributes(1).isEmpty() == true) goto L39;
        this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(r1[1], this.mExifData.getByteOrder()));
    L39:
        if (this.mExifData.getAttributes(2).isEmpty() == true) goto L42;
        this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(r1[2], this.mExifData.getByteOrder()));
    L42:
        if (this.mExifData.getAttributes(3).isEmpty() == true) goto L44;
        this.mExifData.getAttributes(1).put(ExifData.EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(r1[3], this.mExifData.getByteOrder()));
    L44:
        r15.writeUnsignedShort(r113);
        r15.write(IDENTIFIER_EXIF_APP1);
        if (this.mExifData.getByteOrder() != ByteOrder.BIG_ENDIAN) goto L47;
        short r03 = 19789;
    L48:
        r15.writeShort(r03);
        r15.setByteOrder(this.mExifData.getByteOrder());
        r15.writeUnsignedShort(42);
        r15.writeUnsignedInt(8);
        int r04 = 0;
    L50:
        if (r04 >= ExifData.EXIF_TAGS.length) goto L70;
        if (this.mExifData.getAttributes(r04).isEmpty() == true) goto L69;
        r15.writeUnsignedShort(this.mExifData.getAttributes(r04).size());
        int r23 = ((r1[r04] + 2) + (this.mExifData.getAttributes(r04).size() * 12)) + 4;
        Iterator<Map.Entry<String, ExifAttribute>> r32 = this.mExifData.getAttributes(r04).entrySet().iterator();
    L55:
        if (r32.hasNext() == false) goto L63;
        Map.Entry<String, ExifAttribute> r8 = r32.next();
        int r93 = ((ExifTag) Preconditions.checkNotNull(ExifData.Builder.sExifTagMapsForWriting.get(r04).get(r8.getKey()), "Tag not supported: " + r8.getKey() + ". Tag needs to be ported from ExifInterface to ExifData.")).number;
        ExifAttribute r82 = r8.getValue();
        int r114 = r82.size();
        r15.writeUnsignedShort(r93);
        r15.writeUnsignedShort(r82.format);
        r15.writeInt(r82.numberOfComponents);
        if (r114 > 4) goto L58;
        r15.write(r82.bytes);
        if (r114 >= 4) goto L55;
    L61:
        if (r114 >= 4) goto L55;
        r15.writeByte(0);
        r114 = r114 + 1;
        goto L61
    L58:
        r15.writeUnsignedInt(r23);
        r23 = r23 + r114;
        goto L55
    L63:
        r15.writeUnsignedInt(0);
        Iterator<Map.Entry<String, ExifAttribute>> r24 = this.mExifData.getAttributes(r04).entrySet().iterator();
    L65:
        if (r24.hasNext() == false) goto L69;
        byte[] r33 = r24.next().getValue().bytes;
        if (r33.length <= 4) goto L65;
        r15.write(r33, 0, r33.length);
    L69:
        r04 = r04 + 1;
        goto L50
    L70:
        r15.setByteOrder(ByteOrder.BIG_ENDIAN);
        return;
    L47:
        r03 = 18761;
        goto L48
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int r3) {
        byte[] r0 = this.mSingleByteArray;
        r0[0] = (byte) (r3 & 255);
        write(r0);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] r3) {
        write(r3, 0, r3.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] r7, int r8, int r9) {
    L2:
        int r0 = this.mByteToSkip;
        if (r0 <= 0) goto L5;
    L8:
        if (r9 <= 0) goto L44;
        if (r0 <= 0) goto L11;
        int r02 = Math.min(r9, r0);
        r9 = r9 - r02;
        this.mByteToSkip -= r02;
        r8 = r8 + r02;
    L11:
        int r03 = this.mByteToCopy;
        if (r03 <= 0) goto L14;
        int r04 = Math.min(r9, r03);
        ((FilterOutputStream) this).out.write(r7, r8, r04);
        r9 = r9 - r04;
        this.mByteToCopy -= r04;
        r8 = r8 + r04;
    L14:
        if (r9 == 0) goto L61;
        int r05 = this.mState;
        if (r05 != 0) goto L18;
        int r06 = requestByteToBuffer(2, r7, r8, r9);
        r8 = r8 + r06;
        r9 = r9 - r06;
        if (this.mBuffer.position() < 2) goto L63;
        this.mBuffer.rewind();
        if (this.mBuffer.getShort() != (-40)) goto L42;
        ((FilterOutputStream) this).out.write(this.mBuffer.array(), 0, 2);
        this.mState = 1;
        this.mBuffer.rewind();
        ByteOrderedDataOutputStream r07 = new ByteOrderedDataOutputStream(((FilterOutputStream) this).out, ByteOrder.BIG_ENDIAN);
        r07.writeShort(-31);
        writeExifSegment(r07);
        goto L2
    L42:
        Model$$ExternalSyntheticBUOutline0.m("Not a valid jpeg image, cannot write exif");
        return;
    L63:
        return;
    L18:
        if (r05 != 1) goto L2;
        int r3 = requestByteToBuffer(4, r7, r8, r9);
        r8 = r8 + r3;
        r9 = r9 - r3;
        if (this.mBuffer.position() != 2) goto L26;
        if (this.mBuffer.getShort() != (-39)) goto L26;
        ((FilterOutputStream) this).out.write(this.mBuffer.array(), 0, 2);
        this.mBuffer.rewind();
    L26:
        if (this.mBuffer.position() < 4) goto L62;
        this.mBuffer.rewind();
        short r32 = this.mBuffer.getShort();
        if (r32 != (-31)) goto L31;
        this.mByteToSkip = (this.mBuffer.getShort() & UShort.MAX_VALUE) - 2;
        this.mState = 2;
    L35:
        this.mBuffer.rewind();
        goto L2
    L31:
        boolean r2 = JpegHeader.isSofMarker(r32);
        OutputStream r33 = ((FilterOutputStream) this).out;
        if (r2 == true) goto L34;
        r33.write(this.mBuffer.array(), 0, 4);
        this.mByteToCopy = (this.mBuffer.getShort() & UShort.MAX_VALUE) - 2;
        goto L35
    L34:
        r33.write(this.mBuffer.array(), 0, 4);
        this.mState = 2;
        goto L35
    L62:
        return;
    L61:
        return;
    L44:
        if (r9 <= 0) goto L60;
        ((FilterOutputStream) this).out.write(r7, r8, r9);
        return;
    L60:
        return;
    L5:
        if (this.mByteToCopy > 0) goto L8;
        if (this.mState == 2) goto L44;
        goto L8
    }
}
