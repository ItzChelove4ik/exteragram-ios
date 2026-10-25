.class public final Lcom/google/android/recaptcha/internal/zzzy;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/recaptcha/internal/zzzv;

.field private zzb:Ljava/math/BigInteger;

.field private zzc:Ljava/lang/Integer;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzb:Ljava/math/BigInteger;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzc:Ljava/lang/Integer;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzzv;)Lcom/google/android/recaptcha/internal/zzzy;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    return-object p0
.end method

.method public final zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzzy;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzb:Ljava/math/BigInteger;

    return-object p0
.end method

.method public final zzc(Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzzy;
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzc:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzzz;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    const/4 v1, 0x0

    if-eqz v0, :cond_a

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzb:Ljava/math/BigInteger;

    if-eqz v0, :cond_9

    .line 3
    invoke-virtual {v0}, Ljava/math/BigInteger;->bitLength()I

    move-result v0

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 4
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzzv;->zzb()I

    move-result v2

    if-ne v0, v2, :cond_8

    .line 5
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 6
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzzv;->zzf()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzc:Ljava/lang/Integer;

    if-eqz v0, :cond_0

    goto :goto_0

    .line 7
    :cond_0
    const-string p0, "Cannot create key without ID requirement with parameters with ID requirement"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzzv;->zzf()Z

    move-result v0

    if-nez v0, :cond_3

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzc:Ljava/lang/Integer;

    if-nez v0, :cond_2

    goto :goto_1

    .line 17
    :cond_2
    const-string p0, "Cannot create key with ID requirement with parameters without ID requirement"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 8
    :cond_3
    :goto_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 9
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzzu;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    if-ne v0, v2, :cond_4

    .line 10
    sget-object v0, Lcom/google/android/recaptcha/internal/zzvc;->zza:Lcom/google/android/recaptcha/internal/zzady;

    :goto_2
    move-object v4, v0

    goto :goto_4

    :cond_4
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 11
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzzu;->zzc:Lcom/google/android/recaptcha/internal/zzzu;

    if-eq v0, v2, :cond_7

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzzu;->zzb:Lcom/google/android/recaptcha/internal/zzzu;

    if-ne v0, v2, :cond_5

    goto :goto_3

    .line 16
    :cond_5
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 13
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object v0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzzu;->zza:Lcom/google/android/recaptcha/internal/zzzu;

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzc:Ljava/lang/Integer;

    .line 14
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzvc;->zzb(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    goto :goto_2

    .line 13
    :cond_6
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    .line 15
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzv;->zzd()Lcom/google/android/recaptcha/internal/zzzu;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "Unknown RsaSsaPkcs1Parameters.Variant: "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 12
    :cond_7
    :goto_3
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzc:Ljava/lang/Integer;

    .line 16
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzvc;->zza(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v0

    goto :goto_2

    .line 10
    :goto_4
    new-instance v1, Lcom/google/android/recaptcha/internal/zzzz;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzy;->zza:Lcom/google/android/recaptcha/internal/zzzv;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzb:Ljava/math/BigInteger;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzzy;->zzc:Ljava/lang/Integer;

    const/4 v6, 0x0

    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzzz;-><init>(Lcom/google/android/recaptcha/internal/zzzv;Ljava/math/BigInteger;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;[B)V

    return-object v1

    .line 5
    :cond_8
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v3

    add-int/lit8 v1, v1, 0x38

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    add-int/2addr v1, v3

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "Got modulus size "

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", but parameters requires modulus size "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 2
    :cond_9
    const-string p0, "Cannot build without modulus"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 1
    :cond_a
    const-string p0, "Cannot build without parameters"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
