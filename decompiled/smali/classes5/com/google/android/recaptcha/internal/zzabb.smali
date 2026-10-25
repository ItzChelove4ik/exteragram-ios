.class public final Lcom/google/android/recaptcha/internal/zzabb;
.super Lcom/google/android/recaptcha/internal/zzaav;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzaay;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzaaz;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzaba;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzaay;ILcom/google/android/recaptcha/internal/zzaaz;Lcom/google/android/recaptcha/internal/zzaba;)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaav;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzabb;->zza:Lcom/google/android/recaptcha/internal/zzaay;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzb:Lcom/google/android/recaptcha/internal/zzaaz;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzc:Lcom/google/android/recaptcha/internal/zzaba;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzaba;)Lcom/google/android/recaptcha/internal/zzabb;
    .locals 4

    new-instance v0, Lcom/google/android/recaptcha/internal/zzabb;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaay;->zza:Lcom/google/android/recaptcha/internal/zzaay;

    const/16 v2, 0x40

    sget-object v3, Lcom/google/android/recaptcha/internal/zzaaz;->zza:Lcom/google/android/recaptcha/internal/zzaaz;

    invoke-direct {v0, v1, v2, v3, p0}, Lcom/google/android/recaptcha/internal/zzabb;-><init>(Lcom/google/android/recaptcha/internal/zzaay;ILcom/google/android/recaptcha/internal/zzaaz;Lcom/google/android/recaptcha/internal/zzaba;)V

    return-object v0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzabb;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzabb;

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzabb;->zza:Lcom/google/android/recaptcha/internal/zzaay;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzabb;->zza:Lcom/google/android/recaptcha/internal/zzaay;

    if-ne v0, v2, :cond_1

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzabb;->zzb:Lcom/google/android/recaptcha/internal/zzaaz;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzb:Lcom/google/android/recaptcha/internal/zzaaz;

    if-ne v0, v2, :cond_1

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzabb;->zzc:Lcom/google/android/recaptcha/internal/zzaba;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzc:Lcom/google/android/recaptcha/internal/zzaba;

    if-ne p1, p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public final hashCode()I
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabb;->zza:Lcom/google/android/recaptcha/internal/zzaay;

    const/16 v1, 0x40

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzb:Lcom/google/android/recaptcha/internal/zzaaz;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzc:Lcom/google/android/recaptcha/internal/zzaba;

    const-class v3, Lcom/google/android/recaptcha/internal/zzabb;

    filled-new-array {v3, v0, v1, v2, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 5

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzabb;->zza:Lcom/google/android/recaptcha/internal/zzaay;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaay;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzb:Lcom/google/android/recaptcha/internal/zzaaz;

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzabb;->zzc:Lcom/google/android/recaptcha/internal/zzaba;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v4

    add-int/lit8 v1, v1, 0xc

    add-int/2addr v1, v3

    new-instance v3, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x14

    add-int/2addr v1, v4

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "SLH-DSA-"

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "-128"

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, " instance, variant: "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
