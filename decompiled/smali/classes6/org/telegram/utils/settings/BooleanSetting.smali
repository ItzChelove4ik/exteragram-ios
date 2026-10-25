.class public final Lorg/telegram/utils/settings/BooleanSetting;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final defaultValue:Z

.field private volatile loaded:Z

.field private final name:Ljava/lang/String;

.field private volatile value:Z


# direct methods
.method private constructor <init>(Ljava/lang/String;Z)V
    .locals 0

    .line 10
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 11
    iput-object p1, p0, Lorg/telegram/utils/settings/BooleanSetting;->name:Ljava/lang/String;

    .line 12
    iput-boolean p2, p0, Lorg/telegram/utils/settings/BooleanSetting;->defaultValue:Z

    return-void
.end method

.method public static of(Ljava/lang/String;Z)Lorg/telegram/utils/settings/BooleanSetting;
    .locals 1

    .line 16
    new-instance v0, Lorg/telegram/utils/settings/BooleanSetting;

    invoke-direct {v0, p0, p1}, Lorg/telegram/utils/settings/BooleanSetting;-><init>(Ljava/lang/String;Z)V

    return-object v0
.end method

.method private setLocked(Z)V
    .locals 1

    .line 46
    iput-boolean p1, p0, Lorg/telegram/utils/settings/BooleanSetting;->value:Z

    const/4 v0, 0x1

    .line 47
    iput-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->loaded:Z

    .line 48
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences;->get()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    iget-object p0, p0, Lorg/telegram/utils/settings/BooleanSetting;->name:Ljava/lang/String;

    invoke-interface {v0, p0, p1}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method


# virtual methods
.method public get()Z
    .locals 3

    .line 20
    iget-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->loaded:Z

    if-nez v0, :cond_1

    .line 21
    monitor-enter p0

    .line 22
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->loaded:Z

    if-nez v0, :cond_0

    .line 23
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences;->get()Landroid/content/SharedPreferences;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/utils/settings/BooleanSetting;->name:Ljava/lang/String;

    iget-boolean v2, p0, Lorg/telegram/utils/settings/BooleanSetting;->defaultValue:Z

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->value:Z

    const/4 v0, 0x1

    .line 24
    iput-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->loaded:Z

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
    iget-boolean p0, p0, Lorg/telegram/utils/settings/BooleanSetting;->value:Z

    return p0
.end method

.method public declared-synchronized set(Z)V
    .locals 0

    monitor-enter p0

    .line 32
    :try_start_0
    invoke-direct {p0, p1}, Lorg/telegram/utils/settings/BooleanSetting;->setLocked(Z)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 33
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

.method public declared-synchronized toggle()Z
    .locals 4

    monitor-enter p0

    .line 36
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->loaded:Z

    const/4 v1, 0x1

    if-nez v0, :cond_0

    .line 37
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences;->get()Landroid/content/SharedPreferences;

    move-result-object v0

    iget-object v2, p0, Lorg/telegram/utils/settings/BooleanSetting;->name:Ljava/lang/String;

    iget-boolean v3, p0, Lorg/telegram/utils/settings/BooleanSetting;->defaultValue:Z

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->value:Z

    .line 38
    iput-boolean v1, p0, Lorg/telegram/utils/settings/BooleanSetting;->loaded:Z

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 40
    :cond_0
    :goto_0
    iget-boolean v0, p0, Lorg/telegram/utils/settings/BooleanSetting;->value:Z

    xor-int/2addr v0, v1

    .line 41
    invoke-direct {p0, v0}, Lorg/telegram/utils/settings/BooleanSetting;->setLocked(Z)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 42
    monitor-exit p0

    return v0

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method
