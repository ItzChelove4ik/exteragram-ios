.class final Lcom/google/android/recaptcha/internal/zzqn;
.super Lcom/google/android/recaptcha/internal/zzqb;
.source "SourceFile"


# instance fields
.field private final transient zza:Lcom/google/android/recaptcha/internal/zzpw;

.field private final transient zzb:[Ljava/lang/Object;

.field private final transient zzc:I


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzpw;[Ljava/lang/Object;II)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqb;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqn;->zza:Lcom/google/android/recaptcha/internal/zzpw;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzqn;->zzb:[Ljava/lang/Object;

    iput p4, p0, Lcom/google/android/recaptcha/internal/zzqn;->zzc:I

    return-void
.end method


# virtual methods
.method public final contains(Ljava/lang/Object;)Z
    .locals 2

    .line 1
    instance-of v0, p1, Ljava/util/Map$Entry;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 2
    check-cast p1, Ljava/util/Map$Entry;

    .line 3
    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    .line 4
    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqn;->zza:Lcom/google/android/recaptcha/internal/zzpw;

    .line 5
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzpw;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    return v1
.end method

.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqb;->zzj()Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    const/4 v0, 0x0

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzpt;->zzj(I)Lcom/google/android/recaptcha/internal/zzqv;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzqn;->zzc:I

    return p0
.end method

.method public final zza()Lcom/google/android/recaptcha/internal/zzqu;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqb;->zzj()Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    const/4 v0, 0x0

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzpt;->zzj(I)Lcom/google/android/recaptcha/internal/zzqv;

    move-result-object p0

    return-object p0
.end method

.method public final zze()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public final zzf([Ljava/lang/Object;I)I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqb;->zzj()Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzpq;->zzf([Ljava/lang/Object;I)I

    move-result p0

    return p0
.end method

.method public final zzk()Lcom/google/android/recaptcha/internal/zzpt;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzqm;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzqm;-><init>(Lcom/google/android/recaptcha/internal/zzqn;)V

    return-object v0
.end method

.method public final synthetic zzn()[Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqn;->zzb:[Ljava/lang/Object;

    return-object p0
.end method

.method public final synthetic zzo()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzqn;->zzc:I

    return p0
.end method
