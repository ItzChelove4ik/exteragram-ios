.class public abstract Lcom/google/android/recaptcha/internal/zzafq;
.super Lcom/google/android/recaptcha/internal/zzaeb;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<MessageType:",
        "Lcom/google/android/recaptcha/internal/zzafv<",
        "TMessageType;TBuilderType;>;BuilderType:",
        "Lcom/google/android/recaptcha/internal/zzafq<",
        "TMessageType;TBuilderType;>;>",
        "Lcom/google/android/recaptcha/internal/zzaeb<",
        "TMessageType;TBuilderType;>;"
    }
.end annotation


# instance fields
.field protected zza:Lcom/google/android/recaptcha/internal/zzafv;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzafv;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzafv;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TMessageType;)V"
        }
    .end annotation

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaeb;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzafq;->zzb:Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzC()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    return-void

    .line 1
    :cond_0
    const-string p0, "Default instance must be immutable."

    .line 3
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method private static zza(Lcom/google/android/recaptcha/internal/zzafv;Lcom/google/android/recaptcha/internal/zzafv;)V
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v0

    .line 2
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 1
    invoke-interface {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public final bridge synthetic clone()Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzn()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object p0

    return-object p0
.end method

.method public final zzab()Z
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    const/4 v0, 0x0

    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzY(Lcom/google/android/recaptcha/internal/zzafv;Z)Z

    move-result p0

    return p0
.end method

.method public final zzk()V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzl()V

    :cond_0
    return-void
.end method

.method public zzl()V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zzb:Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzC()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzafq;->zza(Lcom/google/android/recaptcha/internal/zzafv;Lcom/google/android/recaptcha/internal/zzafv;)V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    return-void
.end method

.method public final zzn()Lcom/google/android/recaptcha/internal/zzafq;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zzb:Lcom/google/android/recaptcha/internal/zzafv;

    const/4 v1, 0x5

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2, v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafq;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzo()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    iput-object p0, v0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    return-object v0
.end method

.method public zzo()Lcom/google/android/recaptcha/internal/zzafv;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TMessageType;"
        }
    .end annotation

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    if-nez v0, :cond_0

    return-object v1

    .line 2
    :cond_0
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzE()V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    return-object p0
.end method

.method public final zzp()Lcom/google/android/recaptcha/internal/zzafv;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TMessageType;"
        }
    .end annotation

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzo()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzab()Z

    move-result v0

    if-eqz v0, :cond_0

    return-object p0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzahy;

    .line 3
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzahy;-><init>(Lcom/google/android/recaptcha/internal/zzagx;)V

    .line 4
    throw v0
.end method

.method public final zzq(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafq;
    .locals 1

    if-eqz p1, :cond_1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zzb:Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const-string p0, "mergeFrom(MessageLite) can only merge messages of the same type."

    .line 2
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zzb:Lcom/google/android/recaptcha/internal/zzafv;

    .line 3
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzafv;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    if-nez v0, :cond_2

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzl()V

    :cond_2
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 6
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzafq;->zza(Lcom/google/android/recaptcha/internal/zzafv;Lcom/google/android/recaptcha/internal/zzafv;)V

    :cond_3
    return-object p0
.end method

.method public bridge synthetic zzr()Lcom/google/android/recaptcha/internal/zzagx;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzo()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    return-object p0
.end method
