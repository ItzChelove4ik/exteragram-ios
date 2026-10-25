package androidx.credentials.playservices.controllers.identityauth.createpublickeycredential;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.GetPublicKeyCredentialOption;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.domerrors.AbortError;
import androidx.credentials.exceptions.domerrors.ConstraintError;
import androidx.credentials.exceptions.domerrors.DataError;
import androidx.credentials.exceptions.domerrors.DomError;
import androidx.credentials.exceptions.domerrors.EncodingError;
import androidx.credentials.exceptions.domerrors.InvalidStateError;
import androidx.credentials.exceptions.domerrors.NetworkError;
import androidx.credentials.exceptions.domerrors.NotAllowedError;
import androidx.credentials.exceptions.domerrors.NotReadableError;
import androidx.credentials.exceptions.domerrors.NotSupportedError;
import androidx.credentials.exceptions.domerrors.SecurityError;
import androidx.credentials.exceptions.domerrors.TimeoutError;
import androidx.credentials.exceptions.domerrors.UnknownError;
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialDomException;
import androidx.credentials.exceptions.publickeycredential.GetPublicKeyCredentialDomException;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier;
import com.google.android.gms.fido.fido2.api.common.ErrorCode;
import com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension;
import com.google.android.gms.fido.fido2.api.common.GoogleThirdPartyPaymentExtension;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement;
import com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00022\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/createpublickeycredential/PublicKeyCredentialControllerUtility;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "GetGMSVersion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class PublicKeyCredentialControllerUtility {
    public static final Companion Companion = null;
    private static final String JSON_KEY_ALG = null;
    private static final String JSON_KEY_APPID = null;
    private static final String JSON_KEY_ATTESTATION = null;
    private static final String JSON_KEY_ATTESTATION_OBJ = null;
    private static final String JSON_KEY_AUTH_ATTACHMENT = null;
    private static final String JSON_KEY_AUTH_DATA = null;
    private static final String JSON_KEY_AUTH_SELECTION = null;
    private static final String JSON_KEY_CHALLENGE = null;
    private static final String JSON_KEY_CLIENT_DATA = null;
    private static final String JSON_KEY_CLIENT_EXTENSION_RESULTS = null;
    private static final String JSON_KEY_CRED_PROPS = null;
    private static final String JSON_KEY_DISPLAY_NAME = null;
    private static final String JSON_KEY_EXCLUDE_CREDENTIALS = null;
    private static final String JSON_KEY_EXTENSTIONS = null;
    private static final String JSON_KEY_ICON = null;
    private static final String JSON_KEY_ID = null;
    private static final String JSON_KEY_KEY_PROTECTION_TYPE = null;
    private static final String JSON_KEY_MATCHER_PROTECTION_TYPE = null;
    private static final String JSON_KEY_NAME = null;
    private static final String JSON_KEY_PUB_KEY_CRED_PARAMS = null;
    private static final String JSON_KEY_RAW_ID = null;
    private static final String JSON_KEY_REQUIRE_RES_KEY = null;
    private static final String JSON_KEY_RESPONSE = null;
    private static final String JSON_KEY_RES_KEY = null;
    private static final String JSON_KEY_RK = null;
    private static final String JSON_KEY_RP = null;
    private static final String JSON_KEY_RPID = null;
    private static final String JSON_KEY_SIGNATURE = null;
    private static final String JSON_KEY_THIRD_PARTY_PAYMENT = null;
    private static final String JSON_KEY_TIMEOUT = null;
    private static final String JSON_KEY_TRANSPORTS = null;
    private static final String JSON_KEY_TYPE = null;
    private static final String JSON_KEY_USER = null;
    private static final String JSON_KEY_USER_HANDLE = null;
    private static final String JSON_KEY_USER_VERIFICATION_METHOD = null;
    private static final LinkedHashMap<ErrorCode, DomError> orderedErrorCodeToExceptions = null;

    @Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007¢\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010\u0019\u001a\u00020%¢\u0006\u0004\b'\u0010(J!\u0010/\u001a\u00020,2\u0006\u0010*\u001a\u00020)2\b\u0010+\u001a\u0004\u0018\u00010\u001aH\u0000¢\u0006\u0004\b-\u0010.J\u001f\u00105\u001a\u0002022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b3\u00104J\u001f\u00107\u001a\u0002022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b6\u00104J\u001f\u00109\u001a\u0002022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b8\u00104J\u001f\u0010;\u001a\u0002022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b:\u00104J\u001f\u0010=\u001a\u0002022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b<\u00104J\u001f\u0010?\u001a\u0002022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b>\u00104J\u0015\u0010A\u001a\u00020\r2\u0006\u0010@\u001a\u00020\u001a¢\u0006\u0004\bA\u0010BJ\u0015\u0010E\u001a\u00020\b2\u0006\u0010D\u001a\u00020C¢\u0006\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bK\u0010H\u001a\u0004\bL\u0010JR\u001a\u0010M\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bM\u0010H\u001a\u0004\bN\u0010JR\u001a\u0010O\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bO\u0010H\u001a\u0004\bP\u0010JR\u001a\u0010Q\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bQ\u0010H\u001a\u0004\bR\u0010JR\u001a\u0010S\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bS\u0010H\u001a\u0004\bT\u0010JR\u001a\u0010U\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bU\u0010H\u001a\u0004\bV\u0010JR\u001a\u0010W\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bW\u0010H\u001a\u0004\bX\u0010JR\u001a\u0010Y\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bY\u0010H\u001a\u0004\bZ\u0010JR\u001a\u0010[\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\b[\u0010H\u001a\u0004\b\\\u0010JR\u001a\u0010]\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\b]\u0010H\u001a\u0004\b^\u0010JR\u001a\u0010_\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\b_\u0010H\u001a\u0004\b`\u0010JR\u001a\u0010a\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\ba\u0010H\u001a\u0004\bb\u0010JR\u001a\u0010c\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bc\u0010H\u001a\u0004\bd\u0010JR\u001a\u0010e\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\be\u0010H\u001a\u0004\bf\u0010JR\u001a\u0010g\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bg\u0010H\u001a\u0004\bh\u0010JR\u001a\u0010i\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bi\u0010H\u001a\u0004\bj\u0010JR\u001a\u0010k\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bk\u0010H\u001a\u0004\bl\u0010JR\u001a\u0010m\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bm\u0010H\u001a\u0004\bn\u0010JR\u001a\u0010o\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bo\u0010H\u001a\u0004\bp\u0010JR\u001a\u0010q\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bq\u0010H\u001a\u0004\br\u0010JR\u001a\u0010s\u001a\u00020\u001a8\u0000X\u0080D¢\u0006\f\n\u0004\bs\u0010H\u001a\u0004\bt\u0010JR6\u0010x\u001a\u001e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020v0uj\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020v`w8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bx\u0010y\u001a\u0004\bz\u0010{R\u0014\u0010|\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b|\u0010}R\u0014\u0010~\u001a\u00020C8\u0002X\u0082T¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00020\u001a8\u0002X\u0082T¢\u0006\u0007\n\u0005\b\u0080\u0001\u0010H¨\u0006\u0081\u0001"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/createpublickeycredential/PublicKeyCredentialControllerUtility$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "version", _UrlKt.FRAGMENT_ENCODE_SET, "isDeviceGMSVersionOlderThan", "(Landroid/content/Context;J)Z", "Lorg/json/JSONObject;", "json", _UrlKt.FRAGMENT_ENCODE_SET, "getChallenge", "(Lorg/json/JSONObject;)[B", "Landroidx/credentials/CreatePublicKeyCredentialRequest;", PluginsConstants.REQUEST, "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions;", "convert", "(Landroidx/credentials/CreatePublicKeyCredentialRequest;Landroid/content/Context;)Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions;", "convertJSON$credentials_play_services_auth", "(Lorg/json/JSONObject;)Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions;", "convertJSON", "Lcom/google/android/gms/auth/api/identity/SignInCredential;", "cred", _UrlKt.FRAGMENT_ENCODE_SET, "toAssertPasskeyResponse", "(Lcom/google/android/gms/auth/api/identity/SignInCredential;)Ljava/lang/String;", "Landroidx/credentials/GetPublicKeyCredentialOption;", "option", "Lcom/google/android/gms/auth/api/identity/BeginSignInRequest$PasskeyJsonRequestOptions;", "convertToPlayAuthPasskeyJsonRequest", "(Landroidx/credentials/GetPublicKeyCredentialOption;)Lcom/google/android/gms/auth/api/identity/BeginSignInRequest$PasskeyJsonRequestOptions;", "Lcom/google/android/gms/auth/api/identity/BeginSignInRequest$PasskeysRequestOptions;", "convertToPlayAuthPasskeyRequest", "(Landroidx/credentials/GetPublicKeyCredentialOption;)Lcom/google/android/gms/auth/api/identity/BeginSignInRequest$PasskeysRequestOptions;", "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredential;", "Landroidx/credentials/exceptions/CreateCredentialException;", "publicKeyCredentialResponseContainsError", "(Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredential;)Landroidx/credentials/exceptions/CreateCredentialException;", "Lcom/google/android/gms/fido/fido2/api/common/ErrorCode;", "code", "msg", "Landroidx/credentials/exceptions/GetCredentialException;", "beginSignInPublicKeyCredentialResponseContainsError$credentials_play_services_auth", "(Lcom/google/android/gms/fido/fido2/api/common/ErrorCode;Ljava/lang/String;)Landroidx/credentials/exceptions/GetCredentialException;", "beginSignInPublicKeyCredentialResponseContainsError", "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions$Builder;", "builder", _UrlKt.FRAGMENT_ENCODE_SET, "parseOptionalExtensions$credentials_play_services_auth", "(Lorg/json/JSONObject;Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions$Builder;)V", "parseOptionalExtensions", "parseOptionalAuthenticatorSelection$credentials_play_services_auth", "parseOptionalAuthenticatorSelection", "parseOptionalTimeout$credentials_play_services_auth", "parseOptionalTimeout", "parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials$credentials_play_services_auth", "parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials", "parseRequiredRpAndParams$credentials_play_services_auth", "parseRequiredRpAndParams", "parseRequiredChallengeAndUser$credentials_play_services_auth", "parseRequiredChallengeAndUser", "str", "b64Decode", "(Ljava/lang/String;)[B", _UrlKt.FRAGMENT_ENCODE_SET, "alg", "checkAlgSupported", "(I)Z", "JSON_KEY_ID", "Ljava/lang/String;", "getJSON_KEY_ID$credentials_play_services_auth", "()Ljava/lang/String;", "JSON_KEY_TYPE", "getJSON_KEY_TYPE$credentials_play_services_auth", "JSON_KEY_RPID", "getJSON_KEY_RPID$credentials_play_services_auth", "JSON_KEY_CHALLENGE", "getJSON_KEY_CHALLENGE$credentials_play_services_auth", "JSON_KEY_APPID", "getJSON_KEY_APPID$credentials_play_services_auth", "JSON_KEY_THIRD_PARTY_PAYMENT", "getJSON_KEY_THIRD_PARTY_PAYMENT$credentials_play_services_auth", "JSON_KEY_AUTH_SELECTION", "getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth", "JSON_KEY_REQUIRE_RES_KEY", "getJSON_KEY_REQUIRE_RES_KEY$credentials_play_services_auth", "JSON_KEY_RES_KEY", "getJSON_KEY_RES_KEY$credentials_play_services_auth", "JSON_KEY_AUTH_ATTACHMENT", "getJSON_KEY_AUTH_ATTACHMENT$credentials_play_services_auth", "JSON_KEY_TIMEOUT", "getJSON_KEY_TIMEOUT$credentials_play_services_auth", "JSON_KEY_EXCLUDE_CREDENTIALS", "getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth", "JSON_KEY_TRANSPORTS", "getJSON_KEY_TRANSPORTS$credentials_play_services_auth", "JSON_KEY_RP", "getJSON_KEY_RP$credentials_play_services_auth", "JSON_KEY_NAME", "getJSON_KEY_NAME$credentials_play_services_auth", "JSON_KEY_ICON", "getJSON_KEY_ICON$credentials_play_services_auth", "JSON_KEY_ALG", "getJSON_KEY_ALG$credentials_play_services_auth", "JSON_KEY_USER", "getJSON_KEY_USER$credentials_play_services_auth", "JSON_KEY_DISPLAY_NAME", "getJSON_KEY_DISPLAY_NAME$credentials_play_services_auth", "JSON_KEY_EXTENSTIONS", "getJSON_KEY_EXTENSTIONS$credentials_play_services_auth", "JSON_KEY_ATTESTATION", "getJSON_KEY_ATTESTATION$credentials_play_services_auth", "JSON_KEY_PUB_KEY_CRED_PARAMS", "getJSON_KEY_PUB_KEY_CRED_PARAMS$credentials_play_services_auth", "Ljava/util/LinkedHashMap;", "Landroidx/credentials/exceptions/domerrors/DomError;", "Lkotlin/collections/LinkedHashMap;", "orderedErrorCodeToExceptions", "Ljava/util/LinkedHashMap;", "getOrderedErrorCodeToExceptions$credentials_play_services_auth", "()Ljava/util/LinkedHashMap;", "AUTH_MIN_VERSION_JSON_CREATE", "J", "FLAGS", "I", "TAG", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final byte[] getChallenge(JSONObject r3) {
            String r32 = r3.optString(getJSON_KEY_CHALLENGE$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r32.length() == 0) goto L7;
            return b64Decode(r32);
        L7:
            throw new JSONException("Challenge not found in request or is unexpectedly empty");
        }

        private final boolean isDeviceGMSVersionOlderThan(Context r4, long r5) {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(r4) == 0) goto L5;
            return false;
        L5:
            PackageManager r3 = r4.getPackageManager();
            if (Build.VERSION.SDK_INT < 28) goto L8;
            long r32 = GetGMSVersion.getVersionLong(r3.getPackageInfo("com.google.android.gms", 0));
        L10:
            if (r32 <= r5) goto L13;
            return true;
        L13:
            return false;
        L8:
            r32 = r3.getPackageInfo("com.google.android.gms", 0).versionCode;
            goto L10
        }

        public final byte[] b64Decode(String r1) {
            return Base64.decode(r1, 11);
        }

        public final GetCredentialException beginSignInPublicKeyCredentialResponseContainsError$credentials_play_services_auth(ErrorCode r4, String r5) {
            DomError r3 = getOrderedErrorCodeToExceptions$credentials_play_services_auth().get(r4);
            if (r3 != null) goto L7;
            return new GetPublicKeyCredentialDomException(new UnknownError(), "unknown fido gms exception - " + r5);
        L7:
            if (r4 != ErrorCode.NOT_ALLOWED_ERR) goto L14;
            if (r5 == null) goto L14;
            if (StringsKt.contains$default(r5, "Unable to get sync account", false, 2, null) != true) goto L14;
            return new GetCredentialCancellationException("Passkey retrieval was cancelled by the user.");
        L14:
            return new GetPublicKeyCredentialDomException(r3, r5);
        }

        public final boolean checkAlgSupported(int r1) {
            COSEAlgorithmIdentifier.fromCoseValue(r1);     // Catch: Throwable -> L5
            return true;
        L5:
            return false;
        }

        @JvmStatic
        public final PublicKeyCredentialCreationOptions convert(CreatePublicKeyCredentialRequest r3, Context r4) {
            if (isDeviceGMSVersionOlderThan(r4, 241217000) == false) goto L7;
            return new PublicKeyCredentialCreationOptions(r3.getRequestJson());
        L7:
            return convertJSON$credentials_play_services_auth(new JSONObject(r3.getRequestJson()));
        }

        public final PublicKeyCredentialCreationOptions convertJSON$credentials_play_services_auth(JSONObject r2) {
            PublicKeyCredentialCreationOptions.Builder r0 = new PublicKeyCredentialCreationOptions.Builder();
            parseRequiredChallengeAndUser$credentials_play_services_auth(r2, r0);
            parseRequiredRpAndParams$credentials_play_services_auth(r2, r0);
            parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials$credentials_play_services_auth(r2, r0);
            parseOptionalTimeout$credentials_play_services_auth(r2, r0);
            parseOptionalAuthenticatorSelection$credentials_play_services_auth(r2, r0);
            parseOptionalExtensions$credentials_play_services_auth(r2, r0);
            return r0.build();
        }

        public final BeginSignInRequest.PasskeyJsonRequestOptions convertToPlayAuthPasskeyJsonRequest(GetPublicKeyCredentialOption r2) {
            return new BeginSignInRequest.PasskeyJsonRequestOptions.Builder().setSupported(true).setRequestJson(r2.getRequestJson()).build();
        }

        @Deprecated(message = "Upgrade GMS version so 'convertToPlayAuthPasskeyJsonRequest' is used")
        public final BeginSignInRequest.PasskeysRequestOptions convertToPlayAuthPasskeyRequest(GetPublicKeyCredentialOption r3) {
            JSONObject r0 = new JSONObject(r3.getRequestJson());
            String r32 = r0.optString(getJSON_KEY_RPID$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r32.length() == 0) goto L7;
            byte[] r2 = getChallenge(r0);
            return new BeginSignInRequest.PasskeysRequestOptions.Builder().setSupported(true).setRpId(r32).setChallenge(r2).build();
        L7:
            throw new JSONException("GetPublicKeyCredentialOption - rpId not specified in the request or is unexpectedly empty");
        }

        public final String getJSON_KEY_ALG$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_ALG$cp();
        }

        public final String getJSON_KEY_APPID$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_APPID$cp();
        }

        public final String getJSON_KEY_ATTESTATION$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_ATTESTATION$cp();
        }

        public final String getJSON_KEY_AUTH_ATTACHMENT$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_AUTH_ATTACHMENT$cp();
        }

        public final String getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_AUTH_SELECTION$cp();
        }

        public final String getJSON_KEY_CHALLENGE$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_CHALLENGE$cp();
        }

        public final String getJSON_KEY_DISPLAY_NAME$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_DISPLAY_NAME$cp();
        }

        public final String getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_EXCLUDE_CREDENTIALS$cp();
        }

        public final String getJSON_KEY_EXTENSTIONS$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_EXTENSTIONS$cp();
        }

        public final String getJSON_KEY_ICON$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_ICON$cp();
        }

        public final String getJSON_KEY_ID$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_ID$cp();
        }

        public final String getJSON_KEY_NAME$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_NAME$cp();
        }

        public final String getJSON_KEY_PUB_KEY_CRED_PARAMS$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_PUB_KEY_CRED_PARAMS$cp();
        }

        public final String getJSON_KEY_REQUIRE_RES_KEY$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_REQUIRE_RES_KEY$cp();
        }

        public final String getJSON_KEY_RES_KEY$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_RES_KEY$cp();
        }

        public final String getJSON_KEY_RP$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_RP$cp();
        }

        public final String getJSON_KEY_RPID$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_RPID$cp();
        }

        public final String getJSON_KEY_THIRD_PARTY_PAYMENT$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_THIRD_PARTY_PAYMENT$cp();
        }

        public final String getJSON_KEY_TIMEOUT$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_TIMEOUT$cp();
        }

        public final String getJSON_KEY_TRANSPORTS$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_TRANSPORTS$cp();
        }

        public final String getJSON_KEY_TYPE$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_TYPE$cp();
        }

        public final String getJSON_KEY_USER$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getJSON_KEY_USER$cp();
        }

        public final LinkedHashMap<ErrorCode, DomError> getOrderedErrorCodeToExceptions$credentials_play_services_auth() {
            return PublicKeyCredentialControllerUtility.access$getOrderedErrorCodeToExceptions$cp();
        }

        public final void parseOptionalAuthenticatorSelection$credentials_play_services_auth(JSONObject r6, PublicKeyCredentialCreationOptions.Builder r7) {
            if (r6.has(getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth()) == false) goto L13;
            JSONObject r62 = r6.getJSONObject(getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth());
            AuthenticatorSelectionCriteria.Builder r0 = new AuthenticatorSelectionCriteria.Builder();
            boolean r1 = r62.optBoolean(getJSON_KEY_REQUIRE_RES_KEY$credentials_play_services_auth(), false);
            String r2 = r62.optString(getJSON_KEY_RES_KEY$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r2.length() <= 0) goto L7;
            ResidentKeyRequirement r22 = ResidentKeyRequirement.fromString(r2);
        L8:
            r0.setRequireResidentKey(Boolean.valueOf(r1)).setResidentKeyRequirement(r22);
            String r5 = r62.optString(getJSON_KEY_AUTH_ATTACHMENT$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r5.length() <= 0) goto L11;
            r0.setAttachment(Attachment.fromString(r5));
        L11:
            r7.setAuthenticatorSelection(r0.build());
            return;
        L7:
            r22 = null;
            goto L8
        }

        public final void parseOptionalExtensions$credentials_play_services_auth(JSONObject r4, PublicKeyCredentialCreationOptions.Builder r5) {
            if (r4.has(getJSON_KEY_EXTENSTIONS$credentials_play_services_auth()) == false) goto L15;
            JSONObject r42 = r4.getJSONObject(getJSON_KEY_EXTENSTIONS$credentials_play_services_auth());
            AuthenticationExtensions.Builder r0 = new AuthenticationExtensions.Builder();
            String r1 = r42.optString(getJSON_KEY_APPID$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r1.length() <= 0) goto L8;
            r0.setFido2Extension(new FidoAppIdExtension(r1));
        L8:
            if (r42.optBoolean(getJSON_KEY_THIRD_PARTY_PAYMENT$credentials_play_services_auth(), false) == false) goto L11;
            r0.setGoogleThirdPartyPaymentExtension(new GoogleThirdPartyPaymentExtension(true));
        L11:
            if (r42.optBoolean("uvm", false) == false) goto L13;
            r0.setUserVerificationMethodExtension(new UserVerificationMethodExtension(true));
        L13:
            r5.setAuthenticationExtensions(r0.build());
            return;
        }

        public final void parseOptionalTimeout$credentials_play_services_auth(JSONObject r3, PublicKeyCredentialCreationOptions.Builder r4) {
            if (r3.has(getJSON_KEY_TIMEOUT$credentials_play_services_auth()) == false) goto L6;
            r4.setTimeoutSeconds(Double.valueOf(r3.getLong(getJSON_KEY_TIMEOUT$credentials_play_services_auth()) / 1000.0d));
            return;
        }

        public final void parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials$credentials_play_services_auth(JSONObject r13, PublicKeyCredentialCreationOptions.Builder r14) {
            ArrayList r0 = new ArrayList();
            if (r13.has(getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth()) == false) goto L25;
            JSONArray r1 = r13.getJSONArray(getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth());
            int r2 = r1.length();
            int r4 = 0;
        L5:
            if (r4 >= r2) goto L25;
            JSONObject r5 = r1.getJSONObject(r4);
            byte[] r6 = b64Decode(r5.getString(getJSON_KEY_ID$credentials_play_services_auth()));
            String r7 = r5.getString(getJSON_KEY_TYPE$credentials_play_services_auth());
            if (r7.length() == 0) goto L24;
            if (r6.length == 0) goto L22;
            if (r5.has(getJSON_KEY_TRANSPORTS$credentials_play_services_auth()) == false) goto L19;
            ArrayList r8 = new ArrayList();
            JSONArray r52 = r5.getJSONArray(getJSON_KEY_TRANSPORTS$credentials_play_services_auth());
            int r9 = r52.length();
            int r10 = 0;
        L13:
            if (r10 >= r9) goto L20;
            r8.add(Transport.fromString(r52.getString(r10)));     // Catch: Transport.UnsupportedTransportException -> L16
            r10 = r10 + 1;
        L16:
            e = move-exception;
            throw new CreatePublicKeyCredentialDomException(new EncodingError(), e.getMessage());
        L20:
            r0.add(new PublicKeyCredentialDescriptor(r7, r6, r8));
            r4 = r4 + 1;
            goto L5
        L19:
            r8 = null;
            goto L20
        L22:
            throw new JSONException("PublicKeyCredentialDescriptor id value is not found or unexpectedly empty");
        L24:
            throw new JSONException("PublicKeyCredentialDescriptor type value is not found or unexpectedly empty");
        L25:
            r14.setExcludeList(r0);
            String r02 = "none";
            String r12 = r13.optString(getJSON_KEY_ATTESTATION$credentials_play_services_auth(), "none");
            if (r12.length() == 0) goto L29;
            r02 = r12;
        L29:
            r14.setAttestationConveyancePreference(AttestationConveyancePreference.fromString(r02));
        }

        public final void parseRequiredChallengeAndUser$credentials_play_services_auth(JSONObject r5, PublicKeyCredentialCreationOptions.Builder r6) {
            r6.setChallenge(getChallenge(r5));
            JSONObject r52 = r5.getJSONObject(getJSON_KEY_USER$credentials_play_services_auth());
            byte[] r0 = b64Decode(r52.getString(getJSON_KEY_ID$credentials_play_services_auth()));
            String r1 = r52.getString(getJSON_KEY_NAME$credentials_play_services_auth());
            String r2 = r52.getString(getJSON_KEY_DISPLAY_NAME$credentials_play_services_auth());
            String r4 = r52.optString(getJSON_KEY_ICON$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r2.length() == 0) goto L15;
            if (r0.length == 0) goto L13;
            if (r1.length() == 0) goto L11;
            r6.setUser(new PublicKeyCredentialUserEntity(r0, r1, r4, r2));
            return;
        L11:
            throw new JSONException("PublicKeyCredentialCreationOptions UserEntity missing user name or they are unexpectedly empty");
        L13:
            throw new JSONException("PublicKeyCredentialCreationOptions UserEntity missing user id or they are unexpectedly empty");
        L15:
            throw new JSONException("PublicKeyCredentialCreationOptions UserEntity missing displayName or they are unexpectedly empty");
        }

        public final void parseRequiredRpAndParams$credentials_play_services_auth(JSONObject r8, PublicKeyCredentialCreationOptions.Builder r9) {
            JSONObject r0 = r8.getJSONObject(getJSON_KEY_RP$credentials_play_services_auth());
            String r1 = r0.getString(getJSON_KEY_ID$credentials_play_services_auth());
            String r2 = r0.optString(getJSON_KEY_NAME$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            String r02 = r0.optString(getJSON_KEY_ICON$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r02.length() != 0) goto L6;
            r02 = null;
        L6:
            if (r2.length() == 0) goto L24;
            if (r1.length() == 0) goto L22;
            r9.setRp(new PublicKeyCredentialRpEntity(r1, r2, r02));
            JSONArray r82 = r8.getJSONArray(getJSON_KEY_PUB_KEY_CRED_PARAMS$credentials_play_services_auth());
            ArrayList r03 = new ArrayList();
            int r12 = r82.length();
            int r22 = 0;
        L10:
            if (r22 >= r12) goto L19;
            JSONObject r4 = r82.getJSONObject(r22);
            int r5 = (int) r4.getLong(getJSON_KEY_ALG$credentials_play_services_auth());
            String r42 = r4.optString(getJSON_KEY_TYPE$credentials_play_services_auth(), _UrlKt.FRAGMENT_ENCODE_SET);
            if (r42.length() == 0) goto L18;
            if (checkAlgSupported(r5) == false) goto L16;
            r03.add(new PublicKeyCredentialParameters(r42, r5));
        L16:
            r22 = r22 + 1;
            goto L10
        L18:
            throw new JSONException("PublicKeyCredentialCreationOptions PublicKeyCredentialParameter type missing or unexpectedly empty");
        L19:
            r9.setParameters(r03);
            return;
        L22:
            throw new JSONException("PublicKeyCredentialCreationOptions rp ID is missing or unexpectedly empty");
        L24:
            throw new JSONException("PublicKeyCredentialCreationOptions rp name is missing or unexpectedly empty");
        }

        public final CreateCredentialException publicKeyCredentialResponseContainsError(PublicKeyCredential r5) {
            AuthenticatorResponse r52 = r5.getResponse();
            if ((r52 instanceof AuthenticatorErrorResponse) == false) goto L17;
            AuthenticatorErrorResponse r53 = (AuthenticatorErrorResponse) r52;
            ErrorCode r0 = r53.getErrorCode();
            DomError r4 = getOrderedErrorCodeToExceptions$credentials_play_services_auth().get(r0);
            String r54 = r53.getErrorMessage();
            if (r4 != null) goto L9;
            return new CreatePublicKeyCredentialDomException(new UnknownError(), "unknown fido gms exception - " + r54);
        L9:
            if (r0 != ErrorCode.NOT_ALLOWED_ERR) goto L16;
            if (r54 == null) goto L16;
            if (StringsKt.contains$default(r54, "Unable to get sync account", false, 2, null) != true) goto L16;
            return new CreateCredentialCancellationException("Passkey registration was cancelled by the user.");
        L16:
            return new CreatePublicKeyCredentialDomException(r4, r54);
        L17:
            return null;
        }

        public final String toAssertPasskeyResponse(SignInCredential r4) {
            JSONObject r0 = new JSONObject();
            PublicKeyCredential r42 = r4.getPublicKeyCredential();
            if (r42 == null) goto L5;
            AuthenticatorResponse r1 = r42.getResponse();
        L7:
            if ((r1 instanceof AuthenticatorErrorResponse) == false) goto L9;
            AuthenticatorErrorResponse r12 = (AuthenticatorErrorResponse) r1;
            throw beginSignInPublicKeyCredentialResponseContainsError$credentials_play_services_auth(r12.getErrorCode(), r12.getErrorMessage());
        L9:
            if ((r1 instanceof AuthenticatorAssertionResponse) == true) goto L19;
            Log.e("PublicKeyUtility", "AuthenticatorResponse expected assertion response but got: ".concat(r1.getClass().getName()));
            return r0.toString();
        L19:
            return r42.toJson();
        L12:
            th = move-exception;
            throw new GetCredentialUnknownException("The PublicKeyCredential response json had an unexpected exception when parsing: " + th.getMessage());
        L5:
            r1 = null;
            goto L7
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/createpublickeycredential/PublicKeyCredentialControllerUtility$GetGMSVersion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getVersionLong", _UrlKt.FRAGMENT_ENCODE_SET, "info", "Landroid/content/pm/PackageInfo;", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class GetGMSVersion {
        public static final GetGMSVersion INSTANCE = null;

        static {
            INSTANCE = new GetGMSVersion();
        }

        private GetGMSVersion() {
        }

        @JvmStatic
        public static final long getVersionLong(PackageInfo r2) {
            return r2.getLongVersionCode();
        }
    }

    static {
        Companion = new Companion(null);
        JSON_KEY_CLIENT_DATA = "clientDataJSON";
        JSON_KEY_ATTESTATION_OBJ = "attestationObject";
        JSON_KEY_AUTH_DATA = "authenticatorData";
        JSON_KEY_SIGNATURE = "signature";
        JSON_KEY_USER_HANDLE = "userHandle";
        JSON_KEY_RESPONSE = PluginsConstants.RESPONSE;
        JSON_KEY_ID = "id";
        JSON_KEY_RAW_ID = "rawId";
        JSON_KEY_TYPE = "type";
        JSON_KEY_RPID = "rpId";
        JSON_KEY_CHALLENGE = "challenge";
        JSON_KEY_APPID = "appid";
        JSON_KEY_THIRD_PARTY_PAYMENT = "thirdPartyPayment";
        JSON_KEY_AUTH_SELECTION = "authenticatorSelection";
        JSON_KEY_REQUIRE_RES_KEY = "requireResidentKey";
        JSON_KEY_RES_KEY = "residentKey";
        JSON_KEY_AUTH_ATTACHMENT = "authenticatorAttachment";
        JSON_KEY_TIMEOUT = "timeout";
        JSON_KEY_EXCLUDE_CREDENTIALS = "excludeCredentials";
        JSON_KEY_TRANSPORTS = "transports";
        JSON_KEY_RP = "rp";
        JSON_KEY_NAME = "name";
        JSON_KEY_ICON = "icon";
        JSON_KEY_ALG = "alg";
        JSON_KEY_USER = "user";
        JSON_KEY_DISPLAY_NAME = "displayName";
        JSON_KEY_USER_VERIFICATION_METHOD = "userVerificationMethod";
        JSON_KEY_KEY_PROTECTION_TYPE = "keyProtectionType";
        JSON_KEY_MATCHER_PROTECTION_TYPE = "matcherProtectionType";
        JSON_KEY_EXTENSTIONS = "extensions";
        JSON_KEY_ATTESTATION = "attestation";
        JSON_KEY_PUB_KEY_CRED_PARAMS = "pubKeyCredParams";
        JSON_KEY_CLIENT_EXTENSION_RESULTS = "clientExtensionResults";
        JSON_KEY_RK = "rk";
        JSON_KEY_CRED_PROPS = "credProps";
        orderedErrorCodeToExceptions = MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(ErrorCode.UNKNOWN_ERR, new UnknownError()), TuplesKt.to(ErrorCode.ABORT_ERR, new AbortError()), TuplesKt.to(ErrorCode.ATTESTATION_NOT_PRIVATE_ERR, new NotReadableError()), TuplesKt.to(ErrorCode.CONSTRAINT_ERR, new ConstraintError()), TuplesKt.to(ErrorCode.DATA_ERR, new DataError()), TuplesKt.to(ErrorCode.INVALID_STATE_ERR, new InvalidStateError()), TuplesKt.to(ErrorCode.ENCODING_ERR, new EncodingError()), TuplesKt.to(ErrorCode.NETWORK_ERR, new NetworkError()), TuplesKt.to(ErrorCode.NOT_ALLOWED_ERR, new NotAllowedError()), TuplesKt.to(ErrorCode.NOT_SUPPORTED_ERR, new NotSupportedError()), TuplesKt.to(ErrorCode.SECURITY_ERR, new SecurityError()), TuplesKt.to(ErrorCode.TIMEOUT_ERR, new TimeoutError())});
    }

    public static final /* synthetic */ String access$getJSON_KEY_ALG$cp() {
        return JSON_KEY_ALG;
    }

    public static final /* synthetic */ String access$getJSON_KEY_APPID$cp() {
        return JSON_KEY_APPID;
    }

    public static final /* synthetic */ String access$getJSON_KEY_ATTESTATION$cp() {
        return JSON_KEY_ATTESTATION;
    }

    public static final /* synthetic */ String access$getJSON_KEY_AUTH_ATTACHMENT$cp() {
        return JSON_KEY_AUTH_ATTACHMENT;
    }

    public static final /* synthetic */ String access$getJSON_KEY_AUTH_SELECTION$cp() {
        return JSON_KEY_AUTH_SELECTION;
    }

    public static final /* synthetic */ String access$getJSON_KEY_CHALLENGE$cp() {
        return JSON_KEY_CHALLENGE;
    }

    public static final /* synthetic */ String access$getJSON_KEY_DISPLAY_NAME$cp() {
        return JSON_KEY_DISPLAY_NAME;
    }

    public static final /* synthetic */ String access$getJSON_KEY_EXCLUDE_CREDENTIALS$cp() {
        return JSON_KEY_EXCLUDE_CREDENTIALS;
    }

    public static final /* synthetic */ String access$getJSON_KEY_EXTENSTIONS$cp() {
        return JSON_KEY_EXTENSTIONS;
    }

    public static final /* synthetic */ String access$getJSON_KEY_ICON$cp() {
        return JSON_KEY_ICON;
    }

    public static final /* synthetic */ String access$getJSON_KEY_ID$cp() {
        return JSON_KEY_ID;
    }

    public static final /* synthetic */ String access$getJSON_KEY_NAME$cp() {
        return JSON_KEY_NAME;
    }

    public static final /* synthetic */ String access$getJSON_KEY_PUB_KEY_CRED_PARAMS$cp() {
        return JSON_KEY_PUB_KEY_CRED_PARAMS;
    }

    public static final /* synthetic */ String access$getJSON_KEY_REQUIRE_RES_KEY$cp() {
        return JSON_KEY_REQUIRE_RES_KEY;
    }

    public static final /* synthetic */ String access$getJSON_KEY_RES_KEY$cp() {
        return JSON_KEY_RES_KEY;
    }

    public static final /* synthetic */ String access$getJSON_KEY_RP$cp() {
        return JSON_KEY_RP;
    }

    public static final /* synthetic */ String access$getJSON_KEY_RPID$cp() {
        return JSON_KEY_RPID;
    }

    public static final /* synthetic */ String access$getJSON_KEY_THIRD_PARTY_PAYMENT$cp() {
        return JSON_KEY_THIRD_PARTY_PAYMENT;
    }

    public static final /* synthetic */ String access$getJSON_KEY_TIMEOUT$cp() {
        return JSON_KEY_TIMEOUT;
    }

    public static final /* synthetic */ String access$getJSON_KEY_TRANSPORTS$cp() {
        return JSON_KEY_TRANSPORTS;
    }

    public static final /* synthetic */ String access$getJSON_KEY_TYPE$cp() {
        return JSON_KEY_TYPE;
    }

    public static final /* synthetic */ String access$getJSON_KEY_USER$cp() {
        return JSON_KEY_USER;
    }

    public static final /* synthetic */ LinkedHashMap access$getOrderedErrorCodeToExceptions$cp() {
        return orderedErrorCodeToExceptions;
    }
}
