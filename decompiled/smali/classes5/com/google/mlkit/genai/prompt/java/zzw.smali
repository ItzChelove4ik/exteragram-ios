.class final synthetic Lcom/google/mlkit/genai/prompt/java/zzw;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/mlkit/genai/prompt/java/zzal;

.field private final synthetic zzb:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

.field private final synthetic zzc:Lcom/google/mlkit/genai/common/StreamingCallback;


# direct methods
.method public synthetic constructor <init>(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzw;->zza:Lcom/google/mlkit/genai/prompt/java/zzal;

    iput-object p2, p0, Lcom/google/mlkit/genai/prompt/java/zzw;->zzb:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iput-object p3, p0, Lcom/google/mlkit/genai/prompt/java/zzw;->zzc:Lcom/google/mlkit/genai/common/StreamingCallback;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 2

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzw;->zza:Lcom/google/mlkit/genai/prompt/java/zzal;

    iget-object v1, p0, Lcom/google/mlkit/genai/prompt/java/zzw;->zzb:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzw;->zzc:Lcom/google/mlkit/genai/common/StreamingCallback;

    invoke-static {v0, v1, p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzal;->zzg(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
