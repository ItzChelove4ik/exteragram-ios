package com.exteragram.messenger.api.dto;

import com.exteragram.messenger.api.model.ProfileStatus;
import com.exteragram.messenger.api.model.ProfileType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010%\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u001cJ\\\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020\r2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020-HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001e\u0010\u001c¨\u0006."}, d2 = {"Lcom/exteragram/messenger/api/dto/ProfileDTO;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "type", "Lcom/exteragram/messenger/api/model/ProfileType;", "status", "Lcom/exteragram/messenger/api/model/ProfileStatus;", "badge", "Lcom/exteragram/messenger/api/dto/BadgeDTO;", "nowPlaying", "Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;", "deleted", _UrlKt.FRAGMENT_ENCODE_SET, "canChangeBadge", "<init>", "(JLcom/exteragram/messenger/api/model/ProfileType;Lcom/exteragram/messenger/api/model/ProfileStatus;Lcom/exteragram/messenger/api/dto/BadgeDTO;Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getId", "()J", "getType", "()Lcom/exteragram/messenger/api/model/ProfileType;", "getStatus", "()Lcom/exteragram/messenger/api/model/ProfileStatus;", "getBadge", "()Lcom/exteragram/messenger/api/dto/BadgeDTO;", "getNowPlaying", "()Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;", "getDeleted", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCanChangeBadge", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JLcom/exteragram/messenger/api/model/ProfileType;Lcom/exteragram/messenger/api/model/ProfileStatus;Lcom/exteragram/messenger/api/dto/BadgeDTO;Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/exteragram/messenger/api/dto/ProfileDTO;", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProfileDTO {
    private final BadgeDTO badge;
    private final Boolean canChangeBadge;
    private final Boolean deleted;
    private final long id;
    private final NowPlayingInfoDTO nowPlaying;
    private final ProfileStatus status;
    private final ProfileType type;

    public ProfileDTO(long r1, ProfileType r3, ProfileStatus r4, BadgeDTO r5, NowPlayingInfoDTO r6, Boolean r7, Boolean r8) {
        this.id = r1;
        this.type = r3;
        this.status = r4;
        this.badge = r5;
        this.nowPlaying = r6;
        this.deleted = r7;
        this.canChangeBadge = r8;
    }

    public static /* synthetic */ ProfileDTO copy$default(ProfileDTO r9, long r10, ProfileType r12, ProfileStatus r13, BadgeDTO r14, NowPlayingInfoDTO r15, Boolean r16, Boolean r17, int r18, Object r19) {
        if ((r18 & 1) == 0) goto L5;
        r10 = r9.id;
    L5:
        long r1 = r10;
        if ((r18 & 2) == 0) goto L8;
        r12 = r9.type;
    L8:
        ProfileType r3 = r12;
        if ((r18 & 4) == 0) goto L11;
        r13 = r9.status;
    L11:
        ProfileStatus r4 = r13;
        if ((r18 & 8) == 0) goto L14;
        r14 = r9.badge;
    L14:
        BadgeDTO r5 = r14;
        if ((r18 & 16) == 0) goto L17;
        r15 = r9.nowPlaying;
    L17:
        NowPlayingInfoDTO r6 = r15;
        if ((r18 & 32) == 0) goto L20;
        Boolean r7 = r9.deleted;
    L22:
        if ((r18 & 64) == 0) goto L25;
        Boolean r8 = r9.canChangeBadge;
    L27:
        return r9.copy(r1, r3, r4, r5, r6, r7, r8);
    L25:
        r8 = r17;
        goto L27
    L20:
        r7 = r16;
        goto L22
    }

    public final long component1() {
        return this.id;
    }

    public final ProfileType component2() {
        return this.type;
    }

    public final ProfileStatus component3() {
        return this.status;
    }

    public final BadgeDTO component4() {
        return this.badge;
    }

    public final NowPlayingInfoDTO component5() {
        return this.nowPlaying;
    }

    public final Boolean component6() {
        return this.deleted;
    }

    public final Boolean component7() {
        return this.canChangeBadge;
    }

    public final ProfileDTO copy(long r1, ProfileType r3, ProfileStatus r4, BadgeDTO r5, NowPlayingInfoDTO r6, Boolean r7, Boolean r8) {
        return new ProfileDTO(r1, r3, r4, r5, r6, r7, r8);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof ProfileDTO) == true) goto L8;
        return false;
    L8:
        ProfileDTO r82 = (ProfileDTO) r8;
        if (this.id == r82.id) goto L12;
        return false;
    L12:
        if (this.type == r82.type) goto L15;
        return false;
    L15:
        if (this.status == r82.status) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.badge, r82.badge) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.nowPlaying, r82.nowPlaying) == true) goto L24;
        return false;
    L24:
        if (Intrinsics.areEqual(this.deleted, r82.deleted) == true) goto L27;
        return false;
    L27:
        if (Intrinsics.areEqual(this.canChangeBadge, r82.canChangeBadge) == true) goto L29;
        return false;
    L29:
        return true;
    }

    public final BadgeDTO getBadge() {
        return this.badge;
    }

    public final Boolean getCanChangeBadge() {
        return this.canChangeBadge;
    }

    public final Boolean getDeleted() {
        return this.deleted;
    }

    public final long getId() {
        return this.id;
    }

    public final NowPlayingInfoDTO getNowPlaying() {
        return this.nowPlaying;
    }

    public final ProfileStatus getStatus() {
        return this.status;
    }

    public final ProfileType getType() {
        return this.type;
    }

    public int hashCode() {
        int r0 = ((((Long.hashCode(this.id) * 31) + this.type.hashCode()) * 31) + this.status.hashCode()) * 31;
        BadgeDTO r1 = this.badge;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        NowPlayingInfoDTO r13 = this.nowPlaying;
        if (r13 != null) goto L9;
        int r14 = 0;
    L10:
        int r03 = (r02 + r14) * 31;
        Boolean r15 = this.deleted;
        if (r15 != null) goto L13;
        int r16 = 0;
    L14:
        int r04 = (r03 + r16) * 31;
        Boolean r3 = this.canChangeBadge;
        if (r3 == null) goto L19;
        r2 = r3.hashCode();
    L19:
        return r04 + r2;
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

    public String toString() {
        return "ProfileDTO(id=" + this.id + ", type=" + this.type + ", status=" + this.status + ", badge=" + this.badge + ", nowPlaying=" + this.nowPlaying + ", deleted=" + this.deleted + ", canChangeBadge=" + this.canChangeBadge + ")";
    }
}
