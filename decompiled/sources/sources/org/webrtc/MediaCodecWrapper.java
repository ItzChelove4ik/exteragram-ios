package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
interface MediaCodecWrapper {
    void configure(MediaFormat r1, Surface r2, MediaCrypto r3, int r4);

    Surface createInputSurface();

    int dequeueInputBuffer(long r1);

    int dequeueOutputBuffer(MediaCodec.BufferInfo r1, long r2);

    void flush();

    MediaCodecInfo getCodecInfo();

    ByteBuffer getInputBuffer(int r1);

    MediaFormat getInputFormat();

    ByteBuffer getOutputBuffer(int r1);

    MediaFormat getOutputFormat();

    MediaFormat getOutputFormat(int r1);

    void queueInputBuffer(int r1, int r2, int r3, long r4, int r6);

    void release();

    void releaseOutputBuffer(int r1, boolean r2);

    void setParameters(Bundle r1);

    void start();

    void stop();
}
