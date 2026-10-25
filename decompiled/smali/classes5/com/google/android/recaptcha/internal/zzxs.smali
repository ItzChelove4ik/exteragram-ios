.class public final Lcom/google/android/recaptcha/internal/zzxs;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzxv;

.field private zzb:Lcom/google/android/recaptcha/internal/zzxt;

.field private zzc:Lcom/google/android/recaptcha/internal/zzxu;

.field private zzd:Lcom/google/android/recaptcha/internal/zzxw;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    sget-object p1, Lcom/google/android/recaptcha/internal/zzxw;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzxv;)Lcom/google/android/recaptcha/internal/zzxs;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    return-object p0
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzxt;)Lcom/google/android/recaptcha/internal/zzxs;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    return-object p0
.end method

.method public final zzc(Lcom/google/android/recaptcha/internal/zzxu;)Lcom/google/android/recaptcha/internal/zzxs;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    return-object p0
.end method

.method public final zzd(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzxs;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzxx;
    .locals 6

    .line 1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzxs;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    const/4 v0, 0x0

    if-eqz v1, :cond_9

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    if-eqz v2, :cond_8

    .line 2
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    if-eqz v3, :cond_7

    .line 3
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzxs;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    if-eqz v4, :cond_6

    .line 4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxt;->zza:Lcom/google/android/recaptcha/internal/zzxt;

    if-ne v2, p0, :cond_1

    sget-object p0, Lcom/google/android/recaptcha/internal/zzxu;->zza:Lcom/google/android/recaptcha/internal/zzxu;

    if-ne v3, p0, :cond_0

    goto :goto_0

    .line 5
    :cond_0
    const-string p0, "NIST_P256 requires SHA256"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    :cond_1
    :goto_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxt;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    if-ne v2, p0, :cond_3

    sget-object p0, Lcom/google/android/recaptcha/internal/zzxu;->zzb:Lcom/google/android/recaptcha/internal/zzxu;

    if-eq v3, p0, :cond_3

    sget-object p0, Lcom/google/android/recaptcha/internal/zzxu;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    if-ne v3, p0, :cond_2

    goto :goto_1

    .line 6
    :cond_2
    const-string p0, "NIST_P384 requires SHA384 or SHA512"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    :cond_3
    :goto_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzxt;->zzc:Lcom/google/android/recaptcha/internal/zzxt;

    if-ne v2, p0, :cond_5

    sget-object p0, Lcom/google/android/recaptcha/internal/zzxu;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    if-ne v3, p0, :cond_4

    goto :goto_2

    .line 7
    :cond_4
    const-string p0, "NIST_P521 requires SHA512"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    :cond_5
    :goto_2
    new-instance v0, Lcom/google/android/recaptcha/internal/zzxx;

    const/4 v5, 0x0

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzxx;-><init>(Lcom/google/android/recaptcha/internal/zzxv;Lcom/google/android/recaptcha/internal/zzxt;Lcom/google/android/recaptcha/internal/zzxu;Lcom/google/android/recaptcha/internal/zzxw;[B)V

    return-object v0

    .line 4
    :cond_6
    const-string/jumbo p0, "variant is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    .line 3
    :cond_7
    const-string p0, "hash type is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    .line 2
    :cond_8
    const-string p0, "EC curve type is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    .line 1
    :cond_9
    const-string p0, "signature encoding is not set"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0
.end method
