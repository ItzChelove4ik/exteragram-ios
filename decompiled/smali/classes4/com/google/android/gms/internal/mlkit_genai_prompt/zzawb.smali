.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Iterable;
.implements Ljava/io/Serializable;


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;


# instance fields
.field private zzb:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawa;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawa;-><init>([B)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    .line 2
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavq;->$r8$clinit:I

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb:I

    return-void
.end method

.method public static zzi([BII)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;
    .locals 2

    .line 1
    :try_start_0
    array-length v0, p0

    add-int v1, p1, p2

    invoke-static {p1, v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzk(III)I

    new-array v0, p2, [B

    const/4 v1, 0x0

    .line 2
    invoke-static {p0, p1, v0, v1, p2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 3
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawa;

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawa;-><init>([B)V
    :try_end_0
    .catch Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxm; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    new-instance p1, Ljava/lang/AssertionError;

    const-string p2, "Expected no InvalidProtocolBufferException as data UTF8 validity is not checked."

    .line 4
    invoke-direct {p1, p2, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw p1
.end method

.method public static zzk(III)I
    .locals 3

    or-int v0, p0, p1

    sub-int v1, p1, p0

    or-int/2addr v0, v1

    sub-int v2, p2, p1

    or-int/2addr v0, v2

    if-gez v0, :cond_2

    if-ltz p0, :cond_1

    if-ge p1, p0, :cond_0

    .line 2
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    add-int/lit8 p2, p2, 0x2c

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/2addr p2, v0

    const-string v0, "Beginning index larger than ending index: "

    const-string v1, ", "

    invoke-static {p2, v0, p0, v1, p1}, Lcom/google/android/gms/internal/cast/zzxk$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;I)V

    :goto_0
    const/4 p0, 0x0

    return p0

    .line 3
    :cond_0
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    add-int/lit8 p0, p0, 0xf

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/2addr p0, v0

    const-string v0, "End index: "

    const-string v1, " >= "

    invoke-static {p0, v0, p1, v1, p2}, Lcom/google/android/gms/internal/cast/zzxk$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;I)V

    goto :goto_0

    .line 1
    :cond_1
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p1

    add-int/lit8 p1, p1, 0x15

    invoke-static {p1, p0}, Lcom/google/android/gms/internal/cast/zzxk$$ExternalSyntheticBUOutline1;->m(II)V

    goto :goto_0

    :cond_2
    return v1
.end method

.method public static synthetic zzl([BI[BII)Z
    .locals 2

    add-int v0, p1, p4

    .line 1
    array-length v1, p0

    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzk(III)I

    add-int/2addr p4, p3

    array-length v1, p2

    .line 2
    invoke-static {p3, p4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzk(III)I

    :goto_0
    if-ge p1, v0, :cond_1

    .line 3
    aget-byte p4, p0, p1

    aget-byte v1, p2, p3

    if-eq p4, v1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    add-int/lit8 p1, p1, 0x1

    add-int/lit8 p3, p3, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb()I

    move-result v1

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb()I

    move-result v3

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    if-eqz v1, :cond_4

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb:I

    iget v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb:I

    if-eqz v0, :cond_3

    if-eqz v1, :cond_3

    if-eq v0, v1, :cond_3

    return v2

    .line 3
    :cond_3
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;)Z

    move-result p0

    return p0

    :cond_4
    return v0
.end method

.method public final hashCode()I
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb:I

    if-nez v0, :cond_1

    const/4 v0, 0x0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb()I

    move-result v1

    invoke-virtual {p0, v1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzf(III)I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :cond_0
    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb:I

    :cond_1
    return v0
.end method

.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavv;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavv;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;)V

    return-object v0
.end method

.method public final toString()Ljava/lang/String;
    .locals 5

    .line 1
    sget-object v0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-static {p0}, Ljava/lang/System;->identityHashCode(Ljava/lang/Object;)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb()I

    move-result v2

    .line 2
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb()I

    move-result v3

    const/16 v4, 0x32

    if-gt v3, v4, :cond_0

    .line 3
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzj()[B

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayo;->zza([B)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 v3, 0x0

    const/16 v4, 0x2f

    .line 4
    invoke-virtual {p0, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzc(II)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    move-result-object p0

    .line 3
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzj()[B

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayo;->zza([B)Ljava/lang/String;

    move-result-object p0

    const-string v3, "..."

    invoke-virtual {p0, v3}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    :goto_0
    filled-new-array {v1, v2, p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string v1, "<ByteString@%s size=%d contents=\"%s\">"

    .line 5
    invoke-static {v0, v1, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public abstract zza(I)B
.end method

.method public abstract zzb()I
.end method

.method public abstract zzc(II)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;
.end method

.method public abstract zzd([BIII)V
.end method

.method public abstract zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;)Z
.end method

.method public abstract zzf(III)I
.end method

.method public final zzj()[B
    .locals 3

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzb()I

    move-result v0

    if-nez v0, :cond_0

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    return-object p0

    .line 2
    :cond_0
    new-array v1, v0, [B

    const/4 v2, 0x0

    .line 3
    invoke-virtual {p0, v1, v2, v2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzd([BIII)V

    return-object v1
.end method
