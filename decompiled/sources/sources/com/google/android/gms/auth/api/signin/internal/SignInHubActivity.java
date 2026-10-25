package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;

@KeepName
/* loaded from: classes4.dex */
public class SignInHubActivity extends FragmentActivity {
    private static boolean zba = false;
    private boolean zbb;
    private SignInConfiguration zbc;
    private boolean zbd;
    private int zbe;
    private Intent zbf;

    static {
    }

    public SignInHubActivity() {
        this.zbb = false;
    }

    private final void zbc(String r3) {
        Intent r0 = new Intent(r3);
        if (r3.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") == false) goto L5;
        r0.setPackage("com.google.android.gms");
    L6:
        r0.putExtra("config", this.zbc);
        startActivityForResult(r0, 40962);     // Catch: ActivityNotFoundException -> L9
        return;
    L9:
        this.zbb = true;
        Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
        zbe(17);
        return;
    L5:
        r0.setPackage(getPackageName());
        goto L6
    }

    private final void zbd() {
        getSupportLoaderManager().initLoader(0, null, new zbv(this, null));
        zba = false;
    }

    private final void zbe(int r3) {
        Status r0 = new Status(r3);
        Intent r32 = new Intent();
        r32.putExtra("googleSignInStatus", r0);
        setResult(0, r32);
        finish();
        zba = false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r1) {
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int r4, int r5, Intent r6) {
        if (this.zbb == true) goto L29;
        setResult(0);
        if (r4 == 40962) goto L9;
        return;
    L9:
        if (r6 == null) goto L27;
        SignInAccount r1 = (SignInAccount) r6.getParcelableExtra("signInAccount");
        if (r1 == null) goto L21;
        if (r1.zba() == null) goto L21;
        GoogleSignInAccount r42 = r1.zba();
        if (r42 != null) goto L18;
        Log.e("AuthSignInClient", "Google account is null");
        zbe(12500);
        return;
    L18:
        zbn.zba(this).zbc(this.zbc.zba(), r42);
        r6.removeExtra("signInAccount");
        r6.putExtra("googleSignInAccount", r42);
        this.zbd = true;
        this.zbe = r5;
        this.zbf = r6;
        zbd();
        return;
    L21:
        if (r6.hasExtra("errorCode") == false) goto L27;
        int r43 = r6.getIntExtra("errorCode", 8);
        if (r43 != 13) goto L25;
        r43 = 12501;
    L25:
        zbe(r43);
        return;
    L27:
        zbe(8);
        return;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle r6) {
        super.onCreate(r6);
        Intent r0 = getIntent();
        String r1 = r0.getAction();
        if (r1 != null) goto L7;
        Log.e("AuthSignInClient", "Null action");
        zbe(12500);
        return;
    L7:
        if (r1.equals("com.google.android.gms.auth.NO_IMPL") == false) goto L11;
        Log.e("AuthSignInClient", "Action not implemented");
        zbe(12500);
        return;
    L11:
        if (r1.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") == false) goto L13;
    L16:
        Bundle r02 = r0.getBundleExtra("config");
        if (r02 != null) goto L20;
        Log.e("AuthSignInClient", "Activity started with no configuration.");
        setResult(0);
        finish();
        return;
    L20:
        SignInConfiguration r03 = (SignInConfiguration) r02.getParcelable("config");
        if (r03 != null) goto L24;
        Log.e("AuthSignInClient", "Activity started with invalid configuration.");
        setResult(0);
        finish();
        return;
    L24:
        this.zbc = r03;
        if (r6 == null) goto L27;
        boolean r04 = r6.getBoolean("signingInGoogleApiClients");
        this.zbd = r04;
        if (r04 == false) goto L40;
        this.zbe = r6.getInt("signInResultCode");
        Intent r62 = (Intent) r6.getParcelable("signInResultData");
        if (r62 != null) goto L38;
        Log.e("AuthSignInClient", "Sign in result data cannot be null");
        setResult(0);
        finish();
        return;
    L38:
        this.zbf = r62;
        zbd();
        return;
    L40:
        return;
    L27:
        if (zba == false) goto L30;
        setResult(0);
        zbe(12502);
        return;
    L30:
        zba = true;
        zbc(r1);
        return;
    L13:
        if (r1.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN") == true) goto L16;
        Log.e("AuthSignInClient", "Unknown action: ".concat(String.valueOf(r0.getAction())));
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        zba = false;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle r3) {
        super.onSaveInstanceState(r3);
        r3.putBoolean("signingInGoogleApiClients", this.zbd);
        if (this.zbd == false) goto L6;
        r3.putInt("signInResultCode", this.zbe);
        r3.putParcelable("signInResultData", this.zbf);
        return;
    }

    public final /* synthetic */ int zba() {
        return this.zbe;
    }

    public final /* synthetic */ Intent zbb() {
        return this.zbf;
    }
}
