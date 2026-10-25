package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MaskingMediaPeriod;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class MediaSourceList {
    private final HashMap<MediaSourceHolder, MediaSourceAndListener> childSources;
    private final Set<MediaSourceHolder> enabledMediaSourceHolders;
    private final HandlerWrapper eventHandler;
    private final AnalyticsCollector eventListener;
    private boolean isPrepared;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final Map<Object, MediaSourceHolder> mediaSourceByUid;
    private final List<MediaSourceHolder> mediaSourceHolders;
    private final MediaSourceListInfoRefreshListener mediaSourceListInfoListener;
    private TransferListener mediaTransferListener;
    private final PlayerId playerId;
    private ShuffleOrder shuffleOrder;

    public final class ForwardingEventListener implements MediaSourceEventListener, DrmSessionEventListener {
        private final MediaSourceHolder id;
        final /* synthetic */ MediaSourceList this$0;

        /* renamed from: $r8$lambda$0Z-yd-TVNO1BA6ZIw41DQL5wPpk, reason: not valid java name */
        public static /* synthetic */ void m784$r8$lambda$0ZydTVNO1BA6ZIw41DQL5wPpk(ForwardingEventListener r1, Pair r2, int r3) {
            MediaSourceList.access$300(r1.this$0).onDrmSessionAcquired(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second, r3);
        }

        /* renamed from: $r8$lambda$M_FI3UQ-aQfd-iGoPVaFKfkH5LM, reason: not valid java name */
        public static /* synthetic */ void m785$r8$lambda$M_FI3UQaQfdiGoPVaFKfkH5LM(ForwardingEventListener r1, Pair r2) {
            MediaSourceList.access$300(r1.this$0).onDrmKeysRemoved(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second);
        }

        public static /* synthetic */ void $r8$lambda$TJmpSk9MNTZKNlqqsIArZpSAt_s(ForwardingEventListener r1, Pair r2, LoadEventInfo r3, MediaLoadData r4) {
            MediaSourceList.access$300(r1.this$0).onLoadCanceled(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$ZMGwHWUNvR1505hMAJh0GmFrbzs(ForwardingEventListener r1, Pair r2) {
            MediaSourceList.access$300(r1.this$0).onDrmSessionReleased(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second);
        }

        /* renamed from: $r8$lambda$ab80WGWV05HTUTqTQGt-d_epHEA, reason: not valid java name */
        public static /* synthetic */ void m786$r8$lambda$ab80WGWV05HTUTqTQGtd_epHEA(ForwardingEventListener r1, Pair r2, MediaLoadData r3) {
            MediaSourceList.access$300(r1.this$0).onUpstreamDiscarded(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) Assertions.checkNotNull((MediaSource.MediaPeriodId) r2.second), r3);
        }

        public static /* synthetic */ void $r8$lambda$boZ13wacEW83btlE99T8rv63cKo(ForwardingEventListener r1, Pair r2, LoadEventInfo r3, MediaLoadData r4) {
            MediaSourceList.access$300(r1.this$0).onLoadCompleted(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$d_kLqle5eJxc6odA4dxFL6P9kJU(ForwardingEventListener r7, Pair r8, LoadEventInfo r9, MediaLoadData r10, IOException r11, boolean r12) {
            MediaSourceList.access$300(r7.this$0).onLoadError(((Integer) r8.first).intValue(), (MediaSource.MediaPeriodId) r8.second, r9, r10, r11, r12);
        }

        public static /* synthetic */ void $r8$lambda$iPObngWVMYgI0cfoHcJIwPkZals(ForwardingEventListener r1, Pair r2) {
            MediaSourceList.access$300(r1.this$0).onDrmKeysRestored(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second);
        }

        public static /* synthetic */ void $r8$lambda$rF7VJjN_kTbHGD25F7clXCWBas0(ForwardingEventListener r1, Pair r2) {
            MediaSourceList.access$300(r1.this$0).onDrmKeysLoaded(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second);
        }

        public static /* synthetic */ void $r8$lambda$sTIitGVZhAWgaM3UPxmodClRv1U(ForwardingEventListener r1, Pair r2, Exception r3) {
            MediaSourceList.access$300(r1.this$0).onDrmSessionManagerError(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second, r3);
        }

        public static /* synthetic */ void $r8$lambda$y5VcA74lGnftQLqZJy2RYOLgx8g(ForwardingEventListener r1, Pair r2, MediaLoadData r3) {
            MediaSourceList.access$300(r1.this$0).onDownstreamFormatChanged(((Integer) r2.first).intValue(), (MediaSource.MediaPeriodId) r2.second, r3);
        }

        public static /* synthetic */ void $r8$lambda$zhztQi2tdFJfXzsxdq2l1ej7BJE(ForwardingEventListener r6, Pair r7, LoadEventInfo r8, MediaLoadData r9, int r10) {
            MediaSourceList.access$300(r6.this$0).onLoadStarted(((Integer) r7.first).intValue(), (MediaSource.MediaPeriodId) r7.second, r8, r9, r10);
        }

        public ForwardingEventListener(MediaSourceList r1, MediaSourceHolder r2) {
            this.this$0 = r1;
            this.id = r2;
        }

        private Pair<Integer, MediaSource.MediaPeriodId> getEventParameters(int r3, MediaSource.MediaPeriodId r4) {
            MediaSource.MediaPeriodId r0 = null;
            if (r4 == null) goto L9;
            MediaSource.MediaPeriodId r42 = MediaSourceList.access$100(this.id, r4);
            if (r42 != null) goto L7;
            return null;
        L7:
            r0 = r42;
        L9:
            return Pair.create(Integer.valueOf(MediaSourceList.access$200(this.id, r3)), r0);
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onDownstreamFormatChanged(int r2, MediaSource.MediaPeriodId r3, MediaLoadData r4) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda3(this, r22, r4));
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmKeysLoaded(int r2, MediaSource.MediaPeriodId r3) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda11(this, r22));
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmKeysRemoved(int r2, MediaSource.MediaPeriodId r3) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda9(this, r22));
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmKeysRestored(int r2, MediaSource.MediaPeriodId r3) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda10(this, r22));
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmSessionAcquired(int r2, MediaSource.MediaPeriodId r3, int r4) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda6(this, r22, r4));
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmSessionManagerError(int r2, MediaSource.MediaPeriodId r3, Exception r4) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda2(this, r22, r4));
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmSessionReleased(int r2, MediaSource.MediaPeriodId r3) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda4(this, r22));
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadCanceled(int r2, MediaSource.MediaPeriodId r3, LoadEventInfo r4, MediaLoadData r5) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda1(this, r22, r4, r5));
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadCompleted(int r2, MediaSource.MediaPeriodId r3, LoadEventInfo r4, MediaLoadData r5) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda5(this, r22, r4, r5));
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadError(int r2, MediaSource.MediaPeriodId r3, LoadEventInfo r4, MediaLoadData r5, IOException r6, boolean r7) {
            Pair<Integer, MediaSource.MediaPeriodId> r32 = getEventParameters(r2, r3);
            if (r32 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda8(this, r32, r4, r5, r6, r7));
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadStarted(int r2, MediaSource.MediaPeriodId r3, LoadEventInfo r4, MediaLoadData r5, int r6) {
            Pair<Integer, MediaSource.MediaPeriodId> r32 = getEventParameters(r2, r3);
            if (r32 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda7(this, r32, r4, r5, r6));
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onUpstreamDiscarded(int r2, MediaSource.MediaPeriodId r3, MediaLoadData r4) {
            Pair<Integer, MediaSource.MediaPeriodId> r22 = getEventParameters(r2, r3);
            if (r22 == null) goto L6;
            MediaSourceList.access$000(this.this$0).post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda0(this, r22, r4));
            return;
        }
    }

    public static final class MediaSourceAndListener {
        public final MediaSource.MediaSourceCaller caller;
        public final ForwardingEventListener eventListener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource r1, MediaSource.MediaSourceCaller r2, ForwardingEventListener r3) {
            this.mediaSource = r1;
            this.caller = r2;
            this.eventListener = r3;
        }
    }

    public static final class MediaSourceHolder implements MediaSourceInfoHolder {
        public final List<MediaSource.MediaPeriodId> activeMediaPeriodIds;
        public int firstWindowIndexInChild;
        public boolean isRemoved;
        public final MaskingMediaSource mediaSource;
        public final Object uid;

        public MediaSourceHolder(MediaSource r2, boolean r3) {
            this.mediaSource = new MaskingMediaSource(r2, r3);
            this.activeMediaPeriodIds = new ArrayList();
            this.uid = new Object();
        }

        @Override // androidx.media3.exoplayer.MediaSourceInfoHolder
        public Timeline getTimeline() {
            return this.mediaSource.getTimeline();
        }

        @Override // androidx.media3.exoplayer.MediaSourceInfoHolder
        public Object getUid() {
            return this.uid;
        }

        public void reset(int r1) {
            this.firstWindowIndexInChild = r1;
            this.isRemoved = false;
            this.activeMediaPeriodIds.clear();
        }
    }

    public interface MediaSourceListInfoRefreshListener {
        void onPlaylistUpdateRequested();
    }

    public static /* synthetic */ void $r8$lambda$unkhEKSDldKS1WZm5pAG8HKa9xE(MediaSourceList r0, MediaSource r1, Timeline r2) {
        r0.mediaSourceListInfoListener.onPlaylistUpdateRequested();
    }

    public MediaSourceList(MediaSourceListInfoRefreshListener r1, AnalyticsCollector r2, HandlerWrapper r3, PlayerId r4) {
        this.playerId = r4;
        this.mediaSourceListInfoListener = r1;
        this.shuffleOrder = new ShuffleOrder.DefaultShuffleOrder(0);
        this.mediaSourceByMediaPeriod = new IdentityHashMap();
        this.mediaSourceByUid = new HashMap();
        this.mediaSourceHolders = new ArrayList();
        this.eventListener = r2;
        this.eventHandler = r3;
        this.childSources = new HashMap();
        this.enabledMediaSourceHolders = new HashSet();
    }

    public static /* synthetic */ HandlerWrapper access$000(MediaSourceList r0) {
        return r0.eventHandler;
    }

    public static /* synthetic */ MediaSource.MediaPeriodId access$100(MediaSourceHolder r0, MediaSource.MediaPeriodId r1) {
        return getMediaPeriodIdForChildMediaPeriodId(r0, r1);
    }

    public static /* synthetic */ int access$200(MediaSourceHolder r0, int r1) {
        return getWindowIndexForChildWindowIndex(r0, r1);
    }

    public static /* synthetic */ AnalyticsCollector access$300(MediaSourceList r0) {
        return r0.eventListener;
    }

    private void correctOffsets(int r3, int r4) {
    L3:
        if (r3 >= this.mediaSourceHolders.size()) goto L5;
        this.mediaSourceHolders.get(r3).firstWindowIndexInChild += r4;
        r3 = r3 + 1;
        goto L3
    }

    private void disableChildSource(MediaSourceHolder r1) {
        MediaSourceAndListener r0 = this.childSources.get(r1);
        if (r0 == null) goto L6;
        r0.mediaSource.disable(r0.caller);
        return;
    }

    private void disableUnusedMediaSources() {
        Iterator<MediaSourceHolder> r0 = this.enabledMediaSourceHolders.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        MediaSourceHolder r1 = r0.next();
        if (r1.activeMediaPeriodIds.isEmpty() == false) goto L4;
        disableChildSource(r1);
        r0.remove();
        goto L4
    }

    private void enableMediaSource(MediaSourceHolder r2) {
        this.enabledMediaSourceHolders.add(r2);
        MediaSourceAndListener r1 = this.childSources.get(r2);
        if (r1 == null) goto L6;
        r1.mediaSource.enable(r1.caller);
        return;
    }

    private static Object getChildPeriodUid(Object r0) {
        return AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(r0);
    }

    private static MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder r5, MediaSource.MediaPeriodId r6) {
        int r0 = 0;
    L4:
        if (r0 >= r5.activeMediaPeriodIds.size()) goto L10;
        if (r5.activeMediaPeriodIds.get(r0).windowSequenceNumber == r6.windowSequenceNumber) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r6.copyWithPeriodUid(getPeriodUid(r5, r6.periodUid));
    L10:
        return null;
    }

    private static Object getMediaSourceHolderUid(Object r0) {
        return AbstractConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(r0);
    }

    private static Object getPeriodUid(MediaSourceHolder r0, Object r1) {
        return AbstractConcatenatedTimeline.getConcatenatedUid(r0.uid, r1);
    }

    private static int getWindowIndexForChildWindowIndex(MediaSourceHolder r0, int r1) {
        return r1 + r0.firstWindowIndexInChild;
    }

    private void maybeReleaseChildSource(MediaSourceHolder r4) {
        if (r4.isRemoved == true) goto L5;
        return;
    L5:
        if (r4.activeMediaPeriodIds.isEmpty() == false) goto L9;
        MediaSourceAndListener r0 = (MediaSourceAndListener) Assertions.checkNotNull(this.childSources.remove(r4));
        r0.mediaSource.releaseSource(r0.caller);
        r0.mediaSource.removeEventListener(r0.eventListener);
        r0.mediaSource.removeDrmEventListener(r0.eventListener);
        this.enabledMediaSourceHolders.remove(r4);
        return;
    }

    private void prepareChildSource(MediaSourceHolder r6) {
        MaskingMediaSource r0 = r6.mediaSource;
        MediaSourceList$$ExternalSyntheticLambda0 r1 = new MediaSourceList$$ExternalSyntheticLambda0(this);
        ForwardingEventListener r2 = new ForwardingEventListener(this, r6);
        this.childSources.put(r6, new MediaSourceAndListener(r0, r1, r2));
        r0.addEventListener(Util.createHandlerForCurrentOrMainLooper(), r2);
        r0.addDrmEventListener(Util.createHandlerForCurrentOrMainLooper(), r2);
        r0.prepareSource(r1, this.mediaTransferListener, this.playerId);
    }

    private void removeMediaSourcesInternal(int r5, int r6) {
        int r62 = r6 - 1;
    L3:
        if (r62 < r5) goto L8;
        MediaSourceHolder r1 = this.mediaSourceHolders.remove(r62);
        this.mediaSourceByUid.remove(r1.uid);
        correctOffsets(r62, -r1.mediaSource.getTimeline().getWindowCount());
        r1.isRemoved = true;
        if (this.isPrepared == false) goto L7;
        maybeReleaseChildSource(r1);
    L7:
        r62 = r62 - 1;
        goto L3
    }

    public Timeline addMediaSources(int r4, List<MediaSourceHolder> r5, ShuffleOrder r6) {
        if (r5.isEmpty() == true) goto L19;
        this.shuffleOrder = r6;
        int r62 = r4;
    L6:
        if (r62 >= (r5.size() + r4)) goto L19;
        MediaSourceHolder r0 = r5.get(r62 - r4);
        if (r62 <= 0) goto L10;
        MediaSourceHolder r1 = this.mediaSourceHolders.get(r62 - 1);
        r0.reset(r1.firstWindowIndexInChild + r1.mediaSource.getTimeline().getWindowCount());
    L11:
        correctOffsets(r62, r0.mediaSource.getTimeline().getWindowCount());
        this.mediaSourceHolders.add(r62, r0);
        this.mediaSourceByUid.put(r0.uid, r0);
        if (this.isPrepared == false) goto L17;
        prepareChildSource(r0);
        if (this.mediaSourceByMediaPeriod.isEmpty() == false) goto L16;
        this.enabledMediaSourceHolders.add(r0);
        goto L17
    L16:
        disableChildSource(r0);
    L17:
        r62 = r62 + 1;
        goto L6
    L10:
        r0.reset(0);
    L19:
        return createTimeline();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r3, Allocator r4, long r5) {
        Object r0 = getMediaSourceHolderUid(r3.periodUid);
        MediaSource.MediaPeriodId r32 = r3.copyWithPeriodUid(getChildPeriodUid(r3.periodUid));
        MediaSourceHolder r02 = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByUid.get(r0));
        enableMediaSource(r02);
        r02.activeMediaPeriodIds.add(r32);
        MaskingMediaPeriod r33 = r02.mediaSource.createPeriod(r32, r4, r5);
        this.mediaSourceByMediaPeriod.put(r33, r02);
        disableUnusedMediaSources();
        return r33;
    }

    public Timeline createTimeline() {
        if (this.mediaSourceHolders.isEmpty() == true) goto L5;
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r0 >= this.mediaSourceHolders.size()) goto L11;
        MediaSourceHolder r2 = this.mediaSourceHolders.get(r0);
        r2.firstWindowIndexInChild = r1;
        r1 = r1 + r2.mediaSource.getTimeline().getWindowCount();
        r0 = r0 + 1;
        goto L8
    L11:
        return new PlaylistTimeline(this.mediaSourceHolders, this.shuffleOrder);
    L5:
        return Timeline.EMPTY;
    }

    public ShuffleOrder getShuffleOrder() {
        return this.shuffleOrder;
    }

    public int getSize() {
        return this.mediaSourceHolders.size();
    }

    public boolean isPrepared() {
        return this.isPrepared;
    }

    public Timeline moveMediaSourceRange(int r4, int r5, int r6, ShuffleOrder r7) {
        if (r4 < 0) goto L9;
        if (r4 > r5) goto L9;
        if (r5 > getSize()) goto L9;
        if (r6 < 0) goto L9;
        boolean r1 = true;
    L10:
        Assertions.checkArgument(r1);
        this.shuffleOrder = r7;
        if (r4 == r5) goto L20;
        if (r4 == r6) goto L20;
        int r72 = Math.min(r4, r6);
        int r0 = Math.max(((r5 - r4) + r6) - 1, r5 - 1);
        int r12 = this.mediaSourceHolders.get(r72).firstWindowIndexInChild;
        Util.moveItems(this.mediaSourceHolders, r4, r5, r6);
    L15:
        if (r72 > r0) goto L18;
        MediaSourceHolder r42 = this.mediaSourceHolders.get(r72);
        r42.firstWindowIndexInChild = r12;
        r12 = r12 + r42.mediaSource.getTimeline().getWindowCount();
        r72 = r72 + 1;
        goto L15
    L18:
        return createTimeline();
    L20:
        return createTimeline();
    L9:
        r1 = false;
        goto L10
    }

    public void prepare(TransferListener r4) {
        Assertions.checkState(!this.isPrepared);
        this.mediaTransferListener = r4;
        int r42 = 0;
    L4:
        if (r42 >= this.mediaSourceHolders.size()) goto L6;
        MediaSourceHolder r0 = this.mediaSourceHolders.get(r42);
        prepareChildSource(r0);
        this.enabledMediaSourceHolders.add(r0);
        r42 = r42 + 1;
        goto L4
    L6:
        this.isPrepared = true;
    }

    public void release() {
        Iterator<MediaSourceAndListener> r0 = this.childSources.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        MediaSourceAndListener r1 = r0.next();
        r1.mediaSource.releaseSource(r1.caller);     // Catch: RuntimeException -> L8
    L10:
        r1.mediaSource.removeEventListener(r1.eventListener);
        r1.mediaSource.removeDrmEventListener(r1.eventListener);
    L8:
        e = move-exception;
        Log.e("MediaSourceList", "Failed to release child source.", e);
        goto L10
    L11:
        this.childSources.clear();
        this.enabledMediaSourceHolders.clear();
        this.isPrepared = false;
    }

    public void releasePeriod(MediaPeriod r3) {
        MediaSourceHolder r0 = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(r3));
        r0.mediaSource.releasePeriod(r3);
        r0.activeMediaPeriodIds.remove(((MaskingMediaPeriod) r3).id);
        if (this.mediaSourceByMediaPeriod.isEmpty() == true) goto L5;
        disableUnusedMediaSources();
    L5:
        maybeReleaseChildSource(r0);
    }

    public Timeline removeMediaSourceRange(int r2, int r3, ShuffleOrder r4) {
        if (r2 < 0) goto L7;
        if (r2 > r3) goto L7;
        if (r3 > getSize()) goto L7;
        boolean r0 = true;
    L8:
        Assertions.checkArgument(r0);
        this.shuffleOrder = r4;
        removeMediaSourcesInternal(r2, r3);
        return createTimeline();
    L7:
        r0 = false;
        goto L8
    }

    public Timeline setMediaSources(List<MediaSourceHolder> r3, ShuffleOrder r4) {
        removeMediaSourcesInternal(0, this.mediaSourceHolders.size());
        return addMediaSources(this.mediaSourceHolders.size(), r3, r4);
    }

    public Timeline setShuffleOrder(ShuffleOrder r3) {
        int r0 = getSize();
        if (r3.getLength() == r0) goto L5;
        r3 = r3.cloneAndClear().cloneAndInsert(0, r0);
    L5:
        this.shuffleOrder = r3;
        return createTimeline();
    }

    public Timeline updateMediaSourcesWithMediaItems(int r5, int r6, List<MediaItem> r7) {
        boolean r0 = false;
        if (r5 < 0) goto L8;
        if (r5 > r6) goto L8;
        if (r6 > getSize()) goto L8;
        boolean r2 = true;
    L9:
        Assertions.checkArgument(r2);
        if (r7.size() != (r6 - r5)) goto L12;
        r0 = true;
    L12:
        Assertions.checkArgument(r0);
        int r02 = r5;
    L13:
        if (r02 >= r6) goto L16;
        this.mediaSourceHolders.get(r02).mediaSource.updateMediaItem(r7.get(r02 - r5));
        r02 = r02 + 1;
        goto L13
    L16:
        return createTimeline();
    L8:
        r2 = false;
        goto L9
    }
}
