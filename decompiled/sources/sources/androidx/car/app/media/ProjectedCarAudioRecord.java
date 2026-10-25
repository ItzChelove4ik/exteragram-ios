package androidx.car.app.media;

import android.util.Log;
import androidx.car.app.CarContext;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class ProjectedCarAudioRecord extends CarAudioRecord {
    private InputStream mInputStream;

    public ProjectedCarAudioRecord(CarContext r1) {
        super(r1);
    }

    @Override // androidx.car.app.media.CarAudioRecord
    public int readInternal(byte[] r2, int r3, int r4) {
        InputStream r0 = this.mInputStream;
        if (r0 == null) goto L11;
        return r0.read(r2, r3, r4);
    L6:
        this.stopRecording();
        return -1;
    L11:
        return -1;
    }

    @Override // androidx.car.app.media.CarAudioRecord
    public void startRecordingInternal(OpenMicrophoneResponse r1) {
        this.mInputStream = r1.getCarMicrophoneInputStream();
    }

    @Override // androidx.car.app.media.CarAudioRecord
    public void stopRecordingInternal() {
        InputStream r0 = this.mInputStream;     // Catch: IOException -> L6
        if (r0 == null) goto L11;
        r0.close();     // Catch: IOException -> L6
        this.mInputStream = null;     // Catch: IOException -> L6
        return;
    L11:
        return;
    L6:
        e = move-exception;
        Log.e("CarApp", "Exception closing microphone pipe", e);
    }
}
