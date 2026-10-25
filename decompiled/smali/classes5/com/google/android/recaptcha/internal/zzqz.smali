.class final Lcom/google/android/recaptcha/internal/zzqz;
.super Lcom/google/android/recaptcha/internal/zzqy;
.source "SourceFile"


# instance fields
.field zza:Ljava/nio/ByteBuffer;

.field final synthetic zzb:Lcom/google/android/recaptcha/internal/zzra;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzra;I)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqz;->zzb:Lcom/google/android/recaptcha/internal/zzra;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqy;-><init>()V

    const/16 p1, 0x20

    .line 2
    invoke-static {p1}, Ljava/nio/ByteBuffer;->allocate(I)Ljava/nio/ByteBuffer;

    move-result-object p1

    sget-object p2, Ljava/nio/ByteOrder;->LITTLE_ENDIAN:Ljava/nio/ByteOrder;

    invoke-virtual {p1, p2}, Ljava/nio/ByteBuffer;->order(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    return-void
.end method


# virtual methods
.method public final zzc([BII)Lcom/google/android/recaptcha/internal/zzrg;
    .locals 1

    .line 1
    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    invoke-virtual {p2}, Ljava/nio/Buffer;->remaining()I

    move-result p2

    if-ge p2, p3, :cond_0

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    .line 2
    invoke-virtual {p2}, Ljava/nio/Buffer;->capacity()I

    move-result p2

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    .line 3
    invoke-virtual {v0}, Ljava/nio/Buffer;->position()I

    move-result v0

    add-int/2addr v0, p3

    add-int/2addr p2, p2

    .line 4
    invoke-static {p2, v0}, Ljava/lang/Math;->max(II)I

    move-result p2

    .line 5
    invoke-static {p2}, Ljava/nio/ByteBuffer;->allocate(I)Ljava/nio/ByteBuffer;

    move-result-object p2

    sget-object v0, Ljava/nio/ByteOrder;->LITTLE_ENDIAN:Ljava/nio/ByteOrder;

    invoke-virtual {p2, v0}, Ljava/nio/ByteBuffer;->order(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;

    move-result-object p2

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    .line 6
    invoke-virtual {v0}, Ljava/nio/Buffer;->flip()Ljava/nio/Buffer;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    .line 7
    invoke-virtual {p2, v0}, Ljava/nio/ByteBuffer;->put(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    :cond_0
    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    const/4 v0, 0x0

    .line 8
    invoke-virtual {p2, p1, v0, p3}, Ljava/nio/ByteBuffer;->put([BII)Ljava/nio/ByteBuffer;

    return-object p0
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzre;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->array()[B

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzqz;->zza:Ljava/nio/ByteBuffer;

    invoke-virtual {v1}, Ljava/nio/Buffer;->position()I

    move-result v1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqz;->zzb:Lcom/google/android/recaptcha/internal/zzra;

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lcom/google/android/recaptcha/internal/zzqx;->zzb([BII)Lcom/google/android/recaptcha/internal/zzre;

    move-result-object p0

    return-object p0
.end method
