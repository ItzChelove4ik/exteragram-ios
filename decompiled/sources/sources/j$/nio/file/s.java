package j$.nio.file;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryStream;
import java.util.Set;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class s implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f259a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f260b;

    public /* synthetic */ s(k r2) {
        this.f259a = 1;
        this.f260b = r2;
    }

    public /* synthetic */ s(DirectoryStream r2) {
        this.f259a = 0;
        this.f260b = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch(this.f259a) {
            case 0: goto L6;
            default: goto L4;
        };
    L4:
        ((k) this.f260b).close();
        return;
    L6:
        DirectoryStream r1 = (DirectoryStream) this.f260b;
        Set r0 = Files.f181a;
        r1.close();     // Catch: IOException -> L9
        return;
    L9:
        e = move-exception;
        throw new UncheckedIOException(e);
    }
}
