package androidx.media3.decoder;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class SimpleDecoderOutputBuffer extends DecoderOutputBuffer {
    public ByteBuffer data;
    private final DecoderOutputBuffer.Owner<SimpleDecoderOutputBuffer> owner;

    public SimpleDecoderOutputBuffer(DecoderOutputBuffer.Owner<SimpleDecoderOutputBuffer> r1) {
        this.owner = r1;
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer, androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        ByteBuffer r0 = this.data;
        if (r0 == null) goto L6;
        r0.clear();
        return;
    }

    public ByteBuffer grow(int r5) {
        ByteBuffer r0 = (ByteBuffer) Assertions.checkNotNull(this.data);
        if (r5 < r0.limit()) goto L5;
        boolean r1 = true;
    L6:
        Assertions.checkArgument(r1);
        ByteBuffer r12 = ByteBuffer.allocateDirect(r5).order(ByteOrder.nativeOrder());
        int r3 = r0.position();
        r0.position(0);
        r12.put(r0);
        r12.position(r3);
        r12.limit(r5);
        this.data = r12;
        return r12;
    L5:
        r1 = false;
        goto L6
    }

    public ByteBuffer init(long r1, int r3) {
        this.timeUs = r1;
        ByteBuffer r12 = this.data;
        if (r12 != null) goto L5;
    L6:
        this.data = ByteBuffer.allocateDirect(r3).order(ByteOrder.nativeOrder());
    L7:
        this.data.position(0);
        this.data.limit(r3);
        return this.data;
    L5:
        if (r12.capacity() >= r3) goto L7;
        goto L6
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer
    public void release() {
        this.owner.releaseOutputBuffer(this);
    }
}
