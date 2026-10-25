package org.telegram.messenger.secretmedia;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import kotlin.UByte;
import org.telegram.messenger.SecureDocumentKey;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public class EncryptedFileInputStream extends FileInputStream implements InputStreamRetargetInterface {
    private static final int MODE_CBC = 1;
    private static final int MODE_CTR = 0;
    private int currentMode;
    private int fileOffset;
    private byte[] iv;
    private byte[] key;

    public EncryptedFileInputStream(File r5, File r6) {
        super(r5);
        this.key = new byte[32];
        this.iv = new byte[16];
        this.currentMode = 0;
        RandomAccessFile r2 = new RandomAccessFile(r6, "r");
        r2.read(this.key, 0, 32);
        r2.read(this.iv, 0, 16);
        r2.close();
    }

    public EncryptedFileInputStream(File r4, SecureDocumentKey r5) {
        super(r4);
        byte[] r42 = new byte[32];
        this.key = r42;
        this.iv = new byte[16];
        this.currentMode = 1;
        System.arraycopy(r5.file_key, 0, r42, 0, r42.length);
        byte[] r43 = r5.file_iv;
        byte[] r3 = this.iv;
        System.arraycopy(r43, 0, r3, 0, r3.length);
    }

    public static void decryptBytesWithKeyFile(byte[] r8, int r9, int r10, File r11) {
        byte[] r2 = new byte[32];
        byte[] r3 = new byte[16];
        RandomAccessFile r4 = new RandomAccessFile(r11, "r");
        r4.read(r2, 0, 32);
        r4.read(r3, 0, 16);
        r4.close();
        Utilities.aesCtrDecryptionByteArray(r8, r2, r3, r9, r10, 0);
    }

    public static void decryptBytesWithKeyFile(byte[] r7, int r8, int r9, SecureDocumentKey r10) {
        Utilities.aesCbcEncryptionByteArraySafe(r7, r10.file_key, r10.file_iv, r8, r9, 0, 0);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] r20, int r21, int r22) {
        if (this.currentMode == 1) goto L5;
    L7:
        int r9 = super.read(r20, r21, r22);
        int r1 = this.currentMode;
        if (r1 != 1) goto L10;
        int r5 = r22;
        Utilities.aesCbcEncryptionByteArraySafe(r20, this.key, this.iv, r21, r5, this.fileOffset, 0);
    L13:
        this.fileOffset += r5;
        return r9;
    L10:
        r5 = r22;
        if (r1 != 0) goto L13;
        Utilities.aesCtrDecryptionByteArray(r20, this.key, this.iv, r21, r5, this.fileOffset);
        goto L13
    L5:
        if (this.fileOffset != 0) goto L7;
        super.read(new byte[32], 0, 32);
        Utilities.aesCbcEncryptionByteArraySafe(r20, this.key, this.iv, r21, r22, this.fileOffset, 0);
        this.fileOffset += 32;
        skip((r10[0] & UByte.MAX_VALUE) - 32);
        goto L7
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public long skip(long r3) {
        this.fileOffset = (int) (this.fileOffset + r3);
        return super.skip(r3);
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
