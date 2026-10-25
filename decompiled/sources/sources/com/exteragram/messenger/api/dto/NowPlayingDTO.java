package com.exteragram.messenger.api.dto;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0011\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0088\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020\u000b2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001aR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001e¨\u00061"}, d2 = {"Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", _UrlKt.FRAGMENT_ENCODE_SET, "trackName", _UrlKt.FRAGMENT_ENCODE_SET, "artists", _UrlKt.FRAGMENT_ENCODE_SET, "albumName", "coverUrl", "previewUrl", "songUrl", "isPlaying", _UrlKt.FRAGMENT_ENCODE_SET, "deviceName", "platform", "duration", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getTrackName", "()Ljava/lang/String;", "getArtists", "()Ljava/util/List;", "getAlbumName", "getCoverUrl", "getPreviewUrl", "getSongUrl", "()Z", "getDeviceName", "getPlatform", "getDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingDTO {
    private final String albumName;
    private final List<String> artists;
    private final String coverUrl;
    private final String deviceName;
    private final Long duration;
    private final boolean isPlaying;
    private final String platform;
    private final String previewUrl;
    private final String songUrl;
    private final String trackName;

    public NowPlayingDTO(String r1, List<String> r2, String r3, String r4, String r5, String r6, boolean r7, String r8, String r9, Long r10) {
        this.trackName = r1;
        this.artists = r2;
        this.albumName = r3;
        this.coverUrl = r4;
        this.previewUrl = r5;
        this.songUrl = r6;
        this.isPlaying = r7;
        this.deviceName = r8;
        this.platform = r9;
        this.duration = r10;
    }

    public static /* synthetic */ NowPlayingDTO copy$default(NowPlayingDTO r0, String r1, List r2, String r3, String r4, String r5, String r6, boolean r7, String r8, String r9, Long r10, int r11, Object r12) {
        if ((r11 & 1) == 0) goto L6;
        r1 = r0.trackName;
    L6:
        if ((r11 & 2) == 0) goto L9;
        r2 = r0.artists;
    L9:
        if ((r11 & 4) == 0) goto L12;
        r3 = r0.albumName;
    L12:
        if ((r11 & 8) == 0) goto L15;
        r4 = r0.coverUrl;
    L15:
        if ((r11 & 16) == 0) goto L18;
        r5 = r0.previewUrl;
    L18:
        if ((r11 & 32) == 0) goto L21;
        r6 = r0.songUrl;
    L21:
        if ((r11 & 64) == 0) goto L24;
        r7 = r0.isPlaying;
    L24:
        if ((r11 & 128) == 0) goto L27;
        r8 = r0.deviceName;
    L27:
        if ((r11 & 256) == 0) goto L30;
        r9 = r0.platform;
    L30:
        if ((r11 & 512) == 0) goto L32;
        r10 = r0.duration;
    L32:
        String r112 = r9;
        Long r122 = r10;
        boolean r92 = r7;
        String r102 = r8;
        String r72 = r5;
        String r82 = r6;
        String r52 = r3;
        String r62 = r4;
        return r0.copy(r1, r2, r52, r62, r72, r82, r92, r102, r112, r122);
    }

    public final String component1() {
        return this.trackName;
    }

    public final Long component10() {
        return this.duration;
    }

    public final List<String> component2() {
        return this.artists;
    }

    public final String component3() {
        return this.albumName;
    }

    public final String component4() {
        return this.coverUrl;
    }

    public final String component5() {
        return this.previewUrl;
    }

    public final String component6() {
        return this.songUrl;
    }

    public final boolean component7() {
        return this.isPlaying;
    }

    public final String component8() {
        return this.deviceName;
    }

    public final String component9() {
        return this.platform;
    }

    public final NowPlayingDTO copy(String r1, List<String> r2, String r3, String r4, String r5, String r6, boolean r7, String r8, String r9, Long r10) {
        return new NowPlayingDTO(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof NowPlayingDTO) == true) goto L8;
        return false;
    L8:
        NowPlayingDTO r52 = (NowPlayingDTO) r5;
        if (Intrinsics.areEqual(this.trackName, r52.trackName) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.artists, r52.artists) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.albumName, r52.albumName) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.coverUrl, r52.coverUrl) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.previewUrl, r52.previewUrl) == true) goto L24;
        return false;
    L24:
        if (Intrinsics.areEqual(this.songUrl, r52.songUrl) == true) goto L27;
        return false;
    L27:
        if (this.isPlaying == r52.isPlaying) goto L30;
        return false;
    L30:
        if (Intrinsics.areEqual(this.deviceName, r52.deviceName) == true) goto L33;
        return false;
    L33:
        if (Intrinsics.areEqual(this.platform, r52.platform) == true) goto L36;
        return false;
    L36:
        if (Intrinsics.areEqual(this.duration, r52.duration) == true) goto L38;
        return false;
    L38:
        return true;
    }

    public final String getAlbumName() {
        return this.albumName;
    }

    public final List<String> getArtists() {
        return this.artists;
    }

    public final String getCoverUrl() {
        return this.coverUrl;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final Long getDuration() {
        return this.duration;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getPreviewUrl() {
        return this.previewUrl;
    }

    public final String getSongUrl() {
        return this.songUrl;
    }

    public final String getTrackName() {
        return this.trackName;
    }

    public int hashCode() {
        int r0 = this.trackName.hashCode() * 31;
        List<String> r1 = this.artists;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        String r13 = this.albumName;
        if (r13 != null) goto L9;
        int r14 = 0;
    L10:
        int r03 = (r02 + r14) * 31;
        String r15 = this.coverUrl;
        if (r15 != null) goto L13;
        int r16 = 0;
    L14:
        int r04 = (r03 + r16) * 31;
        String r17 = this.previewUrl;
        if (r17 != null) goto L17;
        int r18 = 0;
    L18:
        int r05 = (r04 + r18) * 31;
        String r19 = this.songUrl;
        if (r19 != null) goto L21;
        int r110 = 0;
    L22:
        int r06 = (((r05 + r110) * 31) + Boolean.hashCode(this.isPlaying)) * 31;
        String r111 = this.deviceName;
        if (r111 != null) goto L25;
        int r112 = 0;
    L26:
        int r07 = (r06 + r112) * 31;
        String r113 = this.platform;
        if (r113 != null) goto L29;
        int r114 = 0;
    L30:
        int r08 = (r07 + r114) * 31;
        Long r3 = this.duration;
        if (r3 == null) goto L35;
        r2 = r3.hashCode();
    L35:
        return r08 + r2;
    L29:
        r114 = r113.hashCode();
        goto L30
    L25:
        r112 = r111.hashCode();
        goto L26
    L21:
        r110 = r19.hashCode();
        goto L22
    L17:
        r18 = r17.hashCode();
        goto L18
    L13:
        r16 = r15.hashCode();
        goto L14
    L9:
        r14 = r13.hashCode();
        goto L10
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public final boolean isPlaying() {
        return this.isPlaying;
    }

    public String toString() {
        return "NowPlayingDTO(trackName=" + this.trackName + ", artists=" + this.artists + ", albumName=" + this.albumName + ", coverUrl=" + this.coverUrl + ", previewUrl=" + this.previewUrl + ", songUrl=" + this.songUrl + ", isPlaying=" + this.isPlaying + ", deviceName=" + this.deviceName + ", platform=" + this.platform + ", duration=" + this.duration + ")";
    }
}
