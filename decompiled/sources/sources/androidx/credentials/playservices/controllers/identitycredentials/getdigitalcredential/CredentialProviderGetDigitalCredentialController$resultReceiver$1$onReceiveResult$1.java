package androidx.credentials.playservices.controllers.identitycredentials.getdigitalcredential;

import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* synthetic */ class CredentialProviderGetDigitalCredentialController$resultReceiver$1$onReceiveResult$1 extends FunctionReferenceImpl implements Function2<String, String, GetCredentialException> {
    public CredentialProviderGetDigitalCredentialController$resultReceiver$1$onReceiveResult$1(Object r8) {
        super(2, r8, CredentialProviderBaseController.Companion.class, "getCredentialExceptionTypeToException", "getCredentialExceptionTypeToException$credentials_play_services_auth(Ljava/lang/String;Ljava/lang/String;)Landroidx/credentials/exceptions/GetCredentialException;", 0);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final GetCredentialException invoke2(String r1, String r2) {
        return ((CredentialProviderBaseController.Companion) this.receiver).getCredentialExceptionTypeToException$credentials_play_services_auth(r1, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ GetCredentialException invoke(String r1, String r2) {
        return invoke2(r1, r2);
    }
}
