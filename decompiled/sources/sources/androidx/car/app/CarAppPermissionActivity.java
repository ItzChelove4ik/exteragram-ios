package androidx.car.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions;
import androidx.car.app.IOnRequestPermissionsListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class CarAppPermissionActivity extends ComponentActivity {
    public static /* synthetic */ void $r8$lambda$k1ognOoCW2FmgYy8_myqLBJYZBA(CarAppPermissionActivity r4, IOnRequestPermissionsListener r5, Map r6) {
        r4.getClass();
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator r62 = r6.entrySet().iterator();
    L4:
        if (r62.hasNext() == false) goto L18;
        Map.Entry r2 = (Map.Entry) r62.next();
        Boolean r3 = (Boolean) r2.getValue();
        if (r3 == null) goto L10;
        if (r3.booleanValue() == false) goto L10;
        r0.add((String) r2.getKey());
    L10:
        r1.add((String) r2.getKey());
        goto L4
    L18:
        r5.onRequestPermissionsResult((String[]) r0.toArray(new String[0]), (String[]) r1.toArray(new String[0]));     // Catch: RemoteException -> L14
    L16:
        r4.finish();
        return;
    L14:
        e = move-exception;
        Log.e("CarApp", "CarAppService dead when accepting/rejecting permissions", e);
        goto L16
    }

    public CarAppPermissionActivity() {
    }

    private void maybeSetCustomBackground() {
        Bundle r0 = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData;
        if (r0 == null) goto L6;
        int r02 = r0.getInt("androidx.car.app.theme");
    L7:
        Context r2 = createConfigurationContext(getResources().getConfiguration());
        if (r02 == 0) goto L10;
        r2.setTheme(r02);
    L10:
        int r03 = r2.getResources().getIdentifier("carPermissionActivityLayout", "attr", getPackageName());
        if (r03 == 0) goto L21;
        int r04 = r2.getTheme().obtainStyledAttributes(new int[]{r03}).getResourceId(0, 0);
        if (r04 == 0) goto L22;
        setContentView(r04);
        return;
    L22:
        return;
    L21:
        return;
    L6:
        r02 = 0;
    }

    private void processInternal(Intent r3) {
        if (r3 != null) goto L4;
    L7:
        StringBuilder r0 = new StringBuilder("Unexpected intent action for CarAppPermissionActivity: ");
        if (r3 != null) goto L10;
        String r32 = "null Intent";
    L11:
        r0.append(r32);
        Log.e("CarApp", r0.toString());
        finish();
        return;
    L10:
        r32 = r3.getAction();
        goto L11
    L4:
        if ("androidx.car.app.action.REQUEST_PERMISSIONS".equals(r3.getAction()) == false) goto L7;
        requestPermissions(r3);
    }

    private void requestPermissions(Intent r4) {
        Bundle r42 = r4.getExtras();
        IOnRequestPermissionsListener r0 = IOnRequestPermissionsListener.Stub.asInterface(r42.getBinder("androidx.car.app.action.EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY"));
        String[] r43 = r42.getStringArray("androidx.car.app.action.EXTRA_PERMISSIONS_KEY");
        if (r0 == null) goto L8;
        if (r43 == null) goto L8;
        registerForActivityResult(new ActivityResultContracts$RequestMultiplePermissions(), new CarAppPermissionActivity$$ExternalSyntheticLambda0(this, r0)).launch(r43);
        return;
    L8:
        Log.e("CarApp", "Intent to request permissions is missing the callback binder");
        finish();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        maybeSetCustomBackground();
        processInternal(getIntent());
    }
}
