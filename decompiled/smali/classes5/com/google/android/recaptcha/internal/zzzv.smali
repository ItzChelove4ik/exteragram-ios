.class public final Lcom/google/android/recaptcha/internal/zzzv;
.super Lcom/google/android/recaptcha/internal/zzaav;
.source "SourceFile"


# static fields
.field public static final zza:Ljava/math/BigInteger;


# instance fields
.field private final zzb:I

.field private final zzc:Ljava/math/BigInteger;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzzu;

.field private final zze:Lcom/google/android/recaptcha/internal/zzzt;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-wide/32 v0, 0x10001

    .line 1
    invoke-static {v0, v1}, Ljava/math/BigInteger;->valueOf(J)Ljava/math/BigInteger;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzv;->zza:Ljava/math/BigInteger;

    return-void
.end method

.method public synthetic constructor <init>(ILjava/math/BigInteger;Lcom/google/android/recaptcha/internal/zzzu;Lcom/google/android/recaptcha/internal/zzzt;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaav;-><init>()V

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzb:I

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzc:Ljava/math/BigInteger;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzzv;->zze:Lcom/google/android/recaptcha/internal/zzzt;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzzs;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzzs;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzzs;-><init>([B)V

    return-object v0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzzv;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzzv;

    iget v0, p1, Lcom/google/android/recaptcha/internal/zzzv;->zzb:I

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzb:I

    if-ne v0, v2, :cond_1

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzzv;->zzc:Ljava/math/BigInteger;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzc:Ljava/math/BigInteger;

    .line 2
    invoke-static {v0, v2}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzzv;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    if-ne v0, v2, :cond_1

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzzv;->zze:Lcom/google/android/recaptcha/internal/zzzt;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zze:Lcom/google/android/recaptcha/internal/zzzt;

    if-ne p1, p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public final hashCode()I
    .locals 4

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzb:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzc:Ljava/math/BigInteger;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zze:Lcom/google/android/recaptcha/internal/zzzt;

    const-class v3, Lcom/google/android/recaptcha/internal/zzzv;

    filled-new-array {v3, v0, v1, v2, p0}, [Ljava/lang/Object;

    move-result-object p0

    .line 2
    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzc:Ljava/math/BigInteger;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzzv;->zze:Lcom/google/android/recaptcha/internal/zzzt;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v4

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v5

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzb:I

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->length()I

    move-result v6

    add-int/lit8 v3, v3, 0x2f

    add-int/2addr v3, v4

    add-int/lit8 v3, v3, 0x12

    add-int/2addr v3, v5

    add-int/lit8 v3, v3, 0x6

    add-int/2addr v3, v6

    new-instance v4, Ljava/lang/StringBuilder;

    add-int/lit8 v3, v3, 0xd

    invoke-direct {v4, v3}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v3, "RSA SSA PKCS1 Parameters (variant: "

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", hashType: "

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", publicExponent: "

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", and "

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "-bit modulus)"

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzb:I

    return p0
.end method

.method public final zzc()Ljava/math/BigInteger;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzc:Ljava/math/BigInteger;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzzu;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzzt;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zze:Lcom/google/android/recaptcha/internal/zzzt;

    return-object p0
.end method

.method public final zzf()Z
    .locals 1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzzv;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    if-eq p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
