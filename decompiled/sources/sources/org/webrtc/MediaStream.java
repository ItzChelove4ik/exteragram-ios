package org.webrtc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class MediaStream {
    private static final String TAG = "MediaStream";
    public final List<AudioTrack> audioTracks;
    private long nativeStream;
    public final List<VideoTrack> preservedVideoTracks;
    public final List<VideoTrack> videoTracks;

    @CalledByNative
    public MediaStream(long r2) {
        this.audioTracks = new ArrayList();
        this.videoTracks = new ArrayList();
        this.preservedVideoTracks = new ArrayList();
        this.nativeStream = r2;
    }

    private void checkMediaStreamExists() {
        if (this.nativeStream == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("MediaStream has been disposed.");
    }

    private static native boolean nativeAddAudioTrackToNativeStream(long r0, long r2);

    private static native boolean nativeAddVideoTrackToNativeStream(long r0, long r2);

    private static native String nativeGetId(long r0);

    private static native boolean nativeRemoveAudioTrack(long r0, long r2);

    private static native boolean nativeRemoveVideoTrack(long r0, long r2);

    private static void removeMediaStreamTrack(List<? extends MediaStreamTrack> r3, long r4) {
        Iterator<? extends MediaStreamTrack> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L9;
        MediaStreamTrack r0 = r32.next();
        if (r0.getNativeMediaStreamTrack() != r4) goto L4;
        r0.dispose();
        r32.remove();
        return;
    L9:
        Logging.e(TAG, "Couldn't not find track");
    }

    @CalledByNative
    public void addNativeAudioTrack(long r2) {
        this.audioTracks.add(new AudioTrack(r2));
    }

    @CalledByNative
    public void addNativeVideoTrack(long r2) {
        this.videoTracks.add(new VideoTrack(r2));
    }

    public boolean addPreservedTrack(VideoTrack r5) {
        checkMediaStreamExists();
        if (nativeAddVideoTrackToNativeStream(this.nativeStream, r5.getNativeVideoTrack()) == false) goto L6;
        this.preservedVideoTracks.add(r5);
        return true;
    L6:
        return false;
    }

    public boolean addTrack(AudioTrack r5) {
        checkMediaStreamExists();
        if (nativeAddAudioTrackToNativeStream(this.nativeStream, r5.getNativeAudioTrack()) == false) goto L6;
        this.audioTracks.add(r5);
        return true;
    L6:
        return false;
    }

    public boolean addTrack(VideoTrack r5) {
        checkMediaStreamExists();
        if (nativeAddVideoTrackToNativeStream(this.nativeStream, r5.getNativeVideoTrack()) == false) goto L6;
        this.videoTracks.add(r5);
        return true;
    L6:
        return false;
    }

    @CalledByNative
    public void dispose() {
        checkMediaStreamExists();
    L4:
        if (this.audioTracks.isEmpty() == true) goto L7;
        AudioTrack r0 = this.audioTracks.get(0);
        removeTrack(r0);
        r0.dispose();
    L7:
        if (this.videoTracks.isEmpty() == true) goto L10;
        VideoTrack r02 = this.videoTracks.get(0);
        removeTrack(r02);
        r02.dispose();
    L10:
        if (this.preservedVideoTracks.isEmpty() == true) goto L12;
        removeTrack(this.preservedVideoTracks.get(0));
        goto L10
    L12:
        JniCommon.nativeReleaseRef(this.nativeStream);
        this.nativeStream = 0;
    }

    public String getId() {
        checkMediaStreamExists();
        return nativeGetId(this.nativeStream);
    }

    public long getNativeMediaStream() {
        checkMediaStreamExists();
        return this.nativeStream;
    }

    @CalledByNative
    public void removeAudioTrack(long r1) {
        removeMediaStreamTrack(this.audioTracks, r1);
    }

    public boolean removeTrack(AudioTrack r3) {
        checkMediaStreamExists();
        this.audioTracks.remove(r3);
        return nativeRemoveAudioTrack(this.nativeStream, r3.getNativeAudioTrack());
    }

    public boolean removeTrack(VideoTrack r3) {
        checkMediaStreamExists();
        this.videoTracks.remove(r3);
        this.preservedVideoTracks.remove(r3);
        return nativeRemoveVideoTrack(this.nativeStream, r3.getNativeVideoTrack());
    }

    @CalledByNative
    public void removeVideoTrack(long r1) {
        removeMediaStreamTrack(this.videoTracks, r1);
    }

    public String toString() {
        return "[" + getId() + ":A=" + this.audioTracks.size() + ":V=" + this.videoTracks.size() + "]";
    }
}
