.class public abstract Lcom/google/android/recaptcha/internal/zztj;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza([J[JI)V
    .locals 5

    const/4 v0, 0x0

    :goto_0
    const/16 v1, 0xa

    if-ge v0, v1, :cond_0

    neg-int v1, p2

    .line 1
    aget-wide v2, p0, v0

    long-to-int v2, v2

    aget-wide v3, p1, v0

    long-to-int v3, v3

    xor-int/2addr v3, v2

    and-int/2addr v1, v3

    xor-int/2addr v1, v2

    int-to-long v1, v1

    .line 2
    aput-wide v1, p0, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method
