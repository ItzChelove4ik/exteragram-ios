package org.telegram.messenger;

import org.telegram.messenger.FileLoader;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileLoader$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FileLoader.AnonymousClass1 f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.InputFile f$4;
    public final /* synthetic */ TLRPC.InputEncryptedFile f$5;
    public final /* synthetic */ byte[] f$6;
    public final /* synthetic */ byte[] f$7;
    public final /* synthetic */ FileUploadOperation f$8;

    public /* synthetic */ FileLoader$1$$ExternalSyntheticLambda1(FileLoader.AnonymousClass1 r1, boolean r2, String r3, boolean r4, TLRPC.InputFile r5, TLRPC.InputEncryptedFile r6, byte[] r7, byte[] r8, FileUploadOperation r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FileLoader.AnonymousClass1.$r8$lambda$HMnMMxtRvKDn1hEQYH6QEfm66eU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
