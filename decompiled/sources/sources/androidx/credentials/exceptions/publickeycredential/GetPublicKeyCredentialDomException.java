package androidx.credentials.exceptions.publickeycredential;

import androidx.credentials.exceptions.GetCredentialCustomException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.domerrors.AbortError;
import androidx.credentials.exceptions.domerrors.ConstraintError;
import androidx.credentials.exceptions.domerrors.DataCloneError;
import androidx.credentials.exceptions.domerrors.DataError;
import androidx.credentials.exceptions.domerrors.DomError;
import androidx.credentials.exceptions.domerrors.EncodingError;
import androidx.credentials.exceptions.domerrors.HierarchyRequestError;
import androidx.credentials.exceptions.domerrors.InUseAttributeError;
import androidx.credentials.exceptions.domerrors.InvalidCharacterError;
import androidx.credentials.exceptions.domerrors.InvalidModificationError;
import androidx.credentials.exceptions.domerrors.InvalidNodeTypeError;
import androidx.credentials.exceptions.domerrors.InvalidStateError;
import androidx.credentials.exceptions.domerrors.NamespaceError;
import androidx.credentials.exceptions.domerrors.NetworkError;
import androidx.credentials.exceptions.domerrors.NoModificationAllowedError;
import androidx.credentials.exceptions.domerrors.NotAllowedError;
import androidx.credentials.exceptions.domerrors.NotFoundError;
import androidx.credentials.exceptions.domerrors.NotReadableError;
import androidx.credentials.exceptions.domerrors.NotSupportedError;
import androidx.credentials.exceptions.domerrors.OperationError;
import androidx.credentials.exceptions.domerrors.OptOutError;
import androidx.credentials.exceptions.domerrors.QuotaExceededError;
import androidx.credentials.exceptions.domerrors.ReadOnlyError;
import androidx.credentials.exceptions.domerrors.SecurityError;
import androidx.credentials.exceptions.domerrors.SyntaxError;
import androidx.credentials.exceptions.domerrors.TimeoutError;
import androidx.credentials.exceptions.domerrors.TransactionInactiveError;
import androidx.credentials.exceptions.domerrors.UnknownError;
import androidx.credentials.exceptions.domerrors.VersionError;
import androidx.credentials.exceptions.domerrors.WrongDocumentError;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\b\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialDomException;", "Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialException;", "Landroidx/credentials/exceptions/domerrors/DomError;", "domError", _UrlKt.FRAGMENT_ENCODE_SET, "errorMessage", "<init>", "(Landroidx/credentials/exceptions/domerrors/DomError;Ljava/lang/CharSequence;)V", "Landroidx/credentials/exceptions/domerrors/DomError;", "getDomError", "()Landroidx/credentials/exceptions/domerrors/DomError;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetPublicKeyCredentialDomException extends GetPublicKeyCredentialException {
    public static final Companion Companion = null;
    private final DomError domError;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialDomException$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", _UrlKt.FRAGMENT_ENCODE_SET, "createFrom", "Landroidx/credentials/exceptions/GetCredentialException;", "type", "msg", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGetPublicKeyCredentialDomException.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPublicKeyCredentialDomException.kt\nandroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialDomException$Companion\n+ 2 DomExceptionUtils.kt\nandroidx/credentials/exceptions/publickeycredential/DomExceptionUtils$Companion\n*L\n1#1,67:1\n67#2,75:68\n*S KotlinDebug\n*F\n+ 1 GetPublicKeyCredentialDomException.kt\nandroidx/credentials/exceptions/publickeycredential/GetPublicKeyCredentialDomException$Companion\n*L\n53#1:68,75\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final GetCredentialException createFrom(String r5, String r6) {
            DomExceptionUtils.Companion r4 = DomExceptionUtils.Companion;     // Catch: FrameworkClassParsingException -> L93
            GetPublicKeyCredentialDomException r0 = new GetPublicKeyCredentialDomException(new UnknownError(), null, 2, null);     // Catch: FrameworkClassParsingException -> L93
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_ABORT_ERROR") == false) goto L6;
            Object r42 = DomExceptionUtils.Companion.access$generateException(r4, new AbortError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
        L90:
            return (GetCredentialException) r42;
        L6:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_CONSTRAINT_ERROR") == false) goto L9;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new ConstraintError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L9:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_DATA_CLONE_ERROR") == false) goto L12;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new DataCloneError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L12:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_DATA_ERROR") == false) goto L15;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new DataError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L15:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_ENCODING_ERROR") == false) goto L18;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new EncodingError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L18:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_HIERARCHY_REQUEST_ERROR") == false) goto L21;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new HierarchyRequestError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L21:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_IN_USE_ATTRIBUTE_ERROR") == false) goto L24;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new InUseAttributeError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L24:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_CHARACTER_ERROR") == false) goto L27;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new InvalidCharacterError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L27:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_MODIFICATION_ERROR") == false) goto L30;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new InvalidModificationError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L30:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_NODE_TYPE_ERROR") == false) goto L33;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new InvalidNodeTypeError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L33:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_STATE_ERROR") == false) goto L36;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new InvalidStateError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L36:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NAMESPACE_ERROR") == false) goto L39;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new NamespaceError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L39:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NETWORK_ERROR") == false) goto L42;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new NetworkError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L42:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NO_MODIFICATION_ALLOWED_ERROR") == false) goto L45;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new NoModificationAllowedError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L45:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_ALLOWED_ERROR") == false) goto L48;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new NotAllowedError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L48:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_FOUND_ERROR") == false) goto L51;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new NotFoundError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L51:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_READABLE_ERROR") == false) goto L54;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new NotReadableError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L54:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_SUPPORTED_ERROR") == false) goto L57;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new NotSupportedError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L57:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_OPERATION_ERROR") == false) goto L60;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new OperationError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L60:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_OPT_OUT_ERROR") == false) goto L63;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new OptOutError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L63:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_QUOTA_EXCEEDED_ERROR") == false) goto L66;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new QuotaExceededError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L66:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_READ_ONLY_ERROR") == false) goto L69;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new ReadOnlyError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L69:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_SECURITY_ERROR") == false) goto L72;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new SecurityError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L72:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_SYNTAX_ERROR") == false) goto L75;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new SyntaxError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L75:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_TIMEOUT_ERROR") == false) goto L78;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new TimeoutError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L78:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_TRANSACTION_INACTIVE_ERROR") == false) goto L81;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new TransactionInactiveError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L81:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_UNKNOWN_ERROR") == false) goto L84;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new UnknownError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L84:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_VERSION_ERROR") == false) goto L87;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new VersionError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L87:
            if (Intrinsics.areEqual(r5, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_WRONG_DOCUMENT_ERROR") == false) goto L92;
            r42 = DomExceptionUtils.Companion.access$generateException(r4, new WrongDocumentError(), r6, r0);     // Catch: FrameworkClassParsingException -> L93
            goto L90
        L92:
            throw new FrameworkClassParsingException();     // Catch: FrameworkClassParsingException -> L93
        L94:
            return new GetCredentialCustomException(r5, r6);
        }
    }

    static {
        Companion = new Companion(null);
    }

    @JvmOverloads
    public GetPublicKeyCredentialDomException(DomError r3, CharSequence r4) {
        super("androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/" + r3.getType(), r4);
        this.domError = r3;
    }

    public /* synthetic */ GetPublicKeyCredentialDomException(DomError r1, CharSequence r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }
}
