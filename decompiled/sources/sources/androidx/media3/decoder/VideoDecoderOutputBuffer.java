package androidx.media3.decoder;

import androidx.media3.common.Format;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class VideoDecoderOutputBuffer extends DecoderOutputBuffer {
    public static final int COLORSPACE_BT2020 = 3;
    public static final int COLORSPACE_BT601 = 1;
    public static final int COLORSPACE_BT709 = 2;
    public static final int COLORSPACE_UNKNOWN = 0;
    public int colorspace;
    public ByteBuffer data;
    public long decoderPrivate;
    public Format format;
    public int height;
    public int mode;
    private final DecoderOutputBuffer.Owner<VideoDecoderOutputBuffer> owner;
    public ByteBuffer supplementalData;
    public int uvStride;
    public int width;
    public int yStride;
    public ByteBuffer[] yuvPlanes;
    public int[] yuvStrides;

    public VideoDecoderOutputBuffer(DecoderOutputBuffer.Owner<VideoDecoderOutputBuffer> r1) {
        this.owner = r1;
    }

    private static boolean isSafeToMultiply(int r1, int r2) {
        if (r1 < 0) goto L9;
        if (r2 < 0) goto L12;
        if (r2 > 0) goto L6;
        return true;
    L6:
        if (r1 >= (Integer.MAX_VALUE / r2)) goto L13;
        return true;
    L13:
        return false;
    L12:
        return false;
    L9:
        return false;
    }

    public void init(long r1, int r3, ByteBuffer r4) {
        this.timeUs = r1;
        this.mode = r3;
        if (r4 != null) goto L5;
    L15:
        this.supplementalData = null;
        return;
    L5:
        if (r4.hasRemaining() == false) goto L15;
        addFlag(268435456);
        int r12 = r4.limit();
        ByteBuffer r2 = this.supplementalData;
        if (r2 != null) goto L9;
    L12:
        this.supplementalData = ByteBuffer.allocate(r12);
    L13:
        this.supplementalData.put(r4);
        this.supplementalData.flip();
        r4.position(0);
        return;
    L9:
        if (r2.capacity() < r12) goto L12;
        this.supplementalData.clear();
        goto L13
    }

    public boolean initForOffsetFrames(int r7, int r8, int r9, int r10, int r11, int r12, int r13) {
        if (this.yuvPlanes != null) goto L5;
        this.yuvPlanes = new ByteBuffer[3];
    L5:
        ByteBuffer r0 = this.data;
        if (r0 != null) goto L8;
        return false;
    L8:
        this.width = r8;
        this.height = r9;
        this.colorspace = r12;
        ByteBuffer[] r82 = this.yuvPlanes;
        int r122 = r10 * r9;
        int r92 = (r9 >> 1) * r11;
        int r4 = r10 * r13;
        r0.position(r7);
        ByteBuffer r5 = r0.slice();
        r82[0] = r5;
        r5.limit(r122);
        r0.position(r4 + r7);
        ByteBuffer r123 = r0.slice();
        r82[1] = r123;
        r123.limit(r92);
        r0.position((r4 + ((r13 >> 1) * r11)) + r7);
        ByteBuffer r72 = r0.slice();
        r82[2] = r72;
        r72.limit(r92);
        if (this.yuvStrides != null) goto L11;
        this.yuvStrides = new int[3];
    L11:
        int[] r6 = this.yuvStrides;
        r6[0] = r10;
        r6[1] = r11;
        r6[2] = r11;
        return true;
    }

    public void initForPrivateFrame(int r1, int r2) {
        this.width = r1;
        this.height = r2;
    }

    public boolean initForYuvFrame(int r7, int r8, int r9, int r10, int r11) {
        this.width = r7;
        this.height = r8;
        this.colorspace = r11;
        this.yStride = r9;
        this.uvStride = r10;
        int r72 = (int) ((r8 + 1) / 2);
        if (isSafeToMultiply(r9, r8) == true) goto L5;
    L26:
        return false;
    L5:
        if (isSafeToMultiply(r10, r72) == false) goto L26;
        int r82 = r8 * r9;
        int r73 = r72 * r10;
        int r112 = (r73 * 2) + r82;
        if (isSafeToMultiply(r73, 2) == false) goto L26;
        if (r112 < r82) goto L26;
        ByteBuffer r2 = this.data;
        if (r2 != null) goto L14;
    L17:
        this.data = ByteBuffer.allocateDirect(r112);
    L19:
        if (this.yuvPlanes != null) goto L21;
        this.yuvPlanes = new ByteBuffer[3];
    L21:
        ByteBuffer r113 = this.data;
        ByteBuffer[] r3 = this.yuvPlanes;
        ByteBuffer r4 = r113.slice();
        r3[0] = r4;
        r4.limit(r82);
        r113.position(r82);
        ByteBuffer r42 = r113.slice();
        r3[1] = r42;
        r42.limit(r73);
        r113.position(r82 + r73);
        ByteBuffer r83 = r113.slice();
        r3[2] = r83;
        r83.limit(r73);
        if (this.yuvStrides != null) goto L24;
        this.yuvStrides = new int[3];
    L24:
        int[] r6 = this.yuvStrides;
        r6[0] = r9;
        r6[1] = r10;
        r6[2] = r10;
        return true;
    L14:
        if (r2.capacity() < r112) goto L17;
        this.data.position(0);
        this.data.limit(r112);
        goto L19
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer
    public void release() {
        this.owner.releaseOutputBuffer(this);
    }
}
