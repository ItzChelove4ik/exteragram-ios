package org.vosk;

import com.sun.jna.PointerType;

/* loaded from: classes6.dex */
public class Model extends PointerType implements AutoCloseable {
    public Model() {
    }

    public Model(String r1) {
        super(LibVosk.vosk_model_new(r1));
        if (getPointer() == null) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("Failed to create a model");
        throw null;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        LibVosk.vosk_model_free(getPointer());
    }
}
