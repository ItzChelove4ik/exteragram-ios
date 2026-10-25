package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public class UnknownDescriptor extends BaseDescriptor {
    private static Logger log;
    private ByteBuffer data;

    static {
        log = Logger.getLogger(UnknownDescriptor.class.getName());
    }

    public UnknownDescriptor() {
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer r2) {
        this.data = (ByteBuffer) r2.slice().limit(getSizeOfInstance());
    }

    public String toString() {
        return "UnknownDescriptor{tag=" + this.tag + ", sizeOfInstance=" + this.sizeOfInstance + ", data=" + this.data + '}';
    }
}
