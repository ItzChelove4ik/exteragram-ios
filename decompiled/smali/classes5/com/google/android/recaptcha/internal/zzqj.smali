.class public abstract Lcom/google/android/recaptcha/internal/zzqj;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza(Ljava/lang/Object;I)Ljava/lang/Object;
    .locals 0

    if-eqz p0, :cond_0

    return-object p0

    .line 1
    :cond_0
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x9

    invoke-static {p0, p1}, Lcom/google/android/gms/internal/cast/zzib$$ExternalSyntheticBUOutline0;->m(II)V

    const/4 p0, 0x0

    return-object p0
.end method
