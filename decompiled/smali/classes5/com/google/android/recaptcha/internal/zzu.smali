.class final Lcom/google/android/recaptcha/internal/zzu;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Comparator;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 3

    check-cast p1, Lcom/google/android/recaptcha/internal/zzv;

    check-cast p2, Lcom/google/android/recaptcha/internal/zzv;

    const/4 p0, 0x0

    move v0, p0

    :goto_0
    iget-object v1, p1, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v1, v1

    if-ge p0, v1, :cond_1

    iget-object v2, p2, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v2, v2

    if-ge v0, v2, :cond_1

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzv;->zzb(I)B

    move-result v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzv;->zzg(B)I

    move-result v1

    invoke-virtual {p2, v0}, Lcom/google/android/recaptcha/internal/zzv;->zzb(I)B

    move-result v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzv;->zzg(B)I

    move-result v2

    invoke-static {v1, v2}, Ljava/lang/Integer;->compare(II)I

    move-result v1

    if-eqz v1, :cond_0

    return v1

    :cond_0
    add-int/lit8 p0, p0, 0x1

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    iget-object p0, p2, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length p0, p0

    invoke-static {v1, p0}, Ljava/lang/Integer;->compare(II)I

    move-result p0

    return p0
.end method
