package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class SecureDocument extends TLObject {
    public byte[] fileHash;
    public byte[] fileSecret;
    public TLRPC.TL_inputFile inputFile;
    public String path;
    public SecureDocumentKey secureDocumentKey;
    public TLRPC.TL_secureFile secureFile;
    public int type;

    public SecureDocument(SecureDocumentKey r1, TLRPC.TL_secureFile r2, String r3, byte[] r4, byte[] r5) {
        this.secureDocumentKey = r1;
        this.secureFile = r2;
        this.path = r3;
        this.fileHash = r4;
        this.fileSecret = r5;
    }
}
