[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [NormalItemViewHolder](./index.md)

# NormalItemViewHolder

`open class NormalItemViewHolder : ViewHolder`

SettingItemsViewのタイトル・テキスト・アイコンで表示されるアイテムの表示に使用する

### Types

| [ItemClickListener](-item-click-listener/index.md) | `interface ItemClickListener`<br>アイテムをクリックしたときのリスナー |

### Constructors

| [&lt;init&gt;](-init-.md) | `NormalItemViewHolder(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`)`<br>通常のアイテムのレイアウトを呼び出す |

### Properties

| [bmpIcon](bmp-icon.md) | `var bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`?`<br>アイコン |
| [iconView](icon-view.md) | `open val iconView: `[`ImageView`](https://developer.android.com/reference/android/widget/ImageView.html) |
| [isSelectable](is-selectable.md) | `open var isSelectable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>選択可能であるか |
| [itemClickListener](item-click-listener.md) | `open var itemClickListener: `[`ItemClickListener`](-item-click-listener/index.md)`?`<br>アイテムをクリックしたときのリスナー |
| [text](text.md) | `open var text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>テキスト |
| [textView](text-view.md) | `open val textView: `[`TextView`](https://developer.android.com/reference/android/widget/TextView.html) |
| [title](title.md) | `var title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タイトル |
| [titleView](title-view.md) | `open val titleView: `[`TextView`](https://developer.android.com/reference/android/widget/TextView.html) |

