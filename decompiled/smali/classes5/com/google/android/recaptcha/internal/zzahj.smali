.class final Lcom/google/android/recaptcha/internal/zzahj;
.super Lcom/google/android/recaptcha/internal/zzael;
.source "SourceFile"


# instance fields
.field final zza:Lcom/google/android/recaptcha/internal/zzahl;

.field zzb:Lcom/google/android/recaptcha/internal/zzaen;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzahm;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzahm;)V
    .locals 2

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzahj;->zzc:Lcom/google/android/recaptcha/internal/zzahm;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzael;-><init>()V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzahl;

    const/4 v1, 0x0

    .line 2
    invoke-direct {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzahl;-><init>(Lcom/google/android/recaptcha/internal/zzaer;[B)V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzahj;->zza:Lcom/google/android/recaptcha/internal/zzahl;

    .line 3
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzahj;->zzb()Lcom/google/android/recaptcha/internal/zzaen;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzahj;->zzb:Lcom/google/android/recaptcha/internal/zzaen;

    return-void
.end method

.method private final zzb()Lcom/google/android/recaptcha/internal/zzaen;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahj;->zza:Lcom/google/android/recaptcha/internal/zzahl;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahl;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahl;->zza()Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzq()Lcom/google/android/recaptcha/internal/zzaen;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public final hasNext()Z
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahj;->zzb:Lcom/google/android/recaptcha/internal/zzaen;

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zza()B
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahj;->zzb:Lcom/google/android/recaptcha/internal/zzaen;

    if-eqz v0, :cond_1

    .line 2
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzaen;->zza()B

    move-result v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzahj;->zzb:Lcom/google/android/recaptcha/internal/zzaen;

    .line 3
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-nez v1, :cond_0

    .line 4
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzahj;->zzb()Lcom/google/android/recaptcha/internal/zzaen;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzahj;->zzb:Lcom/google/android/recaptcha/internal/zzaen;

    :cond_0
    return v0

    .line 1
    :cond_1
    invoke-static {}, Lretrofit2/Utils$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return p0
.end method
