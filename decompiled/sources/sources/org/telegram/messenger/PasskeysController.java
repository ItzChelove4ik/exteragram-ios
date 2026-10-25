package org.telegram.messenger;

import android.content.Context;
import android.content.DialogInterface;
import android.os.CancellationSignal;
import android.util.Base64;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.GetPublicKeyCredentialOption;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialInterruptedException;
import androidx.credentials.exceptions.CreateCredentialNoCreateOptionException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.NoCredentialException;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.PasskeysUtil;
import java.util.Arrays;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.json.JSONObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public class PasskeysController {

    /* renamed from: org.telegram.messenger.PasskeysController$1, reason: invalid class name */
    public class AnonymousClass1 implements CredentialManagerCallback<GetCredentialResponse, GetCredentialException> {
        final /* synthetic */ String val$clientDataJSON;
        final /* synthetic */ Context val$context;
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ Utilities.Callback3 val$done;

        public static /* synthetic */ void $r8$lambda$aD_TSB9_08HxxMQbIkVP1CwvnZ0(int r0, int r1, Utilities.Callback3 r2, long r3, DialogInterface r5) {
            ConnectionsManager.getInstance(r0).cancelRequest(r1, true);
            r2.run(Long.valueOf(r3), null, "CANCELLED");
        }

        public static /* synthetic */ void $r8$lambda$gx4vqzEbMkPbuY3kEBWRgHgji2Q(AlertDialog r0, Utilities.Callback3 r1, long r2, TLRPC.auth_Authorization r4, TLRPC.TL_error r5) {
            r0.dismiss();
            if (r5 == null) goto L6;
            r1.run(Long.valueOf(r2), null, r5.text);
            return;
        L6:
            r1.run(Long.valueOf(r2), r4, null);
        }

        public AnonymousClass1(String r1, Utilities.Callback3 r2, Context r3, int r4) {
            this.val$clientDataJSON = r1;
            this.val$done = r2;
            this.val$context = r3;
            this.val$currentAccount = r4;
        }

        /* renamed from: onError, reason: avoid collision after fix types in other method */
        public void onError2(GetCredentialException r5) {
            if ((r5 instanceof NoCredentialException) == false) goto L7;
            this.val$done.run(0L, null, "EMPTY");
            return;
        L7:
            if ((r5 instanceof GetCredentialCancellationException) == false) goto L11;
            this.val$done.run(0L, null, "CANCELLED");
            return;
        L11:
            if ((r5 instanceof GetCredentialInterruptedException) == false) goto L14;
            this.val$done.run(0L, null, "CANCELLED");
            return;
        L14:
            if (r5 == null) goto L17;
            this.val$done.run(0L, null, r5.getMessage());
            return;
        }

        @Override // androidx.credentials.CredentialManagerCallback
        public /* bridge */ /* synthetic */ void onError(GetCredentialException r1) {
            onError2(r1);
        }

        /* renamed from: onResult, reason: avoid collision after fix types in other method */
        public void onResult2(GetCredentialResponse r13) {
            Credential r132 = r13.getCredential();
            TL_account.finishPasskeyLogin r2 = new TL_account.finishPasskeyLogin();
            r2.credential = new TL_account.inputPasskeyCredentialPublicKey();
            JSONObject r1 = new JSONObject(r132.getData().getString("androidx.credentials.BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON"));     // Catch: Exception -> L9
            TL_account.inputPasskeyCredentialPublicKey r133 = r2.credential;     // Catch: Exception -> L9
            r133.id = r1.getString("id");     // Catch: Exception -> L9
            TL_account.inputPasskeyCredentialPublicKey r134 = r2.credential;     // Catch: Exception -> L9
            r134.raw_id = r1.getString("rawId");     // Catch: Exception -> L9
            JSONObject r135 = r1.getJSONObject(PluginsConstants.RESPONSE);     // Catch: Exception -> L9
            TL_account.inputPasskeyResponseLogin r12 = new TL_account.inputPasskeyResponseLogin();     // Catch: Exception -> L9
            TLRPC.TL_dataJSON r3 = new TLRPC.TL_dataJSON();     // Catch: Exception -> L9
            r12.client_data = r3;     // Catch: Exception -> L9
            r3.data = this.val$clientDataJSON;     // Catch: Exception -> L9
            r12.authenticator_data = Base64.decode(r135.getString("authenticatorData"), 8);     // Catch: Exception -> L9
            r12.signature = Base64.decode(r135.getString("signature"), 8);     // Catch: Exception -> L9
            String r32 = new String(Base64.decode(r135.getString("userHandle"), 8));     // Catch: Exception -> L9
            r12.user_handle = r32;     // Catch: Exception -> L9
            int r5 = Integer.parseInt(r32.split(":")[0]);     // Catch: Exception -> L9
            long r10 = Long.parseLong(r12.user_handle.split(":")[1]);     // Catch: Exception -> L9
            r2.credential.response = r12;     // Catch: Exception -> L9
            AlertDialog r136 = new AlertDialog(this.val$context, 3);
            r136.showDelayed(500);
            if (r5 == ConnectionsManager.getInstance(this.val$currentAccount).getCurrentDatacenterId()) goto L7;
            int r14 = ConnectionsManager.getInstance(this.val$currentAccount).getCurrentDatacenterId();
            long r33 = ConnectionsManager.getInstance(this.val$currentAccount).getCurrentAuthKeyId();
            ConnectionsManager.getInstance(this.val$currentAccount).setDefaultDatacenterId(r5);
            r2.flags = 1 | r2.flags;
            r2.from_dc_id = r14;
            r2.from_auth_key_id = r33;
        L7:
            int r8 = ConnectionsManager.getInstance(this.val$currentAccount).sendRequestTyped(r2, new AiTonesController$$ExternalSyntheticLambda0(), new PasskeysController$1$$ExternalSyntheticLambda0(r136, this.val$done, r10), r5, 72);
            r136.setOnCancelListener(new PasskeysController$1$$ExternalSyntheticLambda1(this.val$currentAccount, r8, this.val$done, r10));
            return;
        L9:
            e = move-exception;
            FileLog.e(e);
            this.val$done.run(0L, null, e.getMessage());
        }

        @Override // androidx.credentials.CredentialManagerCallback
        public /* bridge */ /* synthetic */ void onResult(GetCredentialResponse r1) {
            onResult2(r1);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: org.telegram.messenger.PasskeysController$2, reason: invalid class name */
    public class AnonymousClass2<T> implements Continuation<T> {
        final /* synthetic */ CoroutineContext val$ctx;
        final /* synthetic */ Utilities.Callback2 val$done;

        public AnonymousClass2(CoroutineContext r1, Utilities.Callback2 r2) {
            this.val$ctx = r1;
            this.val$done = r2;
        }

        @Override // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.val$ctx;
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(Object r3) {
            boolean r0 = r3 instanceof Result.Failure;
            Utilities.Callback2 r2 = this.val$done;
            if (r0 == false) goto L6;
            r2.run(null, ((Result.Failure) r3).exception);
            return;
        L6:
            r2.run(r3, null);
        }
    }

    /* renamed from: $r8$lambda$1MjSTZs9CI0K3y07f-5XdZkv0jE, reason: not valid java name */
    public static /* synthetic */ void m4810$r8$lambda$1MjSTZs9CI0K3y07f5XdZkv0jE(Utilities.Callback2 r3, String r4, Context r5, int r6, CreateCredentialResponse r7, Throwable r8) {
        if ((r8 instanceof CreateCredentialCancellationException) == false) goto L5;
    L21:
        AndroidUtilities.runOnUIThread(new PasskeysController$$ExternalSyntheticLambda3(r3));
        return;
    L5:
        if ((r8 instanceof CreateCredentialInterruptedException) == true) goto L21;
        if ((r8 instanceof CreateCredentialNoCreateOptionException) == false) goto L11;
        AndroidUtilities.runOnUIThread(new PasskeysController$$ExternalSyntheticLambda4(r3));
        return;
    L11:
        if (r8 == null) goto L14;
        FileLog.e(r8);
        AndroidUtilities.runOnUIThread(new PasskeysController$$ExternalSyntheticLambda5(r3, r8));
        return;
    L14:
        TL_account.registerPasskey r82 = new TL_account.registerPasskey();
        JSONObject r1 = new JSONObject(r7.getData().getString("androidx.credentials.BUNDLE_KEY_REGISTRATION_RESPONSE_JSON"));     // Catch: Exception -> L18
        TL_account.inputPasskeyCredentialPublicKey r72 = new TL_account.inputPasskeyCredentialPublicKey();     // Catch: Exception -> L18
        r82.credential = r72;     // Catch: Exception -> L18
        r72.id = r1.getString("id");     // Catch: Exception -> L18
        r82.credential.raw_id = r1.getString("rawId");     // Catch: Exception -> L18
        JSONObject r73 = r1.getJSONObject(PluginsConstants.RESPONSE);     // Catch: Exception -> L18
        TL_account.inputPasskeyResponseRegister r12 = new TL_account.inputPasskeyResponseRegister();     // Catch: Exception -> L18
        TLRPC.TL_dataJSON r2 = new TLRPC.TL_dataJSON();     // Catch: Exception -> L18
        r12.client_data = r2;     // Catch: Exception -> L18
        r2.data = r4;     // Catch: Exception -> L18
        r12.attestation_object = Base64.decode(r73.getString("attestationObject"), 8);     // Catch: Exception -> L18
        FileLog.d("AAGUID: " + bytesToHex(Arrays.copyOfRange(r12.attestation_object, 67, 83)));     // Catch: Exception -> L18
        r82.credential.response = r12;     // Catch: Exception -> L18
        AndroidUtilities.runOnUIThread(new PasskeysController$$ExternalSyntheticLambda7(r5, r6, r82, r3));
        return;
    L18:
        e = move-exception;
        FileLog.e(e);
        AndroidUtilities.runOnUIThread(new PasskeysController$$ExternalSyntheticLambda6(r3, e));
    }

    public static /* synthetic */ void $r8$lambda$3L4sUw_47wNxWVaSQ6GIWRaiOEQ(boolean[] r10, Utilities.Callback3 r11, boolean r12, CredentialManager r13, Context r14, int r15, Runnable[] r16, TL_account.passkeyLoginOptions r17, TLRPC.TL_error r18) {
        if (r10[0] == false) goto L6;
        return;
    L6:
        if (r18 == null) goto L18;
        r11.run(0L, null, r18.text);
        return;
    L18:
        JSONObject r0 = new JSONObject(r17.options.data).getJSONObject("publicKey");     // Catch: Exception -> L15
        String r4 = r0.toString();     // Catch: Exception -> L15
        String r2 = "https://" + r0.getString("rpId");     // Catch: Exception -> L15
        String r02 = PasskeysUtil.generateClientDataJSONRaw(true, r0.getString("challenge"), r2);     // Catch: Exception -> L15
        GetCredentialRequest r6 = new GetCredentialRequest.Builder().addCredentialOption(new GetPublicKeyCredentialOption(r4, PasskeysUtil.computeClientDataHash(r02))).setPreferImmediatelyAvailableCredentials(!r12).setOrigin(r2).build();
        CancellationSignal r7 = new CancellationSignal();     // Catch: Exception -> L13
        r13.getCredentialAsync(r14, r6, r7, r14.getMainExecutor(), new AnonymousClass1(r02, r11, r14, r15));     // Catch: Exception -> L13
        r16[0] = new PasskeysController$$ExternalSyntheticLambda13(r7);     // Catch: Exception -> L13
        return;
    L13:
        e = move-exception;
        r11.run(0L, null, e.getMessage());
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
        r11.run(0L, null, e.getMessage());
    }

    /* renamed from: $r8$lambda$4LI-8kUkkvm4fTJiz1FfZxtqb1c, reason: not valid java name */
    public static /* synthetic */ void m4811$r8$lambda$4LI8kUkkvm4fTJiz1FfZxtqb1c(Utilities.Callback2 r2) {
        r2.run(null, "EMPTY");
    }

    public static /* synthetic */ void $r8$lambda$7PaUYTRxLxqIuBYAKKSMAW6Rxm4(Utilities.Callback2 r2) {
        r2.run(null, "CANCELLED");
    }

    public static /* synthetic */ void $r8$lambda$9XpDvasej2ZlaH5A73JOyh8A3uo(AlertDialog r0, Utilities.Callback2 r1, TL_account.Passkey r2, TLRPC.TL_error r3) {
        r0.dismiss();
        if (r3 == null) goto L6;
        r1.run(null, r3.text);
        return;
    L6:
        r1.run(r2, null);
    }

    public static /* synthetic */ void $r8$lambda$UVUyS2yws1lPQGxhp8X8lhdWak0(int r1, int r2) {
        ConnectionsManager.getInstance(r1).cancelRequest(r2, true);
    }

    public static /* synthetic */ void $r8$lambda$jGBKiqU5vnTfDuEB3HKwGZLM41I(Utilities.Callback2 r1, Exception r2) {
        r1.run(null, r2.getMessage());
    }

    public static /* synthetic */ void $r8$lambda$kmLO7vdCRZXfRZ3Hc6aXdhHg8_Q(Utilities.Callback2 r1, Throwable r2) {
        r1.run(null, r2.getMessage());
    }

    public static /* synthetic */ void $r8$lambda$nn76CV_Y1sHdcydbtd3_ZJgwwi4(int r0, int r1, Utilities.Callback2 r2, DialogInterface r3) {
        ConnectionsManager.getInstance(r0).cancelRequest(r1, true);
        r2.run(null, "CANCELLED");
    }

    /* renamed from: $r8$lambda$r0BjSG3ZEIMsdcfxhA6pR0B2F-M, reason: not valid java name */
    public static /* synthetic */ void m4812$r8$lambda$r0BjSG3ZEIMsdcfxhA6pR0B2FM(boolean[] r2, Runnable[] r3) {
        r2[0] = true;
        Runnable r22 = r3[0];
        if (r22 == null) goto L6;
        r22.run();
        return;
    }

    /* renamed from: $r8$lambda$uzlz7C9R-irnBVnV8ty3z_v7jkQ, reason: not valid java name */
    public static /* synthetic */ void m4813$r8$lambda$uzlz7C9RirnBVnV8ty3z_v7jkQ(AlertDialog r3, Utilities.Callback2 r4, CredentialManager r5, Context r6, int r7, TL_account.passkeyRegistrationOptions r8, TLRPC.TL_error r9) {
        r3.dismiss();
        if (r9 == null) goto L17;
        r4.run(null, r9.text);
        return;
    L17:
        JSONObject r82 = new JSONObject(r8.options.data).getJSONObject("publicKey");     // Catch: Exception -> L12
        String r92 = r82.toString();     // Catch: Exception -> L12
        String r0 = "https://" + r82.getJSONObject("rp").getString("id");     // Catch: Exception -> L12
        String r83 = PasskeysUtil.generateClientDataJSONRaw(false, r82.getString("challenge"), r0);     // Catch: Exception -> L12
        r5.createCredential(r6, new CreatePublicKeyCredentialRequest(r92, PasskeysUtil.computeClientDataHash(r83), false, r0), ktxCallback(new PasskeysController$$ExternalSyntheticLambda8(r4, r83, r6, r7)));     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        AndroidUtilities.runOnUIThread(new PasskeysController$$ExternalSyntheticLambda9(r4, e));
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        r4.run(null, e.getMessage());
    }

    public static /* synthetic */ void $r8$lambda$wQWLfpnjbDXGpZVvTUA_EUCYbA4(Utilities.Callback2 r1, Exception r2) {
        r1.run(null, r2.getMessage());
    }

    public static /* synthetic */ void $r8$lambda$y0t8xdzxstUVdPjML0pMI_yGUOg(Context r3, int r4, TL_account.registerPasskey r5, Utilities.Callback2 r6) {
        AlertDialog r0 = new AlertDialog(r3, 3);
        r0.showDelayed(500);
        r0.setOnCancelListener(new PasskeysController$$ExternalSyntheticLambda11(r4, ConnectionsManager.getInstance(r4).sendRequestTyped(r5, new AiTonesController$$ExternalSyntheticLambda0(), new PasskeysController$$ExternalSyntheticLambda10(r0, r6)), r6));
    }

    public PasskeysController() {
    }

    public static String bytesToHex(byte[] r5) {
        StringBuilder r0 = new StringBuilder(r5.length * 2);
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.append(String.format("%02x", new Object[]{Byte.valueOf(r5[r2])}));
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    public static void create(Context r9, int r10, Utilities.Callback2<TL_account.Passkey, String> r11) {
        if (BuildVars.SUPPORTS_PASSKEYS == true) goto L5;
        return;
    L5:
        CredentialManager r4 = CredentialManager.create(r9);
        AlertDialog r2 = new AlertDialog(r9, 3);
        r2.showDelayed(500);
        ConnectionsManager.getInstance(r10).sendRequestTyped(new TL_account.initPasskeyRegistration(), new AiTonesController$$ExternalSyntheticLambda0(), new PasskeysController$$ExternalSyntheticLambda12(r2, r11, r4, r9, r10));
    }

    public static <T> Continuation<T> ktxCallback(CoroutineContext r1, Utilities.Callback2<T, Throwable> r2) {
        return new AnonymousClass2(r1, r2);
    }

    public static <T> Continuation<T> ktxCallback(Utilities.Callback2<T, Throwable> r1) {
        return ktxCallback(EmptyCoroutineContext.INSTANCE, r1);
    }

    public static Runnable login(Context r11, int r12, boolean r13, Utilities.Callback3<Long, TLRPC.auth_Authorization, String> r14) {
        if (BuildVars.SUPPORTS_PASSKEYS == true) goto L6;
        return null;
    L6:
        CredentialManager r4 = CredentialManager.create(r11);
        boolean[] r1 = new boolean[1];
        Runnable[] r7 = new Runnable[1];
        TL_account.initPasskeyLogin r8 = new TL_account.initPasskeyLogin();
        r8.api_id = BuildVars.getExteraAppId();
        r8.api_hash = BuildVars.getExteraAppHash();
        r7[0] = new PasskeysController$$ExternalSyntheticLambda1(r12, ConnectionsManager.getInstance(r12).sendRequestTyped(r8, new AiTonesController$$ExternalSyntheticLambda0(), new PasskeysController$$ExternalSyntheticLambda0(r1, r14, r13, r4, r11, r12, r7), 8));
        return new PasskeysController$$ExternalSyntheticLambda2(r1, r7);
    }
}
