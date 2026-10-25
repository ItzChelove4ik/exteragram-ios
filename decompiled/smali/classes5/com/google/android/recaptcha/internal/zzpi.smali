.class final Lcom/google/android/recaptcha/internal/zzpi;
.super Ljava/util/AbstractCollection;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzpj;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzpj;[B)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpi;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-direct {p0}, Ljava/util/AbstractCollection;-><init>()V

    return-void
.end method


# virtual methods
.method public final clear()V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpi;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->clear()V

    return-void
.end method

.method public final iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpi;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->zzb()Ljava/util/Map;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 2
    invoke-interface {v0}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p0

    return-object p0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzpd;

    .line 3
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzpd;-><init>(Lcom/google/android/recaptcha/internal/zzpj;)V

    return-object v0
.end method

.method public final size()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpi;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpj;->size()I

    move-result p0

    return p0
.end method
