package org.webrtc;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class EncodedImage implements RefCounted {
    public final ByteBuffer buffer;
    public final long captureTimeMs;
    public final long captureTimeNs;
    public final int encodedHeight;
    public final int encodedWidth;
    public final FrameType frameType;
    public final Integer qp;
    private final RefCountDelegate refCountDelegate;
    public final int rotation;

    public static class Builder {
        private ByteBuffer buffer;
        private long captureTimeNs;
        private int encodedHeight;
        private int encodedWidth;
        private FrameType frameType;
        private Integer qp;
        private Runnable releaseCallback;
        private int rotation;

        private Builder() {
        }

        public /* synthetic */ Builder(EncodedImageIA r1) {
            this();
        }

        public EncodedImage createEncodedImage() {
            return new EncodedImage(this.buffer, this.releaseCallback, this.encodedWidth, this.encodedHeight, this.captureTimeNs, this.frameType, this.rotation, this.qp, null);
        }

        public Builder setBuffer(ByteBuffer r1, Runnable r2) {
            this.buffer = r1;
            this.releaseCallback = r2;
            return this;
        }

        @Deprecated
        public Builder setCaptureTimeMs(long r2) {
            this.captureTimeNs = TimeUnit.MILLISECONDS.toNanos(r2);
            return this;
        }

        public Builder setCaptureTimeNs(long r1) {
            this.captureTimeNs = r1;
            return this;
        }

        public Builder setEncodedHeight(int r1) {
            this.encodedHeight = r1;
            return this;
        }

        public Builder setEncodedWidth(int r1) {
            this.encodedWidth = r1;
            return this;
        }

        public Builder setFrameType(FrameType r1) {
            this.frameType = r1;
            return this;
        }

        public Builder setQp(Integer r1) {
            this.qp = r1;
            return this;
        }

        public Builder setRotation(int r1) {
            this.rotation = r1;
            return this;
        }
    }

    public enum FrameType extends Enum<FrameType> {
        private static final /* synthetic */ FrameType[] $VALUES = null;
        public static final FrameType EmptyFrame = null;
        public static final FrameType VideoFrameDelta = null;
        public static final FrameType VideoFrameKey = null;
        private final int nativeIndex;

        private static /* synthetic */ FrameType[] $values() {
            return new FrameType[]{EmptyFrame, VideoFrameKey, VideoFrameDelta};
        }

        static {
            EmptyFrame = new FrameType("EmptyFrame", 0, 0);
            VideoFrameKey = new FrameType("VideoFrameKey", 1, 3);
            VideoFrameDelta = new FrameType("VideoFrameDelta", 2, 4);
            $VALUES = $values();
        }

        FrameType(String r1, int r2, int r3) {
            this.nativeIndex = r3;
        }

        @CalledByNative("FrameType")
        public static FrameType fromNativeIndex(int r5) {
            FrameType[] r0 = values();
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L8;
            FrameType r3 = r0[r2];
            if (r3.getNative() == r5) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return r3;
        L8:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown native frame type: ", r5);
            return null;
        }

        public static FrameType valueOf(String r1) {
            return (FrameType) Enum.valueOf(FrameType.class, r1);
        }

        public static FrameType[] values() {
            return (FrameType[]) $VALUES.clone();
        }

        public int getNative() {
            return this.nativeIndex;
        }
    }

    @CalledByNative
    private EncodedImage(ByteBuffer r1, Runnable r2, int r3, int r4, long r5, FrameType r7, int r8, Integer r9) {
        this.buffer = r1;
        this.encodedWidth = r3;
        this.encodedHeight = r4;
        this.captureTimeMs = r5 / 1000000;
        this.captureTimeNs = r5;
        this.frameType = r7;
        this.rotation = r8;
        this.qp = r9;
        this.refCountDelegate = new RefCountDelegate(r2);
    }

    public /* synthetic */ EncodedImage(ByteBuffer r1, Runnable r2, int r3, int r4, long r5, FrameType r7, int r8, Integer r9, EncodedImageIA r10) {
        this(r1, r2, r3, r4, r5, r7, r8, r9);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @CalledByNative
    private ByteBuffer getBuffer() {
        return this.buffer;
    }

    @CalledByNative
    private long getCaptureTimeNs() {
        return this.captureTimeNs;
    }

    @CalledByNative
    private int getEncodedHeight() {
        return this.encodedHeight;
    }

    @CalledByNative
    private int getEncodedWidth() {
        return this.encodedWidth;
    }

    @CalledByNative
    private int getFrameType() {
        return this.frameType.getNative();
    }

    @CalledByNative
    private Integer getQp() {
        return this.qp;
    }

    @CalledByNative
    private int getRotation() {
        return this.rotation;
    }

    @Override // org.webrtc.RefCounted
    public void release() {
        this.refCountDelegate.release();
    }

    @Override // org.webrtc.RefCounted
    public void retain() {
        this.refCountDelegate.retain();
    }
}
