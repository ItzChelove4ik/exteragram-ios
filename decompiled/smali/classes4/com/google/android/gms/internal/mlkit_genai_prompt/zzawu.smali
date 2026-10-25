.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavn;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<MessageType:",
        "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy<",
        "TMessageType;TBuilderType;>;BuilderType:",
        "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu<",
        "TMessageType;TBuilderType;>;>",
        "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavn<",
        "TMessageType;TBuilderType;>;"
    }
.end annotation


# instance fields
.field protected zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TMessageType;)V"
        }
    .end annotation

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavn;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzE()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzG()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    return-void

    .line 1
    :cond_0
    const-string p0, "Default instance must be immutable."

    .line 3
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method private static zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;

    move-result-object v0

    .line 2
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zzb(Ljava/lang/Class;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v0

    .line 1
    invoke-interface {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public final bridge synthetic clone()Ljava/lang/Object;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzE()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;

    move-result-object p0

    return-object p0
.end method

.method public final zzB()V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzE()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzC()V

    :cond_0
    return-void
.end method

.method public zzC()V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzG()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    .line 2
    invoke-static {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    return-void
.end method

.method public final zzE()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    const/4 v1, 0x5

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzb(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzF()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p0

    iput-object p0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    return-object v0
.end method

.method public zzF()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TMessageType;"
        }
    .end annotation

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzE()Z

    move-result v0

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    if-nez v0, :cond_0

    return-object v1

    .line 2
    :cond_0
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzJ()V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    return-object p0
.end method

.method public final zzG(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 3
    invoke-virtual {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    .line 4
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzE()Z

    move-result v0

    if-nez v0, :cond_0

    .line 5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzC()V

    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    .line 6
    invoke-static {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V

    :cond_1
    return-object p0

    .line 1
    :cond_2
    const-string p0, "mergeFrom(MessageLite) can only merge messages of the same type."

    .line 2
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public bridge synthetic zzH()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawu;->zzF()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p0

    return-object p0
.end method
