package androidx.media3.exoplayer.drm;

import android.annotation.SuppressLint;
import android.media.ResourceBusyException;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DefaultDrmSession;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes4.dex */
public class DefaultDrmSessionManager implements DrmSessionManager {
    private final MediaDrmCallback callback;
    private ExoMediaDrm exoMediaDrm;
    private final ExoMediaDrm.Provider exoMediaDrmProvider;
    private final Set<DefaultDrmSession> keepaliveSessions;
    private final HashMap<String, String> keyRequestParameters;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    volatile MediaDrmHandler mediaDrmHandler;
    private int mode;
    private final boolean multiSession;
    private DefaultDrmSession noMultiSessionDrmSession;
    private byte[] offlineLicenseKeySetId;
    private DefaultDrmSession placeholderDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private Handler playbackHandler;
    private Looper playbackLooper;
    private PlayerId playerId;
    private final Set<PreacquiredSessionReference> preacquiredSessionReferences;
    private int prepareCallsCount;
    private final ProvisioningManagerImpl provisioningManagerImpl;
    private final ReferenceCountListenerImpl referenceCountListener;
    private final long sessionKeepaliveMs;
    private final List<DefaultDrmSession> sessions;
    private final int[] useDrmSessionsForClearContentTrackTypes;
    private final UUID uuid;

    /* renamed from: androidx.media3.exoplayer.drm.DefaultDrmSessionManager$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private ExoMediaDrm.Provider exoMediaDrmProvider;
        private final HashMap<String, String> keyRequestParameters;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private boolean multiSession;
        private boolean playClearSamplesWithoutKeys;
        private long sessionKeepaliveMs;
        private int[] useDrmSessionsForClearContentTrackTypes;
        private UUID uuid;

        public Builder() {
            this.keyRequestParameters = new HashMap();
            this.uuid = C.WIDEVINE_UUID;
            this.exoMediaDrmProvider = FrameworkMediaDrm.DEFAULT_PROVIDER;
            this.useDrmSessionsForClearContentTrackTypes = new int[0];
            this.playClearSamplesWithoutKeys = true;
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.sessionKeepaliveMs = 300000;
        }

        public DefaultDrmSessionManager build(MediaDrmCallback r13) {
            return new DefaultDrmSessionManager(this.uuid, this.exoMediaDrmProvider, r13, this.keyRequestParameters, this.multiSession, this.useDrmSessionsForClearContentTrackTypes, this.playClearSamplesWithoutKeys, this.loadErrorHandlingPolicy, this.sessionKeepaliveMs, null);
        }

        public Builder setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1) {
            this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(r1);
            return this;
        }

        public Builder setMultiSession(boolean r1) {
            this.multiSession = r1;
            return this;
        }

        public Builder setPlayClearSamplesWithoutKeys(boolean r1) {
            this.playClearSamplesWithoutKeys = r1;
            return this;
        }

        public Builder setUseDrmSessionsForClearContent(int... r7) {
            int r0 = r7.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L10;
            int r3 = r7[r2];
            boolean r5 = true;
            if (r3 == 2) goto L9;
            if (r3 == 1) goto L9;
            r5 = false;
        L9:
            Assertions.checkArgument(r5);
            r2 = r2 + 1;
            goto L3
        L10:
            this.useDrmSessionsForClearContentTrackTypes = (int[]) r7.clone();
            return this;
        }

        public Builder setUuidAndExoMediaDrmProvider(UUID r1, ExoMediaDrm.Provider r2) {
            this.uuid = (UUID) Assertions.checkNotNull(r1);
            this.exoMediaDrmProvider = (ExoMediaDrm.Provider) Assertions.checkNotNull(r2);
            return this;
        }
    }

    public class MediaDrmEventListener implements ExoMediaDrm.OnEventListener {
        final /* synthetic */ DefaultDrmSessionManager this$0;

        private MediaDrmEventListener(DefaultDrmSessionManager r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MediaDrmEventListener(DefaultDrmSessionManager r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.drm.ExoMediaDrm.OnEventListener
        public void onEvent(ExoMediaDrm r1, byte[] r2, int r3, int r4, byte[] r5) {
            ((MediaDrmHandler) Assertions.checkNotNull(this.this$0.mediaDrmHandler)).obtainMessage(r3, r2).sendToTarget();
        }
    }

    @SuppressLint({"HandlerLeak"})
    public class MediaDrmHandler extends Handler {
        final /* synthetic */ DefaultDrmSessionManager this$0;

        public MediaDrmHandler(DefaultDrmSessionManager r1, Looper r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message r4) {
            byte[] r0 = (byte[]) r4.obj;
            if (r0 == null) goto L15;
            Iterator r3 = DefaultDrmSessionManager.access$400(this.this$0).iterator();
        L7:
            if (r3.hasNext() == false) goto L16;
            DefaultDrmSession r1 = (DefaultDrmSession) r3.next();
            if (r1.hasSessionId(r0) == false) goto L7;
            r1.onMediaDrmEvent(r4.what);
            return;
        L16:
            return;
        }
    }

    public static final class MissingSchemeDataException extends Exception {
        private MissingSchemeDataException(UUID r3) {
            super("Media does not support uuid: " + r3);
        }

        public /* synthetic */ MissingSchemeDataException(UUID r1, AnonymousClass1 r2) {
            this(r1);
        }
    }

    public class PreacquiredSessionReference implements DrmSessionManager.DrmSessionReference {
        private final DrmSessionEventListener.EventDispatcher eventDispatcher;
        private boolean isReleased;
        private DrmSession session;
        final /* synthetic */ DefaultDrmSessionManager this$0;

        public static /* synthetic */ void $r8$lambda$3uBQpbDSBR_JYCKTHo5FzQm93bs(PreacquiredSessionReference r4, Format r5) {
            if (DefaultDrmSessionManager.access$800(r4.this$0) != 0) goto L5;
            return;
        L5:
            if (r4.isReleased == true) goto L10;
            DefaultDrmSessionManager r0 = r4.this$0;
            r4.session = DefaultDrmSessionManager.access$1500(r0, (Looper) Assertions.checkNotNull(DefaultDrmSessionManager.access$1400(r0)), r4.eventDispatcher, r5, false);
            DefaultDrmSessionManager.access$1300(r4.this$0).add(r4);
            return;
        }

        public static /* synthetic */ void $r8$lambda$gLsR6d0kDtgRH2R38KINniO9lM0(PreacquiredSessionReference r2) {
            if (r2.isReleased == false) goto L5;
            return;
        L5:
            DrmSession r0 = r2.session;
            if (r0 == null) goto L8;
            r0.release(r2.eventDispatcher);
        L8:
            DefaultDrmSessionManager.access$1300(r2.this$0).remove(r2);
            r2.isReleased = true;
        }

        public PreacquiredSessionReference(DefaultDrmSessionManager r1, DrmSessionEventListener.EventDispatcher r2) {
            this.this$0 = r1;
            this.eventDispatcher = r2;
        }

        public void acquire(Format r3) {
            ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.access$700(this.this$0))).post(new DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda0(this, r3));
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager.DrmSessionReference
        public void release() {
            Util.postOrRun((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.access$700(this.this$0)), new DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda1(this));
        }
    }

    public class ProvisioningManagerImpl implements DefaultDrmSession.ProvisioningManager {
        private DefaultDrmSession provisioningSession;
        private final Set<DefaultDrmSession> sessionsAwaitingProvisioning;
        final /* synthetic */ DefaultDrmSessionManager this$0;

        public ProvisioningManagerImpl(DefaultDrmSessionManager r1) {
            this.this$0 = r1;
            this.sessionsAwaitingProvisioning = new HashSet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.media3.exoplayer.drm.DefaultDrmSession.ProvisioningManager
        public void onProvisionCompleted() {
            this.provisioningSession = null;
            ImmutableList r0 = ImmutableList.copyOf(this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            UnmodifiableIterator r1 = r0.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            ((DefaultDrmSession) r1.next()).onProvisionCompleted();
            goto L4
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.media3.exoplayer.drm.DefaultDrmSession.ProvisioningManager
        public void onProvisionError(Exception r2, boolean r3) {
            this.provisioningSession = null;
            ImmutableList r0 = ImmutableList.copyOf(this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            UnmodifiableIterator r1 = r0.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            ((DefaultDrmSession) r1.next()).onProvisionError(r2, r3);
            goto L4
        }

        public void onSessionFullyReleased(DefaultDrmSession r2) {
            this.sessionsAwaitingProvisioning.remove(r2);
            if (this.provisioningSession != r2) goto L8;
            this.provisioningSession = null;
            if (this.sessionsAwaitingProvisioning.isEmpty() == true) goto L9;
            DefaultDrmSession r22 = this.sessionsAwaitingProvisioning.iterator().next();
            this.provisioningSession = r22;
            r22.provision();
            return;
        L9:
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DefaultDrmSession.ProvisioningManager
        public void provisionRequired(DefaultDrmSession r2) {
            this.sessionsAwaitingProvisioning.add(r2);
            if (this.provisioningSession == null) goto L5;
            return;
        L5:
            this.provisioningSession = r2;
            r2.provision();
        }
    }

    public class ReferenceCountListenerImpl implements DefaultDrmSession.ReferenceCountListener {
        final /* synthetic */ DefaultDrmSessionManager this$0;

        public static /* synthetic */ void $r8$lambda$p_3PmZlZM1XRGYZJ6X3dI4CMFm0(DefaultDrmSession r1) {
            r1.release(null);
        }

        private ReferenceCountListenerImpl(DefaultDrmSessionManager r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ReferenceCountListenerImpl(DefaultDrmSessionManager r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.drm.DefaultDrmSession.ReferenceCountListener
        public void onReferenceCountDecremented(DefaultDrmSession r6, int r7) {
            if (r7 == 1) goto L5;
        L9:
            if (r7 != 0) goto L19;
            DefaultDrmSessionManager.access$400(this.this$0).remove(r6);
            if (DefaultDrmSessionManager.access$900(this.this$0) != r6) goto L14;
            DefaultDrmSessionManager.access$902(this.this$0, null);
        L14:
            if (DefaultDrmSessionManager.access$1000(this.this$0) != r6) goto L16;
            DefaultDrmSessionManager.access$1002(this.this$0, null);
        L16:
            DefaultDrmSessionManager.access$1100(this.this$0).onSessionFullyReleased(r6);
            if (DefaultDrmSessionManager.access$500(this.this$0) == (-9223372036854775807L)) goto L19;
            ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.access$700(this.this$0))).removeCallbacksAndMessages(r6);
            DefaultDrmSessionManager.access$600(this.this$0).remove(r6);
        L19:
            DefaultDrmSessionManager.access$1200(this.this$0);
            return;
        L5:
            if (DefaultDrmSessionManager.access$800(this.this$0) <= 0) goto L9;
            if (DefaultDrmSessionManager.access$500(this.this$0) == (-9223372036854775807L)) goto L9;
            DefaultDrmSessionManager.access$600(this.this$0).add(r6);
            ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.access$700(this.this$0))).postAtTime(new DefaultDrmSessionManager$ReferenceCountListenerImpl$$ExternalSyntheticLambda0(r6), r6, SystemClock.uptimeMillis() + DefaultDrmSessionManager.access$500(this.this$0));
            goto L19
        }

        @Override // androidx.media3.exoplayer.drm.DefaultDrmSession.ReferenceCountListener
        public void onReferenceCountIncremented(DefaultDrmSession r5, int r6) {
            if (DefaultDrmSessionManager.access$500(this.this$0) == (-9223372036854775807L)) goto L6;
            DefaultDrmSessionManager.access$600(this.this$0).remove(r5);
            ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.access$700(this.this$0))).removeCallbacksAndMessages(r5);
            return;
        }
    }

    private DefaultDrmSessionManager(UUID r3, ExoMediaDrm.Provider r4, MediaDrmCallback r5, HashMap<String, String> r6, boolean r7, int[] r8, boolean r9, LoadErrorHandlingPolicy r10, long r11) {
        Assertions.checkNotNull(r3);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(r3), "Use C.CLEARKEY_UUID instead");
        this.uuid = r3;
        this.exoMediaDrmProvider = r4;
        this.callback = r5;
        this.keyRequestParameters = r6;
        this.multiSession = r7;
        this.useDrmSessionsForClearContentTrackTypes = r8;
        this.playClearSamplesWithoutKeys = r9;
        this.loadErrorHandlingPolicy = r10;
        this.provisioningManagerImpl = new ProvisioningManagerImpl(this);
        this.referenceCountListener = new ReferenceCountListenerImpl(this, null);
        this.mode = 0;
        this.sessions = new ArrayList();
        this.preacquiredSessionReferences = Sets.newIdentityHashSet();
        this.keepaliveSessions = Sets.newIdentityHashSet();
        this.sessionKeepaliveMs = r11;
    }

    public /* synthetic */ DefaultDrmSessionManager(UUID r1, ExoMediaDrm.Provider r2, MediaDrmCallback r3, HashMap r4, boolean r5, int[] r6, boolean r7, LoadErrorHandlingPolicy r8, long r9, AnonymousClass1 r11) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ DefaultDrmSession access$1000(DefaultDrmSessionManager r0) {
        return r0.noMultiSessionDrmSession;
    }

    public static /* synthetic */ DefaultDrmSession access$1002(DefaultDrmSessionManager r0, DefaultDrmSession r1) {
        r0.noMultiSessionDrmSession = r1;
        return r1;
    }

    public static /* synthetic */ ProvisioningManagerImpl access$1100(DefaultDrmSessionManager r0) {
        return r0.provisioningManagerImpl;
    }

    public static /* synthetic */ void access$1200(DefaultDrmSessionManager r0) {
        r0.maybeReleaseMediaDrm();
    }

    public static /* synthetic */ Set access$1300(DefaultDrmSessionManager r0) {
        return r0.preacquiredSessionReferences;
    }

    public static /* synthetic */ Looper access$1400(DefaultDrmSessionManager r0) {
        return r0.playbackLooper;
    }

    public static /* synthetic */ DrmSession access$1500(DefaultDrmSessionManager r0, Looper r1, DrmSessionEventListener.EventDispatcher r2, Format r3, boolean r4) {
        return r0.acquireSession(r1, r2, r3, r4);
    }

    public static /* synthetic */ List access$400(DefaultDrmSessionManager r0) {
        return r0.sessions;
    }

    public static /* synthetic */ long access$500(DefaultDrmSessionManager r2) {
        return r2.sessionKeepaliveMs;
    }

    public static /* synthetic */ Set access$600(DefaultDrmSessionManager r0) {
        return r0.keepaliveSessions;
    }

    public static /* synthetic */ Handler access$700(DefaultDrmSessionManager r0) {
        return r0.playbackHandler;
    }

    public static /* synthetic */ int access$800(DefaultDrmSessionManager r0) {
        return r0.prepareCallsCount;
    }

    public static /* synthetic */ DefaultDrmSession access$900(DefaultDrmSessionManager r0) {
        return r0.placeholderDrmSession;
    }

    public static /* synthetic */ DefaultDrmSession access$902(DefaultDrmSessionManager r0, DefaultDrmSession r1) {
        r0.placeholderDrmSession = r1;
        return r1;
    }

    private DrmSession acquireSession(Looper r5, DrmSessionEventListener.EventDispatcher r6, Format r7, boolean r8) {
        maybeCreateMediaDrmHandler(r5);
        DrmInitData r52 = r7.drmInitData;
        if (r52 == null) goto L5;
        DefaultDrmSession r1 = null;
        if (this.offlineLicenseKeySetId != null) goto L15;
        List<DrmInitData.SchemeData> r53 = getSchemeDatas((DrmInitData) Assertions.checkNotNull(r52), this.uuid, false);
        if (r53.isEmpty() == false) goto L17;
        MissingSchemeDataException r54 = new MissingSchemeDataException(this.uuid, null);
        Log.e("DefaultDrmSessionMgr", "DRM error", r54);
        if (r6 == null) goto L14;
        r6.drmSessionManagerError(r54);
    L14:
        return new ErrorStateDrmSession(new DrmSession.DrmSessionException(r54, 6003));
    L17:
        if (this.multiSession == true) goto L19;
        r1 = this.noMultiSessionDrmSession;
    L25:
        if (r1 != null) goto L31;
        DefaultDrmSession r55 = createAndAcquireSessionWithRetry(r53, false, r6, r8);
        if (this.multiSession == true) goto L29;
        this.noMultiSessionDrmSession = r55;
    L29:
        this.sessions.add(r55);
        return r55;
    L31:
        r1.acquire(r6);
        return r1;
    L19:
        Iterator<DefaultDrmSession> r72 = this.sessions.iterator();
    L21:
        if (r72.hasNext() == false) goto L25;
        DefaultDrmSession r2 = r72.next();
        if (Objects.equals(r2.schemeDatas, r53) == false) goto L21;
        r1 = r2;
        goto L25
    L15:
        r53 = null;
        goto L17
    L5:
        return maybeAcquirePlaceholderSession(MimeTypes.getTrackType(r7.sampleMimeType), r8);
    }

    private static boolean acquisitionFailedIndicatingResourceShortage(DrmSession r3) {
        if (r3.getState() == 1) goto L5;
        return false;
    L5:
        Throwable r32 = ((DrmSession.DrmSessionException) Assertions.checkNotNull(r3.getError())).getCause();
        if ((r32 instanceof ResourceBusyException) == false) goto L8;
    L11:
        return true;
    L8:
        if (DrmUtil.isFailureToConstructResourceBusyException(r32) == true) goto L11;
        return false;
    }

    private boolean canAcquireSession(DrmInitData r5) {
        if (this.offlineLicenseKeySetId == null) goto L6;
        return true;
    L6:
        if (getSchemeDatas(r5, this.uuid, true).isEmpty() == true) goto L8;
    L13:
        String r4 = r5.schemeType;
        if (r4 != null) goto L16;
    L31:
        return true;
    L16:
        if ("cenc".equals(r4) == true) goto L31;
        if ("cbcs".equals(r4) == false) goto L25;
        if (Build.VERSION.SDK_INT < 25) goto L23;
        return true;
    L23:
        return false;
    L25:
        if ("cbc1".equals(r4) == false) goto L27;
    L30:
        return false;
    L27:
        if ("cens".equals(r4) == true) goto L30;
        return true;
    L8:
        if (r5.schemeDataCount == 1) goto L10;
    L12:
        return false;
    L10:
        if (r5.get(0).matches(C.COMMON_PSSH_UUID) == false) goto L12;
        Log.w("DefaultDrmSessionMgr", "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + this.uuid);
        goto L13
    }

    private DefaultDrmSession createAndAcquireSession(List<DrmInitData.SchemeData> r18, boolean r19, DrmSessionEventListener.EventDispatcher r20) {
        Assertions.checkNotNull(this.exoMediaDrm);
        boolean r9 = this.playClearSamplesWithoutKeys | r19;
        DefaultDrmSession r2 = new DefaultDrmSession(this.uuid, this.exoMediaDrm, this.provisioningManagerImpl, this.referenceCountListener, r18, this.mode, r9, r19, this.offlineLicenseKeySetId, this.keyRequestParameters, this.callback, (Looper) Assertions.checkNotNull(this.playbackLooper), this.loadErrorHandlingPolicy, (PlayerId) Assertions.checkNotNull(this.playerId));
        r2.acquire(r20);
        if (this.sessionKeepaliveMs == (-9223372036854775807L)) goto L5;
        r2.acquire(null);
    L5:
        return r2;
    }

    private DefaultDrmSession createAndAcquireSessionWithRetry(List<DrmInitData.SchemeData> r3, boolean r4, DrmSessionEventListener.EventDispatcher r5, boolean r6) {
        DefaultDrmSession r0 = createAndAcquireSession(r3, r4, r5);
        if (acquisitionFailedIndicatingResourceShortage(r0) == false) goto L8;
        if (this.keepaliveSessions.isEmpty() == true) goto L8;
        releaseAllKeepaliveSessions();
        undoAcquisition(r0, r5);
        r0 = createAndAcquireSession(r3, r4, r5);
    L8:
        if (acquisitionFailedIndicatingResourceShortage(r0) == false) goto L17;
        if (r6 == false) goto L17;
        if (this.preacquiredSessionReferences.isEmpty() == true) goto L17;
        releaseAllPreacquiredSessions();
        if (this.keepaliveSessions.isEmpty() == true) goto L15;
        releaseAllKeepaliveSessions();
    L15:
        undoAcquisition(r0, r5);
        return createAndAcquireSession(r3, r4, r5);
    L17:
        return r0;
    }

    private static List<DrmInitData.SchemeData> getSchemeDatas(DrmInitData r4, UUID r5, boolean r6) {
        ArrayList r0 = new ArrayList(r4.schemeDataCount);
        int r1 = 0;
    L4:
        if (r1 >= r4.schemeDataCount) goto L16;
        DrmInitData.SchemeData r2 = r4.get(r1);
        if (r2.matches(r5) == true) goto L12;
        if (C.CLEARKEY_UUID.equals(r5) == false) goto L15;
        if (r2.matches(C.COMMON_PSSH_UUID) == true) goto L12;
    L15:
        r1 = r1 + 1;
    L12:
        if (r2.data != null) goto L14;
        if (r6 == false) goto L15;
    L14:
        r0.add(r2);
        goto L15
    L16:
        return r0;
    }

    @EnsuresNonNull({"this.playbackLooper", "this.playbackHandler"})
    private synchronized void initPlaybackLooper(Looper r2) {
        monitor-enter(this);
        Looper r0 = this.playbackLooper;     // Catch: Throwable -> L6
        if (r0 != null) goto L8;
        this.playbackLooper = r2;     // Catch: Throwable -> L6
        this.playbackHandler = new Handler(r2);     // Catch: Throwable -> L6
    L12:
        monitor-exit(this);
        return;
    L8:
        if (r0 != r2) goto L10;
        boolean r22 = true;
    L11:
        Assertions.checkState(r22);     // Catch: Throwable -> L6
        Assertions.checkNotNull(this.playbackHandler);     // Catch: Throwable -> L6
        goto L12
    L10:
        r22 = false;
    L6:
        th = move-exception;
        throw th;
    }

    private DrmSession maybeAcquirePlaceholderSession(int r5, boolean r6) {
        ExoMediaDrm r0 = (ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm);
        if (r0.getCryptoType() != 2) goto L8;
        if (FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC == false) goto L8;
    L18:
        return null;
    L8:
        if (Util.linearSearch(this.useDrmSessionsForClearContentTrackTypes, r5) == (-1)) goto L18;
        if (r0.getCryptoType() == 1) goto L18;
        DefaultDrmSession r52 = this.placeholderDrmSession;
        if (r52 != null) goto L15;
        DefaultDrmSession r53 = createAndAcquireSessionWithRetry(ImmutableList.of(), true, null, r6);
        this.sessions.add(r53);
        this.placeholderDrmSession = r53;
    L17:
        return this.placeholderDrmSession;
    L15:
        r52.acquire(null);
        goto L17
    }

    private void maybeCreateMediaDrmHandler(Looper r2) {
        if (this.mediaDrmHandler != null) goto L6;
        this.mediaDrmHandler = new MediaDrmHandler(this, r2);
        return;
    }

    private void maybeReleaseMediaDrm() {
        if (this.exoMediaDrm != null) goto L5;
        return;
    L5:
        if (this.prepareCallsCount == 0) goto L7;
        return;
    L7:
        if (this.sessions.isEmpty() == true) goto L9;
        return;
    L9:
        if (this.preacquiredSessionReferences.isEmpty() == false) goto L15;
        ((ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm)).release();
        this.exoMediaDrm = null;
        return;
    }

    private void releaseAllKeepaliveSessions() {
        UnmodifiableIterator r2 = ImmutableSet.copyOf(this.keepaliveSessions).iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        ((DrmSession) r2.next()).release(null);
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void releaseAllPreacquiredSessions() {
        UnmodifiableIterator r1 = ImmutableSet.copyOf(this.preacquiredSessionReferences).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((PreacquiredSessionReference) r1.next()).release();
        goto L4
    }

    private void undoAcquisition(DrmSession r5, DrmSessionEventListener.EventDispatcher r6) {
        r5.release(r6);
        if (this.sessionKeepaliveMs == (-9223372036854775807L)) goto L6;
        r5.release(null);
        return;
    }

    private void verifyPlaybackThread(boolean r3) {
        if (r3 == false) goto L9;
        if (this.playbackLooper != null) goto L9;
        Log.w("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed before setPlayer(), possibly on the wrong thread.", new IllegalStateException());
        return;
    L9:
        if (Thread.currentThread() == ((Looper) Assertions.checkNotNull(this.playbackLooper)).getThread()) goto L12;
        Log.w("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + this.playbackLooper.getThread().getName(), new IllegalStateException());
        return;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManager
    public DrmSession acquireSession(DrmSessionEventListener.EventDispatcher r4, Format r5) {
        boolean r0 = false;
        verifyPlaybackThread(false);
        if (this.prepareCallsCount <= 0) goto L5;
        r0 = true;
    L5:
        Assertions.checkState(r0);
        Assertions.checkStateNotNull(this.playbackLooper);
        return acquireSession(this.playbackLooper, r4, r5, true);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManager
    public int getCryptoType(Format r4) {
        verifyPlaybackThread(false);
        int r1 = ((ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm)).getCryptoType();
        DrmInitData r2 = r4.drmInitData;
        if (r2 != null) goto L9;
        int r42 = MimeTypes.getTrackType(r4.sampleMimeType);
        if (Util.linearSearch(this.useDrmSessionsForClearContentTrackTypes, r42) != (-1)) goto L10;
        return 0;
    L10:
        return r1;
    L9:
        if (canAcquireSession(r2) == true) goto L10;
        return 1;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManager
    public DrmSessionManager.DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher r2, Format r3) {
        if (this.prepareCallsCount <= 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        Assertions.checkStateNotNull(this.playbackLooper);
        PreacquiredSessionReference r02 = new PreacquiredSessionReference(this, r2);
        r02.acquire(r3);
        return r02;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManager
    public final void prepare() {
        verifyPlaybackThread(true);
        int r0 = this.prepareCallsCount;
        this.prepareCallsCount = r0 + 1;
        if (r0 == 0) goto L6;
        return;
    L6:
        if (this.exoMediaDrm != null) goto L10;
        ExoMediaDrm r02 = this.exoMediaDrmProvider.acquireExoMediaDrm(this.uuid);
        this.exoMediaDrm = r02;
        r02.setOnEventListener(new MediaDrmEventListener(this, null));
        return;
    L10:
        if (this.sessionKeepaliveMs == (-9223372036854775807L)) goto L15;
        int r03 = 0;
    L13:
        if (r03 >= this.sessions.size()) goto L18;
        this.sessions.get(r03).acquire(null);
        r03 = r03 + 1;
        goto L13
    L18:
        return;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManager
    public final void release() {
        verifyPlaybackThread(true);
        int r1 = this.prepareCallsCount - 1;
        this.prepareCallsCount = r1;
        if (r1 == 0) goto L6;
        return;
    L6:
        if (this.sessionKeepaliveMs == (-9223372036854775807L)) goto L11;
        ArrayList r0 = new ArrayList(this.sessions);
        int r12 = 0;
    L9:
        if (r12 >= r0.size()) goto L11;
        ((DefaultDrmSession) r0.get(r12)).release(null);
        r12 = r12 + 1;
    L11:
        releaseAllPreacquiredSessions();
        maybeReleaseMediaDrm();
    }

    public void setMode(int r2, byte[] r3) {
        Assertions.checkState(this.sessions.isEmpty());
        if (r2 != 1) goto L5;
    L6:
        Assertions.checkNotNull(r3);
    L7:
        this.mode = r2;
        this.offlineLicenseKeySetId = r3;
        return;
    L5:
        if (r2 != 3) goto L7;
        goto L6
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManager
    public void setPlayer(Looper r1, PlayerId r2) {
        initPlaybackLooper(r1);
        this.playerId = r2;
    }
}
