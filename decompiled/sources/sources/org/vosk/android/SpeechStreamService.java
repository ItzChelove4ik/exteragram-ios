package org.vosk.android;

import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.io.InputStream;
import org.vosk.Recognizer;

/* loaded from: classes6.dex */
public class SpeechStreamService {
    private final int bufferSize;
    private final InputStream inputStream;
    private final Handler mainHandler;
    private final Recognizer recognizer;
    private Thread recognizerThread;
    private final int sampleRate;

    public final class RecognizerThread extends Thread {
        RecognitionListener listener;
        private int remainingSamples;
        final /* synthetic */ SpeechStreamService this$0;
        private final int timeoutSamples;

        public static /* synthetic */ void $r8$lambda$Crh4lsjDk2vAQyv9zkrYKVMTTSA(RecognizerThread r0, String r1) {
            r0.listener.onFinalResult(r1);
        }

        public static /* synthetic */ void $r8$lambda$DmGrQiGReCc1MDQHuzb2TktCZe4(RecognizerThread r0) {
            r0.listener.onTimeout();
        }

        /* renamed from: $r8$lambda$Iif20kFMwELe1_ktQVn-KWMq1DU, reason: not valid java name */
        public static /* synthetic */ void m22265$r8$lambda$Iif20kFMwELe1_ktQVnKWMq1DU(RecognizerThread r0, String r1) {
            r0.listener.onResult(r1);
        }

        public static /* synthetic */ void $r8$lambda$QXk3dc4A3NIJpKRkvy8Iaz_fFPw(RecognizerThread r0, IOException r1) {
            r0.listener.onError(r1);
        }

        public static /* synthetic */ void $r8$lambda$mVlV7glbHFSql2gYB8WQfijSkiA(RecognizerThread r0, String r1) {
            r0.listener.onPartialResult(r1);
        }

        public RecognizerThread(SpeechStreamService r2, RecognitionListener r3) {
            this(r2, r3, -1);
        }

        public RecognizerThread(SpeechStreamService r1, RecognitionListener r2, int r3) {
            this.this$0 = r1;
            this.listener = r2;
            if (r3 == (-1)) goto L5;
            this.timeoutSamples = (r3 * SpeechStreamService.access$000(r1)) / 1000;
        L6:
            this.remainingSamples = this.timeoutSamples;
            return;
        L5:
            this.timeoutSamples = -1;
            goto L6
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int r0 = SpeechStreamService.access$100(this.this$0);
            byte[] r1 = new byte[r0];
        L4:
            if (Thread.interrupted() == true) goto L25;
            if (this.timeoutSamples != (-1)) goto L8;
        L31:
            int r2 = SpeechStreamService.access$200(this.this$0).read(r1, 0, r0);     // Catch: IOException -> L16
            if (r2 < 0) goto L25;
            boolean r4 = SpeechStreamService.access$300(this.this$0).acceptWaveForm(r1, r2);     // Catch: IOException -> L16
            SpeechStreamService r5 = this.this$0;
            if (r4 == false) goto L18;
            SpeechStreamService.access$400(this.this$0).post(new SpeechStreamService$RecognizerThread$$ExternalSyntheticLambda0(this, SpeechStreamService.access$300(r5).getResult()));     // Catch: IOException -> L16
        L20:
            if (this.timeoutSamples == (-1)) goto L4;
            this.remainingSamples -= r2;
            goto L4
        L18:
            SpeechStreamService.access$400(this.this$0).post(new SpeechStreamService$RecognizerThread$$ExternalSyntheticLambda1(this, SpeechStreamService.access$300(r5).getPartialResult()));     // Catch: IOException -> L16
        L16:
            e = move-exception;
            SpeechStreamService.access$400(this.this$0).post(new SpeechStreamService$RecognizerThread$$ExternalSyntheticLambda2(this, e));
            goto L4
        L8:
            if (this.remainingSamples > 0) goto L31;
        L25:
            if (this.timeoutSamples != (-1)) goto L27;
        L29:
            SpeechStreamService.access$400(this.this$0).post(new SpeechStreamService$RecognizerThread$$ExternalSyntheticLambda4(this, SpeechStreamService.access$300(this.this$0).getFinalResult()));
            return;
        L27:
            if (this.remainingSamples > 0) goto L29;
            SpeechStreamService.access$400(this.this$0).post(new SpeechStreamService$RecognizerThread$$ExternalSyntheticLambda3(this));
        }
    }

    public SpeechStreamService(Recognizer r3, InputStream r4, float r5) {
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.recognizer = r3;
        int r32 = (int) r5;
        this.sampleRate = r32;
        this.inputStream = r4;
        this.bufferSize = Math.round((r32 * 0.2f) * 2.0f);
    }

    public static /* synthetic */ int access$000(SpeechStreamService r0) {
        return r0.sampleRate;
    }

    public static /* synthetic */ int access$100(SpeechStreamService r0) {
        return r0.bufferSize;
    }

    public static /* synthetic */ InputStream access$200(SpeechStreamService r0) {
        return r0.inputStream;
    }

    public static /* synthetic */ Recognizer access$300(SpeechStreamService r0) {
        return r0.recognizer;
    }

    public static /* synthetic */ Handler access$400(SpeechStreamService r0) {
        return r0.mainHandler;
    }

    public boolean start(RecognitionListener r2) {
        if (this.recognizerThread == null) goto L6;
        return false;
    L6:
        RecognizerThread r0 = new RecognizerThread(this, r2);
        this.recognizerThread = r0;
        r0.start();
        return true;
    }
}
