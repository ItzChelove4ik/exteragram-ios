package androidx.car.app.model.signin;

import android.net.Uri;
import androidx.car.app.model.signin.SignInTemplate;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class QRCodeSignInMethod implements SignInTemplate.SignInMethod {
    private final Uri mUri;

    private QRCodeSignInMethod() {
        this.mUri = null;
    }

    public QRCodeSignInMethod(Uri r1) {
        Objects.requireNonNull(r1);
        this.mUri = r1;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof QRCodeSignInMethod) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mUri, ((QRCodeSignInMethod) r2).mUri);
    }

    public Uri getUri() {
        Uri r0 = this.mUri;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mUri});
    }
}
