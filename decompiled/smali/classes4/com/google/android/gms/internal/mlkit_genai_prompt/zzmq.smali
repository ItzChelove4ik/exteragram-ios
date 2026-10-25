.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlg;
.source "SourceFile"

# interfaces
.implements Ljava/io/Serializable;


# static fields
.field public static final synthetic $r8$clinit:I

.field private static final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

.field private static final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;


# instance fields
.field private final transient zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->$r8$clinit:I

    .line 2
    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzng;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    .line 3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzl(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-direct {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlg;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-void
.end method

.method private constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlg;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-void
.end method

.method public static zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    return-object v0
.end method

.method public static zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    return-object v0
.end method


# virtual methods
.method public final bridge synthetic zzc()Ljava/util/Set;
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-virtual {p0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zznm;

    return-object p0

    .line 3
    :cond_0
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;

    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->$r8$clinit:I

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznd;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zznb;

    invoke-direct {v0, p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Ljava/util/Comparator;)V

    return-object v0
.end method
