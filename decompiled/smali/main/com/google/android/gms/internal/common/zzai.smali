.class public abstract Lcom/google/android/gms/internal/common/zzai;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza([Ljava/lang/Object;I)[Ljava/lang/Object;
    .locals 2

    const/4 v0, 0x0

    :goto_0
    if-ge v0, p1, :cond_1

    .line 1
    aget-object v1, p0, v0

    if-eqz v1, :cond_0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 2
    :cond_0
    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x9

    invoke-static {p0, v0}, Lcom/google/android/gms/internal/cast/zzib$$ExternalSyntheticBUOutline0;->m(II)V

    const/4 p0, 0x0

    :cond_1
    return-object p0
.end method
