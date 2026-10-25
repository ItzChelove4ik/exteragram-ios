package org.webrtc;

import java.util.List;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraVideoCapturer;

/* loaded from: classes6.dex */
public interface CameraEnumerator {
    CameraVideoCapturer createCapturer(String r1, CameraVideoCapturer.CameraEventsHandler r2);

    String[] getDeviceNames();

    List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String r1);

    boolean isBackFacing(String r1);

    boolean isFrontFacing(String r1);
}
