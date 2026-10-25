package androidx.credentials;

import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/credentials/CreateCustomCredentialResponse;", "Landroidx/credentials/CreateCredentialResponse;", "type", _UrlKt.FRAGMENT_ENCODE_SET, "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCreateCustomCredentialResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CreateCustomCredentialResponse.kt\nandroidx/credentials/CreateCustomCredentialResponse\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,47:1\n1#2:48\n*E\n"})
/* loaded from: classes3.dex */
public class CreateCustomCredentialResponse extends CreateCredentialResponse {
    public CreateCustomCredentialResponse(String r1, Bundle r2) {
        super(r1, r2);
        if (r1.length() <= 0) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("type should not be empty");
        throw null;
    }
}
