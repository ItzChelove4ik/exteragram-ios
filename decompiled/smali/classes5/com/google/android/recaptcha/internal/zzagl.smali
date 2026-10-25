.class final Lcom/google/android/recaptcha/internal/zzagl;
.super Ljava/io/InputStream;
.source "SourceFile"

# interfaces
.implements Lj$/io/InputStreamRetargetInterface;


# instance fields
.field private zza:Ljava/util/Iterator;

.field private zzb:Ljava/nio/ByteBuffer;

.field private zzc:I

.field private zzd:I

.field private zze:I

.field private zzf:Z

.field private zzg:[B

.field private zzh:I


# direct methods
.method public constructor <init>(Ljava/lang/Iterable;)V
    .locals 2

    .line 1
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 2
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zza:Ljava/util/Iterator;

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzc:I

    .line 3
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/nio/ByteBuffer;

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzc:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzc:I

    goto :goto_0

    :cond_0
    const/4 p1, -0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzd:I

    .line 4
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzagl;->zza()Z

    move-result p1

    if-nez p1, :cond_1

    .line 5
    sget-object p1, Lcom/google/android/recaptcha/internal/zzagf;->zzb:Ljava/nio/ByteBuffer;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    :cond_1
    return-void
.end method

.method private final zza()Z
    .locals 3

    .line 1
    :cond_0
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzd:I

    const/4 v1, 0x1

    add-int/2addr v0, v1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzd:I

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zza:Ljava/util/Iterator;

    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    const/4 v2, 0x0

    if-nez v0, :cond_1

    return v2

    :cond_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zza:Ljava/util/Iterator;

    .line 2
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/nio/ByteBuffer;

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 3
    invoke-virtual {v0}, Ljava/nio/Buffer;->hasRemaining()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 4
    invoke-virtual {v0}, Ljava/nio/Buffer;->position()I

    move-result v0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 5
    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->hasArray()Z

    move-result v0

    if-eqz v0, :cond_2

    iput-boolean v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzf:Z

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 6
    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->array()[B

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzg:[B

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 7
    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->arrayOffset()I

    move-result v0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzh:I

    goto :goto_0

    :cond_2
    iput-boolean v2, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzf:Z

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzg:[B

    :goto_0
    return v1
.end method

.method private final zzb(I)V
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    add-int/2addr v0, p1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    invoke-virtual {p1}, Ljava/nio/Buffer;->limit()I

    move-result p1

    if-ne v0, p1, :cond_0

    .line 2
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzagl;->zza()Z

    :cond_0
    return-void
.end method


# virtual methods
.method public final read()I
    .locals 4

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzd:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzc:I

    if-ne v0, v1, :cond_0

    const/4 p0, -0x1

    return p0

    :cond_0
    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzf:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzg:[B

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzh:I

    add-int/2addr v2, v3

    aget-byte v0, v0, v2

    and-int/lit16 v0, v0, 0xff

    .line 2
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzagl;->zzb(I)V

    return v0

    :cond_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    .line 3
    invoke-virtual {v0, v2}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    and-int/lit16 v0, v0, 0xff

    .line 4
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzagl;->zzb(I)V

    return v0
.end method

.method public final read([BII)I
    .locals 3

    .line 5
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzd:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzc:I

    if-ne v0, v1, :cond_0

    const/4 p0, -0x1

    return p0

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    invoke-virtual {v0}, Ljava/nio/Buffer;->limit()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    sub-int/2addr v0, v1

    if-le p3, v0, :cond_1

    move p3, v0

    :cond_1
    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzf:Z

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzg:[B

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzh:I

    add-int/2addr v1, v2

    .line 6
    invoke-static {v0, v1, p1, p2, p3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 7
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzagl;->zzb(I)V

    return p3

    :cond_2
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 8
    invoke-virtual {v0}, Ljava/nio/Buffer;->position()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzagl;->zze:I

    .line 9
    invoke-virtual {v1, v2}, Ljava/nio/Buffer;->position(I)Ljava/nio/Buffer;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 10
    invoke-virtual {v1, p1, p2, p3}, Ljava/nio/ByteBuffer;->get([BII)Ljava/nio/ByteBuffer;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzagl;->zzb:Ljava/nio/ByteBuffer;

    .line 11
    invoke-virtual {p1, v0}, Ljava/nio/Buffer;->position(I)Ljava/nio/Buffer;

    .line 12
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzagl;->zzb(I)V

    return p3
.end method

.method public synthetic transferTo(Ljava/io/OutputStream;)J
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lj$/io/DesugarInputStream;->transferTo(Ljava/io/InputStream;Ljava/io/OutputStream;)J

    move-result-wide p0

    return-wide p0
.end method
