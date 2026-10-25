package androidx.media3.decoder;

import androidx.media3.common.Format;
import androidx.media3.common.MediaLibraryInfo;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class DecoderInputBuffer extends Buffer {
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo;
    public ByteBuffer data;
    public Format format;
    private final int paddingSize;
    public ByteBuffer supplementalData;
    public long timeUs;
    public boolean waitingForKeys;

    public static final class InsufficientCapacityException extends IllegalStateException {
        public final int currentCapacity;
        public final int requiredCapacity;

        public InsufficientCapacityException(int r3, int r4) {
            super("Buffer too small (" + r3 + " < " + r4 + ")");
            this.currentCapacity = r3;
            this.requiredCapacity = r4;
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.decoder");
    }

    public DecoderInputBuffer(int r2) {
        this(r2, 0);
    }

    public DecoderInputBuffer(int r2, int r3) {
        this.cryptoInfo = new CryptoInfo();
        this.bufferReplacementMode = r2;
        this.paddingSize = r3;
    }

    private ByteBuffer createReplacementByteBuffer(int r3) {
        int r0 = this.bufferReplacementMode;
        if (r0 != 1) goto L7;
        return ByteBuffer.allocate(r3);
    L7:
        if (r0 == 2) goto L9;
        ByteBuffer r2 = this.data;
        if (r2 != null) goto L13;
        int r22 = 0;
    L15:
        throw new InsufficientCapacityException(r22, r3);
    L13:
        r22 = r2.capacity();
        goto L15
    L9:
        return ByteBuffer.allocateDirect(r3);
    }

    public static DecoderInputBuffer newNoDataInstance() {
        return new DecoderInputBuffer(0);
    }

    @Override // androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        ByteBuffer r0 = this.data;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        ByteBuffer r02 = this.supplementalData;
        if (r02 == null) goto L8;
        r02.clear();
    L8:
        this.waitingForKeys = false;
    }

    @EnsuresNonNull({"data"})
    public void ensureSpaceForWrite(int r4) {
        int r42 = r4 + this.paddingSize;
        ByteBuffer r0 = this.data;
        if (r0 != null) goto L6;
        this.data = createReplacementByteBuffer(r42);
        return;
    L6:
        int r1 = r0.capacity();
        int r2 = r0.position();
        int r43 = r42 + r2;
        if (r1 < r43) goto L10;
        this.data = r0;
        return;
    L10:
        ByteBuffer r44 = createReplacementByteBuffer(r43);
        r44.order(r0.order());
        if (r2 <= 0) goto L13;
        r0.flip();
        r44.put(r0);
    L13:
        this.data = r44;
    }

    public final void flip() {
        ByteBuffer r0 = this.data;
        if (r0 == null) goto L5;
        r0.flip();
    L5:
        ByteBuffer r1 = this.supplementalData;
        if (r1 == null) goto L9;
        r1.flip();
        return;
    }

    public final boolean isEncrypted() {
        return getFlag(TLObject.FLAG_30);
    }

    @EnsuresNonNull({"supplementalData"})
    public void resetSupplementalData(int r2) {
        ByteBuffer r0 = this.supplementalData;
        if (r0 != null) goto L5;
    L9:
        this.supplementalData = ByteBuffer.allocate(r2);
        return;
    L5:
        if (r0.capacity() < r2) goto L9;
        this.supplementalData.clear();
    }
}
