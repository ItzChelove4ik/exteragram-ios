package androidx.media3.exoplayer.mediacodec;

import android.media.LoudnessCodecController$OnLoudnessCodecUpdateListener;
import android.media.MediaCodec;
import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class LoudnessCodecController {
    private android.media.LoudnessCodecController loudnessCodecController;
    private final HashSet<MediaCodec> mediaCodecs;
    private final LoudnessParameterUpdateListener updateListener;

    /* renamed from: androidx.media3.exoplayer.mediacodec.LoudnessCodecController$1, reason: invalid class name */
    public class AnonymousClass1 implements LoudnessCodecController$OnLoudnessCodecUpdateListener {
        final /* synthetic */ LoudnessCodecController this$0;

        public AnonymousClass1(LoudnessCodecController r1) {
            this.this$0 = r1;
        }

        public Bundle onLoudnessCodecUpdate(MediaCodec r1, Bundle r2) {
            return LoudnessCodecController.access$000(this.this$0).onLoudnessParameterUpdate(r2);
        }
    }

    public interface LoudnessParameterUpdateListener {
        public static final LoudnessParameterUpdateListener DEFAULT = null;

        /* renamed from: $r8$lambda$cLKI5fRo2IRpy7pnaiyHNIEE-yk, reason: not valid java name */
        static /* synthetic */ Bundle m832$r8$lambda$cLKI5fRo2IRpy7pnaiyHNIEEyk(Bundle r0) {
            return r0;
        }

        static {
            DEFAULT = new LoudnessCodecController$LoudnessParameterUpdateListener$$ExternalSyntheticLambda0();
        }

        Bundle onLoudnessParameterUpdate(Bundle r1);
    }

    public LoudnessCodecController() {
        this(LoudnessParameterUpdateListener.DEFAULT);
    }

    public LoudnessCodecController(LoudnessParameterUpdateListener r2) {
        this.mediaCodecs = new HashSet();
        this.updateListener = r2;
    }

    public static /* synthetic */ LoudnessParameterUpdateListener access$000(LoudnessCodecController r0) {
        return r0.updateListener;
    }

    public void addMediaCodec(MediaCodec r2) {
        android.media.LoudnessCodecController r0 = this.loudnessCodecController;
        if (r0 != null) goto L5;
    L7:
        Assertions.checkState(this.mediaCodecs.add(r2));
        return;
    L5:
        if (r0.addMediaCodec(r2) == true) goto L7;
    }

    public void release() {
        this.mediaCodecs.clear();
        android.media.LoudnessCodecController r1 = this.loudnessCodecController;
        if (r1 == null) goto L6;
        r1.close();
        return;
    }

    public void removeMediaCodec(MediaCodec r2) {
        if (this.mediaCodecs.remove(r2) == false) goto L8;
        android.media.LoudnessCodecController r1 = this.loudnessCodecController;
        if (r1 == null) goto L9;
        r1.removeMediaCodec(r2);
        return;
    L9:
        return;
    }

    public void setAudioSessionId(int r3) {
        android.media.LoudnessCodecController r0 = this.loudnessCodecController;
        if (r0 == null) goto L5;
        r0.close();
        this.loudnessCodecController = null;
    L5:
        android.media.LoudnessCodecController r32 = android.media.LoudnessCodecController.create(r3, MoreExecutors.directExecutor(), new AnonymousClass1(this));
        this.loudnessCodecController = r32;
        Iterator<MediaCodec> r2 = this.mediaCodecs.iterator();
    L7:
        if (r2.hasNext() == false) goto L11;
        if (r32.addMediaCodec(r2.next()) == true) goto L7;
        r2.remove();
        goto L7
    }
}
