package org.telegram.messenger;

/* loaded from: classes5.dex */
public class SecureDocumentKey {
    public byte[] file_iv;
    public byte[] file_key;

    public SecureDocumentKey(byte[] r1, byte[] r2) {
        this.file_key = r1;
        this.file_iv = r2;
    }
}
