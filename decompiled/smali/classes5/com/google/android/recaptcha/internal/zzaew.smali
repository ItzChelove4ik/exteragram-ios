.class final Lcom/google/android/recaptcha/internal/zzaew;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzaev;

.field private zzb:I

.field private zzc:I

.field private zzd:I


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzaev;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    iput-object p0, p1, Lcom/google/android/recaptcha/internal/zzaev;->zzd:Ljava/lang/Object;

    return-void
.end method

.method private final zzO(I)V
    .locals 0

    .line 1
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 p0, p0, 0x7

    if-ne p0, p1, :cond_0

    return-void

    .line 2
    :cond_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void
.end method

.method private final zzP(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    .line 2
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzF()V

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzaev;->zzA(I)I

    move-result v1

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzaev;->zza:I

    add-int/lit8 v2, v2, 0x1

    iput v2, v0, Lcom/google/android/recaptcha/internal/zzaev;->zza:I

    .line 4
    invoke-interface {p2, p1, p0, p3}, Lcom/google/android/recaptcha/internal/zzahn;->zzg(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;Lcom/google/android/recaptcha/internal/zzafh;)V

    const/4 p0, 0x0

    .line 5
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzb(I)V

    iget p0, v0, Lcom/google/android/recaptcha/internal/zzaev;->zza:I

    add-int/lit8 p0, p0, -0x1

    iput p0, v0, Lcom/google/android/recaptcha/internal/zzaev;->zza:I

    .line 6
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzaev;->zzB(I)V

    return-void
.end method

.method private final zzQ(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzF()V

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzc:I

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    ushr-int/lit8 v2, v2, 0x3

    shl-int/lit8 v2, v2, 0x3

    or-int/lit8 v2, v2, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzc:I

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzaev;->zzb:I

    add-int/lit8 v2, v2, 0x1

    iput v2, v0, Lcom/google/android/recaptcha/internal/zzaev;->zzb:I

    .line 2
    :try_start_0
    invoke-interface {p2, p1, p0, p3}, Lcom/google/android/recaptcha/internal/zzahn;->zzg(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;Lcom/google/android/recaptcha/internal/zzafh;)V

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    iget p2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzc:I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-ne p1, p2, :cond_0

    .line 5
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    iget p2, p1, Lcom/google/android/recaptcha/internal/zzaev;->zzb:I

    add-int/lit8 p2, p2, -0x1

    iput p2, p1, Lcom/google/android/recaptcha/internal/zzaev;->zzb:I

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzc:I

    return-void

    .line 2
    :cond_0
    :try_start_1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzagk;

    const-string p2, "Failed to parse the message."

    .line 3
    invoke-direct {p1, p2}, Lcom/google/android/recaptcha/internal/zzagk;-><init>(Ljava/lang/String;)V

    .line 4
    throw p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :catchall_0
    move-exception p1

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    iget p3, p2, Lcom/google/android/recaptcha/internal/zzaev;->zzb:I

    add-int/lit8 p3, p3, -0x1

    iput p3, p2, Lcom/google/android/recaptcha/internal/zzaev;->zzb:I

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzc:I

    .line 5
    throw p1
.end method

.method private final zzR(I)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result p0

    if-ne p0, p1, :cond_0

    return-void

    :cond_0
    const-string p0, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method private static final zzS(I)V
    .locals 0

    and-int/lit8 p0, p0, 0x3

    if-nez p0, :cond_0

    return-void

    .line 1
    :cond_0
    const-string p0, "Failed to parse the message."

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method private static final zzT(I)V
    .locals 0

    and-int/lit8 p0, p0, 0x7

    if-nez p0, :cond_0

    return-void

    .line 1
    :cond_0
    const-string p0, "Failed to parse the message."

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzaev;)Lcom/google/android/recaptcha/internal/zzaew;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaev;->zzd:Ljava/lang/Object;

    if-eqz v0, :cond_0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzaew;

    return-object v0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaew;

    .line 2
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzaew;-><init>(Lcom/google/android/recaptcha/internal/zzaev;)V

    return-object v0
.end method


# virtual methods
.method public final zzA(Ljava/util/List;)V
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafw;

    and-int/lit8 p1, v1, 0x7

    if-eqz p1, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 5
    :cond_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzg()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_0

    .line 6
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzg()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eqz v0, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzg()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_5

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzg()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzB(Ljava/util/List;)V
    .locals 4

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzagq;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    const/4 v3, 0x1

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzagq;

    and-int/lit8 p1, v1, 0x7

    if-eq p1, v3, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result p1

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzT(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, p1

    .line 6
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzh()J

    move-result-wide v2

    invoke-virtual {v0, v2, v3}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result p1

    if-lt p1, v1, :cond_0

    goto :goto_1

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzh()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eq v0, v3, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v0

    .line 13
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzT(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, v0

    .line 14
    :cond_5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzh()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v0

    if-lt v0, v1, :cond_5

    goto :goto_1

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzh()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzC(Ljava/util/List;)V
    .locals 6

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 9
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x5

    const/4 v3, 0x2

    if-eqz v0, :cond_5

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafw;

    and-int/lit8 p1, v1, 0x7

    if-eq p1, v3, :cond_3

    if-ne p1, v2, :cond_2

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzi()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_1

    goto/16 :goto_1

    .line 6
    :cond_1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_0

    goto :goto_0

    .line 3
    :cond_2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_3
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result p0

    .line 8
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaew;->zzS(I)V

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result p1

    add-int v5, p1, p0

    .line 9
    :cond_4
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzi()I

    move-result p0

    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result p0

    if-lt p0, v5, :cond_4

    goto :goto_1

    :cond_5
    and-int/lit8 v0, v1, 0x7

    if-eq v0, v3, :cond_8

    if-ne v0, v2, :cond_7

    :cond_6
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzi()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 13
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_a

    .line 14
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_6

    move p1, v0

    .line 6
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    return-void

    .line 11
    :cond_7
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 14
    :cond_8
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v0

    .line 16
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzS(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, v0

    .line 17
    :cond_9
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzi()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v0

    if-lt v0, v1, :cond_9

    :cond_a
    :goto_1
    return-void
.end method

.method public final zzD(Ljava/util/List;)V
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzaei;

    const/4 v1, 0x2

    if-eqz v0, :cond_2

    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 p1, p1, 0x7

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    if-eq p1, v1, :cond_0

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 9
    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzj()Z

    throw v0

    .line 1
    :cond_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzj()Z

    throw v0

    .line 6
    :cond_2
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 v0, v0, 0x7

    if-eqz v0, :cond_5

    if-ne v0, v1, :cond_4

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzj()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_3

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_5
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzj()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_6

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_5

    .line 9
    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_6
    return-void
.end method

.method public final zzE(Ljava/util/List;Z)V
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 v0, v0, 0x7

    const/4 v1, 0x2

    if-ne v0, v1, :cond_6

    .line 2
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzagn;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-nez p2, :cond_2

    .line 3
    check-cast p1, Lcom/google/android/recaptcha/internal/zzagn;

    .line 4
    :cond_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaew;->zzp()Lcom/google/android/recaptcha/internal/zzaer;

    invoke-interface {p1}, Lcom/google/android/recaptcha/internal/zzagn;->zzb()V

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 5
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v0

    if-nez v0, :cond_4

    .line 6
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p2

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p2, v0, :cond_1

    goto :goto_2

    :cond_2
    :goto_0
    if-eqz p2, :cond_3

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaew;->zzm()Ljava/lang/String;

    move-result-object v0

    goto :goto_1

    :cond_3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaew;->zzl()Ljava/lang/String;

    move-result-object v0

    :goto_1
    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_5

    :cond_4
    return-void

    .line 9
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_2

    move p2, v0

    :goto_2
    iput p2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    return-void

    .line 2
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void
.end method

.method public final zzF(Ljava/util/List;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 3

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 v1, v0, 0x7

    const/4 v2, 0x2

    if-ne v1, v2, :cond_3

    :cond_0
    invoke-interface {p2}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object v1

    .line 2
    invoke-direct {p0, v1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzP(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 3
    invoke-interface {p2, v1}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V

    .line 4
    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 5
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v2

    if-nez v2, :cond_2

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    if-eqz v2, :cond_1

    goto :goto_0

    .line 6
    :cond_1
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v1

    if-eq v1, v0, :cond_0

    .line 8
    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_2
    :goto_0
    return-void

    :cond_3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void
.end method

.method public final zzG(Ljava/util/List;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 3
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 v1, v0, 0x7

    const/4 v2, 0x3

    if-ne v1, v2, :cond_3

    :cond_0
    invoke-interface {p2}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object v1

    .line 2
    invoke-direct {p0, v1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzQ(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 3
    invoke-interface {p2, v1}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V

    .line 4
    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 5
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v2

    if-nez v2, :cond_2

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    if-eqz v2, :cond_1

    goto :goto_0

    .line 6
    :cond_1
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v1

    if-eq v1, v0, :cond_0

    .line 8
    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_2
    :goto_0
    return-void

    :cond_3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void
.end method

.method public final zzH(Ljava/util/List;)V
    .locals 2

    .line 3
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 v0, v0, 0x7

    const/4 v1, 0x2

    if-ne v0, v1, :cond_2

    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaew;->zzp()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_1

    return-void

    .line 5
    :cond_1
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_0

    .line 2
    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    return-void

    :cond_2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void
.end method

.method public final zzI(Ljava/util/List;)V
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafw;

    and-int/lit8 p1, v1, 0x7

    if-eqz p1, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 5
    :cond_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_0

    .line 6
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eqz v0, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_5

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzJ(Ljava/util/List;)V
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafw;

    and-int/lit8 p1, v1, 0x7

    if-eqz p1, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 5
    :cond_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzo()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_0

    .line 6
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzo()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eqz v0, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzo()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_5

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzo()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzK(Ljava/util/List;)V
    .locals 6

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 9
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x5

    const/4 v3, 0x2

    if-eqz v0, :cond_5

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafw;

    and-int/lit8 p1, v1, 0x7

    if-eq p1, v3, :cond_3

    if-ne p1, v2, :cond_2

    :cond_0
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzp()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_1

    goto/16 :goto_1

    .line 6
    :cond_1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_0

    goto :goto_0

    .line 3
    :cond_2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_3
    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result p0

    .line 8
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaew;->zzS(I)V

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result p1

    add-int v5, p1, p0

    .line 9
    :cond_4
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzp()I

    move-result p0

    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result p0

    if-lt p0, v5, :cond_4

    goto :goto_1

    :cond_5
    and-int/lit8 v0, v1, 0x7

    if-eq v0, v3, :cond_8

    if-ne v0, v2, :cond_7

    :cond_6
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzp()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 13
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_a

    .line 14
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_6

    move p1, v0

    .line 6
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    return-void

    .line 11
    :cond_7
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 14
    :cond_8
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v0

    .line 16
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzS(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, v0

    .line 17
    :cond_9
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzp()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v0

    if-lt v0, v1, :cond_9

    :cond_a
    :goto_1
    return-void
.end method

.method public final zzL(Ljava/util/List;)V
    .locals 4

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzagq;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    const/4 v3, 0x1

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzagq;

    and-int/lit8 p1, v1, 0x7

    if-eq p1, v3, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result p1

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzT(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, p1

    .line 6
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzq()J

    move-result-wide v2

    invoke-virtual {v0, v2, v3}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result p1

    if-lt p1, v1, :cond_0

    goto :goto_1

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzq()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eq v0, v3, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v0

    .line 13
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzT(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, v0

    .line 14
    :cond_5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzq()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v0

    if-lt v0, v1, :cond_5

    goto :goto_1

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzq()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzM(Ljava/util/List;)V
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafw;

    and-int/lit8 p1, v1, 0x7

    if-eqz p1, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 5
    :cond_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzr()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_0

    .line 6
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzr()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eqz v0, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzr()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_5

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzr()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzN(Ljava/util/List;)V
    .locals 5

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzagq;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzagq;

    and-int/lit8 p1, v1, 0x7

    if-eqz p1, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 5
    :cond_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzs()J

    move-result-wide v3

    invoke-virtual {v0, v3, v4}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_0

    .line 6
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzs()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eqz v0, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzs()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_5

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzs()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzb()I
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    if-eqz v0, :cond_0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v1, 0x0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    :goto_0
    if-eqz v0, :cond_2

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzc:I

    if-ne v0, p0, :cond_1

    goto :goto_1

    :cond_1
    ushr-int/lit8 p0, v0, 0x3

    return p0

    :cond_2
    :goto_1
    const p0, 0x7fffffff

    return p0
.end method

.method public final zzc()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    return p0
.end method

.method public final zzd()D
    .locals 2

    const/4 v0, 0x1

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzc()D

    move-result-wide v0

    return-wide v0
.end method

.method public final zze()F
    .locals 1

    const/4 v0, 0x5

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzd()F

    move-result p0

    return p0
.end method

.method public final zzf()J
    .locals 2

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zze()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzg()J
    .locals 2

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzf()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzh()I
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzg()I

    move-result p0

    return p0
.end method

.method public final zzi()J
    .locals 2

    const/4 v0, 0x1

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzh()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzj()I
    .locals 1

    const/4 v0, 0x5

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzi()I

    move-result p0

    return p0
.end method

.method public final zzk()Z
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzj()Z

    move-result p0

    return p0
.end method

.method public final zzl()Ljava/lang/String;
    .locals 1

    const/4 v0, 0x2

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzk()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zzm()Ljava/lang/String;
    .locals 1

    const/4 v0, 0x2

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzl()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zzn(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 1

    const/4 v0, 0x2

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    .line 2
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzP(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    return-void
.end method

.method public final zzo(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 1

    const/4 v0, 0x3

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    .line 2
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzQ(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    return-void
.end method

.method public final zzp()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 1

    const/4 v0, 0x2

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzm()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0
.end method

.method public final zzq()I
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result p0

    return p0
.end method

.method public final zzr()I
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzo()I

    move-result p0

    return p0
.end method

.method public final zzs()I
    .locals 1

    const/4 v0, 0x5

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzp()I

    move-result p0

    return p0
.end method

.method public final zzt()J
    .locals 2

    const/4 v0, 0x1

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzq()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzu()I
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzr()I

    move-result p0

    return p0
.end method

.method public final zzv()J
    .locals 2

    const/4 v0, 0x0

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzO(I)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzs()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzw(Ljava/util/List;)V
    .locals 4

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafc;

    const/4 v1, 0x2

    const/4 v2, 0x1

    if-eqz v0, :cond_2

    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 p1, p1, 0x7

    const/4 v0, 0x0

    if-eq p1, v2, :cond_1

    if-eq p1, v1, :cond_0

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 9
    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result p1

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzT(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    .line 6
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzc()D

    throw v0

    .line 1
    :cond_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzc()D

    throw v0

    .line 6
    :cond_2
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 v0, v0, 0x7

    if-eq v0, v2, :cond_5

    if-ne v0, v1, :cond_4

    .line 17
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v0

    .line 13
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzT(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, v0

    .line 14
    :cond_3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzc()D

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v0

    if-lt v0, v1, :cond_3

    goto :goto_0

    .line 11
    :cond_4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_5
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzc()D

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_6

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_5

    .line 9
    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_6
    :goto_0
    return-void
.end method

.method public final zzx(Ljava/util/List;)V
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafo;

    const/4 v1, 0x5

    const/4 v2, 0x2

    if-eqz v0, :cond_2

    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 p1, p1, 0x7

    const/4 v0, 0x0

    if-eq p1, v2, :cond_1

    if-eq p1, v1, :cond_0

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzd()F

    throw v0

    .line 6
    :cond_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result p1

    .line 8
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzS(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    .line 9
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzd()F

    throw v0

    :cond_2
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    and-int/lit8 v0, v0, 0x7

    if-eq v0, v2, :cond_5

    if-ne v0, v1, :cond_4

    :cond_3
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzd()F

    move-result v1

    invoke-static {v1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 13
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_7

    .line 14
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_3

    .line 6
    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    return-void

    .line 11
    :cond_4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 14
    :cond_5
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v0

    .line 16
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaew;->zzS(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    add-int/2addr v1, v0

    .line 17
    :cond_6
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzd()F

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v0

    if-lt v0, v1, :cond_6

    :cond_7
    return-void
.end method

.method public final zzy(Ljava/util/List;)V
    .locals 5

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzagq;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzagq;

    and-int/lit8 p1, v1, 0x7

    if-eqz p1, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 5
    :cond_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zze()J

    move-result-wide v3

    invoke-virtual {v0, v3, v4}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_0

    .line 6
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zze()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eqz v0, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zze()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_5

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zze()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method

.method public final zzz(Ljava/util/List;)V
    .locals 5

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzagq;

    .line 6
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    const/4 v2, 0x2

    if-eqz v0, :cond_4

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzagq;

    and-int/lit8 p1, v1, 0x7

    if-eqz p1, :cond_2

    if-ne p1, v2, :cond_1

    .line 9
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 5
    :cond_0
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzf()J

    move-result-wide v3

    invoke-virtual {v0, v3, v4}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_0

    .line 6
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 3
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 1
    :cond_2
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 7
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzf()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    .line 8
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 9
    :cond_3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result p1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq p1, v1, :cond_2

    goto :goto_0

    :cond_4
    and-int/lit8 v0, v1, 0x7

    if-eqz v0, :cond_7

    if-ne v0, v2, :cond_6

    .line 17
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 12
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzn()I

    move-result v1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v2

    add-int/2addr v2, v1

    .line 13
    :cond_5
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzf()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzD()I

    move-result v1

    if-lt v1, v2, :cond_5

    .line 14
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaew;->zzR(I)V

    return-void

    .line 11
    :cond_6
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaew$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 6
    :cond_7
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaew;->zza:Lcom/google/android/recaptcha/internal/zzaev;

    .line 15
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzf()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {p1, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 16
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzC()Z

    move-result v1

    if-nez v1, :cond_8

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaev;->zza()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzb:I

    if-eq v0, v1, :cond_7

    move p1, v0

    .line 9
    :goto_0
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaew;->zzd:I

    :cond_8
    :goto_1
    return-void
.end method
