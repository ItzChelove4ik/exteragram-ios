.class public final Lcom/google/android/recaptcha/internal/zzya;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzxx;

.field private zzb:Ljava/security/spec/ECPoint;

.field private zzc:Ljava/lang/Integer;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzya;->zzb:Ljava/security/spec/ECPoint;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzya;->zzc:Ljava/lang/Integer;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzxx;)Lcom/google/android/recaptcha/internal/zzya;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    return-object p0
.end method

.method public final zzb(Ljava/security/spec/ECPoint;)Lcom/google/android/recaptcha/internal/zzya;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzya;->zzb:Ljava/security/spec/ECPoint;

    return-object p0
.end method

.method public final zzc(Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzya;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzya;->zzc:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzyb;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    const/4 v1, 0x0

    if-eqz v0, :cond_9

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzya;->zzb:Ljava/security/spec/ECPoint;

    if-eqz v2, :cond_8

    .line 2
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zzc()Lcom/google/android/recaptcha/internal/zzxt;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxt;->zza()Ljava/security/spec/ECParameterSpec;

    move-result-object v0

    .line 3
    invoke-virtual {v0}, Ljava/security/spec/ECParameterSpec;->getCurve()Ljava/security/spec/EllipticCurve;

    move-result-object v0

    .line 4
    invoke-static {v2, v0}, Lcom/google/android/recaptcha/internal/zztt;->zza(Ljava/security/spec/ECPoint;Ljava/security/spec/EllipticCurve;)V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zzf()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zzc:Ljava/lang/Integer;

    if-eqz v0, :cond_0

    goto :goto_0

    .line 6
    :cond_0
    const-string p0, "Cannot create key without ID requirement with parameters with ID requirement"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 7
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zzf()Z

    move-result v0

    if-nez v0, :cond_3

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zzc:Ljava/lang/Integer;

    if-nez v0, :cond_2

    goto :goto_1

    .line 16
    :cond_2
    const-string p0, "Cannot create key with ID requirement with parameters without ID requirement"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 7
    :cond_3
    :goto_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxw;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    if-ne v0, v2, :cond_4

    .line 9
    sget-object v0, Lcom/google/android/recaptcha/internal/zzvc;->zza:Lcom/google/android/recaptcha/internal/zzady;

    :goto_2
    move-object v4, v0

    goto :goto_4

    :cond_4
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 10
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxw;->zzc:Lcom/google/android/recaptcha/internal/zzxw;

    if-eq v0, v2, :cond_7

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 11
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxw;->zzb:Lcom/google/android/recaptcha/internal/zzxw;

    if-ne v0, v2, :cond_5

    goto :goto_3

    .line 15
    :cond_5
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzxw;->zza:Lcom/google/android/recaptcha/internal/zzxw;

    if-ne v0, v2, :cond_6

    .line 16
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zzc:Ljava/lang/Integer;

    .line 13
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzvc;->zzb(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    goto :goto_2

    .line 12
    :cond_6
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 14
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxx;->zze()Lcom/google/android/recaptcha/internal/zzxw;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v0, "Unknown EcdsaParameters.Variant: "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 11
    :cond_7
    :goto_3
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzya;->zzc:Ljava/lang/Integer;

    .line 15
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzvc;->zza(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    goto :goto_2

    .line 9
    :goto_4
    new-instance v1, Lcom/google/android/recaptcha/internal/zzyb;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzya;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzya;->zzb:Ljava/security/spec/ECPoint;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzya;->zzc:Ljava/lang/Integer;

    const/4 v6, 0x0

    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzyb;-><init>(Lcom/google/android/recaptcha/internal/zzxx;Ljava/security/spec/ECPoint;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;[B)V

    return-object v1

    .line 2
    :cond_8
    const-string p0, "Cannot build without public point"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_9
    const-string p0, "Cannot build without parameters"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
