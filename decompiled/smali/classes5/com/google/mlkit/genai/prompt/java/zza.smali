.class final synthetic Lcom/google/mlkit/genai/prompt/java/zza;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/mlkit/genai/prompt/java/zzm;


# direct methods
.method public synthetic constructor <init>(Lcom/google/mlkit/genai/prompt/java/zzm;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zza;->zza:Lcom/google/mlkit/genai/prompt/java/zzm;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zza;->zza:Lcom/google/mlkit/genai/prompt/java/zzm;

    invoke-static {p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzm;->zzc(Lcom/google/mlkit/genai/prompt/java/zzm;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
