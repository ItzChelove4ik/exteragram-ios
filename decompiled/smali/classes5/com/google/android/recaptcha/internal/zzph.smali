.class final Lcom/google/android/recaptcha/internal/zzph;
.super Lcom/google/android/recaptcha/internal/zzox;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzpj;

.field private final zzb:Ljava/lang/Object;

.field private zzc:I


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzpj;I)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzph;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzox;-><init>()V

    .line 2
    invoke-virtual {p1, p2}, Lcom/google/android/recaptcha/internal/zzpj;->zzn(I)Ljava/lang/Object;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzph;->zzb:Ljava/lang/Object;

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzph;->zzc:I

    return-void
.end method

.method private final zza()V
    .locals 3

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzc:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_1

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzph;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzpj;->size()I

    move-result v2

    if-ge v0, v2, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzb:Ljava/lang/Object;

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzph;->zzc:I

    .line 2
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzpj;->zzn(I)Ljava/lang/Object;

    move-result-object v1

    invoke-static {v0, v1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    return-void

    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzph;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzph;->zzb:Ljava/lang/Object;

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzpj;->zzh(Ljava/lang/Object;)I

    move-result v0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzc:I

    return-void
.end method


# virtual methods
.method public final getKey()Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzb:Ljava/lang/Object;

    return-object p0
.end method

.method public final getValue()Ljava/lang/Object;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzph;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzpj;->zzb()Ljava/util/Map;

    move-result-object v1

    if-eqz v1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzb:Ljava/lang/Object;

    .line 2
    invoke-interface {v1, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 3
    :cond_0
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzph;->zza()V

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzc:I

    const/4 v1, -0x1

    if-ne p0, v1, :cond_1

    const/4 p0, 0x0

    return-object p0

    .line 4
    :cond_1
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzo(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final setValue(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzph;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzpj;->zzb()Ljava/util/Map;

    move-result-object v1

    if-eqz v1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzb:Ljava/lang/Object;

    .line 2
    invoke-interface {v1, p0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 3
    :cond_0
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzph;->zza()V

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzph;->zzc:I

    const/4 v2, -0x1

    if-ne v1, v2, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzb:Ljava/lang/Object;

    .line 4
    invoke-virtual {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzpj;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x0

    return-object p0

    .line 5
    :cond_1
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzpj;->zzo(I)Ljava/lang/Object;

    move-result-object v1

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzph;->zzc:I

    .line 6
    invoke-virtual {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzpj;->zzp(ILjava/lang/Object;)V

    return-object v1
.end method
