.class public final Lcom/google/android/recaptcha/internal/zzk;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzj;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzv;I)B
    .locals 0

    invoke-virtual {p1, p2}, Lcom/google/android/recaptcha/internal/zzv;->zzb(I)B

    move-result p0

    return p0
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzv;II)Lcom/google/android/recaptcha/internal/zzv;
    .locals 0

    if-ltz p2, :cond_0

    if-gt p2, p3, :cond_0

    iget-object p0, p1, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length p1, p0

    if-gt p3, p1, :cond_0

    if-gt p2, p3, :cond_0

    if-gt p3, p1, :cond_0

    sub-int/2addr p3, p2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzv;

    invoke-static {p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzv;->zzh([BII)[B

    move-result-object p0

    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzv;-><init>([B)V

    return-object p1

    :cond_0
    new-instance p0, Ljava/lang/IndexOutOfBoundsException;

    invoke-direct {p0}, Ljava/lang/IndexOutOfBoundsException;-><init>()V

    throw p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzj;
    .locals 0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzk;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzk;-><init>()V

    return-object p0
.end method
