package com.google.firebase.messaging;

import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes.dex */
public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda8 implements OnSuccessListener {
    public final /* synthetic */ FirebaseMessaging f$0;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda8(FirebaseMessaging r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r1) {
        FirebaseMessaging.m1963$r8$lambda$yrmi9keLOjePJlj1v0bkHmgXns(this.f$0, (CloudMessage) r1);
    }
}
