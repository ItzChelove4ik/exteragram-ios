package java.nio.file;

import java.io.IOException;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class FileSystemException extends IOException {

    /* renamed from: a, reason: collision with root package name */
    private final String f495a;

    /* renamed from: b, reason: collision with root package name */
    private final String f496b;

    public FileSystemException(String r2) {
        super(null);
        this.f495a = r2;
        this.f496b = null;
    }

    public FileSystemException(String r1, String r2, String r3) {
        super(r3);
        this.f495a = r1;
        this.f496b = r2;
    }

    public String getFile() {
        return this.f495a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.f495a == null) goto L5;
    L8:
        StringBuilder r0 = new StringBuilder();
        if (this.f495a == null) goto L12;
        r0.append(this.f495a);
    L12:
        if (this.f496b == null) goto L15;
        r0.append(" -> ");
        r0.append(this.f496b);
    L15:
        if (getReason() == null) goto L18;
        r0.append(": ");
        r0.append(getReason());
    L18:
        return r0.toString();
    L5:
        if (this.f496b != null) goto L8;
        return getReason();
    }

    public String getOtherFile() {
        return this.f496b;
    }

    public String getReason() {
        return super.getMessage();
    }
}
