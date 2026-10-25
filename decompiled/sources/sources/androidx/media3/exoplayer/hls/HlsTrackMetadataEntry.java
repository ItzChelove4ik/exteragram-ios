package androidx.media3.exoplayer.hls;

import android.text.TextUtils;
import androidx.media3.common.Metadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class HlsTrackMetadataEntry implements Metadata.Entry {
    public final String groupId;
    public final String name;
    public final List<VariantInfo> variantInfos;

    public static final class VariantInfo {
        public final String audioGroupId;
        public final int averageBitrate;
        public final String captionGroupId;
        public final int peakBitrate;
        public final String subtitleGroupId;
        public final String videoGroupId;

        public VariantInfo(int r1, int r2, String r3, String r4, String r5, String r6) {
            this.averageBitrate = r1;
            this.peakBitrate = r2;
            this.videoGroupId = r3;
            this.audioGroupId = r4;
            this.subtitleGroupId = r5;
            this.captionGroupId = r6;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L23:
            return false;
        L8:
            if (VariantInfo.class != r5.getClass()) goto L23;
            VariantInfo r52 = (VariantInfo) r5;
            if (this.averageBitrate != r52.averageBitrate) goto L23;
            if (this.peakBitrate != r52.peakBitrate) goto L23;
            if (TextUtils.equals(this.videoGroupId, r52.videoGroupId) == false) goto L23;
            if (TextUtils.equals(this.audioGroupId, r52.audioGroupId) == false) goto L23;
            if (TextUtils.equals(this.subtitleGroupId, r52.subtitleGroupId) == false) goto L23;
            if (TextUtils.equals(this.captionGroupId, r52.captionGroupId) == false) goto L23;
            return true;
        }

        public int hashCode() {
            int r0 = ((this.averageBitrate * 31) + this.peakBitrate) * 31;
            String r1 = this.videoGroupId;
            int r2 = 0;
            if (r1 == null) goto L5;
            int r12 = r1.hashCode();
        L6:
            int r02 = (r0 + r12) * 31;
            String r13 = this.audioGroupId;
            if (r13 == null) goto L9;
            int r14 = r13.hashCode();
        L10:
            int r03 = (r02 + r14) * 31;
            String r15 = this.subtitleGroupId;
            if (r15 == null) goto L13;
            int r16 = r15.hashCode();
        L14:
            int r04 = (r03 + r16) * 31;
            String r3 = this.captionGroupId;
            if (r3 == null) goto L18;
            r2 = r3.hashCode();
        L18:
            return r04 + r2;
        L13:
            r16 = 0;
            goto L14
        L9:
            r14 = 0;
            goto L10
        L5:
            r12 = 0;
            goto L6
        }
    }

    public HlsTrackMetadataEntry(String r1, String r2, List<VariantInfo> r3) {
        this.groupId = r1;
        this.name = r2;
        this.variantInfos = Collections.unmodifiableList(new ArrayList(r3));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (HlsTrackMetadataEntry.class != r5.getClass()) goto L17;
        HlsTrackMetadataEntry r52 = (HlsTrackMetadataEntry) r5;
        if (TextUtils.equals(this.groupId, r52.groupId) == false) goto L17;
        if (TextUtils.equals(this.name, r52.name) == false) goto L17;
        if (this.variantInfos.equals(r52.variantInfos) == false) goto L17;
        return true;
    }

    public int hashCode() {
        String r0 = this.groupId;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r03 = r02 * 31;
        String r2 = this.name;
        if (r2 == null) goto L10;
        r1 = r2.hashCode();
    L10:
        return ((r03 + r1) * 31) + this.variantInfos.hashCode();
    L5:
        r02 = 0;
        goto L6
    }

    public String toString() {
        if (this.groupId == null) goto L5;
        String r2 = " [" + this.groupId + ", " + this.name + "]";
    L7:
        return "HlsTrackMetadataEntry".concat(r2);
    L5:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }
}
