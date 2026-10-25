package org.vosk;

import com.sun.jna.PointerType;

/* loaded from: classes6.dex */
public class Recognizer extends PointerType implements AutoCloseable {
    public Recognizer(Model r1, float r2) {
        super(LibVosk.vosk_recognizer_new(r1, r2));
        if (getPointer() == null) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("Failed to create a recognizer");
        throw null;
    }

    public Recognizer(Model r1, float r2, String r3) {
        super(LibVosk.vosk_recognizer_new_grm(r1.getPointer(), r2, r3));
        if (getPointer() == null) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("Failed to create a recognizer");
        throw null;
    }

    public Recognizer(Model r1, float r2, SpeakerModel r3) {
        super(LibVosk.vosk_recognizer_new_spk(r1.getPointer(), r2, r3.getPointer()));
        if (getPointer() == null) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("Failed to create a recognizer");
        throw null;
    }

    public boolean acceptWaveForm(byte[] r1, int r2) {
        return LibVosk.vosk_recognizer_accept_waveform(getPointer(), r1, r2);
    }

    public boolean acceptWaveForm(float[] r1, int r2) {
        return LibVosk.vosk_recognizer_accept_waveform_f(getPointer(), r1, r2);
    }

    public boolean acceptWaveForm(short[] r1, int r2) {
        return LibVosk.vosk_recognizer_accept_waveform_s(getPointer(), r1, r2);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        LibVosk.vosk_recognizer_free(getPointer());
    }

    public String getFinalResult() {
        return LibVosk.vosk_recognizer_final_result(getPointer());
    }

    public String getPartialResult() {
        return LibVosk.vosk_recognizer_partial_result(getPointer());
    }

    public String getResult() {
        return LibVosk.vosk_recognizer_result(getPointer());
    }

    public void reset() {
        LibVosk.vosk_recognizer_reset(getPointer());
    }

    public void setEndpointerDelays(float r1, float r2, float r3) {
        LibVosk.vosk_recognizer_set_endpointer_delays(getPointer(), r1, r2, r3);
    }

    public void setEndpointerMode(int r1) {
        LibVosk.vosk_recognizer_set_endpointer_mode(getPointer(), r1);
    }

    public void setGrammar(String r1) {
        LibVosk.vosk_recognizer_set_grm(getPointer(), r1);
    }

    public void setMaxAlternatives(int r1) {
        LibVosk.vosk_recognizer_set_max_alternatives(getPointer(), r1);
    }

    public void setPartialWords(boolean r1) {
        LibVosk.vosk_recognizer_set_partial_words(getPointer(), r1);
    }

    public void setSpeakerModel(SpeakerModel r1) {
        LibVosk.vosk_recognizer_set_spk_model(getPointer(), r1.getPointer());
    }

    public void setWords(boolean r1) {
        LibVosk.vosk_recognizer_set_words(getPointer(), r1);
    }
}
