.class public final Lcom/google/android/recaptcha/internal/zzf;
.super Ljava/lang/Exception;
.source "SourceFile"


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zze;Lcom/google/android/recaptcha/internal/zzc;J)V
    .locals 7

    .line 0
    const v0, 0x7338868f

    not-int v1, v0

    const v2, 0x4071620a

    and-int/2addr v1, v2

    const v2, 0x5f4c1b70

    or-int/2addr v1, v2

    const v2, 0x3839e00a

    and-int/2addr v0, v2

    const v2, 0x3a0e85b4

    or-int/2addr v0, v2

    add-int/2addr v1, v0

    const v0, -0x67b7cbb6

    sub-int/2addr v1, v0

    const v0, 0x3b69ecd

    const v2, 0x5e194eec

    rem-int/2addr v2, v0

    const v0, 0x19248b51

    not-int v3, v0

    const v4, 0x7522d808

    and-int/2addr v3, v4

    const v4, 0x660d8863

    or-int/2addr v3, v4

    const v4, 0x11a27088

    and-int/2addr v0, v4

    const v4, 0xc84a5f5

    or-int/2addr v0, v4

    add-int/2addr v3, v0

    const v0, 0x65995afc

    sub-int/2addr v3, v0

    const v0, 0x3189923b

    const v4, 0x4fa4b5a1

    rem-int/2addr v4, v0

    xor-int v0, v1, v2

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v0, v0, [Ljava/lang/Object;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zze;->zza()J

    move-result-wide v5

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const/4 v2, 0x0

    aput-object p1, v0, v2

    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzc;->zza()J

    move-result-wide v5

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const/4 v2, 0x1

    aput-object p1, v0, v2

    xor-int p1, v3, v4

    invoke-static {p3, p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p3

    aput-object p3, v0, p1

    const-string p1, "bk3t6gFTc30="

    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1, v0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    invoke-static {p2}, Ljava/util/Optional;->of(Ljava/lang/Object;)Ljava/util/Optional;

    invoke-static {p3}, Ljava/util/Optional;->of(Ljava/lang/Object;)Ljava/util/Optional;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/recaptcha/internal/zze;Ljava/lang/Throwable;)V
    .locals 3

    .line 0
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zze;->zza()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string v1, "bk0="

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1, p2}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    return-void
.end method
