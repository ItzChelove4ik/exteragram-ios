package org.vosk;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

/* loaded from: classes6.dex */
public class LibVosk {
    static {
        Native.register(LibVosk.class, "vosk");
    }

    public static void setLogLevel(LogLevel r0) {
        vosk_set_log_level(r0.getValue());
    }

    public static native void vosk_model_free(Pointer r0);

    public static native Pointer vosk_model_new(String r0);

    public static native boolean vosk_recognizer_accept_waveform(Pointer r0, byte[] r1, int r2);

    public static native boolean vosk_recognizer_accept_waveform_f(Pointer r0, float[] r1, int r2);

    public static native boolean vosk_recognizer_accept_waveform_s(Pointer r0, short[] r1, int r2);

    public static native String vosk_recognizer_final_result(Pointer r0);

    public static native void vosk_recognizer_free(Pointer r0);

    public static native Pointer vosk_recognizer_new(Model r0, float r1);

    public static native Pointer vosk_recognizer_new_grm(Pointer r0, float r1, String r2);

    public static native Pointer vosk_recognizer_new_spk(Pointer r0, float r1, Pointer r2);

    public static native String vosk_recognizer_partial_result(Pointer r0);

    public static native void vosk_recognizer_reset(Pointer r0);

    public static native String vosk_recognizer_result(Pointer r0);

    public static native void vosk_recognizer_set_endpointer_delays(Pointer r0, float r1, float r2, float r3);

    public static native void vosk_recognizer_set_endpointer_mode(Pointer r0, int r1);

    public static native void vosk_recognizer_set_grm(Pointer r0, String r1);

    public static native void vosk_recognizer_set_max_alternatives(Pointer r0, int r1);

    public static native void vosk_recognizer_set_partial_words(Pointer r0, boolean r1);

    public static native void vosk_recognizer_set_spk_model(Pointer r0, Pointer r1);

    public static native void vosk_recognizer_set_words(Pointer r0, boolean r1);

    public static native void vosk_set_log_level(int r0);

    public static native void vosk_spk_model_free(Pointer r0);

    public static native Pointer vosk_spk_model_new(String r0);

    public static native void vosk_text_processor_free(Pointer r0);

    public static native String vosk_text_processor_itn(Pointer r0, String r1);

    public static native Pointer vosk_text_processor_new(String r0, String r1);
}
