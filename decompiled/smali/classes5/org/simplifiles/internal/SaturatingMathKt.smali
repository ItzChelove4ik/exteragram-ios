.class public abstract Lorg/simplifiles/internal/SaturatingMathKt;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u00a8\u0006\u0004"
    }
    d2 = {
        "saturatingPlus",
        "",
        "current",
        "added",
        "simplifiles"
    }
    k = 0x2
    mv = {
        0x2,
        0x3,
        0x0
    }
    xi = 0x30
.end annotation


# direct methods
.method public static final saturatingPlus(JJ)J
    .locals 4

    const-wide v0, 0x7fffffffffffffffL

    sub-long v2, v0, p0

    cmp-long v2, v2, p2

    if-gez v2, :cond_0

    return-wide v0

    :cond_0
    add-long/2addr p0, p2

    return-wide p0
.end method
