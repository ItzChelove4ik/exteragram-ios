package com.exteragram.messenger.api.dto;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/api/dto/BadgeDTO;", _UrlKt.FRAGMENT_ENCODE_SET, "documentId", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JLjava/lang/String;)V", "getDocumentId", "()J", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BadgeDTO {

    @SerializedName("documentId")
    private final long documentId;

    @SerializedName("text")
    private String text;

    public BadgeDTO(long r1, String r3) {
        this.documentId = r1;
        this.text = r3;
    }

    public static /* synthetic */ BadgeDTO copy$default(BadgeDTO r0, long r1, String r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = r0.documentId;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r3 = r0.text;
    L9:
        return r0.copy(r1, r3);
    }

    public final long component1() {
        return this.documentId;
    }

    public final String component2() {
        return this.text;
    }

    public final BadgeDTO copy(long r1, String r3) {
        return new BadgeDTO(r1, r3);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof BadgeDTO) == true) goto L8;
        return false;
    L8:
        BadgeDTO r82 = (BadgeDTO) r8;
        if (this.documentId == r82.documentId) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.text, r82.text) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final long getDocumentId() {
        return this.documentId;
    }

    public final String getText() {
        return this.text;
    }

    public int hashCode() {
        int r0 = Long.hashCode(this.documentId) * 31;
        String r2 = this.text;
        if (r2 != null) goto L5;
        int r22 = 0;
    L7:
        return r0 + r22;
    L5:
        r22 = r2.hashCode();
        goto L7
    }

    public final void setText(String r1) {
        this.text = r1;
    }

    public String toString() {
        return "BadgeDTO(documentId=" + this.documentId + ", text=" + this.text + ")";
    }
}
