package j$.nio.channels;

import j$.nio.file.attribute.FileAttribute;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.Channel;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class a implements Channel {

    /* renamed from: b, reason: collision with root package name */
    public static final FileAttribute[] f176b = null;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AsynchronousFileChannel f177a;

    static {
        f176b = new FileAttribute[0];
    }

    public a(AsynchronousFileChannel r1) {
        this.f177a = r1;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.f177a.close();
    }

    public final /* synthetic */ boolean equals(Object r2) {
        AsynchronousFileChannel r1 = this.f177a;
        if ((r2 instanceof a) == false) goto L6;
        r2 = ((a) r2).f177a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f177a.hashCode();
    }

    @Override // java.nio.channels.Channel
    public final /* synthetic */ boolean isOpen() {
        return this.f177a.isOpen();
    }
}
