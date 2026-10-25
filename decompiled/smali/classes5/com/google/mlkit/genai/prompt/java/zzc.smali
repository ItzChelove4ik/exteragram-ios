.class final synthetic Lcom/google/mlkit/genai/prompt/java/zzc;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/mlkit/genai/prompt/java/zzm;

.field private final synthetic zzb:Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Lcom/google/mlkit/genai/prompt/java/zzm;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzc;->zza:Lcom/google/mlkit/genai/prompt/java/zzm;

    iput-object p2, p0, Lcom/google/mlkit/genai/prompt/java/zzc;->zzb:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 1

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/java/zzc;->zza:Lcom/google/mlkit/genai/prompt/java/zzm;

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzc;->zzb:Ljava/lang/String;

    invoke-static {v0, p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzm;->zze(Lcom/google/mlkit/genai/prompt/java/zzm;Ljava/lang/String;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
