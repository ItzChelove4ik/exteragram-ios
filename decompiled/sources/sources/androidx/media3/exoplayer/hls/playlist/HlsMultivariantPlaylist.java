package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.StreamKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* loaded from: classes4.dex */
public final class HlsMultivariantPlaylist extends HlsPlaylist {
    public static final HlsMultivariantPlaylist EMPTY = null;
    public final List<Rendition> audios;
    public final List<Rendition> closedCaptions;
    public final List<Uri> mediaPlaylistUrls;
    public final Format muxedAudioFormat;
    public final List<Format> muxedCaptionFormats;
    public final List<DrmInitData> sessionKeyDrmInitData;
    public final List<Rendition> subtitles;
    public final Map<String, String> variableDefinitions;
    public final List<Variant> variants;
    public final List<Rendition> videos;

    public static final class Rendition {
        public final Format format;
        public final String groupId;
        public final String name;
        public final Uri url;

        public Rendition(Uri r1, Format r2, String r3, String r4) {
            this.url = r1;
            this.format = r2;
            this.groupId = r3;
            this.name = r4;
        }
    }

    public static final class Variant {
        public final String audioGroupId;
        public final String captionGroupId;
        public final Format format;
        public final String subtitleGroupId;
        public final Uri url;
        public final String videoGroupId;

        public Variant(Uri r1, Format r2, String r3, String r4, String r5, String r6) {
            this.url = r1;
            this.format = r2;
            this.videoGroupId = r3;
            this.audioGroupId = r4;
            this.subtitleGroupId = r5;
            this.captionGroupId = r6;
        }

        public static Variant createMediaPlaylistVariantUrl(Uri r8) {
            return new Variant(r8, new Format.Builder().setId(MVEL.VERSION_SUB).setContainerMimeType("application/x-mpegURL").build(), null, null, null, null);
        }

        public Variant copyWithFormat(Format r8) {
            return new Variant(this.url, r8, this.videoGroupId, this.audioGroupId, this.subtitleGroupId, this.captionGroupId);
        }
    }

    static {
        List r2 = Collections.EMPTY_LIST;
        EMPTY = new HlsMultivariantPlaylist(_UrlKt.FRAGMENT_ENCODE_SET, r2, r2, r2, r2, r2, r2, null, r2, false, Collections.EMPTY_MAP, r2);
    }

    public HlsMultivariantPlaylist(String r1, List<String> r2, List<Variant> r3, List<Rendition> r4, List<Rendition> r5, List<Rendition> r6, List<Rendition> r7, Format r8, List<Format> r9, boolean r10, Map<String, String> r11, List<DrmInitData> r12) {
        super(r1, r2, r10);
        this.mediaPlaylistUrls = Collections.unmodifiableList(getMediaPlaylistUrls(r3, r4, r5, r6, r7));
        this.variants = Collections.unmodifiableList(r3);
        this.videos = Collections.unmodifiableList(r4);
        this.audios = Collections.unmodifiableList(r5);
        this.subtitles = Collections.unmodifiableList(r6);
        this.closedCaptions = Collections.unmodifiableList(r7);
        this.muxedAudioFormat = r8;
        if (r9 == null) goto L5;
        List<Format> r13 = Collections.unmodifiableList(r9);
    L6:
        this.muxedCaptionFormats = r13;
        this.variableDefinitions = Collections.unmodifiableMap(r11);
        this.sessionKeyDrmInitData = Collections.unmodifiableList(r12);
        return;
    L5:
        r13 = null;
        goto L6
    }

    private static void addMediaPlaylistUrls(List<Rendition> r3, List<Uri> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L11;
        Uri r1 = r3.get(r0).url;
        if (r1 == null) goto L10;
        if (r4.contains(r1) == true) goto L10;
        r4.add(r1);
    L10:
        r0 = r0 + 1;
        goto L4
    }

    private static <T> List<T> copyStreams(List<T> r7, int r8, List<StreamKey> r9) {
        ArrayList r0 = new ArrayList(r9.size());
        int r2 = 0;
    L4:
        if (r2 >= r7.size()) goto L15;
        T r3 = r7.get(r2);
        int r4 = 0;
    L7:
        if (r4 >= r9.size()) goto L14;
        StreamKey r5 = r9.get(r4);
        if (r5.groupIndex != r8) goto L13;
        if (r5.streamIndex != r2) goto L13;
        r0.add(r3);
    L13:
        r4 = r4 + 1;
    L14:
        r2 = r2 + 1;
        goto L4
    L15:
        return r0;
    }

    public static HlsMultivariantPlaylist createSingleVariantMultivariantPlaylist(String r13) {
        List r3 = Collections.singletonList(Variant.createMediaPlaylistVariantUrl(Uri.parse(r13)));
        List r2 = Collections.EMPTY_LIST;
        return new HlsMultivariantPlaylist(_UrlKt.FRAGMENT_ENCODE_SET, r2, r3, r2, r2, r2, r2, null, null, false, Collections.EMPTY_MAP, r2);
    }

    private static List<Uri> getMediaPlaylistUrls(List<Variant> r4, List<Rendition> r5, List<Rendition> r6, List<Rendition> r7, List<Rendition> r8) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L9;
        Uri r2 = r4.get(r1).url;
        if (r0.contains(r2) == true) goto L8;
        r0.add(r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        addMediaPlaylistUrls(r5, r0);
        addMediaPlaylistUrls(r6, r0);
        addMediaPlaylistUrls(r7, r0);
        addMediaPlaylistUrls(r8, r0);
        return r0;
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public HlsPlaylist copy(List<StreamKey> r14) {
        String r1 = this.baseUri;
        List<String> r2 = this.tags;
        List r3 = copyStreams(this.variants, 0, r14);
        List r4 = Collections.EMPTY_LIST;
        return new HlsMultivariantPlaylist(r1, r2, r3, r4, copyStreams(this.audios, 1, r14), copyStreams(this.subtitles, 2, r14), r4, this.muxedAudioFormat, this.muxedCaptionFormats, this.hasIndependentSegments, this.variableDefinitions, this.sessionKeyDrmInitData);
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    /* renamed from: copy, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ HlsPlaylist copy2(List r1) {
        return copy(r1);
    }
}
