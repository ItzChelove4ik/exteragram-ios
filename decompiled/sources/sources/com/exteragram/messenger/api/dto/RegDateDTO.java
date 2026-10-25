package com.exteragram.messenger.api.dto;

import com.exteragram.messenger.api.model.RegDateFlag;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/exteragram/messenger/api/dto/RegDateDTO;", _UrlKt.FRAGMENT_ENCODE_SET, "timestamp", _UrlKt.FRAGMENT_ENCODE_SET, "accuracy", _UrlKt.FRAGMENT_ENCODE_SET, "flag", "Lcom/exteragram/messenger/api/model/RegDateFlag;", "date", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JDLcom/exteragram/messenger/api/model/RegDateFlag;Ljava/lang/String;)V", "getTimestamp", "()J", "getAccuracy", "()D", "getFlag", "()Lcom/exteragram/messenger/api/model/RegDateFlag;", "getDate", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class RegDateDTO {
    private final double accuracy;
    private final String date;
    private final RegDateFlag flag;
    private final long timestamp;

    public RegDateDTO(long r1, double r3, RegDateFlag r5, String r6) {
        this.timestamp = r1;
        this.accuracy = r3;
        this.flag = r5;
        this.date = r6;
    }

    public static /* synthetic */ RegDateDTO copy$default(RegDateDTO r7, long r8, double r10, RegDateFlag r12, String r13, int r14, Object r15) {
        if ((r14 & 1) == 0) goto L5;
        r8 = r7.timestamp;
    L5:
        long r1 = r8;
        if ((r14 & 2) == 0) goto L8;
        r10 = r7.accuracy;
    L8:
        double r3 = r10;
        if ((r14 & 4) == 0) goto L11;
        r12 = r7.flag;
    L11:
        RegDateFlag r5 = r12;
        if ((r14 & 8) == 0) goto L15;
        r13 = r7.date;
    L15:
        return r7.copy(r1, r3, r5, r13);
    }

    public final long component1() {
        return this.timestamp;
    }

    public final double component2() {
        return this.accuracy;
    }

    public final RegDateFlag component3() {
        return this.flag;
    }

    public final String component4() {
        return this.date;
    }

    public final RegDateDTO copy(long r1, double r3, RegDateFlag r5, String r6) {
        return new RegDateDTO(r1, r3, r5, r6);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof RegDateDTO) == true) goto L8;
        return false;
    L8:
        RegDateDTO r82 = (RegDateDTO) r8;
        if (this.timestamp == r82.timestamp) goto L12;
        return false;
    L12:
        if (Double.compare(this.accuracy, r82.accuracy) == 0) goto L15;
        return false;
    L15:
        if (this.flag == r82.flag) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.date, r82.date) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public final double getAccuracy() {
        return this.accuracy;
    }

    public final String getDate() {
        return this.date;
    }

    public final RegDateFlag getFlag() {
        return this.flag;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.timestamp) * 31) + Double.hashCode(this.accuracy)) * 31) + this.flag.hashCode()) * 31) + this.date.hashCode();
    }

    public String toString() {
        return "RegDateDTO(timestamp=" + this.timestamp + ", accuracy=" + this.accuracy + ", flag=" + this.flag + ", date=" + this.date + ")";
    }
}
