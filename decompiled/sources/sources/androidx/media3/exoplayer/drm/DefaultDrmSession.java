package androidx.media3.exoplayer.drm;

import android.annotation.SuppressLint;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.CopyOnWriteMultiset;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
class DefaultDrmSession implements DrmSession {
    private final MediaDrmCallback callback;
    private CryptoConfig cryptoConfig;
    private ExoMediaDrm.KeyRequest currentKeyRequest;
    private ExoMediaDrm.ProvisionRequest currentProvisionRequest;
    private final CopyOnWriteMultiset<DrmSessionEventListener.EventDispatcher> eventDispatchers;
    private final boolean isPlaceholderSession;
    private final HashMap<String, String> keyRequestParameters;
    private DrmSession.DrmSessionException lastException;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final ExoMediaDrm mediaDrm;
    private final int mode;
    private byte[] offlineLicenseKeySetId;
    private final boolean playClearSamplesWithoutKeys;
    private final Looper playbackLooper;
    private final PlayerId playerId;
    private final ProvisioningManager provisioningManager;
    private int referenceCount;
    private final ReferenceCountListener referenceCountListener;
    private RequestHandler requestHandler;
    private HandlerThread requestHandlerThread;
    private final ResponseHandler responseHandler;
    public final List<DrmInitData.SchemeData> schemeDatas;
    private byte[] sessionId;
    private int state;
    private final UUID uuid;

    public interface ProvisioningManager {
        void onProvisionCompleted();

        void onProvisionError(Exception r1, boolean r2);

        void provisionRequired(DefaultDrmSession r1);
    }

    public interface ReferenceCountListener {
        void onReferenceCountDecremented(DefaultDrmSession r1, int r2);

        void onReferenceCountIncremented(DefaultDrmSession r1, int r2);
    }

    @SuppressLint({"HandlerLeak"})
    public class RequestHandler extends Handler {
        private boolean isReleased;
        final /* synthetic */ DefaultDrmSession this$0;

        public RequestHandler(DefaultDrmSession r1, Looper r2) {
            this.this$0 = r1;
            super(r2);
        }

        private boolean maybeRetryRequest(Message r22, MediaDrmCallbackException r23) {
            RequestTask r3 = (RequestTask) r22.obj;
            if (r3.allowRetry == true) goto L5;
            return false;
        L5:
            int r4 = r3.errorCount + 1;
            r3.errorCount = r4;
            if (r4 <= DefaultDrmSession.access$400(this.this$0).getMinimumLoadableRetryCount(3)) goto L8;
            return false;
        L8:
            LoadEventInfo r9 = new LoadEventInfo(r3.taskId, r23.dataSpec, r23.uriAfterRedirects, r23.responseHeaders, SystemClock.elapsedRealtime(), SystemClock.elapsedRealtime() - r3.startTimeMs, r23.bytesLoaded);
            MediaLoadData r5 = new MediaLoadData(3);
            if ((r23.getCause() instanceof IOException) == false) goto L11;
            IOException r2 = (IOException) r23.getCause();
        L12:
            long r24 = DefaultDrmSession.access$400(this.this$0).getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(r9, r5, r2, r3.errorCount));
            if (r24 != (-9223372036854775807L)) goto L15;
            return false;
        L15:
            monitor-enter(this);
        L21:
            th = move-exception;
            throw th;
        L17:
            if (this.isReleased == true) goto L23;
            sendMessageDelayed(Message.obtain(r22), r24);     // Catch: Throwable -> L21
            monitor-exit(this);     // Catch: Throwable -> L21
            return true;
        L23:
            monitor-exit(this);     // Catch: Throwable -> L21
            return false;
        L11:
            r2 = new UnexpectedDrmSessionException(r23.getCause());
            goto L12
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message r6) {
            RequestTask r0 = (RequestTask) r6.obj;
            int r1 = r6.what;     // Catch: Exception -> L8 MediaDrmCallbackException -> L10
            if (r1 != 1) goto L6;
            Throwable r12 = DefaultDrmSession.access$300(this.this$0).executeProvisionRequest(DefaultDrmSession.access$200(this.this$0), (ExoMediaDrm.ProvisionRequest) r0.request);     // Catch: Exception -> L8 MediaDrmCallbackException -> L10
        L20:
            DefaultDrmSession.access$400(this.this$0).onLoadTaskConcluded(r0.taskId);
            monitor-enter(this);
        L25:
            th = move-exception;
            throw th;
        L23:
            if (this.isReleased == true) goto L27;
            DefaultDrmSession.access$500(this.this$0).obtainMessage(r6.what, Pair.create(r0.request, r12)).sendToTarget();     // Catch: Throwable -> L25
        L27:
            monitor-exit(this);     // Catch: Throwable -> L25
            return;
        L6:
            if (r1 != 2) goto L13;
            r12 = DefaultDrmSession.access$300(this.this$0).executeKeyRequest(DefaultDrmSession.access$200(this.this$0), (ExoMediaDrm.KeyRequest) r0.request);     // Catch: Exception -> L8 MediaDrmCallbackException -> L10
            goto L20
        L13:
            throw new RuntimeException();     // Catch: Exception -> L8 MediaDrmCallbackException -> L10
        L10:
            e = move-exception;
            boolean r2 = maybeRetryRequest(r6, e);
            r12 = e;
            if (r2 == false) goto L20;
            return;
        L8:
            e = move-exception;
            Log.w("DefaultDrmSession", "Key/provisioning request produced an unexpected exception. Not retrying.", e);
            r12 = e;
            goto L20
        }

        public void post(int r8, Object r9, boolean r10) {
            obtainMessage(r8, new RequestTask(LoadEventInfo.getNewId(), r10, SystemClock.elapsedRealtime(), r9)).sendToTarget();
        }

        public synchronized void release() {
            monitor-enter(this);
            removeCallbacksAndMessages(null);     // Catch: Throwable -> L7
            this.isReleased = true;     // Catch: Throwable -> L7
            monitor-exit(this);
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    public static final class RequestTask {
        public final boolean allowRetry;
        public int errorCount;
        public final Object request;
        public final long startTimeMs;
        public final long taskId;

        public RequestTask(long r1, boolean r3, long r4, Object r6) {
            this.taskId = r1;
            this.allowRetry = r3;
            this.startTimeMs = r4;
            this.request = r6;
        }
    }

    @SuppressLint({"HandlerLeak"})
    public class ResponseHandler extends Handler {
        final /* synthetic */ DefaultDrmSession this$0;

        public ResponseHandler(DefaultDrmSession r1, Looper r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message r4) {
            Pair r0 = (Pair) r4.obj;
            Object r1 = r0.first;
            Object r02 = r0.second;
            int r42 = r4.what;
            if (r42 != 1) goto L5;
            DefaultDrmSession.access$000(this.this$0, r1, r02);
            return;
        L5:
            if (r42 == 2) goto L7;
            return;
        L7:
            DefaultDrmSession.access$100(this.this$0, r1, r02);
        }
    }

    public static final class UnexpectedDrmSessionException extends IOException {
        public UnexpectedDrmSessionException(Throwable r1) {
            super(r1);
        }
    }

    /* renamed from: $r8$lambda$YNQbSNt_W9dFE-mXt9c2lr9Yrp8, reason: not valid java name */
    public static /* synthetic */ void m827$r8$lambda$YNQbSNt_W9dFEmXt9c2lr9Yrp8(Throwable r0, DrmSessionEventListener.EventDispatcher r1) {
        r1.drmSessionManagerError((Exception) r0);
    }

    public static /* synthetic */ void $r8$lambda$w6gWyQSmnHX0wJBJs3p8PITIN0A(int r0, DrmSessionEventListener.EventDispatcher r1) {
        r1.drmSessionAcquired(r0);
    }

    public DefaultDrmSession(UUID r2, ExoMediaDrm r3, ProvisioningManager r4, ReferenceCountListener r5, List<DrmInitData.SchemeData> r6, int r7, boolean r8, boolean r9, byte[] r10, HashMap<String, String> r11, MediaDrmCallback r12, Looper r13, LoadErrorHandlingPolicy r14, PlayerId r15) {
        if (r7 != 1) goto L5;
    L6:
        Assertions.checkNotNull(r10);
    L7:
        this.uuid = r2;
        this.provisioningManager = r4;
        this.referenceCountListener = r5;
        this.mediaDrm = r3;
        this.mode = r7;
        this.playClearSamplesWithoutKeys = r8;
        this.isPlaceholderSession = r9;
        if (r10 == null) goto L10;
        this.offlineLicenseKeySetId = r10;
        this.schemeDatas = null;
    L11:
        this.keyRequestParameters = r11;
        this.callback = r12;
        this.eventDispatchers = new CopyOnWriteMultiset();
        this.loadErrorHandlingPolicy = r14;
        this.playerId = r15;
        this.state = 2;
        this.playbackLooper = r13;
        this.responseHandler = new ResponseHandler(this, r13);
        return;
    L10:
        this.schemeDatas = Collections.unmodifiableList((List) Assertions.checkNotNull(r6));
        goto L11
    L5:
        if (r7 != 3) goto L7;
        goto L6
    }

    public static /* synthetic */ void access$000(DefaultDrmSession r0, Object r1, Object r2) {
        r0.onProvisionResponse(r1, r2);
    }

    public static /* synthetic */ void access$100(DefaultDrmSession r0, Object r1, Object r2) {
        r0.onKeyResponse(r1, r2);
    }

    public static /* synthetic */ UUID access$200(DefaultDrmSession r0) {
        return r0.uuid;
    }

    public static /* synthetic */ MediaDrmCallback access$300(DefaultDrmSession r0) {
        return r0.callback;
    }

    public static /* synthetic */ LoadErrorHandlingPolicy access$400(DefaultDrmSession r0) {
        return r0.loadErrorHandlingPolicy;
    }

    public static /* synthetic */ ResponseHandler access$500(DefaultDrmSession r0) {
        return r0.responseHandler;
    }

    private void dispatchEvent(Consumer<DrmSessionEventListener.EventDispatcher> r2) {
        Iterator<DrmSessionEventListener.EventDispatcher> r1 = this.eventDispatchers.elementSet().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r2.accept(r1.next());
        goto L4
    }

    @RequiresNonNull({"sessionId"})
    private void doLicense(boolean r9) {
        if (this.isPlaceholderSession == true) goto L43;
        byte[] r0 = (byte[]) Util.castNonNull(this.sessionId);
        int r1 = this.mode;
        if (r1 == 0) goto L21;
        if (r1 == 1) goto L21;
        if (r1 == 2) goto L15;
        if (r1 != 3) goto L44;
        Assertions.checkNotNull(this.offlineLicenseKeySetId);
        Assertions.checkNotNull(this.sessionId);
        postKeyRequest(this.offlineLicenseKeySetId, 3, r9);
        return;
    L44:
        return;
    L15:
        if (this.offlineLicenseKeySetId != null) goto L17;
    L18:
        postKeyRequest(r0, 2, r9);
        return;
    L17:
        if (restoreKeys() == true) goto L18;
        return;
    L21:
        if (this.offlineLicenseKeySetId != null) goto L25;
        postKeyRequest(r0, 1, r9);
        return;
    L25:
        if (this.state != 4) goto L27;
    L30:
        long r4 = getLicenseDurationRemainingSec();
        if (this.mode != 0) goto L37;
        if (r4 > 60) goto L37;
        Log.d("DefaultDrmSession", "Offline license has expired or will expire soon. Remaining seconds: " + r4);
        postKeyRequest(r0, 2, r9);
        return;
    L37:
        if (r4 > 0) goto L40;
        onError(new KeysExpiredException(), 2);
        return;
    L40:
        this.state = 4;
        dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda4());
        return;
    L27:
        if (restoreKeys() == true) goto L30;
        return;
    }

    private long getLicenseDurationRemainingSec() {
        if (C.WIDEVINE_UUID.equals(this.uuid) == true) goto L6;
        return LongCompanionObject.MAX_VALUE;
    L6:
        Pair r4 = (Pair) Assertions.checkNotNull(WidevineUtil.getLicenseDurationRemainingSec(this));
        return Math.min(((Long) r4.first).longValue(), ((Long) r4.second).longValue());
    }

    @EnsuresNonNullIf(expression = {"sessionId"}, result = true)
    private boolean isOpen() {
        int r1 = this.state;
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 == 4) goto L11;
        return false;
    L11:
        return true;
    }

    private void onError(Throwable r2, int r3) {
        this.lastException = new DrmSession.DrmSessionException(r2, DrmUtil.getErrorCodeForMediaDrmException(r2, r3));
        Log.e("DefaultDrmSession", "DRM session error", r2);
        if ((r2 instanceof Exception) == false) goto L6;
        dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda3(r2));
    L15:
        if (this.state == 4) goto L20;
        this.state = 1;
        return;
    L20:
        return;
    L6:
        if ((r2 instanceof Error) == true) goto L8;
        Constants$$ExternalSyntheticBUOutline0.m("Unexpected Throwable subclass", r2);
        return;
    L8:
        if (DrmUtil.isFailureToConstructResourceBusyException(r2) == true) goto L15;
        if (DrmUtil.isFailureToConstructNotProvisionedException(r2) == true) goto L15;
        throw ((Error) r2);
    }

    private void onKeyResponse(Object r3, Object r4) {
        if (r3 == this.currentKeyRequest) goto L5;
        return;
    L5:
        if (isOpen() == false) goto L38;
        this.currentKeyRequest = null;
        if ((r4 instanceof Exception) == false) goto L10;
    L34:
        onKeysError((Throwable) r4, false);
        return;
    L10:
        if ((r4 instanceof NoSuchMethodError) == true) goto L34;
        byte[] r42 = (byte[]) r4;     // Catch: NoSuchMethodError -> L17 Exception -> L19
        int r32 = this.mode;     // Catch: NoSuchMethodError -> L17 Exception -> L19
        ExoMediaDrm r0 = this.mediaDrm;
        if (r32 != 3) goto L21;
        r0.provideKeyResponse((byte[]) Util.castNonNull(this.offlineLicenseKeySetId), r42);     // Catch: NoSuchMethodError -> L17 Exception -> L19
        dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda1());     // Catch: NoSuchMethodError -> L17 Exception -> L19
        return;
    L21:
        byte[] r33 = r0.provideKeyResponse(this.sessionId, r42);     // Catch: NoSuchMethodError -> L17 Exception -> L19
        int r43 = this.mode;     // Catch: NoSuchMethodError -> L17 Exception -> L19
        if (r43 == 2) goto L26;
        if (r43 == 0) goto L25;
    L30:
        this.state = 4;     // Catch: NoSuchMethodError -> L17 Exception -> L19
        dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda2());     // Catch: NoSuchMethodError -> L17 Exception -> L19
        return;
    L25:
        if (this.offlineLicenseKeySetId == null) goto L30;
    L26:
        if (r33 == null) goto L30;
        if (r33.length == 0) goto L30;
        this.offlineLicenseKeySetId = r33;     // Catch: NoSuchMethodError -> L17 Exception -> L19
    L19:
        e = e;
    L32:
        onKeysError(e, true);
        return;
    L17:
        e = e;
        goto L32
    }

    private void onKeysError(Throwable r2, boolean r3) {
        if ((r2 instanceof NotProvisionedException) == false) goto L5;
    L12:
        this.provisioningManager.provisionRequired(this);
        return;
    L5:
        if (DrmUtil.isFailureToConstructNotProvisionedException(r2) == true) goto L12;
        if (r3 == false) goto L9;
        int r32 = 1;
    L10:
        onError(r2, r32);
        return;
    L9:
        r32 = 2;
        goto L10
    }

    private void onKeysRequired() {
        if (this.mode == 0) goto L5;
        return;
    L5:
        if (this.state != 4) goto L9;
        Util.castNonNull(this.sessionId);
        doLicense(false);
        return;
    }

    private void onProvisionResponse(Object r2, Object r3) {
        if (r2 == this.currentProvisionRequest) goto L5;
        return;
    L5:
        if (this.state != 2) goto L7;
    L9:
        this.currentProvisionRequest = null;
        if ((r3 instanceof Exception) == false) goto L19;
        this.provisioningManager.onProvisionError((Exception) r3, false);
        return;
    L19:
        this.mediaDrm.provideProvisionResponse((byte[]) r3);     // Catch: Exception -> L16
        this.provisioningManager.onProvisionCompleted();
        return;
    L16:
        e = move-exception;
        this.provisioningManager.onProvisionError(e, true);
        return;
    L7:
        if (isOpen() == true) goto L9;
    }

    @EnsuresNonNullIf(expression = {"sessionId"}, result = true)
    private boolean openInternal() {
        if (isOpen() == false) goto L17;
        return true;
    L17:
        byte[] r0 = this.mediaDrm.openSession();     // Catch: NoSuchMethodError -> L7 Exception -> L9 NotProvisionedException -> L14
        this.sessionId = r0;     // Catch: NoSuchMethodError -> L7 Exception -> L9 NotProvisionedException -> L14
        this.mediaDrm.setPlayerIdForSession(r0, this.playerId);     // Catch: NoSuchMethodError -> L7 Exception -> L9 NotProvisionedException -> L14
        this.cryptoConfig = this.mediaDrm.createCryptoConfig(this.sessionId);     // Catch: NoSuchMethodError -> L7 Exception -> L9 NotProvisionedException -> L14
        this.state = 3;     // Catch: NoSuchMethodError -> L7 Exception -> L9 NotProvisionedException -> L14
        dispatchEvent(new DefaultDrmSession$$ExternalSyntheticLambda0(3));     // Catch: NoSuchMethodError -> L7 Exception -> L9 NotProvisionedException -> L14
        Assertions.checkNotNull(this.sessionId);     // Catch: NoSuchMethodError -> L7 Exception -> L9 NotProvisionedException -> L14
        return true;
    L14:
        this.provisioningManager.provisionRequired(this);
        return false;
    L9:
        e = e;
    L11:
        if (DrmUtil.isFailureToConstructNotProvisionedException(e) == false) goto L13;
        this.provisioningManager.provisionRequired(this);
        return false;
    L13:
        onError(e, 1);
        return false;
    L7:
        e = e;
        goto L11
    }

    private void postKeyRequest(byte[] r4, int r5, boolean r6) {
        this.currentKeyRequest = this.mediaDrm.getKeyRequest(r4, this.schemeDatas, r5, this.keyRequestParameters);     // Catch: Throwable -> L4 Exception -> L6
        ((RequestHandler) Util.castNonNull(this.requestHandler)).post(2, Assertions.checkNotNull(this.currentKeyRequest), r6);     // Catch: Throwable -> L4 Exception -> L6
        return;
    L4:
        e = move-exception;
        onKeysError(e, true);
    }

    @RequiresNonNull({"sessionId", "offlineLicenseKeySetId"})
    private boolean restoreKeys() {
        this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);     // Catch: NoSuchMethodError -> L5 Throwable -> L7
        return true;
    L7:
        e = move-exception;
        onError(e, 1);
        return false;
    }

    private void verifyPlaybackThread() {
        if (Thread.currentThread() == this.playbackLooper.getThread()) goto L6;
        Log.w("DefaultDrmSession", "DefaultDrmSession accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + this.playbackLooper.getThread().getName(), new IllegalStateException());
        return;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public void acquire(DrmSessionEventListener.EventDispatcher r4) {
        verifyPlaybackThread();
        boolean r1 = false;
        if (this.referenceCount >= 0) goto L5;
        Log.e("DefaultDrmSession", "Session reference count less than zero: " + this.referenceCount);
        this.referenceCount = 0;
    L5:
        if (r4 == null) goto L7;
        this.eventDispatchers.add(r4);
    L7:
        int r0 = this.referenceCount + 1;
        this.referenceCount = r0;
        if (r0 == 1) goto L10;
        if (r4 != null) goto L17;
    L21:
        this.referenceCountListener.onReferenceCountIncremented(this, this.referenceCount);
        return;
    L17:
        if (isOpen() == false) goto L21;
        if (this.eventDispatchers.count(r4) != 1) goto L21;
        r4.drmSessionAcquired(this.state);
        goto L21
    L10:
        if (this.state != 2) goto L12;
        r1 = true;
    L12:
        Assertions.checkState(r1);
        HandlerThread r42 = new HandlerThread("ExoPlayer:DrmRequestHandler");
        this.requestHandlerThread = r42;
        r42.start();
        this.requestHandler = new RequestHandler(this, this.requestHandlerThread.getLooper());
        if (openInternal() == false) goto L21;
        doLicense(true);
        goto L21
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public final CryptoConfig getCryptoConfig() {
        verifyPlaybackThread();
        return this.cryptoConfig;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public final DrmSession.DrmSessionException getError() {
        verifyPlaybackThread();
        if (this.state == 1) goto L5;
        return null;
    L5:
        return this.lastException;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public final UUID getSchemeUuid() {
        verifyPlaybackThread();
        return this.uuid;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public final int getState() {
        verifyPlaybackThread();
        return this.state;
    }

    public boolean hasSessionId(byte[] r1) {
        verifyPlaybackThread();
        return Arrays.equals(this.sessionId, r1);
    }

    public void onMediaDrmEvent(int r2) {
        if (r2 == 2) goto L5;
        return;
    L5:
        onKeysRequired();
    }

    public void onProvisionCompleted() {
        if (openInternal() == false) goto L6;
        doLicense(true);
        return;
    }

    public void onProvisionError(Exception r1, boolean r2) {
        if (r2 == false) goto L4;
        int r22 = 1;
    L5:
        onError(r1, r22);
        return;
    L4:
        r22 = 3;
        goto L5
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public boolean playClearSamplesWithoutKeys() {
        verifyPlaybackThread();
        return this.playClearSamplesWithoutKeys;
    }

    public void provision() {
        this.currentProvisionRequest = this.mediaDrm.getProvisionRequest();
        ((RequestHandler) Util.castNonNull(this.requestHandler)).post(1, Assertions.checkNotNull(this.currentProvisionRequest), true);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public Map<String, String> queryKeyStatus() {
        verifyPlaybackThread();
        byte[] r0 = this.sessionId;
        if (r0 != null) goto L7;
        return null;
    L7:
        return this.mediaDrm.queryKeyStatus(r0);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public void release(DrmSessionEventListener.EventDispatcher r4) {
        verifyPlaybackThread();
        int r0 = this.referenceCount;
        if (r0 > 0) goto L6;
        Log.e("DefaultDrmSession", "release() called on a session that's already fully released.");
        return;
    L6:
        int r02 = r0 - 1;
        this.referenceCount = r02;
        if (r02 != 0) goto L11;
        this.state = 0;
        ((ResponseHandler) Util.castNonNull(this.responseHandler)).removeCallbacksAndMessages(null);
        ((RequestHandler) Util.castNonNull(this.requestHandler)).release();
        this.requestHandler = null;
        ((HandlerThread) Util.castNonNull(this.requestHandlerThread)).quit();
        this.requestHandlerThread = null;
        this.cryptoConfig = null;
        this.lastException = null;
        this.currentKeyRequest = null;
        this.currentProvisionRequest = null;
        byte[] r03 = this.sessionId;
        if (r03 == null) goto L11;
        this.mediaDrm.closeSession(r03);
        this.sessionId = null;
    L11:
        if (r4 == null) goto L15;
        this.eventDispatchers.remove(r4);
        if (this.eventDispatchers.count(r4) != 0) goto L15;
        r4.drmSessionReleased();
    L15:
        this.referenceCountListener.onReferenceCountDecremented(this, this.referenceCount);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSession
    public boolean requiresSecureDecoder(String r2) {
        verifyPlaybackThread();
        return this.mediaDrm.requiresSecureDecoder((byte[]) Assertions.checkStateNotNull(this.sessionId), r2);
    }
}
