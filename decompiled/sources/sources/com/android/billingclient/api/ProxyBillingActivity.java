package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.android.billingclient.api.BillingResult;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzie;
import com.google.android.gms.internal.play_billing.zzil;

@UsedByReflection("PlatformActivityProxy")
/* loaded from: classes4.dex */
public class ProxyBillingActivity extends Activity {
    private int activityCode;
    private long billingClientTransactionId;
    private ResultReceiver inAppMessageResultReceiver;
    private boolean isFlowFromFirstPartyClient;
    private boolean sendCancelledBroadcastIfFinished;
    private boolean wasServiceAutoReconnected;

    public ProxyBillingActivity() {
    }

    private zzie getReasonForNullData(int r1) {
        if (r1 == (-1)) goto L22;
        if (r1 == 0) goto L20;
        if (r1 == 3) goto L18;
        if (r1 == 4) goto L16;
        if (r1 == 5) goto L14;
        return zzie.zzbm;
    L14:
        return zzie.zzbn;
    L16:
        return zzie.zzbl;
    L18:
        return zzie.zzbk;
    L20:
        return zzie.zzbj;
    L22:
        return zzie.zzbi;
    }

    private Intent makeAlternativeBillingIntent(String r3) {
        Intent r0 = new Intent("com.android.vending.billing.ALTERNATIVE_BILLING");
        r0.setPackage(getApplicationContext().getPackageName());
        r0.putExtra("ALTERNATIVE_BILLING_USER_CHOICE_DATA", r3);
        return r0;
    }

    private Intent makePurchaseUpdatedIntentWithInternalErrorReason(zzie r6, long r7) {
        Intent r0 = makePurchasesUpdatedIntent();
        r0.putExtra("RESPONSE_CODE", 6);
        r0.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
        BillingResult.Builder r1 = BillingResult.newBuilder();
        r1.setResponseCode(6);
        r1.setDebugMessage("An internal error occurred.");
        BillingResult r12 = r1.build();
        int r2 = zzcg.$r8$clinit;
        r0.putExtra("FAILURE_LOGGING_PAYLOAD", zzcg.zzb(r6, 2, r12, null, zzil.zza).zzM());
        r0.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
        r0.putExtra("billingClientTransactionId", r7);
        r0.putExtra("wasServiceAutoReconnected", this.wasServiceAutoReconnected);
        return r0;
    }

    private Intent makePurchasesUpdatedIntent() {
        Intent r0 = new Intent("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        r0.setPackage(getApplicationContext().getPackageName());
        return r0;
    }

    @Override // android.app.Activity
    public void onActivityResult(int r6, int r7, Intent r8) {
        super.onActivityResult(r6, r7, r8);
        if (r6 == 100) goto L15;
        if (r6 == 110) goto L15;
        if (r6 != 101) goto L14;
        int r62 = zzc.zza(r8, "ProxyBillingActivity");
        ResultReceiver r72 = this.inAppMessageResultReceiver;
        if (r72 == null) goto L33;
        if (r8 != null) goto L12;
        Bundle r82 = null;
    L13:
        r72.send(r62, r82);
        goto L33
    L12:
        r82 = r8.getExtras();
    L33:
        this.sendCancelledBroadcastIfFinished = false;
        finish();
        return;
    L14:
        zzc.zzn("ProxyBillingActivity", "Got onActivityResult with wrong requestCode: " + r6 + "; skipping...");
    L15:
        int r0 = zzc.zzh(r8, "ProxyBillingActivity").getResponseCode();
        int r3 = -1;
        if (r7 != (-1)) goto L19;
        if (r0 == 0) goto L20;
        r7 = -1;
    L20:
        if (r8 != null) goto L22;
        zzc.zzn("ProxyBillingActivity", "Got null data with resultCode " + r3 + "!");
        Intent r73 = makePurchaseUpdatedIntentWithInternalErrorReason(getReasonForNullData(r3), this.billingClientTransactionId);
    L30:
        if (r6 != 110) goto L32;
        r73.putExtra("IS_FIRST_PARTY_PURCHASE", true);
    L32:
        sendBroadcast(r73);
        goto L33
    L22:
        if (r8.getExtras() == null) goto L28;
        String r74 = r8.getExtras().getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
        if (r74 == null) goto L26;
        r73 = makeAlternativeBillingIntent(r74);
        r73.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
    L27:
        r73.putExtra("billingClientTransactionId", this.billingClientTransactionId);
        r73.putExtra("wasServiceAutoReconnected", this.wasServiceAutoReconnected);
        goto L30
    L26:
        r73 = makePurchasesUpdatedIntent();
        r73.putExtras(r8.getExtras());
        r73.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
        goto L27
    L28:
        zzc.zzn("ProxyBillingActivity", "Got null bundle!");
        r73 = makePurchaseUpdatedIntentWithInternalErrorReason(zzie.zzv, this.billingClientTransactionId);
    L19:
        zzc.zzn("ProxyBillingActivity", "Activity finished with resultCode " + r7 + " and billing's responseCode: " + r0);
        r3 = r7;
        goto L20
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r13) {
        super.onCreate(r13);
        if (r13 != null) goto L33;
        zzc.zzm("ProxyBillingActivity", "Launching Play Store billing flow");
        this.activityCode = 100;
        if (getIntent().hasExtra("BUY_INTENT") == false) goto L12;
        PendingIntent r0 = (PendingIntent) getIntent().getParcelableExtra("BUY_INTENT");
        if (getIntent().hasExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT") == false) goto L16;
        if (getIntent().getBooleanExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false) == false) goto L16;
        this.isFlowFromFirstPartyClient = true;
        this.activityCode = 110;
    L16:
        if (getIntent().hasExtra("billingClientTransactionId") == false) goto L19;
        this.billingClientTransactionId = getIntent().getLongExtra("billingClientTransactionId", 0);
    L19:
        if (getIntent().hasExtra("wasServiceAutoReconnected") == false) goto L43;
        this.wasServiceAutoReconnected = getIntent().getBooleanExtra("wasServiceAutoReconnected", false);
    L43:
        this.sendCancelledBroadcastIfFinished = true;     // Catch: IntentSender.SendIntentException -> L23
        startIntentSenderForResult(r0.getIntentSender(), this.activityCode, new Intent(), 0, 0, 0);     // Catch: IntentSender.SendIntentException -> L23
        return;
    L23:
        e = move-exception;
        zzc.zzo("ProxyBillingActivity", "Got exception while trying to start a purchase flow.", e);
        ResultReceiver r02 = this.inAppMessageResultReceiver;
        if (r02 == null) goto L27;
        r02.send(0, null);
    L31:
        this.sendCancelledBroadcastIfFinished = false;
        finish();
        return;
    L27:
        Intent r03 = makePurchaseUpdatedIntentWithInternalErrorReason(zzie.zzbG, this.billingClientTransactionId);
        if (this.isFlowFromFirstPartyClient == false) goto L30;
        r03.putExtra("IS_FIRST_PARTY_PURCHASE", true);
    L30:
        sendBroadcast(r03);
        goto L31
    L12:
        if (getIntent().hasExtra("IN_APP_MESSAGE_INTENT") == false) goto L14;
        r0 = (PendingIntent) getIntent().getParcelableExtra("IN_APP_MESSAGE_INTENT");
        this.inAppMessageResultReceiver = (ResultReceiver) getIntent().getParcelableExtra("in_app_message_result_receiver");
        this.activityCode = 101;
        goto L16
    L14:
        r0 = null;
        goto L16
    L33:
        zzc.zzm("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
        this.sendCancelledBroadcastIfFinished = r13.getBoolean("send_cancelled_broadcast_if_finished", false);
        if (r13.containsKey("in_app_message_result_receiver") == false) goto L36;
        this.inAppMessageResultReceiver = (ResultReceiver) r13.getParcelable("in_app_message_result_receiver");
    L36:
        this.isFlowFromFirstPartyClient = r13.getBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false);
        this.activityCode = r13.getInt("activity_code", 100);
        if (r13.containsKey("billingClientTransactionId") == false) goto L40;
        this.billingClientTransactionId = r13.getLong("billingClientTransactionId");
    L40:
        if (r13.containsKey("wasServiceAutoReconnected") == false) goto L45;
        this.wasServiceAutoReconnected = r13.getBoolean("wasServiceAutoReconnected");
        return;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing() == true) goto L6;
        return;
    L6:
        if (this.sendCancelledBroadcastIfFinished == false) goto L17;
        Intent r0 = makePurchasesUpdatedIntent();
        r0.putExtra("RESPONSE_CODE", 1);
        r0.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
        if (this.isFlowFromFirstPartyClient == false) goto L10;
        r0.putExtra("IS_FIRST_PARTY_PURCHASE", true);
    L10:
        int r1 = this.activityCode;
        if (r1 != 110) goto L13;
    L14:
        r0.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
        r0.putExtra("billingClientTransactionId", this.billingClientTransactionId);
    L15:
        sendBroadcast(r0);
        return;
    L13:
        if (r1 != 100) goto L15;
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle r4) {
        super.onSaveInstanceState(r4);
        ResultReceiver r0 = this.inAppMessageResultReceiver;
        if (r0 == null) goto L5;
        r4.putParcelable("in_app_message_result_receiver", r0);
    L5:
        r4.putBoolean("send_cancelled_broadcast_if_finished", this.sendCancelledBroadcastIfFinished);
        r4.putBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", this.isFlowFromFirstPartyClient);
        r4.putInt("activity_code", this.activityCode);
        r4.putLong("billingClientTransactionId", this.billingClientTransactionId);
        r4.putBoolean("wasServiceAutoReconnected", this.wasServiceAutoReconnected);
    }
}
