.class final Lcom/google/android/recaptcha/internal/zzaep;
.super Lcom/google/android/recaptcha/internal/zzaeo;
.source "SourceFile"


# instance fields
.field private final zzb:[B


# direct methods
.method public constructor <init>([B)V
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeo;-><init>([B)V

    .line 2
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    return-void
.end method


# virtual methods
.method public final zza(I)B
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    aget-byte p0, p0, p1

    return p0
.end method

.method public final zzb()I
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    array-length p0, p0

    return p0
.end method

.method public final zzc(II)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    array-length v0, p0

    invoke-static {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    move-result p2

    if-nez p2, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    .line 2
    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaem;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaem;-><init>([BII)V

    return-object v0
.end method

.method public final zzd(II)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    array-length v0, p0

    invoke-static {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    move-result p2

    if-nez p2, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    .line 2
    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaem;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaem;-><init>([BII)V

    return-object v0
.end method

.method public final zze([BIII)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    invoke-static {p0, p2, p1, p3, p4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-void
.end method

.method public final zzf()Ljava/nio/ByteBuffer;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    invoke-static {p0}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object p0

    invoke-virtual {p0}, Ljava/nio/ByteBuffer;->asReadOnlyBuffer()Ljava/nio/ByteBuffer;

    move-result-object p0

    return-object p0
.end method

.method public final zzg(Lcom/google/android/recaptcha/internal/zzaej;)V
    .locals 2

    check-cast p1, Lcom/google/android/recaptcha/internal/zzaex;

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    array-length v0, p0

    const/4 v1, 0x0

    invoke-virtual {p1, p0, v1, v0}, Lcom/google/android/recaptcha/internal/zzaex;->zzr([BII)V

    return-void
.end method

.method public final zzh(Ljava/nio/charset/Charset;)Ljava/lang/String;
    .locals 1

    new-instance v0, Ljava/lang/String;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    invoke-direct {v0, p0, p1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0
.end method

.method public final zzi(Lcom/google/android/recaptcha/internal/zzaer;)Z
    .locals 2

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzaep;

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    check-cast p1, Lcom/google/android/recaptcha/internal/zzaep;

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    invoke-static {p0, p1}, Ljava/util/Arrays;->equals([B[B)Z

    move-result p0

    return p0

    :cond_0
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzaem;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    array-length v0, v0

    const/4 v1, 0x0

    .line 2
    invoke-virtual {p0, p1, v1, v0}, Lcom/google/android/recaptcha/internal/zzaep;->zzj(Lcom/google/android/recaptcha/internal/zzaer;II)Z

    move-result p0

    return p0

    .line 3
    :cond_1
    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzi(Lcom/google/android/recaptcha/internal/zzaer;)Z

    move-result p0

    return p0
.end method

.method public final zzj(Lcom/google/android/recaptcha/internal/zzaer;II)Z
    .locals 3

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    const/4 v1, 0x0

    if-gt p3, v0, :cond_3

    add-int v0, p2, p3

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v2

    if-gt v0, v2, :cond_2

    .line 4
    instance-of v2, p1, Lcom/google/android/recaptcha/internal/zzaep;

    if-eqz v2, :cond_0

    .line 5
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaep;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    .line 6
    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    invoke-static {p0, v1, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaer;->zzC([BI[BII)Z

    move-result p0

    return p0

    :cond_0
    instance-of v2, p1, Lcom/google/android/recaptcha/internal/zzaem;

    if-eqz v2, :cond_1

    .line 7
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaem;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaem;->zzm()[B

    move-result-object v0

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaem;->zzn()I

    move-result p1

    add-int/2addr p1, p2

    invoke-static {p0, v1, v0, p1, p3}, Lcom/google/android/recaptcha/internal/zzaer;->zzC([BI[BII)Z

    move-result p0

    return p0

    .line 9
    :cond_1
    invoke-virtual {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzd(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-virtual {p0, v1, p3}, Lcom/google/android/recaptcha/internal/zzaep;->zzd(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzaer;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0

    .line 3
    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result p1

    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    invoke-static {p3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    add-int/lit8 v0, v0, 0x18

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    add-int/2addr v0, v1

    add-int/lit8 v0, v0, 0x2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v1

    add-int/2addr v0, v1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v0, "Ran off end of other: "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, ", "

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 1
    :cond_3
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    .line 2
    invoke-static {p3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p1

    array-length p0, p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    add-int/lit8 p1, p1, 0x12

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/2addr p1, p2

    invoke-static {p1, p3, p0}, Lcom/google/android/gms/internal/vision/zzid$$ExternalSyntheticBUOutline0;->m(III)V

    return v1
.end method

.method public final zzk(III)I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    invoke-static {p1, p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzagf;->zzc(I[BII)I

    move-result p0

    return p0
.end method

.method public final zzl()Lcom/google/android/recaptcha/internal/zzaev;
    .locals 3

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    array-length v0, p0

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-static {p0, v2, v0, v1}, Lcom/google/android/recaptcha/internal/zzaev;->zzE([BIIZ)Lcom/google/android/recaptcha/internal/zzaev;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzm()[B
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaep;->zzb:[B

    return-object p0
.end method
