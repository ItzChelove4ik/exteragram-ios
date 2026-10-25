package androidx.core.provider;

import android.graphics.Typeface;
import androidx.core.provider.FontRequestWorker;
import androidx.core.provider.FontsContractCompat;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
class CallbackWrapper {
    private final FontsContractCompat.FontRequestCallback mCallback;
    private final Executor mExecutor;

    /* renamed from: androidx.core.provider.CallbackWrapper$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ CallbackWrapper this$0;
        final /* synthetic */ FontsContractCompat.FontRequestCallback val$callback;
        final /* synthetic */ Typeface val$typeface;

        public AnonymousClass1(CallbackWrapper r1, FontsContractCompat.FontRequestCallback r2, Typeface r3) {
            this.this$0 = r1;
            this.val$callback = r2;
            this.val$typeface = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$callback.onTypefaceRetrieved(this.val$typeface);
        }
    }

    /* renamed from: androidx.core.provider.CallbackWrapper$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ CallbackWrapper this$0;
        final /* synthetic */ FontsContractCompat.FontRequestCallback val$callback;
        final /* synthetic */ int val$reason;

        public AnonymousClass2(CallbackWrapper r1, FontsContractCompat.FontRequestCallback r2, int r3) {
            this.this$0 = r1;
            this.val$callback = r2;
            this.val$reason = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$callback.onTypefaceRequestFailed(this.val$reason);
        }
    }

    public CallbackWrapper(FontsContractCompat.FontRequestCallback r1, Executor r2) {
        this.mCallback = r1;
        this.mExecutor = r2;
    }

    private void onTypefaceRequestFailed(int r4) {
        FontsContractCompat.FontRequestCallback r0 = this.mCallback;
        this.mExecutor.execute(new AnonymousClass2(this, r0, r4));
    }

    private void onTypefaceRetrieved(Typeface r4) {
        FontsContractCompat.FontRequestCallback r0 = this.mCallback;
        this.mExecutor.execute(new AnonymousClass1(this, r0, r4));
    }

    public void onTypefaceResult(FontRequestWorker.TypefaceResult r2) {
        if (r2.isSuccess() == false) goto L6;
        onTypefaceRetrieved(r2.mTypeface);
        return;
    L6:
        onTypefaceRequestFailed(r2.mResult);
    }
}
