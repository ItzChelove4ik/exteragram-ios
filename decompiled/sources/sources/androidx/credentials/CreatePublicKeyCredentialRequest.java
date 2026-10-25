package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.internal.RequestValidationHelper;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB]\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0011BG\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u000f\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0019\u001a\u0004\b\u000f\u0010\u001a¨\u0006\u001c"}, d2 = {"Landroidx/credentials/CreatePublicKeyCredentialRequest;", "Landroidx/credentials/CreateCredentialRequest;", _UrlKt.FRAGMENT_ENCODE_SET, "requestJson", _UrlKt.FRAGMENT_ENCODE_SET, "clientDataHash", _UrlKt.FRAGMENT_ENCODE_SET, "isAutoSelectAllowed", "preferImmediatelyAvailableCredentials", "Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "displayInfo", "origin", "Landroid/os/Bundle;", "credentialData", "candidateQueryData", "isConditional", "<init>", "(Ljava/lang/String;[BZZLandroidx/credentials/CreateCredentialRequest$DisplayInfo;Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;Z)V", "(Ljava/lang/String;[BZLjava/lang/String;ZZ)V", "Ljava/lang/String;", "getRequestJson", "()Ljava/lang/String;", "[B", "getClientDataHash", "()[B", "Z", "()Z", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreatePublicKeyCredentialRequest extends CreateCredentialRequest {
    public static final Companion Companion = null;
    private final byte[] clientDataHash;
    private final boolean isConditional;
    private final String requestJson;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0001¢\u0006\u0004\b\b\u0010\tJ#\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0001¢\u0006\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0014¨\u0006\u0018"}, d2 = {"Landroidx/credentials/CreatePublicKeyCredentialRequest$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "requestJson", "defaultProvider", "Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "getRequestDisplayInfo$credentials", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "getRequestDisplayInfo", _UrlKt.FRAGMENT_ENCODE_SET, "clientDataHash", "Landroid/os/Bundle;", "toCredentialDataBundle$credentials", "(Ljava/lang/String;[B)Landroid/os/Bundle;", "toCredentialDataBundle", "toCandidateDataBundle$credentials", "toCandidateDataBundle", "BUNDLE_KEY_CLIENT_DATA_HASH", "Ljava/lang/String;", "BUNDLE_KEY_REQUEST_JSON", "BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST", "BUNDLE_KEY_CONDITIONAL_CREATE", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ CreateCredentialRequest.DisplayInfo getRequestDisplayInfo$credentials$default(Companion r0, String r1, String r2, int r3, Object r4) {
            if ((r3 & 2) == 0) goto L6;
            r2 = null;
        L6:
            return r0.getRequestDisplayInfo$credentials(r1, r2);
        }

        @JvmStatic
        public final CreateCredentialRequest.DisplayInfo getRequestDisplayInfo$credentials(String r4, String r5) {
            JSONObject r42 = new JSONObject(r4).getJSONObject("user");     // Catch: Exception -> L9
            String r1 = r42.getString("name");     // Catch: Exception -> L9
            if (r42.isNull("displayName") == false) goto L6;
            String r3 = null;
        L7:
            return new CreateCredentialRequest.DisplayInfo(r1, r3, null, r5);
        L6:
            r3 = r42.getString("displayName");     // Catch: Exception -> L9
        L9:
            f$$ExternalSyntheticBUOutline1.m("user.name must be defined in requestJson");
            return null;
        }

        @JvmStatic
        public final Bundle toCandidateDataBundle$credentials(String r3, byte[] r4) {
            Bundle r2 = new Bundle();
            r2.putString("androidx.credentials.BUNDLE_KEY_SUBTYPE", "androidx.credentials.BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST");
            r2.putString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON", r3);
            r2.putByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH", r4);
            return r2;
        }

        @JvmStatic
        public final Bundle toCredentialDataBundle$credentials(String r3, byte[] r4) {
            Bundle r2 = new Bundle();
            r2.putString("androidx.credentials.BUNDLE_KEY_SUBTYPE", "androidx.credentials.BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST");
            r2.putString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON", r3);
            r2.putByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH", r4);
            return r2;
        }
    }

    static {
        Companion = new Companion(null);
    }

    @JvmOverloads
    public CreatePublicKeyCredentialRequest(String r10, byte[] r11, boolean r12, String r13) {
        this(r10, r11, r12, r13, false, false, 48, null);
    }

    @JvmOverloads
    public CreatePublicKeyCredentialRequest(String r16, byte[] r17, boolean r18, String r19, boolean r20, boolean r21) {
        this(r16, r17, r20, r18, Companion.getRequestDisplayInfo$credentials$default(Companion, r16, null, 2, null), r19, null, null, r21, 192, null);
    }

    public /* synthetic */ CreatePublicKeyCredentialRequest(String r3, byte[] r4, boolean r5, String r6, boolean r7, boolean r8, int r9, DefaultConstructorMarker r10) {
        if ((r9 & 2) == 0) goto L6;
        r4 = null;
    L6:
        if ((r9 & 4) == 0) goto L9;
        r5 = false;
    L9:
        if ((r9 & 8) == 0) goto L12;
        r6 = null;
    L12:
        if ((r9 & 16) == 0) goto L15;
        r7 = false;
    L15:
        if ((r9 & 32) == 0) goto L17;
        r8 = false;
    L17:
        this(r3, r4, r5, r6, r7, r8);
    }

    private CreatePublicKeyCredentialRequest(String r11, byte[] r12, boolean r13, boolean r14, CreateCredentialRequest.DisplayInfo r15, String r16, Bundle r17, Bundle r18, boolean r19) {
        super("androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL", r17, r18, false, r13, r15, r16, r14);
        this.requestJson = r11;
        this.clientDataHash = r12;
        this.isConditional = r19;
        if (RequestValidationHelper.Companion.isValidJSON(r11) == false) goto L7;
        if (r19 == false) goto L9;
        r18.putBoolean("androidx.credentials.BUNDLE_KEY_IS_CONDITIONAL_REQUEST", true);
        r17.putBoolean("androidx.credentials.BUNDLE_KEY_IS_CONDITIONAL_REQUEST", true);
        return;
    L9:
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("requestJson must not be empty, and must be a valid JSON");
        throw null;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ CreatePublicKeyCredentialRequest(java.lang.String r11, byte[] r12, boolean r13, boolean r14, androidx.credentials.CreateCredentialRequest.DisplayInfo r15, java.lang.String r16, android.os.Bundle r17, android.os.Bundle r18, boolean r19, int r20, kotlin.jvm.internal.DefaultConstructorMarker r21) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: ARITH (r3v0 ?? I:??[int, boolean]) = (r20v0 ?? I:??[int, boolean, short, byte, char]) & (32 ??[int, float, short, byte, char]) A[DECLARE_VAR] in method: androidx.credentials.CreatePublicKeyCredentialRequest.<init>(java.lang.String, byte[], boolean, boolean, androidx.credentials.CreateCredentialRequest$DisplayInfo, java.lang.String, android.os.Bundle, android.os.Bundle, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void, file: classes3.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            r10 = this;
            r0 = r20
            r3 = r0 & 32
            if (r3 == 0) goto L9
            r3 = 0
            r6 = r3
            goto Lb
        L9:
            r6 = r16
        Lb:
            r3 = r0 & 64
            if (r3 == 0) goto L17
            androidx.credentials.CreatePublicKeyCredentialRequest$Companion r3 = androidx.credentials.CreatePublicKeyCredentialRequest.Companion
            android.os.Bundle r3 = r3.toCredentialDataBundle$credentials(r11, r12)
            r7 = r3
            goto L19
        L17:
            r7 = r17
        L19:
            r3 = r0 & 128(0x80, float:1.8E-43)
            if (r3 == 0) goto L25
            androidx.credentials.CreatePublicKeyCredentialRequest$Companion r3 = androidx.credentials.CreatePublicKeyCredentialRequest.Companion
            android.os.Bundle r3 = r3.toCandidateDataBundle$credentials(r11, r12)
            r8 = r3
            goto L27
        L25:
            r8 = r18
        L27:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L34
            r0 = 0
            r9 = r0
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r0 = r10
            goto L3c
        L34:
            r9 = r19
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
        L3c:
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.CreatePublicKeyCredentialRequest.<init>(java.lang.String, byte[], boolean, boolean, androidx.credentials.CreateCredentialRequest$DisplayInfo, java.lang.String, android.os.Bundle, android.os.Bundle, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getRequestJson() {
        return this.requestJson;
    }

    public final boolean isConditional() {
        return this.isConditional;
    }
}
