[settingitemsviewlibrary](../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](./index.md)

## Package io.github.kaztakgh.settingitemsviewlibrary

### Types

| [DateItem](-date-item/index.md) | `data class DateItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>日付を選択するアイテムの作成 |
| [HeaderItem](-header-item/index.md) | `data class HeaderItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>ヘッダーの作成 |
| [InputTextItem](-input-text-item/index.md) | `data class InputTextItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>テキストを入力するアイテムの作成 |
| [ItemInterface](-item-interface/index.md) | `interface ItemInterface`<br>設定アイテムの共通要素     省略不可能もしくは非推奨の要素のみ記載する         アイテムを作成するときはこのinterfaceを継承すること |
| [MultiSelectItem](-multi-select-item/index.md) | `data class MultiSelectItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>指定されたものから複数選択するアイテムの作成     SingleSelectItemと同様に、選択するものに画像、音楽などを追加することができる         アイテムを押下したとき、ダイアログを表示する |
| [NormalItemViewHolder](-normal-item-view-holder/index.md) | `open class NormalItemViewHolder : ViewHolder`<br>SettingItemsViewのタイトル・テキスト・アイコンで表示されるアイテムの表示に使用する |
| [SeekBarItem](-seek-bar-item/index.md) | `data class SeekBarItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>SeekBarで値を指定するアイテムの作成 |
| [SettingItemsAdapter](-setting-items-adapter/index.md) | `class SettingItemsAdapter : Adapter<ViewHolder>`<br>RecyclerViewにアイテムを表示するためのアダプター |
| [SettingItemsView](-setting-items-view/index.md) | `class SettingItemsView : RecyclerView` |
| [SingleSelectItem](-single-select-item/index.md) | `data class SingleSelectItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>指定されたものから1つ選択するアイテムの作成     SpinnerItemと比較すると、選択するものに画像、音楽などを追加することができる         アイテムを押下したとき、ダイアログを表示する |
| [SpinnerItem](-spinner-item/index.md) | `data class SpinnerItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>Spinnerで状態を指定するアイテムの作成 |
| [StorageFileSelectItem](-storage-file-select-item/index.md) | `data class StorageFileSelectItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>ファイルを指定するアイテムの作成     Android6.0以降で使用する場合、Permissionの確認が必須になる         それ以前の場合はManifestに使用することを記述する |
| [SwitchItem](-switch-item/index.md) | `data class SwitchItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>状態をON/OFFで指定するアイテムの作成 |
| [TimeItem](-time-item/index.md) | `data class TimeItem : `[`ItemInterface`](-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>時刻を選択するアイテムの作成 |

