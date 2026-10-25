package org.webrtc;

import android.content.Context;
import android.os.Process;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;
import org.webrtc.NativeLibrary;
import org.webrtc.PeerConnection;
import org.webrtc.audio.AudioDeviceModule;
import org.webrtc.audio.JavaAudioDeviceModule;

/* loaded from: classes6.dex */
public class PeerConnectionFactory {
    private static final String TAG = "PeerConnectionFactory";
    public static final String TRIAL_ENABLED = "Enabled";
    private static final String VIDEO_CAPTURER_THREAD_NAME = "VideoCapturerThread";

    @Deprecated
    public static final String VIDEO_FRAME_EMIT_TRIAL = "VideoFrameEmit";
    private static volatile boolean internalTracerInitialized;
    private static ThreadInfo staticNetworkThread;
    private static ThreadInfo staticSignalingThread;
    private static ThreadInfo staticWorkerThread;
    private long nativeFactory;
    private volatile ThreadInfo networkThread;
    private volatile ThreadInfo signalingThread;
    private volatile ThreadInfo workerThread;

    public static class Builder {
        private AudioDecoderFactoryFactory audioDecoderFactoryFactory;
        private AudioDeviceModule audioDeviceModule;
        private AudioEncoderFactoryFactory audioEncoderFactoryFactory;
        private AudioProcessingFactory audioProcessingFactory;
        private FecControllerFactoryFactoryInterface fecControllerFactoryFactory;
        private NetEqFactoryFactory neteqFactoryFactory;
        private NetworkControllerFactoryFactory networkControllerFactoryFactory;
        private NetworkStatePredictorFactoryFactory networkStatePredictorFactoryFactory;
        private Options options;
        private VideoDecoderFactory videoDecoderFactory;
        private VideoEncoderFactory videoEncoderFactory;

        private Builder() {
            this.audioEncoderFactoryFactory = new BuiltinAudioEncoderFactoryFactory();
            this.audioDecoderFactoryFactory = new BuiltinAudioDecoderFactoryFactory();
        }

        public /* synthetic */ Builder(PeerConnectionFactoryIA r1) {
            this();
        }

        public PeerConnectionFactory createPeerConnectionFactory() {
            PeerConnectionFactory.m22372$$Nest$smcheckInitializeHasBeenCalled();
            if (this.audioDeviceModule != null) goto L5;
            this.audioDeviceModule = JavaAudioDeviceModule.builder(ContextUtils.getApplicationContext()).createAudioDeviceModule();
        L5:
            Context r2 = ContextUtils.getApplicationContext();
            Options r3 = this.options;
            long r4 = this.audioDeviceModule.getNativeAudioDeviceModulePointer();
            long r6 = this.audioEncoderFactoryFactory.createNativeAudioEncoderFactory();
            long r8 = this.audioDecoderFactoryFactory.createNativeAudioDecoderFactory();
            VideoEncoderFactory r10 = this.videoEncoderFactory;
            VideoDecoderFactory r11 = this.videoDecoderFactory;
            AudioProcessingFactory r1 = this.audioProcessingFactory;
            long r12 = 0;
            if (r1 != null) goto L8;
            long r14 = 0;
        L9:
            FecControllerFactoryFactoryInterface r13 = this.fecControllerFactoryFactory;
            if (r13 != null) goto L12;
            long r16 = 0;
        L13:
            NetworkControllerFactoryFactory r15 = this.networkControllerFactoryFactory;
            if (r15 != null) goto L16;
            long r18 = 0;
        L17:
            NetworkStatePredictorFactoryFactory r17 = this.networkStatePredictorFactoryFactory;
            if (r17 != null) goto L20;
            long r20 = 0;
        L21:
            NetEqFactoryFactory r0 = this.neteqFactoryFactory;
            if (r0 == null) goto L26;
            r12 = r0.createNativeNetEqFactory();
        L26:
            return PeerConnectionFactory.m22373$$Nest$smnativeCreatePeerConnectionFactory(r2, r3, r4, r6, r8, r10, r11, r14, r16, r18, r20, r12);
        L20:
            r20 = r17.createNativeNetworkStatePredictorFactory();
            goto L21
        L16:
            r18 = r15.createNativeNetworkControllerFactory();
            goto L17
        L12:
            r16 = r13.createNative();
            goto L13
        L8:
            r14 = r1.createNative();
            goto L9
        }

        public Builder setAudioDecoderFactoryFactory(AudioDecoderFactoryFactory r1) {
            if (r1 == null) goto L5;
            this.audioDecoderFactoryFactory = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline1.m("PeerConnectionFactory.Builder does not accept a null AudioDecoderFactoryFactory.");
            return null;
        }

        public Builder setAudioDeviceModule(AudioDeviceModule r1) {
            this.audioDeviceModule = r1;
            return this;
        }

        public Builder setAudioEncoderFactoryFactory(AudioEncoderFactoryFactory r1) {
            if (r1 == null) goto L5;
            this.audioEncoderFactoryFactory = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline1.m("PeerConnectionFactory.Builder does not accept a null AudioEncoderFactoryFactory.");
            return null;
        }

        public Builder setAudioProcessingFactory(AudioProcessingFactory r1) {
            if (r1 == null) goto L5;
            this.audioProcessingFactory = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("PeerConnectionFactory builder does not accept a null AudioProcessingFactory.");
            return null;
        }

        public Builder setFecControllerFactoryFactoryInterface(FecControllerFactoryFactoryInterface r1) {
            this.fecControllerFactoryFactory = r1;
            return this;
        }

        public Builder setNetEqFactoryFactory(NetEqFactoryFactory r1) {
            this.neteqFactoryFactory = r1;
            return this;
        }

        public Builder setNetworkControllerFactoryFactory(NetworkControllerFactoryFactory r1) {
            this.networkControllerFactoryFactory = r1;
            return this;
        }

        public Builder setNetworkStatePredictorFactoryFactory(NetworkStatePredictorFactoryFactory r1) {
            this.networkStatePredictorFactoryFactory = r1;
            return this;
        }

        public Builder setOptions(Options r1) {
            this.options = r1;
            return this;
        }

        public Builder setVideoDecoderFactory(VideoDecoderFactory r1) {
            this.videoDecoderFactory = r1;
            return this;
        }

        public Builder setVideoEncoderFactory(VideoEncoderFactory r1) {
            this.videoEncoderFactory = r1;
            return this;
        }
    }

    public static class InitializationOptions {
        final Context applicationContext;
        final boolean enableInternalTracer;
        final String fieldTrials;
        Loggable loggable;
        Logging.Severity loggableSeverity;
        final NativeLibraryLoader nativeLibraryLoader;
        final String nativeLibraryName;

        public static class Builder {
            private final Context applicationContext;
            private boolean enableInternalTracer;
            private String fieldTrials;
            private Loggable loggable;
            private Logging.Severity loggableSeverity;
            private NativeLibraryLoader nativeLibraryLoader;
            private String nativeLibraryName;

            public Builder(Context r2) {
                this.fieldTrials = _UrlKt.FRAGMENT_ENCODE_SET;
                this.nativeLibraryLoader = new NativeLibrary.DefaultLoader();
                this.nativeLibraryName = "jingle_peerconnection_so";
                this.applicationContext = r2;
            }

            public InitializationOptions createInitializationOptions() {
                return new InitializationOptions(this.applicationContext, this.fieldTrials, this.enableInternalTracer, this.nativeLibraryLoader, this.nativeLibraryName, this.loggable, this.loggableSeverity, null);
            }

            public Builder setEnableInternalTracer(boolean r1) {
                this.enableInternalTracer = r1;
                return this;
            }

            public Builder setFieldTrials(String r1) {
                this.fieldTrials = r1;
                return this;
            }

            public Builder setInjectableLogger(Loggable r1, Logging.Severity r2) {
                this.loggable = r1;
                this.loggableSeverity = r2;
                return this;
            }

            public Builder setNativeLibraryLoader(NativeLibraryLoader r1) {
                this.nativeLibraryLoader = r1;
                return this;
            }

            public Builder setNativeLibraryName(String r1) {
                this.nativeLibraryName = r1;
                return this;
            }
        }

        private InitializationOptions(Context r1, String r2, boolean r3, NativeLibraryLoader r4, String r5, Loggable r6, Logging.Severity r7) {
            this.applicationContext = r1;
            this.fieldTrials = r2;
            this.enableInternalTracer = r3;
            this.nativeLibraryLoader = r4;
            this.nativeLibraryName = r5;
            this.loggable = r6;
            this.loggableSeverity = r7;
        }

        public /* synthetic */ InitializationOptions(Context r1, String r2, boolean r3, NativeLibraryLoader r4, String r5, Loggable r6, Logging.Severity r7, PeerConnectionFactoryIA r8) {
            this(r1, r2, r3, r4, r5, r6, r7);
        }

        public static Builder builder(Context r1) {
            return new Builder(r1);
        }
    }

    public static class Options {
        public static final int ADAPTER_TYPE_ANY = 32;
        public static final int ADAPTER_TYPE_CELLULAR = 4;
        public static final int ADAPTER_TYPE_ETHERNET = 1;
        public static final int ADAPTER_TYPE_LOOPBACK = 16;
        public static final int ADAPTER_TYPE_UNKNOWN = 0;
        public static final int ADAPTER_TYPE_VPN = 8;
        public static final int ADAPTER_TYPE_WIFI = 2;
        public boolean disableEncryption;
        public boolean disableNetworkMonitor;
        public int networkIgnoreMask;

        public Options() {
        }

        @CalledByNative("Options")
        public boolean getDisableEncryption() {
            return this.disableEncryption;
        }

        @CalledByNative("Options")
        public boolean getDisableNetworkMonitor() {
            return this.disableNetworkMonitor;
        }

        @CalledByNative("Options")
        public int getNetworkIgnoreMask() {
            return this.networkIgnoreMask;
        }
    }

    public static class ThreadInfo {
        final Thread thread;
        final int tid;

        private ThreadInfo(Thread r1, int r2) {
            this.thread = r1;
            this.tid = r2;
        }

        public static ThreadInfo getCurrent() {
            return new ThreadInfo(Thread.currentThread(), Process.myTid());
        }
    }

    /* renamed from: -$$Nest$smcheckInitializeHasBeenCalled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22372$$Nest$smcheckInitializeHasBeenCalled() {
        checkInitializeHasBeenCalled();
    }

    /* renamed from: -$$Nest$smnativeCreatePeerConnectionFactory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PeerConnectionFactory m22373$$Nest$smnativeCreatePeerConnectionFactory(Context r0, Options r1, long r2, long r4, long r6, VideoEncoderFactory r8, VideoDecoderFactory r9, long r10, long r12, long r14, long r16, long r18) {
        return nativeCreatePeerConnectionFactory(r0, r1, r2, r4, r6, r8, r9, r10, r12, r14, r16, r18);
    }

    @CalledByNative
    public PeerConnectionFactory(long r3) {
        checkInitializeHasBeenCalled();
        if (r3 == 0) goto L6;
        this.nativeFactory = r3;
        return;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("Failed to initialize PeerConnectionFactory!");
        throw null;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    private static void checkInitializeHasBeenCalled() {
        if (NativeLibrary.isLoaded() == true) goto L5;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("PeerConnectionFactory.initialize was not called before creating a PeerConnectionFactory.");
        return;
    L5:
        if (ContextUtils.getApplicationContext() == null) goto L7;
    }

    private void checkPeerConnectionFactoryExists() {
        if (this.nativeFactory == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("PeerConnectionFactory has been disposed.");
    }

    public static String fieldTrialsFindFullName(String r1) {
        if (NativeLibrary.isLoaded() == true) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return nativeFindFieldTrialsFullName(r1);
    }

    public static void initialize(InitializationOptions r2) {
        ContextUtils.initialize(r2.applicationContext);
        NativeLibrary.initialize(r2.nativeLibraryLoader, r2.nativeLibraryName);
        nativeInitializeAndroidGlobals();
        nativeInitializeFieldTrials(r2.fieldTrials);
        if (r2.enableInternalTracer == true) goto L5;
    L7:
        Loggable r0 = r2.loggable;
        if (r0 == null) goto L11;
        Logging.injectLoggable(r0, r2.loggableSeverity);
        nativeInjectLoggable(new JNILogging(r2.loggable), r2.loggableSeverity.ordinal());
        return;
    L11:
        Logging.d(TAG, "PeerConnectionFactory was initialized without an injected Loggable. Any existing Loggable will be deleted.");
        Logging.deleteInjectedLoggable();
        nativeDeleteLoggable();
        return;
    L5:
        if (internalTracerInitialized == true) goto L7;
        initializeInternalTracer();
        goto L7
    }

    @Deprecated
    public static void initializeFieldTrials(String r0) {
        nativeInitializeFieldTrials(r0);
    }

    private static void initializeInternalTracer() {
        internalTracerInitialized = true;
        nativeInitializeInternalTracer();
    }

    private static native long nativeCreateAudioSource(long r0, MediaConstraints r2);

    private static native long nativeCreateAudioTrack(long r0, String r2, long r3);

    private static native long nativeCreateLocalMediaStream(long r0, String r2);

    private static native long nativeCreatePeerConnection(long r0, PeerConnection.RTCConfiguration r2, MediaConstraints r3, long r4, SSLCertificateVerifier r6);

    private static native PeerConnectionFactory nativeCreatePeerConnectionFactory(Context r0, Options r1, long r2, long r4, long r6, VideoEncoderFactory r8, VideoDecoderFactory r9, long r10, long r12, long r14, long r16, long r18);

    private static native long nativeCreateVideoSource(long r0, boolean r2, boolean r3);

    private static native long nativeCreateVideoTrack(long r0, String r2, long r3);

    private static native void nativeDeleteLoggable();

    private static native String nativeFindFieldTrialsFullName(String r0);

    private static native void nativeFreeFactory(long r0);

    private static native long nativeGetNativePeerConnectionFactory(long r0);

    private static native RtpCapabilities nativeGetRtpReceiverCapabilities(long r0, MediaStreamTrack.MediaType r2);

    private static native RtpCapabilities nativeGetRtpSenderCapabilities(long r0, MediaStreamTrack.MediaType r2);

    private static native void nativeInitializeAndroidGlobals();

    private static native void nativeInitializeFieldTrials(String r0);

    private static native void nativeInitializeInternalTracer();

    private static native void nativeInjectLoggable(JNILogging r0, int r1);

    private static native void nativePrintStackTrace(int r0);

    private static native void nativeShutdownInternalTracer();

    private static native boolean nativeStartAecDump(long r0, int r2, int r3);

    private static native boolean nativeStartInternalTracingCapture(String r0);

    private static native void nativeStopAecDump(long r0);

    private static native void nativeStopInternalTracingCapture();

    @CalledByNative
    private void onNetworkThreadReady() {
        this.networkThread = ThreadInfo.getCurrent();
        staticNetworkThread = this.networkThread;
        Logging.d(TAG, "onNetworkThreadReady");
    }

    @CalledByNative
    private void onSignalingThreadReady() {
        this.signalingThread = ThreadInfo.getCurrent();
        staticSignalingThread = this.signalingThread;
        Logging.d(TAG, "onSignalingThreadReady");
    }

    @CalledByNative
    private void onWorkerThreadReady() {
        this.workerThread = ThreadInfo.getCurrent();
        staticWorkerThread = this.workerThread;
        Logging.d(TAG, "onWorkerThreadReady");
    }

    private static void printStackTrace(ThreadInfo r6, boolean r7) {
        if (r6 == null) goto L14;
        String r0 = r6.thread.getName();
        StackTraceElement[] r1 = r6.thread.getStackTrace();
        if (r1.length <= 0) goto L9;
        Logging.w(TAG, r0 + " stacktrace:");
        int r2 = r1.length;
        int r4 = 0;
    L7:
        if (r4 >= r2) goto L9;
        Logging.w(TAG, r1[r4].toString());
        r4 = r4 + 1;
    L9:
        if (r7 == false) goto L13;
        Logging.w(TAG, "*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***");
        Logging.w(TAG, "pid: " + Process.myPid() + ", tid: " + r6.tid + ", name: " + r0 + "  >>> WebRTC <<<");
        nativePrintStackTrace(r6.tid);
        return;
    L13:
        return;
    }

    @Deprecated
    public static void printStackTraces() {
        printStackTrace(staticNetworkThread, false);
        printStackTrace(staticWorkerThread, false);
        printStackTrace(staticSignalingThread, false);
    }

    public static void shutdownInternalTracer() {
        internalTracerInitialized = false;
        nativeShutdownInternalTracer();
    }

    public static boolean startInternalTracingCapture(String r0) {
        return nativeStartInternalTracingCapture(r0);
    }

    public static void stopInternalTracingCapture() {
        nativeStopInternalTracingCapture();
    }

    public AudioSource createAudioSource(MediaConstraints r4) {
        checkPeerConnectionFactoryExists();
        return new AudioSource(nativeCreateAudioSource(this.nativeFactory, r4));
    }

    public AudioTrack createAudioTrack(String r6, AudioSource r7) {
        checkPeerConnectionFactoryExists();
        return new AudioTrack(nativeCreateAudioTrack(this.nativeFactory, r6, r7.getNativeAudioSource()));
    }

    public MediaStream createLocalMediaStream(String r4) {
        checkPeerConnectionFactoryExists();
        return new MediaStream(nativeCreateLocalMediaStream(this.nativeFactory, r4));
    }

    @Deprecated
    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> r2, MediaConstraints r3, PeerConnection.Observer r4) {
        PeerConnection.RTCConfiguration r0 = new PeerConnection.RTCConfiguration(r2);
        r0.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        return createPeerConnection(r0, r3, r4);
    }

    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> r2, PeerConnection.Observer r3) {
        PeerConnection.RTCConfiguration r0 = new PeerConnection.RTCConfiguration(r2);
        r0.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        return createPeerConnection(r0, r3);
    }

    @Deprecated
    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration r2, MediaConstraints r3, PeerConnection.Observer r4) {
        return createPeerConnectionInternal(r2, r3, r4, null);
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration r2, PeerConnection.Observer r3) {
        return createPeerConnection(r2, null, r3);
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration r3, PeerConnectionDependencies r4) {
        return createPeerConnectionInternal(r3, null, r4.getObserver(), r4.getSSLCertificateVerifier());
    }

    public PeerConnection createPeerConnectionInternal(PeerConnection.RTCConfiguration r11, MediaConstraints r12, PeerConnection.Observer r13, SSLCertificateVerifier r14) {
        checkPeerConnectionFactoryExists();
        long r4 = PeerConnection.createNativePeerConnectionObserver(r13);
        if (r4 != 0) goto L5;
        return null;
    L5:
        long r10 = nativeCreatePeerConnection(this.nativeFactory, r11, r12, r4, r14);
        if (r10 != 0) goto L9;
        return null;
    L9:
        return new PeerConnection(r10);
    }

    public VideoSource createVideoSource(boolean r2) {
        return createVideoSource(r2, true);
    }

    public VideoSource createVideoSource(boolean r4, boolean r5) {
        checkPeerConnectionFactoryExists();
        return new VideoSource(nativeCreateVideoSource(this.nativeFactory, r4, r5));
    }

    public VideoTrack createVideoTrack(String r6, VideoSource r7) {
        checkPeerConnectionFactoryExists();
        return new VideoTrack(nativeCreateVideoTrack(this.nativeFactory, r6, r7.getNativeVideoTrackSource()));
    }

    public void dispose() {
        checkPeerConnectionFactoryExists();
        nativeFreeFactory(this.nativeFactory);
        this.networkThread = null;
        this.workerThread = null;
        this.signalingThread = null;
        this.nativeFactory = 0;
    }

    public long getNativeOwnedFactoryAndThreads() {
        checkPeerConnectionFactoryExists();
        return this.nativeFactory;
    }

    public long getNativePeerConnectionFactory() {
        checkPeerConnectionFactoryExists();
        return nativeGetNativePeerConnectionFactory(this.nativeFactory);
    }

    public RtpCapabilities getRtpReceiverCapabilities(MediaStreamTrack.MediaType r3) {
        checkPeerConnectionFactoryExists();
        return nativeGetRtpReceiverCapabilities(this.nativeFactory, r3);
    }

    public RtpCapabilities getRtpSenderCapabilities(MediaStreamTrack.MediaType r3) {
        checkPeerConnectionFactoryExists();
        return nativeGetRtpSenderCapabilities(this.nativeFactory, r3);
    }

    public void printInternalStackTraces(boolean r2) {
        printStackTrace(this.signalingThread, r2);
        printStackTrace(this.workerThread, r2);
        printStackTrace(this.networkThread, r2);
    }

    public boolean startAecDump(int r3, int r4) {
        checkPeerConnectionFactoryExists();
        return nativeStartAecDump(this.nativeFactory, r3, r4);
    }

    public void stopAecDump() {
        checkPeerConnectionFactoryExists();
        nativeStopAecDump(this.nativeFactory);
    }
}
