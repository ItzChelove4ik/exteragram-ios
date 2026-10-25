package org.chromium.net.apihelpers;

import java.nio.ByteBuffer;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* loaded from: classes5.dex */
public abstract class UploadDataProviders {

    public static final class ByteBufferUploadProvider extends UploadDataProvider {
        private final ByteBuffer mUploadBuffer;

        private ByteBufferUploadProvider(ByteBuffer r1) {
            this.mUploadBuffer = r1;
        }

        public /* synthetic */ ByteBufferUploadProvider(ByteBuffer r1, UploadDataProvidersIA r2) {
            this(r1);
        }

        @Override // org.chromium.net.UploadDataProvider
        public long getLength() {
            return this.mUploadBuffer.limit();
        }

        @Override // org.chromium.net.UploadDataProvider
        public void read(UploadDataSink r5, ByteBuffer r6) {
            if (r6.hasRemaining() == false) goto L10;
            int r0 = r6.remaining();
            int r1 = this.mUploadBuffer.remaining();
            ByteBuffer r2 = this.mUploadBuffer;
            if (r0 < r1) goto L7;
            r6.put(r2);
        L8:
            r5.onReadSucceeded(false);
            return;
        L7:
            int r02 = r2.limit();
            ByteBuffer r12 = this.mUploadBuffer;
            ByteBuffer r13 = (ByteBuffer) r12.limit(r12.position() + r6.remaining());
            r6.put(this.mUploadBuffer);
            ByteBuffer r4 = (ByteBuffer) this.mUploadBuffer.limit(r02);
            goto L8
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("Cronet passed a buffer with no bytes remaining");
        }

        @Override // org.chromium.net.UploadDataProvider
        public void rewind(UploadDataSink r2) {
            ByteBuffer r1 = (ByteBuffer) this.mUploadBuffer.position(0);
            r2.onRewindSucceeded();
        }
    }

    public static UploadDataProvider create(byte[] r2) {
        return create(r2, 0, r2.length);
    }

    public static UploadDataProvider create(byte[] r1, int r2, int r3) {
        return new ByteBufferUploadProvider(ByteBuffer.wrap(r1, r2, r3).slice(), null);
    }
}
