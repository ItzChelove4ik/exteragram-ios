.class public final Lorg/telegram/utils/settings/IntSetting;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final defaultValue:I

.field private volatile loaded:Z

.field private final name:Ljava/lang/String;

.field private volatile value:I


# direct methods
.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0

    .line 10
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 11
    iput-object p1, p0, Lorg/telegram/utils/settings/IntSetting;->name:Ljava/lang/String;

    .line 12
    iput p2, p0, Lorg/telegram/utils/settings/IntSetting;->defaultValue:I

    return-void
.end method

.method public static of(Ljava/lang/String;I)Lorg/telegram/utils/settings/IntSetting;
    .locals 1

    .line 16
    new-instance v0, Lorg/telegram/utils/settings/IntSetting;

    invoke-direct {v0, p0, p1}, Lorg/telegram/utils/settings/IntSetting;-><init>(Ljava/lang/String;I)V

    return-object v0
.end method


# virtual methods
.method public get()I
    .locals 3

    .line 20
    iget-boolean v0, p0, Lorg/telegram/utils/settings/IntSetting;->loaded:Z

    if-nez v0, :cond_1

    .line 21
    monitor-enter p0

    .line 22
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/settings/IntSetting;->loaded:Z

    if-nez v0, :cond_0

    .line 23
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences;->get()Landroid/content/SharedPreferences;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/utils/settings/IntSetting;->name:Ljava/lang/String;

    iget v2, p0, Lorg/telegram/utils/settings/IntSetting;->defaultValue:I

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    iput v0, p0, Lorg/telegram/utils/settings/IntSetting;->value:I

    const/4 v0, 0x1

    .line 24
    iput-boolean v0, p0, Lorg/telegram/utils/settings/IntSetting;->loaded:Z

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 26
    :cond_0
    :goto_0
    monitor-exit p0

    goto :goto_2

    :goto_1
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    .line 28
    :cond_1
    :goto_2
    iget p0, p0, Lorg/telegram/utils/settings/IntSetting;->value:I

    return p0
.end method

.method public declared-synchronized set(I)V
    .locals 2

    monitor-enter p0

    .line 32
    :try_start_0
    iput p1, p0, Lorg/telegram/utils/settings/IntSetting;->value:I

    const/4 v0, 0x1

    .line 33
    iput-boolean v0, p0, Lorg/telegram/utils/settings/IntSetting;->loaded:Z

    .line 34
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences;->get()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/utils/settings/IntSetting;->name:Ljava/lang/String;

    invoke-interface {v0, v1, p1}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 35
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method
