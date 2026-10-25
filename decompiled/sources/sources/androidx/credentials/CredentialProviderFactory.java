package androidx.credentials;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.credentials.internal.FormFactorHelper;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b\t\u0010\bJ'\u0010\r\u001a\u0004\u0018\u00010\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u00128\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u0004\u0018\u00010\u00068\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00068\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010!\u001a\u0004\b&\u0010\b\"\u0004\b'\u0010$¨\u0006)"}, d2 = {"Landroidx/credentials/CredentialProviderFactory;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroidx/credentials/CredentialProvider;", "tryCreateClosedSourceProviderFromManifest", "()Landroidx/credentials/CredentialProvider;", "tryCreatePostUProvider", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "classNames", "instantiatePreUProvider", "(Ljava/util/List;Landroid/content/Context;)Landroidx/credentials/CredentialProvider;", "getAllowedProvidersFromManifest", "(Landroid/content/Context;)Ljava/util/List;", PluginsConstants.REQUEST, _UrlKt.FRAGMENT_ENCODE_SET, "shouldFallbackToPreU", "getBestAvailableProvider", "(Ljava/lang/Object;Z)Landroidx/credentials/CredentialProvider;", "(Z)Landroidx/credentials/CredentialProvider;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "testMode", "Z", "getTestMode", "()Z", "setTestMode", "(Z)V", "testPostUProvider", "Landroidx/credentials/CredentialProvider;", "getTestPostUProvider", "setTestPostUProvider", "(Landroidx/credentials/CredentialProvider;)V", "testPreUProvider", "getTestPreUProvider", "setTestPreUProvider", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderFactory {
    public static final Companion Companion = null;
    private final Context context;
    private boolean testMode;
    private CredentialProvider testPostUProvider;
    private CredentialProvider testPreUProvider;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/credentials/CredentialProviderFactory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_CRED_MAN_PRE_FRAMEWORK_API_LEVEL", _UrlKt.FRAGMENT_ENCODE_SET, "CREDENTIAL_PROVIDER_KEY", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CredentialProviderFactory(Context r1) {
        this.context = r1;
    }

    private final List<String> getAllowedProvidersFromManifest(Context r5) {
        PackageInfo r4 = r5.getPackageManager().getPackageInfo(r5.getPackageName(), 132);
        ArrayList r52 = new ArrayList();
        ServiceInfo[] r42 = r4.services;
        if (r42 == null) goto L13;
        int r0 = r42.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L13;
        Bundle r2 = r42[r1].metaData;
        if (r2 == null) goto L11;
        String r22 = r2.getString("androidx.credentials.CREDENTIAL_PROVIDER_KEY");
        if (r22 == null) goto L11;
        r52.add(r22);
    L11:
        r1 = r1 + 1;
    L13:
        return CollectionsKt.toList(r52);
    }

    public static /* synthetic */ CredentialProvider getBestAvailableProvider$default(CredentialProviderFactory r0, Object r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = true;
    L6:
        return r0.getBestAvailableProvider(r1, r2);
    }

    private final CredentialProvider instantiatePreUProvider(List<String> r4, Context r5) {
        Iterator<String> r3 = r4.iterator();
        CredentialProvider r0 = null;
    L4:
        if (r3.hasNext() == false) goto L12;
        CredentialProvider r1 = (CredentialProvider) Class.forName(r3.next()).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{r5});     // Catch: Throwable -> L13
        if (r1.isAvailableOnDevice() == false) goto L4;
        if (r0 == null) goto L11;
        Log.i("CredProviderFactory", "Only one active OEM CredentialProvider allowed");     // Catch: Throwable -> L13
        return null;
    L11:
        r0 = r1;
        goto L4
    L12:
        return r0;
    }

    private final CredentialProvider tryCreateClosedSourceProviderFromManifest() {
        if (this.testMode == false) goto L12;
        CredentialProvider r0 = this.testPreUProvider;
        if (r0 != null) goto L8;
        return null;
    L8:
        if (r0.isAvailableOnDevice() == true) goto L10;
        return null;
    L10:
        return this.testPreUProvider;
    L12:
        List<String> r02 = getAllowedProvidersFromManifest(this.context);
        if (r02.isEmpty() == false) goto L16;
        return null;
    L16:
        return instantiatePreUProvider(r02, this.context);
    }

    private final CredentialProvider tryCreatePostUProvider() {
        if (this.testMode == false) goto L12;
        CredentialProvider r0 = this.testPostUProvider;
        if (r0 != null) goto L8;
        return null;
    L8:
        if (r0.isAvailableOnDevice() == true) goto L10;
        return null;
    L10:
        return this.testPostUProvider;
    L12:
        CredentialProviderFrameworkImpl r02 = new CredentialProviderFrameworkImpl(this.context);
        if (r02.isAvailableOnDevice() == false) goto L15;
        return r02;
    L15:
        return null;
    }

    public final CredentialProvider getBestAvailableProvider(Object r2, boolean r3) {
        if (Intrinsics.areEqual(r2, "androidx.credentials.TYPE_CLEAR_RESTORE_CREDENTIAL") == false) goto L7;
        return tryCreateClosedSourceProviderFromManifest();
    L7:
        if ((r2 instanceof GetCredentialRequest) == false) goto L13;
        Iterator<CredentialOption> r22 = ((GetCredentialRequest) r2).getCredentialOptions().iterator();
    L10:
        if (r22.hasNext() == false) goto L19;
        CredentialOption r0 = r22.next();
    L19:
        return getBestAvailableProvider(r3);
    L13:
        if ((r2 instanceof CreatePublicKeyCredentialRequest) == false) goto L19;
        if (((CreatePublicKeyCredentialRequest) r2).isConditional() == false) goto L19;
        return tryCreateClosedSourceProviderFromManifest();
    }

    public final CredentialProvider getBestAvailableProvider(boolean r3) {
        if (FormFactorHelper.isTV(this.context) == true) goto L22;
        if (FormFactorHelper.isAuto(this.context) == true) goto L22;
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 34) goto L16;
        CredentialProvider r02 = tryCreatePostUProvider();
        if (r02 != null) goto L14;
        if (r3 == false) goto L14;
        return tryCreateClosedSourceProviderFromManifest();
    L14:
        return r02;
    L16:
        if (r0 <= 33) goto L18;
        return null;
    L18:
        return tryCreateClosedSourceProviderFromManifest();
    L22:
        return tryCreateClosedSourceProviderFromManifest();
    }
}
