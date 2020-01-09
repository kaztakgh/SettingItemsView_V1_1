[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SettingItemsView](./index.md)

# SettingItemsView

`class SettingItemsView : RecyclerView`

### Constructors

| [&lt;init&gt;](-init-.md) | `SettingItemsView(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)`<br>コンストラクタ`SettingItemsView(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?)` |

### Properties

| [fragment](fragment.md) | `var fragment: Fragment?`<br>Fragment上で表示する場合に、現在のFragmentを指定する |
| [fragmentManager](fragment-manager.md) | `var fragmentManager: FragmentManager?`<br>Fragmentを使用する際、FragmentManagerも指定すること |
| [itemsList](items-list.md) | `var itemsList: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ItemInterface`](../-item-interface/index.md)`>`<br>ItemInterface型のArrayList 表示するアイテムを挿入することで画面上に表示される |

### Functions

| [getSettingItemsAdapter](get-setting-items-adapter.md) | `fun getSettingItemsAdapter(): `[`SettingItemsAdapter`](../-setting-items-adapter/index.md)<br>adapterの取得 |
| [onAttachedToWindow](on-attached-to-window.md) | `fun onAttachedToWindow(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>画面にセットしたときに行う処理 |
| [onDetachedFromWindow](on-detached-from-window.md) | `fun onDetachedFromWindow(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>画面から離れるときに行う処理 |
| [onRestoreInstanceState](on-restore-instance-state.md) | `fun onRestoreInstanceState(state: `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>状態を復元するときの処理 |
| [onSaveInstanceState](on-save-instance-state.md) | `fun onSaveInstanceState(): `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)`?`<br>回転などで状態を保存するときの処理 |

