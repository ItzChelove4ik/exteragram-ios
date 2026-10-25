package androidx.car.app.media;

import android.util.Log;
import androidx.car.app.AppManager;
import androidx.car.app.CarContext;
import androidx.car.app.media.OpenMicrophoneRequest;
import androidx.car.app.media.OpenMicrophoneResponse;
import androidx.car.app.utils.CommonUtils;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class CarAudioRecord {
    public static final int AUDIO_CONTENT_BUFFER_SIZE = 512;
    public static final String AUDIO_CONTENT_MIME = "audio/l16";
    public static final int AUDIO_CONTENT_SAMPLING_RATE = 16000;
    private static final int RECORDSTATE_RECORDING = 1;
    private static final int RECORDSTATE_REMOTE_CLOSED = 2;
    private static final int RECORDSTATE_STOPPED = 0;
    private final CarContext mCarContext;
    private OpenMicrophoneResponse mOpenMicrophoneResponse;
    private int mRecordingState;
    private final Object mRecordingStateLock;

    public static /* synthetic */ void $r8$lambda$eiaUsw7_X25Hzek8e7b5aQdG054() {
    }

    public static /* synthetic */ void $r8$lambda$gO6P46xJLpbKFBEuOOe19YVnJeI(CarAudioRecord r2) {
        Object r0 = r2.mRecordingStateLock;
        monitor-enter(r0);
        r2.mRecordingState = 2;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public CarAudioRecord(CarContext r2) {
        this.mRecordingState = 0;
        this.mRecordingStateLock = new Object();
        this.mCarContext = r2;
    }

    public static CarAudioRecord create(CarContext r1) {
        Objects.requireNonNull(r1);
        if (CommonUtils.isAutomotiveOS(r1) == false) goto L5;
        String r0 = "androidx.car.app.media.AutomotiveCarAudioRecord";
    L7:
        return createCarAudioRecord(r1, r0);
    L5:
        r0 = "androidx.car.app.media.ProjectedCarAudioRecord";
        goto L7
    }

    private static CarAudioRecord createCarAudioRecord(CarContext r1, String r2) {
        return (CarAudioRecord) Class.forName(r2).getConstructor(new Class[]{CarContext.class}).newInstance(new Object[]{r1});
    L4:
        Segment$$ExternalSyntheticBUOutline1.m("CarAudioRecord not configured. Did you forget to add a dependency on app-automotive or app-projected artifacts?");
        return null;
    }

    public int read(byte[] r4, int r5, int r6) {
        Object r0 = this.mRecordingStateLock;
        monitor-enter(r0);
        int r1 = this.mRecordingState;     // Catch: Throwable -> L11
        if (r1 == 0) goto L17;
        if (r1 == 2) goto L14;
        monitor-exit(r0);     // Catch: Throwable -> L11
        return readInternal(r4, r5, r6);
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return -1;
    L17:
        throw new IllegalStateException("Called read before calling startRecording or after calling stopRecording");     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public abstract int readInternal(byte[] r1, int r2, int r3);

    public void startRecording() {
        Object r0 = this.mRecordingStateLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mRecordingState != 0) goto L15;
        OpenMicrophoneResponse r1 = ((AppManager) this.mCarContext.getCarService(AppManager.class)).openMicrophone(new OpenMicrophoneRequest.Builder(new CarAudioRecord$$ExternalSyntheticLambda0(this)).build());     // Catch: Throwable -> L9
        this.mOpenMicrophoneResponse = r1;     // Catch: Throwable -> L9
        if (r1 != null) goto L11;
        Log.e("CarApp", "Did not get microphone input from host");     // Catch: Throwable -> L9
        this.mOpenMicrophoneResponse = new OpenMicrophoneResponse.Builder(new CarAudioRecord$$ExternalSyntheticLambda1()).build();     // Catch: Throwable -> L9
    L11:
        startRecordingInternal(this.mOpenMicrophoneResponse);     // Catch: Throwable -> L9
        this.mRecordingState = 1;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L15:
        throw new IllegalStateException("Cannot start recording if it has started and not been stopped");     // Catch: Throwable -> L9
    }

    public abstract void startRecordingInternal(OpenMicrophoneResponse r1);

    public void stopRecording() {
        Object r0 = this.mRecordingStateLock;
        monitor-enter(r0);
        OpenMicrophoneResponse r1 = this.mOpenMicrophoneResponse;     // Catch: Throwable -> L9
        if (r1 != null) goto L7;
    L12:
        stopRecordingInternal();     // Catch: Throwable -> L9
        this.mRecordingState = 0;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L7:
        if (this.mRecordingState == 2) goto L11;
        r1.getCarAudioCallback().onStopRecording();     // Catch: Throwable -> L9
    L11:
        this.mOpenMicrophoneResponse = null;     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public abstract void stopRecordingInternal();
}
