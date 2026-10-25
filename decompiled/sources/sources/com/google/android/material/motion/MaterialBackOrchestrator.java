package com.google.android.material.motion;

import android.os.Build;
import android.view.View;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.BackEventCompat;
import java.util.Objects;
import kotlin.time.DurationKt;

/* loaded from: classes5.dex */
public final class MaterialBackOrchestrator {
    private final BackCallbackDelegate backCallbackDelegate;
    private final MaterialBackHandler backHandler;
    private final View view;

    /* renamed from: com.google.android.material.motion.MaterialBackOrchestrator$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Api33BackCallbackDelegate implements BackCallbackDelegate {
        private OnBackInvokedCallback onBackInvokedCallback;

        private Api33BackCallbackDelegate() {
        }

        public /* synthetic */ Api33BackCallbackDelegate(AnonymousClass1 r1) {
            this();
        }

        public OnBackInvokedCallback createOnBackInvokedCallback(MaterialBackHandler r1) {
            Objects.requireNonNull(r1);
            return new MaterialBackOrchestrator$Api33BackCallbackDelegate$$ExternalSyntheticLambda0(r1);
        }

        public boolean isListeningForBackCallbacks() {
            if (this.onBackInvokedCallback == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // com.google.android.material.motion.MaterialBackOrchestrator.BackCallbackDelegate
        public void startListeningForBackCallbacks(MaterialBackHandler r2, View r3, boolean r4) {
            if (this.onBackInvokedCallback != null) goto L14;
            OnBackInvokedDispatcher r32 = r3.findOnBackInvokedDispatcher();
            if (r32 != null) goto L8;
            return;
        L8:
            OnBackInvokedCallback r22 = createOnBackInvokedCallback(r2);
            this.onBackInvokedCallback = r22;
            if (r4 == false) goto L11;
            int r1 = DurationKt.NANOS_IN_MILLIS;
        L12:
            r32.registerOnBackInvokedCallback(r1, r22);
            return;
        L11:
            r1 = 0;
            goto L12
        }

        @Override // com.google.android.material.motion.MaterialBackOrchestrator.BackCallbackDelegate
        public void stopListeningForBackCallbacks(View r2) {
            if (this.onBackInvokedCallback == null) goto L10;
            OnBackInvokedDispatcher r22 = r2.findOnBackInvokedDispatcher();
            if (r22 != null) goto L8;
            return;
        L8:
            r22.unregisterOnBackInvokedCallback(this.onBackInvokedCallback);
            this.onBackInvokedCallback = null;
            return;
        }
    }

    public static class Api34BackCallbackDelegate extends Api33BackCallbackDelegate {

        /* renamed from: com.google.android.material.motion.MaterialBackOrchestrator$Api34BackCallbackDelegate$1, reason: invalid class name */
        public class AnonymousClass1 implements OnBackAnimationCallback {
            final /* synthetic */ Api34BackCallbackDelegate this$0;
            final /* synthetic */ MaterialBackHandler val$backHandler;

            public AnonymousClass1(Api34BackCallbackDelegate r1, MaterialBackHandler r2) {
                this.this$0 = r1;
                this.val$backHandler = r2;
            }

            public void onBackCancelled() {
                if (this.this$0.isListeningForBackCallbacks() == true) goto L5;
                return;
            L5:
                this.val$backHandler.cancelBackProgress();
            }

            public void onBackInvoked() {
                this.val$backHandler.handleBackInvoked();
            }

            public void onBackProgressed(BackEvent r2) {
                if (this.this$0.isListeningForBackCallbacks() == true) goto L5;
                return;
            L5:
                this.val$backHandler.updateBackProgress(new BackEventCompat(r2));
            }

            public void onBackStarted(BackEvent r2) {
                if (this.this$0.isListeningForBackCallbacks() == true) goto L5;
                return;
            L5:
                this.val$backHandler.startBackProgress(new BackEventCompat(r2));
            }
        }

        private Api34BackCallbackDelegate() {
            super(null);
        }

        public /* synthetic */ Api34BackCallbackDelegate(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.android.material.motion.MaterialBackOrchestrator.Api33BackCallbackDelegate
        public OnBackInvokedCallback createOnBackInvokedCallback(MaterialBackHandler r2) {
            return new AnonymousClass1(this, r2);
        }
    }

    public interface BackCallbackDelegate {
        void startListeningForBackCallbacks(MaterialBackHandler r1, View r2, boolean r3);

        void stopListeningForBackCallbacks(View r1);
    }

    public <T extends View & MaterialBackHandler> MaterialBackOrchestrator(T r2) {
        this(r2, r2);
    }

    public MaterialBackOrchestrator(MaterialBackHandler r2, View r3) {
        this.backCallbackDelegate = createBackCallbackDelegate();
        this.backHandler = r2;
        this.view = r3;
    }

    private static BackCallbackDelegate createBackCallbackDelegate() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 34) goto L7;
        return new Api34BackCallbackDelegate(null);
    L7:
        if (r0 >= 33) goto L9;
        return null;
    L9:
        return new Api33BackCallbackDelegate(null);
    }

    private void startListeningForBackCallbacks(boolean r3) {
        BackCallbackDelegate r0 = this.backCallbackDelegate;
        if (r0 == null) goto L6;
        r0.startListeningForBackCallbacks(this.backHandler, this.view, r3);
        return;
    }

    public boolean shouldListenForBackCallbacks() {
        if (this.backCallbackDelegate == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void startListeningForBackCallbacks() {
        startListeningForBackCallbacks(false);
    }

    public void startListeningForBackCallbacksWithPriorityOverlay() {
        startListeningForBackCallbacks(true);
    }

    public void stopListeningForBackCallbacks() {
        BackCallbackDelegate r0 = this.backCallbackDelegate;
        if (r0 == null) goto L6;
        r0.stopListeningForBackCallbacks(this.view);
        return;
    }
}
