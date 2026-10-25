.class public final Lcom/google/android/recaptcha/internal/zznb;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lorg/chromium/net/UrlResponseInfo;

.field private final zzb:[B


# direct methods
.method public constructor <init>(Lorg/chromium/net/UrlResponseInfo;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zznb;->zza:Lorg/chromium/net/UrlResponseInfo;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zznb;->zzb:[B

    return-void
.end method


# virtual methods
.method public final zza()[B
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznb;->zzb:[B

    return-object p0
.end method

.method public final zzb()V
    .locals 12

    .line 1
    :try_start_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznb;->zza:Lorg/chromium/net/UrlResponseInfo;

    invoke-virtual {p0}, Lorg/chromium/net/UrlResponseInfo;->getHttpStatusCode()I

    move-result p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const/16 v0, 0xc8

    if-eq p0, v0, :cond_4

    const/16 v0, 0x190

    if-eq p0, v0, :cond_3

    const/16 v0, 0x1f7

    if-eq p0, v0, :cond_2

    const/16 v0, 0x193

    if-eq p0, v0, :cond_1

    const/16 v0, 0x194

    if-eq p0, v0, :cond_0

    .line 8
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzo:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    goto :goto_0

    .line 5
    :cond_0
    new-instance v2, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzhe;->zzi:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v7, 0xc

    const/4 v8, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-direct/range {v2 .. v8}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v2

    goto :goto_0

    .line 6
    :cond_1
    new-instance v3, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v4, Lcom/google/android/recaptcha/internal/zzhf;->zzi:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v5, Lcom/google/android/recaptcha/internal/zzhe;->zzn:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v8, 0xc

    const/4 v9, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-direct/range {v3 .. v9}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v3

    goto :goto_0

    .line 4
    :cond_2
    new-instance v4, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v5, Lcom/google/android/recaptcha/internal/zzhf;->zzi:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v6, Lcom/google/android/recaptcha/internal/zzhe;->zzn:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v9, 0xc

    const/4 v10, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    invoke-direct/range {v4 .. v10}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v4

    goto :goto_0

    .line 7
    :cond_3
    new-instance v5, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v6, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v7, Lcom/google/android/recaptcha/internal/zzhe;->zzK:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v10, 0xc

    const/4 v11, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-direct/range {v5 .. v11}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v5

    .line 9
    :goto_0
    throw v1

    :cond_4
    return-void

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 2
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzv:Lcom/google/android/recaptcha/internal/zzhe;

    .line 3
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 2
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0
.end method

.method public final zzc()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznb;->zza:Lorg/chromium/net/UrlResponseInfo;

    invoke-virtual {p0}, Lorg/chromium/net/UrlResponseInfo;->getHttpStatusCode()I

    move-result p0

    return p0
.end method

.method public final zzd(Lcom/google/android/recaptcha/internal/zzagx;)Lcom/google/android/recaptcha/internal/zzagx;
    .locals 8

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zznb;->zzb()V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznb;->zzb:[B

    array-length v0, p0

    if-eqz v0, :cond_0

    .line 3
    :try_start_0
    invoke-interface {p1}, Lcom/google/android/recaptcha/internal/zzagx;->zzB()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object p1

    invoke-interface {p1, p0}, Lcom/google/android/recaptcha/internal/zzahe;->zzb([B)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzagx;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 4
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzl:Lcom/google/android/recaptcha/internal/zzhe;

    .line 5
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 4
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v0

    .line 2
    :cond_0
    new-instance v1, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v3, Lcom/google/android/recaptcha/internal/zzhe;->zzJ:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    throw v1
.end method
