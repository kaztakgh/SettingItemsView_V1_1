[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SettingItemsAdapter](./index.md)

# SettingItemsAdapter

`class SettingItemsAdapter : Adapter<ViewHolder>`

RecyclerViewにアイテムを表示するためのアダプター

### Constructors

| [&lt;init&gt;](-init-.md) | `SettingItemsAdapter(itemsList: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ItemInterface`](../-item-interface/index.md)`>, fragment: Fragment? = null, fragmentManager: FragmentManager? = null)`<br>RecyclerViewにアイテムを表示するためのアダプター |

### Functions

| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>アダプターに登録されたリストの要素数を返す |
| [getItemFromRequestCode](get-item-from-request-code.md) | `fun getItemFromRequestCode(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ItemInterface`](../-item-interface/index.md)<br>リクエストコードからアダプター内アイテムを取得する 取得した際にアダプター内の状態を利用するには、Activity/Fragmentでキャストを行うこと |
| [getItemViewType](get-item-view-type.md) | `fun getItemViewType(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Return the view type of the item at `position` for the purposes of view recycling. |
| [getItemsList](get-items-list.md) | `fun getItemsList(): `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ItemInterface`](../-item-interface/index.md)`>`<br>リストのデータを取得する |
| [onAttachedToRecyclerView](on-attached-to-recycler-view.md) | `fun onAttachedToRecyclerView(recyclerView: RecyclerView): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called by RecyclerView when it starts observing this Adapter. |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: ViewHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called by RecyclerView to display the data at the specified position. This method should update the contents of the [ViewHolder.itemView](#) to reflect the item at the given position. |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ViewHolder`<br>Called when RecyclerView needs a new [ViewHolder](#) of the given type to represent an item. |
| [onDetachedFromRecyclerView](on-detached-from-recycler-view.md) | `fun onDetachedFromRecyclerView(recyclerView: RecyclerView): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called by RecyclerView when it stops observing this Adapter. |
| [update](update.md) | `fun update(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, item: `[`ItemInterface`](../-item-interface/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>指定したタグを持つアイテムの更新 |

### Companion Object Properties

| [VIEW_TYPE_HEADER](-v-i-e-w_-t-y-p-e_-h-e-a-d-e-r.md) | `const val VIEW_TYPE_HEADER: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>ヘッダー |
| [VIEW_TYPE_INPUT_TEXT](-v-i-e-w_-t-y-p-e_-i-n-p-u-t_-t-e-x-t.md) | `const val VIEW_TYPE_INPUT_TEXT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>文字入力 |
| [VIEW_TYPE_SEEKBAR](-v-i-e-w_-t-y-p-e_-s-e-e-k-b-a-r.md) | `const val VIEW_TYPE_SEEKBAR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>シークバーでの数値選択 |
| [VIEW_TYPE_SPINNER](-v-i-e-w_-t-y-p-e_-s-p-i-n-n-e-r.md) | `const val VIEW_TYPE_SPINNER: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>スピナーによる選択 |
| [VIEW_TYPE_SWITCH](-v-i-e-w_-t-y-p-e_-s-w-i-t-c-h.md) | `const val VIEW_TYPE_SWITCH: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>スイッチによる選択 |
| [VIEW_TYPE_TITLE_AND_TEXT](-v-i-e-w_-t-y-p-e_-t-i-t-l-e_-a-n-d_-t-e-x-t.md) | `const val VIEW_TYPE_TITLE_AND_TEXT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>通常(タイトルとテキスト) |

