.class final synthetic Lcom/google/mlkit/genai/prompt/java/zzac;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/mlkit/genai/prompt/java/zzal;


# direct methods
.method public synthetic constructor <init>(Lcom/google/mlkit/genai/prompt/java/zzal;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/java/zzac;->zza:Lcom/google/mlkit/genai/prompt/java/zzal;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/java/zzac;->zza:Lcom/google/mlkit/genai/prompt/java/zzal;

    invoke-static {p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzal;->zzb(Lcom/google/mlkit/genai/prompt/java/zzal;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
