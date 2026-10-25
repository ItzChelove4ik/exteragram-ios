package org.webrtc;

import android.graphics.Matrix;
import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class VideoFrame implements RefCounted {
    private final Buffer buffer;
    private final int rotation;
    private final long timestampNs;

    public interface Buffer extends RefCounted {
        @CalledByNative("Buffer")
        Buffer cropAndScale(int r1, int r2, int r3, int r4, int r5, int r6);

        default int getBufferType() {
            return 0;
        }

        @CalledByNative("Buffer")
        int getHeight();

        @CalledByNative("Buffer")
        int getWidth();

        @Override // org.webrtc.RefCounted
        @CalledByNative("Buffer")
        void release();

        @Override // org.webrtc.RefCounted
        @CalledByNative("Buffer")
        void retain();

        @CalledByNative("Buffer")
        I420Buffer toI420();
    }

    public interface I420Buffer extends Buffer {
        @Override // org.webrtc.VideoFrame.Buffer
        default int getBufferType() {
            return 1;
        }

        @CalledByNative("I420Buffer")
        ByteBuffer getDataU();

        @CalledByNative("I420Buffer")
        ByteBuffer getDataV();

        @CalledByNative("I420Buffer")
        ByteBuffer getDataY();

        @CalledByNative("I420Buffer")
        int getStrideU();

        @CalledByNative("I420Buffer")
        int getStrideV();

        @CalledByNative("I420Buffer")
        int getStrideY();
    }

    public interface TextureBuffer extends Buffer {

        public enum Type extends Enum<Type> {
            private static final /* synthetic */ Type[] $VALUES = null;
            public static final Type OES = null;
            public static final Type RGB = null;
            private final int glTarget;

            private static /* synthetic */ Type[] $values() {
                return new Type[]{OES, RGB};
            }

            static {
                OES = new Type("OES", 0, 36197);
                RGB = new Type("RGB", 1, 3553);
                $VALUES = $values();
            }

            Type(String r1, int r2, int r3) {
                this.glTarget = r3;
            }

            public static Type valueOf(String r1) {
                return (Type) Enum.valueOf(Type.class, r1);
            }

            public static Type[] values() {
                return (Type[]) $VALUES.clone();
            }

            public int getGlTarget() {
                return this.glTarget;
            }
        }

        default TextureBuffer applyTransformMatrix(Matrix r1, int r2, int r3) {
            throw new UnsupportedOperationException("Not implemented");
        }

        int getTextureId();

        Matrix getTransformMatrix();

        Type getType();

        default int getUnscaledHeight() {
            return getHeight();
        }

        default int getUnscaledWidth() {
            return getWidth();
        }
    }

    @CalledByNative
    public VideoFrame(Buffer r3, int r4, long r5) {
        if (r3 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("buffer not allowed to be null");
        throw null;
    L5:
        if ((r4 % 90) != 0) goto L8;
        this.buffer = r3;
        this.rotation = r4;
        this.timestampNs = r5;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("rotation must be a multiple of 90");
        throw null;
    }

    @CalledByNative
    public Buffer getBuffer() {
        return this.buffer;
    }

    public int getRotatedHeight() {
        int r0 = this.rotation % 180;
        Buffer r1 = this.buffer;
        if (r0 != 0) goto L7;
        return r1.getHeight();
    L7:
        return r1.getWidth();
    }

    public int getRotatedWidth() {
        int r0 = this.rotation % 180;
        Buffer r1 = this.buffer;
        if (r0 != 0) goto L7;
        return r1.getWidth();
    L7:
        return r1.getHeight();
    }

    @CalledByNative
    public int getRotation() {
        return this.rotation;
    }

    @CalledByNative
    public long getTimestampNs() {
        return this.timestampNs;
    }

    @Override // org.webrtc.RefCounted
    @CalledByNative
    public void release() {
        this.buffer.release();
    }

    @Override // org.webrtc.RefCounted
    public void retain() {
        this.buffer.retain();
    }
}
