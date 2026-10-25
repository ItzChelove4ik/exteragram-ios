.class final Lcom/google/android/recaptcha/internal/zzqm;
.super Lcom/google/android/recaptcha/internal/zzpt;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzqn;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzqn;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqm;->zza:Lcom/google/android/recaptcha/internal/zzqn;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzpt;-><init>()V

    return-void
.end method


# virtual methods
.method public final bridge synthetic get(I)Ljava/lang/Object;
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqm;->zza:Lcom/google/android/recaptcha/internal/zzqn;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqn;->zzo()I

    move-result v0

    const-string v1, "index"

    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzoq;->zze(IILjava/lang/String;)I

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqn;->zzn()[Ljava/lang/Object;

    move-result-object v0

    add-int/2addr p1, p1

    .line 2
    aget-object v0, v0, p1

    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqn;->zzn()[Ljava/lang/Object;

    move-result-object p0

    add-int/lit8 p1, p1, 0x1

    .line 3
    aget-object p0, p0, p1

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance p1, Ljava/util/AbstractMap$SimpleImmutableEntry;

    .line 4
    invoke-direct {p1, v0, p0}, Ljava/util/AbstractMap$SimpleImmutableEntry;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object p1
.end method

.method public final size()I
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqm;->zza:Lcom/google/android/recaptcha/internal/zzqn;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqn;->zzo()I

    move-result p0

    return p0
.end method
