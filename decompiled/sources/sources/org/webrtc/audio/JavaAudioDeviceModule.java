package org.webrtc.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import java.util.concurrent.ScheduledExecutorService;
import org.webrtc.JniCommon;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class JavaAudioDeviceModule implements AudioDeviceModule {
    private static final String TAG = "JavaAudioDeviceModule";
    private final WebRtcAudioRecord audioInput;
    private final AudioManager audioManager;
    private final WebRtcAudioTrack audioOutput;
    private final Context context;
    private final int inputSampleRate;
    private long nativeAudioDeviceModule;
    private final Object nativeLock;
    private final int outputSampleRate;
    private final boolean useStereoInput;
    private final boolean useStereoOutput;

    public interface AudioRecordErrorCallback {
        void onWebRtcAudioRecordError(String r1);

        void onWebRtcAudioRecordInitError(String r1);

        void onWebRtcAudioRecordStartError(AudioRecordStartErrorCode r1, String r2);
    }

    public enum AudioRecordStartErrorCode extends Enum<AudioRecordStartErrorCode> {
        private static final /* synthetic */ AudioRecordStartErrorCode[] $VALUES = null;
        public static final AudioRecordStartErrorCode AUDIO_RECORD_START_EXCEPTION = null;
        public static final AudioRecordStartErrorCode AUDIO_RECORD_START_STATE_MISMATCH = null;

        private static /* synthetic */ AudioRecordStartErrorCode[] $values() {
            return new AudioRecordStartErrorCode[]{AUDIO_RECORD_START_EXCEPTION, AUDIO_RECORD_START_STATE_MISMATCH};
        }

        static {
            AUDIO_RECORD_START_EXCEPTION = new AudioRecordStartErrorCode("AUDIO_RECORD_START_EXCEPTION", 0);
            AUDIO_RECORD_START_STATE_MISMATCH = new AudioRecordStartErrorCode("AUDIO_RECORD_START_STATE_MISMATCH", 1);
            $VALUES = $values();
        }

        AudioRecordStartErrorCode(String r1, int r2) {
        }

        public static AudioRecordStartErrorCode valueOf(String r1) {
            return (AudioRecordStartErrorCode) Enum.valueOf(AudioRecordStartErrorCode.class, r1);
        }

        public static AudioRecordStartErrorCode[] values() {
            return (AudioRecordStartErrorCode[]) $VALUES.clone();
        }
    }

    public interface AudioRecordStateCallback {
        void onWebRtcAudioRecordStart();

        void onWebRtcAudioRecordStop();
    }

    public static class AudioSamples {
        private final int audioFormat;
        private final int channelCount;
        private final byte[] data;
        private final int sampleRate;

        public AudioSamples(int r1, int r2, int r3, byte[] r4) {
            this.audioFormat = r1;
            this.channelCount = r2;
            this.sampleRate = r3;
            this.data = r4;
        }

        public int getAudioFormat() {
            return this.audioFormat;
        }

        public int getChannelCount() {
            return this.channelCount;
        }

        public byte[] getData() {
            return this.data;
        }

        public int getSampleRate() {
            return this.sampleRate;
        }
    }

    public interface AudioTrackErrorCallback {
        void onWebRtcAudioTrackError(String r1);

        void onWebRtcAudioTrackInitError(String r1);

        void onWebRtcAudioTrackStartError(AudioTrackStartErrorCode r1, String r2);
    }

    public enum AudioTrackStartErrorCode extends Enum<AudioTrackStartErrorCode> {
        private static final /* synthetic */ AudioTrackStartErrorCode[] $VALUES = null;
        public static final AudioTrackStartErrorCode AUDIO_TRACK_START_EXCEPTION = null;
        public static final AudioTrackStartErrorCode AUDIO_TRACK_START_STATE_MISMATCH = null;

        private static /* synthetic */ AudioTrackStartErrorCode[] $values() {
            return new AudioTrackStartErrorCode[]{AUDIO_TRACK_START_EXCEPTION, AUDIO_TRACK_START_STATE_MISMATCH};
        }

        static {
            AUDIO_TRACK_START_EXCEPTION = new AudioTrackStartErrorCode("AUDIO_TRACK_START_EXCEPTION", 0);
            AUDIO_TRACK_START_STATE_MISMATCH = new AudioTrackStartErrorCode("AUDIO_TRACK_START_STATE_MISMATCH", 1);
            $VALUES = $values();
        }

        AudioTrackStartErrorCode(String r1, int r2) {
        }

        public static AudioTrackStartErrorCode valueOf(String r1) {
            return (AudioTrackStartErrorCode) Enum.valueOf(AudioTrackStartErrorCode.class, r1);
        }

        public static AudioTrackStartErrorCode[] values() {
            return (AudioTrackStartErrorCode[]) $VALUES.clone();
        }
    }

    public interface AudioTrackStateCallback {
        void onWebRtcAudioTrackStart();

        void onWebRtcAudioTrackStop();
    }

    public static class Builder {
        private AudioAttributes audioAttributes;
        private int audioFormat;
        private final AudioManager audioManager;
        private AudioRecordErrorCallback audioRecordErrorCallback;
        private AudioRecordStateCallback audioRecordStateCallback;
        private int audioSource;
        private AudioTrackErrorCallback audioTrackErrorCallback;
        private AudioTrackStateCallback audioTrackStateCallback;
        private final Context context;
        private boolean enableVolumeLogger;
        private int inputSampleRate;
        private int outputSampleRate;
        private SamplesReadyCallback samplesReadyCallback;
        private ScheduledExecutorService scheduler;
        private boolean useHardwareAcousticEchoCanceler;
        private boolean useHardwareNoiseSuppressor;
        private boolean useLowLatency;
        private boolean useStereoInput;
        private boolean useStereoOutput;

        private Builder(Context r2) {
            this.audioSource = 7;
            this.audioFormat = 2;
            this.useHardwareAcousticEchoCanceler = JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported();
            this.useHardwareNoiseSuppressor = JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported();
            this.context = r2;
            AudioManager r22 = (AudioManager) r2.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            this.audioManager = r22;
            this.inputSampleRate = WebRtcAudioManager.getSampleRate(r22);
            this.outputSampleRate = WebRtcAudioManager.getSampleRate(r22);
            this.useLowLatency = false;
            this.enableVolumeLogger = true;
        }

        public /* synthetic */ Builder(Context r1, JavaAudioDeviceModuleIA r2) {
            this(r1);
        }

        public JavaAudioDeviceModule createAudioDeviceModule() {
            Logging.d(JavaAudioDeviceModule.TAG, "createAudioDeviceModule");
            if (this.useHardwareNoiseSuppressor == false) goto L6;
            Logging.d(JavaAudioDeviceModule.TAG, "HW NS will be used.");
        L10:
            if (this.useHardwareAcousticEchoCanceler == false) goto L13;
            Logging.d(JavaAudioDeviceModule.TAG, "HW AEC will be used.");
        L17:
            if (this.useLowLatency == true) goto L19;
        L21:
            ScheduledExecutorService r0 = this.scheduler;
            if (r0 != null) goto L24;
            r0 = WebRtcAudioRecord.newDefaultScheduler();
        L24:
            Context r2 = this.context;
            WebRtcAudioRecord r1 = new WebRtcAudioRecord(r2, r0, this.audioManager, this.audioSource, this.audioFormat, this.audioRecordErrorCallback, this.audioRecordStateCallback, this.samplesReadyCallback, this.useHardwareAcousticEchoCanceler, this.useHardwareNoiseSuppressor);
            WebRtcAudioTrack r22 = new WebRtcAudioTrack(this.context, this.audioManager, this.audioAttributes, this.audioTrackErrorCallback, this.audioTrackStateCallback, this.useLowLatency, this.enableVolumeLogger);
            return new JavaAudioDeviceModule(this.context, this.audioManager, r1, r22, this.inputSampleRate, this.outputSampleRate, this.useStereoInput, this.useStereoOutput, null);
        L19:
            if (Build.VERSION.SDK_INT < 26) goto L21;
            Logging.d(JavaAudioDeviceModule.TAG, "Low latency mode will be used.");
            goto L21
        L13:
            if (JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported() == false) goto L15;
            Logging.d(JavaAudioDeviceModule.TAG, "Overriding default behavior; now using WebRTC AEC!");
        L15:
            Logging.d(JavaAudioDeviceModule.TAG, "HW AEC will not be used.");
            goto L17
        L6:
            if (JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported() == false) goto L8;
            Logging.d(JavaAudioDeviceModule.TAG, "Overriding default behavior; now using WebRTC NS!");
        L8:
            Logging.d(JavaAudioDeviceModule.TAG, "HW NS will not be used.");
            goto L10
        }

        public Builder setAudioAttributes(AudioAttributes r1) {
            this.audioAttributes = r1;
            return this;
        }

        public Builder setAudioFormat(int r1) {
            this.audioFormat = r1;
            return this;
        }

        public Builder setAudioRecordErrorCallback(AudioRecordErrorCallback r1) {
            this.audioRecordErrorCallback = r1;
            return this;
        }

        public Builder setAudioRecordStateCallback(AudioRecordStateCallback r1) {
            this.audioRecordStateCallback = r1;
            return this;
        }

        public Builder setAudioSource(int r1) {
            this.audioSource = r1;
            return this;
        }

        public Builder setAudioTrackErrorCallback(AudioTrackErrorCallback r1) {
            this.audioTrackErrorCallback = r1;
            return this;
        }

        public Builder setAudioTrackStateCallback(AudioTrackStateCallback r1) {
            this.audioTrackStateCallback = r1;
            return this;
        }

        public Builder setEnableVolumeLogger(boolean r1) {
            this.enableVolumeLogger = r1;
            return this;
        }

        public Builder setInputSampleRate(int r3) {
            Logging.d(JavaAudioDeviceModule.TAG, "Input sample rate overridden to: " + r3);
            this.inputSampleRate = r3;
            return this;
        }

        public Builder setOutputSampleRate(int r3) {
            Logging.d(JavaAudioDeviceModule.TAG, "Output sample rate overridden to: " + r3);
            this.outputSampleRate = r3;
            return this;
        }

        public Builder setSampleRate(int r3) {
            Logging.d(JavaAudioDeviceModule.TAG, "Input/Output sample rate overridden to: " + r3);
            this.inputSampleRate = r3;
            this.outputSampleRate = r3;
            return this;
        }

        public Builder setSamplesReadyCallback(SamplesReadyCallback r1) {
            this.samplesReadyCallback = r1;
            return this;
        }

        public Builder setScheduler(ScheduledExecutorService r1) {
            this.scheduler = r1;
            return this;
        }

        public Builder setUseHardwareAcousticEchoCanceler(boolean r2) {
            if (r2 == true) goto L4;
        L6:
            this.useHardwareAcousticEchoCanceler = r2;
            return this;
        L4:
            if (JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported() == true) goto L6;
            Logging.e(JavaAudioDeviceModule.TAG, "HW AEC not supported");
            r2 = false;
            goto L6
        }

        public Builder setUseHardwareNoiseSuppressor(boolean r2) {
            if (r2 == true) goto L4;
        L6:
            this.useHardwareNoiseSuppressor = r2;
            return this;
        L4:
            if (JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported() == true) goto L6;
            Logging.e(JavaAudioDeviceModule.TAG, "HW NS not supported");
            r2 = false;
            goto L6
        }

        public Builder setUseLowLatency(boolean r1) {
            this.useLowLatency = r1;
            return this;
        }

        public Builder setUseStereoInput(boolean r1) {
            this.useStereoInput = r1;
            return this;
        }

        public Builder setUseStereoOutput(boolean r1) {
            this.useStereoOutput = r1;
            return this;
        }
    }

    public interface SamplesReadyCallback {
        void onWebRtcAudioRecordSamplesReady(AudioSamples r1);
    }

    private JavaAudioDeviceModule(Context r2, AudioManager r3, WebRtcAudioRecord r4, WebRtcAudioTrack r5, int r6, int r7, boolean r8, boolean r9) {
        this.nativeLock = new Object();
        this.context = r2;
        this.audioManager = r3;
        this.audioInput = r4;
        this.audioOutput = r5;
        this.inputSampleRate = r6;
        this.outputSampleRate = r7;
        this.useStereoInput = r8;
        this.useStereoOutput = r9;
    }

    public /* synthetic */ JavaAudioDeviceModule(Context r1, AudioManager r2, WebRtcAudioRecord r3, WebRtcAudioTrack r4, int r5, int r6, boolean r7, boolean r8, JavaAudioDeviceModuleIA r9) {
        this(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static Builder builder(Context r2) {
        return new Builder(r2, null);
    }

    public static boolean isBuiltInAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.isAcousticEchoCancelerSupported();
    }

    public static boolean isBuiltInNoiseSuppressorSupported() {
        return WebRtcAudioEffects.isNoiseSuppressorSupported();
    }

    private static native long nativeCreateAudioDeviceModule(Context r0, AudioManager r1, WebRtcAudioRecord r2, WebRtcAudioTrack r3, int r4, int r5, boolean r6, boolean r7);

    @Override // org.webrtc.audio.AudioDeviceModule
    public long getNativeAudioDeviceModulePointer() {
        Object r1 = this.nativeLock;
        monitor-enter(r1);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.nativeAudioDeviceModule != 0) goto L9;
        this.nativeAudioDeviceModule = nativeCreateAudioDeviceModule(this.context, this.audioManager, this.audioInput, this.audioOutput, this.inputSampleRate, this.outputSampleRate, this.useStereoInput, this.useStereoOutput);     // Catch: Throwable -> L7
    L9:
        long r2 = this.nativeAudioDeviceModule;     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return r2;
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void release() {
        Object r0 = this.nativeLock;
        monitor-enter(r0);
        long r1 = this.nativeAudioDeviceModule;     // Catch: Throwable -> L7
        if (r1 == 0) goto L9;
        JniCommon.nativeReleaseRef(r1);     // Catch: Throwable -> L7
        this.nativeAudioDeviceModule = 0;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setMicrophoneMute(boolean r3) {
        Logging.d(TAG, "setMicrophoneMute: " + r3);
        this.audioInput.setMicrophoneMute(r3);
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public boolean setNoiseSuppressorEnabled(boolean r3) {
        Logging.d(TAG, "setNoiseSuppressorEnabled: " + r3);
        return this.audioInput.setNoiseSuppressorEnabled(r3);
    }

    public void setPreferredInputDevice(AudioDeviceInfo r3) {
        Logging.d(TAG, "setPreferredInputDevice: " + r3);
        this.audioInput.setPreferredDevice(r3);
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setSpeakerMute(boolean r3) {
        Logging.d(TAG, "setSpeakerMute: " + r3);
        this.audioOutput.setSpeakerMute(r3);
    }
}
