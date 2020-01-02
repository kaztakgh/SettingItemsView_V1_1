[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SettingItemsAdapter](index.md) / [onBindViewHolder](./on-bind-view-holder.md)

# onBindViewHolder

`fun onBindViewHolder(holder: ViewHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called by RecyclerView to display the data at the specified position. This method should
update the contents of the [ViewHolder.itemView](#) to reflect the item at the given
position.

Note that unlike [android.widget.ListView](https://developer.android.com/reference/android/widget/ListView.html), RecyclerView will not call this method
again if the position of the item changes in the data set unless the item itself is
invalidated or the new position cannot be determined. For this reason, you should only
use the `position` parameter while acquiring the related data item inside
this method and should not keep a copy of it. If you need the position of an item later
on (e.g. in a click listener), use [ViewHolder.getAdapterPosition](#) which will
have the updated adapter position.

Override [.onBindViewHolder](#) instead if Adapter can
handle efficient partial bind.

### Parameters

`holder` - アイテムを表示するビューホルダー

`position` - アイテムの表示位置(0から開始)