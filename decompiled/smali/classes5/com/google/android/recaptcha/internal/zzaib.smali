.class final Lcom/google/android/recaptcha/internal/zzaib;
.super Lcom/google/android/recaptcha/internal/zzahz;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzahz;-><init>()V

    return-void
.end method

.method public static final zzi(Lcom/google/android/recaptcha/internal/zzaia;IJ)V
    .locals 0

    shl-int/lit8 p1, p1, 0x3

    .line 1
    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaia;->zzk(ILjava/lang/Object;)V

    return-void
.end method

.method public static final zzj(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;
    .locals 2

    .line 1
    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zza()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v1

    if-ne v0, v1, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zzb()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v0

    .line 2
    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    :cond_0
    return-object v0
.end method


# virtual methods
.method public final bridge synthetic zza(Ljava/lang/Object;IJ)V
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaia;

    invoke-static {p1, p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzaib;->zzi(Lcom/google/android/recaptcha/internal/zzaia;IJ)V

    return-void
.end method

.method public final bridge synthetic zzb(Ljava/lang/Object;II)V
    .locals 0

    shl-int/lit8 p0, p2, 0x3

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaia;

    or-int/lit8 p0, p0, 0x5

    .line 2
    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzaia;->zzk(ILjava/lang/Object;)V

    return-void
.end method

.method public final bridge synthetic zzc(Ljava/lang/Object;IJ)V
    .locals 0

    shl-int/lit8 p0, p2, 0x3

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaia;

    or-int/lit8 p0, p0, 0x1

    .line 2
    invoke-static {p3, p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-virtual {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzaia;->zzk(ILjava/lang/Object;)V

    return-void
.end method

.method public final bridge synthetic zzd(Ljava/lang/Object;ILcom/google/android/recaptcha/internal/zzaer;)V
    .locals 0

    shl-int/lit8 p0, p2, 0x3

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaia;

    or-int/lit8 p0, p0, 0x2

    .line 2
    invoke-virtual {p1, p0, p3}, Lcom/google/android/recaptcha/internal/zzaia;->zzk(ILjava/lang/Object;)V

    return-void
.end method

.method public final bridge synthetic zze(Ljava/lang/Object;ILjava/lang/Object;)V
    .locals 0

    shl-int/lit8 p0, p2, 0x3

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaia;

    or-int/lit8 p0, p0, 0x3

    check-cast p3, Lcom/google/android/recaptcha/internal/zzaia;

    .line 2
    invoke-virtual {p1, p0, p3}, Lcom/google/android/recaptcha/internal/zzaia;->zzk(ILjava/lang/Object;)V

    return-void
.end method

.method public final synthetic zzf()Ljava/lang/Object;
    .locals 0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zzb()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzg(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaia;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaia;->zzd()V

    return-object p1
.end method
