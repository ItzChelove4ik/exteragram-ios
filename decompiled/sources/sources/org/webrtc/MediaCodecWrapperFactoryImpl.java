package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
class MediaCodecWrapperFactoryImpl implements MediaCodecWrapperFactory {

    public static class MediaCodecWrapperImpl implements MediaCodecWrapper {
        private final MediaCodec mediaCodec;

        public MediaCodecWrapperImpl(MediaCodec r1) {
            this.mediaCodec = r1;
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void configure(MediaFormat r1, Surface r2, MediaCrypto r3, int r4) {
            this.mediaCodec.configure(r1, r2, r3, r4);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public Surface createInputSurface() {
            return this.mediaCodec.createInputSurface();
        }

        @Override // org.webrtc.MediaCodecWrapper
        public int dequeueInputBuffer(long r1) {
            return this.mediaCodec.dequeueInputBuffer(r1);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public int dequeueOutputBuffer(MediaCodec.BufferInfo r1, long r2) {
            return this.mediaCodec.dequeueOutputBuffer(r1, r2);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void flush() {
            this.mediaCodec.flush();
        }

        @Override // org.webrtc.MediaCodecWrapper
        public MediaCodecInfo getCodecInfo() {
            return this.mediaCodec.getCodecInfo();
        }

        @Override // org.webrtc.MediaCodecWrapper
        public ByteBuffer getInputBuffer(int r1) {
            return this.mediaCodec.getInputBuffer(r1);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public MediaFormat getInputFormat() {
            return this.mediaCodec.getInputFormat();
        }

        @Override // org.webrtc.MediaCodecWrapper
        public ByteBuffer getOutputBuffer(int r1) {
            return this.mediaCodec.getOutputBuffer(r1);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public MediaFormat getOutputFormat() {
            return this.mediaCodec.getOutputFormat();
        }

        @Override // org.webrtc.MediaCodecWrapper
        public MediaFormat getOutputFormat(int r1) {
            return this.mediaCodec.getOutputFormat(r1);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void queueInputBuffer(int r1, int r2, int r3, long r4, int r6) {
            this.mediaCodec.queueInputBuffer(r1, r2, r3, r4, r6);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void release() {
            this.mediaCodec.release();
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void releaseOutputBuffer(int r1, boolean r2) {
            this.mediaCodec.releaseOutputBuffer(r1, r2);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void setParameters(Bundle r1) {
            this.mediaCodec.setParameters(r1);
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void start() {
            this.mediaCodec.start();
        }

        @Override // org.webrtc.MediaCodecWrapper
        public void stop() {
            this.mediaCodec.stop();
        }
    }

    public MediaCodecWrapperFactoryImpl() {
    }

    @Override // org.webrtc.MediaCodecWrapperFactory
    public MediaCodecWrapper createByCodecName(String r1) {
        return new MediaCodecWrapperImpl(MediaCodec.createByCodecName(r1));
    }
}
