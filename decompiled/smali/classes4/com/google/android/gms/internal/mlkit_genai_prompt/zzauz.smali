.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

.field private final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

.field private final synthetic zzd:Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zzd:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final synthetic run()V
    .locals 3

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauz;->zzd:Ljava/lang/String;

    invoke-virtual {v0, v1, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;Ljava/lang/String;)V

    return-void
.end method
