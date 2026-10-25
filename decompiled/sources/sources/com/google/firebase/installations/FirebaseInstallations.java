package com.google.firebase.installations;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Lazy;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.internal.FidListener;
import com.google.firebase.installations.internal.FidListenerHandle;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class FirebaseInstallations implements FirebaseInstallationsApi {
    private static final ThreadFactory THREAD_FACTORY = null;
    private static final Object lockGenerateFid = null;
    private final ExecutorService backgroundExecutor;
    private String cachedFid;
    private final RandomFidGenerator fidGenerator;
    private Set<FidListener> fidListeners;
    private final FirebaseApp firebaseApp;
    private final Lazy<IidStore> iidStore;
    private final List<StateListener> listeners;
    private final Object lock;
    private final Executor networkExecutor;
    private final PersistedInstallation persistedInstallation;
    private final FirebaseInstallationServiceClient serviceClient;
    private final Utils utils;

    /* renamed from: com.google.firebase.installations.FirebaseInstallations$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        private final AtomicInteger mCount;

        public AnonymousClass1() {
            this.mCount = new AtomicInteger(1);
        }

        @Override // java.util.concurrent.ThreadFactory
        @SuppressLint({"ThreadPoolCreation"})
        public Thread newThread(Runnable r3) {
            return new Thread(r3, String.format("firebase-installations-executor-%d", new Object[]{Integer.valueOf(this.mCount.getAndIncrement())}));
        }
    }

    /* renamed from: com.google.firebase.installations.FirebaseInstallations$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 implements FidListenerHandle {
        final /* synthetic */ FirebaseInstallations this$0;
        final /* synthetic */ FidListener val$listener;

        public AnonymousClass2(FirebaseInstallations r1, FidListener r2) {
            this.this$0 = r1;
            this.val$listener = r2;
        }
    }

    /* renamed from: com.google.firebase.installations.FirebaseInstallations$3, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode = null;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode = null;

        static {
            int[] r0 = new int[TokenResult.ResponseCode.values().length];
            $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode = r0;
            r0[TokenResult.ResponseCode.OK.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
        L16:
            $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode[TokenResult.ResponseCode.BAD_CONFIG.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
        L18:
            $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode[TokenResult.ResponseCode.AUTH_ERROR.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
        L7:
            int[] r2 = new int[InstallationResponse.ResponseCode.values().length];
            $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode = r2;
            r2[InstallationResponse.ResponseCode.OK.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L24:
            $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode[InstallationResponse.ResponseCode.BAD_CONFIG.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$OSO9eK_Xa1c8M9IT8c3BJLq2_VU(FirebaseInstallations r0, boolean r1) {
        r0.doRegistrationOrRefresh(r1);
    }

    public static /* synthetic */ void $r8$lambda$_LVWvNUQ4Iq8r5kiH7vh8J1nAu0(FirebaseInstallations r0, boolean r1) {
        r0.doNetworkCallIfNecessary(r1);
    }

    public static /* synthetic */ void $r8$lambda$jX0pccJHLZRoBEZDXzgxla0Cc58(FirebaseInstallations r1) {
        r1.doRegistrationOrRefresh(false);
    }

    public static /* synthetic */ IidStore $r8$lambda$z0Ybp6xAOnLAhXE51EMdjIBTAfA(FirebaseApp r1) {
        return new IidStore(r1);
    }

    static {
        lockGenerateFid = new Object();
        THREAD_FACTORY = new AnonymousClass1();
    }

    @SuppressLint({"ThreadPoolCreation"})
    public FirebaseInstallations(FirebaseApp r10, Provider<HeartBeatController> r11, ExecutorService r12, Executor r13) {
        this(r12, r13, r10, new FirebaseInstallationServiceClient(r10.getApplicationContext(), r11), new PersistedInstallation(r10), Utils.getInstance(), new Lazy(new FirebaseInstallations$$ExternalSyntheticLambda0(r10)), new RandomFidGenerator());
    }

    @SuppressLint({"ThreadPoolCreation"})
    public FirebaseInstallations(ExecutorService r2, Executor r3, FirebaseApp r4, FirebaseInstallationServiceClient r5, PersistedInstallation r6, Utils r7, Lazy<IidStore> r8, RandomFidGenerator r9) {
        this.lock = new Object();
        this.fidListeners = new HashSet();
        this.listeners = new ArrayList();
        this.firebaseApp = r4;
        this.serviceClient = r5;
        this.persistedInstallation = r6;
        this.utils = r7;
        this.iidStore = r8;
        this.fidGenerator = r9;
        this.backgroundExecutor = r2;
        this.networkExecutor = r3;
    }

    private Task<InstallationTokenResult> addGetAuthTokenListener() {
        TaskCompletionSource r0 = new TaskCompletionSource();
        addStateListeners(new GetAuthTokenListener(this.utils, r0));
        return r0.getTask();
    }

    private Task<String> addGetIdListener() {
        TaskCompletionSource r0 = new TaskCompletionSource();
        addStateListeners(new GetIdListener(r0));
        return r0.getTask();
    }

    private void addStateListeners(StateListener r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.listeners.add(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private synchronized void checkAndUpdateFidListeners(PersistedInstallationEntry r3, PersistedInstallationEntry r4) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (shouldNotifyFidListener(r3, r4) == false) goto L12;
        Iterator<FidListener> r32 = this.fidListeners.iterator();     // Catch: Throwable -> L10
    L6:
        if (r32.hasNext() == false) goto L12;
        r32.next().onFidChanged(r4.getFirebaseInstallationId());     // Catch: Throwable -> L10
    L12:
        monitor-exit(this);
    }

    private void doNetworkCallIfNecessary(boolean r3) {
        PersistedInstallationEntry r0 = getMultiProcessSafePrefs();
    L13:
        e = move-exception;
        triggerOnException(e);
        return;
    L4:
        if (r0.isErrored() == false) goto L6;
    L16:
        PersistedInstallationEntry r32 = registerFidWithServer(r0);     // Catch: FirebaseInstallationsException -> L13
    L17:
        insertOrUpdatePrefs(r32);
        checkAndUpdateFidListeners(r0, r32);
        if (r32.isRegistered() == false) goto L21;
        updateCacheFid(r32.getFirebaseInstallationId());
    L21:
        if (r32.isErrored() == false) goto L25;
        triggerOnException(new FirebaseInstallationsException(FirebaseInstallationsException.Status.BAD_CONFIG));
        return;
    L25:
        if (r32.isNotGenerated() == false) goto L28;
        triggerOnException(new IOException("Installation ID could not be validated with the Firebase servers (maybe it was deleted). Firebase Installations will need to create a new Installation ID and auth token. Please retry your last request."));
        return;
    L28:
        triggerOnStateReached(r32);
        return;
    L6:
        if (r0.isUnregistered() == true) goto L16;
        if (r3 == false) goto L10;
    L15:
        r32 = fetchAuthTokenFromServer(r0);     // Catch: FirebaseInstallationsException -> L13
        goto L17
    L10:
        if (this.utils.isAuthTokenExpired(r0) == true) goto L15;
    }

    private final void doRegistrationOrRefresh(boolean r3) {
        PersistedInstallationEntry r0 = getPrefsWithGeneratedIdMultiProcessSafe();
        if (r3 == false) goto L5;
        r0 = r0.withClearedAuthToken();
    L5:
        triggerOnStateReached(r0);
        this.networkExecutor.execute(new FirebaseInstallations$$ExternalSyntheticLambda3(this, r3));
    }

    private PersistedInstallationEntry fetchAuthTokenFromServer(PersistedInstallationEntry r7) {
        TokenResult r0 = this.serviceClient.generateAuthToken(getApiKey(), r7.getFirebaseInstallationId(), getProjectIdentifier(), r7.getRefreshToken());
        int r1 = AnonymousClass3.$SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode[r0.getResponseCode().ordinal()];
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 != 3) goto L11;
        updateCacheFid(null);
        return r7.withNoGeneratedFid();
    L11:
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    L13:
        return r7.withFisError("BAD CONFIG");
    L15:
        return r7.withAuthToken(r0.getToken(), r0.getTokenExpirationTimestamp(), this.utils.currentTimeInSecs());
    }

    private synchronized String getCacheFid() {
        monitor-enter(this);
        String r0 = this.cachedFid;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    private IidStore getIidStore() {
        return this.iidStore.get();
    }

    public static FirebaseInstallations getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseInstallations getInstance(FirebaseApp r2) {
        if (r2 == null) goto L4;
        boolean r0 = true;
    L5:
        Preconditions.checkArgument(r0, "Null is not a valid value of FirebaseApp.");
        return (FirebaseInstallations) r2.get(FirebaseInstallationsApi.class);
    L4:
        r0 = false;
        goto L5
    }

    private PersistedInstallationEntry getMultiProcessSafePrefs() {
        Object r0 = lockGenerateFid;
        monitor-enter(r0);
        CrossProcessLock r1 = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), "generatefid.lock");     // Catch: Throwable -> L8
        PersistedInstallationEntry r3 = this.persistedInstallation.readPersistedInstallationEntryValue();     // Catch: Throwable -> L12
        if (r1 == null) goto L10;
        r1.releaseAndClose();     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r3;
    L12:
        th = move-exception;
        if (r1 == null) goto L15;
        r1.releaseAndClose();     // Catch: Throwable -> L8
    L15:
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    private PersistedInstallationEntry getPrefsWithGeneratedIdMultiProcessSafe() {
        Object r0 = lockGenerateFid;
        monitor-enter(r0);
        CrossProcessLock r1 = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), "generatefid.lock");     // Catch: Throwable -> L13
        PersistedInstallationEntry r2 = this.persistedInstallation.readPersistedInstallationEntryValue();     // Catch: Throwable -> L9
        if (r2.isNotGenerated() == false) goto L11;
        String r3 = readExistingIidOrCreateFid(r2);     // Catch: Throwable -> L9
        r2 = this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(r2.withUnregisteredFid(r3));     // Catch: Throwable -> L9
    L11:
        if (r1 == null) goto L15;
        r1.releaseAndClose();     // Catch: Throwable -> L13
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L13
        return r2;
    L9:
        th = move-exception;
        if (r1 == null) goto L19;
        r1.releaseAndClose();     // Catch: Throwable -> L13
    L19:
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    }

    private void insertOrUpdatePrefs(PersistedInstallationEntry r4) {
        Object r0 = lockGenerateFid;
        monitor-enter(r0);
        CrossProcessLock r1 = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), "generatefid.lock");     // Catch: Throwable -> L8
        this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(r4);     // Catch: Throwable -> L12
        if (r1 == null) goto L10;
        r1.releaseAndClose();     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L12:
        th = move-exception;
        if (r1 == null) goto L15;
        r1.releaseAndClose();     // Catch: Throwable -> L8
    L15:
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    private void preConditionChecks() {
        Preconditions.checkNotEmpty(getApplicationId(), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkNotEmpty(getProjectIdentifier(), "Please set your Project ID. A valid Firebase Project ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkNotEmpty(getApiKey(), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(Utils.isValidAppIdFormat(getApplicationId()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(Utils.isValidApiKeyFormat(getApiKey()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    private String readExistingIidOrCreateFid(PersistedInstallationEntry r3) {
        if (this.firebaseApp.getName().equals("CHIME_ANDROID_SDK") == true) goto L7;
        if (this.firebaseApp.isDefaultApp() == true) goto L7;
    L9:
        return this.fidGenerator.createRandomFid();
    L7:
        if (r3.shouldAttemptMigration() == false) goto L9;
        String r32 = getIidStore().readIid();
        if (TextUtils.isEmpty(r32) == true) goto L13;
        return r32;
    L13:
        return this.fidGenerator.createRandomFid();
    }

    private PersistedInstallationEntry registerFidWithServer(PersistedInstallationEntry r10) {
        if (r10.getFirebaseInstallationId() != null) goto L5;
    L8:
        String r0 = null;
    L7:
        String r6 = r0;
        InstallationResponse r02 = this.serviceClient.createFirebaseInstallation(getApiKey(), r10.getFirebaseInstallationId(), getProjectIdentifier(), getApplicationId(), r6);
        int r1 = AnonymousClass3.$SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode[r02.getResponseCode().ordinal()];
        if (r1 == 1) goto L18;
        if (r1 != 2) goto L16;
        return r10.withFisError("BAD CONFIG");
    L16:
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    L18:
        return r10.withRegisteredFid(r02.getFid(), r02.getRefreshToken(), this.utils.currentTimeInSecs(), r02.getAuthToken().getToken(), r02.getAuthToken().getTokenExpirationTimestamp());
    L5:
        if (r10.getFirebaseInstallationId().length() != 11) goto L8;
        r0 = getIidStore().readToken();
        goto L7
    }

    private boolean shouldNotifyFidListener(PersistedInstallationEntry r1, PersistedInstallationEntry r2) {
        if (r2.isRegistered() == true) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(r2.getFirebaseInstallationId()) == false) goto L7;
        return false;
    L7:
        if (TextUtils.equals(r1.getFirebaseInstallationId(), r2.getFirebaseInstallationId()) == true) goto L10;
        return true;
    L10:
        return !r1.isRegistered();
    }

    private void triggerOnException(Exception r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Iterator<StateListener> r2 = this.listeners.iterator();     // Catch: Throwable -> L10
    L6:
        if (r2.hasNext() == false) goto L12;
        if (r2.next().onException(r3) == false) goto L6;
        r2.remove();     // Catch: Throwable -> L10
        goto L6
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    private void triggerOnStateReached(PersistedInstallationEntry r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Iterator<StateListener> r2 = this.listeners.iterator();     // Catch: Throwable -> L10
    L6:
        if (r2.hasNext() == false) goto L12;
        if (r2.next().onStateReached(r3) == false) goto L6;
        r2.remove();     // Catch: Throwable -> L10
        goto L6
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    private synchronized void updateCacheFid(String r1) {
        monitor-enter(this);
        this.cachedFid = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public void clearFidCache() {
        this.persistedInstallation.clearDataFile();
    }

    public String getApiKey() {
        return this.firebaseApp.getOptions().getApiKey();
    }

    public String getApplicationId() {
        return this.firebaseApp.getOptions().getApplicationId();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<String> getId() {
        preConditionChecks();
        String r0 = getCacheFid();
        if (r0 != null) goto L5;
        Task<String> r02 = addGetIdListener();
        this.backgroundExecutor.execute(new FirebaseInstallations$$ExternalSyntheticLambda1(this));
        return r02;
    L5:
        return Tasks.forResult(r0);
    }

    public String getProjectIdentifier() {
        return this.firebaseApp.getOptions().getProjectId();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<InstallationTokenResult> getToken(boolean r4) {
        preConditionChecks();
        Task<InstallationTokenResult> r0 = addGetAuthTokenListener();
        this.backgroundExecutor.execute(new FirebaseInstallations$$ExternalSyntheticLambda2(this, r4));
        return r0;
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public synchronized FidListenerHandle registerFidListener(FidListener r2) {
        monitor-enter(this);
        this.fidListeners.add(r2);     // Catch: Throwable -> L6
        AnonymousClass2 r0 = new AnonymousClass2(this, r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }
}
