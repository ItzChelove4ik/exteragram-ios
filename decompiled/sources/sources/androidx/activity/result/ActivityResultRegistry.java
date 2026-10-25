package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.work.WorkerFactory$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.random.Random;

/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    final transient Map<String, CallbackAndContract<?>> mKeyToCallback;
    private final Map<String, LifecycleContainer> mKeyToLifecycleContainers;
    final Map<String, Integer> mKeyToRc;
    ArrayList<String> mLaunchedKeys;
    final Map<String, Object> mParsedPendingResults;
    final Bundle mPendingResults;
    private final Map<Integer, String> mRcToKey;

    /* renamed from: androidx.activity.result.ActivityResultRegistry$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 implements LifecycleEventObserver {
        final /* synthetic */ ActivityResultRegistry this$0;
        final /* synthetic */ ActivityResultCallback val$callback;
        final /* synthetic */ ActivityResultContract val$contract;
        final /* synthetic */ String val$key;

        public AnonymousClass1(ActivityResultRegistry r1, String r2, ActivityResultCallback r3, ActivityResultContract r4) {
            this.this$0 = r1;
            this.val$key = r2;
            this.val$callback = r3;
            this.val$contract = r4;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r4, Lifecycle.Event r5) {
            if (Lifecycle.Event.ON_START.equals(r5) == false) goto L12;
            this.this$0.mKeyToCallback.put(this.val$key, new CallbackAndContract(this.val$callback, this.val$contract));
            if (this.this$0.mParsedPendingResults.containsKey(this.val$key) == false) goto L7;
            Object r42 = this.this$0.mParsedPendingResults.get(this.val$key);
            this.this$0.mParsedPendingResults.remove(this.val$key);
            this.val$callback.onActivityResult(r42);
        L7:
            ActivityResult r43 = (ActivityResult) this.this$0.mPendingResults.getParcelable(this.val$key);
            if (r43 == null) goto L19;
            this.this$0.mPendingResults.remove(this.val$key);
            this.val$callback.onActivityResult(this.val$contract.parseResult(r43.getResultCode(), r43.getData()));
            return;
        L19:
            return;
        L12:
            if (Lifecycle.Event.ON_STOP.equals(r5) == false) goto L16;
            this.this$0.mKeyToCallback.remove(this.val$key);
            return;
        L16:
            if (Lifecycle.Event.ON_DESTROY.equals(r5) == false) goto L20;
            this.this$0.unregister(this.val$key);
            return;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* renamed from: androidx.activity.result.ActivityResultRegistry$2, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass2<I> extends ActivityResultLauncher<I> {
        final /* synthetic */ ActivityResultRegistry this$0;
        final /* synthetic */ ActivityResultContract val$contract;
        final /* synthetic */ String val$key;

        public AnonymousClass2(ActivityResultRegistry r1, String r2, ActivityResultContract r3) {
            this.this$0 = r1;
            this.val$key = r2;
            this.val$contract = r3;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I r4, ActivityOptionsCompat r5) {
            Integer r0 = this.this$0.mKeyToRc.get(this.val$key);
            if (r0 == null) goto L10;
            this.this$0.mLaunchedKeys.add(this.val$key);
            this.this$0.onLaunch(r0.intValue(), this.val$contract, r4, r5);     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            this.this$0.mLaunchedKeys.remove(this.val$key);
            throw e;
        L10:
            WorkerFactory$$ExternalSyntheticBUOutline0.m("Attempting to launch an unregistered ActivityResultLauncher with contract ", this.val$contract, " and input ", r4, ". You must ensure the ActivityResultLauncher is registered before calling launch().");
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            this.this$0.unregister(this.val$key);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* renamed from: androidx.activity.result.ActivityResultRegistry$3, reason: invalid class name */
    public class AnonymousClass3<I> extends ActivityResultLauncher<I> {
        final /* synthetic */ ActivityResultRegistry this$0;
        final /* synthetic */ ActivityResultContract val$contract;
        final /* synthetic */ String val$key;

        public AnonymousClass3(ActivityResultRegistry r1, String r2, ActivityResultContract r3) {
            this.this$0 = r1;
            this.val$key = r2;
            this.val$contract = r3;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I r4, ActivityOptionsCompat r5) {
            Integer r0 = this.this$0.mKeyToRc.get(this.val$key);
            if (r0 == null) goto L10;
            this.this$0.mLaunchedKeys.add(this.val$key);
            this.this$0.onLaunch(r0.intValue(), this.val$contract, r4, r5);     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            this.this$0.mLaunchedKeys.remove(this.val$key);
            throw e;
        L10:
            WorkerFactory$$ExternalSyntheticBUOutline0.m("Attempting to launch an unregistered ActivityResultLauncher with contract ", this.val$contract, " and input ", r4, ". You must ensure the ActivityResultLauncher is registered before calling launch().");
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            this.this$0.unregister(this.val$key);
        }
    }

    public static class CallbackAndContract<O> {
        final ActivityResultCallback<O> mCallback;
        final ActivityResultContract<?, O> mContract;

        public CallbackAndContract(ActivityResultCallback<O> r1, ActivityResultContract<?, O> r2) {
            this.mCallback = r1;
            this.mContract = r2;
        }
    }

    /* loaded from: classes3.dex */
    public static class LifecycleContainer {
        final Lifecycle mLifecycle;
        private final ArrayList<LifecycleEventObserver> mObservers;

        public LifecycleContainer(Lifecycle r1) {
            this.mLifecycle = r1;
            this.mObservers = new ArrayList();
        }

        public void addObserver(LifecycleEventObserver r2) {
            this.mLifecycle.addObserver(r2);
            this.mObservers.add(r2);
        }

        public void clearObservers() {
            ArrayList<LifecycleEventObserver> r0 = this.mObservers;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            LifecycleEventObserver r3 = r0.get(r2);
            r2 = r2 + 1;
            Lifecycle r4 = this.mLifecycle;
            r4.removeObserver(r3);
            goto L3
        L5:
            this.mObservers.clear();
        }
    }

    public ActivityResultRegistry() {
        this.mRcToKey = new HashMap();
        this.mKeyToRc = new HashMap();
        this.mKeyToLifecycleContainers = new HashMap();
        this.mLaunchedKeys = new ArrayList();
        this.mKeyToCallback = new HashMap();
        this.mParsedPendingResults = new HashMap();
        this.mPendingResults = new Bundle();
    }

    private void bindRcKey(int r3, String r4) {
        this.mRcToKey.put(Integer.valueOf(r3), r4);
        this.mKeyToRc.put(r4, Integer.valueOf(r3));
    }

    private <O> void doDispatch(String r2, int r3, Intent r4, CallbackAndContract<O> r5) {
        if (r5 != null) goto L4;
    L9:
        this.mParsedPendingResults.remove(r2);
        this.mPendingResults.putParcelable(r2, new ActivityResult(r3, r4));
        return;
    L4:
        if (r5.mCallback == null) goto L9;
        if (this.mLaunchedKeys.contains(r2) == false) goto L9;
        r5.mCallback.onActivityResult(r5.mContract.parseResult(r3, r4));
        this.mLaunchedKeys.remove(r2);
    }

    private int generateRandomNumber() {
        int r0 = Random.Default.nextInt(2147418112);
    L3:
        int r02 = r0 + 65536;
        if (this.mRcToKey.containsKey(Integer.valueOf(r02)) == false) goto L6;
        r0 = Random.Default.nextInt(2147418112);
        goto L3
    L6:
        return r02;
    }

    private void registerKey(String r2) {
        if (this.mKeyToRc.get(r2) == null) goto L5;
        return;
    L5:
        bindRcKey(generateRandomNumber(), r2);
    }

    public final boolean dispatchResult(int r2, int r3, Intent r4) {
        String r22 = this.mRcToKey.get(Integer.valueOf(r2));
        if (r22 != null) goto L6;
        return false;
    L6:
        doDispatch(r22, r3, r4, this.mKeyToCallback.get(r22));
        return true;
    }

    public final <O> boolean dispatchResult(int r2, @SuppressLint({"UnknownNullness"}) O r3) {
        String r22 = this.mRcToKey.get(Integer.valueOf(r2));
        if (r22 != null) goto L6;
        return false;
    L6:
        CallbackAndContract<?> r0 = this.mKeyToCallback.get(r22);
        if (r0 == null) goto L14;
        ActivityResultCallback<?> r02 = r0.mCallback;
        if (r02 == null) goto L14;
        if (this.mLaunchedKeys.remove(r22) == false) goto L17;
        r02.onActivityResult(r3);
        return true;
    L17:
        return true;
    L14:
        this.mPendingResults.remove(r22);
        this.mParsedPendingResults.put(r22, r3);
        return true;
    }

    public abstract <I, O> void onLaunch(int r1, ActivityResultContract<I, O> r2, @SuppressLint({"UnknownNullness"}) I r3, ActivityOptionsCompat r4);

    public final void onRestoreInstanceState(Bundle r6) {
        if (r6 == null) goto L22;
        ArrayList<Integer> r0 = r6.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> r1 = r6.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (r1 == null) goto L17;
        if (r0 == null) goto L23;
        this.mLaunchedKeys = r6.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.mPendingResults.putAll(r6.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
        int r62 = 0;
    L10:
        if (r62 >= r1.size()) goto L24;
        String r2 = r1.get(r62);
        if (this.mKeyToRc.containsKey(r2) == false) goto L16;
        Integer r3 = this.mKeyToRc.remove(r2);
        if (this.mPendingResults.containsKey(r2) == true) goto L16;
        this.mRcToKey.remove(r3);
    L16:
        bindRcKey(r0.get(r62).intValue(), r1.get(r62));
        r62 = r62 + 1;
        goto L10
    L24:
        return;
    L23:
        return;
    L17:
        return;
    }

    public final void onSaveInstanceState(Bundle r3) {
        r3.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList(this.mKeyToRc.values()));
        r3.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList(this.mKeyToRc.keySet()));
        r3.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList(this.mLaunchedKeys));
        r3.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.mPendingResults.clone());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <I, O> ActivityResultLauncher<I> register(String r3, ActivityResultContract<I, O> r4, ActivityResultCallback<O> r5) {
        registerKey(r3);
        this.mKeyToCallback.put(r3, new CallbackAndContract(r5, r4));
        if (this.mParsedPendingResults.containsKey(r3) == false) goto L5;
        Object r0 = this.mParsedPendingResults.get(r3);
        this.mParsedPendingResults.remove(r3);
        r5.onActivityResult(r0);
    L5:
        ActivityResult r02 = (ActivityResult) this.mPendingResults.getParcelable(r3);
        if (r02 == null) goto L9;
        this.mPendingResults.remove(r3);
        r5.onActivityResult(r4.parseResult(r02.getResultCode(), r02.getData()));
    L9:
        return new AnonymousClass3(this, r3, r4);
    }

    public final <I, O> ActivityResultLauncher<I> register(String r4, LifecycleOwner r5, ActivityResultContract<I, O> r6, ActivityResultCallback<O> r7) {
        Lifecycle r0 = r5.getLifecycle();
        if (r0.getCurrentState().isAtLeast(Lifecycle.State.STARTED) == true) goto L9;
        registerKey(r4);
        LifecycleContainer r52 = this.mKeyToLifecycleContainers.get(r4);
        if (r52 != null) goto L7;
        r52 = new LifecycleContainer(r0);
    L7:
        r52.addObserver(new AnonymousClass1(this, r4, r7, r6));
        this.mKeyToLifecycleContainers.put(r4, r52);
        return new AnonymousClass2(this, r4, r6);
    L9:
        StringBuilder r42 = new StringBuilder("LifecycleOwner ");
        r42.append(r5);
        Lifecycle.State r53 = r0.getCurrentState();
        r42.append(" is attempting to register while current state is ");
        r42.append(r53);
        r42.append(". LifecycleOwners must call register before they are STARTED.");
        throw new IllegalStateException(r42.toString());
    }

    public final void unregister(String r6) {
        if (this.mLaunchedKeys.contains(r6) == true) goto L7;
        Integer r0 = this.mKeyToRc.remove(r6);
        if (r0 == null) goto L7;
        this.mRcToKey.remove(r0);
    L7:
        this.mKeyToCallback.remove(r6);
        if (this.mParsedPendingResults.containsKey(r6) == false) goto L11;
        Log.w("ActivityResultRegistry", "Dropping pending result for request " + r6 + ": " + this.mParsedPendingResults.get(r6));
        this.mParsedPendingResults.remove(r6);
    L11:
        if (this.mPendingResults.containsKey(r6) == false) goto L13;
        Log.w("ActivityResultRegistry", "Dropping pending result for request " + r6 + ": " + this.mPendingResults.getParcelable(r6));
        this.mPendingResults.remove(r6);
    L13:
        LifecycleContainer r02 = this.mKeyToLifecycleContainers.get(r6);
        if (r02 == null) goto L17;
        r02.clearObservers();
        this.mKeyToLifecycleContainers.remove(r6);
        return;
    }
}
