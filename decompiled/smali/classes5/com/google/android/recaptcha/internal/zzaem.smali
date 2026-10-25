.class final Lcom/google/android/recaptcha/internal/zzaem;
.super Lcom/google/android/recaptcha/internal/zzaeo;
.source "SourceFile"


# instance fields
.field private final zzb:[B

.field private final zzc:I

.field private final zzd:I


# direct methods
.method public constructor <init>([BII)V
    .locals 2

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeo;-><init>([B)V

    add-int v0, p2, p3

    array-length v1, p1

    .line 2
    invoke-static {p2, v0, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    return-void
.end method


# virtual methods
.method public final zza(I)B
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    add-int/2addr v0, p1

    aget-byte p0, p0, v0

    return p0
.end method

.method public final zzb()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    return p0
.end method

.method public final zzc(II)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    invoke-static {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    move-result p2

    if-nez p2, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    add-int/2addr p0, p1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzaem;

    .line 2
    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzaem;-><init>([BII)V

    return-object p1
.end method

.method public final zzd(II)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    invoke-static {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    move-result p2

    if-nez p2, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    add-int/2addr p0, p1

    new-instance p1, Lcom/google/android/recaptcha/internal/zzaem;

    .line 2
    invoke-direct {p1, v0, p0, p2}, Lcom/google/android/recaptcha/internal/zzaem;-><init>([BII)V

    return-object p1
.end method

.method public final zze([BIII)V
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    add-int/2addr v0, p2

    invoke-static {p0, v0, p1, p3, p4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return-void
.end method

.method public final zzf()Ljava/nio/ByteBuffer;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    invoke-static {v0, v1, p0}, Ljava/nio/ByteBuffer;->wrap([BII)Ljava/nio/ByteBuffer;

    move-result-object p0

    invoke-virtual {p0}, Ljava/nio/ByteBuffer;->asReadOnlyBuffer()Ljava/nio/ByteBuffer;

    move-result-object p0

    return-object p0
.end method

.method public final zzg(Lcom/google/android/recaptcha/internal/zzaej;)V
    .locals 2

    check-cast p1, Lcom/google/android/recaptcha/internal/zzaex;

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    invoke-virtual {p1, v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzaex;->zzr([BII)V

    return-void
.end method

.method public final zzh(Ljava/nio/charset/Charset;)Ljava/lang/String;
    .locals 3

    new-instance v0, Ljava/lang/String;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    invoke-direct {v0, v1, v2, p0, p1}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    return-object v0
.end method

.method public final zzi(Lcom/google/android/recaptcha/internal/zzaer;)Z
    .locals 2

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzaep;

    if-nez v0, :cond_1

    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzaem;

    if-eqz v0, :cond_0

    goto :goto_0

    .line 2
    :cond_0
    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzi(Lcom/google/android/recaptcha/internal/zzaer;)Z

    move-result p0

    return p0

    :cond_1
    :goto_0
    const/4 v0, 0x0

    .line 1
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    invoke-virtual {p0, p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaem;->zzj(Lcom/google/android/recaptcha/internal/zzaer;II)Z

    move-result p0

    return p0
.end method

.method public final zzj(Lcom/google/android/recaptcha/internal/zzaer;II)Z
    .locals 3

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    if-gt p3, v0, :cond_3

    add-int v0, p2, p3

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    if-gt v0, v1, :cond_2

    .line 4
    instance-of v1, p1, Lcom/google/android/recaptcha/internal/zzaep;

    if-eqz v1, :cond_0

    .line 5
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaep;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    .line 6
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaep;->zzm()[B

    move-result-object p1

    invoke-static {v0, p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaer;->zzC([BI[BII)Z

    move-result p0

    return p0

    :cond_0
    instance-of v1, p1, Lcom/google/android/recaptcha/internal/zzaem;

    if-eqz v1, :cond_1

    .line 7
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaem;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    .line 8
    iget-object v1, p1, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget p1, p1, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    add-int/2addr p1, p2

    invoke-static {v0, p0, v1, p1, p3}, Lcom/google/android/recaptcha/internal/zzaer;->zzC([BI[BII)Z

    move-result p0

    return p0

    .line 9
    :cond_1
    invoke-virtual {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzd(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    iget p2, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    add-int/2addr p3, p2

    .line 10
    invoke-virtual {p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzaem;->zzd(II)Lcom/google/android/recaptcha/internal/zzaer;

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
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    .line 2
    invoke-static {p3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p1

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    add-int/lit8 p1, p1, 0x12

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/2addr p1, p2

    invoke-static {p1, p3, p0}, Lcom/google/android/gms/internal/vision/zzid$$ExternalSyntheticBUOutline0;->m(III)V

    const/4 p0, 0x0

    return p0
.end method

.method public final zzk(III)I
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    add-int/2addr v0, p2

    invoke-static {p1, p0, v0, p3}, Lcom/google/android/recaptcha/internal/zzagf;->zzc(I[BII)I

    move-result p0

    return p0
.end method

.method public final zzl()Lcom/google/android/recaptcha/internal/zzaev;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzd:I

    const/4 v2, 0x1

    invoke-static {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzE([BIIZ)Lcom/google/android/recaptcha/internal/zzaev;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzm()[B
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzb:[B

    return-object p0
.end method

.method public final synthetic zzn()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaem;->zzc:I

    return p0
.end method
