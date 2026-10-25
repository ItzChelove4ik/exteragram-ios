package org.webrtc;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.IdentityHashMap;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class VideoTrack extends MediaStreamTrack {
    private final IdentityHashMap<VideoSink, Long> sinks;

    public VideoTrack(long r1) {
        super(r1);
        this.sinks = new IdentityHashMap();
    }

    private static native void nativeAddSink(long r0, long r2);

    private static native void nativeFreeSink(long r0);

    private static native void nativeRemoveSink(long r0, long r2);

    private static native long nativeWrapSink(VideoSink r0);

    public void addSink(VideoSink r5) {
        if (r5 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("The VideoSink is not allowed to be null");
        return;
    L4:
        if (this.sinks.containsKey(r5) == true) goto L9;
        long r0 = nativeWrapSink(r5);
        this.sinks.put(r5, Long.valueOf(r0));
        nativeAddSink(getNativeMediaStreamTrack(), r0);
        return;
    }

    @Override // org.webrtc.MediaStreamTrack
    public void dispose() {
        Iterator<Long> r0 = this.sinks.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        long r1 = r0.next().longValue();
        nativeRemoveSink(getNativeMediaStreamTrack(), r1);
        nativeFreeSink(r1);
        goto L4
    L6:
        this.sinks.clear();
        super.dispose();
    }

    public long getNativeVideoTrack() {
        return getNativeMediaStreamTrack();
    }

    public void removeSink(VideoSink r5) {
        Long r52 = this.sinks.remove(r5);
        if (r52 == null) goto L6;
        nativeRemoveSink(getNativeMediaStreamTrack(), r52.longValue());
        nativeFreeSink(r52.longValue());
        return;
    }
}
