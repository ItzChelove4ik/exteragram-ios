.class final Lcom/google/android/recaptcha/internal/zzahl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Iterator;


# instance fields
.field private final zza:Ljava/util/ArrayDeque;

.field private zzb:Lcom/google/android/recaptcha/internal/zzaeo;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzaer;[B)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    instance-of p2, p1, Lcom/google/android/recaptcha/internal/zzahm;

    if-eqz p2, :cond_0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzahm;

    new-instance p2, Ljava/util/ArrayDeque;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzahm;->zzo()I

    move-result v0

    invoke-direct {p2, v0}, Ljava/util/ArrayDeque;-><init>(I)V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzahl;->zza:Ljava/util/ArrayDeque;

    .line 3
    invoke-virtual {p2, p1}, Ljava/util/ArrayDeque;->push(Ljava/lang/Object;)V

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzahm;->zzn()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzahl;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzahl;->zzb:Lcom/google/android/recaptcha/internal/zzaeo;

    return-void

    :cond_0
    const/4 p2, 0x0

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzahl;->zza:Ljava/util/ArrayDeque;

    .line 5
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaeo;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzahl;->zzb:Lcom/google/android/recaptcha/internal/zzaeo;

    return-void
.end method

.method private final zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaeo;
    .locals 1

    .line 1
    :goto_0
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzahm;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzahm;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahl;->zza:Ljava/util/ArrayDeque;

    .line 2
    invoke-virtual {v0, p1}, Ljava/util/ArrayDeque;->push(Ljava/lang/Object;)V

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzahm;->zzn()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    goto :goto_0

    .line 4
    :cond_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaeo;

    return-object p1
.end method


# virtual methods
.method public final hasNext()Z
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahl;->zzb:Lcom/google/android/recaptcha/internal/zzaeo;

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final bridge synthetic next()Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahl;->zza()Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object p0

    return-object p0
.end method

.method public final remove()V
    .locals 0

    .line 1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p0
.end method

.method public final zza()Lcom/google/android/recaptcha/internal/zzaeo;
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahl;->zzb:Lcom/google/android/recaptcha/internal/zzaeo;

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    :cond_0
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzahl;->zza:Ljava/util/ArrayDeque;

    if-eqz v2, :cond_2

    .line 2
    invoke-virtual {v2}, Ljava/util/ArrayDeque;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_1

    goto :goto_0

    .line 3
    :cond_1
    invoke-virtual {v2}, Ljava/util/ArrayDeque;->pop()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzahm;

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzahm;->zzD()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzahl;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object v2

    .line 4
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzr()Z

    move-result v3

    if-nez v3, :cond_0

    move-object v1, v2

    .line 2
    :cond_2
    :goto_0
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzahl;->zzb:Lcom/google/android/recaptcha/internal/zzaeo;

    return-object v0

    .line 1
    :cond_3
    invoke-static {}, Lretrofit2/Utils$$ExternalSyntheticBUOutline0;->m()V

    return-object v1
.end method
