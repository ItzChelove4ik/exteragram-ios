package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.dash.DashSegmentIndex;
import androidx.media3.exoplayer.dash.manifest.SegmentBase;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class Representation {
    public final ImmutableList<BaseUrl> baseUrls;
    public final List<Descriptor> essentialProperties;
    public final Format format;
    public final List<Descriptor> inbandEventStreams;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;
    public final List<Descriptor> supplementalProperties;

    /* renamed from: androidx.media3.exoplayer.dash.manifest.Representation$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        final SegmentBase.MultiSegmentBase segmentBase;

        public MultiSegmentRepresentation(long r11, Format r13, List<BaseUrl> r14, SegmentBase.MultiSegmentBase r15, List<Descriptor> r16, List<Descriptor> r17, List<Descriptor> r18) {
            super(r11, r13, r14, r15, r16, r17, r18, null);
            this.segmentBase = r15;
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getAvailableSegmentCount(long r1, long r3) {
            return this.segmentBase.getAvailableSegmentCount(r1, r3);
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public String getCacheKey() {
            return null;
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getDurationUs(long r1, long r3) {
            return this.segmentBase.getSegmentDurationUs(r1, r3);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getFirstAvailableSegmentNum(long r1, long r3) {
            return this.segmentBase.getFirstAvailableSegmentNum(r1, r3);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return null;
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getNextSegmentAvailableTimeUs(long r1, long r3) {
            return this.segmentBase.getNextSegmentAvailableTimeUs(r1, r3);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getSegmentCount(long r1) {
            return this.segmentBase.getSegmentCount(r1);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getSegmentNum(long r1, long r3) {
            return this.segmentBase.getSegmentNum(r1, r3);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public RangedUri getSegmentUrl(long r2) {
            return this.segmentBase.getSegmentUrl(this, r2);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getTimeUs(long r1) {
            return this.segmentBase.getSegmentTimeUs(r1);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }

    public static class SingleSegmentRepresentation extends Representation {
        private final String cacheKey;
        public final long contentLength;
        private final RangedUri indexUri;
        private final SingleSegmentIndex segmentIndex;
        public final Uri uri;

        public SingleSegmentRepresentation(long r11, Format r13, List<BaseUrl> r14, SegmentBase.SingleSegmentBase r15, List<Descriptor> r16, List<Descriptor> r17, List<Descriptor> r18, String r19, long r20) {
            super(r11, r13, r14, r15, r16, r17, r18, null);
            this.uri = Uri.parse(r14.get(0).url);
            RangedUri r112 = r15.getIndex();
            this.indexUri = r112;
            this.cacheKey = r19;
            this.contentLength = r20;
            if (r112 == null) goto L5;
            SingleSegmentIndex r113 = null;
        L6:
            this.segmentIndex = r113;
            return;
        L5:
            r113 = new SingleSegmentIndex(new RangedUri(null, 0, r20));
            goto L6
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public String getCacheKey() {
            return this.cacheKey;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return this.indexUri;
        }
    }

    private Representation(long r2, Format r4, List<BaseUrl> r5, SegmentBase r6, List<Descriptor> r7, List<Descriptor> r8, List<Descriptor> r9) {
        Assertions.checkArgument(!r5.isEmpty());
        this.revisionId = r2;
        this.format = r4;
        this.baseUrls = ImmutableList.copyOf(r5);
        if (r7 != null) goto L5;
        List<Descriptor> r22 = Collections.EMPTY_LIST;
    L6:
        this.inbandEventStreams = r22;
        this.essentialProperties = r8;
        this.supplementalProperties = r9;
        this.initializationUri = r6.getInitialization(this);
        this.presentationTimeOffsetUs = r6.getPresentationTimeOffsetUs();
        return;
    L5:
        r22 = Collections.unmodifiableList(r7);
        goto L6
    }

    public /* synthetic */ Representation(long r1, Format r3, List r4, SegmentBase r5, List r6, List r7, List r8, AnonymousClass1 r9) {
        this(r1, r3, r4, r5, r6, r7, r8);
    }

    public static Representation newInstance(long r14, Format r16, List<BaseUrl> r17, SegmentBase r18, List<Descriptor> r19, List<Descriptor> r20, List<Descriptor> r21, String r22) {
        if ((r18 instanceof SegmentBase.SingleSegmentBase) == false) goto L7;
        return new SingleSegmentRepresentation(r14, r16, r17, (SegmentBase.SingleSegmentBase) r18, r19, r20, r21, r22, -1);
    L7:
        if ((r18 instanceof SegmentBase.MultiSegmentBase) == true) goto L9;
        f$$ExternalSyntheticBUOutline1.m("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
        return null;
    L9:
        return new MultiSegmentRepresentation(r14, r16, r17, (SegmentBase.MultiSegmentBase) r18, r19, r20, r21);
    }

    public abstract String getCacheKey();

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }
}
