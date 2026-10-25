.class final synthetic Lcom/google/mlkit/genai/prompt/java/zzv;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/mlkit/genai/prompt/java/zzal;

.field private final synthetic zzb:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;


# direct methods
.method public synthetic constructor <init>(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzv;->zza:Lcom/google/mlkit/genai/prompt/java/zzal;

    iput-object p2, p0, Lcom/google/mlkit/genai/prompt/java/zzv;->zzb:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 1

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzv;->zza:Lcom/google/mlkit/genai/prompt/java/zzal;

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzv;->zzb:Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    invoke-static {v0, p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzal;->zzf(Lcom/google/mlkit/genai/prompt/java/zzal;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
