.class public final Lcom/google/android/recaptcha/internal/zzv;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zzb:Lcom/google/android/recaptcha/internal/zzv;

.field public static final zzc:Ljava/util/Comparator;


# instance fields
.field public final zza:[B


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzv;

    const/4 v1, 0x0

    new-array v1, v1, [B

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzv;-><init>([B)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzv;->zzb:Lcom/google/android/recaptcha/internal/zzv;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzu;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzu;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzv;->zzc:Ljava/util/Comparator;

    return-void
.end method

.method public constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    return-void
.end method

.method public static zze([B)Lcom/google/android/recaptcha/internal/zzv;
    .locals 3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzv;

    const/4 v1, 0x0

    array-length v2, p0

    invoke-static {p0, v1, v2}, Lcom/google/android/recaptcha/internal/zzv;->zzh([BII)[B

    move-result-object p0

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzv;-><init>([B)V

    return-object v0
.end method

.method public static zzf(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzv;
    .locals 1

    const-string v0, "Hn2H4l0="

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/nio/charset/Charset;->forName(Ljava/lang/String;)Ljava/nio/charset/Charset;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzv;->zze([B)Lcom/google/android/recaptcha/internal/zzv;

    move-result-object p0

    return-object p0
.end method

.method public static zzg(B)I
    .locals 9

    const/16 v0, 0x9

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget v1, v0, v1

    const/4 v2, 0x1

    aget v2, v0, v2

    const/4 v3, 0x2

    aget v3, v0, v3

    const/4 v4, 0x3

    aget v4, v0, v4

    const/4 v5, 0x4

    aget v5, v0, v5

    const/4 v6, 0x5

    aget v6, v0, v6

    const/4 v7, 0x6

    aget v7, v0, v7

    const/4 v8, 0x7

    aget v0, v0, v8

    not-int v8, v1

    and-int/2addr v2, v8

    or-int/2addr v2, v3

    and-int/2addr v1, v4

    or-int/2addr v1, v5

    add-int/2addr v2, v1

    sub-int/2addr v2, v6

    add-int/2addr v7, v2

    const v1, 0x178f7b67

    rem-int/2addr v0, v1

    xor-int/2addr v0, v7

    and-int/2addr p0, v0

    return p0

    :array_0
    .array-data 4
        0x1565ac99
        0xabc642
        0x1604f817
        0x24ab0644
        0x3414b09f
        0x675b340d
        0x1d206b8e
        0x76574f8b
        0x178f7b67
    .end array-data
.end method

.method public static zzh([BII)[B
    .locals 2

    const/4 v0, 0x0

    if-nez p2, :cond_0

    new-array p0, v0, [B

    return-object p0

    :cond_0
    new-array v1, p2, [B

    invoke-static {p0, p1, v1, v0, p2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-object v1
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 1

    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzv;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzv;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    invoke-static {p0, p1}, Ljava/util/Arrays;->equals([B[B)Z

    move-result p0

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final hashCode()I
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    invoke-static {p0}, Ljava/util/Arrays;->hashCode([B)I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->hashCode(I)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 9

    const/16 v0, 0x9

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget v1, v0, v1

    const/4 v2, 0x1

    aget v2, v0, v2

    const/4 v3, 0x2

    aget v3, v0, v3

    const/4 v4, 0x3

    aget v4, v0, v4

    const/4 v5, 0x4

    aget v5, v0, v5

    const/4 v6, 0x5

    aget v6, v0, v6

    const/4 v7, 0x6

    aget v7, v0, v7

    const/4 v8, 0x7

    aget v0, v0, v8

    not-int v8, v1

    and-int/2addr v2, v8

    or-int/2addr v2, v3

    and-int/2addr v1, v4

    or-int/2addr v1, v5

    add-int/2addr v2, v1

    sub-int/2addr v2, v6

    add-int/2addr v7, v2

    const v1, 0x62e5fd99

    rem-int/2addr v0, v1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    invoke-static {p0}, Ljava/util/Arrays;->toString([B)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    xor-int/2addr v0, v7

    add-int/2addr v1, v0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "CVC1qiQNJHikW0iU1TIPZA=="

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "Ng=="

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :array_0
    .array-data 4
        0x5ada634
        0x21e4c7ec
        0xaefbe00
        0x210051ec
        0x1e119613
        0x399854d6
        0x57a5ae1
        0x78c999b4
        0x62e5fd99
    .end array-data
.end method

.method public final zza()[B
    .locals 2

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v0, p0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    new-array p0, v1, [B

    return-object p0

    :cond_0
    invoke-static {p0, v1, v0}, Lcom/google/android/recaptcha/internal/zzv;->zzh([BII)[B

    move-result-object p0

    return-object p0
.end method

.method public final zzb(I)B
    .locals 6

    const v0, 0x6181ef69

    not-int v1, v0

    const v2, 0x1a0476c0

    and-int/2addr v1, v2

    const v2, 0x61037710

    or-int/2addr v1, v2

    const v2, 0x5b0e81d8

    and-int/2addr v0, v2

    const v2, 0x411bcd1a

    or-int/2addr v0, v2

    add-int/2addr v1, v0

    const v0, -0x4a99e3a5

    sub-int/2addr v1, v0

    const v0, 0x21faa2fa

    const v2, 0x4ab26e78    # 5846844.0f

    rem-int/2addr v2, v0

    const v0, 0x4c502870    # 5.456736E7f

    not-int v3, v0

    const v4, 0x59f85a40

    and-int/2addr v3, v4

    const v4, 0x4010ad3f

    or-int/2addr v3, v4

    const v4, 0x19e8d665

    and-int/2addr v0, v4

    const v4, 0x406a427    # 1.5827E-36f

    or-int/2addr v0, v4

    add-int/2addr v3, v0

    const v0, 0x533fb7a6

    sub-int/2addr v3, v0

    const v0, 0x434bae75

    const v4, 0x4e0b9a87    # 5.8554003E8f

    rem-int/2addr v4, v0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v0, p0

    add-int/lit8 v5, p1, 0x1

    sub-int v5, v0, v5

    or-int/2addr v5, p1

    if-gez v5, :cond_1

    if-gez p1, :cond_0

    xor-int p0, v1, v2

    new-instance v0, Ljava/lang/ArrayIndexOutOfBoundsException;

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    add-int/2addr v1, p0

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "Akelqh1fajntGgo="

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/lang/ArrayIndexOutOfBoundsException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_0
    xor-int p0, v3, v4

    new-instance v1, Ljava/lang/ArrayIndexOutOfBoundsException;

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v2, p0

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/2addr v2, p0

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0, v2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v2, "Akelqh1faDmxRUSK1T9GeQ=="

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "Zwk="

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v1, p0}, Ljava/lang/ArrayIndexOutOfBoundsException;-><init>(Ljava/lang/String;)V

    throw v1

    :cond_1
    aget-byte p0, p0, p1

    return p0
.end method

.method public final zzc()Ljava/lang/String;
    .locals 4

    const-string v0, "Hn2H4l0="

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/nio/charset/Charset;->forName(Ljava/lang/String;)Ljava/nio/charset/Charset;

    move-result-object v0

    new-instance v1, Ljava/lang/String;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v2, p0

    const/4 v3, 0x0

    invoke-direct {v1, p0, v3, v2, v0}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    return-object v1
.end method

.method public final zzd(Lcom/google/android/recaptcha/internal/zzv;)Lcom/google/android/recaptcha/internal/zzv;
    .locals 4

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v0, p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v1, p0

    add-int v2, v1, v0

    new-array v2, v2, [B

    const/4 v3, 0x0

    invoke-static {p0, v3, v2, v3, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    invoke-static {p1, v3, v2, v1, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzv;->zze([B)Lcom/google/android/recaptcha/internal/zzv;

    move-result-object p0

    return-object p0
.end method
