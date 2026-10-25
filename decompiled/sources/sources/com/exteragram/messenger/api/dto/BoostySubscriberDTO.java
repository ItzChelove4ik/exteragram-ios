package com.exteragram.messenger.api.dto;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "totalAmountRub", "Ljava/math/BigDecimal;", "totalAmountUsd", "<init>", "(JLjava/lang/String;Ljava/math/BigDecimal;Ljava/math/BigDecimal;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getTotalAmountRub", "()Ljava/math/BigDecimal;", "getTotalAmountUsd", "component1", "component2", "component3", "component4", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BoostySubscriberDTO {
    private final long id;
    private final String name;
    private final BigDecimal totalAmountRub;
    private final BigDecimal totalAmountUsd;

    public BoostySubscriberDTO(long r1, String r3, BigDecimal r4, BigDecimal r5) {
        this.id = r1;
        this.name = r3;
        this.totalAmountRub = r4;
        this.totalAmountUsd = r5;
    }

    public static /* synthetic */ BoostySubscriberDTO copy$default(BoostySubscriberDTO r6, long r7, String r9, BigDecimal r10, BigDecimal r11, int r12, Object r13) {
        if ((r12 & 1) == 0) goto L5;
        r7 = r6.id;
    L5:
        long r1 = r7;
        if ((r12 & 2) == 0) goto L8;
        r9 = r6.name;
    L8:
        String r3 = r9;
        if ((r12 & 4) == 0) goto L11;
        r10 = r6.totalAmountRub;
    L11:
        BigDecimal r4 = r10;
        if ((r12 & 8) == 0) goto L15;
        r11 = r6.totalAmountUsd;
    L15:
        return r6.copy(r1, r3, r4, r11);
    }

    public final long component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final BigDecimal component3() {
        return this.totalAmountRub;
    }

    public final BigDecimal component4() {
        return this.totalAmountUsd;
    }

    public final BoostySubscriberDTO copy(long r1, String r3, BigDecimal r4, BigDecimal r5) {
        return new BoostySubscriberDTO(r1, r3, r4, r5);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof BoostySubscriberDTO) == true) goto L8;
        return false;
    L8:
        BoostySubscriberDTO r82 = (BoostySubscriberDTO) r8;
        if (this.id == r82.id) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.name, r82.name) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.totalAmountRub, r82.totalAmountRub) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.totalAmountUsd, r82.totalAmountUsd) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final BigDecimal getTotalAmountRub() {
        return this.totalAmountRub;
    }

    public final BigDecimal getTotalAmountUsd() {
        return this.totalAmountUsd;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + this.totalAmountRub.hashCode()) * 31) + this.totalAmountUsd.hashCode();
    }

    public String toString() {
        return "BoostySubscriberDTO(id=" + this.id + ", name=" + this.name + ", totalAmountRub=" + this.totalAmountRub + ", totalAmountUsd=" + this.totalAmountUsd + ")";
    }
}
