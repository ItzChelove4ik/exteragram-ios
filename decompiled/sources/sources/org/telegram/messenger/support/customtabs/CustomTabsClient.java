package org.telegram.messenger.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import org.telegram.messenger.support.customtabs.ICustomTabsCallback;

/* loaded from: classes.dex */
public abstract class CustomTabsClient {
    private final ICustomTabsService mService;
    private final ComponentName mServiceComponentName;

    /* renamed from: org.telegram.messenger.support.customtabs.CustomTabsClient$2, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass2 extends ICustomTabsCallback.Stub {
        private Handler mHandler;
        final /* synthetic */ CustomTabsClient this$0;
        final /* synthetic */ CustomTabsCallback val$callback;

        /* renamed from: org.telegram.messenger.support.customtabs.CustomTabsClient$2$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ Bundle val$extras;
            final /* synthetic */ int val$navigationEvent;

            public AnonymousClass1(AnonymousClass2 r1, int r2, Bundle r3) {
                this.this$1 = r1;
                this.val$navigationEvent = r2;
                this.val$extras = r3;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.val$callback.onNavigationEvent(this.val$navigationEvent, this.val$extras);
            }
        }

        /* renamed from: org.telegram.messenger.support.customtabs.CustomTabsClient$2$2, reason: invalid class name and collision with other inner class name */
        public class RunnableC00332 implements Runnable {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ Bundle val$args;
            final /* synthetic */ String val$callbackName;

            public RunnableC00332(AnonymousClass2 r1, String r2, Bundle r3) {
                this.this$1 = r1;
                this.val$callbackName = r2;
                this.val$args = r3;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.val$callback.extraCallback(this.val$callbackName, this.val$args);
            }
        }

        /* renamed from: org.telegram.messenger.support.customtabs.CustomTabsClient$2$3, reason: invalid class name */
        public class AnonymousClass3 implements Runnable {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ Bundle val$extras;

            public AnonymousClass3(AnonymousClass2 r1, Bundle r2) {
                this.this$1 = r1;
                this.val$extras = r2;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.val$callback.onMessageChannelReady(this.val$extras);
            }
        }

        /* renamed from: org.telegram.messenger.support.customtabs.CustomTabsClient$2$4, reason: invalid class name */
        public class AnonymousClass4 implements Runnable {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ Bundle val$extras;
            final /* synthetic */ String val$message;

            public AnonymousClass4(AnonymousClass2 r1, String r2, Bundle r3) {
                this.this$1 = r1;
                this.val$message = r2;
                this.val$extras = r3;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.val$callback.onPostMessage(this.val$message, this.val$extras);
            }
        }

        public AnonymousClass2(CustomTabsClient r1, CustomTabsCallback r2) {
            this.this$0 = r1;
            this.val$callback = r2;
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        @Override // org.telegram.messenger.support.customtabs.ICustomTabsCallback
        public void extraCallback(String r3, Bundle r4) {
            if (this.val$callback != null) goto L5;
            return;
        L5:
            this.mHandler.post(new RunnableC00332(this, r3, r4));
        }

        @Override // org.telegram.messenger.support.customtabs.ICustomTabsCallback
        public void onMessageChannelReady(Bundle r3) {
            if (this.val$callback != null) goto L5;
            return;
        L5:
            this.mHandler.post(new AnonymousClass3(this, r3));
        }

        @Override // org.telegram.messenger.support.customtabs.ICustomTabsCallback
        public void onNavigationEvent(int r3, Bundle r4) {
            if (this.val$callback != null) goto L5;
            return;
        L5:
            this.mHandler.post(new AnonymousClass1(this, r3, r4));
        }

        @Override // org.telegram.messenger.support.customtabs.ICustomTabsCallback
        public void onPostMessage(String r3, Bundle r4) {
            if (this.val$callback != null) goto L5;
            return;
        L5:
            this.mHandler.post(new AnonymousClass4(this, r3, r4));
        }
    }

    public CustomTabsClient(ICustomTabsService r1, ComponentName r2) {
        this.mService = r1;
        this.mServiceComponentName = r2;
    }

    public static boolean bindCustomTabsService(Context r2, String r3, CustomTabsServiceConnection r4) {
        Intent r0 = new Intent("android.support.customtabs.action.CustomTabsService");
        if (TextUtils.isEmpty(r3) == true) goto L6;
        r0.setPackage(r3);
    L6:
        return r2.bindService(r0, r4, 33);
    }

    public CustomTabsSession newSession(CustomTabsCallback r3) {
        AnonymousClass2 r0 = new AnonymousClass2(this, r3);
        if (this.mService.newSession(r0) == true) goto L7;
        return null;
    L7:
        return new CustomTabsSession(this.mService, r0, this.mServiceComponentName);
    L11:
        return null;
    }

    public boolean warmup(long r1) {
        return this.mService.warmup(r1);
    L4:
        return false;
    }
}
