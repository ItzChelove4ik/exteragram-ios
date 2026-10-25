.class public final Lcom/google/android/recaptcha/internal/zzxx;
.super Lcom/google/android/recaptcha/internal/zzaav;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzxv;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzxt;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzxu;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzxw;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzxv;Lcom/google/android/recaptcha/internal/zzxt;Lcom/google/android/recaptcha/internal/zzxu;Lcom/google/android/recaptcha/internal/zzxw;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaav;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzxx;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzxs;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzxs;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzxs;-><init>([B)V

    return-object v0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzxx;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzxx;

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzxx;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxx;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    if-ne v0, v2, :cond_1

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzxx;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    if-ne v0, v2, :cond_1

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzxx;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    if-ne v0, v2, :cond_1

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzxx;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    if-ne p1, p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public final hashCode()I
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    const-class v3, Lcom/google/android/recaptcha/internal/zzxx;

    filled-new-array {v3, v0, v1, v2, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzxx;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v5

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v6

    add-int/lit8 v1, v1, 0x27

    add-int/2addr v1, v3

    add-int/lit8 v1, v1, 0xc

    add-int/2addr v1, v5

    add-int/lit8 v1, v1, 0x9

    add-int/2addr v1, v6

    new-instance v3, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "ECDSA Parameters (variant: "

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", hashType: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", encoding: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", curve: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzxv;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzxt;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzxu;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzxw;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    return-object p0
.end method

.method public final zzf()Z
    .locals 1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzxx;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzxw;->zzd:Lcom/google/android/recaptcha/internal/zzxw;

    if-eq p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
