package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import androidx.camera.core.impl.Quirks$$ExternalSyntheticBackport0;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class HlsMediaPlaylist extends HlsPlaylist {
    public final int discontinuitySequence;
    public final long durationUs;
    public final boolean hasDiscontinuitySequence;
    public final boolean hasEndTag;
    public final boolean hasPositiveStartOffset;
    public final boolean hasProgramDateTime;
    public final ImmutableList<Interstitial> interstitials;
    public final long mediaSequence;
    public final long partTargetDurationUs;
    public final int playlistType;
    public final boolean preciseStart;
    public final DrmInitData protectionSchemes;
    public final Map<Uri, RenditionReport> renditionReports;
    public final List<Segment> segments;
    public final ServerControl serverControl;
    public final long startOffsetUs;
    public final long startTimeUs;
    public final long targetDurationUs;
    public final List<Part> trailingParts;
    public final int version;

    /* renamed from: androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class ClientDefinedAttribute {
        private final double doubleValue;
        public final String name;
        private final String textValue;
        public final int type;

        public ClientDefinedAttribute(String r1, double r2) {
            this.name = r1;
            this.type = 2;
            this.doubleValue = r2;
            this.textValue = null;
        }

        public ClientDefinedAttribute(String r3, String r4, int r5) {
            boolean r0 = true;
            if (r5 == 1) goto L5;
        L10:
            Assertions.checkState(r0);
            this.name = r3;
            this.type = r5;
            this.textValue = r4;
            this.doubleValue = 0.0d;
            return;
        L5:
            if (r4.startsWith("0x") == true) goto L10;
            if (r4.startsWith("0X") == true) goto L10;
            r0 = false;
            goto L10
        }

        public static /* synthetic */ String access$100(ClientDefinedAttribute r0) {
            return r0.textValue;
        }

        public static /* synthetic */ double access$200(ClientDefinedAttribute r2) {
            return r2.doubleValue;
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof ClientDefinedAttribute) == true) goto L8;
            return false;
        L8:
            ClientDefinedAttribute r82 = (ClientDefinedAttribute) r8;
            if (this.type == r82.type) goto L11;
        L17:
            return false;
        L11:
            if (Double.compare(this.doubleValue, r82.doubleValue) != 0) goto L17;
            if (Objects.equals(this.name, r82.name) == false) goto L17;
            if (Objects.equals(this.textValue, r82.textValue) == false) goto L17;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.name, Integer.valueOf(this.type), Double.valueOf(this.doubleValue), this.textValue});
        }
    }

    public static final class Interstitial {
        public final Uri assetListUri;
        public final Uri assetUri;
        public final ImmutableList<ClientDefinedAttribute> clientDefinedAttributes;
        public final boolean contentMayVary;
        public final List<String> cue;
        public final long durationUs;
        public final long endDateUnixUs;
        public final boolean endOnNext;
        public final String id;
        public final long plannedDurationUs;
        public final long playoutLimitUs;
        public final ImmutableList<String> restrictions;
        public final long resumeOffsetUs;
        public final ImmutableList<String> snapTypes;
        public final long startDateUnixUs;
        public final String timelineOccupies;
        public final String timelineStyle;

        public static final class Builder {
            private Uri assetListUri;
            private Uri assetUri;
            private final Map<String, ClientDefinedAttribute> clientDefinedAttributes;
            private Boolean contentMayVary;
            private List<String> cue;
            private long durationUs;
            private long endDateUnixUs;
            private boolean endOnNext;
            private final String id;
            private long plannedDurationUs;
            private long playoutLimitUs;
            private List<String> restrictions;
            private long resumeOffsetUs;
            private List<String> snapTypes;
            private long startDateUnixUs;
            private String timelineOccupies;
            private String timelineStyle;

            public Builder(String r3) {
                this.id = r3;
                this.clientDefinedAttributes = new HashMap();
                this.startDateUnixUs = -9223372036854775807L;
                this.endDateUnixUs = -9223372036854775807L;
                this.durationUs = -9223372036854775807L;
                this.plannedDurationUs = -9223372036854775807L;
                this.cue = new ArrayList();
                this.resumeOffsetUs = -9223372036854775807L;
                this.playoutLimitUs = -9223372036854775807L;
                this.snapTypes = new ArrayList();
                this.restrictions = new ArrayList();
            }

            public Interstitial build() {
                Uri r3 = this.assetListUri;
                if (r3 == null) goto L5;
            L6:
                if (r3 != null) goto L8;
                return null;
            L8:
                if (this.assetUri != null) goto L31;
            L9:
                long r4 = this.startDateUnixUs;
                if (r4 == (-9223372036854775807L)) goto L32;
                String r1 = this.id;
                Uri r2 = this.assetUri;
                long r6 = this.endDateUnixUs;
                long r8 = this.durationUs;
                long r10 = this.plannedDurationUs;
                List<String> r12 = this.cue;
                boolean r13 = this.endOnNext;
                long r14 = this.resumeOffsetUs;
                long r15 = this.playoutLimitUs;
                List<String> r16 = this.snapTypes;
                List<String> r22 = this.restrictions;
                ArrayList r17 = new ArrayList(this.clientDefinedAttributes.values());
                Boolean r23 = this.contentMayVary;
                if (r23 != null) goto L14;
            L18:
                boolean r24 = true;
            L19:
                String r18 = this.timelineOccupies;
                if (r18 != null) goto L23;
                r18 = "POINT";
            L23:
                String r0 = this.timelineStyle;
                if (r0 != null) goto L28;
                r0 = "HIGHLIGHT";
            L28:
                return new Interstitial(r1, r2, r3, r4, r6, r8, r10, r12, r13, r14, r15, r16, r22, r17, r24, r18, r0);
            L14:
                if (r23.booleanValue() == true) goto L18;
                r24 = false;
                goto L19
            L32:
                return null;
            L31:
                return null;
            L5:
                if (this.assetUri != null) goto L9;
                goto L6
            }

            public Builder setAssetListUri(Uri r4) {
                if (r4 != null) goto L4;
                return this;
            L4:
                Uri r0 = this.assetListUri;
                if (r0 == null) goto L7;
                Assertions.checkArgument(r0.equals(r4), "Can't change assetListUri from " + this.assetListUri + " to " + r4);
            L7:
                this.assetListUri = r4;
                return this;
            }

            public Builder setAssetUri(Uri r4) {
                if (r4 != null) goto L4;
                return this;
            L4:
                Uri r0 = this.assetUri;
                if (r0 == null) goto L7;
                Assertions.checkArgument(r0.equals(r4), "Can't change assetUri from " + this.assetUri + " to " + r4);
            L7:
                this.assetUri = r4;
                return this;
            }

            public Builder setClientDefinedAttributes(List<ClientDefinedAttribute> r10) {
                if (r10.isEmpty() == true) goto L12;
                int r0 = 0;
            L7:
                if (r0 >= r10.size()) goto L12;
                ClientDefinedAttribute r1 = r10.get(r0);
                String r2 = r1.name;
                ClientDefinedAttribute r3 = this.clientDefinedAttributes.get(r2);
                if (r3 == null) goto L11;
                Assertions.checkArgument(r3.equals(r1), "Can't change " + r2 + " from " + ClientDefinedAttribute.access$100(r3) + " " + ClientDefinedAttribute.access$200(r3) + " to " + ClientDefinedAttribute.access$100(r1) + " " + ClientDefinedAttribute.access$200(r1));
            L11:
                this.clientDefinedAttributes.put(r2, r1);
                r0 = r0 + 1;
            L12:
                return this;
            }

            public Builder setContentMayVary(Boolean r4) {
                if (r4 != null) goto L4;
                return this;
            L4:
                Boolean r0 = this.contentMayVary;
                if (r0 == null) goto L7;
                Assertions.checkArgument(r0.equals(r4), "Can't change contentMayVary from " + this.contentMayVary + " to " + r4);
            L7:
                this.contentMayVary = r4;
                return this;
            }

            public Builder setCue(List<String> r5) {
                if (r5.isEmpty() == false) goto L6;
                return this;
            L6:
                if (this.cue.isEmpty() == true) goto L8;
                Assertions.checkArgument(this.cue.equals(r5), "Can't change cue from " + Quirks$$ExternalSyntheticBackport0.m(", ", this.cue) + " to " + Quirks$$ExternalSyntheticBackport0.m(", ", r5));
            L8:
                this.cue = r5;
                return this;
            }

            public Builder setDurationUs(long r5) {
                if (r5 != (-9223372036854775807L)) goto L5;
                return this;
            L5:
                long r2 = this.durationUs;
                if (r2 != (-9223372036854775807L)) goto L8;
            L12:
                this.durationUs = r5;
                return this;
            L8:
                if (r2 != r5) goto L10;
                boolean r0 = true;
            L11:
                Assertions.checkArgument(r0, "Can't change durationUs from " + this.durationUs + " to " + r5);
                goto L12
            L10:
                r0 = false;
                goto L11
            }

            public Builder setEndDateUnixUs(long r5) {
                if (r5 != (-9223372036854775807L)) goto L5;
                return this;
            L5:
                long r2 = this.endDateUnixUs;
                if (r2 != (-9223372036854775807L)) goto L8;
            L12:
                this.endDateUnixUs = r5;
                return this;
            L8:
                if (r2 != r5) goto L10;
                boolean r0 = true;
            L11:
                Assertions.checkArgument(r0, "Can't change endDateUnixUs from " + this.endDateUnixUs + " to " + r5);
                goto L12
            L10:
                r0 = false;
                goto L11
            }

            public Builder setEndOnNext(boolean r1) {
                if (r1 == true) goto L4;
                return this;
            L4:
                this.endOnNext = true;
                return this;
            }

            public Builder setPlannedDurationUs(long r5) {
                if (r5 != (-9223372036854775807L)) goto L5;
                return this;
            L5:
                long r2 = this.plannedDurationUs;
                if (r2 != (-9223372036854775807L)) goto L8;
            L12:
                this.plannedDurationUs = r5;
                return this;
            L8:
                if (r2 != r5) goto L10;
                boolean r0 = true;
            L11:
                Assertions.checkArgument(r0, "Can't change plannedDurationUs from " + this.plannedDurationUs + " to " + r5);
                goto L12
            L10:
                r0 = false;
                goto L11
            }

            public Builder setPlayoutLimitUs(long r5) {
                if (r5 != (-9223372036854775807L)) goto L5;
                return this;
            L5:
                long r2 = this.playoutLimitUs;
                if (r2 != (-9223372036854775807L)) goto L8;
            L12:
                this.playoutLimitUs = r5;
                return this;
            L8:
                if (r2 != r5) goto L10;
                boolean r0 = true;
            L11:
                Assertions.checkArgument(r0, "Can't change playoutLimitUs from " + this.playoutLimitUs + " to " + r5);
                goto L12
            L10:
                r0 = false;
                goto L11
            }

            public Builder setRestrictions(List<String> r5) {
                if (r5.isEmpty() == false) goto L6;
                return this;
            L6:
                if (this.restrictions.isEmpty() == true) goto L8;
                Assertions.checkArgument(this.restrictions.equals(r5), "Can't change restrictions from " + Quirks$$ExternalSyntheticBackport0.m(", ", this.restrictions) + " to " + Quirks$$ExternalSyntheticBackport0.m(", ", r5));
            L8:
                this.restrictions = r5;
                return this;
            }

            public Builder setResumeOffsetUs(long r5) {
                if (r5 != (-9223372036854775807L)) goto L5;
                return this;
            L5:
                long r2 = this.resumeOffsetUs;
                if (r2 != (-9223372036854775807L)) goto L8;
            L12:
                this.resumeOffsetUs = r5;
                return this;
            L8:
                if (r2 != r5) goto L10;
                boolean r0 = true;
            L11:
                Assertions.checkArgument(r0, "Can't change resumeOffsetUs from " + this.resumeOffsetUs + " to " + r5);
                goto L12
            L10:
                r0 = false;
                goto L11
            }

            public Builder setSnapTypes(List<String> r5) {
                if (r5.isEmpty() == false) goto L6;
                return this;
            L6:
                if (this.snapTypes.isEmpty() == true) goto L8;
                Assertions.checkArgument(this.snapTypes.equals(r5), "Can't change snapTypes from " + Quirks$$ExternalSyntheticBackport0.m(", ", this.snapTypes) + " to " + Quirks$$ExternalSyntheticBackport0.m(", ", r5));
            L8:
                this.snapTypes = r5;
                return this;
            }

            public Builder setStartDateUnixUs(long r5) {
                if (r5 != (-9223372036854775807L)) goto L5;
                return this;
            L5:
                long r2 = this.startDateUnixUs;
                if (r2 != (-9223372036854775807L)) goto L8;
            L12:
                this.startDateUnixUs = r5;
                return this;
            L8:
                if (r2 != r5) goto L10;
                boolean r0 = true;
            L11:
                Assertions.checkArgument(r0, "Can't change startDateUnixUs from " + this.startDateUnixUs + " to " + r5);
                goto L12
            L10:
                r0 = false;
                goto L11
            }

            public Builder setTimelineOccupies(String r4) {
                if (r4 != null) goto L4;
                return this;
            L4:
                String r0 = this.timelineOccupies;
                if (r0 == null) goto L7;
                Assertions.checkArgument(r0.equals(r4), "Can't change timelineOccupies from " + this.timelineOccupies + " to " + r4);
            L7:
                this.timelineOccupies = r4;
                return this;
            }

            public Builder setTimelineStyle(String r4) {
                if (r4 != null) goto L4;
                return this;
            L4:
                String r0 = this.timelineStyle;
                if (r0 == null) goto L7;
                Assertions.checkArgument(r0.equals(r4), "Can't change timelineStyle from " + this.timelineStyle + " to " + r4);
            L7:
                this.timelineStyle = r4;
                return this;
            }
        }

        public static /* synthetic */ int $r8$lambda$UpCUmLraRCymosAFSes06sHsPuY(ClientDefinedAttribute r0, ClientDefinedAttribute r1) {
            return r0.name.compareTo(r1.name);
        }

        public Interstitial(String r2, Uri r3, Uri r4, long r5, long r7, long r9, long r11, List<String> r13, boolean r14, long r15, long r17, List<String> r19, List<String> r20, List<ClientDefinedAttribute> r21, boolean r22, String r23, String r24) {
            if (r3 == null) goto L5;
            if (r4 == null) goto L5;
        L8:
            boolean r0 = false;
        L10:
            Assertions.checkArgument(r0);
            this.id = r2;
            this.assetUri = r3;
            this.assetListUri = r4;
            this.startDateUnixUs = r5;
            this.endDateUnixUs = r7;
            this.durationUs = r9;
            this.plannedDurationUs = r11;
            this.cue = r13;
            this.endOnNext = r14;
            this.resumeOffsetUs = r15;
            this.playoutLimitUs = r17;
            this.snapTypes = ImmutableList.copyOf(r19);
            this.restrictions = ImmutableList.copyOf(r20);
            this.clientDefinedAttributes = ImmutableList.sortedCopyOf(new HlsMediaPlaylist$Interstitial$$ExternalSyntheticLambda0(), r21);
            this.contentMayVary = r22;
            this.timelineOccupies = r23;
            this.timelineStyle = r24;
            return;
        L5:
            if (r3 != null) goto L9;
            if (r4 == null) goto L8;
        L9:
            r0 = true;
            goto L10
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof Interstitial) == true) goto L8;
            return false;
        L8:
            Interstitial r82 = (Interstitial) r8;
            if (this.startDateUnixUs == r82.startDateUnixUs) goto L11;
        L43:
            return false;
        L11:
            if (this.endDateUnixUs != r82.endDateUnixUs) goto L43;
            if (this.durationUs != r82.durationUs) goto L43;
            if (this.plannedDurationUs != r82.plannedDurationUs) goto L43;
            if (this.endOnNext != r82.endOnNext) goto L43;
            if (this.resumeOffsetUs != r82.resumeOffsetUs) goto L43;
            if (this.playoutLimitUs != r82.playoutLimitUs) goto L43;
            if (this.contentMayVary != r82.contentMayVary) goto L43;
            if (Objects.equals(this.id, r82.id) == false) goto L43;
            if (Objects.equals(this.assetUri, r82.assetUri) == false) goto L43;
            if (Objects.equals(this.assetListUri, r82.assetListUri) == false) goto L43;
            if (Objects.equals(this.cue, r82.cue) == false) goto L43;
            if (Objects.equals(this.snapTypes, r82.snapTypes) == false) goto L43;
            if (Objects.equals(this.restrictions, r82.restrictions) == false) goto L43;
            if (Objects.equals(this.clientDefinedAttributes, r82.clientDefinedAttributes) == false) goto L43;
            if (Objects.equals(this.timelineOccupies, r82.timelineOccupies) == false) goto L43;
            if (Objects.equals(this.timelineStyle, r82.timelineStyle) == false) goto L43;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.id, this.assetUri, this.assetListUri, Long.valueOf(this.startDateUnixUs), Long.valueOf(this.endDateUnixUs), Long.valueOf(this.durationUs), Long.valueOf(this.plannedDurationUs), this.cue, Boolean.valueOf(this.endOnNext), Long.valueOf(this.resumeOffsetUs), Long.valueOf(this.playoutLimitUs), this.snapTypes, this.restrictions, this.clientDefinedAttributes, Boolean.valueOf(this.contentMayVary), this.timelineOccupies, this.timelineStyle});
        }
    }

    public static final class Part extends SegmentBase {
        public final boolean isIndependent;
        public final boolean isPreload;

        public Part(String r18, Segment r19, long r20, int r22, long r23, DrmInitData r25, String r26, String r27, long r28, long r30, boolean r32, boolean r33, boolean r34) {
            super(r18, r19, r20, r22, r23, r25, r26, r27, r28, r30, r32, null);
            this.isIndependent = r33;
            this.isPreload = r34;
        }

        public Part copyWith(long r19, int r21) {
            return new Part(this.url, this.initializationSegment, this.durationUs, r21, r19, this.drmInitData, this.fullSegmentEncryptionKeyUri, this.encryptionIV, this.byteRangeOffset, this.byteRangeLength, this.hasGapTag, this.isIndependent, this.isPreload);
        }
    }

    public static final class RenditionReport {
        public final long lastMediaSequence;
        public final int lastPartIndex;
        public final Uri playlistUri;

        public RenditionReport(Uri r1, long r2, int r4) {
            this.playlistUri = r1;
            this.lastMediaSequence = r2;
            this.lastPartIndex = r4;
        }
    }

    public static final class Segment extends SegmentBase {
        public final List<Part> parts;
        public final String title;

        public Segment(String r19, long r20, long r22, String r24, String r25) {
            this(r19, null, _UrlKt.FRAGMENT_ENCODE_SET, 0, -1, -9223372036854775807L, null, r24, r25, r20, r22, false, ImmutableList.of());
        }

        public Segment(String r18, Segment r19, String r20, long r21, int r23, long r24, DrmInitData r26, String r27, String r28, long r29, long r31, boolean r33, List<Part> r34) {
            super(r18, r19, r21, r23, r24, r26, r27, r28, r29, r31, r33, null);
            this.title = r20;
            this.parts = ImmutableList.copyOf(r34);
        }

        public Segment copyWith(long r19, int r21) {
            ArrayList r1 = new ArrayList();
            int r2 = 0;
            long r3 = r19;
        L4:
            if (r2 >= this.parts.size()) goto L7;
            Part r5 = this.parts.get(r2);
            r1.add(r5.copyWith(r3, r21));
            r3 = r3 + r5.durationUs;
            r2 = r2 + 1;
            goto L4
        L7:
            return new Segment(this.url, this.initializationSegment, this.title, this.durationUs, r21, r19, this.drmInitData, this.fullSegmentEncryptionKeyUri, this.encryptionIV, this.byteRangeOffset, this.byteRangeLength, this.hasGapTag, r1);
        }
    }

    public static class SegmentBase implements Comparable<Long> {
        public final long byteRangeLength;
        public final long byteRangeOffset;
        public final DrmInitData drmInitData;
        public final long durationUs;
        public final String encryptionIV;
        public final String fullSegmentEncryptionKeyUri;
        public final boolean hasGapTag;
        public final Segment initializationSegment;
        public final int relativeDiscontinuitySequence;
        public final long relativeStartTimeUs;
        public final String url;

        private SegmentBase(String r1, Segment r2, long r3, int r5, long r6, DrmInitData r8, String r9, String r10, long r11, long r13, boolean r15) {
            this.url = r1;
            this.initializationSegment = r2;
            this.durationUs = r3;
            this.relativeDiscontinuitySequence = r5;
            this.relativeStartTimeUs = r6;
            this.drmInitData = r8;
            this.fullSegmentEncryptionKeyUri = r9;
            this.encryptionIV = r10;
            this.byteRangeOffset = r11;
            this.byteRangeLength = r13;
            this.hasGapTag = r15;
        }

        public /* synthetic */ SegmentBase(String r1, Segment r2, long r3, int r5, long r6, DrmInitData r8, String r9, String r10, long r11, long r13, boolean r15, AnonymousClass1 r16) {
            this(r1, r2, r3, r5, r6, r8, r9, r10, r11, r13, r15);
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Long r5) {
            if (this.relativeStartTimeUs <= r5.longValue()) goto L7;
            return 1;
        L7:
            if (this.relativeStartTimeUs >= r5.longValue()) goto L10;
            return -1;
        L10:
            return 0;
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Long r1) {
            return compareTo2(r1);
        }
    }

    public static final class ServerControl {
        public final boolean canBlockReload;
        public final boolean canSkipDateRanges;
        public final long holdBackUs;
        public final long partHoldBackUs;
        public final long skipUntilUs;

        public ServerControl(long r1, boolean r3, long r4, long r6, boolean r8) {
            this.skipUntilUs = r1;
            this.canSkipDateRanges = r3;
            this.holdBackUs = r4;
            this.partHoldBackUs = r6;
            this.canBlockReload = r8;
        }
    }

    public HlsMediaPlaylist(int r5, String r6, List<String> r7, long r8, boolean r10, long r11, boolean r13, int r14, long r15, int r17, long r18, long r20, boolean r22, boolean r23, boolean r24, DrmInitData r25, List<Segment> r26, List<Part> r27, ServerControl r28, Map<Uri, RenditionReport> r29, List<Interstitial> r30) {
        super(r6, r7, r22);
        this.playlistType = r5;
        this.startTimeUs = r11;
        this.preciseStart = r10;
        this.hasDiscontinuitySequence = r13;
        this.discontinuitySequence = r14;
        this.mediaSequence = r15;
        this.version = r17;
        this.targetDurationUs = r18;
        this.partTargetDurationUs = r20;
        this.hasEndTag = r23;
        this.hasProgramDateTime = r24;
        this.protectionSchemes = r25;
        this.segments = ImmutableList.copyOf(r26);
        this.trailingParts = ImmutableList.copyOf(r27);
        this.renditionReports = ImmutableMap.copyOf(r29);
        this.interstitials = ImmutableList.copyOf(r30);
        if (r27.isEmpty() == true) goto L6;
        Part r52 = (Part) Iterables.getLast(r27);
        this.durationUs = r52.relativeStartTimeUs + r52.durationUs;
    L9:
        long r0 = -9223372036854775807L;
        if (r8 == (-9223372036854775807L)) goto L16;
        long r02 = this.durationUs;
        if (r8 < 0) goto L15;
        r0 = Math.min(r02, r8);
        goto L16
    L15:
        r0 = Math.max(0, r02 + r8);
    L16:
        this.startOffsetUs = r0;
        if (r8 < 0) goto L19;
        boolean r53 = true;
    L20:
        this.hasPositiveStartOffset = r53;
        this.serverControl = r28;
        return;
    L19:
        r53 = false;
        goto L20
    L6:
        if (r26.isEmpty() == true) goto L8;
        Segment r54 = (Segment) Iterables.getLast(r26);
        this.durationUs = r54.relativeStartTimeUs + r54.durationUs;
        goto L9
    L8:
        this.durationUs = 0;
        goto L9
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public HlsPlaylist copy(List<StreamKey> r1) {
        return this;
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    /* renamed from: copy, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ HlsPlaylist copy2(List r1) {
        return copy(r1);
    }

    public HlsMediaPlaylist copyWith(long r28, int r30) {
        return new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, this.preciseStart, r28, true, r30, this.mediaSequence, this.version, this.targetDurationUs, this.partTargetDurationUs, this.hasIndependentSegments, this.hasEndTag, this.hasProgramDateTime, this.protectionSchemes, this.segments, this.trailingParts, this.serverControl, this.renditionReports, this.interstitials);
    }

    public HlsMediaPlaylist copyWithEndTag() {
        if (this.hasEndTag == false) goto L6;
        return this;
    L6:
        return new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, this.preciseStart, this.startTimeUs, this.hasDiscontinuitySequence, this.discontinuitySequence, this.mediaSequence, this.version, this.targetDurationUs, this.partTargetDurationUs, this.hasIndependentSegments, true, this.hasProgramDateTime, this.protectionSchemes, this.segments, this.trailingParts, this.serverControl, this.renditionReports, this.interstitials);
    }

    public long getEndTimeUs() {
        return this.startTimeUs + this.durationUs;
    }

    public boolean isNewerThan(HlsMediaPlaylist r7) {
        if (r7 == null) goto L24;
        long r1 = this.mediaSequence;
        long r3 = r7.mediaSequence;
        if (r1 > r3) goto L24;
        if (r1 >= r3) goto L10;
        return false;
    L10:
        int r12 = this.segments.size() - r7.segments.size();
        if (r12 == 0) goto L15;
        if (r12 <= 0) goto L14;
        return true;
    L14:
        return false;
    L15:
        int r13 = this.trailingParts.size();
        int r32 = r7.trailingParts.size();
        if (r13 > r32) goto L24;
        if (r13 == r32) goto L19;
    L23:
        return false;
    L19:
        if (this.hasEndTag == false) goto L23;
        if (r7.hasEndTag == true) goto L23;
    L24:
        return true;
    }
}
