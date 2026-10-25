package org.telegram.messenger.voip;

import android.graphics.Point;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.voip.Instance;
import org.telegram.messenger.voip.VoIPService;
import org.webrtc.ContextUtils;
import org.webrtc.VideoSink;

/* loaded from: classes6.dex */
public class NativeInstance {
    private AudioLevelsCallback audioLevelsCallback;
    private RequestBroadcastPartCallback cancelRequestBroadcastPartCallback;
    private Instance.FinalState finalState;
    private boolean isGroup;
    private long nativePtr;
    private Instance.OnRemoteMediaStateUpdatedListener onRemoteMediaStateUpdatedListener;
    private Instance.OnSignalBarsUpdatedListener onSignalBarsUpdatedListener;
    private Instance.OnSignalingDataListener onSignalDataListener;
    private Instance.OnStateUpdatedListener onStateUpdatedListener;
    private PayloadCallback payloadCallback;
    private String persistentStateFilePath;
    private RequestBroadcastPartCallback requestBroadcastPartCallback;
    private RequestCurrentTimeCallback requestCurrentTimeCallback;
    private CountDownLatch stopBarrier;
    private float[] temp;
    private VideoSourcesCallback unknownParticipantsCallback;

    public interface AudioLevelsCallback {
        void run(int[] r1, float[] r2, boolean[] r3);
    }

    public interface PayloadCallback {
        void run(int r1, String r2);
    }

    public interface RequestBroadcastPartCallback {
        void run(long r1, long r3, int r5, int r6);
    }

    public interface RequestCurrentTimeCallback {
        void run(long r1);
    }

    public static class SsrcGroup {
        public String semantics;
        public int[] ssrcs;

        public SsrcGroup() {
        }
    }

    public interface VideoSourcesCallback {
        void run(long r1, int[] r3);
    }

    public static /* synthetic */ void $r8$lambda$19U_gvbBNNDoxYB7Pk292IVfkzE(NativeInstance r0, boolean r1, boolean r2) {
        r0.lambda$onNetworkStateUpdated$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8D2nDF9pW2QMBwYiuocYAmbGuUw(NativeInstance r0, int[] r1, float[] r2, boolean[] r3) {
        r0.lambda$onAudioLevelsUpdated$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$L2HkwerKwmAsw7NFWbFEBHQzlNg(NativeInstance r0, int r1, String r2) {
        r0.lambda$onEmitJoinPayload$3(r1, r2);
    }

    /* renamed from: $r8$lambda$WbBN7JPpg1RBvMVwSXo7BemD-KI, reason: not valid java name */
    public static /* synthetic */ void m5279$r8$lambda$WbBN7JPpg1RBvMVwSXo7BemDKI(NativeInstance r0, long r1, int[] r3) {
        r0.lambda$onParticipantDescriptionsRequired$2(r1, r3);
    }

    public NativeInstance() {
        this.temp = new float[1];
    }

    public static native long createVideoCapturer(VideoSink r0, int r1);

    public static native void destroyVideoCapturer(long r0);

    public static native String[] getAllVersions();

    private /* synthetic */ void lambda$onAudioLevelsUpdated$1(int[] r1, float[] r2, boolean[] r3) {
        this.audioLevelsCallback.run(r1, r2, r3);
    }

    private /* synthetic */ void lambda$onEmitJoinPayload$3(int r1, String r2) {
        this.payloadCallback.run(r1, r2);
    }

    private /* synthetic */ void lambda$onNetworkStateUpdated$0(boolean r1, boolean r2) {
        this.onStateUpdatedListener.onStateUpdated(r1 ? 1 : 0, r2);
    }

    private /* synthetic */ void lambda$onParticipantDescriptionsRequired$2(long r1, int[] r3) {
        this.unknownParticipantsCallback.run(r1, r3);
    }

    public static NativeInstance make(String r13, Instance.Config r14, String r15, Instance.Endpoint[] r16, Instance.Proxy r17, int r18, Instance.EncryptionKey r19, VideoSink r20, long r21, AudioLevelsCallback r23) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("create new tgvoip instance, version " + r13);
    L5:
        NativeInstance r2 = new NativeInstance();
        r2.persistentStateFilePath = r15;
        r2.audioLevelsCallback = r23;
        Point r0 = AndroidUtilities.displaySize;
        float r02 = Math.min(r0.x, r0.y);
        Point r1 = AndroidUtilities.displaySize;
        r2.nativePtr = makeNativeInstance(r13, r2, r14, r15, r16, r17, r18, r19, r20, r21, r02 / Math.max(r1.x, r1.y));
        return r2;
    }

    public static NativeInstance makeGroup(String r3, long r4, boolean r6, boolean r7, PayloadCallback r8, AudioLevelsCallback r9, VideoSourcesCallback r10, RequestBroadcastPartCallback r11, RequestBroadcastPartCallback r12, RequestCurrentTimeCallback r13, boolean r14) {
        ContextUtils.initialize(ApplicationLoader.applicationContext);
        NativeInstance r32 = new NativeInstance();
        r32.payloadCallback = r8;
        r32.audioLevelsCallback = r9;
        r32.unknownParticipantsCallback = r10;
        r32.requestBroadcastPartCallback = r11;
        r32.cancelRequestBroadcastPartCallback = r12;
        r32.requestCurrentTimeCallback = r13;
        r32.isGroup = true;
        r32.nativePtr = makeGroupNativeInstance(r32, r3, SharedConfig.disableVoiceAudioEffects, r4, r6, r7, r14);
        return r32;
    }

    private static native long makeGroupNativeInstance(NativeInstance r0, String r1, boolean r2, long r3, boolean r5, boolean r6, boolean r7);

    private static native long makeNativeInstance(String r0, NativeInstance r1, Instance.Config r2, String r3, Instance.Endpoint[] r4, Instance.Proxy r5, int r6, Instance.EncryptionKey r7, VideoSink r8, long r9, float r11);

    private void onAudioLevelsUpdated(int[] r2, float[] r3, boolean[] r4) {
        if (this.isGroup == false) goto L8;
        if (r2 == null) goto L8;
        if (r2.length != 0) goto L8;
        return;
    L8:
        AndroidUtilities.runOnUIThread(new NativeInstance$$ExternalSyntheticLambda2(this, r2, r3, r4));
    }

    private void onCancelRequestBroadcastPart(long r8, int r10, int r11) {
        this.cancelRequestBroadcastPartCallback.run(r8, 0, 0, 0);
    }

    private void onEmitJoinPayload(String r2, int r3) {
        AndroidUtilities.runOnUIThread(new NativeInstance$$ExternalSyntheticLambda0(this, r3, r2));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private void onNetworkStateUpdated(boolean r2, boolean r3) {
        if (this.onStateUpdatedListener == null) goto L6;
        AndroidUtilities.runOnUIThread(new NativeInstance$$ExternalSyntheticLambda1(this, r2, r3));
        return;
    }

    private void onParticipantDescriptionsRequired(long r2, int[] r4) {
        if (this.unknownParticipantsCallback != null) goto L5;
        return;
    L5:
        AndroidUtilities.runOnUIThread(new NativeInstance$$ExternalSyntheticLambda3(this, r2, r4));
    }

    private void onRemoteMediaStateUpdated(int r1, int r2) {
        Instance.OnRemoteMediaStateUpdatedListener r0 = this.onRemoteMediaStateUpdatedListener;
        if (r0 == null) goto L6;
        r0.onMediaStateUpdated(r1, r2);
        return;
    }

    private void onRequestBroadcastPart(long r1, long r3, int r5, int r6) {
        this.requestBroadcastPartCallback.run(r1, r3, r5, r6);
    }

    private void onSignalBarsUpdated(int r1) {
        Instance.OnSignalBarsUpdatedListener r0 = this.onSignalBarsUpdatedListener;
        if (r0 == null) goto L6;
        r0.onSignalBarsUpdated(r1);
        return;
    }

    private void onSignalingData(byte[] r1) {
        Instance.OnSignalingDataListener r0 = this.onSignalDataListener;
        if (r0 == null) goto L6;
        r0.onSignalingData(r1);
        return;
    }

    private void onStateUpdated(int r2) {
        Instance.OnStateUpdatedListener r1 = this.onStateUpdatedListener;
        if (r1 == null) goto L6;
        r1.onStateUpdated(r2, false);
        return;
    }

    private void onStop(Instance.FinalState r1) {
        this.finalState = r1;
        CountDownLatch r0 = this.stopBarrier;
        if (r0 == null) goto L6;
        r0.countDown();
        return;
    }

    private void requestCurrentTime(long r1) {
        this.requestCurrentTimeCallback.run(r1);
    }

    public static native void setVideoStateCapturer(long r0, int r2);

    private native void stopGroupNative();

    private native void stopNative();

    public static native void switchCameraCapturer(long r0, boolean r2);

    public native void activateVideoCapturer(long r1);

    public native long addIncomingVideoOutput(int r1, String r2, SsrcGroup[] r3, VideoSink r4, long r5);

    public native void clearVideoCapturer();

    public native String getDebugInfo();

    public native String getLastError();

    public int getPeerCapabilities() {
        return 0;
    }

    public native byte[] getPersistentState();

    public native long getPreferredRelayId();

    public native Instance.TrafficStats getTrafficStats();

    public native boolean hasVideoCapturer();

    public boolean isGroup() {
        return this.isGroup;
    }

    public native void onMediaDescriptionAvailable(long r1, VoIPService.RequestedParticipant[] r3);

    public native void onRequestTimeComplete(long r1, long r3);

    public native void onSignalingDataReceive(byte[] r1);

    public native void onStreamPartAvailable(long r1, ByteBuffer r3, int r4, long r5, int r7, int r8);

    public native void prepareForStream(boolean r1);

    public native void removeIncomingVideoOutput(long r1);

    public native void resetGroupInstance(boolean r1, boolean r2);

    public native void setAudioOutputGainControlEnabled(boolean r1);

    public native void setBufferSize(int r1);

    public native void setConferenceCallId(long r1);

    public native void setEchoCancellationStrength(int r1);

    public native void setGlobalServerConfig(String r1);

    public native void setJoinResponsePayload(String r1);

    public native void setMuteMicrophone(boolean r1);

    public native void setNetworkType(int r1);

    public native void setNoiseSuppressionEnabled(boolean r1);

    public void setOnRemoteMediaStateUpdatedListener(Instance.OnRemoteMediaStateUpdatedListener r1) {
        this.onRemoteMediaStateUpdatedListener = r1;
    }

    public void setOnSignalBarsUpdatedListener(Instance.OnSignalBarsUpdatedListener r1) {
        this.onSignalBarsUpdatedListener = r1;
    }

    public void setOnSignalDataListener(Instance.OnSignalingDataListener r1) {
        this.onSignalDataListener = r1;
    }

    public void setOnStateUpdatedListener(Instance.OnStateUpdatedListener r1) {
        this.onStateUpdatedListener = r1;
    }

    public native void setVideoEndpointQuality(String r1, int r2);

    public native void setVideoState(int r1);

    public native void setVolume(int r1, double r2);

    public native void setupOutgoingVideo(VideoSink r1, int r2);

    public native void setupOutgoingVideoCreated(long r1);

    public Instance.FinalState stop() {
        this.stopBarrier = new CountDownLatch(1);
        stopNative();
        this.stopBarrier.await();     // Catch: Exception -> L5
    L8:
        return this.finalState;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public void stopGroup() {
        stopGroupNative();
    }

    public native void switchCamera(boolean r1);
}
