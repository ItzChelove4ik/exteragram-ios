.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyk;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;


# instance fields
.field private final synthetic zza:Lcom/google/mlkit/genai/common/StreamingCallback;


# direct methods
.method public synthetic constructor <init>(Lcom/google/mlkit/genai/common/StreamingCallback;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyk;->zza:Lcom/google/mlkit/genai/common/StreamingCallback;

    return-void
.end method


# virtual methods
.method public final synthetic zza(ILjava/lang/String;)V
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyk;->zza:Lcom/google/mlkit/genai/common/StreamingCallback;

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    invoke-interface {p0, p2}, Lcom/google/mlkit/genai/common/StreamingCallback;->onNewThought(Ljava/lang/String;)V

    return-void

    .line 2
    :cond_0
    invoke-interface {p0, p2}, Lcom/google/mlkit/genai/common/StreamingCallback;->onNewText(Ljava/lang/String;)V

    return-void
.end method
