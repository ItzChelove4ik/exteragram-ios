.class public final Lcom/google/android/recaptcha/internal/zzyo;
.super Lcom/google/android/recaptcha/internal/zzaax;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzyh;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzady;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzady;

.field private final zzd:Ljava/lang/Integer;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzyh;Lcom/google/android/recaptcha/internal/zzady;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaax;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzyo;->zza:Lcom/google/android/recaptcha/internal/zzyh;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzyo;->zzb:Lcom/google/android/recaptcha/internal/zzady;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzyo;->zzc:Lcom/google/android/recaptcha/internal/zzady;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzyo;->zzd:Ljava/lang/Integer;

    return-void
.end method

.method public static zzc(Lcom/google/android/recaptcha/internal/zzyg;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzyo;
    .locals 4

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzyh;->zza(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzyh;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzyg;->zzd:Lcom/google/android/recaptcha/internal/zzyg;

    invoke-virtual {p0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_1

    if-eqz p2, :cond_0

    goto :goto_0

    .line 2
    :cond_0
    new-instance p1, Ljava/security/GeneralSecurityException;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p2

    add-int/lit8 p2, p2, 0x3e

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, p2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string p2, "For given Variant "

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " the value of idRequirement must be non-null"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_1
    :goto_0
    const/4 v2, 0x0

    if-eq p0, v1, :cond_2

    goto :goto_1

    :cond_2
    if-nez p2, :cond_8

    .line 3
    :goto_1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzady;->zzd()I

    move-result p0

    const/16 v3, 0x20

    if-ne p0, v3, :cond_7

    .line 6
    new-instance p0, Lcom/google/android/recaptcha/internal/zzyo;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v3

    if-ne v3, v1, :cond_3

    .line 7
    sget-object v1, Lcom/google/android/recaptcha/internal/zzvc;->zza:Lcom/google/android/recaptcha/internal/zzady;

    goto :goto_3

    :cond_3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v1

    sget-object v3, Lcom/google/android/recaptcha/internal/zzyg;->zzb:Lcom/google/android/recaptcha/internal/zzyg;

    if-eq v1, v3, :cond_6

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v1

    sget-object v3, Lcom/google/android/recaptcha/internal/zzyg;->zzc:Lcom/google/android/recaptcha/internal/zzyg;

    if-ne v1, v3, :cond_4

    goto :goto_2

    .line 10
    :cond_4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object v1

    sget-object v3, Lcom/google/android/recaptcha/internal/zzyg;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    if-ne v1, v3, :cond_5

    .line 8
    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzvc;->zzb(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v1

    goto :goto_3

    .line 10
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzyh;->zzb()Lcom/google/android/recaptcha/internal/zzyg;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string p1, "Unknown Variant: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 9
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2

    .line 10
    :cond_6
    :goto_2
    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzvc;->zza(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v1

    .line 7
    :goto_3
    invoke-direct {p0, v0, p1, v1, p2}, Lcom/google/android/recaptcha/internal/zzyo;-><init>(Lcom/google/android/recaptcha/internal/zzyh;Lcom/google/android/recaptcha/internal/zzady;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;)V

    return-object p0

    .line 5
    :cond_7
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzady;->zzd()I

    move-result p1

    .line 6
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/lit8 p2, p2, 0x41

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, p2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string p2, "Ed25519 key must be constructed with key of length 32 bytes, not "

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 4
    :cond_8
    const-string p0, "For given Variant NO_PREFIX the value of idRequirement must be null"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2
.end method


# virtual methods
.method public final synthetic zza()Lcom/google/android/recaptcha/internal/zzsl;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyo;->zza:Lcom/google/android/recaptcha/internal/zzyh;

    return-object p0
.end method

.method public final zzb()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyo;->zzd:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzady;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyo;->zzb:Lcom/google/android/recaptcha/internal/zzady;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzady;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyo;->zzc:Lcom/google/android/recaptcha/internal/zzady;

    return-object p0
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzyh;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyo;->zza:Lcom/google/android/recaptcha/internal/zzyh;

    return-object p0
.end method
