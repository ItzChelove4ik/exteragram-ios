package com.android.billingclient.api;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts$StartIntentSenderForResult;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzie;

@UsedByReflection("PlatformActivityProxy")
/* loaded from: classes4.dex */
public class ProxyBillingActivityV2 extends ComponentActivity {
    private ActivityResultLauncher zza;
    private ActivityResultLauncher zzb;
    private ActivityResultLauncher zzc;
    private ResultReceiver zzd;
    private ResultReceiver zze;
    private ResultReceiver zzf;

    public ProxyBillingActivityV2() {
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle r5) {
        super.onCreate(r5);
        this.zza = registerForActivityResult(new ActivityResultContracts$StartIntentSenderForResult(), new zzct(this));
        this.zzb = registerForActivityResult(new ActivityResultContracts$StartIntentSenderForResult(), new zzcu(this));
        this.zzc = registerForActivityResult(new ActivityResultContracts$StartIntentSenderForResult(), new zzcv(this));
        if (r5 != null) goto L17;
        zzc.zzm("ProxyBillingActivityV2", "Launching Play Store billing dialog");
        if (getIntent().hasExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT") == false) goto L9;
        PendingIntent r52 = (PendingIntent) getIntent().getParcelableExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT");
        this.zzd = (ResultReceiver) getIntent().getParcelableExtra("alternative_billing_only_dialog_result_receiver");
        this.zza.launch(new IntentSenderRequest.Builder(r52).build());
        return;
    L9:
        if (getIntent().hasExtra("external_payment_dialog_pending_intent") == false) goto L13;
        PendingIntent r53 = (PendingIntent) getIntent().getParcelableExtra("external_payment_dialog_pending_intent");
        this.zze = (ResultReceiver) getIntent().getParcelableExtra("external_payment_dialog_result_receiver");
        this.zzb.launch(new IntentSenderRequest.Builder(r53).build());
        return;
    L13:
        if (getIntent().hasExtra("external_offer_flow_pending_intent") == false) goto L26;
        PendingIntent r54 = (PendingIntent) getIntent().getParcelableExtra("external_offer_flow_pending_intent");
        this.zzf = (ResultReceiver) getIntent().getParcelableExtra("external_offer_flow_result_receiver");
        this.zzc.launch(new IntentSenderRequest.Builder(r54).build());
        return;
    L26:
        return;
    L17:
        if (r5.containsKey("alternative_billing_only_dialog_result_receiver") == false) goto L20;
        this.zzd = (ResultReceiver) r5.getParcelable("alternative_billing_only_dialog_result_receiver");
    L20:
        if (r5.containsKey("external_payment_dialog_result_receiver") == false) goto L23;
        this.zze = (ResultReceiver) r5.getParcelable("external_payment_dialog_result_receiver");
    L23:
        if (r5.containsKey("external_offer_flow_result_receiver") == false) goto L27;
        this.zzf = (ResultReceiver) r5.getParcelable("external_offer_flow_result_receiver");
        return;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle r3) {
        super.onSaveInstanceState(r3);
        ResultReceiver r0 = this.zzd;
        if (r0 == null) goto L5;
        r3.putParcelable("alternative_billing_only_dialog_result_receiver", r0);
    L5:
        ResultReceiver r02 = this.zze;
        if (r02 == null) goto L8;
        r3.putParcelable("external_payment_dialog_result_receiver", r02);
    L8:
        ResultReceiver r2 = this.zzf;
        if (r2 == null) goto L12;
        r3.putParcelable("external_offer_flow_result_receiver", r2);
        return;
    }

    public final void zza(ActivityResult r5) {
        Intent r0 = r5.getData();
        int r2 = zzc.zzh(r0, "ProxyBillingActivityV2").getResponseCode();
        ResultReceiver r3 = this.zzd;
        if (r3 == null) goto L9;
        if (r0 != null) goto L6;
        Bundle r02 = null;
    L7:
        r3.send(r2, r02);
        goto L9
    L6:
        r02 = r0.getExtras();
    L9:
        if (r5.getResultCode() != (-1)) goto L11;
        if (r2 != 0) goto L11;
    L12:
        finish();
        return;
    L11:
        zzc.zzn("ProxyBillingActivityV2", "Alternative billing only dialog finished with resultCode " + r5.getResultCode() + " and billing's responseCode: " + r2);
        goto L12
    }

    public final void zzb(ActivityResult r5) {
        Intent r0 = r5.getData();
        int r2 = zzc.zzh(r0, "ProxyBillingActivityV2").getResponseCode();
        ResultReceiver r3 = this.zze;
        if (r3 == null) goto L9;
        if (r0 != null) goto L6;
        Bundle r02 = null;
    L7:
        r3.send(r2, r02);
        goto L9
    L6:
        r02 = r0.getExtras();
    L9:
        if (r5.getResultCode() != (-1)) goto L11;
        if (r2 != 0) goto L11;
    L12:
        finish();
        return;
    L11:
        zzc.zzn("ProxyBillingActivityV2", String.format("External offer dialog finished with resultCode: %s and billing's responseCode: %s", new Object[]{Integer.valueOf(r5.getResultCode()), Integer.valueOf(r2)}));
        goto L12
    }

    public final void zzc(ActivityResult r6) {
        Intent r0 = r6.getData();
        if (r0 != null) goto L5;
        Bundle r1 = null;
    L7:
        if (r6.getResultCode() == (-1)) goto L11;
        if (r1 != null) goto L10;
        r1 = new Bundle();
    L10:
        zzc.zzn("ProxyBillingActivityV2", String.format("External offer flow finished with resultCode: %s", new Object[]{Integer.valueOf(r6.getResultCode())}));
        r1.putInt("INTERNAL_LOG_ERROR_REASON", zzie.zzbv.zza());
        r1.putString("INTERNAL_LOG_ERROR_ADDITIONAL_DETAILS", String.format("External offer flow finished with error resultCode: %s", new Object[]{Integer.valueOf(r6.getResultCode())}));
    L11:
        int r62 = zzc.zzh(r0, "ProxyBillingActivityV2").getResponseCode();
        ResultReceiver r02 = this.zzf;
        if (r02 == null) goto L14;
        r02.send(r62, r1);
    L15:
        if (r62 == 0) goto L17;
        zzc.zzn("ProxyBillingActivityV2", String.format("External offer flow finished with billing responseCode: %s", new Object[]{Integer.valueOf(r62)}));
    L17:
        finish();
        return;
    L14:
        zzc.zzn("ProxyBillingActivityV2", "External offer flow result receiver is null");
        goto L15
    L5:
        r1 = r0.getExtras();
        goto L7
    }
}
