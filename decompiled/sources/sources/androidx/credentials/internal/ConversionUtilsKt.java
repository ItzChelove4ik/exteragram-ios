package androidx.credentials.internal;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.R$drawable;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialCustomException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialInterruptedException;
import androidx.credentials.exceptions.CreateCredentialNoCreateOptionException;
import androidx.credentials.exceptions.CreateCredentialProviderConfigurationException;
import androidx.credentials.exceptions.CreateCredentialUnknownException;
import androidx.credentials.exceptions.CreateCredentialUnsupportedException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialCustomException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.GetCredentialProviderConfigurationException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.GetCredentialUnsupportedException;
import androidx.credentials.exceptions.NoCredentialException;
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialException;
import androidx.credentials.exceptions.publickeycredential.GetPublicKeyCredentialException;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0004\b\f\u0010\r\u001a#\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/credentials/CreateCredentialRequest;", PluginsConstants.REQUEST, "Landroid/content/Context;", "context", "Landroid/os/Bundle;", "getFinalCreateCredentialData", "(Landroidx/credentials/CreateCredentialRequest;Landroid/content/Context;)Landroid/os/Bundle;", _UrlKt.FRAGMENT_ENCODE_SET, "errorType", _UrlKt.FRAGMENT_ENCODE_SET, "errorMsg", "Landroidx/credentials/exceptions/GetCredentialException;", "toJetpackGetException", "(Ljava/lang/String;Ljava/lang/CharSequence;)Landroidx/credentials/exceptions/GetCredentialException;", "Landroidx/credentials/exceptions/CreateCredentialException;", "toJetpackCreateException", "(Ljava/lang/String;Ljava/lang/CharSequence;)Landroidx/credentials/exceptions/CreateCredentialException;", "credentials"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ConversionUtilsKt {
    public static final Bundle getFinalCreateCredentialData(CreateCredentialRequest r2, Context r3) {
        Bundle r0 = r2.getCredentialData();
        Bundle r1 = r2.getDisplayInfo().toBundle();
        if ((r2 instanceof CreatePublicKeyCredentialRequest) == false) goto L5;
        int r22 = R$drawable.adx_ic_passkey;
    L6:
        r1.putParcelable("androidx.credentials.BUNDLE_KEY_CREDENTIAL_TYPE_ICON", Icon.createWithResource(r3, r22));
        r0.putBundle("androidx.credentials.BUNDLE_KEY_REQUEST_DISPLAY_INFO", r1);
        return r0;
    L5:
        r22 = R$drawable.adx_ic_other_sign_in;
        goto L6
    }

    public static final CreateCredentialException toJetpackCreateException(String r4, CharSequence r5) {
        switch(r4.hashCode()) {
            case -2055374133: goto L31;
            case -1166690414: goto L26;
            case -580283253: goto L21;
            case 1316905704: goto L16;
            case 2092588512: goto L11;
            case 2131915191: goto L6;
            default: goto L32;
        };
    L32:
        String r3 = null;
        if (StringsKt.startsWith$default(r4, "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", false, 2, null) == false) goto L40;
        CreatePublicKeyCredentialException.Companion r0 = CreatePublicKeyCredentialException.Companion;
        if (r5 == null) goto L38;
        r3 = r5.toString();
    L38:
        return r0.createFrom(r4, r3);
    L40:
        return new CreateCredentialCustomException(r4, r5);
    L6:
        if (r4.equals("android.credentials.CreateCredentialException.TYPE_NO_CREATE_OPTIONS") == false) goto L32;
        return new CreateCredentialNoCreateOptionException(r5);
    L11:
        if (r4.equals("android.credentials.CreateCredentialException.TYPE_INTERRUPTED") == false) goto L32;
        return new CreateCredentialInterruptedException(r5);
    L16:
        if (r4.equals("android.credentials.CreateCredentialException.TYPE_UNKNOWN") == false) goto L32;
        return new CreateCredentialUnknownException(r5);
    L21:
        if (r4.equals("androidx.credentials.TYPE_CREATE_CREDENTIAL_PROVIDER_CONFIGURATION_EXCEPTION") == false) goto L32;
        return new CreateCredentialProviderConfigurationException(r5);
    L26:
        if (r4.equals("androidx.credentials.TYPE_CREATE_CREDENTIAL_UNSUPPORTED_EXCEPTION") == false) goto L32;
        return new CreateCredentialUnsupportedException(r5);
    L31:
        if (r4.equals("android.credentials.CreateCredentialException.TYPE_USER_CANCELED") == false) goto L32;
        return new CreateCredentialCancellationException(r5);
    }

    public static final GetCredentialException toJetpackGetException(String r4, CharSequence r5) {
        switch(r4.hashCode()) {
            case -781118336: goto L31;
            case -408155724: goto L26;
            case -45448328: goto L21;
            case 580557411: goto L16;
            case 627896683: goto L11;
            case 1594095913: goto L6;
            default: goto L32;
        };
    L32:
        String r3 = null;
        if (StringsKt.startsWith$default(r4, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", false, 2, null) == false) goto L40;
        GetPublicKeyCredentialException.Companion r0 = GetPublicKeyCredentialException.Companion;
        if (r5 == null) goto L38;
        r3 = r5.toString();
    L38:
        return r0.createFrom(r4, r3);
    L40:
        return new GetCredentialCustomException(r4, r5);
    L6:
        if (r4.equals("androidx.credentials.TYPE_GET_CREDENTIAL_PROVIDER_CONFIGURATION_EXCEPTION") == false) goto L32;
        return new GetCredentialProviderConfigurationException(r5);
    L11:
        if (r4.equals("android.credentials.GetCredentialException.TYPE_NO_CREDENTIAL") == false) goto L32;
        return new NoCredentialException(r5);
    L16:
        if (r4.equals("android.credentials.GetCredentialException.TYPE_USER_CANCELED") == false) goto L32;
        return new GetCredentialCancellationException(r5);
    L21:
        if (r4.equals("android.credentials.GetCredentialException.TYPE_INTERRUPTED") == false) goto L32;
        return new GetCredentialInterruptedException(r5);
    L26:
        if (r4.equals("androidx.credentials.TYPE_GET_CREDENTIAL_UNSUPPORTED_EXCEPTION") == false) goto L32;
        return new GetCredentialUnsupportedException(r5);
    L31:
        if (r4.equals("android.credentials.GetCredentialException.TYPE_UNKNOWN") == false) goto L32;
        return new GetCredentialUnknownException(r5);
    }
}
