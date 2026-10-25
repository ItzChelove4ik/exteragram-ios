package org.vosk;

import com.sun.jna.PointerType;

/* loaded from: classes6.dex */
public class SpeakerModel extends PointerType implements AutoCloseable {
    public SpeakerModel() {
    }

    public SpeakerModel(String r1) {
        super(LibVosk.vosk_spk_model_new(r1));
        if (getPointer() == null) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("Failed to create a speaker model");
        throw null;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        LibVosk.vosk_spk_model_free(getPointer());
    }
}
